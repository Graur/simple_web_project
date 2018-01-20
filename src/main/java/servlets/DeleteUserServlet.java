package servlets;

import dao.SQL_Connection;
import dao.UsersDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete")
public class DeleteUserServlet extends HttpServlet {
    private UsersDAO usersDAO = new UsersDAO(SQL_Connection.getDBConnection());

    public DeleteUserServlet() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            int id = Integer.parseInt(req.getParameter("id"));

            User deletedUser = new User(id);
            usersDAO.deleteUser(deletedUser);
            resp.sendRedirect("list");

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
