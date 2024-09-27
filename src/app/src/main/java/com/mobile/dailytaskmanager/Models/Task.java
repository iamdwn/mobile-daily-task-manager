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

    public String getCompletionTime() {
        return String.format("%02d:%02d", hour, minute);
    }
}
