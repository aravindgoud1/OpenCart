package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class AccountRegistrationTest extends BaseClass {

	@Test(groups = { "Regression", "Master" })
	public void verify_account_registration() {
		logger.info("**** starting AccountRegistrationTest *****");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on clickMyAccount link");

			hp.clickRegister();
			logger.info("clicked on clickRegister link ");

			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			logger.info("providing Customer details");
			regpage.setFirstName(randomeString().toUpperCase());
			regpage.setLastName(randomeString().toUpperCase());
			regpage.setEmail(randomeString() + "@gmail.com");// randomly generated the email
			regpage.setTelephone(randomeNumber());

			String password = randomeAlphaNumberic();

			regpage.setPassword(password);
			regpage.setConfirmPassword(password);

			regpage.setPrivacyPolicy();
			regpage.clickContinue();
			logger.info("validating expected message");

			String confmsg = regpage.getConfirmationMsg();
			Assert.assertEquals(confmsg, "Your Account Has Been Created!", "Confirmation message mismatch");

		} catch (Exception e) {
			logger.info("Test Failed...");
			logger.debug("Debug logs....");

			Assert.fail("Test failed: " + e.getMessage());

		}
	}

}
