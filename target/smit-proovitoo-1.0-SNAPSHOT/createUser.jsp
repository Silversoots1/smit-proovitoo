<!-- filepath: c:\Users\silve\Documents\NetBeansProjects\smit-proovitoo\src\main\webapp\createUser.jsp -->
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
    <title><fmt:message key="create_user_title" /></title>
    <script>
        function validateForm(event) {
            event.preventDefault();
            const username = document.getElementById('username').value;
            const password = document.getElementById('password').value;
            const confirm_password = document.getElementById('confirm_password').value;
            const error_message = document.getElementById('error_message');

            if (password !== confirm_password) {
                error_message.textContent = '<fmt:message key="passwords_match_error"/>';
                return;
            }
            document.getElementById('createUserForm').submit();
        }
    </script>
</head>
<body>
    <div class="container">
        <h1><fmt:message key="create_user_header" /></h1>

        <div id="error_message" class="error-message">
            <c:if test="${not empty error_message}">
                <fmt:message key="${error_message}" />
            </c:if>
        </div>
        <form id="createUserForm" action="CreateUserServlet" method="post" onsubmit="validateForm(event)">
            <label for="username"><fmt:message key="username" /></label>
            <input type="text" id="username" name="username" required>
            <label for="password"><fmt:message key="password" /></label>
            <input type="password" id="password" name="password" required>
            <label for="confirm_password"><fmt:message key="confirm_password" /></label>
            <input type="password" id="confirm_password" name="confirm_password" required>
            <button type="submit"><fmt:message key="create_user_button" /></button>
        </form>

        <form action="login.jsp" method="get">
            <button type="submit"><fmt:message key="back_to_login_button" /></button>
        </form>

        <form action="createUser.jsp" method="get">
            <select id="lang" name="lang" onchange="this.form.submit()">
                <option value="en" <c:if test="${param.lang == 'en'}">selected</c:if>>English</option>
                <option value="et" <c:if test="${param.lang == 'et'}">selected</c:if>>Estonian</option>
            </select>
        </form>
    </div>
</body>
</html>