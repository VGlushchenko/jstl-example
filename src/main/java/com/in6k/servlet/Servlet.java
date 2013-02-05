package com.in6k.servlet;

import java.io.*;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.in6k.domain.EmployeeModel;
import com.in6k.form.EmployeeForm;
import com.in6k.persistence.Identifier;
import com.in6k.persistence.ProviderFactory;

public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<Identifier> employeeList = null;

        String type = request.getParameter("providertype");
        ProviderFactory.ProviderType providerType = (type.equals("XML")) ? ProviderFactory.ProviderType.XML : ProviderFactory.ProviderType.DB;

        try {
            employeeList = (new EmployeeModel(providerType)).load();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	request.setAttribute("employers", employeeList);

    	request.getRequestDispatcher("/List.jsp").include(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EmployeeForm ef = new EmployeeForm();

        ef.setId(request.getParameter("id"));
    	ef.setName(request.getParameter("name"));
    	ef.setLastName(request.getParameter("lastName"));
		ef.setEmail(request.getParameter("email"));
		ef.setPassword(request.getParameter("password"));
		ef.setPasswordConfirmation(request.getParameter("passwordConfirmation"));
		ef.setBirthDate(request.getParameter("birthDate"));

        if (request.getParameter("id") == null) {

        List<String> errors = ef.validate();
        boolean HAS_ERROR = errors.size() > 0;

        if (HAS_ERROR) {
            request.setAttribute("form", ef);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/employeeform.jsp").include(request, response);
            return;
        }

            String type = request.getParameter("providertype");

            ProviderFactory.ProviderType providerType = (type.equals("XML")) ? ProviderFactory.ProviderType.XML : ProviderFactory.ProviderType.DB;

            EmployeeModel em = new EmployeeModel(ef, providerType);
            em.save();

            request.setAttribute("form", em);
        }
        else {

            String type = request.getParameter("providertype");

            ProviderFactory.ProviderType providerType = (type.equals("XML")) ? ProviderFactory.ProviderType.XML : ProviderFactory.ProviderType.DB;

            EmployeeModel em = new EmployeeModel(ef, providerType);
            try {
                em.update(Integer.parseInt(request.getParameter("id")), em);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("form", em);
        }




        request.getRequestDispatcher("/success.jsp").include(request, response);
    }

    

}
