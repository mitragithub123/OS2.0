package pageObjects.outerPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import pageObjects.BasePage;

public class LoginPopup extends BasePage {

	public LoginPopup(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "a[title='Sign in']")
	WebElement loginLink;

	@FindBy(css = "#txt_UserId")
	WebElement email;

	@FindBy(css = "#txt_Password")
	WebElement password;

	@FindBy(css = "#submit_Pass")
	WebElement loginBtn;

	@FindBy(xpath = "//h5[text()='Mitra Bhanu']")
	WebElement lauchPadLink;

	public void openLoginPopup() {
		loginLink.click();
	}

	public void enterEmail(String userEmail) throws InterruptedException {
		email.sendKeys(userEmail);
		Thread.sleep(3000);
	}

	public void enterPassword(String userPassword) throws InterruptedException {
		password.sendKeys(userPassword);
		Thread.sleep(3000);
	}

	public void clickLoginButton() throws InterruptedException {
		loginBtn.click();
		Thread.sleep(3000);
	}

	public void login(String userEmail, String userPassword) throws InterruptedException {
		openLoginPopup();
		enterEmail(userEmail);
		enterPassword(userPassword);
		clickLoginButton();
	}

	public void launchPadCheck() {
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.contains("users/launchpad")) {
			lauchPadLink.click();
			currentUrl = driver.getCurrentUrl(); // Update currentUrl
		}
		if (currentUrl.contains("/mydashboard")) {
			System.out.println("Login successful.");
			Assert.assertTrue(true);
		} else {
			Assert.fail("Failed to navigate to projects/manage.");
		}
	}

}
