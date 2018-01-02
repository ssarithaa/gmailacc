package com.autogmail.createacct;

import org.openqa.selenium.By;

import com.autogmail.testelements.TestElements;

public class CreateAcc extends CheckingErrors {
	public static void main(String args[]) throws Exception {
		new CreateAcc().testflow();
	}

	public void testflow() throws Exception {
		// CheckingErrors objerr = new CheckingErrors();
		TestElements tdataobj = new TestElements();
		super.setbrowser(tdataobj.brwser);
		this.getDriver().get(tdataobj.baseurl);
		super.entertxt(tdataobj.fnamefield, tdataobj.fnamevalue);
		super.entertxt(tdataobj.lnamefield, tdataobj.lnamevalue);
		super.entertxt(tdataobj.emailidfield, tdataobj.emailidvalue);
		super.entertxt(tdataobj.pwdfield, tdataobj.pwdvalue);
		super.entertxt(tdataobj.cnfpwdfield, tdataobj.cnfpwdvalue);
		super.click(tdataobj.Monthfield);
		super.click(tdataobj.Monthvalue);
		super.entertxt(tdataobj.bdayfield, tdataobj.bdayvalue);
		super.entertxt(tdataobj.byearfield, tdataobj.byearvalue);
		super.click(tdataobj.Genderfield);
		super.click(tdataobj.Gendervalue);
		super.entertxt(tdataobj.Phonenofield, tdataobj.Phonenovalue);
		super.entertxt(tdataobj.RecoEmailfield, tdataobj.RecoEmailvalue);
		// checking if emailID already exists
		boolean pass = super.isEmailAlreadyPresent(tdataobj.emailErrTxt);
		if (!pass) {

			Thread.sleep(2000);
			String newname = this.getDriver().findElement(By.xpath(tdataobj.altemail)).getText();
			this.getDriver().findElement(By.xpath(tdataobj.emailidfield)).clear();
			super.entertxt(tdataobj.emailidfield, newname); // enter a new emailID from the available list provided
			Thread.sleep(2000);

		}
		// alternate flow when password errors occur
		boolean pwdok = super.isPasswordValid(tdataobj.pwderrtxt, tdataobj.cnfpwderrtxt);

		if (!pwdok) {
			this.getDriver().findElement(By.xpath(tdataobj.pwdfield)).clear();
			this.getDriver().findElement(By.xpath(tdataobj.cnfpwdfield)).clear();
			super.entertxt(tdataobj.pwdfield, tdataobj.pwdvalue2);
			super.entertxt(tdataobj.cnfpwdfield, tdataobj.cnfpwdvalue2);
		}

		super.click(tdataobj.NxtBtnId);
		super.click(tdataobj.arrowbtn);
		super.click(tdataobj.arrowbtn);
		super.click(tdataobj.AgrBtnId);
		Thread.sleep(1000);
		super.click(tdataobj.CntBtnId);
		Thread.sleep(10000);// to manually enter verification code sent to mobile
		super.click(tdataobj.VerifyPhBtnId);

	}

}
