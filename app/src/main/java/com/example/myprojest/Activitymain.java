package com.example.myprojest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Activitymain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Make sure activity_main.xml has btnAddExpense

        // Find the button
        Button btnAddExpense = findViewById(R.id.btnAddExpense);

        // Button click listener
        btnAddExpense.setOnClickListener(v -> {
            Toast.makeText(this, "Opening Add Expense...", Toast.LENGTH_SHORT).show();

            // Go to AddExpenseActivity
            Intent intent = new Intent(Activitymain.this, AddExpenseActivity.class);
            startActivity(intent);
        });
    }
}
