package com.example.examplejsp.controller;

import com.example.examplejsp.model.Database;
import com.example.examplejsp.model.Order;
import com.example.examplejsp.model.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class GetOrdersController extends HttpServlet {

    public GetOrdersController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {


        RequestDispatcher rd = null;

        Database database = new Database();
        String username = (String) request.getSession().getAttribute("username");
        List<Order> result = database.getUserOrders(username);
        HttpSession session = request.getSession();
        session.setAttribute("orderHistory", result);
        rd = request.getRequestDispatcher("/orderHistory.jsp");
        rd.forward(request, response);
    }
}
