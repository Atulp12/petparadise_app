package com.example.petparadise.models;

public class PackageModel {
    public String success,status,message,packages,invoice_no;

    public PackageModel(String success, String status, String message, String packages,String invoice_no) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.packages = packages;
        this.invoice_no = invoice_no;
    }

    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
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

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }
}
