package android.example.studygroup.activity;
import java.util.ArrayList;

import com.example.studygroup.R;
import android.app.Activity;
import android.example.studygroup.helper.DatabaseHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class JoinGroup extends Activity {
	DatabaseHelper db;
	ListView lv;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_join_group);
	        db = DatabaseHelper.getDataHelper(this.getApplicationContext());
	        
	        ArrayList<String> courseList = new ArrayList<String>();
	        courseList = (ArrayList<String>) db.retrieveCourses("sramesh3");
	      	
	        Spinner spinner = (Spinner) findViewById( R.id.coursespinner );
	        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
	                this, android.R.layout.simple_spinner_item, courseList);
	        spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
	        spinner.setAdapter(spinnerArrayAdapter);
	       spinner.setOnItemSelectedListener(new OnItemSelectedListener() 
	       {
	    	    @Override
	    	    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) 
	    	    {
	    	    	 String courseId = (String) parentView.getItemAtPosition(position);
	    			 ArrayList<String> groupList = new ArrayList<String>();
	    				groupList = (ArrayList<String>) db.retriveGroups(courseId);
	    				
	    				
	    				lv = (ListView) findViewById(R.id.listView1);
	    				
	    				ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(lv.getContext(), android.R.layout.simple_list_item_1, groupList);
	    				lv.setAdapter(arrayAdapter);
	    	    }

	    	    @Override
	    	    public void onNothingSelected(AdapterView<?> parentView) 
	    	    {
	    	       
	    	    }
	    	});
	       /* String courseId = (String) spinner.getSelectedItem();
	        ArrayList<String> groupList = new ArrayList<String>();
			groupList = (ArrayList<String>) db.retriveGroups(courseId);
			
			
			lv = (ListView) findViewById(R.id.listView1);
			
			ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, groupList);
			lv.setAdapter(arrayAdapter);*/
	    }

}
