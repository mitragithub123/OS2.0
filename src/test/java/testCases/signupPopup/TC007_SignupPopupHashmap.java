package testCases.signupPopup;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import globalMethods.GlobalMethods;
import pageObjects.outerPages.SignupPopup;
import utilities.DataProviders;

public class TC007_SignupPopupHashmap extends BaseTest {
	@Test(dataProvider = "SignupDataHashMap", dataProviderClass = DataProviders.class)
	public void testSignupDataDriven(HashMap<String, String> input) throws InterruptedException {
		try {
			logger.info("Test started.");
			SignupPopup sp = new SignupPopup(driver);
			sp.clickGetStartedNow();
			sp.setEmail(input.get("email"));
			sp.setPassword(input.get("password"));
			sp.clickCreateAccBtn();

			GlobalMethods gm = new GlobalMethods(driver);
			gm.verifySuccessfulSignup();
			boolean targetPage = gm.isDashboardorLoginExists();

			String expectedResult = input.get("expRes");

			if (expectedResult.equalsIgnoreCase("Valid")) {
				if (targetPage == true) {
					sp.waitForTakeTourCloseIconToBeVisible();
					sp.logout();
					Assert.assertTrue(true);
				} else {
					Assert.fail("Expected valid signup, but test failed.");
				}
			} else if (expectedResult.equalsIgnoreCase("Invalid")) {
				sp.clickOnCloseIcon();
				Assert.fail("Expected invalid signup");
			}
			logger.info("Test ended.");
		} catch (InterruptedException e) {
			Assert.fail("Test interrupted: " + e.getMessage());
			logger.info(e.getMessage());
		}
	}
}
