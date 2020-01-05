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
			  text = "Le jeu se joue sur un plateau de 8x8 avec 12 pi�ces de chaque c�t�.\r\n" + 
				  		"Les pi�ces sans couronne d�placer d'un �space en diagonale avant,\r\n" + 
				  		"mais elle peut capturer en diagonale vers l'avant ou vers l'arri�re.\r\n" + 
				  		"Il est obligatoire de prendre autant de pi�ces que possible.\r\n" + 
				  		"Les dames peuvent se d�placer � un certain nombre de carr�s.\r\n" + 
				  		"Un pi�ce se change en dame lorsqu'elle atteind la d�rni�re \r\n" + 
				  		"ligne de l'adversaire.\r\n" + 
				  		"On gagne lorsque l'adversaire n'a plus de pi�ces.";
		  } else {
			  text = "Nom: DaMalagasy\r\n" + 
			  		"Type: Jeu de dame\r\n" + 
			  		"D�veloppeur: Hasina Develop <hasinadevelop@gmail.com>\r\n" + 
			  		"T�chnologie utilis�e: Java (Swing)\r\n" + 
			  		"Version: 1.0";
		  }
		  this.showMessageDialog(null, text, type,
		    		JOptionPane.INFORMATION_MESSAGE);
		}
}
