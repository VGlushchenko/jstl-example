package com.in6k.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.in6k.domain.EmployeeModel;
import com.in6k.form.EmployeeForm;
import com.in6k.persistence.ProviderFactory;

import java.io.IOException;
import java.sql.SQLException;

public class EditListServlet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if ((request.getParameter("action").equals("edit"))) {
            EmployeeForm ef = new EmployeeForm();

            ef.setId(request.getParameter("id"));
            ef.setName(request.getParameter("name"));
            ef.setLastName(request.getParameter("lastName"));
            ef.setEmail(request.getParameter("email"));
            ef.setBirthDate(request.getParameter("birthDate"));

            /*EmployeeModel em = new EmployeeModel(ProviderFactory.ProviderType.DB);
            try {
                em.getEmployeeById(new Integer(request.getParameter("id")));

            } catch (SQLException e) {
                e.printStackTrace();
            }

            ef.setName(em.getName());
            ef.setLastName(em.getLastName());
            ef.setEmail(em.getEmail());
            ef.setPassword(em.getPassword());
            ef.setBirthDate(em.getBirthDate());*/


            request.setAttribute("form", ef);

            request.getRequestDispatcher("/employeeform.jsp").include(request, response);
        }
        else if ((request.getParameter("action").equals("delete"))) {
            EmployeeModel em = new EmployeeModel(ProviderFactory.ProviderType.DB);

            try {
                em.delete(new Integer(request.getParameter("id")));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.getRequestDispatcher("/List.jsp").include(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/employeeform.jsp").forward(request, response);
    }
}
