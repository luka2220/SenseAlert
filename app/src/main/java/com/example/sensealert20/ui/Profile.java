package com.example.sensealert20.ui;

import android.provider.ContactsContract;

public class Profile {
    private String FirstName;
    private String LastName;
    private String PhoneNumber;
    private String UseOfApp;

    public Profile() {}

    public String getFirstName() {return FirstName;}

    public void setFirstName(String name) {FirstName = name;}

    public String getLastName() {return LastName;}

    public void setLastName(String name) {LastName = name;}

    public String getPhoneNumber() {return PhoneNumber;}

    public void setPhoneNumber(String num) {PhoneNumber = num;}

    public String getUseofApp() {return UseOfApp;}

    public void setUseofApp(String use) {UseOfApp = use;}
}
