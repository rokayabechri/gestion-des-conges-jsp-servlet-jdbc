<%@taglib uri="jstl-core" prefix="c"%>
<jsp:include page="/themes/templates/header.jsp">
<jsp:param name="pageTitle" value="Poser un Conges"/>
</jsp:include>

<%--HttpSession Session= request.getSession(true);--%>
<%--Employe user=(Employe)Session.getAttribute("user"); --%>

<b>Employe prenom :  </b><b>${user.prenom}</b> <b>Employe nom: </b> <b>${user.nom}</b>
<form  method="POST" action="PoserCongesControlleur">
<table border="1">
    <tr>
	<td><label>ID USER :</label></td><td><input type="text" name="ID_USER" value="${param.id}"/></td>
	</tr>
	
	<tr>
	<td><label> DATE DEBUT :</label></td><td><input type="text" name="DATE_DEBUT"/></td>
	</tr>
	
	<tr>
	<td><label> DATE FIN :</label></td><td><input type="text" name="DATE_FIN"/></td>
	</tr>
	
	<tr>
	<td><label> TYPE :</label></td>
	<td><select name="typeconges" size="">
	    <option value="CP"> CP</option>
	    <option value="RTT">RTT</option>
	    <option value="RECUPERATION">RECUPERATION</option>
	    <option value="ANCIENNETE">ANCIENNETE</option>
	   </select>
	</td>
	</tr>
	
	<tr>
	<td><label> COMENTAIRE :</label></td>
	<td><textarea name="COMMENTAIRE" rows=10 cols=40 ></textarea></td>
	</tr>
	<tr>
	<td><input type="submit" value="VALIDER"/></td>
    </tr>
    </table>
  </form>

</body>
</html>