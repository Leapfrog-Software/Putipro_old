package leapfrog_inc.putipro.Fragment.Offer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import leapfrog_inc.putipro.Fragment.BaseFragment;
import leapfrog_inc.putipro.Fragment.Common.Dialog;
import leapfrog_inc.putipro.Fragment.Common.Loading;
import leapfrog_inc.putipro.Fragment.Registration.RegistrationCompleteFragment;
import leapfrog_inc.putipro.Fragment.Registration.RegistrationInterviewFragment;
import leapfrog_inc.putipro.Fragment.Registration.RegistrationProfileFragment;
import leapfrog_inc.putipro.Fragment.Registration.RegistrationTermsFragment;
import leapfrog_inc.putipro.Function.Constants;
import leapfrog_inc.putipro.Function.PicassoUtility;
import leapfrog_inc.putipro.Function.SaveData;
import leapfrog_inc.putipro.Http.Requester.CreateWorkRequester;
import leapfrog_inc.putipro.Http.Requester.GetCategoryRequester;
import leapfrog_inc.putipro.Http.Requester.GetUserRequester;
import leapfrog_inc.putipro.R;

public class OfferConfirmFragment extends BaseFragment {

    private String mCategoryId = "";
    private String mDescription = "";
    private String mFee = "";
    private Calendar mCalendar;
    private String mName = "";
    private String mAge = "";
    private String mUserId = "";

    public void set(String categoryId, String description, String fee, Calendar calendar, String name, String age, String userId) {
        mCategoryId = categoryId;
        mDescription = description;
        mFee = fee;
        mCalendar = calendar;
        mName = name;
        mAge = age;
        mUserId = userId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_offer_confirm, null);

        initAction(view);
        initContents(view);

        return view;
    }

    private void initAction(View view) {

        ((Button)view.findViewById(R.id.sendButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSend();
            }
        });

        ((ImageButton)view.findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popFragment(AnimationType.horizontal);
            }
        });
    }

    private void initContents(View view) {

        String imageUrl = Constants.ServerUserImageDirectory + mUserId;
        PicassoUtility.getFaceImage(getActivity(), imageUrl, (ImageView)view.findViewById(R.id.faceImageView));

        GetUserRequester.UserData userData = GetUserRequester.getInstance().query(mUserId);
        ((TextView)view.findViewById(R.id.nameTextView)).setText(userData.name);

        String profile = userData.age + "歳 " + userData.gender;
        ((TextView)view.findViewById(R.id.profileTextView)).setText(profile);

        GetCategoryRequester.CategoryData categoryData = GetCategoryRequester.getInstance().query(mCategoryId);
        ((TextView)view.findViewById(R.id.categoryTextView)).setText(categoryData.name);

        ((TextView)view.findViewById(R.id.descriptionTextView)).setText(mDescription);

        ((TextView)view.findViewById(R.id.feeTextView)).setText(mFee);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/M/d kk:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
        ((TextView)view.findViewById(R.id.datetimeTextView)).setText(dateFormat.format(mCalendar.getTime()));
    }

    private void onClickSend() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMdkkmm00");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
        String date = dateFormat.format(mCalendar.getTime());

        Loading.start(getActivity());

        CreateWorkRequester.create(mCategoryId, mDescription, mFee, date, SaveData.getInstance().userId, mUserId, new CreateWorkRequester.CreateWorkRequesterCallback() {
            @Override
            public void didReceiveData(boolean result) {
                Loading.stop(getActivity());

                if (result) {
                    Dialog.show(getActivity(), Dialog.Style.success, "完了", "仕事の依頼を作成しました", new Dialog.DialogCallback() {
                        @Override
                        public void didClose() {
                            List<Fragment> fragments = getActivity().getSupportFragmentManager().getFragments();
                            for (int i = 0; i < fragments.size(); i++) {
                                BaseFragment fragment = (BaseFragment) fragments.get(i);
                                if ((fragment instanceof OfferInfoFragment)
                                        || (fragment instanceof OfferCategoryFragment)
                                        || (fragment instanceof OfferDetailFragment)
                                        || (fragment instanceof OfferCandidateFragment)
                                        || (fragment instanceof OfferConfirmFragment)) {
                                    fragment.popFragment(AnimationType.horizontal);
                                }
                            }
                        }
                    });
                } else {
                    Dialog.show(getActivity(), Dialog.Style.error, "エラー", "通信に失敗しました", null);
                }
            }
        });
    }
}
