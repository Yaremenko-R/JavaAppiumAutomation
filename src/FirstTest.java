import lib.CoreTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import ui.*;

public class FirstTest extends CoreTestCase {
  private MainPageObject MainPageObject;

  protected void setUp() throws Exception {
    super.setUp();
    MainPageObject = new MainPageObject(driver);
  }

  @Test
  public void testSearch() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    SearchPageObject.waitForSearchResult("Object-oriented programming language");
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
  public void testCompareArticleTitle() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
    ArticlePageObject.waitForTitleElement();
    String article_title = ArticlePageObject.getArticleTitle();

    Assert.assertEquals(
            "Unexpected title",
            "Java (programming language)",
            article_title);
  }

  @Test
  public void testTextCheck() {
    MainPageObject.waitForElementPresent(
            By.xpath("//*[contains(@text,'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' Input",
            10);

    MainPageObject.assertElementHasText(
            By.xpath("//*[contains(@text,'Search Wikipedia')]"),
            "Search Wikipedia",
            "Actual text of the element is not equal to expected text");
  }

  @Test
  public void testCancelSearch2() {
    MainPageObject.waitForElementAndClick(
            By.id("org.wikipedia:id/search_container"),
            "Cannot find 'Search Wikipedia' Input",
            10);

    MainPageObject.waitForElementAndSendKeys(
            By.xpath("//*[contains(@text,'Search')]"),
            "Java",
            "Cannot find search input",
            15);

    MainPageObject.waitForElementPresent(
            By.id("org.wikipedia:id/search_results_list"),
            "Search results list is empty",
            15);

    MainPageObject.waitForElementAndClick(
            By.id("org.wikipedia:id/search_close_btn"),
            "Cannot find X to cancel search",
            10);

    MainPageObject.waitForElementPresent(
            By.id("org.wikipedia:id/search_empty_message"),
            "Search results is still on the page",
            15);
  }

  @Test
  public void testSearchResultsCheck() {
    MainPageObject.waitForElementAndClick(
            By.id("org.wikipedia:id/search_container"),
            "Cannot find 'Search Wikipedia' Input",
            10);

    MainPageObject.waitForElementAndSendKeys(
            By.xpath("//*[contains(@text,'Search')]"),
            "JAVA",
            "Cannot find search input",
            15);

    MainPageObject.assertSearchResultsCorrectness(
            By.id("org.wikipedia:id/page_list_item_title"),
            "JAVA",
            "Search results do not contain expected values");
  }

  @Test
  public void testSwipeArticle() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Appium");
    SearchPageObject.clickByArticleWithSubstring("Appium");

    ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
    ArticlePageObject.waitForTitleElement();
    ArticlePageObject.swipeToFooter();
  }

  @Test
  public void testSaveFirstArticleToMyList() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
    ArticlePageObject.waitForTitleElement();
    String article_title = ArticlePageObject.getArticleTitle();
    String name_of_folder = "Learning programming";
    ArticlePageObject.addArticleToMyList(name_of_folder);
    ArticlePageObject.closeArticle();

    NavigationUI NavigationUI = new NavigationUI(driver);
    NavigationUI.clickMyLists();

    MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
    MyListsPageObject.openFolderByName(name_of_folder);
    MyListsPageObject.swipeByArticleToDelete(article_title);
  }

  @Test
  public void testAmountOfNotEmptySearch() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    String search_line = "Linkin Park Discography";
    SearchPageObject.typeSearchLine(search_line);
    int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

    Assert.assertTrue(
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

  @Test
  public void testChangingScreenOrientationOnSearchResults() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
    ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
    String title_before_rotation = ArticlePageObject.getArticleTitle();
    this.rotateScreenLandscape();
    String title_after_rotation = ArticlePageObject.getArticleTitle();

    Assert.assertEquals(
            "Article title has been changed after rotation",
            title_before_rotation,
            title_after_rotation);

    this.rotateScreenPortrait();
    String title_after_second_rotation = ArticlePageObject.getArticleTitle();

    Assert.assertEquals(
            "Article title has been changed after rotation",
            title_before_rotation,
            title_after_second_rotation);
  }

  @Test
  public void testCheckSearchArticleInBackground() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    SearchPageObject.waitForSearchResult("Object-oriented programming language");
    this.backgroundApp(2);
    SearchPageObject.waitForSearchResult("Object-oriented programming language");
  }

  @Test
  public void testSaveTwoArticles() {
    MainPageObject.waitForElementAndClick(
            By.xpath("//*[contains(@text,'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' Input",
            15);

    String search_line = "Java";

    MainPageObject.waitForElementAndSendKeys(
            By.xpath("//*[contains(@text,'Search')]"),
            search_line,
            "Cannot find search input",
            15);

    String article1_locator = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']";

    MainPageObject.waitForElementAndClick(
            By.xpath(article1_locator),
            "Cannot find expected article",
            15);

    MainPageObject.waitForElementPresent(
            By.id("org.wikipedia:id/view_page_title_text"),
            "Cannot find article title",
            15);

    MainPageObject.waitForElementAndClick(
            By.xpath("//android.widget.FrameLayout[@content-desc='More options']"),
            "Cannot find button to open article options",
            15);

    MainPageObject.waitForElementAndClick(
            By.xpath("//*[@text='Add to reading list']"),
            "Cannot find option to add article to reading list",
            15);

    MainPageObject.waitForElementAndClick(
            By.id("org.wikipedia:id/onboarding_button"),
            "Cannot find 'Got it' tip overlay",
            15);

    MainPageObject.waitForElementAndClear(
            By.id("org.wikipedia:id/text_input"),
            "Cannot find input to set name of article's folder",
            15);

    String name_of_folder = "Learning programming";

    MainPageObject.waitForElementAndSendKeys(
            By.id("org.wikipedia:id/text_input"),
            name_of_folder,
            "Cannot put text into article's folder input",
            15);

    MainPageObject.waitForElementAndClick(
            By.xpath("//*[@text='OK']"),
            "Cannot press OK button",
            15);

    MainPageObject.waitForElementAndClick(
            By.xpath("//android.widget.ImageButton[@content_desc='Navigate up']"),
            "Cannot close article, cannot find X link",
            15);

    MainPageObject.waitForElementAndClick(
            By.xpath("//*[contains(@text,'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' Input",
            15);

    MainPageObject.waitForElementAndSendKeys(
            By.xpath("//*[contains(@text,'Search')]"),
            search_line,
            "Cannot find search input",
            15);

    String article2_locator = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='JavaScript']";

    MainPageObject.waitForElementAndClick(
            By.xpath(article2_locator),
            "Cannot find expected article",
            15);

    MainPageObject.waitForElementPresent(
            By.id("org.wikipedia:id/view_page_title_text"),
            "Cannot find article title",
            15);

    String article_title_before = MainPageObject.waitForElementAndGetAttribute(
            By.xpath(article2_locator),
            "text",
            "Cannot find the title of the article",
            15);

    MainPageObject.waitForElementAndClick(
            By.xpath("//android.widget.FrameLayout[@content-desc='More options']"),
            "Cannot find button to open article options",
            15);

    MainPageObject.waitForElementAndClick(
            By.xpath("//*[@text='Add to reading list']"),
            "Cannot find option to add article to reading list",
            15);

    MainPageObject.waitForElementAndClick(
            By.xpath("//*[@text='" + name_of_folder + "']"),
            "Cannot find list " + name_of_folder,
            15);

    MainPageObject.waitForElementAndClick(
            By.xpath("//android.widget.ImageButton[@content_desc='Navigate up']"),
            "Cannot close article, cannot find X link",
            15);

    MainPageObject.waitForElementAndClick(
            By.xpath("//android.widget.FrameLayout[@content_desc='My lists']"),
            "Cannot find navigation button to My list",
            15);

    MainPageObject.waitForElementAndClick(
            By.xpath("//*[@text='" + name_of_folder + "']"),
            "Cannot find list " + name_of_folder,
            15);

    MainPageObject.swipeElementToLeft(
            By.xpath("//*[@text='Java (programming language)']"),
            "Cannot find saved article");

    MainPageObject.waitForElementNotPresent(
            By.xpath("//*[@text='Java (programming language)']"),
            "Cannot delete saved article",
            15);

    MainPageObject.waitForElementPresent(
            By.xpath("//*[@text='JavaScript']"),
            "Cannot find the second saved article in the list " + name_of_folder,
            15);

    MainPageObject.waitForElementAndClick(
            By.xpath(article2_locator),
            "Cannot find expected article",
            15);

    String article_title_after = MainPageObject.waitForElementAndGetAttribute(
            By.xpath("//*[@text='JavaScript']"),
            "text",
            "Cannot find the title of the article",
            15);

    Assert.assertEquals(
            "Title of the article in the list '" + name_of_folder + "' is not matching",
            article_title_before,
            article_title_after);
  }

  @Test
  public void testAssertTitleNoWait() {
    MainPageObject.waitForElementAndClick(
            By.xpath("//*[contains(@text,'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' Input",
            15);

    String search_line = "Java";

    MainPageObject.waitForElementAndSendKeys(
            By.xpath("//*[contains(@text,'Search')]"),
            search_line,
            "Cannot find search input",
            15);

    String article_locator = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']";

    MainPageObject.waitForElementAndClick(
            By.xpath(article_locator),
            "Cannot find expected article",
            15);

    MainPageObject.noWaitForElementAndGetAttribute(
            By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']"),
            "text",
            "Cannot find the title of the article");
  }
}