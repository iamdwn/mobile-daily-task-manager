package com.mobile.dailytaskmanager.Models;

public class Task {
    private String name;
    private int hour;
    private int minute;

    public Task(String name, int hour, int minute) {
        this.name = name;
        this.hour = hour;
        this.minute = minute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getFormattedTime() {
        return String.format("%02d:%02d", hour, minute);
    }
}
