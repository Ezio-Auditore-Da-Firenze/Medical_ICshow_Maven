package toserver;


public class UrlString {
    public static String getIP() {
        return IP;
    }

    public static void setIP(String IP) {
        UrlString.IP = IP;
    }

    //IP地址
    public static String IP = "49.234.120.88:8080";
    //47.93.202.79:6688
    //10.11.72.222:8080
    public static final String Mine = "http://"+IP+"/ICT/mine";
    public static final String Record = "http://"+IP+"/ICT/record";
}
