package testCases.contactSales;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageObjects.outerPages.ContactSalesPopup;

public class TC002_ContactSalesPopupCloseIconVerify extends BaseTest {
	@Test
	public void contactSupportTest() throws InterruptedException {
		try {
			logger.info("Test started");
			ContactSalesPopup contactPage = new ContactSalesPopup(driver);
			contactPage.openContactSupportForm();
			contactPage.clickCloseIcon();
			contactPage.isContactSalePopupClosed();
		} catch (Exception e) {
			logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}
		logger.info("Test ended");
	}
}
