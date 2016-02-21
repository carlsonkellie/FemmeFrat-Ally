package com.example.kelliecarlson.femmefratally2;

/**
 * Created by Jaimie on 2/20/2016.
 */
public class MyReview {
    public String review;
    public int stars;

    public MyReview(String review, int stars){
        this.review = review;
        this.stars = stars;
    }

    public String getReview() {
        return review;
    }

    public int getStars() {
        return stars;
    }

}
