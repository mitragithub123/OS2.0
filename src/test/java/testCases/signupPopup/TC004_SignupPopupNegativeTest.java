package testCases.signupPopup;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageObjects.outerPages.SignupPopup;


public class TC004_SignupPopupNegativeTest extends BaseTest {
	public SignupPopup sp;

	@BeforeMethod
	public void setUp() throws InterruptedException {
		sp = new SignupPopup(driver);
		sp.clickGetStartedNow();
	}

	// Blank email & blank password
	@Test(priority = 1)
	public void testSignupPopup() throws InterruptedException {
		try {
			sp.testBlankFields();
		} catch (InterruptedException e) {
			Assert.fail(e.getMessage());
		}
	}

	// Valid email & blank password
	@Test(priority = 2)
	public void testvalidMailBlankPassword() throws InterruptedException {
		try {
			sp.validMailBlankPassword();

		} catch (InterruptedException e) {
			Assert.fail(e.getMessage());
		}
	}

	// Invalid email & valid password
	@Test(priority = 3)
	public void testinvalidMailValidPassword() throws InterruptedException {
		try {
			sp.invalidMailValidPassword();

		} catch (InterruptedException e) {
			Assert.fail(e.getMessage());
		}
	}
}
