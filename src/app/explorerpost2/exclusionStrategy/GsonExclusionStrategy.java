package app.explorerpost2.exclusionStrategy;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class GsonExclusionStrategy implements ExclusionStrategy{

	public boolean shouldSkipClass(Class<?> clazz){
		
		return false;
		
	}
	public boolean shouldSkipField(FieldAttributes f){
		
		return (f.getName().equals("__equalsCalc") || 
				f.getName().equals("__hashCodeCalc") ||
				f.getName().equals("maximumIntegerDigits"));
		
	}
}
