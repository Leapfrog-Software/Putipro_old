package leapfrog_inc.putipro.Function;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveData {

    private static SaveData container = null;

    public Context mContext;
    public String userId = "";

    private SaveData(){}

    public static SaveData getInstance(){
        if(container == null){
            container = new SaveData();
        }
        return container;
    }

    public void initialize(Context context) {

        mContext = context;

        SharedPreferences data = context.getSharedPreferences(Constants.SharedPreferenceKey.Key, Context.MODE_PRIVATE);

        userId = data.getString(Constants.SharedPreferenceKey.UserId, "");
    }

    public void save() {

        SharedPreferences data = mContext.getSharedPreferences(Constants.SharedPreferenceKey.Key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();

        editor.putString(Constants.SharedPreferenceKey.UserId, userId);

        editor.apply();
    }
}

