package com.ravi.katas.socialnetworking.util;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeUtil {

  public enum TimeLineEnum{
    SECOND("%d seconds ago"),
    MINUTE("%d minutes ago"),
    HOUR("%d hours ago"),
    DAY("%d days ago"),
    YEARS("%d years ago");
    String value;
    TimeLineEnum(String value){
      this.value = value;
    }
    public String toString(){
      return value;
    }

  }
  public String getDifference(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
    if(localDateTime1.isBefore(localDateTime2)){
      throw new RuntimeException("Date should be after");
    }
    Duration duration = Duration.between(localDateTime2,localDateTime1);
    if(duration.getSeconds()<60){
      System.out.println(String.format(TimeLineEnum.SECOND.toString(),duration.getSeconds()));
      return String.format(TimeLineEnum.SECOND.toString(),duration.getSeconds());
    }else if(duration.toMinutes()<60) {
      System.out.println(String.format(TimeLineEnum.MINUTE.toString(),duration.toMinutes()));
      return String.format(TimeLineEnum.MINUTE.toString(), duration.toMinutes());
    }else if(duration.toHours()<24) {
      System.out.println(String.format(TimeLineEnum.HOUR.toString(),duration.toHours()));
      return String.format(TimeLineEnum.HOUR.toString(), duration.toHours());
    }else if(duration.toDays()<31) {
      System.out.println(String.format(TimeLineEnum.DAY.toString(),duration.toDays()));
      return String.format(TimeLineEnum.DAY.toString(), duration.toDays());
    }else if(duration.toDays()/365>1) {
      System.out.println(String.format(TimeLineEnum.YEARS.toString(),duration.toDays()/365));
      return String.format(TimeLineEnum.YEARS.toString(), duration.toDays()/365);
    }else{
      return "";
    }
  }
}
