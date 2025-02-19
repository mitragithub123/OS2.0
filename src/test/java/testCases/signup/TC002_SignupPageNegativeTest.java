package testCases.signup;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import globalMethods.GlobalMethods;
import pageObjects.outerPages.SignupPage;


public class TC002_SignupPageNegativeTest extends BaseTest {
	public SignupPage sp;
	public GlobalMethods gm;

	@BeforeMethod
	public void setUp() {
		sp = new SignupPage(driver);
		try {
			sp.clickOnTryFreeLinkInHeader();
		} catch (InterruptedException e) {
			Assert.fail("Failed to navigate to Signup page: " + e.getMessage());
		}
	}

	// Blank email test
	@Test(priority = 1)
	public void testErrorMessageInSignupPageUsingBlankEmail() {
		try {
			sp.blankEmailSubmit();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	// Missing "@" symbol test
	@Test(priority = 2)
	public void testErrorMessageInSignupPageMissingAtSymbol() {
		try {
			sp.missingAtTheRateSymbol();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	// Test email without a top-level domain (e.g., user@domain)
	@Test(priority = 3)
	public void testEmailWithoutTopLevelDomain() {
		try {
			sp.emailWithoutTopLevelDomain();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	// Test email without a dot in the domain (e.g., user@domaincom)
	@Test(priority = 4)
	public void testEmailWithoutDotInDomain() {
		try {
			sp.emailWithoutDotInDomain();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	// Test email with invalid characters (e.g., user!@domain.com)
	@Test(priority = 5)
	public void testEmailWithInvalidCharacters() {
		try {
			sp.emailWithInvalidCharacters();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	// Test email with multiple '@' symbols (e.g user@@domain.com)
	@Test(priority = 6)
	public void testEmailWithMultipleAtSymbols() {
		try {
			sp.emailWithMultipleAtSymbols();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@AfterMethod
	public void urlCheck() {
		sp = new SignupPage(driver);
		try {
			sp.printErrorMessage();
			gm.verifySuccessfulSignup();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
}
