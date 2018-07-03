package leapfrog_inc.putipro.Fragment.Offer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import leapfrog_inc.putipro.Fragment.BaseFragment;
import leapfrog_inc.putipro.R;

public class OfferCategoryFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_offer_category, null);

        initListView(view);

        return view;
    }

    private void initListView(View view) {

        ArrayList<OfferCategoryData> dataList = new ArrayList();
        dataList.add(OfferCategoryData.create("0", "掃除"));
        dataList.add(OfferCategoryData.create("1", "日曜大工"));
        dataList.add(OfferCategoryData.create("2", "見守り"));
        dataList.add(OfferCategoryData.create("3", "買い物"));

        OfferCategoryAdapter adapter = new OfferCategoryAdapter(getActivity());
        for (int i = 0; i < dataList.size(); i++) {
            adapter.add(dataList.get(i));
        }
        adapter.notifyDataSetChanged();

        ListView listView = view.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                OfferCategoryData data = (OfferCategoryData) adapterView.getItemAtPosition(i);
                stackFragment(new OfferInfoFragment(), AnimationType.horizontal);
            }
        });
    }

    private static class OfferCategoryData {
        String no;
        String name;

        public static OfferCategoryData create(String no, String name) {
            OfferCategoryData data = new OfferCategoryData();
            data.no = no;
            data.name = name;
            return data;
        }
    }

    public static class OfferCategoryAdapter extends ArrayAdapter<OfferCategoryData> {

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

            OfferCategoryData data = getItem(position);

            ((TextView)convertView.findViewById(R.id.nameTextView)).setText(data.name);

            return convertView;
        }
    }
}
