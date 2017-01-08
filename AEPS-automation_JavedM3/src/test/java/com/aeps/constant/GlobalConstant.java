package com.aeps.constant;


public class GlobalConstant {
	
	//Example related to file
	public enum FileNames {
		TestDataRelativePath("src/test/resources/testdata/"),
		ProdcutFileName("Product.xlsx"),
		UserProperties("User.properies");
		
		private String value;
		private FileNames(String value) {
			this.value = value;
		}
		
		public String toString() {
			return value;
		}
	}
	
	//Example related to file
		public enum StringConstant {
			Welcome("Welcome"),
			LogoutMessage("You have successfully logged out. Kindly close this window to erase the information that is stored on your browser."),
			UserValidationMsg("Please enter valid Email ID/Mobile number"),
			PasswordValidation("Please enter Password"),
			UserValiation2("You are not registered with us. Please sign up"),
			PersonnelInfo("Personal Information");
			
			private String value;
			private StringConstant(String value) {
				this.value = value;
			}
			
			public String toString() {
				return value;
			}
		}

}
