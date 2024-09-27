package StepDefinition;

import POJOMapper.CreateBook.*;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;

import POJOMapper.CreateBook;
import POJOMapper.CreateBookPOJO;
import POJOMapper.DeleteBook_2;
import Utilities.FetchDataFromPropertiesFiles;
import Utilities.ReUsableData;
import Utilities.testData;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class StepDefinition {

	RequestSpecification req;
	RequestSpecification res;
	ResponseSpecification respec;
	Response response;
	public String IDActual;

	String URL_value = FetchDataFromPropertiesFiles.readDataFromProperty().getProperty("baseURI");
	String AuthorName_value = FetchDataFromPropertiesFiles.readDataFromProperty().getProperty("AuthorName");
	String Incorrect_AuthorName_value = FetchDataFromPropertiesFiles.readDataFromProperty()
			.getProperty("Incorrect_AuthorName");
	String ID_value = FetchDataFromPropertiesFiles.readDataFromProperty().getProperty("ID");
	String Incorrect_ID_value = FetchDataFromPropertiesFiles.readDataFromProperty().getProperty("Incorrect_ID");

	
	//@Before(value="@smoke_x",order=0)
	@Given("User requested to hit the application URL")
	public void user_requested_to_hit_the_application_url() {

		req = new RequestSpecBuilder().setBaseUri(URL_value).setContentType(ContentType.JSON).build();// Step-1-setting
																										// up the
																										// BaseUri

	}
	
	//@Before(value="@smoke_x",order=1)
	@Given("User will pass the payload with all details required")
	public void user_will_pass_the_payload_with_all_details_required() {
		res = given().log().all().relaxedHTTPSValidation().spec(req) // Step-2-given section and pass body
				.body(CreateBook.createBook());
	}
	
	//@Before(value="@smoke_x",order=2)
	@When("the User will hit the specific {string}")
	public void the_user_will_hit_the_specific(String endpoint) {
		respec = new ResponseSpecBuilder().expectStatusCode(200).build();// Step-4-check status code

		response = res.when().post(endpoint).then().log().all().spec(respec).extract().response();// Step-3-define
																									// method like post
																									// / put / get and
																									// etc
	}

	@Then("We are going to validate the response body with specific {string}")
	public void we_are_going_to_validate_the_response_body_with_specific(String status_code) {
		String s = status_code; // Step-4-check status code
		int status_code_actual = Integer.parseInt(s);

		int status_code_expected = response.getStatusCode();

		Assert.assertEquals(status_code_actual, status_code_expected, "Validating status code");// actual / expected
		System.out.println("Test case is passed");
	}
	
	//@Before(value="@smoke_x",order=3)
	@Then("User Validates the response body")
	public void user_validates_the_response_body() {
		System.out.println("Validate the response body");

		String response_1 = response.asString();
		System.out.println("Response is : " + response_1);

		JsonPath js = new JsonPath(response_1);
		//System.out.println("js " + js);
		String Msg = js.getString("Msg");
		IDActual=js.getString("ID");

		System.out.println("Msg Data is : " + Msg);
		System.out.println("IDActual Data is : " + IDActual);

		Assert.assertEquals(Msg, "successfully added", "Validating the Msg");
		//Assert.assertEquals(IDActual, CreateBook.createBook().ge, "Validating the Msg");

		System.out.println("Test case is passed");
	}

	@Given("User will pass the payload with incomplete details required")
	public void user_will_pass_the_payload_with_incomplete_details_required() {
		// Write code here that turns the phrase above into concrete actions
		res = given().log().all().relaxedHTTPSValidation().spec(req) // Step-2-given section and pass body
				.body(testData.ramdomdata());
	}

	@Then("User Validates the response body with incorrect data")
	public void user_validates_the_response_body_with_incorrect_data() {
		System.out.println("Validate the response body");

		String response_1 = response.asString();
		boolean responseEmpty = response_1.isEmpty();
		System.out.println("Response is Empty: " + responseEmpty);

	}

	@Then("User Validates the response body with existing data")
	public void user_validates_the_response_body_with_existing_data() {
		System.out.println("Validate the response body");

		String response_1 = response.asString();
		System.out.println("Response is : " + response_1);

		JsonPath js = new JsonPath(response_1);
		String Msg = js.getString("Msg");
		String ID = js.getString("ID");

		System.out.println("Msg Data is : " + Msg);
		System.out.println("ID Data is : " + ID);

		Assert.assertEquals(Msg, "Book Already Exists", "Validating the Msg");
		Assert.assertEquals(ID, "Velazquez Press2855", "Validating the ID");

		System.out.println("Test case is passed");
	}

	@Given("User requested to hit the application URL for get request")
	public void user_requested_to_hit_the_application_url_for_get_request() {

		RestAssured.baseURI = "http://216.10.245.166";
		res = given().log().all().queryParam("AuthorName", AuthorName_value).relaxedHTTPSValidation(); // Step-2-given
																										// section and
																										// pass body

	}

	@When("the User will hit the specific for get request {string}")
	public void the_user_will_hit_the_specific_for_get_request(String endpoint) {
		response = res.when().get(endpoint).then().log().all().extract().response();// Step-3-define method like post /
																					// put / get and etc
	}

	@Then("We are going to validate the response body with specific {string} for get request")
	public void we_are_going_to_validate_the_response_body_with_specific_for_get_request(String status_code) {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("Validate the response body");

		String response_1 = response.asString();
		System.out.println("Response is : " + response_1);

		JsonPath js = new JsonPath(response_1);
		String book_name = js.getString("[0].book_name");
		String isbn = js.getString("[0].isbn");
		String aisle = js.getString("[0].aisle");

		System.out.println("Msg Data is : " + book_name);
		System.out.println("ID Data is : " + isbn);
		System.out.println("aisle Data is : " + aisle);

		Assert.assertEquals(book_name, "The Golden Bowl", "Validating the book_name");
		Assert.assertEquals(isbn, "1-414-876-2483 x725", "Validating the isbn");
		Assert.assertEquals(aisle, "9", "Validating the aisle");

		System.out.println("Test case is passed");

	}

	@Given("User requested to hit the application URL for get request with incorrect author name")
	public void user_requested_to_hit_the_application_url_for_get_request_with_incorrect_author_name() {
		// Write code here that turns the phrase above into concrete actions
		RestAssured.baseURI = "http://216.10.245.166";
		res = given().log().all().queryParam("AuthorName", Incorrect_AuthorName_value).relaxedHTTPSValidation(); // Step-2-given
																													// section
																													// and
																													// pass
																													// body
	}

	@Given("User requested to hit the application URL for get request with blank author name")
	public void user_requested_to_hit_the_application_url_for_get_request_with_blank_author_name() {
		// Write code here that turns the phrase above into concrete actions
		RestAssured.baseURI = "http://216.10.245.166";
		res = given().log().all().queryParam("AuthorName", "").relaxedHTTPSValidation(); // Step-2-given section and
																							// pass body
	}

	@Then("We are going to validate the response body with specific {string} for get request with incorrect author name")
	public void we_are_going_to_validate_the_response_body_with_specific_for_get_request_with_incorrect_author_name(
			String status_code) {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("Validate the response body");

		String response_1 = response.asString();
		System.out.println("Response is : " + response_1);

		JsonPath js = new JsonPath(response_1);
		String msg = js.getString("msg");

		System.out.println("Msg Data is : " + msg);
		Assert.assertEquals(msg, "The book by requested bookid / author name does not exists!",
				"Validating the book_name");

		System.out.println("Test case is passed");
	}

	@Then("We are going to validate the response body with specific {string} for get request with blank author name")
	public void we_are_going_to_validate_the_response_body_with_specific_for_get_request_with_blank_author_name(
			String status_code) {

		System.out.println("Test case is passed");
	}

	@Given("User requested to hit the application URL for get request by ID")
	public void user_requested_to_hit_the_application_url_for_get_request_by_id() {
		RestAssured.baseURI = "http://216.10.245.166";
		res = given().log().all().queryParam("ID", ID_value).relaxedHTTPSValidation(); // Step-2-given section and pass
																						// body
	}

	@When("the User will hit the specific for get request {string} by ID")
	public void the_user_will_hit_the_specific_for_get_request_by_id(String endpoint) {
		response = res.when().get(endpoint).then().log().all().extract().response();// Step-3-define method like post /
																					// put / get and etc
	}

	@Then("We are going to validate the response body with specific {string} for get request by ID")
	public void we_are_going_to_validate_the_response_body_with_specific_for_get_request_by_id(String status_code) {
		System.out.println("Validate the response body");

		String response_1 = response.asString();
		System.out.println("Response is : " + response_1);

		JsonPath js = new JsonPath(response_1);
		String book_name = js.getString("[0].book_name");
		String isbn = js.getString("[0].isbn");
		String aisle = js.getString("[0].aisle");
		String author = js.getString("[0].author");

		System.out.println("book name Data is : " + book_name);
		System.out.println("isbn Data is : " + isbn);
		System.out.println("aisle Data is : " + aisle);
		System.out.println("author Data is : " + author);

		Assert.assertEquals(book_name, "Paths of Glory", "Validating the book_name");
		Assert.assertEquals(isbn, "Open University Press", "Validating the isbn");
		Assert.assertEquals(aisle, "8666", "Validating the aisle");
		Assert.assertEquals(author, "Desmond Douglas DDS", "Validating the author");

		System.out.println("Test case is passed");
	}

	@Given("User requested to hit the application URL for get request by ID with incorrect author name")
	public void user_requested_to_hit_the_application_url_for_get_request_by_id_with_incorrect_author_name() {
		System.out.println("Validate the response body");

		String response_1 = response.asString();
		System.out.println("Response is : " + response_1);

		JsonPath js = new JsonPath(response_1);
		String msg = js.getString("msg");

		System.out.println("Msg Data is : " + msg);

		Assert.assertEquals(msg, "The book by requested bookid / author name does not exists!",
				"Validating the book_name");

		System.out.println("Test case is passed");
	}

	@Given("User requested to hit the application URL for get request by ID with incorrect ID")
	public void user_requested_to_hit_the_application_url_for_get_request_by_id_with_incorrect_id() {
		// Write code here that turns the phrase above into concrete actions
		RestAssured.baseURI = "http://216.10.245.166";
		res = given().log().all().queryParam("ID", Incorrect_ID_value).relaxedHTTPSValidation(); // Step-2-given section
																									// and pass body
	}

	@Then("We are going to validate the response body with specific {string} for get request with incorrect ID")
	public void we_are_going_to_validate_the_response_body_with_specific_for_get_request_with_incorrect_id(
			String status_code) {
		System.out.println("Validate the response body");

		String response_1 = response.asString();
		System.out.println("Response is : " + response_1);

		JsonPath js = new JsonPath(response_1);
		String msg = js.getString("msg");

		System.out.println("Msg Data is : " + msg);

		Assert.assertEquals(msg, "The book by requested bookid / author name does not exists!",
				"Validating the book_name");

		System.out.println("Test case is passed");
	}

	@Given("User requested to hit the application URL for get request by ID with blank ID")
	public void user_requested_to_hit_the_application_url_for_get_request_by_id_with_blank_id() {
		RestAssured.baseURI = "http://216.10.245.166";
		res = given().log().all().queryParam("ID", "").relaxedHTTPSValidation(); // Step-2-given section and pass body
	}

	@Then("We are going to validate the response body with specific {string} for get request with blank ID")
	public void we_are_going_to_validate_the_response_body_with_specific_for_get_request_with_blank_id(
			String status_code) {

		System.out.println("Test case is passed");
	}

	@Given("User requested to hit the application URL for Delete request")
	public void user_requested_to_hit_the_application_url_for_delete_request() {
		req = new RequestSpecBuilder().setBaseUri(URL_value).setContentType(ContentType.JSON).build();// Step-1-setting
																										// up the
																										// BaseUri
	}

	@When("the User will hit the specific for Delete request {string}")
	public void the_user_will_hit_the_specific_for_delete_request(String endpoint) {
		respec = new ResponseSpecBuilder().expectStatusCode(404).build();// Step-4-check status code

		response = res.when().post(endpoint).then().log().all().spec(respec).extract().response();// Step-3-define
																									// method like post
																									// / put / get and
																									// etc
	}

	@Given("User will pass the payload with all details required for Delete request")
	public void user_will_pass_the_payload_with_all_details_required_for_delete_request() {
		// Write code here that turns the phrase above into concrete actions
		res = given().log().all().relaxedHTTPSValidation().spec(req).body(testData.ramdomdataForDelete());
	}

	@Then("We are going to validate the response body with specific {string} for Delete request")
	public void we_are_going_to_validate_the_response_body_with_specific_for_delete_request(String status_code) {
		System.out.println("Validate the response body");

		String response_1 = response.asString();
		System.out.println("Response is : " + response_1);

		JsonPath js = new JsonPath(response_1);
		String msg = js.getString("msg");

		System.out.println("Msg Data is : " + msg);

		Assert.assertEquals(msg, "Delete Book operation failed, looks like the book doesnt exists");

		System.out.println("Test case is passed");
	}

	@Given("User will pass the payload with existing data")
	public void user_will_pass_the_payload_with_existing_data() {
		res = given().log().all().relaxedHTTPSValidation().spec(req) // Step-2-given section and pass body
				.body(testData.ExistingData());
	}

	// @After("@sanity_y")
	@Given("User will pass the payload with all details required for Delete request with existing data")
	public void user_will_pass_the_payload_with_all_details_required_for_delete_request_with_existing_data() {

		//System.out.println("IDActual : " + IDActual);
		res = given().log().all().relaxedHTTPSValidation().spec(req).body(DeleteBook_2.deleteBook_2());
	}

	
	@When("the User will hit the specific for Delete request {string} with correct data")
	public void the_user_will_hit_the_specific_for_delete_request_with_correct_data(String endpoint) {
		respec = new ResponseSpecBuilder().expectStatusCode(200).build();// Step-4-check status code

		response = res.when().post(endpoint).then().log().all().spec(respec).extract().response();// Step-3-define
																									// method like post
																									// / put / get and																							// etc
	}
	
	@Then("We are going to validate the response body with specific {string} for Delete request with correct data")
	public void we_are_going_to_validate_the_response_body_with_specific_for_delete_request_with_correct_data(String status_code) {
		System.out.println("Validate the response body");

		String response_1 = response.asString();
		boolean responseEmpty = response_1.isEmpty();
		System.out.println("Response is Empty: " + responseEmpty);
	}

	@Given("User will pass the payload with all details required for Delete request with incorrect data")
	public void user_will_pass_the_payload_with_all_details_required_for_delete_request_with_incorrect_data() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("IDActual : " + CreateBook.createBook());
		res = given().log().all().relaxedHTTPSValidation().spec(req).body(DeleteBook_2.deleteBook_2());
	}




}
