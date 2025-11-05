package com.example.myprojest;

import java.io.Serializable;

public class Expense implements Serializable {
    private String amount, category, remark, date, currency;

    public Expense(String amount, String currency, String category, String remark, String date) {
        this.amount = amount;
        this.currency = currency;
        this.category = category;
        this.remark = remark;
        this.date = date;
    }

    public String getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public String getCategory() { return category; }
    public String getRemark() { return remark; }
    public String getDate() { return date; }
}