package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class List extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    EditText eTask;
    Button add;
    ListView list;

    private ArrayList<String> tasks;
    private ArrayAdapter<String> adapt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toast.makeText(this, "got here", Toast.LENGTH_SHORT).show();
        eTask = findViewById(R.id.addTask);
        add = findViewById(R.id.addButton);
        list = findViewById(R.id.todoList);

        tasks = FileHelp.readData(this);


        if(tasks == null){
            tasks = new ArrayList<>();
        }
        if(tasks.isEmpty()){
            tasks.add("All tasks completed");
        }
        adapt = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, tasks);

        list.setAdapter(adapt);

        add.setOnClickListener(this);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.addButton:
                String entered = eTask.getText().toString();
                adapt.add(entered);
                eTask.setText("");
                FileHelp.writeData(tasks,this);
                Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        tasks.remove(position);
        adapt.notifyDataSetChanged();
        Toast.makeText(this, "Task Removed", Toast.LENGTH_SHORT).show();
    }
}
