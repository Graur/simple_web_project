package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/user")
public class UserWriterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        UserWriterServlet.printToBrowser(req,resp,login);
        Cookie cookie = new Cookie("userCookie","001");
        resp.addCookie(cookie);
    }

    private static void printToBrowser(HttpServletRequest req, HttpServletResponse resp, String login){
        try {
            Writer out = resp.getWriter();
            out.write("Hello, " + login);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
