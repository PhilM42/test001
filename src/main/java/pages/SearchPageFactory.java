package pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webutilities.TextParsingUtilities;

public class SearchPageFactory {
	private WebDriver driver;
	private TextParsingUtilities textParsingUtilities = new TextParsingUtilities();
	private static final Logger logger = LogManager.getLogger(SearchPageFactory.class);

// WebElements*************************************************************
	@FindBy(name = "searchval")
	private WebElement searchBox;

	@FindBy(xpath = "//div[@class='hidden flex-1 ml-0 lt:" + "flex max-w-[900px]']//button[@value='Search']")
	private WebElement searchButton;

	@FindBy(xpath = "//h1[@class='page-header search--title']")
	private WebElement headerSearchTitle;
	
	@FindBy(xpath = "//div[@id='paging']//li/a[contains(@aria-label, 'current page')]")
	private WebElement currentPageNumbertext;

	@FindBy(xpath = "//div[@id='paging']//li/a[contains(@aria-label, 'last page')]")
	private WebElement lastPageNumbertext;

	@FindBy(xpath = "//div[@id='paging']//ul/li[last()]/a")
	private WebElement nextPage;
	
	@FindBy(xpath = "//div[@id='paging']//ul/li")
	private WebElement toPages;
	
	@FindBy(xpath = "//div[@id='paging']//ul/li/a")
	private List<WebElement> pagesList;

	@FindBy(xpath = "//a[contains(@aria-label, 'Your cart')]")
	private WebElement openCart;

	@FindBy(id = "add-to-cart-button")
	private WebElement addToCartButton;

	@FindBy(xpath = "//span[@data-testid = 'itemDescription']")
	private List<WebElement> productDescription;

	@FindBy(id = "product_listing")
	private WebElement productListings;

	@FindBy(xpath = "//div[@id='product_listing']/div")
	private List<WebElement> productsOnPage;

	@FindBy(xpath = "//button[text()='Empty Cart']")
	private WebElement cartPopup;

	@FindBy(xpath = "//footer/button[following-sibling::button[text()='Cancel'][1]]")
	private WebElement EmptyCartVerify;
	
	@FindBy(xpath = "//button[text()='Empty Cart']")
	private WebElement emptyCart;
	
	@FindBy(xpath = "//div[@class='empty-cart__inner']")
	private WebElement emptyCartScreen;
	
	@FindBy(xpath = "//div[@class='cartItems']//li[@data-cart-item-id]"
			+ "//span[@class='itemDescription description overflow-visible']/a")
	private List<WebElement> cartItemDescription;		
	

// Constructor*************************************************************
	/**
	 * Initializes a new instance of the SearchPageFactory.
	 *
	 * @param driver The WebDriver instance used for page initialization.
	 */
	public SearchPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

// Methods*****************************************************************

	/**
	 * Searches for a product using the provided product name.
	 *
	 * @param productName The name of the product to search for.
	 * @throws NoSuchElementException If the searchBox WebElement is not found, this exception is caught and logged.
	 */
	public void searchForProduct(String productName) {
	    try {
	        // Try to send the product name to the searchBox element and submit the form
	        searchBox.sendKeys(productName);
	        searchBox.submit();
	    } catch (NoSuchElementException e) {
	        // Log the exception
	        logger.error("An error occurred: ", e);
	    }
	}

	/**
	 * Clears the search box.
	 *
	 * @throws NoSuchElementException If the searchBox WebElement is not found, this exception is caught and logged.
	 */
	public void clearSearchBox() {
	    try {
	        // Try to clear the searchBox element
	        searchBox.clear();
	    } catch (NoSuchElementException e) {
	        // Log the exception
	        logger.error("An error occurred: ", e);
	    }
	}
	
	/**
	 * Navigates to the next page of search results.
	 *
	 * @throws NoSuchElementException If the nextPage WebElement is not found, this exception is caught and logged.
	 */
	public void goToNextResultPage() {
	    try {
	        // Try to click on the nextPage element
	        nextPage.click();
	    } catch (NoSuchElementException e) {
	        // Log the exception
	        logger.error("An error occurred: ", e);
	    }
	}

	/**
	 * Navigates to the cart page.
	 *
	 * @throws NoSuchElementException If the openCart WebElement is not found, this exception is caught and logged.
	 */
	public void goToCartPage() {
	    try {
	        // Try to click on the openCart element
	        openCart.click();
	    } catch (NoSuchElementException e) {
	        // Log the exception
	        logger.error("An error occurred: ", e);
	    }
	}

	/**
	 * Clicks on the 'empty cart' button.
	 *
	 * @throws NoSuchElementException If the emptyCart WebElement is not found, this exception is caught and logged.
	 */
	public void clickEmptyCart() {
	    try {
	        // Try to click on the emptyCart element
	        emptyCart.click();
	    } catch (NoSuchElementException e) {
	        // Log the exception
	        logger.error("An error occurred: ", e);
	    }
	}
	
	/**
	 * Verifies if the cart is empty by clicking on the 'EmptyCartVerify' element.
	 *
	 * @throws NoSuchElementException If the 'EmptyCartVerify' WebElement is not found, this exception is caught and logged.
	 */
	public void emptyCartVerify() {
	    try {
	        // Try to click on the 'EmptyCartVerify' element
	        EmptyCartVerify.click();
	    } catch (NoSuchElementException e) {
	        // Log the exception
	        logger.error("An error occurred: ", e);
	    }
	}

	/**
	 * Checks if the cart is empty.
	 *
	 * @return true if the cart is empty, false if the WebElement is not found or the cart is not empty.
	 * @throws NoSuchElementException If the WebElement is not found, this exception is caught and the method returns false.
	 */
	public boolean isEmptyCartPresent() {
	    try {
	        // Try to check if the 'emptyCart' element is displayed
	        return emptyCart.isDisplayed();
	    } catch (NoSuchElementException e) {
	        // If the WebElement is not found, return false
	        return false;
	    }
	}
	
	/**
	 * Checks if the cart empty screen is present.
	 *
	 * @return true if the cart empty screen is displayed, false if the WebElement is not found or the cart is not empty.
	 * @throws NoSuchElementException If the WebElement is not found, this exception is caught and the method returns false.
	 */
	public boolean isCartEmptyScreenPresent() {
	    try {
	        // Try to check if the 'emptyCartScreen' element is displayed
	        return emptyCartScreen.isDisplayed();
	    } catch (NoSuchElementException e) {
	        // If the WebElement is not found, return false
	        return false;
	    }
	}
	
	/**
	 * Retrieves the page numbers from the list of pages.
	 *
	 * @return A list of integers representing the page numbers. If no pages are found, this method returns an empty list.
	 */
	public List<Integer> getPageNumbers() {
	    // Create a set to store the page numbers (a set is used to avoid duplicates)
	    Set<Integer> pageNumbersSet = new HashSet<>();    
	    // Iterate over each page in the list of pages
	    for (WebElement page : pagesList) {
	        // Get the 'aria-label' attribute of the page element
	        String ariaLabel = page.getAttribute("aria-label");   
	        // Split the 'aria-label' text into parts
	        String[] parts = ariaLabel.split(" ");
	        // Parse the last part as an integer (assuming it's the page number)
	        int pageNumber = Integer.parseInt(parts[parts.length - 1]);
	        // Add the page number to the set
	        pageNumbersSet.add(pageNumber);
	    }
	    // Convert the set of page numbers to a list and return it
	    return new ArrayList<>(pageNumbersSet);
	}

	/**
	 * Navigates to the specified page number in the search results.
	 *
	 * @param pageNumber The page number to navigate to.
	 * @throws IllegalArgumentException If the specified page number is not in the list of page numbers.
	 * @throws NoSuchElementException If the WebElement representing the page number link is not found.
	 */
	public void goToResultPage(int pageNumber) {
	    // Get the list of page numbers
	    List<Integer> pageNumbers = getPageNumbers();
	    // Check if the requested page number is in the list of page numbers
	    boolean isPageInList = pageNumbers.contains(pageNumber);
	    // If the page number is not in the list, throw an exception or log a message
	    if (!isPageInList) {
	        try {
	            throw new IllegalArgumentException("Page number " + pageNumber + " is not in the list of page numbers.");
	        } catch (IllegalArgumentException e) {
	            // Log the exception
	            logger.error("An error occurred: ", e);
	        }
	    }
	    // Define the XPath for the page number link
	    String xpath = String.format("//div[@id='paging']//ul/li/a[contains(@aria-label, 'page %d')]", pageNumber);
	    // Locate the WebElement using the formatted XPath
	    WebElement item = toPages.findElement(By.xpath(xpath));
	    // Click the requested page's button
	    item.click();		
	}

	/**
	 * Returns the count of search results.
	 *
	 * @return The count of search results. If no search results are found or if the WebElement is not found, this method returns 0.
	 * @throws NoSuchElementException If the WebElement is not found, this exception is caught and the method returns 0.
	 */
	public int returnSearchResultCount() {
	    try {
	        // Try to get the title text from the header element
	        String titleText = headerSearchTitle.getText();
	        // Create an instance of TextParsingUtilities and extract the integer count from the title text
	        return new TextParsingUtilities().getIntegerFromText(titleText);
	    } catch (NoSuchElementException e) {
	        // Log the exception
	        logger.error("An error occurred: ", e);
	        // If the WebElement is not found, return 0
	        return 0;
	    }
	}

	/**
	 * Returns the count of search result pages.
	 *
	 * @return The count of search result pages. If no search results are found or if the WebElement is not found, this method returns 0.
	 * @throws NoSuchElementException If the WebElement is not found, this exception is caught and the method returns 0.
	 */
	public int returnSearchPageCount() {
	    try {
	        // Try to get the text from the 'lastPageNumbertext' element
	        String pagingText = lastPageNumbertext.getText();
	        // Use a utility method to extract the integer from the text
	        return textParsingUtilities.getIntegerFromText(pagingText);
	    } catch (NoSuchElementException e) {
	        // Log the exception
	        logger.error("An error occurred: ", e);
	        // If the WebElement is not found, return 0
	        return 0;
	    }
	}

	/**
	 * Returns the current page number of search results from the search page
	 * currentPageNumbertext.
	 *
	 * @return The integer pageNumber extracted from the pagingText (or 0 if not
	 *         parsable or if the WebElement is not found).
	 * @throws NoSuchElementException If the WebElement is not found, this exception is caught and the method returns 0.
	 */
	public int returnCurrentSearchPageNumber() {
	    try {
	        // Try to get the text from the 'currentPageNumbertext' element
	        String pagingText = currentPageNumbertext.getText();
	        // Use a utility method to extract the integer from the text
	        return textParsingUtilities.getIntegerFromText(pagingText);
	    } catch (NoSuchElementException e) {
	        // Log the exception
	        logger.error("An error occurred: ", e);
	        // If the WebElement is not found, return 0
	        return 0;
	    }
	}

	/**
	 * This method is used to find all titles in the search results that are missing
	 * a specified keyword.
	 *
	 * @param keyword The keyword to search for in titles.
	 * @return An array of titles that do not contain the specified keyword.
	 */
	public String[] findAllTitlesMissingKeyword(String keyword) {
		// Initialize a list to store the titles missing the keyword
		List<String> missingItemsList = new ArrayList<>();
		// Set the current page to 1
		int currentPage = 1;
		// Get the total number of pages in the search results
		int totalPages = returnSearchPageCount();
		// Loop through each page in the search results
		while (currentPage <= totalPages) {
			// Check the titles on the current page for the keyword and add any missing
			// titles to the list
			checkTitlesForMissingKeyword(keyword, missingItemsList);
			// Go to the next page of search results
			goToNextResultPage();
			// Increment the current page number
			currentPage++;
		}
		// If the list of missing titles is empty, print a message indicating that no
		// titles are missing the keyword
		if (missingItemsList.isEmpty()) {
			logger.info("No titles missing the keyword '" + keyword + "'.");
		}
		// Convert the list of missing titles to an array and return it
		return missingItemsList.toArray(new String[0]);
	}

	/**
	 * Checks product titles for a missing keyword and adds them to a list if the
	 * keyword is not found.
	 *
	 * @param keyword          The keyword to search for in the titles.
	 * @param missingItemsList A list to store titles missing the keyword.
	 */
	private void checkTitlesForMissingKeyword(String keyword, List<String> missingItemsList) {
		// Iterate through each WebElement representing a product title
		for (WebElement title : productDescription) {
			// Convert the title text to lowercase for case-insensitive comparison
			String lowercaseTitle = title.getText().toLowerCase();
			// If the lowercase title does not contain the specified keyword
			if (!lowercaseTitle.contains(keyword)) {
				// Add the title to the missingItemsList
				missingItemsList.add(title.getText());
			}
		}
	}

	/**
	 * This method is used to get the count of items on a page.
	 *
	 * @return int This returns the total number of items on the page. If the WebElement is not found, this method returns 0.
	 * @throws NoSuchElementException If the WebElement is not found, this exception is caught and the method returns 0.
	 */
	public int getPageItemCount() {
	    try {
	        // Try to initialize a list of WebElements with the products on the page
	        List<WebElement> items = productsOnPage;
	        // Calculate the count of the items on the page
	        int pageItemCount = items.size();
	        // Return the count of items on the page
	        return pageItemCount;
	    } catch (NoSuchElementException e) {
	        // Log the exception
	        logger.error("An error occurred: ", e);
	        // If the WebElement is not found, return 0
	        return 0;
	    }
	}

	/**
	 * Retrieves the description of an item from a webpage, given the item's number.
	 *
	 * @param itemNumber The number of the item.
	 * @return The description of the item. If the WebElement is not found, this method returns an empty string.
	 * @throws NoSuchElementException If the WebElement is not found, this exception is caught and logged.
	 */
	public String getSearchItemDescription(int itemNumber) {
	    try {
	        // Use String.format to insert the itemNumber into the XPath
	        String xpath = String.format("//div[@id = 'product_listing']//div[%d]//span[@data-testid='itemDescription']", itemNumber);
	        // Try to locate WebElement using the formatted XPath
	        WebElement item = productListings.findElement(By.xpath(xpath));
	        // Get the text of the item (the description)
	        String itemDescription = item.getText();
	        // Return the item description
	        return itemDescription;
	    } catch (NoSuchElementException e) {
	        // Log the exception
	        logger.error("An error occurred: ", e);
	        // If the WebElement is not found, return an empty string
	        return "";
	    }
	}

	/**
	 * Retrieves the description of a cart item, given the item's number.
	 *
	 * @param itemNumber The number of the item in the cart.
	 * @return The description of the cart item. If the WebElement is not found, this method returns an empty string.
	 * @throws IndexOutOfBoundsException If the itemNumber is out of range (itemNumber < 0 || itemNumber >= cartItemDescription.size()), this exception is caught and logged.
	 */
	public String getCartItemDescription(int itemNumber) {
	    try {
	        // Try to get the cart item WebElement using the item number
	        WebElement item = cartItemDescription.get(itemNumber);
	        // Try to get the text of the item (the description)
	        String description = item.getText();
	        // Return the item description
	        return description;
	    } catch (IndexOutOfBoundsException e) {
	        // Log the exception
	        logger.error("An error occurred: ", e);
	        // If the itemNumber is out of range, return an empty string
	        return "";
	    }
	}

	/**
	 * Chooses a product from the list of available product links and adds it to the
	 * cart.
	 *
	 * @param productIndex The index of the product to choose (1-based).
	 * @throws NoSuchElementException If the WebElement is not found, this exception is caught and logged.
	 */
	public void AddItemToCart(int itemNumber) {
	    try {
	        // Use String.format to insert the itemNumber into the XPath
	        String xpath = String.format("//div[@id = 'product_listing']//div[%d]//input[@type='submit']", itemNumber);
	        // Try to locate WebElement using the formatted XPath
	        WebElement item = productListings.findElement(By.xpath(xpath));
	        // Try to click the "Add to Cart" button
	        item.click();
	    } catch (NoSuchElementException e) {
	        // Log the exception
	        logger.error("An error occurred: ", e);
	        // You can add additional actions here, such as notifying the user that the item is out of stock
	    }
	}
}