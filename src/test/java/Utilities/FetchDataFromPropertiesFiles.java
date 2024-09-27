package Utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import Constants.ConstantsVar;

public class FetchDataFromPropertiesFiles {
 
	static FileReader reader;
	
	public static Properties readDataFromProperty() 
	{
		
		try {
			reader= new FileReader(ConstantsVar.Property_file_Path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//proviing the location of the "GlobalData.properties" file
		
		Properties prop = new Properties();//object creation for properties
		
		try {
			prop.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//load teh data from the Properties file
		
		return prop;
		
	}
}
