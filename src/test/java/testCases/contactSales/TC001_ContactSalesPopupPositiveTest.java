package testCases.contactSales;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageObjects.outerPages.ContactSalesPopup;

public class TC001_ContactSalesPopupPositiveTest extends BaseTest{
	@Test
    public void contactSupportTest() throws InterruptedException {
        try {
			ContactSalesPopup contactPage = new ContactSalesPopup(driver);

			contactPage.openContactSupportForm();
			contactPage.closeCookieBannerIfPresent();
			contactPage.enterFirstName("Test by Mitra");
			contactPage.enterEmail("andolasoft.user133@gmail.com");
			Thread.sleep(5000);
			contactPage.selectCountryIndia();
			contactPage.enterPhone("7655042701");
			contactPage.enterSubject("Test by Mitra");
			contactPage.enterMessage("Test by Mitra");
			contactPage.clickSubmit();
			Thread.sleep(7000);
			
			String resultMessage = contactPage.getResultMessage();
			System.out.println(resultMessage);
		} catch (InterruptedException e) {
			Assert.fail(e.getMessage());
		}
    }
}
