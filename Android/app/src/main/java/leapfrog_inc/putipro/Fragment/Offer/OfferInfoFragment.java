package leapfrog_inc.putipro.Fragment.Offer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import leapfrog_inc.putipro.Fragment.BaseFragment;
import leapfrog_inc.putipro.R;

public class OfferInfoFragment extends BaseFragment {

    private String mCategoryId = "";

    public void setCategoryId(String categoryId) {
        mCategoryId = categoryId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_offer_info, null);

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

        View view = getView();
        String description = ((EditText)view.findViewById(R.id.descriptionEditText)).getText().toString();
        String fee = ((EditText)view.findViewById(R.id.feeTextField)).getText().toString();

        OfferDetailFragment fragment = new OfferDetailFragment();
        fragment.setInfo(mCategoryId, description, fee);
        stackFragment(fragment, AnimationType.horizontal);
    }
}
