package edu.cs.fsu.gibbens.collaborativewriting;

import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Change extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    if(Main.black)
	    	  setContentView(R.layout.changeb);
	    else
	    setContentView(R.layout.change1);
	    Button ClearButton = (Button) findViewById(R.id.clear);
		  Button SubmitButton = (Button) findViewById(R.id.submit);
		 

		    	ClearButton.setOnClickListener( new OnClickListener() {
		    	public void onClick(View v) {
		    		TextView username=(TextView) findViewById(R.id.F);
		    		username.setTextColor(Color.BLACK);
		    		TextView password=(TextView) findViewById(R.id.G);
		    		password.setTextColor(Color.BLACK);
		    		TextView passwordCon=(TextView) findViewById(R.id.H);
		    		passwordCon.setTextColor(Color.BLACK);
		    		
		    		EditText Email = (EditText) findViewById(R.id.email);
		    		Email.setText("");
		    		EditText Username = (EditText) findViewById(R.id.username);
		    		Username.setText("");
		    		EditText Password = (EditText) findViewById(R.id.password);
		    		Password.setText("");
		    		EditText PasswordCon = (EditText) findViewById(R.id.passwordconfirm);
		    		PasswordCon.setText("");

		    	}
		    		});
		    	
		    SubmitButton.setOnClickListener( new OnClickListener() {
		    	public void onClick(View v) {

		    		int c =1;
		    		
		    	
		    		EditText Email = (EditText) findViewById(R.id.email);
		    		
		    		
		    		
		 		
		    		

		    		EditText Password = (EditText) findViewById(R.id.password); 
		 		TextView password=(TextView) findViewById(R.id.G);
		    		String pass=Password.getText().toString();
		    		if(pass.matches("")){}
		    		else{password.setTextColor(Color.BLACK);}	
		    		
		    		EditText PasswordCon = (EditText) findViewById(R.id.passwordconfirm); 
		    		TextView passwordCon=(TextView) findViewById(R.id.H);
		    		
		    		String passcon=PasswordCon.getText().toString();int b=0;
		    		if(!passcon.matches(pass)){passwordCon.setTextColor(Color.RED);c=0;
		    		Toast.makeText(Change.this, "Passwords do not match", Toast.LENGTH_SHORT).show();b=1;}
		    		else{passwordCon.setTextColor(Color.BLACK);}
		    		if(passcon.matches("")){}
		    		else{if(b!=1){passwordCon.setTextColor(Color.BLACK);}}	
		    		
		    	if(c==1)
		    	{
		    		if (!passcon.matches(""))
		    		ParseUser.getCurrentUser().setPassword(passcon);
		    		ParseUser.getCurrentUser().saveInBackground();
		    		if (!Email.getText().toString().matches(""))
		    		ParseUser.getCurrentUser().setEmail(Email.getText().toString());
		    		ParseUser.getCurrentUser().saveInBackground();
		    		
		    		 EditText Aboutme = (EditText) findViewById(R.id.aboutme);  
		    		if(!Aboutme.getText().toString().equals(""))
		    		{
		    			String value =ParseUser.getCurrentUser().getString("username");
		    			 ParseQuery<ParseObject> query = ParseQuery.getQuery("AboutMe");
		 	    	    query.whereEqualTo("user", value);
		 	    	    query.findInBackground(new FindCallback<ParseObject>() {
		 	    	   	 @Override
		 	    	        public void done(List<ParseObject> List, ParseException e) {
		 	    	   		if (e == null) {
		 	    	   			if(List.iterator().hasNext())
		 	    	   			{ParseObject n= List.iterator().next();
		 	    	   		
		 	    	 	   
		 	    	 	    
		 	    	 	    n.deleteInBackground();
		 	    	 	    
		 	    	   			}
		 	    	   		ParseObject sStory = new ParseObject("AboutMe");
			    			
			    			
			    			
			    			
			    			
		 	    	   	 EditText Aboutme = (EditText) findViewById(R.id.aboutme);  
			    			
			    			sStory.put("AboutMe", Aboutme.getText().toString());
			    			
			    			sStory.put("user", ParseUser.getCurrentUser().get("username").toString());
			    		
			    				
			    		sStory.saveInBackground();
		 	    	   		}
		 	    	   		}});
		    		
		 	    	   
		    		}
		    		Toast.makeText(getBaseContext(), "Changed info", Toast.LENGTH_SHORT).show();
			        finish();
		    		// other fields can be set just like with ParseObject
		    		
		    		 
		    		
		      
		       
		    	}
		    	
		    		
		    	
		    
		    		}});
		    
	    // TODO Auto-generated method stub
	}

}
