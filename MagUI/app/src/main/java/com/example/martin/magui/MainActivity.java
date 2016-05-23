package com.example.martin.magui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

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
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] id = new String[100];
    String[] arm = new String[300];
    String[] val = new String[500];
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ListView listView = (ListView) findViewById(R.id.listView);







        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new getAlarms().execute("192.168.10.14",8000,"alarms");

            }
        });
        //startService(new Intent(this, MyService.class));
    }

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class getAlarms extends AsyncTask<Object, Void, Void> {
        //TextView odpoved = (TextView) findViewById(R.id.tpokoj);
       // TextView check = (TextView) findViewById(R.id.status);
        ListView listView = (ListView) findViewById(R.id.listView);

        String response;
        boolean connSucc = false;
        String IP;
        int port;
        String command;
        @Override
        protected Void doInBackground(Object... args) {
            // code where data is processing


            try {

                IP = (String)args[0];
                port = (int)args[1];
                command = (String)args[2];




                    Socket sockettp = new Socket();
                    sockettp.connect(new InetSocketAddress(InetAddress.getByName(IP),port),1000);
                    sockettp.setSoTimeout(5000);
                    if (sockettp.isBound()){
                        connSucc = true;
                        PrintWriter out = new PrintWriter(new BufferedWriter(
                                new OutputStreamWriter(sockettp.getOutputStream())),
                                true);
                        out.println(command);
                        BufferedReader in = new BufferedReader(new InputStreamReader(sockettp.getInputStream()));





                        char[] data = new char[65000];


                    in.read(data, 0, data.length);


                    response = new String(data).trim();
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
        protected void onPostExecute(Void result) {

            if (connSucc == true) {

                Gson gson = new GsonBuilder().create();
                //String mag = gson.fromJson(response, Inputdata.class).getDevice();
                boolean sta = gson.fromJson(response, Inputdata.class).getChanged();







                    Type listType = new TypeToken<List<Inputdata.Davkovac>>() {}.getType();
                    JsonParser parser = new JsonParser();
                    JsonObject o = parser.parse(response).getAsJsonObject();

                    List<Inputdata.Davkovac> inty = new Gson().fromJson(o.get("davkovace"), listType);
                    //for (int x = 0; x < inty.size(); x++) {

               // id[0]=(inty.get(0).id) ;
                //id[1]=inty.get(1).Alarmy;

               /* arm[0]=inty.get(0).Alarmy;
                arm[1]=inty.get(1).Alarmy;
                val[0]=inty.get(0).Hodnoty;
                val[1]=inty.get(1).Hodnoty;*/



                for (int i = 0; i<id.length;i++)
                {


                    id[i]= null;
                    arm[i]=null;
                    val[i]=null;
                }




                for (int i = 0; i<inty.size();i++)
                {


                    id[i]= "Zarizeni " + inty.get(i).id;
                    arm[i]="Alarmy: " + inty.get(i).Alarmy;
                    val[i]="Hodnoty " + inty.get(i).Hodnoty;
                }

                MagListItem adapter = new
                        MagListItem(MainActivity.this, id, arm, val);


                /*final ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_2, android.R.id.text1,  id);*/



                listView.setAdapter(adapter);
               //listView.add(inty.get(1).Hodnoty);

                int numberOfItems = inty.size();

                // Get total height of all items.
                int totalItemsHeight = 0;
                for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                    View item = adapter.getView(itemPos, null, listView);
                    item.measure(0, 0);
                    totalItemsHeight += item.getMeasuredHeight();
                }

                // Get total height of all item dividers.
                int totalDividersHeight = listView.getDividerHeight() *
                        (numberOfItems - 1);
                ViewGroup.LayoutParams params = listView.getLayoutParams();
                params.height = totalItemsHeight + totalDividersHeight;
                listView.setLayoutParams(params);
                listView.requestLayout();
              //  adapter.add("bla");
                    //}

                    //int id = rec.getInt("id");
                   // String loc = rec.getString("loc");


                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), "data refresh was successful", Snackbar.LENGTH_LONG);



                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.GREEN);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                snackbar.show();


            }
            else {
               Snackbar snackbar = Snackbar
                       .make(findViewById(android.R.id.content), "ERROR: could not reach server!", Snackbar.LENGTH_INDEFINITE)
                       .setAction("RETRY", new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               new getAlarms().execute(IP,port,command);

                           }
                       });
                snackbar.setActionTextColor(Color.CYAN);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.RED);
                snackbar.show();
            }

            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {

           //Snackbar.make(findViewById(android.R.id.content), "alarmy načteny", Snackbar.LENGTH_SHORT).show();

            super.onPreExecute();

        }
    }

}
