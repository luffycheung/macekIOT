namespace IoTbrain
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.Windows.Forms.DataVisualization.Charting.ChartArea chartArea1 = new System.Windows.Forms.DataVisualization.Charting.ChartArea();
            System.Windows.Forms.DataVisualization.Charting.Legend legend1 = new System.Windows.Forms.DataVisualization.Charting.Legend();
            System.Windows.Forms.DataVisualization.Charting.Series series1 = new System.Windows.Forms.DataVisualization.Charting.Series();
            System.Windows.Forms.DataVisualization.Charting.Series series2 = new System.Windows.Forms.DataVisualization.Charting.Series();
            System.Windows.Forms.DataVisualization.Charting.Series series3 = new System.Windows.Forms.DataVisualization.Charting.Series();
            System.Windows.Forms.DataVisualization.Charting.Series series4 = new System.Windows.Forms.DataVisualization.Charting.Series();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.timer1 = new System.Windows.Forms.Timer(this.components);
            this.devices = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.label6 = new System.Windows.Forms.Label();
            this.labelbox1 = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.dev1sql = new System.Windows.Forms.CheckBox();
            this.dev1en = new System.Windows.Forms.CheckBox();
            this.label4 = new System.Windows.Forms.Label();
            this.commandbox1 = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.tickbox1 = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.portbox1 = new System.Windows.Forms.TextBox();
            this.ipbox1 = new System.Windows.Forms.TextBox();
            this.tabPage2 = new System.Windows.Forms.TabPage();
            this.label14 = new System.Windows.Forms.Label();
            this.labelbox2 = new System.Windows.Forms.TextBox();
            this.label15 = new System.Windows.Forms.Label();
            this.dev2sql = new System.Windows.Forms.CheckBox();
            this.dev2en = new System.Windows.Forms.CheckBox();
            this.label16 = new System.Windows.Forms.Label();
            this.commandbox2 = new System.Windows.Forms.TextBox();
            this.label17 = new System.Windows.Forms.Label();
            this.tickbox2 = new System.Windows.Forms.TextBox();
            this.label18 = new System.Windows.Forms.Label();
            this.label19 = new System.Windows.Forms.Label();
            this.portbox2 = new System.Windows.Forms.TextBox();
            this.ipbox2 = new System.Windows.Forms.TextBox();
            this.tabPage3 = new System.Windows.Forms.TabPage();
            this.label20 = new System.Windows.Forms.Label();
            this.labelbox3 = new System.Windows.Forms.TextBox();
            this.label21 = new System.Windows.Forms.Label();
            this.dev3sql = new System.Windows.Forms.CheckBox();
            this.dev3en = new System.Windows.Forms.CheckBox();
            this.label22 = new System.Windows.Forms.Label();
            this.commandbox3 = new System.Windows.Forms.TextBox();
            this.label23 = new System.Windows.Forms.Label();
            this.tickbox3 = new System.Windows.Forms.TextBox();
            this.label24 = new System.Windows.Forms.Label();
            this.label25 = new System.Windows.Forms.Label();
            this.portbox3 = new System.Windows.Forms.TextBox();
            this.ipbox3 = new System.Windows.Forms.TextBox();
            this.log = new System.Windows.Forms.RichTextBox();
            this.tablerefresh = new System.Windows.Forms.Button();
            this.ioTDataSet = new IoTbrain.IoTDataSet();
            this.chart1 = new System.Windows.Forms.DataVisualization.Charting.Chart();
            this.label7 = new System.Windows.Forms.Label();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.label8 = new System.Windows.Forms.Label();
            this.label9 = new System.Windows.Forms.Label();
            this.sql_ip = new System.Windows.Forms.TextBox();
            this.sql_user = new System.Windows.Forms.TextBox();
            this.sql_pass = new System.Windows.Forms.TextBox();
            this.label10 = new System.Windows.Forms.Label();
            this.label11 = new System.Windows.Forms.Label();
            this.label12 = new System.Windows.Forms.Label();
            this.sql_db = new System.Windows.Forms.TextBox();
            this.label13 = new System.Windows.Forms.Label();
            this.SQLbutton = new System.Windows.Forms.Button();
            this.checkBox3 = new System.Windows.Forms.CheckBox();
            this.timerS = new System.Windows.Forms.Timer(this.components);
            this.SQLbutton2 = new System.Windows.Forms.Button();
            this.comboBox1 = new System.Windows.Forms.ComboBox();
            this.timer2 = new System.Windows.Forms.Timer(this.components);
            this.timer3 = new System.Windows.Forms.Timer(this.components);
            this.tcpServer2 = new tcpServer.TcpServer(this.components);
            this.timer4 = new System.Windows.Forms.Timer(this.components);
            this.aut_Timer = new System.Windows.Forms.Timer(this.components);
            this.devices.SuspendLayout();
            this.tabPage1.SuspendLayout();
            this.tabPage2.SuspendLayout();
            this.tabPage3.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.ioTDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.chart1)).BeginInit();
            this.SuspendLayout();
            // 
            // timer1
            // 
            this.timer1.Interval = 2000;
            this.timer1.Tick += new System.EventHandler(this.timer1_Tick);
            // 
            // devices
            // 
            this.devices.Controls.Add(this.tabPage1);
            this.devices.Controls.Add(this.tabPage2);
            this.devices.Controls.Add(this.tabPage3);
            this.devices.Location = new System.Drawing.Point(12, 135);
            this.devices.Name = "devices";
            this.devices.SelectedIndex = 0;
            this.devices.Size = new System.Drawing.Size(193, 217);
            this.devices.TabIndex = 2;
            // 
            // tabPage1
            // 
            this.tabPage1.Controls.Add(this.label6);
            this.tabPage1.Controls.Add(this.labelbox1);
            this.tabPage1.Controls.Add(this.label5);
            this.tabPage1.Controls.Add(this.dev1sql);
            this.tabPage1.Controls.Add(this.dev1en);
            this.tabPage1.Controls.Add(this.label4);
            this.tabPage1.Controls.Add(this.commandbox1);
            this.tabPage1.Controls.Add(this.label3);
            this.tabPage1.Controls.Add(this.tickbox1);
            this.tabPage1.Controls.Add(this.label2);
            this.tabPage1.Controls.Add(this.label1);
            this.tabPage1.Controls.Add(this.portbox1);
            this.tabPage1.Controls.Add(this.ipbox1);
            this.tabPage1.Location = new System.Drawing.Point(4, 22);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(185, 191);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "obyvák";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label6.Location = new System.Drawing.Point(139, 92);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(32, 16);
            this.label6.TabIndex = 12;
            this.label6.Text = "min";
            // 
            // labelbox1
            // 
            this.labelbox1.Location = new System.Drawing.Point(71, 164);
            this.labelbox1.Name = "labelbox1";
            this.labelbox1.Size = new System.Drawing.Size(100, 20);
            this.labelbox1.TabIndex = 11;
            this.labelbox1.Text = "temp1_1";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label5.Location = new System.Drawing.Point(16, 168);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(43, 16);
            this.label5.TabIndex = 10;
            this.label5.Text = "table";
            // 
            // dev1sql
            // 
            this.dev1sql.AutoSize = true;
            this.dev1sql.Enabled = false;
            this.dev1sql.Location = new System.Drawing.Point(71, 141);
            this.dev1sql.Name = "dev1sql";
            this.dev1sql.Size = new System.Drawing.Size(76, 17);
            this.dev1sql.TabIndex = 9;
            this.dev1sql.Text = "log to SQL";
            this.dev1sql.UseVisualStyleBackColor = true;
            this.dev1sql.CheckedChanged += new System.EventHandler(this.dev1sql_CheckedChanged);
            // 
            // dev1en
            // 
            this.dev1en.AutoSize = true;
            this.dev1en.Location = new System.Drawing.Point(71, 12);
            this.dev1en.Name = "dev1en";
            this.dev1en.Size = new System.Drawing.Size(58, 17);
            this.dev1en.TabIndex = 8;
            this.dev1en.Text = "enable";
            this.dev1en.UseVisualStyleBackColor = true;
            this.dev1en.CheckedChanged += new System.EventHandler(this.dev1en_CheckedChanged);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label4.Location = new System.Drawing.Point(15, 115);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(37, 16);
            this.label4.TabIndex = 7;
            this.label4.Text = "cmd";
            // 
            // commandbox1
            // 
            this.commandbox1.Location = new System.Drawing.Point(71, 111);
            this.commandbox1.Name = "commandbox1";
            this.commandbox1.Size = new System.Drawing.Size(100, 20);
            this.commandbox1.TabIndex = 6;
            this.commandbox1.Text = "TEMP1";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label3.Location = new System.Drawing.Point(6, 92);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(53, 16);
            this.label3.TabIndex = 5;
            this.label3.Text = "period";
            // 
            // tickbox1
            // 
            this.tickbox1.Location = new System.Drawing.Point(71, 87);
            this.tickbox1.Name = "tickbox1";
            this.tickbox1.Size = new System.Drawing.Size(62, 20);
            this.tickbox1.TabIndex = 4;
            this.tickbox1.Text = "1";
            this.tickbox1.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label2.Location = new System.Drawing.Point(30, 66);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(35, 16);
            this.label2.TabIndex = 3;
            this.label2.Text = "port";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label1.Location = new System.Drawing.Point(43, 36);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(22, 16);
            this.label1.TabIndex = 2;
            this.label1.Text = "IP";
            // 
            // portbox1
            // 
            this.portbox1.Location = new System.Drawing.Point(71, 61);
            this.portbox1.Name = "portbox1";
            this.portbox1.Size = new System.Drawing.Size(100, 20);
            this.portbox1.TabIndex = 1;
            this.portbox1.Text = "43333";
            // 
            // ipbox1
            // 
            this.ipbox1.Location = new System.Drawing.Point(71, 35);
            this.ipbox1.Name = "ipbox1";
            this.ipbox1.Size = new System.Drawing.Size(100, 20);
            this.ipbox1.TabIndex = 0;
            this.ipbox1.Text = "192.168.10.62";
            // 
            // tabPage2
            // 
            this.tabPage2.BackColor = System.Drawing.SystemColors.Control;
            this.tabPage2.Controls.Add(this.label14);
            this.tabPage2.Controls.Add(this.labelbox2);
            this.tabPage2.Controls.Add(this.label15);
            this.tabPage2.Controls.Add(this.dev2sql);
            this.tabPage2.Controls.Add(this.dev2en);
            this.tabPage2.Controls.Add(this.label16);
            this.tabPage2.Controls.Add(this.commandbox2);
            this.tabPage2.Controls.Add(this.label17);
            this.tabPage2.Controls.Add(this.tickbox2);
            this.tabPage2.Controls.Add(this.label18);
            this.tabPage2.Controls.Add(this.label19);
            this.tabPage2.Controls.Add(this.portbox2);
            this.tabPage2.Controls.Add(this.ipbox2);
            this.tabPage2.Location = new System.Drawing.Point(4, 22);
            this.tabPage2.Name = "tabPage2";
            this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage2.Size = new System.Drawing.Size(185, 191);
            this.tabPage2.TabIndex = 1;
            this.tabPage2.Text = "venkovni";
            // 
            // label14
            // 
            this.label14.AutoSize = true;
            this.label14.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label14.Location = new System.Drawing.Point(139, 92);
            this.label14.Name = "label14";
            this.label14.Size = new System.Drawing.Size(32, 16);
            this.label14.TabIndex = 25;
            this.label14.Text = "min";
            // 
            // labelbox2
            // 
            this.labelbox2.Location = new System.Drawing.Point(71, 164);
            this.labelbox2.Name = "labelbox2";
            this.labelbox2.Size = new System.Drawing.Size(100, 20);
            this.labelbox2.TabIndex = 24;
            this.labelbox2.Text = "temp2_1";
            // 
            // label15
            // 
            this.label15.AutoSize = true;
            this.label15.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label15.Location = new System.Drawing.Point(16, 168);
            this.label15.Name = "label15";
            this.label15.Size = new System.Drawing.Size(43, 16);
            this.label15.TabIndex = 23;
            this.label15.Text = "table";
            // 
            // dev2sql
            // 
            this.dev2sql.AutoSize = true;
            this.dev2sql.Location = new System.Drawing.Point(71, 141);
            this.dev2sql.Name = "dev2sql";
            this.dev2sql.Size = new System.Drawing.Size(76, 17);
            this.dev2sql.TabIndex = 22;
            this.dev2sql.Text = "log to SQL";
            this.dev2sql.UseVisualStyleBackColor = true;
            this.dev2sql.CheckedChanged += new System.EventHandler(this.dev2sql_CheckedChanged);
            // 
            // dev2en
            // 
            this.dev2en.AutoSize = true;
            this.dev2en.Location = new System.Drawing.Point(71, 12);
            this.dev2en.Name = "dev2en";
            this.dev2en.Size = new System.Drawing.Size(58, 17);
            this.dev2en.TabIndex = 21;
            this.dev2en.Text = "enable";
            this.dev2en.UseVisualStyleBackColor = true;
            this.dev2en.CheckedChanged += new System.EventHandler(this.dev2en_CheckedChanged);
            // 
            // label16
            // 
            this.label16.AutoSize = true;
            this.label16.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label16.Location = new System.Drawing.Point(15, 115);
            this.label16.Name = "label16";
            this.label16.Size = new System.Drawing.Size(37, 16);
            this.label16.TabIndex = 20;
            this.label16.Text = "cmd";
            // 
            // commandbox2
            // 
            this.commandbox2.Location = new System.Drawing.Point(71, 111);
            this.commandbox2.Name = "commandbox2";
            this.commandbox2.Size = new System.Drawing.Size(100, 20);
            this.commandbox2.TabIndex = 19;
            this.commandbox2.Text = "TEMP1";
            // 
            // label17
            // 
            this.label17.AutoSize = true;
            this.label17.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label17.Location = new System.Drawing.Point(6, 92);
            this.label17.Name = "label17";
            this.label17.Size = new System.Drawing.Size(53, 16);
            this.label17.TabIndex = 18;
            this.label17.Text = "period";
            // 
            // tickbox2
            // 
            this.tickbox2.Location = new System.Drawing.Point(71, 87);
            this.tickbox2.Name = "tickbox2";
            this.tickbox2.Size = new System.Drawing.Size(62, 20);
            this.tickbox2.TabIndex = 17;
            this.tickbox2.Text = "20";
            this.tickbox2.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            // 
            // label18
            // 
            this.label18.AutoSize = true;
            this.label18.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label18.Location = new System.Drawing.Point(30, 66);
            this.label18.Name = "label18";
            this.label18.Size = new System.Drawing.Size(35, 16);
            this.label18.TabIndex = 16;
            this.label18.Text = "port";
            // 
            // label19
            // 
            this.label19.AutoSize = true;
            this.label19.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label19.Location = new System.Drawing.Point(43, 36);
            this.label19.Name = "label19";
            this.label19.Size = new System.Drawing.Size(22, 16);
            this.label19.TabIndex = 15;
            this.label19.Text = "IP";
            // 
            // portbox2
            // 
            this.portbox2.Location = new System.Drawing.Point(71, 61);
            this.portbox2.Name = "portbox2";
            this.portbox2.Size = new System.Drawing.Size(100, 20);
            this.portbox2.TabIndex = 14;
            this.portbox2.Text = "43333";
            // 
            // ipbox2
            // 
            this.ipbox2.Location = new System.Drawing.Point(71, 35);
            this.ipbox2.Name = "ipbox2";
            this.ipbox2.Size = new System.Drawing.Size(100, 20);
            this.ipbox2.TabIndex = 13;
            this.ipbox2.Text = "192.168.10.63";
            // 
            // tabPage3
            // 
            this.tabPage3.BackColor = System.Drawing.SystemColors.Control;
            this.tabPage3.Controls.Add(this.label20);
            this.tabPage3.Controls.Add(this.labelbox3);
            this.tabPage3.Controls.Add(this.label21);
            this.tabPage3.Controls.Add(this.dev3sql);
            this.tabPage3.Controls.Add(this.dev3en);
            this.tabPage3.Controls.Add(this.label22);
            this.tabPage3.Controls.Add(this.commandbox3);
            this.tabPage3.Controls.Add(this.label23);
            this.tabPage3.Controls.Add(this.tickbox3);
            this.tabPage3.Controls.Add(this.label24);
            this.tabPage3.Controls.Add(this.label25);
            this.tabPage3.Controls.Add(this.portbox3);
            this.tabPage3.Controls.Add(this.ipbox3);
            this.tabPage3.Location = new System.Drawing.Point(4, 22);
            this.tabPage3.Name = "tabPage3";
            this.tabPage3.Size = new System.Drawing.Size(185, 191);
            this.tabPage3.TabIndex = 2;
            this.tabPage3.Text = "pokoj";
            // 
            // label20
            // 
            this.label20.AutoSize = true;
            this.label20.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label20.Location = new System.Drawing.Point(137, 92);
            this.label20.Name = "label20";
            this.label20.Size = new System.Drawing.Size(32, 16);
            this.label20.TabIndex = 38;
            this.label20.Text = "min";
            // 
            // labelbox3
            // 
            this.labelbox3.Location = new System.Drawing.Point(69, 164);
            this.labelbox3.Name = "labelbox3";
            this.labelbox3.Size = new System.Drawing.Size(100, 20);
            this.labelbox3.TabIndex = 37;
            this.labelbox3.Text = "temp3_1";
            // 
            // label21
            // 
            this.label21.AutoSize = true;
            this.label21.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label21.Location = new System.Drawing.Point(14, 168);
            this.label21.Name = "label21";
            this.label21.Size = new System.Drawing.Size(43, 16);
            this.label21.TabIndex = 36;
            this.label21.Text = "table";
            // 
            // dev3sql
            // 
            this.dev3sql.AutoSize = true;
            this.dev3sql.Location = new System.Drawing.Point(69, 141);
            this.dev3sql.Name = "dev3sql";
            this.dev3sql.Size = new System.Drawing.Size(76, 17);
            this.dev3sql.TabIndex = 35;
            this.dev3sql.Text = "log to SQL";
            this.dev3sql.UseVisualStyleBackColor = true;
            this.dev3sql.CheckedChanged += new System.EventHandler(this.dev3sql_CheckedChanged);
            // 
            // dev3en
            // 
            this.dev3en.AutoSize = true;
            this.dev3en.Location = new System.Drawing.Point(69, 12);
            this.dev3en.Name = "dev3en";
            this.dev3en.Size = new System.Drawing.Size(58, 17);
            this.dev3en.TabIndex = 34;
            this.dev3en.Text = "enable";
            this.dev3en.UseVisualStyleBackColor = true;
            this.dev3en.CheckedChanged += new System.EventHandler(this.dev3en_CheckedChanged);
            // 
            // label22
            // 
            this.label22.AutoSize = true;
            this.label22.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label22.Location = new System.Drawing.Point(13, 115);
            this.label22.Name = "label22";
            this.label22.Size = new System.Drawing.Size(37, 16);
            this.label22.TabIndex = 33;
            this.label22.Text = "cmd";
            // 
            // commandbox3
            // 
            this.commandbox3.Location = new System.Drawing.Point(69, 111);
            this.commandbox3.Name = "commandbox3";
            this.commandbox3.Size = new System.Drawing.Size(100, 20);
            this.commandbox3.TabIndex = 32;
            this.commandbox3.Text = "TEMP1";
            // 
            // label23
            // 
            this.label23.AutoSize = true;
            this.label23.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label23.Location = new System.Drawing.Point(4, 92);
            this.label23.Name = "label23";
            this.label23.Size = new System.Drawing.Size(53, 16);
            this.label23.TabIndex = 31;
            this.label23.Text = "period";
            // 
            // tickbox3
            // 
            this.tickbox3.Location = new System.Drawing.Point(69, 87);
            this.tickbox3.Name = "tickbox3";
            this.tickbox3.Size = new System.Drawing.Size(62, 20);
            this.tickbox3.TabIndex = 30;
            this.tickbox3.Text = "1";
            this.tickbox3.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            // 
            // label24
            // 
            this.label24.AutoSize = true;
            this.label24.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label24.Location = new System.Drawing.Point(28, 66);
            this.label24.Name = "label24";
            this.label24.Size = new System.Drawing.Size(35, 16);
            this.label24.TabIndex = 29;
            this.label24.Text = "port";
            // 
            // label25
            // 
            this.label25.AutoSize = true;
            this.label25.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label25.Location = new System.Drawing.Point(41, 36);
            this.label25.Name = "label25";
            this.label25.Size = new System.Drawing.Size(22, 16);
            this.label25.TabIndex = 28;
            this.label25.Text = "IP";
            // 
            // portbox3
            // 
            this.portbox3.Location = new System.Drawing.Point(69, 61);
            this.portbox3.Name = "portbox3";
            this.portbox3.Size = new System.Drawing.Size(100, 20);
            this.portbox3.TabIndex = 27;
            this.portbox3.Text = "43333";
            // 
            // ipbox3
            // 
            this.ipbox3.Location = new System.Drawing.Point(69, 35);
            this.ipbox3.Name = "ipbox3";
            this.ipbox3.Size = new System.Drawing.Size(100, 20);
            this.ipbox3.TabIndex = 26;
            this.ipbox3.Text = "192.168.10.65";
            // 
            // log
            // 
            this.log.AutoWordSelection = true;
            this.log.BackColor = System.Drawing.SystemColors.Info;
            this.log.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.log.HideSelection = false;
            this.log.Location = new System.Drawing.Point(12, 358);
            this.log.Name = "log";
            this.log.ReadOnly = true;
            this.log.ScrollBars = System.Windows.Forms.RichTextBoxScrollBars.Vertical;
            this.log.Size = new System.Drawing.Size(1222, 86);
            this.log.TabIndex = 3;
            this.log.Text = "";
            // 
            // tablerefresh
            // 
            this.tablerefresh.Location = new System.Drawing.Point(230, 325);
            this.tablerefresh.Name = "tablerefresh";
            this.tablerefresh.Size = new System.Drawing.Size(118, 23);
            this.tablerefresh.TabIndex = 5;
            this.tablerefresh.Text = "refresh table";
            this.tablerefresh.UseVisualStyleBackColor = true;
            this.tablerefresh.Click += new System.EventHandler(this.refreshtablebutton_Click);
            // 
            // ioTDataSet
            // 
            this.ioTDataSet.DataSetName = "IoTDataSet";
            this.ioTDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // chart1
            // 
            this.chart1.BackColor = System.Drawing.SystemColors.Control;
            this.chart1.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.chart1.BorderlineWidth = 2;
            this.chart1.BorderSkin.BorderColor = System.Drawing.Color.LightGray;
            chartArea1.AxisX.Interval = 1D;
            chartArea1.AxisX.IntervalAutoMode = System.Windows.Forms.DataVisualization.Charting.IntervalAutoMode.VariableCount;
            chartArea1.AxisX.LineDashStyle = System.Windows.Forms.DataVisualization.Charting.ChartDashStyle.DashDot;
            chartArea1.AxisX.MajorGrid.LineColor = System.Drawing.Color.Silver;
            chartArea1.AxisX.TitleFont = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            chartArea1.AxisX.TitleForeColor = System.Drawing.Color.DimGray;
            chartArea1.AxisX2.TitleFont = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            chartArea1.AxisY.Interval = 5D;
            chartArea1.AxisY.IntervalAutoMode = System.Windows.Forms.DataVisualization.Charting.IntervalAutoMode.VariableCount;
            chartArea1.AxisY.LineColor = System.Drawing.Color.Gray;
            chartArea1.AxisY.MajorGrid.Interval = 0D;
            chartArea1.AxisY.MajorGrid.IntervalOffset = 0D;
            chartArea1.BackColor = System.Drawing.Color.LightGray;
            chartArea1.BackSecondaryColor = System.Drawing.Color.White;
            chartArea1.BorderColor = System.Drawing.Color.Transparent;
            chartArea1.Name = "ChartArea1";
            this.chart1.ChartAreas.Add(chartArea1);
            legend1.Name = "Legend1";
            this.chart1.Legends.Add(legend1);
            this.chart1.Location = new System.Drawing.Point(367, 9);
            this.chart1.Name = "chart1";
            this.chart1.Palette = System.Windows.Forms.DataVisualization.Charting.ChartColorPalette.None;
            series1.BackImageTransparentColor = System.Drawing.Color.White;
            series1.BackSecondaryColor = System.Drawing.Color.White;
            series1.BorderWidth = 2;
            series1.ChartArea = "ChartArea1";
            series1.ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Spline;
            series1.Color = System.Drawing.Color.Black;
            series1.CustomProperties = "LineTension=0.25";
            series1.Font = new System.Drawing.Font("Arial Narrow", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            series1.IsValueShownAsLabel = true;
            series1.IsVisibleInLegend = false;
            series1.IsXValueIndexed = true;
            series1.LabelBorderDashStyle = System.Windows.Forms.DataVisualization.Charting.ChartDashStyle.NotSet;
            series1.Legend = "Legend1";
            series1.MarkerColor = System.Drawing.SystemColors.Highlight;
            series1.MarkerSize = 4;
            series1.MarkerStyle = System.Windows.Forms.DataVisualization.Charting.MarkerStyle.Circle;
            series1.Name = "temp1_1";
            series1.XValueMember = "re";
            series1.XValueType = System.Windows.Forms.DataVisualization.Charting.ChartValueType.Double;
            series1.YValuesPerPoint = 3;
            series2.BorderWidth = 2;
            series2.ChartArea = "ChartArea1";
            series2.ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Spline;
            series2.CustomProperties = "LineTension=0.25";
            series2.IsValueShownAsLabel = true;
            series2.IsVisibleInLegend = false;
            series2.IsXValueIndexed = true;
            series2.Legend = "Legend1";
            series2.Name = "temp2_1";
            series3.ChartArea = "ChartArea1";
            series3.ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Spline;
            series3.CustomProperties = "LineTension=0.25";
            series3.IsVisibleInLegend = false;
            series3.IsXValueIndexed = true;
            series3.Legend = "Legend1";
            series3.Name = "temp2_1max";
            series4.ChartArea = "ChartArea1";
            series4.ChartType = System.Windows.Forms.DataVisualization.Charting.SeriesChartType.Spline;
            series4.CustomProperties = "LineTension=0.25";
            series4.IsVisibleInLegend = false;
            series4.IsXValueIndexed = true;
            series4.Legend = "Legend1";
            series4.Name = "temp2_1min";
            this.chart1.Series.Add(series1);
            this.chart1.Series.Add(series2);
            this.chart1.Series.Add(series3);
            this.chart1.Series.Add(series4);
            this.chart1.Size = new System.Drawing.Size(867, 343);
            this.chart1.TabIndex = 6;
            this.chart1.Text = "chart1";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label7.Location = new System.Drawing.Point(227, 135);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(121, 16);
            this.label7.TabIndex = 8;
            this.label7.Text = "SQL data viewer";
            // 
            // textBox1
            // 
            this.textBox1.Location = new System.Drawing.Point(256, 160);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(100, 20);
            this.textBox1.TabIndex = 12;
            this.textBox1.Text = "temp1_1";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label8.Location = new System.Drawing.Point(207, 161);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(43, 16);
            this.label8.TabIndex = 13;
            this.label8.Text = "table";
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label9.Location = new System.Drawing.Point(164, 9);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(37, 16);
            this.label9.TabIndex = 14;
            this.label9.Text = "SQL";
            // 
            // sql_ip
            // 
            this.sql_ip.Location = new System.Drawing.Point(60, 35);
            this.sql_ip.Name = "sql_ip";
            this.sql_ip.Size = new System.Drawing.Size(100, 20);
            this.sql_ip.TabIndex = 15;
            this.sql_ip.Text = "192.168.10.120";
            // 
            // sql_user
            // 
            this.sql_user.Location = new System.Drawing.Point(60, 61);
            this.sql_user.Name = "sql_user";
            this.sql_user.Size = new System.Drawing.Size(100, 20);
            this.sql_user.TabIndex = 16;
            this.sql_user.Text = "iot";
            // 
            // sql_pass
            // 
            this.sql_pass.Location = new System.Drawing.Point(59, 87);
            this.sql_pass.Name = "sql_pass";
            this.sql_pass.PasswordChar = '*';
            this.sql_pass.Size = new System.Drawing.Size(100, 20);
            this.sql_pass.TabIndex = 17;
            this.sql_pass.Text = "iot";
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label10.Location = new System.Drawing.Point(32, 36);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(22, 16);
            this.label10.TabIndex = 18;
            this.label10.Text = "IP";
            // 
            // label11
            // 
            this.label11.AutoSize = true;
            this.label11.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label11.Location = new System.Drawing.Point(16, 65);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(38, 16);
            this.label11.TabIndex = 19;
            this.label11.Text = "user";
            // 
            // label12
            // 
            this.label12.AutoSize = true;
            this.label12.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label12.Location = new System.Drawing.Point(11, 91);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(42, 16);
            this.label12.TabIndex = 20;
            this.label12.Text = "pass";
            // 
            // sql_db
            // 
            this.sql_db.Location = new System.Drawing.Point(248, 36);
            this.sql_db.Name = "sql_db";
            this.sql_db.Size = new System.Drawing.Size(100, 20);
            this.sql_db.TabIndex = 21;
            this.sql_db.Text = "IOT";
            // 
            // label13
            // 
            this.label13.AutoSize = true;
            this.label13.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label13.Location = new System.Drawing.Point(168, 37);
            this.label13.Name = "label13";
            this.label13.Size = new System.Drawing.Size(74, 16);
            this.label13.TabIndex = 22;
            this.label13.Text = "database";
            // 
            // SQLbutton
            // 
            this.SQLbutton.Location = new System.Drawing.Point(171, 85);
            this.SQLbutton.Name = "SQLbutton";
            this.SQLbutton.Size = new System.Drawing.Size(87, 23);
            this.SQLbutton.TabIndex = 23;
            this.SQLbutton.Text = "connect";
            this.SQLbutton.UseVisualStyleBackColor = true;
            this.SQLbutton.Click += new System.EventHandler(this.connectSQLbutton_Click);
            // 
            // checkBox3
            // 
            this.checkBox3.AutoSize = true;
            this.checkBox3.Location = new System.Drawing.Point(210, 64);
            this.checkBox3.Name = "checkBox3";
            this.checkBox3.Size = new System.Drawing.Size(86, 17);
            this.checkBox3.TabIndex = 24;
            this.checkBox3.Text = "autoconnect";
            this.checkBox3.UseVisualStyleBackColor = true;
            // 
            // timerS
            // 
            this.timerS.Interval = 1500;
            this.timerS.Tick += new System.EventHandler(this.timerS_Tick);
            // 
            // SQLbutton2
            // 
            this.SQLbutton2.Enabled = false;
            this.SQLbutton2.Location = new System.Drawing.Point(264, 85);
            this.SQLbutton2.Name = "SQLbutton2";
            this.SQLbutton2.Size = new System.Drawing.Size(92, 23);
            this.SQLbutton2.TabIndex = 25;
            this.SQLbutton2.Text = "disconnect";
            this.SQLbutton2.UseVisualStyleBackColor = true;
            this.SQLbutton2.Click += new System.EventHandler(this.disconnectSQLbutton_Click);
            // 
            // comboBox1
            // 
            this.comboBox1.Enabled = false;
            this.comboBox1.FormattingEnabled = true;
            this.comboBox1.Items.AddRange(new object[] {
            "day",
            "hour",
            "month"});
            this.comboBox1.Location = new System.Drawing.Point(234, 185);
            this.comboBox1.Name = "comboBox1";
            this.comboBox1.Size = new System.Drawing.Size(121, 21);
            this.comboBox1.TabIndex = 26;
            this.comboBox1.Text = "hour";
            // 
            // timer2
            // 
            this.timer2.Interval = 2000;
            this.timer2.Tick += new System.EventHandler(this.timer2_Tick);
            // 
            // timer3
            // 
            this.timer3.Interval = 2000;
            this.timer3.Tick += new System.EventHandler(this.timer3_Tick);
            // 
            // tcpServer2
            // 
            this.tcpServer2.Encoding = ((System.Text.Encoding)(resources.GetObject("tcpServer2.Encoding")));
            this.tcpServer2.IdleTime = 50;
            this.tcpServer2.IsOpen = false;
            this.tcpServer2.MaxCallbackThreads = 100;
            this.tcpServer2.MaxSendAttempts = 3;
            this.tcpServer2.Port = -1;
            this.tcpServer2.VerifyConnectionInterval = 100;
            this.tcpServer2.OnDataAvailable += new tcpServer.tcpServerConnectionChanged(this.tcpServer2_OnDataAvailable);
            // 
            // timer4
            // 
            this.timer4.Tick += new System.EventHandler(this.timer4_Tick);
            // 
            // aut_Timer
            // 
            this.aut_Timer.Tick += new System.EventHandler(this.aut_Timer_Tick);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1246, 456);
            this.Controls.Add(this.comboBox1);
            this.Controls.Add(this.SQLbutton2);
            this.Controls.Add(this.checkBox3);
            this.Controls.Add(this.SQLbutton);
            this.Controls.Add(this.label13);
            this.Controls.Add(this.sql_db);
            this.Controls.Add(this.label12);
            this.Controls.Add(this.label11);
            this.Controls.Add(this.label10);
            this.Controls.Add(this.sql_pass);
            this.Controls.Add(this.sql_user);
            this.Controls.Add(this.sql_ip);
            this.Controls.Add(this.label9);
            this.Controls.Add(this.label8);
            this.Controls.Add(this.textBox1);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.chart1);
            this.Controls.Add(this.tablerefresh);
            this.Controls.Add(this.log);
            this.Controls.Add(this.devices);
            this.Name = "Form1";
            this.ShowIcon = false;
            this.Text = "IoT Brain";
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.Form1_FormClosed);
            this.devices.ResumeLayout(false);
            this.tabPage1.ResumeLayout(false);
            this.tabPage1.PerformLayout();
            this.tabPage2.ResumeLayout(false);
            this.tabPage2.PerformLayout();
            this.tabPage3.ResumeLayout(false);
            this.tabPage3.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.ioTDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.chart1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Timer timer1;
        private System.Windows.Forms.TabControl devices;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TextBox portbox1;
        private System.Windows.Forms.TextBox ipbox1;
        private System.Windows.Forms.TabPage tabPage2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.CheckBox dev1en;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox commandbox1;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox tickbox1;
        private System.Windows.Forms.RichTextBox log;
        private System.Windows.Forms.Button tablerefresh;
        private System.Windows.Forms.TextBox labelbox1;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.CheckBox dev1sql;
        private IoTDataSet ioTDataSet;
        private System.Windows.Forms.DataVisualization.Charting.Chart chart1;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.TextBox sql_ip;
        private System.Windows.Forms.TextBox sql_user;
        private System.Windows.Forms.TextBox sql_pass;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.Label label11;
        private System.Windows.Forms.Label label12;
        private System.Windows.Forms.TextBox sql_db;
        private System.Windows.Forms.Label label13;
        private System.Windows.Forms.Button SQLbutton;
        private System.Windows.Forms.CheckBox checkBox3;
        private System.Windows.Forms.Timer timerS;
        private System.Windows.Forms.Button SQLbutton2;
        private System.Windows.Forms.ComboBox comboBox1;
        private tcpServer.TcpServer tcpServer2;
        private System.Windows.Forms.Label label14;
        private System.Windows.Forms.TextBox labelbox2;
        private System.Windows.Forms.Label label15;
        private System.Windows.Forms.CheckBox dev2sql;
        private System.Windows.Forms.CheckBox dev2en;
        private System.Windows.Forms.Label label16;
        private System.Windows.Forms.TextBox commandbox2;
        private System.Windows.Forms.Label label17;
        private System.Windows.Forms.TextBox tickbox2;
        private System.Windows.Forms.Label label18;
        private System.Windows.Forms.Label label19;
        private System.Windows.Forms.TextBox portbox2;
        private System.Windows.Forms.TextBox ipbox2;
        private System.Windows.Forms.Timer timer2;
        private System.Windows.Forms.TabPage tabPage3;
        private System.Windows.Forms.Timer timer3;
        private System.Windows.Forms.Label label20;
        private System.Windows.Forms.TextBox labelbox3;
        private System.Windows.Forms.Label label21;
        private System.Windows.Forms.CheckBox dev3sql;
        private System.Windows.Forms.CheckBox dev3en;
        private System.Windows.Forms.Label label22;
        private System.Windows.Forms.TextBox commandbox3;
        private System.Windows.Forms.Label label23;
        private System.Windows.Forms.TextBox tickbox3;
        private System.Windows.Forms.Label label24;
        private System.Windows.Forms.Label label25;
        private System.Windows.Forms.TextBox portbox3;
        private System.Windows.Forms.TextBox ipbox3;
        private System.Windows.Forms.Timer timer4;
        private System.Windows.Forms.Timer aut_Timer;
    }
}

