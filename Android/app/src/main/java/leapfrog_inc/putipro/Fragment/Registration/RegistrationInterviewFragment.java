package leapfrog_inc.putipro.Fragment.Registration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import leapfrog_inc.putipro.Fragment.BaseFragment;
import leapfrog_inc.putipro.Http.Requester.GetUserRequester;
import leapfrog_inc.putipro.Http.Requester.UpdateWorkerProfileRequester;
import leapfrog_inc.putipro.R;

public class RegistrationInterviewFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_registration_interview, null);

        initAction(view);

        return view;
    }

    private void initAction(View view) {

        ((ImageButton)view.findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popFragment(AnimationType.horizontal);
            }
        });

        ((Button)view.findViewById(R.id.nextButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNext();
            }
        });
    }

    private void onClickNext() {

        // TODO
        UpdateWorkerProfileRequester.update("test", "", "", "", "", "", new UpdateWorkerProfileRequester.UpdateWorkerProfileCallback() {
            @Override
            public void didReceiveData(boolean result) {
                if (result) {
                    GetUserRequester.getInstance().fetch(new GetUserRequester.GetUserCallback() {
                        @Override
                        public void didReceiveData(boolean result) {
                            if (result) {
                                stackFragment(new RegistrationCompleteFragment(), AnimationType.horizontal);
                            } else {
                                // TODO
                            }
                        }
                    });
                } else {
                    // TODO
                }
            }
        });
    }
}