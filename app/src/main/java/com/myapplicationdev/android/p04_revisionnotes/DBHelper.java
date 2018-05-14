package com.myapplicationdev.android.p04_revisionnotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

	//TODO Define the Database properties
	private static final String DATABASE_NAME = "db";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NOTE = "note";
	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_CONTENT = "content";
	private static final String COLUMN_STARS = "stars";



	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//TODO CREATE TABLE Note
		String createTable = "CREATE TABLE "+TABLE_NOTE +"("
				+ COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ COLUMN_CONTENT +" TEXT,"
				+ COLUMN_STARS + " INTEGER)";
		db.execSQL(createTable);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
		onCreate(db);
	}

	public void insertNote(String noteContent, int stars) {
		//TODO insert the data into the database
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_CONTENT, noteContent);
		values.put(COLUMN_STARS, stars);
		db.insert(TABLE_NOTE, null, values);


	}

	public ArrayList<Note> getAllNotes() {
		//TODO return records in Java objects
		ArrayList<Note> noteArrayList = new ArrayList<>();
		String selectQuery = "SELECT *"
				+ " FROM " + TABLE_NOTE;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			// Loop while moveToNext() points to next row
			//  and returns true; moveToNext() returns false
			//  when no more next row to move to
			do {
				int id = cursor.getInt(0);
				String noteContent = cursor.getString(1);
				int stars = cursor.getInt(2);
				Note obj = new Note(id, noteContent, stars);
				noteArrayList.add(obj);	} while (cursor.moveToNext());
		}
		// Close connection
		cursor.close();
		db.close();

		return noteArrayList;
	}

    public ArrayList<String> getNoteContent() {
        //TODO return records in Strings

		// Create an ArrayList that holds String objects
        ArrayList<String> notes = new ArrayList<String>();
        // Select all the notes' content
		String selectQuery = "SELECT *"
				+ " FROM " + TABLE_NOTE;
		// Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        // Run the SQL query and get back the Cursor object
        Cursor cursor = db.rawQuery(selectQuery, null);
        // moveToFirst() moves to first row
        if (cursor.moveToFirst()) {
            // Loop while moveToNext() points to next row and returns true;
            // moveToNext() returns false when no more next row to move to
            do {
				String noteContent = cursor.getString(1);
				notes.add(noteContent);

            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return notes;
    }
}
