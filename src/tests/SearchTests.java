package tests;

import lib.CoreTestCase;
import org.junit.Test;
import ui.SearchPageObject;

public class SearchTests extends CoreTestCase {
  @Test
  public void testSearch() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    SearchPageObject.waitForSearchResult("Object-oriented programming language");
  }

  @Test
  public void testTextCheck() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.assertElementText("Search Wikipedia");
  }

  @Test
  public void testCancelSearch() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.waitForCancelButtonToAppear();
    SearchPageObject.clickCancelSearch();
    SearchPageObject.waitForCancelButtonToDisappear();
  }

  @Test
  public void testCancelSearch2() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

    assertTrue(
            "We found too few results",
            amount_of_search_results > 1
    );

    SearchPageObject.clickCancelSearch();
    int amount_of_search_results_after_canceling_search = SearchPageObject.getAmountOfFoundArticles();

    assertEquals("Search result is still present on a page", 0, amount_of_search_results_after_canceling_search);
  }

  @Test
  public void testSearchResultsCheck() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);
    String search_line = "JAVA";
    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine(search_line);
    SearchPageObject.checkingSearchResultsCorrectness(search_line);
  }

  @Test
  public void testAmountOfNotEmptySearch() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    String search_line = "Linkin Park Discography";
    SearchPageObject.typeSearchLine(search_line);
    int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

    assertTrue(
            "We found too few results",
            amount_of_search_results > 0
    );
  }

  @Test
  public void testAmountOfEmptySearch() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    String search_line = "fasdfassdfggdgdg";
    SearchPageObject.typeSearchLine(search_line);
    SearchPageObject.waitForEmptyResultsLabel();
    SearchPageObject.assertThereIsNoResultOfSearch();
  }
}
