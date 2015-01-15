package tiles;



import android.app.Activity; 
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cubes.musicwithstence.R;


public class main extends Activity {
	
	Button B1,B2,B3,B4,B5,map;
	TextView TV1,TV2,TV3,TV4,TV5;
	final Context context = this;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.look);
        B1 = (Button) findViewById(R.id.b1);
        

		B1.setOnClickListener(new Button.OnClickListener() {

			
			public void onClick(View v) {
				
				Toast.makeText(main.this, "Reading....!!", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(context, display.class);
                startActivity(intent);  

			}

		});
		B2 = (Button) findViewById(R.id.b2);
		

		B2.setOnClickListener(new Button.OnClickListener() {

			
			

			public void onClick(View v) {
				
				Toast.makeText(main.this, "Coding....!!", Toast.LENGTH_SHORT).show(); 

			}

		});
		B3 = (Button) findViewById(R.id.b3);
		

		B3.setOnClickListener(new Button.OnClickListener() {

			
			public void onClick(View v) {
				
				Toast.makeText(main.this, "Walking....!!", Toast.LENGTH_SHORT).show();
			

			}

		});
		B4 = (Button) findViewById(R.id.b4);
		

		B4.setOnClickListener(new Button.OnClickListener() {

			
			public void onClick(View v) {
				
				Toast.makeText(main.this, "Running....!!", Toast.LENGTH_SHORT).show();
			

			}

		});
		B5 = (Button) findViewById(R.id.b5);
		

		B5.setOnClickListener(new Button.OnClickListener() {

			
			public void onClick(View v) {
				
				Toast.makeText(main.this, "Driving....!!", Toast.LENGTH_SHORT).show();
			

			}

		});
		map = (Button) findViewById(R.id.map);
		map.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(main.this, "Mapping songs....!!", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(context, listofsongs.class);
                startActivity(intent);  

			}

		});
    }   
}
