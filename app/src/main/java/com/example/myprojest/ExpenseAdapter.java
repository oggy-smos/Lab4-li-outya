package com.example.myprojest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    private ArrayList<Expense> expenses;

    public ExpenseAdapter(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_expense, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Expense e = expenses.get(position);

        // Set text (added currency)
        holder.txtAmount.setText("Amount: " + e.getAmount() + " " + e.getCurrency());
        holder.txtCategory.setText("Category: " + e.getCategory());
        holder.txtDate.setText("Date: " + e.getDate());
        holder.txtRemark.setText("Remark: " + e.getRemark());

        // --- ADDED THIS ---
        // Set click listener for the whole item
        holder.itemView.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, ExpenseDetailActivity.class);

            // Pass all expense data to the detail activity
            intent.putExtra("amount", e.getAmount());
            intent.putExtra("currency", e.getCurrency());
            intent.putExtra("category", e.getCategory());
            intent.putExtra("remark", e.getRemark());
            intent.putExtra("date", e.getDate());

            context.startActivity(intent);
        });
        // --- END OF ADDED CODE ---
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtAmount, txtCategory, txtDate, txtRemark;

        ViewHolder(View itemView) {
            super(itemView);
            txtAmount = itemView.findViewById(R.id.txtAmount);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtRemark = itemView.findViewById(R.id.txtRemark);
        }
    }
}