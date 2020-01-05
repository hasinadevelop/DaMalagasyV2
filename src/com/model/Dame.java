package com.model;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;

import com.Response;

/**
 * @author Hasina Develop
 *
 */
public class Dame extends AbstractModel implements Serializable {

	private ArrayList<ArrayList<Piece>> pieces = new ArrayList<ArrayList<Piece>>();
	private ArrayList<ArrayList<Case>> cases = new ArrayList<ArrayList<Case>>();
	private Piece selected_piece;
	private ArrayList<Case> possibles_coups = new ArrayList<Case>();
	private ArrayList<Case> possibles_coups_fatals = new ArrayList<Case>();
	private Response res;
	private int tour = 1, prevTour = -1;
	private ArrayList<Piece> possibles_pieces_to_attaq = new ArrayList<Piece>();
	private ArrayList<Piece> piece_a_retirer = new ArrayList<Piece>();
	private int piecesnoiresnumber = 12;
	private int piecesblanchesnumber = 12;
	private ArrayList<Coup> listCoups = new ArrayList<Coup> ();
	private Coup lastCoups;
 	
	public Dame() {
		this.init();
	}
	
	/**
	 * Fonction d'initialisation
	 * On appelle l'initialisation des cases, des pièces, ...			
	 */
	public void init () {
		this.initCases();
		this.initPieces();
		for ( int i=0; i<2; i++ ) {
			for ( int j=0; j<12; j++ ) {
				this.pieces.get(i).get(j).addObserver(this);
			}
		}
		this.listCoups.add(new Coup("< Start >"));
	    this.lastCoups = this.listCoups.get(this.listCoups.size() - 1);
	}
	/**
	 * Fonction d'initialisation des cases
	 */
	public void initCases () {
		for ( int i=0; i<8; i++ ) {
			this.cases.add(new ArrayList<Case>());
			for ( int j=0; j<8; j++ ) {
				if (i%2 == 0 && j%2 == 0) {
					this.cases.get(i).add(j, new Case(i, j, Color.RED));
				} else if (i%2 == 0 && j%2 != 0) {
					this.cases.get(i).add(j, new Case(i, j, Color.DARK_GRAY));
				} else if (i%2 != 0 && j%2 != 0) {
					this.cases.get(i).add(j, new Case(i, j, Color.RED));
				} else {
					this.cases.get(i).add(j, new Case(i, j, Color.DARK_GRAY));
				}
				this.cases.get(i).get(j).addObserver(this);
			}
		}
	}

	/**
	 * Fonction d'initialisation des pieces
	 */
	public void initPieces() {
		this.pieces.add(new ArrayList<Piece>());
		this.pieces.add(new ArrayList<Piece>());
		//Pieces noires
		this.pieces.get(0).add(0, new Piece(this.cases.get(0).get(1), 0, 1));
		this.pieces.get(0).add(1, new Piece(this.cases.get(0).get(3), 0, 2));
		this.pieces.get(0).add(2, new Piece(this.cases.get(0).get(5), 0, 3));
		this.pieces.get(0).add(3, new Piece(this.cases.get(0).get(7), 0, 4));
		this.pieces.get(0).add(4, new Piece(this.cases.get(1).get(0), 0, 5));
		this.pieces.get(0).add(5, new Piece(this.cases.get(1).get(2), 0, 6));
		this.pieces.get(0).add(6, new Piece(this.cases.get(1).get(4), 0, 7));
		this.pieces.get(0).add(7, new Piece(this.cases.get(1).get(6), 0, 8));
		this.pieces.get(0).add(8, new Piece(this.cases.get(2).get(1), 0, 9));
		this.pieces.get(0).add(9, new Piece(this.cases.get(2).get(3), 0, 10));
		this.pieces.get(0).add(10, new Piece(this.cases.get(2).get(5), 0, 11));
		this.pieces.get(0).add(11, new Piece(this.cases.get(2).get(7), 0, 12));
		
		//Pieces blanches
		this.pieces.get(1).add(0, new Piece(this.cases.get(7).get(6), 1, 1));
		this.pieces.get(1).add(1, new Piece(this.cases.get(7).get(4), 1, 2));
		this.pieces.get(1).add(2, new Piece(this.cases.get(7).get(2), 1, 3));
		this.pieces.get(1).add(3, new Piece(this.cases.get(7).get(0), 1, 4));
		this.pieces.get(1).add(4, new Piece(this.cases.get(6).get(7), 1, 5));
		this.pieces.get(1).add(5, new Piece(this.cases.get(6).get(5), 1, 6));
		this.pieces.get(1).add(6, new Piece(this.cases.get(6).get(3), 1, 7));
		this.pieces.get(1).add(7, new Piece(this.cases.get(6).get(1), 1, 8));
		this.pieces.get(1).add(8, new Piece(this.cases.get(5).get(6), 1, 9));
		this.pieces.get(1).add(9, new Piece(this.cases.get(5).get(4), 1, 10));
		this.pieces.get(1).add(10, new Piece(this.cases.get(5).get(2), 1, 11));
		this.pieces.get(1).add(11, new Piece(this.cases.get(5).get(0), 1, 12));
		
	}

	/**
	 * Fonction qui permet la liaison avec les observers
	 * @param Response res
	 */
	public void update ( Response res ) {
		this.res = res;
	}

	/**
	 * Getters des pieces
	 * @return ArrayList
	 */
	public ArrayList<ArrayList<Piece>> getPieces() {
		return pieces;
	}

	/**
	 * Setters des pieces
	 * @param ArrayList<ArrayList<Piece>> pieces
	 */
	public void setPieces(ArrayList<ArrayList<Piece>> pieces) {
		this.pieces = pieces;
	}

	/**
	 * Getters des cases
	 * @return ArrayList
	 */
	public ArrayList<ArrayList<Case>> getCases() {
		return cases;
	}

	/**
	 * Setters des cases
	 * @param ArrayList<ArrayList<Case>> cases
	 */
	public void setCases(ArrayList<ArrayList<Case>> cases) {
		this.cases = cases;
	}

	/**
	 * Fonction qui permet d'avoir la piece actuellement selectionnée
	 * @return Piece
	 */
	public Piece getSelected_piece() {
		return selected_piece;
	}

	/**
	 * Fonction qui permet de définir la piece actuellement selectionnée
	 * @param Piece selected_piece
	 */
	public void setSelected_piece(Piece selected_piece, boolean suiteCoup) {
		if(this.lastCoups.hasSuite()) {
			suiteCoup = true;
		}
		this.clearPossibles_coups();
		if ( !this.hasPossibleAttaq() ) {
			
			/*if ( this.selected_piece == null ) {*/
				if ( this.tour == selected_piece.getType() ) {
					this.selected_piece = selected_piece;
					this.setPossibles_coups();
					for ( Case pc : this.possibles_coups ) {
						pc.isPossibleCoup();
					}
				}
			/*} else {
				this.selected_piece = null;
				this.clearPossibles_coups();
			}*/	
			
		} else {
			if ( this.isPossibleAttaq(selected_piece) ) {
				/*if ( this.selected_piece == null ) {*/
					if ( this.tour == selected_piece.getType() ) {
						this.selected_piece = selected_piece;
						for ( Case pc : this.possibles_coups_fatals ) {
							pc.isPossibleCoup();
						}
					}
				/*} else {
					this.selected_piece = null;
					this.clearPossibles_coups();
				}*/
			} else {
					// this.selected_piece = null;
					this.clearPossibles_coups();
					this.hasPossibleAttaq();
					for ( Piece p : this.possibles_pieces_to_attaq ) {
						p.isMangeur();
					}
			}
		}
		if(!suiteCoup) {
			this.notifyObserver(new Response("pc"){
				public ArrayList<Case> possiblesCoups () {
					return possibles_coups.size() != 0 ? possibles_coups : possibles_coups_fatals;
				}
			});
		} else {
			this.notifyObserver(new Response("spc"){
				public ArrayList<Case> possiblesCoups () {
					return possibles_coups.size() != 0 ? possibles_coups : possibles_coups_fatals;
				}
			});
		}
		
	}

	public boolean hasAlreadyMangeur() {
		boolean res = false;
		for ( Piece p : this.pieces.get(this.tour) ) {
			if(p.isAlreadyMangeur()) {
				res = true;
				break;
			}
		}
		return res;
	}
	
	public ArrayList<Case> getPossibles_coups() {
		return possibles_coups;
	}

	/**
	 * Fonction qui permet de definir les différents coups possibles
	 * @param ArrayList possibles_coups
	 */
	public void setPossibles_coups() {
		
		if ( !this.selected_piece.isDame() ) {
			if ( this.selected_piece != null && this.selected_piece.getType() == 1 ) {
				int caseIndexi = (int) this.getCaseIndex((Case) this.selected_piece.getParent()) / 10;
				int caseIndexj = this.getCaseIndex((Case) this.selected_piece.getParent()) % 10;
				
				if ( caseIndexj != 7 && caseIndexj != 0 ) {
					if (caseIndexj < 7 && caseIndexi > 0 && !this.cases.get(caseIndexi - 1).get(caseIndexj + 1).hasChild()) {
						this.possibles_coups.add(this.cases.get(caseIndexi - 1).get(caseIndexj + 1));
					} 
					if (caseIndexj > 0 && caseIndexi > 0 && !this.cases.get(caseIndexi - 1).get(caseIndexj - 1).hasChild()) {
						this.possibles_coups.add(this.cases.get(caseIndexi - 1).get(caseIndexj - 1));
					}
				} else {
					if (caseIndexj == 7 && caseIndexi > 0 && !this.cases.get(caseIndexi - 1).get(caseIndexj - 1).hasChild()) {
						this.possibles_coups.add(this.cases.get(caseIndexi - 1).get(caseIndexj - 1));
					}
					if (caseIndexj == 0 && caseIndexi > 0 && !this.cases.get(caseIndexi - 1).get(caseIndexj + 1).hasChild()){
						this.possibles_coups.add(this.cases.get(caseIndexi - 1).get(caseIndexj + 1));
					}
				}
			} else if ( this.selected_piece != null && this.selected_piece.getType() == 0 ){
				int caseIndexi = (int) this.getCaseIndex((Case) this.selected_piece.getParent()) / 10;
				int caseIndexj = this.getCaseIndex((Case) this.selected_piece.getParent()) % 10;
				
				if ( caseIndexj != 7 && caseIndexj != 0 ) {
					if (caseIndexj < 7 && caseIndexi < 7 && !this.cases.get(caseIndexi + 1).get(caseIndexj + 1).hasChild()) {
						this.possibles_coups.add(this.cases.get(caseIndexi + 1).get(caseIndexj + 1));
					} 
					if (caseIndexj > 0 && caseIndexi < 7 && !this.cases.get(caseIndexi + 1).get(caseIndexj - 1).hasChild()) {
						this.possibles_coups.add(this.cases.get(caseIndexi + 1).get(caseIndexj - 1));
					}
				} else {
					if (caseIndexj == 7 && caseIndexi < 7 && !this.cases.get(caseIndexi + 1).get(caseIndexj - 1).hasChild()) {
						this.possibles_coups.add(this.cases.get(caseIndexi + 1).get(caseIndexj - 1));
					}
					if (caseIndexj == 0 && caseIndexi < 7 && !this.cases.get(caseIndexi + 1).get(caseIndexj + 1).hasChild()){
						this.possibles_coups.add(this.cases.get(caseIndexi + 1).get(caseIndexj + 1));
					}
				}
			} 
		} else {
			
			int caseIndexi = ((Case) this.selected_piece.getParent()).getI();
			int caseIndexj = ((Case) this.selected_piece.getParent()).getJ();
			
			int cii = caseIndexi;
			int cij = caseIndexj;
			
			int r = 0;
			if ( caseIndexi > 0 && caseIndexi < 7 ) {
				for ( int i=caseIndexi-1; i>=0; i-- ) {
					if ( caseIndexj-1 >= 0 && !this.cases.get(i).get(caseIndexj-1).hasChild()) {
						this.possibles_coups.add(r, this.cases.get(i).get(caseIndexj-1));
						r++;
					} else {
						break;
					}
					caseIndexj--;
				}
				caseIndexi = cii;
				caseIndexj = cij;
				for ( int i=caseIndexi-1; i>=0; i-- ) {
					if ( caseIndexj+1 <= 7 && !this.cases.get(i).get(caseIndexj+1).hasChild()) {
						this.possibles_coups.add(r, this.cases.get(i).get(caseIndexj+1));
						r++;
					} else {
						break;
					}
					caseIndexj++;
				}
				caseIndexi = cii;
				caseIndexj = cij;
				for ( int i=caseIndexi+1; i<=7; i++ ) {
					if ( caseIndexj-1 >= 0 && !this.cases.get(i).get(caseIndexj-1).hasChild()) {
						this.possibles_coups.add(r, this.cases.get(i).get(caseIndexj-1));
						r++;
					} else {
						break;
					}
					caseIndexj--;
				}
				caseIndexi = cii;
				caseIndexj = cij;
				for ( int i=caseIndexi+1; i<=7; i++ ) {
					if ( caseIndexj+1 <= 7 && !this.cases.get(i).get(caseIndexj+1).hasChild()) {
						this.possibles_coups.add(r, this.cases.get(i).get(caseIndexj+1));
						r++;
					} else {
						break;
					}
					caseIndexj++;
				}
			} else if ( caseIndexi == 0 ) {
				for ( int i=caseIndexi+1; i<=7; i++ ) {
					if ( caseIndexj-1 >= 0 && !this.cases.get(i).get(caseIndexj-1).hasChild()) {
						this.possibles_coups.add(r, this.cases.get(i).get(caseIndexj-1));
						r++;
					} else {
						break;
					}
					caseIndexj--;
				}
				caseIndexi = cii;
				caseIndexj = cij;
				for ( int i=caseIndexi+1; i<=7; i++ ) {
					if ( caseIndexj+1 <= 7 && !this.cases.get(i).get(caseIndexj+1).hasChild()) {
						this.possibles_coups.add(r, this.cases.get(i).get(caseIndexj+1));
						r++;
					} else {
						break;
					}
					caseIndexj++;
				}
			} else if ( caseIndexi == 7 ) {
				for ( int i=caseIndexi-1; i>=0; i-- ) {
					if ( caseIndexj-1 >= 0 && !this.cases.get(i).get(caseIndexj-1).hasChild()) {
						this.possibles_coups.add(r, this.cases.get(i).get(caseIndexj-1));
						r++;
					} else {
						break;
					}
					caseIndexj--;
				}
				caseIndexi = cii;
				caseIndexj = cij;
				for ( int i=caseIndexi-1; i>=0; i-- ) {
					if ( caseIndexj+1 <= 7 && !this.cases.get(i).get(caseIndexj+1).hasChild()) {
						this.possibles_coups.add(r, this.cases.get(i).get(caseIndexj+1));
						r++;
					} else {
						break;
					}
					caseIndexj++;
				}
			}
			
		}
		
	}
	
	/**
	 * Fonction qui permet de nettoyer la table après un déplacement
	 */
	private void clearPossibles_coups () {
		if ( this.possibles_coups_fatals.size() == 0 ) {
			for ( Case pc : this.possibles_coups ) {
				for ( MouseListener ml : pc.getMouseListeners() ) {
					pc.removeMouseListener(ml);
				}
			}
			this.possibles_coups = new ArrayList<Case>();
		} else {
			for ( Case pc : this.possibles_coups_fatals ) {
				for ( MouseListener ml : pc.getMouseListeners() ) {
					pc.removeMouseListener(ml);
				}
			}
			for ( Case c : this.possibles_coups_fatals ) {
				c.isNotPossibleCoup();
			}
			this.possibles_coups_fatals = new ArrayList<Case>();
			this.piece_a_retirer = new ArrayList<Piece>();
		}
		if ( this.possibles_pieces_to_attaq.size() != 0 ) {
			for ( Piece p : this.possibles_pieces_to_attaq ) {
				p.isNotMangeur();
			}
			this.possibles_pieces_to_attaq = new ArrayList<Piece>();
		}
	}
	
	/**
	 * Fonction qui permet d'avoir l'index d'une case dans le tableau
	 * @param Case c
	 * @return int
	 */
	public int getCaseIndex ( Case c ) {
		int x = 0, y = 0;
		for ( int i=0; i<8; i++ ) {
			for ( int j=0; j<8; j++ ) {
				if ( this.cases.get(i).get(j) == c ) {
					x = i; y = j;
				}
			}
		}
		
		return x*10 + y;
	}

	/**
	 * Fonction qui permet de savoir si une piece peut attaquer, ou pas
	 * @param Piece p
	 * @return booelan
	 */
	public boolean isPossibleAttaq ( Piece p ) {
		if ( !p.isDame() ) {
			Case c = (Case) p.getParent();
			int caseIndexi = (int) this.getCaseIndex(c) / 10;
			int caseIndexj = this.getCaseIndex(c) % 10;
			
			Case chg = null, chd = null, cbg = null, cbd = null;
			try {
				chg = this.cases.get(caseIndexi - 1).get(caseIndexj - 1);
			} catch (Exception e1) {}
			try {
				chd = this.cases.get(caseIndexi - 1).get(caseIndexj + 1);
			} catch (Exception e1) {}
			try {
				cbg = this.cases.get(caseIndexi + 1).get(caseIndexj - 1);
			} catch (Exception e1) {}
			try{
				cbd = this.cases.get(caseIndexi + 1).get(caseIndexj + 1);
			} catch (Exception e1) {}
			
			if ( chg != null && chg.getChild() != null && chg.getChild().getType() != this.tour ) {
				if ( caseIndexi - 2 >= 0 && caseIndexj - 2 >= 0 && !this.cases.get(caseIndexi-2).get(caseIndexj-2).hasChild() ) {
					this.possibles_coups_fatals.add(this.cases.get(caseIndexi-2).get(caseIndexj-2));
					this.piece_a_retirer.add(chg.getChild());
				}
			}
			if ( chd != null && chd.getChild() != null && chd.getChild().getType() != this.tour ) {
				if ( caseIndexi - 2 >= 0 && caseIndexj + 2 <= 7 && !this.cases.get(caseIndexi-2).get(caseIndexj+2).hasChild() ) {
					this.possibles_coups_fatals.add(this.cases.get(caseIndexi-2).get(caseIndexj+2));
					this.piece_a_retirer.add(chd.getChild());
				}
			}
			if ( cbg != null && cbg.getChild() != null && cbg.getChild().getType() != this.tour ) {
				if ( caseIndexi + 2 <= 7 && caseIndexj - 2 >= 0 && !this.cases.get(caseIndexi+2).get(caseIndexj-2).hasChild() ) {
					this.possibles_coups_fatals.add(this.cases.get(caseIndexi+2).get(caseIndexj-2));
					this.piece_a_retirer.add(cbg.getChild());
				}
			}
			if ( cbd != null && cbd.getChild() != null && cbd.getChild().getType() != this.tour ) {
				if ( caseIndexi + 2 <= 7 && caseIndexj + 2 <= 7 && !this.cases.get(caseIndexi+2).get(caseIndexj+2).hasChild() ) {
					this.possibles_coups_fatals.add(this.cases.get(caseIndexi+2).get(caseIndexj+2));
					this.piece_a_retirer.add(cbd.getChild());
				}
			}
			
			return this.possibles_coups_fatals.size() > 0 ? true : false;
			
		} else {
			
			Case c = (Case) p.getParent();
			int caseIndexi = (int) this.getCaseIndex(c) / 10;
			int caseIndexj = this.getCaseIndex(c) % 10;
			
			int cii = caseIndexi;
			int cij = caseIndexj;
			
			
			Case cahg = null, cahd = null, cabg = null, cabd = null;
			if ( caseIndexi > 0 && caseIndexi < 7 ) {
				for ( int i=caseIndexi-1; i>=0; i-- ) {
					if ( caseIndexj-1 >= 0 && this.cases.get(i).get(caseIndexj-1).hasChild() && this.cases.get(i).get(caseIndexj-1).getChild().getType() != this.tour ) {
						cahg = this.cases.get(i).get(caseIndexj-1);
						break;
					}
					caseIndexj--;
				}
				caseIndexi = cii;
				caseIndexj = cij;
				for ( int i=caseIndexi-1; i>=0; i-- ) {
					if ( caseIndexj+1 <= 7 && this.cases.get(i).get(caseIndexj+1).hasChild() && this.cases.get(i).get(caseIndexj+1).getChild().getType() != this.tour ) {
						cahd = this.cases.get(i).get(caseIndexj+1);
						break;
					}
					caseIndexj++;
				}
				caseIndexi = cii;
				caseIndexj = cij;
				for ( int i=caseIndexi+1; i<=7; i++ ) {
					if ( caseIndexj-1 >= 0 && this.cases.get(i).get(caseIndexj-1).hasChild() && this.cases.get(i).get(caseIndexj-1).getChild().getType() != this.tour ) {
						cabg = this.cases.get(i).get(caseIndexj-1);
						break;
					} 
					caseIndexj--;
				}
				caseIndexi = cii;
				caseIndexj = cij;
				for ( int i=caseIndexi+1; i<=7; i++ ) {
					if ( caseIndexj+1 <= 7 && this.cases.get(i).get(caseIndexj+1).hasChild() && this.cases.get(i).get(caseIndexj+1).getChild().getType() != this.tour ) {
						cabd = this.cases.get(i).get(caseIndexj+1);
						break;
					}
					caseIndexj++;
				}
			} else if ( caseIndexi == 0 ) {
				for ( int i=caseIndexi+1; i<=7; i++ ) {
					if ( caseIndexj-1 >= 0 && this.cases.get(i).get(caseIndexj-1).hasChild() && this.cases.get(i).get(caseIndexj-1).getChild().getType() != this.tour ) {
						cabg = this.cases.get(i).get(caseIndexj-1);
						break;
					} 
					caseIndexj--;
				}
				caseIndexi = cii;
				caseIndexj = cij;
				for ( int i=caseIndexi+1; i<=7; i++ ) {
					if ( caseIndexj+1 <= 7 && this.cases.get(i).get(caseIndexj+1).hasChild() && this.cases.get(i).get(caseIndexj+1).getChild().getType() != this.tour ) {
						cabd = this.cases.get(i).get(caseIndexj+1);
						break;
					}
					caseIndexj++;
				}
			} else if ( caseIndexi == 7 ) {
				for ( int i=caseIndexi-1; i>=0; i-- ) {
					if ( caseIndexj-1 >= 0 && this.cases.get(i).get(caseIndexj-1).hasChild() && this.cases.get(i).get(caseIndexj-1).getChild().getType() != this.tour ) {
						cahg = this.cases.get(i).get(caseIndexj-1);
						break;
					}
					caseIndexj--;
				}
				caseIndexi = cii;
				caseIndexj = cij;
				for ( int i=caseIndexi-1; i>=0; i-- ) {
					if ( caseIndexj+1 <= 7 && this.cases.get(i).get(caseIndexj+1).hasChild() && this.cases.get(i).get(caseIndexj+1).getChild().getType() != this.tour ) {
						cahd = this.cases.get(i).get(caseIndexj+1);
						break;
					}
					caseIndexj++;
				}
			}
			
			if ( cahg != null ) {
				if ( cahg.getI() > 0 && cahg.getJ() > 0  ) {
					int ci = cahg.getI(), cj = cahg.getJ();
					for ( int i=ci-1; i>=0; i-- ) {
						if ( cj-1 >= 0 && !this.cases.get(i).get(cj-1).hasChild()) {
							this.possibles_coups_fatals.add(this.cases.get(i).get(cj-1));
							this.piece_a_retirer.add(cahg.getChild());
						} else {
							break;
						}
						cj--;
					}
				}
			}
			if ( cahd != null ) {
				if ( cahd.getI() > 0 && cahd.getJ() < 7  ) {
					int ci = cahd.getI(), cj = cahd.getJ();
					for ( int i=ci-1; i>=0; i-- ) {
						if ( cj+1 <= 7 && !this.cases.get(i).get(cj+1).hasChild()) {
							this.possibles_coups_fatals.add(this.cases.get(i).get(cj+1));
							this.piece_a_retirer.add(cahd.getChild());
						} else {
							break;
						}
						cj++;
					}
				}
			}
			if ( cabg != null ) {
				if ( cabg.getI() < 7 && cabg.getJ() > 0  ) {
					int ci = cabg.getI(), cj = cabg.getJ();
					for ( int i=ci+1; i<=7; i++ ) {
						if ( cj-1 >= 0 && !this.cases.get(i).get(cj-1).hasChild()) {
							this.possibles_coups_fatals.add(this.cases.get(i).get(cj-1));
							this.piece_a_retirer.add(cabg.getChild());
						} else {
							break;
						}
						cj--;
					}
				}
			}
			if ( cabd != null ) {
				if ( cabd.getI() < 7 && cabd.getJ() < 7  ) {
					int ci = cabd.getI(), cj = cabd.getJ();
					for ( int i=ci+1; i<=7; i++ ) {
						if ( cj+1 <= 7 && !this.cases.get(i).get(cj+1).hasChild()) {
							this.possibles_coups_fatals.add(this.cases.get(i).get(cj+1));
							this.piece_a_retirer.add(cabd.getChild());
						} else {
							break;
						}
						cj++;
					}
				}
			}
			
			return this.possibles_coups_fatals.size() > 0 ? true : false;
			
		}
	}
	
	/**
	 * Fonction qui permet de faire le deplacement d'une piece avec attaque ou sans attaque
	 * @param suiteCoup 
	 * @param Case finish
	 */
	public void deplaceSelected_piece(Case finish, boolean suiteCoup) {
		if ( this.possibles_coups_fatals.size() != 0 ) {
			for ( int i=0; i<this.possibles_coups_fatals.size(); i++ ) {
				if ( this.possibles_coups_fatals.get(i) == finish ) {
					int index = this.selected_piece.getType() == 1 ? 0 : 1;
					for ( int j=0; j<12; j++ ) {
						boolean coupDame = this.selected_piece.isDame();
						if ( this.piece_a_retirer.get(i) == this.pieces.get(index).get(j) ) {
							this.addCoups(((Case) this.selected_piece.getParent()).getI(), ((Case) this.selected_piece.getParent()).getJ(), finish.getI(), finish.getJ(), this.selected_piece, this.pieces.get(index).get(j), suiteCoup, false, coupDame);
							this.pieces.get(index).get(j).remove();
							if ( index == 0 ) {
								this.piecesnoiresnumber--;
							} else if ( index == 1 ) {
								this.piecesblanchesnumber--;
							}
						}
					}
				}
			}
			this.selected_piece.setParent(finish);
			this.clearPossibles_coups();
			
			if ( this.isPossibleAttaq(this.selected_piece) ) {
				this.reColor();
				this.reinit(false);
				this.selected_piece = null;
				this.setSelected_piece(finish.getChild(), true);
				this.listCoups.get(listCoups.size()-1).setHasSuite(true);
				this.notifyObserver(new Response("possibleContinue"){});
			} else {
				this.isDame();
				this.reColor();
				this.reinit(true);
			}
			this.notifyObserver(new Response("winner"){
				public boolean hasWinner () {
					if ( piecesblanchesnumber <= 0 || piecesnoiresnumber <= 0 ) {
						return true;
					}
					return false;
				}
				
				public int winner () {
					if ( piecesblanchesnumber <= 0 ) {
						return 0;
					} 
					return 1;
				}
			});
		} else {
			boolean coupDame = this.selected_piece.isDame();
			this.addCoups(((Case) this.selected_piece.getParent()).getI(), ((Case) this.selected_piece.getParent()).getJ(), finish.getI(), finish.getJ(), this.selected_piece, null, suiteCoup, false, coupDame);
			this.selected_piece.setParent(finish);
			this.isDame();
			this.reColor();
			this.reinit(true);
		}
	}
	
	/**
	 * Fonction qui permet de definir si une piece est dame
	 * @return boolean
	 */
	public boolean isDame () {
		if ( !this.selected_piece.isDame() ) {
			if ( this.selected_piece.getType() == 0 && ((Case) this.selected_piece.getParent()).getI() == 7 ) {
				this.selected_piece.setDame(true);
				return true;
			} else if ( this.selected_piece.getType() == 1 && ((Case) this.selected_piece.getParent()).getI() == 0 ) {
				this.selected_piece.setDame(true);
				return true;
			} 
			return false;
		} 
		return true;
	}
	
	/**
	 * Fonction qui permet de tous reinitialiser après un coup
	 */
	public void reinit (boolean changeTour) {
		this.clearPossibles_coups();
		this.listCoups.get(this.listCoups.size()-1).setPrevTour(this.prevTour);
		this.prevTour = this.tour;
		if(changeTour) {
			this.tour = this.tour == 1 ? 0 : 1;
		}
		
		this.listCoups.get(this.listCoups.size()-1).setNextTour(this.tour);
		this.selected_piece = null;
		this.notifyObserver(new Response("changeTour"){
			public int getTour () {
				return tour;
			}
		});
	}

	/**
	 * Fonction qui permet de savoir si il y a une possible attaque 
	 * @return boolean
	 */
	private boolean hasPossibleAttaq () {
		for ( Piece p : this.pieces.get(this.tour) ) {
			if ( this.isPossibleAttaq(p) ) {
				this.possibles_pieces_to_attaq.add(p);
				this.possibles_coups_fatals = new ArrayList<Case>();
				this.piece_a_retirer = new ArrayList<Piece>();
			}
		}
		
		return this.possibles_pieces_to_attaq.size() == 0 ? false : true;
	}

	/**
	 * Fonction qui permet de recolorer la table par defaut
	 */
	public void reColor() {
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++){
				if (i%2 == 0 && j%2 == 0) {
					this.cases.get(i).get(j).setBackground(Color.RED);
				} else if (i%2 == 0 && j%2 != 0) {
					this.cases.get(i).get(j).setBackground(Color.DARK_GRAY);
				} else if (i%2 != 0 && j%2 != 0) {
					this.cases.get(i).get(j).setBackground(Color.RED);
				} else {
					this.cases.get(i).get(j).setBackground(Color.DARK_GRAY);
				}
				this.cases.get(i).get(j).repaint();
			}
		}
		
	}
	
	/**
	 * Fonction qui permet d'ajouter un coup dans la liste
	 * @param int is
	 * @param int js
	 * @param int ie
	 * @param int je
	 * @param Piece piece
	 * @param Piece aretirer
	 * @param boolean suiteCoup
	 * @param boolean coupDame
	 */
	public void addCoups (int is, int js, int ie, int je, Piece piece, Piece aretirer, boolean suiteCoup, boolean hasSuite, boolean coupDame) {
		this.removeRestCoups();
		this.listCoups.add(new Coup(is, js, ie, je, piece, aretirer, suiteCoup, hasSuite, coupDame));
		this.lastCoups = this.listCoups.get(this.listCoups.size() - 1);
		this.notifyObserver(new Response("listCoups"){
			public ArrayList<String> listCoups () {
				ArrayList<String> listCoupsString = new ArrayList<String> ();
				for ( Coup c : listCoups ) {
					listCoupsString.add(c.getChaine());
				}
				return listCoupsString;
			}
		});
	}
	
	/**
	 * Fonction qui permet de retirer tous les coups inutiles dans la liste
	 */
	public void removeRestCoups () {
		ArrayList<Coup> lc = new ArrayList<Coup>();
		for (int i=0; i<=this.getIndexLastCoups(); i++) {
			lc.add(this.listCoups.get(i));
		}
		this.listCoups = lc;
		this.notifyObserver(new Response("listCoups"){
			public ArrayList<String> listCoups () {
				ArrayList<String> listCoupsString = new ArrayList<String> ();
				for ( Coup c : listCoups ) {
					listCoupsString.add(c.getChaine());
				}
				return listCoupsString;
			}
		});
	}
	
	/**
	 * Fonction qui permet d'avoir l'index du dernier coup dans la liste
	 * @return int
	 */
	public int getIndexLastCoups () {
		int res = 0;
		for (int i=0; i<this.listCoups.size(); i++) {
			Coup c = this.listCoups.get(i);
			if ( c == this.lastCoups ){
				res = i;
				break;
			}
		}
		return res;
	}
	
	/**
	 * La fonction qui permet d'annuler un coup
	 */
	public void undo () {
		this.reColor();this.clearPossibles_coups();
		Coup coupToRemove = this.listCoups.get(this.getIndexLastCoups());
		this.lastCoups = this.listCoups.get(this.getIndexLastCoups() - 1);
		int is = coupToRemove.getIs();
		int js = coupToRemove.getJs();
		Case depart = this.cases.get(is).get(js);
		Piece piece = coupToRemove.getPiece();
		if ( !coupToRemove.isCoupDame() ) { piece.setDame(false); }
		
		if ( coupToRemove.isCoupFatal() ) {
			coupToRemove.getPiece_mange().regenere();
			if ( coupToRemove.getPiece_mange().getType() == 0 ) {
				this.piecesnoiresnumber++;
			} else if ( coupToRemove.getPiece_mange().getType() == 1 ) {
				this.piecesblanchesnumber++;
			}
			
		}
		piece.setParent(depart);
		try { this.tour = coupToRemove.getPiece().getType(); }
		catch (Exception e1) { this.tour = 1; }
		if( coupToRemove.isSuite() ) {
			this.selected_piece = piece;
		}
		this.notifyObserver(new Response("undo"){
			public boolean hasPreviousCoup () {
				return lastCoups.getChaine().equals("< START >") ? false : true;
			}
			public boolean hasNextCoup () {
				return lastCoups == listCoups.get(listCoups.size()-1) ? false : true;
			}
			
		});
		
		this.notifyObserver(new Response("changeTour"){
			public int getTour () {
				return tour;
			}
			
			public boolean isSuite() {
				return coupToRemove.isSuite();
			}
		});
	}
	
	/**
	 * La fonction qui permet de retablir le coup annulé
	 */
	public void redo () {
		this.reColor();this.clearPossibles_coups();
		this.lastCoups = this.listCoups.get(this.getIndexLastCoups() + 1);
		int ie = this.lastCoups.getIe();
		int je = this.lastCoups.getJe();
		Case finish = this.cases.get(ie).get(je);
		Piece piece = this.lastCoups.getPiece();
		if ( this.lastCoups.isCoupFatal() ) {
			if ( this.lastCoups.getPiece_mange().getType() == 0 ) {
				this.piecesnoiresnumber--;
			} else if ( this.lastCoups.getPiece_mange().getType() == 1 ) {
				this.piecesblanchesnumber--;
			}
			this.lastCoups.getPiece_mange().remove();
		}
		piece.setParent(finish);
		try { this.tour = this.lastCoups.getNextTour(); }
		catch (Exception e1) { this.tour = 1; } 
		this.selected_piece = piece;
		this.isDame();
		
		this.notifyObserver(new Response("redo"){
			public boolean hasNextCoup () {
				return lastCoups == listCoups.get(listCoups.size()-1) ? false : true;
			}
			public boolean hasPreviousCoup () {
				return lastCoups.getChaine().equals("< START >") ? false : true;
			}
		});
		this.notifyObserver(new Response("changeTour"){
			public int getTour () {
				return tour;
			}
			
			public boolean isSuite() {
				return lastCoups.hasSuite();
			}
		});
	}

	/**
	 * @param tour the tour to set
	 */
	public void setTour(final int tour) {
		this.tour = tour;
		this.notifyObserver(new Response("changeTour"){
			public int getTour () {
				return tour;
			}
		});
	}

	/**
	 * @return the tour
	 */
	public int getTour() {
		return tour;
	}

	public ArrayList<Coup> getListCoups() {
		return this.listCoups;
	}
	
	public Coup getLastCoups() {
		return this.lastCoups;
	}

}

