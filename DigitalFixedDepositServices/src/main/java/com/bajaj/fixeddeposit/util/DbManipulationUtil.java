package com.bajaj.fixeddeposit.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bajaj.fixeddeposit.dao.FixedDepositDao;
import com.bajaj.fixeddeposit.model.FixedDepositData;

@Service
public class DbManipulationUtil 
{

	private static final Logger logger = Logger.getLogger(DbManipulationUtil.class);

	@Autowired
	private FixedDepositDao fixedDepositDao;


	public void recordExeption(String ctaName,String category ,String customerId)
	{
		try
		{
			if(!(customerId.isEmpty())){
				if( !"NA".equalsIgnoreCase(customerId)){
					logger.info(" == Cust Id in recordExeption ==== " + customerId+"====CTA==="+ctaName);	
					FixedDepositData depositData  = fixedDepositDao.getFixedDepositData(customerId);
					logger.info(" == depositData in recordExeption ==== " + depositData);

					String exceptionCount=depositData.getExceptionCount() == null ? "0":depositData.getExceptionCount();
					String exceptionName=depositData.getExceptionCtaName() == null? "":depositData.getExceptionCtaName();
					String categoryType=depositData.getExceptionCategory()==null?"":depositData.getExceptionCategory();


					int count = Integer.parseInt(exceptionCount);
					count++;
					exceptionName=exceptionName.isEmpty() && exceptionName==null ? ctaName:exceptionName+"|"+ctaName;
					categoryType=categoryType.isEmpty() && categoryType==null ?categoryType:categoryType+" |"+category;


					depositData.setExceptionCount(String.valueOf(count));
					depositData.setExceptionCtaName(exceptionName);
					depositData.setExceptionCategory(categoryType);


					String updateStatus = fixedDepositDao.updateFixedDeposit(depositData);
					logger.info(" === updateStatus after recordExeption == " + updateStatus);
				}	}}catch(Exception e)
		{
					logger.info("-----------------Exception in recordExeption----------"+e);	
		}
	}


}
