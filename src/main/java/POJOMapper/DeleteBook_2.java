package POJOMapper;

import com.github.javafaker.Faker;

public class DeleteBook_2 {
	

	//public static String ID;
	

	public static  Object deleteBook_2() {
			
		DeleteBookPOJO_2 book = new DeleteBookPOJO_2();
		System.out.println("CreateBook.ID :"+CreateBook.ID);
		book.setID(CreateBook.ID);

		return book;
		
	}

}
