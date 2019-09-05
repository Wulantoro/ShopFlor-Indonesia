package com.example.shopfloor.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ServerModel extends RealmObject {

    @PrimaryKey
    private Integer id;
    private String address;

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }
}
