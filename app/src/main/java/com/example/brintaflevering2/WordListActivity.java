package com.example.brintaflevering2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class WordListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public ArrayAdapter arrayAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/*
        String ord[] = new String[Galgelogik.muligeOrd.size()];
        for (int i = 0; i < ord.length; i++) {
            ord[i] = Galgelogik.muligeOrd.get(i);
        }

 */

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Galgelogik.muligeOrd);
       // arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ord);

        ListView listView = new ListView(this);
        listView.setOnItemClickListener(this);
        listView.setAdapter(arrayAdapter);

        setContentView(listView);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showDialog1(position);

    }

    public void showDialog1 (final int position) {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Vil du fjerne ordet?");
        b.setPositiveButton("Fjern ord", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                Galgelogik.muligeOrd.remove(position);
                arrayAdapter.notifyDataSetChanged();
            }
        });
        b.setNegativeButton("Annuller", null);
        b.show();
    };
}
