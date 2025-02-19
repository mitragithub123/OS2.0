package globalMethods;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class GlobalMethods {
	private WebDriver driver;
	public WebDriverWait wait;

	public GlobalMethods(WebDriver driver) {
		this.driver = driver;
	}

	// Global method to verify successful signup
	public void verifySuccessfulSignup() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		boolean isUrlCorrect = wait.until(ExpectedConditions.or(ExpectedConditions.urlContains("projects/manage"),
				ExpectedConditions.urlContains("users/login")));

		if (isUrlCorrect) {
			System.out.println("Signup is successful.");
			Assert.assertTrue(true);
		} else {
			String currentUrl = driver.getCurrentUrl();
			System.out.println("Current URL: " + currentUrl);
			Assert.fail("Signup failed. Current URL: " + currentUrl);
		}
	}

	public boolean isDashboardorLoginExists() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		boolean isUrlCorrect = wait.until(ExpectedConditions.or(ExpectedConditions.urlContains("projects/manage"),
				ExpectedConditions.urlContains("users/login")));

		if (isUrlCorrect) {
			return true;
		} else {
			String currentUrl = driver.getCurrentUrl();
			System.out.println("Current URL: " + currentUrl);
			return false;
		}
	}

	// Method to verify a popup is closed or not (call this method in every page
	// object class)
	public boolean isPopupClosed(WebElement popupElement) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		if (wait.until(ExpectedConditions.invisibilityOf(popupElement))) {
			return true;
		}
		return false;
	}

	public void verifyShowHidePassword(WebElement passwordField, WebElement hideIcon, WebElement showIcon)
			throws InterruptedException {
		// Check if the password is hidden by default
		String initialType = passwordField.getAttribute("type");
		Assert.assertEquals(initialType, "password", "Password is not hidden by default.");

		hideIcon.click();
		Thread.sleep(3000);

		// Verify the password field type has changed to 'text'
		String visibleType = passwordField.getAttribute("type");
		Assert.assertEquals(visibleType, "text", "Password is not visible after clicking the show icon.");

		showIcon.click();
		Thread.sleep(3000);

		// Verify the password field type has changed back to 'password'
		String hiddenTypeAgain = passwordField.getAttribute("type");
		Assert.assertEquals(hiddenTypeAgain, "password", "Password is not hidden after clicking the hide icon.");
	}

	public void missingAtTheRateSymbol(WebElement emailEle) {
		emailEle.sendKeys("userdomain.com");
	}

	public void emailWithoutTopLevelDomain(WebElement emailEle) {
		emailEle.sendKeys("user@domain");
	}

	public void emailWithInvalidCharacters(WebElement emailEle) {
		emailEle.sendKeys("user!@domain.com");
	}

	public void emailWithMultipleAtSymbols(WebElement emailEle) {
		emailEle.sendKeys("user!@domain.com");
	}

	public void printErrorMessage(WebElement errorMsg) {
		String errorMessage = errorMsg.getText();
		System.out.println(errorMessage);
	}

	public void logout(WebElement userIcon, WebElement logoutIcon) {
		userIcon.click();
		logoutIcon.click();
	}

	public String isEmailAlreadyExistsErrorDisplayed(WebElement errorMessage) {
		if (errorMessage.isDisplayed()) {
			return "Email already exists";
		}
		return "Email not exists";
	}

	public void switchToWindow() throws InterruptedException {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> iterator = windows.iterator();
		while (iterator.hasNext()) {
			String window = iterator.next();
			driver.switchTo().window(window);
			Thread.sleep(5000);
		}
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

}
