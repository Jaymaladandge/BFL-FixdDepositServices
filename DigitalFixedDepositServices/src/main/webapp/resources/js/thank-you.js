$(document).ready(function () {
	
	
	var custType =  $('#custType').val().trim();
	var transStatus =  $('#transStatus').val().trim();
	var investmentType =  $('#investmentType').val();
	var customerId =  $('#customerId').val().trim();
	var interestRate =  $('#interestRate').val().trim();
	var maturityDate =  $('#maturityDate').val().trim();
	var interestEarned =  $('#interestEarned').val().trim();
	var depositAmount =  $('#depositAmount').val().trim();
	var maturityAmount =  $('#maturityAmount').val().trim();
	var sdpTotalPriAmt = $("#sdpTotalPriAmnt").val().trim();
    var monthIntrestAmt = $("#monthIntrestAmnt").val().trim();
    var noOfDeposit = $("#noOfDeposit").val().trim();	    
    var totPayoutAmt = $("#totPayoutAmnt").val().trim();
    var tenorOfDeposit = $("#tenorOfDeposit").val().trim();
	var pocketActiveflag = $("#pocketActiveflag").val().trim();
    var interestPayoutType=$("#interestPayoutType").val().trim();
    var utrNumber=$('#utrNumber').val().trim();
    var partnerCode=$('#partnerCode').val().trim();
    var partnercodeFlage=$('#partnercodeFlage').val().trim();
    var paymentMode=$('#paymentMode').val().trim();
	
	if(custType == 'ETB' && transStatus=="success")           
	{	                
	console.log("etb success user");        
	$(".thank_success").show();
	$(".failTrans").hide();
	$(".ntb").hide();
	trackPageviewScriptUpdated('Payment_Success');
	/*if(pocketActiveflag == "Y"){
		
		$("#autoDebitPayout").show();
	}*/
	
	if(investmentType == 'SDP' )           
	{
		$('#fdnum').show();
	 $('#da').html( 'Monthly Deposit Amount').siblings('strong').html(monthIntrestAmt);
	 $('#md').html( 'No. of deposits through e-mandate').siblings('strong').html(noOfDeposit);
	 $('#ir').html( 'Tenure of each deposit').siblings('strong').html(tenorOfDeposit);
	 $('#ie').html( 'Total Investment Amount').siblings('strong').html(sdpTotalPriAmt);
	// $('#totalAmount').html( 'Total Interest Amount').siblings('strong').html(totPayoutAmt);
	 var tndeps= parseInt(noOfDeposit) + 1;
	 $('#tir').html( 'Total number of deposits').siblings('strong').html(tndeps);
	 $('#totalAmount').siblings('strong').html(totPayoutAmt);
	 trackPageviewScriptTy(customerId,totPayoutAmt);
	 vizLayer("STEP-ThankYouPage",totPayoutAmt,"NA",custType,customerId)
	 depositAmount=sdpTotalPriAmt;
	}
	if(investmentType == 'FD' )           
	{
		$('#tir').hide();
		$('#tir').siblings('strong').hide();
	 $('#da').siblings('strong').html(depositAmount);
	 $('#md').siblings('strong').html(maturityDate);
	 $('#ir').siblings('strong').html(interestRate);
	// $('#ie').siblings('strong').html(interestEarned);
	 $('#ie').html( 'Interest amount(<i class="intPay">'+interestPayoutType+'</i>)').siblings('strong').html(interestEarned);
	 $('#totalAmount').siblings('strong').html(maturityAmount);
	
	
	 trackPageviewScriptTy(customerId,depositAmount);
	 vizLayer("STEP-ThankYouPage",depositAmount,"NA",custType,customerId)
	}
	
	/**************************Datalayer Call************************************/
	dataLayer.push({
		'event':'thankyou_page_load',
		'pageType':document.title, 
		'id': utrNumber,
		'eventValue':depositAmount,  
		});
   /**************************Datalayer Call************************************/
	newrelic.addPageAction('fd-transaction-status', {result: 'success', amount: depositAmount,cust:customerId,trans:utrNumber,AssistedByPartner:partnercodeFlage,partnercode:partnerCode,paymentMode:paymentMode});
	
	} 
	else if(custType == 'NTB')           
	{	                
	console.log("ntb user");        
	$(".thank_success").hide();
	$(".failTrans").hide();
	$(".ntb").show();
	trackPageviewScriptUpdated('Error_Page');     
	} 
	else            
	{	                
	console.log("fail user");        
	$(".thank_success").hide();
	$(".failTrans").show();
	$(".ntb").hide();
	trackPageviewScriptUpdated('Payment_Failure');
	if("SDP" == investmentType){
		
		$("#fdbankNameID").hide();
		$("#fdbankNameID").prop("disabled", true);
		$("#fdbankNameID").remove();
		$("#fdbnkMethod").remove();
		 depositAmount=sdpTotalPriAmt;
	}

	if("FD" == investmentType){
		
		$("#sdpbankNameID").hide();
		$("#sdpbankNameID").prop("disabled", true);
		$("#sdpbankNameID").remove();
		$("#sdpbnkMethod").remove();
	}
	newrelic.addPageAction('fd-transaction-status', {result: 'Fail', amount: depositAmount,cust:customerId,trans:utrNumber,AssistedByPartner:partnercodeFlage,partnercode:partnerCode,paymentMode:paymentMode});      
	      
	} 
	
	
	 
	 
	 function fildValidation(th, ErrMSG) {
	        if (ErrMSG) {
	            $(th).siblings(".errormsg").text(ErrMSG);
	        }
	    }
	
	 function mainError(th, ErrMSG) {
	        if (ErrMSG) {
	            $(".mainError").children('p').text(ErrMSG);
	        }
	    }
	
	 $(".a_ReInput select").change(function() {
			
	        if (!$(this).hasClass("nomandetory")) {
	          //  if (!$(this).attr('disabled')) {
	        	 if ($(this).val() == null) {
	        		 $('select option:contains("Bank Name")').prop('selected',true);
	             }
	        	 else if ($(this).val() == "" ) {
	                    $(this).siblings(".errormsg").show().css('color','#ea1212');
	                    fildValidation(this);
	                } else {
	                    $(this).siblings(".errormsg").hide();
	                    fildValidation(this);
	                }
	            }
	     //   }
	
	
	    });
	 
	 
	 $('.ifscCodeVD').blur(function(e) {
	        var mo = $(this).val();
	        mo = mo.toUpperCase();
	        $(this).val(mo);
	
	    });
	 
	 $('.accountNumVD').keyup(function(e) {
	        var mo = $(this).val();
	        if (mo.length > 20) {
	            $(this).val(mo.substr(0, 20));
	
	        }
	       /* if (mo.length == 6) {
	            $(this).blur();
	        }*/
	    });
	 
	 $('input').keyup(function(e) {
			
	        var maxlength = $(this).attr('maxlength');
	        var value = $(this).val();
	
	        if (value && value.length >= maxlength) {
	            $(this).val(value.substr(0, maxlength));
	        }
	    });
	 
	 $('.a_ReInput select').focus(function(){
	        $(this).parents('.a_ReInput').addClass('active');
	        
	    });
	    $('.a_ReInput select').blur(function(){
	        $(this).parents('.a_ReInput').removeClass('active');
	        
	    });
	    
	    $('.a_ReInput2 select').focus(function(){
	        $(this).parents('.a_ReInput2').addClass('active');
	        
	    });
	    $('.a_ReInput2 select').blur(function(){
	        $(this).parents('.a_ReInput2').removeClass('active');
	        
	    });
	    
	    $('.a_ReInput select').change(function(){
	        $(this).css('color','#3f4353');
	        //$(this).parents('.a_ReInput').removeClass('active');
	        
	    });
	    
	    $('.a_ReInput input').focus(function(){
	        if (!$(this).attr('readonly')) {
	            $(this).parent().addClass('active');    
	        }
	        
	    });
	    $('.a_ReInput input').blur(function(){
	        $(this).parent().removeClass('active');
	        
	            var th = $(this);
				setTimeout(function(){validornot(th)},300);
	    });
	    
	    $('.a_ReInput2 input').focus(function(){
	        $(this).parent().addClass('active');
	    });
	    $('.a_ReInput2 input').blur(function(){
	        $(this).parent().removeClass('active');
	        
	    });
	    function validornot(th){
			if(!th.hasClass("nomandetory")){
			if(th.siblings(".errormsg").css("display")=="none" ){
				th.parents('.a_ReInput').addClass("valid").removeClass("invalid");
			}else{
				th.parents('.a_ReInput').removeClass("valid").addClass("invalid");
			}
			}
		}
	    
	 
	  $(".a_ReInput input").blur(function() {
	        var ThisObj = $(this);
	        $(this).siblings(".errormsg").css('color','#ea1212');
	        if (!$(this).hasClass("nomandetory")) {
	         //   if (!$(this).attr('disabled')) {
	                if ($(this).val() == "") 
	                {
	                    if (ThisObj.attr("type") == "file") {
	
	                    } else {
	                        $(this).siblings(".errormsg").show();
	                        fildValidation(this);
	                    }
	                    if (!$(this).hasClass('otpVD')) {
	                        $('.mainError').show();
	                    }
	
	                    //mainError(this,$('.errormsg').children('p').text());
	                    //alert($('.errormsg').children('p').text());
	                    var totalerror = 0;
	                    var firsterr = 0;
	                    $("#pstpF1 .errormsg").each(function(i) {
	                        if ($(this).css("display") == "block") {
	                            totalerror++;
	                            if (firsterr == 0) {
	                                firsterr = $(this).parent(".a_ReInput").offset().top;
	                                var getError = $(this).parent(".a_ReInput").children('.errormsg').children('p').text();
	                                $('.a_step1 .mainError').show().children('p').text(getError);
	                                $('html, body').animate({ scrollTop: 0 }, 200);
	                                //alert(getError);
	                            }
	                        }
	
	                    });
	
	
	                } else {
	
	                    if ($(this).hasClass('FirstNameVD')) {
	
	                        var myText = $(this).val();
	                        myText = myText.trim();
	                        $(this).val(myText);
	
	                        if (!/^[a-zA-Z]*$/g.test($(this).val())) {
	                            $(this).siblings(".errormsg").show();
	                            $(this).parent().removeClass('valid');
	                            fildValidation(this, "Only alphabets are allowed in name field");
	
	                            $('.mainError').show();
	                            mainError(this, 'Only alphabets are allowed in name field');
	
	                            $('html, body').animate({ scrollTop: 0 }, 200);
	
	                            if ($(this).val().indexOf(" ") != -1) {
	                                $(this).siblings(".errormsg").show();
	                                $(this).parent().removeClass('valid');
	                                fildValidation(this, "Space not allowed in name field");
	
	                                $('.mainError').show();
	                                mainError(this, 'Space not allowed in name field');
	
	                                $('html, body').animate({ scrollTop: 0 }, 200);
	                            }
	
	                        } else {
	                            if ($(this).val().length > 2) {
	                                $(this).siblings(".errormsg").hide();
	                                $('.mainError').hide();
	                                $(this).parent().addClass('valid');
	
	
	
	                            } else {
	                                $(this).siblings(".errormsg").show();
	                                $(this).parent().removeClass('valid');
	                                fildValidation(this, "Minimum 3 characters required in name field");
	
	                                $('.mainError').show();
	                                $(this).parent().removeClass('valid');
	                                mainError(this, 'Minimum 3 characters required in name field');
	
	
	                            }
	                        }
	
	
	                    } else if ($(this).hasClass('LastNameVD')) {
	
	                        var myText = $(this).val();
	                        myText = myText.trim();
	                        $(this).val(myText);
	
	                        if (!/^[a-zA-Z]*$/g.test($(this).val())) {
	                            $(this).siblings(".errormsg").show();
	                            $(this).parent().removeClass('valid');
	                            fildValidation(this, "Only alphabets are allowed in name field");
	
	                            $('.mainError').show();
	                            mainError(this, 'Only alphabets are allowed in name field');
	
	                            $('html, body').animate({ scrollTop: 0 }, 200);
	
	                            if ($(this).val().indexOf(" ") != -1) {
	                                $(this).siblings(".errormsg").show();
	                                $(this).parent().removeClass('valid');
	                                fildValidation(this, "Space not allowed in name field");
	
	                                $('.mainError').show();
	                                mainError(this, 'Space not allowed in name field');
	
	                                $('html, body').animate({ scrollTop: 0 }, 200);
	                            }
	                        } else {
	                            if ($(this).val().length > 2) {
	                                $(this).siblings(".errormsg").hide();
	                                $('.mainError').hide();
	                                $(this).parent().addClass('valid');
	                            } else {
	                                $(this).siblings(".errormsg").show();
	                                $(this).parent().removeClass('valid');
	                                fildValidation(this, "Minimum 3 characters required in name field");
	
	                                $('.mainError').show();
	                                mainError(this, 'Minimum 3 characters required in name field');
	                            }
	                        }
	
	                    } else if ($(this).hasClass('PinCodeVD')) {
	
	                        if (!/^[0-9]{6}$/.test($(this).val()))
	                        {
	                            $(this).siblings(".errormsg").show();
	                            fildValidation(this);
	                        } else { $(this).siblings(".errormsg").hide(); }
	
	                    } else if ($(this).hasClass('otpVD')) {
	
	                        /*if(!/^[0-9]+$/.test($(this).val())){
										  $(this).siblings(".errormsg").show();
										    fildValidation(this);
										}else{$(this).siblings(".errormsg").hide();}*/
	
	                        var value = $(this).val();
	                        if (value.length < 6 || value.length > 6) {
	                            $(this).siblings(".errormsg").show();
	                            fildValidation(this, "OTP entered is incorrect");
	                            $(this).siblings('.a_optlinMain').addClass('invalidOTP');
	                        } else {
	                            $(this).siblings(".errormsg").hide();
	                            $(this).siblings('.a_optlinMain').removeClass('invalidOTP');
	                        }
	
	                    } else if ($(this).hasClass('mobileVD')) {
	
	                        var value = $(this).val();
	
	                        if (value.length < 10 || value.length > 10) {
	                            $(this).siblings(".errormsg").show();
	                            $(this).parent().removeClass('valid');
	                            fildValidation(this, "Please enter your 10 digit mobile number");
	
	                            $('.mainError').show();
	                            mainError(this, 'Please enter your 10 digit mobile number');
	
	                            $('html, body').animate({ scrollTop: 0 }, 200);
	
	                        } else {
	                            if (value.indexOf('.') > -1) {
	                                $(this).siblings(".errormsg").show();
	                                $(this).parent().removeClass('valid');
	                                fildValidation(this, "Please enter your 10 digit mobile number");
	
	                                $('.mainError').show();
	                                mainError(this, 'Please enter your 10 digit mobile number');
	
	                                $('html, body').animate({ scrollTop: 0 }, 200);
	                            } else if (value.substr(0, 1) == 9 || value.substr(0, 1) == 8 || value.substr(0, 1) == 7 || value.substr(0, 1) == 6 || value.substr(0, 1) == 5) {
	                                $(this).siblings(".errormsg").hide();
	                                $(this).parent().addClass('valid');
	                                fildValidation(this);
	
	                                $('.mainError').hide();
	                                mainError(this);
	
	                            } else {
	                                $(this).siblings(".errormsg").show();
	                                $(this).parent().removeClass('valid');
	                                fildValidation(this, "Mobile number should start with 9 or 8 or 7 or 6 or 5");
	
	                                $('.mainError').show();
	                                mainError(this, 'Mobile number should start with 9 or 8 or 7 or 6 or 5');
	
	                                $('html, body').animate({ scrollTop: 0 }, 200);
	                            }
	
	
	
	                        }
	                        /*$(this).attr("type", "text");
	                        var newmono = value.substring(0, 5) + '-' + value.substring(5, 10);
	                        $(this).val(newmono);*/
	
	
	
	                    }
	                    else if ($(this).hasClass('gaurdianAge')) 
                      {
                      	
                          if ($(this).val())
                          {
                          	
                          	var age=$('#gaurdianNomineeAge').val();
                          	
                          	
              					if (age <18) 
              					{
              						$(this).siblings(".errormsg").show().text('A minor cannot be a guardian');
                          			fildValidation(this);
                          			$(this).siblings("label").removeClass("active");
                          		}else
                  				{
              					$(this).siblings(".errormsg").hide();
                                  fildValidation(this);
                                  $(this).siblings("label").addClass("active");
                                  }
              					
                          
                          } else {
                          	$(this).siblings(".errormsg").show().text('Please Enter valid Age');
                              fildValidation(this);
                              $(this).siblings("label").removeClass("active");
                          }
                      }
	                    else if ($(this).hasClass('datepickerVD')) {
                          if ($(this).val())
                          {
                          	var currentId=$(".nomineeYear:visible").attr('id');
                              var year=$('#'+currentId+'').val();
                              console.log(year);
                              var yearStart = year.substr(0, 2);
                              var today = new Date();
              				if(yearStart!="20" & yearStart!="19")
              				{
              					$(this).siblings(".errormsg").show();
                                  fildValidation(this);
                                  $(this).siblings("label").removeClass("active");
              				}else
              					{
              					if (year > today.getFullYear())
              					{
                  					$(this).siblings(".errormsg").show();
                                      fildValidation(this);
                                      $(this).siblings("label").removeClass("active");
                  				}else{
              					$(this).siblings(".errormsg").hide();
                                  fildValidation(this);
                                  $(this).siblings("label").addClass("active");}
              					}
                          } else {
                              $(this).siblings(".errormsg").show();
                              fildValidation(this);
                              $(this).siblings("label").removeClass("active");
                          }
                      }else if ($(this).hasClass('NomineeNameVD'))
                      {
                          var sval = $(this).val().trim();
                          $(this).val(sval);
                          if (!/^[a-zA-Z. ]*$/g.test($(this).val())) {
                              $(this).siblings(".errormsg").show();
                              fildValidation(this, "Only alphabets are allowed");

                          } else {
                              if ($(this).val().split(" ").length > 10) {
                                  $(this).siblings(".errormsg").show();
                                  fildValidation(this, "Ten then more space is not allowed");
                              } else {
                                  $(this).siblings(".errormsg").hide();
                                  fildValidation(this);
                              }

                          }
                      }
	                    else if ($(this).hasClass('dobcheckVD')){

                      	var year = $("#yyyy").val().trim();
                      	var month = $("#mm").val().trim();
                      	var day = $("#dd").val().trim();
                      	var yearStart = year.substr(0, 2);
                      	if (yearStart != "20" & yearStart != "19") {
                      		$(this).siblings(".errormsg").show().text('Please Enter valid DOB');
                      		fildValidation(this);
                      		$(this).siblings("label").removeClass("active");
                      	} else {
                      		var today = new Date();
                      		var age = today.getFullYear() - year;
                      		if (today.getMonth() + 1 < month || (today.getMonth() + 1 == month && today.getDate() < day)) {
                      			age--;
                      		}
                      		if (year.length != 4) {
                      			$(this).siblings(".errormsg").show();
                      			fildValidation(this);
                      			$(this).siblings("label").removeClass("active");
                      		}

                      		if (age >= 18) {
                      			$(this).siblings(".errormsg").hide();
                      			fildValidation(this);
                      			$(this).siblings("label").addClass("active");
                      		} else {
                      			if (year > today.getFullYear()) {
                      				$(this).siblings(".errormsg").show().text('Please Enter valid DOB');
                      				fildValidation(this);
                      				$(this).siblings("label").removeClass("active");
                      			} else {
                      				$(this).siblings(".errormsg").show().text('Minors cannot apply for our Fixed Deposit');
                      				fildValidation(this);
                      				$(this).siblings("label").removeClass("active");
                      			}
                      		}
                      	}


                      }
	                    
	                    else if ($(this).hasClass('AddressVD')) {
	
	                        var value = $(this).val();
	
	                       /* if (/[`~.<>;':"\/\[\]\|{}()=_+-]/g.test($(this).val())) {
	
	                            $(this).siblings(".errormsg").show();
	                            fildValidation(this, "<>?'=+-_^`~  not allowed");
	                        }*/
	                        
	                    	    
						  if(!value){
							   
							  $(this).siblings(".errormsg").show();
							  fildValidation(this); 
								
							  
							}
	               
	                        else {
	                            $(this).siblings(".errormsg").hide();
	                            fildValidation(this);
	                        }
	
	                    } else if ($(this).hasClass('PanVD')) {
	                        var value = $(this).val();
	                        if (value.length < 10 || value.length > 10) {
	                            $(this).siblings(".errormsg").show();
	                            fildValidation(this, "PAN number should be of 10 characters");
	                        }
	                      //  if (!/^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/.test($(this).val())) {
	                        if (!/^([a-zA-Z]){3}([pP]){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/.test($(this).val())) {
	  
	                        $(this).siblings(".errormsg").show();
	                            fildValidation(this, "Please enter your valid PAN number; eg: ABCPC9999A");
	                        } else {
	                            $(this).siblings(".errormsg").hide();
	                            fildValidation(this);
	                        }
	
	                    } else if ($(this).hasClass('emailVD')) {
	                        var sval = $(this).val().trim();
	                        $(this).val(sval);
	                        var a = $(this).val();
	                        var filter = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	                        if (filter.test(a)) {
	                            $(this).siblings(".errormsg").hide();
	                            fildValidation(this);
	                        } else {
	
	                            $(this).siblings(".errormsg").show();
	                            fildValidation(this, "Please enter your valid email ID");
	
	                        }
	                    } else if ($(this).hasClass('numberVD')) {
	
	                        if (!/^[0-9]+$/.test($(this).val())) {
	                            $(this).siblings(".errormsg").show();
	                            fildValidation(this);
	
	                        } else {
	                            $(this).siblings(".errormsg").hide();
	                            fildValidation(this);
	                        }
	
	
	                    }
	                   /* else if ($(this).hasClass('BankVD')) {
	
	                   	 var value = $(".BankVD").val();                  	                   	        
	                      if (value =="" || value==null) {
	                            $(this).siblings(".errormsg").show();
	                            fildValidation(this);
	                          
	                        } else {
	                            $(this).siblings(".errormsg").hide();
	                            fildValidation(this);
	                         
	                        }
	
	                    } */
	                    else if ($(this).hasClass('partnerVD')) {
	
	                        if (!/^[0-9]+$/.test($(this).val())) {
	                            $(this).siblings(".errormsg").show();
	                            fildValidation(this);
	
	                        } else {
	                            $(this).siblings(".errormsg").hide();
	                            fildValidation(this);
	                        }
	
	                    } 
	                    else if ($(this).hasClass('sliderAmunt')) {
	
	                    	 var value = $(this).val().replace(/\,/g,''); 
	                   	 if (!/^[0-9,]+$/.test($(this).val())) {
	                         $(this).siblings(".errormsg").show();
	                         fildValidation(this, "Enter number only");
	                	 }
	                   	 
	                   	 
	                   	 else if (value < 25000 || value > 50000000) {
	                            $(this).siblings(".errormsg").show();
	                            fildValidation(this, "Please enter value between 25000 to 50000000");
	                          
	                        } else {
	                            $(this).siblings(".errormsg").hide();
	                            fildValidation(this);
	                         
	                        }
	
	                   }
	                    else if ($(this).hasClass('sliderAmunt_2')) {
	
	                    	 var value = $(this).val().replace(/\,/g,''); 
	                      	 if (!/^[0-9,]+$/.test($(this).val())) {
	                            $(this).siblings(".errormsg").show();
	                            fildValidation(this, "Enter number only");
	                   	 }
	                      	 
	                      	 
	                      	 else if (value < 5000 || value > 1000000) {
	                               $(this).siblings(".errormsg").show();
	                               fildValidation(this, "Please enter value between 5000 to 1000000");
	                             
	                           } else {
	                               $(this).siblings(".errormsg").hide();
	                               fildValidation(this);
	                            
	                           }
	
	                      }
	                    else if ($(this).hasClass('customTenor')) {
	                   	 var value = $(this).val();
	                   	 
	                   	 
	                    if (value < 12 || value > 60) {
	                           $(this).siblings(".errormsg").show();
	                           fildValidation(this, "Enter Tenor between 12 to 60");
	
	                       }
	                       else {
	                           $(this).siblings(".errormsg").hide();
	                           fildValidation(this);
	                        
	                       }
	
	                   }
	                    else if ($(this).hasClass('customTenor_2')) {
	                      	 var value = $(this).val();
	                      	 
	                       if (value < 12 || value > 60) {
	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this, "Enter Tenor between 12 to 60");
	
	                          }
	                          else {
	                              $(this).siblings(".errormsg").hide();
	                              fildValidation(this);
	                           
	                          }
	
	                      }
	                   
	                    else if ($(this).hasClass('accountNumVD')) {
	                    	var accNum=$(".accountNumVD:visible").val();
	                        if (!/^[0-9]+$/.test(accNum)) {
	                            $(this).siblings(".errormsg").show();
	                            fildValidation(this);
	
	                        } else {
	                            $(this).siblings(".errormsg").hide();
	                            fildValidation(this);
	                        }
	
	                        }else if ($(this).hasClass('ifscCodeVD')) {
	
	                            if (!/^[A-Za-z]{4}[a-zA-Z0-9]{7}$/.test($(this).val())) {
	                                $(this).siblings(".errormsg").show();
	                                fildValidation(this);  
	
	                            }else {
	                            	
	                            	 var value = $(".ifscCheck:visible").val();
	                        		     value=value.toUpperCase();
	                        			 ifscValidator(value); 
	                            	
	                            	function ifscValidator(userValue){
	                            			
	                            			var data = {
	                            					 "ifscCode":userValue
	                            					 };
	                            			 
	                            				       
	                            			         $.ajax({ 
	                            			                                    
	                            			             url: "/fixed-deposit-application-form/ifscValidator", 
	                            			             type: "POST",
	                            			             data:JSON.stringify(data),
	                            			             async: false,
	                            			             contentType: 'application/json',
	                            			             error: function(response)
	                            				 			{
	                            				          	console.log("inside errror function");
	                            				             }  ,
	                            			             success: function(response) {            
	                            			            
	                            			            	var response = JSON.parse(response);
	                            			         //   response={"status":"success"};
	                            			            	 if((response.status == "success")){
	                            			            		 
	                            			            		 $(".ifscCheck").siblings(".errormsg").hide();
	                            			            		   
	                            			            	 }else{
	                            			            		 $(".ifscCheck").siblings(".errormsg").text("Please enter valid IFSC code").show();
	                            			            	
	                            			            		                                 		                                                               
	                            			            		
	                            			            	    }		
	                            			        					
	                            			            	 }   
	                            			      });
	                            			
	                            			}
	                            			
	                            			
	                            			
	                            	
	                            	
	                            	
	                              /*  $(this).siblings(".errormsg").hide();
	                                fildValidation(this);*/
	                            }
	
	                        }else if ($(this).hasClass('FullNameVD')) {
	                        var sval = $(this).val().trim();
	                        $(this).val(sval);
	                        if (!/^[a-zA-Z. ]*$/g.test($(this).val())) {
	                            $(this).siblings(".errormsg").show();
	                            fildValidation(this, "Only alphabets are allowed");
	
	                        } else if ($(this).val().split(" ").length == 1) {
	                            $(this).siblings(".errormsg").show();
	                        } else {
	                            if ($(this).val().split(" ").length > 10) {
	                                $(this).siblings(".errormsg").show();
	                                fildValidation(this, "Ten then more space is not allowed");
	                            } else {
	                                $(this).siblings(".errormsg").hide();
	                                fildValidation(this);
	                            }
	
	                        }
	                    } else if ($(this).hasClass('textVD')) {
	
	                        /*if (!/^[a-zA-Z ]*$/g.test($(this).val())) {*/
	                        	/*if (!/^[a-zA-Z0-9!@#\$%\^\&*\)\(+=._-]+$/g.test($(this).val())) {
	                            $(this).siblings(".errormsg").show();
	                            $(this).parent().removeClass('valid');
	                            fildValidation(this, "Only alphabets are allowed");
	
	                        } else {*/
	                            $(this).siblings(".errormsg").hide();
	                            $(this).parent().addClass('valid');
	                            fildValidation(this);
	                        /*}*/
	                    } else if ($(this).hasClass('captchaVD')) {
	
	                        if (!/^[A-Za-z0-9-]*$/g.test($(this).val())) {
	                            $(this).siblings(".errormsg").show();
	                            $(this).parent().removeClass('valid');
	                            fildValidation(this, "Please enter valid captcha");
	
	                        } else {
	                            $(this).siblings(".errormsg").hide();
	                            $(this).parent().addClass('valid');
	                            fildValidation(this);
	                        }
	                    } else if ($(this).hasClass('AnyValueVD')) {
	
	                        var value = $(this).val();
	
	                        if (!value) {
	
	
	                            $(this).siblings(".errormsg").show();
	                            $(this).parent().removeClass('valid');
	                            fildValidation(this);
	                        } else {
	                            $(this).siblings(".errormsg").hide();
	                            $(this).parent().addClass('valid');
	                            fildValidation(this);
	                        }
	
	                    }
	                }
	
	           // }
	        }
	
	
	
	    });
	
	  

	  function allFormFieldValidationCheck(ThisObj, eventType) {
	  	
	      var parent = ThisObj.parents("form").attr("id");
	      var error = 0;
	      $("#" + parent + " " + ".a_ReInput input").each(function() {

	          if (!$(this).hasClass("nomandetory")) {
	            //  if (!$(this).attr('disabled')) {
	                  if ($(this).val() == "") {
	                  	 if ($(this).hasClass('UploadVD')) {
	                  		 $(this).parent(".uploadDoc").siblings(".errormsg").show();
	                  		 
	                       }
	                      $(this).siblings(".errormsg").show();
	                      fildValidation(this);

	                  } else {

	                      if ($(this).hasClass('FirstNameVD')) {

	                          if (!/^[a-zA-Z]*$/g.test($(this).val())) {
	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this, "Only alphabets are allowed in name field");
	                              if ($(this).val().indexOf(" ") != -1) {
	                                  $(this).siblings(".errormsg").show();
	                                  fildValidation(this, "Space is not allowed in name field");
	                              }
	                          } else {
	                              if ($(this).val().length > 2) {
	                                  $(this).siblings(".errormsg").hide();
	                                  $('.mainError').hide();

	                              } else {
	                                  $(this).siblings(".errormsg").show();
	                                  fildValidation(this, "Minimum 3 characters required in name field");

	                                  $('.mainError').show();
	                                  mainError(this, 'Minimum 3 characters required in name field');
	                              }
	                          }

	                      } else if ($(this).hasClass('LastNameVD')) 
	                      {

	                          if (!/^[a-zA-Z]*$/g.test($(this).val())) 
	                          {
	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this, "Only alphabets are allowed in name field");
	                              if ($(this).val().indexOf(" ") != -1)
	                              {
	                                  $(this).siblings(".errormsg").show();
	                                  fildValidation(this, "Space is not allowed in name field");
	                              }
	                          } else {
	                              if ($(this).val().length > 2) {
	                                  $(this).siblings(".errormsg").hide();
	                                  $('.mainError').hide();

	                              } else {
	                                  $(this).siblings(".errormsg").show();
	                                  fildValidation(this, "Minimum 3 characters required in name field");

	                                  $('.mainError').show();
	                                  mainError(this, 'Minimum 3 characters required in name field');
	                              }
	                          }

	                      } else if ($(this).hasClass('PinCodeVD'))
	                      { 

	                          if (!/^[0-9]{6}$/.test($(this).val())) 
	                          {  
	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this, "Please enter 6 digit Pin Code, only digits");

	                          } else { $(this).siblings(".errormsg").hide(); }

	                      } else if ($(this).hasClass('otpVD')) {

	                          /*if(!/^[0-9]+$/.test($(this).val())){
	  								    $(this).siblings(".errormsg").show();
	  								    fildValidation(this,"Please enter 6 digit Pin Code, only digits");
	  								
	  								}else{$(this).siblings(".errormsg").hide();}*/

	                          var value = $(this).val();
	                          if (value.length < 6 || value.length > 6) {
	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this, "OTP entered is incorrect");
	                              $(this).siblings('.a_optlinMain').addClass('invalidOTP');
	                          } else {
	                              $(this).siblings(".errormsg").hide();
	                              $(this).siblings('.a_optlinMain').removeClass('invalidOTP');
	                          }

	                      } else if ($(this).hasClass('mobileVD')) {

	                          var value = $(this).val();

	                          if (value.length < 10 || value.length > 10) {
	                              if (eventType == 1) {
	                                  $(this).siblings(".errormsg").show();
	                                  fildValidation(this, "Please enter your 10 digit mobile number");
	                              }
	                          } else {
	                              if (value.indexOf('.') > -1) {
	                                  $(this).siblings(".errormsg").show();
	                                  fildValidation(this, "Please enter your 10 digit mobile number");
	                              } else if (value.substr(0, 1) == 9 || value.substr(0, 1) == 8 || value.substr(0, 1) == 7 || value.substr(0, 1) == 6 || value.substr(0, 1) == 5) {
	                                  $(this).siblings(".errormsg").hide();
	                                  fildValidation(this);
	                              } else {
	                                  $(this).siblings(".errormsg").show();
	                                  fildValidation(this, "Mobile number should start with 9 or 8 or 7 or 6 or 5");
	                              }
	                          }

	                      }
	                      else if ($(this).hasClass('gaurdianAge')) 
	                      {
	                      	
	                          if ($(this).val())
	                          {
	                          	
	                          	var age=$('#gaurdianNomineeAge').val();
	                          	
	                          	
	              					if (age <18) 
	              					{
	              						$(this).siblings(".errormsg").show().text('A minor cannot be a guardian');
	                          			fildValidation(this);
	                          			$(this).siblings("label").removeClass("active");
	                          		}else
	                  				{
	              					$(this).siblings(".errormsg").hide();
	                                  fildValidation(this);
	                                  $(this).siblings("label").addClass("active");
	                                  }
	              					
	                          
	                          } else {
	                          	$(this).siblings(".errormsg").show().text('Please Enter valid Age');
	                              fildValidation(this);
	                              $(this).siblings("label").removeClass("active");
	                          }
	                      }
	                      else if ($(this).hasClass('NomineeNameVD'))
	                      {
	                      var sval = $(this).val().trim();
	                      $(this).val(sval);
	                      if (!/^[a-zA-Z. ]*$/g.test($(this).val())) {
	                          $(this).siblings(".errormsg").show();
	                          fildValidation(this, "Only alphabets are allowed");

	                      } else {
	                          if ($(this).val().split(" ").length > 10) {
	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this, "Ten then more space is not allowed");
	                          } else {
	                              $(this).siblings(".errormsg").hide();
	                              fildValidation(this);
	                          }

	                      }
	                  }
	                      else if ($(this).hasClass('datepickerVD')) {
	                          if ($(this).val())
	                          {
	                          	var currentId=$(".nomineeYear:visible").attr('id');
	                              var year=$('#'+currentId+'').val();
	                              console.log(year);
	                              var yearStart = year.substr(0, 2);
	                              var today = new Date();
	              				if(yearStart!="20" & yearStart!="19")
	              				{
	              					$(this).siblings(".errormsg").show();
	                                  fildValidation(this);
	                                  $(this).siblings("label").removeClass("active");
	              				}else
	              					{
	              					if (year > today.getFullYear())
	              					{
	                  					$(this).siblings(".errormsg").show();
	                                      fildValidation(this);
	                                      $(this).siblings("label").removeClass("active");
	                  				}else{
	              					$(this).siblings(".errormsg").hide();
	                                  fildValidation(this);
	                                  $(this).siblings("label").addClass("active");}
	              					}
	                          } else {
	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this);
	                              $(this).siblings("label").removeClass("active");
	                          }
	                      }else if ($(this).hasClass('dobcheckVD')){

	                      	var year = $("#yyyy").val().trim();
	                      	var month = $("#mm").val().trim();
	                      	var day = $("#dd").val().trim();
	                      	var yearStart = year.substr(0, 2);
	                      	if (yearStart != "20" & yearStart != "19") {
	                      		$(this).siblings(".errormsg").show().text('Please Enter valid DOB');
	                      		fildValidation(this);
	                      		$(this).siblings("label").removeClass("active");
	                      	} else {
	                      		var today = new Date();
	                      		var age = today.getFullYear() - year;
	                      		if (today.getMonth() + 1 < month || (today.getMonth() + 1 == month && today.getDate() < day)) {
	                      			age--;
	                      		}
	                      		if (year.length != 4) {
	                      			$(this).siblings(".errormsg").show();
	                      			fildValidation(this);
	                      			$(this).siblings("label").removeClass("active");
	                      		}

	                      		if (age >= 18) {
	                      			$(this).siblings(".errormsg").hide();
	                      			fildValidation(this);
	                      			$(this).siblings("label").addClass("active");
	                      		} else {
	                      			if (year > today.getFullYear()) {
	                      				$(this).siblings(".errormsg").show().text('Please Enter valid DOB');
	                      				fildValidation(this);
	                      				$(this).siblings("label").removeClass("active");
	                      			} else {
	                      				$(this).siblings(".errormsg").show().text('Minors cannot apply for our Fixed Deposit');
	                      				fildValidation(this);
	                      				$(this).siblings("label").removeClass("active");
	                      			}
	                      		}
	                      	}


	                      } 
	                      
	                      else if ($(this).hasClass('AddressVD')) {

	                          var value = $(this).val();
	                          if(!value){
	   						   
	    						  $(this).siblings(".errormsg").show();
	    						  fildValidation(this); 							
	    						  
	    						}

	                         /* if (/[`~.<>;':"\/\[\]\|{}()=_+-]/g.test($(this).val())) {

	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this, "<>?'=+-_^`~  not allowed");
	                          } */
	                          else {
	                              $(this).siblings(".errormsg").hide();
	                              fildValidation(this);
	                          }


	                      } else if ($(this).hasClass('PanVD')) {

	                          var value = $(this).val();
	                          if (value.length < 10 || value.length > 10) {
	                              if (eventType == 1) {
	                                  $(this).siblings(".errormsg").show();
	                                  fildValidation(this, "PAN number should be of 10 characters");
	                              }
	                              error++;
	                          }
	                      //    if (!/^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/.test($(this).val())) {
	                        
	                              if (!/^([a-zA-Z]){3}([pP]){1}([a-zA-Z]){1}([0-9]){4}([a-zA-Z]){1}?$/.test($(this).val())) {

	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this, "Please enter your valid PAN number; eg: ABCPC9999A");
	                          } else {
	                              $(this).siblings(".errormsg").hide();
	                              fildValidation(this);
	                          }

	                      } else if ($(this).hasClass('emailVD')) {
	                          var sval = $(this).val().trim();
	                          $(this).val(sval);
	                          var a = $(this).val();
	                          var filter = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	                          if (filter.test(a)) {
	                              $(this).siblings(".errormsg").hide();
	                              fildValidation(this);
	                          } else {
	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this, "Please enter your valid email ID");
	                          }
	                      } else if ($(this).hasClass('numberVD')) {

	                          if (!/^[0-9]+$/.test($(this).val())) {
	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this);

	                          }

	                      }
	                      else if ($(this).hasClass('partnerVD')) {

	                          if (!/^[0-9]+$/.test($(this).val())) {
	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this);

	                          }

	                      }
	                     
	                      else if($(this).hasClass('sliderAmunt')) {

	                      	            	
	                      	 var value = $(this).val().replace(/\,/g,'');
	                      	  
	                      	   if (!/^[0-9,]+$/.test(value)) {
	                               $(this).siblings(".errormsg").show();
	                               fildValidation(this, "Enter number only");
	                      	 }
	                         	 
	                      	   else if (value < 25000 || value > 50000000) {
	                      		   $(this).siblings(".errormsg").show();
	                                  fildValidation(this, "Please enter value between 25000 to 50000000");
	                                
	                              } else {
	                              	 $(this).siblings(".errormsg").hide();
	                                  fildValidation(this);
	                               
	                              }

	                      }
	                      
	                      else if ($(this).hasClass('sliderAmunt_2')) {

	                      	 var value = $(this).val().replace(/\,/g,''); 
	                     	                	
	                     	if (!/^[0-9,]+$/.test($(this).val())) {
	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this, "Enter number only");
	                     	 }
	                        
	                     	else if (value < 5000 || value > 1000000) {
	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this, "Please enter value between 5000 to 1000000");
	                            
	                          } else {
	                              $(this).siblings(".errormsg").hide();
	                              fildValidation(this);
	                          }

	                     }
	                      else if ($(this).hasClass('customTenor')) {
	                      	 var value = $(this).val();
	                      
	                      	 if (value < 12 || value > 60) {
	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this, "Enter Tenor between 12 to 60");

	                          }
	                          else {
	                              $(this).siblings(".errormsg").hide();
	                              fildValidation(this);
	                           
	                          }

	                      } else if ($(this).hasClass('customTenor_2')) {
	                     	 var value = $(this).val();
	                       
	                  	 if (value < 12 || value > 60) {
	                          $(this).siblings(".errormsg").show();
	                          fildValidation(this, "Enter Tenor between 12 to 60");

	                      }
	                      else {
	                          $(this).siblings(".errormsg").hide();
	                          fildValidation(this);
	                       
	                      }

	                  }
	                      else if ($(this).hasClass('UploadVD')) {
	                     
	              			var fileexist =  $(this)[0].files[0].size; 
	              			var filename =  $(this).val();
	              			var extension = filename.replace(/^.*\./, '');

	              	        if(extension == filename){
	              	            extension = '';
	              	        }else{
	              	            extension = extension.toLowerCase();
	              	        }
	              						
	              	      
	              	       if(!(extension =="jpg" || extension =="jpeg" ||extension =="pdf")){
	              	    	   
	             			    $(this).parent(".uploadDoc").siblings(".errormsg").text("File format allowed are .pdf, .jpg, .jpeg").show();

	              		   }
	              	       else if(fileexist>4194305){
	          				 $(this).parent(".uploadDoc").siblings(".errormsg").text("Max file size allowed is 4mb").show();

	         			   }
	              	       else
	              	    	   {
	              	    	   $(this).parent(".uploadDoc").siblings(".errormsg").hide();
	              	    	   }
	              	    
	              			 }
	              		
	                      else if ($(this).hasClass('accountNumVD')) {

	                      	var accNum=$(".accountNumVD:visible").val();
	                          if (!/^[0-9]+$/.test(accNum)) {
	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this);

	                          }

	                      }else if ($(this).hasClass('ifscCodeVD')) {

	                          if (!/^[A-Za-z]{4}[a-zA-Z0-9]{7}$/.test($(this).val())) {
	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this);

	                          }

	                      } else if ($(this).hasClass('FullNameVD')) {
	                          var sval = $(this).val().trim();
	                          $(this).val(sval);
	                          if (!/^[a-zA-Z. ]*$/g.test($(this).val())) {
	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this, "Only alphabets are allowed");

	                          } else if ($(this).val().split(" ").length == 1) {
	                              $(this).siblings(".errormsg").show();
	                          } else {
	                              if ($(this).val().split(" ").length > 10) {
	                                  $(this).siblings(".errormsg").show();
	                                  fildValidation(this, "Ten then more space is not allowed");
	                              } else {
	                                  $(this).siblings(".errormsg").hide();
	                                  fildValidation(this);
	                              }
	                          }
	                      } else if ($(this).hasClass('textVD')) {

	                          /*if (!/^[a-zA-Z ]*$/g.test($(this).val())) {*/
	                          	/*if (!/^[a-zA-Z0-9!@#\$%\^\&*\)\(+=._-]+$/g.test($(this).val())) {
	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this, "Only alphabets are allowed");

	                          } else {*/
	                              $(this).siblings(".errormsg").hide();
	                              fildValidation(this);
	                          /*}*/
	                      } else if ($(this).hasClass('captchaVD')) {

	                          if (!/^[A-Za-z0-9-]*$/g.test($(this).val())) {
	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this, "Please enter valid captcha");

	                          } else {
	                              $(this).siblings(".errormsg").hide();
	                              fildValidation(this);
	                          }
	                      } else if ($(this).hasClass('AnyValueVD')) {

	                          var value = $(this).val();

	                          if (!value) {


	                              $(this).siblings(".errormsg").show();
	                              fildValidation(this);
	                          } else {
	                              $(this).siblings(".errormsg").hide();
	                              fildValidation(this);
	                          }

	                      }




	               //   }

	              }
	          }

	      });
	      
	     

	      $("#" + parent + " " + ".chechTC input").each(function() {
	          if (!$(this).hasClass("nomandetory")) {
	              if (!$(this).attr('disabled')) {
	                  if ($(this).hasClass('checkVD')) {

	                      if (!$(this).prop('checked')) {
	                          $(this).parent(".turmcon").siblings(".errormsg").show();
	                          fildValidation(this);

	                      } else {
	                          $(this).parent(".turmcon").siblings(".errormsg").hide();
	                          fildValidation(this);
	                      }
	                  }
	              }
	          }
	      });



	      $("#" + parent + " " + ".a_ReInput select").each(function() {
	          if (!$(this).hasClass("nomandetory")) {
	              if (!$(this).attr('disabled')) {
	              	
	                  if ($(this).val() == "" ) {
	                      $(this).siblings(".errormsg").show().css('color','#ea1212');
	                      fildValidation(this);
	                  } else {
	                      $(this).siblings(".errormsg").hide();
	                      fildValidation(this);
	                  }
	              }
	          } 
	      });


	  /*        var days = $(".dayselect").val();
	      var months = $(".monthselect").val();
	      var years = $(".yearselect").val();

	      if (days && months && years) {
	          $(".yearselect").addClass("valid");
	          $(".yearselect").parent().siblings(".errormsg").hide();
	      } else {
	          $(".yearselect").removeClass("valid");
	          $(".yearselect").parent().siblings(".errormsg").show();
	      }*/


	  }


	  $(".submitBTN .validBtn").click(function(e) {
	      e.preventDefault();
	      var ThisObj = $(this);
	      var NoError = allFormFieldValidationCheck(ThisObj, 1);

	  });


	  $(".a_ReInput input").focus(function() {
	      
	      if (!$(this).attr('readonly')) {
	   $(this).siblings(".errormsg").show().css('color','#000000');
	   fildValidation(this);
	      }
	       });
	  
	  $(".a_ReInput select").focus(function() {
	   
	   if (!$(this).attr('readonly')) {
	  $(this).siblings(".errormsg").show().css('color','#000000');
	  fildValidation(this);
	   }  
	  });

	  $('#panCarVerify .validBtn').click(function(e)
	  		 {	
	     e.preventDefault();	
	     trackPageviewScriptUpdated('Retry_Payment');
	     var ThisObj = $(this);
	      var NoError = allFormFieldValidationCheck(ThisObj, 1);
	    
	     if($('.a_radiodeposit input[name="banking_det_1"]').is(':checked'))
         {
        	 
             $('input[name="banking_det_1"]').parents('.a_radiodeposit').siblings('.errormsg').hide();
         }else
         {
        	
             $('input[name="banking_det_1"]').parents('.a_radiodeposit').siblings('.errormsg').show();
         }
	    
	    
	    setTimeout(function() 
	    		{	
	        var totalerror = 0;	
	        var firsterr = 0;	
	        $("#panCarVerify .errormsg").each(function(i) {	
	            if ($(this).css("display") == "block"){	
	                totalerror++;	
	                if (firsterr == 0) {	
	                    firsterr = $(this).parent(".a_ReInput").offset().top;	
	                }	
	            }	
	        });	
	        if (totalerror == 0)
	        {	 $('#panCarVerify .a_blueBtnPart button .fd_sdp_loder').show(); 
	        	retryPayment();
	        	
	        } else {
	            if ($(window).width() < 900) {
	                $('html, body').animate({
	                    scrollTop: firsterr - 55
	                }, 200);
	            } else {
	                $('html, body').animate({
	                    scrollTop: firsterr - 30
	                }, 200);
	            }
	        }
	    }, 500);     

	  });


	  function retryPayment()
	  {
		 
	    	
	  	var accountNumBD=$("#fdaccountNumber").val().trim();
	  	var bankNameBD= $(".bankNameSelect").val().trim();
	  	var ifscCodeBD= $("#fdifscCode").val().trim();
	  	var customerId=$("#customerId").val();
	  	 var paymentOption = $('input[name="banking_det_1"]:checked').val();
	  	 
	  	var finalData = {
	  	    	"accountNumBD":accountNumBD,
	  	    	"bankNameBD":bankNameBD,        
	              "ifscCodeBD":ifscCodeBD,
	              "customerId":customerId,
	              "paymentOption":paymentOption  
	      };
	  	finalData= customenc(JSON.stringify(finalData));
	  	$.ajax({                                  	     
	  	        url: "/fixed-deposit-application-form/retryPay",
	  	        type: "POST",
	  	        contentType: 'application/json',
	  	        data:    finalData,                              
	  	        error: function(response) {
	  	        alert("something went wrong try after sometime");
	  	        newrelic.noticeError(new Error('custom_error'), { attribute1: "Technical Error Page in retry pay ", attribute2: "/fixed-deposit-application-form/retryPay", attribute3: response.status, FdcNumber: customerId });
	  	        $('.ErrorPage').show();
	  	        },   
	  		success: function(response) {            
	  			var encResponse = JSON.parse(response);
	           	var decResponse = getOfferobje(encResponse);
      	        	    				             	
      	         var response = JSON.parse(decResponse);          
	      	 if((response.status == "success"))
	      	 {
	      		 
	      		 if((response.checksum != null))
	      		 {
	            		
	           			$("#fdmsg").val(response.checksum);
	           		    $("#fdbilldesk").submit();
	           		 $('#panCarVerify .a_blueBtnPart button .fd_sdp_loder').hide();  
	      		 
	      		 }else
	      		 {
	      			$('#panCarVerify .a_blueBtnPart button .fd_sdp_loder').hide(); 
	      		    if(response.tryCount=='2'){alert(response.paylimit);}
	      			 $("#panCarVerify").hide();
	      		 }
	      		 
	      		  
	      	 }else{
	      		 $('#panCarVerify .a_blueBtnPart button .fd_sdp_loder').hide(); 
	      		 alert("Something went wrong ! Please try again. ");
	       			 
	      		 
         		 var err = new Error('custom_error');
         		 newrelic.noticeError(err, { attribute1: "Technical Error Page in retry pay ", attribute2: "/fixed-deposit-application-form/retryPay", attribute3: "Fail", FdcNumber: customerId });
         		$('.ErrorPage').show();
	      	    }		
	  					
	      	 }  
	     });
	  	
	  }

	  /****************FD BANK CHANGE Start************/
	  
	  $('#fdbankName').change(function()
	    	    {
	    	var className=$(this).find('option:selected').attr('class');
	    	console.log(className);
	    	var sliderAmunt =	$('#txnAmountForFail').val(); //user enter amount
		    sliderAmunt = sliderAmunt.replace(/\,/g,'');
		    sliderAmunt = Number(sliderAmunt);
		    console.log(sliderAmunt); 
		    $(this).parents('.bnkMethod').siblings().slideUp(200);
		    if(className=="bothBankOption" && sliderAmunt <= 100000)
		    	{
		    		$('#fdupiCheck').show();
		    		$('#fdNetBankCheck').show();
		    		  $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
		    		 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
		    	}
		    else if(className=="bothBankOption" && sliderAmunt >100000)
	    	{
	    	     $('#fdNetBankCheck').show();
	    	     $('#fdupiCheck').hide();
	    		  $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
	    		 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
	    	}
		     else if(className=="netBankShow" && sliderAmunt>=100001)
		    {
		    		$('#fdNetBankCheck').show();
		    	    $('#fdupiCheck').hide();
		    	    $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
		    	 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
		    }
		     else if(className=="netBankShow" && sliderAmunt<=100000)
		     {
		     		$('#fdNetBankCheck').show();
		     	     $('#fdupiCheck').hide();
		     	    $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
		     	 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
		     }
		    else if(className=="upihide" && sliderAmunt>=100001)
		    	{
		    	 $(this).parents('.a_ReInput').siblings('.a_opplsLink').slideDown(200);
		    	  $(this).parents('.a_ReInput').siblings('.bnkMethod').hide();
		    	 
		    	  $("#fdaccountNumber").val("");
		    	  $("#fdifscCode").val("");
		    	
		    	  $('input[name="banking_det_1"]').attr('checked',false);
		    	}
		    else if(className=="upihide" && sliderAmunt<=100000)
		    	{
		    	
		    		$('#fdupiCheck').show();
		    		$('#fdNetBankCheck').hide();
		    		  $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
		    		 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
		    	}
		    else if(className=="noBankSelected")
		    	{
		    	 
		    	 $("#panCarVerify .a_radiodeposit_2 input").val("");
		    	 $(this).parents('.a_ReInput').siblings('.bnkMethod').hide();
		    	 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
		    	}
		    $("#panCarVerify .a_radiodeposit_2").hide();
		    $('input[name="banking_det_1"]').attr('checked',false);   
	    	    });
	  
	  /****************FD BANK CHANGE End************/
	  /****************SDP BANK CHANGE Start************/
	    $('#sdpbankName').change(function()
	    	    {
	    	var className=$(this).find('option:selected').attr('class');
	    	console.log(className);
	    	 var sliderAmunt =	$('#txnAmountForFail').val();
	    	 sliderAmunt = sliderAmunt.replace(/\,/g,'');
	    	 sliderAmunt = Number(sliderAmunt);
	    	 console.log(sliderAmunt);
	    	 $(this).parents('.bnkMethod').siblings().slideUp(200);
	    	  $("#sdpnomeeni .a_radiodeposit_2 input").val('');
	    	if(className=="bothBankOption" && sliderAmunt <= 100000)
	    	{
	    	
	    		$('#SdpupiCheck').show();
	    		$('#SdpNetBankCheck').show();
	    		  $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
	    		 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
	    		 $('#SdpNetBankCheck input').prop('checked', true).change(); 
	    		 
	    	}
	    	 else if(className=="bothBankOption" && sliderAmunt >=100001)
	     	{
	    		 $('#SdpupiCheck').hide();
	     		$('#SdpNetBankCheck').show();
	     		  $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
	     		 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
	     		$('#SdpNetBankCheck input').prop('checked', true).change();
	     		
	     	}
	 	 else if(className=="netBankShow" && sliderAmunt>=100001)
	    {
	    		$('#SdpNetBankCheck').show();
	    	    $('#SdpupiCheck').hide();
	    	   $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
	    	 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
	    	 $('#SdpNetBankCheck input').prop('checked', true).change();
	    	 
	    }
	 	 else if(className=="netBankShow" && sliderAmunt<=100000)
	     {
	     		$('#SdpNetBankCheck').show();
	     	    $('#SdpupiCheck').hide();
	     	   $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
	     	 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
	     	$('#SdpNetBankCheck input').prop('checked', true).change(); 
	     	
	     }
	    else if(className=="upihide" && sliderAmunt >=100001)
	    	{
	    	 $(this).parents('.a_ReInput').siblings('.a_opplsLink').slideDown(200);
	    	  $(this).parents('.a_ReInput').siblings('.bnkMethod').hide();
	    	  $("#sdpnomeeni .a_radiodeposit_2").hide();
	    	  $("#sdpaccountNumber").val("");
	    	  $("#sdpifscCode").val("");
	    	  $("#sdpnomeeni .a_radiodeposit_2").hide();
	    	  $('input[name="banking_det_2"]').attr('checked',false);
	    	}
	    else if(className=="upihide" && sliderAmunt<=100000)
	    	{
	    	
	    		$('#SdpupiCheck').show();
	    		$('#SdpNetBankCheck').hide();
	    		$('#SdpupiCheck input').prop('checked', true).change(); 
	    		  $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
	    		 $("#sdpnomeeni .a_radiodeposit_2").show();
	    		 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
	    	}
	    else if(className=="upishow" && sliderAmunt<=100000)
	{
	
		$('#SdpupiCheck').show();
		$('#SdpNetBankCheck').hide();
		$('#SdpupiCheck input').prop('checked', true).change(); 
		  $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
		 $("#sdpnomeeni .a_radiodeposit_2").show();
		 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
	}
	    else if(className=="noBankSelected")
		{
	    	
	  	 $("#sdpnomeeni .a_radiodeposit_2 input").val("");
	     $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
	     $("#fdNominee .a_radiodeposit_2").show();
	  	 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
	  	$('#SdpNetBankCheck input').prop('checked', true).change();
		}
	    	
	    	
	    	    });
	  /****************SDP BANK CHANGE END************/
	  
	    $('.a_ReInput label a.a_viewPament').click(function(){
	        $('.a_blackoverlay').toggle();
	        $('.a_termsAndConPart_4').toggleClass('a_termsAndConPart_4Show');
	    });
	        $('.a_termsAndConPart_4 .a_closePop').click(function(){
	            $('.a_blackoverlay').hide();
	            $('.a_termsAndConPart_4').removeClass('a_termsAndConPart_4Show');
	        });
	        $('.a_leftSide .a_opplsLink a').click(function(){
	            $(this).parents('.a_part_5').hide();
	            $(this).parents('.a_part_5').siblings('.a_part_6').show();
	        });
	        $('.a_blackoverlay').click(function(){
		    	 $(this).css('display','none');
		       
		        $('.a_termsAndConPart_4').removeClass('a_termsAndConPart_4Show');
		    });  
	    $('.a_rightSide .a_opplsLink a').click(function(){
	            $('.a_blackoverlay').toggle();
	            $('.a_termsAndConPart_4').toggleClass('a_termsAndConPart_4Show');
	        });   
	        $('input[name="banking_det_1"],input[name="banking_det_2"]').change(function(){
	            $(this).parents('.a_radiodeposit').siblings('.errormsg').hide();
	            $(this).parents('.bnkMethod').siblings().slideDown(200);
	            $(".bnkMethod").siblings(".a_opplsLink").css('display','none');
	            $("#panCarVerify .a_radiodeposit_2").show();
	           
	            
		    	
	        });
	  
	  
});


$('.planCta a').click(function(){
	var tccheck=$("input[name='pocketTC']:checked"). val();
	var payotption=$("input[name='paytype']:checked"). val();
	
	if(tccheck==undefined){
	$("#tcerror").show();
	}else if(payotption==undefined)
		{
		if(tccheck=="tNc")
			{
				$("#tcerror").hide();
			}
		alert("Please select payment Mode to get redirected");
		}
		else if(tccheck=="tNc" && payotption=="Pay online"){
	window.location.href = "https://www.bajajfinserv.in/new-pocket-insurance-application-form?644d504f6b46=7b661415&utm_source=fdthankyoupage";
	}
	});

	function PIcallDATA(){
	var payotption=$("input[name='paytype']:checked"). val();
	var context;
	if(payotption=="Pay online"){
	context="walletFDPayment";
	}else if(payotption=="Auto debit"){
	context="fdAddSmartDetails";
	}
	var customerId=$("#customerId").val();
	if(customerId==""||customerId==" "||customerId==undefined){
	alert("customer ID not present")
	}else if(customerId!=""&&customerId!=" "&&customerId!=undefined){
	PIcallservice(context,customerId);
	}
	}

	function PIcallservice(context,fdcustomerId){
	//alert("*****"+context);
	//alert("****"+fdcustomerId);
	var customerId=fdcustomerId;
	var finalData = {};
	var currentDate = new Date();
	var currentDateTime = currentDate.getDate() + '/' + (currentDate.getMonth()+1) + '/' + currentDate.getFullYear() + ' ' + currentDate.getHours() + ':' + currentDate.getMinutes() + ':' + currentDate.getSeconds();
	var dt = new Date();
	var time =dt.getDate() + "" + (dt.getMonth()+1) +""+ dt.getFullYear()+""+ dt.getHours() + "" + dt.getMinutes() + "" + dt.getSeconds() + "" + dt.getMilliseconds() ;

	finalData.productCode = "WP"; 
	finalData.fdCustomerID = "FDC000000000094"; 
	finalData.fdCustomerID = customerId;
	finalData.formId ="YD67"; 
	finalData.policyName ="Wallet Care"; 
	finalData.formName ="Wallet Care Application Form"; 
	finalData.formId ="YD67";  
	finalData.policyPartner="CPP"; 
	finalData.title="Mr"; 
	finalData.productId="WALLET";
	finalData.premium="399"; 
	finalData.partner="CPP";


	var pageURL=window.location.href;
	finalData.PageUrl =pageURL; 
	finalData.lastClick =pageURL;
	finalData.unqiuecodeId = time ;          
	finalData.wcmtime=currentDateTime; 	           
	finalData.createon=currentDateTime;
	finalData=JSON.stringify(finalData);
	//alert(finalData);

	$.ajax({                                  	     
		 	        url: "https://www.bajajfinserv.in/RevampPocketWallet/"+context,
		 	        type: "POST",
		 	        contentType: 'application/json',
		 	        cache: false,    
		 	        async: true,                
		 	        data:  finalData,                                 
		 	        error: function(response) {
		 	         alert("something went wrong try after sometime");		  
		 	        },   
					success: function(response) { 			 	          
		   		  	 console.log(response);         
			   		 var redirecturl= response.replace("redirect:", "");
		       		  window.location.href=redirecturl; 
		 	       }  
			   });
	}
	
	
	
	 /********************************************video Functionality Start here**************************************************/

    $('.a_bannerVidModem > a').click(function(){
        $(this).parent().hide();
        $(".a_bannerVidModem > div").empty()
    }); 
        
    $('.p_videoplayicon').click(function(){
    	
        $('.a_bannerVidModem').show();
    });     
        
    $('body').on('click', '.p_videoplayicon', function() 
    		{
  
    	var videosrc= $(this).find(".videoUrlId div").attr("data-kvideo-id");
        var e = '<iframe id="videoPlay" width="560" height="315" src="https://videos.bajajfinserv.in/kapsule/'+videosrc+'/v3/embedded" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>'
       
        $(".a_bannerVidModem > div").html(e);

        
        setTimeout(function(){
                var symbol = $("#videoPlay")[0].src.indexOf("?") > -1 ? "&" : "?";
                $("#videoPlay")[0].src += symbol + "autoplay=1";
            },600);
    });


           
    $('.videoSliderPart').slick({
    	  dots: false,
    	  infinite: false,
    	  speed: 300,
    	  //autoplay: true,
    	  slidesToShow: 3.9,
    	  slidesToScroll: 1,
    	  responsive: [
    	    {   
    	      breakpoint: 1024,
    	      settings: {
    	        slidesToShow: 3.1,
    	        slidesToScroll: 1,
    	        infinite: true,
    	        dots: true
    	      }
    	    },
    	    {
    	      breakpoint: 600,
    	      settings: {
    	        slidesToShow: 1.1,
    	        slidesToScroll: 1
    	      }
    	    },
    	    {
    	      breakpoint: 480,
    	      settings: {
    	        slidesToShow: 1.1,
    			  arrows:false,
    	        slidesToScroll: 1
    	      }
    	    } 
    	  ]
    	});
    
    /********************************************video Functionality End here***************************************************/
    /********************************************page view start here***************************************************/
    
    function trackPageviewScriptTy(id,revenue)
    {
    	console.log("id>>");
    window._uxa = window._uxa || [];
        window._uxa.push(['ecommerce:addTransaction',
    	{
            'id': id,               
            'revenue': revenue,  
            'shipping': '0',      
            'tax': '0'           
        }]);
        window._uxa.push(['ecommerce:send']);
    }
    /*********************************************page view start End here***************************************************/
   

    function trackPageviewScriptUpdated(para)
	{
		
		window._uxa = window._uxa || [];
		window._uxa.push(['trackPageview', window.location.pathname+window.location.hash.replace('#','?__') + '?cs-form-step='+para+'']);
	}
    
    
    function getOfferobje(encryptedResponsedata){
    	var iterationCount = 1000;
    	var keySize = 128;
    	var aesUtil = new AesUtil(keySize, iterationCount);
    	var onloadoffer = "F27D5C9927726BCEFE7510B1BDD3D137";
    	var offloadoffer = "abcdef";
    	
    	var offervalue = encryptedResponsedata.offjson;
    	
    	 var ciphertext1newOffer = aesUtil.decrypt(encryptedResponsedata.newoffer, onloadoffer, offloadoffer, encryptedResponsedata.offjson);
    	
    	
		 ciphertext1newOffer=JSON.parse(ciphertext1newOffer);
		 var newciphertext1newOffer = ciphertext1newOffer.offer;
		 
		 newciphertext1newOffer=newciphertext1newOffer.replace(/~/g,',');
		 newciphertext1newOffer=newciphertext1newOffer.split(',');
	    	
	    	 var offer = {};
	    	 
	    	 for(var i=0; i<newciphertext1newOffer.length; i++)
			 {
			     var keyValue = newciphertext1newOffer[i].split(":");
			     offer[keyValue[0]] = keyValue[1];
				
				 
			 }
	    	 
	    	 var offersa=offer.off1;
			 var offeri=offer.off2;
			 var offerp=offer.off3;
			 var offerED=offer.off4;
		 
    
    	   
    	var ciphertext1 = aesUtil.decrypt(offersa, offeri, offerp, offerED);
            
    	
            return ciphertext1;
    } 
    function customenc(encdata)
  	{
  	  var val = encdata;   
  	  var Offercheckval=$('#reactRetryone').val().trim();
  	var Offercheckvall=$('#reactRetryTwo').val().trim();
  	  var rkEncryptionKey = CryptoJS.enc.Base64.parse(Offercheckval);
  	  var rkEncryptionIv = CryptoJS.enc.Base64.parse(Offercheckvall);
  	  var utf8Stringified = CryptoJS.enc.Utf8.parse(val);
  	  var encrypted = CryptoJS.AES.encrypt(utf8Stringified.toString(), rkEncryptionKey,
  	  {mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7, iv: rkEncryptionIv});
  	  var value123= encrypted.ciphertext.toString(CryptoJS.enc.Base64);
  	  return value123;
  	  }
    
    
    /***************************************************VIZ layer call**************************************/
    
    function vizLayer(vizoffer,vizofferamount,vizofferId,vizofferflag,vizoffercustomerId)
    {
    	
   	 window.vizLayer = [];
    	
    	try {

    		window.vizLayer.push({  

    		offer_title: vizoffer,

    		offer_amount: vizofferamount,

    		offerId: vizofferId,

    		api_flag: vizofferflag,

    		customerId: vizoffercustomerId

    		}), pixel.parse("ajax call")

    		} catch (e) {

    		console.log(e.message)

    		}

    		console.log("vizLayer done");
    }
    			        /***************************************************VIZ layer call**************************************/
    				         
    
    /*************30-Nov-2021 ************/


    
