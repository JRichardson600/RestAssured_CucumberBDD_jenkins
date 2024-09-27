package POJOMapper;

import com.github.javafaker.Faker;

public class CreateBook {
	

	public static String ID;
	

	public static Object createBook() {
		
		Faker faker = new Faker();

		String name = faker.book().title();
		String Isbn = faker.book().publisher();
		int Aisle = faker.number().numberBetween(1000, 9999);
		String Author = faker.book().author();
		
		ID = Isbn + Aisle;
		
		CreateBookPOJO book = new CreateBookPOJO();

		book.setName(name);
		book.setIsbn(Isbn);
		book.setAisle(Aisle);
		book.setAuthor(Author);
//		book.setID(Isbn + Aisle);

		return book;
		
	}

}
