package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patients")
public class PatientModel {
    @javax.persistence.Id
    @GeneratedValue

    int Id;
    String pName;
    String pAddress;
    String pPhone;

    public PatientModel() {
    }

    public PatientModel(int id, String pName, String pAddress, String pPhone) {
        Id = id;
        this.pName = pName;
        this.pAddress = pAddress;
        this.pPhone = pPhone;
    }
    public int getId() {
        return Id;
    }

    public String getpName() {
        return pName;
    }

    public String getpAddress() {
        return pAddress;
    }

    public String getpPhone() {
        return pPhone;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public void setpPhone(String pPhone) {
        this.pPhone = pPhone;
    }

}
