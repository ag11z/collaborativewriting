package edu.cs.fsu.gibbens.collaborativewriting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.ListActivity;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.SimpleAdapter.ViewBinder;

public class LibarayList extends ListActivity  {
	HashMap<String, Object> map1;
	Iterator<ParseObject> x;
  Cursor Cursorn;

	
	/** Called when the activity is first created. */
	private java.util.List<? extends Map<String, ?>> m_data;
	private int listc;
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    if (Main.black)
	    {
	    	  ListView l = getListView();
	    	  l.setBackgroundColor(Color.BLACK);
	    listc = R.layout.storylistb;
	    	int[] colors = {0xffffffff, 0xffffffff, 0xffffffff}; // red for the example
	    	l.setDivider(new GradientDrawable(Orientation.RIGHT_LEFT, colors));
	    	l.setDividerHeight(1);
	    	 
	    	
	    }
	    else
	    	{
	    	listc=R.layout.storylist;
	    	  ListView l = getListView();
	    	  l.setBackgroundColor(Color.WHITE);
	    	
	    	int[] colors = {Color.BLACK, Color.BLACK, Color.BLACK}; // red for the example
	    	l.setDivider(new GradientDrawable(Orientation.RIGHT_LEFT, colors));
	    	l.setDividerHeight(1);
	    	 
	    	}
	    
 final ArrayList<HashMap<String, Object>> m_data = new ArrayList<HashMap<String, Object>>();
// Cursor Cursorn;
 String s;
 int x=0;
String mSelectionClauses =  CwLibaray.ENTRY+" = ?";
      String[] mSelectionArgs = {""+x};


 Cursorn=  getContentResolver().query(CwLibaray.CONTENT_URI,
        null, mSelectionClauses, mSelectionArgs, null);
map1 = new HashMap<String, Object>();
	
if (Cursorn!=null)
        	 for(int k=0; k<Cursorn.getCount(); k++)
        	 {
        		 Cursorn.moveToNext();
        		 s=	 ""
        		+Cursorn.getString(2);
        		String a ="";
        		// m=x.next();
        	///(String) m.get("Name");
        //	Boolean p=(Boolean) m.get("Personal");
        	
        	// if (p==true)
        	//	a="By : "+(String) m.get("user"); 
        	// else
        		// a="";
        	map1 = new HashMap<String, Object>();
        	 map1.put("maintext", s);
        	 map1.put("subtext", a);        
        	 m_data.add(map1);
        	 }
        	 
        	    for (HashMap<String, Object> m1 :m_data) //make data of this view should not be null (hide )
        	    	m1.put("checked", false);
        	 SimpleAdapter adapter = new SimpleAdapter(LibarayList.this,
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
        	 setListAdapter(adapter);
        	 
       
         ListView l = getListView(); 
     
     l.setLongClickable(true);
        l.setOnItemLongClickListener(new OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                    int p, long arg3) {
            	//Toast.makeText(getBaseContext(), "Error failed to delete", Toast.LENGTH_LONG).show();
	    			 final int x=p;
	    			 if( Cursorn.getCount()>=1){
	    		
	    				//Toast.makeText(getBaseContext(), "Error failed to delete", Toast.LENGTH_LONG).show();
	    				ListView l =(ListView) findViewById(R.id.entries);
	    				;
	    				PopupMenu popup = new PopupMenu(LibarayList.this, arg1);
	    				popup.getMenuInflater().inflate(R.menu.popup4, popup.getMenu());
	    				
	    				popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
	    					private Cursor mCursor;

							public boolean onMenuItemClick(MenuItem item) {
	    						
	    						if (item.getTitle().equals("View author"))
	    						{
	    							
	    							Bundle mBundle = new Bundle();
	    			            	mBundle.putString("user",Cursorn.getString(1));
	    			            	Intent mIntent = new Intent(getBaseContext(), Profile.class);
	    			        		mIntent.putExtra("k", mBundle);
	    			        		startActivity(mIntent);
	    						}
	    						if (item.getTitle().equals("View first author"))
	    						{
	    							
	    							Bundle mBundle = new Bundle();
	    			            	mBundle.putString("user",Cursorn.getString(1));
	    			            	Intent mIntent = new Intent(getBaseContext(), Profile.class);
	    			        		mIntent.putExtra("k", mBundle);
	    			        		startActivity(mIntent);
	    						}
	    						if (item.getTitle().equals("Refresh story"))
	    						{
	    							Toast.makeText(LibarayList.this,
		    								"You want to store but it is not implemented yet =( ",
		    								Toast.LENGTH_SHORT).show();
	    							Cursorn.moveToPosition(x);
	    							 ParseQuery<ParseObject> query = ParseQuery.getQuery("Story");
	   					    		 query.whereEqualTo("StoryId",Cursorn.getInt(5));
	   					    		 query.whereEqualTo("Name",Cursorn.getString(2));
	   					    		 query.addAscendingOrder("entry");
	   					    		 query.addAscendingOrder("createdAt");
	   					    		 query.findInBackground(new FindCallback<ParseObject>() {
	   					    			private ParseObject m;

										@Override
										public void done(
												java.util.List<ParseObject> objects,
												ParseException e) 
		    					    		 
	   					    			 {     
											 Iterator<ParseObject> y=objects.iterator();
	    						String mSelectionClause =  CwLibaray.STORY+" = ?";
	   			  		               String[] mSelectionArgs1 = {Cursorn.getString(5)};

	   			  		               int mRowsDeleted = 0;

	   			  		               mRowsDeleted = getContentResolver().delete(
	   			  		               		CwLibaray.CONTENT_URI,
	   			  		                        mSelectionClause,
	   			  		                       mSelectionArgs1
	   			  		                     
	   			  		                       );
	   			  		         
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
	   					    			 }});}
	   					    		 
	    						return true;
	    					}
	    				});

	    				popup.show();
		  			
	  				
		  				
	    			}
            
	  			return true;
            
        
        
            }});
	        
	    
	      //end init data
	        
	        
            
	       
	        
	        
	        final SimpleAdapter adapter1 = new SimpleAdapter(LibarayList.this,
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
	        setListAdapter(adapter1);

	        getListView().setOnItemClickListener(new OnItemClickListener(){

	            
	            public void onItemClick(AdapterView<?> parent, View v, int p, long id) {
	            	
	    	    	 
	            	
	            	Bundle mBundle = new Bundle();
	            	Cursorn.moveToPosition(p);
	            
	            	String user=Cursorn.getString(1);
	            	String name=Cursorn.getString(2);
	            	//Toast.makeText(getBaseContext(), name, Toast.LENGTH_LONG).show();
	            	//int id1=(Integer) m.get("StoryId");
	            	mBundle.putInt("id",Cursorn.getInt(5) );
	            	mBundle.putString("user",user);
	            	mBundle.putString("name", name);
	            	
	            	
	            	
	            		Intent mIntent = new Intent(getBaseContext(), LibView.class);
	            		mIntent.putExtra("k", mBundle);
	            		LibarayList.this.startActivityForResult(mIntent, 2);
	            	
	            	
	            	
	            }});
	    			 
	}
	 @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
  	    // Check which request it is that we're responding to
  	    if (requestCode == 2) {
  	        // Make sure the request was successful
  	        if (resultCode == RESULT_OK) {
  	        	//Toast.makeText(getBaseContext(), ""+, Toast.LENGTH_LONG).show();
  	        //Toast.makeText(getBaseContext(), ""+, Toast.LENGTH_LONG).show();
  	         LibView.Edit=true;
  	       Bundle mBundle = new Bundle();
       	
       
       	String name=data.getBundleExtra("story").getString("name");
       	//Toast.makeText(getBaseContext(), name, Toast.LENGTH_LONG).show();
       	//int id1=(Integer) m.get("StoryId");
       	mBundle.putInt("id",data.getBundleExtra("story").getInt("id"));
       	
       	mBundle.putString("name", name);
       	
       	
       	
       		Intent mIntent = new Intent(getBaseContext(), LibView.class);
       		mIntent.putExtra("k", mBundle);
       		LibarayList.this.startActivityForResult(mIntent, 2);
       		
  	        }}}
}
	
	    			 
           
  
 
	    			
    
