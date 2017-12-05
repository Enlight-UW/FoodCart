package club.enlight.foodtruck.data.model;

/**
 * Schedule containing food truck hours of operation
 *
 *
 * Created by jzmai on 10/17/17.
 */
public class Schedule implements Serializable {

    private Time[] openingTime;
    private Time[] closingTime;

    //TODO: create schedule from string. Should reverse toString
    public Schedule(String schedule){
        String[] schedules = schedule.split(";");
        String[] openings = schedules[0].split(",");
        String[] closings = schedules[1].split(",");

        openingTime = new Time[7];
        closingTime = new Time[7];

        for(int i = 0; i < openingTime.length; i++){
            openingTime[i] = new Time(openings[i]);
        }
        for(int i = 0; i < closingTime.length; i++){
            closingTime[i] = new Time(closings[i]);
        }

    }

    public Schedule(){
        openingTime = new Time[7];
        closingTime = new Time[7];
        for(int i = 0; i < 7; i++) {
            openingTime[i] = new Time("0000");
            closingTime[i] = new Time("0000");
        }
    }

    public void setOpeningTimeByDay(int day, int hour, int minute) {
        openingTime[day] = new Time(hour, minute);
    }

    public void setClosingTimeTimeByDay(int day, int hour, int minute) {
        closingTime[day] = new Time(hour, minute);
    }

    public Time getOpeningTimeByDay(int day) {
        return openingTime[day];
    }

    public Time getClosingTimeByDay(int day) {
        return closingTime[day];
    }

    @Override
    public String toJSON() {

        return null;
    }


    //TODO: implement toString function for storing in DB
    @Override
    public String toString(){
        String str = "";
        for(int i = 0; i < openingTime.length; i++)
            str += openingTime[i].toString() + ",";

        str +=";";
        for(int i = 0; i < closingTime.length; i++)
            str+= closingTime[i].toString() + ",";


        return str;
    }
}

