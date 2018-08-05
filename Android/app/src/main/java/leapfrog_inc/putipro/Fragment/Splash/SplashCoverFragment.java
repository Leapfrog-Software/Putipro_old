package leapfrog_inc.putipro.Fragment.Splash;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import leapfrog_inc.putipro.Fragment.BaseFragment;
import leapfrog_inc.putipro.R;

public class SplashCoverFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_splash, null);
        return view;
    }

    public void hide() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                popFragment(AnimationType.none);
            }
        }, 3000);

    }
}
