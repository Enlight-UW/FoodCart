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

    public Review(String author, int rating, String reviewText){
        this.author = author;
        this.rating = rating;
        this.reviewText = reviewText;
        this.submitDate = new Date();
    }

    // TODO: Getter Functions, Setter functions

    public String getAuthor() {
        return author;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }


}
