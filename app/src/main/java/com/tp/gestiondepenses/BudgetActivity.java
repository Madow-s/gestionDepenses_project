package com.tp.gestiondepenses;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class BudgetActivity extends AppCompatActivity {

    private RecyclerView recyclerViewBudgets;
    private BudgetAdapter budgetAdapter;
    private List<Budget> budgetList;
    private DatabaseHelper databaseHelper;
    private LinearLayout emptyStateLayout;
    private TextView textViewEmptyState;
    private FloatingActionButton fabAddBudget;
    private Button buttonCreateFirstBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerViewBudgets = findViewById(R.id.recyclerViewBudgets);
        emptyStateLayout = findViewById(R.id.emptyStateLayout);
        textViewEmptyState = findViewById(R.id.textViewEmptyState);
        fabAddBudget = findViewById(R.id.fabAddBudget);
        buttonCreateFirstBudget = findViewById(R.id.buttonCreateFirstBudget);

        databaseHelper = new DatabaseHelper(this);
        budgetList = new ArrayList<>();

        setupRecyclerView();
        loadBudgets();

        fabAddBudget.setOnClickListener(v -> showAddBudgetDialog());
        
        if (buttonCreateFirstBudget != null) {
            buttonCreateFirstBudget.setOnClickListener(v -> showAddBudgetDialog());
        }
    }

    private void setupRecyclerView() {
        budgetAdapter = new BudgetAdapter(budgetList, new BudgetAdapter.OnBudgetActionListener() {
            @Override
            public void onEdit(Budget budget) {
                showEditBudgetDialog(budget);
            }
            @Override
            public void onDelete(Budget budget) {
                deleteBudget(budget);
            }
        });
        recyclerViewBudgets.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewBudgets.setAdapter(budgetAdapter);
    }

    private void loadBudgets() {
        budgetList.clear();
        budgetList.addAll(databaseHelper.getAllBudgets());
        budgetAdapter.notifyDataSetChanged();
        updateEmptyState();
    }

    private void updateEmptyState() {
        if (budgetList.isEmpty()) {
            emptyStateLayout.setVisibility(View.VISIBLE);
            recyclerViewBudgets.setVisibility(View.GONE);
        } else {
            emptyStateLayout.setVisibility(View.GONE);
            recyclerViewBudgets.setVisibility(View.VISIBLE);
        }
    }

    private void showAddBudgetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_budget, null);
        builder.setView(dialogView);

        EditText editTextCategorie = dialogView.findViewById(R.id.editTextCategorie);
        EditText editTextPlafond = dialogView.findViewById(R.id.editTextPlafond);
        Button buttonSave = dialogView.findViewById(R.id.buttonSave);
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);

        AlertDialog dialog = builder.create();
        dialog.show();

        buttonSave.setOnClickListener(v -> {
            String categorie = editTextCategorie.getText().toString().trim();
            String plafondStr = editTextPlafond.getText().toString().trim();

            if (categorie.isEmpty() || plafondStr.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            double plafond = Double.parseDouble(plafondStr);
            Budget newBudget = new Budget(categorie, plafond, 0);
            long id = databaseHelper.addBudget(newBudget);
            if (id != -1) {
                newBudget.setId((int) id);
                budgetList.add(newBudget);
                budgetAdapter.notifyItemInserted(budgetList.size() - 1);
                updateEmptyState();
                Toast.makeText(this, "Budget créé avec succès", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else {
                Toast.makeText(this, "Erreur lors de la création", Toast.LENGTH_SHORT).show();
            }
        });

        buttonCancel.setOnClickListener(v -> dialog.dismiss());
    }

    private void showEditBudgetDialog(Budget budget) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_budget, null);
        builder.setView(dialogView);

        EditText editTextCategorie = dialogView.findViewById(R.id.editTextCategorie);
        EditText editTextPlafond = dialogView.findViewById(R.id.editTextPlafond);
        Button buttonSave = dialogView.findViewById(R.id.buttonSave);
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);

        editTextCategorie.setText(budget.getCategorie());
        editTextPlafond.setText(String.valueOf(budget.getPlafond()));

        AlertDialog dialog = builder.create();
        dialog.show();

        buttonSave.setOnClickListener(v -> {
            String categorie = editTextCategorie.getText().toString().trim();
            String plafondStr = editTextPlafond.getText().toString().trim();

            if (categorie.isEmpty() || plafondStr.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            budget.setCategorie(categorie);
            budget.setPlafond(Double.parseDouble(plafondStr));

            if (databaseHelper.updateBudget(budget)) {
                int position = budgetList.indexOf(budget);
                if (position != -1) {
                    budgetAdapter.notifyItemChanged(position);
                }
                Toast.makeText(this, "Budget mis à jour", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else {
                Toast.makeText(this, "Erreur lors de la mise à jour", Toast.LENGTH_SHORT).show();
            }
        });

        buttonCancel.setOnClickListener(v -> dialog.dismiss());
    }

    private void deleteBudget(Budget budget) {
        new AlertDialog.Builder(this)
                .setTitle("Supprimer le budget")
                .setMessage("Voulez-vous vraiment supprimer le budget " + budget.getCategorie() + " ?")
                .setPositiveButton("Supprimer", (dialog, which) -> {
                    if (databaseHelper.deleteBudget(budget.getId())) {
                        int position = budgetList.indexOf(budget);
                        if (position != -1) {
                            budgetList.remove(position);
                            budgetAdapter.notifyItemRemoved(position);
                            updateEmptyState();
                            Toast.makeText(this, "Budget supprimé", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Annuler", null)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadBudgets();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
