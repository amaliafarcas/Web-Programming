package com.example.examplejsp.controller;

import com.example.examplejsp.model.Database;
import com.example.examplejsp.model.Product;
import com.example.examplejsp.model.User;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;


public class SearchController extends HttpServlet {

    public SearchController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        String search = request.getParameter("search");
        RequestDispatcher rd = null;

        Database database = new Database();
        List<Product> result = database.getProductsSearch(search);
        HttpSession session = request.getSession();
        session.setAttribute("products", result);
        rd = request.getRequestDispatcher("/profile.jsp");
        rd.forward(request, response);
    }
}
