package com.bajaj.fixeddeposit.dao;

import java.util.List;

import com.bajaj.fixeddeposit.model.AssistedPartnerCode;
import com.bajaj.fixeddeposit.model.BankNameDetails;
import com.bajaj.fixeddeposit.model.FDPincodeMaster;
import com.bajaj.fixeddeposit.model.FixedDepositData;
import com.bajaj.fixeddeposit.model.IMPSFixedDepositData;
import com.bajaj.fixeddeposit.model.IfscCode;
import com.bajaj.fixeddeposit.model.PartnerCode;

public interface FixedDepositDao {
	
	String customerIdGenerator();
	
	String saveFixedDepositData(FixedDepositData fixedDepositData);
	
	FixedDepositData getFixedDepositData(String customerId);
	
	String updateFixedDeposit(FixedDepositData fixedDepositData);
	
	IfscCode ifscCodeValidator(String ifscCode);
	
	String applicationIdGenerator();
	
	PartnerCode partnerCodeValidator(String partnerCode);
	
	AssistedPartnerCode AssistedPCodeValidator(String partnerCode);
	
	String getSchemeCode(String investmentType, String customerType, String payoutType, String interestPayoutFrequency,String locationType);

	String getDealID();

	FDPincodeMaster getcitystatename(int requestPin);
	
	List<FixedDepositData> custListforgetBillDeskRequery(String time);
	
	List<FixedDepositData> custListforgetBillDeskHourRequery();
	
	public String saveIMPSFixedDepositData(IMPSFixedDepositData impsfixedDepositData);
	
	public List<FixedDepositData> custListRemoveUploadedDocumets() ;
	
	public List<FixedDepositData> custListRemoveFailUploadedDocumets();
	
	public FixedDepositData getFixedDepositDataByMobile(String mobNo);
	
	public IMPSFixedDepositData getImpsDepositData(String customerId);
	
	BankNameDetails getBankDetailsforPayment(String bankName);
	
	FixedDepositData getFixedDepositDataByAppNumber(String custFormAppNumber);
}
