package com.example.hidday.directfarming;

import android.location.Address;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;

/**
 * Created by hidday on 25/02/2015.
 */
public class Market {
    private String name;
    private int id;
    private String phone;
    private String address;
    private String manager;

    public Market(String name, int id, String phone, String address, String manager) {
        this.name = name;
        this.id = id;

        this.address = address;
        this.manager = manager;

        PhoneNumberUtils phoneFormat= new PhoneNumberUtils();

        this.phone = phoneFormat.formatNumber(phone);
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

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
