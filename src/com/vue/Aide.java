/**
 * 
 */
package com.vue;

import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Hasina Develop
 *
 */
public class Aide extends JOptionPane implements Serializable {
	//Constructeur
	  public Aide(JFrame parent, String title, boolean modal, String type){
		  String text = "";
		  if(type == "Comment Jouer ?") {
			  text = "Le jeu se joue sur un plateau de 8x8 avec 12 pièces de chaque côté.\r\n" + 
				  		"Les pièces sans couronne déplacer d'un éspace en diagonale avant,\r\n" + 
				  		"mais elle peut capturer en diagonale vers l'avant ou vers l'arrière.\r\n" + 
				  		"Il est obligatoire de prendre autant de pièces que possible.\r\n" + 
				  		"Les dames peuvent se déplacer à un certain nombre de carrés.\r\n" + 
				  		"Un pièce se change en dame lorsqu'elle atteind la dérnière \r\n" + 
				  		"ligne de l'adversaire.\r\n" + 
				  		"On gagne lorsque l'adversaire n'a plus de pièces.";
		  } else {
			  text = "Nom: DaMalagasy\r\n" + 
			  		"Type: Jeu de dame\r\n" + 
			  		"Développeur: Hasina Develop <hasinadevelop@gmail.com>\r\n" + 
			  		"Téchnologie utilisée: Java (Swing)\r\n" + 
			  		"Version: 1.0";
		  }
		  this.showMessageDialog(null, text, type,
		    		JOptionPane.INFORMATION_MESSAGE);
		}
}
