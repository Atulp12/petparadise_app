package com.example.petparadise.models;

import java.util.List;

public class WaitingListModel {
    public String success,status;

    public List<WaitingListResultModel> result;

    public WaitingListModel(String success, String status, List<WaitingListResultModel> result) {
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

    public List<WaitingListResultModel> getResult() {
        return result;
    }

    public void setResult(List<WaitingListResultModel> result) {
        this.result = result;
    }
}
