package com.example.ivaylovasilev.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivaylo Vasilev on 25/5/2018.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "TaskManager.db";

    public static final String TABLE_NAME = "tasks_table";

    public static final String COLUMN_NAME_ID = "ID";

    public static final String COLUMN_NAME_TASK = "task";

    private static final String SQL_CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " (" +

            COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME_TASK + " TEXT)";

    private static final String SQL_DELETE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;



    public DbHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_QUERY);
        onCreate(db);
    }

    public void Insert(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TASK, task.getText());


        long result = db.insert(TABLE_NAME, null, values);



    }
    public List<Task> Get() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);

        List<Task> tasks = new ArrayList<>();
        while(cursor.moveToNext()) {

            String id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_ID));
            String text = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_TASK));



            Task task = new Task(Integer.parseInt(id), text);
            tasks.add(task);
        }
        cursor.close();

        return tasks;

    }
    public void Update(Task task){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TASK, task.getText());


        String selection = COLUMN_NAME_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(task.getId()) };

        db.update(TABLE_NAME, values, selection, selectionArgs);

    }

    public void Delete(int ID){

        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_NAME_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(ID) };
        db.delete(TABLE_NAME, selection, selectionArgs);
    }

}
