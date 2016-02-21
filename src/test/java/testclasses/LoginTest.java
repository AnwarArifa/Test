package testclasses;

import org.testng.annotations.Test;

import pageobjects.LoginPageObject;
import dataprovider.LoginDataProvider;

public class LoginTest {

	@Test(dataProvider = "invalidLogin", dataProviderClass = LoginDataProvider.class)
	public void testInvalidLogin(String username, String pwd, int browserType) {
		LoginPageObject lpob = new LoginPageObject();
		lpob.setBrowser(browserType);
		// click on sign in button
		lpob.clickSigninButton();
		// enter username
		lpob.enterUserName(username);
		// enter pwd
		lpob.enterPassword(pwd);
		// click login
		lpob.login();
		// close the browser
		lpob.closeBrowser();
	}
}
