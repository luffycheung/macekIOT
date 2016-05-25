package com.example.martin.macekinternetofthings;

import java.util.List;

/**
 * Created by mk on 25.5.16.
 */
public class Outputdata {

    public static boolean master = false;
    public static boolean obdobi = false;
    public static int period;
    public static int targ;
    public static float hys;

    //private String davkovac = "null";
    //private List<Automat> automat;

/*
    public class Automat
    {
        int id;
        String teplota;
        List<Float> teploty;


    } */

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
