package com.example.brintaflevering2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class WordListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String ord[] = new String[Galgelogik.muligeOrd.size()];
        for (int i = 0; i < ord.length; i++) {
            ord[i] = Galgelogik.muligeOrd.get(i);
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ord);

        ListView listView = new ListView(this);
        listView.setOnItemClickListener(this);
        listView.setAdapter(arrayAdapter);

        setContentView(listView);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
