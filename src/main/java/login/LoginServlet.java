package login;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null) {
            if (session.getAttribute("page") != null) {
                response.sendRedirect(session.getAttribute("page").toString());
            } else {
                response.sendRedirect("WelcomeServlet");
            }
            return;
        }

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Class.forName("com.mysql.cj.jdbc.Driver");
            LoginUtils login_utils = new LoginUtils();

            if (username != null && password != null && login_utils.validateUser(username, password)) {
                if (session != null) {
                    session.invalidate();
                }
                session = request.getSession(true);
                session.setAttribute("username", username);

                response.setHeader("Set-Cookie", "JSESSIONID=" + session.getId() + "; Secure; HttpOnly");

                response.sendRedirect("WelcomeServlet");
            } else {
                request.getRequestDispatcher("login.html").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}