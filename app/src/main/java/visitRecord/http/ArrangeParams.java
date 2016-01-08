package visitRecord.http;

import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;
import org.xutils.http.app.DefaultParamsBuilder;

import java.util.Date;

/**
 * Created by wyouflf on 15/11/4.
 */
@HttpRequest(
       // host = "https://www.baidu.com",
//        host = "http://114.215.237.241:8080/",
        host = "http://192.168.30.142:8080/",
        path = "record",

        builder = DefaultParamsBuilder.class/*可选参数, 控制参数构建过程, 定义参数签名, SSL证书等*/)
public class ArrangeParams extends RequestParams {
    /**0未领取;1已领取;2已探访;3已取消*/
    public Integer status;
    public String name;
//    public Date fromTime;
    public String fromTime;
//    public Date toTime;
    public String toTime;
    public Integer start;
    public Integer limit;
    public Long uid;
    //public long timestamp = System.currentTimeMillis();
    //public File uploadFile;
}
