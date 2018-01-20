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

@WebServlet("/update")
public class UpdateUserServlet extends HttpServlet {
    private UsersDAO usersDAO = new UsersDAO(SQL_Connection.getDBConnection());

    public UpdateUserServlet() throws SQLException {
    }

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            User existingUser = usersDAO.getUser(id);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("UserForm.jsp");
            req.setAttribute("user", existingUser);
            requestDispatcher.forward(req, resp);
        } catch (SQLException e){
            throw new ServletException(e);
        }
    }
}
