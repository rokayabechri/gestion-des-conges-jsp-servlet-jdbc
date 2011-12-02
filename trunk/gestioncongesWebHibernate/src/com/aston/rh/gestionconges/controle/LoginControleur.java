package com.aston.rh.gestionconges.controle;

import java.awt.List;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aston.rh.gestionconges.entites.DemandeConges;
import com.aston.rh.gestionconges.entites.Employe;
import com.aston.rh.gestionconges.entites.Manager;
import com.aston.rh.gestionconges.entites.conversionDate;
import com.aston.rh.gestionconges.persistance.FactoryBD;
import com.aston.rh.gestionconges.persistance.FactoryDAO;
import com.aston.rh.gestionconges.persistance.IAuthentificationDAO;
import com.aston.rh.gestionconges.service.FactoryService;
import com.aston.rh.gestionconges.service.IServiceAuthentification;



/**
 * Servlet implementation class LoginControleur
 */
public class LoginControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginControleur() {
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
		String value1 = request.getParameter("Login");
		String value2=request.getParameter("pwd");
		/**creer un objet IServiceAuthentificationavec la Factory 	 */
		IServiceAuthentification srv=FactoryService.getAuthentification("HIB");
		
		Employe emp =	srv.identifier(value1, value2);
		PrintWriter writer=response.getWriter();
		Manager man=srv.isManager(emp);
		/*****************initialiser les parametres*****************/
		RequestDispatcher aiguilleur=null;
		String url=null;
		HttpSession mySession=null;		
		
		/***************apeler la methode readDemandeforAffiche sur le service IServiceAuthentification***********/
	//	ArrayList<DemandeConges> ls=srv.readDemandeforAffiche(emp);
       
        mySession=request.getSession();
      
   
        String role="";
        if(man.getListeEmp().isEmpty()){
        	 role="employe";
        	
        }else{
        	 role="manager";
        }
		if("".equals(emp.getNom())){
			request.setAttribute("msg", "Vos identifiants sont incorrectes");
			url="Login.jsp";
			//request.setAttribute("user", emp);
			aiguilleur=request.getRequestDispatcher(url);
			aiguilleur.forward(request, response);
		}
		else {
			//request.setAttribute("user", emp);
			//session=request.getSession();
			
			mySession.setAttribute("user", emp);
			mySession.setAttribute("role", role);
			url="acceuil.jsp";
			response.sendRedirect(url);
			
			}
		
		
	//	aiguilleur=request.getRequestDispatcher(url);
	//	aiguilleur.forward(request, response);
		
	}
}
