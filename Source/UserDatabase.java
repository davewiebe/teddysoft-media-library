	/*
	 * 	userDatabase.java
	 * 	
	 * 	Written by David Wiebe and Jordan McMillan
	 * 	Edited by David Wiebe and Jordan McMillan
	 * 
	 * 	Team TeddySoft is:
	 * 	David Wiebe
	 *  Frankie Yan
	 * 	Jordan McMillan
	 * 	Lisa Chen
	 */

package teddySoft;
import java.io.Serializable;

public class UserDatabase implements Serializable{

	// list of users, and integer of list size;
	private User[] userList;
	private int userListSize;
	
	// constructors
	public UserDatabase(User[] newList){
		userList = newList;
		userListSize = newList.length;
	}
	
	public UserDatabase(){
		userList = new User[100];
		userListSize = 0;
	}
	
	// PARAM: Name of user (string)
	// POST: Returns User object, null if not found.
	public User getUser(String name){//, User[] userList){
		for (int i = 0; i < this.getUserListSize(); i++){
			if (userList[i] != null && (userList[i].getName()).equals(name)){
				return userList[i];
			}
		}
		return null;
	}
	
	// PARAM:  add user with username and password;
	public boolean addUser(String username, String password){
		if (getUser(username) != null){
			return false;  //the user already exists in the system.
		}
		if (userListSize == userList.length){
			User[] temp = new User[userListSize*2];
			for (int i =0; i>userListSize; i++){
				temp[i] = userList[i];
			}
			userList = temp;
		}
		userList[userListSize] = new User(username, password);
		userListSize++;
		return true;
	}
	
	// Getters
	public User[] getUserList(){
		return userList;
	}
	
	public int getUserListSize(){
		return userListSize;
	}
	
	// Setters
	public void setUserList(User[] newList){
		userList = newList;
	}
		
}

