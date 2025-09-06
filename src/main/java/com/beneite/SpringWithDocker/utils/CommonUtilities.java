package com.beneite.SpringWithDocker.utils;

import com.beneite.SpringWithDocker.constants.GlobalConstants;

public final class CommonUtilities {

    private CommonUtilities(){}

    /**
     * this method will create company email address
     * @param firstName firstName
     * @param lastName lastName
     * @return companyEmailId
     */
    public static String createCompanyEmailFromFirstAndLastName(String firstName, String lastName){
        return firstName.toLowerCase().trim()
                + "."
                + lastName.toLowerCase().trim()
                + GlobalConstants.EMAIL_DOMAIN;
    }


}
