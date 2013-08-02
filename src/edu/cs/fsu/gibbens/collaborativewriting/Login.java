package edu.cs.fsu.gibbens.collaborativewriting;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Login extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    if (Main.black)
	    	 setContentView(R.layout.tableb);	
	    else
	    setContentView(R.layout.table);
	    Button Submit = (Button) findViewById(R.id.submit);
		 
	Submit.setOnClickListener( new OnClickListener() {
		@Override
    	public void onClick(View v) {
    		
    		
    		EditText Username = (EditText) findViewById(R.id.username);
    		
    		EditText Password = (EditText) findViewById(R.id.password);
    		ParseUser.logInInBackground(Username.getText().toString(), Password.getText().toString(), new LogInCallback() {

				@Override
				public void done(ParseUser user, ParseException e) {
					// TODO Auto-generated method stub
					   if (user != null) {
						   Toast.makeText(Login.this,"Logged in",Toast.LENGTH_LONG).show();
						   Main.login=true;
						   
						  
						   Intent returnIntent = new Intent();
						      returnIntent.putExtra("login",true);
						      setResult(RESULT_OK,returnIntent);
						   finish();
		    			    } else {
		    			    Toast.makeText(getBaseContext(), "Username or password is incorrect.", Toast.LENGTH_LONG).show();
		    			      // Signup failed. Look at the ParseException to see what happened.
		    			    }
				}
    			});
    		
    	}});
	    // TODO Auto-generated method stub
	}

}
