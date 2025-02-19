package testCases.signup;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageObjects.outerPages.SignupPage;


public class TC003_SignupPageExistingUserTest extends BaseTest {
	public SignupPage sp;
	public String existingEmail;

	@BeforeMethod
	public void setUp() {
		sp = new SignupPage(driver);
	}

	@Test(priority = 1)
	public void testSignupUser() throws InterruptedException {
		try {
			sp.clickOnTryFreeLinkInHeader();
			existingEmail = sp.signUpWithExistingEmail();
			sp.signUpWithExistingEmail(existingEmail);
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Assert.fail(e.getMessage());
		}
	}

}
