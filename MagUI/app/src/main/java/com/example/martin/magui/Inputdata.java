package com.example.martin.magui;

import java.util.List;

/**
 * Created by Martin on 19.04.2016.
 */
public class Inputdata
{
    private boolean check = false;
    //private String davkovac = "null";
    private List<Davkovac> davkovace;

    public Inputdata(){
    }

    public Inputdata(boolean change, List<Davkovac> davkovace){
        //this.check = check;
        //this.davkovac = davkovac;

    }
    public class Davkovac
    {
        int id;
        String Alarmy;
        String Hodnoty;

    }

    public boolean getChanged()
    {
        return check;
    }

        /*public String getDevice()
        {
            return davkovac;
        }*/


    public List<Davkovac> getData()
    {
        return davkovace;
    }


       /* @Override
        public String toString()
        {
            return "Employee [id=" + id + ", firstName=" + firstName + ", " +
                    "lastName=" + lastName + ", roles=" + roles + "]";
        }*/
}