package edu.cs.fsu.gibbens.collaborativewriting;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Newstory extends Activity {
int l; int random;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.story);
	    Button Submit = (Button) findViewById(R.id.submit);
		 
		Submit.setOnClickListener( new OnClickListener() {
			@Override
	    	public void onClick(View v) {
	    		
	    		
	    		EditText Story = (EditText) findViewById(R.id.story);
	    		String s =Story.getText().toString();
	    		EditText Name = (EditText) findViewById(R.id.name);
	    		String n =Name.getText().toString();
	    	
	    		RadioGroup x=(RadioGroup) findViewById(R.id.radioGroup1);
	    		if (s.equals("")||n.equals(""))
	    		{}
	    		else
	    		{
	    			 l=1;
	    			 ParseObject sStory = new ParseObject("Story");
	    			random= (int)(Math.random()*100);
	    			 
	    			    	sStory.put("StoryId", random);
		    			
	    			
	    			
	    			
	    			
	    					
	    			
	    			sStory.put("Name", n);
	    			sStory.put("entry", 0);
	    			sStory.put("Part", s);
	    			sStory.put("user", ParseUser.getCurrentUser().get("username").toString());
	    			
	    			
	    		int type =x.getCheckedRadioButtonId();
	    		if (type == R.id.radio0)
	    		{
	    			sStory.put("Personal", true);
	    			sStory.put("Delete", true);
	    		}
	    		if(type == R.id.radio1)
	    		{
	    			sStory.put("Personal", false);
	    			sStory.put("Delete", false);
	    		}
	    			
	    		if(type == R.id.radio2)
	    			{
	    			sStory.put("Personal", false);
	    			sStory.put("Delete", true);
	    			}
	    				
	    		sStory.saveInBackground();
	    		Toast.makeText(getBaseContext(), "Story submitted", Toast.LENGTH_LONG).show();
	    		finish();
	    		}
			}});
	    // TODO Auto-generated method stub
	}

}
