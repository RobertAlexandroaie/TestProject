package com.endava.model;

import java.util.LinkedList;
import java.util.List;

public class KeywordsListContainer {
	private List<Keyword> keywordsList = new LinkedList<Keyword>(); 
	
	public KeywordsListContainer(){		
	}
	
    public KeywordsListContainer(List<Keyword> list){
    	this.keywordsList=list;		
	}

	public List<Keyword> getKeywordsList() {
		return keywordsList;
	}

	public void setKeywordsList(List<Keyword> keywordsList) {
		this.keywordsList = keywordsList;
	}
    
    
}
