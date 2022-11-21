package servlet.form;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/registro")
public class FormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String pais = req.getParameter("pais");
        String[] lenguajes = req.getParameterValues("lenguajes");
        String[] roles = req.getParameterValues("roles");
        String idioma = req.getParameter("idioma");
        boolean habilitar = req.getParameter("habilitar") != null && req.getParameter("habilitar").equals("on");
        String secreto = req.getParameter("secreto");

        Map<String, String> errores = new HashMap<>();

        if (username == null || username.isBlank()) {
            errores.put("username","el campo username es requerido");
        }
        if (password == null || password.isBlank()) {
            errores.put("password","el campo password es requerido");
        }
        if (email == null || !email.contains("@") || !email.contains(".")) {
            errores.put("email","el campo email es requerido y debe ser valido");
        }
        if (pais == null || pais.isBlank()) {
            errores.put("pais", "El pais es requerido");
        }
        if (lenguajes == null || lenguajes.length == 0) {
            errores.put("lenguajes","El lenguaje es requerido");
        }
        if (roles == null || roles.length == 0) {
            errores.put("roles","El rol es requerido");
        }
        if (idioma == null) {
            errores.put("idioma","El idioma es requerido");
        }
        if (errores.isEmpty()) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Resultado Form</title>");
                out.println("    </head>");
                out.println("    </body>");
                out.println("        <h1>Resultado</h1>");
                out.println("    </body>");
                out.println("</html>");

                out.println("        <h3>Username: " + username + "</h3>");
                out.println("        <h3>Password: " + password + "</h3>");
                out.println("        <h3>Email: " + email + "</h3>");
                out.println("        <h3>Pais: " + pais + "</h3>");
                out.println("        <h3>Lenguajes: </h3><ul>");
                Arrays.asList(lenguajes).forEach(lenguaje -> {
                    out.println("<li>" + lenguaje + "</li>");
                });
                out.println("        </ul>");

                out.println("        <h3>Roles: </h3><ul>");
                Arrays.asList(roles).forEach(rol -> {
                    out.println("<li>" + rol + "</li>");
                });
                out.println("        </ul>");
                out.println("        <h3>Idioma: " + idioma + "</h3>");
                out.println("        <h3>Habilitar: " + habilitar + "</h3>");
                out.println("        <h3>Secreto: " + secreto + "</h3>");
            }
        }else{
                /*out.println("<ul>");
                errores.forEach(error ->{
                    out.println("<li>" + error + "</li>");
                });
                out.println("</ul>");
                out.println("<p><a href=\"/webapp-form/index.jsp\">devolverse</p>");*/
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, response);
        }
    }
}
