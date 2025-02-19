package testCases.signupPopup;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageObjects.outerPages.SignupPopup;

public class TC005_SignupPopupExistingUserTest extends BaseTest {
	public SignupPopup sp;
	public String existingEmail;

	@BeforeMethod
	public void setUp() {
		sp = new SignupPopup(driver);
	}

	@Test(priority = 1)
	public void testSignupUser() throws InterruptedException {
		try {
			sp.clickGetStartedNow();
			existingEmail = sp.signupWithExistingUser();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 2, dependsOnMethods = { "testSignupUser" })
	public void testExistingUser() throws InterruptedException {
		try {
			sp.clickGetStartedNow();
			sp.signupWithExistingUser(existingEmail);
		} catch (InterruptedException e) {
			Assert.fail(e.getMessage());
		}

	}
}
