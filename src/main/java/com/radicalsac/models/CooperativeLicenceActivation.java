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
public class CooperativeLicenceActivation {

    private int id;
    private int cooperativeId;
    private String cooperativeName;
    private String licenceInformation;
    private String startDate;
    private String endDate;
    private int periodTypeId;
    private String period;
    private int duration;
    private boolean inPerpetuity;

    public CooperativeLicenceActivation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCooperativeId() {
        return cooperativeId;
    }

    public void setCooperativeId(int cooperativeId) {
        this.cooperativeId = cooperativeId;
    }

    public String getCooperativeName() {
        return cooperativeName;
    }

    public void setCooperativeName(String cooperativeName) {
        this.cooperativeName = cooperativeName;
    }

    public String getLicenceInformation() {
        return licenceInformation;
    }

    public void setLicenceInformation(String licenceInformation) {
        this.licenceInformation = licenceInformation;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPeriodTypeId() {
        return periodTypeId;
    }

    public void setPeriodTypeId(int periodTypeId) {
        this.periodTypeId = periodTypeId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isInPerpetuity() {
        return inPerpetuity;
    }

    public void setInPerpetuity(boolean inPerpetuity) {
        this.inPerpetuity = inPerpetuity;
    }
}
