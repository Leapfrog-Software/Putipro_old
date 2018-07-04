package leapfrog_inc.putipro.Fragment.Offer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import leapfrog_inc.putipro.Fragment.BaseFragment;
import leapfrog_inc.putipro.Http.Requester.GetCategoryRequester;
import leapfrog_inc.putipro.R;

public class OfferCategoryFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_offer_category, null);

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

        OfferCategoryAdapter adapter = new OfferCategoryAdapter(getActivity());
        for (int i = 0; i < GetCategoryRequester.getInstance().getDataList().size(); i++) {
            adapter.add(GetCategoryRequester.getInstance().getDataList().get(i));
        }
        adapter.notifyDataSetChanged();

        ListView listView = view.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GetCategoryRequester.CategoryData data = (GetCategoryRequester.CategoryData) adapterView.getItemAtPosition(i);
                OfferInfoFragment fragment = new OfferInfoFragment();
                fragment.setCategoryId(data.id);
                stackFragment(fragment, AnimationType.horizontal);
            }
        });
    }

    public static class OfferCategoryAdapter extends ArrayAdapter<GetCategoryRequester.CategoryData> {

        LayoutInflater mInflater;
        Context mContext;

        public OfferCategoryAdapter(Context context){
            super(context, 0);
            mInflater = LayoutInflater.from(context);
            mContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = mInflater.inflate(R.layout.adapter_offer_category, parent, false);

            GetCategoryRequester.CategoryData data = getItem(position);

            ((TextView)convertView.findViewById(R.id.nameTextView)).setText(data.name);

            return convertView;
        }
    }
}
