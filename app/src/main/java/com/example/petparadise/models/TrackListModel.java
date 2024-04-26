package com.example.petparadise.models;

import java.util.List;

public class TrackListModel {

    public String success,status;

    public TrackListResultModel result;

    public TrackListModel(String success, String status, TrackListResultModel result) {
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

    public TrackListResultModel getResult() {
        return result;
    }

    public void setResult(TrackListResultModel result) {
        this.result = result;
    }

    public class TrackListResultModel{
        public String branch_id,branch_name;
        List<TrackTableData> table_data;

        public TrackListResultModel(String branch_id, String branch_name, List<TrackTableData> table_data) {
            this.branch_id = branch_id;
            this.branch_name = branch_name;
            this.table_data = table_data;
        }

        public String getBranch_id() {
            return branch_id;
        }

        public void setBranch_id(String branch_id) {
            this.branch_id = branch_id;
        }

        public String getBranch_name() {
            return branch_name;
        }

        public void setBranch_name(String branch_name) {
            this.branch_name = branch_name;
        }

        public List<TrackTableData> getTable_data() {
            return table_data;
        }

        public void setTable_data(List<TrackTableData> table_data) {
            this.table_data = table_data;
        }
    }
}
