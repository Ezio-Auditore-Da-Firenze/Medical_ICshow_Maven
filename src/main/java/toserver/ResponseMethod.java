package toserver;

//import android.text.TextUtils;
//import android.util.Log;

//import net.sf.json.JSONException;
//import net.sf.json.JSONObject;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2019/2/5.
 */

public class ResponseMethod {
    //服务端返回值方法
    public static JSONObject sendData(JSONObject jo2, String url) {
        InputStreamReader isr = null;
        DataOutputStream dos = null;
        HttpURLConnection urlConn = null;
        BufferedReader bufferedReader = null;
        JSONObject jsonObject = null;
        try {
            URL url1 = new URL(url);
            urlConn = (HttpURLConnection) url1.openConnection();
            urlConn.setDoInput(true);//设置输入流采用字节流
            urlConn.setDoOutput(true);//设置输出流采用字节流
            urlConn.setRequestMethod("POST");//post方法
            urlConn.setUseCaches(false);//缓存
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置meta参数
            urlConn.setRequestProperty("Charset", "utf-8");//缓存
            urlConn.connect();//连接服务器发送消息
            System.out.println("发送信息为："+jo2.toString());
            dos = new DataOutputStream(urlConn.getOutputStream());
            dos.write((jo2.toString()).getBytes());//写数据
            dos.close();
            dos.flush();
            isr = new InputStreamReader(urlConn.getInputStream());
            bufferedReader = new BufferedReader(isr);
            String result = "";
            String readLine = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                result += readLine;
            }
           // Log.e("AKA", "返回的" + result);
            result = removeBOM(result);
            jsonObject = new JSONObject(result);
            bufferedReader.close();
            isr.close();
            urlConn.disconnect();//关闭连接
            return jsonObject;
        } catch (MalformedURLException e) {
            System.out.println("异常=MalformedURLException");
            e.printStackTrace();
            return jsonObject;
        } catch (IOException e) {
            System.out.println("异常=IOException"+e.getMessage());
            e.printStackTrace();
            return jsonObject;
        } catch (JSONException e) {
            System.out.println("异常=JSONException");
            e.printStackTrace();
            return jsonObject;
        }
    }

    public static final String removeBOM(String data) {
//        if (TextUtils.isEmpty(data)) {
//            return data;
//        }
        if (data.startsWith("\ufeff")) {
            return data.substring(1);
        } else {
            return data;
        }
    }
}
