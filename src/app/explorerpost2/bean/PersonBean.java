package app.explorerpost2.bean;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;


public class PersonBean implements Cloneable {
	
	private String firstName;
	private String lastName;
	private int numFingers;
	private int numToes;
	private BigDecimal pctGlassFull;
	private String gender;	
	private Date arbitraryDate = null;
	 
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public int getNumFingers() {
		return numFingers;
	}

	public void setNumFingers(int numFingers) {
		this.numFingers = numFingers;
	}

	public int getNumToes() {
		return numToes;
	}

	public void setNumToes(int numToes) {
		this.numToes = numToes;
	}

	public BigDecimal getPctGlassFull() {
		return pctGlassFull;
	}
	
	
	public void setPctGlassFull(double pctGlassFull) {
		this.pctGlassFull = new BigDecimal(pctGlassFull).setScale(2, BigDecimal.ROUND_HALF_DOWN);
	}


	public void setPctGlassFull(BigDecimal pctGlassFull) {
		this.pctGlassFull = pctGlassFull;
	}
	
	public void setPctGlassFull(String pctGlassFull)
	{
		try
		{
			this.pctGlassFull = new BigDecimal(Double.parseDouble(pctGlassFull)).setScale(1, BigDecimal.ROUND_HALF_DOWN);			
		}
		catch (Exception ex)
		{
			this.pctGlassFull = new BigDecimal(0.0);			
		}
	}
	
	
	public Date getArbitraryDate() {
		return arbitraryDate;
	}
	public void setArbitraryDate(Date arbitraryDate) {
		this.arbitraryDate = arbitraryDate;
	}
	public void setArbitraryDate(String arbitraryDate) {
		try
		{
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			this.arbitraryDate = formatter.parse(arbitraryDate);
		}
		catch (Exception ex)
		{
			this.arbitraryDate = null;
			
		}
		
	}
	
	public String getFormattedArbitraryDate()
	{
		String fst = "";
		
		if (arbitraryDate != null)
			fst = new SimpleDateFormat("MM/dd/yyyy").format(arbitraryDate);
		
		return fst;
	}
	
	
	
	

	public PersonBean() {
	}

	public Object clone()
	{
		try
		{
			return super.clone();
		}
		catch(CloneNotSupportedException e)
		{
			throw new Error("Cloning not supported in this century");
		}
	}
}
