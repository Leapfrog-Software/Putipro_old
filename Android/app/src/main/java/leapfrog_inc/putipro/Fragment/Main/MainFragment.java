package leapfrog_inc.putipro.Fragment.Main;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

import leapfrog_inc.putipro.Fragment.BaseFragment;
import leapfrog_inc.putipro.Fragment.Offer.OfferCategoryFragment;
import leapfrog_inc.putipro.Fragment.Registration.RegistrationTermsFragment;
import leapfrog_inc.putipro.Function.SaveData;
import leapfrog_inc.putipro.Http.Requester.GetUserRequester;
import leapfrog_inc.putipro.R;

public class MainFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_main, null);

        initAction(view);

        return view;
    }

    private void initAction(View view) {

        ((ImageButton)view.findViewById(R.id.menuButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ((Button)view.findViewById(R.id.offerButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stackFragment(new OfferCategoryFragment(), AnimationType.horizontal);
            }
        });

        ((Button)view.findViewById(R.id.searchButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetUserRequester.UserData myUserData = GetUserRequester.getInstance().query(SaveData.getInstance().userId);
                if (myUserData.name.length() == 0) {
                    RegistrationTermsFragment fragment = new RegistrationTermsFragment();
                    stackFragment(fragment, AnimationType.horizontal);
                } else {

                }
            }
        });
    }
}
