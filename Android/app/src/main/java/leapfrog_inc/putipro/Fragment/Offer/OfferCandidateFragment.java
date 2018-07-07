package leapfrog_inc.putipro.Fragment.Offer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import leapfrog_inc.putipro.Fragment.BaseFragment;
import leapfrog_inc.putipro.Function.Constants;
import leapfrog_inc.putipro.Function.PicassoUtility;
import leapfrog_inc.putipro.Http.Requester.GetUserRequester;
import leapfrog_inc.putipro.R;

public class OfferCandidateFragment extends BaseFragment {

    private String mCategoryId = "";
    private String mDescription = "";
    private String mFee = "";
    private Calendar mCalendar;
    private String mName = "";
    private String mAge = "";

    public void set(String categoryId, String description, String fee, Calendar calendar, String name, String age) {
        mCategoryId = categoryId;
        mDescription = description;
        mFee = fee;
        mCalendar = calendar;
        mName = name;
        mAge = age;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_offer_candidate, null);

        initAction(view);
        initListView(view);

        return view;
    }

    private void initAction(View view) {

        ((ImageButton)view.findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popFragment(AnimationType.horizontal);
            }
        });
    }

    private void initListView(View view) {

        ArrayList<GetUserRequester.UserData> userList = GetUserRequester.getInstance().getDataList();
        OfferCandidateAdapter adapter = new OfferCandidateAdapter(getActivity());

        for (int i = 0; i < userList.size(); i++) {
            GetUserRequester.UserData userData = userList.get(i);
            if (userData.name.length() > 0) {
                adapter.add(userData);
            }
        }
        adapter.notifyDataSetChanged();

        ListView listView = view.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                GetUserRequester.UserData userData = (GetUserRequester.UserData)adapterView.getItemAtPosition(position);
                onTapGuide(userData.id);
            }
        });
    }

    private void onTapGuide(String userId) {

        OfferConfirmFragment fragment = new OfferConfirmFragment();
        fragment.set(mCategoryId, mDescription, mFee, mCalendar, mName, mAge, userId);
        stackFragment(fragment, AnimationType.horizontal);
    }

    public static class OfferCandidateAdapter extends ArrayAdapter<GetUserRequester.UserData> {

        LayoutInflater mInflater;
        Context mContext;

        public OfferCandidateAdapter(Context context){
            super(context, 0);
            mInflater = LayoutInflater.from(context);
            mContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = mInflater.inflate(R.layout.adapter_offer_candidate, parent, false);

            GetUserRequester.UserData userData = getItem(position);

            String imageUrl = Constants.ServerUserImageDirectory + userData.id;
            PicassoUtility.getFaceImage(mContext, imageUrl, (ImageView)convertView.findViewById(R.id.faceImageView));

            ((TextView)convertView.findViewById(R.id.nameTextView)).setText(userData.name);

            String profile = userData.age + "歳 " + userData.gender;
            ((TextView)convertView.findViewById(R.id.profileTextView)).setText(profile);

            ((TextView)convertView.findViewById(R.id.messageTextView)).setText(userData.message);

            return convertView;
        }
    }
}
