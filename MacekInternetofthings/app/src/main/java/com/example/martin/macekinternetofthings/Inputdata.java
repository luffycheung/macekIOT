package com.example.martin.macekinternetofthings;

import java.util.List;

/**
 * Created by Martin on 19.04.2016.
 */
public class Inputdata
{
    private boolean check = false;
    //private String davkovac = "null";
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



    public List<Davkovac> getData()
    {
        return davkovace;
    }



}