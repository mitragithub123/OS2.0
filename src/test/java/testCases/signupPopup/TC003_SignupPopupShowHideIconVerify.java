package testCases.signupPopup;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageObjects.outerPages.SignupPopup;


public class TC003_SignupPopupShowHideIconVerify extends BaseTest {
	@Test
	public void testSignupPopup() throws InterruptedException {
		try {
			SignupPopup sp = new SignupPopup(driver);
			sp.clickGetStartedNow();
			sp.showAndHide();
		} catch (InterruptedException e) {
			Assert.fail(e.getMessage());
		}
	}
}
