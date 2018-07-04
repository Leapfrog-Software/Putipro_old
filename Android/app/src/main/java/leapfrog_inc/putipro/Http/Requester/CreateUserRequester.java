package leapfrog_inc.putipro.Http.Requester;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import leapfrog_inc.putipro.Function.Constants;
import leapfrog_inc.putipro.Http.HttpManager;

public class CreateUserRequester {

    public static void create(final CreateUserCallback callback) {

        HttpManager httpManager = new HttpManager(new HttpManager.HttpCallback() {
            @Override
            public void didReceiveData(boolean result, String data) {
                if (result) {
                    try {
                        JSONObject jsonObject = new JSONObject(data);
                        String ret = jsonObject.getString("result");
                        if (ret.equals("0")) {
                            String userId = jsonObject.getString("userId");
                            callback.didReceiveData(true, userId);
                            return;
                        }
                    } catch(Exception e) {}
                }
                callback.didReceiveData(false, null);
            }
        });
        StringBuffer param = new StringBuffer();
        param.append("command=createUser");
        httpManager.execute(Constants.ServerApiUrl, "POST", param.toString());
    }

    public interface CreateUserCallback {
        void didReceiveData(boolean result, String userId);
    }
}
