package com.webresturantstore.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertionError;
import org.assertj.core.api.SoftAssertions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.SearchPageFactory;

//This annotation ensures that the test methods in this class are executed in ascending order by name
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

/**
 * This TableTest class performs the following test case;
 * 	1.	Go to https://www.webstaurantstore.com/
 *	2.	Search for 'stainless work table'.
 *	3.	Check the search result ensuring every product has the word 'Table' in its title.
 *  4.	Add the last of found items to Cart.
 *	5.	Empty Cart.
 *
 *The results of the test are logged in 2 seperate log files;
 *testoutput_* logs the junit test results with a PASSES or FAILS
 *tabletest-* logs the details of the tests
 *new logs are generated for each test run with the date and time appended to the file name.
 */
public class TableTest {
 // Logger for this class
 private static final Logger logger = LogManager.getLogger(TableTest.class);
  // StringBuilder to collect the results of the tests
 private static StringBuilder builder = new StringBuilder();
  // WebDriver instance for browser automation
 private static WebDriver driver;
  // Base URL for the web application
 private static String baseUrl;
  // Page factory for the search page
 private static SearchPageFactory searchPage;
  // SoftAssertions instance for soft assertions
 private SoftAssertions softAssertions = new SoftAssertions();
 private static int testNumber = 0;
 // Rule that defines a TestWatcher. This allows us to add logic that will be executed when a test succeeds or fails
 @Rule
 public TestWatcher watchman = new TestWatcher() {
     // This method is called when a test fails
     @Override
     protected void failed(Throwable e, Description description) {
         // Increment the test number
         testNumber++;
         // Append the test number, test description, exception, and "FAIL" to the builder
         builder.append("Test ").append(testNumber).append(" -> FAILS\n").append(description).append(' ').append(e).append("\n");
     }
     // This method is called when a test succeeds
     @Override
     protected void succeeded(Description description) {
         // Increment the test number
         testNumber++;
         // Append the test number, test description, and "OK" to the builder
         builder.append("Test ").append(testNumber).append(" -> PASSES\n").append(description).append("\n");
     }
 };

 	// This method is executed before the first test method of the current class
	// 1.	Go to https://www.webstaurantstore.com/
 @BeforeClass
 public static void setUp() {
     // Set up Chrome WebDriver using WebDriverManager
     WebDriverManager.chromedriver().setup();
     driver = new ChromeDriver();
     // Base URL for the web application
     baseUrl = "https://www.webstaurantstore.com/";
     // Initialize the SearchPageFactory
     searchPage = new SearchPageFactory(driver);
     // Set implicit wait timeout to 15 seconds
     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
     // Maximize the browser window
     driver.manage().window().maximize();
     // Navigate to the base URL
     driver.get(baseUrl);
 }


	/**
	 * test1SearchForProductAndCheckMissingKeywords() is a test method 
	 * that tests searching for a product and checking for missing keywords.
	 * It uses soft assertions to allow the test to continue even if an 
	 * assertion fails, and collects all failures to be reported at the 
	 * end of the test.
	 * 
	 * 2.	Search for 'stainless work table'.
	 * 3.	Check the search result ensuring every product has the word 'Table' in its title.
	 */
 @Test
 public void test1SearchForProductAndCheckMissingKeywords() {
     // Log the start of the test
     logger.info("***Start of test1SearchForProductAndCheckMissingKeywords ***");
     // Define the keyword to search for
     String keyword = "table";
     // Perform a search for the keyword
     searchPage.searchForProduct("stainless steel table");
     // Get the count of search results and log it
     int pageCount = searchPage.returnSearchResultCount();
     logger.info("Returned search result count -> {}", pageCount);
     // Get the count of search result pages and log it
     int searchResultsCount = searchPage.returnSearchPageCount();
     logger.info("Page count -> {}", searchResultsCount);
     // Find all titles in the search results that are missing the keyword
     String[] actualMissingResults = searchPage.findAllTitlesMissingKeyword(keyword);
     // Define the expected titles that are missing the keyword (none in this case)
     String[] expectedTitlesMissingKeyword = new String[0];
     // Log the expected and actual results
     logger.info("Expected Result -> {}", Arrays.toString(expectedTitlesMissingKeyword));
     logger.info("Actual Result -> {}", Arrays.toString(actualMissingResults));
     // Log the number of titles that are missing the keyword
     logger.info("Number of Titles that are missing the keyword '{}' -> {}", keyword, actualMissingResults.length);
     // Log the current page number
     logger.info("Page Number -> {}", searchPage.returnCurrentSearchPageNumber());
     // Assert that all titles contain the keyword
     softAssertions.assertThat(actualMissingResults).as("Check that all titles contain the keyword")
             .isEqualTo(expectedTitlesMissingKeyword);
     // Assert all soft assertions
     try {
         softAssertions.assertAll();
     } catch (SoftAssertionError e) {
         // If any soft assertions fail, log the error message and rethrow the exception
         logger.error(e.getMessage());
         throw e;
     }
 }

	/**
	 * test2AddItemToCartAndVerifyCartOperations() is a test method 
	 * that tests adding the last item to the cart and verifies cart empty operations. 
	 * It uses soft assertions to allow the test to continue even if an 
	 * assertion fails, and collects all failures to be reported at the 
	 * end of the test.
	 * 
	 * 2.   Search for 'stainless work table'.
	 * 4.	Add the last of found items to Cart.
	 * 5.	Empty Cart.
	 */
	 @Test
	    public void test2AddItemToCartAndVerifyCartOperations() {
	        // Log the start of the test case
	        logger.info("***Start of test2AddItemToCartAndVerifyCartOperations ***");  
	        // Clear the SearchBox
	        searchPage.clearSearchBox();
	        // Search for a product
	        searchPage.searchForProduct("stainless steel table");
	        // Log the current page number
	        logger.info("Before Page -> {}", searchPage.returnCurrentSearchPageNumber());
	        // Get the page numbers
	        List<Integer> pageNumbers = searchPage.getPageNumbers();
	        // Log the page numbers
	        logger.info("Page numbers: {}", pageNumbers);
	        // Go to the result page
	        searchPage.goToResultPage(pageNumbers.get(pageNumbers.size()-1));
	        // Log the current page number
	        logger.info("After Page -> {}", searchPage.returnCurrentSearchPageNumber());
	        // Get the item count on the page
	        int pageItemCount = searchPage.getPageItemCount();
	        // Log the item count
	        logger.info("Page item count -> {}", pageItemCount);
	        // Get the Description of the given item from theSearch results
	        String pageitemDescription = searchPage.getSearchItemDescription(pageItemCount);
	        // Log the item description
	        logger.info("Page item description -> {}", pageitemDescription);
	        // Add an last item on the page to the cart
	        searchPage.AddItemToCart(pageItemCount);
	        // Log that the item has been added to the cart
	        logger.info("Item Added to Cart complete");
	        // Log that the cart is being opened
	        logger.info("Opening Cart");
	        // Go to the cart page
	        searchPage.goToCartPage();
	        // Get the Description of the first item in the cart (index 0 for item 1)
	        String cartitemDescription = searchPage.getCartItemDescription(0);
	        // Log the item description
	        logger.info("Cart item description -> {}", cartitemDescription);
	        // Assert that the cart is not empty
	        softAssertions.assertThat(cartitemDescription).as("Check to see if expected item is in the cart").isEqualTo(pageitemDescription);
	        // Check if the cart is not empty
	        boolean cartNotEmpty = searchPage.isEmptyCartPresent();
	        // Assert that the cart is not empty
	        softAssertions.assertThat(cartNotEmpty).as("Check to see if the cart is not empty").isEqualTo(true);
	        // Log whether the Empty Cart button is present
	        logger.info("Is Empty Cart Button Present? -> {}", cartNotEmpty);
	        // Log that the Empty Cart button is being clicked
	        logger.info("Clicking Empty Cart");
	        // Click the Empty Cart button
	        searchPage.clickEmptyCart();
	        // Log that the cart is being verified as empty
	        logger.info("Verifying Cart is empty");
	        // Verify that the cart is empty
	        searchPage.emptyCartVerify();
	        // Check if the cart is empty
	        boolean cartIsEmpty = searchPage.isCartEmptyScreenPresent();
	        // Log whether the Empty Cart screen is present
	        logger.info("Is Empty Cart Screen present? -> {}", cartIsEmpty);
	        // Assert that the cart is empty
	        softAssertions.assertThat(cartIsEmpty).as("Check to see if the cart is empty").isEqualTo(true);
	        // Try to assert all soft assertions
	        try {
	            softAssertions.assertAll();
	        } catch (SoftAssertionError e) {
	            // Print the default error message
	            logger.error(e.getMessage());
	            // Rethrow the error to fail the test to junit
	            throw e;
	        }
	    }
	 
	// This method is executed once after all tests in the current class
	 /**
	  * afterClass() is a method that’s executed once after all tests in the current class. 
	  * It checks if driver is not null, and if it’s not, it quits the WebDriver instance, 
	  * closing the browser session. It then creates a DateTimeFormatter with the desired 
	  * pattern, gets the current date and time, and formats the current date and time as a 
	  * string. This string is then used to create a PrintWriter that writes to a log file (testoutput_*). 
	  * The contents of builder are written to this log file. If an IOException is thrown 
	  * (for example, if the file cannot be created, opened, or written to), 
	  * it will be caught and handled.
	  */
	@AfterClass
	public static void afterClass() throws IOException {
		if (driver != null) {
			driver.quit();
			// Create a DateTimeFormatter with the desired pattern
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
			// Get the current date and time
			LocalDateTime now = LocalDateTime.now();
			// Format the current date and time as a string
			String formattedNow = dtf.format(now);
			// Try-with-resources statement for automatic resource management
			try (PrintWriter logFile = new PrintWriter("logs\\testoutput_" + formattedNow + ".txt", "UTF-8")) {
				// Write the contents of builder to the log file
				logFile.write(builder.toString());
			}
			// IOException is thrown if the file cannot be created, opened, or written to
		}
	}
}
