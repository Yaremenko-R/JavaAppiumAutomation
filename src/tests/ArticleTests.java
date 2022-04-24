package tests;

import lib.CoreTestCase;
import org.junit.Test;
import ui.ArticlePageObject;
import ui.SearchPageObject;

public class ArticleTests extends CoreTestCase {

  @Test
  public void testCompareArticleTitle() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
    ArticlePageObject.waitForTitleElement();
    String article_title = ArticlePageObject.getArticleTitle();

    assertEquals(
            "Unexpected title",
            "Java (programming language)",
            article_title);
  }

  @Test
  public void testAssertArticleTitleWithNoWait() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
    ArticlePageObject.getArticleTitleNoWait();
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
}
