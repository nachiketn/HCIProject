package android.example.studygroup.adapter;

import android.example.studygroup.activity.GroupsFragment;
import android.example.studygroup.activity.InfoFragment;
import android.example.studygroup.activity.NotifsFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class TabbedPagesAdapter extends
		android.support.v4.app.FragmentPagerAdapter {

	public TabbedPagesAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int itemNumber) {

		switch (itemNumber) {
		case 0:
			return new GroupsFragment();
		case 1:
			return new NotifsFragment();
		case 2:
			return new InfoFragment();
		}

		return null;
	}

	@Override
	public int getCount() {
		return 3;
	}

}
