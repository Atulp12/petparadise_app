package com.example.petparadise.models;

public class CustomerUpdateResult {

    public String success,status,message;

    public CustomerUpdateResult(String success, String status, String message) {
        this.success = success;
        this.status = status;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
