package ic_ethereum;



import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;

import static ic_ethereum.Idus.*;

public class IC_Administration {
   public IC_Administration(){
        System.out.println("web3j");
    }
    public static  IC_Administration ICA=new IC_Administration();
//    private volatile static Web3j web3j;
//    public static Web3j getClient(){
//        if(web3j==null){
//            synchronized (IC_Administration.class){
//                if(web3j==null){
//                    System.out.println("new W3J!!!");
//                    web3j = Web3j.build(new HttpService());
//                }
//            }
//        }
//        return web3j;
//    }
    public String IC_deploy() throws Exception {
        // 创建一个 web3j 的连接
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545/"));
        //web3j=getClient();
        // 部署的时候需要用到该账户的 gas，务必保证该账户余额充足
        Credentials credentials = WalletUtils.loadCredentials(
                "1234",
                "F:\\GoWorkSpace\\ether-test\\db\\keystore\\UTC--2020-01-10T13-00-45.976152300Z--1b17a608ca853903c8fada1bacb236bb4ac2e93f"
                 );
        // 部署合约
        Idus idus= deploy(web3j, credentials, BigInteger.valueOf(200000), BigInteger.valueOf(20000000)).send();
        // 部署完成后返回合约地址
        //Geth

        return idus.getContractAddress();
    }
}
