package edu.cs.fsu.gibbens.collaborativewriting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

	    import java.util.ArrayList;
import android.widget.Button;
import android.widget.SlidingDrawer;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import android.opengl.Visibility;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SimpleAdapter.ViewBinder;

	    public class Profile extends Activity {
	    	HashMap<String, Object> map1;
	    	HashMap<String, Object> map2;
	    	Iterator<ParseObject> x;
	    	protected List<ParseObject> List;
	    	/** Called when the activity is first created. */
	    	
	    	@Override
	    	public void onCreate(Bundle savedInstanceState) {
	    	    super.onCreate(savedInstanceState);
	    	    setContentView(R.layout.profile);
	    	    TextView about =(TextView) findViewById(R.id.aboutme);
	    	    about.setText("");
	    	    TextView Name =(TextView) findViewById(R.id.name);
	    	    String value = getIntent().getExtras().getBundle("k").getString("user");
	    	    Name.setText(value+": ");
	    	    
	    	    ParseQuery<ParseObject> query = ParseQuery.getQuery("AboutMe");
	    	    query.whereEqualTo("user", value);
	    	    query.findInBackground(new FindCallback<ParseObject>() {
	    	   	 @Override
	    	        public void done(List<ParseObject> List, ParseException e) {
	    	   		if (e == null) {
	    	   			if(List.iterator().hasNext())
	    	   			{ParseObject n= List.iterator().next();
	    	   		
	    	 	    TextView about =(TextView) findViewById(R.id.aboutme);
	    	 	    
	    	 	    about.setText(n.getString("AboutMe"));}
	    	   		}
	    	   		}});
	    	    
	    	    final ArrayList<HashMap<String, Object>> m_data = new ArrayList<HashMap<String, Object>>();

	    	    ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Story");
	    	    query1.whereEqualTo("entry", 0);
	    	    query1.whereEqualTo("Personal", true);
	    	    query1.findInBackground(new FindCallback<ParseObject>() {
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
	    	           	 SimpleAdapter adapter = new SimpleAdapter(Profile.this,
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
	    	           		     ListView l =(ListView) findViewById(R.id.pstories);
	    	           	l.setAdapter(adapter);
	    	                Log.i("score", "Retrieved "  + " scores");
	    	            } else {
	    	                Log.i("score", "Error: " + e.getMessage());
	    	            }
	    	        }

	    	   	
	    	    });
	    	    ListView l =(ListView) findViewById(R.id.pstories);  
	    	           
	    	           
	    	          
	    	   	        
	    	   	    
	    	   	      //end init data
	    	   	        
	    	   	        
	    	   	        
	    	   	       
	    	   	        
	    	   	        
	    	   	    

	    	   	        l.setOnItemClickListener(new OnItemClickListener(){

	    	   	            
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
	    	   	        
	    	   	     final ArrayList<HashMap<String, Object>> m_data1 = new ArrayList<HashMap<String, Object>>();

	 	    	    ParseQuery<ParseObject> query11 = ParseQuery.getQuery("Story");
	 	    	    query11.whereEqualTo("entry", 0);
	 	    	    query11.whereEqualTo("Personal", false);
	 	    	    query11.findInBackground(new FindCallback<ParseObject>() {
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
	 	    	           	 
	 	    	           	map2 = new HashMap<String, Object>();
	 	    	           	 map2.put("maintext", s);
	 	    	           	 map2.put("subtext", a);
	 	    	           	if(! m_data1.contains(map2))
	 	    	           	 m_data1.add(map2);
	 	    	           	else
	 	    	           	{
	 	    	           	List.remove(k);	
	 	    	           	}
	 	    	            }
	 	    	           	 x=scoreList.iterator();
	 	    	           	    for (HashMap<String, Object> m1 :m_data1) //make data of this view should not be null (hide )
	 	    	           	    	m1.put("checked", false);
	 	    	           	 SimpleAdapter adapter = new SimpleAdapter(Profile.this,
	 	    	           		        m_data1,
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
	 	    	           		     ListView l =(ListView) findViewById(R.id.cstories);
	 	    	           	l.setAdapter(adapter);
	 	    	                Log.i("score", "Retrieved "  + " scores");
	 	    	            } else {
	 	    	                Log.i("score", "Error: " + e.getMessage());
	 	    	            }
	 	    	        }

	 	    	   	
	 	    	    });
	 	    	    ListView c =(ListView) findViewById(R.id.cstories);  
	 	    	           
	 	    	           
	 	    	          
	 	    	   	        
	 	    	   	    
	 	    	   	      //end init data
	 	    	   	        
	 	    	   	        
	 	    	   	        
	 	    	   	       
	 	    	   	        
	 	    	   	        
	 	    	   	    

	 	    	   	        c.setOnItemClickListener(new OnItemClickListener(){

	 	    	   	            
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

	    	     
	    	    
	      		
	    	       
	    	    }
	    	   	 
	    	
	    	