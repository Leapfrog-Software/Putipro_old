package leapfrog_inc.putipro.Fragment.Offer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import leapfrog_inc.putipro.Fragment.BaseFragment;
import leapfrog_inc.putipro.R;

public class OfferCandidateFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_offer_candidate, null);

        initListView(view);

        return view;
    }

    private void initListView(View view) {

        OfferCandidateAdapter adapter = new OfferCandidateAdapter(getActivity());
        for (int i = 0; i < 10; i++) {
            adapter.add(String.valueOf(i));
        }
        adapter.notifyDataSetChanged();

        ListView listView = view.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String i = (String)adapterView.getItemAtPosition(position);
                stackFragment(new OfferConfirmFragment(), AnimationType.horizontal);
            }
        });
    }

    public static class OfferCandidateAdapter extends ArrayAdapter<String> {

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

            String data = getItem(position);



            return convertView;
        }
    }
}
