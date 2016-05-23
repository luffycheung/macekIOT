package com.example.martin.magui;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.TextView;

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
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MyService extends Service {

    public static boolean sta = false;
    //public static int sleep = 10;
    public boolean finished = false;


    private String ConnectedWIFI() {
        WifiManager wifiMgr = (WifiManager) super.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
        String name = "aa";
        try {
            name = wifiInfo.getSSID();
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (name == null) name = "null";
        return name;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

            if (ConnectedWIFI().contains("MANGO666"))
        {
            String re = "null";

            try {
                 re = new getAlarms().execute("192.168.10.14",8000,"alarms").get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if (re != null) {
                NotificationCompat.Builder mBuilder =
                        (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Maguire alarm")
                                .setColor(getResources().getColor(R.color.colorPrimary))
                                .setStyle(new NotificationCompat.BigTextStyle().bigText(re));


                int mNotificationId = 0;

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
                System.currentTimeMillis() + (1000 * 20),
                PendingIntent.getService(this, 0, new Intent(this, MyService.class), 0)
        );
    }






}
class getAlarms extends AsyncTask<Object, Void, String> {



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
            sockettp.connect(new InetSocketAddress(InetAddress.getByName(IP),port),1000);
            sockettp.setSoTimeout(1000);
            if (sockettp.isBound()){
                connSucc = true;
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(sockettp.getOutputStream())),
                        true);
                out.println(command);
                BufferedReader in = new BufferedReader(new InputStreamReader(sockettp.getInputStream()));
                char[] data = new char[256];
                in.read(data, 0, data.length);
                response = new String(data).trim();
                sockettp.close();

                StringBuilder re = new StringBuilder();
              /*  Gson gson = new GsonBuilder().create();
                String mag = gson.fromJson(response, data.class).getDevice();
                boolean sta = gson.fromJson(response, data.class).getChanged();


                if (gson.fromJson(response, data.class).getAlarms() != null) {
                    List<String> alarmy = gson.fromJson(response, data.class).getAlarms();

                    re.setLength(0);
                    for (int x = 0; x < alarmy.size(); x++) {
                        re.append(alarmy.get(x) + "\r\n");

                    }
                }*/
                Gson gson = new GsonBuilder().create();
                boolean sta = gson.fromJson(response, Inputdata.class).getChanged();
                if (sta) {
                    Type listType = new TypeToken<List<Inputdata.Davkovac>>() {
                    }.getType();
                    JsonParser parser = new JsonParser();
                    JsonObject o = parser.parse(response).getAsJsonObject();

                    List<Inputdata.Davkovac> inty = new Gson().fromJson(o.get("davkovace"), listType);
                    for (int x = 0; x < inty.size(); x++) {
                        re.append("device " + (x + 1) + ": " + inty.get(x).Alarmy + "\r\n");


                    }
                    result = re.toString();
                }
                else result = null;






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
 class data
    {
        private boolean check;
        public static boolean finished;
        private String davkovac;
        private List<String> alarm;

        public data(){
        }

        public data(boolean change, String davkovac){
            //this.check = check;
            this.davkovac = davkovac;

        }

        public boolean getChanged()
        {
            return check;
        }
        public static boolean getFinished()
        {
            return finished;
        }

        public String getDevice()
        {
            return davkovac;
        }


        public List<String> getAlarms()
        {
            return alarm;
        }


        /*@Override
        public String toString()
        {
            return "Employee [id=" + id + ", firstName=" + firstName + ", " +
                    "lastName=" + lastName + ", roles=" + roles + "]";
        }*/
    }






