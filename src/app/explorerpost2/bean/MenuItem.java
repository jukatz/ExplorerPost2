package app.explorerpost2.bean;

public class MenuItem {
	String key = null;
	String value = null;
	
	public MenuItem(){
		
	}

	public MenuItem(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "MenuItem [key=" + key + ", value=" + value + "]";
	}
	
	
	

}
