package secured;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import db.DatabaseUtils;

@WebServlet(name = "WelcomeServlet", urlPatterns = {"/WelcomeServlet"})
public class Welcome extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
        String lang = request.getParameter("lang");

        if (lang != null) {
            session.setAttribute("lang", lang);
        } else {
            session.setAttribute("lang", "en");
        }

        try {
            UserDetails user_details = getUserDetails(username);
            request.setAttribute("user_details", user_details);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("welcome.jsp").forward(request, response);
    }

    private UserDetails getUserDetails(String username) throws SQLException {
        String sql = "SELECT name, phone, car_ids, has_license FROM user_details WHERE username = ?";
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String phone = resultSet.getString("phone");
                    String carIds = resultSet.getString("car_ids");
                    boolean hasLicense = resultSet.getBoolean("has_license");
                    return new UserDetails(name, phone, carIds, hasLicense);
                } else {
                    return null;
                }
            }
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