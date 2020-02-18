package ic_ethereum;



import bean.Patient;
//import net.sf.json.JSONObject;
import org.java_websocket.util.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import org.web3j.protocol.geth.Geth;
import org.web3j.protocol.http.HttpService;
import toserver.KeyValue;
import toserver.RequestMethod;
import toserver.ServerLinkInterFace;

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
                    IC_Administration.class.getResource("key_01").getPath()
                    //"F:\\GoWorkSpace\\ether-test\\db\\keystore\\UTC--2020-01-10T13-00-45.976152300Z--1b17a608ca853903c8fada1bacb236bb4ac2e93f"
            );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }
    }
    public String IC_deploy() throws Exception {
        // 部署合约
        mine(3,10);
        Idus idus= Idus.deploy(web3j, credentials, BigInteger.valueOf(22000000000L), BigInteger.valueOf(4300000L)).send();
        // 部署完成后返回合约地址
        return idus.getContractAddress();
    }
    public void addPatient(Patient pa,String addr) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Idus idus=Idus.load(addr,web3j,credentials,BigInteger.valueOf(22000000000L), BigInteger.valueOf(4300000L));
                    //System.out.println("addPatient_isValid"+idus.isValid());
                    mine(1,10);
                    TransactionReceipt x= null;
                    try {
                        x = idus.addPatientToStore(pa.pno,pa.pname,pa.identity,pa.psexdes,pa.totalcost+"").send();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(x.getBlockNumber()+"\nRecording:");
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put(KeyValue.PNO,pa.getPno());
                    jsonObject.put(KeyValue.ADDR,addr);
                    getRecord(jsonObject);
                }
            }).start();
    }
    public Patient selectPatient(String pno,String addr) {
        Patient pa=new Patient();
        try {
            System.out.println(IC_Administration.class.getResource("key_01").getPath());
            Idus idus= load(addr,web3j,credentials,BigInteger.valueOf(22000000000L), BigInteger.valueOf(4300000L));
            System.out.println("selectPatient_isValid"+idus.isValid());
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
    public void mine(int mineNum,int threadNum){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(KeyValue.MINENUM,mineNum+"");
            jsonObject.put(KeyValue.THREADNUM,threadNum+"");
            getMine(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void mineNow() throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request<?, EthBlockNumber> request=geth.ethBlockNumber();
                    int oldblock=Integer.parseInt(request.send().getBlockNumber().toString());
                    System.out.println("MineStart\nTopBlockNumber_OLD="+oldblock);
                    geth.minerStart(1).send();
                    int newblock=oldblock;
                    while(newblock<oldblock+4){
                        newblock=Integer.parseInt(geth.ethBlockNumber().send().getBlockNumber().toString());
                    }
                    geth.minerStop().send();
                    System.out.println("TopBlockNumber_NEW="+newblock+"\nMineStop");
                } catch (IOException  e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void getMine (final JSONObject jsO){
        RequestMethod.RequestMine(this,jsO, new ServerLinkInterFace.onMineCallback() {
            @Override
            public void Success(JSONObject role) {//回调函数
                try {
                    if(role==null) {
                        System.exit(0);
                    }
                   System.out.println(role.getString("minedata"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void Fail(String error) {

            }
            @Override
            public void UnkownError(String unkownerror) {
            }
        });
    }

    public void getRecord (final JSONObject jsO){
        RequestMethod.RequestRecord(this,jsO, new ServerLinkInterFace.onRecordCallback() {
            @Override
            public void Success(JSONObject role) {//回调函数
                try {
                    if(role==null) {
                        System.exit(0);
                    }
                    //JSONObject takedata= null;
                    //takedata = (JSONObject) role.get("");
                    //final byte[]kk=  Base64.decode(takedata.getString("upic"));
                    //mydata.setUpic(kk);
                    System.out.println(role.getString("recorddata"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void Fail(String error) {
            }
            @Override
            public void UnkownError(String unkownerror) {
            }
        });
    }
}
