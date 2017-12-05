package club.enlight.foodtruck.data.model;

import java.util.Date;

/**
 * Contains raw image data and relevant meta-data;
 *
 * Created by jzmai on 12/5/17.
 */

public class Doodle {
    public final double id;
    public final double truckId;
    private byte[] rawImage;
    private long timestamp;


    public Doodle(double truckId){
        this.id = -1;
        this.truckId = truckId;
        this.touch();
    }
    public Doodle(double id, double truckId){
        this.id = id;
        this.truckId = truckId;
        this.touch();
    }

    public byte[] getRawImage() {
        return rawImage;
    }

    public void setRawImage(byte[] rawImage) {
        this.rawImage = rawImage;
        this.touch();

    }

    public long getTimestamp() {
        return timestamp;
    }

    public void touch() {
        Date date = new Date();
        this.timestamp = date.getTime()/1000;
    }
}
