package com.example.ivaylovasilev.taskmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewTaskActivity extends AppCompatActivity {

    EditText inputEditText;

    Button addButton;

    DbHelper db;

    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        db = new DbHelper(getApplicationContext());

        inputEditText = (EditText) findViewById(R.id.inputEditText);

        addButton = (Button) findViewById(R.id.addButton);

        addButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!inputEditText.getText().toString().isEmpty()){
                            task = new Task();
                            task.setText(inputEditText.getText().toString());
                            db.Insert(task);
                            Toast.makeText(NewTaskActivity.this, task.getText() + "is successfully added", Toast.LENGTH_SHORT).show();
                            inputEditText.setText("");

                        }
                        else{
                            Toast.makeText(NewTaskActivity.this, "Please enter task to add", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

}
