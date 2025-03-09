package login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;

public class LoginServletTest {

    private LoginServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;

    @BeforeEach
    public void setUp() {
        servlet = new LoginServlet();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
    }

    @Test
    public void testProcessRequestWithValidSession() throws IOException, ServletException {
        Mockito.when(request.getSession(false)).thenReturn(session);
        Mockito.when(session.getAttribute("username")).thenReturn("testuser");
        Mockito.when(session.getAttribute("page")).thenReturn("somePage.jsp");

        servlet.processRequest(request, response);

        Mockito.verify(response).sendRedirect("somePage.jsp");
    }

    @Test
    public void testProcessRequestWithValidSessionNoPage() throws IOException, ServletException {
        Mockito.when(request.getSession(false)).thenReturn(session);
        Mockito.when(session.getAttribute("username")).thenReturn("testuser");
        Mockito.when(session.getAttribute("page")).thenReturn(null);

        servlet.processRequest(request, response);

        Mockito.verify(response).sendRedirect("WelcomeServlet");
    }

    @Test
    public void testProcessRequestWithInvalidCredentials() throws IOException, ServletException, SQLException, ClassNotFoundException {
        Mockito.when(request.getSession(false)).thenReturn(null);
        Mockito.when(request.getParameter("username")).thenReturn("testuser");
        Mockito.when(request.getParameter("password")).thenReturn("wrongpassword");
        Mockito.when(request.getRequestDispatcher("login.jsp")).thenReturn(dispatcher);

        LoginUtils loginUtils = Mockito.mock(LoginUtils.class);
        Mockito.when(loginUtils.validateUser("testuser", "wrongpassword")).thenReturn(false);

        LoginServlet servletSpy = Mockito.spy(servlet);

        servletSpy.processRequest(request, response);

        Mockito.verify(request).setAttribute("error_message", "invalid_credentials");
        Mockito.verify(dispatcher).forward(request, response);
    }
}