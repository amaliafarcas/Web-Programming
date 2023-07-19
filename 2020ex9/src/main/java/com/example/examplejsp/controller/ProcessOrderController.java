package com.example.examplejsp.controller;

import com.example.examplejsp.model.Database;
import com.example.examplejsp.model.Order;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProcessOrderController extends HttpServlet {

    public ProcessOrderController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");
        boolean result = false;
        if(Objects.equals(action, "order")){
            List<Order> cart = (List<Order>) request.getSession().getAttribute("cart");
            Database db = new Database();
            result = db.placeOrder(cart);
        }
        else{
            result=true;
        }

        List<Order> cart = new ArrayList<>();
        request.getSession().setAttribute("cart", cart);
        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/cart.jsp?message=successAdd");

        rd.forward(request, response);
    }
}
