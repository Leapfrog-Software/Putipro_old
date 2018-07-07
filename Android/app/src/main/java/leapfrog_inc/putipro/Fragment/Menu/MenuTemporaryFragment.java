package leapfrog_inc.putipro.Fragment.Menu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import leapfrog_inc.putipro.Fragment.BaseFragment;
import leapfrog_inc.putipro.Fragment.Offer.OfferInfoFragment;
import leapfrog_inc.putipro.R;

public class MenuTemporaryFragment extends BaseFragment {

    private String mTitle = "";
    private String mMessage = "";

    public void set(String title, String message) {
        mTitle = title;
        mMessage = message;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_menu_temporary, null);

        initAction(view);

        ((TextView)view.findViewById(R.id.headerTitleTextView)).setText(mTitle);
        ((TextView)view.findViewById(R.id.messageTextView)).setText(mMessage);

        return view;
    }

    private void initAction(View view) {

        ((ImageButton)view.findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Fragment> fragments = getActivity().getSupportFragmentManager().getFragments();
                for (int i = 0; i < fragments.size(); i++) {
                    BaseFragment fragment = (BaseFragment) fragments.get(i);
                    if (fragment instanceof MenuFragment) {
                        fragment.popFragment(AnimationType.none);
                    }
                }

                popFragment(AnimationType.horizontal);
            }
        });
    }

}
