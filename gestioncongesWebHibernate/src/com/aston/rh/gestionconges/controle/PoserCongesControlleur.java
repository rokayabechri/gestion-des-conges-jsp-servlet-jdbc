package com.aston.rh.gestionconges.controle;

import com.aston.rh.gestionconges.entites.DemandeConges;
import com.aston.rh.gestionconges.entites.Employe;
import com.aston.rh.gestionconges.entites.conversionDate;
import com.aston.rh.gestionconges.exception.GestionCongesTechniqueException;
import com.aston.rh.gestionconges.persistance.FactoryBD;
import com.aston.rh.gestionconges.service.FactoryService;
import com.aston.rh.gestionconges.service.IServiceAuthentification;
import com.aston.rh.gestionconges.service.IServiceGestionConges;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class PoserCongesControlleur extends HttpServlet {
	
    public boolean validerparametre(HttpServletRequest request) {
      //  Enumeration list = request.getParameterNames();
        String iduser = request.getParameter("ID_USER");
        String datedebut = request.getParameter("DATE_DEBUT");
        String datefin = request.getParameter("DATE_FIN");
        String type = request.getParameter("typeconges");
        String commentaire = request.getParameter("COMMENTAIRE");
        boolean res = true;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
			execute(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
			execute(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
    if (validerparametre(request) == true) {
          //  Enumeration list = request.getParameterNames();
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
            IServiceGestionConges demcong = FactoryService.getServiceDemandeConges(
                    "jdbc");
            
            DemandeConges cong = new DemandeConges(Integer.parseInt(
                        request.getParameter("ID_USER")),
                    date1,
                    date2, request.getParameter("typeconges"),
                    "en cours", request.getParameter("COMMENTAIRE"));
            boolean res = false;
/*
 * intialiser l'aiguileur
 */
            RequestDispatcher aiguilleur=null;
    		String url=null;
    		
            try {
                res = demcong.createDemande(cong);
                
            } catch (GestionCongesTechniqueException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            HttpSession session=null;
            if (res == true) {
                //System.out.println("ajout reuissi");
            	//request.setAttribute("gestionconges", cong);
				url="acceuil.jsp";
				// session=request.getSession();
				// session.setAttribute("gestionconges", cong);
            } else {
                //System.out.println("Probleme d'insertion dans la base de données");
            	//request.setAttribute("msg", "Vos identifiants sont incorrectes");
    			url="PoserConges.jsp";
            }
            aiguilleur=request.getRequestDispatcher(url);
    		aiguilleur.forward(request, response);
    		
        } else {
            System.out.println("Votre demande de congé n'est pas valide");
        }
    }
}
