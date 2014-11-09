package com.ing.hackaton.database;

import java.util.Arrays;

public class OBAccount {
	private OBAccount accounts;
	private String id;
	private String label;
	private boolean[] views_available;
	private String bank_id;
	
	private OBAccount getAccounts(){
		return accounts;
	}
	
	public void setId(OBAccount accounts){
		this.accounts = accounts;
	}
	
	private String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	private String getlabel(){
		return label;
	}
	
	public void setLabel(String label){
		this.label = label;
	}
	
	private String getBankId(){
		return bank_id;
	}
	
	public void setBankId(String bank_id){
		this.bank_id = bank_id;
	}
	
	private boolean[] getViewsAvailable(){
		return views_available;
	}
	
	public void setViewsAvailable(boolean[] views_available){
		this.views_available = views_available;
	}
	
	public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("***** Account Details *****\n");
        sb.append("ID="+getId()+"\n");
        sb.append("Label="+getlabel()+"\n");
        sb.append("Bank="+getBankId()+"\n");
        sb.append("Phone Numbers="+Arrays.toString(getViewsAvailable())+"\n");
        //sb.append("Cities="+Arrays.toString(getCities().toArray())+"\n");
        sb.append("*****************************");
         
        return sb.toString();
    }
	
}
