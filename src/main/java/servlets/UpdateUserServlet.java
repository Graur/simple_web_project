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

@WebServlet("/update")
public class UpdateUserServlet extends HttpServlet {
    UsersDAO usersDAO = new UsersDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            User updatedUser = new User(id, name, login, password);
            usersDAO.updateUser(updatedUser);
            resp.sendRedirect("list");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
