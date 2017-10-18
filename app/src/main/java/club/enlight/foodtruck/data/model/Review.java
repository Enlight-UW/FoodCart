package club.enlight.foodtruck.data.model;

import java.util.Date;

/**
 * Created by jzmai on 10/17/17.
 */

public class Review {
    private final String author;
    private int rating;
    private String reviewText;
    private Date submitDate;

    public Review(String author, int rating, String reviewText, Date submitDate){
        this.author = author;
        this.rating = rating;
        this.reviewText = reviewText;
        this.submitDate = submitDate;
    }

    // TODO: Getter Functions, Setter functions
}
