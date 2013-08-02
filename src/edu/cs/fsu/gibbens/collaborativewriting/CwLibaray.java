package edu.cs.fsu.gibbens.collaborativewriting;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

public class CwLibaray extends ContentProvider {
    public final static String DBNAME = "FormDatabase";
    public final static String TABLE_FORMTABLE = "formtable";
    public final static String User = "user";
    public final static String NAME = "name";
    public final static String ENTRY = "entry";
    public final static String PART = "part";
    public final static String STORY = "story"; 
    

    public static final String AUTHORITY = "edu.cs.fsu.gibbens.collaborativewriting.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://edu.cs.fsu.gibbens.collaborativewriting.provider/" + TABLE_FORMTABLE);

    private static UriMatcher sUriMatcher;

  
    private DatabaseHelper OpenHelper;

    private static final String SQL_CREATE_MAIN = "CREATE TABLE " +
            TABLE_FORMTABLE +                       // Table's name
            "(" +                           // The columns in the table
            " _ID INTEGER PRIMARY KEY, " +
            User+
            " TEXT," +
            NAME+
            " TEXT,"+
            PART+
            " TEXT,"+
             ENTRY+
            " INTEGER,"+
            STORY+
            " INTEGER)" ;
    
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		
		 return OpenHelper.getWritableDatabase().delete(TABLE_FORMTABLE, arg1, arg2);
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		// TODO Auto-generated method stub
        Log.i("Ping", "Retrieved "  + "Insert");
        String user = arg1.getAsString(User).trim();
        String name = arg1.getAsString(NAME).trim();
        String part = arg1.getAsString(PART).trim();
        Integer story = arg1.getAsInteger(STORY);
        Integer entry = arg1.getAsInteger(ENTRY);

        if(name.equals(""))
            return null;
        if(story==null)
            return null;
        if(entry==null)
            return null;
        if(part.equals(""))
            return null;
        if(user.equals(""))
            return null;
        long id = OpenHelper.getWritableDatabase().insert(TABLE_FORMTABLE, null, arg1);

        return Uri.withAppendedPath(CONTENT_URI, "" + id);
	
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		
		   OpenHelper = new DatabaseHelper(getContext());
		   

	        return true;
	}

	@Override
	public Cursor query(Uri arg0, String[] arg1, String arg2, String[] arg3,
			String arg4) {
		// TODO Auto-generated method stub 
		return OpenHelper.getReadableDatabase().query(TABLE_FORMTABLE, arg1, arg2, arg3, null, null, arg4);
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		 return OpenHelper.getWritableDatabase().update(TABLE_FORMTABLE, arg1, arg2, arg3);
		
	}
	protected static final class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DBNAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(SQL_CREATE_MAIN);
        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    }

}