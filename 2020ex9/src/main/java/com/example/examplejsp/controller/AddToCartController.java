package com.example.examplejsp.controller;

import com.example.examplejsp.model.Database;
import com.example.examplejsp.model.Order;
import com.example.examplejsp.model.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class AddToCartController extends HttpServlet {
    public AddToCartController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        int productCode = Integer.parseInt(request.getParameter("code"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String user = (String) request.getSession().getAttribute("username");
        List<Order> cart = (List<Order>) request.getSession().getAttribute("cart");

        cart.add(new Order(user, productCode, quantity));
        request.getSession().setAttribute("cart", cart);

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/cart.jsp?message=successAdd");

        rd.forward(request, response);
    }


}
