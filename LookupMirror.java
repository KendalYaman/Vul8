
public class LookupMirror extends Lookup{
	Class<?> lookupClass;
	int allowedModes;
	
	public void setSecurityNull() {
		System.setSecurityManager(null);
	}
}
