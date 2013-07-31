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
import android.content.Intent;
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
@SuppressWarnings("deprecation")
public class ViewPersonal extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.viewstory);
	    TextView n =(TextView) findViewById(R.id.name);
	  
	    
	   String value = getIntent().getExtras().getBundle("k").getString("name");
	  
	   n.setText(value);
	  
	   int id = getIntent().getExtras().getBundle("k").getInt("id"); 
	   
	  SlidingDrawer drawer=(SlidingDrawer)findViewById(R.id.drawer);
	   
	   drawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {
	   @Override
	   public void onDrawerOpened() {
	   // TODO Auto-generated method stub
		   Button button=(Button)findViewById(R.id.handle);
	   button.setText("Close");
	   }
	   });
	   drawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {
	   @Override
	   public void onDrawerClosed() {
	   // TODO Auto-generated method stub
		   Button button=(Button)findViewById(R.id.handle);
	   button.setText("Edit");
	   }
	   });
	   
	    
final ArrayList<HashMap<String, Object>> m_data = new ArrayList<HashMap<String, Object>>();

ParseQuery<ParseObject> query = ParseQuery.getQuery("Story");
query.whereEqualTo("StoryId",id );
query.whereEqualTo("Name", value);
query.addAscendingOrder("entry");
query.addAscendingOrder("createdAt");
query.findInBackground(new FindCallback<ParseObject>() {
	 private Iterator<ParseObject> x;
	 HashMap<String, Object> map1;
	 List<ParseObject> List;
	@Override
    public void done(List<ParseObject> scoreList, ParseException e) {
        if (e == null) {
       	 ParseObject m;
       	 List=scoreList;
       	ParseObject N=List.get(0);
        TextView author =(TextView) findViewById(R.id.author);
        author.setText("By: "+N.getString("user"));
        author.setOnClickListener(new OnClickListener(){

        	@Override
            public void onClick(View arg0) {
            	
            	Bundle mBundle = new Bundle();
            	mBundle.putString("user",List.get(0).getString("user"));
            	Intent mIntent = new Intent(getBaseContext(), Profile.class);
        		mIntent.putExtra("k", mBundle);
        		startActivity(mIntent);
            	
            }

        });
       	x=scoreList.iterator();
       	
       	 String s;
       	 String a;
       	 if (Main.login==false)
       	 {
       		
			SlidingDrawer drawer=(SlidingDrawer)findViewById(R.id.drawer);
       		 drawer.setVisibility(View.GONE);
       	 }
       	 else
       	 	if(!N.getString("user").equals(ParseUser.getCurrentUser().get("username").toString()))
           	{
           		SlidingDrawer drawer=(SlidingDrawer)findViewById(R.id.drawer);
           	 drawer.setVisibility(View.GONE);
           	}
       	 for(int k=0; k<scoreList.size(); k++)
       	 {
       		 m=x.next();
       	s=(String) m.get("Part");
       	Boolean p=(Boolean) m.get("Personal");
       	
       	 if (p==false)
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
       	 SimpleAdapter adapter = new SimpleAdapter(ViewPersonal.this,
       		        m_data,
       		                R.layout.storyviewlist,
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
       		    				PopupMenu popup = new PopupMenu(ViewPersonal.this, arg1);
       		    				if (List.get(0).getBoolean("Delete")==true&&Main.login)
       		    	    		{   
       		    	    			if (List.get(0).getString("user").equals(ParseUser.getCurrentUser().getString("username"))){
       		    				popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
       		    	    			}else
       			    				{popup.getMenuInflater().inflate(R.menu.popup2, popup.getMenu());}}
       		    				else
       		    				{popup.getMenuInflater().inflate(R.menu.popup2, popup.getMenu());}
       		    				popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
       		    					public boolean onMenuItemClick(MenuItem item) {
       		    						
       		    						if (item.getTitle().equals("Delete"))
       		    						{
       		    							List.get(x).deleteInBackground();	
       		    							Toast.makeText(ViewPersonal.this,
       			    								"Deleted",
       			    								Toast.LENGTH_SHORT).show();
       		    							finish();
       		    						}
       		    						if (item.getTitle().equals("View author"))
       		    						{
       		    							
       		    							Bundle mBundle = new Bundle();
       		    			            	mBundle.putString("user",List.get(x).getString("user"));
       		    			            	Intent mIntent = new Intent(getBaseContext(), Profile.class);
       		    			        		mIntent.putExtra("k", mBundle);
       		    			        		startActivity(mIntent);
       		    						}
       		    						if (item.getTitle().equals("Store story"))
       		    						{
       		    							Toast.makeText(ViewPersonal.this,
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
       		    
       	 l.setAdapter(adapter);
            Log.i("score", "Retrieved "  + " scores");
        } else {
            Log.i("score", "Error: " + e.getMessage());
            Toast.makeText(getBaseContext(), "Need internet connection", Toast.LENGTH_LONG).show();
        }
        Button add =(Button) findViewById(R.id.add);
        add.setOnClickListener( new OnClickListener() {
        	public void onClick(View v) {
        		EditText Part=(EditText) findViewById(R.id.newentry);
        		String part =Part.getText().toString();
        		if(!part.equals("")){
        		 ParseObject sStory = new ParseObject("Story");
	    			ParseObject M= List.get(List.size()-1);
	    			
	    			    	sStory.put("StoryId",M.getInt("StoryId"));
		    			int entry =M.getInt("entry")+1;
	    			
	    			
	    			sStory.put("Name", M.getString("Name"));
	    			sStory.put("entry", entry);
	    			sStory.put("Part", part);
	    			sStory.put("user", ParseUser.getCurrentUser().get("username").toString());    		
	    			sStory.put("Personal", M.getBoolean("Personal") );
	    			sStory.put("Delete", M.getBoolean("Delete"));
	    			
	    				
	    		sStory.saveInBackground();
	    		finish();
	    		}
        	}});
    }

	
});
       
	   
	   
	    // TODO Auto-generated method stub
	}

}
