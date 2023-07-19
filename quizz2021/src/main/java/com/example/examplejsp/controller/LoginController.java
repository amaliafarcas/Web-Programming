package com.example.examplejsp.controller;

import com.example.examplejsp.model.Database;
import com.example.examplejsp.model.File;
import com.example.examplejsp.model.Question;
import com.example.examplejsp.model.User;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LoginController extends HttpServlet {

    public LoginController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        String username = request.getParameter("username");
        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        Database db = new Database();
        List<Question> questions = db.getQuestions();
        session.setAttribute("questions", questions);

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/profile.jsp");

        rd.forward(request, response);
    }
}
