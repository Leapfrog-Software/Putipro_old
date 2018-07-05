package leapfrog_inc.putipro.Fragment.Registration;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import leapfrog_inc.putipro.Fragment.BaseFragment;
import leapfrog_inc.putipro.R;

public class RegistrationCompleteFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_registration_complete, null);

        initAction(view);

        return view;
    }

    private void initAction(View view) {

        ((Button)view.findViewById(R.id.okButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Fragment> fragments = getActivity().getSupportFragmentManager().getFragments();
                for (int i = 0; i < fragments.size(); i++) {
                    BaseFragment fragment = (BaseFragment) fragments.get(i);
                    if ((fragment instanceof RegistrationTermsFragment)
                            || (fragment instanceof RegistrationProfileFragment)
                            || (fragment instanceof RegistrationInterviewFragment)
                            || (fragment instanceof RegistrationCompleteFragment)) {
                        fragment.popFragment(AnimationType.horizontal);
                    }
                }
            }
        });
    }
}