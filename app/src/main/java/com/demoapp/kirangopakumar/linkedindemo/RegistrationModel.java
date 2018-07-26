package com.demoapp.kirangopakumar.linkedindemo;

public class RegistrationModel {

    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mPassword;


    //default constructor
    public RegistrationModel(){

    }


    public RegistrationModel(String mFirstName, String mLastName, String mEmail,String mPassword){
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mEmail = mEmail;
        this.mPassword = mPassword;

    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
