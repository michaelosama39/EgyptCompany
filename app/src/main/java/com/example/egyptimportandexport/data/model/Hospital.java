package com.example.egyptimportandexport.data.model;

public class Hospital {
    String hospitalId;
    String hospitalName;


    public Hospital(String hospitalId, String hospitalName) {
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }
}
