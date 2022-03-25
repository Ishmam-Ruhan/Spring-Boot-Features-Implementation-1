package com.ishmamruhan.PracDay1.Helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateGenerator {

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");

    public String getDate(){
        return dateFormat.format(new Date());
    }
}
