package visitRecord.http;

import org.xutils.http.RequestParams;

/**
 * Created by luo on 15-12-14.
 */
public class HttpUtil {

    private static RequestParams params;
    private static final String CHARESRT = "UTF-8";

      private static final String BASE_HTTP = "http://192.168.30.142:8080/";
//    private static final String BASE_HTTP = "http://cs.80gd.cn/";


    /* 登陆 */
    public static final String LOGIN = BASE_HTTP + "index.php/User/Api/login";

    /*获取用户信息*/
    public static final String USER_INFO = BASE_HTTP+"user/";

    /*获取用户列表*/
    public static final String USER_LIST = BASE_HTTP+"user";

    /*根据条件获取记录列表*/
    public static final String RECORD_LIST = BASE_HTTP+"record";
}
