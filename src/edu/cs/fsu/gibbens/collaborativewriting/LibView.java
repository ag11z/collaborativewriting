package edu.cs.fsu.gibbens.collaborativewriting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class LibView extends Activity {
	static boolean Edit=false; static boolean type=false;
	HashMap<String, Object> map1;
	Cursor Cursorn;
	Cursor Cursorn2;
	private int listc;
	/** Called when the activity is first created. */
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    if (Main.black)
	    {
	    	setContentView(R.layout.viewstory);
	    	
	    }
	    else
	    {
	    setContentView(R.layout.viewstoryb);
	    
	    }
	    if (Main.black)
	    {
	    	  ListView l =(ListView) findViewById(R.id.entries);
	    	  l.setBackgroundColor(Color.BLACK);
	    	listc=R.layout.storyviewlistb;
	    	int[] colors = {0xffffffff, 0xffffffff, 0xffffffff}; // red for the example
	    	l.setDivider(new GradientDrawable(Orientation.RIGHT_LEFT, colors));
	    	l.setDividerHeight(1);
	    	 
	    	
	    }
	    else
	    	{
	    	listc=R.layout.storyviewlist;
	    	 ListView l =(ListView) findViewById(R.id.entries);
	    	  l.setBackgroundColor(Color.WHITE);
	    	
	    	int[] colors = {Color.BLACK, Color.BLACK, Color.BLACK}; // red for the example
	    	l.setDivider(new GradientDrawable(Orientation.RIGHT_LEFT, colors));
	    	l.setDividerHeight(1);
	    	 
	    	}
	    TextView n =(TextView) findViewById(R.id.name);
	    TextView a =(TextView) findViewById(R.id.author);
	  
	
	    Button r = (Button) findViewById(R.id.refresh);
	    r.setVisibility(View.VISIBLE);
	  
	   int id = getIntent().getExtras().getBundle("k").getInt("id"); 
	   
	  String view=getIntent().getExtras().getBundle("k").getString("name"); 
	  
	  
	  if (Edit){
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Story");
			 query.whereEqualTo("StoryId",id);
			 query.whereEqualTo("Name",view);
			 query.addDescendingOrder("entry");
			 query.addDescendingOrder("createdAt");
			 query.setLimit(1);
			 query.findInBackground(new FindCallback<ParseObject>() {

				@Override
				public void done(List<ParseObject> arg0,
						ParseException arg1) {
					Iterator<ParseObject> y =arg0.iterator();
					ParseObject m =y.next();
					if (m.getBoolean("Personal"))
					{if(!arg0.iterator().next().getString("user").equals(ParseUser.getCurrentUser().get("username").toString()))
		           	{
		           		SlidingDrawer drawer=(SlidingDrawer)findViewById(R.id.drawer);
		           	 drawer.setVisibility(View.GONE);
		           	}else
					{
						type=true;
					}}
					else
					{
						type=true;
					}
				
				
					
				}
				});}
	   String mSelectionClauses =  CwLibaray.STORY+" = ? AND "+ CwLibaray.NAME+" = ?";
       String[] mSelectionArgs = {""+id,view};

	   Cursorn=  getContentResolver().query(CwLibaray.CONTENT_URI,
         null, mSelectionClauses, mSelectionArgs ,CwLibaray.ENTRY+" ASC");
 String value = getIntent().getExtras().getBundle("k").getString("name");
a.setVisibility(View.GONE);
 n.setText(value);
 r.setOnClickListener( new OnClickListener() {
	 	public void onClick(View v) {
	 		 final String book = getIntent().getExtras().getBundle("k").getString("name");
	 		 final int id = getIntent().getExtras().getBundle("k").getInt("id"); 
	 		 ParseQuery<ParseObject> query = ParseQuery.getQuery("Story");
	    		 query.whereEqualTo("StoryId",id);
	    		 query.whereEqualTo("Name",book);
	    		 query.addAscendingOrder("entry");
	    		
	    		 query.findInBackground(new FindCallback<ParseObject>() {
	    			private ParseObject m;
				private Cursor mCursor;
				private Iterator<ParseObject> y;

				@Override
				public void done(
						java.util.List<ParseObject> objects,
						ParseException e) 
		    		 
	    			 {       	
					 String mSelectionClauses =  CwLibaray.STORY+" = ? AND "+ CwLibaray.NAME+" = ?";
	  		               String[] mSelectionArgs = {""+id,book};
		           
					  Cursor Cursorn2=  getContentResolver().query(CwLibaray.CONTENT_URI,
		                      null, mSelectionClauses, mSelectionArgs, null);
		             if (Cursorn2.getCount()==0)
		             { y=objects.iterator();for(int k=0; k<objects.size(); k++)
		    		        	 {  
		    		        	
		    		     		m=y.next();
		    		     		
		    		        	 ContentValues mNewValues=new ContentValues();
		    		       	  mNewValues.put(CwLibaray.NAME, m.getString("Name").toString().trim());
		    		              mNewValues.put(CwLibaray.ENTRY, k );
		    		              mNewValues.put(CwLibaray.STORY, m.getInt("StoryId"));
		    		              mNewValues.put(CwLibaray.User,m.getString("user").toString().trim());
		    		              mNewValues.put(CwLibaray.PART, m.getString("Part").toString().trim());
		    		              getApplication().getContentResolver().insert(
		    		             	CwLibaray.CONTENT_URI, mNewValues);
		    		              //mCursor=getContentResolver().query(CwLibaray.CONTENT_URI,null, null, null, null);
		    		        	
		    		              Toast.makeText(getBaseContext(), "Stored Story", Toast.LENGTH_LONG).show();
		    		        		 mCursor=getContentResolver().query(CwLibaray.CONTENT_URI,null, null, null, null);
		    		             }}
	    		 else
	    		 {
	    			Cursorn2.moveToFirst();
	    			Cursorn2.getString(5);
	    			 String mSelectionClause =  CwLibaray.STORY+" = ?";
		               String[] mSelectionArgs1 = {Cursorn2.getString(5)};

		               int mRowsDeleted = 0;

		               mRowsDeleted = getContentResolver().delete(
		               		CwLibaray.CONTENT_URI,
		                        mSelectionClause,
		                       mSelectionArgs1
		                     
		                       );
		               y=objects.iterator();
		          for(int k=0; k<objects.size(); k++)
        	 {  
        	
     		m=y.next();
     		
        	 ContentValues mNewValues=new ContentValues();
       	  mNewValues.put(CwLibaray.NAME, m.getString("Name").toString().trim());
              mNewValues.put(CwLibaray.ENTRY, k );
              mNewValues.put(CwLibaray.STORY, m.getInt("StoryId"));
              mNewValues.put(CwLibaray.User,m.getString("user").toString().trim());
              mNewValues.put(CwLibaray.PART, m.getString("Part").toString().trim());
              getApplication().getContentResolver().insert(
             	CwLibaray.CONTENT_URI, mNewValues);
              //mCursor=getContentResolver().query(CwLibaray.CONTENT_URI,null, null, null, null);
        	
        		
        		 mCursor=getContentResolver().query(CwLibaray.CONTENT_URI,null, null, null, null);
             }
		               Toast.makeText(getBaseContext(), "Stored Story", Toast.LENGTH_LONG).show();
	    		 }
	    			 
	    			 }});
	 	
	 	      Intent returnIntent = new Intent();
	 	     Bundle x=new Bundle();
	 	     x.putString("name", book);
	 	    x.putInt("id", id);
	 	    
	 	      returnIntent.putExtra("story", x);
	 	      setResult(RESULT_OK,returnIntent);       
	 	      finish();


	 	}});
 Button add =(Button) findViewById(R.id.add);
 add.setOnClickListener( new OnClickListener() {
 	public void onClick(View v) {
 		EditText Part=(EditText) findViewById(R.id.newentry);
 		String part =Part.getText().toString();
 		if(!part.equals("")){
 			 int id = getIntent().getExtras().getBundle("k").getInt("id"); 
 			 String view=getIntent().getExtras().getBundle("k").getString("name");
 			    	ParseQuery<ParseObject> query = ParseQuery.getQuery("Story");
			    		 query.whereEqualTo("StoryId",id);
			    		 query.whereEqualTo("Name",view);
			    		 query.addDescendingOrder("entry");
			    		 query.setLimit(1);
			    		 query.findInBackground(new FindCallback<ParseObject>() {

							@Override
							public void done(List<ParseObject> arg0,
									ParseException arg1) {
								 ParseObject sStory = new ParseObject("Story");
								 ParseObject M=arg0.iterator().next();
						 		 int id = getIntent().getExtras().getBundle("k").getInt("id"); 
						 			    	sStory.put("StoryId",id);
						 			    	EditText Part=(EditText) findViewById(R.id.newentry);
						 			 		String part =Part.getText().toString();
								// TODO Auto-generated method stub
								int entry =M.getInt("entry")+1;
				    			 String view=getIntent().getExtras().getBundle("k").getString("name");
								sStory.put("Name", view);
					 			sStory.put("entry", entry);
					 			sStory.put("Part", part);
					 			sStory.put("user", ParseUser.getCurrentUser().get("username").toString());    		
					 			sStory.put("Personal", M.getBoolean("Personal") );
					 			sStory.put("Delete", M.getBoolean("Delete"));
					 			
					 				
					 		sStory.saveInBackground();
					 		finish();
							}});
			    		 }
	    			
 			
 		
 		}
 	});
SlidingDrawer drawer=(SlidingDrawer)findViewById(R.id.drawer);
if (Main.login==false||!LibView.Edit||type)
	 {
		 SlidingDrawer drawer1=(SlidingDrawer)findViewById(R.id.drawer);
		 drawer1.setVisibility(View.GONE);
	 }
 drawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {
 @Override
 public void onDrawerOpened() {
 // TODO Auto-generated method stub
	   Button button=(Button)findViewById(R.id.handle);
	   button.setText("Hide");
 //button.setVisibility(View.GONE);
 }
 });
 drawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {
 @Override
 public void onDrawerClosed() {
 // TODO Auto-generated method stub
	   Button button=(Button)findViewById(R.id.handle);
	   button.setText("Add More");

 }
 });
	    
final ArrayList<HashMap<String, Object>> m_data = new ArrayList<HashMap<String, Object>>();


       
       	 for(int k=0; k<Cursorn.getCount(); k++)
       	 { if(!Cursorn.isLast())
       		Cursorn.moveToNext();
       String a1=Cursorn.getString(1);
    	 if (Main.hide)
       		 a1="";
       	map1 = new HashMap<String, Object>();
       	 map1.put("maintext", Cursorn.getString(3));
       	 map1.put("subtext", a1);        
       	 m_data.add(map1);
       	
        }
       	 
       
       	    for (HashMap<String, Object> m1 :m_data) //make data of this view should not be null (hide )
       	    	m1.put("checked", false);
       	 SimpleAdapter adapter = new SimpleAdapter(LibView.this,
       		        m_data,
       		                listc,
       		                new String[] {"maintext", "subtext"}, 
       		                new int[] {R.id.StoryName, R.id.by}); 
       		        
       		        adapter.setViewBinder(new ViewBinder()
       		{
       		        public boolean setViewValue(View view, Object data, String textRepresentation)
       		        {
       		if (data == null) //if 2nd line text is null, its textview should be hidden 
       		{
       		view.setVisibility(View.GONE);
       		return true;
       		}	
       		view.setVisibility(View.VISIBLE);
       		return false;
       		}	

       		});
       		     ListView l =(ListView) findViewById(R.id.entries);
       	 l.setAdapter(adapter);
       
     
       
        ListView l1 =(ListView) findViewById(R.id.entries);
       
            
       
            
            
        l1.setOnItemClickListener(new OnItemClickListener(){

            
            public void onItemClick(AdapterView<?> parent, View v, int p, long id) {
            Cursorn.moveToPosition(p);
            	Bundle mBundle = new Bundle();
            	mBundle.putString("user",Cursorn.getString(1));
            	Intent mIntent = new Intent(getBaseContext(), Profile.class);
        		mIntent.putExtra("k", mBundle);
        		startActivity(mIntent);
            	
            }});
	}
 
	   
	   
	   
	   
	    // TODO Auto-generated method stub
	
	
}
