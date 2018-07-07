package leapfrog_inc.putipro.Fragment.Registration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import leapfrog_inc.putipro.Fragment.BaseFragment;
import leapfrog_inc.putipro.R;

public class RegistrationGenderPickerFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_registration_gender_picker, null);

        NumberPicker picker = (NumberPicker)view.findViewById(R.id.numberPicker);
        picker.setDisplayedValues(null);
        String[] genders = {"男性", "女性"};
        picker.setDisplayedValues(genders);

        ((Button)view.findViewById(R.id.okButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popFragment(AnimationType.none);
            }
        });

        return view;
    }

}
