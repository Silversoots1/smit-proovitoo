<!-- filepath: c:\Users\silve\Documents\NetBeansProjects\smit-proovitoo\src\main\webapp\welcome.jsp -->
<%@ page import="secured.UserDetails" %>
<%@ page import="secured.CarOptionHtml" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome Page</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h1>Palun sisestage enda kontaktandmed ning automargid, millest olete huvitatud</h1>

        <%
            UserDetails userDetails = (UserDetails) request.getAttribute("userDetails");
            String name = userDetails.getName();
            String phone = userDetails.getPhone();
            String carIds = userDetails.getCarIds();
            boolean hasLicense = userDetails.isHasLicense();
        %>

        <form action="saveDetails" method="post">
            <label for="name">Ees- ja perekonnanimi:</label>
            <input type="text" id="name" name="name" value="<%= name %>" required>

            <label for="phone">Kontakttelefon:</label>
            <input type="text" id="phone" name="phone" value="<%= phone %>" required>

            <label for="cars">Automargid:</label>
            <%
                CarOptionHtml car_options_html_class = new CarOptionHtml();
                String car_options_html = car_options_html_class.buildCarOptionsHtml(carIds);
                out.print(car_options_html);
            %>

            <label for="license">
                <span>Teil on kehtiv juhiluba?</span>
                <input class="checkbox" type="checkbox" id="license" name="license" <%= hasLicense ? "checked" : "" %>>
            </label>
            <input type="submit" value="Salvesta">
        </form>

        <form action="logout" method="post">
            <button type="submit">Logout</button>
        </form>
    </div>
</body>
</html>