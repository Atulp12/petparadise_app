package com.example.petparadise.models;

public class FetchCustomerModel {

    public String success,status,message;

    public CustomerFetchResultModel result;

    public FetchCustomerModel(String success, String status, String message, CustomerFetchResultModel result) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.result = result;
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

    public CustomerFetchResultModel getResult() {
        return result;
    }

    public void setResult(CustomerFetchResultModel result) {
        this.result = result;
    }
}
