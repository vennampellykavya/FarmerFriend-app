package com.example.posting.Model;



public class Work {

    private String workerId,workType;
    private String wage;
    private String address;


    public Work() {
        // Required empty public constructor
    }

    public Work(String workerId,String workType, String wage, String address) {
        this.workType = workType;
        this.wage = wage;
        this.address = address;
        this.workerId=workerId;
    }
    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId; }
    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

