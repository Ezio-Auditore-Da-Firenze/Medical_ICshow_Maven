package service;

import bean.Doctor;
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
    String addr;
    public PatientService() {
        dao=new Dao();
        if(dao.selectIntelligentcontract() == null||dao.selectIntelligentcontract().equals("")){
            System.out.println("No Intelligent Contract,Deploy Now");
            String addr= null;
            try {
                addr = IC_Administration.ICA.IC_deploy();
            } catch (Exception e) {
                e.printStackTrace();
            }
            dao.addIntelligentcontract(addr);
        }else{
            addr=dao.selectIntelligentcontract();
            System.out.println("We Got Intelligent Contract At:"+addr);
        }
    }
    public List<Patient> selectVisitingRecord(String pno){
        //List<Patient> patients = dao.chadocbing(userid);
        List<Patient> patients;
        if(pno.equals("")){
            patients= dao.chadocbing(pno);
        }else{
            patients = dao.selectByPno(pno);
            Patient pa=IC_Administration.ICA.selectPatient(pno,addr);
            patients.add(pa);
        }
        return patients;
    }
    public boolean insertPatient(Patient pa){
        boolean pas = dao.addPatient(pa);
        IC_Administration.ICA.addPatient(pa,addr);
        if (pas = true) {
            Doctor chadoc = dao.selectDoctorNumber(pa.dname);
            int co = chadoc.getCount();
            co += 1;
            dao.updatedoccount(pa.dname, co);
        }
        return pas;
    }
}
