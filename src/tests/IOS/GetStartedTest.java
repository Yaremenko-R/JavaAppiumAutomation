package tests.IOS;

import lib.IOSTestCase;
import org.junit.Test;
import ui.WelcomePageObject;

public class GetStartedTest extends IOSTestCase {

  @Test
  public void testPassThroughWelcome() {
    WelcomePageObject WelcomePage = new WelcomePageObject(driver);

    WelcomePage.waitForLearnMoreLink();
    WelcomePage.clickNextButton();

    WelcomePage.waitForNewWaysToExploreText();
    WelcomePage.clickNextButton();

    WelcomePage.waitForAddOrEditPreferredLangText();
    WelcomePage.clickNextButton();

    WelcomePage.waitForLearnMoreAboutDataCollectedText();
    WelcomePage.clickGetStartedButton();
  }
}
