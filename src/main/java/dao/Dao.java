package dao;

import bean.Doctor;
import bean.Patient;
import jdbc.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao implements DaoInterface {

    DBUtil j = new DBUtil();

    @Override
    public Doctor select(String id) {
        String sql = "select self_id,self_name,self_type,par1 from aisr.sr_self where sr_type_id=?";
        Object[] obj = {id};
        ResultSet cha = j.cx(sql, obj);
        Doctor self = null;
        try {
            while (cha.next()) {
                self = new Doctor();

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return self;
    }

    @Override
    public List<Doctor> selall(String id) {
        String sql = "select dno,dname,dsex,dsexdes,department,departdes,position,positiondes,phone,count from doctorinfo where dname like ?";
        Object[] obj = {"%" + id + "%"};
        ResultSet cha = j.cx(sql, obj);
        Doctor self = null;
        List<Doctor> list = new ArrayList<Doctor>();
        try {
            while (cha.next()) {
                self = new Doctor();
                self.setDno(cha.getString("dno"));
                self.setDname(cha.getString("dname"));
                self.setDsex(cha.getInt("dsex"));
                self.setDsexdes(cha.getString("dsexdes"));
                self.setDepartment(cha.getString("department"));
                self.setDepartdes(cha.getString("departdes"));
                self.setPositiondes(cha.getString("positiondes"));
                self.setPosition(cha.getString("position"));
                self.setPhone(cha.getString("phone"));
                self.setCount(cha.getInt("count"));

                list.add(self);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Doctor> seljinquebyeid(String id) {
        // TODO Auto-generated method stub
        String sql = "select dno,dname,dsex,department,position,phone,count from doctorinfo where dname = ?";
        Object[] obj = {id};
        ResultSet cha = j.cx(sql, obj);
        Doctor self = null;
        List<Doctor> list = new ArrayList<Doctor>();
        try {
            while (cha.next()) {
                self = new Doctor();
                self.setDno(cha.getString("dno"));
                self.setDname(cha.getString("dname"));
                self.setDsex(cha.getInt("dsex"));
                self.setDepartment(cha.getString("department"));
                self.setPosition(cha.getString("position"));
                self.setPhone(cha.getString("phone"));
                self.setCount(cha.getInt("count"));

                list.add(self);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Patient> chadocbing(String id) {
        // TODO Auto-generated method stub
        String sql = "select pno,pname,identity,psex,psexdes,age,dname,department,departdes,totalcost,treatdate,arrivedate,notes from patientinfo where dname like ?";
        Object[] obj = {"%" + id + "%"};
        ResultSet cha = j.cx(sql, obj);
        Patient self = null;
        List<Patient> list = new ArrayList<Patient>();
        try {
            while (cha.next()) {
                self = new Patient();
                self.setPno(cha.getString("pno"));
                self.setPname(cha.getString("pname"));
                self.setIdentity(cha.getString("identity"));
                self.setPsex(cha.getInt("psex"));
                self.setPsexdes(cha.getString("psexdes"));
                self.setAge(cha.getInt("age"));
                self.setDname(cha.getString("dname"));
                self.setDepartment(cha.getString("department"));
                self.setDepartdes(cha.getString("departdes"));
                self.setTotalcost(cha.getDouble("totalcost"));
                self.setTreatdate(cha.getDate("treatdate"));
                self.setArrivedate(cha.getDate("arrivedate"));
                self.setNotes(cha.getString("notes"));
                list.add(self);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean addDoctor(Doctor doc) {
        boolean ifsuc = false;
        String sql2 = "insert into doctorinfo(dno,dname,dsex,dsexdes,department,departdes,position,positiondes,phone,count) value(?,?,?,?,?,?,?,?,?,?)";
        Object[] obj = {doc.getDno(), doc.getDname(), doc.getDsex(), doc.getDsexdes(), doc.getDepartment(), doc.getDepartdes(), doc.getPosition(), doc.getPositiondes(), doc.getPhone(), doc.getCount()};
        int zsg = j.zsg(sql2, obj);
        if (zsg > 0) {
            ifsuc = true;
        }
        return ifsuc;
    }

    @Override
    public boolean deleteDoctor(String docid) {
        boolean ifsuc = false;
        String sql2 = "delete from doctorinfo where dno in(?";
        String id[] = docid.split("&");
        for (int i = 0; i < id.length - 1; i++) {
            sql2 += ",?";

        }
        sql2 += ")";
        Object[] obj = id;
        int zsg = j.zsg(sql2, obj);
        if (zsg > 0) {
            ifsuc = true;
        }
        return ifsuc;
    }

    @Override
    public boolean updateDoctor(Doctor doc) {
        boolean ifsuc = false;
        String sql2 = "update doctorinfo set department=?,departdes=?,position=?,positiondes=?,phone=? where dno=?";
        Object[] obj = {doc.getDepartment(), doc.getDepartdes(), doc.getPosition(), doc.getPositiondes(), doc.getPhone(), doc.getDno()};
        int zsg = j.zsg(sql2, obj);
        if (zsg > 0) {
            ifsuc = true;
        }
        return ifsuc;
    }

    @Override
    public boolean deletePatient(String docid) {
        boolean ifsuc = false;
        String sql2 = "delete from patientinfo where pno in(?";
        String id[] = docid.split("&");
        for (int i = 0; i < id.length - 1; i++) {
            sql2 += ",?";

        }
        sql2 += ")";
        Object[] obj = id;
        int zsg = j.zsg(sql2, obj);
        if (zsg > 0) {
            ifsuc = true;
        }
        return ifsuc;
    }

    @Override
    public boolean addPatient(Patient doc) {
        boolean ifsuc = false;

        String sql2 = "insert into patientinfo(pno,pname,identity,age,psex,psexdes,dname,department,departdes,totalcost,treatdate,arrivedate,notes) value(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] obj = {doc.getPno(), doc.getPname(), doc.getIdentity(), doc.getAge(), doc.getPsex(), doc.getPsexdes(), doc.getDname(), doc.getDepartment(), doc.getDepartdes(), doc.getTotalcost(), doc.getTreatdate(), doc.getArrivedate(), doc.getNotes()};
        int zsg = j.zsg(sql2, obj);
        if (zsg > 0) {
            ifsuc = true;
        }
        return ifsuc;
    }

    @Override
    public Doctor selectDoctorNumber(String dname) {
        String sql = "select count from doctorinfo where dname = ?";
        Object[] obj = {dname};
        ResultSet cha = j.cx(sql, obj);
        Doctor self = null;
        try {
            while (cha.next()) {
                self = new Doctor();
                self.setCount(cha.getInt("count"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return self;
    }

    @Override
    public boolean updatedoccount(String dname, int count) {
        boolean ifsuc = false;
        String sql2 = "update doctorinfo set count=? where dname=?";
        Object[] obj = {count, dname};
        int zsg = j.zsg(sql2, obj);
        if (zsg > 0) {
            ifsuc = true;
        }
        return ifsuc;
    }

    @Override
    public String selectIntelligentcontract(){
        String sql = "select * from intelligentcontract";
        ResultSet cha = j.cx(sql);
        String addr=null;
        try {
            while (j.rs.next()) {
                addr=j.rs.getString("addr");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return addr;
    }

    @Override
    public boolean addIntelligentcontract(String addr) {
        boolean ifsuc = false;

        String sql2 = "insert into intelligentcontract(addr) value(?)";
        Object[] obj = {addr};
        int zsg = j.zsg(sql2, obj);
        if (zsg > 0) {
            ifsuc = true;
        }
        return ifsuc;
    }


}
