package leapfrog_inc.putipro.Fragment.Menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import leapfrog_inc.putipro.Fragment.BaseFragment;
import leapfrog_inc.putipro.Function.DeviceUtility;
import leapfrog_inc.putipro.R;

public class MenuFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_menu, null);

        moveContents(view);
        initAction(view);

        return view;
    }

    private void moveContents(View view) {

        int fromX = (int)(-DeviceUtility.getWindowSize(getActivity()).x * 3 / 4);
        TranslateAnimation animation = new TranslateAnimation(fromX, 0, 0, 0);
        animation.setDuration(200);
        animation.setRepeatCount(0);
        animation.setFillAfter(true);

        LinearLayout moveLayout = (LinearLayout)view.findViewById(R.id.moveLayout);
        moveLayout.startAnimation(animation);
    }

    private void initAction(View view) {

        ((Button)view.findViewById(R.id.offerHistoryButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stackTemporary("依頼履歴", "依頼履歴はありません");
            }
        });

        ((Button)view.findViewById(R.id.receiveHistoryButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stackTemporary("受注履歴", "受注履歴はありません");
            }
        });

        ((Button)view.findViewById(R.id.paymentButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stackTemporary("入金手続き", "該当する入金データがありません");
            }
        });

        ((Button)view.findViewById(R.id.questionButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stackFragment(new FragmentInquiry(), AnimationType.horizontal);
            }
        });

        ((Button)view.findViewById(R.id.closeButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close();
            }
        });
    }

    private void stackTemporary(String title, String message) {

        MenuTemporaryFragment fragment = new MenuTemporaryFragment();
        fragment.set(title, message);
        stackFragment(fragment, AnimationType.horizontal);
    }

    private void close() {

        View view = getView();
        if (view == null) return;

        int toX = (int)(-DeviceUtility.getWindowSize(getActivity()).x * 3 / 4);
        TranslateAnimation animation = new TranslateAnimation(0, toX, 0, 0);
        animation.setDuration(200);
        animation.setRepeatCount(0);
        animation.setFillAfter(true);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                popFragment(AnimationType.none);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        LinearLayout moveLayout = (LinearLayout)view.findViewById(R.id.moveLayout);
        moveLayout.startAnimation(animation);
    }
}
