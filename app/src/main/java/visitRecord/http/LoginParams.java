package visitRecord.http;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;
import org.xutils.http.app.DefaultParamsBuilder;

/**
 * Created by wyouflf on 15/11/4.
 */
@HttpRequest(
       // host = "https://www.baidu.com",
//        host = "http://114.215.237.241:8080/",
        host = "http://192.168.30.142:8080/",
        path = "record/1",

        builder = DefaultParamsBuilder.class/*可选参数, 控制参数构建过程, 定义参数签名, SSL证书等*/)
public class LoginParams extends RequestParams {
    public String pwd;
    public String name;
    //public long timestamp = System.currentTimeMillis();
    //public File uploadFile;
}
