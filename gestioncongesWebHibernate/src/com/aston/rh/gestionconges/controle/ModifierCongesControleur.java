package com.aston.rh.gestionconges.controle;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aston.rh.gestionconges.entites.DemandeConges;
import com.aston.rh.gestionconges.entites.Employe;
import com.aston.rh.gestionconges.entites.conversionDate;
import com.aston.rh.gestionconges.exception.GestionCongesTechniqueException;
import com.aston.rh.gestionconges.service.FactoryService;

import com.aston.rh.gestionconges.service.IServiceAuthentification;
import com.aston.rh.gestionconges.service.IServiceGestionConges;

/**
 * Servlet implementation class ModifierCongeesControleur
 */
public class ModifierCongesControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierCongesControleur() {
        super();
        // TODO Auto-generated constructor stub
    }

   

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
			try {
				execute(request,response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
			try {
				execute(request,response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	    }
	
	
	
	public boolean validerparametre( HttpServletRequest request) {
        //  Enumeration list = request.getParameterNames();
    	  String id=request.getParameter("ID");
          String iduser = request.getParameter("ID_USER");
          String datedebut = request.getParameter("DATE_DEBUT");
          String datefin = request.getParameter("DATE_FIN");
          String type = request.getParameter("typeconges");
          String commentaire = request.getParameter("COMMENTAIRE");
          boolean res = true;
          if (("").equals(id)) {
              System.out.println("Veuillez entrer un champ ID_USER");
              res = false;
          } 
          if (("").equals(iduser)) {
              System.out.println("Veuillez entrer un champ ID_USER");
              res = false;
          } 
          if (("").equals(datedebut)) {
              System.out.println("Veuillez entrer un champ DATE_DEBUT sous le format dd/");
              res = false;
          } 
          if (("").equals(datefin)) {
              System.out.println("Veuillez entrer un champ DATE_FIN sous le format dd/mm/aaaa");
              res = false;
          } 
          if (("").equals(type)) {
              System.out.println("Veuillez entrer un champ TYPE");
              res = false;
          } 
          if (("").equals(commentaire)) {
              System.out.println("Veuillez entrer un champ COMENTAIRE");
              res = false;
          }

          return res;
      }
	
	
	private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		
		//String role=(String)request.getSession().getAttribute("role");
		
		
		if (validerparametre(request) == true) {
       
			String s1=request.getParameter("DATE_DEBUT");
	        String s2=request.getParameter("DATE_FIN");
	        Date date1=null;
	        Date date2=null;
	    	    String pattern = "dd/MM/yyyy";
	    	    SimpleDateFormat format = new SimpleDateFormat(pattern);     
	    	    try {
	    	         date1 = format.parse(s1);
	    	         
	    	      } catch (ParseException e) {
	    	        e.printStackTrace();
	    	      }
	    	      try {
	     	        
	     	         date2=format.parse(s2);
	     	      } catch (ParseException e) {
	     	        e.printStackTrace();
	     	      }
			
			          
           int iduser= Integer.parseInt( request.getParameter("ID_USER"));
           int idConges= Integer.parseInt( request.getParameter("ID"));
           /**
            * creer un objet de type IServiceGestionConges en fesant appel a
            * la class FactoryServiceDemandeCongesDAO et sepcificquement a sa méthode 
            * getDAODemandeConges()
            */
            IServiceGestionConges demcong = FactoryService.getServiceDemandeConges("jdbc");
            /**
             * creer l'objet DemandeConges
             */
            DemandeConges cong = new DemandeConges(iduser,date1,date2   , request.getParameter("typeConges"),
                    "en cours", request.getParameter("COMMENTAIRE"));
            cong.setIdConges(idConges);
           /**
            * appeler la methode d'Update sur DemandeConges
            */
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
                  
      			url="ModifierConges.jsp";
              }
              aiguilleur=request.getRequestDispatcher(url);
      		aiguilleur.forward(request, response);
      		
    		
    		
        } else {
            System.out.println("Votre demande de congé n'est pas valide");
            response.sendRedirect("ModifierConges.jsp");
        }
		
	}
	}


