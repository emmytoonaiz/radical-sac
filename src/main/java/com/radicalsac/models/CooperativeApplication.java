/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radicalsac.models;

/**
 *
 * @author emmanuel.idoko
 */
public class CooperativeApplication {
    private String id;
    private String firstName;
    private String middlieName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String nameOfCooperative;
    private String applicationDate;
    private boolean attendedStatus;
    private String attendedDate;

    public CooperativeApplication() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddlieName() {
        return middlieName;
    }

    public void setMiddlieName(String middlieName) {
        this.middlieName = middlieName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNameOfCooperative() {
        return nameOfCooperative;
    }

    public void setNameOfCooperative(String nameOfCooperative) {
        this.nameOfCooperative = nameOfCooperative;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public boolean isAttendedStatus() {
        return attendedStatus;
    }

    public void setAttendedStatus(boolean attendedStatus) {
        this.attendedStatus = attendedStatus;
    }

    public String getAttendedDate() {
        return attendedDate;
    }

    public void setAttendedDate(String attendedDate) {
        this.attendedDate = attendedDate;
    }
    
    
    
}
