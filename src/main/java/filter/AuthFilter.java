package filter;

//@WebFilter(urlPatterns = "/auth",
//        filterName = "AdminFilter",
//        description = "Filter all admin URLs" )
public class AuthFilter {
//    private UsersDAO usersDAO = UsersDaoFactory.getUsersDAO();
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//        System.out.println("start doFilter");
//        final HttpServletRequest req = (HttpServletRequest) request;
//        final HttpServletResponse resp = (HttpServletResponse) response;
//
//        final String login = req.getParameter("login");
//        final String password = req.getParameter("password");
//        System.out.println("Фильтр аутентификации, пользователь::" + login + " " + password);
//
//        String uri = req.getRequestURI();
//        System.out.println("Requested Resource::" + uri);
//
////        Cookie userCookie = null;
////
////        Cookie[] cookies = req.getCookies();
////        for(Cookie cookie : cookies){
////            if(cookie.getName().equals("userCookie")){
////                userCookie = cookie;
////            }
////        }
//
//        final User.ROLE role = getRoleByLoginAndPass(login, password);
////        Cookie cookieSalt = setCookieSaltByRole(userCookie, role);
//        moveToMenu(req, resp, role);
//
//        System.out.println("end doFilter");
//    }
//
//
//    private void moveToMenu(HttpServletRequest req, HttpServletResponse resp, User.ROLE role) throws ServletException, IOException {
//        if (role.equals(User.ROLE.ADMIN)){
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin");
//            requestDispatcher.forward(req, resp);
//            System.out.println("filter role ADMIN");
//        } else if (role.equals(User.ROLE.USER)){
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/user");
//            requestDispatcher.forward(req, resp);
//            System.out.println("filter role User");
//        } else  {
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
//            requestDispatcher.forward(req, resp);
//            System.out.println("filter role Unknown");
//        }



//    private User.ROLE getRoleByLoginAndPass(String login, String password){
//        User.ROLE result;
//
//        if((login.equals("admin")) && (password.equals("admin"))){
//            result = User.ROLE.ADMIN;
//
//        } else {
//            result = User.ROLE.USER;
//            usersDAO.insertUser(new User(login, password));
//
//        }
//
//        return result;
//    }

//    private Cookie setCookieSaltByRole(Cookie userCookie, User.ROLE role) {
//        if(role.equals(User.ROLE.ADMIN)){
//            userCookie.setValue(userCookie.getValue() + "admin");
//        } else {
//            userCookie.setValue(userCookie.getValue() + "user");
//        }
//
//        System.out.println(userCookie.getValue());
//        return userCookie;
//    }
//
//    @Override
//    public void destroy() {
//
//    }
}
