package com.bajaj.fixeddeposit.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bajaj.fixeddeposit.model.ApplicationIdGenerator;
import com.bajaj.fixeddeposit.model.AssistedPartnerCode;
import com.bajaj.fixeddeposit.model.BankNameDetails;
import com.bajaj.fixeddeposit.model.FDDealIdGenerator;
import com.bajaj.fixeddeposit.model.FDPincodeMaster;
import com.bajaj.fixeddeposit.model.FixedDepositCustIdGenerator;
import com.bajaj.fixeddeposit.model.FixedDepositData;
import com.bajaj.fixeddeposit.model.FixedDepositSchemeCode;
import com.bajaj.fixeddeposit.model.IMPSFixedDepositData;
import com.bajaj.fixeddeposit.model.IfscCode;
import com.bajaj.fixeddeposit.model.PartnerCode;
import com.bajaj.fixeddeposit.util.Constants;


@Repository("fixedDepositDao")
@Transactional
public class FixedDepositDaoImpl implements FixedDepositDao {
	
	private static final Logger logger = Logger.getLogger(FixedDepositDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public synchronized String customerIdGenerator() 
	{
		
		synchronized (this)
		{
			Session session = sessionFactory.getCurrentSession();
			FixedDepositCustIdGenerator custIdGenerator = (FixedDepositCustIdGenerator) session.createCriteria(FixedDepositCustIdGenerator.class).add(Restrictions.eq("status", "1")).uniqueResult();
			logger.info(" ==== FixedDepositCustIdGenerator ==== " + custIdGenerator);
			
			Integer custId = custIdGenerator.getCustomerId();
			logger.info(" === Generator Cust Id === " + custId);
			
			Integer incrementalCustId = custId + 1;
			logger.info(" === Incremental Generator Cust Id === " + incrementalCustId);
			custIdGenerator.setCustomerId(incrementalCustId);
			
			session.saveOrUpdate(custIdGenerator);
			logger.info("==== Incremenatl Customer Id updated successfully === ");
			
			String customerId = "FDC" + String.format("1%011d",custId);
			logger.info(" === Generator Cust Id === " + customerId);
			
			return customerId;
		}
		
	}

	@Override
	public String saveFixedDepositData(FixedDepositData fixedDepositData) {

		String dbStatus ="";
		Session session = sessionFactory.getCurrentSession();
		Integer saveId = (Integer) session.save(fixedDepositData);
		logger.info(" == DB Save Id === " + saveId);
		if(saveId!=0){
			dbStatus = Constants.STATUS_SUCCESS;
			logger.info(" === Data Saved DB Successfully == ");
		}else{
			dbStatus = Constants.STATUS_FAIL;
			logger.info(" === Data saving in Db Failed");
		}
		logger.info(" == Data Saving Status in DB === " + dbStatus);
		
		return dbStatus;
	}

	@Override
	public FixedDepositData getFixedDepositData(String customerId) {
		
		Session session = sessionFactory.getCurrentSession();
		logger.info(" === Customer Id to get Data === " + customerId);
		
		Criteria criteria = session.createCriteria(FixedDepositData.class);
		criteria.add(Restrictions.eq("customerId", customerId));
		criteria.setMaxResults(1);
		FixedDepositData fixedDepositData = (FixedDepositData) criteria.uniqueResult();
		logger.info(" === fixedDepositData in FixedDepositDaOImpl === " + fixedDepositData);
		
		return fixedDepositData;
	}

	@Override
	public String updateFixedDeposit(FixedDepositData fixedDepositData) {
		
		String updateStatus = Constants.STATUS_FAIL;
		try {
			Session session = sessionFactory.getCurrentSession();
			logger.info(" === fixedDeposit in updateFixedDeposit == " + fixedDepositData);
			session.update(fixedDepositData);
			updateStatus = Constants.STATUS_SUCCESS;
			logger.info(" == Data updated Successfully === ");
		} catch (Exception e) {
			updateStatus = Constants.STATUS_FAIL;
			logger.info(" == Data updation Failed === ");
			logger.error(" === Exception in updateFixedDeposit ===== ", e);
		}
		logger.info(" === updateStatus === " + updateStatus);
	return updateStatus;	
	}

	@Override
	public IfscCode ifscCodeValidator(String ifscCode) {
		synchronized(this){
		
		logger.info(" === ifscCode in ifscCodeValidator === " + ifscCode);
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from IfscCode where ifsc=:ifscCode");
		query.setCacheable(true);
		query.setParameter("ifscCode",ifscCode);
		IfscCode ifsc = (IfscCode) query.setMaxResults(1).uniqueResult();
		logger.info(" === ifsc in ifscCodeValidator === " + ifsc);
		
		return ifsc;
		}
	}

	@Override
	public String applicationIdGenerator() {
		synchronized(this){
			Session session = sessionFactory.getCurrentSession();
			ApplicationIdGenerator applicationIdGenerator = (ApplicationIdGenerator)session.createCriteria(ApplicationIdGenerator.class).add(Restrictions.eq("status", "1")).uniqueResult();
			
			Integer applicationId = applicationIdGenerator.getApplicationId();
			logger.info(" === application Id in applicationIdGenerator  ==== " + applicationId );
			
			String appId  = String.valueOf(applicationId);
			
			Integer incrementalAppId = applicationId + 1;
			logger.info(" ===== incrementalAppId in applicationIdGenerator ===== " + incrementalAppId);
			
			applicationIdGenerator.setApplicationId(incrementalAppId);
			session.update(applicationIdGenerator);
			logger.info(" == Incremental Id updated Successfully ====");
			
			return appId;
		}
	}

	@Override
	public PartnerCode partnerCodeValidator(String partnerCode) {
		synchronized(this){
		
		logger.info(" === partnerCode in partnerCodeValidator === " + partnerCode);
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from PartnerCode where partnerCode=:partnerCode");
		query.setCacheable(true);
		query.setParameter("partnerCode",partnerCode);
		PartnerCode partnerData = (PartnerCode) query.setMaxResults(1).uniqueResult();
		logger.info(" === Partner in partnerCodeValidator === " + partnerData);
		
		return partnerData;
		}
	}
	
	@Override
	public AssistedPartnerCode AssistedPCodeValidator(String partnerCode) {
		synchronized(this){
			logger.info(" === partnerCode in AssistedPCodeValidator DAO === " + partnerCode);
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from AssistedPartnerCode where partnerCode=:partnerCode");
			query.setCacheable(true);
			query.setParameter("partnerCode",partnerCode);
			AssistedPartnerCode assistedPartnerCode=(AssistedPartnerCode) query.setMaxResults(1).uniqueResult();
			return assistedPartnerCode;  
		}
		
	}

	@Override
	public String getSchemeCode(String investmentType, String customerType, String payoutType,
			String interestPayoutFrequency, String locationType) {
		synchronized(this){
		
		if("Cumulative".equalsIgnoreCase(payoutType)){
			interestPayoutFrequency = "NA";
		}else{
		if("Annual".equalsIgnoreCase(interestPayoutFrequency)|| "Annually".equalsIgnoreCase(interestPayoutFrequency)){
			interestPayoutFrequency = "Yearly";
		}
		}
		logger.info(" === investmentType == " + investmentType + " === customerType === " + customerType + " === interestPayout ==== " + payoutType + " === interestPayoutType === " + interestPayoutFrequency +"======locationType======"+locationType);
		String schemeCode = "";	
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from FixedDepositSchemeCode where product=:investmentType and bflCustType=:customerType and payoutType=:payoutType and"
				+ " interestPayoutFrequency=:interestPayoutFrequency and locationType=:locationType");
		query.setParameter("investmentType", investmentType);
		query.setParameter("customerType", customerType);
		query.setParameter("payoutType", payoutType);
		query.setParameter("interestPayoutFrequency", interestPayoutFrequency);
		query.setParameter("locationType", locationType);
		
		FixedDepositSchemeCode fixedDepositSchemeCode = (FixedDepositSchemeCode) query.setMaxResults(1).uniqueResult();
		logger.info(" == fixedDepositSchemeCode in getSchemeCode === " + fixedDepositSchemeCode);
		
		
		if(fixedDepositSchemeCode != null){
			schemeCode = fixedDepositSchemeCode.getScheme();
			logger.info(" === schemeCode in ==== " + schemeCode);
		}
		
		return schemeCode;
		}
	}
	
	
	public String getDealID(){
		
		synchronized (this){
		
		Session session = null;
		String dealIdData =null;
		try {
			session = sessionFactory.getCurrentSession();

			logger.info("-------- getDealID------------- ");

			Query query = session.createQuery("select dealID from FDDealIdGenerator");   
			logger.info(" ::: query :: "+query);
			logger.info(" ::: query.uniqueResult() :: "+query.uniqueResult());
			logger.info(" ::: query.list() :: "+query.list());

			dealIdData=  (String) query.uniqueResult();

			logger.info("-------- returnDealID------------- "+dealIdData);
			logger.info("-------- returnDealID+1------------- "+String.valueOf((Long.parseLong(dealIdData))+1));

			FDDealIdGenerator dealIdGenerator=(FDDealIdGenerator) session.get(FDDealIdGenerator.class, 1); // 3 is ID of user.
			dealIdGenerator.setDealID(String.valueOf((Long.parseLong(dealIdData))+1));
			session.saveOrUpdate(dealIdGenerator);
			
			logger.info("-------- returnDealIDGenerator after commit------------- "+ dealIdGenerator.toString());

		} catch (Exception e) {
			logger.error("-----Exception in getreturnDealID------",e);
		}     
		return dealIdData;
	}
	}

	@Override
	public FDPincodeMaster getcitystatename(int requestPin) {
		synchronized(this){
		logger.info(" === Pincode in Pincode Master === " + requestPin);
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from FDPincodeMaster where pincode=:pincode");
		query.setCacheable(true);
		query.setParameter("pincode",requestPin);
		FDPincodeMaster pincodeMaster = (FDPincodeMaster) query.setMaxResults(1).uniqueResult();
		logger.info(" === ifsc in ifscCodeValidator === " + pincodeMaster);
		
		return pincodeMaster;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FixedDepositData> custListforgetBillDeskRequery(String time) {
		
		List<FixedDepositData> list = null;
		try {
			Calendar calendarYesterday = Calendar.getInstance();
			calendarYesterday.add(Calendar.DATE, 0);
			Date yesterday = calendarYesterday.getTime();
			
			Calendar calendarDayBeforeYesterday = Calendar.getInstance();
			calendarDayBeforeYesterday.add(Calendar.DATE, -1);
			Date dayBeforeYesterday = calendarDayBeforeYesterday.getTime();
			
			SimpleDateFormat dbFormat = new SimpleDateFormat("dd-MMM-yyyy");
			
			String stDate = dbFormat.format(dayBeforeYesterday);
			String lsDate= dbFormat.format(yesterday);
			
			logger.info("======stDate========"+stDate+"=======lsDate==========="+lsDate);
			
			list = new ArrayList<>();
			Session session = sessionFactory.getCurrentSession();
			Query query = null;
			if(time.equalsIgnoreCase("night")) {
				//Query query = session.createQuery("from FixedDepositData where TO_DATE(NVL(SUBSTR(createdOn, 0, INSTR(createdOn, ' ')-1), createdOn),'dd-mm-yyyy') between to_date(to_char(to_date('"+stDate+"'),'dd-mm-yyyy'),'DD-MM-YYYY') AND to_date(to_char(to_date('"+lsDate+"'),'dd-mm-yyyy') ,'DD-MM-YYYY')and checksumresponse='1' and transactionStatus!='SUCCESS' and requerystatus is null OR requerystatus!='SUCCESS'");
				query = session.createQuery("from FixedDepositData where TO_DATE(NVL(SUBSTR(createdOn, 0, INSTR(createdOn, ' ')-1), createdOn),'dd-mm-yyyy') between to_date(to_char(to_date('"+lsDate+"'),'dd-mm-yyyy'),'DD-MM-YYYY') AND to_date(to_char(to_date('"+lsDate+"'),'dd-mm-yyyy') ,'DD-MM-YYYY')and checksumresponse='1' and (transactionStatus NOT IN ('SUCCESS')  or TRANSACTION_STATUS IS NULL) and requerystatus is null");
			}else  {
				query = session.createQuery("from FixedDepositData where TO_DATE(NVL(SUBSTR(createdOn, 0, INSTR(createdOn, ' ')-1), createdOn),'dd-mm-yyyy') between to_date(to_char(to_date('"+stDate+"'),'dd-mm-yyyy'),'DD-MM-YYYY') AND to_date(to_char(to_date('"+lsDate+"'),'dd-mm-yyyy') ,'DD-MM-YYYY')and checksumresponse='1' and (transactionStatus NOT IN ('SUCCESS')  or TRANSACTION_STATUS IS NULL) and (requerystatus is null OR requerystatus!='SUCCESS')");
			}
			
			logger.info("=======query==========="+query);
			list = query.list();
			
			logger.info("====list======"+list.size());
			
		} catch (Exception e) {
			logger.error("========exception in custListforgetBillDeskRequery============",e);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FixedDepositData> custListforgetBillDeskHourRequery() {
		
		List<FixedDepositData> list = null;
		try {
			Calendar calendarYesterday = Calendar.getInstance();
			calendarYesterday.add(Calendar.DATE, 0);
			Date yesterday = new Date();
			
			
			
			SimpleDateFormat dbFormat = new SimpleDateFormat("dd-MMM-yyyy");
			
			String lsDate= dbFormat.format(yesterday);
			
			logger.info("=======Todays Date==========="+lsDate);
			
			list = new ArrayList<>();
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from FixedDepositData where TO_DATE(NVL(SUBSTR(createdOn, 0, INSTR(createdOn, ' ')-1), createdOn),'dd-mm-yyyy') = to_date(to_char(to_date('"+lsDate+"'),'dd-mm-yyyy'),'DD-MM-YYYY') and checksumresponse='1' and finalSfdcId is null");
			logger.info("=======query==========="+query);
			list = query.list();
			
			logger.info("====list======"+list.size());
			
		} catch (Exception e) {
			logger.error("========exception in custListforgetBillDeskRequery============",e);
		}
		return list;
	}

	public String saveIMPSFixedDepositData(IMPSFixedDepositData impsfixedDepositData) {

		String dbStatus ="";
		Session session = sessionFactory.getCurrentSession();
		Integer saveId = (Integer) session.save(impsfixedDepositData);
		logger.info(" == DB Save Id === " + saveId);
		if(saveId!=0){
			dbStatus = Constants.STATUS_SUCCESS;
			logger.info(" === Data Saved DB Successfully == ");
		}else{
			dbStatus = Constants.STATUS_FAIL;
			logger.info(" === Data saving in Db Failed");
		}
		logger.info(" == Data Saving Status in DB === " + dbStatus);
		
		return dbStatus;
	}
	
	@Override
	public List<FixedDepositData> custListRemoveUploadedDocumets() {
		
		List<FixedDepositData> list = null;
		try {
			Calendar twoDaysBeforeDateCalcV1 = Calendar.getInstance();
			twoDaysBeforeDateCalcV1.add(Calendar.DATE, 0);
			Date yesterday = twoDaysBeforeDateCalcV1.getTime();
			
			Calendar twoDaysBeforeDateCalcV2 = Calendar.getInstance();
			twoDaysBeforeDateCalcV2.add(Calendar.DATE,0);
			Date dayBeforeYesterday = twoDaysBeforeDateCalcV2.getTime();
			
			SimpleDateFormat dbFormat = new SimpleDateFormat("dd-MMM-yyyy");
			
			String stDate = dbFormat.format(dayBeforeYesterday);
			String lsDate= dbFormat.format(yesterday);
			
			logger.info("======stDate========"+stDate+"=======lsDate==========="+lsDate);
			
			list = new ArrayList<>();
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from FixedDepositData where TO_DATE(NVL(SUBSTR(createdOn, 0, INSTR(createdOn, ' ')-1), createdOn),'dd-mm-yyyy') between to_date(to_char(to_date('"+stDate+"'),'dd-mm-yyyy'),'DD-MM-YYYY') AND to_date(to_char(to_date('"+lsDate+"'),'dd-mm-yyyy') ,'DD-MM-YYYY')and remarkCustType='NTBDocumentUpload'");
			//Query query = session.createQuery("from FixedDepositData where remarkCustType='NTBDocumentUpload'");
			
			logger.info("=======query==========="+query);
			list = query.list();
			
			logger.info("====list======"+list.size());
			
		} catch (Exception e) {
			logger.error("========exception in custListRemoveUploadedDocumets============",e);
		}
		return list;
	}
	
	
	
	@Override
	public List<FixedDepositData> custListRemoveFailUploadedDocumets() {
		
		List<FixedDepositData> list = null;
		try {
			Calendar twoDaysBeforeDateCalcV1 = Calendar.getInstance();
			twoDaysBeforeDateCalcV1.add(Calendar.DATE, -10);
			Date yesterday = twoDaysBeforeDateCalcV1.getTime();
			
			Calendar twoDaysBeforeDateCalcV2 = Calendar.getInstance();
			twoDaysBeforeDateCalcV2.add(Calendar.DATE, -10);
			Date dayBeforeYesterday = twoDaysBeforeDateCalcV2.getTime();
			
			SimpleDateFormat dbFormat = new SimpleDateFormat("dd-MMM-yyyy");
			
			String stDate = dbFormat.format(dayBeforeYesterday);
			String lsDate= dbFormat.format(yesterday);
			
			logger.info("======stDate========"+stDate+"=======lsDate==========="+lsDate);
			
			list = new ArrayList<>();
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from FixedDepositData where TO_DATE(NVL(SUBSTR(createdOn, 0, INSTR(createdOn, ' ')-1), createdOn),'dd-mm-yyyy') between to_date(to_char(to_date('"+stDate+"'),'dd-mm-yyyy'),'DD-MM-YYYY') AND to_date(to_char(to_date('"+lsDate+"'),'dd-mm-yyyy') ,'DD-MM-YYYY')and remarkCustType='NTBDocumentUpload'");
		
			logger.info("=======query==========="+query);
			list = query.list();
			
			logger.info("====list======"+list.size());
			
		} catch (Exception e) {
			logger.error("========exception in custListRemoveFailUploadedDocumets============",e);
		}
		return list;
	}
	

	@Override
	public FixedDepositData getFixedDepositDataByMobile(String mobNo) {
		
		Session session = sessionFactory.getCurrentSession();
		logger.info(" === Mobile no to get Data === " + mobNo);
		Query query = session.createQuery("from FixedDepositData where mobileNumber='"+mobNo+"' order by customerId DESC");
		query.setMaxResults(1);
		FixedDepositData fixedDepositData = (FixedDepositData)query.uniqueResult();
		logger.info(" === fixedDepositData in FixedDepositDaOImpl === " + fixedDepositData);
		return fixedDepositData;
	}
	
	@Override
	public IMPSFixedDepositData getImpsDepositData(String customerId) 
	{
		
		Session session = sessionFactory.getCurrentSession();
		logger.info(" === Customer Id to get Data IMPSFixedDepositData === " + customerId);
		Criteria criteria = session.createCriteria(IMPSFixedDepositData.class);
		criteria.add(Restrictions.eq("bflcustomerId", customerId));
		criteria.setMaxResults(1);
		IMPSFixedDepositData impsFixedDepositData = (IMPSFixedDepositData) criteria.uniqueResult();
		logger.info(" === impsFixedDepositData in FixedDepositDaOImpl === " + impsFixedDepositData);
		return impsFixedDepositData;
	}

	@Override
	public BankNameDetails getBankDetailsforPayment(String bankName) {
		
		Session session = sessionFactory.getCurrentSession();
		logger.info(" === bank Name to get Data === " + bankName);
		
		Criteria criteria = session.createCriteria(BankNameDetails.class);
		criteria.add(Restrictions.eq("ccavenuebankname", bankName));
		criteria.setMaxResults(1);
		BankNameDetails bankNameDetails = (BankNameDetails) criteria.uniqueResult();
		logger.info(" === bank Name Details in FixedDepositDaOImpl === " + bankNameDetails);
		
		return bankNameDetails;
	}
	
	@Override
	public FixedDepositData getFixedDepositDataByAppNumber(String custFormAppNumber) {
		
		try{
		Session session = sessionFactory.getCurrentSession();
		logger.info(" === cust FormAppNumber to get Data === " + custFormAppNumber);
		
		Criteria criteria = session.createCriteria(FixedDepositData.class);
		criteria.add(Restrictions.eq("formAppNumber", custFormAppNumber));
		criteria.setMaxResults(1);
		FixedDepositData fixedDepositData = (FixedDepositData) criteria.uniqueResult();
		logger.info(" === fixedDepositData in FixedDepositDaOImpl === " + fixedDepositData);
		return fixedDepositData;
		} catch(Exception e)
		{
			logger.error("========exception in getFixedDepositDataByAppNumber============",e);
			return null;
		}
		
	}
}


