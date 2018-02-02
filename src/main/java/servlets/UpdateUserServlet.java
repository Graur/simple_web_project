package servlets;

import dao.UsersDaoHibernateImpl;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdateUserServlet extends HttpServlet {
    private UsersDaoHibernateImpl usersHibernateDAO = new UsersDaoHibernateImpl();

    public UpdateUserServlet() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            User updatedUser = new User(id, name, login, password);
            usersHibernateDAO.updateUser(updatedUser);
            resp.sendRedirect("list");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            int id = Integer.parseInt(req.getParameter("id"));
            User existingUser = usersHibernateDAO.getUser(id);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("UserForm.jsp");
            req.setAttribute("user", existingUser);
            requestDispatcher.forward(req, resp);

        }
}
