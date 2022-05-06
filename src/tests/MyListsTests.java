package tests;

import lib.CoreTestCase;
import lib.Platform;
import org.junit.Test;
import ui.ArticlePageObject;
import ui.MyListsPageObject;
import ui.NavigationUI;
import ui.SearchPageObject;
import ui.factories.ArticlePageObjectFactory;
import ui.factories.MyListsPageObjectFactory;
import ui.factories.NavigationUIFactory;
import ui.factories.SearchPageObjectFactory;

public class MyListsTests extends CoreTestCase {
  private static final String name_of_folder = "Learning programming";

  @Test
  public void testSaveFirstArticleToMyList() {
    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

    SearchPageObject.initSearchInput();
    SearchPageObject.typeSearchLine("Java");
    SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
    ArticlePageObject.waitForTitleElement();
    String article_title = ArticlePageObject.getArticleTitle();

    if (Platform.getInstance().isAndroid()) {
      ArticlePageObject.addArticleToMyList(name_of_folder);
    } else  {
      ArticlePageObject.addArticleToMySaved();
    }

    ArticlePageObject.closeArticle();

    NavigationUI NavigationUI = NavigationUIFactory.get(driver);
    NavigationUI.clickMyLists();

    MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
    if(Platform.getInstance().isAndroid()) {
      MyListsPageObject.openFolderByName(name_of_folder);
    }
    MyListsPageObject.swipeByArticleToDelete(article_title);
  }

  @Test
  public void testSaveTwoArticles() {
    SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

    SearchPageObject.initSearchInput();
    String search_line = "Java";
    SearchPageObject.typeSearchLine(search_line);
    SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

    ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

    ArticlePageObject.waitForTitleElement();
    String article_title1 = ArticlePageObject.getArticleTitle();

    if (Platform.getInstance().isAndroid()) {
      ArticlePageObject.addArticleToMyList(name_of_folder);
    } else  {
      ArticlePageObject.addArticleToMySaved();
    }

    ArticlePageObject.closeArticle();

    SearchPageObject.typeSearchLine(search_line);
    SearchPageObject.clickByArticleWithSubstring("JavaScript");

    ArticlePageObject.waitForTitleElement();
    String article_title2 = ArticlePageObject.getArticleTitle();

    if (Platform.getInstance().isAndroid()) {
      ArticlePageObject.addArticleToMyList(name_of_folder);
    } else  {
      ArticlePageObject.addArticleToMySaved();
    }

    ArticlePageObject.closeArticle();

    NavigationUI NavigationUI = NavigationUIFactory.get(driver);
    NavigationUI.clickMyLists();

    MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

    if(Platform.getInstance().isAndroid()) {
      MyListsPageObject.openFolderByName(name_of_folder);
    }
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
