/**
 * 
 */
package com.vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import com.Response;
import com.model.Case;
import com.model.Dame;
import com.model.Piece;
import com.observer.Observer;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JSplitPane;

import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Hasina Develop
 *
 */
public class Table extends JFrame implements Observer{

	private Dame dame = new Dame();
	private JPanel container;
	private Response res;
	private Menu menu;
	private JLabel lblTour;
	private JComboBox list_coups;
	private JButton btn_undo;
	private JButton btn_redo;
	private JButton btnStop;

	public Table() {
		this.setTitle("DaMalagasy");
		this.setSize(592, 592);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		container = new JPanel();
		container.setBackground(Color.DARK_GRAY);
		getContentPane().add(container, BorderLayout.CENTER);
		container.setLayout(null);
		
		menu = new Menu();
		this.setJMenuBar(menu);
		menu.addObserver(this);
		this.init();
		this.setVisible(true);
	}
	
	private void init () {
		this.dame = new Dame();
		this.dame.addObserver(this);
		
		JPanel panel_bottom = new JPanel();
		panel_bottom.setBackground(Color.DARK_GRAY);
		panel_bottom.setBounds(0, 489, 586, 53);
		container.add(panel_bottom);
		panel_bottom.setLayout(null);
		
		list_coups = new JComboBox();
		list_coups.setToolTipText("List of coups");
		list_coups.setMaximumRowCount(100);
		list_coups.setBackground(new Color(255, 255, 255));
		list_coups.setForeground(new Color(128, 128, 128));
		list_coups.setFont(new Font("Verdana", Font.BOLD, 12));
		list_coups.setModel(new DefaultComboBoxModel(new String[] {"< Start >"}));
		list_coups.setBounds(76, 9, 431, 30);
		panel_bottom.add(list_coups);
		
		btn_undo = new JButton("Undo");
		btn_undo.setEnabled(false);
		btn_undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dame.undo();
			}
		});
		btn_undo.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btn_undo.setBackground(new Color(255, 255, 255));
		btn_undo.setBounds(10, 9, 59, 30);
		panel_bottom.add(btn_undo);
		
		btn_redo = new JButton("Redo");
		btn_redo.setEnabled(false);
		btn_redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dame.redo();
			}
		});
		btn_redo.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btn_redo.setBackground(new Color(255, 255, 255));
		btn_redo.setBounds(517, 9, 59, 30);
		panel_bottom.add(btn_redo);
		
		JPanel panel_top = new JPanel();
		panel_top.setBackground(Color.DARK_GRAY);
		panel_top.setBounds(0, 0, 586, 44);
		container.add(panel_top);
		panel_top.setLayout(null);
		
		lblTour = new JLabel("White to play");
		lblTour.setForeground(Color.WHITE);
		lblTour.setBackground(Color.DARK_GRAY);
		lblTour.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTour.setHorizontalAlignment(SwingConstants.CENTER);
		lblTour.setBounds(223, 0, 117, 44);
		panel_top.add(lblTour);
		
		btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tour = dame.getTour();
				int tour_suivant = tour == 0 ? 1 : 0;
				dame.isDame();
				dame.reColor();
				dame.reinit();
			}
		});
		btnStop.setEnabled(false);
		btnStop.setForeground(Color.WHITE);
		btnStop.setBackground(Color.DARK_GRAY);
		btnStop.setBounds(350, 13, 63, 23);
		panel_top.add(btnStop);
		
		JPanel panel_left = new JPanel();
		panel_left.setBackground(Color.DARK_GRAY);
		panel_left.setBounds(0, 43, 70, 435);
		container.add(panel_left);
		panel_left.setLayout(null);
		
		JPanel panel_right = new JPanel();
		panel_right.setBackground(Color.DARK_GRAY);
		panel_right.setBounds(508, 44, 78, 434);
		container.add(panel_right);
		panel_right.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setForeground(Color.WHITE);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 20));
		textPane.setBackground(Color.DARK_GRAY);
		textPane.setEditable(false);
		textPane.setText("8\r\n\r\n7\r\n\r\n6\r\n\r\n5\r\n\r\n4\r\n\r\n3\r\n\r\n2\r\n\r\n1");
		textPane.setBounds(69, 69, 15, 397);
		container.add(textPane);
		
		JTextPane txtpnA = new JTextPane();
		txtpnA.setBackground(Color.DARK_GRAY);
		txtpnA.setForeground(Color.WHITE);
		txtpnA.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtpnA.setText("   A      B      C      D      E       F      G      H");
		txtpnA.setBounds(94, 457, 433, 26);
		container.add(txtpnA);
		
		ArrayList<ArrayList<Case>> cases = this.dame.getCases();
		int x = 90,y = 53;
		for ( int i=0; i<8; i++ ) {
			x = 90;
			for ( int j=0; j<8; j++ ) {
				cases.get(i).get(j).setBounds(x, y, 52, 51);
				x += 51;
				this.container.add(cases.get(i).get(j));
			}
			y += 50;
		}
		
		ArrayList<ArrayList<Piece>> pieces = this.dame.getPieces();
		for ( int i=0; i<2; i++ ) {
			for ( int j=0; j<12; j++ ) {
				pieces.get(i).get(j).addMouseListener(new MouseAdapter(){
					public void mouseClicked ( MouseEvent e ) {
						dame.reColor();
						dame.setSelected_piece((Piece) e.getComponent());
					}
				});
			}
		}
	}
	
	public void reInit () {
		this.container.removeAll();
		this.init();
		this.container.repaint();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void update ( Response res ) {
		this.res = res;
		if ( this.res.getId() == "pc" ) {
			try {
				for ( Case pc : res.possiblesCoups() ) {
					pc.addMouseListener(new MouseAdapter(){
						public void mouseClicked ( MouseEvent e ) {
							dame.deplaceSelected_piece((Case) e.getComponent());
						}
					});
				}
			} catch ( Exception e1 ) {}
		}
		
		if ( this.res.getId() == "possibleContinue" ) {
			this.btnStop.setEnabled(true);
		}
		
		if ( this.res.getId() == "winner" ) {
			if ( res.hasWinner() ) {
				System.exit(0);
			}
		}
		
		if ( this.res.getId() == "changeTour" ) {
			if ( this.res.getTour() == 0 ) {
				lblTour.setText("Black to play");
			} else if ( this.res.getTour() == 1 ) {
				lblTour.setText("White to play");
			}
			this.btnStop.setEnabled(false);
		}
		
		if ( this.res.getId() == "quit" ) { System.exit(0); }
		if ( this.res.getId() == "newGame") { this.reInit(); }
		if ( this.res.getId() == "listCoups" ) {
			this.list_coups.setModel(new DefaultComboBoxModel());
			this.list_coups.setModel(new DefaultComboBoxModel(this.res.listCoups().toArray()));
			this.list_coups.setSelectedIndex(this.list_coups.getModel().getSize() - 1);
			this.btn_undo.setEnabled(true);
			this.btn_redo.setEnabled(false);
		}
		if ( this.res.getId() == "undo" ) {
			if (!this.res.hasPreviousCoup()) {
				this.btn_undo.setEnabled(false);
			}
			if (!this.res.hasNextCoup()) {
				this.btn_redo.setEnabled(false);
			} else {
				this.btn_redo.setEnabled(true);
			}
			this.list_coups.setSelectedIndex(this.dame.getIndexLastCoups());
		}
		if ( this.res.getId() == "redo" ) {
			if (!this.res.hasPreviousCoup()) {
				this.btn_undo.setEnabled(false);
			} else {
				this.btn_undo.setEnabled(true);
			}
			if (!this.res.hasNextCoup()) {
				this.btn_redo.setEnabled(false);
			}
			this.list_coups.setSelectedIndex(this.dame.getIndexLastCoups());
		}
	}
}
