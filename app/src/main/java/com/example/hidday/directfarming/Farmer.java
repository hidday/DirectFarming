package com.example.hidday.directfarming;

import android.location.Address;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberFormattingTextWatcher;

/**
 * Created by hidday on 25/02/2015.
 */
public class Farmer {
    private String name;
    private int id;
    private PhoneNumberFormattingTextWatcher phone;
    private Address address;

    public Farmer(String name, int id, PhoneNumberFormattingTextWatcher phone, Address address) {
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


}
