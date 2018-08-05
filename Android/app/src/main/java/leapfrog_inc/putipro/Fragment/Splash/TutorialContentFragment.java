package leapfrog_inc.putipro.Fragment.Splash;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import leapfrog_inc.putipro.R;

public class TutorialContentFragment extends Fragment {

    private int mIndex = 0;

    public void setIndex(int index) {
        mIndex = index;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_tutorial_content, null);

        if (mIndex == 0) {
            ((TextView) view.findViewById(R.id.textView)).setText("アプリの使い方1");
        } else if (mIndex == 1) {
            ((TextView) view.findViewById(R.id.textView)).setText("アプリの使い方2");
        } else {
            ((TextView) view.findViewById(R.id.textView)).setText("アプリの使い方3");
        }
        return view;
    }
}
