package secured;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DatabaseUtils;

public class CarOptionHtml {

    public String buildCarOptionsHtml() {
        List<CarOption> carOptions = getCarOptions();
        
        StringBuilder html = new StringBuilder();
        html.append("<select id=\"cars\" name=\"cars\" multiple=\"\">\n");

        String currentBrand = "";
        String currentSeries = "";

        for (CarOption carOption : carOptions) {
            if (!carOption.getBrand().equals(currentBrand)) {
                if (!currentBrand.isEmpty()) {
                    html.append("</option>\n");
                }
                currentBrand = carOption.getBrand();
                html.append("<option class=\"option-group\" value=\"").append(carOption.getId()).append("\">").append(currentBrand).append("</option>\n");
            }

            if (!carOption.getSeries().equals(currentSeries) && currentSeries != "") {
                currentSeries = carOption.getSeries();
                html.append("<option class=\"option-series\" value=\"").append(carOption.getId()).append("\">").append(currentSeries).append("</option>\n");
            }

            if (!carOption.getModel().isEmpty()) {
                html.append("<option class=\"option-model\" value=\"").append(carOption.getId()).append("\">").append(carOption.getModel()).append("</option>\n");
            }
        }

        if (!currentBrand.isEmpty()) {
            html.append("</option>\n");
        }

        html.append("</select>\n");
        return html.toString();
    }

    private List<CarOption> getCarOptions() {

        List<CarOption> carOptions = new ArrayList<>();
        String sql = "SELECT id, brand, series, model FROM car_options ORDER BY brand, series, model";

        try (Connection connection = DatabaseUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String brand = resultSet.getString("brand");
                String series = resultSet.getString("series");
                String model = resultSet.getString("model");
                carOptions.add(new CarOption(id, brand, series, model));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carOptions;
    }
}