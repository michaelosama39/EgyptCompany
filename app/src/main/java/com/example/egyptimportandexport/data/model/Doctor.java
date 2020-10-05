package com.example.egyptimportandexport.data.model;

public class Doctor {
    String doctorId;
    String doctorName;

    public Doctor(String doctorId, String doctorName) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
    }

    public String getdoctorId() {
        return doctorId;
    }

    public String getdoctorName() {
        return doctorName;
    }
}
