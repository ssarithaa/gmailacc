package com.autogmail.createacct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.autogmail.base.Base;
import com.autogmail.exceptions.InvalidConfigException;

public class CheckingErrors extends Base {
	/**
	 * checking if emailID already exists
	 *
	 * @param xpathUsernameErrorField
	 *            = //div[@id='username-errormsg-and-suggestions' and @role='alert']
	 * @return
	 * @throws InvalidConfigException
	 */
	public boolean isEmailAlreadyPresent(String xpathUsernameErrorField) throws InvalidConfigException {
		// Find username error message field using its xpath
		WebElement we = super.findEle(xpathUsernameErrorField);

		// Get role attribute from the error message field, if any
		String role = (we != null) ? we.getAttribute("role") : null;

		// Based on role's value to alert; we are expecting error message is thrown
		return !((role != null) && (role.equals("alert")));
	}

	/**
	 *
	 * @param errTxt
	 * @param fieldXPath
	 * @return
	 * @throws InvalidConfigException
	 */
	public boolean isError(String errTxt, String fieldXPath) throws InvalidConfigException {
		String errMessage = super.getTxt(fieldXPath);

		if ((errMessage != null) && errMessage.contains(errTxt)) {
			return true;
		}

		return false;
	}

	/**
	 * Check for password errors
	 *
	 * @param pwderrtxt
	 * @param cnfpwderrtxt
	 * @return
	 * @throws InvalidConfigException
	 */
	public boolean checkPwdErr(String pwderrtxt, String cnfpwderrtxt) throws InvalidConfigException {
		WebElement we = super.getDriver().findElement(By.xpath(pwderrtxt));
		WebElement we2 = super.getDriver().findElement(By.xpath(cnfpwderrtxt));

		String msg = we.getText(); // error when password doesn't meet criteria
		String msg2 = we2.getText(); // error when confirmation password mismatches
		if (((msg != null) && (msg.contains("Try"))) || msg2.contains("Try")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Check for password errors
	 *
	 * @param xpathPwdErrtxt
	 * @param xpathCnfPwdErrTxt
	 * @return
	 * @throws InvalidConfigException
	 */
	public boolean isPasswordValid(String xpathPwdErrtxt, String xpathCnfPwdErrTxt) {
		String msg = super.getTxt(xpathPwdErrtxt); // error when password doesn't meet criteria
		String msg2 = super.getTxt(xpathCnfPwdErrTxt); // error when confirmation password mismatches

		if (((msg != null) && (msg.contains("Try"))) || msg2.contains("Try")) {
			return false;
		}
		return true;
	}
}
