package dao;

import bean.Doctor;
import bean.Patient;

import java.sql.SQLException;
import java.util.List;

public interface DaoInterface {

    Doctor select(String id);

    List<Doctor> selall(String id);

    List<Doctor> seljinquebyeid(String id);

    List<Patient> chadocbing(String id);

    //新增医生
    boolean addDoctor(Doctor doc);

    //删除医生
    boolean deleteDoctor(String docid);

    //更新医生
    boolean updateDoctor(Doctor docid);

    //删除病人
    boolean deletePatient(String docid);

    //新增病人
    boolean addPatient(Patient docid);

    //查询医生病人数
    Doctor selectDoctorNumber(String dname);

    //更新医生病人数
    boolean updatedoccount(String dname, int count);
    //获取合约地址
    String selectIntelligentcontract();
    List<Patient> selectByPno(String pno);
    boolean addIntelligentcontract(String addr);
}
