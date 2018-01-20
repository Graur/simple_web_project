package servlets;

import dao.SQL_Connection;
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

@WebServlet ("/")
public class ListUsersServlet extends HttpServlet {
    private UsersDAO usersDAO = new UsersDAO(SQL_Connection.getDBConnection());

    public ListUsersServlet() throws SQLException {
    }

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
