package com.in28minutes.jpa.hibernate.entity;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    private String rating;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    private String description;

    @ManyToOne
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    protected Review() {}

    public Review(String rating,String description) {
        this.rating = rating;
        this.description = description;
    }

    public Long getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Review[%s %s]", rating, description);
//        return "Review{" +
//                "'" + rating + '\'' +
//                ", '" + description + '\'' +
//                '}';
    }
}
