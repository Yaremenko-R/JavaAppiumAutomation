package tests;

import lib.CoreTestCase;
import org.junit.Test;
import ui.ArticlePageObject;
import ui.MyListsPageObject;
import ui.NavigationUI;
import ui.SearchPageObject;

public class MyListsTests extends CoreTestCase {
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
  public void testSaveTwoArticles() {
    SearchPageObject SearchPageObject = new SearchPageObject(driver);

    SearchPageObject.initSearchInput();
    String search_line = "Java";
    SearchPageObject.typeSearchLine(search_line);
    SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

    ArticlePageObject.waitForTitleElement();
    String article_title1 = ArticlePageObject.getArticleTitle();
    String name_of_folder = "Learning programming";
    ArticlePageObject.addArticleToMyList(name_of_folder);
    ArticlePageObject.closeArticle();

    SearchPageObject.typeSearchLine(search_line);
    SearchPageObject.clickByArticleWithSubstring("JavaScript");

    ArticlePageObject.waitForTitleElement();
    String article_title2 = ArticlePageObject.getArticleTitle();
    ArticlePageObject.addArticleToMyList(name_of_folder);
    ArticlePageObject.closeArticle();

    NavigationUI NavigationUI = new NavigationUI(driver);
    NavigationUI.clickMyLists();

    MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
    MyListsPageObject.openFolderByName(name_of_folder);
    MyListsPageObject.swipeByArticleToDelete(article_title1);
    MyListsPageObject.waitForArticleToAppearByTitle(article_title2);
    SearchPageObject.clickByArticleWithSubstring("JavaScript");
    String article_title2_after = ArticlePageObject.getArticleTitle();

    assertEquals(
            "Title of the article in the list '" + name_of_folder + "' is not matching",
            article_title2,
            article_title2_after);
  }
}
