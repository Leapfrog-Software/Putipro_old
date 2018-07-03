package leapfrog_inc.putipro.Fragment.Offer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import leapfrog_inc.putipro.Fragment.BaseFragment;
import leapfrog_inc.putipro.R;

public class OfferDetailFragment extends BaseFragment {

    private String mCategoryId = "";
    private String mDescription = "";
    private String mFee = "";

    public void setInfo(String categoryId, String description, String fee) {
        mCategoryId = categoryId;
        mDescription = description;
        mFee = fee;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_offer_detail, null);

        initAction(view);

        return view;
    }

    private void initAction(View view) {

        ((Button)view.findViewById(R.id.nextButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stackFragment(new OfferCandidateFragment(), AnimationType.horizontal);
            }
        });
    }
}
