<%@taglib uri="jstl-core" prefix="c"%>
<%@taglib uri="jstl-fmt" prefix="fmt"%>

<%@page import="java.util.ArrayList" %>
<%@page import="com.aston.rh.gestionconges.entites.DemandeConges" %>
<%@page import="java.util.Iterator" %>
<jsp:include page="/themes/templates/header.jsp">
<jsp:param name="pageTitle" value="Modifier un Conges"/>
</jsp:include>





<form name="modifierconges" method="POST" action="ModifierCongesControleur">
<table border="1">
    <tr>
      <td><label>ID  :</label></td><td><input type="text" name="ID"  value='${param.idConges}'/></td>
	</tr>
	<tr>
	<td><label>ID USER :</label></td><td><input type="text" name="ID_USER" value= "${param.idUser}"/></td>
	</tr>
	<tr>
	
	<tr>
	<!-- td><label><a href="ListerConges.jsp"> Lister Conges </a></label></td-->
	
	<!-- <td><select id="listeconges" onChange="remplire(this);">
	    <option value=0>choisir</option>
	  <!--% ArrayList <DemandeConges> ls=(ArrayList<DemandeConges>)session.getAttribute("List");
	    Iterator e = ls.iterator();
	    int i=0;
	    DemandeConges dem;
	   while (e.hasNext()){  
		   
		 	   dem =(DemandeConges)e.next();%-->
			   <!--option VALUE=<!--%out.print(dem.getIdConges()); %>><!--%out.print(dem.getTypeConges() + " elle est "+ dem.getStatut());%></option-->
		   <!--% i++;} %>
	          
	   <!--/select-->
	<!-- /td-->
	
	</tr>
	<tr>
	<td><label> DATE DEBUT :</label></td>
	<td><input type="text" name="DATE_DEBUT" value="${param.DD}"/></td>
	</tr>
	<tr>
	<td><label> DATE FIN :</label></td><td>
	<input type="text" name="DATE_FIN" value="${param.DF}" /></td>
	</tr>
	
	<tr>
	<td><label> TYPE :</label></td>
	<td><select name="typeConges" size="">
	
	
    	<option <c:if test="${param['Type']=='CP'}" >value="CP" selected="selected"</c:if> > CP</option>
		<option <c:if test="${param['Type']== 'RTT'}"> value="RTT" selected="selected" </c:if>> RTT</option>
		<option <c:if test="${param['Type']=='RECUPERATION'}">value="RECUPERATION" selected="selected"</c:if>> RECUPERATION</option>
		<option <c:if test="${param['Type']=='ANCIENNETE'}">value="ANCIENNETE" selected="selected" </c:if>> ANCIENNETE</option>
		
		<option value="RTT">RTT</option>
		<option value="RECUPERATION" >RECUPERATION</option>
		<option value="ANCIENNETE">ANCIENNETE</option>
	
    
     </select>
	   
	</td>
	</tr>
	
	<tr>
	<c:if test="${param.comhidden=='vraie'}">
		
		<td><label> COMENTAIRE :</label></td><td><input type="text" name="COMMENTAIRE" disabled="disabled" value="${param.com}"></td>
	</c:if>
	</tr>
	<tr>
	<c:if test="${param.comhidden=='faux'}">
		
		<td><label> COMENTAIRE :</label></td>
		<td><textarea name="COMMENTAIRE" rows=10 cols=40 >${param.com}</textarea></td>
	</c:if>
	</tr>
	
	
	<tr>
	<td><input type="submit" value="VALIDER"/></td>
    </tr>
    </table>
  </form>
  
 
</body>
</html>