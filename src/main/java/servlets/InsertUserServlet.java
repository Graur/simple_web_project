package servlets;

import dao.UsersDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/insert")
public class InsertUserServlet extends HttpServlet {
    UsersDAO usersDAO = new UsersDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            User newUser = new User(name, login, password);
            usersDAO.insertUser(newUser);
            resp.sendRedirect("list");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
