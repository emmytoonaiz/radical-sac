/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radicalsac.models;

import java.util.List;

/**
 *
 * @author emmanuel.idoko
 */
public class MemberLoanHistory {

    private int id;
    private int memberAccountId;
    private String memberName;
    private int loanTypeId;
    private String loanType;
    private long amount;
    private String appliedDate;
    private String approvedDate;
    private String approvedBy;
    private String expectedDisbursementDate;
    private String datePaid;
    private List<LoanGuarantors> guarantors;

    public MemberLoanHistory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the memberAccountId
     */
    public int getMemberAccountId() {
        return memberAccountId;
    }

    /**
     * @param memberAccountId the memberAccountId to set
     */
    public void setMemberAccountId(int memberAccountId) {
        this.memberAccountId = memberAccountId;
    }

    /**
     * @return the memberName
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * @param memberName the memberName to set
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     * @return the loanTypeId
     */
    public int getLoanTypeId() {
        return loanTypeId;
    }

    /**
     * @param loanTypeId the loanTypeId to set
     */
    public void setLoanTypeId(int loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    /**
     * @return the loanType
     */
    public String getLoanType() {
        return loanType;
    }

    /**
     * @param loanType the loanType to set
     */
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    /**
     * @return the amount
     */
    public long getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(long amount) {
        this.amount = amount;
    }

    /**
     * @return the appliedDate
     */
    public String getAppliedDate() {
        return appliedDate;
    }

    /**
     * @param appliedDate the appliedDate to set
     */
    public void setAppliedDate(String appliedDate) {
        this.appliedDate = appliedDate;
    }

    /**
     * @return the approvedDate
     */
    public String getApprovedDate() {
        return approvedDate;
    }

    /**
     * @param approvedDate the approvedDate to set
     */
    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }

    /**
     * @return the approvedBy
     */
    public String getApprovedBy() {
        return approvedBy;
    }

    /**
     * @param approvedBy the approvedBy to set
     */
    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    /**
     * @return the expectedDisbursementDate
     */
    public String getExpectedDisbursementDate() {
        return expectedDisbursementDate;
    }

    /**
     * @param expectedDisbursementDate the expectedDisbursementDate to set
     */
    public void setExpectedDisbursementDate(String expectedDisbursementDate) {
        this.expectedDisbursementDate = expectedDisbursementDate;
    }

    /**
     * @return the datePaid
     */
    public String getDatePaid() {
        return datePaid;
    }

    /**
     * @param datePaid the datePaid to set
     */
    public void setDatePaid(String datePaid) {
        this.datePaid = datePaid;
    }

    /**
     * @return the guarantors
     */
    public List<LoanGuarantors> getGuarantors() {
        return guarantors;
    }

    /**
     * @param guarantors the guarantors to set
     */
    public void setGuarantors(List<LoanGuarantors> guarantors) {
        this.guarantors = guarantors;
    }

}
