/**
 * 
 */
package com.model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import com.Response;


/**
 * @author Hasina Develop
 *
 */
public class Piece extends AbstractJPanelModel implements Serializable {

	private Case parent;
	private int type;
	private int index;
	private boolean isDame;
	private boolean isMangeur;
	
	public Piece( Case parent, int type, int index ) {
		this.parent = parent;
		this.type = type;
		this.index = index;
		this.init();
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Case parent) {
		Case old = this.parent;
		this.parent = parent;
		this.parent.add(this);
		old.repaint();
		this.parent.repaint();
	}
	
	public void regenere(){
		this.setParent(this.parent);
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return this.type;
	}
	
	/**
	 * @return the type adverse
	 */
	public int getTypeAdverse() {
		return this.type == 0 ? 1 : 0;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	public void remove () {
		this.parent.remove(this);
		this.parent.repaint();
	}
	
	public void isMangeur () {
		this.isMangeur = true;
		this.setBorder(new LineBorder(new Color(255, 0, 0), 35, true));
	}
	
	public void isNotMangeur () {
		if ( !this.isDame ) {
			init();
		} else {
			setDame(true);
		}
		this.isMangeur = false;
	}
	
	public boolean isAlreadyMangeur() {
		return this.isMangeur;
	}
	
	public boolean isDame () {
		return this.isDame;
	}
	
	public void setDame (boolean d) {
		this.isDame = d;
		if ( this.isDame == true ) {
			if ( this.type == 0 ) {
				this.setBorder(new LineBorder(new Color(0, 0, 255), 35, true));
				this.setToolTipText("Pi�ce Noire Dame");
			} else {
				this.setBorder(new LineBorder(new Color(0, 255, 0), 35, true));
				this.setToolTipText("Pi�ce Blanche Dame");
			}
		} else {
			if ( this.type == 0 ) {
				this.setBorder(new LineBorder(new Color(0, 0, 0), 35, true));
				this.setToolTipText("Pi�ce Noire");
			} else {
				this.setBorder(new LineBorder(new Color(255, 255, 255), 35, true));
				this.setToolTipText("Pi�ce Blanche");
			}
		}
	}
	
	private void init () {
		if ( this.type == 0 ) {
			this.setToolTipText("Pi�ce Noire");
			this.setBackground(Color.DARK_GRAY);
			this.setBorder(new LineBorder(new Color(0, 0, 0), 35, true));
			this.setBounds(10, 11, 35, 35);
			this.parent.add(this);
			this.setLayout(new BorderLayout(0, 0));
		} else {
			this.setToolTipText("Pi�ce Blanche");
			this.setBackground(Color.DARK_GRAY);
			this.setBorder(new LineBorder(new Color(255, 255, 255), 35, true));
			this.setBounds(10, 11, 35, 35);
			this.parent.add(this);
			this.setLayout(new BorderLayout(0, 0));
		}
	}

}
