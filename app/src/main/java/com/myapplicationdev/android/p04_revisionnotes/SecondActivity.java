package com.myapplicationdev.android.p04_revisionnotes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
	ArrayAdapter aa;
	ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.activity_second);

		//TODO implement the Custom ListView
		listView = findViewById(R.id.lv);
		ArrayList<Note> arrayList = new ArrayList<>();
		DBHelper db = new DBHelper(SecondActivity.this);
		arrayList = db.getAllNotes();
		aa = new RevisionNotesArrayAdapter(this, R.layout.row, arrayList);
		listView.setAdapter(aa);
	}


}
