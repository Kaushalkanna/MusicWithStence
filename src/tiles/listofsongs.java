package tiles;
import java.util.ArrayList;  
import java.util.Collections;
import java.util.Comparator;

import tiles.MusicService.MusicBinder;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.MenuItem;
import android.view.View;

import com.cubes.musicwithstence.R;
public class listofsongs extends Activity {
	private ArrayList<Song> songList;
	private ListView songView;
	DataBaseAdapter databaseadapter;
	 LayoutInflater songInf;
	 private MusicService musicSrv;
	 private Intent playIntent;
	 ServiceConnection musicConnection;
	 private boolean musicBound=false;
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.player);
        
        	
        databaseadapter=new DataBaseAdapter(this);
		databaseadapter=databaseadapter.open();
        songView = (ListView)findViewById(R.id.song_list);
        songList = new ArrayList<Song>();
        getSongList();
        Collections.sort(songList, new Comparator<Song>(){
        	  public int compare(Song a, Song b){
        	    return a.getTitle().compareTo(b.getTitle());
        	  }
        	});
        SongAdapter songAdt = new SongAdapter(this, songList);
        songView.setAdapter(songAdt);
        
        
        
        
       songView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, final int position,long id) {
            	
            	
            	
            	
            	
				
            	
			
            	
				 // view = (LinearLayout)songInf.inflate(R.layout.song, parent, false);
				
           		  TextView songView = (TextView)view.findViewById(R.id.song_title);
           		final String title = songView.getText().toString();
           		  TextView artistView = (TextView)view.findViewById(R.id.song_artist);
           		final String artist = artistView.getText().toString();
           		//  Song currSong = songs.get(position);
           	
           		  
           		  
           		  
           		  
           		  
            LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);  
            View popupView = layoutInflater.inflate(R.layout.popup, null);  
            final PopupWindow popupWindow = new PopupWindow(popupView,LayoutParams.WRAP_CONTENT,  LayoutParams.WRAP_CONTENT);  
            Button o1 = (Button)popupView.findViewById(R.id.o1);
            Button o2 = (Button)popupView.findViewById(R.id.o2);
            Button o3 = (Button)popupView.findViewById(R.id.o3);
            Button o4 = (Button)popupView.findViewById(R.id.o4);
            Button o5 = (Button)popupView.findViewById(R.id.o5);
            Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
            o1.setOnClickListener(new Button.OnClickListener(){
                public void onClick(View v) {
                	databaseadapter.insertEntry(position,title,artist,"Reading");
                Toast.makeText(listofsongs.this, "Song added to Reading!", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
                }}); 
            o2.setOnClickListener(new Button.OnClickListener(){
                public void onClick(View v) {
                popupWindow.dismiss();
                Toast.makeText(listofsongs.this, "Song added to Coding!", Toast.LENGTH_SHORT).show();
                }}); 
            o3.setOnClickListener(new Button.OnClickListener(){
                public void onClick(View v) {
                popupWindow.dismiss();
                Toast.makeText(listofsongs.this, "Song added to Walking!", Toast.LENGTH_SHORT).show();
                }}); 
            o4.setOnClickListener(new Button.OnClickListener(){
                public void onClick(View v) {
                popupWindow.dismiss();
                Toast.makeText(listofsongs.this, "Song added to running!", Toast.LENGTH_SHORT).show();
                }}); 
            o5.setOnClickListener(new Button.OnClickListener(){
                public void onClick(View v) {
                popupWindow.dismiss();
                Toast.makeText(listofsongs.this, "Song added to Driving!", Toast.LENGTH_SHORT).show();
                }}); 
            btnDismiss.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
            popupWindow.dismiss();
           
            }});  
            popupWindow.showAtLocation(popupView, Gravity.CENTER_HORIZONTAL, 0, 0);
        
  }});
            	
       musicConnection = new ServiceConnection(){
      	 
     	  @Override
     	  public void onServiceConnected(ComponentName name, IBinder service) {
     	    MusicBinder binder = (MusicBinder)service;
     	    //get service
     	    musicSrv = binder.getService();
     	    //pass list
     	    musicSrv.setList(songList);
     	    musicBound = true;
     	  }
     	 
     	  @Override
     	  public void onServiceDisconnected(ComponentName name) {
     	    musicBound = false;
     	  }
     	};     	
            	
            	
            	
            }
     /* ******************************  
    public void songPicked(View view){
    	  musicSrv.setSong(Integer.parseInt(view.getTag().toString()));
    	  musicSrv.playSong();
    	}
    	
    ********************************** */
    
    
    public void getSongList() {
    	ContentResolver musicResolver = getContentResolver();
    	Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    	Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);
    	if(musicCursor!=null && musicCursor.moveToFirst())
    	{
    		  int titleColumn = musicCursor.getColumnIndex
    		    (android.provider.MediaStore.Audio.Media.TITLE);
    		  int idColumn = musicCursor.getColumnIndex
    		    (android.provider.MediaStore.Audio.Media._ID);
    		  int artistColumn = musicCursor.getColumnIndex
    		    (android.provider.MediaStore.Audio.Media.ARTIST);
    		  do {
    		    long thisId = musicCursor.getLong(idColumn);
    		    String thisTitle = musicCursor.getString(titleColumn);
    		    String thisArtist = musicCursor.getString(artistColumn);
    		    songList.add(new Song(thisId, thisTitle, thisArtist));
    		  }
    		  while (musicCursor.moveToNext());
    		}
    	}
    protected void onStart() {
    	  super.onStart();
    	  if(playIntent==null){
    	    playIntent = new Intent(this, MusicService.class);
    	    bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
    	    startService(playIntent);
    	  }
    	}
    	}
