package com.example.examplejsp.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.example.examplejsp.model.Database;
import com.example.examplejsp.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UpdateProfileController extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "profile_pictures";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Retrieve form inputs
        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String town = request.getParameter("town");

        // Process the uploaded profile picture
        Part filePart = request.getPart("profilePicture");
        System.out.println(filePart);
        if(filePart==null){
            System.out.println("null");
        }

        if (validateUpdateData(fullName, email, address, town)) {
            String profilePicture;

            // Update user profile in the database
            Database database = new Database();

            if (filePart == null) {
                User user = (User) request.getSession().getAttribute("user");
                profilePicture = user.getProfilePicture();
            } else {
                profilePicture = uploadProfilePicture(filePart);
            }
            System.out.println(profilePicture);
            boolean success = database.updateUserProfile(username, fullName, email, address, town, profilePicture);

            if (success) {
                // Update the user object in the session with the new profile information
                User user = (User) request.getSession().getAttribute("user");
                user.setFullName(fullName);
                user.setEmail(email);
                user.setAddress(address);
                user.setTown(town);
                user.setProfilePicture(profilePicture);

                // Redirect to the profile page with a success message
                response.sendRedirect("profile.jsp?message=editSuccess");
            }
        } else {
            // Redirect to the profile page with an error message
            response.sendRedirect("editProfile.jsp?message=incorrectFields");
        }
    }

    private boolean validateUpdateData(String name, String email, String address, String hometown) {
        // Perform validation on the registration data (e.g., check for required fields, password strength, etc.)
        // You can customize this method based on your specific validation requirements
        // For example, you can check if the username is unique, validate email format, etc.
        // Ensure that the filePart contains a valid profile picture
        return (address != null && !address.isEmpty() && name != null && !name.isEmpty() && email != null && !email.isEmpty() && hometown != null && !hometown.isEmpty());
    }

    private String uploadProfilePicture(Part filePart) throws IOException {

        String applicationPath = getServletContext().getRealPath("");
        String uploadPath = applicationPath + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Generate a unique file name
        String fileName = UUID.randomUUID() + getFileName(filePart);
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        // Return the file name to be stored in the database
        return fileName;
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");

        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "";
    }
}
