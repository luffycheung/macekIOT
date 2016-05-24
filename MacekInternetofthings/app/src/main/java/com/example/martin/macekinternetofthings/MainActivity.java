package com.example.martin.macekinternetofthings;




import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Region;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
//import org.ajoberstar.*;
//import com.example.robinhood.*;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
//import com.robinhood.spark.SparkAdapter;
//import com.robinhood.spark.SparkView;

import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.ServiceConfigurationError;

import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity {

    private Socket socket;

    public static int SERVERPORT1 = 90;
    public static String SERVER_IP1 = "192.168.10.120";
    public static int sec = 20;
    private int SERVERPORT2 = 43333;
    private String SERVER_IP2 = "192.168.10.65";
    private String command = "";
    private String command_sw = "";
    private String PWMcommand = "";
    private String PWMcommand1 = "";
    private String PWMcommand2 = "";
    //StringBuffer response = new StringBuffer();
    private String response_sw = "aa";
    private String response_tp;
    private Integer lastcolor = Color.rgb(0,30,255);
    public static float[]numb=new float[3];
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("internet of things controller");

        final int delay = 90;

        //region findView

        final SeekBar seekBarG = (SeekBar) findViewById(R.id.seekBar);
        final SeekBar seekBarB = (SeekBar) findViewById(R.id.seekBarBlue);
        final SeekBar seekBarR = (SeekBar) findViewById(R.id.seekBarRed);
        final TextView testtxt = (TextView) findViewById(R.id.textView12);
        final EditText ventalarm = (EditText) findViewById(R.id.ventAlarm);
        final SparkView sparkView = (SparkView) findViewById(R.id.sparkview);
        final SparkView sparkView1 = (SparkView) findViewById(R.id.sparkview2);
        final SparkView sparkView2 = (SparkView) findViewById(R.id.sparkview3);

        Switch sw = (Switch) findViewById(R.id.switch1);
        Button off = (Button) findViewById(R.id.button2);
        Button selectClr = (Button) findViewById(R.id.button7);
        Button all = (Button) findViewById(R.id.button6);
        Button test = (Button) findViewById(R.id.button);

        //endregion


        sparkView1.setLineColor(getResources().getColor(R.color.colorGraph2));
        sparkView2.setLineColor(getResources().getColor(R.color.colorGraph3));

        numb[0]=2;
        numb[1]=1;
        numb[2]=3;
        sparkView.setAdapter(new MyAdapter(numb));


        //region SEEKBARY
        seekBarG.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int prevVal;
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                  prevVal = seekBarG.getProgress();
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                //int progress = seekBar1.getProgress();
                if (abs(prevVal-progress)>10) {

                    PWMcommand = ("PWMD5=" + progress);
                    new Thread(new PWMThread()).start();
                    prevVal = progress;
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //testtxt.setText(progress);
                }

            }
        });

        seekBarB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int prevVal;
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                prevVal = seekBarB.getProgress();
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                //int progress = seekBar1.getProgress();
                if (abs(prevVal-progress)>10) {
                PWMcommand = ("PWMD6=" + progress);
                new Thread(new PWMThread()).start();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    prevVal = progress;

                }

            }
        });

        seekBarR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int prevVal;
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                prevVal = seekBarR.getProgress();

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                //int progress = seekBar1.getProgress();

                if (abs(prevVal-progress)>10) {

                    //if (chck.isChecked()) progress = Math.round(progress / 2);


                    PWMcommand1 = ("PWMD7=" + progress);


                    new Thread(new PWMThread()).start();

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    prevVal = progress;
                }

            }
        });
        //endregion

        //region BUTTONY

        off.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try{
                   seekBarR.setProgress(0);
                    Thread.sleep(delay);
                    seekBarG.setProgress(0);
                    Thread.sleep(delay);
                    seekBarB.setProgress(0);}
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        selectClr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ColorPickerDialogBuilder
                        .with(context)
                        .setTitle("VYBER BARVU")
                        .initialColor(lastcolor)
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(7)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                                // toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));

                                PWMcommand = ("PWMD7=" + Color.red(selectedColor)); //R
                                PWMcommand1 = ("PWMD5=" + Color.green(selectedColor)); //G
                                PWMcommand2 = ("PWMD6=" + Color.blue(selectedColor)); //B
                                new Thread(new PWMThread()).start();

                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                //  changeBackgroundColor(selectedColor);
                                lastcolor = selectedColor;
                                try {
                                    seekBarR.setProgress(Color.red(selectedColor)*3);
                                    Thread.sleep(delay);
                                    seekBarG.setProgress(Color.green(selectedColor)*3);
                                    Thread.sleep(delay);
                                    seekBarB.setProgress(Color.blue(selectedColor)*4);
                                    //Snackbar.make(findViewById(android.R.id.content), "nastaveno " + selectedColor, Snackbar.LENGTH_LONG).show();
                                }
                                catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .noSliders()


                        .build().show();


            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    seekBarR.setProgress(1023);
                    Thread.sleep(delay);
                    seekBarG.setProgress(1023);
                    Thread.sleep(delay);
                    seekBarB.setProgress(1023);



                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }



            }
        });




        final FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.FAB);

        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myFab.startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotate));
                new ZjistitTeploty().execute(SERVER_IP1,SERVERPORT1,"JSONteploty");
            }
        });
        myFab.performClick();





        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                // new Thread(new ClientThread()).start();

                if (isChecked) {
                    command_sw = "SWITCH1=1_" + ventalarm.getText();

                    Snackbar.make(findViewById(android.R.id.content), "zapínám ventilátor", Snackbar.LENGTH_SHORT).show();

                } else {
                    command_sw = "SWITCH1=0";
                    Snackbar.make(findViewById(android.R.id.content), "vypínám ventilátor", Snackbar.LENGTH_SHORT).show();
                }

                new Thread(new SwitchThread()).start();


            }

        });

        test.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(context, detailsactivity.class);
                startActivity(i);
            }
        });
        //endregion
        new Thread(new ServiceStartThread()).start();
    }


    @Override
    public void onRestart(){
        super.onRestart();

        new ZjistitTeploty().execute(SERVER_IP1,SERVERPORT1,"JSONteploty");

    }

    //region THREADY

    class SwitchThread implements Runnable {


        public void run() {

            try {






                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(InetAddress.getByName(SERVER_IP1),SERVERPORT1),1000);
                socket.setSoTimeout(2000);
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())),
                        true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.println(command_sw);






                char [] data = new char[256];

                in.read(data, 0, data.length);

                response_sw = new String(data);



                in.reset();
                socket.close();

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }



    }

    class PWMThread implements Runnable {


        public void run() {

            try {



                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(InetAddress.getByName(SERVER_IP2),SERVERPORT2),200);
                socket.setSoTimeout(200);
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())),
                        true);
                out.println(PWMcommand);
                Thread.sleep(100);
                out.println(PWMcommand1);
                Thread.sleep(100);
                out.println(PWMcommand2);



                socket.close();

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }

    class ServiceStartThread implements Runnable {


        public void run() {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                startService(new Intent(MainActivity.this, MyService.class));
            } catch (ServiceConfigurationError e) {

            }







        }
    }
    //endregion

    //region MENU

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        RelativeLayout main = (RelativeLayout)findViewById(R.id.rl1);
        View view = getLayoutInflater().inflate(R.layout.addressdialog, main, false);
        final EditText input1 = (EditText) view.findViewById(R.id.editText);
        final  EditText input2 = (EditText) view.findViewById(R.id.editText2);
        //input1.setText(SERVER_IP1, TextView.BufferType.EDITABLE);
        //input2.setText("DefaultValue", TextView.BufferType.EDITABLE);
       // final AlertDialog dialogIOT = MainActivity.builderIOT.create();


        final EditText input = new EditText(this);


        AlertDialog.Builder builderLED = new AlertDialog.Builder(MainActivity.this)
                .setTitle("LED controller address")
                .setView(R.layout.addressdialog)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        //SERVER_IP2 = IP.getText().toString();
                        //SERVERPORT2 = Integer.parseInt(port.getText().toString());
                    }
                });




        AlertDialog.Builder aboutbuilder = new AlertDialog.Builder(MainActivity.this)
                .setView(R.layout.aboutdialog)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                });


        AlertDialog.Builder notiftick = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Notification update period")

                .setView(input)


                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        sec = 20;//Integer.getInteger(input.getText().toString());

                        //SERVERPORT1 = Integer.parseInt(input2.getText().toString());

                    }
                });


        final AlertDialog dialogLED = builderLED.create();
       // final AlertDialog dialogIOT = builderIOT.create();
        final AlertDialog about = aboutbuilder.create();
        final AlertDialog notif = notiftick.create();
        //noinspection SimplifiableIfStatement
        if (id == R.id.Brainsettings) {

            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.addressdialog);
            dialog.setTitle("Title");

            Button ano = (Button) dialog.findViewById(R.id.button4);
            Button ne = (Button) dialog.findViewById(R.id.button5);
            TextView titulek = (TextView) dialog.findViewById(R.id.textView16);
            titulek.setText("Adresa IOTbrain serveru");
           final EditText IP=(EditText)dialog.findViewById(R.id.editText);
            final EditText port=(EditText)dialog.findViewById(R.id.editText2);

            IP.setText(SERVER_IP1);
            String p = ((Integer)SERVERPORT1).toString();
            port.setText(p);

            ano.setOnClickListener(new View.OnClickListener() {
                //EditText edit=(EditText)dialog.findViewById(R.id.editText);


                public void onClick(View v) {


                    String text=IP.getText().toString();

                    dialog.dismiss();
                    SERVER_IP1=text;

                }
            });
            ne.setOnClickListener(new View.OnClickListener() {



                public void onClick(View v) {




                    dialog.dismiss();


                }
            });


            dialog.show();


            return true;
        }
        if (id == R.id.PWMsettings) {
           // IP.setText(SERVER_IP2);
           // port.setText(((Integer) SERVERPORT2).toString());
            dialogLED.show();

            return true;
        }
        if (id == R.id.about) {
            about.show();
            return true;
        }
        if (id == R.id.notifTick) {
            notif.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //endregion


    private class ZjistitTeploty extends AsyncTask<Object, Void, String> {
        TextView temp1 = (TextView) findViewById(R.id.tobyvak);
        TextView temp2 = (TextView) findViewById(R.id.tvenek);
        TextView temp3 = (TextView) findViewById(R.id.tpokoj);
        TextView cas1 = (TextView) findViewById(R.id.casView1);
        TextView cas2 = (TextView) findViewById(R.id.casView2);
        TextView cas3 = (TextView) findViewById(R.id.casView3);
        TextView min1 = (TextView) findViewById(R.id.tmin1);
        TextView max1 = (TextView) findViewById(R.id.tmax1);
        TextView min2 = (TextView) findViewById(R.id.tmin2);
        TextView max2 = (TextView) findViewById(R.id.tmax2);
        TextView min3 = (TextView) findViewById(R.id.tmin3);
        TextView max3 = (TextView) findViewById(R.id.tmax3);
        TextView hod1 = (TextView) findViewById(R.id.thod1);
        TextView hod2 = (TextView) findViewById(R.id.thod2);
        TextView hod3 = (TextView) findViewById(R.id.thod3);
        ProgressBar prg = (ProgressBar) findViewById(R.id.FABpb);
        Switch sw = (Switch) findViewById(R.id.switch1);
        SparkView sparkView = (SparkView) findViewById(R.id.sparkview);
        SparkView sparkView1 = (SparkView) findViewById(R.id.sparkview2);
        SparkView sparkView2 = (SparkView) findViewById(R.id.sparkview3);


        String response1;
        String response2;
        String response3;
        String response;
        boolean connSucc = false;
        String IP;
        int port;
        String command;
        boolean sta;
        List<Float> teploty1;
        List<Float> teploty2;
        List<Float> teploty3;
        @Override
        protected String doInBackground(Object... args) {
            // code where data is processing



            String result="null";

            try {

                IP = (String)args[0];
                port = (int)args[1];
                command = (String)args[2];


                Socket sockettp = new Socket();
                sockettp.connect(new InetSocketAddress(InetAddress.getByName(IP),port),1000);
                sockettp.setSoTimeout(1000);
                if (sockettp.isBound()) {
                    connSucc = true;
                    PrintWriter out = new PrintWriter(new BufferedWriter(
                            new OutputStreamWriter(sockettp.getOutputStream())),
                            true);
                    out.println(command);
                    BufferedReader in = new BufferedReader(new InputStreamReader(sockettp.getInputStream()));
                    char[] data = new char[2000];
                    in.read(data, 0, data.length);
                    response = new String(data).trim();
                    sockettp.close();

                    Gson gson = new GsonBuilder().create();
                    sta = gson.fromJson(response, Inputdata.class).getChanged();

                    Type listType = new TypeToken<List<Inputdata.Davkovac>>() {
                    }.getType();
                    JsonParser parser = new JsonParser();
                    JsonObject o = parser.parse(response).getAsJsonObject();

                    List<Inputdata.Davkovac> inty = new Gson().fromJson(o.get("teplomery"), listType);
                    response1 = inty.get(0).teplota;
                    response2 = inty.get(1).teplota;
                    response3 = inty.get(2).teplota;
                    teploty1 = inty.get(0).teploty;
                    teploty2 = inty.get(1).teploty;
                    teploty3 = inty.get(2).teploty;

                }
                else {
                    connSucc = false;
                    sockettp.close();
                }

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            prg.setVisibility(View.INVISIBLE);

            if (connSucc) {
                Calendar c = Calendar.getInstance();
                final String time  = (Integer.toString(c.get(Calendar.HOUR_OF_DAY))+":"+ Integer.toString(c.get(Calendar.MINUTE)));

                Snackbar.make(findViewById(R.id.clayout), "teploty úspěšně obnoveny", Snackbar.LENGTH_SHORT).show();
                temp1.setText(response1 + " °C");
                temp2.setText(response2 + " °C");
                temp3.setText(response3 + " °C");
                hod1.setText(time);
                hod2.setText(time);
                hod3.setText(time);

                try {


                    float[] t1 = ArrayUtils.toPrimitive(teploty1.toArray(new Float[teploty1.size()]));
                    float[] t2 = ArrayUtils.toPrimitive(teploty2.toArray(new Float[teploty2.size()]));
                    float[] t3 = ArrayUtils.toPrimitive(teploty3.toArray(new Float[teploty3.size()]));

                    sparkView.setAdapter(new MyAdapter(t3));
                    sparkView1.setAdapter(new MyAdapter(t1));
                    sparkView2.setAdapter(new MyAdapter(t2));

                    Integer c1=teploty3.size();
                    cas1.setText(c1.toString() + "h");
                    Integer c2=teploty1.size();
                    cas2.setText(c2.toString() + "h");
                    Integer c3=teploty2.size();
                    cas3.setText(c3.toString() + "h");

                    min1.setText("Min: " + Collections.min(teploty3).toString() + " °C");
                    max1.setText("Max: " + Collections.max(teploty3).toString() + " °C");
                    min2.setText("Min: " + Collections.min(teploty1).toString() + " °C");
                    max2.setText("Max: " + Collections.max(teploty1).toString() + " °C");
                    min3.setText("Min: " + Collections.min(teploty2).toString() + " °C");
                    max3.setText("Max: " + Collections.max(teploty2).toString() + " °C");

                    final float xIndex1= (float)(c1*0.001);
                    final float xIndex2= (float)(c2*0.001);
                    final float xIndex3= (float)(c3*0.001);



                    sparkView.setScrubListener(new SparkView.OnScrubListener() {
                        @Override
                        public void onScrubbed(Object value, Float x) {


                            String scrub = getString(R.string.scrub_format, value);
                            if (value == null) {
                                temp3.setText(response3 + " °C");
                                hod1.setText(time);

                            } else {
                                String X = getString(R.string.scrub_format, Math.round(x*xIndex1));
                                temp3.setText(scrub + " °C");
                                hod1.setText(X+"h");
                            }

                        }

                    });
                    sparkView1.setScrubListener(new SparkView.OnScrubListener() {
                        @Override
                        public void onScrubbed(Object value, Float x) {


                            String scrub = getString(R.string.scrub_format, value);
                            if (value == null) {
                                temp1.setText(response1 + " °C");
                                hod2.setText(time);

                            } else {

                                String X = getString(R.string.scrub_format, Math.round(x*xIndex2));
                                temp1.setText(scrub + " °C");
                                hod2.setText(X+"h");
                            }

                        }

                    });
                    sparkView2.setScrubListener(new SparkView.OnScrubListener() {
                        @Override
                        public void onScrubbed(Object value, Float x) {


                            String scrub = getString(R.string.scrub_format, value);
                            if (value == null) {
                                temp2.setText(response2 + " °C");
                                hod3.setText(time);

                            } else {
                                String X = getString(R.string.scrub_format, Math.round(x*xIndex3));
                                temp2.setText(scrub + " °C");
                                hod3.setText(X+"h");
                            }

                        }

                    });







                } catch (NullPointerException e1) {
                }


                //  sparkView.setScrubEnabled(true);
                // sparkView.setAnimateChanges(true);


                sw.setChecked(sta);
            }else

            {
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.clayout), "ERROR: nemohl jsem najít server!", Snackbar.LENGTH_INDEFINITE)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                new ZjistitTeploty().execute(SERVER_IP1,SERVERPORT1,"JSONteploty");

                            }
                        });
                snackbar.show();
            }
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {

            prg.setVisibility(View.VISIBLE);



            super.onPreExecute();
        }
    }

    public class MyAdapter extends SparkAdapter {
        private float[] yData;

        public MyAdapter(float[] yData) {
            this.yData = yData;
        }


        @Override
        public int getCount() {
            return yData.length;
        }

        @Override
        public Object getItem(int index) {
            return yData[index];
        }

        @Override
        public float getY(int index) {
            return yData[index];
        }
    }


}

