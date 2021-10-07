package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
@WebServlet(name = "loginServlet", value = "/loginServlet")
public class LoginServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet doGet Call");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        System.out.println("email = " + email + " password = " + password + " remember = " + remember);

        DBUtil util = new DBUtil();
        boolean status = util.login(email, password, remember, req, resp);
        System.out.println("status = " + status);
        if ( status ) {
            // contact
            resp.sendRedirect( Util.base_url + "admin_dashboard.jsp" );
        } else {
            // login error
            req.setAttribute("error", "Kullanıcı Adı veya Şifre Hatalı!");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin_login.jsp");
            dispatcher.forward(req, resp);
            // resp.sendRedirect( Util.base_url+"login.jsp" );
        }

    }
}*/