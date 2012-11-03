	/*
	 * 	Security.java
	 * 	
	 * 	Written by David Wiebe
	 * 	Edited by Jordan McMillan
	 * 
	 * 	Team TeddySoft is:
	 * 	David Wiebe
	 *  Frankie Yan
	 * 	Jordan McMillan
	 * 	Lisa Chen
	 */

package teddySoft;

public class Security{
	
	//private KeyGen key;
	private UserDatabase users;
	
	// PARAM: username and password
	// POST: Returns string of key generated from param.
	public static String createKey(String username, String password){
		KeyGen keyToCreate = new KeyGen();
		//System.out.println(keyToCreate.generateKey(username, password));
		return keyToCreate.generateKey(username, password);
	}
	
	// PRE: must have users in database
	// PARAM: username, password, and userdatabase
	// POST: checks to make sure password is valid with key in userdatabase.  returns true
	public boolean validateKey(String username, String password, UserDatabase userDB){
		User a = userDB.getUser(username);
		if (a == null){ return false;}
		else{
			return validateKeyHelper(username, password, (userDB.getUser(username)).getKey());
		}
	}
	
	
	//PRE: Helper function of validateKey
	private static boolean validateKeyHelper(String username, String password, String key){
		if(createKey(username, password).equals(key))
			return true;
		else return false;
	}
	
	// test application
	public static void main(String [] args){
		testKeys();
	}
	
	// Test functions
	private static void testKeys(){
		System.out.println(createKey("", "jordan"));
		System.out.println(createKey("", ""));
		System.out.println(createKey("8765434546765ytwr5uytgr", " dsfkdsj fjdskl jfkdjs lfjsl"));
		System.out.println(createKey("$*&#(&$#(%)", "-1"));
		System.out.println(createKey("i love pudding", "oh, do you?"));
		
		System.out.println(validateKeyHelper("dave username", "daves password", createKey("dave username", "daves password")) + " - should be true");
		System.out.println(validateKeyHelper("dave  username", "daves password", createKey("dave username", "daves password")) + " - should be false");
		System.out.println(validateKeyHelper("dave username", "daves  password", createKey("dave username", "daves password")) + " - should be false");
		System.out.println(validateKeyHelper("daveusername", "daves password", createKey("dave username", "daves password")) + " - should be false");
		
	}
}