<%-- 
    Document   : listadoCursos
    Created on : 23/10/2023, 02:17:55 PM
    Author     : mauri
--%>

<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="modelos.Conexion"%>

<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>

<%
  Conexion con = new Conexion();
  File reportFile = new File(application.getRealPath("/reportes/listadoCursos.jasper"));
  Map parameters = new HashMap();
  //-------------------------------------------
  byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath (),
                      parameters, con.Conectar());
 
  response.setContentType("application/pdf");
  response.setContentLength(bytes.length);
  ServletOutputStream ouputStream = response.getOutputStream();
  ouputStream.write(bytes, 0, bytes.length);
  ouputStream.flush();
%>
