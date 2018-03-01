package app.explorerpost2.util;

public class Util {
	
	public static boolean isRetailerCardValid(String retailerCard, String opco)
	{
		boolean ret = false;
		
		if (opco.equalsIgnoreCase("STSH")) // 13 long, starts with 22
		{
			ret = ((retailerCard.trim().length() == 13) && (retailerCard.substring(0, 2).equalsIgnoreCase("22")));
		}
		
		if (opco.equalsIgnoreCase("GNTL")) // 12 long, starts with 44
		{
			ret = ((retailerCard.trim().length() == 12) && (retailerCard.substring(0, 2).equalsIgnoreCase("44")));
		}
		
		if (opco.equalsIgnoreCase("GNTC")) // 11 long, starts with 48
		{
			ret = ((retailerCard.trim().length() == 11) && (retailerCard.substring(0, 2).equalsIgnoreCase("48")));
		}
		
		if (opco.equalsIgnoreCase("MRTN")) // 11 long, starts with 48
		{
			ret = ((retailerCard.trim().length() == 11) && (retailerCard.substring(0, 2).equalsIgnoreCase("48")));
		}
		
		return ret;
	}
	
	
	
	
	

}
