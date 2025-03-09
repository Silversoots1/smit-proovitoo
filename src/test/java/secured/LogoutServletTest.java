package secured;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class LogoutServletTest {

    @Test
    public void testProcessRequestWithSession() throws IOException, ServletException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(request.getSession(false)).thenReturn(session);

        LogoutServlet servlet = new LogoutServlet();
        servlet.processRequest(request, response);

        Mockito.verify(session).invalidate();
        Mockito.verify(response).sendRedirect("login.jsp");
    }

}