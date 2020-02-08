package ic_ethereum;



import bean.Patient;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import org.web3j.protocol.geth.Geth;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import static ic_ethereum.Idus.*;

public class IC_Administration {
    private HttpService hs;
    private Web3j web3j;
    private Geth geth;
    Credentials credentials;
    public static  IC_Administration ICA=new IC_Administration();
    public IC_Administration(){
        hs=new HttpService("http://localhost:8545/");
        web3j = Web3j.build(hs); // 创建一个 web3j 的连接
        geth=Geth.build(hs);
        try {
            credentials= WalletUtils.loadCredentials(        // 加载钱包
                    "1234",
                    "F:\\GoWorkSpace\\ether-test\\db\\keystore\\UTC--2020-01-10T13-00-45.976152300Z--1b17a608ca853903c8fada1bacb236bb4ac2e93f"
            );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }
    }
    public String IC_deploy() throws Exception {

        // 部署合约
        mine();
        //geth.minerStart(1).send();
        Idus idus= Idus.deploy(web3j, credentials, BigInteger.valueOf(22000000000L), BigInteger.valueOf(4300000L)).send();
        // 部署完成后返回合约地址
        //geth.minerStop().send();
        return idus.getContractAddress();
    }
    public void addPatient(Patient pa,String addr) {
        try {
            Idus idus=Idus.load(addr,web3j,credentials,BigInteger.valueOf(22000000000L), BigInteger.valueOf(4300000L));
            System.out.println("addPatient_isValid"+idus.isValid());
            mine();
            TransactionReceipt x=idus.addPatientToStore(pa.pno,pa.pname,pa.identity,pa.psexdes,pa.totalcost+"").send();
            System.out.println(x);
            //mine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Patient selectPatient(String pno,String addr) {
        Patient pa=new Patient();
        try {
            Idus idus= load(addr,web3j,credentials,BigInteger.valueOf(22000000000L), BigInteger.valueOf(4300000L));
            System.out.println("selectPatient_isValid"+idus.isValid());
            mine();
            List<Type> result = idus.getPatient(pno).send();
            System.out.println(result);
            pa.setPno(result.get(0).toString());
            pa.setPname(result.get(1).toString());
            pa.setIdentity(result.get(2).toString());
            pa.setPsexdes(result.get(3).toString());
            pa.setTotalcost(Double.parseDouble(result.get(4).toString()));
            pa.setNotes("Read Form BlockChain");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pa;
    }
    public void mine() throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request<?, EthBlockNumber> request=geth.ethBlockNumber();
                    int oldblock=Integer.parseInt(request.send().getBlockNumber().toString());
                    System.out.println("MineStart\nTopBlockNumber_OLD="+oldblock);
                    //Admin admin=Admin.build(new HttpService());
                    geth.minerStart(1).send();
                    int newblock=oldblock;
                    while(newblock<oldblock+3){
                        Thread.sleep(Long.parseLong("20"));
                        newblock=Integer.parseInt(geth.ethBlockNumber().send().getBlockNumber().toString());
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
