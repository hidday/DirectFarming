package com.example.hidday.directfarming;

import android.telephony.PhoneNumberUtils;

import com.parse.ParseObject;

/**
 * Created by hidday on 25/02/2015.
 */
public class Market  {


    private String name;
    private String phone;
    private String address;
    private String manager;

    public Market(String name, String phone, String address, String manager) {
        this.name = name;
        this.address = address;
        this.manager = manager;

        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
