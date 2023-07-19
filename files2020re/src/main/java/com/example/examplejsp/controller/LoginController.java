package com.example.examplejsp.controller;

import com.example.examplejsp.model.Database;
import com.example.examplejsp.model.File;
import com.example.examplejsp.model.User;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;


public class LoginController extends HttpServlet {

    public LoginController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RequestDispatcher rd = null;

        Database database = new Database();
        String result = database.authenticate(username, password);
        if (result.equals("success")) {
            rd = request.getRequestDispatcher("/profile.jsp");
            User user = database.getUserByUsername(username);
             HttpSession session = request.getSession();
             session.setAttribute("user", user);
             session.setAttribute("recordsPerPage", 2);
             session.setAttribute("currentPage", 1);
             session.setAttribute("viewedFiles", null);

            List<File> resultSet = database.getUserFiles(user, 2, 1);
            session.setAttribute("currentPageResults", resultSet);
        } else {
            rd = request.getRequestDispatcher("/login.jsp?message=incorrectCredentials");
        }
        rd.forward(request, response);
    }
}
