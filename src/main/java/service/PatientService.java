package service;

import bean.Patient;
import dao.Dao;
import dao.DaoInterface;
import ic_ethereum.IC_Administration;
import ic_ethereum.Idus;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import static ic_ethereum.Idus.deploy;

public class PatientService {
    Dao dao;
    public PatientService(){
        dao=new Dao();
    }
    public List<Patient>selectVisitingRecord(String userid) throws Exception {
        if(dao.selectIntelligentcontract() == null||dao.selectIntelligentcontract().equals("")){
            System.out.println("No Intelligent Contract,Deploy Now");
            String addr=IC_Administration.ICA.IC_deploy();
            dao.addIntelligentcontract(addr);
        }else{
            System.out.println("We Got Intelligent Contract At:"+dao.selectIntelligentcontract());
            //IC_Administration.ICA.mine();
        }
        //System.out.println("223333");
        List<Patient> selall = dao.chadocbing(userid);
        return selall;
    }
}
