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

        tvAmount = findViewById(R.id.tvAmount);
        tvCurrency = findViewById(R.id.tvCurrency);
        tvCategory = findViewById(R.id.tvCategory);
        tvRemark = findViewById(R.id.tvRemark);
        tvDate = findViewById(R.id.tvDate);
        btnAddNew = findViewById(R.id.btnAddNew);
        btnBackHome = findViewById(R.id.btnBackHome);

        Intent intent = getIntent();
        if (intent != null) {
            tvAmount.setText("Amount: " + intent.getStringExtra("amount"));
            tvCurrency.setText("Currency: " + intent.getStringExtra("currency"));
            tvCategory.setText("Category: " + intent.getStringExtra("category"));
            tvRemark.setText("Remark: " + intent.getStringExtra("remark"));
            tvDate.setText("Date: " + intent.getStringExtra("date"));
        }

        btnAddNew.setOnClickListener(v -> startActivity(new Intent(this, AddExpenseActivity.class)));

        btnBackHome.setOnClickListener(v -> {
            Intent home = new Intent(this, Activitymain.class);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(home);
            finish();
        });
    }
}
