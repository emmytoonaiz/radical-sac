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
public class CooperativeDetails {

    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String state;
    private String country;
    private double interestRate;
    private boolean loanPayOffAllowed;
    private String contributionFrequency;
    private boolean overrideMemContribFreq;
    private String contibutionCurrency;
    private double contributionAmount;
    private boolean overrideMemberContributionAmount;
    private boolean active;
    private String fullAddress;

    public CooperativeDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public boolean isLoanPayOffAllowed() {
        return loanPayOffAllowed;
    }

    public void setLoanPayOffAllowed(boolean loanPayOffAllowed) {
        this.loanPayOffAllowed = loanPayOffAllowed;
    }

    public String getContributionFrequency() {
        return contributionFrequency;
    }

    public void setContributionFrequency(String contributionFrequency) {
        this.contributionFrequency = contributionFrequency;
    }

    public boolean isOverrideMemContribFreq() {
        return overrideMemContribFreq;
    }

    public void setOverrideMemContribFreq(boolean overrideMemContribFreq) {
        this.overrideMemContribFreq = overrideMemContribFreq;
    }

    public String getContibutionCurrency() {
        return contibutionCurrency;
    }

    public void setContibutionCurrency(String contibutionCurrency) {
        this.contibutionCurrency = contibutionCurrency;
    }

    public double getContributionAmount() {
        return contributionAmount;
    }

    public void setContributionAmount(double contributionAmount) {
        this.contributionAmount = contributionAmount;
    }

    public boolean isOverrideMemberContributionAmount() {
        return overrideMemberContributionAmount;
    }

    public void setOverrideMemberContributionAmount(boolean overrideMemberContributionAmount) {
        this.overrideMemberContributionAmount = overrideMemberContributionAmount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

}
