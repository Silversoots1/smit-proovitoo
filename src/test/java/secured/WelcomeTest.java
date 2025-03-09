package secured;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.sql.SQLException;

public class WelcomeTest {

    private Welcome servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;

    @BeforeEach
    public void setUp() {
        servlet = new Welcome();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
    }

    @Test
    public void testProcessRequestWithInvalidSession() throws IOException, ServletException {
        Mockito.when(request.getSession(false)).thenReturn(null);

        servlet.processRequest(request, response);

        Mockito.verify(response).sendRedirect("login.jsp");
    }

    @Test
    public void testProcessRequestWithSQLException() throws IOException, ServletException, SQLException {
        Mockito.when(request.getSession(false)).thenReturn(session);
        Mockito.when(session.getAttribute("username")).thenReturn("testuser");
        Mockito.when(request.getParameter("lang")).thenReturn("en");
        Mockito.when(request.getRequestDispatcher("welcome.jsp")).thenReturn(dispatcher);

        Welcome servletSpy = Mockito.spy(servlet);

        servletSpy.processRequest(request, response);

        Mockito.verify(session).setAttribute("lang", "en");
        Mockito.verify(dispatcher).forward(request, response);
    }
}