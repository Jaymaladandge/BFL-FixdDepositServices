<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1,  minimal-ui" />
<meta name="description" content="pstp-prime" />
<meta name="author" content="Bajaj" />
<meta name="robots" content="index, follow" />
<meta http-equiv="content-language" content="en,de,fr" />

<title>Fixed Deposit and SDP application form</title>
<meta name="description"
	content="Bajaj Finance Fixed Deposit offers attractive interest rates up to 6.75%, so you can invest easily from the comfort of your home. Get assured returns with 0.25% higher interest rate for senior citizens.">
<meta name="keywords"
	content="Online fd application form, fixed deposit online, invest in fd online, open fd online">

<link rel="SHORTCUT ICON"
	href="${pageContext.request.contextPath}/resources/images/baflicon.ico" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/Style_old.css" />
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/newCss/daterangepicker.css" />
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

<%@ include file="wrapper.jsp"%>

<!-- Global site tag (gtag.js) - Google Ads: 802197272 -->
 <script async src="https://www.googletagmanager.com/gtag/js?id=AW-802197272"></script> 
 <script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());
  gtag('config', 'AW-802197272');
</script> 


 <script>
!function(f,b,e,v,n,t,s)
{if(f.fbq)return;n=f.fbq=function(){n.callMethod?
n.callMethod.apply(n,arguments):n.queue.push(arguments)};
if(!f._fbq)f._fbq=n;n.push=n;n.loaded=!0;n.version='2.0';
n.queue=[];t=b.createElement(e);t.async=!0;
t.src=v;s=b.getElementsByTagName(e)[0];
s.parentNode.insertBefore(t,s)}(window, document,'script','https://connect.facebook.net/en_US/fbevents.js');
fbq('init', '188490321873243');
fbq('track', 'PageView');
</script>
 <noscript><img height="1" width="1" style="display:none" src="https://www.facebook.com/tr?id=188490321873243&ev=PageView&noscript=1" /></noscript>

</head>



<body>

	<%
		String okycstatus = "";
		String investmentType = "";
		String fdslID = "";
		String fdFullname = "";
		String fdDateOfBirth = "";
		String fdExistingCustId = "";
		String fdPincode = "";
		String fdcity = "";
		String fdEmail = "";
		String fdPAN = "";
		String fdUserMobileNumber = "";
		String fdDedupeCustType = "";
		String fdCustType = "";
		String fdAddress = "";
		String customIDForManuplation = "";
		String fdproductForID = "";
		String fdcNumber="";
		if (session != null) {

			if (session.getAttribute("investmentType") != null) {
				investmentType = session.getAttribute("investmentType").toString();
			}else{
				investmentType="";
			}

			if (session.getAttribute("okycstatus") != null) {
				okycstatus = session.getAttribute("okycstatus").toString();
			}else{
				okycstatus="";
			}
			

			if (session.getAttribute("fdslID") != null) {
				fdslID = session.getAttribute("fdslID").toString();
				
			}else{
				fdslID="";
			}
			
			if (session.getAttribute("fdFullname") != null) {
				fdFullname = session.getAttribute("fdFullname").toString();
			}else{
				fdFullname="";
			}
			
			if (session.getAttribute("fdDateOfBirth") != null) {
				fdDateOfBirth = session.getAttribute("fdDateOfBirth").toString();
			}else{
				fdDateOfBirth="";
			}
			
			if (session.getAttribute("fdExistingCustId") != null) {
				fdExistingCustId = session.getAttribute("fdExistingCustId").toString();
			}else{
				fdExistingCustId="";
			}
			
			if (session.getAttribute("fdPincode") != null) {
				fdPincode = session.getAttribute("fdPincode").toString();
			}else{
				fdPincode="";
			}
			
			if (session.getAttribute("fdcity") != null) {
				fdcity = session.getAttribute("fdcity").toString();
			}else{
				fdcity="";
			}
			
			if (session.getAttribute("fdEmail") != null) {
				fdEmail = session.getAttribute("fdEmail").toString();
			}else{
				fdEmail="";
			}
			
			if (session.getAttribute("fdPAN") != null) {
				fdPAN = session.getAttribute("fdPAN").toString();
			}else{
				fdPAN="";
			}
			
			if (session.getAttribute("fdUserMobileNumber") != null) {
				fdUserMobileNumber = session.getAttribute("fdUserMobileNumber").toString();
			}else{
				fdUserMobileNumber="";
			}
			
			if (session.getAttribute("fdDedupeCustType") != null) {
				fdDedupeCustType = session.getAttribute("fdDedupeCustType").toString();
			}else{
				fdDedupeCustType= "NTB";
			}
			
			if (session.getAttribute("fdCustType") != null) {
				fdCustType = session.getAttribute("fdCustType").toString();
			}else{
				fdCustType="";
			}
			
			if (session.getAttribute("fdAddress") != null) {
				fdAddress = session.getAttribute("fdAddress").toString();
			}else{
				fdAddress="";
			}
			
			if (session.getAttribute("customIDForManuplation") != null) {
				customIDForManuplation = session.getAttribute("customIDForManuplation").toString();
			}else{
				fdAddress="";	
			}
			
			if (session.getAttribute("fdproductForID") != null) {
				fdproductForID = session.getAttribute("fdproductForID").toString();
			}else{
				fdproductForID="";
			}
			
			if (session.getAttribute("fdcNumber") != null) {
				fdcNumber = session.getAttribute("fdcNumber").toString();
			}else{
				fdcNumber="";
			}
		}
	%>




	<input id="investmentTypeOkyc" type="hidden" value='<c:out value="${investmentType}"/>' />
	<input id="okycstatusIDValue" type="hidden" value='<c:out value="${okycstatus}"/>' />
	
	<input id="fdslIDNew" type="hidden" value='<c:out value="${fdslID}"/>' />
	<input id="fdFullnameNew" type="hidden" value='<c:out value="${fdFullname}"/>' />
	<input id="fdDateOfBirthNew" type="hidden" value='<c:out value="${fdDateOfBirth}"/>' />
	<input id="fdExistingCustIdNew" type="hidden" value='<c:out value="${fdExistingCustId}"/>' />
	<input id="fdPincodeNew" type="hidden" value='<c:out value="${fdPincode}"/>' />
	<input id="fdcityNew" type="hidden" value='<c:out value="${fdcity}"/>' />
	<input id="fdEmailNew" type="hidden" value='<c:out value="${fdEmail}"/>' />
	<input id="fdPANnew" type="hidden" value='<c:out value="${fdPAN}"/>' />
	<input id="fdUserMobileNumberNew" type="hidden" value='<c:out value="${fdUserMobileNumber}"/>' />
	<input id="fdDedupeCustTypeNew" type="hidden" value='<c:out value="${fdDedupeCustType}"/>' />
	<input id="fdCustTypeNew" type="hidden" value='<c:out value="${fdCustType}"/>' />
	<input id="fdAddressNew" type="hidden" value='<c:out value="${fdAddress}"/>' />
	<input id="customIDForManuplationNew" type="hidden" value='<c:out value="${customIDForManuplation}"/>' />
	<input id="fdproductForIDNew" type="hidden" value='<c:out value="${fdproductForID}"/>' />
	<input type="hidden" name="tokenVal" id="csrf">
	<input id="fdcNumber" type="hidden" value='<c:out value="${fdcNumber}"/>' />
	
	<div class="maxcontainer">
		<div class="a_part_1" style="display: none">


			<section class="p_fdheadtitle">
				<div class="p_fdtextname">
					<h1>
						<p>Choose the deposit type</p>
						<img
							src="${pageContext.request.contextPath}/resources/images/allicon-new.png"
							alt="logo" class="logoimg">
					</h1>
				</div>
			</section>

			<section class="a_planCardSec">
				<div class="p_productcard">
					<div class="p_cardrow">
						<div class="a_perAnnnum">
							<div class="cardInner">
								<div class="halfPart">
									<p>Now grow your savings</p>
									<strong>Senior citizens get up to 6.75%* per annum</strong>
									<strong>Non senior citizens get up to 6.60%* per annum</strong>
									<!--<i>T&C Apply</i>-->
								</div>
							</div>

						</div>
						<div class="p_carddeposit" id="FD">
							<a href="#;" data-href="a_part_2">
								<div class="p_fullcardspace">
									<div class="p_fixdepo">
										<div class="p_lefttextdepo">
											<h2>Fixed Deposit</h2>
											<p>Invest in a single go for a specific tenor, during
												which your money earns interest.</p>
										</div>

									</div>
									<div class="p_depositsstarting">
										<p>Deposits starting</p>
										<strong><i class="fas fa-rupee"></i> 25,000<sub></sub></strong>
									</div>

									<div class="p_blueround"></div>
									<div class="p_bigblueround"></div>

									<i class="getStated">Get Started</i>
								</div>
							</a>

						</div>
						<div class="p_carddeposit" id="SDP">
							<a href="#;" data-href="a_part_2">
								<div class="p_fullcardspace green">
									<div class="p_fixdepo">
										<div class="p_lefttextdepo">
											<h2>Systematic Deposit Plan</h2>
											<p>Save small amounts every month and get assured returns
												on every deposit.</p>
										</div>

									</div>
									<div class="p_depositsstarting">
										<p>Deposits starting</p>
										<strong><i class="fas fa-rupee"></i> 5,000 <sub>Per
												month</sub></strong>
									</div>


									<div class="p_roundgreen"></div>
									<div class="p_biggreenround"></div>
									<i class="getStated">Get Started</i>
								</div>
							</a>

						</div>
					</div>
				</div>
			</section>

			<section class="a_questpartSec">
				<h2>Can't decide? Read this to know more</h2>
				<div class="a_questCardBlok">
					<div class="oneBlock">
						<div class="a_blockInner">
							<strong>How is Systematic Deposit Plan different from
								Fixed Deposit?</strong>
							<p>Fixed Deposit is best for lumpsum investments, but to save
								and earn on a monthly basis, you can choose Systematic Deposit
								Plan (SDP). Under SDP, each of your deposits are separate FDs,
								which earn interest as per the interest rates prevailing on the
								day of booking.</p>
						</div>
					</div>
					<div class="oneBlock">
						<div class="a_blockInner">
							<strong>What is the minimum amount for investing in
								Fixed Deposit?</strong>
							<p>You can start saving with just Rs. 25,000 in a Bajaj
								Finance Fixed Deposit, or start saving with just Rs. 5000 per
								month in a Systematic Deposit Plan.</p>
						</div>
					</div>
					<div class="oneBlock">
						<div class="a_blockInner">
							<strong>Can an NRI also invest in a Bajaj Finance Fixed
								Deposit?</strong>
							<p>Currently, NRIs cannot book a Bajaj Finance Fixed Deposit
								online.</p>
						</div>
					</div>
					<div class="oneBlock">
						<div class="a_blockInner">
							<strong>How many deposits can I make with Bajaj Finance
								Fixed Deposit?</strong>
							<p>You can make any number of deposits with Bajaj Finance FD.
								However, you can choose to make 6 to 48 deposits with Systematic
								Deposit Plan.</p>
						</div>
					</div>

					<div class="oneBlock">
						<div class="a_blockInner">
							<strong>Why should I choose a Fixed Deposit from Bajaj
								Finance?</strong>
							<p>- Returns as high as 6.75% on your investment - The
								highest stability rating as certified by CRISIL and ICRA -
								Choice of tenor between 12 to 60 months as per your convenience
								- Additional 0.25% rate of interest for senior citizens</p>
						</div>
					</div>
					<div class="oneBlock">
						<div class="a_blockInner">
							<strong>Can I withdraw my Fixed Deposit before maturity?</strong>
							<p>You can prematurely withdraw post the initial lock-in
								period of 3 months. However, to avoid loss of interest, you can
								choose to avail a Loan against Fixed Deposit, where you can take
								an easy loan. Which is less than 75% of the FD value.</p>
						</div>
					</div>

					<div class="oneBlock">
						<div class="a_blockInner">
							<strong>What are my payout options?</strong>
							<p>There are 2 options. In the cumulative option, the
								interest and principal is payable at the time of maturity and is
								compounded annually. In the non-cumulative option, you can
								choose from monthly, quarterly, half yearly and yearly payout
								options.</p>
						</div>
					</div>
				</div>
			</section>



		</div>
		<div class="a_part_2">
			<%--   <section class="p_fdheadtitle">
            <div class="p_fdtextname">
             <img   src="${pageContext.request.contextPath}/resources/images/logo.png" alt="logo" class="logoimg" >
             <p>Enter basic details</p></div>
        </section> --%>

			<section class="p_fdheadtitle">
				<div class="p_fdtextname">
					<h1>
						<p>Enter basic details</p>
						<img
							src="${pageContext.request.contextPath}/resources/images/allicon-new.png"
							alt="logo" class="logoimg">
					</h1>
				</div>
			</section>

			<section class="p_formandvideos">




				<div class="p_loginformpart">
					<div class="p_growyoursaving">
						<div class="p_growicon">
							<img
								src="${pageContext.request.contextPath}/resources/images/surface1.png"
								alt="surface">
						</div>
						<div class="p_datagrowing">
							<h2>Earn more with our Fixed Deposit</h2>

							<p>Additional 0.10% p.a. interest if you book an FD online</p>
							<p>100% online process. Quickest way to book an FD.</p>
							<p>Brand trusted by over 4 crore customers</p>
							<p >Highest stability ratings of FAAA and MAAA</p>
						</div>
					</div>
					<input type="hidden" name="tokenVal" id="csrf">
					<div class="p_loginformsection">
						<form id="SendOtp">
						<a href="#;" class="allIntTbl step1intrest">View interest rates</a>
						
                           
						 <div class="a_radiodeposit">
                                <label class="j_hunpd">
                                    <input type="radio" name="fd" id="new" value="newuser" checked="">
                                    <i></i>
                                    <p>I want to apply for a new FD</p>
                                </label>
                            </div>
                             <div class="mobBorderBlock active">
						
							<div class="a_ReInput">


								<label for="mobileNO">MOBILE NUMBER <img src="${pageContext.request.contextPath}/resources/images/information_black.png" alt="">
                                        <div class="p_fixdepositstultip">
                                            <div class="p_fixdepositBG">
                                                <p>Enter your mobile no. registered with us. If you are new to Bajaj Finance Limited then enter your mobile number registered with cKYC/Aadhaar.</p>
                                                
                                                <div class="p_gotitbtn"><button class="gotitone">Got it</button></div>
                                            </div>
                                        </div>
								</label>
								<div class="j_countCd">+91</div>
								<input type="number" class="mobileVD inspectletIgnore" id="mobileNO"
									autocomplete="off">

								<div class="errormsg">Enter your mobile number</div>
							</div>
							
							<div class="a_ReInput datefield">

								<label for="DD">DATE OF BIRTH <img src="${pageContext.request.contextPath}/resources/images/information_black.png" alt="">
                                        <div class="p_fixdepositstultip">
                                            <div class="p_fixdepositBG">
                                                <p>Please enter the date of birth, as per your PAN card.</p> 
                                                <p>To invest online, you need to be at least 18 years of age.</p>
                                                <div class="p_gotitbtn"><button class="gotitone">Got it</button></div>
                                            </div>
                                        </div> 
                                    </label>
                                     <input type="number"
									class="dobcheckVD" id="dd" placeholder="DD" autocomplete="off"
									aria-labelledby="dobRange">
								<div class="dateslace">/</div>
								<input type="number" class="dobcheckVD" id="mm" placeholder="MM"
									autocomplete="off" aria-labelledby="dobRange">
								<div class="dateslace">/</div>
								<input type="number" class="dobcheckVD" id="yyyy"
									placeholder="YYYY" autocomplete="off"
									aria-labelledby="dobRange">
								<div class="borderDown"></div>




								<div class="errormsg">Enter your date of birth in DD/MM/YYYY format</div>
							</div>
							
							</div>
							 <div class="a_radiodeposit pt-0">
                                <label class="j_hunpd">
                                    <input type="radio" name="fd" id="preapp" value="existinguser">
                                    <i></i>
                                    <p>I want to resume previous application</p>
                                </label>
                            </div>
                             <div class="mobBorderBlock">
                            <div class="a_ReInput" id="appNofield">
                                <label for="enterapp">Mobile Number <img src="${pageContext.request.contextPath}/resources/images/information_black.png" alt="">
                                        <div class="p_fixdepositstultip">
                                            <div class="p_fixdepositBG">
                                                <p>Please provide the mobile number you entered during your previous application</p>
                                                <div class="p_gotitbtn"><button class="gotitone">Got it</button></div>
                                            </div>
                                        </div>
                                </label>
                                <div class="j_countCd">+91</div>
                                <input type="number" class="mobileVD nomandetory" autocomplete="off" id="enterapp" disabled>
                                <div class="errormsg">Enter mobile number used in previous application process</div>
                            </div>
                             </div>
                            
							<div class="a_ourParner mt-10">
								<div class="a_afterMarurity">
									<strong>Are you assisted by our employee/partner?<img src="${pageContext.request.contextPath}/resources/images/information_black.png" alt="">
                                        <div class="p_fixdepositstultip">
                                            <div class="p_fixdepositBG">
                                                <p>Enter the employee/partner sourcing code, only if you're being assisted by a Bajaj Finance employee or authorized partner.</p> 
                                                <p>Once you enter the code, the name of employee/partner will be pre-populated against the sourcing code.</p>
                                                <div class="p_gotitbtn"><button class="gotitone">Got it</button></div>
                                            </div>
                                        </div>
									
									</strong>
									<div class="a_swichTgl">
										<label for="checkBox_step1"> <input type="checkbox"
											id="checkBox_step1"> <i></i>
											<p class="yes">Yes</p>
											<p class="no">No</p>
										</label>
									</div>
								</div>
								<div class="a_ReInput">
									<label for="pCode">Employee/Partner Code</label> <input
										type="number" class="partnerVD nomandetory inspectletIgnore" id="pCode"
										autocomplete="off">
									<div class="errormsg">Enter correct sourcing code of the employee/partner assisting you</div>
								</div>
								<div class="a_ReInput">
									<label for="pName">Employee/Partner Name</label> <input
										type="text" class="textVD nomandetory inspectletIgnore" id="pName"
										autocomplete="off" readonly="readonly">
									<div class="errormsg">Employee/partner name not found</div>
								</div>
							</div>

							<div class="etbterm mb-20">
								<div class="chechTC">
									<label class="turmcon" for="t&ccheckbox">
										<p class="more">
											I authorise BFL to fetch my CKYC/offline Aadhaar KYC details
											available with CERSAI/UIDAI. I have gone through the
											financials and other statements / particulars /
											representations furnished / made by the company and after
											careful consideration I am making the deposit with the
											company at my own risk and volition. I also authorise Bajaj
											Finance representative to call/SMS towards this application.
											This consent overrides my registration for DNC <a
												class="otpTncPdf"
												href="${pageContext.request.contextPath}/resources/pdf/TCs-RI.pdf"
												target="_blank">T&C</a>
										</p> <input type="checkbox" value="tNc" id="t&ccheckbox"
										class="checkVD" checked> <i class="checkmark">
										</i>
									</label>
									<div class="errormsg" style="display: none;">Please check T&C to proceed</div>
								</div>
							</div>
							<div class="p_reqotp a_blueBtnPart">
								<button class="submitBTN validBtn">
									<span> GENERATE OTP </span> <img
										src="${pageContext.request.contextPath}/resources/images/whtarrow.png"
										alt="whtarrow" class="imgarrowwt">

										
									<div class="fd_sdp_loder"></div>
								</button>
							</div>
						</form>
					</div>


				</div>

<div class="a_assistanceStep">
            <strong>Book your FD in 4 simple steps</strong>
            <ul>
                <li class="active"><i>1</i><p>Enter mobile number, date of birth and verify OTP to continue</p></li>
                <li><i>2</i><p>If you're an existing customer, enter nominee details to continue. In case you are a new customer, complete your KYC by providing PAN or Aadhaar or simply upload documents</p>
                </li>
                <li><i>3</i><p>Enter deposit amount, tenor and interest payout type, along with bank account details.</p> </li>
                <li><i>4</i><p>Choose to pay via NetBanking or UPI. For investment above Rs. 1,00,000, only Netbanking option is available.</p><p>Upon successful payment, your deposit will be booked and you'll receive an acknowledgement via email and SMS within 15 minutes.</p> </li>
            </ul>
            <strong>Note:</strong>
            <p>On the day of maturity, your maturity amount will be directly credited into your bank account as per the details shared by you.</p>
        </div>


			</section>

		</div>

		<div class="a_part_3">

			<%--        <section class="p_fdheadtitle">
            <div class="p_fdtextname">
            <img   src="${pageContext.request.contextPath}/resources/images/logo.png" alt="logo" class="logoimg" > <p>Verify your mobile number</p></div>
        </section> --%>

			<section class="p_fdheadtitle">
				<div class="p_fdtextname">
					<h1>
						<p>Verify your mobile number</p>
						<img
							src="${pageContext.request.contextPath}/resources/images/allicon-new.png"
							alt="logo" class="logoimg">
					</h1>
				</div>
			</section>

			<section class="p_formandvideos">




				<div class="p_loginformpart">
					<div class="p_growyoursaving">
						<div class="p_datagrowing tax-data inspectlet-sensitive">

							<strong class="appe_MO" id="sent_Mo">OTP is successfully
								sent to mobile no. <span></span>
							</strong> <i>Entered a wrong number? <a href="#;">Change here</a></i>
						</div>
					</div>

					<div class="p_loginformsection">
						<form id="receiveOtp">

							<div class="a_ReInput">
								<label for="enterOtp1">One Time Password (OTP)</label> 
								<input type="number" id="otpDigit" class="otpVD inspectletIgnore" autocomplete="off">
								<div class="errormsg">Please enter OTP</div>
							</div>

							<div class="j_resendDes" style="display: none;">
								<p>
									<a href="#;" class="resendot "> Resend OTP
										<div class="fd_sdp_loder"></div>
									</a>

								</p>
							</div>



							<p class="a_resendTime">
								<img
									src="${pageContext.request.contextPath}/resources/images/reload.jpg"
									alt="reload"> Resend OTP in <i>00:60</i> seconds
							</p>
							<p>
								<strong class="appe_MO resent_MO" style="display: none">
									We have resent another OTP to your mobile no. <span>9999999999</span>
								</strong>
							</p>

							<div class="p_reqotp a_blueBtnPart">
								<button class="submitBTN validBtn" disabled>
									CONTINUE <img
										src="${pageContext.request.contextPath}/resources/images/whtarrow.png"
										alt="whtarrow" class="imgarrowwt">
										
										

									<div class="fd_sdp_loder"></div>
								</button>
							</div>
						</form>
					</div>


				</div>
				<div class="a_assistanceStep">
            <strong>Book your FD in 4 simple steps</strong>
            <ul>
                <li class="active"><i>1</i><p>Enter mobile number, date of birth and verify OTP to continue</p></li>
                <li><i>2</i><p>If you're an existing customer, enter nominee details to continue. In case you are a new customer, complete your KYC by providing PAN or Aadhaar or simply upload documents</p>
                </li>
                <li><i>3</i><p>Enter deposit amount, tenor and interest payout type, along with bank account details.</p> </li>
                <li><i>4</i><p>Choose to pay via NetBanking or UPI. For investment above Rs. 1,00,000, only Netbanking option is available.</p><p>Upon successful payment, your deposit will be booked and you'll receive an acknowledgement via email and SMS within 15 minutes.</p> </li>
            </ul>
            <strong>Note:</strong>
            <p>On the day of maturity, your maturity amount will be directly credited into your bank account as per the details shared by you.</p>
        </div>
			</section>
		</div>

		<div class="a_part_3-1">

			

			<section class="p_fdheadtitle">
				<div class="p_fdtextname">
					<h1>
						<p>Help us identify you better</p>
						<img
							src="${pageContext.request.contextPath}/resources/images/allicon-new.png"
							alt="logo" class="logoimg">
					</h1>
				</div>
			</section>

			<section class="p_formandvideos">

				<div class="p_loginformpart">
					<div class="p_growyoursaving">
						<div class="p_datagrowing">

							<strong>The below details will help us check your
								KIN/CKYCR no. available with CERSAI</strong>
						</div>
					</div>
					<div class="p_loginformsection">
						<form id="panCarVerify">
							<div class="a_ReInput">
								<label for="fullNamePD">Full name</label> <input type="text"
									name="fullName" class="FullNameVD inspectletIgnore" id="fullNamePDNew"
									autocomplete="off">
								<div class="errormsg">Please enter full name</div>

							</div>

							<div class="a_ReInput">
								<label for="pinCodePV">PIN CODE</label> <input type="number"
									name="pinCode" id="pinCodePV" class="PinCodeVD"
									autocomplete="off">
								<div class="errormsg">Enter your pincode</div>
							</div> 
							<div class="a_ReInput">
								<label for="panCardPV">PAN <img
									src="${pageContext.request.contextPath}/resources/images/information_black.png"
									alt="information.png">
									<div class="p_fixdepositstultip">
										<div class="p_fixdepositBG">
											<p>Valid PAN format is "AAAPANNNNA", where A - any
												letter, P - only letter P, N - any number</p>
											<div class="p_gotitbtn">
												<button class="gotitone">Got it</button>
											</div>
										</div>
									</div>
								</label> <input type="text" name="pan" id="panCardPV" class="PanVD inspectletIgnore"
									autocomplete="off">
								<div class="errormsg">Enter your PAN</div>
							</div>

							
							<div class="p_reqotp a_blueBtnPart" id="ckycSubmitt" style=" padding-bottom: 23px;">
								<button class="submitBTN validBtn">
									PROCEED <img
										src="${pageContext.request.contextPath}/resources/images/whtarrow.png"
										alt="whtarrow" class="imgarrowwt">
										
										
										
									<div class="fd_sdp_loder"></div>
								</button>
							</div>
							
							<%--DOCUPLOAD AND OKYC RADIO BUTTON --%>
							<div class="a_part_3-2 okycandDocSplitChech" id="okycandDocSplitChech">
							 <div class="a_opplsLink"   id="panOpsError">
                                <div class="a_oopsPlan">
                                    <p> Your CKYC Process was unsuccessful.You can proceed further by choosing any of the below option</p>
                                </div>
                            </div>
							<div class="a_radiodeposit">
							 <p class="okycLabel">HOW DO YOU LIKE TO PROCEED FURTHER ?</p>
               <label id="okycCheckoption">
                  <input type="radio" name="authentication" value="OKYC" checked>
                  <i></i>
                 <p>Aadhaar verification process <i>Fastest</i></p>
                <b>Docs</b>
                                        <div class="p_fixdepositstultip">
                                            <div class="p_fixdepositBG"> 
                                                <p>Please keep your Aadhaar handy.</p>
                                                <div class="p_gotitbtn"><button class="gotitone">Got it</button></div>
                                            </div>
                                        </div>
     </label>
               <label id="documentcheckoption">
                  <input type="radio" name="authentication" value="Document Upload" >
                  <i></i>
                  <p>By uploading KYC documents</p>
				  <b>Docs</b>
                                        <div class="p_fixdepositstultip">
                                            <div class="p_fixdepositBG">
                                                <strong>Please keep the below documents handy -</strong>
                                                <p>For Proof of Identity (Any 1):</p>
                                                <p>- Passport</p> 
                                                <p>- Driving License</p> 
                                                <p>- Voter Id</p> 
                                                <p>- NREGA</p> 
                                                <p>- Aadhar</p>
                                                 <br>
                                                <p>For Proof of Address (Any 1):</p>
                                                <p>- Passport</p>
                                                <p>- Driving License</p>
                                                <p>- Voter Id</p>
                                                <p>- NREGA</p>
                                                <p>- Aadhar</p>
                                                  <p>- NPR</p>
                                                <p></p>
                                                <p>For Sign Proof</p>
                                                <p>- PAN</p>
                                                <p>- Signature on blank page</p>
                                                
                                                <div class="p_gotitbtn"><button class="gotitone">Got it</button></div>
                                            </div>
                                        </div>
               </label>
            </div>
							
                            <div class="p_reqotp" >
								<button class="submitBTN okycandDocSplitChechButton" id="okycandDocSplitChechButton" >
									PROCEED <img
										src="${pageContext.request.contextPath}/resources/images/whtarrow.png"
										alt="whtarrow" class="imgarrowwt">
									<div class="fd_sdp_loder"></div>
								</button>
							</div>    
                            </div>
							
						</form>
					</div>


				</div>


<div class="a_assistanceStep">
            <strong>Book your FD in 4 simple steps</strong>
            <ul>
                <li><i>1</i><p>Enter mobile number, date of birth and verify OTP to continue</p></li>
                <li class="active"><i>2</i><p>If you're an existing customer, enter nominee details to continue. In case you are a new customer, complete your KYC by providing PAN or Aadhaar or simply upload documents</p>
                </li>
                <li><i>3</i><p>Enter deposit amount, tenor and interest payout type, along with bank account details.</p> </li>
                <li><i>4</i><p>Choose to pay via NetBanking or UPI. For investment above Rs. 1,00,000, only Netbanking option is available.</p><p>Upon successful payment, your deposit will be booked and you'll receive an acknowledgement via email and SMS within 15 minutes.</p> </li>
            </ul>
            <strong>Note:</strong>
            <p>On the day of maturity, your maturity amount will be directly credited into your bank account as per the details shared by you.</p>
        </div>


			</section>

		</div>
<div class="a_part_document">
    <section class="p_fdheadtitle">
        <div class="p_fdtextname"><!-- <a href="#;">Back</a> -->
            <p>KYC documents upload</p> 	<img
							src="${pageContext.request.contextPath}/resources/images/allicon-new.png"
							alt="logo" class="logoimg">
        </div>
    </section>
    <section class="p_formandvideos">
        <div class="updd">
            <form id="uploadDocument">
                <div class="j_inupdiv">
                    <p class="topdesc">Please upload your KYC documents to proceed further</p>
                    <ul class="tpsubline">
                        <li>Maximum size of a document that can be uploaded is 4 MB</li>
                        <li>Documents formats allowed are .jpeg, .jpg, .pdf</li>
                    </ul>
                    <div class="j_docs identity">
                        <div class="a_ReInput">
                            <label>Select Proof of Identity document</label>
                            <select class="identityval" name="indentityDocumentSelected">
                                 <option value=""> Select Identity Document</option> 
                                <option value="Passport">Passport</option>
                                <option value="Driving License">Driving License</option>
                                <option value="VoterID">VoterID</option>
                                <option value="NREGA">NREGA</option>
                                <option value="Aadhaar">Aadhaar</option>
                            </select>
                            <i class="fas fa-caret-down"></i>
                            <div class="errormsg">Please select Proof of Identity document</div>
                        </div>
                        <div class="a_ReInput uploadbtn tipstool_1">
                            <label for="frontidproof" class="upload" id="identityHoverup"><i class="fa fa-upload" aria-hidden="true"></i>Front</label>
                            <input type="file" id="frontidproof" >
                            <input type="text" autocomplete="off" disabled="">
                            <label class="removedoc">Remove</label>
                            <div class="errormsg">Please upload necessary document</div>
                            <div class="custooltip_1 identityvalhover">
                                            <p>Please select Proof of Identity document</p>
                                        </div>
                        </div>
                        <div class="a_ReInput uploadbtn tipstool_1">
                            <label for="backidproof" class="upload" id="identityHoverup"><i class="fa fa-upload" aria-hidden="true"></i>Back</label>
                            <input type="file" id="backidproof"  >
                            <input type="text" autocomplete="off" disabled="">
                            <label class="removedoc">Remove</label>
                            <div class="errormsg">Please upload necessary document</div>
                            <div class="custooltip_1 identityvalhover">
                                            <p>Please select Proof of Identity document</p>
                                        </div>
                        </div>
                        <div class="j_checkprf">
                            <div class="chechTC">
                                <label class="turmcon">
                                    <p class="more">
                                        Use this document as my address proof</p>
                                    <input type="checkbox" name="addprf" value="No"  checked>
                                    <i class="checkmark"></i>
                                </label>
                            </div>
                            <div class="chechTC hdprof">
                                <label class="turmcon">
                                    <p class="more">
                                        Use this document as my sign proof</p>
                                    <input type="checkbox" name="signprf" value="No" checked>
                                    <i class="checkmark"></i>
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="j_docs addfield">
                        <div class="a_ReInput">
                            <label>Select Proof of Address document</label>
                            <select class="adddropdown"  id="addressDocumentSelected" disabled>
                                 <option value="">Select Address document</option> 
                                <option value="Passport">Passport</option>
                                <option value="Driving License">Driving License</option>
                                <option value="VoterID">VoterID</option>
                                <option value="NREGA">NREGA</option>
                                <option value="Aadhaar">Aadhaar</option>
                                <option value="NPR">NPR</option>
                            </select>
                            <i class="fas fa-caret-down"></i>
                            <div class="errormsg">Please select Proof of Address document</div>
                        </div>
                        <div class="a_ReInput uploadbtn tipstool_1">
                            <label for="frontadd" class="upload" id="hoverUpadd"><i class="fa fa-upload" aria-hidden="true"></i>Front</label>
                            <input type="file" id="frontadd" disabled>
                            <input type="text" autocomplete="off" disabled="">
                            <label class="removedoc">Remove</label>
                            <div class="errormsg">Please upload necessary document</div>
                             <div class="custooltip_1" id="signDocumentSelectedhover">
                                            <p>Please select Proof of Address document</p>
                                        </div>
                        </div>
                        <div class="a_ReInput uploadbtn tipstool_1">
                            <label for="backadd" class="upload" id="hoverUpadd"><i class="fa fa-upload" aria-hidden="true"></i>Back</label>
                            <input type="file" id="backadd" class="adabj" disabled>
                            <input type="text" autocomplete="off" disabled="">
                            <label class="removedoc">Remove</label>
                            <div class="errormsg">Please upload necessary document</div>
                            <div class="custooltip_1" id="signDocumentSelectedhover">
                                            <p>Please select Proof of Address document</p>
                                        </div>
                        </div>
                    </div>
                </div>
                <div class="j_inupdiv mar_tp">
                    <div class="j_docs signpr">
                        <div class="a_ReInput">
                            <label>Select Sign Proof</label>
                            <select disabled  id="signDocumentSelected">
                               <option value="">Select Sign Proof</option> 
                                <option value="Passport">Passport</option>
                                <option value="Driving License">Driving License</option>
                                <option value="PAN">PAN</option>
                                <option value="Signature on blank page">Signature on blank page</option>
                               <!--  <option>NAREGA</option>
                                <option>Aadhaar</option>
                                <option>NPR</option> -->
                            </select>
                            <i class="fas fa-caret-down"></i>
                            <div class="errormsg">Please select Sign Proof</div>
                        </div>
                        <div class="a_ReInput uploadbtn tipstool_1">
                            <label for="signproof" id="hoverUpSign" class="upload"><i class="fa fa-upload" aria-hidden="true"></i>Upload</label>
                            <input type="file" id="signproof" disabled>
                            <input type="text" autocomplete="off" disabled="">
                            <label class="removedoc">Remove</label>
                            <div class="errormsg">Please upload necessary document</div>
                            <div class="custooltip_1 adddropdownhover">
                                            <p>Please select Proof of Sign</p>
                                        </div>
                        </div>
                    </div>
                    <div class="j_docs photosec">
                        <div class="a_ReInput uploadbtn tipstool_1">
                            <label class="piclab paddingPhoto">Please upload Photograph</label>
                            <input type="file" id="photo" >
                            <label for="photo" class="upload"><i class="fa fa-upload" aria-hidden="true"></i>Upload</label>
                            <input type="text" autocomplete="off" disabled="">
                            <label class="removedoc">Remove</label>
                            <div class="errormsg">Please upload necessary document</div>
                            
                        </div>
                    </div>
                     
                                <div class="etbterm">
                                    <div class="chechTC">
                                        <label class="turmcon">
                                            <p class="more">
                                                I accept all the below T&C <br/>
                                                - I hereby state that all particulars, information and details provided above together with documents submitted to Bajaj Finance Limited ("BFL") are true, correct and up to date and I am obliged to keep BFL immediately updated of any change in the information provided by me herein. In case any of the above information is found to be false or untrue or misleading or misrepresenting, I am aware that I will be solely held liable for it. <br/>- To the best of my knowledge, I confirm that this application is not in contravention of any Act, Rules, Regulations or any statute of legislation or any notifications/directions issued by any Govt. or Statutory authority from time to time. <br/>- I consent for sharing my information including KYC details with Central KYC Records Registry (CKYCR) namely Central Registry of Securitisation Asset Reconstruction and Security Interest (CERSAI) and Credit Information Companies (CICs). Further to that I consent to receive information from CKYCR through SMS/ E-mail on the above registered number/ e-mail address.<br/>- certify that the information provided above is in accordance with section 285BA of the Income Tax Act, 1961 read with Rules 114F to 114H of the Income Tax Rules, 1962. I undertake to inform BFL timely and in writing, any change in status of my citizenship, nationality or tax residence.
                                                
                                            </p>
                                            <input type="checkbox" value="tNc" checked class="checkVD">
                                            <i class="checkmark"></i>
                                        </label>
                                        <div class="errormsg" style="display: none;">Please check</div>
                                    </div>
                                </div>
                    <div class="subval a_blueBtnPart"><button class="submitBTN validBtn">Submit<div class="fd_sdp_loder"></div></button></div>
                </div>
            </form>
        </div>
    </section>
</div>
		<div class="a_part_4">


			<section class="p_fdheadtitle">
				<div class="p_fdtextname">
					<h1>
						<p>Confirm your details</p>
						<img
							src="${pageContext.request.contextPath}/resources/images/allicon-new.png"
							alt="logo" class="logoimg">
					</h1>
				</div>
			</section>

			<section class="a_confirmDetSec">
				<div class="a_topBoxbord">
					<div class="a_halfBox">
						<div class="a_welcomeBord">
							<div class="a_alphaFont"></div>
							<h2>Welcome</h2>
							<p id="existedCustLine">You&#39;re just 2 steps away from
								booking your deposit.Below details are fetched from our existing
								record.</p>
							<p id="newCustline" style="display: none">You're just 2 steps
								away from booking your deposit. Below details have been received
								from CKYCR.</p>
							<p id="okycCustLine" style="display: none">You're just 2
								steps away from booking your deposit. Below details have been
								received from offline verification of Aadhaar number.</p>
						</div>
					</div>
					<div class="a_halfBox">
						<div class="a_exclusiveBox">
							<img
								src="${pageContext.request.contextPath}/resources/images/gift.jpg"
								alt="gift">
							<h2>Exclusive benefits for you</h2>
							<p id="etbExclusive" style="display: none">Additional
								interest rate of 0.10% p.a</p>
							<p id="stbExclusive" style="display: none">Additional
								interest rate of 0.25% p.a</p>
							<p id="newExclusive" style="display: none">Additional
								interest rate of 0.10% p.a</p>
							<p>100% online deposit process</p>
						</div>
					</div>
				</div>
			</section>

			<section class="a_confirmtabsSec">
				<div class="a_topBoxbord">
					<div class="a_halfBox">
						<a href="#;" class="active" data-tab="Personal_Details">Personal
							Details</a> <img
							src="${pageContext.request.contextPath}/resources/images/exclamation.png"
							alt="" class="tickNdisimg">
					</div>
					<div class="a_halfBox">
						<a href="#;" data-tab="Banking_Details">Nominee Details</a>
						<img src="/fixed-deposit-application-form/resources/images/exclamation.png" alt="" class="tickNdisimg">
					</div>
				</div>
			</section>
			<input id="calc" type="hidden" value="" /> <input id="custType"
				type="hidden" value="" />
			<section class="a_fuuildSec">
				<div class="a_topBoxbord">
					<form id="panform" method="POST" enctype="multipart/form-data">
						<div class="a_halfBox fildPartShow" id="Personal_Details">

							<div class="a_ReInput tipstool ckycHide" id="okyxAdharSection">
								<label for="custCkycIDVD" id="ntbCustleble">CKYC ID</label> <label
									for="custCkycIDVD" id="etbCustleble">Customer ID</label> <input
									type="text" name="custCkycID" class="accountNumVD nomandetory"
									id="custCkycIDVD" autocomplete="off" readonly>
								<div class="errormsg">Please enter full name</div>
								<div class="p_clecnder">
									<i class="fa fa-lock"></i>
								</div><div class="custooltip">
        <p>You cannot edit this field.</p> 
    </div>
							</div>
<div class="a_ReInput" id="prefillSelectinput">
                        <label for="costomerId">Customer ID</label>
                        <select id="costomerId" name="costomerId">
                            <option value=""> </option>
                            
                        </select>
                        <i class="fas fa-caret-down"></i>
                        <div class="errormsg">Please select customer ID.</div>
                    </div> 
                    <div class="a_opplsLink"  id="prefillSelectoops">
                        <div class="a_oopsPlan">
                            <p>This customer ID belongs to your <span id="PoductName">FD</span> account maintained with us</p>
                        </div>
                    </div>
                    
                    <div class="a_ReInput" id="titleDivMain">
                                <label for="titleMain">Title</label>
                                <select id="titleMain" name="titleMain" >
                                    <option value="">Select title</option>
                                    <option value="Mr.">Mr.</option>
                                    <option value="Mrs.">Mrs.</option>
                                    <option value="Ms.">Ms.</option>
                                   
                                </select>
                                <i class="fas fa-caret-down"></i>
                                <div class="errormsg">Please select title</div>
                     </div>
                    
							<div class="a_ReInput tipstool">
								<label for="fullNamePD">Full name</label> <input type="text"
									name="fullName" class="FullNameVD nomandetory inspectletIgnore" id="fullNamePD"
									autocomplete="off" readonly>
								<div class="errormsg">Please enter full name</div>
								<div class="p_clecnder">
									<i class="fa fa-lock"></i>
									
								</div>
								<div class="custooltip">
        <p>You cannot edit this field.</p>
    </div>
							</div>
							<div class="a_ReInput tipstool">
								<label for="mobilePD">Mobile number</label> <input type="number"
									name="mobileNumber" class="mobileVD nomandetory inspectletIgnore" id="mobilePD"
									autocomplete="off" readonly>
								<div class="errormsg">Please enter mobile number</div>
								<div class="p_clecnder">
									<i class="fa fa-lock"></i></div>
									<div class="custooltip">
        <p>You cannot edit this field.</p>
    </div>
								
							</div>
							<div class="a_ReInput">
								<label for="emailPD">Email address</label> <input type="text"
									name="emailAddress" class="emailVD inspectletIgnore" id="emailPD"
									autocomplete="off">
								<div class="errormsg">Enter a valid email address</div>
							</div>
							<div class="a_ReInput tipstool">
								<label for="dobPD">Date of birth</label> <input type="text"
									name="dateOfBirth" class="datepickerVD nomandetory" id="dobPD"
									autocomplete="off" readonly>
								<div class="errormsg">Enter Date of birth</div>
								<div class="p_clecnder">
									<i class="fa fa-lock"></i>	</div>
									<div class="custooltip">
        <p>You cannot edit this field.</p> 
    </div>
							
							</div>
							<div class="a_ReInput" id="genderDiv">
                                <label for="gender">gender</label>
                                <select id="gender">
                                    <option value="">Select gender </option>
                                    <option value="M">Male</option>
                                    <option value="F">Female</option>
                                   
                                </select>
                                <i class="fas fa-caret-down"></i>
                                <div class="errormsg">Please select gender</div>
                            </div>
                            
							<div class="a_ReInput tipstool">
								<label for="panPD">PAN <img
									src="${pageContext.request.contextPath}/resources/images/information_black.png"
									alt="information" />
									<div class="p_fixdepositstultip">
										<div class="p_fixdepositBG">
											<p id="etbToolTipPan",>If there is a discrepancy in
												attached PAN you can either edit it or you can write to fd@bajajfinserv.in
												after the FD booking.A valid PAN number format is
												"AAAPANNNNA", where A - any letter, P - only letter P, N -
												any number.Maximum document upload size should be 1 MB.</p>

											<p id="ntbToolTipPan" style="display: none">All your
												details are fetched from CKYC records based on your PAN
												number</p>
											<div class="p_gotitbtn">
												<button class="gotitone">Got it</button>
											</div>
										</div>
									</div>
								</label> <input type="text" name="panCard" class="PanVD inspectletIgnore" id="panPD"
									autocomplete="off"> <!-- <label class="uploadDoc"
									id="panupLevel" style="display: none; cursor: pointer"
									for="panupload"> Upload pan<input type="file"
									name="file" class="UploadVD nomandetory" id="panupload"
									autocomplete="off" disabled></label> -->

								<div class="errormsg">Enter your PAN</div>
								<div class="p_clecnder" id="cKycpanLock" style="display: none">
									<i class="fa fa-lock"></i>
								</div>
								<!-- <div class="custooltip">
        <p>You cannot edit this field. If you wish to change details, you can do so later.</p> 
    </div> -->
							</div>

							<div class="a_ReInput tipstool address">
								<label for="addressPD">Address <img
									src="${pageContext.request.contextPath}/resources/images/information_black.png"
									alt="information">
									<div class="p_fixdepositstultip">
										<div class="p_fixdepositBG">

											<p>If your current address is different from the one
												mentioned here, you can have it changed after the booking
												has been made by writing to fd@bajajfinserv.in</p>
											<div class="p_gotitbtn">
												<button class="gotitone">Got it</button>
											</div>
										</div>
									</div>
								</label>
								<input type="text" name="address" class="AddressVD inspectletIgnore"
									id="addressPD" autocomplete="off" readonly>

								<div class="errormsg">Enter your address</div>
								<div class="p_clecnder">
									<i class="fa fa-lock"></i>
								</div>
								<div>
        <!-- <p>You cannot edit this field. If you wish to change details, you can do so later.</p>  -->
    </div>
							</div>


							<div class="a_ReInput tipstool">
								<label for="pinCodePD">PIN Code </label> <input type="number"
									name="pincode" class="PinCodeVD inspectletIgnore" id="pinCodePD"
									autocomplete="off">
								<div class="errormsg">Enter a valid 6-digit pin code</div>
								<div class="p_clecnder" id="cKycpinLock" style="display: none">
									<i class="fa fa-lock"></i>
								</div><div class="custooltip">
        <p>You cannot edit this field. If you wish to change details, you can do so later.</p> 
    </div>
							</div>
							
							<div class="a_ourCommAdd">
								<div class="a_afterMarurity">
									<strong>My communication address is same as permanent address</strong>
									<div class="a_swichTgl">
										<label for="checkBox_commAddress"> <input type="checkbox"
											value="No" id="checkBox_commAddress" checked > <i></i>
											<p class="yes">Yes</p>
											<p class="no">No</p>
										</label>
									</div>
								</div>
								<div class="a_ReInput">
									<label for="commAddress">Communication Address</label> <input
										type="text" class="AddressVD nomandetory" name="commAddress" id="commAddress"
										autocomplete="off">
									<div class="errormsg">'Enter your Communication Address</div>
								</div>
								<div class="a_ReInput">
									<label for="commPincode">Communication Pincode</label> <input
										type="number" class="PinCodeVD nomandetory" name="commPincode" id="commPincode"
										autocomplete="off">
									<div class="errormsg">'Enter your Communication Pincode</div>
								</div>
							</div>
							
							
							<div class="addfdrCheckBtn">
								<p>You will receive a digital copy of Fixed Deposit Receipt (FDR) on your email ID and mobile number. However, if you wish to receive physical copy then select Yes</p>
								<div class="a_swichTgl">
									<label> <input type="checkbox" 
										id="fdrCHeck" value="No"> <i></i>
										<p class="yes">Yes</p>
										<p class="no">No</p>
									</label>
								</div>

							</div>

						</div>
                   <div class="a_halfBox" id="Banking_Details">
							<div class="addNomineeBtn">
								<p>Do you wish to add a nominee ?</p>
								<div class="a_swichTgl">
									<label> <input type="checkbox" name="nomineeCheck"
										id="nomineeCheck" value="YES" checked> <i></i>
										<p class="yes">Yes</p>
										<p class="no">No</p>
									</label>
								</div>

							</div>
							<div class="a_radiodeposit_2">
							
							<div class="a_ReInput halfNominee" id="titleDiv">
                                <label for="title">Title</label>
                                <select id="title" name="title" >
                                    <option value="">Select title </option>
                                    <option value="Mr.">Mr.</option>
                                    <option value="Mrs.">Mrs.</option>
                                    <option value="Ms.">Ms.</option>
                                   
                                </select>
                                <i class="fas fa-caret-down"></i>
                                <div class="errormsg">Please select title</div>
                            </div>
							
								<div class="a_ReInput halfNominee">
									<label for="nomineeName">Nominee Full Name</label> <input
										type="text" class="NomineeNameVD inspectletIgnore" id="nomineeName"
										name="NomineeName" autocomplete="off">
									<div class="errormsg">Enter Nominee Full Name.</div>
								</div>

								<div class="a_ReInput datefield halfNominee2 ">
									<label for="sdpdateOfBirth">Nominee Date of Birth</label> <input
										type="number" class="datepickerVD get18age" id="nomineedd"
										placeholder="DD" autocomplete="off"
										aria-labelledby="sdpdateOfBirth">
									<div class="dateslace">/</div>
									<input type="number" class="datepickerVD get18age"
										id="nomineemm" placeholder="MM" autocomplete="off"
										aria-labelledby="sdpdateOfBirth">
									<div class="dateslace">/</div>
									<input type="number" class="datepickerVD nomineeYear get18age"
										id="nomineeyy" placeholder="YYYY" autocomplete="off"
										aria-labelledby="sdpdateOfBirth">
									<div class="borderDown"></div>
									<div class="errormsg">Enter Date of Birth.</div>
								</div>
								<div class="a_ReInput">
									<label for="sdprelationshipNominee">Relationship with
										nominee</label> <select id="sdprelationshipNominee" class="inspectletIgnore"
										name="nomineeRelation">
										<option value=""></option>
										<option value="SPOUSE">Spouse</option>
										<option value="SON">Son</option>
										<option value="DAUGHTER">Daughter</option>
										<option value="FATHER">Father</option>
										<option value="MOTHER">Mother</option>
										<option value="AUNT">Aunt</option>
										<option value="BROTHER">Brother</option>
										<option value="BROTHER IN LAW">Brother-In-Law</option>
										<option value="BUSINESS PARTNER">Business Partner</option>
										<option value="Cousin">Cousin</option>
										<option value="Daughter in Law">Daughter-In-Law</option>
										<option value="FATHER-IN-LAW">Father-In-Law</option>
										<option value="Friend">Friend</option>
										<option value="GRAND DAUGHTER">Grand Daughter</option>
										<option value="Grand Father">Grand Father</option>
										<option value="Grand Mother">Grand Mother</option>
										<option value="GRAND SON">Grand Son</option>
										<option value="MOTHER-IN-LAW">Mother-In-Law</option>
										<option value="NEPHEW">Nephew</option>
										<option value="NIECE">Niece</option>
										<option value="RELATIVE">Relative</option>
										<option value="Servant">Servant</option>
										<option value="SISTER">Sister</option>
										<option value="SISTER IN LAW">Sister-In-Law</option>
										<option value="Son - in Law">Son-In-Law</option>
										<option value="UNCLE">Uncle</option>
										<option value="Others">Others</option>

									</select> <i class="fas fa-caret-down"></i>
									<div class="errormsg">Please select relationship with nominee.</div>
								</div>
								<div class="a_primartAppli nomaddrs">
									<div class="a_CKBoxTgl">
										<label> <input type="checkbox" class="inspectletIgnore" checked
											name="nomineeAddresCheck" value="NO" id="nomineeAddrsCheck">
											<i></i>
										</label>
									</div> Nominee address is same as Primary Applicant's
										address
  
								</div>
								<div class="a_ReInput primartAdd">
								<label for="nomineepinCodePV">Nominee Pin Code</label> <input type="number"
									name="nomineePinCode" id="nomineepinCodePV" class="PinCodeVD nomandetory"
									autocomplete="off">
								<div class="errormsg">Enter your pincode</div>
							</div>
								<div class="a_ReInput primartAdd">
									<label for="scpnomineeAdd">Nominee Address</label> <input
										type="text" class="AddressVD nomandetory inspectletIgnore" id="scpnomineeAdd"
										name="nomineeAddress" autocomplete="off">
									<div class="errormsg">Enter Nominee Address.</div>
								</div>
								
								<div class="a_gardianDet">
									<div class="a_ReInput halfNominee">
										<label for="sdpguardianName">GUARDIAN NAME</label> <input
											type="text" class="NomineeNameVD nomandetory inspectletIgnore"
											id="sdpguardianName" name="guardianName" autocomplete="off">
										<div class="errormsg">Enter Guardian name.</div>
									</div>
									<div class="a_ReInput  halfNominee2">
										<label for="gaurdianNomineeAge">Guardian Age</label>
										 <input type="number" name="gaurdianAge" class="gaurdianAge nomandetory inspectletIgnore"
											id="gaurdianNomineeAge"  maxlength="2" autocomplete="off"
											aria-labelledby="gaurdianNomineeAge">
										    
										<div class="errormsg">Enter Guardian Age.</div>
									</div>
									<div class="a_ReInput">
										<label for="sdprelationshipNominee2">Guardian
											Relationship with minor</label> <select class="nomandetory inspectletIgnore"
											id="sdprelationshipNominee2"
											name="relationshipNomineeGuardian">
											<option value=""></option>
											<option value="AUNT">Aunt</option>
											<option value="BROTHER">Brother</option>
											<option value="BROTHER IN LAW">Brother-In-Law</option>
											<option value="BUSINESS PARTNER">Business Partner</option>
											<option value="Cousin">Cousin</option>
											<option value="DAUGHTER">Daughter</option>
											<option value="Daughter in Law">Daughter-In-Law</option>
											<option value="FATHER">Father</option>
											<option value="FATHER-IN-LAW">Father-In-Law</option>
											<option value="Friend">Friend</option>
											<option value="GRAND DAUGHTER">Grand Daughter</option>
											<option value="Grand Father">Grand Father</option>
											<option value="Grand Mother">Grand Mother</option>
											<option value="GRAND SON">Grand Son</option>
											<option value="MOTHER-IN-LAW">Mother-In-Law</option>
											<option value="MOTHER">Mother</option>
											<option value="NEPHEW">Nephew</option>
											<option value="NIECE">Niece</option>
											<option value="RELATIVE">Relative</option>
											<option value="Servant">Servant</option>
											<option value="SISTER">Sister</option>
											<option value="SISTER IN LAW">Sister-In-Law</option>
											<option value="Son - in Law">Son-In-Law</option>
											<option value="SON">Son</option>
											<option value="SPOUSE">Spouse</option>
											<option value="UNCLE">Uncle</option>
											<option value="Others">Others</option>

										</select> <i class="fas fa-caret-down"></i>
										<div class="errormsg">Please select Relationship with minor.</div>
									</div>
									<div class="a_ReInput ">
								<label for="guardiunPincode">GUARDIAN PIN CODE</label> <input type="number"
									name="GuardianpinCode" id="guardiunPincode" class="PinCodeVD nomandetory"
									autocomplete="off">
								<div class="errormsg">Enter your pincode</div>
							</div>
									<div class="a_ReInput">
										<label for="sdpguardianAddress">Guardian Address</label> <input
											type="text" class="AddressVD nomandetory inspectletIgnore"
											id="sdpguardianAddress" name="guardianAddress"
											autocomplete="off">
										<div class="errormsg">Enter Guardian Address.</div>
									</div>


								</div>
							</div>

							<div class="a_blueBtnPart">
							<!-- <div class="allfieldvali" style="display: none;">Please fill all fields. All fields are mandatory.</div>
								 --><button class="submitBTN validBtn">
									CONTINUE
									
									<div class="fd_sdp_loder"></div>
								</button>
							</div>
						</div>
					</form>
				</div>
			</section>


		</div>


		<div class="a_part_5">
			<%--        <section class="p_fdheadtitle">
    <div class="p_fdtextname"><a href="#;"><i class="fa fa-arrow-left"></i></a> 
    <img   src="${pageContext.request.contextPath}/resources/images/logo.png" alt="logo" class="logoimg" >
    <p>Choose your investment details</p></div>
</section> --%>

			<section class="p_fdheadtitle">
				<div class="p_fdtextname">
					<h1>
						<p>Choose your investment details</p>
						<img
							src="${pageContext.request.contextPath}/resources/images/allicon-new.png"
							alt="logo" class="logoimg">
					</h1>
				</div>
			</section>

			<section class="a_fixeddepositSec">
			<input type="hidden" name="hiddenPayOutTypeCheck" id="hiddenPayOutTypeCheck" >
				<form id="fdNominee">
					<div class="a_leftSide">
						<div class="mainOneBlock">
							<div class="a_ReInput_sapce">
								<div class="a_ReInput2 a_ReInput">
									<label for="fdAmount">DEPOSIT AMOUNT <a href="#;">View
											interest rates</a></label>
									<div class="j_countCd">
										<i class="fas fa-rupee"></i>
									</div>
									<input type="text" name="depositAmount" class="sliderAmunt"
										id="fdAmount" placeholder="Enter amount" autocomplete="off"
										value="">
									<div class="errormsg">Please enter the amount</div>
									<a href="#;" class="infored"><img
										src="${pageContext.request.contextPath}/resources/images/errorRed.png"
										alt="errorRed"></a>
									<div class="inputDesc">
										Min <i class="fas fa-rupee"></i> 25,000 & Max <i
											class="fas fa-rupee"></i> 5,00,00,000
									</div>

								</div>
							</div>
							<div class="a_amountRange"></div>
							<div class="sliderBotDesc statusgrip">
								<div class="a_satausfild2">
									<img
										src="${pageContext.request.contextPath}/resources/images/idea.png"
										alt="idea">
									<p>That&#39;s a great start!</p>
									<p>Invest more to earn to more.</p>
								</div>
								<div class="a_greensataus2">
									<img
										src="${pageContext.request.contextPath}/resources/images/like.png"
										alt="like">
									<p>Great choice!</p>
									<p class="a_greatCamnt">
										By choosing this tenor, you will get maximum returns of Rs. <i
											class="fas fa-rupee"></i> on your deposit
									</p>
								</div>
							</div>
							<div class="a_opplsLink statusgrip">
								<div class="a_oopsPlan">
									<img
										src="${pageContext.request.contextPath}/resources/images/error.png"
										alt="error">
									<p>
										Oops! You have entered an amount less than <i
											class="fas fa-rupee"></i> 25,000
									</p>
									<p>But you can still continue by choosing our Systematic
										Deposit Plan option below.</p>
								</div>
								<a href="#;"> CONTINUE WITH SYSTEMATIC DEPOSIT PLAN <i
									class="fas fa-chevron-right"></i>
								</a>
							</div>
							<div class="a_mobborder"></div>
						</div>
						<div class="mainOneBlock">
							<div class="a_titlePart">
								<p>TENOR (months)</p>
								<a href="#;"> <img
									src="${pageContext.request.contextPath}/resources/images/information.png"
									alt="information">
								</a>
								<div class="a_gotItInfo">

									<p>Choosing a longer tenor helps you earn higher interest</p>
									<a href="#;">GOT IT</a>
								</div>
							</div>

							<div class="a_selectblock a_ReInput">
								<div class="row4">
									<div class="a_part20 tenorPeriod">
										<label for="tenor1"> <input type="radio" name="tenor"
											value="60" id="tenor1" >
											<p>60</p> <i></i> <strong class="fas fa-star"></strong>
										</label>
									</div>
									<div class="a_part20 tenorPeriod">
										<label for="tenor2"> <input type="radio" name="tenor"
											id="tenor2" value="48">
											<p>48</p> <i></i>
										</label>
									</div>
									<div class="a_part20 tenorPeriod">
										<label for="tenor3"> <input type="radio" name="tenor"
											id="tenor3" value="36">
											<p>36</p> <i></i>
										</label>
									</div>
									<div class="a_part20 tenorPeriod">
										<label for="tenor4"> <input type="radio" name="tenor"
											id="tenor4" value="24">
											<p>24</p> <i></i>
										</label>
									</div>
									<div class="a_part20 tenorPeriod">
										<label for="tenor5"> <input type="radio" name="tenor"
											id="tenor5" value="12">
											<p>12</p> <i></i>
										</label>
									</div>
									<div class="a_part20 custmTenor tenorPeriod">
										<label for="tenor6"> <input type="radio" name="tenor"
											id="tenor6" value="Custom">
											<p>Custom</p> <i></i>
										</label>
									</div>
									
									
									<div class="errormsg">Please select tenor</div>
								</div>
							</div>
							<div class="a_monthCustom">
								<p>OR</p>
								<div class="a_ReInput2 a_ReInput">
									<select name="fdCustomTenor" id="fdCustomTenor"  class="customTenor nomandetory inspectletIgnore">
    <option value="">Select custom tenor</option>
	<option value="12">12</option>
    <option value="13">13</option>
    <option value="14">14</option>
    <option value="15">15</option>
	<option value="16">16</option>
    <option value="17">17</option>
    <option value="18">18</option>
    <option value="19">19</option>
	<option value="20">20</option>
    <option value="21">21</option>
    <option value="22">22</option>
    <option value="23">23</option>
	<option value="24">24</option>
    <option value="25">25</option>
    <option value="26">26</option>
    <option value="27">27</option>
	<option value="28">28</option>
    <option value="29">29</option>
    <option value="30">30</option>
    <option value="31">31</option>
	<option value="32">32</option>
    <option value="33">33</option>
    <option value="34">34</option>
    <option value="35">35</option>
	<option value="36">36</option>
    <option value="37">37</option>
    <option value="38">38</option>
    <option value="39">39</option>
	<option value="40">40</option>
    <option value="41">41</option>
    <option value="42">42</option>
    <option value="43">43</option>
	<option value="44">44</option>
    <option value="45">45</option>
    <option value="46">46</option>
    <option value="47">47</option>
	<option value="48">48</option>
    <option value="49">49</option>
    <option value="50">50</option>
    <option value="51">51</option>
	<option value="52">52</option>
    <option value="53">53</option>
    <option value="54">54</option>
    <option value="55">55</option>
	<option value="56">56</option>
    <option value="57">57</option>
    <option value="58">58</option>
    <option value="59">59</option>
	 <option value="60">60</option>
  </select>
										
									<div class="errormsg">Select tenor in months (min - 12, max - 60)</div>
</div>
							</div>

							<div class="sliderBotDesc statusgrip">
								<div class="a_satausfild2">
									<img
										src="${pageContext.request.contextPath}/resources/images/idea.png"
										alt="ide">
									<p>More is better!</p>
									<p>Stay invested for 60 months to get maximum returns</p>
								</div>
								<div class="a_greensataus2">
									<img
										src="${pageContext.request.contextPath}/resources/images/like.png"
										alt="like">
									<p>Great choice!</p>
									<p class="a_fdTenorgreatChose">
										By choosing interest payout on maturity, you're getting returns of Rs. <i
											class="fas fa-rupee"></i> on your deposit
									</p>
								</div>
							</div>
							<div class="a_mobborder"></div>
						</div>

						<div class="mainOneBlock">
							<div class="a_titlePart">
								<p>INTEREST PAYOUT</p>
								<a href="#;"> <img
									src="${pageContext.request.contextPath}/resources/images/information.png"
									alt="information">
								</a>
								<div class="a_gotItInfo">

									<p>Please select or enter the interest payout mode. You
										will receive interest as per the selected option.</p>
									<a href="#;">GOT IT</a>
								</div>
							</div>

							<div class="a_selectblock">
								<div class="row4">
									<div class="a_part20">
										<label for="payOut1"> <input type="radio" class="inspectletIgnore"
											name="payOut" id="payOut1" value="OnMaturity" checked>
											<p>On Maturity</p> <i></i> <strong class="fas fa-star"></strong>
										</label>
									</div>
									<div class="a_part20">
										<label for="payOut2"> <input type="radio" class="inspectletIgnore"
											name="payOut" id="payOut2" value="Monthly">
											<p>Monthly</p> <i></i>
										</label>
									</div>
									<div class="a_part20">
										<label for="payOut3"> <input type="radio" class="inspectletIgnore"
											name="payOut" id="payOut3" value="Quarterly">
											<p>Quarterly</p> <i></i>
										</label>
									</div>
									<div class="a_part20">
										<label for="payOut4"> <input type="radio" class="inspectletIgnore"
											name="payOut" id="payOut4" value="HalfYearly">
											<p>Half Yearly</p> <i></i>
										</label>
									</div>
									<div class="a_part20">
										<label for="payOut5"> <input type="radio" class="inspectletIgnore"
											name="payOut" id="payOut5" value="Annually">
											<p>Annually</p> <i></i>
										</label>
									</div>
								</div>
							</div>

							<div class="sliderBotDesc statusgrip">
								<div class="a_satausfild2">
									<img
										src="${pageContext.request.contextPath}/resources/images/idea.png"
										alt="idea">

									<p>Get maximum returns by choosing 'On Maturity'</p>
								</div>
								<div class="a_greensataus2">
									<img
										src="${pageContext.request.contextPath}/resources/images/like.png"
										alt="like">
									<p>Well done!</p>
									<p class="a_fdTenorgreatChose">By choosing interest payout
										on maturity, you are getting the highest returns on your
										deposit</p>
								</div>
							</div>
							<div class="a_mobborder"></div>
						</div>
						<div class="a_growAve">
							<i class="fas fa-star"></i>
							<p id="growFDamt">Recommended for maximum savings</p>
						</div>
						<div class="a_renewFDeposite" style="display: none;">
							<div class="a_afterMarurity">
								Do you wish to renew this FD at maturity ?
								<div class="a_swichTgl">
									<label for="fdRenew"> <input type="checkbox"
										id="fdRenew" checked> <i></i>
										<p class="yes">Yes</p>
										<p class="no">No</p>
									</label>
								</div>
							</div>
							<div class="a_radiodeposit">
								<label for="principal"> <input type="radio" class="inspectletIgnore"
									name="princiAmount" id="principal" value="principal"> <i></i>
									<p>Only principal amount</p> <strong class="pDeposit"><i
										class="fas fa-rupee"></i></strong>
								</label> <label for="principalAndInterest"> <input type="radio" class="inspectletIgnore"
									name="princiAmount" id="principalAndInterest"
									value="principalAndInterest" checked> <i></i>
									<p>Principal + Interest amount</p> <strong class="totalAmount"><i
										class="fas fa-rupee"></i></strong>
								</label>
							</div>
						</div>
					</div>
					<div class="a_rightSide">
						<div class="a_investmentTbl">
							<h2>
								<B>YOUR DEPOSIT SUMMARY</B> 
							</h2>
							<ul class="">
								<li><p>Interest rate per annum</p>
									<p id="FDInterestRate"></p></li>
								<li><p>Maturity month</p>
									<p id="FDmaturityDate"></p></li>
								<li><p>Total deposit amount</p>
									<p class="pDeposit">
										<i class="fas fa-rupee"></i>
									</p></li>
								<li><p>
										Interest amount* <i class="intPay"></i>
									</p>
									<p id="InterestFD">
										<i class="fas fa-rupee"></i>
									</p></li>
								<li><p>Total payout</p>
									<p class="zoom" id="FDmaturityAmnt">
										<i class="fas fa-rupee"></i>
									</p></li>
							</ul>
							<p></p>
							<p>Disclaimer: Actual returns may vary slightly if deposit enters into a leap year</p>
						</div>
						<div class="a_renewFDeposite">
							<div class="a_afterMarurity">
								<strong>Banking Details</strong>

							</div>
<div class="a_ReInput">
								<label for="bankName">Select Bank Name <a href="#;"
									class="a_viewPament">View available payment modes</a></label> <select
									id="fdbankName" class="bankNameSelect BankVD"  name="fdbankName">
									<option class="noBankSelected" value="">Bank Name</option>

<option class="bothBankOption" value="Axis Bank">AXIS BANK</option>
<option class="bothBankOption" value="HDFC Bank">HDFC BANK</option>
<option class="bothBankOption" value="ICICI Bank">ICICI BANK LIMITED</option>
<option class="bothBankOption" value="162">KOTAK MAHINDRA BANK LIMITED</option>
<option class="bothBankOption" value="State Bank of India">STATE BANK OF INDIA</option>
<option class="upihide" value="ALB">ALLAHABAD BANK</option>
<option class="bothBankOption" value="Union Bank of India">ANDHRA BANK</option>
<option class="bothBankOption" value="Bandhan Bank">BANDHAN BANK LIMITED</option>
<option class="bothBankOption" value="Bank of Baroda Retail">BANK OF BARODA</option>
<option class="bothBankOption" value="Bank of Maharashtra">BANK OF MAHARASHTRA</option>
<option class="netBankShow" value="Canara Bank">CANARA BANK</option>
<option class="bothBankOption" value="Central Bank of India">CENTRAL BANK OF INDIA</option>
<option class="bothBankOption" value="City Union Bank">CITY UNION BANK LIMITED</option>
<option class="bothBankOption" value="Union Bank of India">CORPORATION BANK</option>
<option class="bothBankOption" value="Bank of Baroda Retail">DENA BANK</option>
<option class="bothBankOption" value="Dhanlaxmi Bank">DHANALAKSHMI BANK</option>
<option class="bothBankOption" value="IDB">IDBI BANK</option>
<option class="bothBankOption" value="IDN">IDFC First Bank Ltd</option>
<option class="bothBankOption" value="IDS">INDUSIND BANK</option>
<option class="bothBankOption" value="Karur Vysya Bank">KARUR VYSYA BANK</option>
<option class="bothBankOption" value="Lakshmi Vilas Bank">LAXMI VILAS BANK</option>
<option class="bothBankOption" value="Punjab National Bank [Retail]">PUNJAB NATIONAL BANK</option>
<option class="bothBankOption" value="SIB">SOUTH INDIAN BANK</option>
<option class="netBankShow" value="Canara Bank">SYNDICATE BANK</option>
<option class="bothBankOption" value="Union Bank of India">UNION BANK OF INDIA</option>
<option class="upihide" value="ABHYUDAYA COOPERATIVE BANK LIMITED">ABHYUDAYA COOPERATIVE BANK LIMITED</option>
<option class="upihide" value="AHMEDABAD MERCANTILE COOPERATIVE BANK">AHMEDABAD MERCANTILE COOPERATIVE BANK</option>
<option class="upihide" value="AHMEDNAGAR MERCHANTS CO-OP BANK LTD">AHMEDNAGAR MERCHANTS CO-OP BANK LTD</option>
<option class="upihide" value="APNA SAHAKARI BANK LIMITED">APNA SAHAKARI BANK LIMITED</option>
<option class="bothBankOption" value="AU Small Finance Bank">AU SMALL FINANCE BANK LIMITED</option>
<option class="upihide" value="THE BARAMATI SAHAKARI BANK LTD">THE BARAMATI SAHAKARI BANK LTD</option>
<option class="upihide" value="BHAGINI NIVEDITA SAHAKARI BANK LTD PUNE">BHAGINI NIVEDITA SAHAKARI BANK LTD PUNE</option>
<option class="upihide" value="CITI BANK">CITI BANK</option>
<option class="upihide" value="CAPITAL SMALL FINANCE BANK LIMITED">CAPITAL SMALL FINANCE BANK LIMITED</option>
<option class="upihide" value="DEUSTCHE BANK">DEUSTCHE BANK</option>
<option class="bothBankOption" value="Equitas Small finance bank">EQUITAS SMALL FINANCE BANK LIMITED</option>
<option class="upihide" value="ESAF SMALL FINANCE BANK LIMITED">ESAF SMALL FINANCE BANK LIMITED</option>
<option class="bothBankOption" value="FEDERAL BANK">FEDERAL BANK</option>
<option class="upihide" value="FINCARE SMALL FINANCE BANK LTD">FINCARE SMALL FINANCE BANK LTD</option>
<option class="upihide" value="THE GADCHIROLI DISTRICT CENTRAL COOPERATIVE BANK LIMITED">THE GADCHIROLI DISTRICT CENTRAL COOPERATIVE BANK LIMITED</option>
<option class="upihide" value="THE GUJARAT STATE COOPERATIVE BANK LIMITED">THE GUJARAT STATE COOPERATIVE BANK LIMITED</option>
<option class="upihide" value="THE HASTI COOP BANK LTD">THE HASTI COOP BANK LTD</option>
<option class="upihide" value="HSBC BANK">HSBC BANK</option>
<option class="upihide" value="INDIAN BANK">INDIAN BANK</option>
<option class="upihide" value="KALLAPPANNA AWADE ICHALKARANJI JANATA SAHAKARI BANK LIMITED">KALLAPPANNA AWADE ICHALKARANJI JANATA SAHAKARI BANK LIMITED</option>
<option class="upihide" value="KARNATAKA BANK LIMITED">KARNATAKA BANK LIMITED</option>
<option class="upihide" value="KALUPUR COMMERCIAL COOPERATIVE BANK">KALUPUR COMMERCIAL COOPERATIVE BANK</option>
<option class="upihide" value="KALYAN JANATA SAHAKARI BANK">KALYAN JANATA SAHAKARI BANK</option>
<option class="upihide" value="KARNATAKA VIKAS GRAMEENA BANK">KARNATAKA VIKAS GRAMEENA BANK</option>
<option class="upihide" value="Maharashtra Gramin Bank">Maharashtra Gramin Bank</option>
<option class="upihide" value="The Muslim Co-operative Bank Ltd">The Muslim Co-operative Bank Ltd</option>
<option class="upihide" value="THE MEHSANA URBAN COOPERATIVE BANK">THE MEHSANA URBAN COOPERATIVE BANK</option>
<option class="upihide" value="NUTAN NAGARIK SAHAKARI BANK LIMITED">NUTAN NAGARIK SAHAKARI BANK LIMITED</option>
<option class="bothBankOption" value="NKGSB Co-op Bank Ltd">NKGSB COOPERATIVE BANK LIMITED</option>
<option class="upihide" value="ORIENTAL BANK OF COMMERCE">ORIENTAL BANK OF COMMERCE</option>
<option class="upihide" value="G P PARSIK BANK">G P PARSIK BANK</option>
<option class="upihide" value="RBL Bank Limited">RBL Bank Limited</option>
<option class="upihide" value="RAJASTHAN MARUDHARA GRAMIN BANK">RAJASTHAN MARUDHARA GRAMIN BANK</option>
<option class="upihide" value="RAJKOT NAGRIK SAHAKARI BANK LIMITED">RAJKOT NAGRIK SAHAKARI BANK LIMITED</option>
<option class="bothBankOption" value="Standard Chartered Bank">STANDARD CHARTERED BANK</option>
<option class="upihide" value="SARASWAT COOPERATIVE BANK LIMITED">SARASWAT COOPERATIVE BANK LIMITED</option>
<option class="bothBankOption" value="Suryoday Small Finance Bank Ltd">SURYODAY SMALL FINANCE BANK LIMITED</option>
<option class="upihide" value="Suco Souharda Sahakari Bank Ltd">Suco Souharda Sahakari Bank Ltd</option>
<option class="upihide" value="SUTEX COOPERATIVE BANK LIMITED">SUTEX COOPERATIVE BANK LIMITED</option>
<option class="upihide" value="Shri Veershaiv Co Op Bank Ltd">Shri Veershaiv Co Op Bank Ltd</option>
<option class="upihide" value="THE THANE BHARAT SAHAKARI BANK LIMITED">THE THANE BHARAT SAHAKARI BANK LIMITED</option>
<option class="bothBankOption" value="UCO Bank">UCO BANK</option>
<option class="upihide" value="Ujjivan Small Finance Bank Limited">Ujjivan Small Finance Bank Limited</option>
<option class="upihide" value="UNITED BANK OF INDIA">UNITED BANK OF INDIA</option>
<option class="upihide" value="THE UDAIPUR URBAN CO OPERATIVE BANK LTD">THE UDAIPUR URBAN CO OPERATIVE BANK LTD</option>
<option class="upihide" value="VIJAYA BANK">VIJAYA BANK</option>
<option class="upihide" value="THE VISHWESHWAR SAHAKARI BANK LIMITED">THE VISHWESHWAR SAHAKARI BANK LIMITED</option>
<option class="bothBankOption" value="YES BANK">YES BANK</option>
<option class="upihide" value="Shamrao Vithal Bank">Shamrao vithal bank</option>


</select>
							
								<div class="errormsg">Select Bank Name</div>
								<i class="fas fa-caret-down"></i>
							</div>
							<div class="a_opplsLink statusgripBank">
								<div class="a_oopsPlan">
									<img src="${pageContext.request.contextPath}/resources/images/error.png" alt="">
									<p>Oops! Only UPI payment mode is available for the bank selected and UPI payments do not support transaction amounts greater than 1 lakh in a day. Hence, please select another bank from the list which has Netbanking payment mode available. You can view available payment modes <a href="#;" >here</a></p>
								</div>
							</div>
							<div class="bnkMethod">
								<p>Choose payment mode</p>
								<div class="a_radiodeposit">
									<label id="fdNetBankCheck"> <input type="radio" class="inspectletIgnore" name="banking_det_1"
										value="Netbanking"> <i></i>
										<p>Netbanking</p>
									</label> <label id="fdupiCheck"> <input type="radio" class="inspectletIgnore" id="fdnetBank" name="banking_det_1"
										value="UPI"> <i></i>
										<p>UPI</p>
									</label>
								</div>
								<div class="errormsg">Please select payment mode</div>
							</div>
<div class="a_radiodeposit_2">
								<div class="a_ReInput">
									<label for="accountNumber">Account Number <i>(Should
											be in the name of FD applicant)</i><img src="${pageContext.request.contextPath}/resources/images/information_black.png" alt="">
                                        <div class="p_fixdepositstultip">
                                            <div class="p_fixdepositBG">
                                                <p>Please enter the account number on the name of FD applicant. On maturity, the final amount will be credited to this bank account. You can change your bank account later by submitting a cancelled cheque to us.</p>
                                                <div class="p_gotitbtn"><button class="gotitone">Got it</button></div>
                                            </div>
                                        </div>
											
											</label>
											 <input type="number"
										class="accountNumVD inspectletIgnore" id="fdaccountNumber" name="accountNumber"  autocomplete="off">
									<div class="errormsg">Enter your account number</div>
								</div>
								<div class="a_ReInput">
									<label for="ifscCode">IFSC</label> <input type="text"
										class="ifscCodeVD ifscCheck inspectletIgnore" name="ifscCode" id="fdifscCode" autocomplete="off">
									<div class="errormsg">Please enter IFSC code</div>
								</div>
<p class="more">
In case of any mismatch in bank account number / account holder name from where FD funds are received, refund shall be processed in 7 working days without any interest
</p>				
							</div>
							<div class="a_blueBtnPart">
								<button class="submitBTN validBtn">PROCEED TO PAY
								  <div class="fd_sdp_loder"></div>
								
								</button>
							</div>
				</form>
		</div>

	</div>
	</section>


	<div class="FDcal"></div>

	</div>

	<div class="a_part_6">
		<%--         <section class="p_fdheadtitle">
    <div class="p_fdtextname"><a href="#;"><i class="fa fa-arrow-left"></i></a> 
    <img   src="${pageContext.request.contextPath}/resources/images/logo.png" alt="logo" class="logoimg" >
    <p>Choose your investment details</p></div>
</section> --%>

		<section class="p_fdheadtitle">
			<div class="p_fdtextname">
				<h1>
					<p>Choose your investment details</p>
					<img
						src="${pageContext.request.contextPath}/resources/images/allicon-new.png"
						alt="logo" class="logoimg">
				</h1>
			</div>
		</section>


		<section class="a_fixeddepositSec">
			<form id="sdpnomeeni">
				<div class="a_leftSide">
					<div class="mainOneBlock">
						<div class="a_ReInput_sapce">
							<div class="a_ReInput2 a_ReInput">
								<label for="sdpAmount">MONTHLY DEPOSIT AMOUNT
								<a href="#;">View interest rates</a></label>
								<div class="j_countCd">
									<i class="fas fa-rupee"></i>
								</div>
								<input type="text" name="depositAmount" class="sliderAmunt_2"
									id="sdpAmount" autocomplete="off" placeholder="Enter amount"
									value="">
								<div class="errormsg">Please enter the amount</div>
								<a href="#;" class="infored"><img
									src="${pageContext.request.contextPath}/resources/images/errorRed.png"
									alt="errorRed"></a>
								<div class="inputDesc">
									Min <i class="fas fa-rupee"></i> 5,000
								</div>
							</div>
						</div>
						<div class="a_amountRange"></div>
						

						<div class="a_mobborder"></div>
					</div>
	<!-- Maturity SCHEME PART START HERE -->
					<div class="mainOneBlock mScheme">
						<div class="a_titlePart">
							<p>Choose Maturity SCHEME</p>

						</div>
						<div class="a_radiodeposit">
							<label> <input type="radio" name="maturitysdheme" value="Single Maturity" checked>
								<i></i>
								<p>Single Maturity</p> <a href="#;"> <img
									src="${pageContext.request.contextPath}/resources/images/information.png"
									alt="">
							</a>
								<div class="a_gotItInfo">
									<h4>Interest payout</h4>
									<p>Choose this, if you would prefer a one-time maturity for all your deposits</p>
									<a href="#;">GOT IT</a>
								</div>
							</label> <label> <input type="radio" name="maturitysdheme" value="Monthly Maturity">
								<i></i>
								<p>Monthly Maturity</p> <a href="#;"> <img
									src="${pageContext.request.contextPath}/resources/images/information.png"
									alt="">
							</a>
								<div class="a_gotItInfo">
									<h4>Interest payout</h4>
									<p>Choose this, if you would prefer maturity on monthly basis for your deposits</p>
									<a href="#;">GOT IT</a>
								</div>
							</label>
						</div>
					</div>
					<!-- Maturity SCHEME PART END HERE -->
					
					<div class="mainOneBlock">
						<div class="a_titlePart">
							<p id="tenorText">TENOR (months)</p>
							<a href="#;"> <img class="depo2"
								src="${pageContext.request.contextPath}/resources/images/information.png"
								alt="info">
							</a>
							<div class="a_gotItInfo">
								<p>Choose the frequency of your interest payouts as per your
									convenience</p>
								<a href="#;">GOT IT</a>
							</div>
						</div>

						<div class="a_selectblock a_ReInput">
							<div class="row4">
								<div class="a_part20 sdptenorPeriod">
									<label for="sdptenor1"> <input type="radio" class="inspectletIgnore"
										name="sdptenor" id="sdptenor1" value="60">
										<p>60</p> <i></i> <strong class="fas fa-star"></strong>
									</label>
								</div>
								<div class="a_part20 sdptenorPeriod">
									<label for="sdptenor2"> <input type="radio" class="inspectletIgnore"
										name="sdptenor" id="sdptenor2" value="48">
										<p>48</p> <i></i>
									</label>
								</div>
								<div class="a_part20 sdptenorPeriod">
									<label for="sdptenor3"> <input type="radio" class="inspectletIgnore"
										name="sdptenor" id="sdptenor3" value="36">
										<p>36</p> <i></i>
									</label>
								</div>
								<div class="a_part20 sdptenorPeriod">
									<label for="sdptenor4"> <input type="radio" class="inspectletIgnore"
										name="sdptenor" id="sdptenor4" value="24">
										<p>24</p> <i></i>
									</label>
								</div>
								<div class="a_part20 onlyMonth">
                                    <label for="sdptenor5">
                                        <input type="radio" class="inspectletIgnore" name="sdptenor" id="sdptenor5" value="12">
                                        <p>12</p>
                                        <i></i>
                                    </label>
                                </div>
                                <div class="a_part20 custmTenor onlyMonth">
                                    <label for="sdptenor6">
                                        <input type="radio" class="inspectletIgnore" name="sdptenor" id="sdptenor6" value="Custom">
                                        <p>Custom</p>
                                        <i></i>
                                    </label>
                                </div>
                                
                                <div class="a_part20 onlySingal">
                                    <label for="sdptenor7">
                                        <input type="radio" class="inspectletIgnore" name="sdptenor" id="sdptenor7" value="19">
                                        <p>19</p>
                                        <i></i>
                                    </label>
                                </div>
                                <div class="a_part20 custmTenor onlySingal">
                                    <label for="sdptenor8">
                                        <input type="radio" class="inspectletIgnore" name="sdptenor" id="sdptenor8" value="Custom">
                                        <p>Custom</p>
                                        <i></i>
                                    </label>
                                </div>
									<div class="errormsg">Enter tenor between 19 to 60 for single maturity and 12 to 60 for monthly</div>
									
							</div>
						</div>
						<div class="a_monthCustom">
							<p>OR</p>
							<div class="a_ReInput2 a_ReInput">
								<input type="number" class="customTenor_2 nomandetory inspectletIgnore"
									id="sdpCustomTenor" autocomplete="off"
									placeholder="Enter months">
								<div class="errormsg">Enter your tenor</div>
								<div class="inputDesc onlyMonth">Minimum tenor 12 months & Maximum
									tenor 60 months</div>
								<div class="inputDesc onlySingal">Minimum tenor 19 months & Maximum
									tenor 60 months</div>
							</div>
						</div>

						
						<div class="a_mobborder"></div>
					</div>
					<div class="mainOneBlock">
						<div class="a_ReInput_sapce">

							<div class="a_ReInput2">

								<div class="a_titlePart">
									<p>NUMBER OF SUBSEQUENT DEPOSITS</p>
									<a href="#;"> <img class="depo1"
										src="${pageContext.request.contextPath}/resources/images/information.png"
										alt="informaton">
									</a>
									<div class="a_gotItInfo">

										<p>Select the number of subsequent deposits you wish to
											make. This would be over and above the first deposit you will
											be making now</p>
										<a href="#;">GOT IT</a>
									</div>
								</div>
								<div class="a_opplsLink">
                                <div class="a_oopsPlan">
                                    <p>This is excluding the 1st deposit you would be paying now through Netbanking/UPI</p>
                                </div>
                            </div>
								<select class="p_relation" id="sdpDeposit">
                                    <option value="" selected disabled>Select</option>
                                    <!-- <option class="singleMaturitySelect" selected>5</option> -->
                                    <option class="select24 select36 select48 select60" selected>6</option>
                                    <option>7</option>
                                    <option>8</option>
                                    <option>9</option>
                                    <option>10</option>
                                    <option>11</option>
                                    <option class="select36 select48 select60">12</option>
                                    <option>13</option>
                                    <option>14</option>
                                    <option>15</option>
                                    <option>16</option>
                                    <option>17</option>
                                    <option>18</option>
                                    <option>19</option>
                                    <option>20</option>
                                    <option>21</option>
                                    <option>22</option>
                                    <option>23</option>
                                    <option value="24" class="select48 select60">24</option>
                                    <option>25</option>
                                    <option>26</option>
                                    <option>27</option>
                                    <option>28</option>
                                    <option>29</option>
                                    <option>30</option>
                                    <option>31</option>
                                    <option>32</option>
                                    <option>33</option>
                                    <option>34</option>
                                    <option>35</option>
                                    <option class="select60">36</option>
                                    <option>37</option>
                                    <option>38</option>
                                    <option>39</option>
                                    <option>40</option>
                                    <option>41</option>
                                    <option>42</option>
                                    <option>43</option>
                                    <option>44</option>
                                    <option>45</option>
                                    <option>46</option>
                                    <option>47</option>
                                    <option>48</option>
                                </select>
								<i class="fas fa-caret-down"></i>

							</div>
						</div>

						<div class="a_mobborder"></div>
					</div>
					<!-- <div class="a_growAve">
						<i class="fas fa-star"></i>
						<p id="growFDamt_2">Recommended for maximum savings</p>
					</div> -->


					<div class="a_renewFDeposite">
						<div class="mainOneBlock">
							<div class="a_titlePart">
								<p>DATE OF EACH SUBSEQUENT DEPOSIT</p>
								<a href="#;"> <img class="depo3"
									src="${pageContext.request.contextPath}/resources/images/information.png"
									alt="information">
								</a>
								<div class="a_gotItInfo">

									<p>Your deposit amount will be deducted on this date every
										month</p>
									<a href="#;">GOT IT</a>
								</div>
							</div>

							<div class="a_selectblock">
								<div class="row4">
									<div class="a_part20">
										<label for="3rd"> <input type="radio" class="inspectletIgnore"
											name="sdpDeposite" id="3rd" value="3rd" checked>
											<p>3rd</p> <i></i>
										</label>
									</div>
									<div class="a_part20">
										<label for="7th"> <input type="radio" class="inspectletIgnore"
											name="sdpDeposite" id="7th" value="7th">
											<p>7th</p> <i></i>
										</label>
									</div>
									<div class="a_part20">
										<label for="12th"> <input type="radio" class="inspectletIgnore"
											name="sdpDeposite" id="12th" value="12th">
											<p>12th</p> <i></i>
										</label>
									</div>
								</div>
							</div>

							<div class="sliderBotDesc statusgrip">
								<div class="a_satausfild2">
									<img
										src="${pageContext.request.contextPath}/resources/images/idea.png"
										alt="idea">
									<p>Pro Tip</p>
									<p>Earn more interest by choosing payout at maturity</p>
								</div>
								<div class="a_greensataus2">
									<img
										src="${pageContext.request.contextPath}/resources/images/like.png"
										alt="like">
									<p>Great choice!</p>
									<p>
										By choosing this tenor, you will get maximum returns of Rs. <i
											class="fas fa-rupee"></i> on your deposit
									</p>
								</div>
							</div>
							<div class="a_mobborder"></div>
						</div>

					</div>
				</div>
				<div class="a_rightSide">
					<div class="a_investmentTbl">
						<h2>
							<B>YOUR DEPOSIT SUMMARY</B>
						</h2>
						<ul class="">
							<li><p>Interest rate per annum*</p>
								<p id="sdpInterestRate"></p></li>
							<li><p>Deposit amount per month</p>
								<p id="sdpDepAmtmon">MONTHLY DEPOSIT AMOUNT</p></li>
							<li><p>Number of deposits</p>
								<p id="sdpNumOfDep">CHOOSE TOTAL NUMBER OF DEPOSITS</p></li>
							<li><p id="sdpTenOfEacDepText">Tenor </p>
								<p id="sdpTenOfEacDep">Tenor Of Each Deposit</p></li>
							<li><p>Total deposit amount</p>
								<p id="sdpTotPriAmt">amount * deposit</p></li>
							<li class="showmonthly"><p>Interest on each deposit*</p>
								<p id="monthIntAmt">
									<i class="fas fa-rupee"></i>
								</p></li>
							<li><p>Total payout at maturity*</p>
								<p id="totPayAmt">
									<i class="fas fa-rupee"></i>
								</p></li>
							<li><p>Total interest amount*</p>
								<p id="totIntEar">
									<i class="fas fa-rupee"></i>
								</p></li>
						</ul>
						<p>*Indicative returns if ROI does not change. In practice,
							interest rate prevailing on date of each deposit will be
							applicable to that particular deposit, hence this amount may
							vary.</p>
						<p>All the figures shown above are pertaining to the total
							number of deposits (first deposit by payment gateway and
							subsequent deposits by e-NACH)</p>
							<p></p>
							<p>Disclaimer: Actual returns may vary slightly if deposit enters into a leap year</p>
					</div>
					<div class="a_renewFDeposite">
						<div class="a_afterMarurity">
							<strong>Banking Details</strong>

						</div>


						<div class="a_ReInput">
                    <label for="bankName_2">Select Bank Name <a href="#;" class="a_viewPament">View available payment modes</a></label>
                    <select id="sdpbankName" name="sdpbankName" class="bankNameSelect BankVD">
                       <option class="noBankSelected" value="">Bank Name</option>
<option class="bothBankOption" value="Axis Bank">AXIS BANK</option>
<option class="bothBankOption" value="HDFC Bank">HDFC BANK</option>
<option class="bothBankOption" value="ICICI Bank">ICICI BANK LIMITED</option>
<option class="bothBankOption" value="162">KOTAK MAHINDRA BANK LIMITED</option>
<option class="bothBankOption" value="State Bank of India">STATE BANK OF INDIA</option>									
<option class="bothBankOption" value="Union Bank of India">ANDHRA BANK</option>
<option class="bothBankOption" value="Bandhan Bank">BANDHAN BANK LIMITED</option>
<option class="bothBankOption" value="Bank of Baroda Retail">BANK OF BARODA</option>
<option class="bothBankOption" value="Bank of Maharashtra">BANK OF MAHARASHTRA</option>
<option class="netBankShow" value="Canara Bank">CANARA BANK</option>
<option class="bothBankOption" value="Central Bank of India">CENTRAL BANK OF INDIA</option>
<option class="bothBankOption" value="City Union Bank">CITY UNION BANK LIMITED</option>
<option class="bothBankOption" value="Dhanlaxmi Bank">DHANALAKSHMI BANK</option>
<option class="bothBankOption" value="IDB">IDBI BANK</option>
<option class="bothBankOption" value="IDN">IDFC First Bank Ltd</option>
<option class="bothBankOption" value="IDS">INDUSIND BANK</option>
<option class="bothBankOption" value="Karur Vysya Bank">KARUR VYSYA BANK</option>
<option class="bothBankOption" value="Punjab National Bank [Retail]">PUNJAB NATIONAL BANK</option>
<option class="bothBankOption" value="SIB">SOUTH INDIAN BANK</option>
<option class="netBankShow" value="Canara Bank">SYNDICATE BANK</option>
<option class="bothBankOption" value="Union Bank of India">UNION BANK OF INDIA</option>
<option class="upihide" value="THE COSMOS CO OPERATIVE BANK LIMITED">THE COSMOS CO OPERATIVE BANK LIMITED</option>
<option class="upihide" value="DEUSTCHE BANK">DEUSTCHE BANK</option>
<option class="bothBankOption" value="Equitas Small finance bank" >EQUITAS SMALL FINANCE BANK LIMITED</option>
<option class="bothBankOption" value="FEDERAL BANK">FEDERAL BANK</option>
<option class="upihide" value="INDIAN OVERSEAS BANK">INDIAN OVERSEAS BANK</option>
<option class="upihide" value="JANA SMALL FINANCE BANK LTD">JANA SMALL FINANCE BANK LTD</option>
<option class="upihide" value="KARNATAKA BANK LIMITED">KARNATAKA BANK LIMITED</option>
<option class="upihide" value="ORIENTAL BANK OF COMMERCE">ORIENTAL BANK OF COMMERCE</option>
<option class="upihide" value="PUNJAB AND SIND BANK">PUNJAB AND SIND BANK</option>
<option class="upihide" value="RBL Bank Limited">RBL Bank Limited</option>
<option class="bothBankOption" value="Standard Chartered Bank">STANDARD CHARTERED BANK</option>
<option class="upihide" value="Ujjivan Small Finance Bank Limited">Ujjivan Small Finance Bank Limited</option>
<option class="bothBankOption" value="YES BANK">YES BANK</option>
</select>
                    <div class="errormsg">Select Bank Name</div>
                    <i class="fas fa-caret-down"></i>
                </div>
<div class="a_opplsLink statusgripBank">
                    <div class="a_oopsPlan">
                        <img src="${pageContext.request.contextPath}/resources/images/error.png" alt="">
                        <p>Oops! Only UPI payment mode is available for the bank selected and UPI payments do not support transaction amounts greater than 1 lakh in a day. Hence, please select another bank from the list which has Netbanking payment mode available. You can view available payment modes <a href="#;">here</a></p>
                    </div>
                </div>
                <div class="bnkMethod">
                    <p>Choose payment mode</p>
                    <div class="a_radiodeposit">
                        <label id="SdpNetBankCheck">
                            <input type="radio" class="inspectletIgnore" name="banking_det_2"  id="sdpnetBank" value="Netbanking">
                            <i></i>
                            <p>Netbanking</p>
                        </label>
                        <label id="SdpupiCheck">
                            <input type="radio" class="inspectletIgnore" name="banking_det_2" id="sdpUpi" value="UPI">
                            <i></i>
                            <p>UPI</p>
                        </label>
                        
                    </div>
                    <div class="errormsg">Please select payment mode</div>
                </div>

					 <div class="a_radiodeposit_2"> 
                    
                    
                    <div class="a_ReInput">
                        <label for="accountNumber">Account Number <i>(Should be in the name of FD applicant)</i><img src="${pageContext.request.contextPath}/resources/images/information_black.png" alt="">
                                        <div class="p_fixdepositstultip">
                                            <div class="p_fixdepositBG">
                                                <p>Please enter the account number on the name of FD applicant. On maturity, the final amount will be credited to this bank account. You can change your bank account later by submitting a cancelled cheque to us.</p>
                                                <div class="p_gotitbtn"><button class="gotitone">Got it</button></div>
                                            </div>
                                        </div>
                        
                        </label>
                        <input type="number" class="accountNumVD" name="accountNumber" id="sdpaccountNumber" autocomplete="off" >
                        <div class="errormsg">Enter your account number</div>
                    </div>
                    <div class="a_ReInput">
                        <label for="ifscCode">IFSC</label>
                        <input type="text" class="ifscCodeVD ifscCheck" name="ifscCode" id="sdpifscCode" autocomplete="off" >
                        <div class="errormsg">Please enter IFSC code</div>
                    </div>
 
<p class="more">
In case of any mismatch in bank account number / account holder name from where FD funds are received, refund shall be processed in 7 working days without any interest
</p>
                 
                
  </div><div class="a_blueBtnPart">
                    <button class="submitBTN validBtn">PROCEED TO PAY
                    
                      <div class="fd_sdp_loder"></div>
                    </button>
                </div>

					</div>

				</div>
			</form>

		</section>



	</div>

	<div class="a_part_7">

		<section class="p_fdheadtitle thnakupdd">
			<div class="p_fdtextname">
				<p>Thank you for investing with us!</p>
			</div>
		</section>

		<section class="p_thankuparts">

			<div class="p_leftthankupart">
				<div class="p_leftalldata">
					<div class="p_congratulations">
						<img
							src="${pageContext.request.contextPath}/resources/images/right.jpg"
							alt="right">
						<p>Congratulations! You have successfully made your first
							deposit</p>
					</div>
					<div class="p_depositsummery">
						<div class="p_titledeposit">
							<h2>Your deposit summary</h2>
						</div>
						<div class="p_maturityamount">
							<p>Maturity amount</p>
							<strong><i class="fas fa-rupee"></i> 4,625</strong>
						</div>
						<div class="p_listmaturariy">
							<ul>
								<li><p>Interest rate</p>
									<strong>7.9%</strong></li>
								<li><p>Maturity date</p>
									<strong>04-Mar-2025</strong></li>
								<li><p>Interest earned</p>
									<strong><i class="fas fa-rupee"></i> 4,625</strong></li>
								<li><p>Deposit amount</p>
									<strong><i class="fas fa-rupee"></i> 10,000</strong></li>
							</ul>
						</div>
					</div>
				</div>
			</div>


			<div class="p_rightuserpart">
				<div class="p_rightalldata">



					<div class="p_depositinfo">
						<div class="p_deposittext">
							<p>We will send you all your deposit-related information on
								your registered contact details.</p>
						</div>
						<div class="row">
							<div class="p_commen">
								<button>Download Reference Form</button>
							</div>
							<div class="p_commen">
								<button>Request callback</button>
							</div>
						</div>
					</div>

					<div class="p_regnach">
						<p>Please register NACH for future auto debits</p>
						<div class="p_regbutton">
							<button>Register NACH for Auto Debit</button>
						</div>
					</div>

					<div class="p_investplan">
						<div class="p_titleinvest">
							<h2>How did the investment process go?</h2>
						</div>
						<div class="p_yourexp">
							<strong>Tell us about your experience</strong>
							<ul>
								<li><a href="#;"><img
										src="${pageContext.request.contextPath}/resources/images/angry.png"
										alt="angry">
									<p>Annoying</p></a></li>
								<li><a href="#;"><img
										src="${pageContext.request.contextPath}/resources/images/sad.png"
										alt="sad">
									<p>Time-consuming</p></a></li>
								<li><a href="#;"><img
										src="${pageContext.request.contextPath}/resources/images/good.png"
										alt="good">
									<p>Good</p></a></li>
								<li><a href="#;"><img
										src="${pageContext.request.contextPath}/resources/images/fast.png"
										alt="fast">
									<p>Fast</p></a></li>
								<li><a href="#;"><img
										src="${pageContext.request.contextPath}/resources/images/Easy.png"
										alt="Easy">
									<p>Easy</p></a></li>
							</ul>
						</div>
					</div>

				</div>
			</div>

		</section>

	</div>



	<div class="a_blackoverlay"></div>

	<div class="a_termsAndConPart">
		<a href="#;" class="a_closePop"><i class="fas fa-times"></i></a>
		<h2>Terms and Conditions</h2>
		<div class="a_tNcPopBox">
			<h2>Please tick any of the below option if applicable</h2>
			<div>
				<p>Director or Promoter of Bajaj Finance Ltd</p>
				<div class="a_swichTgl">
					<label for="dirOrProm"> <input type="checkbox"
						id="dirOrProm" name="dirOrProm"> <i></i>
						<p class="yes">Yes</p>
						<p class="no">No</p>
					</label>
				</div>
			</div>
			<div>
				<p>Relative of Director of Bajaj Finance Ltd</p>
				<div class="a_swichTgl">
					<label for="relativeOfD"> <input type="checkbox"
						name="relativeOfD" id="relativeOfD"> <i></i>
						<p class="yes">Yes</p>
						<p class="no">No</p>
					</label>
				</div>
			</div>
			<div>
				<p>Shareholder of Bajaj Finance Ltd</p>
				<div class="a_swichTgl">
					<label for="shareholder"> <input type="checkbox"
						name="shareholder" id="shareholder"> <i></i>
						<p class="yes">Yes</p>
						<p class="no">No</p>
					</label>
				</div>
			</div>
		</div>
		<div class="a_tNcPopBox">
			<h2>Tax residency declaration:</h2>
			<div>
				<p>Are you a citizen, national or tax resident of any country
					outside India?</p>
				<div class="a_swichTgl">
					<label for="foreignCitizen"> <input type="checkbox"
						name="foreignCitizen" id="foreignCitizen">
						<div class="errormsg">Please select 'No' to proceed</div> <i></i>
						<p class="yes">Yes</p>
						<p class="no">No</p>
					</label>

					<div class="p_fixdepositstultip">
						<div class="p_fixdepositBG">
							<p>We cannot proceed to book a deposit for a citizen/national
								of another country other than India</p>
							<div class="p_gotitbtn">
								<button class="gotitone">Got it</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div>

				<div>
            <p class="tctext">I undertake to inform company any change in status of my nationality or tax residence. I am making investment from my Indian resident Individual Savings bank account. I/We confirm that I/we have read and understood the detailed terms and conditions annexed to this Application including the interest rate and other charges, the financials and other statements/ particulars/ representations furnished by BFL and after careful consideration, I/we am/are making the deposit with the BFL at my/our own risk and volition. I have read and agree to the  <a  class="otpTncPdf" href="${pageContext.request.contextPath}/resources/pdf/TCs-RI.pdf" target="_blank">Terms & Conditions</a></p>
            <div class="a_swichTgl">
                <label for="TandC">
                    <input type="checkbox" name ="TandC" id="TandC" checked disabled>
                    <i></i>
                    <p class="yes">Yes</p>
                    <p class="no">No</p>
                </label>
            </div>
            <br>
             <br>
            <p><br>Disclaimer: Funds are transferred to Bajaj Finance Ltd's bank account through payment gateway by an intermediary. Interest start date will be at T+1 working days based on fund realisation from payment gateway.</p>
            <p></p><br>
            <p>Fund transfer limits through internet banking are predefined by banks and can vary from bank to bank. Please check you bank's third party transfer limit before making any online payments</p>
     </div>

			</div>
			<div class="a_blueBtnPart">
				<button id="fdPayment">
					PAY VIA NETBANKING <img
						src="${pageContext.request.contextPath}/resources/images/whtarrow.png"
						alt="whtarrow" class="imgarrowwt">


					<div class="fd_sdp_loder"></div>
				</button>
			</div>
		</div>

	</div>
	<div class="tncSafaricheck">
	<div class="a_termsAndConPart_2">
            <a href="#;" class="a_closePop"><i class="fas fa-times"></i></a>
            <h2>
                <p>Annual rate of interest</p>
                Valid for deposits up to Rs.5 crore (w.e.f. 12 May 2021)
            </h2>
            <strong>For customers investing through Online</strong>
            <table>
                <tbody>
                    <tr style="border: 0;">
                        <th class="text-center">Months</th>
                        <th class="text-center">Cumulative</th>
                        <th class="text-center" colspan="4">Non-Cumulative</th>
                    </tr>
                    <tr>
                        <th class="text-center"></th>
                        <th class="text-center"></th>
                        <th class="text-center">Monthly</th>
                        <th class="text-center">Quarterly</th>
                        <th class="text-center">Half Yearly</th>
                        <th class="text-center">Annual</th>
                    </tr>
                    <tr>
                        <td>12 &ndash; 23</td>
                        <td>5.75%</td>
                        <td>5.60%</td>
                        <td>5.63%</td>
                        <td>5.67%</td>
                        <td>5.75%</td>
                    </tr>
                    <tr>
                        <td>24 &ndash; 35</td>
                        <td>6.20%</td>
                        <td>6.03%</td>
                        <td>6.06%</td>
                        <td>6.11%</td>
                        <td>6.20%</td>
                    </tr>
                    <tr>
                        <td>36 - 60</td>
                        <td>6.60%</td>
                        <td>6.41%</td>
                        <td>6.44%</td>
                        <td>6.49%</td>
                        <td>6.60%</td>
                    </tr>
                </tbody>
            </table>


            <strong>For Senior citizens</strong>
            <table>
                <tbody>
                    <tr style="border: 0;">
                        <th class="text-center">Months</th>
                        <th class="text-center">Cumulative</th>
                        <th class="text-center" colspan="4">Non-Cumulative</th>
                    </tr>
                    <tr>
                        <th class="text-center"></th>
                        <th class="text-center"></th>
                        <th class="text-center">Monthly</th>
                        <th class="text-center">Quarterly</th>
                        <th class="text-center">Half Yearly</th>
                        <th class="text-center">Annual</th>
                    </tr>
                    <tr>
                        <td>12 &ndash; 23</td>
                        <td>5.90%</td>
                        <td>5.75%</td>
                        <td>5.77%</td>
                        <td>5.82%</td>
                        <td>5.90%</td>
                    </tr>
                    <tr>
                        <td>24 &ndash; 35</td>
                        <td>6.35%</td>
                        <td>6.17%</td>
                        <td>6.20%</td>
                        <td>6.25%</td>
                        <td>6.35%</td>
                    </tr>
                    <tr>
                        <td>36 - 60</td>
                        <td>6.75%</td>
                        <td>6.55%</td>
                        <td>6.59%</td>
                        <td>6.64%</td>
                        <td>6.75%</td>
                    </tr>
                </tbody>
            </table>
        </div>


		<div class="a_termsAndConPart_2-1">
            <a href="#;" class="a_closePop"><i class="fas fa-times"></i></a>
            <h2>
                <p>Annual rate of interest</p>
                Valid for deposits up to Rs.5 crore (w.e.f. 12 May 2021)
            </h2>
            <table border="1">
                <tbody>
                    <tr>
                        <th class="text-center" rowspan="3">Tenor in months</th>
                        <th class="text-center" colspan="3">Cumulative rates of interest</th>
                    </tr>
                    <tr>
                        <th class="text-center" colspan="2">Special category</th>
                    </tr>
                    <tr>
                        <th>For Senior citizens</th>
                        <th>For Customers investing through Online</th>
                    </tr>
                    <tr>
                        <td>12 - 23</td>
                        <td>5.90%</td>
                        <td>5.75%</td>
                    </tr>
                    <tr>
                        <td>24 - 35</td>
                        <td>6.35%</td>
                        <td>6.20%</td>
                    </tr>
                    <tr>
                        <td>36 - 60</td>
                        <td>6.75%</td>
                        <td>6.60%</td>
                    </tr>
                </tbody>
            </table>

        </div>
	</div>
<div class="a_termsAndConPart_2-2">
            <a href="#;" class="a_closePop"><i class="fas fa-times"></i></a>
            <h2>
                <p>Fixed Deposit</p>
                Valid for deposits up to Rs.5 crore (w.e.f. 12 May 2021)
            </h2>
            <strong>For Online customer</strong>
            <table>
                <tbody>
                    <tr style="border: 0;">
                        <th class="text-center">Months</th>
                        <th class="text-center">Cumulative</th>
                        <th class="text-center" colspan="4">Non-Cumulative</th>
                    </tr>
                    <tr>
                        <th class="text-center"></th>
                        <th class="text-center"></th>
                        <th class="text-center">Monthly</th>
                        <th class="text-center">Quarterly</th>
                        <th class="text-center">Half Yearly</th>
                        <th class="text-center">Annual</th>
                    </tr>
                    <tr>
                        <td>12 &ndash; 23</td>
                        <td>5.75%</td>
                        <td>5.60%</td>
                        <td>5.63%</td>
                        <td>5.67%</td>
                        <td>5.75%</td>
                    </tr>
                    <tr>
                        <td>24 &ndash; 35</td>
                        <td>6.20%</td>
                        <td>6.03%</td>
                        <td>6.06%</td>
                        <td>6.11%</td>
                        <td>6.20%</td>
                    </tr>
                    <tr>
                        <td>36 - 60</td>
                        <td>6.60%</td>
                        <td>6.41%</td>
                        <td>6.44%</td>
                        <td>6.49%</td>
                        <td>6.60%</td>
                    </tr>
                </tbody>
            </table>


            <strong>For Senior citizens</strong>
            <table>
                <tbody>
                    <tr style="border: 0;">
                        <th class="text-center">Months</th>
                        <th class="text-center">Cumulative</th>
                        <th class="text-center" colspan="4">Non-Cumulative</th>
                    </tr>
                    <tr>
                        <th class="text-center"></th>
                        <th class="text-center"></th>
                        <th class="text-center">Monthly</th>
                        <th class="text-center">Quarterly</th>
                        <th class="text-center">Half Yearly</th>
                        <th class="text-center">Annual</th>
                    </tr>
                    <tr>
                        <td>12 &ndash; 23</td>
                        <td>5.90%</td>
                        <td>5.75%</td>
                        <td>5.77%</td>
                        <td>5.82%</td>
                        <td>5.90%</td>
                    </tr>
                    <tr>
                        <td>24 &ndash; 35</td>
                        <td>6.35%</td>
                        <td>6.17%</td>
                        <td>6.20%</td>
                        <td>6.25%</td>
                        <td>6.35%</td>
                    </tr>
                    <tr>
                        <td>36 - 60</td>
                        <td>6.75%</td>
                        <td>6.55%</td>
                        <td>6.59%</td>
                        <td>6.64%</td>
                        <td>6.75%</td>
                    </tr>
                </tbody>
            </table>
            
            <h2 class="marTop30">
                <p>Systematic Deposit Plan</p>
                Valid for deposits up to Rs.5 crore (w.e.f. 12 May 2021)
            </h2>
            <table>
                <tbody>
                    <tr style="border: 0;">
                        <th class="text-center" rowspan="3">Tenor in months</th>
                        <th class="text-center" colspan="3">Cumulative rates of interest</th>
                    </tr>
                    <tr style="border: 0;">
                        <th class="text-center" colspan="2">Special category</th>
                    </tr>
                    <tr style="border: 0;">
                        <th>For senior citizens</th>
                        <th>For Online Customer</th>
                    </tr>
                    <tr>
                        <td>12 - 23</td>
                        <td>5.90%</td>
                        <td>5.75%</td>
                    </tr>
                    <tr>
                        <td>24 - 35</td>
                        <td>6.35%</td>
                        <td>6.20%</td>
                    </tr>
                    <tr>
                        <td>36 - 60</td>
                        <td>6.75%</td>
                        <td>6.60%</td>
                    </tr>
                </tbody>
            </table>
        </div>
	<div class="a_termsAndConPart_4">
		<a href="#;" class="a_closePop"><i class="fas fa-times"></i></a>
		<table border="1">
			<tr>
				<th>Bank Name</th>
				<th>Available Payment Modes</th>
			</tr>
			<tr>
				<td>AXIS BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>HDFC BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>ICICI BANK LIMITED</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>KOTAK MAHINDRA BANK LIMITED</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>STATE BANK OF INDIA</td>
				<td>Net Banking , UPI</td>
			</tr>
			
			<tr>
				<td>ABHYUDAYA COOPERATIVE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>AHMEDABAD MERCANTILE COOPERATIVE BANK</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>ALLAHABAD BANK</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>ANDHRA BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
		
			<tr>
				<td>APNA SAHAKARI BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>AU SMALL FINANCE BANK LIMITED</td>
				<td>Net Banking , UPI</td>
			</tr>
			
			<tr>
				<td>BANDHAN BANK LIMITED</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>BANK OF BARODA</td>
				<td>Net Banking , UPI</td>
			</tr>
			
			
			<tr>
				<td>BANK OF MAHARASHTRA</td>
				<td>Net Banking , UPI</td>
			</tr>
			
			<tr>
				<td>BHAGINI NIVEDITA SAHAKARI BANK LTD PUNE</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>CANARA BANK</td>
				<td>Net Banking</td>
			</tr>
			<tr>
				<td>CAPITAL SMALL FINANCE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>CENTRAL BANK OF INDIA</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>CITI BANK</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>CITY UNION BANK LIMITED</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>CORPORATION BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			
			<tr>
				<td>DENA BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>DEUSTCHE BANK</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>DHANALAKSHMI BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>EQUITAS SMALL FINANCE BANK LIMITED</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>ESAF SMALL FINANCE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>FEDERAL BANK</td>
				<td>Net Banking ,UPI</td>
			</tr>
			<tr>
				<td>FINCARE SMALL FINANCE BANK LTD</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>G P PARSIK BANK</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>HSBC BANK</td>
				<td>UPI</td>
			</tr>
			
			
			<tr>
				<td>IDBI BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			
			<tr>
				<td>IDFC FIRST BANK LTD</td>
				<td>Net Banking , UPI</td>
			</tr>
			
			<tr>
				<td>INDIAN BANK</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>INDUSIND BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			
			<tr>
				<td>KALLAPPANNA AWADE ICHALKARANJI JANATA SAHAKARI BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>KALUPUR COMMERCIAL COOPERATIVE BANK</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>KARNATAKA BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>KARNATAKA VIKAS GRAMEENA BANK</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>KARUR VYSYA BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			
			<tr>
				<td>Maharashtra Gramin Bank</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>NUTAN NAGARIK SAHAKARI BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>NKGSB Co-op Bank Ltd </td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>ORIENTAL BANK OF COMMERCE</td>
				<td>Net Banking , UPI</td>
			</tr>
			
			<tr>
				<td>PUNJAB NATIONAL BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>RAJASTHAN MARUDHARA GRAMIN BANK</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>RAJKOT NAGRIK SAHAKARI BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>RBL Bank Limited</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>Shri Veershaiv Co Op Bank Ltd</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>SOUTH INDIAN BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>STANDARD CHARTERED BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			
			<tr>
				<td>STATE BANK OF INDIA</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>Suco Souharda Sahakari Bank Ltd</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>SURYODAY SMALL FINANCE BANK LIMITED</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>SUTEX COOPERATIVE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>SYNDICATE BANK</td>
				<td>Net Banking</td>
			</tr>
			
			
			<tr>
				<td>THE THANE BHARAT SAHAKARI BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>AHMEDNAGAR MERCHANTS CO-OP BANK LTD</td>
				<td>UPI</td>
			</tr>

			
			<tr>
				<td>THE BARAMATI SAHAKARI BANK LTD</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>THE GADCHIROLI DISTRICT CENTRAL COOPERATIVE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>THE GUJARAT STATE COOPERATIVE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>THE HASTI COOP BANK LTD</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>KALYAN JANATA SAHAKARI BANK</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>LAXMI VILAS BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>THE MEHSANA URBAN COOPERATIVE BANK</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>The Muslim Co-operative Bank Ltd</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>SARASWAT COOPERATIVE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>THE UDAIPUR URBAN CO OPERATIVE BANK LTD</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>THE URBAN CO OPERATIVE BANK Ltd No ONE SEVEN FIVE EIGHT
					PERINTHALMANNA</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>THE VISHWESHWAR SAHAKARI BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>UCO BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>Ujjivan Small Finance Bank Limited</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>UNION BANK OF INDIA</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>UNITED BANK OF INDIA</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>VIJAYA BANK</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>YES BANK</td>
				<td>Net Banking ,UPI</td>
			</tr>
		</table>
	</div>
	
	
	<div class="fdbilldesk" style="display: none;">
		<form
			action="https://payment.bajajfinserv.in/Payments/FD_Payment.aspx"
			method="POST" id="fdbilldesk">
			<input type="text" name="msg" value="" id="fdmsg"> <input
				type="submit" value="Submit">
		</form>
	</div>


	<div class="instaformClass" style="display: none;">
		<form name="instaform" id="instaform"
			action="https://bfin.in/AadhaarOkyc/OkycProcess.aspx" method="POST">
			<input type="hidden" name="Request" id="RequestnewValue" value="">
			<button type="submit" name="save"></button>
		</form>
	</div>
	<div class="loadoverlay">
<div class="loader">Loading...</div>
<div class="slidermain">
<div class="loaderslide">
<div>Hang on for just a moment! We are fetching your Details.</div>
</div>
</div>
</div>

	<script src="${pageContext.request.contextPath}/resources/js/system.js"
		defer></script>
	<script src="${pageContext.request.contextPath}/resources/js/common_old.js"
		defer></script>
	
	<script src="${pageContext.request.contextPath}/resources/newJS/moment.min.js"
		defer></script>
		
	<script src="${pageContext.request.contextPath}/resources/newJS/daterangepicker.js"
		defer></script>	
	
	<script src="${pageContext.request.contextPath}/resources/js/AES.js"
		defer></script>
	<script src="${pageContext.request.contextPath}/resources/js/PBKDF2.js"
		defer></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/AESUtil.js" defer></script>

	 <script type="text/javascript" src="//cdn25.lemnisk.co/ssp/st/5459.js"></script> 
	
  <script type="text/javascript" src="/sites/bajaj/15Sep2017/js/bfl.js"></script>
</body>


</html>