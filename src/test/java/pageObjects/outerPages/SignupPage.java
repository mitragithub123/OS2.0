package pageObjects.outerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import baseTest.BaseTest;
import globalMethods.GlobalMethods;
import pageObjects.BasePage;

public class SignupPage extends BasePage {
	public GlobalMethods gm;

	public SignupPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "a[title='Try Free']")
	WebElement tryFreeBtn;

	@FindBy(xpath = "//input[@id='email']")
	WebElement emailSignUp;

	@FindBy(css = "#submit_email_button")
	WebElement emailSubmitSignUp;

	@FindBy(xpath = "//input[@id='password']")
	WebElement passwordSignUp;

	@FindBy(css = "#submit_button")
	WebElement submitSignUp;

	@FindBy(css = "div[id='email_exist'] div")
	WebElement emailErrorMsg;

	@FindBy(css = "div[id='email_exist'] div")
	WebElement existEmailMsg;

	public void clickOnTryFreeLinkInHeader() throws InterruptedException {
		tryFreeBtn.click();
		Thread.sleep(3000);
	}

	public void signUpPositive() throws InterruptedException {
		String randEmail = BaseTest.randomEmail();
		emailSignUp.sendKeys(randEmail);
		System.out.println(randEmail);
		emailSubmitSignUp.click();
		passwordSignUp.sendKeys("1234567890");
		submitSignUp.click();
		Thread.sleep(10000);
	}

	public String signUpWithExistingEmail() throws InterruptedException {
		String randEmail = BaseTest.randomEmail();
		emailSignUp.sendKeys(randEmail);
		emailSubmitSignUp.click();
		passwordSignUp.sendKeys("1234567890");
		submitSignUp.click();
		Thread.sleep(10000);
		SignupPopup sp=new SignupPopup(driver);
		sp.waitForCloseIconAndClickAndLogOut();
		return randEmail;
	}

	public void signUpWithExistingEmail(String existingEmail) throws InterruptedException {
		tryFreeBtn.click();
		emailSignUp.sendKeys(existingEmail);
		emailSubmitSignUp.click();
		passwordSignUp.sendKeys("1234567890");
		submitSignUp.click();
		Thread.sleep(3000);
		gm = new GlobalMethods(driver);
		gm.printErrorMessage(existEmailMsg);
		gm.isEmailAlreadyExistsErrorDisplayed(existEmailMsg);
	}

	public void blankEmailSubmit() {
		emailSubmitSignUp.click();
		printErrorMessage();
	}

	public void missingAtTheRateSymbol() {
		String invalidEmail = "userdomain.com";
		emailSignUp.sendKeys(invalidEmail);
		System.out.println("Testing email: " + invalidEmail);
		emailSubmitSignUp.click();
		printErrorMessage();
	}

	public void emailWithoutTopLevelDomain() {
		String invalidEmail = "user@domain";
		emailSignUp.sendKeys(invalidEmail);
		System.out.println("Testing email: " + invalidEmail);
		emailSubmitSignUp.click();
		printErrorMessage();
	}

	public void emailWithoutDotInDomain() {
		String invalidEmail = "user@domaincom";
		emailSignUp.sendKeys(invalidEmail);
		System.out.println("Testing email: " + invalidEmail);
		emailSubmitSignUp.click();
		printErrorMessage();
	}

	public void emailWithInvalidCharacters() {
		String invalidEmail = "user!@domain.com";
		emailSignUp.sendKeys(invalidEmail);
		System.out.println("Testing email: " + invalidEmail);
		emailSubmitSignUp.click();
		printErrorMessage();
	}

	public void emailWithMultipleAtSymbols() {
		String invalidEmail = "user@@domain.com";
		emailSignUp.sendKeys(invalidEmail);
		System.out.println("Testing email: " + invalidEmail);
		emailSubmitSignUp.click();
		printErrorMessage();
	}

	public void printErrorMessage() {
		gm = new GlobalMethods(driver);
		gm.printErrorMessage(emailErrorMsg);
	}
	
	// Google sign up
	@FindBy(css = "#customBtn")
	public WebElement googleSignupButton;

	@FindBy(id = "identifierId")
	public WebElement emailField;

	@FindBy(xpath = "//span[normalize-space()='Next']")
	public WebElement nextButtonOnEmail;

	@FindBy(name = "Passwd")
	public WebElement passwordField;

	@FindBy(xpath = "//span[contains(.,'Next')]")
	public WebElement nextButtonOnPassword;
	
	public void clickGoogleSignup() throws InterruptedException {
		googleSignupButton.click();
		Thread.sleep(3000);
	}

	public void enterEmail(String email) {
		emailField.sendKeys(email);
	}
	
	public void enterPassword(String pass) {
		passwordField.sendKeys(pass);
	}

	public void clickNextOnEmail() throws InterruptedException {
		nextButtonOnEmail.click();
		Thread.sleep(3000);
	}
	
	public void clickNextOnPassword() throws InterruptedException {
		nextButtonOnPassword.click();
		Thread.sleep(15000);
	}
}
