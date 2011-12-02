<%@taglib uri="jstl-core" prefix="c"%>
<%@taglib uri="jstl-fmt" prefix="fmt"%>

<%@page import="java.util.ArrayList" %>
<%@page import="com.aston.rh.gestionconges.entites.DemandeConges" %>
<%@page import="com.aston.rh.gestionconges.entites.conversionDate" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.aston.rh.gestionconges.entites.DemandeConges" %>
<%@page import="java.util.Iterator" %>


<jsp:include page="/themes/templates/header.jsp">
<jsp:param name="pageTitle" value="Lister un Conges"/>
</jsp:include>




<form >

	    
<b>${role}</b>
<TABLE BORDER=1>
     
      <c:forEach items="${List}" var="dem">
    	<tr>
 		 <fmt:formatDate value="${dem.dateDebut}" pattern="dd/MM/yyyy" var="dateDD"/>
 		 <fmt:formatDate value="${dem.dateFin}" pattern="dd/MM/yyyy" var="dateFF"/>
 		 
			<td><label> ${dem.idConges}: </label></td><td><c:out value="${dateDD}"/> </td><td><c:out value="${dateFF}"/> </td><td>${dem.typeConges}</td><td>${dem.statut} </td>
			
			<c:url value="ModifierConges.jsp" var="ModifierCongesURL">
			<c:param name="idConges" value="${dem.idConges}" />
			<c:param name="idUser" value="${dem.idUser}" />
			<c:param name="DD" value="${dateDD}" />
			<c:param name="DF" value="${dateFF}"  />
			<c:param name="Type" value="${dem.typeConges}" />
			<c:param name="com" value="${dem.comment}" />
			<c:param name="comhidden" value="vraie"/>
			</c:url>
			
			<c:url value="StatuerControleur" var="AccepterURL">
			<c:param name="idConges" value="${dem.idConges}" />
			<c:param name="idUser" value="${dem.idUser}" />
			<c:param name="DD" value="${dateDD}" />
			<c:param name="FF" value="${dateFF}"  />
			<c:param name="Type" value="${dem.typeConges}" />
			<c:param name="com" value="${dem.comment}" />
			<c:param name="Statut" value="Accepté"/>
			</c:url>
			
			<c:url value="StatuerControleur" var="RefuserURL">
			<c:param name="idConges" value="${dem.idConges}" />
			<c:param name="idUser" value="${dem.idUser}" />
			<c:param name="DD" value="${dateDD}" />
			<c:param name="FF" value="${dateFF}"  />
			<c:param name="Type" value="${dem.typeConges}" />
			<c:param name="com" value="${dem.comment}" />
			<c:param name="Statut" value="Refusé"/>
			</c:url> 
					
	        
			<td><a href='<c:out value="${ModifierCongesURL}" />'>Modifier Conges</a><br/></td>
	   	        
		</tr>
            
	</c:forEach>
	
</TABLE>


</form>
</body>
</html>