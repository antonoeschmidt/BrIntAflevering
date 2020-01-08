package com.example.brintaflevering2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class WordListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public ArrayAdapter arrayAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(WordListActivity.this,"Tryk på et ord, for at fjerne det fra spillet", Toast.LENGTH_SHORT).show();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Galgelogik.muligeOrd);

        ListView listView = new ListView(this);
        listView.setOnItemClickListener(this);
        listView.setAdapter(arrayAdapter);

        setContentView(listView);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        removeWordDialog(position);

    }

    public void removeWordDialog(final int position) {
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
