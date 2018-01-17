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

@WebServlet("/")
public class ControllerServlet extends HttpServlet {
    private UsersDAO usersDAO;

    public void init() {
//        String jdbcURL = "jdbc:mysql://localhost:3306/users";
//        String jdbcUsername = "root";
//        String jdbcPassword = "root";

        usersDAO = new UsersDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getContextPath() + req.getServletPath();

        try {
            if (action.equals("/new")) {
                showNewForm(req, resp);
            } else if (action.equals("/insert")) {
                insertUser(req, resp);
            } else if (action.equals("/delete")) {
                deleteUser(req, resp);
            } else if (action.equals("/update")) {
                updateUser(req, resp);
            } else if (action.equals("/edit")) {
                showEditForm(req, resp);
            } else {
                listUsers(req, resp);
            }
        } catch (SQLException e){
            throw new ServletException(e);
        }
    }

    private void listUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<User> userList = usersDAO.listAllUsers();
        req.setAttribute("listUsers", userList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("UsersList.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("UserForm.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        User existingUser = usersDAO.getUser(id);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("UserForm.jsp");
        req.setAttribute("user", existingUser);
        requestDispatcher.forward(req, resp);
    }

    private void insertUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User newUser = new User(name, login, password);
        usersDAO.insertUser(newUser);
        resp.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User updatedUser = new User(id, name, login, password);
        usersDAO.updateUser(updatedUser);
        resp.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        User deletedUser = new User(id);
        usersDAO.deleteUser(deletedUser);
        resp.sendRedirect("list");
    }

}
