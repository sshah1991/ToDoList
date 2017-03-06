package com.example.sumeet.todolist_try1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sumeet on 19-02-2017.
 */

public class dbHelper extends SQLiteOpenHelper {
    //Database version
    private static final int database_VERSION = 1;

    public static final String DATABASE_NAME = "contents.db";


    public static final String TABLE_NAME = "ToDo";

    public static final String Contents_ID = "id";
    public static final String Contents_Status = "status";
    public static final String Contents_Tittle = "tittle";
    public static final String Contents_Description = "description";
    public static final String Contents_Date = "date";


    private static final String CREATE_Contents_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    Contents_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Contents_Tittle + " TEXT,"
                    + Contents_Description + " TEXT,"
                    + Contents_Status + " INTEGER,"
                    + Contents_Date + " TEXT );";

    dbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_Contents_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Contents");
        onCreate(sqLiteDatabase);
    }

    public void createToDo(dataContents dataObj) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contents_Tittle, dataObj.getKey_Tittle());
        values.put(Contents_Description, dataObj.getKey_Description());
        values.put(Contents_Date, dataObj.getKey_Date());
        values.put(Contents_Status, 0);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public List<dataContents> getAllDoneTodo() {
        List<dataContents> todos = new LinkedList<dataContents>();
        String select_querry_done = "SELECT * FROM ToDo where status = 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_querry_done, null);

        dataContents todo = null;
        if (cursor.moveToFirst()) {
            do {
                todo = new dataContents();
                todo.setKey_id(Integer.parseInt(cursor.getString(0)));
                todo.setKey_Tittle(cursor.getString(1));
                todo.setKey_Description(cursor.getString(2));
                todo.setKey_Status(cursor.getInt(3));
                todo.setKey_Date(cursor.getString(4));
                // Add book to books
                todos.add(todo);
            } while (cursor.moveToNext());
        }
        return todos;
    }

    public List<dataContents> getAllTodo() {
        List<dataContents> todos = new LinkedList<dataContents>();
        String select_querry = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_querry, null);

        dataContents todo = null;
        if (cursor.moveToFirst()) {
            do {
                todo = new dataContents();
                todo.setKey_id(Integer.parseInt(cursor.getString(0)));
                todo.setKey_Tittle(cursor.getString(1));
                todo.setKey_Description(cursor.getString(2));
                todo.setKey_Status(cursor.getInt(3));
                todo.setKey_Date(cursor.getString(4));
                // Add book to books
                todos.add(todo);
            } while (cursor.moveToNext());
        }
        return todos;
    }

    public void deleteItem(int selid) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Log.i("delete pos: ", String.valueOf(position));
        db.delete(TABLE_NAME, Contents_ID + " = ? ", new String[]{String.valueOf(selid)});
        db.close();

    }

    public void updatedataContents(dataContents updItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contents_Tittle, updItem.getKey_Tittle());
        values.put(Contents_Description, updItem.getKey_Description());
        values.put(Contents_Date, updItem.getKey_Date());
        db.update(TABLE_NAME, values, Contents_ID + " = ? ", new String[]{String.valueOf(updItem.getKey_id())});
        db.close();
    }

    public Cursor getdataContents(int updid) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("Select * from ToDo where " + Contents_ID + " = " + updid, null);
        return c;
    }

    public void taskDone(int idDone) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE ToDo SET status=1 WHERE id =" + idDone + "");
        db.close();
    }
}
