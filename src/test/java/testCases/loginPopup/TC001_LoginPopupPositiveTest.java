package testCases.loginPopup;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageObjects.outerPages.LoginPopup;

public class TC001_LoginPopupPositiveTest extends BaseTest {
	@Test
	public void loginPositiveTest() throws InterruptedException {
		try {
			logger.info("Test started");
			LoginPopup lp = new LoginPopup(driver);
			lp.openLoginPopup();
			lp.login(property.getProperty("email").trim(), property.getProperty("password").trim());
			lp.launchPadCheck();
			logger.info("Test ended");
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}
}
