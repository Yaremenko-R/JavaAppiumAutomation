package ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {
  private static final String
          STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
          STEP_NEW_WAYS_TO_EXPLORER_TEXT = "id:New ways to explore",
          STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "id:Add or edit preferred languages",
          STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
          NEXT_LINK = "id:Next",
          GET_STARTED_BUTTON = "id:Get started button",
          SKIP = "id:Skip";


  public WelcomePageObject(AppiumDriver driver) {
    super(driver);
  }

  public void waitForLearnMoreLink() {
    this.waitForElementPresent(STEP_LEARN_MORE_LINK, "Cannot find 'Learn more about Wikipedia' link", 15);
  }

  public void waitForNewWaysToExploreText() {
    this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORER_TEXT, "Cannot find 'New ways to explore' link", 15);
  }

  public void waitForAddOrEditPreferredLangText() {
    this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK, "Cannot find 'Add or edit preferred languages' link", 15);
  }

  public void waitForLearnMoreAboutDataCollectedText() {
    this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "Cannot find 'Learn more about data collected' link", 15);
  }

  public void clickNextButton() {
    this.waitForElementAndClick(NEXT_LINK, "Cannot find 'Next' button", 15);
  }

  public void clickGetStartedButton() {
    this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find 'Get started' button", 15);
  }

  public void clickSkip() {
    this.waitForElementAndClick(SKIP, "Cannot find and click skip button", 15);
  }
}
