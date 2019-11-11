package com.example.brintaflevering2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WordListActivity extends AppCompatActivity {
    RecyclerView recyclerView;


    //Kode taget fra Jakob Nordfalk
    //Er ikke taget i brug, da koden ikke virker endnu
    //Skal bruges til næste implementation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = new RecyclerView(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        setContentView(recyclerView);
    }

    RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = getLayoutInflater().inflate(R.layout.activity_word_list, parent, false);
            return new RecyclerView.ViewHolder(itemView) {};
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {
            TextView overskrift = vh.itemView.findViewById(R.id.textViewListElement);
            overskrift.setText(Galgelogik.muligeOrd.get(position));
        }

        @Override
        public int getItemCount() {
            return Galgelogik.muligeOrd.size();
        }
    };
}
