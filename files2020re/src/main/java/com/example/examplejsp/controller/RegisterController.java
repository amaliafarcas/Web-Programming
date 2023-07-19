package com.example.examplejsp.controller;

import com.example.examplejsp.model.Database;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 25 * 1024 * 1024)
public class RegisterController extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "profile_pictures";

    public RegisterController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String hometown = request.getParameter("hometown");

        // Retrieve the profile picture
        Part filePart = request.getPart("profilePicture");

        RequestDispatcher rd = null;
        boolean result = saveUserRegistration(username, password, name, email, address, hometown, filePart);
        if (result) {
            rd = request.getRequestDispatcher("/login.jsp?message=successRegister");
        } else {
            rd = request.getRequestDispatcher("/register.jsp?message=errorRegister");
        }
        rd.forward(request, response);
    }

    private boolean validateRegistrationData(String username, String password, String name, String email, String address, String hometown, Part filePart) {
        // Perform validation on the registration data (e.g., check for required fields, password strength, etc.)
        // You can customize this method based on your specific validation requirements
        // For example, you can check if the username is unique, validate email format, etc.
        // Ensure that the filePart contains a valid profile picture
        return (username != null && !username.isEmpty()
                && password != null && !password.isEmpty()
                && address != null && !address.isEmpty()
                && name != null && !name.isEmpty()
                && email != null && !email.isEmpty()
                && hometown != null && !hometown.isEmpty()
                && filePart != null && filePart.getSize() > 0);
    }

    private boolean saveUserRegistration(String username, String password, String name, String email, String address, String hometown, Part filePart) throws IOException {

        if(validateRegistrationData(username, password, name, email, address, hometown, filePart)){
            //System.out.println("ggg");
            // Create the upload directory if it doesn't exist
            String applicationPath = getServletContext().getRealPath("");
            String uploadPath = applicationPath + File.separator + UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Process the uploaded profile picture
            String fileName = UUID.randomUUID().toString() + getFileName(filePart);
            String uploadedFileName = fileName;
            System.out.println("file" + fileName);
            File filePath = new File(uploadDir, fileName);
            filePart.write(String.valueOf(filePath));

// Get the file name with separators
            Database database = new Database();
            String result = database.register(username, password, name, email, address, hometown, uploadedFileName);

            return result.equals("success");
        }
        return false;
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
