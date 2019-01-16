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
    private String licenceStartDate;
    private String licenceEndDate;
     private int periodTyped;
    private String licencePeriod;
    private String licenceDuration;
    private String licenceInPerpetuity;
    private boolean save;
    private boolean activate;
    private boolean deactivate;

    public CooperativeLicenceActivation() {
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

    public String getLicenceStartDate() {
        return licenceStartDate;
    }

    public void setLicenceStartDate(String licenceStartDate) {
        this.licenceStartDate = licenceStartDate;
    }

    public String getLicenceEndDate() {
        return licenceEndDate;
    }

    public void setLicenceEndDate(String licenceEndDate) {
        this.licenceEndDate = licenceEndDate;
    }

    public String getLicencePeriod() {
        return licencePeriod;
    }

    public void setLicencePeriod(String licencePeriod) {
        this.licencePeriod = licencePeriod;
    }

    public String getLicenceDuration() {
        return licenceDuration;
    }

    public void setLicenceDuration(String licenceDuration) {
        this.licenceDuration = licenceDuration;
    }

    public String getLicenceInPerpetuity() {
        return licenceInPerpetuity;
    }

    public void setLicenceInPerpetuity(String licenceInPerpetuity) {
        this.licenceInPerpetuity = licenceInPerpetuity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public boolean isDeactivate() {
        return deactivate;
    }

    public void setDeactivate(boolean deactivate) {
        this.deactivate = deactivate;
    }

    public boolean isSave() {
        return save;
    }

    public void setSave(boolean save) {
        this.save = save;
    }
    

}
