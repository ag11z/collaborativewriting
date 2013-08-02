package edu.cs.fsu.gibbens.collaborativewriting;


	import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

	public class Create extends Activity {
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        if (Main.black)
	        setContentView(R.layout.logintableb);
	        else
	        	setContentView(R.layout.logintable);
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
	    		
	    		
	    		EditText Username = (EditText) findViewById(R.id.username);  
	 		TextView username=(TextView) findViewById(R.id.F);
	    		String use=Username.getText().toString();
	    		if(use.matches("")){username.setTextColor(Color.RED);c=0;}
	    		else{username.setTextColor(Color.BLACK);}
	    		

	    		EditText Password = (EditText) findViewById(R.id.password); 
	 		TextView password=(TextView) findViewById(R.id.G);
	    		String pass=Password.getText().toString();
	    		if(pass.matches("")){password.setTextColor(Color.RED);c=0;}
	    		else{password.setTextColor(Color.BLACK);}	
	    		EditText PasswordCon = (EditText) findViewById(R.id.passwordconfirm); 
	    		TextView passwordCon=(TextView) findViewById(R.id.H);
	    		String passcon=PasswordCon.getText().toString();int b=0;
	    		if(!passcon.matches(pass)){passwordCon.setTextColor(Color.RED);c=0;
	    		Toast.makeText(Create.this, "Passwords do not match", Toast.LENGTH_SHORT).show();b=1;}
	    		else{passwordCon.setTextColor(Color.BLACK);}
	    		if(passcon.matches("")){passwordCon.setTextColor(Color.RED);c=0;}
	    		else{if(b!=1){passwordCon.setTextColor(Color.BLACK);}}	
	    		
	    	if(c==1)
	    	{
	    	
	    		ParseUser user = new ParseUser();
	    		user.setUsername(use);
	    		user.setPassword(pass);
	    		if (!Email.getText().toString().equals(""))
	    		user.setEmail(Email.getText().toString());
	    		 
	    		// other fields can be set just like with ParseObject
	    		
	    		 
	    		user.signUpInBackground(new SignUpCallback() {
	    		  public void done(ParseException e) {
	    		    if (e == null) {
	    		      // Hooray! Let them use the app now.
	    		    	finish();
	    		    } else {
	    		    	Toast.makeText(Create.this, "username is taken", Toast.LENGTH_SHORT).show();
	    		      // Sign up didn't succeed. Look at the ParseException
	    		      // to figure out what went wrong
	    		    }
	    		  }
	    		});
	      
	        Toast.makeText(Create.this, "Form info sent to database", Toast.LENGTH_SHORT).show();
	    	}
	    	
	    		
	    	
	    
	    		}});
	    
	}
	    
	    	

}
