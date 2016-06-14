package com.example.martin.macekinternetofthings;




import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
//import org.ajoberstar.*;
//import com.example.robinhood.*;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.github.rubensousa.floatingtoolbar.FloatingToolbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
//import com.robinhood.spark.SparkAdapter;
//import com.robinhood.spark.SparkView;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.ServiceConfigurationError;

import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity {

    private Socket socket;

    public static int SERVERPORT1;
    public static String SERVER_IP1;
    public static int sec = 20;
    public int scrollY;

    public boolean scrollrun = false;
    public boolean toolbarshown = false;
    private int SERVERPORT2;
    private String SERVER_IP2;
    private String command = "";
   // private String command_sw = "";
    private String PWMcommand = "";
    private String PWMcommand1 = "";
    private String PWMcommand2 = "";
    //StringBuffer response = new StringBuffer();
    private String response_sw = "aa";
    private String response_tp;
    private Integer lastcolor;
    public static float[]numb=new float[3];
    private boolean globalConnSucc = false;

    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("internet of things controller");

        final int delay = 90;

        //region findView

        final SeekBar seekBarG = (SeekBar) findViewById(R.id.seekBar);
        final SeekBar seekBarB = (SeekBar) findViewById(R.id.seekBarBlue);
        final SeekBar seekBarR = (SeekBar) findViewById(R.id.seekBarRed);
       // final SeekBar seekBarMeritko = (SeekBar) findViewById(R.id.meritkoSB);
        final TextView testtxt = (TextView) findViewById(R.id.textView12);
        final EditText ventalarm = (EditText) findViewById(R.id.ventAlarm);
        final SparkView sparkView = (SparkView) findViewById(R.id.sparkview);
        final SparkView sparkView1 = (SparkView) findViewById(R.id.sparkview2);
        final SparkView sparkView2 = (SparkView) findViewById(R.id.sparkview3);
        final SparkView sparkView3 = (SparkView) findViewById(R.id.sparkview4);
        final FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.FAB);
        final FloatingActionButton myFab2 = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton fFab = (FloatingActionButton) findViewById(R.id.fab);
        final FloatingToolbar fToolbar = (FloatingToolbar) findViewById(R.id.floatingToolbar);

        Switch sw = (Switch) findViewById(R.id.switch1);
        Button off = (Button) findViewById(R.id.button2);
        Button selectClr = (Button) findViewById(R.id.button7);
        Button all = (Button) findViewById(R.id.button6);
        Button test = (Button) findViewById(R.id.button);

        //endregion
        seekBarR.setProgress(11);
        seekBarG.setProgress(11);
        seekBarB.setProgress(11);



        numb[0]=10;
        numb[1]=8;
        numb[2]=20;
        sparkView.setAdapter(new MyAdapter(numb));
        sparkView1.setAdapter(new MyAdapter(numb));
        sparkView2.setAdapter(new MyAdapter(numb));
        sparkView.setAnimateChanges(true);
        sparkView1.setAnimateChanges(true);
        sparkView2.setAnimateChanges(true);

        fToolbar.attachFab(fFab);
        myFab.hide();







        final View iView = fToolbar.getCustomView();
        final SeekBar seekBarMeritko = (SeekBar) iView.findViewById(R.id.meritkoSBfloat);
        Button float_ok = (Button) iView.findViewById(R.id.button3);

       final Handler handler = new Handler();

       final NestedScrollView nScrollView =  (NestedScrollView) findViewById(R.id.nScrolView);

        nScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {

            @Override
            public void onScrollChanged() {

                 scrollY = nScrollView.getScrollY(); //for verticalScrollView


                myFab2.hide();
                if (toolbarshown) ftoolbarhide.run();


                if (scrollrun == false)
                {

                    new Thread(new ScrollThread()).start();
                    scrollrun = true;
                }



            }


        });

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


        seekBarMeritko.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
                Data.nasobic = (double)(progress)/100;
                sparkView.populatePath();
                sparkView1.populatePath();
                sparkView2.populatePath();
                sparkView3.populatePath();



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
        float_ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                fToolbar.hide();
                myFab.hide();
                toolbarshown = false;
            }
        });

        myFab2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //myFab.show();


                fToolbar.show();
                handler.postDelayed(FABshow, 500);
                toolbarshown = true;
            }
        });





        selectClr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ColorPickerDialogBuilder
                        .with(context)
                        .setTitle("VYBER BARVU")
                        .initialColor(lastcolor)

                        .wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
                        .density(5)
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






        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myFab.startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotate));
                new ZjistitTeploty().execute(SERVER_IP1,SERVERPORT1,"JSONteploty");
            }
        });
       // myFab.performClick();





        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                // new Thread(new ClientThread()).start();

                if (isChecked) {
                    Data.command_sw = "SWITCH1=1_" + ventalarm.getText();

                    //Snackbar.make(findViewById(android.R.id.content), "zapínám ventilátor", Snackbar.LENGTH_SHORT).show();

                } else {
                    Data.command_sw = "SWITCH1=0_"+ ventalarm.getText();
                    //Snackbar.make(findViewById(android.R.id.content), "vypínám ventilátor", Snackbar.LENGTH_SHORT).show();
                }

                new Thread(new SwitchThread()).start();


            }

        });

        test.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent i = new Intent(context, detailsactivity.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        //endregion


        startService(new Intent(MainActivity.this, MyService.class));
        seekBarMeritko.setProgress((int)Data.nasobic*100);
        handler.postDelayed(StartRefresh, 4000);

       // new Thread(new ServiceStartThread()).start();


    }
     public Runnable FABshow = new Runnable() {
        @Override
        public void run() {
            FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.FAB);
            myFab.show();


        }
    };
    public Runnable FABshow2 = new Runnable() {
        @Override
        public void run() {
            FloatingActionButton myFab2 = (FloatingActionButton) findViewById(R.id.fab);
            myFab2.show();

        }
    };
    public Runnable StartRefresh = new Runnable() {
        @Override
        public void run() {
            new ZjistitTeploty().execute(SERVER_IP1,SERVERPORT1,"JSONteploty");


        }
    };
    public Runnable ftoolbarhide = new Runnable() {
        @Override
        public void run() {
            FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.FAB);
            FloatingToolbar fToolbar = (FloatingToolbar) findViewById(R.id.floatingToolbar);


            fToolbar.hide();
            myFab.hide();
            toolbarshown = false;


        }
    };



    //region ACTIVITY EVENTS
    @Override
    public void onRestart(){
        super.onRestart();

        SharedPreferences load = getSharedPreferences("app_data", MODE_PRIVATE);
        SERVER_IP1 = load.getString("IP1","192.168.10.120");
        SERVERPORT1 = load.getInt("port1",90);
        new ZjistitTeploty().execute(SERVER_IP1,SERVERPORT1,"JSONteploty");


    }
    @Override
    public void onPause(){
        super.onPause();



        SharedPreferences save = getSharedPreferences("app_data", MODE_PRIVATE);
        SharedPreferences.Editor ed = save.edit();
        ed.putString("IP1", SERVER_IP1);
        ed.putString("IP2", SERVER_IP2);
        ed.putInt("port1",SERVERPORT1);
        ed.putInt("port2",SERVERPORT2);
        ed.putInt("notif",Data.notifperiod);
        ed.putBoolean("notif_en",Data.notif);
        ed.putInt("rgb",lastcolor);
        ed.putFloat("meritko",(float)Data.nasobic);
        ed.putString("psw",Data.password);
        ed.commit();



    }
    @Override
    public void onStart(){
        super.onStart();

        SeekBar seekBarG = (SeekBar) findViewById(R.id.seekBar);
        SeekBar seekBarB = (SeekBar) findViewById(R.id.seekBarBlue);
        SeekBar seekBarR = (SeekBar) findViewById(R.id.seekBarRed);
        //SeekBar seekBarMeritko = (SeekBar) findViewById(R.id.meritkoSB);

        SharedPreferences load = getSharedPreferences("app_data", MODE_PRIVATE);

        SERVER_IP1 = load.getString("IP1","control.aurox.cz");
        SERVER_IP2 = load.getString("IP2","192.168.10.65");
        SERVERPORT1 = load.getInt("port1",90);
        SERVERPORT2 = load.getInt("port2",43333);
        sec = load.getInt("notif",20);
        lastcolor = load.getInt("rgb",Color.rgb(0,100,255)); //TODO: nefunguji seekbary
        Data.nasobic = load.getFloat("meritko", 1);
        Data.notif = load.getBoolean("notif_en", true);
        Data.notifperiod = load.getInt("notif", 5);
        Data.password = load.getString("psw", "nene");

        //new ZjistitTeploty().execute(SERVER_IP1,SERVERPORT1,"JSONteploty");



    }

    //endregion

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
                out.println(Data.command_sw);






                char [] data = new char[256];

                in.read(data, 0, data.length);

                response_sw = new String(data);



               // in.reset();
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
            if (globalConnSucc) {
                try {


                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(InetAddress.getByName(SERVER_IP2), SERVERPORT2), 200);
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


    }

    class ServiceStartThread implements Runnable {


        public void run() {




            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                startService(new Intent(MainActivity.this, MyService.class));
            } catch (ServiceConfigurationError e) {

            }









        }
    }

    class ScrollThread implements Runnable {


        public void run() {
            Looper.prepare();
            try {

                int prevscroll;
                while (scrollrun == true){
                    prevscroll = scrollY;
                    Thread.sleep(150);

                    if (prevscroll == scrollY)
                    {
                        scrollrun = false;


                        FABshow2.run();

                    }

                }






            } catch (InterruptedException e) {
                e.printStackTrace();
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







       /* AlertDialog.Builder aboutbuilder = new AlertDialog.Builder(MainActivity.this)
                .setView(R.layout.aboutdialog)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                });
*/





       // final AlertDialog dialogIOT = builderIOT.create();
       // final AlertDialog about = aboutbuilder.create();

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
                    Integer po = Integer.parseInt(port.getText().toString());
                    dialog.dismiss();
                    SERVER_IP1=text;
                    SERVERPORT1 = po;

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
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.addressdialog);
            dialog.setTitle("Title");

            Button ano = (Button) dialog.findViewById(R.id.button4);
            Button ne = (Button) dialog.findViewById(R.id.button5);
            TextView titulek = (TextView) dialog.findViewById(R.id.textView16);
            titulek.setText("Adresa PWM ovladače");
            final EditText IP=(EditText)dialog.findViewById(R.id.editText);
            final EditText port=(EditText)dialog.findViewById(R.id.editText2);

            IP.setText(SERVER_IP2);
            String p = ((Integer)SERVERPORT2).toString();
            port.setText(p);

            ano.setOnClickListener(new View.OnClickListener() {
                //EditText edit=(EditText)dialog.findViewById(R.id.editText);


                public void onClick(View v) {


                    String text=IP.getText().toString();
                    Integer po = Integer.parseInt(port.getText().toString());
                    dialog.dismiss();
                    SERVER_IP2=text;
                    SERVERPORT2 = po;

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
        if (id == R.id.about) {
            //about.show();
            return true;
        }
        if (id == R.id.notifTick) {

            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.notification_dialog);
            dialog.setTitle("Title");




            Button ok = (Button) dialog.findViewById(R.id.button8);
            final EditText period=(EditText)dialog.findViewById(R.id.editText3);
           final CheckBox enable =(CheckBox) dialog.findViewById(R.id.checkBox);
            enable.setChecked(Data.notif);
            period.setText(Integer.toString(Data.notifperiod));


            ok.setOnClickListener(new View.OnClickListener() {
                //EditText edit=(EditText)dialog.findViewById(R.id.editText);


                public void onClick(View v) {


                    dialog.dismiss();
                    Data.notif = enable.isChecked();
                    Data.notifperiod = Integer.parseInt(period.getText().toString());
                    if (Data.notif){
                        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
                    alarm.set(
                            alarm.RTC_WAKEUP,
                            System.currentTimeMillis() + (1000 * Data.notifperiod * 60),
                            PendingIntent.getService(context, 0, new Intent(context, MyService.class), 0)
                    );

                  }
                }
            });



            dialog.show();




            return true;
        }
        if (id == R.id.perm) {
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.permissiondialog);
            dialog.setTitle("Title");

            Button ano = (Button) dialog.findViewById(R.id.button4);

            TextView titulek = (TextView) dialog.findViewById(R.id.textView16);
            //titulek.setText("Adresa PWM ovladače");
            final EditText heslo=(EditText)dialog.findViewById(R.id.editText);
            //final EditText port=(EditText)dialog.findViewById(R.id.editText2);



            ano.setOnClickListener(new View.OnClickListener() {
                //EditText edit=(EditText)dialog.findViewById(R.id.editText);


                public void onClick(View v) {


                    Data.password=heslo.getText().toString();

                    dialog.dismiss();


                }
            });



            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //endregion


    private class ZjistitTeploty extends AsyncTask<Object, Void, String> {
        TextView temp1 = (TextView) findViewById(R.id.t1);
        TextView temp2 = (TextView) findViewById(R.id.t2);
        TextView temp3 = (TextView) findViewById(R.id.t3);
        TextView temp4 = (TextView) findViewById(R.id.t4);
        TextView temp5 = (TextView) findViewById(R.id.t5);
        TextView temp6 = (TextView) findViewById(R.id.t6);
        TextView cas1 = (TextView) findViewById(R.id.casView1);
        TextView cas2 = (TextView) findViewById(R.id.casView2);
        TextView cas3 = (TextView) findViewById(R.id.casView3);
        TextView cas4 = (TextView) findViewById(R.id.casView4);
        TextView cas5 = (TextView) findViewById(R.id.casView5);
        TextView pcas1 = (TextView) findViewById(R.id.pcas1);
        TextView pcas2 = (TextView) findViewById(R.id.pcas2);
        TextView pcas3 = (TextView) findViewById(R.id.pcas3);
        TextView pcas4 = (TextView) findViewById(R.id.pcas3);
        TextView pcas5 = (TextView) findViewById(R.id.pcas3);
        TextView min1 = (TextView) findViewById(R.id.tmin1);
        TextView max1 = (TextView) findViewById(R.id.tmax1);
        TextView min2 = (TextView) findViewById(R.id.tmin2);
        TextView max2 = (TextView) findViewById(R.id.tmax2);
        TextView min3 = (TextView) findViewById(R.id.tmin3);
        TextView max3 = (TextView) findViewById(R.id.tmax3);
        TextView min4 = (TextView) findViewById(R.id.tmin4);
        TextView max4 = (TextView) findViewById(R.id.tmax4);
        TextView min5 = (TextView) findViewById(R.id.tmin5);
        TextView max5 = (TextView) findViewById(R.id.tmax5);
        TextView min6 = (TextView) findViewById(R.id.tmin6);
        TextView max6 = (TextView) findViewById(R.id.tmax6);
        TextView hod1 = (TextView) findViewById(R.id.thod1);
        TextView hod2 = (TextView) findViewById(R.id.thod2);
        TextView hod3 = (TextView) findViewById(R.id.thod3);
        TextView hod4 = (TextView) findViewById(R.id.thod4);
        TextView hod5 = (TextView) findViewById(R.id.thod5);
        ProgressBar prg = (ProgressBar) findViewById(R.id.FABpb);
        Switch sw = (Switch) findViewById(R.id.switch1);
        SparkView sparkView = (SparkView) findViewById(R.id.sparkview);
        SparkView sparkView1 = (SparkView) findViewById(R.id.sparkview2);
        SparkView sparkView2 = (SparkView) findViewById(R.id.sparkview3);
        SparkView sparkView3 = (SparkView) findViewById(R.id.sparkview4);
        SparkView sparkView4 = (SparkView) findViewById(R.id.sparkview5);
        RelativeLayout extras = (RelativeLayout) findViewById(R.id.marL);
        String response1;
        String response2;
        String response3;
        String response4;
        String response5;
        String response6;
        String response;
        boolean connSucc = false;
        String IP;
        int port;
        String command;
        boolean sta;
        List<Float> teploty1;
        List<Float> teploty2;
        List<Float> teploty3;
        List<Float> teploty4;
        List<Float> teploty5;
        List<Float> teploty6;
        @Override
        protected String doInBackground(Object... args) {
            // code where data is processing



            String result="null";
            boolean end = false;

            try {

                IP = (String)args[0];
                port = (int)args[1];
                command = (String)args[2];


                Socket sockettp = new Socket();
                sockettp.connect(new InetSocketAddress(InetAddress.getByName(IP),port),4000);
                sockettp.setSoTimeout(4000);
                if (sockettp.isBound()) {
                    connSucc = true;
                    PrintWriter out = new PrintWriter(new BufferedWriter(
                            new OutputStreamWriter(sockettp.getOutputStream())),
                            true);
                    out.println(command);

                    InputStream instream = sockettp.getInputStream();
                    InputStreamReader instr = new InputStreamReader(instream);


                    char[] data = new char[8000];


                    //byte[] data= IOUtils.toByteArray(new InputStreamReader(sockettp.getInputStream()));
                    try {
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException e){}
                    BufferedReader in = new BufferedReader(instr);
                    in.read(data,0,data.length);


                    response = new String(data).trim();

                    sockettp.close();

                    Gson gson = new GsonBuilder().create();
                    sta = gson.fromJson(response, Inputdata.class).getChanged();

                    Type listType = new TypeToken<List<Inputdata.Davkovac>>() {
                    }.getType();
                    JsonParser parser = new JsonParser();
                    JsonObject o = parser.parse(response).getAsJsonObject();

                    Data.master = gson.fromJson(response, Inputdata.class).getMaster();
                    Data.obdobi = gson.fromJson(response, Inputdata.class).getObdobi();
                    Data.hys = gson.fromJson(response, Inputdata.class).getHys();
                    Data.klid1 = gson.fromJson(response, Inputdata.class).getKlid1();
                    Data.klid2 = gson.fromJson(response, Inputdata.class).getKlid2();
                    Data.period = gson.fromJson(response, Inputdata.class).getPeriod();



                    List<Inputdata.Davkovac> inty = new Gson().fromJson(o.get("teplomery"), listType);
                    response1 = inty.get(2).teplota; //venek
                    response2 = inty.get(4).teplota; //bazen
                    response3 = inty.get(1).teplota; //pokoj
                    response4 = inty.get(3).teplota; //delta
                    response5 = inty.get(0).teplota; //obyvak
                    response6 = inty.get(6).teplota; //ESP






                    teploty1 = inty.get(2).teploty;
                    teploty2 = inty.get(5).teploty; //poklop
                    teploty3 = inty.get(1).teploty;
                    teploty4 = inty.get(3).teploty;
                    teploty5 = inty.get(0).teploty;
                    teploty6 = inty.get(6).teploty;











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
            if (Data.password.contains("prdelprdel")) extras.setVisibility(View.VISIBLE);
            else extras.setVisibility(View.GONE);





            globalConnSucc = connSucc;
            if (connSucc) {
                final Calendar c = Calendar.getInstance();
                DateFormat casFormat = new SimpleDateFormat("HH:mm");

                final String time = casFormat.format(c.getTime());
              //  Snackbar.make(findViewById(R.id.clayout), "teploty úspěšně obnoveny", Snackbar.LENGTH_SHORT).show();
                temp1.setText(response1 + " °C");
                temp2.setText(response2 + " °C");
                temp3.setText(response3 + " °C");
                temp4.setText(response4 + " °C");
                temp5.setText(response5 + " °C");
                temp6.setText(response6 + " °C");
                hod1.setText(time);
                hod2.setText(time);
                hod3.setText(time);
                hod4.setText(time);
                hod5.setText(time);


                try {


                    float[] t1 = ArrayUtils.toPrimitive(teploty1.toArray(new Float[teploty1.size()]));
                    float[] t2 = ArrayUtils.toPrimitive(teploty2.toArray(new Float[teploty2.size()]));
                    float[] t3 = ArrayUtils.toPrimitive(teploty3.toArray(new Float[teploty3.size()]));
                    float[] t4 = ArrayUtils.toPrimitive(teploty4.toArray(new Float[teploty4.size()]));
                    float[] t5 = ArrayUtils.toPrimitive(teploty5.toArray(new Float[teploty5.size()]));


                    sparkView.setAdapter(new MyAdapter(t1));
                    sparkView1.setAdapter(new MyAdapter(t2));
                    sparkView2.setAdapter(new MyAdapter(t3));
                    sparkView3.setAdapter(new MyAdapter(t4));
                    sparkView4.setAdapter(new MyAdapter(t5));

                    DateFormat datumFormat = new SimpleDateFormat("dd.MM.yy");
                    final Integer hodina = (c.get(Calendar.HOUR_OF_DAY));
                    String hod = hodina.toString()+ "h";
                    cas1.setText(hod);
                    cas2.setText(hod);
                    cas3.setText(hod);
                    cas4.setText(hod);
                    cas5.setText(hod);
                    c.add(Calendar.DATE, -1);
                    String pred = " "+ datumFormat.format(c.getTime());
                    pcas1.setText(hod + pred);
                    pcas2.setText(hod + pred);
                    pcas3.setText(hod + pred);
                    pcas4.setText(hod + pred);
                    pcas5.setText(hod + pred);

                    min1.setText("Min: " + Collections.min(teploty1).toString() + " °C");
                    max1.setText("Max: " + Collections.max(teploty1).toString() + " °C");
                    min2.setText("Min: " + Collections.min(teploty2).toString() + " °C");
                    max2.setText("Max: " + Collections.max(teploty2).toString() + " °C");
                    min3.setText("Min: " + Collections.min(teploty3).toString() + " °C");
                    max3.setText("Max: " + Collections.max(teploty3).toString() + " °C");
                    min4.setText("Min: " + Collections.min(teploty4).toString() + " °C");
                    max4.setText("Max: " + Collections.max(teploty4).toString() + " °C");
                    min5.setText("Min: " + Collections.min(teploty5).toString() + " °C");
                    max5.setText("Max: " + Collections.max(teploty5).toString() + " °C");
                    min6.setText("Min: " + Collections.min(teploty6).toString() + " °C");
                    max6.setText("Max: " + Collections.max(teploty6).toString() + " °C");



                    final float xIndex = (float)0.024;




                    sparkView.setScrubListener(new SparkView.OnScrubListener() {
                        @Override
                        public void onScrubbed(Object value, Float x) {


                            String scrub = getString(R.string.scrub_format, value);
                            if (value == null) {
                                temp1.setText(response1 + " °C");
                                hod1.setText(time);

                            } else {
                                Integer h = hodina + Math.round((x-(float)0.5)*xIndex);
                                if (h>=24) h=h-24;

                                temp1.setText(scrub + " °C");
                                hod1.setText(h+"h");
                            }

                        }

                    });
                    sparkView1.setScrubListener(new SparkView.OnScrubListener() {
                        @Override
                        public void onScrubbed(Object value, Float x) {


                            String scrub = getString(R.string.scrub_format, value);
                            if (value == null) {
                                temp2.setText(response2 + " °C");
                                hod2.setText(time);

                            } else {

                                Integer h = hodina + Math.round((x-(float)0.5)*xIndex);
                                if (h>=24) h=h-24;
                                temp2.setText(scrub + " °C");
                                hod2.setText(h+"h");
                            }

                        }

                    });
                    sparkView2.setScrubListener(new SparkView.OnScrubListener() {
                        @Override
                        public void onScrubbed(Object value, Float x) {


                            String scrub = getString(R.string.scrub_format, value);
                            if (value == null) {
                                temp3.setText(response3 + " °C");
                                hod3.setText(time);

                            } else {
                                Integer h = hodina + Math.round((x-(float)0.5)*xIndex);
                                if (h>=24) h=h-24;
                                temp3.setText(scrub + " °C");
                                hod3.setText(h+"h");
                            }



                        }


                    });
                    sparkView3.setScrubListener(new SparkView.OnScrubListener() {
                        @Override
                        public void onScrubbed(Object value, Float x) {


                            String scrub = getString(R.string.scrub_format, value);
                            if (value == null) {
                                temp4.setText(response4 + " °C");
                                hod4.setText(time);

                            } else {
                                Integer h = hodina + Math.round((x-(float)0.5)*xIndex);
                                if (h>=24) h=h-24;
                                temp4.setText(scrub + " °C");
                                hod4.setText(h+"h");
                            }



                        }


                    });

                    sparkView4.setScrubListener(new SparkView.OnScrubListener() {
                        @Override
                        public void onScrubbed(Object value, Float x) {


                            String scrub = getString(R.string.scrub_format, value);
                            if (value == null) {
                                temp5.setText(response5 + " °C");
                                hod5.setText(time);

                            } else {
                                Integer h = hodina + Math.round((x-(float)0.5)*xIndex);
                                if (h>=24) h=h-24;
                                temp5.setText(scrub + " °C");
                                hod5.setText(h+"h");
                            }



                        }


                    });

                } catch (NullPointerException e1) {
                }


                //  sparkView.setScrubEnabled(true);
                // sparkView.setAnimateChanges(true);
                int randColor1 = RandomUtils.nextInt(0,220);
                int randColor2 = RandomUtils.nextInt(0,220);
                int randColor3 = RandomUtils.nextInt(0,220) + 35;
                sparkView.setLineColor(Color.rgb(randColor1,randColor2,randColor3));
                sparkView1.setLineColor(Color.rgb(randColor1,randColor2,randColor3));
                sparkView2.setLineColor(Color.rgb(randColor1,randColor2,randColor3));
                sparkView3.setLineColor(Color.rgb(randColor1,randColor2,randColor3));
                sparkView4.setLineColor(Color.rgb(randColor1,randColor2,randColor3));

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


