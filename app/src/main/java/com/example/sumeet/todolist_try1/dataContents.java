package com.example.sumeet.todolist_try1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sumeet on 19-02-2017.
 */

public class dataContents implements Comparable<dataContents> {
    Integer Key_id, Key_Status;
    String Key_Tittle, Key_Description, Key_Date;


    public dataContents() {
    }

    public dataContents(Integer key_id, Integer key_Status, String key_Tittle, String key_Description, String key_Date) {
        this.Key_id = key_id;
        this.Key_Status = key_Status;
        this.Key_Tittle = key_Tittle;
        this.Key_Description = key_Description;
        this.Key_Date = key_Date;
    }

    public Integer getKey_id() {
        return Key_id;
    }

    public Integer getKey_Status() {
        return Key_Status;
    }

    public String getKey_Tittle() {
        return Key_Tittle;
    }

    public String getKey_Description() {
        return Key_Description;
    }

    public String getKey_Date() {
        return Key_Date;
    }


    public void setKey_id(Integer key_id) {
        Key_id = key_id;
    }

    public void setKey_Status(Integer key_Status) {
        Key_Status = key_Status;
    }

    public void setKey_Tittle(String key_Tittle) {
        Key_Tittle = key_Tittle;
    }

    public void setKey_Description(String key_Description) {
        Key_Description = key_Description;
    }

    public void setKey_Date(String key_Date) {
        Key_Date = key_Date;
    }

    @Override
    public int compareTo(dataContents another) {
        Date lhs = null, rhs = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
        try {
            lhs = simpleDateFormat.parse(another.Key_Date);
            rhs = simpleDateFormat.parse(this.Key_Date);

            //  Log.i("lhs", lhs.toString());
            //Log.i("rhs", rhs.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lhs.compareTo(rhs);
    }
}
