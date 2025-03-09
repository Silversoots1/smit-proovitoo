package login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.sql.SQLException;

public class CreateUserServletTest {

    private CreateUserServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;

    @BeforeEach
    public void setUp() {
        servlet = new CreateUserServlet();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
    }

    @Test
    public void testProcessRequestPasswordsDoNotMatch() throws IOException, ServletException {
        Mockito.when(request.getParameter("username")).thenReturn("testuser");
        Mockito.when(request.getParameter("password")).thenReturn("password1");
        Mockito.when(request.getParameter("confirm_password")).thenReturn("password2");
        Mockito.when(request.getRequestDispatcher("createUser.jsp")).thenReturn(dispatcher);

        servlet.processRequest(request, response);

        Mockito.verify(request).setAttribute("error_message", "passwords_match_error");
        Mockito.verify(dispatcher).forward(request, response);
    }

    @Test
    public void testProcessRequestUserCreationFailure() throws IOException, ServletException, SQLException {
        Mockito.when(request.getParameter("username")).thenReturn("testuser");
        Mockito.when(request.getParameter("password")).thenReturn("password1");
        Mockito.when(request.getParameter("confirm_password")).thenReturn("password1");
        Mockito.when(request.getRequestDispatcher("createUser.jsp")).thenReturn(dispatcher);

        LoginUtils loginUtils = Mockito.mock(LoginUtils.class);
        
        CreateUserServlet servletSpy = Mockito.spy(servlet);
        
        Mockito.doThrow(new SQLException()).when(loginUtils).createUser("testuser", "password1");

        servletSpy.processRequest(request, response);

        Mockito.verify(request).setAttribute("error_message", "user_creation_failed");
        Mockito.verify(dispatcher).forward(request, response);
    }
}