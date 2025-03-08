<!-- filepath: c:\Users\silve\Documents\NetBeansProjects\smit-proovitoo\src\main\webapp\login.jsp -->
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
    <title><fmt:message key="loginTitle" /></title>
</head>
<body>
    <div class="login-container">
        <h2><fmt:message key="loginHeader" /></h2>
        <form action="LoginServlet" method="post">
            <label for="username"><fmt:message key="username" /></label>
            <input type="text" id="username" name="username" required>
            <label for="password"><fmt:message key="password" /></label>
            <input type="password" id="password" name="password" required>
            <button type="submit"><fmt:message key="loginButton" /></button>
        </form>
        <form action="createUser.html" method="get">
            <button type="submit"><fmt:message key="createUserButton" /></button>
        </form>
        <form action="login.jsp" method="get">
            <label for="lang">Language:</label>
            <select id="lang" name="lang" onchange="this.form.submit()">
                <option value="en" <c:if test="${param.lang == 'en'}">selected</c:if>>English</option>
                <option value="et" <c:if test="${param.lang == 'et'}">selected</c:if>>Estonian</option>
            </select>
        </form>
    </div>
</body>
</html>