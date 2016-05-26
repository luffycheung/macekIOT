using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Net;
using System.Net.Sockets;
using System.Data.SqlClient;
using System.Windows.Forms.DataVisualization.Charting;
using tcpServer;
using System.Threading;
using System.Threading.Tasks;
using System.Globalization;
using Newtonsoft.Json;

namespace IoTbrain
{
    

    public partial class Form1 : Form
    {
        BackgroundWorker bw0 = new BackgroundWorker();
        BackgroundWorker bw1 = new BackgroundWorker();
        BackgroundWorker bw2 = new BackgroundWorker();
        BackgroundWorker bw3 = new BackgroundWorker();
        string re1 = "0.00";
        string re2 = "0.00";
        string re3 = "0.00";
        string command1;
        string command2;
        string command3;
        string sw_command1;
        
        private SqlConnection sql;



        bool enabled;
        bool obdobi;
        int period;
        int target;
        int kld1;
        int kld2;
        float hyster;

        //TcpServer tcpServer2 = new TcpServer();

        public Form1()
        {
             
            InitializeComponent();

            bw0.DoWork += new DoWorkEventHandler(bw0_DoWork);

            bw1.WorkerSupportsCancellation = true;
            bw1.WorkerReportsProgress = true;
            bw1.DoWork +=  new DoWorkEventHandler(bw1_DoWork);
            bw1.RunWorkerCompleted += new RunWorkerCompletedEventHandler(bw1_RunWorkerCompleted);

            bw2.WorkerSupportsCancellation = true;
            bw2.WorkerReportsProgress = true;
            bw2.DoWork += new DoWorkEventHandler(bw2_DoWork);
            bw2.RunWorkerCompleted += new RunWorkerCompletedEventHandler(bw2_RunWorkerCompleted);

            bw3.WorkerSupportsCancellation = true;
            bw3.WorkerReportsProgress = true;
            bw3.DoWork += new DoWorkEventHandler(bw3_DoWork);
            bw3.RunWorkerCompleted += new RunWorkerCompletedEventHandler(bw3_RunWorkerCompleted);

            tcpServer2.Port = 90;
            tcpServer2.Open();

            if (checkBox3.Checked) timerS.Start();
                    
        }

        private void timerS_Tick(object sender, EventArgs e)
        {
            
            SQLbutton.PerformClick();
            
            if (tcpServer2.IsOpen) log.AppendText("TCP server started" + "\r\n");
            
            

            timerS.Stop();
        }
        private void Form1_FormClosed(object sender, FormClosedEventArgs e)
        {
            tcpServer2.Close();
        }

        

        

        #region DEVICE1

        private void timer1_Tick(object sender, EventArgs e)
        {
            
            command1 = commandbox1.Text;
            log.AppendText("TCP/command1: " + command1 + "\r\n");
            bw1.RunWorkerAsync();
            
            
            timer1.Stop();


        }

        private void dev1en_CheckedChanged(object sender, EventArgs e)
        {
            if (dev1en.Checked)
            {
                command1 = commandbox1.Text;
                
                timer1.Start();
                log.AppendText("Async1 enabled" + "\r\n");
            }
            else
            {
                
                
                bw1.CancelAsync();
                timer1.Stop();
                log.AppendText("Async1 disabled" + "\r\n");
            }
        }
        
        private void dev1sql_CheckedChanged(object sender, EventArgs e)
        {
            if (dev1sql.Checked)
            {
                labelbox1.Enabled = false;
            }
            else
            {
                labelbox1.Enabled = true;
            }

        }

        private void bw1_DoWork(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;

                if ((worker.CancellationPending == true) || (dev1en.Checked == false))
                {
                    e.Cancel = true;
                   
                  
                }
                else
                {
                    double x;
                    double.TryParse(tickbox1.Text, out x);
                    x = x * 60000;
                    timer1.Interval = (int)Math.Round(x);
                    int port;
                    int.TryParse(portbox1.Text, out port);
                    string response = TCPsend(command1, ipbox1.Text, port);
                    //log.AppendText(response);
                    re1 = response;
                    
                }
            
        }
        
        private void bw1_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {
            if ((e.Cancelled == true))
            {
                log.AppendText("TCP canceled" + "\r\n");
            }

            else if (!(e.Error == null))
            {
                log.AppendText("Async error" + "\r\n");
            }

            else
            {
                
                log.AppendText("TCP/response: " + re1 + "\r\n");
                
            
                if (dev1sql.Checked)
                {
                    try
                    {
                         
                         int date_day = DateTime.Now.Day;
                         int date_month = DateTime.Now.Month;
                         int date_year = DateTime.Now.Year;
                         int time_hour = DateTime.Now.Hour;
                         int time_minute = DateTime.Now.Minute;
                        Int32 unixTimestamp = (Int32)(DateTime.UtcNow.Subtract(new DateTime(1970, 1, 1))).TotalSeconds;
                        string s = "','";

   
                         
                         SqlCommand mycommand1 = new SqlCommand("INSERT INTO " + labelbox1.Text + "(year,month,day,hour,minute,temp,unixtime) VALUES ('"+ date_year +s+ date_month +s+ date_day +s+ time_hour +s+ time_minute +s+ re1 +s+unixTimestamp+"');", sql);
                         mycommand1.ExecuteNonQuery();

                    }
                    catch (Exception e2)
                    {
                        log.AppendText(e2.ToString());

                    }

                }

                
                timer1.Start();
            }
        }
       
        
         #endregion

        #region DEVICE2

        private void timer2_Tick(object sender, EventArgs e)
        {

            command2 = commandbox2.Text;
            log.AppendText("TCP2/command: " + command2 + "\r\n");
            bw2.RunWorkerAsync();


            timer2.Stop();


        }

        private void dev2en_CheckedChanged(object sender, EventArgs e)
        {
            if (dev2en.Checked)
            {
                command2 = commandbox2.Text;
                timer2.Start();
                log.AppendText("Async2 enabled" + "\r\n");
            }
            else
            {


                bw2.CancelAsync();
                timer2.Stop();
                log.AppendText("Async2 disabled" + "\r\n");
            }
        }

        private void dev2sql_CheckedChanged(object sender, EventArgs e)
        {
            if (dev2sql.Checked)
            {
                labelbox2.Enabled = false;
            }
            else
            {
                labelbox2.Enabled = true;
            }

        }

        private void bw2_DoWork(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;

            if ((worker.CancellationPending == true) || (dev2en.Checked == false))
            {
                e.Cancel = true;


            }
            else
            {
                double x;
                double.TryParse(tickbox2.Text, out x);
                x = x * 60000;
                timer2.Interval = (int)Math.Round(x);
                int port;
                int.TryParse(portbox2.Text, out port);
                string response = TCPsend(command2, ipbox2.Text, port);
                
                re2 = response;

            }

        }

        private void bw2_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {
            if ((e.Cancelled == true))
            {
                log.AppendText("TCP canceled" + "\r\n");
            }

            else if (!(e.Error == null))
            {
                log.AppendText("Async error" + "\r\n");
            }

            else
            {

                log.AppendText("TCP/response: " + re2 + "\r\n");


                if (dev2sql.Checked)
                {
                    try
                    {

                        int date_day = DateTime.Now.Day;
                        int date_month = DateTime.Now.Month;
                        int date_year = DateTime.Now.Year;
                        int time_hour = DateTime.Now.Hour;
                        int time_minute = DateTime.Now.Minute;
                        Int32 unixTimestamp = (Int32)(DateTime.UtcNow.Subtract(new DateTime(1970, 1, 1))).TotalSeconds;
                        string s = "','";



                        SqlCommand mycommand1 = new SqlCommand("INSERT INTO " + labelbox2.Text + "(year,month,day,hour,minute,temp,unixtime) VALUES ('" + date_year + s + date_month + s + date_day + s + time_hour + s + time_minute + s + re2 + s + unixTimestamp +"');", sql);
                        mycommand1.ExecuteNonQuery();

                    }
                    catch (Exception e2)
                    {
                        log.AppendText(e2.ToString());

                    }

                }


                timer2.Start();
            }
        }


        #endregion

        #region DEVICE3

        private void timer3_Tick(object sender, EventArgs e)
        {

            command3 = commandbox3.Text;
            log.AppendText("TCP3/command: " + command3 + "\r\n");
            bw3.RunWorkerAsync();


            timer3.Stop();


        }

        private void dev3en_CheckedChanged(object sender, EventArgs e)
        {
            if (dev3en.Checked)
            {
                command3 = commandbox3.Text;
                timer3.Start();
                log.AppendText("Async3 enabled" + "\r\n");
            }
            else
            {


                bw3.CancelAsync();
                timer3.Stop();
                log.AppendText("Async3 disabled" + "\r\n");
            }
        }

        private void dev3sql_CheckedChanged(object sender, EventArgs e)
        {
            if (dev3sql.Checked)
            {
                labelbox3.Enabled = false;
            }
            else
            {
                labelbox3.Enabled = true;
            }

        }

        private void bw3_DoWork(object sender, DoWorkEventArgs e)
        {
            BackgroundWorker worker = sender as BackgroundWorker;

            if ((worker.CancellationPending == true) || (dev3en.Checked == false))
            {
                e.Cancel = true;


            }
            else
            {
                double x;
                double.TryParse(tickbox3.Text, out x);
                x = x * 60000;
                timer3.Interval = (int)Math.Round(x);
                int port;
                int.TryParse(portbox3.Text, out port);
                string response = TCPsend(command3, ipbox3.Text, port);

                re3 = response;

            }

        }

        private void bw3_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {
            if ((e.Cancelled == true))
            {
                log.AppendText("TCP canceled" + "\r\n");
            }

            else if (!(e.Error == null))
            {
                log.AppendText("Async error" + "\r\n");
            }

            else
            {

                log.AppendText("TCP/response: " + re3 + "\r\n");


                if (dev3sql.Checked)
                {
                    try
                    {

                        int date_day = DateTime.Now.Day;
                        int date_month = DateTime.Now.Month;
                        int date_year = DateTime.Now.Year;
                        int time_hour = DateTime.Now.Hour;
                        int time_minute = DateTime.Now.Minute;
                        Int32 unixTimestamp = (Int32)(DateTime.UtcNow.Subtract(new DateTime(1970, 1, 1))).TotalSeconds;
                        string s = "','";



                        SqlCommand mycommand1 = new SqlCommand("INSERT INTO " + labelbox3.Text + "(year,month,day,hour,minute,temp,unixtime) VALUES ('" + date_year + s + date_month + s + date_day + s + time_hour + s + time_minute + s + re3 + s+unixTimestamp+"');", sql);
                        mycommand1.ExecuteNonQuery();

                    }
                    catch (Exception e2)
                    {
                        log.AppendText(e2.ToString());

                    }

                }


                timer3.Start();
            }
        }


        #endregion


        #region Buttony


        private void refreshtablebutton_Click(object sender, EventArgs e)
        {
            

            
                int date_day = DateTime.Now.Day;
            //int date_day = 5;
                int h = 0;
                string table = textBox1.Text;
                List<double> temps = new List<double>();
                chart1.Series[table].Points.Clear();
                chart1.Series["temp2_1max"].Points.Clear();
                chart1.Series["temp2_1min"].Points.Clear();
                for (h = 0; h <= 24; h++)
                {
                    

                    try {
                    SqlCommand comm = new SqlCommand("(SELECT temp FROM "+table +" WHERE day = '" + date_day + "' AND hour = '" + h + "')", sql);
                    SqlDataReader reader = comm.ExecuteReader();

                    List<double> inttemps = new List<double>();
                        while (reader.Read())
                        {

                            
                            double value;
                            double.TryParse(reader.GetValue(0).ToString(), NumberStyles.Number, CultureInfo.InvariantCulture, out value);
                            inttemps.Add(value);
                           
                        

                        }
                        reader.Close();


                        if (inttemps.Count >= 2)
                        {
                            temps.Add(Math.Round(inttemps.Average(), 2));
                        }
                        else
                        {
                            temps.Add(0);
                        }


                        if (temps[h] != 0)
                        {
                            chart1.Series[table].Points.AddXY(h, temps[h]);
                            
                            if (table == "temp2_1")
                            {
                                chart1.Series[table + "max"].Points.AddXY(h, inttemps.Max());
                                chart1.Series[table + "min"].Points.AddXY(h, inttemps.Min());
                                
                            }

                        }
                        else
                        {
                            chart1.Series[table].Points.AddXY(h, 0);
                            if (table == "temp2_1")
                            {
                                chart1.Series[table + "min"].Points.AddXY(h, 0);

                                chart1.Series[table + "max"].Points.AddXY(h, 0);
                            }

                        }
                        
                        if (table == "TEMP1") chart1.ChartAreas["ChartArea1"].AxisY.Maximum = temps.Max() + 5;
                       
                        
                    }

                    catch (Exception e2)
                    {
                       log.AppendText(e2.ToString());
                      

                    }

                   


                }
                //chart1.ChartAreas["ChartArea1"].AxisY.Maximum = chart1.ChartAreas["ChartArea1"].AxisY.Maximum + 10;
                //chart1.Series[table + "min"].Points.FindMaxByValue();

                //chart1.Series[table + "max"].Points.AddXY(h, 0);

            chart1.Invalidate();

               
               
            }
            
 

        public void connectSQLbutton_Click(object sender, EventArgs e)
        {

            string user = sql_user.Text;
            string password = sql_pass.Text;
            string database = sql_db.Text;
            string IP = sql_ip.Text;
         
            
           //puvodni sql = new SqlConnection("user id="+ user + ";password="+password+";server="+IP+";Trusted_Connection=yes;database="+database+";connection timeout=30"); 
            sql = new SqlConnection("user id=" + user + ";password=" + password + ";server=" + IP + ";Initial Catalog=" + database + ";connection timeout=30"); 

            try
            {
                sql.Open();
                log.AppendText("SQL: " + sql.State.ToString() + "\r\n");
                dev1sql.Enabled = true; //dev1

                tablerefresh.Enabled = true;
                SQLbutton2.Enabled = true;
                SQLbutton.Enabled = false;
                sql_db.Enabled = false;
                sql_pass.Enabled = false;
                sql_user.Enabled = false;
                sql_ip.Enabled = false;

            }


            catch (Exception e2)
            {
                log.AppendText(e2.ToString());

            }
            tablerefresh.PerformClick();

        }

        

        private void disconnectSQLbutton_Click(object sender, EventArgs e)
        {
            sql.Close();
            
            dev1sql.Checked = false; //dev1
            dev1sql.Enabled = false;
            
            
            
            tablerefresh.Enabled = false;
            SQLbutton2.Enabled = false;
            SQLbutton.Enabled = true;

            sql_db.Enabled = true;
            sql_pass.Enabled = true;
            sql_user.Enabled = true;
            sql_ip.Enabled = true;
            
            log.AppendText("SQL: " + sql.State.ToString() + "\r\n");
        }
        
        #endregion

        


       #region tcpservery


        public delegate void invokeDelegate();
        private void tcpServer2_OnDataAvailable(TcpServerConnection connection)
        {
           // byte[] data = readStream(connection.Socket);
                

            

                string dataStr;
                System.Net.Sockets.NetworkStream stream = connection.Socket.GetStream();
                Byte [] data = new Byte[256];
                String responseData = String.Empty;
                Int32 bytes = stream.Read(data, 0, data.Length);
                dataStr = System.Text.Encoding.ASCII.GetString(data, 0, bytes);
                
                invokeDelegate del = () =>
                {
                    TCP_request_reply(dataStr);
                   
                };
                Invoke(del);
               
            
        }
        private void TCP_request_reply(string rcvd)
        {
            string response = null;
            log.AppendText("Something came " + rcvd + "\r\n");
            if (rcvd.Contains("SWITCH1"))
            {

                string sw = rcvd;
                if (rcvd.Length > 10)
                {
                    sw = rcvd.Substring(0, 9);
                    Int16 minute;
                    Int16.TryParse(rcvd.Substring(10), out minute);
                    if (minute != 0)
                    {
                        log.AppendText("Setting ventilator shutdown after " + minute.ToString() + "\r\n");
                        timer4.Interval = minute * 60 * 1000;
                        timer4.Start();
                    }
                    

                }
                         
                response = Send_switch("SWITCH1", sw);
                tcpServer2.Send(response);
                log.AppendText("Sending back " + response + "\r\n");



            }
            if(rcvd.Contains("temp"))
            {
                //response = SQL_last_temp(rcvd);
                //tcpServer2.Send(response);
                log.AppendText("Sending back " + response + "\r\n");
            }

            if (rcvd.Contains("JSONteploty"))
            {
                response = JSONteploty();
                tcpServer2.Send(response);
                log.AppendText("Sending back " + response + "\r\n" + "sent " + response.Length.ToString() + "bytes" + "\r\n");
            }

            if (rcvd.Contains("JSONcommand"))
            {
                string cmd = rcvd.Substring(11);
                JSONcommand(cmd);
               // log.AppendText("Sending back " + response + "\r\n" + "sent " + response.Length.ToString() + "bytes" + "\r\n");
            }



            if (rcvd.Contains("MTP"))
            {
                string table = "temp" + rcvd.Substring(4, 3);
                string temp = rcvd.Substring(8);


                try
                {

                    int date_day = DateTime.Now.Day;
                    int date_month = DateTime.Now.Month;
                    int date_year = DateTime.Now.Year;
                    int time_hour = DateTime.Now.Hour;
                    int time_minute = DateTime.Now.Minute;
                    Int32 unixTimestamp = (Int32)(DateTime.UtcNow.Subtract(new DateTime(1970, 1, 1))).TotalSeconds;
                    string s = "','";



                    SqlCommand mycommand1 = new SqlCommand("INSERT INTO " + table + "(year,month,day,hour,minute,temp,unixtime) VALUES ('" + date_year + s + date_month + s + date_day + s + time_hour + s + time_minute + s + temp + s+ unixTimestamp+"');", sql);
                    mycommand1.ExecuteNonQuery();

                    log.AppendText("writing " + temp + " into "+ table + "\r\n");
                }
                catch (Exception e2)
                {
                    log.AppendText(e2.ToString());

                }


            }
            
            //response = SQL_last_temp(rcvd);
            
            //TCPsend(response);
            
           
            
            
        }
        
        #endregion
            
        #region SQLdotazy
        public string SQL_avg_hour(string table)
        {
            try
            {
                String queryresult = String.Empty;
                
                string time_hour = DateTime.Now.Hour.ToString();
                string time_prevhour = (DateTime.Now.Hour - 1).ToString();
                string time_minute = DateTime.Now.Minute.ToString();
                string time_year = DateTime.Now.Year.ToString();
                string time_month = DateTime.Now.Month.ToString();
                string time_day = DateTime.Now.Day.ToString();

                SqlCommand comm = new SqlCommand("(SELECT temp FROM "+ table +" WHERE year='"+ time_year +"' AND month='"+time_month+"' AND day='"+time_day+"' AND((hour = '"+ time_prevhour +"' AND minute > '"+time_minute+"') OR hour = '"+ time_hour +"'))", sql);
                SqlDataReader reader = comm.ExecuteReader();
                List<double>temps = new List<double>();
               
                
                while (reader.Read())
                {

                                    
                    double value;
                    double.TryParse(reader.GetValue(0).ToString(), NumberStyles.Number, CultureInfo.InvariantCulture, out value);
                    temps.Add(value);
                    //log.AppendText(value+" ");
                    

                }
                reader.Close();
                
                queryresult = Math.Round(temps.Average(),2).ToString();

             

                return queryresult;
            }
            catch (Exception e2)
            {
                log.AppendText(e2.ToString());

            }
            return "could not find anything";
        }
        
        public string SQL_last_temp(string table)
        {
            try
            {
                String queryresult = String.Empty;



                SqlCommand comm = new SqlCommand("SELECT TOP 2 [temp],[unixtime] FROM " + table + " ORDER BY id DESC;", sql);
                SqlDataReader reader = comm.ExecuteReader();


                List<double> temps = new List<double>();
                List<Int32> times = new List<Int32>();
                while (reader.Read())
                {

                    double temp;
                    double.TryParse(reader.GetValue(0).ToString(), NumberStyles.Number, CultureInfo.InvariantCulture, out temp);
                    temps.Add(temp);

                    Int32 time;
                    Int32.TryParse(reader.GetValue(1).ToString(), NumberStyles.Number, CultureInfo.InvariantCulture, out time);
                    times.Add(time);




                }
                reader.Close();

                Int32 timenow = (Int32)(DateTime.UtcNow.Subtract(new DateTime(1970, 1, 1))).TotalSeconds;
                double k = ((temps[0] - temps[1]) / (times[0] - times[1]));
                double result_temp = k * (timenow-times[0]) + temps[0];
                
                queryresult = Math.Round(result_temp, 2).ToString();


                return queryresult;
            }
            catch (Exception e2)
            {
                log.AppendText(e2.ToString());

            }
            return "could not find anything";
        }


        public List<float> SQL_whole_day(string table)
        {
            try
            {

                string time_day = DateTime.Now.Day.ToString();
                string time_month = DateTime.Now.Month.ToString();
                string time_year = DateTime.Now.Hour.ToString();

                Int32 unixtime = (Int32)(DateTime.UtcNow.Subtract(new DateTime(1970, 1, 1))).TotalSeconds;
                Int32 cas = unixtime - 86400;
                SqlCommand comm = new SqlCommand("(SELECT [temp],[unixtime] FROM [" + table + "] WHERE [unixtime] > "+cas+") ORDER BY [id]", sql);
                SqlDataReader reader = comm.ExecuteReader();

                List<double> inttemps = new List<double>();
                Int32 hour;
                //double value;
                Int32 prevhour = cas - 5400;
                List<float> temps = new List<float>();

                while (reader.Read())
                {


                    double value;
                    double.TryParse(reader.GetValue(0).ToString(), NumberStyles.Number, CultureInfo.InvariantCulture, out value);
                    inttemps.Add(value);
                    Int32.TryParse(reader.GetValue(1).ToString(), out hour);
                    //hour =  (Int16)reader.GetValue(1);
                    if ((hour - prevhour)>1799)
                    {
                        prevhour = hour;
                        temps.Add((float) Math.Round(inttemps.Average(),2));
                        //log.AppendText(inttemps.Average().ToString());
                        inttemps.Clear();
                        
                    }


                }
                reader.Close();
                return temps;
            }
            catch (Exception e2)
            {
                log.AppendText(e2.ToString());

            }
            return null;
        }
        
        #endregion
        
        
        #region TCPprikazy
        
        public string Send_switch(string table, string sw)
        {
            try
            {
                String check = "null" ;



               if (sw.Contains("="))

                {
                    sw_command1 = sw;
                    bw0.RunWorkerAsync();

                    SqlCommand mycommand1 = new SqlCommand("INSERT INTO " + table + " (state) VALUES (" + sw.Substring(8) + ");", sql);
                    mycommand1.ExecuteNonQuery();

                }
                else
                   {
                       
                    SqlCommand comm = new SqlCommand("SELECT TOP 1 state FROM " + sw + " ORDER BY id DESC;", sql);
                    SqlDataReader reader = comm.ExecuteReader();

                    while (reader.Read())
                    {
                        check = reader.GetValue(0).ToString();
                    }
                    reader.Close();
                }                 


                //check =  TCPsend(sw);

                return check;
                
                
            }
            catch (Exception e2)
            {
                log.AppendText(e2.ToString());

            }
            return "could not find anything";
        }

        public string TCPsend(string message, string IP, int port)
        {
            try
            {
               // Int32 port = 43333;
               // string IP = ipbox1.Text;
                

                System.Net.Sockets.TcpClient client = new System.Net.Sockets.TcpClient(IP, port);
                Byte[] data = Encoding.ASCII.GetBytes(message);
                System.Net.Sockets.NetworkStream stream = client.GetStream();
                stream.Write(data, 0, data.Length);

                data = new Byte[256];
                String responseData = String.Empty;
                Int32 bytes = stream.Read(data, 0, data.Length);
                responseData = System.Text.Encoding.ASCII.GetString(data, 0, bytes);

                stream.Close();
                client.Close();
                return responseData;
            }
            catch
            {
                log.AppendText("TCP failed: " + command1 + "\r\n");
            }
            return "TCP error";
        }
        
         public void TCPsend2(string message, string IP, int port)
        {
            try
            {
                // Int32 port = 43333;
                // string IP = ipbox1.Text;


                System.Net.Sockets.TcpClient client = new System.Net.Sockets.TcpClient(IP, port);
                Byte[] data = Encoding.ASCII.GetBytes(message);
                System.Net.Sockets.NetworkStream stream = client.GetStream();
                stream.Write(data, 0, data.Length);

               /* data = new Byte[256];
                String responseData = String.Empty;
                Int32 bytes = stream.Read(data, 0, data.Length);
                responseData = System.Text.Encoding.ASCII.GetString(data, 0, bytes);*/

                stream.Close();
                client.Close();
                //return responseData;
            }
            catch
            {
                log.AppendText("TCP failed: " + command1 + "\r\n");
            }
            //return "TCP error";
        }
        
        private void bw0_DoWork(object sender, DoWorkEventArgs e)
        {
            int port;
            int.TryParse(portbox1.Text, out port);
            TCPsend2(sw_command1, ipbox1.Text, port);

        }
        
          
        #endregion

        #region JSONzpracovani

        public string JSONteploty()
        {
            string json = String.Empty;
            Product product = new Product();
            product.teplomery = new List<Teplomer>();
            string sw = String.Empty;
            try
            {
                sw = Send_switch("SWITCH1", "SWITCH1");

                if (sw.Contains("1")) product.check = true;
                else product.check = false;

                product.master = enabled;
                product.hys = hyster;
                product.obdobi = obdobi;
                product.period = period;
                product.klid1 = kld1;
                product.klid2 = kld2;

                product.teplomery.Add(new  Teplomer{ id = "1", teplota = SQL_last_temp("temp1_1"), teploty = SQL_whole_day("temp1_1") });
                product.teplomery.Add(new Teplomer { id = "2", teplota = SQL_last_temp("temp3_1"), teploty = SQL_whole_day("temp3_1") });
                product.teplomery.Add(new Teplomer { id = "3", teplota = SQL_last_temp("temp2_1"), teploty = SQL_whole_day("temp2_1") });
                
                //product.teplomery.Add(new Teplomer { id = "4", teploty = SQL_whole_day("temp2_1") });
                json = JsonConvert.SerializeObject(product);
                
                return json;
            }
            catch
            {
                
            }
            return "nada";
        }
        public void JSONcommand(string recieved)
        {


            objAut obj = new objAut();
            obj = JsonConvert.DeserializeObject<objAut>(recieved);
            enabled = obj.master;
            obdobi = obj.obdobi;
            period = obj.period;
           // target = obj.targ;
            kld1 = obj.klid1;
            kld2 = obj.klid2;
            hyster = obj.hys;

            aut_Timer.Interval = period * 60000;
            aut_Timer.Enabled = enabled;

            log.AppendText("Setting automatization - " +enabled+"; period: "+ period +"min; target: "+hyster+"°C"+"\r\n");


        }

        #endregion
       



        #region automatizace

        private void timer4_Tick(object sender, EventArgs e)
        {
            Send_switch("SWITCH1", "SWITCH1=0");
            log.AppendText("Ventilator automatically shut down" + "\r\n");
            timer4.Stop();
            
        }

        private void aut_Timer_Tick(object sender, EventArgs e)
        {

            bool sw;
            if (Send_switch("SWITCH1", "SWITCH1").Contains("1")) sw = true;
            else sw = false;



            if (enabled && (TimeBetween(kld1, kld2) == false))
             {
               float tPokoj = float.Parse(SQL_last_temp("temp3_1"));
               float tObyvak = float.Parse(SQL_last_temp("temp1_1"));

                
                


                

                if (obdobi)
                {
                    if ((tPokoj - tObyvak > hyster))
                    {
                        if (sw == false) Send_switch("SWITCH1", "SWITCH1=1");

                    }
                    else
                    {
                        if (sw == true) Send_switch("SWITCH1", "SWITCH1=0");
                    }

                }
                else
                {
                    if ((tObyvak - tPokoj > hyster))
                    {
                        if (sw == false) Send_switch("SWITCH1", "SWITCH1=1");

                    }
                    else
                    {
                        if (sw == true) Send_switch("SWITCH1", "SWITCH1=0");
                    }

                }



            }
            else
            {
                if (sw == true) Send_switch("SWITCH1", "SWITCH1=0");
            }




        }

        private bool TimeBetween(int kld1, int kld2)
        {
            // convert datetime to a TimeSpan
            TimeSpan start = new TimeSpan(kld1, 0, 0);
            TimeSpan end = new TimeSpan(kld2, 0, 0);
            TimeSpan now = DateTime.Now.TimeOfDay;
            // see if start comes before end
            if (start < end)
                return start <= now && now <= end;
            // start is after end, so do the inverse comparison
            return !(end < now && now < start);
        }

    }
    
    #endregion

    #region jsonClassy
    
    public class Product
    {
        public Boolean check;
        public Boolean master;
        public Boolean obdobi;
        public int period;
        public int klid1;
        public int klid2;
        public float hys;
        public List<Teplomer> teplomery { get; set; }
        

    }

    public class objAut
    {
        public Boolean master;
        public Boolean obdobi;
        public int period;
       // public int targ;
        public int klid1;
        public int klid2;
        public float hys;

    }


    public class Teplomer
    {
        public string id { get; set; }
        public string teplota { get; set; }
        public List<float> teploty { get; set; }

    }
    
    #endregion





}

