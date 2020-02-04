package bean;

import java.io.Serializable;

public class Doctor implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String dno;
    private String dname;
    private int dsex;
    private String department;
    private String position;
    private String phone;
    private int count;
    private String dsexdes;
    private String departdes;
    private String positiondes;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getDsexdes() {
        return dsexdes;
    }

    public void setDsexdes(String dsexdes) {
        this.dsexdes = dsexdes;
    }

    public String getDepartdes() {
        return departdes;
    }

    public void setDepartdes(String departdes) {
        this.departdes = departdes;
    }

    public String getPositiondes() {
        return positiondes;
    }

    public void setPositiondes(String positiondes) {
        this.positiondes = positiondes;
    }

    public String getDno() {
        return dno;
    }

    public void setDno(String dno) {
        this.dno = dno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public int getDsex() {
        return dsex;
    }

    public void setDsex(int dsex) {
        this.dsex = dsex;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
