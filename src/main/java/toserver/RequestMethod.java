package toserver;


import ic_ethereum.IC_Administration;
//import net.sf.json.JSONObject;
import org.json.JSONObject;

/**
 * Created by Administrator on 2019/2/5.
 */

public class RequestMethod {
    //挖矿
    public static  void  RequestMine(IC_Administration ic_administration, final JSONObject jsonObject, final ServerLinkInterFace.onMineCallback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Looper.prepare();
                JSONObject resultObject = ResponseMethod.sendData(jsonObject, UrlString.Mine);
                //解析登录结果
                callback.Success(resultObject);
            }
        }).start();
    }
    //记录
    public static  void  RequestRecord(IC_Administration ic_administration, final JSONObject jsonObject, final ServerLinkInterFace.onRecordCallback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Looper.prepare();
                JSONObject resultObject = ResponseMethod.sendData(jsonObject, UrlString.Record);
                //解析登录结果
                callback.Success(resultObject);
            }
        }).start();
    }
}
