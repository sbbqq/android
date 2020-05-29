package com.example.appdatastructurelearn.Util;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by alone-nine-sword on 20-5-29.
 */

public class TimeTool {

    @RequiresApi(api = Build.VERSION_CODES.O)
   public static LocalDateTime getDate(Long timeStamp){
        Instant instant = Instant.ofEpochMilli(timeStamp);
        ZoneId zoneId = ZoneId.systemDefault();

        return LocalDateTime.ofInstant(instant, zoneId);

    }
}
