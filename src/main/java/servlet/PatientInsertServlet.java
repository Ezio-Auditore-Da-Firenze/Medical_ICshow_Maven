package servlet;

import bean.Doctor;
import bean.Patient;
import dao.Dao;
import dao.DaoInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servlet implementation class Addpan
 */
public class PatientInsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset:utf-8");
        PrintWriter out = response.getWriter();

        String pno = request.getParameter("pno");
        String pname = request.getParameter("pname");
        String identity = request.getParameter("identity");
        String age = request.getParameter("age");
        String psex = request.getParameter("psex");
        String psexdes = request.getParameter("psexdes");
        String dname = request.getParameter("dname");
        String department = request.getParameter("department");
        String departdes = request.getParameter("departdes");
        String totalcost = request.getParameter("totalcost");
        String treatdate = request.getParameter("treatdate");
        String arrivedate = request.getParameter("arrivedate");
        String notes = request.getParameter("notes");
        Patient pa = new Patient();
        pa.setPno(pno);
        pa.setPname(pname);
        pa.setIdentity(identity);
        pa.setAge(Integer.parseInt(age));
        pa.setPsex(Integer.parseInt(psex));
        pa.setPsexdes(psexdes);
        pa.setDname(dname);
        pa.setDepartment(department);
        pa.setDepartdes(departdes);
        pa.setTotalcost(Double.parseDouble(totalcost));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date0 = df.parse(treatdate);
            pa.setTreatdate(date0);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        try {
            Date date1 = df.parse(arrivedate);
            pa.setArrivedate(date1);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        pa.setNotes(notes);
        DaoInterface Iperpan = new Dao();
        boolean pas = Iperpan.addPatient(pa);
        if (pas = true) {
            Doctor chadoc = Iperpan.selectDoctorNumber(dname);
            int co = chadoc.getCount();
            co += 1;
            Iperpan.updatedoccount(dname, co);
        }
        out.print(pas);
        out.flush();
        out.close();

    }

}
