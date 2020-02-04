package bean;

import java.io.Serializable;
import java.util.Date;

public class Patient implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public String pno;
    public String pname;
    public String identity;
    public int psex;
    public String psexdes;
    public int age;
    public String dname;
    public String departdes;
    public String department;
    public double totalcost;
    public Date treatdate;
    public Date arrivedate;
    public String notes;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int getPsex() {
        return psex;
    }

    public void setPsex(int psex) {
        this.psex = psex;
    }

    public String getPsexdes() {
        return psexdes;
    }

    public void setPsexdes(String psexdes) {
        this.psexdes = psexdes;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDepartdes() {
        return departdes;
    }

    public void setDepartdes(String departdes) {
        this.departdes = departdes;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(double totalcost) {
        this.totalcost = totalcost;
    }

    public Date getTreatdate() {
        return treatdate;
    }

    public void setTreatdate(Date treatdate) {
        this.treatdate = treatdate;
    }

    public Date getArrivedate() {
        return arrivedate;
    }

    public void setArrivedate(Date arrivedate) {
        this.arrivedate = arrivedate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
