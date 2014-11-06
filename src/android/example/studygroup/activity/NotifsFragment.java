package android.example.studygroup.activity;

import android.support.v4.app.Fragment;

import com.example.studygroup.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NotifsFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_notifs, container,
				false);

		return rootView;
	}
}
