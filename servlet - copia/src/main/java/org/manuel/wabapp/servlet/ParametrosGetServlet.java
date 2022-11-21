package org.manuel.wabapp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/parametros/get")
public class ParametrosGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String saludo = req.getParameter("saludo");
        String nombre = req.getParameter("nombre");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("    <head>");
        out.println("        <meta charset=\"UTF-8\">");
        out.println("        <title>Parámetro Get de la url</title>");
        out.println("    </head>");
        out.println("    </body>");
        out.println("        <h1>Parámetro Get de la url</h1>");
        if(saludo != null && nombre !=null){
            out.println("        <h2>" + saludo + " " +nombre+"</h2>");
        }else if(saludo != null){
            out.println("        <h2>" + saludo + "</h2>");
        }
        out.println("    </body>");
        out.println("</html>");
        out.close();


    }
}
