package servlet;

import dao.Dao;
import dao.DaoInterface;

import java.io.IOException;
import java.io.PrintWriter;

public class DeleteServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset:utf-8");
        PrintWriter out = response.getWriter();
        String deleteId = request.getParameter("deleteId");
        String signal = request.getParameter("signal");
        DaoInterface Iperpan = new Dao();
        if (signal.equals("docdelete")) {
            boolean deletedocc = Iperpan.deleteDoctor(deleteId);

            out.print(deletedocc);
        } else if (signal.equals("pandelete")) {
            boolean deletedocc = Iperpan.deletePatient(deleteId);

            out.print(deletedocc);
        }
        out.flush();
        out.close();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
