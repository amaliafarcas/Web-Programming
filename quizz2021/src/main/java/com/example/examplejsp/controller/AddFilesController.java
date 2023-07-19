package com.example.examplejsp.controller;

import com.example.examplejsp.model.Database;
import com.example.examplejsp.model.Question;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 25 * 1024 * 1024)
public class AddFilesController extends HttpServlet {
    public AddFilesController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        String title = request.getParameter("title");
        String[] text = request.getParameterValues("text[]");
        String[] correctAnswer = request.getParameterValues("correctAnswer[]");
        String[] wrongAnswer = request.getParameterValues("wrongAnswer[]");

        Database database = new Database();
        RequestDispatcher rd = null;
        boolean result = database.makeQuiz(title, text, correctAnswer, wrongAnswer);
        if (result) {
            rd = request.getRequestDispatcher("/profile.jsp?message=successAdd");
            List<Question> questions = database.getQuestions();
            request.getSession().setAttribute("questions", questions);
        } else {
            rd = request.getRequestDispatcher("/profile.jsp?message=errorAdd");
        }
        rd.forward(request, response);
    }


}
