package com.vue;

import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import com.Response;
import com.observer.Observable;
import com.observer.Observer;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Hasina Develop
 *
 */
public class Menu extends JMenuBar implements Observable{

	private ArrayList<Observer> listObserver = new ArrayList<Observer> ();

	public Menu() {
		this.setBounds(0, 0, 586, 27);
		
		JMenu mnGame = new JMenu("Jeu");
		this.add(mnGame);
		
		JMenuItem NewGame = new JMenuItem("Nouveau");
		NewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notifyObserver(new Response("newGame"));
			}
		});
		mnGame.add(NewGame);
		
		JMenuItem SaveGame = new JMenuItem("Sauvegarder");
		SaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notifyObserver(new Response("saveGame"));
			}
		});
		mnGame.add(SaveGame);
		
		JMenuItem mntmLoadGame = new JMenuItem("Charger");
		mntmLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notifyObserver(new Response("loadGame"));
			}
		});
		mnGame.add(mntmLoadGame);
		
		JSeparator separator = new JSeparator();
		mnGame.add(separator);
		
		JMenuItem mntmQuit = new JMenuItem("Quitter");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notifyObserver(new Response("quit"));
			}
		});
		mntmQuit.setBackground(Color.LIGHT_GRAY);
		
		mnGame.add(mntmQuit);
		
		JMenu mnAide = new JMenu("Aide");
		this.add(mnAide);
		
		JMenuItem CommentJouer = new JMenuItem("Comment jouer ?");
		CommentJouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notifyObserver(new Response("aide"));
			}
		});
		mnAide.add(CommentJouer);
		
		JMenuItem APropos = new JMenuItem("A propos");
		APropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notifyObserver(new Response("about"));
			}
		});
		mnAide.add(APropos);
		
		
	}

	public void addObserver (Observer obs) {
		this.listObserver.add(obs);
	}
	
	public void notifyObserver ( Response res ) {
		for ( Observer obs : this.listObserver ) {
			obs.update(res);
		}
	}
	
	public void removeObserver () {
		this.listObserver = new ArrayList<Observer> ();
	}

}
