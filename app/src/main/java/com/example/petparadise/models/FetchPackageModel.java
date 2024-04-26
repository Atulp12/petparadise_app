package com.example.petparadise.models;

public class FetchPackageModel {
    String success,status,packages,reminder,grooming_id;

    public FetchPackageModel(String success, String status, String packages, String reminder, String grooming_id) {
        this.success = success;
        this.status = status;
        this.packages = packages;
        this.reminder = reminder;
        this.grooming_id = grooming_id;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getGrooming_id() {
        return grooming_id;
    }

    public void setGrooming_id(String grooming_id) {
        this.grooming_id = grooming_id;
    }
}
