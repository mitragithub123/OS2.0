package pageObjects.outerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import globalMethods.GlobalMethods;
import pageObjects.BasePage;

public class ContactSalesPopup extends BasePage {

	public ContactSalesPopup(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "li.parent_li a[title='Contact Sales']")
	public WebElement contactSalesLink;

	@FindBy(css = "a[title='Accept']")
	public WebElement cookieBannerCloseButton;

	@FindBy(css = "#txt_fname")
	public WebElement firstNameField;

	@FindBy(css = "#txt_email")
	public WebElement emailField;

	@FindBy(css = "div[id='v_phone'] div[title='United States: +1']")
	public WebElement countryDropdown;

	@FindBy(xpath = "//div[@id='v_phone']//li[@id='iti-item-in']")
	public WebElement selectIndia;

	@FindBy(css = "#txt_phone")
	public WebElement phoneField;

	@FindBy(css = "#txt_list_box")
	public WebElement subjectField;

	@FindBy(css = "#message_id")
	public WebElement messageField;

	@FindBy(css = "button[type='submit']")
	public WebElement submitButton;

	@FindBy(css = ".hero_tle h1")
	public WebElement resultMessage;

	@FindBy(xpath = "(//button[@class='close' and @data-dismiss='modal'])[3]")
	public WebElement closeIcn;

	@FindBy(xpath = "//body/div[@id='contact_sale_modal']/div[@class='modal-dialog']/div[1]")
	public WebElement contactSaleModal;

	public void openContactSupportForm() throws InterruptedException {
		contactSalesLink.click();
	}

	public void closeCookieBannerIfPresent() {
		if (cookieBannerCloseButton.isDisplayed()) {
			cookieBannerCloseButton.click();
		}
	}

	public void enterFirstName(String firstName) {
		firstNameField.sendKeys(firstName);
	}

	public void enterEmail(String email) {
		emailField.sendKeys(email);
	}

	public void selectCountryIndia() throws InterruptedException {
		countryDropdown.click();
		Thread.sleep(5000);
		selectIndia.click();
	}

	public void enterPhone(String phone) {
		phoneField.sendKeys(phone);
	}

	public void enterSubject(String subject) {
		subjectField.sendKeys(subject);
	}

	public void enterMessage(String message) {
		messageField.sendKeys(message);
	}

	public void clickSubmit() {
		submitButton.click();
	}

	public String getResultMessage() {
		return resultMessage.getText();
	}

	public void clickCloseIcon() {
		closeIcn.click();
	}

	public void isContactSalePopupClosed() {
		GlobalMethods g = new GlobalMethods(driver);
		if (g.isPopupClosed(contactSaleModal)) {
			System.out.println("Contact support modal is closed.");
			Assert.assertTrue(true);
		} else {
			Assert.fail("Contact support modal is closed.");
		}
	}

}
