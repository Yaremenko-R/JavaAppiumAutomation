package tests;

import lib.CoreTestCase;
import org.junit.Test;
import ui.ArticlePageObject;
import ui.SearchPageObject;

public class ChangeAppConditionsTests extends CoreTestCase {
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

    assertEquals(
            "Article title has been changed after rotation",
            title_before_rotation,
            title_after_rotation);

    this.rotateScreenPortrait();
    String title_after_second_rotation = ArticlePageObject.getArticleTitle();

    assertEquals(
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
}
