package Utilities;

import java.util.LinkedHashMap;
import java.util.Map;

import POJOMapper.*;
import StepDefinition.StepDefinition;

public class testData {

	public static Object ramdomdata() {

		Map<Object, Object> book = new LinkedHashMap<Object, Object>();
		// ReUsableData obj= new ReUsableData();
		book.put("name", null);
		book.put("isbn", null);
		book.put("aisle", 0);
		book.put("author", null);

		return book;

	}

	public static Object ExistingData() {

		Map<Object, Object> book = new LinkedHashMap<Object, Object>();
		// ReUsableData obj= new ReUsableData();
		book.put("name", "Precious Bane");
		book.put("isbn", "Velazquez Press");
		book.put("aisle", 2855);
		book.put("author", "Hans Schamberger");

		return book;

	}

	public static Object ramdomdataForDelete() {

		Map<Object, Object> book = new LinkedHashMap<Object, Object>();
		// ReUsableData obj= new ReUsableData();
		book.put("ID", "Open University Press86");

		return book;

	}

	public static Object ExistingDataForDelete() {

		Map<String, String> book = new LinkedHashMap<String, String>();
//		CreateBook c1= new CreateBook();
//		String input1=c1.createBook().toString();
//		input1
////		ReUsableData obj= new ReUsableData();
		//System.out.println("CreateBook.ID : "+CreateBook.ID);
		StepDefinition s1= new StepDefinition();
		String input= s1.IDActual;
		
		
		
		book.put("ID", CreateBook.ID);
	
		return book;

	}
}
