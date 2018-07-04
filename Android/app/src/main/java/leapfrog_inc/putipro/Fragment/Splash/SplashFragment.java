package leapfrog_inc.putipro.Fragment.Splash;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import leapfrog_inc.putipro.Fragment.BaseFragment;
import leapfrog_inc.putipro.Fragment.Main.MainFragment;
import leapfrog_inc.putipro.Function.SaveData;
import leapfrog_inc.putipro.Http.Requester.CreateUserRequester;
import leapfrog_inc.putipro.Http.Requester.GetCategoryRequester;
import leapfrog_inc.putipro.Http.Requester.GetUserRequester;
import leapfrog_inc.putipro.R;

public class SplashFragment extends BaseFragment {

    enum FetchResult {
        ok,
        error,
        progress
    }

    private FetchResult mGetUserFetchResult = FetchResult.progress;
    private FetchResult mGetCategoryFetchResult = FetchResult.progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_splash, null);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                createUser();
            }
        }, 1000);

        return view;
    }

    private void createUser() {

        if (SaveData.getInstance().userId.length() == 0) {
            CreateUserRequester.create(new CreateUserRequester.CreateUserCallback() {
                @Override
                public void didReceiveData(boolean result, String userId) {
                    if ((result) && (userId != null)) {
                        SaveData saveData = SaveData.getInstance();
                        saveData.userId = userId;
                        saveData.save();

                        fetchData();
                    } else {
                        // TODO
                    }
                }
            });
        } else {
            fetchData();
        }
    }

    private void fetchData() {

        if (mGetUserFetchResult != FetchResult.ok) {
            GetUserRequester.getInstance().fetch(new GetUserRequester.GetUserCallback() {
                @Override
                public void didReceiveData(boolean result) {
                    if (result)     mGetUserFetchResult = FetchResult.ok;
                    else            mGetUserFetchResult = FetchResult.error;
                    checkResult();
                }
            });
        }

        if (mGetCategoryFetchResult != FetchResult.ok) {
            GetCategoryRequester.getInstance().fetch(new GetCategoryRequester.GetCategoryCallback() {
                @Override
                public void didReceiveData(boolean result) {
                    if (result)     mGetCategoryFetchResult = FetchResult.ok;
                    else            mGetCategoryFetchResult = FetchResult.error;
                    checkResult();
                }
            });
        }
    }

    private void checkResult() {

        if ((mGetUserFetchResult == FetchResult.progress)
                || (mGetCategoryFetchResult == FetchResult.progress)) {
            return;
        }

        if ((mGetUserFetchResult == FetchResult.error)
                || (mGetCategoryFetchResult == FetchResult.error)) {
            // TODO
            return;
        }

        stackFragment(new MainFragment(), AnimationType.none);
    }
}