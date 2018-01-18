package servlets;

import dao.UsersDAO;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet ("/list")
public class ListUsersServlet extends HttpServlet {
    UsersDAO usersDAO = new UsersDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<User> userList = usersDAO.getAllUsers();
            req.setAttribute("listUsers", userList);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("UsersList.jsp");
            requestDispatcher.forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
