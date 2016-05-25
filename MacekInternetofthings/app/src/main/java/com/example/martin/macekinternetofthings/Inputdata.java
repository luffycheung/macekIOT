package com.example.martin.macekinternetofthings;

import java.util.List;

/**
 * Created by Martin on 19.04.2016.
 */
public class Inputdata
{
    private boolean check = false;
    private boolean master = false;
    private boolean obdobi = false;
    private int period;
    private int klid1;
    private int klid2;
    private float hys;

    private List<Davkovac> davkovace;


    public class Davkovac
    {
        int id;
        String teplota;
        List<Float> teploty;


    }

    public boolean getChanged()
    {
        return check;
    }
    public boolean getMaster()
    {
        return master;
    }
    public boolean getObdobi()
    {
        return obdobi;
    }
    public int getPeriod()
    {
        return period;
    }
    public int getKlid1()
    {
        return klid1;
    }
    public int getKlid2()
    {
        return klid2;
    }
    public float getHys()
    {
        return hys;
    }



    public List<Davkovac> getData()
    {
        return davkovace;
    }



}