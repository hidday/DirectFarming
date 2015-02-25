package com.example.hidday.directfarming;

import android.location.Address;
import android.telephony.PhoneNumberFormattingTextWatcher;

/**
 * Created by hidday on 25/02/2015.
 */
public class Market {
    private String name;
    private int id;
    private PhoneNumberFormattingTextWatcher phone;
    private Address address;
    private String manager;

    public Market(String name, int id, PhoneNumberFormattingTextWatcher phone, Address address, String manager) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.manager = manager;
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

    public PhoneNumberFormattingTextWatcher getPhone() {
        return phone;
    }

    public void setPhone(PhoneNumberFormattingTextWatcher phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
