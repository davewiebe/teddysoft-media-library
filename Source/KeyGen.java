	/*
	 * 	KeyGen.java
	 * 	
	 * 	Written by David Wiebe
	 * 	
	 * 
	 * 	Team TeddySoft is:
	 * 	David Wiebe
	 *  Frankie Yan
	 * 	Jordan McMillan
	 * 	Lisa Chen
	 */
package teddySoft;

//import javax.crypto.*;
//import java.security.*;

public class KeyGen {
	
	//KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(")
	
	// PRE: username and password
	// PARAM: username and password
	// POST: Returns randomized key based on username and password.
	public String generateKey(String username, String password){
				
		// generate key based on username and password.
		String key = "";
		int u = 0;
		int p = 0;
		for (int i = 0; i < (username.length() + password.length()-2); i++){
			if (u<username.length() && p<password.length()){ // randomize key
				if(i%2==1){
					key+= (char)(password.charAt(p)+i+(p+1%u+1));
					p++;
				}else{
					key+= (char)(username.charAt(u)+i+((u+1)%(p+1)));
					
					u++;
				}
			}
			if(p>=password.length()){
				key+= (char)(username.charAt(u)+i+((p+1)%(u+1)));
				
				u++;
			}
			if(u>=username.length()){
				key+= (char)(password.charAt(p)+i+((u+1)%(p+1)));
				
				p++;
			}
		}
		//System.out.println(key);
		return key;
	}
		
	
}
