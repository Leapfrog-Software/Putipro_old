package leapfrog_inc.putipro.Fragment.Menu;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import leapfrog_inc.putipro.Fragment.BaseFragment;
import leapfrog_inc.putipro.Fragment.Common.Dialog;
import leapfrog_inc.putipro.Fragment.Common.Loading;
import leapfrog_inc.putipro.R;

public class FragmentInquiry extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_inquiry, null);

        initAction(view);

        return view;
    }

    private void initAction(View view) {

        ((Button)view.findViewById(R.id.sendButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Loading.start(getActivity());

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Loading.stop(getActivity());

                        Dialog.show(getActivity(), Dialog.Style.success, "完了", "送信しました", new Dialog.DialogCallback() {
                            @Override
                            public void didClose() {
                                popFragment(AnimationType.horizontal);
                            }
                        });
                    }
                }, 1000);
            }
        });

        ((ImageButton)view.findViewById(R.id.backButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Fragment> fragments = getActivity().getSupportFragmentManager().getFragments();
                for (int i = 0; i < fragments.size(); i++) {
                    BaseFragment fragment = (BaseFragment) fragments.get(i);
                    if (fragment instanceof MenuFragment) {
                        fragment.popFragment(AnimationType.none);
                    }
                }
                popFragment(AnimationType.horizontal);
            }
        });
    }
}
