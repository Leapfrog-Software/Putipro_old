package leapfrog_inc.putipro.Http.Requester;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import leapfrog_inc.putipro.Function.Base64Utility;
import leapfrog_inc.putipro.Function.Constants;
import leapfrog_inc.putipro.Http.HttpManager;

public class GetUserRequester {

    public static class UserData {

        public String id;
        public String name;
        public String age;
        public String gender;
        public String message;
        public String phone;
        public String interviewDate;

        static public UserData create(JSONObject json) {

            try {
                UserData userData = new UserData();
                userData.id = json.getString("id");
                userData.name = Base64Utility.decode(json.getString("name"));
                userData.age = json.getString("age");
                userData.gender = Base64Utility.decode(json.getString("gender"));
                userData.message = Base64Utility.decode(json.getString("message"));
                userData.phone = json.getString("phone");
                userData.interviewDate = json.getString("interviewDate");
                return userData;
            } catch(Exception e) {}

            return null;
        }
    }

    private static GetUserRequester requester = new GetUserRequester();

    private GetUserRequester(){}

    public static GetUserRequester getInstance() {
        return requester;
    }

    private ArrayList<UserData> mDataList = new ArrayList<UserData>();

    public void fetch(final GetUserCallback callback) {

        HttpManager httpManager = new HttpManager(new HttpManager.HttpCallback() {
            @Override
            public void didReceiveData(boolean result, String data) {
                if (result) {
                    try {
                        JSONObject jsonObject = new JSONObject(data);
                        String ret = jsonObject.getString("result");
                        if (ret.equals("0")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("users");
                            ArrayList<UserData> dataList = new ArrayList<UserData>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                UserData userData = UserData.create(jsonArray.getJSONObject(i));
                                if (userData != null) {
                                    dataList.add(userData);
                                }
                            }
                            mDataList = dataList;
                            callback.didReceiveData(true);
                            return;
                        }
                    } catch(Exception e) {}
                }
                callback.didReceiveData(false);
            }
        });
        StringBuffer param = new StringBuffer();
        param.append("command=getUser");
        httpManager.execute(Constants.ServerApiUrl, "POST", param.toString());
    }

    public interface GetUserCallback {
        void didReceiveData(boolean result);
    }
}
