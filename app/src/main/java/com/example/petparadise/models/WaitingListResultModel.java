package com.example.petparadise.models;

import java.io.Serializable;

public class WaitingListResultModel implements Serializable {

    public  String cust_id,branch_id,Customer_ID,Customer_Name,Address,packages,packages1,Contact_No,State,GSTIN,account_Balance,reminder,date,status_wait,finish_status,Status,grooming_done,branch_name,pets_id,pets_name,breed,dob,type,invoice_no;

    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(String branch_id) {
        this.branch_id = branch_id;
    }

    public String getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        Customer_ID = customer_ID;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getPackages1() {
        return packages1;
    }

    public void setPackages1(String packages1) {
        this.packages1 = packages1;
    }

    public String getContact_No() {
        return Contact_No;
    }

    public void setContact_No(String contact_No) {
        Contact_No = contact_No;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getGSTIN() {
        return GSTIN;
    }

    public void setGSTIN(String GSTIN) {
        this.GSTIN = GSTIN;
    }

    public String getAccount_Balance() {
        return account_Balance;
    }

    public void setAccount_Balance(String account_Balance) {
        this.account_Balance = account_Balance;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus_wait() {
        return status_wait;
    }

    public void setStatus_wait(String status_wait) {
        this.status_wait = status_wait;
    }

    public String getFinish_status() {
        return finish_status;
    }

    public void setFinish_status(String finish_status) {
        this.finish_status = finish_status;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getGrooming_done() {
        return grooming_done;
    }

    public void setGrooming_done(String grooming_done) {
        this.grooming_done = grooming_done;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getPets_id() {
        return pets_id;
    }

    public void setPets_id(String pets_id) {
        this.pets_id = pets_id;
    }

    public String getPets_name() {
        return pets_name;
    }

    public void setPets_name(String pets_name) {
        this.pets_name = pets_name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
