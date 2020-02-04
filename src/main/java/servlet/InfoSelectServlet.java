package servlet;

import bean.Doctor;
import bean.Patient;
import com.google.gson.Gson;
import dao.Dao;
import dao.DaoInterface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet implementation class Pwepanservlet
 */
public class InfoSelectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoSelectServlet() {
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
        PrintWriter out = response.getWriter();
        String userid = request.getParameter("userId");
        String signal = request.getParameter("signal");

        System.out.println(userid);
        DaoInterface Iperpan = new Dao();

        Gson gson = new Gson();
        if (signal.equals("init")) {
            List<Doctor> selall = Iperpan.selall(userid);
            String json = gson.toJson(selall);
            System.out.println(json);
            out.print(json);
            out.flush();
            out.close();
        } else if (signal.equals("updadocn")) {
            List<Doctor> selall = Iperpan.seljinquebyeid(userid);
            String json = gson.toJson(selall);
            System.out.println(json);
            out.print(json);
            out.flush();
            out.close();
        } else if (signal.equals("chadocbing")) {
            List<Patient> selall = Iperpan.chadocbing(userid);
            String json = gson.toJson(selall);
            System.out.println(json);
            out.print(json);
            out.flush();
            out.close();
        } else if (signal.equals("selectpan")) {
            List<Patient> selall = Iperpan.chadocbing(userid);
            request.getSession().setAttribute("listG", selall);
            request.getRequestDispatcher("sick.jsp").forward(request, response);
        } else if (signal.equals("select")) {
            List<Doctor> selall = Iperpan.selall(userid);
            request.getSession().setAttribute("listC", selall);
            response.sendRedirect("doc.jsp");
            //request.getRequestDispatcher("doc.jsp").forward(request, response);
        }

    }

}
