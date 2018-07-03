package leapfrog_inc.putipro.Http.Requester;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import leapfrog_inc.putipro.Function.Constants;
import leapfrog_inc.putipro.Http.HttpManager;

public class GetCategoryRequester {

    public static class CategoryData {

        public String id;
        public String name;

        static public CategoryData create(JSONObject json) {

            try {
                CategoryData categoryData = new CategoryData();
                categoryData.id = json.getString("id");
                categoryData.name = json.getString("name");
                return categoryData;

            } catch(Exception e) {}

            return null;
        }
    }

    private static GetCategoryRequester requester = new GetCategoryRequester();

    private GetCategoryRequester(){}

    public static GetCategoryRequester getInstance() {
        return requester;
    }

    private ArrayList<CategoryData> mDataList = new ArrayList<CategoryData>();

    public void fetch(final GetCategoryCallback callback) {

        HttpManager httpManager = new HttpManager(new HttpManager.HttpCallback() {
            @Override
            public void didReceiveData(boolean result, String data) {
                if (result) {
                    try {
                        JSONObject jsonObject = new JSONObject(data);
                        String ret = jsonObject.getString("result");
                        if (ret.equals("0")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("categories");
                            ArrayList<CategoryData> dataList = new ArrayList<CategoryData>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                CategoryData userData = CategoryData.create(jsonArray.getJSONObject(i));
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
        param.append("command=getCategory");
        httpManager.execute(Constants.ServerApiUrl, "POST", param.toString());
    }

    public interface GetCategoryCallback {
        void didReceiveData(boolean result);
    }

    public ArrayList<CategoryData> getDataList() {
        return mDataList;
    }
}
