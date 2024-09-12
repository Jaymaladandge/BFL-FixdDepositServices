package com.bajaj.fixeddeposit.validation;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.bajaj.fixeddeposit.util.Constants;
@Component
public class InterestRateValidation
{
    private static final Logger logger = Logger.getLogger(InterestRateValidation.class);
    public String interestRateValidator(String customerType, Integer tenor, String interestPayout, String interestPayoutType, String interestRate, String investmentType)
    { 
        logger.info(" === tenor === " + tenor + " === interestPayout === " + interestPayout + " === periodicWithdrawalType === " + interestPayoutType + " === interestRate == " + interestRate + " === investmentType === " + investmentType);
        String backendInterestRate = "";
        String interetsRateStatus = "";
        if("FD".equalsIgnoreCase(investmentType))
        {
            if("Existing Customer".equalsIgnoreCase(customerType))
            {
                backendInterestRate = existingCustomer(tenor, interestPayout, interestPayoutType);
            }
            else if("Senior Citizen".equalsIgnoreCase(customerType))
            {
                backendInterestRate = seniorCitizen(tenor, interestPayout, interestPayoutType);
            }
            else if("New Customer".equalsIgnoreCase(customerType))
            {
                backendInterestRate = newCustomer(tenor, interestPayout, interestPayoutType);
            }
        }
        else if("SDP".equalsIgnoreCase(investmentType))
        {
            if("Existing Customer".equalsIgnoreCase(customerType))
            {
                backendInterestRate = existingCustomerSdp(tenor);
            }
            else if("Senior Citizen".equalsIgnoreCase(customerType))
            {
                backendInterestRate = seniorCitizenSdp(tenor);
            }
            else if("New Customer".equalsIgnoreCase(customerType))
            {
                backendInterestRate = newCustomerSdp(tenor);
            }
        }
        if(backendInterestRate.equalsIgnoreCase(interestRate))
        {
            logger.info(" === Interest Rate Matched in interestRateValidator ==== ");
            interetsRateStatus = Constants.STATUS_SUCCESS;
        }
        else
        {
            interetsRateStatus = Constants.STATUS_FAIL;
            logger.info(" === Interest Rate Not Matched in interestRateValidator ==== ");
        }
        logger.info(" === interetsRateStatus in interestRateValidator === " + interetsRateStatus);
        return interetsRateStatus;
    }
    
    
    private String existingCustomer(Integer tenor, String interestPayout, String interestPayoutType)
    {
        String interestRate = "";
        if(tenor >= 12 && tenor <= 14)
        {
        	if(interestPayoutType.equals(Constants.CUMULATIVE) && !interestPayout.equals("NA"))
            {
        		interestRate = "7.40";
            }
        	 else if(interestPayoutType.equals(Constants.NON_CUMULATIVE))
             {
        		 if(interestPayout.equals(Constants.MONTHLY))
                 {
                     interestRate = "7.16";
                 }
                 else if(interestPayout.equals(Constants.QUARTERLY))
                 {
                     interestRate = "7.20";
                 }
                 else if(interestPayout.equals(Constants.HALF_YEARLY))
                 {
                     interestRate = "7.27";
                 }
                 else if(interestPayout.equals(Constants.YEARLY))
                 {
                     interestRate = "7.40";
                 }
             }
        }
        else if(tenor >= 15 && tenor <= 23)
        {
            if(interestPayoutType.equals(Constants.CUMULATIVE) && !interestPayout.equals("NA"))
            {
                if(tenor == 15)
                {
                    interestRate = "7.45";
                }
                else if(tenor == 18)
                {
                    interestRate = "7.40";
                }
                else if(tenor == 22)
                {
                    interestRate = "7.50";
                }
                else
                {
                    interestRate = "7.50";
                }
            }
            else if(interestPayoutType.equals(Constants.NON_CUMULATIVE))
            {
                if(tenor == 15)
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.21";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.25";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.32";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.45";
                    }
                }
                else if(tenor == 18)
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.16";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.20";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.27";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.40";
                    }
                }
                else if(tenor == 22)
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.25";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.30";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.36";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.50";
                    }
                }
                else
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.25";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.30";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.36";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.50";
                    }
                }
            }
        }
        else if(tenor >= 24 && tenor <= 35)
        {
            if(interestPayoutType.equals(Constants.CUMULATIVE))
            {
            	if(tenor == 24)
                {
                    interestRate = "7.55";
                }
            	else if(tenor == 30)
                {
                    interestRate = "7.45";
                }
                else if(tenor == 33)
                {
                    interestRate = "7.75";
                }
                else
                {
                    interestRate = "7.35";
                }
            }
            else if(interestPayoutType.equals(Constants.NON_CUMULATIVE) && !interestPayout.equals("NA"))
            {
            	if(tenor == 24)
            		{
                        if(interestPayout.equals(Constants.MONTHLY))
                        {
                            interestRate = "7.30";
                        }
                        else if(interestPayout.equals(Constants.QUARTERLY))
                        {
                            interestRate = "7.35";
                        }
                        else if(interestPayout.equals(Constants.HALF_YEARLY))
                        {
                            interestRate = "7.41";
                        }
                        else if(interestPayout.equals(Constants.YEARLY))
                        {
                            interestRate = "7.55";
                        }
            	}
            	else if(tenor == 30)
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.21";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.25";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.32";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.45";
                    }
                }
                else if(tenor == 33)
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.49";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.53";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.61";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.75";
                    }
                }
                else
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.11";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.16";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.22";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.35";
                    }
                }
            }
        }
        else if(tenor >= 36 && tenor <= 60)
        {
            if(interestPayoutType.equals(Constants.CUMULATIVE))
            {
            	if(tenor == 44)
                {
                    interestRate = "7.95";
                }
                else
                {
                    interestRate = "7.65";
                }
            }
            else if(interestPayoutType.equals(Constants.NON_CUMULATIVE) && !interestPayout.equals("NA"))
            {
                 if(tenor == 44)
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.67";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.72";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.80";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.95";
                    }
                }
                else
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.39";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.44";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.51";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.65";
                    }
                }
            }
        }
        logger.info(" ===  Calculated Interest Rate for Existing Customer FD== " + interestRate);
        return interestRate;
    }
    
    
    private String newCustomer(Integer tenor, String interestPayout, String periodicWithdrawalType)
    {
        logger.info(" == tenor === " + tenor + " === interestPayout ==== " + interestPayout + " ==== periodicWithdrawalType == " + periodicWithdrawalType);
        String interestRate = "";
        if(tenor >= 12 && tenor <= 14)
        {
        	if(periodicWithdrawalType.equals(Constants.CUMULATIVE))
            {
        		interestRate = "7.40";
            }
        	else if(periodicWithdrawalType.equals(Constants.NON_CUMULATIVE) && !interestPayout.equals("NA"))
             {
        		 if(interestPayout.equals(Constants.MONTHLY))
                 {
                     interestRate = "7.16";
                 }
                 else if(interestPayout.equals(Constants.QUARTERLY))
                 {
                     interestRate = "7.20";
                 }
                 else if(interestPayout.equals(Constants.HALF_YEARLY))
                 {
                     interestRate = "7.27";
                 }
                 else if(interestPayout.equals(Constants.YEARLY))
                 {
                     interestRate = "7.40";
                 }
             }
        }
        else if(tenor >= 15 && tenor <= 23)
        {
            if(periodicWithdrawalType.equals(Constants.CUMULATIVE))
            {
                if(tenor == 15)
                {
                    interestRate = "7.45";
                }
                else if(tenor == 18)
                {
                    interestRate = "7.40";
                }
                else if(tenor == 22)
                {
                    interestRate = "7.50";
                }
                else
                {
                    interestRate = "7.50";
                }
            }
            else if(periodicWithdrawalType.equals(Constants.NON_CUMULATIVE) && !interestPayout.equals("NA"))
            {
                if(tenor == 15)
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.21";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.25";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.32";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.45";
                    }
                }
                else if(tenor == 18)
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.16";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.20";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.27";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.40";
                    }
                }
                else if(tenor == 22)
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.25";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.30";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.36";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.50";
                    }
                }
                else
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.25";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.30";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.36";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.50";
                    }
                }
            }
        }
        else if(tenor >= 24 && tenor <= 35)
        {
            if(periodicWithdrawalType.equals(Constants.CUMULATIVE))
            {
            	if(tenor == 24)
                {
                    interestRate = "7.55";
                }
            	else if(tenor == 30)
                {
                    interestRate = "7.45";
                }
                else if(tenor == 33)
                {
                    interestRate = "7.75";
                }
                else
                {
                    interestRate = "7.35";
                }
            }
            else if(periodicWithdrawalType.equals(Constants.NON_CUMULATIVE) && !interestPayout.equals("NA"))
            {
            	if(tenor == 24)
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.30";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.35";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.41";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.55";
                    }
                }
            	else if(tenor == 30)
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.21";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.25";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.32";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.45";
                    }
                }
                else if(tenor == 33)
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.49";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.53";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.61";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.75";
                    }
                }
                else
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.11";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.16";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.22";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.35";
                    }
                }
            }
        }
        else if(tenor >= 36 && tenor <= 60)
        {
            if(periodicWithdrawalType.equals(Constants.CUMULATIVE))
            {
            	if(tenor == 44)
                {
                    interestRate = "7.95";
                }
                else
                {
                    interestRate = "7.65";
                }
            }
            else if(periodicWithdrawalType.equals(Constants.NON_CUMULATIVE) && !interestPayout.equals("NA"))
            {
            	if(tenor == 44)
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.67";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.72";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.80";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.95";
                    }
                }
                else
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.39";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.44";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.51";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.65";
                    }
                }
            }
        }
        logger.info(" ===  Calculated Interest Rate for New Customer FD == " + interestRate);
        return interestRate;
    }
    
    private String seniorCitizen(Integer tenor, String interestPayout, String periodicWithdrawalType)
    {
        logger.info(" == tenor === " + tenor + " === interestPayout ==== " + interestPayout + " ==== periodicWithdrawalType == " + periodicWithdrawalType);
        String interestRate = "";
        if(tenor >= 12 && tenor <= 14)
        {
        	if(periodicWithdrawalType.equals(Constants.CUMULATIVE))
            {
        		interestRate = "7.65";
            }
        	else if(periodicWithdrawalType.equals(Constants.NON_CUMULATIVE) && !interestPayout.equals("NA"))
             {
        		 if(interestPayout.equals(Constants.MONTHLY))
                 {
                     interestRate = "7.39";
                 }
                 else if(interestPayout.equals(Constants.QUARTERLY))
                 {
                     interestRate = "7.44";
                 }
                 else if(interestPayout.equals(Constants.HALF_YEARLY))
                 {
                     interestRate = "7.51";
                 }
                 else if(interestPayout.equals(Constants.YEARLY))
                 {
                     interestRate = "7.65";
                 }
             }
        }
        else if(tenor >= 15 && tenor <= 23)
        {
            if(periodicWithdrawalType.equals(Constants.CUMULATIVE))
            {
                if(tenor == 15)
                {
                    interestRate = "7.70";
                }
                else if(tenor == 18)
                {
                    interestRate = "7.65";
                }
                else if(tenor == 22)
                {
                    interestRate = "7.75";
                }
                else
                {
                    interestRate = "7.75";
                }
            }
            else if(periodicWithdrawalType.equals(Constants.NON_CUMULATIVE) && !interestPayout.equals("NA"))
            {
                if(tenor == 15)
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.44";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.49";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.56";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.70";
                    }
                }
                else if(tenor == 18)
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.39";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.44";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.51";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.65";
                    }
                }
                else if(tenor == 22)
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.49";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.53";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.61";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.75";
                    }
                }
                else
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.49";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.53";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.61";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.75";
                    }
                }
            }
        }
        else if(tenor >= 24 && tenor <= 35)
        {
            if(periodicWithdrawalType.equals(Constants.CUMULATIVE))
            {
            	if(tenor == 24)
                {
                    interestRate = "7.80";
                }
            	else if(tenor == 30)
                {
                    interestRate = "7.70";
                }
                else if(tenor == 33)
                {
                    interestRate = "8.00";
                }
                else
                {
                    interestRate = "7.60";
                }
            }
            else if(periodicWithdrawalType.equals(Constants.NON_CUMULATIVE) && !interestPayout.equals("NA"))
            {
            	if(tenor == 24)
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.53";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.58";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.65";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.80";
                    }
                }
            	else if(tenor == 30)
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.44";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.49";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.56";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.70";
                    }
                }
                else if(tenor == 33)
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.72";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.77";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.85";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "8.00";
                    }
                }
                else
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.35";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.39";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.46";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.60";
                    }
                }
            }
        }
        else if(tenor >= 36 && tenor <= 60)
        {
            if(periodicWithdrawalType.equals(Constants.CUMULATIVE))
            {
            	if(tenor == 44)
                {
                    interestRate = "8.20";
                }
                else
                {
                    interestRate = "7.90";
                }
            }
            else if(periodicWithdrawalType.equals(Constants.NON_CUMULATIVE) && !interestPayout.equals("NA"))
            {
            	if(tenor == 44)
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.91";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.96";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "8.04";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "8.20";
                    }
                }
                else
                {
                    if(interestPayout.equals(Constants.MONTHLY))
                    {
                        interestRate = "7.63";
                    }
                    else if(interestPayout.equals(Constants.QUARTERLY))
                    {
                        interestRate = "7.68";
                    }
                    else if(interestPayout.equals(Constants.HALF_YEARLY))
                    {
                        interestRate = "7.75";
                    }
                    else if(interestPayout.equals(Constants.YEARLY))
                    {
                        interestRate = "7.90";
                    }
                }
            }
        }
        logger.info(" ===  Calculated Interest Rate for Senior Citizen FD== " + interestRate);
        return interestRate;
    }
    
    private String seniorCitizenSdp(Integer tenor)
    {
        String interestRate = "";
        if(tenor >= 12 && tenor <= 14)
        {
                interestRate = "7.65";
        }
        else if(tenor >= 15 && tenor <= 23)
        {
            if(tenor == 15)
            {
                interestRate = "7.70";
            }
            else if(tenor == 18)
            {
                interestRate = "7.65";
            }
            else if(tenor == 22)
            {
                interestRate = "7.75";
            }
            else
            {
                interestRate = "7.75";
            }
        }
        else if(tenor >= 24 && tenor <= 35)
        {
        	if(tenor == 24)
            {
                interestRate = "7.80";
            }
        	else if(tenor == 30)
            {
                interestRate = "7.70";
            }
            else if(tenor == 33)
            {
                interestRate = "8.00";
            }
            else
            {
                interestRate = "7.60";
            }
        }
        else if(tenor >= 36 && tenor <= 60)
        {
        	if(tenor == 44)
            {
                interestRate = "8.20";
            }
            else
            {
                interestRate = "7.90";
            }
        }
        logger.info(" ===  Calculated Interest Rate for Senior Citizen in seniorCitizenSdp == " + interestRate);
        return interestRate;
    }
    
    
    private String newCustomerSdp(Integer tenor)
    {
        String interestRate = "";
        if(tenor >= 12 && tenor <= 14)
        {
                interestRate = "7.40";
        }
        else if(tenor >= 15 && tenor <= 23)
        {
            if(tenor == 15)
            {
                interestRate = "7.45";
            }
            else if(tenor == 18)
            {
                interestRate = "7.40";
            }
            else if(tenor == 22)
            {
                interestRate = "7.50";
            }
            else
            {
                interestRate = "7.50";
            }
        }
        else if(tenor >= 24 && tenor <= 35)
        {
        	if(tenor == 24)
            {
                interestRate = "7.55";
            }
        	else if(tenor == 30)
            {
                interestRate = "7.45";
            }
            else if(tenor == 33)
            {
                interestRate = "7.75";
            }
            else
            {
                interestRate = "7.35";
            }
        }
        else if(tenor >= 36 && tenor <= 60)
        {
        	if(tenor == 44)
            {
                interestRate = "7.95";
            }
            else
            {
                interestRate = "7.65";
            }
        }
        logger.info(" ===  Calculated Interest Rate for New Customer  in newCustomerSdp == " + interestRate);
        return interestRate;
    }
    
    private String existingCustomerSdp(Integer tenor)
    {
        String interestRate = "";
        if(tenor >= 12 && tenor <= 14)
        {
                interestRate = "7.40";
        }
        else if(tenor >= 15 && tenor <= 23)
        {
            if(tenor == 15)
            {
                interestRate = "7.45";
            }
            else if(tenor == 18)
            {
                interestRate = "7.40";
            }
            else if(tenor == 22)
            {
                interestRate = "7.50";
            }
            else
            {
                interestRate = "7.50";
            }
        }
        else if(tenor >= 24 && tenor <= 35)
        {
        	if(tenor == 24)
            {
                interestRate = "7.55";
            }
        	else if(tenor == 30)
            {
                interestRate = "7.45";
            }
            else if(tenor == 33)
            {
                interestRate = "7.75";
            }
            else
            {
                interestRate = "7.35";
            }
        }
        else if(tenor >= 36 && tenor <= 60)
        {
        	if(tenor == 44)
            {
                interestRate = "7.95";
            }
            else
            {
                interestRate = "7.65";
            }
        }
        logger.info(" ===  Calculated Interest Rate for New Customer  in newCustomerSdp == " + interestRate);
        return interestRate;
    }
}