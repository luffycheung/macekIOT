package com.example.martin.macekinternetofthings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class detailsactivity extends AppCompatActivity {
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_details);





        EditText perioda = (EditText) findViewById(R.id.aut_perioda);

        EditText hyster = (EditText) findViewById(R.id.aut_hys);
        EditText kld1 = (EditText) findViewById(R.id.aut_kldS);
        EditText kld2 = (EditText) findViewById(R.id.aut_kldK);

        Switch sw1 = (Switch) findViewById(R.id.aut_sw1);
        final Switch sw2 = (Switch) findViewById(R.id.aut_sw2);
        final FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.aut_FAB);

         //region FABblbustky
        perioda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                myFab.show();
            }
        });
        hyster.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                myFab.show();
            }
        });
        kld1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                myFab.show();
            }
        });
        kld2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                myFab.show();
            }
        });


        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    sw2.setText("léto");

                } else {
                    sw2.setText("zima");
                }

            myFab.show();


            }

        });
        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                myFab.show();

            }

        });

//endregion

        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText perioda = (EditText) findViewById(R.id.aut_perioda);

                EditText hyster = (EditText) findViewById(R.id.aut_hys);
                EditText kld1 = (EditText) findViewById(R.id.aut_kldS);
                EditText kld2 = (EditText) findViewById(R.id.aut_kldK);

                Switch sw1 = (Switch) findViewById(R.id.aut_sw1);
                Switch sw2 = (Switch) findViewById(R.id.aut_sw2);



                Outputdata obj = new Outputdata();
                obj.setMaster(sw1.isChecked());
                obj.setObdobi(sw2.isChecked());
                obj.setPeriod(Integer.parseInt(perioda.getText().toString()));
                obj.setKlid1(Integer.parseInt(kld1.getText().toString()));
                obj.setKlid2(Integer.parseInt(kld2.getText().toString()));
                obj.setHys(Float.parseFloat(hyster.getText().toString()));


                Gson gson = new GsonBuilder().serializeNulls().create();
                String command = gson.toJson(obj);
                new Poslatprikazy().execute(MainActivity.SERVER_IP1, MainActivity.SERVERPORT1, command);

            }
        });




        perioda.setText(Integer.toString(Data.period));
        hyster.setText(Float.toString(Data.hys));
        kld1.setText(Integer.toString(Data.klid1));
        kld2.setText(Integer.toString(Data.klid2));
        sw1.setChecked(Data.master);
        sw2.setChecked(Data.obdobi);




    }
    @Override
    public void onPause(){
        super.onPause();

        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);



    }


    private class Poslatprikazy extends AsyncTask<Object, Void, String> {

        ProgressBar prg = (ProgressBar) findViewById(R.id.aut_FABpb);

        final FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.aut_FAB);




        boolean connSucc = false;
        String IP;
        int port;
        String command;



        @Override
        protected String doInBackground(Object... args) {
            // code where data is processing





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
                    out.println("JSONcommand " + command);

                    out.close();

                    sockettp.close();






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


            if (connSucc) {

                Snackbar.make(findViewById(R.id.aut__clayout), "příkaz byl úspěšně vyslán", Snackbar.LENGTH_LONG).show();


            }else

            {
               Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.aut__clayout), "ERROR: nemohl jsem najít server!", Snackbar.LENGTH_INDEFINITE)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                new Poslatprikazy().execute(MainActivity.SERVER_IP1,MainActivity.SERVERPORT1,command);

                            }
                        });
                snackbar.show();
            }
            myFab.hide();
            prg.setVisibility(View.INVISIBLE);


            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {

            prg.setVisibility(View.VISIBLE);



            super.onPreExecute();
        }
    }



}
