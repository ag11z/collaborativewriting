package edu.cs.fsu.gibbens.collaborativewriting;

import android.R.bool;
import android.os.Bundle;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseUser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Main extends Activity {
static boolean login=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   
            Parse.initialize(this, "qFPogmgvOJTbkV2QNsIsUgqYlhtp1go6BApXM1m1", "9NlDvnUSqknpt8DTMpGZoqrBM3gEEBBfBIR8i8oq");
         ListView i = (ListView) findViewById(R.id.mainmenu);
         
    i.setOnItemClickListener(item);
        	
    }
    public  void logged()
    {ListView i = (ListView) findViewById(R.id.mainmenu);
	   String[] list = getResources().getStringArray(R.array.List2);
       //Row layout defined by Android: android.R.layout.simple_list_item_1
       i.setAdapter(new ArrayAdapter<String>(null, android.R.layout.simple_list_item_1, list));}
    private OnItemClickListener item = new OnItemClickListener(){

       
        public void onItemClick(AdapterView<?> parent, View v, int p, long id) {
 if (p==0)
 {
	if (login==false)
	{ //Toast.makeText(getBaseContext(), "login", Toast.LENGTH_LONG).show();

      
       Intent intObj = new Intent(Main.this,Login.class);
       Main.this.startActivityForResult(intObj, 1);
	}
 	
   if (login==true)
       {
       
	   Main.login=false;
	   
	   ParseUser.logOut();  
	   ListView i = (ListView) findViewById(R.id.mainmenu);
  	   String[] list = getResources().getStringArray(R.array.List);
         //Row layout defined by Android: android.R.layout.simple_list_item_1
         i.setAdapter(new ArrayAdapter<String>(Main.this , android.R.layout.simple_list_item_1, list));
    	   
       }
 }
       
      if (p==2)
      
      {
    	if (login==true)
    	
    	{
    		String x= (String )ParseUser.getCurrentUser().get("username");
    		//Toast.makeText(getBaseContext(), " "+x, Toast.LENGTH_LONG).show();
    		  Intent intObj = new Intent(Main.this,Newstory.class);
    	       startActivity(intObj);
    	} else
    	{
  		  Intent intObjx = new Intent(Main.this,Cstories.class);
	    		
	    		startActivity(intObjx);	   
          
    	}
    	  
      }  
      if (p==3)
      {
    	  if (login==false)
      	{
    		  
    		  Intent intObjx = new Intent(Main.this,PersonalStories.class);
	    		
	    		startActivity(intObjx);	   
      	}
    	  else
{
    		  Intent intObjx = new Intent(Main.this,Cstories.class);
	    		
	    		startActivity(intObjx);	   
            
      	}
      }
      if (p==4)
      {
    	  if (login==false)
      	{
    		  
              Intent intObjx = new Intent(Main.this,Allstories.class);
	    		
	    		startActivity(intObjx);	  
      	}
    	  else
{
    		  
              Intent intObjx = new Intent(Main.this,PersonalStories.class);
	    		
	    		startActivity(intObjx);	  
      	}
      }
      if (p==5)
      {
      	if (login==false)
      	{
            
	    }
      	else
      	{
  		  
            Intent intObjx = new Intent(Main.this,Allstories.class);
	    		
	    		startActivity(intObjx);	  
    	}	
      }
        if (p==6)
        {
        	if (login==false)
        	{
              
              Intent intObjx = new Intent(Main.this,Create.class);
	    		
	    		startActivity(intObjx);}
        }
        if (p==7)
        {
        	if (login==false)
        	{
             //Toast.makeText(getBaseContext(), "Forgot?", Toast.LENGTH_LONG).show();
              Intent intObjx = new Intent(Main.this,Forgot.class);
	    		
	    		startActivity(intObjx);}
        else
        {
        	 Intent intObjx = new Intent(Main.this,Change.class);
	    		
	    		startActivity(intObjx);
        }
        }
        } 
    }; 
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
 	    // Check which request it is that we're responding to
 	    if (requestCode == 1) {
 	        // Make sure the request was successful
 	        if (resultCode == RESULT_OK) {
 	        	ListView i = (ListView) findViewById(R.id.mainmenu);
 	     	   String[] list = getResources().getStringArray(R.array.List2);
 	            //Row layout defined by Android: android.R.layout.simple_list_item_1
 	            i.setAdapter(new ArrayAdapter<String>(Main.this , android.R.layout.simple_list_item_1, list));
 	           
 	        	
 	        }}};
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
