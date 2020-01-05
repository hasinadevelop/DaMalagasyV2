/**
 * 
 */
package com.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Hasina Develop
 *
 */
public class Dialog extends JDialog implements Serializable {
	
	private String response = new String("");
	
	//Constructeur
	  public Dialog(JFrame parent, String title, boolean modal, int winner){
		  super(parent, title, modal);
		    this.setSize(500, 100);
		    this.setLocationRelativeTo(null);
		    this.setResizable(false);
		    
		    JPanel pan_content = new JPanel();
		    JLabel text = new JLabel("");
		    if(winner == 1) {
		    	text = new JLabel("Le blanc a gagné la partie.");
		    } else {
		    	text = new JLabel("Le noir a gagné la partie.");
		    }
		    Font police = new Font("Arial", Font.BOLD, 16);
		    text.setFont(police);
		    JPanel pan_text = new JPanel();
		    pan_text.add(text);
		    pan_content.add(pan_text);
		    
		    JPanel pan_control = new JPanel();
		    JButton newParty = new JButton("Nouvelle partie");
		    newParty.setBackground(Color.WHITE);
		    newParty.setForeground(Color.BLACK);
		    pan_control.add(newParty);
		    
		    newParty.addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent e){
		          setVisible(false);
		          response = "new";
		        }
		      });
		    
		    this.getContentPane().setLayout(new BorderLayout());
		    this.getContentPane().add(pan_content, BorderLayout.CENTER);
		    this.getContentPane().add(pan_control, BorderLayout.SOUTH);
		    this.setVisible(false);
		}
	  
	  public String show_dialog() {
		  this.setVisible(true);
		  return this.response;
	  }

}
