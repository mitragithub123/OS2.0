package testCases.signupPopup;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import globalMethods.GlobalMethods;
import pageObjects.outerPages.SignupPopup;

public class TC001_SignupPopupPositiveTest extends BaseTest {
	@Test
	public void testSignupPopup() throws InterruptedException {
		try {
			SignupPopup sp = new SignupPopup(driver);
			sp.clickGetStartedNow();
			sp.signupPopupPositiveSumbit();
			GlobalMethods gm = new GlobalMethods(driver);
			gm.verifySuccessfulSignup();
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}
}
