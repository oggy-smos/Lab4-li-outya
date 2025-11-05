package com.example.myprojest;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView; // ADDED
import java.util.Calendar;

public class AddExpenseActivity extends AppCompatActivity {

    private EditText edtAmount, edtRemark, edtCreatedDate;
    private Spinner spinnerCategory;
    private RadioGroup radioGroupCurrency;
    private Button btnSave;
    private BottomNavigationView bottomNavigationView; // ADDED

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        edtAmount = findViewById(R.id.edtAmount);
        edtRemark = findViewById(R.id.edtRemark);
        edtCreatedDate = findViewById(R.id.edtCreatedDate);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        btnSave = findViewById(R.id.btnAddExpense);
        radioGroupCurrency = findViewById(R.id.radioGroupCurrency);
        bottomNavigationView = findViewById(R.id.bottomNavigationView); // ADDED

        // --- NAVIGATION LOGIC ---
        // Set Add as selected
        bottomNavigationView.setSelectedItemId(R.id.nav_add);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                // Go to Home Activity
                startActivity(new Intent(this, Activitymain.class));
                overridePendingTransition(0, 0);
                return true;

            } else if (id == R.id.nav_add) {
                return true; // You are already here

            } else if (id == R.id.nav_list) {
                // Go to Expense List Activity
                startActivity(new Intent(this, ExpenseListActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });
        // --- END OF NAVIGATION LOGIC ---


        // Spinner setup
        String[] categories = {"Food", "Transport", "Shopping", "Bills", "Other"};
        spinnerCategory.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, categories));

        edtCreatedDate.setOnClickListener(v -> showDatePicker());
        btnSave.setOnClickListener(v -> saveExpense());
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    edtCreatedDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                },
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void saveExpense() {
        String amount = edtAmount.getText().toString();
        String category = spinnerCategory.getSelectedItem().toString();
        String remark = edtRemark.getText().toString();
        String date = edtCreatedDate.getText().toString();

        // Get selected currency
        int selectedCurrencyId = radioGroupCurrency.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedCurrencyId);
        String currency = "USD"; // Default
        if (selectedRadioButton != null) {
            currency = selectedRadioButton.getText().toString();
        }

        if (amount.isEmpty() || date.isEmpty() || selectedCurrencyId == -1) {
            Toast.makeText(this, "Please fill all fields and select currency", Toast.LENGTH_SHORT).show();
            return;
        }

        Expense expense = new Expense(amount, currency, category, remark, date);

        // ---MODIFIED SAVE LOGIC---
        // 1. Add expense to the shared repository
        ExpenseRepository.addExpense(expense);

        // 2. Show a message
        Toast.makeText(this, "Expense Saved", Toast.LENGTH_SHORT).show();

        // 3. Go to the Expense List to see the new item
        startActivity(new Intent(this, ExpenseListActivity.class));
        overridePendingTransition(0,0);
        finish(); // Close this activity

        /* REMOVED this logic
        Intent resultIntent = new Intent();
        resultIntent.putExtra("expense", expense);
        setResult(RESULT_OK, resultIntent);
        finish();
        */
    }
}