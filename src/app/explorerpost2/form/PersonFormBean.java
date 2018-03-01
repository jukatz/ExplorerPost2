package app.explorerpost2.form;

import java.io.Serializable;

public class PersonFormBean implements Serializable {
	private String firstName;
	private String lastName;
	private String numFingers;
	private String numToes;
	private String pctGlassFull;
	private String gender;	
	private String arbitraryDate = null;
	private String addEdit = null;
	
	
	
	
	public String getAddEdit() {
		return addEdit;
	}
	public void setAddEdit(String addEdit) {
		this.addEdit = addEdit;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNumFingers() {
		return numFingers;
	}
	public void setNumFingers(String numFingers) {
		this.numFingers = numFingers;
	}
	public String getNumToes() {
		return numToes;
	}
	public void setNumToes(String numToes) {
		this.numToes = numToes;
	}
	public String getPctGlassFull() {
		return pctGlassFull;
	}
	public void setPctGlassFull(String pctGlassFull) {
		this.pctGlassFull = pctGlassFull;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getArbitraryDate() {
		return arbitraryDate;
	}
	public void setArbitraryDate(String arbitraryDate) {
		this.arbitraryDate = arbitraryDate;
	}
	
	
	
	
}
