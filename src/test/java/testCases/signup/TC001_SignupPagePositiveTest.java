package testCases.signup;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import globalMethods.GlobalMethods;
import pageObjects.outerPages.SignupPage;

public class TC001_SignupPagePositiveTest extends BaseTest {
	@Test
	public void testSignupPage() throws InterruptedException {
		try {
			SignupPage sp = new SignupPage(driver);
			sp.clickOnTryFreeLinkInHeader();
			sp.signUpPositive();
			GlobalMethods gm = new GlobalMethods(driver);
			gm.verifySuccessfulSignup();
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}
}
