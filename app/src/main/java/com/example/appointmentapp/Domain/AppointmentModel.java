package com.example.appointmentapp.Domain;

import java.io.Serializable;

public class AppointmentModel implements Serializable {
    private String doctorId;
    private String doctorName;
    private String userId; // Assuming user authentication will be added later
    private String date;
    private String time;
    private String appointmentId;

    public AppointmentModel() {
    }

    public AppointmentModel(String doctorId, String doctorName, String userId, String date, String time) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.userId = userId;
        this.date = date;
        this.time = time;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }
}