package com.alena.litvinova.services;

import java.util.ArrayList;
import java.util.List;

public class SearchStruct {
	
	public Result result;
	public Integer id;
	public List<Integer> bookIds = new ArrayList<Integer>();
	public SearchStruct(){
		result = Result.NOT_FOUND;
	}
	public enum Result {
	    NOT_FOUND,
	    WRITER_FOUND,
	    BOOK_FOUND
	}
}
