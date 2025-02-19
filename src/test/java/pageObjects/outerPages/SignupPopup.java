package pageObjects.outerPages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import baseTest.BaseTest;
import globalMethods.GlobalMethods;
import pageObjects.BasePage;

public class SignupPopup extends BasePage {

	public GlobalMethods gm;
	public String randEmail;

	public SignupPopup(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "section[class='customer_review_analysts'] a[title='Get Started']")
	WebElement getStartedBtn;

	@FindBy(css = "#email_popup")
	WebElement email;

	@FindBy(css = "#password_popup")
	WebElement password;

	@FindBy(css = "#submit_button_popup")
	WebElement createAccBtn;

	@FindBy(css = ".modal-content.create_signup_account .close")
	WebElement closeIcn;

	@FindBy(css = ".modal-content.create_signup_account")
	WebElement signupPopup;

	@FindBy(css = ".button_strength.show")
	WebElement showIcn;

	@FindBy(css = "div[id='myPasswordpopup'] a[class='button_strength']")
	WebElement hideIcn;

	@FindBy(css = "#email_exist_popup")
	WebElement emailErrorMsg;

	@FindBy(css = ".hopscotch-bubble-close.hopscotch-close")
	WebElement projectsPageCloseIcn;

	@FindBy(css = ".nav-profile-dropdown.dropdown.hover-menu.cmn_parent_navli.pfl_dtl_li")
	WebElement userIcn;

	@FindBy(css = ".sign_out.grp_ttle")
	WebElement logoutIcn;

	@FindBy(css = "#email_exist_popup")
	WebElement existEmailMsg;

	@FindBy(xpath = "//button[@class='hopscotch-bubble-close hopscotch-close']")
	WebElement takeTourCloseIcon;

	public void setEmail(String emailData) {
		email.sendKeys(emailData);
	}

	public void setPassword(String passwordData) {
		password.sendKeys(passwordData);
	}

	public void clickCreateAccBtn() {
		createAccBtn.click();
	}

	public void clickGetStartedNow() throws InterruptedException {
		getStartedBtn.click();
		Thread.sleep(3000);
	}

	public void signupPopupPositiveSumbit() {
		randEmail = BaseTest.randomEmail();
		email.sendKeys(randEmail);
		System.out.println(randEmail);
		password.sendKeys("1234567890");
		createAccBtn.click();
	}

	public void waitForCloseIconAndClickAndLogOut() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(projectsPageCloseIcn)).click();
		gm = new GlobalMethods(driver);
		gm.logout(userIcn, logoutIcn);
	}

	public String signupWithExistingUser() throws InterruptedException {
		String randEmail = BaseTest.randomEmail();
		email.sendKeys(randEmail);
		password.sendKeys("1234567890");
		createAccBtn.click();
		Thread.sleep(3000);
		waitForCloseIconAndClickAndLogOut();
		return randEmail;
	}

	public void signupWithExistingUser(String existingEmail) throws InterruptedException {
		email.sendKeys(existingEmail);
		password.sendKeys("1234567890");
		createAccBtn.click();
		Thread.sleep(3000);
		gm = new GlobalMethods(driver);
		gm.printErrorMessage(existEmailMsg);
		gm.isEmailAlreadyExistsErrorDisplayed(existEmailMsg);
	}

	public void clickOnCloseIcon() throws InterruptedException {
		closeIcn.click();
		Thread.sleep(3000);
	}

	public void isPopupClosed() {
		gm = new GlobalMethods(driver);
		boolean isClosed = gm.isPopupClosed(closeIcn);
		if (isClosed) {
			System.out.println("Signup popup is closed.");
			Assert.assertTrue(true);
		} else {
			Assert.fail("Signup popup is not closed.");
		}
	}

	public void showAndHide() throws InterruptedException {
		password.sendKeys("1234567890");
		gm = new GlobalMethods(driver);
		gm.verifyShowHidePassword(password, hideIcn, showIcn);
	}

	public void testBlankFields() throws InterruptedException {
		createAccBtn.click();
		Thread.sleep(3000);
		gm = new GlobalMethods(driver);
		gm.printErrorMessage(emailErrorMsg);

	}

	public void validMailBlankPassword() throws InterruptedException {
		String randEmail = BaseTest.randomEmail();
		email.sendKeys(randEmail);
		System.out.println(randEmail);
		createAccBtn.click();
		Thread.sleep(3000);
		gm = new GlobalMethods(driver);
		gm.printErrorMessage(emailErrorMsg);
	}

	public void invalidMailValidPassword() throws InterruptedException {
		gm = new GlobalMethods(driver);
		gm.missingAtTheRateSymbol(email);
		createAccBtn.click();
		Thread.sleep(3000);
		gm.printErrorMessage(emailErrorMsg);
	}

	public void logout() throws InterruptedException {
		userIcn.click();
		Thread.sleep(3000);
		logoutIcn.click();
	}

	public void waitForTakeTourCloseIconToBeVisible() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(takeTourCloseIcon)).click();
	}

}
