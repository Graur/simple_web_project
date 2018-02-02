package servlets;

import dao.UsersDaoHibernateImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteUserServlet extends HttpServlet {
    private UsersDaoHibernateImpl usersHibernateDAO = new UsersDaoHibernateImpl();

    public DeleteUserServlet(){
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            int id = Integer.parseInt(req.getParameter("id"));
            usersHibernateDAO.deleteUser(id);
            resp.sendRedirect("list");
   }
}
