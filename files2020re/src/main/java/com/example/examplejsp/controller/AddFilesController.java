package com.example.examplejsp.controller;

import com.example.examplejsp.model.Database;
import com.example.examplejsp.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 25 * 1024 * 1024)
public class AddFilesController extends HttpServlet {
    public AddFilesController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = (User) request.getSession().getAttribute("user");

        String[] filename = request.getParameterValues("filename[]");
        String[] filepath = request.getParameterValues("filepath[]");
        String[] size = request.getParameterValues("size[]");

        Database database = new Database();
        RequestDispatcher rd = null;
        boolean result = database.addFile(user.getId(), filename, filepath, size);
        if (result) {
            rd = request.getRequestDispatcher("/profile.jsp?message=successAdd");
        } else {
            rd = request.getRequestDispatcher("/profile.jsp?message=errorAdd");
        }
        rd.forward(request, response);
    }


}
