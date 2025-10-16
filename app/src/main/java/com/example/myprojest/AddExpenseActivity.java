package com.example.myprojest;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class AddExpenseActivity extends AppCompatActivity {

    private EditText edtAmount, edtRemark, edtCreatedDate;
    private RadioGroup radioGroupCurrency;
    private Spinner spinnerCategory;
    private Button btnAddExpense;
    private ImageButton btnAddCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        // Initialize views
        edtAmount = findViewById(R.id.edtAmount);
        edtRemark = findViewById(R.id.edtRemark);
        edtCreatedDate = findViewById(R.id.edtCreatedDate);
        radioGroupCurrency = findViewById(R.id.radioGroupCurrency);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        btnAddExpense = findViewById(R.id.btnAddExpense);
        btnAddCategory = findViewById(R.id.btnAddCategory);

        // Set up spinner with example categories
        String[] categories = {"Food", "Transport", "Shopping", "Bills", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        // Date picker for created date
        edtCreatedDate.setOnClickListener(v -> showDatePicker());

        // Add expense button click
        btnAddExpense.setOnClickListener(v -> addExpense());
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    edtCreatedDate.setText(date);
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void addExpense() {
        String amount = edtAmount.getText().toString().trim();
        String remark = edtRemark.getText().toString().trim();
        String createdDate = edtCreatedDate.getText().toString().trim();
        String category = spinnerCategory.getSelectedItem().toString();

        int selectedCurrencyId = radioGroupCurrency.getCheckedRadioButtonId();
        String currency = "";
        if (selectedCurrencyId != -1) {
            RadioButton selectedRadio = findViewById(selectedCurrencyId);
            currency = selectedRadio.getText().toString();
        }

        if (amount.isEmpty() || createdDate.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // ✅ Send data to ExpenseDetailActivity
        Intent intent = new Intent(this, ExpenseDetailActivity.class);
        intent.putExtra("amount", amount);
        intent.putExtra("currency", currency);
        intent.putExtra("category", category);
        intent.putExtra("remark", remark);
        intent.putExtra("date", createdDate);

        startActivity(intent);
    }
}
