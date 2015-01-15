package tiles;



import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Comment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;



public class DataBaseAdapter {

	int lables;
	int lables1;
			static final String DATABASE_NAME = "list1.db";
			static final int DATABASE_VERSION = 1;
			public static final int NAME_COLUMN = 1;
			
			static final String DATABASE_CREATE = "create table "+"list1"+
			                             "( " +"ID"+" integer primary key autoincrement,"+ "songId  interger, title text, artist text, cat text); ";
			
			public  SQLiteDatabase db;
			
			private final Context context;
			
			private DataBaseHelper dbHelper;
			public  DataBaseAdapter(Context _context) 
			{
				context = _context;
				dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
			}
			public  DataBaseAdapter open() throws SQLException 
			{
				db = dbHelper.getWritableDatabase();
				return this;
			}
			public void close() 
			{
				db.close();
			}
            
			public  SQLiteDatabase getDatabaseInstance()
			{
				return db;
			}

			public void insertEntry(long songId, String title, String artist, String cat)
			{
		       ContentValues newValues = new ContentValues();
				
				newValues.put("songId", songId);
				newValues.put("title", title);
				newValues.put("artist", artist);
				newValues.put("cat", cat);
				db.insert("list1", null, newValues);
				
				Toast.makeText(context, "data saved", Toast.LENGTH_SHORT).show();
			}
			public List<String> getAllLabels(){
		        List<String> labels = new ArrayList<String>();
		         
		       String[]columns=new String[]{"songId","title","artist","cat"};
		        Cursor c=db.query("list1", columns, null, null, null, null, null);
		     //   int id=c.getColumnIndexOrThrow("ID");
		       // int id1=id+1;
		        	int id=c.getColumnIndexOrThrow("songId");
		        	int title=c.getColumnIndexOrThrow("title");
		        	int artist=c.getColumnIndexOrThrow("artist");
		        	int cat=c.getColumnIndexOrThrow("cat");
		        	 for(c.moveToFirst(); !c.isAfterLast();c.moveToNext()){
		        		 labels.add(""+c.getString(title)+"                                                          "+c.getString(artist)              );
		        	    }	
		        	 
		        	
				return labels;	
		        
		    }
			public int getsong(int number)
			{
				Cursor cursor11=db.query("list1", null, " ID=?", new String[]{String.valueOf(number)}, null, null, null);
		        if(cursor11.getCount()<1) 
		        {
		        	cursor11.close();
		      
		        	
		        }
			    cursor11.moveToFirst();
			 int name1= cursor11.getInt(cursor11.getColumnIndex("songId"));
				cursor11.close();
				
				return name1;	
			}   
	}

