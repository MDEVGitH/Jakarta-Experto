<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%
 Map<String, String> errores = (Map<String, String>)request.getAttribute("errores");
 %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario de usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

</head>
<body>
    <div class="px-5">
    <h3>Formulario</h3>

    <%
        if(errores != null && errores.size()>0){
    %>
    <ul class="alert alert-danger">
    <% for(String error: errores.values()){%>
    <li><%=error%></li>
    <%}%>
    </ul>

    <%
    }
    %>

    <form action="/webapp-form/registro" method="post">

        <div class="row mb-3">
            <label for="username" class="col-form-label col-sm-2">Username: </label>
            <div class="col-sm-4">
                <input type="text" name="username" id="username" class="form-control" >
            </div>
            <%
            if(errores != null && errores.containsKey("username")){
                out.println("<small class='alert alert-danger' style='color: red;'> " + errores.get("username") +"</small>");
            }
            %>


        </div>
        <div class="row mb-3">
            <label for="password" class="col-form-label col-sm-2">Password: </label>
            <div class="col-sm-4">
                <input type="password" name="password" id = "password" class="form-control">
            </div>
            <%
                if(errores != null && errores.containsKey("password")){
                    out.println("<small class='alert alert-danger' style='color: red;'> " + errores.get("password") +"</small>");
                }
            %>
        </div>
        <div class="row mb-3">
            <label for="email" class="col-form-label col-sm-2">Email: </label>
            <div class="col-sm-4">
                <input type="text" name="email" id="email" class="form-control">
            </div>
            <%
                if(errores != null && errores.containsKey("email")){
                    out.println("<small class='alert alert-danger' style='color: red;'> " + errores.get("email") +"</small>");
                }
            %>
        </div>
        <div class="row mb-3">
            <label for="pais" class="col-form-label col-sm-2">Pais: </label>
            <div class="col-sm-4">
                <select name="pais" id="pais" class="form-select">
                    <option value="" selected>-- seleccionar --</option>
                    <option value="ES">España</option>
                    <option value="CO">Colombia</option>
                    <option value="MX">Mexico</option>
                    <option value="CH">Chile</option>
                    <option value="AR">Argentina</option>
                    <option value="VE">Venezuela</option>
                </select>
            </div>
            <%
                if(errores != null && errores.containsKey("pais")){
                    out.println("<small class='alert alert-danger' style='color: red;'> " + errores.get("pais") +"</small>");
                }
            %>
        </div>
        <div class="row mb-3">
            <label for="lenguajes" class="col-form-label col-sm-2">Lenguajes de programación:</label>
            <div class="col-sm-4">
                <select name="lenguajes" id="lenguajes" multiple class="form-select">
                    <option value="JAVA">java</option>
                    <option value="PYTHON">python</option>
                    <option value="C++">c++</option>
                    <option value="JAVASCRIPT">javascript</option>
                    <option value="HTML">html</option>
                </select>
            </div>
            <%
                if(errores != null && errores.containsKey("lenguajes")){
                    out.println("<small class='alert alert-danger' style='color: red;'> " + errores.get("lenguajes") +"</small>");
                }
            %>
        </div>
        <div class="row mb-3">
            <label class="col-form-label col-sm-2">Roles: </label>
            <div  class"form-check col-sm-2">
                <input type="checkbox" name="roles" value="ROL_ADMIN" class="form-check-input">
                <label class="form-check-label">Administrador</label>
            </div>
            <div class"form-check col-sm-2">
                <input type="checkbox" name="roles" value="ROL_USUARIO" class="form-check-input">
                <label class="form-check-label">Usuario</label>
            </div>
            <div class"form-check col-sm-2">
                <input type="checkbox" name="roles" value="ROL_MODERADOR" class="form-check-input">
                <label class="form-check-label">Moderador</label>
            </div>
            <%
                if(errores != null && errores.containsKey("roles")){
                    out.println("<small class='alert alert-danger' style='color: red;'> " + errores.get("roles") +"</small>");
                }
            %>
        </div>

        <div class="row mb-3">
            <label class="col-form-label col-sm-2">Idioma: </label>
            <div class"form-check col-sm-2">
                <input type="radio" name = "idioma" value = "ES" class="form-check-input">
                <label class="form-check-label">Español</label>
            </div>
            <div class"form-check col-sm-2">
                <input type="radio" name = "idioma" value = "EN" class="form-check-input">
                <label class="form-check-label">Ingles</label>
            </div>
            <div class"form-check col-sm-2">
                <input type="radio" name = "idioma" value = "PR" class="form-check-input">
                <label class="form-check-label">Portugues</label>
            </div>
            <div class"form-check col-sm-2">
                <input type="radio" name = "idioma" value = "FR" class="form-check-input">
                <label class="form-check-label">Frances</label>
            </div>
            <%
                if(errores != null && errores.containsKey("idioma")){
                    out.println("<small class='alert alert-danger' style='color: red;'> " + errores.get("idioma") +"</small>");
                }
            %>
        </div>

        <div class="row mb-3">
            <label for="habilitar" class="col-form-label col-sm-2">acepto terminos y condiciones</label>
            <div class"form-check">
                <input type="checkbox" name="habilitar" id="habilitar" checked class="form-check-input">
            </div>
        </div>
        <div class="row mb-3">
            <div>
                <input type="submit" value="Enviar">
            </div>
        </div>
        <input type="hidden" name="secreto" value="12345" class="btn btn-primary">
    </form>
    </div>
</body>
</html>