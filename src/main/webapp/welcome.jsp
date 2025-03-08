<!-- filepath: c:\Users\silve\Documents\NetBeansProjects\smit-proovitoo\src\main\webapp\welcome.jsp -->
<%@ page import="secured.UserDetails" %>
<%@ page import="secured.CarOptionHtml" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles.css">
    <fmt:setLocale value="${param.lang != null ? param.lang : 'en'}" />
    <fmt:setBundle basename="messages" />
    <title><fmt:message key="title" /></title>
</head>
<body>
    <div class="container">
        <h1><fmt:message key="header" /></h1>

        <form action="welcome.jsp" method="get">
            <label for="lang">Language:</label>
            <select id="lang" name="lang" onchange="this.form.submit()">
                <option value="en" <c:if test="${param.lang == 'en'}">selected</c:if>>English</option>
                <option value="et" <c:if test="${param.lang == 'et'}">selected</c:if>>Estonian</option>
            </select>
        </form>

        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
            <div class="error-message"><fmt:message key="error" /></div>
        <%
            }

            UserDetails userDetails = (UserDetails) request.getAttribute("userDetails");
            String name = userDetails != null ? userDetails.getName() : "";
            String phone = userDetails != null ? userDetails.getPhone() : "";
            String carIds = userDetails != null ? userDetails.getCarIds() : "";
            boolean hasLicense = userDetails != null && userDetails.isHasLicense();
        %>

        <form action="saveDetails" method="post">
            <label for="name"><fmt:message key="name" /></label>
            <input type="text" id="name" name="name" value="<%= name %>" required>

            <label for="phone"><fmt:message key="phone" /></label>
            <input type="text" id="phone" name="phone" value="<%= phone %>" required>

            <label for="cars"><fmt:message key="cars" /></label>
            <%
                CarOptionHtml car_options_html_class = new CarOptionHtml();
                String car_options_html = car_options_html_class.buildCarOptionsHtml(carIds);
                out.print(car_options_html);
            %>

            <label for="license">
                <span><fmt:message key="license" /></span>
                <input class="checkbox" type="checkbox" id="license" name="license" <%= hasLicense ? "checked" : "" %>>
            </label>
            <input type="submit" value="<fmt:message key='save' />">
        </form>

        <form action="logout" method="post">
            <button type="submit"><fmt:message key="logout" /></button>
        </form>
    </div>
</body>
</html>