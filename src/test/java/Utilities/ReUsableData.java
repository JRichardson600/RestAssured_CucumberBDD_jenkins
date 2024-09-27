package Utilities;

import com.github.javafaker.Faker;

public class ReUsableData {

	public static Faker generateRandomData() {
		
		Faker faker = new Faker();
		
		String name= faker.book().title();
		String Isbn= faker.book().publisher();
		int Aisle= faker.number().randomDigit();
		String Author= faker.book().author();
		String ID = Isbn + Aisle;
		
		return faker;
		
	}
	
}
