package com.example.petparadise.models;

import java.io.Serializable;

public class TrackTableData implements Serializable {

    public String Table_assign_id,branch_id,grooming_id,pets_name,cust_id,table_id,start_time,booking_status,washing_status,drying_status,hairStyling_status,status,final_status,table_no,branch_name;

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getTable_assign_id() {
        return Table_assign_id;
    }

    public void setTable_assign_id(String table_assign_id) {
        Table_assign_id = table_assign_id;
    }

    public String getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(String branch_id) {
        this.branch_id = branch_id;
    }

    public String getGrooming_id() {
        return grooming_id;
    }

    public void setGrooming_id(String grooming_id) {
        this.grooming_id = grooming_id;
    }

    public String getPets_name() {
        return pets_name;
    }

    public void setPets_name(String pets_name) {
        this.pets_name = pets_name;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getTable_id() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }

    public String getWashing_status() {
        return washing_status;
    }

    public void setWashing_status(String washing_status) {
        this.washing_status = washing_status;
    }

    public String getDrying_status() {
        return drying_status;
    }

    public void setDrying_status(String drying_status) {
        this.drying_status = drying_status;
    }

    public String getHairStyling_status() {
        return hairStyling_status;
    }

    public void setHairStyling_status(String hairStyling_status) {
        this.hairStyling_status = hairStyling_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFinal_status() {
        return final_status;
    }

    public void setFinal_status(String final_status) {
        this.final_status = final_status;
    }

    public String getTable_no() {
        return table_no;
    }

    public void setTable_no(String table_no) {
        this.table_no = table_no;
    }
}
