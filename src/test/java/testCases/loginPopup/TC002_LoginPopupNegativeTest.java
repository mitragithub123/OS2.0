package testCases.loginPopup;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageObjects.outerPages.LoginPopup;

public class TC002_LoginPopupNegativeTest extends BaseTest {
	@Test(priority = 1, description = "Email invalid & password invalid")
	public void invalidEmailAndPasswordTest() throws InterruptedException {
		try {
			logger.info("Test started");
			LoginPopup lp = new LoginPopup(driver);
			lp.openLoginPopup();
			lp.login("andolasoft.123@gmail.com", "123");
			lp.launchPadCheck();
			logger.info("Test ended");
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 2, description = "Invalid email format and valid password")
	public void invalidEmailFormatTest() {
		try {
			logger.info("Test started");
			LoginPopup lp = new LoginPopup(driver);
			lp.openLoginPopup();
			lp.login("invalidEmailFormat", property.getProperty("password").trim());
			lp.launchPadCheck();
			logger.info("Test ended");
		} catch (Exception e) {
			logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 3, description = "Empty email and valid password")
	public void emptyEmailTest() {
		try {
			logger.info("Test started");
			LoginPopup lp = new LoginPopup(driver);
			lp.openLoginPopup();
			lp.login("", "validPassword123");
			lp.launchPadCheck();
			logger.info("Test ended");
		} catch (Exception e) {
			logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 4, description = "Valid email and empty password")
	public void emptyPasswordTest() {
		try {
			logger.info("Test: Valid email and empty password started");
			LoginPopup lp = new LoginPopup(driver);
			lp.openLoginPopup();
			lp.login(property.getProperty("email").trim(), "");
			lp.launchPadCheck();
			logger.info("Test ended");
		} catch (Exception e) {
			logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 5, description = "Empty email and empty password")
	public void emptyEmailAndPasswordTest() {
		try {
			logger.info("Test started");
			LoginPopup lp = new LoginPopup(driver);
			lp.openLoginPopup();
			lp.login("", "");
			lp.launchPadCheck();
			logger.info("Test ended");
		} catch (Exception e) {
			logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 6, description = "Exceedingly long email and password")
	public void longEmailAndPasswordTest() {
		try {
			logger.info("Test started");
			LoginPopup lp = new LoginPopup(driver);
			lp.openLoginPopup();
			String longEmail = "longemail" + "a".repeat(100) + "@example.com";
			String longPassword = "password" + "b".repeat(100);
			lp.login(longEmail, longPassword);
			lp.launchPadCheck();
			logger.info("Test ended");
		} catch (Exception e) {
			logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 7, description = "SQL injection attempt in email and password")
	public void sqlInjectionTest() {
		try {
			logger.info("Test started");
			LoginPopup lp = new LoginPopup(driver);
			lp.openLoginPopup();
			lp.login("'; DROP TABLE users; --", "password123");
			lp.launchPadCheck();
			logger.info("Test ended");
		} catch (Exception e) {
			logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 8, description = "XSS injection attempt in email and password")
	public void xssInjectionTest() {
		try {
			logger.info("Test started");
			LoginPopup lp = new LoginPopup(driver);
			lp.openLoginPopup();
			lp.login("<script>alert('XSS');</script>", "password123");
			lp.launchPadCheck();
			logger.info("Test ended");
		} catch (Exception e) {
			logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	@Test(priority = 9, description = "Invalid characters in email and password")
	public void invalidCharactersInEmailAndPasswordTest() {
		try {
			logger.info("Test started");
			LoginPopup lp = new LoginPopup(driver);
			lp.openLoginPopup();
			lp.login("invalid!@example.com", "p@$$word");
			lp.launchPadCheck();
			logger.info("Test ended");
		} catch (Exception e) {
			logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}
}
