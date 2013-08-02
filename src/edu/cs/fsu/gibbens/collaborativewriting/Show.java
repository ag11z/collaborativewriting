package edu.cs.fsu.gibbens.collaborativewriting;

import java.util.ArrayList;





import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Show extends Activity {
	Cursor mCursor;
	static String ids;
	  String a="ID | First  |   Last |   Phone# |   Email | Username|   Password |  Gender | Country";
	  final Context context = this;
	ArrayList<String> listItems=new ArrayList<String>();
	ArrayAdapter<String> adapter;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.row);
	   // adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listItems);
	 //   ListView listy=new ListView(this);
	 mCursor = getContentResolver().query(CwLibaray.CONTENT_URI,null, null, null, null);
	//  adapter.add(a);
	 TextView id = (TextView) findViewById(R.id.textView1);
	 TextView first = (TextView) findViewById(R.id.textView2);
   TextView last = (TextView) findViewById(R.id.textView3);
   TextView phone = (TextView) findViewById(R.id.textView4);
   TextView email = (TextView) findViewById(R.id.textView5);
   TextView user = (TextView) findViewById(R.id.textView6);
   TextView pas = (TextView) findViewById(R.id.textView7);
   TextView gender = (TextView) findViewById(R.id.textView8);
   TextView con = (TextView) findViewById(R.id.textView9);
	if(mCursor != null) {
      if(mCursor.getCount() > 0) {
          //  while (mCursor.moveToNext()){
            
    	  mCursor.moveToNext();
       ids =  mCursor.getString(0);
       id.setText(ids);
       first.setText(mCursor.getString(1));
       last.setText(mCursor.getString(2));
       phone.setText(mCursor.getString(3));
       email.setText(mCursor.getString(4));
       
    
   
          }
            
    	  }

	Button next =(Button) findViewById(R.id.next);    
	next.setOnClickListener( new OnClickListener() {
    	public void onClick(View v) {
 		if(mCursor != null) {
    		      if(mCursor.getCount() > 0) {
    		          //  while (mCursor.moveToNext()){
    		    	   if(!mCursor.moveToNext()){
                           mCursor.moveToFirst();
                       }
    		    		 TextView id = (TextView) findViewById(R.id.textView1);
    		    		 TextView first = (TextView) findViewById(R.id.textView2);
    		    	   TextView last = (TextView) findViewById(R.id.textView3);
    		    	   TextView phone = (TextView) findViewById(R.id.textView4);
    		    	   TextView email = (TextView) findViewById(R.id.textView5);
    		    	   TextView user = (TextView) findViewById(R.id.textView6);
    		    	   TextView pas = (TextView) findViewById(R.id.textView7);
    		    	   TextView gender = (TextView) findViewById(R.id.textView8);
    		    	   TextView con = (TextView) findViewById(R.id.textView9);
    		       ids =  mCursor.getString(0);
    		       id.setText(ids);
    		       first.setText(mCursor.getString(1));
    		       last.setText(mCursor.getString(2));
    		       phone.setText(mCursor.getString(3));
    		       email.setText(mCursor.getString(4));
    		   
    		      }
    		      }    	}});
	Button Go =(Button) findViewById(R.id.Go);    
Go.setOnClickListener( new OnClickListener() {
    	public void onClick(View v) {
    		EditText g =(EditText) findViewById(R.id.go);
    		int x=0;
    		if (!g.getText().toString().equals(null));
    		 String mSelectionClauses =  "_ID = ?";
               String[] mSelectionArgs = {g.getText().toString()};
             Cursor Cursorn=  getContentResolver().query(CwLibaray.CONTENT_URI,
                      null, mSelectionClauses, mSelectionArgs, null);
    		if (Cursorn!=null){if(mCursor != null) {
    		      if(mCursor.getCount() > 0) {
    		          while (mCursor.moveToNext()){
    		    	 
    	                   if(g.getText().toString().equals(mCursor.getString(0)))
    	                   {
    		    		 TextView id = (TextView) findViewById(R.id.textView1);
    		    		 TextView first = (TextView) findViewById(R.id.textView2);
    		    	   TextView last = (TextView) findViewById(R.id.textView3);
    		    	   TextView phone = (TextView) findViewById(R.id.textView4);
    		    	   TextView email = (TextView) findViewById(R.id.textView5);
    		    	   TextView user = (TextView) findViewById(R.id.textView6);
    		    	   TextView pas = (TextView) findViewById(R.id.textView7);
    		    	   TextView gender = (TextView) findViewById(R.id.textView8);
    		    	   TextView con = (TextView) findViewById(R.id.textView9);
    		      
    		       id.setText(mCursor.getString(0));
    		       first.setText(mCursor.getString(1));
    		       last.setText(mCursor.getString(2));
    		       phone.setText(mCursor.getString(3));
    		       email.setText(mCursor.getString(4));
    		       user.setText(mCursor.getString(5));
    		       pas.setText(mCursor.getString(6));
    		       gender.setText(mCursor.getString(7));
    		       con.setText(mCursor.getString(8));
    		       
    		       x=1;
    		       break;
    		          }}
 if (x==0)
	 {
         mCursor.moveToFirst();
         TextView id = (TextView) findViewById(R.id.textView1);
		 TextView first = (TextView) findViewById(R.id.textView2);
	   TextView last = (TextView) findViewById(R.id.textView3);
	   TextView phone = (TextView) findViewById(R.id.textView4);
	   TextView email = (TextView) findViewById(R.id.textView5);
	   TextView user = (TextView) findViewById(R.id.textView6);
	   TextView pas = (TextView) findViewById(R.id.textView7);
	   TextView gender = (TextView) findViewById(R.id.textView8);
	   TextView con = (TextView) findViewById(R.id.textView9);
   ids =  mCursor.getString(0);
   id.setText(ids);
   first.setText(mCursor.getString(1));
   last.setText(mCursor.getString(2));
   phone.setText(mCursor.getString(3));
   email.setText(mCursor.getString(4));
 
   id.setText(ids);
     } 
    	                   }
    		         
    		}
    	}}});
	Button pre =(Button) findViewById(R.id.Pre);    
	pre.setOnClickListener( new OnClickListener() {
    	public void onClick(View v) {
    		if(mCursor != null) {
    		      if(mCursor.getCount() > 0) {
    		          //  while (mCursor.moveToNext()){
    		    	 
    	                    if(!mCursor.moveToPrevious()){
    	                        mCursor.moveToLast();
    	                    }

    		    		 TextView id = (TextView) findViewById(R.id.textView1);
    		    		 TextView first = (TextView) findViewById(R.id.textView2);
    		    	   TextView last = (TextView) findViewById(R.id.textView3);
    		    	   TextView phone = (TextView) findViewById(R.id.textView4);
    		    	   TextView email = (TextView) findViewById(R.id.textView5);
    		    	   TextView user = (TextView) findViewById(R.id.textView6);
    		    	   TextView pas = (TextView) findViewById(R.id.textView7);
    		    	   TextView gender = (TextView) findViewById(R.id.textView8);
    		    	   TextView con = (TextView) findViewById(R.id.textView9);
    		       ids =  mCursor.getString(0);
    		       id.setText(ids);
    		       first.setText(mCursor.getString(1));
    		       last.setText(mCursor.getString(2));
    		       phone.setText(mCursor.getString(3));
    		       email.setText(mCursor.getString(4));
    		      
    		          }
	
    	}}});
			Button del =(Button) findViewById(R.id.delete);
		del.setOnClickListener( new OnClickListener() {
	    	public void onClick(View v) {
	    		if(mCursor != null) {
	    			if(mCursor.getCount()>0){
	    			  Show.ids =  mCursor.getString(0);
	    		      
	    	  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	  				context);
	    	//String x=adapter.getItem(0);
				
	  			// set title
	  			alertDialogBuilder.setTitle("Do you want to delete ID "+ids+"  ?");
	   
	  			// set dialog message
	  			alertDialogBuilder
	  				.setMessage("Click yes to delete!")
	  				.setCancelable(false)
	  				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
	  					public void onClick(DialogInterface dialog,int id) {
	  						//if this button is clicked, close
	  						//current activity
	  					//String x=adapter.getItem(myItemInt);
	  				
	  						 String mSelectionClause =  "_ID = ?";
	  		               String[] mSelectionArgs = {Show.ids};

	  		               int mRowsDeleted = 0;

	  		                mRowsDeleted = getContentResolver().delete(
	  		                		CwLibaray.CONTENT_URI,
	  		                        mSelectionClause,
	  		                       mSelectionArgs
	  		                     
	  		                       );
	  		           
	  		            TextView ida = (TextView) findViewById(R.id.textView1);
	  		     	 TextView first = (TextView) findViewById(R.id.textView2);
	  		        TextView last = (TextView) findViewById(R.id.textView3);
	  		        TextView phone = (TextView) findViewById(R.id.textView4);
	  		        TextView email = (TextView) findViewById(R.id.textView5);
	  		        TextView user = (TextView) findViewById(R.id.textView6);
	  		        TextView pas = (TextView) findViewById(R.id.textView7);
	  		        TextView gender = (TextView) findViewById(R.id.textView8);
	  		        TextView con = (TextView) findViewById(R.id.textView9);

	  		            ida.setText("");
	  		            first.setText("");
	  		            last.setText("");
	  		            phone.setText("");
	  		            email.setText("");
	  		            user.setText("");
	  		            pas.setText("");
	  		            gender.setText("");
	  		            con.setText("");
	  		         
	  		          mCursor = getContentResolver().query(CwLibaray.CONTENT_URI,null, null, null, null);
	  		        if (mCursor!=null)
	  		          {
	  		        	 if(mCursor.getCount() == 0){
	  		        		
	  		        	Intent intObjv = new Intent(Show.this,Main.class);
	    	    		//intObjv.addFlags(BIND_AUTO_CREATE);
	    	    		startActivity(intObjv); 
	    	    		
	  		          }}else{Intent intObjv = new Intent(Show.this,Main.class);
	    	    		//intObjv.addFlags(BIND_AUTO_CREATE);
	    	    		startActivity(intObjv); 
	    	    		}
	  						//adapter.remove(adapter.getItem(myItemInt));
	  						
	  					
	  					dialog.cancel();
	  					}				  })
	  				.setNegativeButton("No",new DialogInterface.OnClickListener() {
	  					public void onClick(DialogInterface dialog,int id) {
	  						// if this button is clicked, just close
	  						// the dialog box and do nothing
	  						dialog.cancel();
	  					}
	  				});
	   
	  				// create alert dialog
	  				AlertDialog alertDialog = alertDialogBuilder.create();
	   
	  				// show it
	  				alertDialog.show();
	     
	    			}
	    		}
	    	}       
	});
	    // TODO Auto-generated method stub
	 

	
	
	}	                                                      
}
