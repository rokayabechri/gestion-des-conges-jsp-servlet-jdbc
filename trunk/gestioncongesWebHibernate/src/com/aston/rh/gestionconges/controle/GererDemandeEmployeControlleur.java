package com.aston.rh.gestionconges.controle;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aston.rh.gestionconges.entites.DemandeConges;
import com.aston.rh.gestionconges.entites.Employe;
import com.aston.rh.gestionconges.entites.Manager;
import com.aston.rh.gestionconges.service.FactoryService;
import com.aston.rh.gestionconges.service.IServiceGestionConges;

public class GererDemandeEmployeControlleur extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GererDemandeEmployeControlleur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//PrintWriter out = response.getWriter();
		
		try {
			execute(request,response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			execute(request,response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException{
		Enumeration list = request.getParameterNames();
		/*String value1 = request.getParameter("Login");
		String value2=request.getParameter("pwd");
		*//**creer un objet IServiceAuthentificationavec la Factory 	 *//*
		IServiceAuthentification srv=FactoryServiceAuthentification.getAuthentification("local");
		
		Employe emp =	srv.identifier(value1, value2);*/
		//PrintWriter writer=response.getWriter();
		
		/*****************initialiser les parametres*****************/
		RequestDispatcher aiguilleur=null;
		String url=null;
		HttpSession mySession;		
		//mySession=request.getSession();
		/***************apeler la methode readDemandeforAffiche sur le service IServiceAuthentification***********/
		IServiceGestionConges srv=FactoryService.getServiceDemandeConges("jdbc");
		
		Employe emp=(Employe) request.getSession().getAttribute("user");
		String role=(String) request.getSession().getAttribute("role");
		ArrayList<DemandeConges> ls=null;
		ArrayList<Employe> lsemploye=null;
		
			Manager man=new Manager(emp);
			lsemploye=srv.readDemandeforAfficheManager(man);
		
		
	
					if(lsemploye.equals(null)){
						url="ListerConges.jsp";
						aiguilleur=request.getRequestDispatcher(url);
						aiguilleur.forward(request, response);
										
					}else{
						mySession=request.getSession();
						mySession.setAttribute("List", lsemploye);
						mySession.setAttribute("role", role);
						url="GererDemandeEmploye.jsp";
						response.sendRedirect(url);
					}
				
}
}
