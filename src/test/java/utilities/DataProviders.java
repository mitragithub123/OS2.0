package utilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.DataProvider;
import baseTest.BaseTest;

public class DataProviders extends BaseTest {

	// Signup popup data provider using excel file
	@DataProvider(name = "SignupData")
	public String[][] getData() throws IOException {
		String path = ".\\testData\\signupData.xlsx";
		ExcelUtility xlUtil = new ExcelUtility(path);
		int totalRows = xlUtil.getRowCount("Sheet1");
		int totalCols = xlUtil.getCellCount("Sheet1", 1);
		String signupData[][] = new String[totalRows][totalCols];
		for (int i = 1; i <= totalRows; i++) {
			for (int j = 0; j < totalCols; j++) {
				signupData[i - 1][j] = xlUtil.getCellData("Sheet1", i, j);
			}
		}
		return signupData;
	}

	// Signup popup data provider using Hashed map
	@DataProvider(name = "SignupDataHashMap")
	public Object[][] getSignupData() {
		// Creating the sign up data maps directly within this method
		Map<String, String> signupDataMap1 = new HashMap<>();
		signupDataMap1.put("email", "yyrt@gmail.com");
		signupDataMap1.put("password", "1234567890");
		signupDataMap1.put("expRes", "Valid");

		Map<String, String> signupDataMap2 = new HashMap<>();
		signupDataMap2.put("email", "rthruuyhjryj@gmail.com");
		signupDataMap2.put("password", "1234567890");
		signupDataMap2.put("expRes", "Valid");

		Map<String, String> signupDataMap3 = new HashMap<>();
		signupDataMap3.put("email", "ryjuuyrj@gmail.com");
		signupDataMap3.put("password", "1234567890");
		signupDataMap3.put("expRes", "Valid");

		Map<String, String> signupDataMap4 = new HashMap<>();
		signupDataMap4.put("email", "yjyuujy@gmail.com");
		signupDataMap4.put("password", "1234567890");
		signupDataMap4.put("expRes", "Valid");

		Map<String, String> signupDataMap5 = new HashMap<>();
		signupDataMap5.put("email", "");
		signupDataMap5.put("password", "");
		signupDataMap5.put("expRes", "Invalid");

		return new Object[][] { { signupDataMap1 }, { signupDataMap2 }, { signupDataMap3 }, { signupDataMap4 },
				{ signupDataMap5 } };
	}

}
