package leapfrog_inc.putipro.Function;

public class Constants {

    public static String ServerRootUrl = "http://10.0.2.2/putipro/";
    public static String ServerApiUrl = Constants.ServerRootUrl + "srv.php";

    public static int HttpConnectTimeout = 10000;
    public static int HttpReadTimeout = 10000;

    public static class SharedPreferenceKey {
        public static String Key = "Putipro";
        public static String UserId = "UserId";
    }
}