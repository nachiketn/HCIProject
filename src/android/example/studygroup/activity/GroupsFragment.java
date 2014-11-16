package android.example.studygroup.activity;

import com.example.studygroup.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
public class GroupsFragment extends Fragment implements OnClickListener {
	Button createButton;
	Button joinButton;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_groups, container,
				false);
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
