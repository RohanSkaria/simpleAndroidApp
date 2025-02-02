package edu.northeastern.numad25sp_rohanskaria;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class LinkCollectorActivity extends AppCompatActivity {
    private RecyclerView rv;
    private FloatingActionButton fab;
    private LinkAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            System.out.println("Button Clicked");
        });

        adapter = new LinkAdapter();
        rv.setAdapter(adapter);
    }

    private void showAddLinkMessage() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.add_link_card, null);
        EditText nameEdit = dialogView.findViewById(R.id.editName);
        EditText urlEdit = dialogView.findViewById(R.id.editUrl);

        new AlertDialog.Builder(this).setTitle("Add Link").setView(dialogView)
                .setPositiveButton("Add", (dialog, which) -> {
                    String name = nameEdit.getText().toString();
                    String url = urlEdit.getText().toString();
                    Link newLink = new Link(name, url);
                    adapter.addLink(newLink);

                Snackbar.make(rv, "Link Added", Snackbar.LENGTH_LONG).show();
                })
                .setNegativeButton("Cancel",null)
                .show();
    }
}
