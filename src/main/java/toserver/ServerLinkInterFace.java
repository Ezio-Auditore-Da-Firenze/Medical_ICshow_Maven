package toserver;


import org.json.JSONObject;

//import net.sf.json.JSONObject;

public class ServerLinkInterFace {
    public  interface onMineCallback {
        void Success(JSONObject role);

        void Fail(String error);

        void UnkownError(String unkownerror);
    }
}
