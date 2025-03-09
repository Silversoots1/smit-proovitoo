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

public class SaveDetailsServletTest {

    private SaveDetailsServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;

    @BeforeEach
    public void setUp() {
        servlet = new SaveDetailsServlet();
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
    public void testProcessRequestWithMissingFields() throws IOException, ServletException {
        Mockito.when(request.getSession(false)).thenReturn(session);
        Mockito.when(session.getAttribute("username")).thenReturn("testuser");
        Mockito.when(request.getParameter("name")).thenReturn("");
        Mockito.when(request.getParameter("phone")).thenReturn("1234567890");
        Mockito.when(request.getParameterValues("cars")).thenReturn(new String[]{"1", "2"});
        Mockito.when(request.getParameter("lang")).thenReturn("en");
        Mockito.when(request.getRequestDispatcher("WelcomeServlet?lang=en")).thenReturn(dispatcher);

        servlet.processRequest(request, response);

        Mockito.verify(request).setAttribute("error", "fill_all_fields");
        Mockito.verify(dispatcher).forward(request, response);
    }
}