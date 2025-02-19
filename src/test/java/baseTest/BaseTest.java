package baseTest;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

import java.awt.Desktop;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.chaintest.service.ChainPluginService;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * author: Mitra bhanu
 */
public class BaseTest {
	public static WebDriver driver;
	public Logger logger;
	public Properties property;
	public static ScreenRecorder screenRecorder;
	public static File videoFile;

	@BeforeTest
	public void chainTestSystemProperty() {
		// Set system property for chain test report
		ChainPluginService.getInstance().addSystemInfo("Tester name", "Mitra bhanu");
		ChainPluginService.getInstance().addSystemInfo("Location name", "BBSR");
	}

	@BeforeClass()
	public void setup() throws Exception {
		// Load properties
		FileInputStream file = new FileInputStream(".\\src\\test\\resources\\config.properties");
		property = new Properties();
		property.load(file);
		file.close();

		// Initialize logger
		logger = LogManager.getLogger(this.getClass());

		String browserName = property.getProperty("browser1", "chrome"); // Default to chrome if not specified
		String exeEnv = property.getProperty("exeEnv", "local"); // Default to local if not specified

		// Start screen recording
		startScreenRecording();

		if ("remote".equalsIgnoreCase(exeEnv)) {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// Set platform
			String os = property.getProperty("os1");
			if (os == null)
				os = property.getProperty("os2");
			if (os == null)
				os = property.getProperty("os3");

			if ("windows".equalsIgnoreCase(os)) {
				capabilities.setPlatform(Platform.WIN10);
			} else if ("linux".equalsIgnoreCase(os)) {
				capabilities.setPlatform(Platform.LINUX);
			} else if ("mac".equalsIgnoreCase(os)) {
				capabilities.setPlatform(Platform.MAC);
			} else {
				throw new IllegalArgumentException("Unsupported OS configured");
			}

			// Set browser
			if ("chrome".equalsIgnoreCase(browserName)) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized", "--disable-notifications", "--incognito");
				options.setAcceptInsecureCerts(true);
				options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
				options.setExperimentalOption("useAutomationExtension", false);
				capabilities.merge(options);

				driver = new RemoteWebDriver(new URL("http://192.168.2.173:4444/wd/hub"), capabilities);
			} else if ("firefox".equalsIgnoreCase(browserName)) {
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--start-maximized", "--disable-notifications", "--private");
				options.setAcceptInsecureCerts(true);
				capabilities.merge(options);

				driver = new RemoteWebDriver(new URL("http://192.168.2.173:4444/wd/hub"), capabilities);
			} else {
				logger.error("Browser not supported: " + browserName);
				throw new IllegalArgumentException("Unsupported browser configured");
			}
		} else if ("local".equalsIgnoreCase(exeEnv)) {
			if ("chrome".equalsIgnoreCase(browserName)) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized", "--disable-notifications", "--incognito");
				options.setAcceptInsecureCerts(true);
				options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
				options.setExperimentalOption("useAutomationExtension", false);
				driver = new ChromeDriver(options);
			} else if ("firefox".equalsIgnoreCase(browserName)) {
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--start-maximized", "--disable-notifications", "--private");
				options.setAcceptInsecureCerts(true);
				driver = new FirefoxDriver(options);
			} else {
				logger.error("Browser not supported: " + browserName);
				throw new IllegalArgumentException("Unsupported browser configured");
			}
		} else {
			logger.error("Execution environment not supported: " + exeEnv);
			throw new IllegalArgumentException("Unsupported execution environment configured");
		}

		driver.get(property.getProperty("prodUrl"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass()
	public void teardown() throws IOException {
		if (driver != null) {
			driver.quit();
		}
		// Stop screen recording
		stopScreenRecording();
	}

	public static String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public static String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	public static String randomAlphaNumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return (generatedString + "@" + generatedNumber);
	}

	public static String randomEmail() {
		String generatedString = RandomStringUtils.randomAlphabetic(5).toLowerCase();
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return (generatedString + generatedNumber + "@" + "yahoo.in");
	}

	public static String captureScreenshot(WebDriver driver) throws IOException {
		File screenshotsDir = new File(System.getProperty("user.dir") + File.separator + "screenshots");
		screenshotsDir.mkdirs(); // Create directory if it doesn't exist
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotName = "screenshot" + System.currentTimeMillis() + ".png";
		File destinationFile = new File(screenshotsDir, screenshotName);

		FileUtils.copyFile(screenshotFile, destinationFile);

		return destinationFile.getAbsolutePath();
	}

	// Method to start screen recording
	public void startScreenRecording() throws Exception {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		File screenRecordingsDir = new File(System.getProperty("user.dir") + File.separator + "screenRecordings");
		videoFile = new File(screenRecordingsDir, "TestRecording_" + timeStamp + ".avi");

		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		screenRecorder = new ScreenRecorder(gc, gc.getBounds(),
				new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
						CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
						Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
				null, videoFile);
		screenRecorder.start();
	}

	// Method to stop screen recording
	public void stopScreenRecording() {
		try {
			if (screenRecorder != null) {
				screenRecorder.stop();
			}
		} catch (Exception e) {
			logger.error("Failed to stop screen recording: " + e.getMessage());
		}
	}

	public static File getVideoFile() {
		return videoFile;
	}

	@AfterMethod
	public void attachScreenshot(ITestResult result) {
		if (!result.isSuccess()) {
			ChainTestListener.embed(takeScreenshot(), "image/png");
		}
		// To open report automatically in browser
		String reportPath = System.getProperty("user.dir") + "\\target\\chaintest\\Index.html";
		File chainTestReport = new File(reportPath);
		try {
			Desktop.getDesktop().browse(chainTestReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public byte[] takeScreenshot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

}
