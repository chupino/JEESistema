<%-- 
    Document   : listadoMatriculas
    Created on : 16/10/2023, 06:25:12 PM
    Author     : mauri
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" 
              href="webjars/bootstrap/5.1.0/css/bootstrap.min.css" type="text/css" />
    </head>
    <body>
    <div class="card" style="padding: 30px 0px 0px 30px;">
        <h4 class="display-8">Listado de Matriculas</h4>
        
        <form method=POST action="/WebSistema/controladorPrincipal">
            <input type=HIDDEN name=accion value="listadoMatriculas">
            <input type=HIDDEN name=modo value="busqueda">
            <div>Buscar Matricula: <input name="xmat" value="<c:out value='${nro_doc}'/>"
                size=60></div>
            <button class="btn btn-primary" type=submit name=boton value="Buscar">Buscar</button>
            <table class="table table-striped table-hover">
            <thead>
            <tr>
            <th scope="col">#</th>
            <th scope="col">Nombre Alumno</th>
            <th scope="col">Nro Documento</th>
            <th scope="col">Fecha Matricula</th>
            <th scope="col">Cursos</th>
            <th scope="col">Estado</th>
            <th scope="col">Total Pagado</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${arrMatriculas}" var="matricula">
            <tr>
            <td><c:out value='${matricula.codigo}'/></td>
            <td><a href="/WebSistema/controladorPrincipal?accion=modificarCurso&xcod=
                     <c:out value='${matricula.codigo}'/>">
                <c:out value='${matricula.nombreAlumno}' /></a></td>
            <td><c:out value='${matricula.nroDocumento}'/></td>
            <td><c:out value='${matricula.fecha}'/></td>
            <td><c:out value='${matricula.cursos}'/></td>
            <td><c:out value='${matricula.estado}'/></td>
            <td><c:out value='${matricula.total}'/></td>
            </tr>
            </c:forEach>
            </tbody>
            </table>
        </form>
    </div>
    </body>
</html>
