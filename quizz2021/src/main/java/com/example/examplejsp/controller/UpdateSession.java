package com.example.examplejsp.controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class UpdateSession extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the JSON data from the request
        BufferedReader reader = request.getReader();

        String jsonData = reader.lines().collect(Collectors.joining());

        // Store the array in the session attribute "viewedFiles"
        request.getSession().setAttribute("viewedFiles", jsonData);
    }
}
