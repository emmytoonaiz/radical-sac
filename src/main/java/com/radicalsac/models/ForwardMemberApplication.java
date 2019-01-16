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
public class ForwardMemberApplication {

    private int cooperativeId;
    private String nameOfCooperative;
    private String cooperativeAdminUsername;
    private String additionalInfo;
    private boolean treated;
    private String forwardedBy;
    private String dateOfForwarding;

    public ForwardMemberApplication() {
    }

    public int getCooperativeId() {
        return cooperativeId;
    }

    public void setCooperativeId(int cooperativeId) {
        this.cooperativeId = cooperativeId;
    }

    public String getNameOfCooperative() {
        return nameOfCooperative;
    }

    public void setNameOfCooperative(String nameOfCooperative) {
        this.nameOfCooperative = nameOfCooperative;
    }

    public String getCooperativeAdminUsername() {
        return cooperativeAdminUsername;
    }

    public void setCooperativeAdminUsername(String cooperativeAdminUsername) {
        this.cooperativeAdminUsername = cooperativeAdminUsername;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public boolean isTreated() {
        return treated;
    }

    public void setTreated(boolean treated) {
        this.treated = treated;
    }

    public String getForwardedBy() {
        return forwardedBy;
    }

    public void setForwardedBy(String forwardedBy) {
        this.forwardedBy = forwardedBy;
    }

    public String getDateOfForwarding() {
        return dateOfForwarding;
    }

    public void setDateOfForwarding(String dateOfForwarding) {
        this.dateOfForwarding = dateOfForwarding;
    }
    
    
}
