package com.example.petparadise.models;

import java.util.List;

public class CustomerListModel {

    public String success,status;
    public List<ResultModel> result;

    public CustomerListModel(String success, String status, List<ResultModel> result) {
        this.success = success;
        this.status = status;
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

    public List<ResultModel> getResult() {
        return result;
    }

    public void setResult(List<ResultModel> result) {
        this.result = result;
    }


}
