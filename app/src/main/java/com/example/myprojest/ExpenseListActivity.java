package com.example.myprojest;

import android.content.Intent; // ADDED
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView; // ADDED
import java.util.ArrayList;

public class ExpenseListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;
    private ArrayList<Expense> expenseList;
    private BottomNavigationView bottomNavigationView; // ADDED

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        // --- NAVIGATION LOGIC ---
        bottomNavigationView = findViewById(R.id.bottomNavigationView); // ADDED

        // Set List as selected
        bottomNavigationView.setSelectedItemId(R.id.nav_list);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                // Go to Home Activity
                startActivity(new Intent(this, Activitymain.class));
                overridePendingTransition(0, 0);
                return true;

            } else if (id == R.id.nav_add) {
                // Go to Add Expense Activity
                startActivity(new Intent(this, AddExpenseActivity.class));
                overridePendingTransition(0, 0);
                return true;

            } else if (id == R.id.nav_list) {
                return true; // You are already here
            }
            return false;
        });
        // --- END OF NAVIGATION LOGIC ---

        recyclerView = findViewById(R.id.recyclerViewExpenses);

        // --- MODIFIED DATA LOGIC ---
        // Get the list from the shared repository
        expenseList = ExpenseRepository.getExpenseList();
        // expenseList = (ArrayList<Expense>) getIntent().getSerializableExtra("expenseList"); // REMOVED

        adapter = new ExpenseAdapter(expenseList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    // When returning to this screen (e.g., after saving),
    // we need to tell the adapter to refresh
    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}