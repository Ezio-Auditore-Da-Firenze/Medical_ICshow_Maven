package servlet;

import bean.Doctor;
import dao.Dao;
import dao.DaoInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Addpwepanservlet
 */
public class DoctorInsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset:utf-8");
        PrintWriter out = response.getWriter();
        String dno = request.getParameter("dno");
        String dname = request.getParameter("dname");
        String dsex = request.getParameter("dsex");
        String dsexdes = request.getParameter("dsexdes");
        String department = request.getParameter("department");
        String departdes = request.getParameter("departdes");
        String position = request.getParameter("position");
        String positiondes = request.getParameter("positiondes");
        String phone = request.getParameter("phone");
        String count = request.getParameter("count");
        DaoInterface Iperpan = new Dao();
        Doctor doc = new Doctor();
        doc.setDno(dno);
        doc.setDname(dname);
        doc.setDsex(Integer.parseInt(dsex));
        doc.setDsexdes(dsexdes);
        doc.setDepartment(department);
        doc.setDepartdes(departdes);
        doc.setPosition(position);
        doc.setPositiondes(positiondes);
        doc.setPhone(phone);
        doc.setCount(Integer.parseInt(count));
        boolean adddoc = Iperpan.addDoctor(doc);
        out.print(adddoc);
        out.flush();
        out.close();
    }

}
