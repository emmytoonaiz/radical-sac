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
public class LoanGuarantors {

    private int memberAccountId;
    private String memberName;

    public LoanGuarantors() {
    }

    public int getMemberAccountId() {
        return memberAccountId;
    }

    public void setMemberAccountId(int memberAccountId) {
        this.memberAccountId = memberAccountId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    
    
}
