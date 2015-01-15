package tiles;


import java.util.List; 

import com.cubes.musicwithstence.R;



import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class display extends Activity{

	DataBaseAdapter nadba;
	  
	private MusicService musicSrv;

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dis);
		nadba=new DataBaseAdapter(this);
		nadba=nadba.open();
		
		final Context context = this;
		
		
		final ListView lv2 = (ListView) findViewById(R.id.lv2);
        
        List<String> namegiven=nadba.getAllLabels();
        
        lv2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, namegiven));
        
     
        lv2.setOnItemClickListener(new OnItemClickListener() {
       	 
       	  @SuppressWarnings("deprecation")
		public void onItemClick(AdapterView<?> parent, View view,
       	    int position, long id) {
       		  int pos=position;
       		 final  String poss=Integer.toString(pos);
       		  int send=pos+1;
       		  int id1 = nadba.getsong(send);
       		  int id2=id1+1;
       	   Toast.makeText(display.this, "I am here!" + id2, Toast.LENGTH_SHORT).show(); 
       	 musicSrv.setSong(id2);
   	  // musicSrv.playSong();
       	  }
       	});  
        
}
	
	
	
	
	

	
	
	
	

protected void onDestroy() {
		
		super.onDestroy();
		
		nadba.close();
	}


	
	

}