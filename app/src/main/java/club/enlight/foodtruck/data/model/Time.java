package club.enlight.foodtruck.data.model;

/**
 * Created by Dawanit on 11/5/2017.
 */

public class Time {
    private int hour;
    private int minute;

    public Time(String military){
        this.hour = Integer.parseInt(military.substring(0,2));
        this.minute = Integer.parseInt(military.substring(2,4));
    }

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour(){
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public String toString(){
        return ""+hour+""+minute+"";
    }
}
