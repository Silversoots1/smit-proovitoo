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

@WebServlet(name = "SaveDetailsServlet", urlPatterns = {"/saveDetails"})
public class SaveDetailsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String username = (String) session.getAttribute("username");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String[] carIds = request.getParameterValues("cars");
        boolean hasLicense = request.getParameter("license") != null;

        String carIdsString = (carIds != null) ? String.join(",", carIds) : "";

        try {
            if (userExists(username)) {
                updateDetails(username, name, phone, carIdsString, hasLicense);
            } else {
                saveDetails(username, name, phone, carIdsString, hasLicense);
            }
            response.sendRedirect("WelcomeServlet");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }

    private boolean userExists(String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM user_details WHERE username = ?";
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
        return false;
    }

    private void saveDetails(String username, String name, String phone, String carIds, boolean hasLicense) throws SQLException {
        String sql = "INSERT INTO user_details (name, phone, car_ids, has_license, username) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, phone);
            statement.setString(3, carIds);
            statement.setBoolean(4, hasLicense);
            statement.setString(5, username);
            statement.executeUpdate();
        }
    }

    private void updateDetails(String username, String name, String phone, String carIds, boolean hasLicense) throws SQLException {
        String sql = "UPDATE user_details SET name = ?, phone = ?, car_ids = ?, has_license = ? WHERE username = ?";
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, phone);
            statement.setString(3, carIds);
            statement.setBoolean(4, hasLicense);
            statement.setString(5, username);
            statement.executeUpdate();
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