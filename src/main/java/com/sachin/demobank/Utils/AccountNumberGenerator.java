package com.sachin.demobank.Utils;

import java.util.UUID;

import com.sachin.demobank.Constants.StringConstants;

public class AccountNumberGenerator {
    public static String generate(String AccountType)
    {
        StringBuffer AccountNumber=new StringBuffer(StringConstants.ACCOUNT_PREFIX);
        if(AccountType.equals("SAVINGS"))
        {
            AccountNumber.append(StringConstants.SAVINGS_ACCOUNT_PREFIX);
        }
        else
        {
            AccountNumber.append(StringConstants.CURRENT_ACCOUNT_PREFIX);
        }

        String random=UUID.randomUUID().toString().replaceAll("\\D", "").substring(0,10);
        AccountNumber.append(random);
        return AccountNumber.toString();

    }

}
