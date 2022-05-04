package ui.android;

import io.appium.java_client.AppiumDriver;
import ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
  static {
    SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
    SEARCH_INPUT = "xpath://*[contains(@text,'Search')]";
    SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
    SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
    SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
    SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
    SEARCH_ARTICLE_TITLE = "id:org.wikipedia:id/page_list_item_title";
    SEARCH_RESULT_BY_SUBSTRING_TITLE_DESC_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@resource-id='org.wikipedia:id/page_list_item_title/@text='{TITLE}' and @resource-id='org.wikipedia:id/page_list_item_description/@text='{DESC}']";
  }

  public AndroidSearchPageObject(AppiumDriver driver) {
    super(driver);
  }


}
