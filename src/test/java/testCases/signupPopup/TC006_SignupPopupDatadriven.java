package testCases.signupPopup;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import globalMethods.GlobalMethods;
import pageObjects.outerPages.SignupPopup;
import utilities.DataProviders;

public class TC006_SignupPopupDatadriven extends BaseTest {
	@Test(dataProvider = "SignupData", dataProviderClass = DataProviders.class)
	public void testSignupDataDriven(String email, String password, String expectedResult) throws InterruptedException {
		try {
			logger.info("Test started.");
			SignupPopup sp = new SignupPopup(driver);
			sp.clickGetStartedNow();
			sp.setEmail(email);
			sp.setPassword(password);
			sp.clickCreateAccBtn();
			GlobalMethods gm = new GlobalMethods(driver);
			gm.verifySuccessfulSignup();
			boolean targetPage = gm.isDashboardorLoginExists();

			if (expectedResult.equalsIgnoreCase("Valid")) {
				if (targetPage == true) {
					sp.waitForTakeTourCloseIconToBeVisible();
					sp.logout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			} else if (expectedResult.equalsIgnoreCase("Invalid")) {
				if (targetPage == true) {
					Assert.assertTrue(false);
					sp.waitForTakeTourCloseIconToBeVisible();
					sp.logout();
				} else {
					Assert.assertTrue(true);
				}
			}
			logger.info("Test ended.");
		} catch (InterruptedException e) {
			Assert.fail(e.getMessage());
			logger.info(e.getMessage());
		}
	}
}
