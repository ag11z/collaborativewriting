package edu.cs.fsu.gibbens.collaborativewriting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
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

public class Cstories extends ListActivity  {
	HashMap<String, Object> map1;
	Iterator<ParseObject> x;
	protected List<ParseObject> List;
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
 final ArrayList<HashMap<String, Object>> m_data = new ArrayList<HashMap<String, Object>>();
 
 ParseQuery<ParseObject> query = ParseQuery.getQuery("Story");
 query.whereEqualTo("entry", 0);
 query.whereEqualTo("Personal", false);
 query.findInBackground(new FindCallback<ParseObject>() {
	 @Override
     public void done(List<ParseObject> scoreList, ParseException e) {
         if (e == null) {
        	 ParseObject m;
        	 List=scoreList;
        	x=scoreList.iterator();
        	
        	 String s;
        	 String a;
        	 for(int k=0; k<scoreList.size(); k++)
        	 {
        		 m=x.next();
        	s=(String) m.get("Name");
        	Boolean p=(Boolean) m.get("Personal");
        	
        	 if (p==true)
        		a="By : "+(String) m.get("user"); 
        	 else
        		 a="";
        	map1 = new HashMap<String, Object>();
        	 map1.put("maintext", s);
        	 map1.put("subtext", a);        
        	 m_data.add(map1);
         }
        	 x=scoreList.iterator();
        	    for (HashMap<String, Object> m1 :m_data) //make data of this view should not be null (hide )
        	    	m1.put("checked", false);
        	 SimpleAdapter adapter = new SimpleAdapter(Cstories.this,
        		        m_data,
        		                R.layout.storylist,
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
             Log.i("score", "Retrieved "  + " scores");
         } else {
             Log.i("score", "Error: " + e.getMessage());
             Toast.makeText(getBaseContext(), "Need internet connection", Toast.LENGTH_LONG).show();
         }
         ListView l = getListView(); 
     
     l.setLongClickable(true);
        l.setOnItemLongClickListener(new OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                    int p, long arg3) {
            	//Toast.makeText(getBaseContext(), "Error failed to delete", Toast.LENGTH_LONG).show();
	    			 final int x=p;
	    			 if(List.size()>=2){
	    		
	    				//Toast.makeText(getBaseContext(), "Error failed to delete", Toast.LENGTH_LONG).show();
	    				ListView l =(ListView) findViewById(R.id.entries);
	    				;
	    				PopupMenu popup = new PopupMenu(Cstories.this, arg1);
	    				popup.getMenuInflater().inflate(R.menu.popup3, popup.getMenu());
	    				
	    				popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
	    					public boolean onMenuItemClick(MenuItem item) {
	    						
	    						if (item.getTitle().equals("View author"))
	    						{
	    							
	    							Bundle mBundle = new Bundle();
	    			            	mBundle.putString("user",List.get(x).getString("user"));
	    			            	Intent mIntent = new Intent(getBaseContext(), Profile.class);
	    			        		mIntent.putExtra("k", mBundle);
	    			        		startActivity(mIntent);
	    						}
	    						if (item.getTitle().equals("View first author"))
	    						{
	    							
	    							Bundle mBundle = new Bundle();
	    			            	mBundle.putString("user",List.get(x).getString("user"));
	    			            	Intent mIntent = new Intent(getBaseContext(), Profile.class);
	    			        		mIntent.putExtra("k", mBundle);
	    			        		startActivity(mIntent);
	    						}
	    						if (item.getTitle().equals("Store story"))
	    						{
	    							Toast.makeText(Cstories.this,
		    								"You want to store but it is not implemented yet =( ",
		    								Toast.LENGTH_SHORT).show();
	    						}
	    						return true;
	    					}
	    				});

	    				popup.show();
		  			
	  				
		  				
	    			}
	    		
	  			return true;
	  			}

        }
);   
	 }
	
 });
        
        
        
       
	        
	    
	      //end init data
	        
	        
	        
	       
	        
	        
	        final SimpleAdapter adapter = new SimpleAdapter(Cstories.this,
	        m_data,
	                R.layout.storylist,
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

	        getListView().setOnItemClickListener(new OnItemClickListener(){

	            
	            public void onItemClick(AdapterView<?> parent, View v, int p, long id) {
	            	
	    	    	 
	            	
	            	Bundle mBundle = new Bundle();
	            	ParseObject m = List.get(p);
	            	
	            	String user=(String) m.get("user");
	            	String name=(String) m.get("Name");
	            	//Toast.makeText(getBaseContext(), name, Toast.LENGTH_LONG).show();
	            	int id1=(Integer) m.get("StoryId");
	            	mBundle.putInt("id",id1 );
	            	mBundle.putString("user",user);
	            	mBundle.putString("name", name);
	            	
	            	Boolean p1=(Boolean) m.get("Personal");
	            	if (!p1)
	            	{
	            		Intent mIntent = new Intent(getBaseContext(), ViewCollabative.class);
	            		mIntent.putExtra("k", mBundle);
	            		startActivity(mIntent);
	            	}
	            	else
	            	{
	            		Intent mIntent = new Intent(getBaseContext(), ViewPersonal.class);
	            		mIntent.putExtra("k", mBundle);
	            		startActivity(mIntent);
	            	}
	            	
	            	
	            }});
	
	}

  
 
	    			
    };
