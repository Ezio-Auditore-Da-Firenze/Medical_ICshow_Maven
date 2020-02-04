package servlet;

import bean.Doctor;
import dao.Dao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Updatedocpan
 */
public class DoctorUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
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
        String dno = request.getParameter("dno");
        String department = request.getParameter("department");
        String departdes = request.getParameter("departdes");
        String position = request.getParameter("position");
        String positiondes = request.getParameter("positiondes");
        String phone = request.getParameter("phone");
        Dao Iperpan = new Dao();
        Doctor doc = new Doctor();
        doc.setDno(dno);
        doc.setDepartment(department);
        doc.setDepartdes(departdes);
        doc.setPosition(position);
        doc.setPositiondes(positiondes);
        doc.setPhone(phone);
        PrintWriter out = response.getWriter();
        boolean adddoc = Iperpan.updateDoctor(doc);
        out.print(adddoc);
        out.flush();
        out.close();
    }

}
