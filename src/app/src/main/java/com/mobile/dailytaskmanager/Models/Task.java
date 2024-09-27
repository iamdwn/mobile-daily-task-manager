package com.mobile.dailytaskmanager.Models; // Change to your package name

public class Task {
    private String name;      // Name of the task
    private int hour;        // Hour for the task time
    private int minute;      // Minute for the task time

    // Constructor
    public Task(String name, int hour, int minute) {
        this.name = name;
        this.hour = hour;
        this.minute = minute;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
