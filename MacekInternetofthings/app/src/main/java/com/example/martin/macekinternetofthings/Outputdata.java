package com.example.martin.macekinternetofthings;

import java.util.List;

/**
 * Created by mk on 25.5.16.
 */
public class Outputdata {

    private boolean master = false;
    private boolean obdobi = false;
    private int period;
    private int targ;
    private int hys;

    //private String davkovac = "null";
    //private List<Automat> automat;


    public class Automat
    {
        int id;
        String teplota;
        List<Float> teploty;


    }

    public boolean getChanged()
    {
        return master;
    }



   /* public List<Automat> getData()
    {
        return automat;
    }
    */
}
