package edu.cs.fsu.gibbens.collaborativewriting;


	import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

	public class Settings extends Activity {
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        if (Main.black)
	        setContentView(R.layout.settingb);
	        else
	        	setContentView(R.layout.setting);
	
	  Button SubmitButton = (Button) findViewById(R.id.button1);
	 

	    	
	    	
	    SubmitButton.setOnClickListener( new OnClickListener() {
	    	public void onClick(View v) {
 CheckBox x =(CheckBox)findViewById(R.id.hide);
if(x.isChecked())
	Main.hide=true;
else
	Main.hide=false;
RadioGroup x1 = (RadioGroup) findViewById(R.id.radioGroup1);
int type =x1.getCheckedRadioButtonId();
if (type == R.id.radio0)
{
	Main.black=true;
}
if(type == R.id.radio1)
{
	Main.black=false;
}
	    		 Intent returnIntent = new Intent();
			      returnIntent.putExtra("setting",true);
			      setResult(RESULT_OK,returnIntent);
			      finish();
			      }
	    
	    		});
	    
	}
	    
	    	

}
