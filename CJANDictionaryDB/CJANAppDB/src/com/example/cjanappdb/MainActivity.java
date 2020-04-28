package com.example.cjanappdb;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener 
{

	
	Button b1,b2,b3;
	TextView tabledata;
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		b1 = (Button)findViewById(R.id.dbb1);
		b2 = (Button)findViewById(R.id.dbb2);
		b3 = (Button)findViewById(R.id.dbb3);
		
		tabledata = (TextView)findViewById(R.id.dbrecords);
		
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		
		db = openOrCreateDatabase("mydatabase",MODE_WORLD_READABLE, null);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.dbb1){
			//CRAETE TABLE
String q = "create table if not exists movie(sno int,mname varchar,mtype varchar,mrate int)";
		   db.execSQL(q);
		   Toast.makeText(this,"TABLE CREATED",1000).show();
		}
		else if(v.getId()==R.id.dbb2){
			//LOAD DATA IN TABLE
String q = "insert into movie values(100,'Sholey','Action',5)";
 	      db.execSQL(q);
       q = "insert into movie values(101,'Devdas','Darama',5)";
	      db.execSQL(q);
	   q = "insert into movie values(102,'Dhoom','ACTION',4)";
	      db.execSQL(q);   
	 
	      Toast.makeText(this,"DATA LOADED", 1000).show();
		}
		else if(v.getId()==R.id.dbb3){
			//VIEW TABLE DATA
		String q = "select *from movie";
		Cursor c = db.rawQuery(q,null);
		String data = "MOVIE LIST : \n----------";
		while(c.moveToNext()){
			String n = c.getString(1);
			String t = c.getString(2);
			String r = c.getString(3);
			
			data += "\n"+n+" : "+t+" : "+r;
		}
		
		 tabledata.setText(data);
		
		}
	}

	
}
