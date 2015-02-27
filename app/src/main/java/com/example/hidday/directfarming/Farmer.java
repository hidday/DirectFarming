package com.example.hidday.directfarming;

import android.location.Address;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;

/**
 * Created by hidday on 25/02/2015.
 */
public class Farmer {
    private String name;
    private int id;
    private String phone;
    private String address;

    public Farmer(String name, int id, String phone, String address) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.address = address;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
