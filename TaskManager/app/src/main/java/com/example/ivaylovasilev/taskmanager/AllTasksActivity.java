package com.example.ivaylovasilev.taskmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AllTasksActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    Button updateButton, deleteButton;
    EditText updateEditText;
    ListView tasksListView;
    DbHelper db;
    List<Task> tasks;
    List<String> showAllTasks = new ArrayList<String>();
    ArrayAdapter<String> tasksAdapter;
    Task task;


    int selectedItem;

    boolean isSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);

        updateButton = (Button) findViewById(R.id.updateButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        updateEditText = (EditText) findViewById(R.id.updateEditText);
        tasksListView = (ListView) findViewById(R.id.tasksListView);

        db = new DbHelper(getApplicationContext());

        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);

        listAllTasks();





    }

    public void listAllTasks(){
        tasks = db.Get();

        for(Task task: tasks){

            showAllTasks.add(task.getText());
        }

        tasksAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_tasks, showAllTasks);

        tasksListView.setAdapter(tasksAdapter);
        tasksListView.setOnItemClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == deleteButton.getId()){

            if (isSelected == false){
                Toast.makeText(AllTasksActivity.this, "Please select the task you want to delete", Toast.LENGTH_SHORT).show();
                return;
            }

            tasks = db.Get();

            db.Delete(tasks.get(selectedItem).getId());

            showAllTasks.clear();

            listAllTasks();

            isSelected = false;

            updateEditText.setText("");

        }

        if(v.getId() == updateButton.getId()){

            if (isSelected == false){
                Toast.makeText(AllTasksActivity.this, "Please select the task you want to update", Toast.LENGTH_SHORT).show();
                return;
            }

           if(updateEditText.getText().toString().isEmpty()){
               Toast.makeText(AllTasksActivity.this, "Please enter the field to update", Toast.LENGTH_SHORT).show();
               return;
           }

            task = new Task();

            task.setId(tasks.get(selectedItem).getId());
            task.setText(updateEditText.getText().toString());

            db.Update(task);

            showAllTasks.clear();

            listAllTasks();

            isSelected = false;

            updateEditText.setText("");


        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //updateEditText.setText(tasks.get(position).getText());
        selectedItem = position;
        isSelected = true;
    }
}
