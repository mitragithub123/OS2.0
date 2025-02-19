package testCases.signup.googleSignup;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import globalMethods.GlobalMethods;
import pageObjects.outerPages.SignupPage;

public class GoogleSignupPositiveTest extends BaseTest {
	@Test
	public void testGoogleSignup() throws InterruptedException {
		try {
			logger.info("Test started");
			SignupPage sp = new SignupPage(driver);
			sp.clickOnTryFreeLinkInHeader();
			sp.clickGoogleSignup();
			GlobalMethods gm = new GlobalMethods(driver);
			gm.switchToWindow();
			sp.enterEmail(property.getProperty("email1"));
			sp.clickNextOnEmail();
			sp.enterPassword(property.getProperty("password1"));
			sp.clickNextOnPassword();
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}
		logger.info("Test ended");
	}
}
