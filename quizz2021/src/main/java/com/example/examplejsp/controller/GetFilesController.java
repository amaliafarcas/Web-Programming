package com.example.examplejsp.controller;

import com.example.examplejsp.model.Database;
import com.example.examplejsp.model.File;
import com.example.examplejsp.model.User;
import com.google.gson.JsonObject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.ceil;

public class GetFilesController extends HttpServlet {

    public GetFilesController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {


        String step = request.getParameter("step");

        HttpSession session = request.getSession();
        int recordsPerPage = (int) session.getAttribute("recordsPerPage");
        int currentPage = (int) session.getAttribute("currentPage");
        User user = (User) session.getAttribute("user");

        Database database = new Database();
        int totalRecords = database.getCountUserFiles(user);
        int totalPages = (int) ceil((double) totalRecords/recordsPerPage);

        if(Objects.equals(step, "prev") && currentPage>1){
            currentPage--;
            session.setAttribute("currentPage", currentPage);
            List<File> resultSet = database.getUserFiles(user, recordsPerPage, currentPage);
            session.setAttribute("currentPageResults", resultSet);
        } else if (Objects.equals(step, "next") && currentPage<totalPages) {
            currentPage++;
            session.setAttribute("currentPage", currentPage);
            List<File> resultSet = database.getUserFiles(user, recordsPerPage, currentPage);
            session.setAttribute("currentPageResults", resultSet);
        }

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("/profile.jsp");
        rd.forward(request, response);
    }

}
