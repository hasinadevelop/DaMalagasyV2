/**
 * 
 */
package com.model;

/**
 * @author Hasina Develop
 *
 */
public class Coup {
	
	int is, js, ie, je, nextTour;
	Piece piece = null;
	Piece piece_mange = null;
	String type = "start";
	boolean coupDame;

	/**
	 * 
	 */
	public Coup(int is, int js, int ie, int je, Piece piece, Piece piece_mange, boolean coupDame) {
		this.type = "coup";
		this.is = is;
		this.ie = ie;
		this.js = js;
		this.je = je;
		this.piece = piece;
		this.piece_mange = piece_mange;
		this.coupDame = coupDame;
	}
	
	public Coup(String coup){
		if (coup.equals("< Start >")) { this.type = "start"; }
		else { this.type = "end"; } 
	}
	
	public String getChaine(){
		if ( this.type.equals("coup") ) {
			int is = this.is + 1;
			int ie = this.ie + 1;
			is = is==1 ? 8 : is==2 ? 7 : is==3 ? 6 : is==4 ? 5 : is==5 ? 4 : is==6 ? 3 : is==7 ? 2 : 1; 
			ie = ie==1 ? 8 : ie==2 ? 7 : ie==3 ? 6 : ie==4 ? 5 : ie==5 ? 4 : ie==6 ? 3 : ie==7 ? 2 : 1; 
			String istart = "" + is;
			String iend = "" + ie;
			String jstart = js==0 ? "a" : js==1 ? "b" : js==2 ? "c" : js==3 ? "d" : js==4 ? "e" : js==5 ? "f" : js==6 ? "g" : "h";
			String jend = je==0 ? "a" : je==1 ? "b" : je==2 ? "c" : je==3 ? "d" : je==4 ? "e" : je==5 ? "f" : je==6 ? "g" : "h";
			String coul = this.piece.getType() == 0 ? "Black" : "White";
			return coul+": "+jstart+istart+ " To "+jend+iend;
		}
		return "< "+this.type.toUpperCase()+" >";
	}
	
	public boolean isCoupFatal () {
		return this.piece_mange == null ? false : true;
	}

	
	/**
	 * @return the is
	 */
	public int getIs() {
		return is;
	}

	/**
	 * @param is the is to set
	 */
	public void setIs(int is) {
		this.is = is;
	}

	/**
	 * @return the js
	 */
	public int getJs() {
		return js;
	}

	/**
	 * @param js the js to set
	 */
	public void setJs(int js) {
		this.js = js;
	}

	/**
	 * @return the ie
	 */
	public int getIe() {
		return ie;
	}

	/**
	 * @param ie the ie to set
	 */
	public void setIe(int ie) {
		this.ie = ie;
	}

	/**
	 * @return the je
	 */
	public int getJe() {
		return je;
	}

	/**
	 * @param je the je to set
	 */
	public void setJe(int je) {
		this.je = je;
	}

	/**
	 * @return the piece
	 */
	public Piece getPiece() {
		return piece;
	}

	/**
	 * @param piece the piece to set
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	/**
	 * @return the piece_mange
	 */
	public Piece getPiece_mange() {
		return piece_mange;
	}

	/**
	 * @param piece_mange the piece_mange to set
	 */
	public void setPiece_mange(Piece piece_mange) {
		this.piece_mange = piece_mange;
	}

	public void setNextTour(int tour) {
		this.nextTour = tour;
		
	}

	/**
	 * @return the nextTour
	 */
	public int getNextTour() {
		return nextTour;
	}

	/**
	 * @return the coupDame
	 */
	public boolean isCoupDame() {
		return coupDame;
	}

	/**
	 * @param coupDame the coupDame to set
	 */
	public void setCoupDame(boolean coupDame) {
		this.coupDame = coupDame;
	}
	
}
