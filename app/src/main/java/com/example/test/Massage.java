package com.example.test;

public class Massage {
    private String description;
    private int resid;
    private boolean ishappy;
    private int amount;

    public Massage(String description, int resid, boolean ishappy, int amount) {
        this.description = description;
        this.resid = resid;
        this.ishappy = ishappy;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    public boolean isIshappy() {
        return ishappy;
    }

    public void setIshappy(boolean ishappy) {
        this.ishappy = ishappy;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
