package com.driver;

public class CurrentAccount extends BankAccount {
    String tradeLicenseId; // consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        super(name, balance, 5000); // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        this.tradeLicenseId = tradeLicenseId;
        validateLicenseId();
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        char[] chars = tradeLicenseId.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                rearrangeLicenseId(chars, 0);
                return;
            }
        }
    }

    private void rearrangeLicenseId(char[] chars, int start) throws Exception {
        if (start == chars.length) {
            throw new Exception("Valid License can not be generated");
        }

        for (int i = start; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] != chars[j]) {
                    char temp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = temp;

                    if (i > 0 && chars[i] == chars[i - 1]) {
                        rearrangeLicenseId(chars, i + 1);
                    } else {
                        validateLicenseId();
                    }

                    return;
                }
            }
        }
    }
}
