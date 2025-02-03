package edu.northeastern.numad25sp_rohanskaria;

import android.app.AlertDialog;
import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

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
            showAddLinkMessage();
        });

        adapter = new LinkAdapter();
        rv.setAdapter(adapter);

        if (savedInstanceState != null) {
            ArrayList<Link> saved = savedInstanceState.getParcelableArrayList("links");
            if (saved != null) {
                adapter.setLinks(saved);
            }
        }

        initSwipeToDelete();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle out) {
        super.onSaveInstanceState(out);
        out.putParcelableArrayList("links", adapter.getLinks());
    }


    private void initSwipeToDelete() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                Link deletedLink = adapter.getLink(pos);
                adapter.removeLink(pos);

                Snackbar.make(rv, "Link Deleted", Snackbar.LENGTH_LONG).setAction("Undo", v-> {
                    adapter.insertLink(pos,deletedLink);
                }).show();
            }
        }).attachToRecyclerView(rv);
    }

    private void showAddLinkMessage() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.add_link_card, null);
        EditText nameEdit = dialogView.findViewById(R.id.editName);
        EditText urlEdit = dialogView.findViewById(R.id.editUrl);

        new AlertDialog.Builder(this).setTitle("Add Link").setView(dialogView)
                .setPositiveButton("Add", (dialog, which) -> {
                    String name = nameEdit.getText().toString();
                    String url = urlEdit.getText().toString();

                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        url = "https://"+ url;
                    }

                    Link newLink = new Link(name, url);
                    adapter.addLink(newLink);

                Snackbar.make(rv, "Link Added", Snackbar.LENGTH_LONG).show();
                })
                .setNegativeButton("Cancel",null)
                .show();
    }


}
