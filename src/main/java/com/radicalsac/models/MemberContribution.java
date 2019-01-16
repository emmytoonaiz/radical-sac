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
public class MemberContribution {
    private int id;
    private int memberAccountId;
    private int savingsTypeId;
    private long amount;
    private long balance;
    private String memberName;
    private String valueDate;//repayment date
    private String narration;
    private String savingsType;

    public MemberContribution() {
    }

    public int getMemberAccountId() {
        return memberAccountId;
    }

    public void setMemberAccountId(int memberAccountId) {
        this.memberAccountId = memberAccountId;
    }

    public int getSavingsTypeId() {
        return savingsTypeId;
    }

    public void setSavingsTypeId(int savingsTypeId) {
        this.savingsTypeId = savingsTypeId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getSavingsType() {
        return savingsType;
    }

    public void setSavingsType(String savingsType) {
        this.savingsType = savingsType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
