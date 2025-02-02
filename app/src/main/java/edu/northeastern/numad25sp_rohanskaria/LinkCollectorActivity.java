package edu.northeastern.numad25sp_rohanskaria;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
}
