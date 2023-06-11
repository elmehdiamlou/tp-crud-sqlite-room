package com.example.tp_crud_sqlite_room;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editText, editText2, editText3;
    Button btnAdd, btnReset;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Database database;
    List<PaysEntity> data = new ArrayList<>();
    PaysAdapter paysAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edit_text);
        editText2 = findViewById(R.id.edit_text2);
        editText3 = findViewById(R.id.edit_text3);
        btnAdd = findViewById(R.id.btnAdd);
        btnReset = findViewById(R.id.btnReset);
        recyclerView = findViewById(R.id.recycler_view);
        database = database.getInstance(this);
        data = database.paysDao().getAll();
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        paysAdapter = new PaysAdapter(MainActivity.this, data);
        recyclerView.setAdapter(paysAdapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String paysText = editText.getText().toString().trim();
                String capitalText = editText2.getText().toString().trim();
                String habitantText = editText3.getText().toString().trim();
                if (!paysText.equals("")) {
                    PaysEntity pays = new PaysEntity();
                    pays.setText(paysText);
                    pays.setCapital(capitalText);
                    pays.setHabitants(Float.parseFloat(habitantText));
                    database.paysDao().insert(pays);
                    editText.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    data.clear();
                    data.addAll(database.paysDao().getAll());
                    paysAdapter.notifyDataSetChanged();
                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                editText2.setText("");
                editText3.setText("");
            }
        });
    }
}