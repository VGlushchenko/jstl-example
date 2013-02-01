package com.in6k.servlet;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.in6k.entity.User;

public class Servlet extends HttpServlet {
    private static final String NAME_PATTERN = "^[a-z0-9_-]{3,15}$";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_PATTERN = "^[a-z0-9_-]{3,8}$";
    private static final String BIRTH_DATE_PATTERN = "^[a-z0-9_-]{3,15}$";
    private String errorLogs = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File dir = new File("/home/employee/xml");
        File [] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".xml");
            }
        });

        for(File file : files) {

            FileInputStream fstream = new FileInputStream(file);
            XMLDecoder xmlDecoder = new XMLDecoder(fstream);
            User user = (User)xmlDecoder.readObject();

            xmlDecoder.close();

            request.setAttribute("firstName", user.getFirstName());
            request.setAttribute("lastName", user.getLastName());
            request.setAttribute("email", user.getEmail());
            request.setAttribute("password", user.getPassword());
            request.setAttribute("birth_date", user.getBirthDate());

            request.getRequestDispatcher("/List.jsp").include(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String birth_date = request.getParameter("birth_date");

        if(isValidForm(request)) {
            User user = new User();

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            user.setBirthDate(birth_date);

            try {
                FileOutputStream fos = new FileOutputStream("/home/employee/xml/user_" + firstName + ".xml");

                try {
                    XMLEncoder xmlEncoder = new XMLEncoder(fos);

                    try {
                        xmlEncoder.writeObject(user);
                        xmlEncoder.flush();
                    }
                    finally {
                        xmlEncoder.close();
                    }
                } finally {
                    fos.close();
                }
            }
            catch (Exception ex) {
                System.out.println(ex);
            }

            request.getRequestDispatcher("/succes.jsp").include(request, response);
        }
        else {
            request.setAttribute("errorLogs", errorLogs);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
            request.setAttribute("birth_date", birth_date);

            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }

    protected boolean isValid(String value, String pattern) {
        return value.matches(pattern);
    }

    protected boolean isValidForm(HttpServletRequest request){

        if (!isValid(request.getParameter("firstName"), NAME_PATTERN)) {
            errorLogs += "Name is not valid <br>";
        }
        else if (!isValid(request.getParameter("lastName"), NAME_PATTERN)) {
            errorLogs += "Last name is not valid <br>";
        }
        else if (!isValid(request.getParameter("email"), EMAIL_PATTERN)) {
            errorLogs += "Email name is not valid <br>";
        }
		else if (isValid(request.getParameter("password"), PASSWORD_PATTERN) && request.getParameter("password") == request.getParameter("confirmPassword")) {
			errorLogs += "Password is not valid <br>";
		}
        else if (!isValid(request.getParameter("birth_date"), BIRTH_DATE_PATTERN)) {
            errorLogs += "Birth date is not valid <br>";
        }

        return errorLogs.isEmpty();
    }

}
