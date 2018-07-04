package leapfrog_inc.putipro.Fragment.Offer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import leapfrog_inc.putipro.Fragment.BaseFragment;
import leapfrog_inc.putipro.Function.SaveData;
import leapfrog_inc.putipro.Http.Requester.CreateWorkRequester;
import leapfrog_inc.putipro.Http.Requester.GetCategoryRequester;
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

        CreateWorkRequester.create(mCategoryId, mDescription, mFee, date, SaveData.getInstance().userId, mUserId, new CreateWorkRequester.CreateWorkRequesterCallback() {
            @Override
            public void didReceiveData(boolean result) {
                    Log.d("", "");
            }
        });
    }
}
