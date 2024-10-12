package com.webapp;

import com.g3app.controller.PaymentServlet;
import com.g3app.dao.DBManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class PaymentServletTest {
    private PaymentServlet paymentServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private DBManager dbManager;

    @Before
    public void setUp() {
        paymentServlet = new PaymentServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dbManager = mock(DBManager.class);
    }

    @Test
    public void testAddPayment() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("add");
        when(request.getParameter("cardNumber")).thenReturn("1234567890123456");
        when(request.getParameter("expiryDate")).thenReturn("12/25");
        when(request.getParameter("cardHolderName")).thenReturn("John Doe");

        paymentServlet.doPost(request, response);

        verify(request, times(1)).getParameter("action");
        verify(request, times(1)).getParameter("cardNumber");
        verify(request, times(1)).getParameter("expiryDate");
        verify(request, times(1)).getParameter("cardHolderName");
    }

    @Test
    public void testEditPayment() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("edit");
        when(request.getParameter("paymentId")).thenReturn("1");
        when(request.getParameter("cardNumber")).thenReturn("1234567890123456");
        when(request.getParameter("expiryDate")).thenReturn("12/25");
        when(request.getParameter("cardHolderName")).thenReturn("John Doe");

        paymentServlet.doPost(request, response);

        verify(request, times(1)).getParameter("action");
        verify(request, times(1)).getParameter("paymentId");
        verify(request, times(1)).getParameter("cardNumber");
        verify(request, times(1)).getParameter("expiryDate");
        verify(request, times(1)).getParameter("cardHolderName");
    }

    @Test
    public void testDeletePayment() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("delete");
        when(request.getParameter("paymentId")).thenReturn("1");

        paymentServlet.doPost(request, response);

        verify(request, times(1)).getParameter("action");
        verify(request, times(1)).getParameter("paymentId");
    }
}
