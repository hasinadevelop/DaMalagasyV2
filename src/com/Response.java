/**
 * 
 */
package com;

import java.io.Serializable;
import java.util.ArrayList;

import com.model.Case;
import com.model.Coup;
import com.model.Dame;

/**
 * @author Hasina Develop
 *
 */
public class Response implements Serializable {

	private String id;
	
	/**
	 * 
	 */
	public Response(String id) {
		this.setId(id);
	}

	public ArrayList<Case> possiblesCoups () {
		return null;
	}
	
	public boolean hasWinner () { return false; }
	
	public int winner () { return 2; } 
	
	public int getTour () { return 2; }
	
	

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	public ArrayList<String> listCoups () {
		return new ArrayList();
	}

	public boolean hasPreviousCoup() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasNextCoup() {
		// TODO Auto-generated method stub
		return false;
	}

	public Dame getEtat() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isSuite() {
		return false;
	}

}
