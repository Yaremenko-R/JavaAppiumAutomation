package ui;

import io.appium.java_client.AppiumDriver;

public class SearchPageObject extends MainPageObject {
  private static final String
          SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]",
          SEARCH_INPUT = "xpath://*[contains(@text,'Search')]",
          SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
          SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
          SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
          SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']",
          SEARCH_ARTICLE_TITLE = "id:org.wikipedia:id/page_list_item_title",
          SEARCH_RESULT_BY_SUBSTRING_TITLE_DESC_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@resource-id='org.wikipedia:id/page_list_item_title/@text='{TITLE}' and @resource-id='org.wikipedia:id/page_list_item_description/@text='{DESC}']";

  public SearchPageObject(AppiumDriver driver) {
    super(driver);
  }

  /*TEMPLATES METHODS*/
  private static String getResultSearchElement(String substring) {
    return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
  }

  private static String getResultSearchElementByTitleAndDesc(String title, String description) {
    return SEARCH_RESULT_BY_SUBSTRING_TITLE_DESC_TPL.replace("{TITLE}", title).replace("{DESC}", description);
  }
  /*TEMPLATES METHODS*/

  public void initSearchInput() {
    this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
    this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 15);
  }

  public void typeSearchLine(String search_line) {
    this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 15);
  }

  public void waitForSearchResult(String substring) {
    String search_result_xpath = getResultSearchElement(substring);
    this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
  }

  public void waitForElementByTitleAndDescription(String title, String description) {
    String search_result_xpath = getResultSearchElementByTitleAndDesc(title, description);
    this.waitForElementPresent(search_result_xpath, "Cannot find search result with title " + title + " and description " + description, 15);
  }

  public void waitForCancelButtonToAppear() {
    this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 15);
  }

  public void waitForCancelButtonToDisappear() {
    this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 15);
  }

  public void clickCancelSearch() {
    this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 15);
  }

  public void clickByArticleWithSubstring(String substring) {
    String search_result_xpath = getResultSearchElement(substring);
    this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 15);
  }

  public int getAmountOfFoundArticles() {
    this.waitForElementPresent(
            SEARCH_RESULT_ELEMENT,
            "Cannot find anything by request",
            15);
    return getAmountOfElements(SEARCH_RESULT_ELEMENT);
  }

  public void waitForEmptyResultsLabel() {
    this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element", 15);
  }

  public void assertThereIsNoResultOfSearch() {
    this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any result");
  }

  public void assertElementText(String expected_text) {
    this.assertElementHasText(
            SEARCH_INIT_ELEMENT,
            expected_text,
            "Actual text of the element is not equal to expected");
  }

  public void checkingSearchResultsCorrectness(String search_line) {
    this.assertSearchResultsCorrectness(
            SEARCH_ARTICLE_TITLE,
            search_line,
            "Search results do not contain expected values");
  }
}
