package com.example.martin.macekinternetofthings;





/**
 * Created by mk on 25.5.16.
 */



public class Outputdata {


    private boolean master = false;
    private boolean obdobi = false;
    private int period = 1;
    //private int targ = 1;
    private int klid1 = 1;
    private int klid2 = 1;
    private float hys = 1;


    public void setMaster(boolean cis)
    {
        this.master=cis;
    }
    public void setObdobi(boolean cis)
    {
        this.obdobi=cis;
    }
    public void setPeriod(int cis)
    {
        this.period=cis;
    }
  /*  public void setTarg(int cis)
    {
        this.targ=cis;
    }*/
    public void setKlid1(int cis)
    {
        this.klid1=cis;
    }
    public void setKlid2(int cis)
    {
        this.klid2=cis;
    }

    public void setHys(float cis)
    {
        this.hys=cis;
    }



}
