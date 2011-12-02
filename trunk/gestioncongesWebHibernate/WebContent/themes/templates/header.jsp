<%@page import="com.aston.rh.gestionconges.entites.Employe" %>

<%@taglib uri="jstl-core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${param.pageTitle}</title>
</head>
<body>
<h2>Bienvenue sur l'application de gestion des congés</h2>


<b>${ident}</b>
<a href="acceuil.jsp">Acceuil </a><br/>
<a href="PoserConges.jsp"> Poser Conges </a><br/>
<a href="ListerDemandeControlleur"> Lister Conges </a><br/>
<c:if test="${role=='manager'}">
<a href="GererDemandeEmployeControlleur"> Gerer Demande Congés Equipe </a><br/>
</c:if>
<!--a href="ModifierConges.jsp">Modifier Conges </a><br/-->



