package edu.cs.fsu.gibbens.collaborativewriting;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Forgot extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    if (Main.black)
	        setContentView(R.layout.forgotb);
	    else
	    setContentView(R.layout.forgot);
	    Button Submit = (Button) findViewById(R.id.submit1);
		 
		Submit.setOnClickListener( new OnClickListener() {
			@Override
	    	public void onClick(View v) {
	    		
	    		
	    		final EditText email = (EditText) findViewById(R.id.email);
	    		ParseUser.requestPasswordResetInBackground(email.getText().toString(),
	    				
                        new RequestPasswordResetCallback() {
	    			@Override
	    			public void done(ParseException e) {
	    				if (e == null) {
	    					Toast.makeText(getBaseContext(), "Email sent to "+email.getText().toString(), Toast.LENGTH_LONG).show();
	    					finish();
	    				} else {
	    					Toast.makeText(getBaseContext(), "Email address not recoginzed", Toast.LENGTH_LONG).show();
	    				}
	    			}

				
	    		});
	    		
	    		
	    		
			}});
	    // TODO Auto-generated method stub
	}

}
