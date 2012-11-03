	/*
	 * 	DatabaseTest.java
	 * 	
	 * 	Written by Jordan McMillan
	 * 	
	 * 
	 * 	Team TeddySoft is:
	 * 	David Wiebe
	 *  Frankie Yan
	 * 	Jordan McMillan
	 * 	Lisa Chen
	 */

package teddySoft;

import java.io.Serializable;

	public class DatabaseTest implements Serializable{
		
		public class node implements Serializable{
		    protected Books data;
		    protected node next;

		    public node(){
		        next = null;
		        data = null;
		    }
		    public node(Books d,node n){
		        data = d;
		        next = n;
		    }
		    public void setNext(node n){
		        next = n;
		    }
		    public void setData(Books d){
		        data = d;
		    }
		    public node getNext(){
		        return next;
		    }
		    public Books getData(){
		        return data;
		    }
		    public String toString(){
		        return ""+data;
		    }
		}
		
		
		///////////
	    protected node head;
	    protected int number;

	    public DatabaseTest(){
	        head = null;
	        number = 0;
	    }
	    public boolean isEmpty(){
	        return head == null;
	    }
	    public int size(){
	        return number;
	    }
	    public void insert(Books obj){
	        head = new node(obj,head);
	        number++;
	    }
	    public Books remove(){
	        if(isEmpty())
	            return null;
	        node tmp = head;
	        head = tmp.getNext();
	        number--;
	        return tmp.getData();
	    }
	    public void insertEnd(Books obj){
	        if(isEmpty())
	            insert(obj);
	        else{
	            node t = head;
	            while(t.getNext() != null)
	                t=t.getNext();
	            node tmp =
	                new node(obj,t.getNext());
	            t.setNext(tmp);
	            number++;
	        }
	    }
	    public Books removeEnd(){
	        if(isEmpty())
	            return null;
	        if(head.getNext() == null)
	            return remove();
	        node t = head;
	        while(t.getNext().getNext() != null)
	            t = t.getNext();
	        Books obj = t.getNext().getData();
	        t.setNext(t.getNext().getNext());
	        number--;
	        return obj;
	    }
	    public Books peek(int n){
	        node t = head;
	        for(int i = 0;i<n && t != null;i++)
	            t = t.getNext();
	        return t.getData();
	    }
	    
	    public void print(){
	    	printhelp(head);
	    }
	    private void printhelp(node t){
	    	if (t != head && t == null)
	    		return;
	    	else
	    	printhelp(t.next);
	    	System.out.println(t.data.getTitle());
	    }
	}
