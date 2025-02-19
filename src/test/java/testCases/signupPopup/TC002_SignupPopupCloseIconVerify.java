package testCases.signupPopup;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageObjects.outerPages.SignupPopup;


public class TC002_SignupPopupCloseIconVerify extends BaseTest {
	@Test
	public void testSignupPopup() throws InterruptedException {
		try {
			SignupPopup sp = new SignupPopup(driver);
			sp.clickGetStartedNow();
			sp.clickOnCloseIcon();
			sp.isPopupClosed();
			
		} catch (InterruptedException e) {
			Assert.fail(e.getMessage());
		}
	}
}
