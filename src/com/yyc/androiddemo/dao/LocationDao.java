package com.yyc.androiddemo.dao;

import java.util.ArrayList;
import java.util.List;

import com.yyc.androiddemo.bean.Location;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class LocationDao {
	private LocationDBhelper mHelper;
	private Context mContext;
	public static final String DB_NAME = "AndroidDemo";
	
	public LocationDao(Context context){
		this.mContext = context;
		this.mHelper = new LocationDBhelper(mContext, DB_NAME);
	}
	
	public long insertLocation(Location location){
		ContentValues cv = new ContentValues();
		SQLiteDatabase db = this.mHelper.getWritableDatabase();
		cv.put(LocationDBhelper.title, location.getTitle());
		cv.put(LocationDBhelper.description, location.getDescription());
		cv.put(LocationDBhelper.picURL, location.getPicURL());
		
		long id =  db.insert(LocationDBhelper.TABLE_NAME, null, cv);
		Log.i("LocationDao_tag", "the new record id is : "+id);
		return id;
	}
	public List<Location> getAll(){
		List<Location> locations = new ArrayList<Location>();
		SQLiteDatabase db = this.mHelper.getReadableDatabase();
		Cursor cursor = db.query(LocationDBhelper.TABLE_NAME, null, null, null, null, null, null);
		int titleColumn = cursor.getColumnIndex(LocationDBhelper.title);
		int descriptionColumn = cursor.getColumnIndex(LocationDBhelper.description);
		int picURLColumn = cursor.getColumnIndex(LocationDBhelper.picURL);
		
		while (cursor.moveToNext()) {
			String titleString = cursor.getString(titleColumn);
			String descriptionString = cursor.getString(descriptionColumn);
			String picURLString = cursor.getString(picURLColumn);
			
			Location l = new Location(titleString, descriptionString, picURLString);
			locations.add(l);
		}
		
		return locations;
	}
}

