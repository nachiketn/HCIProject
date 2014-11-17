package android.example.studygroup.activity;

import java.util.ArrayList;

import com.example.studygroup.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import android.example.studygroup.helper.DatabaseHelper;
import android.example.studygroup.model.Course;
import android.example.studygroup.model.StudyGroup;
import android.example.studygroup.model.MemberOf;
import android.example.studygroup.model.Taken;
import android.example.studygroup.model.User;

public class GroupsFragment extends Fragment implements OnClickListener {
	DatabaseHelper db;
	Button createButton;
	Button joinButton;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_groups, container,
				false);
		db = DatabaseHelper.getDataHelper(this.getActivity().getApplicationContext());
		
		User user1= new User();
		user1.setUserId("sramesh3");
		user1.setName("Saranya");
		user1.setEmailId("sramesh3@ncsu.edu");
		user1.setMajor("CS");
		
		User user2= new User();
		user2.setUserId("prbhide");
		user2.setName("Pallavi");
		user2.setEmailId("prbhide@ncsu.edu");
		user2.setMajor("CS");
		
		db.insertUser(user1);
		db.insertUser(user2);
		
		Course course1= new Course();
		course1.setCourseId("CSC554");
		course1.setCourseName("HCI");
		course1.setDays("Mo,We");
		course1.setStartTime("10:30");
		course1.setEndTime("11:45");
		
		Course course2= new Course();
		course2.setCourseId("CSC515");
		course2.setCourseName("Software Security");
		course2.setDays("Fr");
		course2.setStartTime("10:30");
		course2.setEndTime("13:30");
		
		db.insertCourse(course1);
		db.insertCourse(course2);
		
		StudyGroup group1 = new StudyGroup();
		group1.setGroupName("HCIGroup1");
		group1.setCourseId("CSC554");
		group1.setAdminName("prbhide");
		group1.setDescription("Awesome HCI group!");
		group1.setPlace("Hunt Library");
		group1.setStartTime("12:30");
		group1.setEndTime("13:30");
		group1.setDays("Tu,Th");
		
		StudyGroup group2 = new StudyGroup();
		group2.setGroupName("HCIGroup2");
		group2.setCourseId("CSC554");
		group2.setAdminName("sramesh3");
		group2.setDescription("Friends HCI group!");
		group2.setPlace("Hunt Library");
		group2.setStartTime("09:30");
		group2.setEndTime("11:30");
		group2.setDays("Mo,We");
		
		StudyGroup group3 = new StudyGroup();
		group3.setGroupName("HCIGroup3");
		group3.setCourseId("CSC554");
		group3.setAdminName("sramesh3");
		group3.setDescription("Nerdy HCI group!");
		group3.setPlace("Hunt Library");
		group3.setStartTime("19:30");
		group3.setEndTime("20:30");
		group3.setDays("Mo,We");
		
		db.insertGroup(group1);
		db.insertGroup(group2);
		db.insertGroup(group3);
		
		Taken taken1 = new Taken();
		taken1.setUserId("sramesh3");
		taken1.setCourseId("CSC554");
		
		Taken taken2 = new Taken();
		taken2.setUserId("sramesh3");
		taken2.setCourseId("CSC515");	
		
		db.insertTaken(taken1);
		db.insertTaken(taken2);
		
		createButton = (Button) rootView.findViewById(R.id.create_group_tab_button);
		createButton.setOnClickListener(this);	
		joinButton = (Button) rootView.findViewById(R.id.join_group_tab_button);
		joinButton.setOnClickListener(this);	
		return rootView;
	}
	
	public void onClick(View v) { 
	    switch (v.getId()) { 
	    case R.id.create_group_tab_button:
	    	try{
	    	Intent intent = new Intent(this.getActivity(), CreateGroup.class);
	        startActivity(intent);}
	    	catch(Exception e){
	    		System.out.println("Exception");
	    	}
	        break;
	    case R.id.join_group_tab_button:
	    	Intent intent = new Intent(this.getActivity(), JoinGroup.class);
	        startActivity(intent);
	        break;
	    }
	
	}
}
