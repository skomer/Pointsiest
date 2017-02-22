package com.elderj.pointsiest;


public class Sportsperson {

    private String firstName;
    private String lastName;
    private String fullName;
    private int id;
    private double points;
    private String profilePicUrl;

    public Sportsperson() {}

    public Sportsperson(
            String firstName,
            String lastName,
            String fullName,
            int id,
            double points,
            String profilePicUrl
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.id = id;
        this.points = points;
        this.profilePicUrl = profilePicUrl;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public int getId() {
        return this.id;
    }

    public double getPoints() {
        return this.points;
    }

    public String getProfilePicUrl() {
        return this.profilePicUrl;
    }



}
