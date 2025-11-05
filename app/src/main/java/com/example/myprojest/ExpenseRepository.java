package com.example.myprojest;

import java.util.ArrayList;

// This class will hold your expense list
// so all activities can access the same data
public class ExpenseRepository {

    private static final ArrayList<Expense> expenseList = new ArrayList<>();

    public static ArrayList<Expense> getExpenseList() {
        return expenseList;
    }

    public static void addExpense(Expense expense) {
        expenseList.add(expense);
    }
}
