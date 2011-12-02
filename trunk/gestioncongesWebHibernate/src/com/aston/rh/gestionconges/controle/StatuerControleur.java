package com.aston.rh.gestionconges.controle;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aston.rh.gestionconges.entites.DemandeConges;
import com.aston.rh.gestionconges.service.FactoryService;
import com.aston.rh.gestionconges.service.IServiceGestionConges;

/**
 * Servlet implementation class Statuer
 */
public class StatuerControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatuerControleur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			execute(request,response);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			execute(request,response);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
	}

	
private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		
	IServiceGestionConges demcong = FactoryService.getServiceDemandeConges("jdbc");
    /**
     * creer l'objet DemandeConges
     */
	
	String pattern = "dd/MM/yyyy";
    SimpleDateFormat format = new SimpleDateFormat(pattern);   
    
	 int idConges=Integer.parseInt( request.getParameter("idConges"));
	  int idUser=Integer.parseInt( request.getParameter("idUser"));
	  Date date1=null;Date date2=null;
	  try {
	         date1 = format.parse(request.getParameter("DD"));
	         
	      } catch (ParseException e) {
	        e.printStackTrace();
	      }
	      try {
	    	  date2 = format.parse(request.getParameter("FF"));
		         
		      } catch (ParseException e) {
		        e.printStackTrace();
		      }
	  
	  String Type= (String)request.getParameter("Type");
	  String com= (String)request.getParameter("com");
	  String statut= (String)request.getParameter("Statut");

			
	    DemandeConges cong =  new DemandeConges(idConges,Type,date1,date2,statut ,idUser,com);
	   // cong.setStatut("accepté");
	    
   
    
   
    boolean res = false;
    try {
        res = demcong.updateDemande(cong);
        
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    
    
    RequestDispatcher aiguilleur=null;
	String url=null;
	
	  HttpSession session=null;
      if (res == true) {
      
			url="acceuil.jsp";
			
      } else {
          
			url="ListerDemandeConges.jsp";
      }
      aiguilleur=request.getRequestDispatcher(url);
		aiguilleur.forward(request, response);
		
    
}
	
}
