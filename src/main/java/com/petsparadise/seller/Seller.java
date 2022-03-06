package com.petsparadise.seller;

public class Seller {
    private String sId, sFirstName, sLastName, sEmail, sPassword, sContactNumber, sAddress, sFullName, sBank, sBankBranch, sAccountNumber;
    private String sNicFrontImageUrl, sNicBackImageUrl, sBankStatementImageUrl;
    private int approvalStatus;

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsFirstName() {
        return sFirstName;
    }

    public void setsFirstName(String sFirstName) {
        this.sFirstName = sFirstName;
    }

    public String getsLastName() {
        return sLastName;
    }

    public void setsLastName(String sLastName) {
        this.sLastName = sLastName;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public String getsContactNumber() {
        return sContactNumber;
    }

    public void setsContactNumber(String sContactNumber) {
        this.sContactNumber = sContactNumber;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getsFullName() {
        return sFullName;
    }

    public void setsFullName(String sFullName) {
        this.sFullName = sFullName;
    }

    public String getsBank() {
        return sBank;
    }

    public void setsBank(String sBank) {
        this.sBank = sBank;
    }

    public String getsBankBranch() {
        return sBankBranch;
    }

    public void setsBankBranch(String sBankBranch) {
        this.sBankBranch = sBankBranch;
    }

    public String getsAccountNumber() {
        return sAccountNumber;
    }

    public void setsAccountNumber(String sAccountNumber) {
        this.sAccountNumber = sAccountNumber;
    }

    public String getsNicFrontImageUrl() {
        return sNicFrontImageUrl;
    }

    public void setsNicFrontImageUrl(String sNicFrontImageUrl) {
        this.sNicFrontImageUrl = sNicFrontImageUrl;
    }

    public String getsNicBackImageUrl() {
        return sNicBackImageUrl;
    }

    public void setsNicBackImageUrl(String sNicBackImageUrl) {
        this.sNicBackImageUrl = sNicBackImageUrl;
    }

    public String getsBankStatementImageUrl() {
        return sBankStatementImageUrl;
    }

    public void setsBankStatementImageUrl(String sBankStatementImageUrl) {
        this.sBankStatementImageUrl = sBankStatementImageUrl;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
    
}
