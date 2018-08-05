package leapfrog_inc.putipro.Fragment.Splash;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import leapfrog_inc.putipro.Fragment.BaseFragment;
import leapfrog_inc.putipro.Fragment.Main.MainFragment;
import leapfrog_inc.putipro.Function.SaveData;
import leapfrog_inc.putipro.R;

public class TutorialFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_tutorial, null);

        initContents(view);
        initAction(view);

        return view;
    }

    private void initAction(View view) {

        view.findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveData saveData = SaveData.getInstance();
                saveData.didShowTutorial = true;
                saveData.save();
                
                stackFragment(new MainFragment(), AnimationType.horizontal);
            }
        });
    }

    private void initContents(View view) {

        ViewPager viewPager = (ViewPager)view.findViewById(R.id.viewPager);

        TutorialPagerAdapter adapter = new TutorialPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                setCurrentProgress(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    private void setCurrentProgress(int position) {

        View view = getView();

        if (position == 0) view.findViewById(R.id.progress1View).setBackgroundResource(R.drawable.shape_tutorial_progress_selected);
        else view.findViewById(R.id.progress1View).setBackgroundResource(R.drawable.shape_tutorial_progress);
        if (position == 1) view.findViewById(R.id.progress2View).setBackgroundResource(R.drawable.shape_tutorial_progress_selected);
        else view.findViewById(R.id.progress2View).setBackgroundResource(R.drawable.shape_tutorial_progress);
        if (position == 2) view.findViewById(R.id.progress3View).setBackgroundResource(R.drawable.shape_tutorial_progress_selected);
        else view.findViewById(R.id.progress3View).setBackgroundResource(R.drawable.shape_tutorial_progress);

        if (position == 2) {
            view.findViewById(R.id.nextButton).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.nextButton).setVisibility(View.INVISIBLE);
        }
    }

    private class TutorialPagerAdapter extends FragmentPagerAdapter {

        public TutorialPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            TutorialContentFragment fragment = new TutorialContentFragment();
            fragment.setIndex(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
}
