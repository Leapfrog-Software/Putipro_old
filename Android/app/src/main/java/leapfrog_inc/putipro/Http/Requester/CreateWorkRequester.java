package leapfrog_inc.putipro.Http.Requester;

import org.json.JSONObject;

import leapfrog_inc.putipro.Function.Constants;
import leapfrog_inc.putipro.Http.HttpManager;

public class CreateWorkRequester {

    public static void create(String categoryId, String description, String fee, String date, String ordererId, String receiverId, final CreateWorkRequesterCallback callback) {

        HttpManager httpManager = new HttpManager(new HttpManager.HttpCallback() {
            @Override
            public void didReceiveData(boolean result, String data) {
                if (result) {
                    try {
                        JSONObject jsonObject = new JSONObject(data);
                        String ret = jsonObject.getString("result");
                        if (ret.equals("0")) {
                            callback.didReceiveData(true);
                            return;
                        }
                    } catch(Exception e) {}
                }
                callback.didReceiveData(false);
            }
        });
        StringBuffer param = new StringBuffer();
        param.append("command=createWork");
        param.append("&");
        param.append("categoryId=" + categoryId);
        param.append("&");
        param.append("description=" + description);
        param.append("&");
        param.append("fee=" + fee);
        param.append("&");
        param.append("date=" + date);
        param.append("&");
        param.append("ordererId=" + ordererId);
        param.append("&");
        param.append("receiverId=" + receiverId);

        httpManager.execute(Constants.ServerApiUrl, "POST", param.toString());
    }

    public interface CreateWorkRequesterCallback {
        void didReceiveData(boolean result);
    }
}
