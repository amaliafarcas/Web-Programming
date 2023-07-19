package com.example.examplejsp.controller;

import com.example.examplejsp.model.Database;
import com.example.examplejsp.model.Product;
import com.example.examplejsp.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 25 * 1024 * 1024)
public class AddProductsController extends HttpServlet {
    public AddProductsController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        String[] productName = request.getParameterValues("productName[]");
        String[] productDescription = request.getParameterValues("productDescription[]");

        Database database = new Database();
        RequestDispatcher rd = null;
        boolean result = database.addProduct(productName, productDescription);
        if (result) {
            rd = request.getRequestDispatcher("/profile.jsp?message=successAdd");
            List<Product> products = database.getProducts();
            request.getSession().setAttribute("products", products);
        } else {
            rd = request.getRequestDispatcher("/profile.jsp?message=errorAdd");
        }
        rd.forward(request, response);
    }


}
