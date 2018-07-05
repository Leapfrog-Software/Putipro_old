package leapfrog_inc.putipro.Http.Requester;

import org.json.JSONObject;

import leapfrog_inc.putipro.Function.Base64Utility;
import leapfrog_inc.putipro.Function.Constants;
import leapfrog_inc.putipro.Function.SaveData;
import leapfrog_inc.putipro.Http.HttpManager;

public class UpdateWorkerProfileRequester {

    public static void update(String name, String age, String gender, String message, String phone, String interviewDate, final UpdateWorkerProfileCallback callback) {

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
        param.append("command=updateWorkerProfile");
        param.append("&");
        param.append("id=" + SaveData.getInstance().userId);
        param.append("&");
        param.append("name=" + name);
        param.append("&");
        param.append("age=" + age);
        param.append("&");
        param.append("gender=" + Base64Utility.encode(gender));
        param.append("&");
        param.append("message=" + Base64Utility.encode(message));
        param.append("&");
        param.append("phone=" + phone);
        param.append("&");
        param.append("interviewDate=" + interviewDate);

        httpManager.execute(Constants.ServerApiUrl, "POST", param.toString());
    }

    public interface UpdateWorkerProfileCallback {
        void didReceiveData(boolean result);
    }
}
