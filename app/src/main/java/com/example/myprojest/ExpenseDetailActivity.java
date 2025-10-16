package com.example.myprojest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ExpenseDetailActivity extends AppCompatActivity {

    private TextView tvAmount, tvCurrency, tvCategory, tvRemark, tvDate;
    private Button btnAddNew, btnBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);

        // Initialize views
        tvAmount = findViewById(R.id.tvAmount);
        tvCurrency = findViewById(R.id.tvCurrency);
        tvCategory = findViewById(R.id.tvCategory);
        tvRemark = findViewById(R.id.tvRemark);
        tvDate = findViewById(R.id.tvDate);
        btnAddNew = findViewById(R.id.btnAddNew);
        btnBackHome = findViewById(R.id.btnBackHome);

        // Get data from intent
        Intent intent = getIntent();
        if (intent != null) {
            String amount = intent.getStringExtra("amount");
            String currency = intent.getStringExtra("currency");
            String category = intent.getStringExtra("category");
            String remark = intent.getStringExtra("remark");
            String date = intent.getStringExtra("date");

            // Display data
            tvAmount.setText("Amount: " + amount);
            tvCurrency.setText("Currency: " + currency);
            tvCategory.setText("Category: " + category);
            tvRemark.setText("Remark: " + remark);
            tvDate.setText("Created Date: " + date);
        }

        // Go to Add Expense screen
        btnAddNew.setOnClickListener(v -> {
            Intent addExpenseIntent = new Intent(this, AddExpenseActivity.class);
            startActivity(addExpenseIntent);
            finish(); // Optional: finish current activity
        });

        // Go back to Home screen
        btnBackHome.setOnClickListener(v -> {
            Intent homeIntent = new Intent(this, Activitymain.class);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(homeIntent);
            finish();
        });
    }
}
