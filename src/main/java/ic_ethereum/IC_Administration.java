package ic_ethereum;



import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.geth.Geth;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

import java.io.IOException;
import java.math.BigInteger;

import static ic_ethereum.Idus.*;

public class IC_Administration {
    HttpService hs;
    Web3j web3j;
    Geth geth;
    Idus idus;
    public static  IC_Administration ICA=new IC_Administration();
    public IC_Administration(){
        hs=new HttpService("http://localhost:8545/");
        web3j = Web3j.build(hs); // 创建一个 web3j 的连接
        geth=Geth.build(hs);
    }
    public String IC_deploy() throws Exception {
        // 部署的时候需要用到该账户的 gas，务必保证该账户余额充足
        Credentials credentials = WalletUtils.loadCredentials(
                "1234",
                "F:\\GoWorkSpace\\ether-test\\db\\keystore\\UTC--2020-01-10T13-00-45.976152300Z--1b17a608ca853903c8fada1bacb236bb4ac2e93f"
                 );
        // 部署合约
        geth.minerStart(1).send();
        idus= deploy(web3j, credentials, BigInteger.valueOf(22000000000L), BigInteger.valueOf(4300000L)).send();
        // 部署完成后返回合约地址
        geth.minerStop().send();
        return idus.getContractAddress();
    }
    public void mine() throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request<?, EthBlockNumber> request=geth.ethBlockNumber();
                    String oldblock=request.send().getBlockNumber().toString();
                    System.out.println("MineStart\nTopBlockNumber_OLD="+oldblock);
                    geth.minerStart(1).send();
                    String newblock=oldblock;
                    while(oldblock.equals(newblock)){
                        Thread.sleep(Long.parseLong("100"));
                        newblock=geth.ethBlockNumber().send().getBlockNumber().toString();
                    }
                    geth.minerStop().send();
                    System.out.println("TopBlockNumber_NEW="+newblock+"\nMineStop");
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
