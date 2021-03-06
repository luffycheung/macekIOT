package com.example.martin.macekinternetofthings;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.IBinder;
import android.renderscript.RenderScript;
import android.support.v7.app.NotificationCompat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

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
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MyService extends Service {
    public String response;

    private String ConnectedWIFI() {
        WifiManager wifiMgr = (WifiManager) super.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
        String name = "aa";
        try {
            name = wifiInfo.getSSID();
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (name == null) name = "null";
        return name;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

            if (ConnectedWIFI().contains("MANGO666") && Data.notif)
        {


            try {
                response = new getTemp().execute("192.168.10.120",90,"JSONteploty").get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if (response != null) {

                StringBuilder re = new StringBuilder();




                NotificationCompat.Builder mBuilder =
                        (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                                .setSmallIcon(R.drawable.ic_stat_file_cloud)
                                .setColor(getResources().getColor(R.color.colorPrimary))
                                .setContentTitle("MacIoT update")
                                .setPriority(Notification.PRIORITY_MAX)
                                .setStyle(new NotificationCompat.BigTextStyle().bigText(response));


                int mNotificationId = 001;


                PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                        new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

                mBuilder.setContentIntent(contentIntent);
                NotificationManager mNotifyMgr =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


                mNotifyMgr.notify(mNotificationId, mBuilder.build());
            }
        }

        stopSelf();

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        // I want to restart this service again in one hour
        AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarm.set(
                alarm.RTC_WAKEUP,
                System.currentTimeMillis() + (1000 * Data.notifperiod * 60),
                PendingIntent.getService(this, 0, new Intent(this, MyService.class), 0)
        );
    }


    class getTemp extends AsyncTask<Object, Void, String> {



        String response;
        boolean connSucc = false;
        String IP;
        int port;
        String command;

        @Override
        protected String doInBackground(Object... args) {
            // code where data is processing
            String result="null";

            try {

                IP = (String)args[0];
                port = (int)args[1];
                command = (String)args[2];


                Socket sockettp = new Socket();
                sockettp.connect(new InetSocketAddress(InetAddress.getByName(IP),port),3000);
                sockettp.setSoTimeout(3000);
                if (sockettp.isBound()){
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






                        Type listType = new TypeToken<List<Inputdata.Davkovac>>() {
                        }.getType();
                        JsonParser parser = new JsonParser();
                        JsonObject o = parser.parse(response).getAsJsonObject();

                        List<Inputdata.Davkovac> inty = new Gson().fromJson(o.get("teplomery"), listType);

                    Gson gson = new GsonBuilder().create();
                    boolean sta = gson.fromJson(response, Inputdata.class).getChanged();

                    StringBuilder re = new StringBuilder();



                  /*  String tPokoj = inty.get(1).teplota;
                    String tObyvak = inty.get(0).teplota;
                    tPokoj = tPokoj.replace(',', '.');
                    tObyvak = tObyvak.replace(',', '.');

                    DecimalFormat df = new DecimalFormat("#.##");

                     double rozdil = (Float.parseFloat(tPokoj) - Float.parseFloat(tObyvak));*/

                       re.append("Teplota venku: " + inty.get(2).teplota + "°C" +"\r\n");
                       re.append("Rozdíl teplot v pokojích: " + inty.get(3).teplota + "°C" +"\r\n");
                    if (sta) re.append("Ventilátor je zapnutý" +"\r\n");
                    else re.append("Ventilátor je vypnutý" +"\r\n");



                    result = re.toString();




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

            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            if (connSucc == true) {


            }
            //MyService.finished = true;




            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {

            //Snackbar.make(findViewById(android.R.id.content), "alarmy načteny", Snackbar.LENGTH_SHORT).show();
            // MyService.finished = false;
            super.onPreExecute();

        }
    }

}