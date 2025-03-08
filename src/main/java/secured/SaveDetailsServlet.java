package secured;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import db.DatabaseUtils;

@WebServlet(name = "SaveDetailsServlet", urlPatterns = {"/saveDetails"})
public class SaveDetailsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String[] carIds = request.getParameterValues("cars");
        boolean hasLicense = request.getParameter("license") != null;

        try {
            saveDetails(name, phone, carIds, hasLicense);
            response.sendRedirect("success.html");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }

    private void saveDetails(String name, String phone, String[] carIds, boolean hasLicense) throws SQLException {
        String sql = "INSERT INTO user_details (name, phone, car_id, has_license) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (String carId : carIds) {
                statement.setString(1, name);
                statement.setString(2, phone);
                statement.setInt(3, Integer.parseInt(carId));
                statement.setBoolean(4, hasLicense);
                statement.addBatch();
            }
            statement.executeBatch();
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