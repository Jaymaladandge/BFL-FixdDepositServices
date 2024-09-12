		$(document).ready(function() {
			
			
			var checkPCodeglobal=false;
			var fdcNumber="";
			var isActionInProgress = 'No';
			var isPayActionInProgress = 'No';
			var isFDPayActionInProgress= 'No';
			var isSDPPayActionInProgress= 'No';
			var city="";
			var mobileNo="";
			var jwtToken = "";
			var version = "unkown";
			var brwser = "unkown";
			const userAgent = navigator.userAgent;
			var whatsAppCheck="";
			var panValicationCount = "";
			var panNo="";
			var etbFlag ="";
			
			document.ondragstart = function (e) {
				e.preventDefault();
				return false;
				}

				document.oncontextmenu = function (e) {
				e.preventDefault();
				return false;
				}

				function disableControls (e) {
				const keysToBlock = ['a','b','c','f','p','s','u','x','shift','i','j','f12'];
				const ifActionMapsToBlockedKeys = keysToBlock.includes(e.key.toLowerCase());
				if ((e.ctrlKey || e.metaKey) && ifActionMapsToBlockedKeys) {
				e.preventDefault();
				return false;
				}
				}

				function disableInspectKeys (e) {
				e.preventDefault();
				const inspectKeys = ['Control', 'Alt', 'F12'];
				const inspectKeysCodes = [91,93,224,123];

				if (inspectKeys.includes(e.key) || inspectKeysCodes.includes(e.keyCode)) {
				e.preventDefault();
				return false;
				}
				}


				$(document).off().on('keydown keyup keypress', function (e) {
				disableControls(e);
				});
				
				
				
				var newBRCode;
				
				/***************************************************Script JS Start Here****************************************/
				


			    $(".popular_banks ul li a").click(function(){
			      $(this).parent().addClass('active');
			      $(this).parent().siblings().removeClass('active');
			        
			        
			    });
			                                   
			    $(document).click(function(){
			        $('.tooltipMain').hide();
			    });
			    
			      $(".popular_amt ul li").click(function(){
			        $(this).addClass('active');
			        $(this).siblings().removeClass('active');
			    });
			    
			    if($('.tenor_section').length > 0){
			    
			      $('.custom_ternor .slider_title label input').blur(function () {
			          var inputval = $(this).val();
			          inputval = inputval.replace(/,/g, '');
			          var sliderValueSet = $(this).parents('.slider_title').siblings('input').slider();
			          sliderValueSet.slider('setValue', inputval);
			      });
			    
			        $('#tenormonth').slider({
			            formatter: function (value) {
			                $('.tenormonth').val(value).change();
			            }
			        });
			    
			    }
			    
			    
			     $('.find_ifsc').click(function(){
			        $('.ifsc_popup').css('display', 'flex');
			     });
			    
			        
			     $('.cls_popup').click(function(){
			        $('.ifsc_popup').css('display', 'none');
			     });
			    
			    $('.yes_no_toggle li a').click(function(){
			        $(this).addClass('active');
			        $(this).parent().siblings().children('a').removeClass('active');
			    });
			    
			    $('.close_declare_modal, .disclaimer_btn button').click(function(){
			        $('.declaration_BG').hide();
			        $('body').css('overflow','auto');
			    });
			    
			    
			    function validornotNew(th) {
			        if (!th.hasClass("nomandetory")) {
			            if (th.siblings(".errormsg").css("display") == "none") {
			                th.parents('.inputMainBlock').addClass("valid").removeClass("invalid");
			            } else {
			                th.parents('.inputMainBlock').removeClass("valid").addClass("invalid");
			            }
			        }
			    }
			    $('.inputMainBlock input').blur(function () {
			        $(this).parent().removeClass('active');

			        var th = $(this);
			        setTimeout(function () {
			        	validornotNew(th)
			        }, 300);
			    });
			    $('.inputMainBlock select').change(function () {
			        $(this).parent().removeClass('active');

			        var th = $(this);
			        setTimeout(function () {
			        	validornotNew(th)
			        }, 300);
			    });
			    
			    
			    setInterval(function () {
			    
			    if($('.declaration_BG').css('display') == 'flex')
			        {
			            $('body').css('overflow','hidden');
			        }
			     }, 0);
			    
			    
			    $('.list_intrest_payout li label input[type="radio"]').change(function(){
			        
			        $(this).parents('li').addClass('active');
			        $(this).parents('li').siblings().removeClass('active');
			        
			        var getselect = $("input[name='int_payout']:checked").val();
			        
			        if(getselect == "onmaturity"){
			            $(this).parents('.intrest_payout').siblings('.offer_section').css("display","flex");
			        }else{
			            $(this).parents('.intrest_payout').siblings('.offer_section').css("display","none");
			        }
			        
			        
			    });
			    
			    
			    $('.tenor_lists label input[type="radio"]').change(function(){
			        
			        $(this).parent('label').addClass('active');
			        $(this).parent('label').siblings().removeClass('active');
			        
			        var gettenor = $("input[name='tenor']:checked").val();

			        if(gettenor == "60"){
			            $(this).parents('.tenor_block').siblings('.offer_section').css("display","flex");
			            $(this).parents('.tenor_block').siblings('.tenor_section').hide();
			        }else if(gettenor == "custom"){
			            $(this).parents('.tenor_block').siblings('.tenor_section').show();
			            $(this).parents('.tenor_block').siblings('.offer_section').css("display","none");
			        }else{
			            $(this).parents('.tenor_block').siblings('.offer_section').css("display","none");
			             $(this).parents('.tenor_block').siblings('.tenor_section').hide();
			        }
			        
			        
			    });
			    
			$('.amt_field input').keyup(function(){
			    var getamtvalue = $(this).val(); 
			    $(this).val(inrFormat(getamtvalue));
			    //amount_in_words(getamtvalue);
			    //$('.amt_words').text(amount_in_words(getamtvalue));
			    
			    
			});
			    
			    
			     function inrFormat(x) { 
					x=x.toString();
					if (x.indexOf(',') > -1) {
							x = x.replace(/\,/g,'');
					}
					var lastThree = x.replace(/\D/g, '').substring(x.length-3);
					var otherNumbers = x.substring(0,x.length-3);
					if(otherNumbers != ''){
					lastThree = ',' + lastThree;}
					var res = otherNumbers.replace(/\D/g, '').replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree;
					return res;
				}
			    
				
							$("#fdInvest").click(function(e) {
								
								var sectionHeading=$('.grow_saving_part .fd_com_title').text().trim();
								dataLayerDynamic('button_click',sectionHeading,'Fixed Deposit','Fixed Deposit | Invest Now');
								
								fdContent();
								$('#fdInterestTable').show();
									e.preventDefault();
									var getLink = $(this).attr('data-href');
									$(this).parents('.a_part_1').hide();
									$('.'+getLink).show();
									$('#calc').val("FD");
									$( "#mobileNO" ).focus();
									$('.track_step').removeClass('track_step_Sdp');
									trackPageviewScript('Initial_Landing_Page_FD');
								});
								$("#sdpInvest").click(function(e) {
									
									var sectionHeading=$('.grow_saving_part .fd_com_title').text().trim();
									var clickVal=$('.fd_falfpart .green_card_main .s_block .tab_ul li').find('.active').text();
									var clickText=' Systematic Deposit Plan | '+clickVal+' | Invest Now';
									dataLayerDynamic('button_click',sectionHeading,'Systematic Deposit Plat',clickText);
								
									sdpContent();
									$('#sdpInterestTable').show();
									e.preventDefault();
									var getLink = $(this).attr('data-href');
									$(this).parents('.a_part_1').hide();
									$('.'+getLink).show();
									$('#calc').val("SDP");
									
									$('#investType').html("Book new SDP");
									$('#investment').html("SDP");
									
									$( "#mobileNO" ).focus();
									$('.track_step').addClass('track_step_Sdp');
									trackPageviewScript('Initial_Landing_Page_SDP');
								});
								
								$("#fdInvestNew").click(function(e) {
									var sectionHeading=$('.investOnlinePart .fd_com_title').text().trim();
									dataLayerDynamic('button_click',sectionHeading,'','Fixed Deposit |Invest Now');
								
									fdContent();
									$('#fdInterestTable').show();
									e.preventDefault();
									var getLink = $(this).attr('data-href');
									$(this).parents('.a_part_1').hide();
									$('.'+getLink).show();
									$('#calc').val("FD");
									$( "#mobileNO" ).focus();
									$('.track_step').removeClass('track_step_Sdp');
									trackPageviewScript('Initial_Landing_Page_FD');
									});
								$("#sdpInvestNew").click(function(e) {
									
									var sectionHeading=$('.grow_saving_part .fd_com_title').text().trim();
									dataLayerDynamic('button_click',sectionHeading,'','Systematic Deposit Plan |Invest Now');
								
									sdpContent();
									$('#sdpInterestTable').show();
									e.preventDefault();
									var getLink = $(this).attr('data-href');
									$(this).parents('.a_part_1').hide();
									$('.'+getLink).show();
									$('#calc').val("SDP");
									
									$('#investType').html("Book new SDP");
									$('#investment').html("SDP");
									
									$( "#mobileNO" ).focus();
									$('.track_step').addClass('track_step_Sdp');
									trackPageviewScript('Initial_Landing_Page_SDP');
									});
			    
			    
						
								$('.green_card_main .s_block > a').click(function(){
							        $('.part_1').hide();
							        $('.part_2').show();
							        
							        $('.interestRatePopInner').hide();
							        
							        if($(this).attr('data-tab') == 'FD'){
							            fdContent();
							            $('#fdInterestTable').show();
							            $('.track_step').removeClass('track_step_Sdp');
							        }else if($(this).attr('data-tab') == 'SDP'){
							            sdpContent();
							            $('#sdpInterestTable').show();
							            $('.track_step').addClass('track_step_Sdp');
							            $('#investType').html("Book new SDP");
							            $('#investment').html("SDP");
							        }
							        
							    });



			    
			    
			    function fdContent(){
			        $('.bookCon_1 > h1').text('Start saving with Bajaj Finance FD in few steps');
			        $('.fd_com_title').html('Earn more with our Fixed Deposit');
			        //$('.moreDet1').text('Additional 0.10% p.a. interest if you invest online');
			        $('.moreDet2').text('Deposits starting Rs. 15,000');
			        $('.moreDet3').text('Highest Safety- Rated CRISIL AAA/ STABLE and [ICRA]AAA(Stable)');
			        $('.moreDet4').text('Up to 0.25% p.a. rate benefit for senior citizens');
			        $('.newPDFtnc').attr('href','/fixed-deposit-application-form/resources/pdf/FD_T&C.pdf');
			        $('.otpTncPdf').attr('href','/fixed-deposit-application-form/resources/pdf/FD_T&C.pdf');
			        $(".beingAssistedForm #pName").siblings('.errormsg').hide();
			        $(document).on('keypress', ':input[type="number"]', function (e) {
					    if (isNaN(e.key)) {
					        return false;
					    }
					});
			    }
			    
			    function sdpContent(){
			        $('.bookCon_1 > h1').text('Start saving with Systematic Deposit Plan in few steps');
			        $('.fd_com_title').html('Grow your savings on a monthly basis with SDP');
			       // $('.moreDet1').text('Additional 0.10% p.a. interest if you invest online');
			        $('.moreDet2').text('Deposits starting Rs. 5000 per month.');
			        $('.moreDet3').text('Highest Safety- Rated CRISIL AAA/ STABLE and [ICRA]AAA(Stable)');
			        $('.moreDet4').text('Up to 0.25% p.a. rate benefit for senior citizens');
			        $('.newPDFtnc').attr('href','/fixed-deposit-application-form/resources/pdf/SDP_T&C.pdf');
			        $('.otpTncPdf').attr('href','/fixed-deposit-application-form/resources/pdf/SDP_T&C.pdf');
			        $('.onlySdp').show();
			        $(".beingAssistedForm #pName").siblings('.errormsg').hide();
			        $(document).on('keypress', ':input[type="number"]', function (e) {
					    if (isNaN(e.key)) {
					        return false;
					    }
					});
			    }
			    
			    
			    
			    
			    
			    $('.green_card_main .s_block .tab_ul li a').click(function(){
			        if($(this).text() == 'Monthly Maturity'){
			            $(this).parents('.s_block').siblings('.f_block').children('i').text('Achieve your financial goals by making small monthly deposits.');
			            var sectionHeading=$('.grow_saving_part .fd_com_title').text().trim();
						dataLayerDynamic('button_click',sectionHeading,'Systematic Deposit Plan','Monthly Maturity');
					
			        
			        }else{
			            $(this).parents('.s_block').siblings('.f_block').children('i').text('Achieve your financial goals by making small monthly deposits. Get lumpsum amount on maturity.');
			            var sectionHeading=$('.grow_saving_part .fd_com_title').text().trim();
						dataLayerDynamic('button_click',sectionHeading,'Systematic Deposit Plan','Single Maturity');
					
			        
			        
			        }
			    });
			    
			    
			    
			    
			    $('.word_testimonial_inner .testimonial_top > .name_post p').each(function(){
			        var str = $(this).text();
			        var matches = str.match(/\b(\w)/g);
			        var acronym = matches.join('');
			        $(this).parent().siblings('.testiNameLatter').text(acronym);
			    });
			    
			   
			    $('.tab_ul li a').click(function(){
			        $(this).addClass('active');
			        $(this).parent().siblings().children('a').removeClass('active');
			        
			        var sectionHeading='';
			        if($(this).attr('data-tab')== 'offline_deposits')
			        {
			        	 sectionHeading=$('.rate_of_interest_part .fd_com_title').text().trim();
						dataLayerDynamic('button_click',sectionHeading,'','Offline Deposits');
					
			        }
			        
			        if($(this).attr('data-tab')== 'online_deposits')
			        {
			        	 sectionHeading=$('.rate_of_interest_part .fd_com_title').text().trim();
						dataLayerDynamic('button_click',sectionHeading,'','Online Deposits')
						
			        }
			        
			        if($(this).attr('data-tab')== 'investOnlineFd' || $(this).attr('data-tab')== 'investOnlineSdp')
			        	{
			        	 sectionHeading=$('.investOnlinePart .fd_com_title').text().trim();
						dataLayerDynamic('button_click',sectionHeading,'',$(this).text().trim())
			        	}
			        
			        
			        if($(this).attr('data-tab')== 'fdFaq' || $(this).attr('data-tab')== 'sdpFaq')
		        	{
		        	 sectionHeading=$('.faqPart .fd_com_title').text().trim();
					dataLayerDynamic('button_click',sectionHeading.substr(0, sectionHeading.indexOf('?')+1),'',$(this).text().trim())
		        	}
			    });
			    
			    $('.radio_ul li label input[type="radio"]').change(function(){
			        var datatab = $(this).attr('data-tab');
			        
			        $('#'+datatab).fadeIn(200);
			        $('#'+datatab).siblings().hide();
			        
			        
			        $('#'+datatab).find('input').removeClass('nomandetory');
			        $('#'+datatab).siblings().find('input').addClass('nomandetory');
			        $('#'+datatab).siblings().find('.errormsg').hide();
			        
			        
			        if($(this).attr('data-tab')=='offline_fixed_deposit' || $(this).attr('data-tab')=='offline_systematic_deposit'|| $(this).attr('data-tab')=='online_systematic_deposit' || $(this).attr('data-tab')=='online_fixed_deposit')
		        	{
		        	var sectionHeading=$('.rate_of_interest_part .fd_com_title').text().trim();
			        var text1 =$('.rate_of_interest_tab .tab_ul').find('.active').text();
		            var textHeading=text1;
		            var clickText=$(this).siblings('strong').text().trim();
					dataLayerDynamic('button_click',sectionHeading,textHeading,clickText);
		        	}
			        
			    });
			    
			    $('.tab_ul li a').click(function(){
			        var datatab = $(this).attr('data-tab');
			        
			        $('#'+datatab).fadeIn(200);
			        $('#'+datatab).siblings().hide();
			        
			        
			        if(datatab == 'nomineeNow'){
			            $('.nomineeDetailsLater').hide();
			            $('.nomineeDetailsNow').slideDown(200);
			            $('.nomineeDetailsNow .inputMainBlock input:not(.nomineeDetailsNow .whenSelectNo .inputMainBlock input), .nomineeDetailsNow .inputMainBlock select:not(.nomineeDetailsNow .whenSelectNo .inputMainBlock select,.nomineeDetailsNow .inputMainBlock.titleSelect select)').removeClass('nomandetory');
			            $('#title,#guardiantitle').removeClass('nomandetory');
			            
			        }else if(datatab == 'nomineeLater'){
			            $('.nomineeDetailsNow').hide();
			            $('.nomineeDetailsLater').slideDown(200);
			            $('.nomineeDetailsNow .inputMainBlock input:not(.nomineeDetailsNow .whenSelectNo .inputMainBlock input), .nomineeDetailsNow .inputMainBlock select:not(.nomineeDetailsNow .whenSelectNo .inputMainBlock select,.nomineeDetailsNow .inputMainBlock.titleSelect select)').addClass('nomandetory');
			            $('.nomineeDetailsNow .inputMainBlock .errormsg').hide();
			            $('#title,#guardiantitle').addClass('nomandetory');
			        }
			        
			    });
			    

			    
			    if($(window).width() > 768){
			    $(function () {
			      count = 0;
			      fdImg = ["step_1", "step_2", "step_3", "step_4"];
			      sdpImg = ["step_1", "step_2", "step_3", "step_4"];
			      setInterval(function () {
			        count++;
			        $("#investOnlineFd .leftPart img").fadeOut(200, function () {
			            $(this).attr('src','/fixed-deposit-application-form/resources/newImages/'+fdImg[count % fdImg.length]+'.png').fadeIn(200);
			            $('#investOnlineFd .investOnlineStepBox').eq(count % fdImg.length).addClass('active');
			            $('#investOnlineFd .investOnlineStepBox').eq(count % fdImg.length).siblings().removeClass('active');
			        });
			          
			        $("#investOnlineSdp .leftPart img").fadeOut(200, function () {
			            $(this).attr('src','/fixed-deposit-application-form/resources/newImages/'+sdpImg[count % sdpImg.length]+'.png').fadeIn(200);
			            $('#investOnlineSdp .investOnlineStepBox').eq(count % sdpImg.length).addClass('active');
			            $('#investOnlineSdp .investOnlineStepBox').eq(count % sdpImg.length).siblings().removeClass('active');
			        });
			      }, 3000);
			        
			        $('.investOnlineTab .tab_ul li').click(function(){
			            count = 0;
			            $("#investOnlineFd .leftPart img").attr('src','/fixed-deposit-application-form/resources/newImages/'+fdImg[0]+'.png').fadeIn(200);
			            $("#investOnlineSdp .leftPart img").attr('src','/fixed-deposit-application-form/resources/newImages/'+sdpImg[0]+'.png').fadeIn(200);
			            $('.investOnlineStepBox').removeClass('active');
			            $('#investOnlineFd .investOnlineStepBox').eq(0).addClass('active');
			            $('#investOnlineSdp .investOnlineStepBox').eq(0).addClass('active');
			        });
			        
			        $('.investOnlineStepBox').click(function(){
			            count = $(this).index();
			            $("#investOnlineFd .leftPart img").fadeOut(200, function(){
			               $(this).attr('src','/fixed-deposit-application-form/resources/newImages/'+fdImg[count]+'.png').fadeIn(200); 
			            });
			            $("#investOnlineSdp .leftPart img").fadeOut(200, function(){
			               $(this).attr('src','/fixed-deposit-application-form/resources/newImages/'+sdpImg[count]+'.png').fadeIn(200); 
			            });
			            $(this).addClass('active');
			            $(this).siblings().removeClass('active');
			        });
			    });
			    }
			    
			    $('.faqConBlock h3').click(function(){
			        $(this).toggleClass('active');
			        $(this).parent().siblings('.faqConBlock').children('h3').removeClass('active');
			        
			        $(this).siblings('.faqAns').slideToggle(200);
			        $(this).parent().siblings('.faqConBlock').children('.faqAns').slideUp(200);
			        
			        
			        var sectionHeading=$('.faqPart .fd_com_title').text().trim();
			    	var textHeading=$('.faqTab .tab_ul').find('.active').text();
					dataLayerDynamic('button_click',sectionHeading.substr(0, sectionHeading.indexOf('?')),textHeading, $(this).text().trim());
				
			    });
			    
			    $('.viewAllLink a').click(function(){
			        if($(this).text() == 'View all'){
			            $(this).text('View less');
			            
			            
			            var sectionHeading=$('.faqPart .fd_com_title').text().trim();
				    	var textHeading=$('.faqTab .tab_ul').find('.active').text();
						dataLayerDynamic('button_click',sectionHeading.substr(0, sectionHeading.indexOf('?')+1),textHeading,'View all');
			       
			        }else{
			            $(this).text('View all');
			        }
			        $(this).parent().siblings().children('.faqConBlock:nth-child(n + 6)').slideToggle(200);
			    });
			    
			    
			    $('.tooltipBox .tooltipCon .cross, .tooltipBox .tooltipCon .gotIt').click(function(){
			        $(this).parents('.tooltipMain').hide();
			    });
			    
			    $('.tooltipBox a').click(function(e){
			        e.stopPropagation();
			        $('.tooltipMain').hide();
			        $(this).siblings('.tooltipMain').show();
			        

			        if($(this).text().trim()== 'Help me decide')
			        {
			        	var textHeadingVal=$('.grow_saving_part .fd_com_title').text().trim();
						dataLayerDynamic('button_click','Bajaj Finance Fixed Deposit',textHeadingVal,$(this).text().trim());
					
			        }
			        
			        
			        if($(this).attr('data-tab')=='First' || $(this).attr('data-tab')=='second' || $(this).attr('data-tab')=='third' || $(this).attr('data-tab')=='fourth' || $(this).attr('data-tab')=='fifth')
			        	{
			        	var sectionHeading=$('.unmatched_benefits .fd_com_title').text().trim();
				        var text1 = $(this).closest(".benefits_box_inner").find(".forDataLayerI").text();
			            var text2 = $(this).closest(".benefits_box_inner").find(".forDataLayerStrong").text();
			             var text3 = $(this).closest(".benefits_box_inner").find(".forDataLayerP").text();
						 var textHeading=text1+' '+text2+' '+text3;
						dataLayerDynamic('card_click',sectionHeading,textHeading,'Information icon');
			        	}
			       
			        if($(this).attr('data-tab')=='Sixth' || $(this).attr('data-tab')=='seven')
			        	{
			        	var sectionHeading=$('.why_invest_fd .fd_com_title').text().trim();
				        var text1 = $(this).closest(".why_invest_box_inner").find(".forDataLayerP").text();
			            var textHeading=text1;
						dataLayerDynamic('card_click',sectionHeading,textHeading,'Information icon');
			        	}
			    });
			    
			    
			    
			    
			    $('.inputMainBlock input').focus(function(){
			        $(this).siblings('label').addClass('active');
			    });
			    
			    $('.inputMainBlock input').blur(function(){
			        
			        var th = $(this);
			        setTimeout(function(){
			            if($(th).val() == ""){
			                $(th).siblings('label').removeClass('active'); 
			            }else{
			                $(th).siblings('label').addClass('active');               
			            }
			        },200);
			            
			    });
			    

			    $('.inputMainBlock select').change(function(){
			        if($(this).val() == ""){
			            $(this).siblings('label').removeClass('active');    
			        }else{
			            $(this).siblings('label').addClass('active');    
			        }
			    });
			    
			    
			    $('.beingAssistedFormShowOrHide a').click(function(){
			    	
			    	
			    	if(checkPCodeglobal == true){
			    		$(".beingAssistedFormShowOrHide").remove();
						
						$( ".beingAssisted" ).append( "<ul class='tab_ul beingAssistedFormShowOrHide'>  <li><a class=''>NO</a></li>  <li><a class='active'>YES</a></li> </ul>" );
			    	}else{
				        if($(this).text() == 'YES'){
				        	$('.login_block div').removeClass("firstChild");
				        	newBRCode="YES";
				            $('.beingAssistedForm').slideDown(200);
				            $('.beingAssistedForm input').removeClass('nomandetory');
				            $("#pName").prop('readonly', true);
				        }else{
				        	newBRCode="NO";
				            $('.beingAssistedForm').slideUp(200);
				            $('.beingAssistedForm input').addClass('nomandetory');
				            $('.beingAssistedForm .errormsg').hide();
				        }
			    	}
			    });
			    
			    
			    
			  /*  if($('#hldatepicker').length>0){
			    
			    var d = new Date();
			    var year = d.getFullYear();
				year = Number(year);
				var newyear = year+1;
			    var month = d.getMonth();
				month = Number(month);
				month = month+1
			    var day = d.getDate();
				
				 var startdate = day+"/"+month+"/"+year;
			    var lastdate = day+"/"+month+"/"+newyear;*/
			    
			    
			    
			    
			  /*  $('#hldatepicker').daterangepicker({
					"singleDatePicker": true,
					"showDropdowns": true,
			        "autoApply": false,
					"minDate": '01/01/1900',
					"maxDate": startdate,
					"ignoreReadonly": true,
					"allowInputToggle": true,
			        "autoUpdateInput":false,
			        locale: {
			            format: 'DD/MM/YYYY',
			            cancelLabel: 'Clear', 
			        }
				}, function(start, end, label) {
			        
			        setTimeout(function(){
			            $('.dobVD').blur();     
			        },200);
				});*/
			    
			    
			    

			    
			   /* $('#hldatepicker').daterangepicker({
			        "singleDatePicker": true,
			        "showDropdowns": true,
			        "autoUpdateInput": false,
			        "autoApply": true,
			        "minDate": '01/01/1900',
					"maxDate": startdate,
			        locale: {
			            format: 'DD/MM/YYYY',
			            cancelLabel: 'Clear', 
			        }

			    }, function (start_date) {
			        
			        
			        var dateFrom = "01/01/1900";
			        var dateTo = startdate;
			        var dateCheck = $('#hldatepicker').val() ? $('#hldatepicker').val() : start_date.format('DD/MM/YYYY');
			        
			        var d1 = dateFrom.split("/");
			        var d2 = dateTo.split("/");
			        var c = dateCheck.split("/");

			        var from = new Date(d1[2], parseInt(d1[1])-1, d1[0]);  // -1 because months are from 0 to 11
			        var to   = new Date(d2[2], parseInt(d2[1])-1, d2[0]);
			        var check = new Date(c[2], parseInt(c[1])-1, c[0]);
			        
			 
			        console.log(check > from && check < to)
			        
			        if(check > from && check < to){
			            $('#hldatepicker').val(start_date.format('DD/MM/YYYY'));
			        }else{
			            $('#hldatepicker').val('');
			        }
			        
			        
			        setTimeout(function(){
			            $('.dobVD').blur();     
			        },200);
			    });
			    
			    
			    $('#hldatepicker').val('');
			    
			    $('#hldatepicker').focus(function(e){
			        e.preventDefault();
			    });
			    $('.calenderBtn').click(function(e){
			        e.preventDefault();
			        $(this).siblings('input').focus();
			    });
			    
			    $('#hldatepicker').blur(function(e){
			        e.preventDefault();
			        
			        if($(this).val() == '00/00/0000'){
			            $(this).val('');
			        }
			    });

			    
			 
			}*/
			    
			    
			    if($('#hldatepicker_2').length>0){
			    
			    var d = new Date();
			    var year = d.getFullYear();
				year = Number(year);
				var newyear = year+1;
			    var month = d.getMonth();
				month = Number(month);
				month = month+1
			    var day = d.getDate();
				
				 var startdate = day+"/"+month+"/"+year;
			    var lastdate = day+"/"+month+"/"+newyear;
			     
			    $('#hldatepicker_2').daterangepicker({
			        "singleDatePicker": true,
			        "showDropdowns": true,
			        "autoUpdateInput": false,
			        "autoApply": true,
			        "minDate": '01/01/1900',
					"maxDate": startdate,
			        locale: {
			            format: 'DD/MM/YYYY',
			            cancelLabel: 'Clear', 
			        }

			    }, function (start_date) {
			        
			        
			        var dateFrom = "01/01/1900";
			        var dateTo = startdate;
			        var dateCheck = $('#hldatepicker_2').val() ? $('#hldatepicker_2').val() : start_date.format('DD/MM/YYYY');
			        
			        var d1 = dateFrom.split("/");
			        var d2 = dateTo.split("/");
			        var c = dateCheck.split("/");

			        var from = new Date(d1[2], parseInt(d1[1])-1, d1[0]);  // -1 because months are from 0 to 11
			        var to   = new Date(d2[2], parseInt(d2[1])-1, d2[0]);
			        var check = new Date(c[2], parseInt(c[1])-1, c[0]);
			        
			 
			        console.log(check > from && check < to)
			        
			        if(check > from && check < to){
			            $('#hldatepicker_2').val(start_date.format('DD/MM/YYYY'));
			        }else{
			            $('#hldatepicker_2').val('');
			        }
			        
			        
			        setTimeout(function(){
			            $('.dobVD').blur();     
			        },200);
			    });
			    
			    
			    $('#hldatepicker_2').val('');
			    
			    $('#hldatepicker_2').focus(function(e){
			        e.preventDefault();
			    });
			    $('.calenderBtn').click(function(e){
			        e.preventDefault();
			        $(this).siblings('input').focus();
			    });
			    
			    $('#hldatepicker_2').blur(function(e){
			        e.preventDefault();
			        
			        if($(this).val() == '00/00/0000'){
			            $(this).val('');
			        }
			    });
			 
			}
			    
			    
			if($('#hldatepicker_3').length>0){
			    
			    var d = new Date();
			    var year = d.getFullYear();
				year = Number(year);
				var newyear = year+1;
			    var month = d.getMonth();
				month = Number(month);
				month = month+1
			    var day = d.getDate();
				
				 var startdate = day+"/"+month+"/"+year;
			    var lastdate = day+"/"+month+"/"+newyear;
			     
			    $('#hldatepicker_3').daterangepicker({
			        "singleDatePicker": true,
			        "showDropdowns": true,
			        "autoUpdateInput": false,
			        "autoApply": true,
			        "minDate": '01/01/1900',
					"maxDate": startdate,
			        locale: {
			            format: 'DD/MM/YYYY',
			            cancelLabel: 'Clear', 
			        }

			    }, function (start_date) {
			        
			        
			        var dateFrom = "01/01/1900";
			        var dateTo = startdate;
			        var dateCheck = $('#hldatepicker_3').val() ? $('#hldatepicker_3').val() : start_date.format('DD/MM/YYYY');
			        
			        var d1 = dateFrom.split("/");
			        var d2 = dateTo.split("/");
			        var c = dateCheck.split("/");

			        var from = new Date(d1[2], parseInt(d1[1])-1, d1[0]);  // -1 because months are from 0 to 11
			        var to   = new Date(d2[2], parseInt(d2[1])-1, d2[0]);
			        var check = new Date(c[2], parseInt(c[1])-1, c[0]);
			        
			 
			        console.log(check > from && check < to)
			        
			        if(check > from && check < to){
			            $('#hldatepicker_3').val(start_date.format('DD/MM/YYYY'));
			        }else{
			            $('#hldatepicker_3').val('');
			        }
			        
			        
			        setTimeout(function(){
			            $('.dobVD').blur();     
			        },200);
			    });
			    
			    
			    $('#hldatepicker_3').val('');
			    
			    $('#hldatepicker_3').focus(function(e){
			        e.preventDefault();
			    });
			    $('.calenderBtn').click(function(e){
			        e.preventDefault();
			        $(this).siblings('input').focus();
			    });
			    
			    $('#hldatepicker_3').blur(function(e){
			        e.preventDefault();
			        
			        if($(this).val() == '00/00/0000'){
			            $(this).val('');
			        }
			    });
			 
			}
			    
			    
			if($('.otpPart').length > 0){


			        var BACKSPACE_KEY = 8;
			        var ENTER_KEY = 13;
			        var TAB_KEY = 9;
			        var LEFT_KEY = 37;
			        var RIGHT_KEY = 39;
			        var ZERO_KEY = 48;
			        var NINE_KEY = 57;

			        function otp(elementId) {
			            var inputs = document.getElementById(elementId).children;
			            var callback = null;

			            function init(completeCallback) {
			                callback = completeCallback;
			                for (i = 0; i < inputs.length; i++) {
			                    registerEvents(i, inputs[i]);
			                }
			            }

			            function destroy() {
			                for (i = 0; i < inputs.length; i++) {
			                    registerEvents(i, inputs[i]);
			                }
			            }

			            function registerEvents(index, element) {
			                element.addEventListener("input", function (ev) {
			                    onInput(index, ev);
			                });
			                element.addEventListener("paste", function (ev) {
			                    onPaste(index, ev);
			                });
			                element.addEventListener("keydown", function (ev) {
			                    onKeyDown(index, ev);
			                });
			            }

			            function onPaste(index, ev) {
			                ev.preventDefault();
			                var curIndex = index;
			                var clipboardData = ev.clipboardData || window.clipboardData;
			                var pastedData = clipboardData.getData("Text");
			                for (i = 0; i < pastedData.length; i++) {
			                    if (i < inputs.length) {
			                        if (!isDigit(pastedData[i])) break;
			                        inputs[curIndex].value = pastedData[i];
			                        curIndex++;
			                    }
			                }
			                if (curIndex == inputs.length) {
			                    inputs[curIndex - 1].focus();
			                    callback(retrieveOTP());
			                } else {
			                    inputs[curIndex].focus();
			                }
			            }

			            function onKeyDown(index, ev) {
			                var key = ev.keyCode || ev.which;
			                if (key == LEFT_KEY && index > 0) {
			                    ev.preventDefault(); // prevent cursor to move before digit in input
			                    inputs[index - 1].focus();
			                }
			                if (key == RIGHT_KEY && index + 1 < inputs.length) {
			                    ev.preventDefault();
			                    inputs[index + 1].focus();
			                }
			                if (key == BACKSPACE_KEY && index > 0) {
			                    if (inputs[index].value == "") {
			                        // Empty and focus previous input and current input is empty
			                        inputs[index - 1].value = "";
			                        inputs[index - 1].focus();
			                    } else {
			                        inputs[index].value = "";
			                    }
			                }
			                if (key == ENTER_KEY) {
			                    // force submit if enter is pressed
			                    ev.preventDefault();
			                    if (isOTPComplete()) {
			                        callback(retrieveOTP());
			                    }
			                }
			                if (key == TAB_KEY && index == inputs.length - 1) {
			                    // force submit if tab pressed on last input
			                    ev.preventDefault();
			                    if (isOTPComplete()) {
			                        callback(retrieveOTP());
			                    }
			                }
			            }

			            function onInput(index, ev) {
			                var value = ev.data || ev.target.value;
			                var curIndex = index;
			                for (i = 0; i < value.length; i++) {
			                    if (i < inputs.length) {
			                        if (!isDigit(value[i])) {
			                            inputs[curIndex].value = "";
			                            break;
			                        }
			                        inputs[curIndex++].value = value[i];
			                        if (curIndex == inputs.length) {
			                            if (isOTPComplete()) {
			                                callback(retrieveOTP());
			                            }
			                        } else {
			                            inputs[curIndex].focus();
			                        }
			                    }
			                }
			            }

			            function retrieveOTP() {
			                var otp = "";
			                for (i = 0; i < inputs.length; i++) {
			                    otp += inputs[i].value;
			                }
			                return otp;
			            }

			            function isDigit(d) {
			                return d >= "0" && d <= "9";
			            }

			            function isOTPComplete() {
			                var isComplete = true;
			                var i = 0;
			                while (i < inputs.length && isComplete) {
			                    if (inputs[i].value == "") {
			                        isComplete = false;
			                    }
			                    i++;
			                }
			                return isComplete;
			            }

			            return {
			                init: init
			            };
			        }

			        var otpModule = otp("otp-inputs");
			        otpModule.init(function (passcode) {

			            $('#otp-inputs input').blur();
			        });

			    }
			    
			    $("#otpForm :input").blur(function () {
			        if ($(this).val() != '') {
			            $(this).removeClass('invalid').addClass('valid');
			        } else {
			            $(this).addClass('invalid').removeClass('valid');
			        }
			    });
			    
			    
			    var interval;
			    function count3minut() {
			        var timer2 = "00:60";
			        interval = setInterval(function () {
			            
			            var timer = timer2.split(':');
			            
			            var minutes = parseInt(timer[0], 10);
			            var seconds = parseInt(timer[1], 10);
			            
			            --seconds;
			            minutes = (seconds < 0) ? --minutes : minutes;
			            seconds = (seconds < 0) ? 59 : seconds;
			            seconds = (seconds < 10) ? '0' + seconds : seconds;
			            
			            $('.otpCounter > i').html('in 0' + minutes + ':' + seconds);
			            if (minutes < 0) clearInterval(interval);
			            if ((seconds <= 0) && (minutes <= 0)) clearInterval(interval);
			            timer2 = minutes + ':' + seconds;
			            
			            if ((seconds <= 0) && (minutes <= 0)) {
			                $('.otpCounter > a').addClass('resendhighlight');
			            }

			        }, 1000);

			    }
			    
			    
			    
			    $('body').on('click','.otpCounter > a.resendhighlight',function(){
			        $(this).removeClass('resendhighlight');
					
					if(resumeJourneyFlag && ("resume_applicatIon" == $('.login_tab .tab_ul').find('.active').attr('data-tab')))
									 {
										resumeResendOTP();
										
									}else
									  {
										fdResendOTP();
									 }
					
			        count3minut();
			    });
			    
			    $('.optTopDetails a').click(function(e){
			        e.preventDefault();
			        $('.otpMainPart').hide();
			        $('.loginFirstStep').show();
			        $('.forOtpForm').hide();
			        $('.forMobileform').show();
			        tokenCall();
			        $('.otpCounter > a').removeClass('resendhighlight');
			        $('.otpCounter > i').text('in 00:00');
			        clearInterval(interval);
			        isActionInProgress = 'No';
			        $('#receiveOtp').prop("disabled", false);
			        $('#generateOtpButton').prop("disabled", false); 
			        $('#resumeGenerateOtpButton').prop("disabled", false); 
			    });
			    
			    
			    //$('.openIntTable').click(function(){
			    $('body').on('click','.openIntTable',function(){
			        $('.interestRatePop').show();
			        
			        if($('#calc').val()=="FD"){
			        	$('#fdInterestTable').show();
			        }else{
			        	$('#sdpInterestTable').show();
			        	$('#investType').html("Book new SDP");
			        	$('#investment').html("SDP");
			        }
			        setTimeout(function(){
			            $('.interestRatePopMiddle').addClass('interestRatePopMiddleShow');
			        },100);
			        
			        $('body').css('overflow','hidden');
			    });
			    $('.interestRatePopInner > a').click(function(){
			        $(this).parents('.interestRatePop').hide();
			        $(this).parents('.interestRatePopMiddle').removeClass('interestRatePopMiddleShow');
			        $('body').css('overflow-y','auto');
			    });
			    
			    $('.interestRatePop').click(function(){
			        $(this).hide();
			        $(this).children('.interestRatePopMiddle').removeClass('interestRatePopMiddleShow');
			        $('body').css('overflow-y','auto');
			    });
			    
			    $('.interestRatePopInner').click(function(e){
			        e.stopPropagation();
			    });
			    
			    $('.AddressConformToggle .tab_ul li a').click(function(){
			        if($(this).text() == 'NO'){
			            $(this).parents('.AddressConformToggle').siblings('.whenSelectNo').slideDown(200);
			            $(this).parents('.AddressConformToggle').siblings('.whenSelectNo').find('input').removeClass('nomandetory');
			        }else{
			            $(this).parents('.AddressConformToggle').siblings('.whenSelectNo').slideUp(200);
			            $(this).parents('.AddressConformToggle').siblings('.whenSelectNo').find('input').addClass('nomandetory');
			            $(this).parents('.AddressConformToggle').siblings('.whenSelectNo').find('.errormsg').hide();
			        }
			    });
			    
			    $('.ErrorPage .buttonPart_1 button').click(function(){
				     location.reload();   
				});
			    $('.SessionErrorPage .buttonPart_1 button').click(function(){
				     location.reload();   
				});
			    
			    function setCookie(cname, cvalue, expday) {			    				    
			    	document.cookie = cname+"=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/";			    		
			    	var now = new Date();    
			    	var minutes = 30;
			    	if (expday == '1') {
			    	    now.setTime(now.getTime() + (60 * 60 * 1000));
			    	} else {
			    	    now.setTime(now.getTime() + (minutes * 60 * 1000));
			    	} 
			    	var expires = "expires=" + now.toLocaleTimeString();
			    	document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";			    	
			    	}
			    
			    
			    
			    
			/************************************************************************** Validation js *****************************************************************/
			    
			    $('.PanVD').blur(function (e) {
			        var mo = $(this).val();
			        mo = mo.toUpperCase();
			        $(this).val(mo);

			    });
			    
			    /***OTP key press function start ***/
			    
			    $('.otpVD').keypress(function(e) {
			        var mo = $(this).val();
			        if (mo.length > 6) {
			            $(this).val(mo.substr(0, 6)); 
			        }
			    if (mo.length > 5) { 
			            e.preventDefault();
			            return false;
			            $(this).blur();
			        }
			        if (mo.length > 5) {
			            $(this).blur();
			        }
			        
			        
			        var str1 = 0;
			    str1 = mo; 
			    
			    str1 = str1.replace(/[^1]/g,'');
			    
			    if($(window).width() > 1199){
			        if(str1 == '1'){
			            $('.a_otpPart .innrfildlte input').css('letter-spacing','36px');
			        }else if(str1 == '11'){
			            $('.a_otpPart .innrfildlte input').css('letter-spacing','36px');
			        }else if(str1 == '111'){
			            $('.a_otpPart .innrfildlte input').css('letter-spacing','38px');
			        }else if(str1 == '1111'){
			            $('.a_otpPart .innrfildlte input').css('letter-spacing','39px');
			        }else if(str1 == '11111'){
			            $('.a_otpPart .innrfildlte input').css('letter-spacing','39px');
			        }else{
			            $('.a_otpPart .innrfildlte input').css('letter-spacing','34px');
			        }
			    }else if($(window).width() <= 1199 && $(window).width() >= 992){
			        if(str1 == '1'){
			            $('.a_otpPart .innrfildlte input').css('letter-spacing','28px');
			        }else if(str1 == '11'){
			            $('.a_otpPart .innrfildlte input').css('letter-spacing','28px');
			        }else if(str1 == '111'){
			            $('.a_otpPart .innrfildlte input').css('letter-spacing','29px');
			        }else if(str1 == '1111'){
			            $('.a_otpPart .innrfildlte input').css('letter-spacing','29px');
			        }else if(str1 == '11111'){
			            $('.a_otpPart .innrfildlte input').css('letter-spacing','30px');
			        }else{
			            $('.a_otpPart .innrfildlte input').css('letter-spacing','27px');
			        }
			    }else{
			        if(str1 == '1'){
			            $('.a_otpPart .innrfildlte input').css('letter-spacing','31px');
			        }else if(str1 == '11'){
			            $('.a_otpPart .innrfildlte input').css('letter-spacing','32px');
			        }else if(str1 == '111'){
			            $('.a_otpPart .innrfildlte input').css('letter-spacing','34px');
			        }else if(str1 == '1111'){
			            $('.a_otpPart .innrfildlte input').css('letter-spacing','34px');
			        }else if(str1 == '11111'){
			            $('.a_otpPart .innrfildlte input').css('letter-spacing','35px');
			        }else{
			            $('.a_otpPart .innrfildlte input').css('letter-spacing','31px');
			        }
			    }

			    });
		/**OTP key press function end**/
			    
			    
			    $('.otpVD').on("keyup paste", function(e) {
			    	var otplength=0;
			        var mo = $(this).val();
			        otplength=mo.length;
			        if (otplength > 6) {
			            $(this).val(mo.substr(0, 6));

			        }
			        
			    });
			    
			    $(".otpVD").keydown(function(event) {
			      k = event.which; 
			      if ((k >= 48 && k <= 57) || (k >= 96 && k <= 105) || k == 8) {
			        if ($(this).val().length == 10) {
			          if (k == 8) {
			            return true;
			          } else {
			            event.preventDefault();
			            return false;

			          }
			        }
			      } else {
			        event.preventDefault();
			        return false;
			      }

			    });
			    
			    $('.otpVD').keypress(function(e) {
			        var mo = $(this).val();
			        if (mo.length > 6) {
			            $(this).val(mo.substr(0, 6)); 
			        }
			    if (mo.length > 5) { 
			            e.preventDefault();
			            return false;
			            $(this).blur();
			        }

			    });
			    
			    $('#otpForm input').keyup(function() {
			        var vallength = $(this).val().length - 1;
			        $('.a_optlinMain div').eq(vallength).addClass('active');
			        $('.a_optlinMain div').eq(vallength).prevAll().addClass('active');
			        $('.a_optlinMain div').eq(vallength).nextAll().removeClass('active');
			        if (vallength == -1) {
			            $('.a_sa_otpParttep2 .a_optlinMain div').removeClass('active');
			        }
			    });

			    
			     function fildValidation(th, ErrMSG) {
			        if (ErrMSG) {
			            $(th).siblings(".errormsg").text(ErrMSG);
			        }
			    }
			     
			     
			     $('#otpForm #receiveOtp').click(function(e){
				    	$("#otpForm .validBtn").prop("disabled", true);
				    	trackPageviewScript('Submit_OTP');
				               setTimeout(function() {
				            var totalerror = 0;
				            $("#otpForm .errormsg").each(function (i) {
				                if ($(this).css("display") == "block") {
				                    totalerror++;
				                }
				            });
				           if (totalerror == 0)
				           {
				        	   $('#otpForm .validBtn i').show();
				        	   $('#otpForm .errormsg').hide();
				        	   $('#otpForm .validBtn').prop('disabled', true);
								 if(resumeJourneyFlag && ($('.login_tab .tab_ul').find('.active').attr('data-tab')=='resume_applicatIon'))
						    		{
									 resumeFdcJourneyValidateOtp();
									}else
						    			{
										 fdValidateOTP();
						    			}
				           } else {
				        	   $('#otpForm .validBtn').prop('disabled', false);
				               if ($(window).width() < 900) {
				                   $('html, body').animate({
				                       scrollTop: firsterr - 55
				                   }, 200);
				               } else {
				                   $('html, body').animate({
				                       scrollTop: firsterr - 30
				                   }, 200);
				               }
				               $('html, body').animate({'scrollTop':$(".errormsg").offset().top -200 },3000);
				           }
				       }, 500);
				         
				   
				    
			     });
			     
			    
			    
			    
			 function allFormFieldValidationCheckNew(ThisObj, eventType) {
			     
			        var parent = ThisObj.parents("form").attr("id");
			        var error = 0;
			        $("#" + parent + " " + ".inputMainBlock input").each(function () {

			            if (!$(this).hasClass("nomandetory")) {
			                if (!$(this).attr('disabled')) {
			                    if ($(this).val() == "") {

			                        $(this).siblings(".errormsg").show();
			                        fildValidation(this);

			                    } else {

			                        if ($(this).hasClass('mobileVD')) {

			                            var value = $(this).val();

			                            if (value.length < 10 || value.length > 10) {
			                                if (eventType == 1) {
			                                    $(this).siblings(".errormsg").show();
			                                    fildValidation(this, "Please enter a valid 10-digit mobile number");
			                                }
			                            } else {
			                                if (value.indexOf('.') > -1) {
			                                    $(this).siblings(".errormsg").show();
			                                    fildValidation(this, "Please enter a valid 10-digit mobile number");
			                                } else if (value.substr(0, 1) == 9 || value.substr(0, 1) == 8 || value.substr(0, 1) == 7 || value.substr(0, 1) == 6 || value.substr(0, 1) == 5) {
			                                    $(this).siblings(".errormsg").hide();
			                                    fildValidation(this);
			                                    validornotNew(ThisObj);
			                                } else {
			                                    $(this).siblings(".errormsg").show();
			                                    fildValidation(this, "Mobile number should start with 9 or 8 or 7 or 6 or 5");
			                                }
			                            }

			                        }else if ($(this).hasClass('AnyValueVD')) {

			                            var value = $(this).val();

			                            if (!value) {

			                                $(this).siblings(".errormsg").show();
			                                fildValidation(this);
			                            } else {
			                                $(this).siblings(".errormsg").hide();
			                                fildValidation(this);
			                            }

			                        }else if ($(this).hasClass('PinCodeVD')) {
			                        var value = $(this).val();
			                           if(!/^[0-9]+$/.test($(this).val())){
			                              $(this).siblings(".errormsg").show();
			                               fildValidation(this,"Enter valid pin code");
			                            }else{
			                                if($(this).val().length==6){
			                                    if(value.substr(0, 1) == 0){
			                                       $(this).siblings(".errormsg").show();
			                                 fildValidation(this,"Enter valid pin code");
			                                       }else if(value.substr(5, 1) == 0 && value.substr(4, 1) == 0 && value.substr(3, 1) == 0){
			                                                $(this).siblings(".errormsg").show();
			                                 fildValidation(this,"Enter valid pin code");
			                                                }else{
			                             
			                             validornotNew(ThisObj);
			                                                    }
			                            }else{
			                                 $(this).siblings(".errormsg").show();
			                                 fildValidation(this,"Enter valid pin code");
			                            }
			                            }
			                        
			                    }else if ($(this).hasClass('otpVD')) {

			                            var value = $(this).val();
			                            if (value.length < 6 || value.length > 6) {
			                                $(this).siblings(".errormsg").show();
			                                fildValidation(this, "OTP entered is incorrect");
			                                $(this).siblings('.a_optlinMain').addClass('invalidOTP');
			                            } else {
			                                $(this).siblings(".errormsg").hide();
			                                $(this).siblings('.a_optlinMain').removeClass('invalidOTP');
			                            }

			                        }
			                   /* else  if ($(this).hasClass('dobVD')) {
			                        var a = $(this).val();
			                                  var str = a.split('/');    
			                        
			                           var a_year = str[2];
			                           var a_month = str[1];
			                           var a_day = str[0]; 
			                        var age = getAge(a_year+"/"+a_month+"/"+a_day);
			                        //var filter = /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/;
			                        var filter = /^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/g;
			                        if (filter.test(a)) {
			                                   if(age < 18){
			                               $(this).siblings(".errormsg").show();
			                             fildValidation(this,"To book fixed deposit for a minor, please contact the nearest Bajaj Finance FD branch.");
			                               }else{
			                                            $(this).siblings(".errormsg").hide();
			                                            validornotNew(ThisObj);
			                                  fildValidation(this,"Enter valid date of birth");
			                                        }
			                        }
			                        else {
			                            $(this).siblings(".errormsg").show();
			                             fildValidation(this,"Enter valid date of birth");
			                        }
			                    }*/
			                        
			                    else  if ($(this).hasClass('dobVD')) {
			                        var a = $(this).val(); 
			                           var str = a.split('/');    
			                        
			                           var a_year = str[2];
			                           var a_month = str[1];
			                           var a_day = str[0]; 
			                        var age = getAge(a_year+"/"+a_month+"/"+a_day);
			                        var filter = /^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/g;
			                        if (filter.test(a)) {
			                            if(!$(this).parent().hasClass('nomineeDob')){
			                            if(age < 18){
			                               $(this).siblings(".errormsg").show();
			                             fildValidation(this,"To invest in FD for minors please visit our <a href='#;'>nearest branch</a> ");
			                                
			                                if($(this).parent().hasClass('nomineeDob')){
			                                    $('.guardianManiPart').slideDown(200);
			                                    setTimeout(function(){
			                                        $('.guardianManiPart > .input2Grid .inputMainBlock input').removeClass('nomandetory');    
			                                        $('.guardianManiPart > .input2Grid .inputMainBlock select').removeClass('nomandetory');    
			                                    },200);
			                                    
			                                }
			                                 
			                               }else{ 
			                                            $(this).siblings(".errormsg").hide();
			                                            fildValidation(this,"Please enter valid Date of Birth (DD/MM/YYYY) as per your PAN Card");
			                                            
			                                            if($(this).parent().hasClass('nomineeDob')){
			                                                 $('.guardianManiPart').slideUp(200);
			                                                 $('.guardianManiPart > .input2Grid .inputMainBlock input').addClass('nomandetory');
			                                                 $('.guardianManiPart > .input2Grid .inputMainBlock input ~ .errormsg').hide();
			                                                 $('.guardianManiPart > .input2Grid .inputMainBlock select').addClass('nomandetory');
			                                                 $('.guardianManiPart > .input2Grid .inputMainBlock select ~ .errormsg').hide();
			                                             }
			                                         }
			                                     }
			                                 }
			                                 else {
			                                     
			                                     $(this).siblings(".errormsg").show();
			                                      fildValidation(this,"Please enter valid Date of Birth (DD/MM/YYYY) as per your PAN Card");
			                                 }
			                             }
			                        
			                    else if ($(this).hasClass('FullNameVD')) {
			                            var sval = $(this).val().trim();
			                            $(this).val(sval);
			                            if (!/^[a-zA-Z ]*$/g.test($(this).val())) {
			                                $(this).siblings(".errormsg").show();
			                                fildValidation(this, "Only alphabets are allowed");

			                            } else if ($(this).val().split(" ").length == 1) {
			                                $(this).siblings(".errormsg").show();
			                            } else {
			                                if ($(this).val().split(" ").length > 4) {
			                                    $(this).siblings(".errormsg").show();
			                                    fildValidation(this, "More than 3 space is not allowed");
			                                } else {
			                                    $(this).siblings(".errormsg").hide();
			                                    fildValidation(this);
			                                }
			                            }
			                        }else if ($(this).hasClass('PanVD')) {
			    
			                            var value = $(this).val();
			                            if (value.length < 10 || value.length > 10) {
			                                //if (eventType == 1) {
			                                    $(this).siblings(".errormsg").show();
			                                    fildValidation(this, "PAN number should be of 10 characters");
			                                //}
			                            }
			                            if(!/^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/.test($(this).val())){

			                                      $(this).siblings(".errormsg").show();
			                                      fildValidation(this,"Enter valid PAN number; eg: ABCHE9999A"); 
			                                    }else{
			                                        if($(this).val().substr(3, 1)=="P" ){
			                                              $(this).siblings(".errormsg").hide();
			                                               fildValidation(this);
			                                         }else{
			                                              $(this).siblings(".errormsg").show();
			                                              fildValidation(this,"Enter valid PAN number; eg: ABCPC9999A");
			                                         }

			                                    }
			    
			                        }else if ($(this).hasClass('emailVD')) {
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
			                        }else if ($(this).hasClass('AddressVD')) {
			                            var value = $(this).val();
			                            if (/[`~.<>;':"\/\[\]\|{}()=_+-]/g.test($(this).val())) {
			    
			                                $(this).siblings(".errormsg").show();
			                                fildValidation(this, "<>?'=+-_^`~  not allowed");
			                            } else {
			                                $(this).siblings(".errormsg").hide();
			                                fildValidation(this);
			                            }
			    
			    
			                        }
			                    }

			                }
			            }

			        });
			        
			$("#" + parent + " " + ".inputMainBlock select").each(function () {
			            if (!$(this).hasClass("nomandetory")) {
			                if (!$(this).attr('disabled')) {
			                    if ($(this).val() == "") {
			                        $(this).siblings(".errormsg").show();
			                        fildValidation(this);
			                    } else {
			                        $(this).siblings(".errormsg").hide();
			                        fildValidation(this);
			                    }
			                }
			            } else {
			        }
			    });
			     
			     
			    $("#" + parent + " " + "input[type='checkbox']").each(function () {
			            if (!$(this).hasClass("nomandetory")) {
			                if (!$(this).attr('disabled')) {
			                    if (!$(this).is(':checked')) {
			                        $(this).siblings(".errormsg").show();
			                        fildValidation(this);
			                    } else {
			                        $(this).siblings(".errormsg").hide();
			                        fildValidation(this);
			                    }
			                }
			            } else {
			        }
			    });
			     
			    
			 }
			             
			 $(".inputMainBlock select").change(function () {
			    if (!$(this).hasClass("nomandetory")) {
			        if (!$(this).attr('disabled')) {
			            if ($(this).val() == "") {
			                $(this).siblings(".errormsg").show();
			                fildValidation(this);
			            } else {
			                $(this).siblings(".errormsg").hide();
			                fildValidation(this);
			            }
			        }
			    }


			});
			    
			    
			$(".checkTNC input[type='checkbox']").change(function () {
			    if (!$(this).hasClass("nomandetory")) {
			        if (!$(this).attr('disabled')) {
			            if (!$(this).is(':checked')) {
			                $(this).siblings(".errormsg").show();
			                fildValidation(this);
			            } else {
			                $(this).siblings(".errormsg").hide();
			                fildValidation(this);
			            }
			        }
			    }
			});
			                  
			    $(".submitBTN_New").click(function (e) {
			        e.preventDefault();
			        var ThisObj = $('.submitBTN_New');
			        var NoError = allFormFieldValidationCheckNew(ThisObj, 1);
			    });
			     
			    
			    $(".inputMainBlock input").blur(function () {
			        var ThisObj = $(this);

			        if (!$(this).hasClass("nomandetory")) {
			            if (!$(this).attr('disabled')) {
			                if ($(this).val() == "") {
			                        $(this).siblings(".errormsg").show();
			                        fildValidation(this);
			                        if ($(this).hasClass('dobVD')) {
			                            fildValidation(this,"Please enter Date of Birth as it appears on your PAN Card");
			                        }
			                     
			                    
			                } else {
			                    if ($(this).hasClass('mobileVD')) {

			                            var value = $(this).val();

			                            if (value.length < 10 || value.length > 10) {
			                                    $(this).siblings(".errormsg").show();
			                                    fildValidation(this, "Please enter a valid 10-digit mobile number");
			                            } else {
			                                if (value.indexOf('.') > -1) {
			                                    $(this).siblings(".errormsg").show();
			                                    fildValidation(this, "Please enter a valid 10-digit mobile number");
			                                } else if (value.substr(0, 1) == 9 || value.substr(0, 1) == 8 || value.substr(0, 1) == 7 || value.substr(0, 1) == 6 || value.substr(0, 1) == 5) {
			                                    $(this).siblings(".errormsg").hide();
			                                    fildValidation(this);
			                                    validornotNew(ThisObj);
			                                } else {
			                                    $(this).siblings(".errormsg").show();
			                                    fildValidation(this, "Mobile number should start with 9 or 8 or 7 or 6 or 5");
			                                }
			                            }

			                        }else if ($(this).hasClass('AnyValueVD')) {

			                            var value = $(this).val();

			                            if (!value) {


			                                $(this).siblings(".errormsg").show();
			                                fildValidation(this);
			                            } else {
			                                $(this).siblings(".errormsg").hide();
			                                fildValidation(this);
			                            }

			                        }else if ($(this).hasClass('PinCodeVD')) {
			                        var value = $(this).val();
			                           if(!/^[0-9]+$/.test($(this).val())){
			                              $(this).siblings(".errormsg").show();
			                               fildValidation(this,"Enter valid pin code");
			                            }else{
			                                if($(this).val().length==6){
			                                    if(value.substr(0, 1) == 0){
			                                       $(this).siblings(".errormsg").show();
			                                 fildValidation(this,"Enter valid pin code");
			                                       }else if(value.substr(5, 1) == 0 && value.substr(4, 1) == 0 && value.substr(3, 1) == 0){
			                                                $(this).siblings(".errormsg").show();
			                                 fildValidation(this,"Enter valid pin code");
			                                                }else{
			                             $(this).siblings(".errormsg").hide();
			                             validornotNew(ThisObj);
			                                                    }
			                            }else{
			                                 $(this).siblings(".errormsg").show();
			                                 fildValidation(this,"Enter valid pin code");
			                            }
			                            }
			                        
			                    }else if ($(this).hasClass('otpVD')) {
			                        var value = $(this).val();
			                        if (value.length < 6 || value.length > 6) {
			                            $(this).siblings(".errormsg").show();
			                            fildValidation(this, "OTP entered is incorrect");
			                            $(this).siblings('.a_optlinMain').addClass('invalidOTP');
			                        } else {
			                            $(this).siblings(".errormsg").hide();
			                            validornotNew(ThisObj);
			                            $(this).siblings('.a_optlinMain').removeClass('invalidOTP');
			                        }

			                    }
			             /*       else  if ($(this).hasClass('dobVD')) {
			                        var a = $(this).val();
			                        
			                            //var dob1 = a;
			                           var str = a.split('/');    
			                        
			                           var a_year = str[2];
			                           var a_month = str[1];
			                           var a_day = str[0]; 
			                        var age = getAge(a_year+"/"+a_month+"/"+a_day);
			                        //alert(age);
			                        //var filter = /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/;
			                        var filter = /^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/g;
			                        if (filter.test(a)) {
			                            if(age < 18){
			                               $(this).siblings(".errormsg").show();
			                             fildValidation(this,"To book fixed deposit for a minor, please contact the nearest Bajaj Finance FD branch.");
			                               }else{
			                                            $(this).siblings(".errormsg").hide();
			                                            validornotNew(ThisObj);
			                                  fildValidation(this,"Enter valid date of birth");
			                                        }
			                                   
			                        }
			                        else {
			                            
			                            $(this).siblings(".errormsg").show();
			                             fildValidation(this,"Enter valid date of birth");
			                        }
			                    }*/
			                    
			                    else  if ($(this).hasClass('dobVD')) {
			                    	 var a = $(this).val(); 
			                         if($(this).parents().hasClass('part_2')){
			                             $('#personal_details_2 .userBirth').val(a);    
			                         }
			                         
			                            var str = a.split('/');    
			                         
			                            var a_year = str[2];
			                            var a_month = str[1];
			                            var a_day = str[0]; 
			                         var age = getAge(a_year+"/"+a_month+"/"+a_day);
			                         var filter = /^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/g;
			                         
			                         
			                         if (filter.test(a)) {
			                             if(age < 18){
			                                $(this).siblings(".errormsg").show();
			                              fildValidation(this,"To invest in FD for minors please visit our <a href='#;'>nearest branch</a> ");
			                                 
			                                 if($(this).parent().hasClass('nomineeDob')){
			                                     $('.guardianManiPart').slideDown(200);
			                                     setTimeout(function(){
			                                         $('.guardianManiPart > .input2Grid .inputMainBlock input').removeClass('nomandetory');    
			                                         $('.guardianManiPart > .input2Grid .inputMainBlock select').removeClass('nomandetory');
			                                         $('.nomineeDob .errormsg').hide();
			                                     },200);
			                                 }
			                                 
			                                 
			                               }else{
			                            
			                                   
			                                            $(this).siblings(".errormsg").hide();
			                                  fildValidation(this,"Please enter valid Date of Birth (DD/MM/YYYY) as per your PAN Card");
			                                   
			                                   if($(this).parent().hasClass('nomineeDob')){
			                                        $('.guardianManiPart').slideUp(200);
			                                        setTimeout(function(){
			                                        $('.guardianManiPart > .input2Grid .inputMainBlock input').addClass('nomandetory');
			                                        $('.guardianManiPart > .input2Grid .inputMainBlock input ~ .errormsg').hide();
			                                        $('.guardianManiPart > .input2Grid .inputMainBlock select').addClass('nomandetory');
			                                        $('.guardianManiPart > .input2Grid .inputMainBlock select ~ .errormsg').hide();
			                                        },200);
			                                    }
			                                }
			                            
			                            
			                            if(age <= 60){
			                                $('.Form15').val('Form 15G');
			                                $('.result_section > p').hide();
			                            }else{
			                                $('.Form15').val('Form 15H');
			                                $('.result_section > p').show();
			                            }
			                            
			                            
			                        }
			                        else {
			                            
			                            $(this).siblings(".errormsg").show();
			                             fildValidation(this,"Please enter valid Date of Birth (DD/MM/YYYY) as per your PAN Card");
			                        }
			                    }
			                    else if ($(this).hasClass('FullNameVD')) {
			                            var sval = $(this).val().trim();
			                            $(this).val(sval);
			                            if (!/^[a-zA-Z ]*$/g.test($(this).val())) {
			                                $(this).siblings(".errormsg").show();
			                                fildValidation(this, "Only alphabets are allowed");

			                            } else if ($(this).val().split(" ").length == 1) {
			                                $(this).siblings(".errormsg").show();
			                            } else {
			                                if ($(this).val().split(" ").length > 4) {
			                                    $(this).siblings(".errormsg").show();
			                                    fildValidation(this, "More than 3 space is not allowed");
			                                } else {
			                                    $(this).siblings(".errormsg").hide();
			                                    fildValidation(this);
			                                }
			                            }
			                        }else if ($(this).hasClass('PanVD')) {
			    
			                            var value = $(this).val();
			                            if (value.length < 10 || value.length > 10) {
			                                //if (eventType == 1) {
			                                    $(this).siblings(".errormsg").show();
			                                    fildValidation(this, "PAN number should be of 10 characters");
			                                //} 
			                            }
			                            if(!/^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/.test($(this).val())){

			                                      $(this).siblings(".errormsg").show();
			                                      fildValidation(this,"Enter valid PAN number; eg: ABCHE9999A"); 
			                                    }else{
			                                        if($(this).val().substr(3, 1)=="P"){
			                                              $(this).siblings(".errormsg").hide();
			                                               fildValidation(this);
			                                         }else{
			                                              $(this).siblings(".errormsg").show();
			                                              fildValidation(this,"Enter valid PAN number; eg: ABCPC9999A");
			                                             
			                                         }

			                                    }
			    
			                        }else if ($(this).hasClass('emailVD')) {
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
			                        }else if ($(this).hasClass('AddressVD')) {
			                            var value = $(this).val();
			     
			                            if (/[`~.<>;':"\/\[\]\|{}()=_+-]/g.test($(this).val())) {
			    
			                                $(this).siblings(".errormsg").show();
			                                fildValidation(this, "<>?'=+-_^`~  not allowed");
			                            } else {
			                                $(this).siblings(".errormsg").hide();
			                                fildValidation(this);
			                            }
			    
			    
			                        }
			                     
			                }

			            }
			        }



			    });
			    
			    

			    $('.PinCodeVD').keyup(function (e) {
			        var mo = $(this).val();
			        if (mo.length > 6) {
			            $(this).val(mo.substr(0, 6));
			        }
			    });
			    
			    $('.PinCodeVD').keypress(function(e) {
			        var mo = $(this).val();
			    if (mo.length > 5) { 
			            e.preventDefault();
			            return false; 
			        }

			    });








			    $('.FullNameVD').bind('keypress keyup blur',function(){ 
			        var node = $(this);
			        node.val(node.val().replace(/[^A-Za-z ]/g,'') ); }
			    );



			    $('.mobileVD').keyup(function (e) {
			        var mo = $(this).val();
			        if (mo.length > 10) {
			            $(this).val(mo.substr(0, 10));
			        }

			        if (mo.length == 10) {
			            $("#mobilenumber_input .submitBTN").prop('disabled',false);
			            $('.ind_number +a').addClass('numberver');
			        }else{
			            $('.ind_number +a').removeClass('numberver');
			        }

			        
			    });
			    
			    
			    var clear = 0;
			    $('#basicDetailsForm .inputMainBlock input').blur(function(){
			        
			        
			        setTimeout(function () {
			            var totalerror = 0;
			            $("#basicDetailsForm .errormsg").each(function (i) {
			                if ($(this).css("display") == "block") {
			                    totalerror++;
			                }
			            });
			            
			            $("#basicDetailsForm input").each(function (i) {
			                if ($(this).val() == "") {
			                    totalerror++;
			                }
			            });
			            
			            if (totalerror == 0) {
			                $('.Calc_part').slideDown(200);
			            }else{
			                $('.Calc_part').slideUp(200);
			            }
			        }, 500);
			    });

			    $('#basicDetailsForm_2 .inputMainBlock input').blur(function(){
			        
			        setTimeout(function () {
			            var totalerror = 0;
			            $("#basicDetailsForm_2 .errormsg").each(function (i) {
			                if ($(this).css("display") == "block") {
			                    totalerror++;
			                }
			            });
			            
			            $("#basicDetailsForm_2 input").each(function (i) {
			                if ($(this).val() == "") {
			                    totalerror++;
			                }
			            });
			            
			            if (totalerror == 0) {
			                $('.Calc_part').slideDown(200);
			            }else{
			                $('.Calc_part').slideUp(200);
			            }
			        }, 500);
			    });
			    
			    function getAge(dateString) {
			        var today = new Date();
			        var birthDate = new Date(dateString);
			        var age = today.getFullYear() - birthDate.getFullYear();
			        var m = today.getMonth() - birthDate.getMonth();
			        if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
			            age--;
			        }
			        return age;
			    }
			    
			    
			    $('.dobVD').keyup(function(e) {
			        var mo = $(this).val();
			        if (mo.length > 10) {
			            $(this).val(mo.substr(0, 10));
			        }

			        if (mo.length == 10) {
			            //$(this).blur();
			        }
			        
			        if (e.keyCode != 8){   
			            if ($(this).val().length == 2){
			                $(this).val($(this).val() + "/");
			            }else if ($(this).val().length == 5){
			                $(this).val($(this).val() + "/");
			            }
			        }
			    });
			 
			       $(".dobVD").keydown(function(event) {
			      k = event.which;  
			      if ((k >= 48 && k <= 57) || (k >= 96 && k <= 105) || k == 8 || k == 9) {
			        if ($(this).val().length == 2 || $(this).val().length == 5 || $(this).val().length == 10) {
			          if (k == 8 || k == 9) {
			            return true;
			          } else {
			            event.preventDefault();
			            return false;

			          }
			        }
			      } else {
			        event.preventDefault();
			        return false;
			      }

			    });
			    
			    
			    $('.calendarOpen').keyup(function(e) {
			        var mo = $(this).val();
			        if (mo.length > 10) {
			            $(this).val(mo.substr(0, 10));
			        }

			        if (mo.length == 10) {
			            //$(this).blur();
			        }
			        
			        if (e.keyCode != 8){   
			            if ($(this).val().length == 2){
			                $(this).val($(this).val() + "/");
			            }else if ($(this).val().length == 5){
			                $(this).val($(this).val() + "/");
			            }
			        }
			    });
			 
			       $(".calendarOpen").keydown(function(event) {
			      k = event.which;  
			      if ((k >= 48 && k <= 57) || (k >= 96 && k <= 105) || k == 8 || k == 9) {
			        if ($(this).val().length == 2 || $(this).val().length == 5 || $(this).val().length == 10) {
			          if (k == 8 || k == 9) {
			            return true;
			          } else {
			            event.preventDefault();
			            return false;

			          }
			        }
			      } else {
			        event.preventDefault();
			        return false;
			      }

			    });
			    
			    
			    
			    
			    
			    $('#apply_for_new_fd_form .validBtn').click(function (e) {
			        e.preventDefault();
			        //$(".submitBTN_New").click();
			        trackPageviewScript('Generate_OTP');
			        var ThisObj = $(this);
			        allFormFieldValidationCheckNew(ThisObj,1);
			        var pcode=$("#pCode").val();
		            var pname=$("#pName").val();
		            
		            if(!pcode == null || !pcode == "undefined" || !pcode == '' && (pname == null || pname == "undefined" || pname == ''))
		            	{		            	
		            	 $("#pName").siblings(".errormsg").show();		            	
		            	}
			        
		            setTimeout(function () {
		            	var totalerror = 0;
		            
		            		$("#apply_for_new_fd_form .errormsg").each(function (i) {
		            				if ($(this).css("display") == "block") {
		            				totalerror++;
		            				}
		            		});    
		            		if (totalerror == 0) {
		            			pincodeValidator($('#pinCodePV').val());
		            		}
		             }, 500);

			    });
			    
			   

			    $('#resume_applicatIon_form .validBtn').click(function (e) {
			        e.preventDefault();
			        trackPageviewScript('Generate_OTP');
			        var ThisObj = $(this);
			        allFormFieldValidationCheckNew(ThisObj,1);
			        setTimeout(function () {
			            var totalerror = 0;
			            $("#resume_applicatIon_form .errormsg").each(function (i) {
			                if ($(this).css("display") == "block") {
			                    totalerror++;
			                }
			            });
			            if (totalerror == 0) {
			            	$('#resume_applicatIon .validBtn i').show();
			            	 $('#resumeGenerateOtpButton').prop("disabled", true); 
			                fdresumeotp();
			            }
			        }, 500);

			    });
			    
			    
				/***************************************************Script JS End Here****************************************/
			
			
				
			var productFDCustId;
			var bankName;
			var bankifscCode;
			var bankAccNumber;
			var bankDetailsFlag=false;
			var resumeJourneyFlag=false;
			
			var investmentTypeOkyc =  $('#investmentTypeOkyc').val();
			var okycstatusIDValue =  $('#okycstatusIDValue').val().trim();
			
			
			var fdslIDNew =  $('#fdslIDNew').val().trim();
			var fdFullnameNew =  $('#fdFullnameNew').val().trim();
			var fdDateOfBirthNew =  $('#fdDateOfBirthNew').val().trim();
			var fdExistingCustIdNew =  $('#fdExistingCustIdNew').val().trim();
			var fdPincodeNew =  $('#fdPincodeNew').val().trim();
			var fdcityNew =  $('#fdcityNew').val().trim();
			var fdEmailNew =  $('#fdEmailNew').val().trim();
			var fdPANnew =  $('#fdPANnew').val().trim();
			var fdUserMobileNumberNew =  $('#fdUserMobileNumberNew').val().trim();
			var fdDedupeCustTypeNew =  $('#fdDedupeCustTypeNew').val().trim();
			var fdCustTypeNew =  $('#fdCustTypeNew').val().trim();
			var fdAddressNew = $('#fdAddressNew').val().trim();
			var fdproductForIDNew = $('#fdproductForIDNew').val().trim();
			var customIDForManuplationNew = $('#customIDForManuplationNew').val().trim();
			var fdSlfFdcNum = $('#fdcNumber').val().trim();
		
			var dataVal;
			
			var timeOfLogging = new Date().toLocaleTimeString('en-GB');
			var timeOfLoggingday=new Date().toLocaleDateString();
	       
			timeOfLogging=timeOfLoggingday+" "+timeOfLogging;
	        
			
			var custTypeFD;
			var selectedCust=true;
		
			  function inrFormat(x) {
		        x = x.toString();
		        if (x.indexOf(',') > -1) {
		            x = x.replace(/\,/g, '');
		        }
		
		        var lastThree = x.replace(/\D/g, '').substring(x.length - 3);
		        var otherNumbers = x.substring(0, x.length - 3);
		        if (otherNumbers != '') { lastThree = ',' + lastThree; }
		        var res = otherNumbers.replace(/\D/g, '').replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree;
		        return res;
		    }
		 
			
			  var interval;
		    
			     
			    $('.j_hunpd input[name="fd"]').change(function(){
			        $('.mobBorderBlock').removeClass('active');
			        $(this).parents('.a_radiodeposit').next('.mobBorderBlock').addClass('active');
			    });
			    
			    /* 	Extract value form url starts*/   
			    function getUrlValue(name){             
			    	   if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
			    	      return decodeURIComponent(name[1]);
			    	}   
			    /* 	Extract value form url ends*/
			    
			    /********Tnc showmoreLess******/
			    $('.p_loginformsection .allIntTbl').click(function () {
		            $('.a_blackoverlay').toggle();
		            $('.a_termsAndConPart_2-2').toggleClass('a_termsAndConPart_2-2Show');
		            dataLayerCallRate("imp_body_text_click","View interest rates");
		        });

		        $('.a_termsAndConPart_2-2 .a_closePop').click(function () {
		            $('.a_blackoverlay').hide();
		            $('.a_termsAndConPart_2-2').removeClass('a_termsAndConPart_2-2Show');
		            $('#fdPayment').removeClass('floatCssButton');
		           
		        });
		        
		        $('.tncSafaricheck .a_termsAndConPart_2-1 .a_closePop').click(function(){
			        $('.a_blackoverlay').hide();
			        $('.a_termsAndConPart_2-1').removeClass('a_termsAndConPart_2-1Show');
			        $('.tncSafaricheck').toggleClass('Adreltive');
			        $('#fdPayment').removeClass('floatCssButton');
			    });
		        
		        $('.tncSafaricheck .a_termsAndConPart_2 .a_closePop').click(function(){
			        $('.a_blackoverlay').hide();
			        $('.a_termsAndConPart_2').removeClass('a_termsAndConPart_2Show');
			        $('.tncSafaricheck').toggleClass('Adreltive');		
			        $('#fdPayment').removeClass('floatCssButton');
			    });
			    
		        
		        
			  
			  $(".a_ReInput input").keydown(function(event) {
			      k = event.which;
			      if (k == 9) {
			          $(this).parent().next().children('input:eq(0),select').focus();
			          return false;
			      }
			    });
		
		    /* start validation js */
			  
				$('.a_ReInput.datefield input:nth-of-type(1)').keyup(function(e) {
			        var mo = $(this).val();
			        if (mo > 31) {
			            $(this).val('31');
			        }
					if (mo.length > 1) {
			            $(this).parents('.a_ReInput').children('input:nth-of-type(2)').focus(); 
			        }
			    });
				$('.a_ReInput.datefield input:nth-of-type(2)').keyup(function(e) {
			        var mo = $(this).val();
			        if (mo > 12) {
			            $(this).val('12');
			        }
					if (mo.length > 1) {
						$(this).parents('.a_ReInput').children('input:nth-of-type(3)').focus();
			        }
			    });
		
				$('.a_ReInput.datefield input:nth-of-type(3)').keyup(function(e) {
			        var mo = $(this).val();
			        if (mo.length > 4) {
			            $(this).val(mo.substr(0, 4));
			        }
					if (mo.length > 3) {
			            $(this).parents('.a_ReInput').children('input:nth-of-type(3)').blur(); 
			        }
			    });
				$('.a_ReInput.datefield input:nth-of-type(1)').blur(function(e) {
			        var mo = $(this).val().length;
			        if (mo < 2) {
			            $(this).siblings('.errormsg').show();
			        }
			    });
			    $('.a_ReInput.datefield input:nth-of-type(2)').blur(function(e) {
			        var mo = $(this).val().length;
			        if (mo < 2) {
			            $(this).siblings('.errormsg').show();
			        }
			    });
			    $('.a_ReInput.datefield input:nth-of-type(3)').blur(function(e) {
			        var mo = $(this).val().length;
			        if (mo < 4) 
					{
			            $(this).siblings('.errormsg').show();
			        } 
					 var yearStart = $(this).val().substr(0, 2);
					if(yearStart!='20' & yearStart!='19')
					{

    					 $(this).siblings(".errormsg").show().text('Please Enter valid DOB');
                         fildValidation(this);
                         $(this).siblings("label").removeClass("active");
    				
					}
			    });
		    function fildValidation(th, ErrMSG) {
		        if (ErrMSG) {
		            $(th).siblings(".errormsg").html(ErrMSG);
		        }
		    }
		
		    function mainError(th, ErrMSG) {
		        if (ErrMSG) {
		            $(".mainError").children('p').text(ErrMSG);
		        }
		    }
		
		    function allFormFieldValidationCheck(ThisObj, eventType) {
		
		        var parent = ThisObj.parents("form").attr("id");
		        var error = 0;
		        $("#" + parent + " " + ".a_ReInput input").each(function() {
		
		            if (!$(this).hasClass("nomandetory")) {
		                if (!$(this).attr('disabled')) {
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
		                        var fullname = $('#fullNamePD').val();
		                        var nomineeName = $('#nomineeName').val();
		                        var guardianName = $('#sdpguardianName').val();
		                        if (!/^[a-zA-Z. ]*$/g.test($(this).val())) {
		                            $(this).siblings(".errormsg").show();
		                            fildValidation(this, "Only alphabets are allowed");

		                        } else if(fullname.toUpperCase() == nomineeName.toUpperCase()){
	                        		console.log('=== Inside IF Nominee ====');
                            		$(this).siblings(".errormsg").show();
		                            fildValidation(this,"The nominee's name can't be same as the primary account holder's name");
                            	} else if(nomineeName.toUpperCase() == guardianName.toUpperCase()){
	                        		console.log('=== Inside IF Guardian ====');
                            		$(this).siblings(".errormsg").show();
		                            fildValidation(this,"The guardian's name can't be same as the nominee's name");
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
		                        else if ($(this).hasClass('datepickerVD')) 
		                        {
		                            if ($(this).val())
		                            {
		                            	var currentId=$(".nomineeYear:visible").attr('id');
		                                var year=$('#nomineeyy').val();
		                                var monthh=$('#nomineemm').val()-1;
		                                var datee=$('#nomineedd').val();
		                                console.log(datee+"/"+(monthh+1)+"/"+year);
		                                var yearStart = year.substr(0, 2);
		                                var today = new Date();
		                                var selectdate = new Date(year,monthh,datee);
		                                var dd = $('#nomineedd').val();
		                                var mm = $('#nomineemm').val();
		                                var rgx = new RegExp('\\d{2}');
		                                dd = (!rgx.test(dd)) ? ('0'+dd) : (dd);
		            				    mm = (!rgx.test(mm)) ? ('0'+mm) : (mm);
		            				    var nomineedate = dd+"/"+mm+"/"+year;
		            				    var userdob = $('#dobPD').val();
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
		                    				}else
		                    				{
		                    					if($('#nomineedd').val()=="" || $('#nomineemm').val()=="" || $('#nomineeyy').val()=="")
		                    						{
		                    						$(this).siblings(".errormsg").show();
			                                        fildValidation(this);
			                                        $(this).siblings("label").removeClass("active");
		                    						}
		                    					else if($('#nomineedd').val()=="00" || $('#nomineemm').val()=="00")
		                    						{
		                    						$(this).siblings(".errormsg").show();
			                                        fildValidation(this);
			                                        $(this).siblings("label").removeClass("active");
		                    						}
		                    					else if($('#nomineedd').val().length>=3 || $('#nomineemm').val().length>=3)
	                    						{
	                    						$(this).siblings(".errormsg").show();
		                                        fildValidation(this);
		                                        $(this).siblings("label").removeClass("active");
	                    						}
		                    					else if(selectdate > today)
	                    						{
	                    						$(this).siblings(".errormsg").show();
		                                        fildValidation(this);
		                                        $(this).siblings("label").removeClass("active");
	                    						}
		                    					else if(userdob == nomineedate)
	                    						{
	                    						$(this).siblings(".errormsg").show();
		                                        fildValidation(this,"The nominee's date of birth can't be same as the primary account holder's date of birth");
		                                        $(this).siblings("label").removeClass("active");
	                    						}
		                    					else
		                    						{
		                					$(this).siblings(".errormsg").hide();
		                                    fildValidation(this);
		                                    $(this).siblings("label").addClass("active");
		                                    }
		                    					}
		                					}
		                            } else 
		                            {
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
		                        		if (month.length >=3 ) {
		                        			$(this).siblings(".errormsg").show();
		                        			fildValidation(this);
		                        			$(this).siblings("label").removeClass("active");
		                        		}else if( month == "00")
		                        			{
		                        			$(this).siblings(".errormsg").show();
		                        			fildValidation(this);
		                        			$(this).siblings("label").removeClass("active");
		                        		}
		                        		if (day.length >= 3) {
		                        			$(this).siblings(".errormsg").show();
		                        			fildValidation(this);
		                        			$(this).siblings("label").removeClass("active");
		                        		}else if( day == "00")
		                        			{
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
		                            else if(value.includes("<") || value.includes(">"))
									{
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
		                       /* else if ($(this).hasClass('BankVD')) {
		                        
		                          	 var value = $('.BankVD').val();
		                          	
		                             if (value =="" || value==null) {
		                                   $(this).siblings(".errormsg").show();
		                                   fildValidation(this);
		                                 
		                               } else {
		                                   $(this).siblings(".errormsg").hide();
		                                   fildValidation(this);
		                                
		                               }
		
		                           }*/
		                        else if($(this).hasClass('sliderAmunt')) {
		
		                        	            	
		                        	 var value = $(this).val().replace(/\,/g,'');
		                        	  
		                        	   if (!/^[0-9,]+$/.test(value)) {
		                                 $(this).siblings(".errormsg").show();
		                                 fildValidation(this, "Enter number only");
		                        	 }
		                           	 
		                        	   else if (value < 15000 || value > 50000000) {
		                        		   $(this).siblings(".errormsg").show();
		                                    fildValidation(this, "Please enter value between 15000 to 50000000");
		                                  
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
		                          
		                       	else if (value < 5000 || value > 50000000) {
		                                $(this).siblings(".errormsg").show();
		                                fildValidation(this, "Please enter value between 5000 to 50000000");
		                              
		                            } else {
		                                $(this).siblings(".errormsg").hide();
		                                fildValidation(this);
		                            }
		
		                       }
		                        else if ($(this).hasClass('tenorPeriod'))
			                       {
			                        	 var value =$('input[name="tenor"]:checked').val();
			                        
			                        	 if (value =="" || value==undefined) {
			                                $(this).siblings(".errormsg").show();
			                                fildValidation(this, "Please Select tenor");
			
			                            }
			                            else {
			                                $(this).siblings(".errormsg").hide();
			                                fildValidation(this);
			                             
			                            }
			
			                        } 
			                       else if ($(this).hasClass('sdptenorPeriod'))
			                       {
			                        	 var value =$('input[name="sdptenor"]:checked').val();
			                        
			                        	 if (value =="" || value==undefined) {
			                                $(this).siblings(".errormsg").show();
			                                fildValidation(this, "Please Select tenor");
			
			                            }
			                            else {
			                                $(this).siblings(".errormsg").hide();
			                                fildValidation(this);
			                             
			                            }
			
			                        }else if ($(this).hasClass('customTenor')) {
		                        	 var value = $(this).val();
		                        
		                        	 if (value < 12 || value > 60) {
		                                $(this).siblings(".errormsg").show();
		                                fildValidation(this, "Enter Tenor between 12 to 60");
		
		                            }
		                            else {
		                                $(this).siblings(".errormsg").hide();
		                                fildValidation(this);
		                             
		                            }
		
		                        } else if ($(this).hasClass('customTenor_2')) 
		                        {
		                        var value = $(this).val();
		                        var maturityScheme = $('.a_radiodeposit label input[name="maturitysdheme"]:checked').val().trim();

		                        if (maturityScheme == "Monthly Maturity") {
		                        	maturityScheme = "Monthly Maturity";
		                        } else {
		                        	maturityScheme = "Single Maturity";
		                        }

		                        if (maturityScheme == "Single Maturity") {
		                        	if (value < 19 || value > 60) {
		                        		$(this).siblings(".errormsg").show();
		                        		fildValidation(this, "Enter Tenor between 19 to 60");
		                        	} else {
		                        		$(this).siblings(".errormsg").hide();
		                        		fildValidation(this);
		                        	}
		                        } else {
		                        	if (value < 12 || value > 60) {
		                        		$(this).siblings(".errormsg").show();
		                        		fildValidation(this, "Enter Tenor between 12 to 60");

		                        	} else {
		                        		$(this).siblings(".errormsg").hide();
		                        		fildValidation(this);
		                        	}
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
		                            if (!/^[0-9]{9,18}$/.test(accNum)) {
		                                $(this).siblings(".errormsg").show();
		                                fildValidation(this);
		
		                            }
		
		                        }else if ($(this).hasClass('ifscCodeVD')) {
		
		                            if (!/^[A-Z]{4}0[A-Z0-9]{6}$/.test($(this).val())) {
		                                fildValidation(this);
		
		                            }
		
		                        } else if ($(this).hasClass('FullNameVD')) {
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
		
		
		
		
		                  }
		
		                }
		                else
	                	{
	                	if ($(this).attr('disabled')) {
			            	 if($(this).hasClass('CommUploadVD'))
	                		 {
	                		 var commCheckbox=$('#checkBox_commAddress').val();		                        	

	                        	if(commCheckbox == 'Yes')
	                        	  {
								    var commAddDocName=$("#commAddressattache").val().trim();
	                        		if (commAddDocName == "")
	                        		{
	                        		$(this).siblings(".errormsg").show();
	                                fildValidation(this, "Please upload a valid address proof");
	                        		}
	                        	  }
	                        	else
	                        	  {		                        		
	                        		$(this).siblings(".errormsg").hide();
	                                fildValidation(this);
	                        	  }
	                		 }	
			            	}
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
		
		
		    $(".submitBTN").click(function(e) {
		        e.preventDefault();
		        var ThisObj = $(this);
		        var NoError = allFormFieldValidationCheck(ThisObj, 1);
		
		    });
		    
		    $(".a_ReInput input").focus(function() {
			       
		           if (!$(this).attr('readonly')) {
		        	   if(!$(this).hasClass("ifscCodeVD")){
		        		$(this).siblings(".errormsg").hide().css('color','#000000');
		   		        fildValidation(this);   
		        	   }
		           }
		            });
		    
		    $(".a_ReInput select").focus(function() {
		    	if (!$(this).attr('readonly') && $(this).val()==null) {
		    	$(this).siblings(".errormsg").show().css('color','#000000');
		    	fildValidation(this);
		    	}
		    	});
		    
		  /*  $("#digit-1").focus(function() {
		        
		       
		    $("#receiveOtp .errormsg").show().css('color','#000000');
		    
		      
		  
		          });
		*/
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
		                   /* if (!$(this).hasClass('otpVD')) {
		                        $('.mainError').show();
		                    }*/
		
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
		
		                    }  else if ($(this).hasClass('mobileVD')) {
		
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
	                                var year=$('#nomineeyy').val();
	                                var monthh=$('#nomineemm').val()-1;
	                                var datee=$('#nomineedd').val();
	                                console.log(datee+"/"+(monthh+1)+"/"+year);
	                                var selectdate = new Date(year,monthh,datee);
	                                console.log(selectdate);
	                                var yearStart = year.substr(0, 2);
	                                var today = new Date();
	                                var dd = $('#nomineedd').val();
	                                var mm = $('#nomineemm').val();
	                                var rgx = new RegExp('\\d{2}');
	                                dd = (!rgx.test(dd)) ? ('0'+dd) : (dd);
	            				    mm = (!rgx.test(mm)) ? ('0'+mm) : (mm);
	            				    var nomineedate = dd+"/"+mm+"/"+year;
	            				    var userdob = $('#dobPD').val();
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
	                    					if($('#nomineedd').val()=="" || $('#nomineemm').val()=="" || $('#nomineeyy').val()=="")
                    						{
                    						$(this).siblings(".errormsg").show();
	                                        fildValidation(this);
	                                        $(this).siblings("label").removeClass("active");
                    						}else if($('#nomineedd').val()=="00" || $('#nomineemm').val()=="00")
                    						{
	                    						$(this).siblings(".errormsg").show();
		                                        fildValidation(this);
		                                        $(this).siblings("label").removeClass("active");
	                    						}
	                    					else if($('#nomineedd').val().length>=3 || $('#nomineemm').val().length>=3)
                    						{
                    						$(this).siblings(".errormsg").show();
	                                        fildValidation(this);
	                                        $(this).siblings("label").removeClass("active");
                    						}
	                    					else if(selectdate > today)
                    						{
                    						$(this).siblings(".errormsg").show();
	                                        fildValidation(this);
	                                        $(this).siblings("label").removeClass("active");
                    						}
	                    					else if(userdob == nomineedate)
                    						{
                    						$(this).siblings(".errormsg").show();
	                                        fildValidation(this,"The nominee's date of birth can't be same as the primary account holder's date of birth");
	                                        $(this).siblings("label").removeClass("active");
                    						}
	                    					else{
                					$(this).siblings(".errormsg").hide();
                                    fildValidation(this);
                                    $(this).siblings("label").addClass("active");}
	                    					}
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
	                            var fullname = $('#fullNamePD').val();
		                        var nomineeName = $('#nomineeName').val();
		                        var guardianName = $('#sdpguardianName').val();
	                            if (!/^[a-zA-Z. ]*$/g.test($(this).val())) {
	                                $(this).siblings(".errormsg").show();
	                                fildValidation(this, "Only alphabets are allowed");

	                            } else if(fullname.toUpperCase() == nomineeName.toUpperCase()){
	                        		console.log('=== Inside IF Nominee ====');
                            		$(this).siblings(".errormsg").show();
		                            fildValidation(this,"The nominee's name can't be same as the primary account holder's name");
                            	} else if(nomineeName.toUpperCase() == guardianName.toUpperCase()){
	                        		console.log('=== Inside IF Guardian ====');
                            		$(this).siblings(".errormsg").show();
		                            fildValidation(this,"The guardian's name can't be same as the nominee's name");
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
	                        		if (month.length >=3 ) {
	                        			$(this).siblings(".errormsg").show();
	                        			fildValidation(this);
	                        			$(this).siblings("label").removeClass("active");
	                        		}else if( month == "00")
	                        			{
	                        			$(this).siblings(".errormsg").show();
	                        			fildValidation(this);
	                        			$(this).siblings("label").removeClass("active");
	                        		}
	                        		if (day.length >= 3) {
	                        			$(this).siblings(".errormsg").show();
	                        			fildValidation(this);
	                        			$(this).siblings("label").removeClass("active");
	                        		}else if( day == "00")
	                        			{
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
									
								  
								}else if(value.includes("<") || value.includes(">"))
									{
									$(this).siblings(".errormsg").show();
									  fildValidation(this); 
									}
		                        else{
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
		                   	 
		                   	 
		                   	 else if (value < 15000 || value > 50000000) {
		                            $(this).siblings(".errormsg").show();
		                            fildValidation(this, "Please enter value between 15000 to 50000000");
		                          
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
		                      	 
		                      	 
		                      	 else if (value < 5000 || value > 50000000) {
		                               $(this).siblings(".errormsg").show();
		                               fildValidation(this, "Please enter value between 5000 to 50000000");
		                             
		                           } else {
		                               $(this).siblings(".errormsg").hide();
		                               fildValidation(this);
		                            
		                           }
		
		                      }
		                    else if ($(this).hasClass('tenorPeriod'))
		                       {
		                        	 var value =$('input[name="tenor"]:checked').val();
		                        
		                        	 if (value =="" || value==undefined) {
		                                $(this).siblings(".errormsg").show();
		                                fildValidation(this, "Please Select tenor");
		
		                            }
		                            else {
		                                $(this).siblings(".errormsg").hide();
		                                fildValidation(this);
		                             
		                            }
		
		                        } 
		                       else if ($(this).hasClass('sdptenorPeriod'))
		                       {
		                        	 var value =$('input[name="sdptenor"]:checked').val();
		                        
		                        	 if (value =="" || value==undefined) {
		                                $(this).siblings(".errormsg").show();
		                                fildValidation(this, "Please Select tenor");
		
		                            }
		                            else {
		                                $(this).siblings(".errormsg").hide();
		                                fildValidation(this);
		                             
		                            }
		
		                        }else if ($(this).hasClass('customTenor')) {
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
		                        var maturityScheme = $('.a_radiodeposit label input[name="maturitysdheme"]:checked').val().trim();

		                        if (maturityScheme == "Monthly Maturity") {
		                        	maturityScheme = "Monthly Maturity";
		                        } else {
		                        	maturityScheme = "Single Maturity";
		                        }
  
		                        if (maturityScheme == "Single Maturity") {
		                        	if (value < 19 || value > 60) {
		                        		$(this).siblings(".errormsg").show();
		                        		fildValidation(this, "Enter Tenor between 19 to 60");
		                        	} else {
		                        		$(this).siblings(".errormsg").hide();
		                        		fildValidation(this);
		                        	}
		                        } else {
		                        	if (value < 12 || value > 60) {
		                        		$(this).siblings(".errormsg").show();
		                        		fildValidation(this, "Enter Tenor between 12 to 60");

		                        	} else {
		                        		$(this).siblings(".errormsg").hide();
		                        		fildValidation(this);
		                        	}
		                        }
		                        }
		                   
		                    else if ($(this).hasClass('accountNumVD')) {
		                    	var accNum=$(".accountNumVD:visible").val();
		                        if (!/^[0-9]{9,18}$/.test(accNum)) {
		                            $(this).siblings(".errormsg").show();
		                            fildValidation(this);
		
		                        } else {
		                            $(this).siblings(".errormsg").hide();
		                            fildValidation(this);
		                        }
		
		                        }else if ($(this).hasClass('ifscCodeVD')) {
		                    		
	                            	
	                            	 var value = $(".ifscCheck:visible").val();
	                        		     value=value.toUpperCase();
	                        			 ifscValidator(value); 
	                            	if(value.length==11)
	                            		{
	                            		
	                            		ifscValidator(value); 
	                            		}
	                            	else
	                            		{
	                            		if (!/^[A-Z]{4}0[A-Z0-9]{6}$/.test($(this).val())) {
			                                fildValidation(this);  
			
			                            }else{
			                            	ifscValidator(value);
			                            }
	                            		}
	                            	function ifscValidator(userValue){
	                            			
	                            			var data = {
	                            					 "ifscCode":userValue,
	                            					 "fdcNumber":fdcNumber
	                            					 };
	                            			 
	                            				       
	                            			         $.ajax({ 
	                            			                                    
	                            			             url: "/fixed-deposit-application-form/ifscValidator", 
	                            			             type: "POST",
	                            			             data:JSON.stringify(data),
	                            			             contentType: 'application/json',
	                            			             error: function(response)
	                            				 			{
	                            			            	 	newRelicAlert("Technical Error Page in ifscValidator | Fail Ajax call","'/fixed-deposit-application-form/ifscValidator'",response.status,fdcNumber);
	                            				          		console.log("inside errror function");
	                            				             }  ,
	                            			             success: function(response) {            
	                            			            
	                            			            	var response = JSON.parse(response);
	                            			            	 if((response.status == "success")){
	                 							            	
	                            			            		 $(".ifscCheck").siblings(".errormsg").hide();
	                            			            		   
	                            			            	 }else{
	                            			            		 $(".ifscCheck").siblings(".errormsg").text("Please enter valid IFSC code").show();
	                            			            	
	                            			            	    }		
	                            			            	 }   
	                            			      });
	                            			
	                            			}
	                            			
	                        }else if ($(this).hasClass('FullNameVD')) {
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
		
		   /* $(".a_ReInput select").change(function() {
		
		    	 if ( $(this).val() == null) {
		    		 $('select option:contains("Bank Name")').prop('selected',true);
		
		    	 }
		    });
		   */
			
			$('.a_questCardBlok').slick({
    	  dots: false,
    	  infinite: false,
    	  speed: 300,
    	  slidesToShow: 3.1,
    	  slidesToScroll: 3,
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
		    
		    $(".a_ReInput input").change(function() {
		
		        if (!$(this).hasClass("nomandetory")) {
		    if ($(this).hasClass('UploadVD')) {
		        
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
				    $(this).siblings(".fildloader").show();
			   }
		       else if(fileexist>4194305){
				 $(this).parent(".uploadDoc").siblings(".errormsg").text("Max file size allowed is 4mb").show();
				  $(this).siblings(".fildloader").show();
			   }
		       else
		    	   {
		    	   $(this).parent(".uploadDoc").siblings(".errormsg").hide();
		    	   $(this).siblings(".fildloader").hide();
		    	   }
		    
				 }
		    
		        }
		       
		
		       });
		    
		    
		    

		    
		
		    $('.gaurdianAge').keyup(function(e) {
		        var mo = $(this).val();
		        if (mo.length > 2) {
		            $(this).val(mo.substr(0, 2));
		        }
		    });
		
		    $('.PinCodeVD').keyup(function(e) {
		        var mo = $(this).val();
		        if (mo.length > 6) {
		            $(this).val(mo.substr(0, 6));
		        }
		    });
		
		    $('.PanVD').blur(function(e) {
		        var mo = $(this).val();
		        mo = mo.toUpperCase();
		        $(this).val(mo);
		
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
		
		
		
		    $('.mobileVD').keyup(function(e) {
		        var mo = $(this).val();
		        if (mo.length > 10) {
		            $(this).val(mo.substr(0, 10));
		        }
		
		
		    });
		    $('.mobileVD').focus(function() {
		        var moval = $(this).val();
		        moval = moval.replace("-", "");
		        $(this).val(moval);
		        $(this).prop('type', 'number');
		    });
		    
		    $(".mobileVD").keydown(function(event) {
		        k = event.which; 
		          
		        if ((k >= 48 && k <= 57) || (k >= 96 && k <= 105) || k == 8 || k == 9) {
		          if ($(this).val().length == 10) {
		            if (k == 8 || k == 9) {
		              return true;
		            } else {
		              event.preventDefault();
		              return false;

		            }
		          }
		        } else {
		          event.preventDefault();
		          return false;
		        }

		      });
		
		    $('.partnerVD').keyup(function(e) {
		        var mo = $(this).val();
		        if (mo.length > 6) {
		            $(this).val(mo.substr(0, 6));
		        }
		
		    });
		 
		    $(".sliderAmunt").keypress(function (e) {
		       
		        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		         return false ;
		       }
		      });
		    $(".sliderAmunt_2").keypress(function (e) {
		        
		        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		        	  return false ;     
		       }
		      });
		    
		    $('.sliderAmunt').keyup(function(e) {
		        var mo = $(this).val();
		        if (mo.length > 8) {
		            $(this).val(mo.substr(0, 8));
		        }
		
		    });
		    $('.sliderAmunt_2').keyup(function(e) {
		        var mo = $(this).val();
		        if (mo.length > 8) {
		            $(this).val(mo.substr(0, 8));
		        }
		
		    });
		 
		    $('.customTenor').keyup(function(e) {
		        var mo = $(this).val();
		        if (mo.length > 2) {
		            $(this).val(mo.substr(0, 2));
		        }
		
		    });
		    
		    $('.customTenor_2').keyup(function(e) {
		        var mo = $(this).val();
		        if (mo.length > 2) {
		            $(this).val(mo.substr(0, 2));
		        }
		
		    });
		    
		   
		    $('#SendOtp .validBtn').click(function(e) { 
		    	var val = $('.a_radiodeposit label input[name="fd"]:checked').val();
		    	//trackPageviewScript('Step_2');
        		if (val =="newuser"&& $('#new').prop('checked'))
        		{
        	
		    	getIp();
		    	googleEventGenerateOTP();
		    	fbPixeltagGetOTP();
		    	var yearOne = $('.yearOne').val();
				 var monthOne = $('.monthOne').val();
				 var dateOne = $('.dateOne').val();
				 
		  var today = new Date();
		   var birthDate = new Date(yearOne+'/'+monthOne+'/'+dateOne);
		   var age = today.getFullYear() - birthDate.getFullYear();
		   var m = today.getMonth() - birthDate.getMonth();
		   if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
		       age--;
		   }
			if(age > 18){
			   	$('#SendOtp .datepickerVD').siblings('.errormsg').hide();
			   }else{
				   $('#SendOtp .datepickerVD').siblings('.errormsg').show().text('Age minimum 18 year.');
			   } 
			var mo = $('.a_ReInput.datefield input:nth-of-type(3)').val().length;
			var day = $('.a_ReInput.datefield input:nth-of-type(1)').val().length;
			var month = $('.a_ReInput.datefield input:nth-of-type(2)').val().length;
			var monthOneVal = $('.a_ReInput.datefield input:nth-of-type(2)').val();
			 var dateOneVal = $('.a_ReInput.datefield input:nth-of-type(1)').val();
			 if (mo==4 && month==2 && day==2) {
				 
			 
		     if (monthOneVal=="00" || dateOneVal=="00") 
		     { 
		    	 $('#SendOtp .datefield .errormsg').show();
		     }
		    	 
		e.preventDefault();
		       setTimeout(function() {
		           var totalerror = 0;
		           var firsterr = 0;
		           $("#SendOtp .errormsg").each(function(i) {
		               if ($(this).css("display") == "block") {
		                   totalerror++;
		                   if (firsterr == 0) {
		                       firsterr = $(this).parent(".a_ReInput").offset().top;
		                   }
		                   var thh = $(this);
		                    if (thh.siblings('.borderDown')) {
		                        thh.prev('.borderDown').addClass('field-blink');
		                        setTimeout(function () {
		                            thh.prev('.borderDown').removeClass('field-blink');
		                        }, 4000);
		                    }
		                    if (thh.siblings('.input')) {
		                        thh.prev('input').addClass('field-blink');
		                        setTimeout(function () {
		                            thh.prev('input').removeClass('field-blink');
		                        }, 4000);
		                    }
		               }
		           });
		           if (totalerror == 0) {
		 
		               $('#SendOtp .p_reqotp button .fd_sdp_loder').show();	
		                  fdSendOTP();
		             
		             /*  $('.a_part_2').hide();
		               $('.a_part_3').show();
		               count3minut();    
		               $('html, body').animate({ scrollTop: 70 }, 200);*/
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
		               $('html, body').animate({'scrollTop':$(".errormsg").offset().top -200 },3000);
		           }
		       }, 500);
		     
		     }else{
		    	 $('#SendOtp .datefield .errormsg').show();
		    	 $('html, body').animate({'scrollTop':$(".errormsg").offset().top -200 },3000);
		    	 }
		    } else 
    		{
    			var custId=$('#enterapp').val();
    			if(!(custId.length==15))
    				{
    				$('.customerVD').siblings(".errormsg").show();
    				}else
    					{
    					$('.customerVD').siblings(".errormsg").hide();
    					}
    			if(custId!=null && custId!=undefined)
    				{
    	    		e.preventDefault();
    	    		       setTimeout(function() {
    	    		           var totalerror = 0;
    	    		           var firsterr = 0;
    	    		           $("#SendOtp .errormsg").each(function(i) {
    	    		               if ($(this).css("display") == "block") {
    	    		                   totalerror++;
    	    		                   if (firsterr == 0) {
    	    		                       firsterr = $(this).parent(".a_ReInput").offset().top;
    	    		                   }
    	    		                   var thh = $(this);
    	    		                    if (thh.siblings('.borderDown')) {
    	    		                        thh.prev('.borderDown').addClass('field-blink');
    	    		                        setTimeout(function () {
    	    		                            thh.prev('.borderDown').removeClass('field-blink');
    	    		                        }, 4000);
    	    		                    }
    	    		                    if (thh.siblings('.input')) {
    	    		                        thh.prev('input').addClass('field-blink');
    	    		                        setTimeout(function () {
    	    		                            thh.prev('input').removeClass('field-blink');
    	    		                        }, 4000);
    	    		                    }
    	    		               }
    	    		           });
    	    		           if (totalerror == 0) {
    	    		 
    	    		               $('#SendOtp .p_reqotp button .fd_sdp_loder').show();	
    	    		                  fdresumeotp();
    	    		                  
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
    	    		               $('html, body').animate({'scrollTop':$(".errormsg").offset().top -200 },3000);
    	    		           }
    	    		       }, 500);
    	    		       
    				}

    		}
		   });
		    
		   
		  
		    	
		     $('#panCarVerify .validBtn').click(function(e)
		    		 {	
		         e.preventDefault();
		         trackPageviewScript('PAN_Submit');
		         googleEventUserDetails();
		         fbPixeltagCKYCStep();
		        setTimeout(function() 
		        		{	
		            var totalerror = 0;	
		            var firsterr = 0;	
		            $("#panCarVerify .errormsg").each(function(i) {	
		                if ($(this).css("display") == "block") {	
		                    totalerror++;	
		                    if (firsterr == 0) {	
		                        firsterr = $(this).parent(".a_ReInput").offset().top;	
		                    }
		                    
		                    var thh = $(this);
		                    if (thh.siblings('.borderDown')) {
		                        thh.prev('.borderDown').addClass('field-blink');
		                        setTimeout(function () {
		                            thh.prev('.borderDown').removeClass('field-blink');
		                        }, 4000);
		                    }
		                    if (thh.siblings('.input')) {
		                        thh.prev('input').addClass('field-blink');
		                        setTimeout(function () {
		                            thh.prev('input').removeClass('field-blink');
		                        }, 4000);
		                    }
		                }	
		            });	
		            if (totalerror == 0)
		            {	
		            	var panCard=  $('#panCardPV').val();
		            	if(panCard !="")
		            		{
		            		$('#panCarVerify .p_reqotp button .fd_sdp_loder').show();	
			             	ntbPanDetails();
		            		}
		            	
		            	
		            } else {
		                if ($(window).width() < 900) {
		                    $('html, body').animate({
		                        scrollTop: firsterr - 55
		                    }, 200);
		                } else {
		                    $('html, body').animate({
		                        scrollTop: firsterr - 30
		                    }, 200);
		                }$('html, body').animate({'scrollTop':$(".errormsg").offset().top -200 },3000);
		            }
		        }, 500);     
		
		    });
		    
		    
		   
		     
		     $('.a_tNcPopBox .a_swichTgl label input').change(function(){
		         if($(this).is(':checked')){
		            $(this).parent().siblings('.p_fixdepositstultip').addClass('p_fixdepositstultipShow');
		 			$(this).siblings('.errormsg').show();
		         }else{
		 			$(this).siblings('.errormsg').hide();
		 		} 
		     });
		 	
		 	 $('.a_tNcPopBox .a_swichTgl .p_fixdepositstultip .p_fixdepositBG .p_gotitbtn button').click(function(){
		         $(this).parents('.p_fixdepositstultip').removeClass('p_fixdepositstultipShow');
		          $(this).parents('.p_fixdepositstultip').siblings('label').children('input').prop('checked',true);;
		     });
		
		    
		    $('#Personal_Details .a_ReInput input').blur(function(){
		        var perDet = 0;
		            $("#panform #Personal_Details .errormsg").each(function(i) {
		                if ($(this).css("display") == "block" || $(this).siblings('input').val() == "") {
		                   perDet++
		                }
		            });
		            if (perDet == 0) {
		                $('.a_topBoxbord .a_halfBox a[data-tab="Personal_Details"] ~ .tickNdisimg').attr('src','/fixed-deposit-application-form/resources/images/tick-round.png');
		            }else{ 
		                $('.a_topBoxbord .a_halfBox a[data-tab="Personal_Details"] ~ .tickNdisimg').attr('src','/fixed-deposit-application-form/resources/images/exclamation.png');
		            }
		    });
		    
		
		    
		    $('#Banking_Details .a_ReInput input').blur(function(){
		        var benkDet = 0;
		            $("#panform #Banking_Details .errormsg").each(function(i) {
		                if ($(this).css("display") == "block" || $(this).siblings('input').val() == "") {
		                   benkDet++
		                }
		            });
		            if (benkDet == 0) {
		                $('.a_topBoxbord .a_halfBox a[data-tab="Banking_Details"] ~ .tickNdisimg').attr('src','/fixed-deposit-application-form/resources/images/tick-round.png');
		            }else{ 
		                $('.a_topBoxbord .a_halfBox a[data-tab="Banking_Details"] ~ .tickNdisimg').attr('src','/fixed-deposit-application-form/resources/images/exclamation.png');
		            }
		            
		    });
		    
		  
		    
		    $('#panform .validBtn').click(function(e) {
		    	console.log("Personal Details button event Done");
		        e.preventDefault();
		        trackPageviewScript('PI_submit_Details');
		        googleEventBankDetails();
		        fbPixeltagPersonalDetails();
		        var totalerror = 0;
		        
		          if($('#nomineepinCodePV').parent(".primartAdd").css("display") == "block"){
					 totalerror = CityPrepopulateWithPincode('nomineepinCodePV',totalerror);
			        
					}
					if($('#guardiunPincode').parents(".a_gardianDet").css("display") == "block"){
					 totalerror = CityPrepopulateWithPincode('guardiunPincode',totalerror);
			        
					}
					if($('#commPincode').parent(".a_ReInput").css("display") == "block"){
					 totalerror = CityPrepopulateWithPincode('commPincode',totalerror);
			        
					}
		        setTimeout(function() {
		           var perDet = 0;
		           $("#panform #Personal_Details .errormsg").each(function(i) {
		               if ($(this).css("display") == "block") {
		                  perDet++
		               }
		           });
		           console.log("personal Details errormsg Count "+perDet);
		           if (perDet == 0) {
						$('#Personal_Details').removeClass('fildPartShow');
						$('#Banking_Details').addClass('fildPartShow');
						$('.a_confirmtabsSec .a_topBoxbord .a_halfBox a[data-tab="Personal_Details"]').removeClass('active');
						$('.a_confirmtabsSec .a_topBoxbord .a_halfBox a[data-tab="Banking_Details"]').addClass('active');
						
		               $('.a_topBoxbord .a_halfBox a[data-tab="Personal_Details"] ~ .tickNdisimg').attr('src','/fixed-deposit-application-form/resources/images/tick-round.png');
		               console.log("personal Details errormsg zero Count");
		           }else{ 
		               $('.a_topBoxbord .a_halfBox a[data-tab="Personal_Details"] ~ .tickNdisimg').attr('src','/fixed-deposit-application-form/resources/images/exclamation.png');
		               $('.a_confirmtabsSec .a_topBoxbord .a_halfBox a[data-tab="Personal_Details"]').addClass('active');
		             	$('.a_confirmtabsSec .a_topBoxbord .a_halfBox a[data-tab="Banking_Details"]').removeClass('active');
		           	    $('#Personal_Details').addClass('fildPartShow');
		           	    $('#Banking_Details').removeClass('fildPartShow');
		           	 console.log("personal Details errormsg present in html");
		           }
		           
		           var benkDet = 0;
		           $("#panform #Banking_Details .errormsg").each(function(i) {
		               if ($(this).css("display") == "block") {
		                  benkDet++
		               }
		           });
		           console.log("personal Details(nominee section) errormsg count in html "+benkDet);
		           if (benkDet == 0) {
		               $('.a_topBoxbord .a_halfBox a[data-tab="Banking_Details"] ~ .tickNdisimg').attr('src','/fixed-deposit-application-form/resources/images/tick-round.png');
		               console.log("Personal Details button tickNdisimg event Done");
		               console.log("personal Details(nominee section) no errormsg present in html");
		           }else{ 
		               $('.a_topBoxbord .a_halfBox a[data-tab="Banking_Details"] ~ .tickNdisimg').attr('src','/fixed-deposit-application-form/resources/images/exclamation.png');
		               console.log("personal Details(nominee section) errormsg present in html");
		           }
		       
		           var firsterr = 0;
		           $("#panform .errormsg").each(function(i) {
		               if ($(this).css("display") == "block") {
		                   totalerror++;
		                   if (firsterr == 0) {
		                       firsterr = $(this).parent(".a_ReInput").offset().top;
		                   }
		                   var thh = $(this);
		                    if (thh.siblings('.borderDown')) {
		                        thh.prev('.borderDown').addClass('field-blink');
		                        setTimeout(function () {
		                            thh.prev('.borderDown').removeClass('field-blink');
		                        }, 4000);
		                    }
		                    if (thh.siblings('.input')) {
		                        thh.siblings('input').addClass('field-blink');
		                        setTimeout(function () {
		                            thh.siblings('input').removeClass('field-blink');
		                        }, 4000);
		                    }
		                    if (thh.siblings('.select')) {
		                        thh.siblings('select').addClass('field-blink');
		                        setTimeout(function () {
		                            thh.siblings('select').removeClass('field-blink');
		                        }, 4000);
		                    }
		               }
		           });
		           
		           console.log("personal Details-nominee section total errormsg count in html "+totalerror);
		           if (totalerror == 0) {
		           
		        	  // $('.allfieldvali').hide();
		           $('#panform .a_blueBtnPart button .fd_sdp_loder').show();
						
		          sendUserDetails();	
		         		
		      /*   $('.a_part_4').hide();
		              $('.a_part_5').show();
		              
		              
		              $('html, body').animate({ scrollTop: 70 }, 200);*/
		           } else {
		        	   //$('.allfieldvali').show();
		               if ($(window).width() < 900) {
		                   $('html, body').animate({
		                       scrollTop: firsterr - 55
		                   }, 200);
		               } else {
		                   $('html, body').animate({
		                       scrollTop: firsterr - 30
		                   }, 200);
		               }
		               $('html, body').animate({'scrollTop':$(".errormsg").offset().top -200 },3000);
		           }
		       }, 500);
		
		   });
		    

		    
		    $('#fdNominee .validBtn').click(function(e) {
		         e.preventDefault();
		         googleEventPaymentProceed();
		         fbPixeltagBankDetailsProeed();
		         trackPageviewScript('Proceed_to_Pay_FD');
		         if($('.a_radiodeposit input[name="banking_det_1"]').is(':checked'))
		         {
		        	 
		             $('input[name="banking_det_1"]').parents('.a_radiodeposit').siblings('.errormsg').hide();
		         }else
		         {
		        	
		             $('input[name="banking_det_1"]').parents('.a_radiodeposit').siblings('.errormsg').show();
		         }
		         
		        
		         if($('input[name="tenor"]').is(':checked')  || $('#fdCustomTenor').is(':visible'))
		         {
		             $('input[name="tenor"]').parents('.tenorPeriod').siblings('.errormsg').hide();
		         }else{
		             $('input[name="tenor"]').parents('.tenorPeriod').siblings('.errormsg').show();
		         }
		         var fdmaturityAmnt = $("#FDmaturityAmnt").text().trim();
	    	     var totPayoutAmtHiddenVal=$("#hiddenPayOutTypeCheck").val().trim();
	    	     
		        setTimeout(function() {
		            var totalerror = 0;
		            var firsterr = 0;
		            $("#fdNominee .errormsg").each(function(i) {
		                if ($(this).css("display") == "block") {
		                    totalerror++;
		                    if (firsterr == 0) {
		                        firsterr = $(this).parent(".a_ReInput").offset().top;
		                    }
		                    
		                    var thh = $(this);

		                    if (thh.siblings('.select')) {
		                        thh.siblings('select').addClass('field-blink');
		                        setTimeout(function () {
		                            thh.siblings('select').removeClass('field-blink');
		                        }, 4000);
		                    }
		                    if (thh.siblings('.input')) {
		                        thh.siblings('input').addClass('field-blink');
		                        setTimeout(function () {
		                            thh.siblings('input').removeClass('field-blink');
		                        }, 4000);
		                    }
		                }
		            });
		          		            
		            var Ifscvalue = $(".ifscCheck:visible").val().trim();
		            var status=/^[A-Z]{4}0[A-Z0-9]{6}$/.test(Ifscvalue) ;
		            if (totalerror == 0) {
		            	
		            	if(status)
		            		{
		            		$('#fdNominee button .fd_sdp_loder').show();
			            	datalayerEventValue("form_submission_Loan_ApplicationPage_2","Proceed to Pay",true,$("#fdAmount").val().trim());
		            		if (isFDPayActionInProgress == 'No') {
		            			isFDPayActionInProgress = 'Yes';
			            		fdCalculatorDetails();
			                }
		            		}
		            	
		    	    	
		              /*  $('.a_blackoverlay').toggle();
		                $('.a_termsAndConPart').toggleClass('a_termsAndConPartShow');*/
		            } else {
		            	datalayerEventValue("form_submission_Loan_ApplicationPage_2","Proceed to Pay",false,$("#fdAmount").val().trim());
		                if ($(window).width() < 900) {
		                    $('html, body').animate({
		                        scrollTop: firsterr - 55
		                    }, 200);
		                } else {
		                    $('html, body').animate({
		                        scrollTop: firsterr - 30
		                    }, 200);
		                }
		                $('html, body').animate({'scrollTop':$(".errormsg").offset().top -200 },3000);
		            }
		        }, 500);
		
		    });
		    
		    
		    $('#sdpnomeeni .validBtn').click(function(e) {
		         e.preventDefault();
		        
		         trackPageviewScript('Proceed_to_Pay_SDP');
		         googleEventPaymentProceed();
		         fbPixeltagBankDetailsProeed();
		         if($('.a_radiodeposit input[name="banking_det_2"]').is(':checked')){
		             $('input[name="banking_det_2"]').parents('.a_radiodeposit').siblings('.errormsg').hide();
		         }else{
		             $('input[name="banking_det_2"]').parents('.a_radiodeposit').siblings('.errormsg').show();
		         }
		         if($('input[name="sdptenor"]').is(':checked') || $('#sdpCustomTenor').is(':visible') )
		         {
		             $('input[name="sdptenor"]').parents('.sdptenorPeriod').siblings('.errormsg').hide();
		         }else{
		             $('input[name="sdptenor"]').parents('.sdptenorPeriod').siblings('.errormsg').show();
		         }
		         var totPayoutAmt = $("#totPayAmt").text().trim();  
		    	 var totPayoutAmtHiddenVal=$("#hiddenPayOutTypeCheck").val().trim();
		        setTimeout(function() {
		            var totalerror = 0;
		            var firsterr = 0;
		            $("#sdpnomeeni .errormsg").each(function(i) {
		                if ($(this).css("display") == "block") {
		                    totalerror++;
		                    if (firsterr == 0) {
		                        firsterr = $(this).parent(".a_ReInput").offset().top;
		                    }
		                    
		                    var thh = $(this);

		                    if (thh.siblings('.select')) {
		                        thh.siblings('select').addClass('field-blink');
		                        setTimeout(function () {
		                            thh.siblings('select').removeClass('field-blink');
		                        }, 4000);
		                    }
		                    if (thh.siblings('.input')) {
		                        thh.siblings('input').addClass('field-blink');
		                        setTimeout(function () {
		                            thh.siblings('input').removeClass('field-blink');
		                        }, 4000);
		                    }
		                }
		            });
		            var Ifscvalue = $(".ifscCheck:visible").val().trim();
		            var status=/^[A-Z]{4}0[A-Z0-9]{6}$/.test(Ifscvalue) ;   
                    var sdpTenor = $('input[name="sdptenor"]:checked').val();
		    	    if(sdpTenor==undefined)
		               {
		    	    	sdpTenor=$("#sdpCustomTenor").val().trim();
		               }
		    	    var sdpDeposit = $("#sdpDeposit").val().trim();
		    	    
                var maturityScheme = $('.a_radiodeposit label input[name="maturitysdheme"]:checked').val().trim();
		    	    
		    	    if(maturityScheme=="Monthly Maturity"){
		    	    	maturityScheme = "Monthly Maturity";
		    	    }else{
		    	    	maturityScheme = "Single Maturity"; 
		    	    }
		    	    
		    	    if(maturityScheme == "Single Maturity")
		    	    	{
		    	    	sdpTenor=Number(sdpTenor);
		    	    	var highestRange=sdpTenor-13;
		    	    	if(!(sdpDeposit>=6 && sdpDeposit<=highestRange))
		    	    		{
		    	    		totalerror++;
		    	    		}
		    	    	}
		    	    
			            if (totalerror == 0) {
		            	
		            	if(status)
		            		{
		            		$('#sdpnomeeni button .fd_sdp_loder').show();
			            	datalayerEventValue("form_submission_Loan_ApplicationPage_2","Proceed to Pay",true,$("#sdpAmount").val().trim());
			            	if (isSDPPayActionInProgress == 'No') {
			            		isSDPPayActionInProgress = 'Yes';
			            		sdpCalculatorDetails();
			                }
			            	
			            	
		            		}
		            	
		    	    	
		
		              /*  $('.a_blackoverlay').toggle();
		                $('.a_termsAndConPart').toggleClass('a_termsAndConPartShow');*/
		            } else {
		            	datalayerEventValue("form_submission_Loan_ApplicationPage_2","Proceed to Pay",false,$("#sdpAmount").val().trim());
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
		    
		    
		    
		    
		    $('#prsonalDet .validBtn').click(function(e) {
		         e.preventDefault();
		        
		 
		        
		        setTimeout(function() {
		            var totalerror = 0;
		            var firsterr = 0;
		            $("#prsonalDet .errormsg").each(function(i) {
		                if ($(this).css("display") == "block") {
		                    totalerror++;
		                    if (firsterr == 0) {
		                        firsterr = $(this).parent(".a_ReInput").offset().top;
		                    }
		                }
		            });
		            if (totalerror == 0) {
		                $('#prsonalDet .p_paymentinput').addClass('a_onlulabel');
		                $('.p_titleploan .p_maintitleedit a').text('Edit');
		                $('.paymentpart #prsonalDet').find('.a_ReInput input').attr('disabled', 'disabled');
		                
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
		                $('html, body').animate({'scrollTop':$(".errormsg").offset().top -200 },3000);
		            }
		        }, 500);
		
		    });
		    
		    /* end validation js */
		
		
		  /*****************************************************************************************************************************************************************/
		    
		    
		    /*****************Start Part*********************/
		    var getCardLink = "";
		    
		    $(".fixedtulltip").click(function(e) {
		        
		        $(this).parents('.p_carddeposit').find('.p_fixdepositstultip').addClass('p_fixdepositstultipShow');
		        e.stopPropagation();
		        
		        /*getCardLink = $(this).parents('a').attr('href');
		        $(this).parents('a').attr('href','#;');*/
		    });
		 
		
		    $(".p_carddeposit a").click(function(e) {
		       e.preventDefault();
		        var getLink = $(this).attr('data-href');
		      
		        $(this).parents('.a_part_1').hide();
		        $('.'+getLink).show();
		        $( "#mobileNO" ).focus();
		        
		      
		        
		    });
		    
		    $(".p_fixdepositBG").click(function(e) {
		        e.stopPropagation();
		    }); 
		    
			
			$(".gotitone").click(function (e) {
		        e.stopPropagation();
		        e.preventDefault();
		        $(this).parents('.p_carddeposit').find('.p_fixdepositstultip').removeClass('p_fixdepositstultipShow');
		        $(this).parents('.a_ReInput').find('.p_fixdepositstultip').removeClass('p_fixdepositstultipShow');
		        $(this).parents('.p_fixdepositstultip').removeClass('p_fixdepositstultipShow');
		    });
		    $(".a_ReInput label img").click(function(e) {
		        e.stopPropagation();
				e.preventDefault();
		        $(this).siblings('.p_fixdepositstultip').addClass('p_fixdepositstultipShow');
		    });

		    
		    $(".a_afterMarurity strong img").click(function (e) {
		        e.stopPropagation();
		        e.preventDefault();
		        $(this).siblings('.p_fixdepositstultip').addClass('p_fixdepositstultipShow');
		    });
		    
		    $(".tooltipOnpaymentMode img").click(function (e) {
		        e.stopPropagation();
		        e.preventDefault();
		        $(this).siblings('.p_fixdepositstultip').addClass('p_fixdepositstultipShow');
		    });
		    
		        
		    $(document).click(function(){
		        $('.p_fixdepositstultip').removeClass('p_fixdepositstultipShow');
		    });
		
		 
		
		
		    if ($(window).width() < 768) {
		    $(".p_paynow .p_amountpayable .p_texttulltip .p_infobox").click(function(e) {
		        $(".p_sysdeposit").show();
		        e.stopPropagation();
		    });
		
		    $(".p_sysdeposit").click(function(e) {
		        e.stopPropagation();
		    });
		
		    $(document).click(function(){
		        $(".p_sysdeposit").hide();
		    });
		    }
		    
		    
		    
		    $('.p_addnominiunder .a_swichTgl label input').change(function(){
		        if($(this).is(':checked')){
		            $(this).parents('.a_rightSideCon').find('.p_paymentinput').slideDown(200);
		            
		            $(this).parents('.a_rightSideCon').find('.p_paymentinput').find('.a_ReInput input').removeClass('nomandetory');
		            $(this).parents('.a_rightSideCon').find('.p_paymentinput').find('.a_ReInput select').removeClass('nomandetory');
		            
		            $(this).parents('.a_rightSideCon').find('.submitBTN').removeClass('addColor');
		        }else{
		            $(this).parents('.a_rightSideCon').find('.p_paymentinput').slideUp(200,function(){
		                $(this).parents('.a_rightSideCon').find('.p_paymentinput').find('.errormsg').hide();
		            });
		            
		            
		            $(this).parents('.a_rightSideCon').find('.p_paymentinput').find('.a_ReInput input').addClass('nomandetory');
		            $(this).parents('.a_rightSideCon').find('.p_paymentinput').find('.a_ReInput select').addClass('nomandetory');
		            
		            $(this).parents('.a_rightSideCon').find('.submitBTN').addClass('addColor');
		        }
		    });
		    
		    //#3f4353
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
		    $('.a_ReInput2 select').change(function(){
		        $(this).parents('.a_ReInput_sapce').siblings('.a_satausfild').slideDown(200);
		        $(this).parents('.mainOneBlock').siblings().find('.statusgrip').slideUp(200);
		        $('.a_amountRange').slideUp(200); 
		    });
		    
		    $('.a_ReInput:not(.birthSelect) select').blur(function(){
		            var th = $(this);
					setTimeout(function(){validornot(th)},300);
		        
		    });
		    $('.a_ReInput.birthSelect select').blur(function(){
		            /*var th = $(this);
					setTimeout(function(){validornot(th)},300);*/
		        
		        
		        var formId = $(this).parents('form').attr('id');
		        var daySel = $('#'+formId+' .dayselect').val();
		        var monthSel = $('#'+formId+' .monthselect').val();
		        var yearSel = $('#'+formId+' .yearselect').val();
		        //a_ReInput birthSelect invalid
		        if(daySel && monthSel && yearSel){
		           $(this).parent().siblings('.errormsg').hide();
		            $(this).parents('.a_ReInput').removeClass('invalid');
		            
		        }else{
		            $(this).parent().siblings('.errormsg').show();
		            $(this).parents('.a_ReInput').addClass('invalid');
		        }
		        
		    });
		    $('.a_ReInput:not(.birthSelect) select').change(function(){
		            var th = $(this);
					setTimeout(function(){validornot(th)},300);
		        
		    });
		    $('.a_ReInput select').change(function(){
		        $(this).css('color','#3f4353');
		        //$(this).parents('.a_ReInput').removeClass('active');
		        
		    });
		    /*****************End Part*********************/
		    
		    
		    function validornot(th){
				if(!th.hasClass("nomandetory")){
				if(th.siblings(".errormsg").css("display")=="none" ){
					th.parents('.a_ReInput').addClass("valid").removeClass("invalid");
				}else{
					th.parents('.a_ReInput').removeClass("valid").addClass("invalid");
				}
				}
			}
		    
		    
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
		
		 
		    if($('.sliderAmunt').length > 0){ 
		        
		        var SetSldrval = $('.sliderAmunt').val().replace(/\,/g, '');
		        if(SetSldrval >= 400000){
		            $(".a_amountRange ~ .sliderBotDesc").animate({scrollLeft:1000}, 0);
		           }else{
		               $(".a_amountRange ~ .sliderBotDesc").animate({scrollLeft:0}, 0);
		           }
		       }
		 
		    
		   $('.sliderAmunt').focus(function(){
		        var Sldrval =$(this).val();
		        Sldrval = Sldrval.replace(/\,/g, '');
		        $(this).val(Sldrval); 
		        
		        var amountInVal = $(this).val();
		        
		        if(amountInVal < 15000){
		            $(this).parents('.mainOneBlock').find('.a_opplsLink').slideDown(200);
		            $(this).parents('.mainOneBlock').find('.a_opplsLink').siblings('.statusgrip').hide();
		            
		        }else if(amountInVal >= 400000){
		            $(this).parents('.mainOneBlock').find('.sliderBotDesc').slideDown(200);  
		            $(".a_amountRange ~ .sliderBotDesc").animate({scrollLeft:1000}, 300);
		              
		        }else{
		            $(this).parents('.mainOneBlock').find('.sliderBotDesc').slideDown(200);  
		            $(".a_amountRange ~ .sliderBotDesc").animate({scrollLeft:0}, 300);
		              
		        }
		        
		        $(this).parents('.mainOneBlock').siblings().find('.statusgrip').slideUp(200);
		        
		    });
		    
		    
		    $('.sliderAmunt').blur(function(){
		        var amountInVal = $(this).val();
		        amountInVal = amountInVal.replace(/\,/g, '');
		        
		           $(this).val(inrFormat(amountInVal));
		 
		        
		        $(this).parents('.mainOneBlock').siblings().find('.statusgrip').slideUp(200);
		        if(amountInVal < 15000){
		            $(this).parents('.mainOneBlock').find('.a_opplsLink').slideDown(200);
		            $(this).parents('.mainOneBlock').find('.a_opplsLink').siblings('.statusgrip').hide(); 
		            
		        }else if(amountInVal >= 400000){
		            
		            $(this).parents('.mainOneBlock').find('.a_opplsLink').slideUp(200);
		            $(this).parents('.mainOneBlock').find('.a_opplsLink').siblings('.statusgrip').slideDown(200);
		            $(".sliderBotDesc").animate({scrollLeft:1000}, 300); 
		        }else{
		            
		            $(this).parents('.mainOneBlock').find('.a_opplsLink').slideUp(200);
		            $(this).parents('.mainOneBlock').find('.a_opplsLink').siblings('.statusgrip').slideDown(200);
		            $(".sliderBotDesc").animate({scrollLeft:0}, 300); 
		        }
		        
		  
		        if(amountInVal < 15000){
		            $(this).parent().addClass('notvalid');
		        }else{
		            $(this).parent().removeClass('notvalid');    
		        }
		        
		 
		    });
		    
		    
		 
		    if($('.sliderAmunt_2').length > 0){
		        
		        var SetSldrval = $('.sliderAmunt_2').val().replace(/\,/g, '');
		        if(SetSldrval >= 10000){
		           $(".a_amountRange ~ .sliderBotDesc").animate({scrollLeft:1000}, 0);
		           }else{
		               $(".a_amountRange ~ .sliderBotDesc").animate({scrollLeft:0}, 0);
		           }
		       }
		 
		    $('.sliderAmunt_2').focus(function(){
		        var Sldrval =$(this).val();
		        Sldrval = Sldrval.replace(/\,/g, '');
		        $(this).val(Sldrval);
		        
		            
		        var amountInVal = $(this).val();
		        
		        if(amountInVal < 5000){
		            $(this).parents('.mainOneBlock').find('.a_opplsLink').slideDown(200);
		            $(this).parents('.mainOneBlock').find('.a_opplsLink').siblings('.statusgrip').hide();
		            
		        }else if(amountInVal >= 10000){
		            $(this).parents('.mainOneBlock').find('.sliderBotDesc').slideDown(200);  
		            $(".a_amountRange ~ .sliderBotDesc").animate({scrollLeft:1000}, 300);
		              
		        }else{
		            $(this).parents('.mainOneBlock').find('.sliderBotDesc').slideDown(200);  
		            $(".a_amountRange ~ .sliderBotDesc").animate({scrollLeft:0}, 300);
		              
		        }
		        
		        $(this).parents('.mainOneBlock').siblings().find('.statusgrip').slideUp(200);
		         
		    });
		    $('.sliderAmunt_2').blur(function(){
		        var amountInVal = $(this).val();
		        amountInVal = amountInVal.replace(/\,/g, '');
		        
		           $(this).val(inrFormat(amountInVal));
		 
		        
		        $(this).parents('.mainOneBlock').siblings().find('.statusgrip').slideUp(200);
		        if(amountInVal < 5000){
		            $(this).parents('.mainOneBlock').find('.a_opplsLink').slideDown(200);
		            $(this).parents('.mainOneBlock').find('.a_opplsLink').siblings('.statusgrip').hide();
		        }else if(amountInVal >= 10000){
		            
		            $(this).parents('.mainOneBlock').find('.a_opplsLink').slideUp(200);
		            $(this).parents('.mainOneBlock').find('.a_opplsLink').siblings('.statusgrip').slideDown(200);
		            $(this).parents('.mainOneBlock').find(".sliderBotDesc").animate({scrollLeft:1000}, 300);
		        }else{
		            
		            $(this).parents('.mainOneBlock').find('.a_opplsLink').slideUp(200);
		            $(this).parents('.mainOneBlock').find('.a_opplsLink').siblings('.statusgrip').slideDown(200);
		            $(this).parents('.mainOneBlock').find(".sliderBotDesc").animate({scrollLeft:0}, 300);
		        }
		 
		    });
		    
		    
		    
		    
		    
		    
		    
		    $('.a_selectblock .a_part20 label input[type="radio"]').change(function(){
		        //$('.statusgrip').slideUp(200);
		        $(this).parents('.mainOneBlock').siblings().find('.statusgrip').slideUp(200);
		        $('.a_amountRange').slideUp(200);
		        $(this).parents('.mainOneBlock').find('.a_satausfild').slideDown(200);    
		        $(this).parents('.mainOneBlock').find('.sliderBotDesc').slideDown(200);    
		    });
		  
		    
		    $('.a_selectblock .a_part20 label input[name="tenor"]').change(function(){ 
		        $(this).parents('.mainOneBlock').find('.customTenor').val("");
		        
		        if($(this).val() == '60'){
		           $(this).parents('.a_selectblock').siblings(".a_monthCustom ~ .sliderBotDesc").animate({scrollLeft:1000}, 300);
		        }else{
		            $(this).parents('.a_selectblock').siblings(".a_monthCustom ~ .sliderBotDesc").animate({scrollLeft:0}, 300);
		        }
		        
		        var tenorVal = $(this).val();
		       
		            if(tenorVal == 'Custom'){
		                $(this).parents('.a_selectblock').siblings('.a_monthCustom').slideDown(200);  
		                $("#fdCustomTenor").removeClass('nomandetory');
		            }else{
		                $(this).parents('.a_selectblock').siblings('.a_monthCustom').slideUp(200);  
		                $("#fdCustomTenor").addClass('nomandetory');
		                $('#fdCustomTenor').siblings('.errormsg').hide();
		            }
		            if($('input[name="tenor"]').is(':checked'))
			         {
			             $('input[name="tenor"]').parents('.tenorPeriod').siblings('.errormsg').hide();
			         }else{
			             $('input[name="tenor"]').parents('.tenorPeriod').siblings('.errormsg').show();
			         }
		        
		    });
		    
		  
		    
		    $('.a_selectblock .a_part20 label input[name="sdptenor"]').change(function(){ 
		        $(this).parents('.mainOneBlock').find('.customTenor_2').val("");
		        
		        if($(this).val() == '60'){
		           $(this).parents('.a_selectblock').siblings(".a_monthCustom ~ .sliderBotDesc").animate({scrollLeft:1000}, 300);
		        }else{
		            $(this).parents('.a_selectblock').siblings(".a_monthCustom ~ .sliderBotDesc").animate({scrollLeft:0}, 300);
		        }
		        
		        var tenorVal = $(this).val();
		     
		            if(tenorVal == 'Custom'){
		                $(this).parents('.a_selectblock').siblings('.a_monthCustom').slideDown(200);  
		                $("#sdpCustomTenor").removeClass('nomandetory');
		            }else{
		                $(this).parents('.a_selectblock').siblings('.a_monthCustom').slideUp(200);
		                $("#sdpCustomTenor").addClass('nomandetory');
		                $('#sdpCustomTenor').siblings('.errormsg').hide();
		            }
		            if($('input[name="sdptenor"]').is(':checked'))
			         {
			             $('input[name="sdptenor"]').parents('.sdptenorPeriod').siblings('.errormsg').hide();
			         }else{
			             $('input[name="sdptenor"]').parents('.sdptenorPeriod').siblings('.errormsg').show();
			         }
		        
		    });
		    
		    $('.a_selectblock .a_part20 label input[name="payOut"]').change(function(){
		        
		        if($(this).val() == 'OnMaturity'){
		           $(".a_selectblock + .sliderBotDesc").animate({scrollLeft:1000}, 300);
		        }else{
		            $(".a_selectblock + .sliderBotDesc").animate({scrollLeft:0}, 300);
		        }
		        
		 
		        
		    });
		    
		    $('.customTenor').focus(function(){
		        
		        $(this).parents('.mainOneBlock').find("input[name='tenor']").prop('checked',false);
		        //$('.statusgrip').hide();
		        $(this).parents('.mainOneBlock').find('.sliderBotDesc').slideDown(200);
		        $(this).parents('.mainOneBlock').siblings().find('.statusgrip').slideUp(200);
		    });
		    
		    $('.customTenor_2').focus(function(){
		        
		        $(this).parents('.mainOneBlock').find("input[name='sdptenor']").prop('checked',false);
		        //$('.statusgrip').hide();
		        $(this).parents('.mainOneBlock').find('.sliderBotDesc').slideDown(200);
		        $(this).parents('.mainOneBlock').siblings().find('.statusgrip').slideUp(200);
		    });
		    
		    $('.a_titlePart .a_gotItInfo a').click(function(){
		        $(this).parent('.a_gotItInfo').removeClass('a_gotItInfoShow');
		    });
		    
		    $('.a_titlePart > a').click(function(){
		        $(this).siblings('.a_gotItInfo').addClass('a_gotItInfoShow');
		        $('.a_amountRange').hide(200)
		    });
		    $('.a_opntooltio').click(function(){ 
		        $(this).siblings('.a_gotItInfo').addClass('a_gotItInfoShow');
		    });
		    $('.a_halfBox .a_gotItInfo a').click(function(){
		        $(this).parent('.a_gotItInfo').removeClass('a_gotItInfoShow');
		    });
		    
		
		    
		    
		    
		        function submitAddColor(idSet){ 
		            
		         var formId = idSet.parents('form').attr('id');
		            
		        //if($('#'+formId+' input[type="checkbox"]').prop("checked") == true){
		            //$('#'+formId+' .chechTC .errormsg').hide();
		            setTimeout(function() {
		                var tlerr=0;
		                $('#'+formId+' .errormsg').each(function(i) {
		                    // || $(this).siblings('input').val() == ""
		                    if ($(this).css("display") == "block" || $(this).siblings('input').val() == "") {
		                      tlerr++;
		                    }
		                });
		                
		                if(tlerr == 0){
		                    $('#'+formId+' .validBtn').addClass('addColor'); 
		                   }else{
		                       $('#'+formId+' .validBtn').removeClass('addColor');
		                   }
		                 
		            }, 400);
		            
		            /*}else{
		                $('#'+formId+' .chechTC .errormsg').show();
		                  $('#'+formId+' .validBtn').removeClass('addColor');
		            }*/
		    }
		    
		    $('.chechTC input[type="checkbox"]').change(function(){
		        
		        if($(this).is(':checked')){
		            $(this).parent().siblings('.errormsg').hide();
		        }else{
		            $(this).parent().siblings('.errormsg').hide();
		        }
		        
		        
		        
		        var thisIs = $(this);
		        submitAddColor(thisIs);
		    });
		    
		    $('.a_ReInput input').blur(function(){
		         var thisIs = $(this);
		        submitAddColor(thisIs);
		        $("#receiveOtp .validBtn").prop("disabled", true);
		    });
		    
		    $('.a_ReInput select').change(function(){
		         var thisIs = $(this);
		        submitAddColor(thisIs);
		    });
		    
		    
		    $('.dayselect,.monthselect,.yearselect').change(function(){
		        var formId = $(this).parents('form').attr('id');
		        var daySel = $('#'+formId+' .dayselect').val();
		        var monthSel = $('#'+formId+' .monthselect').val();
		        var yearSel = $('#'+formId+' .yearselect').val();
		        //a_ReInput birthSelect invalid
		        if(daySel && monthSel && yearSel){
		           $(this).parent().siblings('.errormsg').hide();
		            //setTimeout(function(){$(this).parents('.a_ReInput').removeClass('invalid')},500);
		            $(this).parents('.a_ReInput').removeClass('invalid');
		            
		        }else{
		            $(this).parent().siblings('.errormsg').show();
		            $(this).parents('.a_ReInput').addClass('invalid');
		        }
		    });
		    
		    
		    
		    $('.p_titleploan .p_maintitleedit a').click(function(){
		        $(this).parents('.paymentpart').find('.p_paymentinput').toggleClass('a_onlulabel');
		        //   $(this).parents('.paymentpart').find('.a_ReInput input')
		        
		        if ($(this).parents('.paymentpart').find('.a_ReInput input').attr('disabled')){
		            $(this).parents('.paymentpart').find('.a_ReInput input').removeAttr('disabled');
		        }else{
		            $(this).parents('.paymentpart').find('.a_ReInput input').attr('disabled', 'disabled');
		        }
		        
		        if($(this).text() == 'Edit'){
		            $(this).text('Cancel');
		        }else{
		            location.reload();
		            $(this).text('Edit');
		        }
		        
		    });
		    
		    
		
		    $('.sysheaderdesign a').click(function(){
		        $('.sysblackbg').hide();
		        $('.syswhiteboxpopup').removeClass('syswhiteboxpopupShow');
		    });
		     
		/*****************************************************************************************************************************************************/
		 
		 $('.a_radiodeposit label > a').click(function(){
		        $(this).siblings('.a_gotItInfo').addClass('a_gotItInfoShow');
		        $('.a_amountRange').hide(200)
		    });
		    $('.a_radiodeposit .a_gotItInfo a').click(function(){
		        $(this).parent('.a_gotItInfo').removeClass('a_gotItInfoShow');
		    });

		    
		
		    
		    $('.a_confirmtabsSec .a_topBoxbord .a_halfBox a').click(function(){
		        $('.a_confirmtabsSec .a_topBoxbord .a_halfBox a').removeClass('active');
		        $(this).addClass('active');
		        
		        var get_tab = $(this).attr('data-tab'); 
		        $('#'+get_tab).addClass('fildPartShow');
		        $('#'+get_tab).siblings().removeClass('fildPartShow');
		    });
		    
		    
		    $('.a_afterMarurity .a_swichTgl label input').change(function(){
		        if($(this).is(':checked')){
		            $(this).parents('.a_renewFDeposite').find('.a_radiodeposit').slideDown(200);
		            $(this).parents('.a_renewFDeposite').find('.a_radiodeposit_2').slideDown(200);
		            $(this).parents('.a_renewFDeposite').find('.a_radiodeposit_2 .a_ReInput input').removeClass('nomandetory');
		            $(this).parents('.a_renewFDeposite').find('.a_radiodeposit_2 .a_ReInput select').removeClass('nomandetory');
		            
		            
		            
		            $(this).parents('.a_ourParner').find('.a_ReInput').slideDown(200); 
		            $(this).parents('.a_ourParner').find('.a_ReInput input').removeClass('nomandetory');
		        }else{
		            $(this).parents('.a_renewFDeposite').find('.a_radiodeposit').slideUp(200);
		            $(this).parents('.a_renewFDeposite').find('.a_radiodeposit_2').slideUp(200);
		            $(this).parents('.a_renewFDeposite').find('.a_radiodeposit_2 .a_ReInput input').addClass('nomandetory');
		            $(this).parents('.a_renewFDeposite').find('.a_radiodeposit_2 .a_ReInput select').addClass('nomandetory');
		            $(this).parents('.a_renewFDeposite').find('.a_radiodeposit_2 .a_ReInput .errormsg').hide();
		            
		            
		            $(this).parents('.a_ourParner').find('.a_ReInput').slideUp(200);
		            $(this).parents('.a_ourParner').find('.a_ReInput input').addClass('nomandetory');
		            $(this).parents('.a_ourParner').find('.errormsg').hide();
		        }
		    });
		    
		   
		    $('.p_regnach .p_regbutton button').click(function(){
		        $('.a_blackoverlay').toggle();
		        $('.a_termsAndConPart_3').toggleClass('a_termsAndConPart_3Show');
		    });
		
		$('.a_termsAndConPart_3 .a_closePop').click(function(){
		        $('.a_blackoverlay').hide();
		        $('.a_termsAndConPart_3').removeClass('a_termsAndConPart_3Show');
		        $('#fdPayment').removeClass('floatCssButton');
		    });
		    
		    
		    
		     $('.datetimepicker5').daterangepicker({
		        locale: {
		            format: 'DD/MM/YYYY',
		        },
		        "singleDatePicker": true,
		        "showDropdowns": true,
		        "minDate": '01/01/1920',
		        "startDate": '01/01/1985',
		        "maxDate": moment(), 
		    //    "endDate": '01/01/1980', 
		        "autoApply": false,
		        //"ignoreReadonly": true,
		       // "allowInputToggle": true
		    }, function(start, end, label) {
		
		        
		    });
		    $('.datetimepicker5').val('');
		    
		  
		  
		 
		    $('.p_datagrowing i a').click(function(){
		    	 tokenCall();
		        $(this).parents('.a_part_3').hide();
		        $(this).parents('.a_part_3').siblings('.a_part_2').show();
		        dataLayerCall("imp_body_text_click","Change Number","True");
		    });
		    
		    
		    
		    if($(window).width() < 768){
		        $('#panform .a_blueBtnPart').insertAfter('#Banking_Details');   
		    }
		    
		    
		    
		    
		    $('.a_termsAndConPart .a_closePop').click(function(){
		        $('.a_blackoverlay').hide();
		        $('.a_termsAndConPart').removeClass('a_termsAndConPartShow');
		        isFDPayActionInProgress= 'No';
                isSDPPayActionInProgress= 'No';
                $('#fdPayment').removeClass('floatCssButton');
		    });
		    
		    
		    
		    $('.a_termsAndConPart .a_blueBtnPart button').click(function(){
		    	trackPageviewScript('Pay_via_Netbanking');
		    	 googleEventPaymentCheckout();
		    	 fbPixeltagPaymentProeed();
				setTimeout(function() {
		            var totalerror = 0;
		            $(".a_termsAndConPart .errormsg").each(function(i) {
		                if ($(this).css("display") == "block") {
		                    totalerror++;
		                    
		                }
		            });
		            if (totalerror == 0) {
		            	$('#fdPaymentButton').show();
		            	if (isPayActionInProgress == 'No') {
		            		isPayActionInProgress = 'Yes';
		                    fdPaymentRequest();
		                }
		            	
		  				
		            }
		        }, 0);
		    });
		    
		    
		   
		    $('.a_part_5 .p_fdheadtitle .p_fdtextname a').click(function(){
		        $('.a_part_5').hide();
		        $('.a_part_4').show();
		    });
		    $('.a_part_6 .p_fdheadtitle .p_fdtextname a').click(function(){
		        $('.a_part_6').hide();
		        $('.a_part_4').show();
		    });
		
		    
		    
		 
		    
		    
		    
		  /*****************************************Main Functionality Start****************************************/  
		    
		    
		    
		    var pan;
		    var address;
		    var pin ;
		
		
		    	 
		    	
		
		
		    /********************************************token call*****************************************************/
		    if(okycstatusIDValue == null || okycstatusIDValue =="" || fdslIDNew == "" ||fdslIDNew == null)
			{
		    	if (!(window.location.href.indexOf("experiaResponse") > -1)) 
		    	{
		    		 tokenCall();
		    	    }
			}
		    function tokenCall()
		    {
		    var d=new Date();
		
		    var urlVal = "/fixed-deposit-application-form/getajaxFdGenerateToken?a="+d.getTime();			
		    		$.ajax({     
		    			url: urlVal ,
		    			type: "GET", 
		    			contentType: 'text/plain', 
		    			error: function(resp)
		    			{
		    				newRelicAlert("Technical Error Page in getajaxFdGenerateToken | Fail Ajax call","'/fixed-deposit-application-form/getajaxFdGenerateToken'",resp.status," ");
		    				console.log("Fail fdtokens resp : " + resp);
		    			},	     										 
		    			success: function(resp)
		    			{   
		    				    $("#csrf").val("");
		    		            $("#csrf").val(resp);
		    				
		    			} 
		    		});
		    }
		    /********************************************token call end*****************************************************/
		
		    /********************************************GTM and Uniquecode generation Start*****************************************************/
		    function GetCookie(c_name) {
		        var c_value = document.cookie,
		            c_start = c_value.indexOf(" " + c_name + "=");
		        if (c_start == -1) c_start = c_value.indexOf(c_name + "=");
		        if (c_start == -1) {
		            c_value = null;
		        } else {
		            c_start = c_value.indexOf("=", c_start) + 1;
		            var c_end = c_value.indexOf(";", c_start);
		            if (c_end == -1) {
		                c_end = c_value.length;
		            }
		            c_value = unescape(c_value.substring(c_start, c_end));
		        }
		        return c_value;
		    } 
		
		
		   (function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
		    new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
		    j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
		    'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
		    })(window,document,'script','dataLayer','GTM-KQPMDMR');
		
		  
		   
		   /***************************************DataLayerCall Function***********************************************/
			function dataLayerCall(event,Click,eventCondition)
			{
				 dataLayer.push({
				        event: event,
				        pageType: document.title,
				        formSubmitType: "DigitalFixedDepositForm",
						clickText:Click,
						eventCondition:eventCondition
				    }); 
			}
			
			function dataLayerCallRate(event,Click)
			{
				 dataLayer.push({
				        event: event,
				        pageType: document.title,
				        textHeading: "DigitalFixedDepositForm",
						clickText:Click,
				    }); 
			}
			
			function dataLayerDynamic(event,sectionHeading,textHeading,click)
			{
				if(textHeading=="")
					{
					dataLayer.push({
				        event: event,
				        pageType: document.title,
						sectionHeading:sectionHeading,
						clickText:click,
				    });
					}else
						{
						dataLayer.push({
					        event: event,
					        pageType: document.title,
							sectionHeading:sectionHeading,
					        textHeading: textHeading,
							clickText:click,
					    });
						}
				  
			}
			
			function datalayerEventValue(event,Click,eventCondition,eventVal)
			{
				dataLayer.push({
			        event: event,
			        pageType: document.title,
			        formSubmitType: "DigitalFixedDepositForm",
					clickText:Click,
					eventCondition:eventCondition,
					eventValue:eventVal
			    }); 
			}
			
			
			function newRelicAlert(firstAttr,secondAttr,thirdAttr,fourthAttr)
			{
				var err = new Error('custom_error');
         		newrelic.noticeError(err, { attribute1: firstAttr, attribute2: secondAttr, attribute3: thirdAttr, FdcNumber: fourthAttr });
			}
			/********************************************DataLayerCall Function**************************************/
		
		    /*  ----- generating Unique Code ------  */
		    function uniqueId() {
		      return '_' + Math.random().toString(36).substr(2, 16);
		    };
		    /*---- getting Valu From Cookie ------*/
		    function getCookie(name) { 
		    	  var regexp = new RegExp("(?:^" + name + "|;\s*"+ name + ")=(.*?)(?:;|$)", "g");
		    	  var result = regexp.exec(document.cookie);
		    	  return (result === null) ? null : result[1];
		    	}	
		    	var cookieID;   // To store cookie id
		
		    		if (document.cookie.indexOf('userCookieID') == -1 ) {
		    		  
		    		  cookieID=uniqueId();
		    		  var date=new Date();
		    		  var days=30;
		    		  date.setTime(date.getTime()+(days*24*60*60*1000));
		    			document.cookie = "userCookieID="+cookieID+";expires="+date.toGMTString()+"; path=/"+ "; secure"
		    		}   
		    	else{  
		    		cookieID=GetCookie('userCookieID');
		    		} 
		
		    var clientId; // To store Client_ID from google analytics
		/*    ga(function(tracker) {
		    	  clientId = tracker.get('clientId');
		    });*/       
		
		    /********************************************Cookie Delete Code Start*****************************************************/
		    function delete_cookie(name) {
		    	  document.cookie = name +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
		    	}
		    
		    /********************************************Cookie Delete Code End*****************************************************/
		    
		
		    /********************************************GTM and Uniquecode generation End*****************************************************/
		
		
		    /********************************************Send otp functionality start*****************************************************/
		
		    function fdSendOTP() {

		        var device;
		        var partnerCode;
		        var partnerName;
		        getIpAddress();
		        var ipAdress = dataVal;
		        if (ipAdress != null || ipAdress != undefined) {
		            ipAdress = ipAdress.replace(/\s/g, '');
		        } else {
		            ipAdress = "NA"
		        }

		        var otpTriggeredTimeday = new Date().toLocaleDateString();
		        var otpTriggeredTime = new Date().toLocaleTimeString('en-GB');
		        otpTriggeredTime = otpTriggeredTimeday + " " + otpTriggeredTime;

		        vizLayer("STEP1", "NA", mobNo, "NA", "NA")

		        var mobNo = $("#mobileNO").val().trim();
		        //var dob =$("#dob").val().trim();
		        
		        //var dd = $("#dd").val().trim();
		        //var mm = $("#mm").val().trim();
		        //var yyyy = $("#yyyy").val().trim();
		        var dobVal = $('#hldatepicker').val().trim();

		        var day = dobVal.substring(0, 2);
		        var month = dobVal.substring(3, 5);
		        var year = dobVal.substring(6, 10);
		        var dob = day + "/" + month + "/" + year;
		        var pincode = $('#pinCodePV').val().trim();

		        if (newBRCode == "YES") {
		            partnerCode = $("#pCode").val().trim();
		            partnerName = $("#pName").val().trim();
		        }

		        if (partnerCode == "" || partnerCode == null || partnerName == "") {
		            partnerCode = "NA";
		            partnerName = "NA";
		        }
		        if (cookieID == null) {
		            cookieID = "NA";
		        }
		        if (clientId == null) {
		            clientId = "NA";
		        }

		        var calcType = $("#calc").val();
		        
		        
		        if($("#whatsAppCheck").is(":checked")){
		        	whatsAppCheck = "Yes";
		        }else{
		        	whatsAppCheck = "No";
		        }
		        
		        var campaignData = {
		            "mobileNumber": mobNo,
		            "dateOfBirth": dob,
		            "partnerCode": partnerCode,
		            "partnerName": partnerName,
		            "timeOfLogging": timeOfLogging,
		            "otpTriggeredTime": otpTriggeredTime,
		            "investmentType": calcType,
		            "ipAdress": ipAdress,
		            "fdslf_pincode": pincode,
		            "whatsAppCheck" : whatsAppCheck
		        }

		        campaignData.cookieID = cookieID;
		        campaignData.clientID = clientId;

		        var campCookie = GetCookie('campaignCookie');
		        var lastClickCookie = GetCookie('LastClickCookie');

		        lastClickCookie = JSON.parse(lastClickCookie);
		        campCookie = JSON.parse(campCookie);

		        var gaID = GetCookie('_ga');

		        if (gaID == null) {
		            gaID = "NA";
		        }

		        campaignData.gaID = gaID;

		        var utm_source = getUrlValue("utm_source");
		        var utm_medium = getUrlValue("utm_medium");
		        var utm_keyword = getUrlValue("utm_keyword");
		        var utm_campaign = getUrlValue("utm_campaign");
		        var utm_content = getUrlValue("utm_content");


		        if (utm_source != null || utm_medium != null || utm_keyword != null || utm_campaign != null || utm_content != null) {

		            if (utm_campaign == null || utm_campaign === 'null') {
		                campaignData.utm_campaign_utmTrue = 'NA';
		            } else {
		                campaignData.utm_campaign_utmTrue = utm_campaign;
		            }

		            if (utm_medium == null || utm_medium === 'null') {

		                campaignData.utm_medium_utmTrue = 'bfl';
		            } else {
		                campaignData.utm_medium_utmTrue = utm_medium;
		            }

		            if (utm_keyword == null || utm_keyword === 'null') {

		                campaignData.utm_keyword_utmTrue = 'NA';
		            } else {
		                campaignData.utm_keyword_utmTrue = utm_keyword;
		            }

		            if (utm_source == null || utm_source === 'null') {
		                campaignData.utm_source_utmTrue = 'organic_bfl';
		            } else {
		                campaignData.utm_source_utmTrue = escape(utm_source);
		            }

		            if (utm_content == null || utm_content === 'null') {

		                campaignData.utm_content_utmTrue = 'NA';
		            } else {
		                campaignData.utm_content_utmTrue = utm_content;
		            }

		            //lastClickCookie = null;


		        } else if (campCookie != null) {
		            campaignData.utm_campaign_utmTrue = campCookie['utm_campaign'];
		            campaignData.utm_medium_utmTrue = campCookie['utm_medium'];
		            campaignData.utm_keyword_utmTrue = campCookie['utm_keyword'];
		            campaignData.utm_source_utmTrue = escape(campCookie['utm_source']);
		            campaignData.utm_content_utmTrue = campCookie['utm_content'];

		        } else {
		            campaignData.utm_content_utmTrue = 'NA';
		            campaignData.utm_campaign_utmTrue = 'NA';
		            campaignData.utm_medium_utmTrue = 'bfl';
		            campaignData.utm_keyword_utmTrue = 'NA';
		            campaignData.utm_source_utmTrue = 'organic_bfl';
		        }
		        if (campaignData.utm_source_utmTrue == "" || campaignData.utm_medium_utmTrue == "") {
		            campaignData.utm_source_utmTrue = 'organic_bfl';
		            campaignData.utm_medium_utmTrue = 'bfl';
		        }
		        var cidValue = getUrlValue("DID");
		        if (cidValue != undefined) {
		            campaignData.cid_value_cidvaluetrue = cidValue;
		        }

		        var windowwidth = $(window).width();
		        if (windowwidth < 600) {
		            device = "Mobile";
		        } else if (windowwidth > 640 && windowwidth < 992) {
		            device = "Tablet";
		        } else {
		            device = "Desktop";
		        }
		        var last_clicked_clicktrue;
		        if (lastClickCookie == null) {
		            lastClickCookie = 'NA';
		            last_clicked_clicktrue = lastClickCookie;
		        } else {
		            last_clicked_clicktrue = lastClickCookie['click'];
		        }
		        
		        campaignData.last_clicked_clicktrue = escape(last_clicked_clicktrue);
		        
		        var pageURL = window.location.href;



		        var url = new URL(pageURL);
		        var rdplan = url.searchParams.get("LAN");
		        var gclid = url.searchParams.get("gclid");

		        if (rdplan != null) {
		            campaignData.rdplan = rdplan;
		        }

		        if (gclid != null) {

		            campaignData.gclid = gclid;

		        }

		        campaignData.device = device;
		        campaignData.pageURL = pageURL;

		        campaignData.formName = "DigitalFixedDepositForm";
		        campaignData.formID = "1501038808633";

		        var currentDate = new Date();
		        var currentDateTime = currentDate.getDate() + '/' + (currentDate.getMonth() + 1) + '/' + currentDate.getFullYear() + ' ' + currentDate.getHours() + ':' + currentDate.getMinutes() + ':' + currentDate.getSeconds();

		        var dt = new Date();
		        var time = dt.getDate() + "" + (dt.getMonth() + 1) + "" + dt.getFullYear() + "" + dt.getHours() + "" + dt.getMinutes() + "" + dt.getSeconds() + "" + dt.getMilliseconds();

		        campaignData.unqiuecodeId = time;
		        campaignData.wcmTime = currentDateTime;

		        var token = $("#csrf").val();
		        console.log(token);
		        $.ajax({

		            url: "/fixed-deposit-application-form/generateOtp",
		            type: "POST",
		            data: JSON.stringify(campaignData),
		            beforeSend: function(xhr) {
		                xhr.setRequestHeader('X-CSRF-Token', token);
		            },
		            contentType: 'application/json',
		            error: function(response) {
		                $('#SendOtp .p_reqotp button .fd_sdp_loder').hide();
		                $('#apply_for_new_fd_form .validBtn i').hide();
		                newRelicAlert("Technical Error Page in Generate OTP | Fail Ajax call","'/fixed-deposit-application-form/generateOtp'",response.status," ");
		                $('.maxcontainer div:visible').hide();		                
		                $('.ErrorPage').show();
		            },
		            success: function(response) {

		                var encResponse = JSON.parse(response);
		                var decResponse = getOfferobje(encResponse);
		                var response = JSON.parse(decResponse);
		                if (response.apiStatus == "success") {

		                    $('#sent_Mo').html(" " + mobNo);
		                    $('#apply_for_new_fd_form .validBtn i').hide();
		                    $("#otpForm .errormsg").hide();
		                    $('.forMobileform').hide();
		                    $('.forOtpForm').show();
		                    $('.loginFirstStep').hide();
		                    $('.otpMainPart').show();
		                    $('.otpPart input').first().focus();
		                    $('#otp1').val('');
	 		          		$('#receiveOtp').prop("disabled", false); 
		                    clearInterval(interval)
		                    count3minut();
		                    dataLayerCall("form_submission_get_otp", "GET OTP", "True");
		                    fdcNumber=response.customerId;
		                    
		                    jwtToken = response.jwtToken;
		                } else if (response.apiStatus == "fail")
		                {
		                    $('#SendOtp .p_reqotp button .fd_sdp_loder').hide();
		                    $('#apply_for_new_fd_form .validBtn i').hide();
		                    $('#generateOtpButton').prop("disabled", false); 
		                    isActionInProgress = 'No';
		                    if (response.hasOwnProperty('tokenStatus')) 
		                    {
		                        alert("Please Refresh the page and try again");
		                        location.reload();
		                    } else if (response.errorCode == "91") {
		                        alert(response.errorMsg);
		                        location.reload();
		                    } else if (response.errorCode == "92") {
		                        console.log("errorCode 92 send otp");
		                        $("#mobileNO .errormsg").text(response.errorMsg).show();
		                    } else if (response.apiStatus == "sessionExpired") {
		                    	 $('.maxcontainer div:visible').hide();
			                     $('.SessionErrorPage').show();
		                    } else {
		                    	newRelicAlert("Technical Error Page in Generate OTP | Api Status Fail","'/fixed-deposit-application-form/generateOtp'",""," ");
		                    	$('.maxcontainer div:visible').hide();		                
				                $('.ErrorPage').show();
		                    }
		                    
		                    dataLayerCall("form_submission_get_otp", "GET OTP", "False");
		             		newRelicAlert("Fail in Generate OTP","'/fixed-deposit-application-form/generateOtp'",""," ");
		                } else if (response.apiStatus == "sessionExpired")
		                {
		                	newRelicAlert("session Expired in Generate OTP","'/fixed-deposit-application-form/generateOtp'",decResponse, " ");
		             		$('.maxcontainer div:visible').hide();
		             		$('.SessionErrorPage').show();
	                    } else {
	                    	newRelicAlert("Technical Error Page in Generate OTP","'/fixed-deposit-application-form/generateOtp'",""," ");
	                    	$('.maxcontainer div:visible').hide();		                
			                $('.ErrorPage').show();

		                }


		            }

		        });



		    }	
		    	
		    /********************************************send otp functionality end*****************************************************/	
		   
		
		    /**
			 * ****************************Whats app consent functionality
			 * start****************************************
			 */
		    
		   
		    getBrowser = () => {
		        
		        brwser = (/ucbrowser/i).test(userAgent) ? 'UCBrowser' : brwser;
		        brwser = (/edg/i).test(userAgent) ? 'Edge' : brwser;
		        brwser = (/googlebot/i).test(userAgent) ? 'GoogleBot' : brwser;
		        brwser = (/chromium/i).test(userAgent) ? 'Chromium' : brwser;
		        brwser = (/firefox|fxios/i).test(userAgent) && !(/seamonkey/i).test(userAgent) ? 'Firefox' : brwser;
		        brwser = (/; msie|trident/i).test(userAgent) && !(/ucbrowser/i).test(userAgent) ? 'IE' : brwser;
		        brwser = (/chrome|crios/i).test(userAgent) && !(/opr|opera|chromium|edg|ucbrowser|googlebot/i).test(userAgent) ? 'Chrome' : brwser;
		        brwser = (/safari/i).test(userAgent) && !(/chromium|edg|ucbrowser|chrome|crios|opr|opera|fxios|firefox/i).test(userAgent) ? 'Safari' : brwser;
		        brwser = (/opr|opera/i).test(userAgent) ? 'Opera' : brwser;
		        
		        return brwser;
		    }
		    
		    
		    getBrowserVersion = () =>{
		    	
		    	brwser = getBrowser();
		    	
		    	switch (brwser) {
		        
				case 'UCBrowser': browserVersion(userAgent,/(ucbrowser)\/([\d\.]+)/i);
				break;
		        case 'Edge': browserVersion(userAgent,/(edge|edga|edgios|edg)\/([\d\.]+)/i);
				break;
		        case 'GoogleBot': browserVersion(userAgent,/(googlebot)\/([\d\.]+)/i);
				break;
		        case 'Chromium': browserVersion(userAgent,/(chromium)\/([\d\.]+)/i);
				break;
		        case 'Firefox':  browserVersion(userAgent,/(firefox|fxios)\/([\d\.]+)/i);
				break;
		        case 'Chrome':  browserVersion(userAgent,/(chrome|crios)\/([\d\.]+)/i);
				break;
		        case 'Safari': browserVersion(userAgent,/(safari)\/([\d\.]+)/i);
				break;
		        case 'Opera': browserVersion(userAgent,/(opera|opr)\/([\d\.]+)/i);
				break;
		        case 'IE': const version = browserVersion(userAgent,/(trident)\/([\d\.]+)/i);
		            return version ? parseFloat(version) + 4.0 : 7.0;
				break;
		        default: return '0.0.0.0';
		    }
			
			return version;
		    }
		    
		    browserVersion = (userAgent,regex) => {
		    	console.log("browserVersion()");
		    	version=userAgent.match(regex) ? userAgent.match(regex)[2] : null;
		    }
		    
		    
		    // WhatsAPP Offer APi and validation start from here
            var TokenCSRF;
       	 	var API_Domain = "https://webservices.bajajfinserv.in/api/v2/whatsapp/consent";                   
            /* generating token */
          	 function totkenGeneartionApi() {
          	 	var tokendata = {
          	 		"url": "https://webservices.bajajfinserv.in/api/v1/csrf",
          	 		"method": "GET",
          	 		"async": false,
          	 		"headers": {
          	 			"Content-Type": "application/json",
          	 		},
          	 		"crossDomain": "true",
          	 		"xhrFields": {
          	 			"withCredentials": "true"
          	 		},
          	 	};
          	 	$.ajax(tokendata).done(function(response) {
          	 		console.log("csrf from api");
         	 		                        
          	 		if(response.data._csrf){
          	 		TokenCSRF = response.data._csrf;
          	 		}
          	 	});
          	 }
          	
          	 /* token generated */
           
            function getWhatsAppOffers()
                  	   {           
                    	  totkenGeneartionApi(); 
                    	  
                    	  var JWT_Token = "Bearer "+jwtToken;
                    	  var lastClickParameter = JSON.parse(GetCookie('LastClickCookie'));    
							  lastClickParameter = (lastClickParameter != null && lastClickParameter != "undefined") ? lastClickParameter['click']: "NA";
                          var whatsappconsent="1";
                        	var whatsappcheck;  
                        	 if(whatsappconsent==1)
                        		 {
                         		whatsappcheck="WYES"; 
                        		consentStatus= 1;
                        		 }else{
                         			whatsappcheck="WNO";
                        			consentStatus= 0;
                        	 }
                        	 
                        	 var gaID = GetCookie('_ga');
	          			     if (gaID == null) {
	          			            gaID = "NA";
	          			     }
                        	 
	          			     var pageURL = window.location.href;
                        	 
	          			   var windowwidth = $(window).width();
	          			   var device="";
	          			   if (windowwidth < 600) {
	       		            device = "Mobile";
	          			   } else if (windowwidth > 640 && windowwidth < 992) {
	       		            device = "Tablet";
	          			   } else {
	       		            device = "Desktop";
	          			   }
	          			   
	          			 var utm_source = getUrlValue("utm_source");
	          			 var utm_medium = getUrlValue("utm_medium");
	          			 var utm_campaign = getUrlValue("utm_campaign");
	          			 
	          			if (utm_source != null || utm_medium != null ||  utm_campaign != null ) {

	    		            if (utm_campaign == null || utm_campaign === 'null') {
	    		                utm_campaign = 'NA';
	    		            } else {
	    		                utm_campaign = utm_campaign;
	    		            }

	    		            if (utm_medium == null || utm_medium === 'null') {

	    		               utm_medium = 'NA';
	    		            } else {
	    		                utm_medium = utm_medium;
	    		            }
	    					
	    					if (utm_source == null || utm_source === 'null') {
	    		                utm_source = 'NA';
	    		            } else {
	    		                utm_source = utm_source;
	    		            }
	    				}
	          			
	          			var deviceBrowser = getBrowser();
	          			var browserVersion = getBrowserVersion(); 
	          			     
                        	 var data={
	          	            			"mobileNo" : mobileNo,
	          	            			"whatsAppText": "WYES",
	          	            		    "source": "Mkt FD",
	          	            		    "consentType": "Promotional",
	          	            		    "pageURL" : pageURL,
	          	            		    "gaId" : gaID,
	          	            		    "deviceType": device,
	          	            		    "deviceModel": "",
	          	            		    "deviceBrowser":deviceBrowser,
	          	            			"browserVersion": browserVersion,
	          	            			"formId":"1501038808633",
	          	            			"city": city,
	          	            			"utmSource": utm_source,
	          	            			"utmMedium": utm_medium,
	          	            			"utmCampaign": utm_campaign,
	          	            			"consentStatus": consentStatus,
	          	            			"keyword": "",
	          	            			"lastClickParameter": lastClickParameter,
	          	            			"_csrf": TokenCSRF
	          	            	};
                        	 
                        	 
							 var sVal = generateSVal(20);          
                    	  		var encptReqVal = encptdata(sVal,JSON.stringify(data));                    
                    	  		var requestData = {
                    	  			 "reqData": encptReqVal,  
                    	  	     	 "_csrf": TokenCSRF
                    	  		};
                        	 
                	    		  		       ajaxurl = API_Domain;   
                    	    		  		       $.ajax({
                	    		  		              url: ajaxurl,
                	    		  		              type: "POST",
     		             	    		  		      headers: {'Authorization': JWT_Token,
    		 		             	    		  		    	"Content-Type": "application/json",         
    		 		             	                  			"reqKey": sVal },
     		     	    		  		              cache: false,
                	    		  		              async: true,
                	    		  		              data: JSON.stringify(requestData),
    	            	    		  		          crossDomain: true,
    	            	    		                  timeout: 35000,       
    	            	    		                  xhrFields: {
    	            	    		                		"withCredentials": "true"
    	            	    		                	}, 
                	    		  		              error: function(response) {
                	    		  		            	newRelicAlert("Technical Error Page in WhatsApp Consent | Fail Ajax call",API_Domain,response.status," ");
                	    		  		              },
                	    		  		              success: function(response) {
                    	    		                  response = JSON.parse(decptdata(sVal,response));
                   	    		  		              response = JSON.stringify(response);
                   	    		  		              if(response.status == "success"){
                   	    		  		            	  dataLayerCall("WhatssApp consent checkbox tick", "Mkt FD", "1");
                   	    		  		              }else{
              	    		  		            	  dataLayerCall("WhatssApp consent checkbox tick", "Mkt FD", "0");
                   	    		  		              }  
                	    		  		           }
                	    		  		          });          
                  	   }
            

	function generateSVal(length){
     		var chars = "0123456789ABCDEFabcdef";
	      	var sVal = '';
	      	for (var i = 0; i < length; i++) {
	      	    var appendchar = chars.charAt(Math.floor(Math.random() * (chars.length)));
	      		sVal = sVal + appendchar;
	      	}
	      	return sVal;
	      }
     	           
     	function encptdata(sVal,reqData) {
     		var iterationCount = 1000;   
	      	var keySize = 256;   
	      	var passphrase = "9486900ed0";    
	      	iv = "930b9760ce9fec3f4b18fec88258e313";
	      	var aesUtil = new AesUtil(keySize, iterationCount);
	      	return aesUtil.encrypt(sVal, iv, passphrase, reqData);
	      }      
      
     	function decptdata(sVal,encptedResponsedata){ 
				var itrCnt = 1000;  
				var kySze = 256;
				var ivctr ="930b9760ce9fec3f4b18fec88258e313";
				var pphrse="9486900ed0";
				var aesUtil = new AesUtil(kySze, itrCnt);           
				var cphertxt1 = aesUtil.decrypt(sVal,ivctr,pphrse,encptedResponsedata);
				return cphertxt1;
			} 

		    
		    
		    /**
			 * ****************************Whats app consent functionality
			 * end***************************************
			 */
		    

		    
		    
		    /********************************************resend otp functionality start*****************************************************/	
		    	
		    function fdResendOTP() {

		        var mobNo = $("#mobileNO").val().trim();
		        var custId=fdcNumber;
			    
			    if(custId==null || custId == undefined)
	    		{
			    	custId="NA";
			    	
	    		}
		    	var data =
		    	{ 
		    	  "mobileNumber":mobNo,
		    	  "fdcNumber":custId
		    	}; 
		    	


		        $.ajax({

		            url: "/fixed-deposit-application-form/regenerateOtp",
		            type: "POST",
		            data: JSON.stringify(data),
		            contentType: 'application/json',
		            error: function(response) {
		            	newRelicAlert("Technical Error Page in Resend OTP | Fail Ajax call","'/fixed-deposit-application-form/regenerateOtp'",response.status,fdcNumber);
		            	$('.maxcontainer div:visible').hide();
		                $('.ErrorPage').show();
		            },
		            success: function(response) {

		                var encResponse = JSON.parse(response);
		                var decResponse = getOfferobje(encResponse);

		                var response = JSON.parse(decResponse);

		                var resendStatus = response.resendStatus;
		                $("#otpForm .validBtn").prop("disabled", false);
		                if (response.apiStatus == "success") {
		                    $('#sent_Mo').html(" " + mobNo);
		                    dataLayerCall("imp_body_text_click", "Resend OTP", "True")
		                } else if (response.apiStatus == "fail") {

		                    if (response.errorCode == "91") {

		                        alert(response.errorMsg);
		                        location.reload();
		                    } else if (response.errorCode == "92") {
		                        console.log("errorCode 92 send otp");

		                        $("#mobileNO .errormsg").text(response.errorMsg).show();
		                    } else {

		                        dataLayerCall("imp_body_text_click", "Resend OTP", "False");
		                        newRelicAlert("Technical Error Page in Resend OTP | Api Status Fail","'/fixed-deposit-application-form/regenerateOtp'"," ",fdcNumber);
		                        $('.maxcontainer div:visible').hide();
				                $('.ErrorPage').show();
		                    }

		             		newRelicAlert("Fail in Resend OTP","'/fixed-deposit-application-form/regenerateOtp'"," ",fdcNumber);
		             		
		                } else if (response.hasOwnProperty('otpResendTime')) {
		                    alert(response.otpResendTime);
		                    location.reload();
		                } else if (response.apiStatus == "sessionExpired") {
		                	newRelicAlert("session Expired in Resend OTP","'/fixed-deposit-application-form/regenerateOtp'",decResponse ,fdcNumber);
		                    $('.maxcontainer div:visible').hide();
		                    $('.SessionErrorPage').show();
		                } else {
		                	newRelicAlert("Technical Error Page in Resend OTP","'/fixed-deposit-application-form/regenerateOtp'"," ",fdcNumber);
		                	$('.maxcontainer div:visible').hide();
			                $('.ErrorPage').show();
		                }
		            }


		        });

		    }	
		    /********************************************resend otp functionality end*****************************************************/
		
		
		    /********************************************validate otp functionality start*****************************************************/
		

		    function fdValidateOTP()
		    {
		    	var mobNo = $("#mobileNO").val().trim();
		    	
		    	 var ipAdress=dataVal;
		    	 if(ipAdress!=null || ipAdress!=undefined)
		    		{ipAdress = ipAdress.replace(/\s/g, '');}else{ipAdress="NA"}
		    	var otpNo =$("#otp1").val().trim();
		    	var otpSubmittedTime = new Date().toLocaleTimeString('en-GB');
				var otpSubmittedTimeDay=new Date().toLocaleDateString();
		       if(otpNo!="")
		    	   {
				otpSubmittedTime=otpSubmittedTimeDay+" "+otpSubmittedTime;
		    	vizLayer("STEP2","NA",mobNo,"NA","NA")
		    	
		    	
		    	 var custId=fdcNumber;
		    
		    if(custId==null || custId == undefined)
    		{
		    	custId="NA";
		    	
    		}
		    	var data =
		    	{ 
		    	"mobileNumber":mobNo,
		    	"userOtp":otpNo,
		    	"otpSubmittedTime":otpSubmittedTime,
		    	"ipAdress":ipAdress,
	            "fdcNumber":custId
		    	}; 
		    	
		    	
		    	           $.ajax({  
		    		
		    	 		       url: "/fixed-deposit-application-form/validateOtp",
		    	 		       type: "POST",
		    	 		       data:JSON.stringify(data),  
		    	 		       contentType: 'application/json',
		    	 		      error: function(response)
		    					{
		    	 		    	 $('#otpForm .validBtn i').hide();
		    	 		    	 newRelicAlert("Technical Error Page in validate OTP | Fail Ajax call","'/fixed-deposit-application-form/validateOtp'",response.status,fdcNumber);
		    	 		    	$('.maxcontainer div:visible').hide();
				                $('.ErrorPage').show();
		    		            }  ,
		    	           	   success: function(response)
		    					{
		    	         	           		
		    	           	 var encResponse = JSON.parse(response);
		    	           	var decResponse = getOfferobje(encResponse);      	        	    				             	
		          	         var response = JSON.parse(decResponse); 
		          	        setCookie("Customer_Type", response.dedupeCustType);
		             	          
		    	 		         if(otpNo == response.userOtp)
		    	 		        	 {
		    	 		        	$("#otpForm .validBtn").prop("disabled", false);
		    	 		        	
		          	            if(response.otpValidateStatus=="success")
		    						{
		          	            	
		          	            	city = response.city;
		          	            	mobileNo = response.mobileNumber;
		          	            	
		          	            		if(response.dedupeCustType=="ETB")
		    							{
		    							   
		    							$('#ntbCustleble').hide();
		    	             			$('#etbCustleble').show();
		    	             			$('#genderDiv').remove();
		    							   var userDetails = JSON.parse(response.userDetails);
		    							   var fullName =userDetails.fullName;
		    							   var mobNumber =userDetails.mobileNumber;
		    							   var email =userDetails.email;
		    							   var dob =userDetails.dateOfBirth;
		    							    pan =userDetails.pan;
		    							    address =userDetails.address;
		    							    pin =userDetails.pin;
		    							   var accountNo =userDetails.accountNo;
		    							   var bankName =userDetails.bankName;
		    							   custTypeFD = userDetails.custType;
		    							   var product =userDetails.Product;
		    							   var flag = true;
		    							   city = userDetails.city;
		          	            	       mobileNo = mobNumber;
		          	            	       etbFlag = 'etbDedupeUser';
		          	            	       panNo = userDetails.pan;
		    								if(custTypeFD=="STB")
		    								{
		    									
		    									$('#stbExclusive').show();
		    								}
		    								
		    								 if(!(dob == "NA" || dob == "")) 
			             		               { 
			    								   $('#dobPD').val(dob);
			    								   $('#dobPD').parent().addClass('valid');
			    								   
			    								}else{
			        	                        	  flag =false;
			      	                           }
		    								 if(!(mobNumber== "NA" || mobNumber == "")) 
			             		               {
			    								   $('#mobilePD').val(mobNumber); 
			    								   $('#mobilePD').parent().addClass('valid');
			    								}else{
			        	                        	  flag =false;
			      	                           }

			    								var custArray=userDetails.custIdArray;
			    								
			    								
			    								var uniqueId = [];
			    								$.each(custArray, function(i, el){
			    								    if($.inArray(el, uniqueId) === -1) uniqueId.push(el);
			    								});
			    								
				    							   $('#costomerId').empty();
				    							   
				    							   $.each(uniqueId, function(i, p) {
				    							       $('#costomerId').append($('<option></option>').val(p).html(p));
				    							   });
				    							   
				    							   if(product=="FD" || product=="FD_BLANK" )
				    								{
				    							   var custCkycIDVD = userDetails.existingCustId;
				    							   if(!(custCkycIDVD== "NA" || custCkycIDVD == "")) 
				             		               {
				    								   $('#custCkycIDVD').val(custCkycIDVD);
				    								   $('#custCkycIDVD').parent().addClass('valid');
				    								   selectedCust=false;
				    								   $("#costomerId").val(custCkycIDVD).attr("selected","selected"); 
				    						        	if(product=="FD")
				    						        	{
				    						        		$('#prefillSelectoops').show();
				    						        		productFDCustId=custCkycIDVD;
				    						        	}else
				    						        	{
				    						        		$('#prefillSelectoops').hide();
				    						        	
				    						        	}
					    								
				     	                           }else{
				     	                        	  flag =false;
				     	                           }
				    							   
				    							   if(!(fullName== "NA" || fullName == "")) 
				             		               {
				    		  	                      	$('#fullNamePD').val(fullName);
				      		  	                        $('#fullNamePD').parent().addClass('valid');
				
				    		  	                        var name = fullName.split(" ");	
				    		  	                        var fname =name[0];
				    		  	                        var fchar = fname.charAt(0);
				    		  	                    $('.a_alphaFont').append(fchar);
				    		  	                	 $('.a_alphaFont').siblings("h2").append(" "+fname);
				    	  	                         	    
				     	                           }else{
				      	                        	  flag =false;
				     	                           }
				    							  
				    							   if(!(email== "NA" || email == "" || email == null)) 
				             		               { 
				    								   $('#emailPD').val(email);
				    								   $('#emailPD').parent().addClass('valid');
				    								}else{
				        	                        	  flag =false;
				      	                           }

				    							   if(!(pan== "NA" || pan == "" || pan == null)) 
				             		               {
				    								   $('#panPD').val(pan);
				    								   $('#panPD').parent().addClass('valid');
				    								 
				     	                           }else{
				      	                        	  flag =false;
				      	                        	$("#panPD").removeAttr("readonly");
				     	                           }
				    							   
				    							   if(!(address== "NA" || address == "")) 
				             		               {
				    								   
				    								   address = address.replace('-', ' ');   
				    								   address = address.replace('.', ' ');
				    		  	                       $('#addressPD').val(address);
				    		  	                       $('#addressPD').parent().addClass('valid');
				    		  	                      	
				     	                           }else{
				      	                        	  flag =false;
				      	                        	$("#addressPD").removeAttr("readonly");
				      	                        	$("#adderssLock").remove();
				     	                           }
				    							   
				    							   if(!(pin== "NA" || pin == "" || pin ==null)) 
				             		               {
				    		  	                       $('#pinCodePD').val(pin);
				    		  	                       $('#pinCodePD').parent().addClass('valid');
				    		  	                       $("#pinCodePD").prop("readonly", true); 
				    								   $("#cKycpinLock").show();
				     	                           }else{
				      	                        	  flag =false;
				      	                        	$("#pinCodePD").removeAttr("readonly");
				     	                           }
				    							   
				    							   
				    							   if(flag){
				    								   $('.a_topBoxbord .a_halfBox a[data-tab="Personal_Details"] ~ .tickNdisimg').attr('src','/fixed-deposit-application-form/resources/images/tick-round.png');
				    								   
				    							   }
				    							   
				    							   
				    							}else
				    								{
				    								$("#fullNamePD").removeAttr("readonly");
				    								$("#emailPD").removeAttr("readonly");
				    								$("#panPD").removeAttr("readonly");
				    								$("#addressPD").removeAttr("readonly");
				    								$("#pinCodePD").removeAttr("readonly");
				    								
				    								}
				    								
				    								$('#accTypeBD').val("Savings").prop('readonly', true);
				    								$('#accTypeBD').parent().addClass('valid');
				    				                $('.a_part_2').hide();
				    				                $('.a_part_4').show();
				    				                $('#otpForm .validBtn i').hide();
				    				                $("#otpForm .errormsg").hide();
				    							   
				    						
				    							}
		    						else if(response.dedupeCustType=="NTB"){
		    							
		    						 	
		    			                $('.a_part_2').hide();   
		    			                $('.a_part_3').hide();
		    			                $("#otpForm .errormsg").hide();
		    			                $('#otpForm .validBtn i').hide();
		    			                
		    			                $('.a_part_3-1').show();
		    			                etbFlag = 'ntbUser';
		    							
		    						}
		    						dataLayerCall("form_submission_submit_otp","SUBMIT OTP","True")
		    						
		    						if(whatsAppCheck == "Yes"){
		    							getWhatsAppOffers();
		    						}
		    						
		    						}	else if(response.otpValidateStatus=="fail"){
		    	 		          		$('#otpForm .validBtn i').hide();	
						                $('#otpForm .validBtn').prop('disabled', false);
		    	 		          	if(response.hasOwnProperty('otpLimit'))
		    	 	 	            	{
		    	 		          		 alert(response.otpLimit);
		    	 		  				 location.reload();    
		    	 		 	          	}
		    	 		          	else if(response.responseCode == "92"){
		             					
		             					alert(response.responseMessage);
		             				
		             				}
		                           else if(response.responseCode == "93"){
		                        	   $('#otpForm .validBtn i').hide();
		                        	   $("#otpForm .errormsg").text("Please enter valid OTP").show();
		                        	   $('#otpForm .validBtn').attr('disabled', false);
		                        	   
		             				}
		                           else {
		                        	   newRelicAlert("Technical Error Page in validate OTP | Otp Validate Status Fail ","'/fixed-deposit-application-form/validateOtp'"," ",fdcNumber);
		                        	   $('.maxcontainer div:visible').hide();
				                       $('.ErrorPage').show();
			                        }
		    	 		          	dataLayerCall("form_submission_submit_otp","SUBMIT OTP","False")
		    	 		          	newRelicAlert("Fail in validate OTP","'/fixed-deposit-application-form/validateOtp'"," ",fdcNumber);
		    	 		          	
		    	 		          	}
		    						else if(response.apiStatus== "sessionExpired")
		    	             		{
		    							newRelicAlert("session expired in validate OTP","'/fixed-deposit-application-form/validateOtp'",decResponse,fdcNumber);
			    	             		$('.maxcontainer div:visible').hide();
			    	             		$('.SessionErrorPage').show();
			    	             		}
			    	 		          	else
			    	 		          		{
			    	 		          		newRelicAlert("Technical Error Page in validate OTP","'/fixed-deposit-application-form/validateOtp'"," ",fdcNumber);
			    	 		          		$('.maxcontainer div:visible').hide();
						                    $('.ErrorPage').show();
			    	 		          		}
		    	 		       }
		    	 		         else
		    	 		        	 {
		    	 		        	$('#otpForm .validBtn i').hide();	
						              	$("#otpForm .validBtn").prop("disabled", false);
		    	 		        	    $("#otpForm .errormsg").text("Please enter valid OTP").show();
		    	 		        	 }
		    	 		         
		    	          	 }
		    	          
		    	         
		    	       	});  
		    }else{$("#otpForm .errormsg").show();$("#otpForm .validBtn").prop("disabled", false);}
		    }
		    	
		    /********************************************validate otp functionality end*****************************************************/
		
		    /********************************************NTB Pan functionality start*****************************************************/
		
		    
		    function ntbPanDetails(){
		    	
		    	var mobNo=$("#mobileNO").val().trim();
		    	vizLayer("STEP3","NA",mobNo,"NTB","NA")
		    	
			var panCard=  $('#panCardPV').val().trim();
			var fullname= $('#fullNamePDNew').val().trim();
			var custId=fdcNumber;
			var dt = new Date();
		    var requestId = dt.getDate() + "" + (dt.getMonth() + 1) + "" + dt.getFullYear() + "" + dt.getHours() + "" + dt.getMinutes() + "" + dt.getSeconds() + "" + dt.getMilliseconds();
		    if(custId==null || custId == undefined)
    		{
		    	custId="NA";
		    	
    		}
				   
				   var data = {          
				    		
				    		 
				    		"panCard":panCard,
				    		"fullname":fullname,
				    		"fdcNumber":custId,
					        "requestId":requestId,
				    };
				
				  $.ajax({ 
			                                    
			             url: "/fixed-deposit-application-form/addNtbUserData", 
			             type: "POST",
			             data:JSON.stringify(data),
			             contentType: 'application/json',
		                error: function(response)
					     {
		                 $('#panCarVerify .p_reqotp button .fd_sdp_loder').hide();
		                 newRelicAlert("Technical Error Page in CKYC verification | Fail Ajax call","'/fixed-deposit-application-form/addNtbUserData'",response.status,fdcNumber);
		                 $('.maxcontainer div:visible').hide();
	                     $('.ErrorPage').show(); 
		                  }  ,
		             success: function(response) {
		            	 var encResponse = JSON.parse(response);
		      	         var decResponse = getOfferobje(encResponse);
		      	        	    				             	
		      	         var response = JSON.parse(decResponse);
		      	         console.log("addNtbUserData response ",response);
		      	    
		      	         if(response.panCard==panCard && response.requestId==requestId)
		      	         {
		      	        	 if(response.validationStatus == "success")
		      	        	 { 
		             		dataLayerCall("form_submission_Loan_ApplicationPage_2","CKYC SUBMIT","True");
		             		
		             		var userDetails = response.userDetails;
		             		userDetails = JSON.parse(userDetails);
		             		
		             		
		             		 
		             		$('#prefillSelectinput').remove();
		             		$('#prefillSelectoops').remove();
		             		if(userDetails.dedupeCustType=="ETB")
							{
		             			$('#cKycpanLock').show();
		             			$('#newCustline').show();
		             			$('#cKycpinLock').show();
		             			$('#existedCustLine').hide();
		             			$('#okycCustLine').hide();
								$('#ntbToolTipPan').show();
		             			$('#etbToolTipPan').hide();
		             			$('#ntbCustleble').show();
		             			$('#etbCustleble').hide();
		             			$('#genderDiv').remove();
							  // var userDetails = JSON.parse(response.userDetails);
							   var fullName =userDetails.fullName;
							   var mobNumber =userDetails.mobileNumber;
							   var dob =userDetails.dateOfBirth;
							    pan =userDetails.pan;
							    address =userDetails.address;
							    pin =userDetails.pin;
							    var custIDval = userDetails.existingCustId;
							   /*$("#custType").val(userDetails.custType);*/
							   custTypeFD = userDetails.custType;
							   etbFlag = 'etbCkycUser';
							   
							if(custTypeFD=="ETB")
							{
								//$('#etbExclusive').show();
							}
							if(custTypeFD=="STB")
							{
								
								$('#stbExclusive').show();
							}
							if(custTypeFD=="CKYCETB")
							{
								
								//$('#newExclusive').show();
							}
							   
							   var custCkycIDVD = userDetails.existingCustId;
							   if(!(custCkycIDVD== "NA" || custCkycIDVD == "")) 
		 		               {
								   $('#custCkycIDVD').val(custCkycIDVD);
								   $('#custCkycIDVD').parent().addClass('valid');
		                       }
							   
							   if(!(fullName== "NA" || fullName == "")) 
		 		               {
		  	                      	$('#fullNamePD').val(fullName).prop('readonly', true);
			  	                        $('#fullNamePD').parent().addClass('valid');
		
		  	                        var name = fullName.split(" ");	
		  	                        var fname =name[0];
		  	                        var fchar = fname.charAt(0);
		  	                    $('.a_alphaFont').append(fchar);
		  	                	 $('.a_alphaFont').siblings("h2").append(" "+fname);
			                         	    
		                        }
							   if(!(mobNumber== "NA" || mobNumber == "")) 
		 		               {
								   $('#mobilePD').val(mobNumber).prop('readonly', true); 
								   $('#mobilePD').parent().addClass('valid');
								}
							  
							   if(!(dob == "NA" || dob == "")) 
		 		               { 
								   $('#dobPD').val(dob).prop('readonly', true);
								   $('#dobPD').parent().addClass('valid');
								   
								}
							
							   if(!(pan== "NA" || pan == "" || pan == null)) 
         		               {
								   $('#panPD').val(pan);
								   $('#panPD').parent().addClass('valid');
								   $("#panPD").prop("readonly", true); 
								   $("#cKycpanLock").show();
 	                           }
							   if(!(address== "NA" || address == "")) 
		 		               {
								   address = address.replace('-', ' ');   
								   address = address.replace('.', ' ');
		  	                       $('#addressPD').val(address).prop('readonly', true);
		  	                       $('#addressPD').parent().addClass('valid');
		  	                      	
		                        }
							   if(!(pin== "NA" || pin == "" || pin == null)) 
		 		               {
		  	                      $('#pinCodePD').val(pin).prop('readonly', true);
		  	                      $('#pinCodePD').parent().addClass('valid');
		  	                      $("#cKycpinLock").show();
		                        }
							 						 
								$('#accTypeBD').val("Savings").prop('readonly', true);
								  $('#accTypeBD').parent().addClass('valid');
								//$('.a_part_3').hide();
								$('.a_part_3-1').hide();	
				                $('.a_part_4').show();
				              	$('#receiveOtp .p_reqotp button .fd_sdp_loder').hide();	
				              	$('#panCarVerify .p_reqotp button .fd_sdp_loder').hide();
				              //  $('html, body').animate({ scrollTop: 70 }, 200);		   
							   
						
							}
						else if(userDetails.dedupeCustType=="NTB"){
							
						 	
			            $('.a_part_3-1').hide();	
			           	$('#receiveOtp .p_reqotp button .fd_sdp_loder').hide();	
		              	$('#panCarVerify .p_reqotp button .fd_sdp_loder').hide();	
		              	customerIdforDocUpload=userDetails.customerId;
						$('.a_part_document').show();
						$('.identity .uploadbtn').find('input:file').prop('disabled', true);
							
						}
						else if(userDetails.dedupeCustType=="okyc_success"){
							
							$("#RequestnewValue").val(userDetails.Request);
							customerIdforDocUpload=userDetails.customerId;
							$('#ckycSubmitt').hide();
							 $('#panCarVerify #fullNamePDNew').prop('readonly', true);
							 $('#panCarVerify #panCardPV').prop('readonly', true);
							 $('.a_part_3').hide();
							 $('.a_part_3-1').hide();
							 $('.a_part_3-2').hide();
							 $('.a_part_document').show();
							
							}
						else if(userDetails.dedupeCustType == "panRecordNOtFound"){
							
							$("#panCarVerify #panCardPV").siblings(".errormsg").css("display", "Block").text(userDetails.Request);
						}
						else if(userDetails.dedupeCustType == "okyc_fail"){
							
							$("#RequestnewValue").val(userDetails.requestString);
							customerIdforDocUpload=userDetails.customerId;
							 $('#ckycSubmitt').hide();
							 $('#panCarVerify #fullNamePDNew').prop('readonly', true);
							 $('#panCarVerify #panCardPV').prop('readonly', true);
							 $('.a_part_3').hide();
							 $('.a_part_3-1').hide();
							 $('.a_part_3-2').hide();
							 $('.a_part_document').show();
                			
						}
		             		
		               	 $('#panCarVerify .p_reqotp button .fd_sdp_loder').hide();
		               	 
		             	            	  
		             	}
		      	   else if(response.validationStatus == "PanValidationexceed")
	         		{
	             		panValicationCount = response.panValicationCount;
	             		$("#RequestnewValue").val(response.requestString);	
	             		
						customerIdforDocUpload=response.customerId;
						$('#ckycSubmitt').hide();
						 $('#panCarVerify #fullNamePDNew').prop('readonly', true);
						 $('#panCarVerify #pinCodePV').prop('readonly', true);
						 $('#panCarVerify #panCardPV').prop('readonly', true);
						$('#okycandDocSplitChech').show();
	         		}
		      	 else if(response.validationStatus == "fail")
          		{
            	 $('#panCarVerify .p_reqotp button .fd_sdp_loder').hide();
             	 var failMsg =	response.validationMsg ;
          	     $.each(failMsg, function(key, value)
          			   { 
        		   $("input[name="+key+"]").siblings(".errormsg").text(value).show(); 
          		 });
          	     dataLayerCall("form_submission_Loan_ApplicationPage_2","CKYC SUBMIT","False");
          	     newRelicAlert("Fail in CKYC verification","'/fixed-deposit-application-form/addNtbUserData'"," ",fdcNumber);
          		}
		      	else if(response.fieldValidation == "fail"){console.log("addNtbUserData fieldValidation failed...");}       	 
		      	 else if(response.apiStatus== "sessionExpired")
		      	 {
		      		newRelicAlert("session expired in CKYC verification","'/fixed-deposit-application-form/addNtbUserData'",decResponse,fdcNumber);	
             		$('.maxcontainer div:visible').hide();
             		$('.SessionErrorPage').show();
		      	 }
		          else
		             {
		        	  newRelicAlert("Technical Error Page in CKYC verification","'/fixed-deposit-application-form/addNtbUserData'"," ",fdcNumber);
		        	  $('.maxcontainer div:visible').hide();
	                  $('.ErrorPage').show();
		             }
         			 } 	
		         	}   
		      });
			}
			
		    /********************************************NTB Pan functionality end*****************************************************/
		  
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		
		    /********************************************Personal and Bank Details functionality Start ****************************************************/
		
		    /********upload functionality Start ********/
		
		   
		    $("input[name='panCard']").change(function(){
		    		    
		    		   var userPan = $("input[name='panCard']").val();
		    	     
		    	     var panResponse = pan;
		    	         
		    	       if(panResponse!=null || panResponse!="NA" )
		    	    	   {        
		    		    	   if(panResponse == userPan)  
		    					{  
		    					
		    					 $("#panupload").attr("disabled", true);
		    					 $("#panupload").addClass("nomandetory");
		    				     $( "#panupload" ).parent(".uploadDoc").siblings(".errormsg").css('display','none');
		    				     $('#panupLevel').hide();
		    					} 
		    				 else
		    					 { 
		    					 $("#panupload").attr("disabled", false);
		    				     $("#panupload").removeClass("nomandetory");
		    				     $('#panupLevel').show();
		    				   
		    				  
		    				     
		    					 }
		    	    	   }
		    	       else
		    	    	   {
		    	    	    
		    				 $("#panupload").attr("disabled", false);
		    				 $("#panupload").removeClass("nomandetory");  
		    				  $('#panupLevel').show();
		    	    	   }
		    		
		    		         
		    		  });          
		    	  
		    $("input[name='address']").change(function(){
		       
		    	   var userAdd = $("input[name='address']").val();
		     
		        var addResponse = address ;
		         
		       if(addResponse!=null || addResponse!="NA" )
		    	   {        
		    	    	   if(addResponse == userAdd)  
		    				{  
		    				
		    				 $("#addupload").attr("disabled", true);
		    				 $("#addupload").addClass("nomandetory");
		    			     $( "#addupload" ).parent(".uploadDoc").siblings(".errormsg").css('display','none');
		    			    $('#addupLevel').hide();
		    				} 
		    			 else
		    				 { 
		    				 $("#addupload").attr("disabled", false);
		    			     $("#addupload").removeClass("nomandetory");
		    			     $('#addupLevel').show();
		    				 }
		    	   }
		       else
		    	   {
		    	    
		    			 $("#addupload").attr("disabled", false);
		    			 $("#addupload").removeClass("nomandetory");  
		    			 $('#addupLevel').show();
		    	   }
		    	
		    	         
		    	  });          
		     
		    $("input[name='pincode']").change(function(){
		       
		    	   var userPin = $("input[name='pincode']").val();
		
		    var pinResponse = pin ;
		      
		    if(pinResponse!=null || pinResponse !="NA" )
		    	   {        
		    	    	   if(pinResponse == userPin)  
		    				{  
		    				
		    				 $("#addupload").attr("disabled", true);
		    				 $("#addupload").addClass("nomandetory");
		    			    $( "#addupload" ).parent(".uploadDoc").siblings(".errormsg").css('display','none');
		    			    $('#addupLevel').hide();
		    				} 
		    			 else
		    				 { 
		    				 $("#addupload").attr("disabled", false);
		    			     $("#addupload").removeClass("nomandetory");
		    			     $('#addupLevel').show();
		    				 }
		    	   }
		    else
		    	   {
		    	    
		    			 $("#addupload").attr("disabled", false);
		    			 $("#addupload").removeClass("nomandetory");  
		    			 $('#addupLevel').show();
		    	   }
		    	
		    	         
		    	  });          
		
		
		    /********upload functionality Start ********/
		
		
		
		    	function sendUserDetails()
		    	{
		    		var custIdselected=$("#custCkycIDVD").val();
		    		var mobNo=$('#mobilePD').val();
		    	    vizLayer("STEP4","NA",mobNo,custTypeFD,custIdselected);
		    	    
		    	    var	formId ="panform";	
		    		var formData = document.forms[formId];
		    		var formVal = new FormData(formData);
		    		var dd = $("#nomineedd").val().trim();
					var mm = $("#nomineemm").val().trim();
					var yyyy = $("#nomineeyy").val().trim();
					var datee = new RegExp('\\d{2}');
					dd = (!datee.test(dd)) ? ('0'+dd) : (dd);
				    mm = (!datee.test(mm)) ? ('0'+mm) : (mm);
					var dob = dd+"/"+mm+"/"+yyyy;
					var panEtb= $("#panPD").val();
					
					
					var commCheckbox=$('#checkBox_commAddress').val();					
					var commAddDocName="";					
					var commPincode="";
					if(commCheckbox == 'Yes')
					{   						
						commCheckbox="No";
						commAddDocName=$("#commAddressattache").val().trim();
						commPincode=$('#commPincode').val();						
						
					}else  
					{ 
						commCheckbox="Yes";					
						commAddDocName="NA";												
						commPincode="NA";						
					}
										
					formVal.append('commCheckbox',commCheckbox);
					formVal.append('commAddDocName',commAddDocName);
					
					if(commAddDocName=="Aadhaar Card")
					{
					formVal.append("commAddDoc",$("#addharcardDoc")[0].files[0]);
					}else if(commAddDocName=="NREGA Job Card")
		    		{
		    		formVal.append("commAddDoc",$("#nrjobcardDoc")[0].files[0]);
		    		}else if(commAddDocName=="Valid Passport")
		    		{
		    			formVal.append("commAddDoc",$("#passportDoc")[0].files[0]);
		    		}else if(commAddDocName=="Valid Driving Licence")
		    		{
		    			formVal.append("commAddDoc",$("#drivinglicenceDoc")[0].files[0]);
		    		}else if(commAddDocName=="Voter ID Card")
		    		{
		    			formVal.append("commAddDoc",$("#voterIdDoc")[0].files[0]);
		    		}else if(commAddDocName=="Letter issued by National Population Register")
		    		{
		    			formVal.append("commAddDoc",$("#letterNPRDoc")[0].files[0]);
		    		}else{
		    			formVal.append("commAddDoc",new File([""], "dummyFile.jpg",{type: "image/jpeg"}));
		    		}
				
					
					
		    	    if($('#sdpguardianName').is(':visible'))
		    	    {
		    	    	formVal.append('guardinPresent',"YES");
		    	    	
		    	    }else
		    	    {
		    	    	formVal.append('guardinPresent',"NO");
		    	    	
		    	    }
		    	    if (!$('#nomineeCheck').is(":checked")) 
		    	    { 
		    	    	formVal.append('nomineeCheck', "NO"); 
		    	    	formVal.append('NomineeDob', "NA");
		    	    	
		    	    }else{  formVal.append('NomineeDob', dob);}
		    	    if (!$('#nomineeAddrsCheck').is(":checked")) { formVal.append('nomineeAddresCheck', "YES"); }
		    	    var custIdselected=$("#custCkycIDVD").val();
		    	    if(custIdselected=="NA" || custIdselected==undefined){custIdselected="NA"}
		    	    formVal.append('existingCustId',custIdselected);
		    		var gender=$("#gender").val();
					if(gender==null || gender==undefined)
						{
						gender="NA";
						}	
					formVal.append('gender',gender);
					var custId=fdcNumber;
				    
				    if(custId==null || custId == undefined)
		    		{
				    	custId="NA";
				    	
		    		}
				    formVal.append('fdcNumber',custId);
				    
				    if( etbFlag == null || etbFlag=="" || etbFlag.trim()=="" || etbFlag == undefined)  { 	etbFlag = "NA";  }
				    	
				    if(etbFlag=='etbDedupeUser'){
				    	if(panNo != "" && panNo != panEtb){
				
				    		formVal.append("etbFlag", etbFlag+'PanEdited');
					    	console.log(panNo,"===",panEtb);
					    }else{
					    	formVal.append("etbFlag", etbFlag);
					    	console.log(panNo,"===",panEtb);
					    }
				    }else{
				    	formVal.append("etbFlag", etbFlag);
				    	console.log(panNo,"===",panEtb);
				    }
					console.log("Personal Details button ajax event Done");
					
		    		 $.support.cors = true;
		             $.ajax({ 
		                         
		                 url: "/fixed-deposit-application-form/addUserData", 
		                 type: "POST",
		                contentType : false,
		         		processData : false,
		                data: formVal,
		                error: function(response)
		    			{
		                	 $('#panform .a_blueBtnPart button .fd_sdp_loder').hide();
		                	 newRelicAlert("Technical Error Page in addUserData | Fail Ajax call","'/fixed-deposit-application-form/addUserData'",response.status,custId);
		                	 $('.maxcontainer div:visible').hide();
			                 $('.ErrorPage').show(); 
		                }  ,
		                 success: function(response) {
		                	 var encResponse = JSON.parse(response);
		                	 var decResponse = getOfferobje(encResponse);
		                	 var response = JSON.parse(decResponse);		                	 		                	 
		                	 
		                	  if(response.validationStatus == "success"){ 
		          	    	    fdcNumber=response.FdcNumber;
		                 		var calcType = $("#calc").val();	
		                 		var bankDeatails=JSON.parse(response.bankDetails);
		                 		var bankDeatilStatus=bankDeatails.IfscStatus;
		                 		if(bankDeatilStatus=="success")
		                 		{
		                 		bankDetailsFlag=true;
		                 		bankName=bankDeatails.bankName;
		                 		bankAccNumber=bankDeatails.accountNo;
		                 		bankifscCode=bankDeatails.ifscCode;
		                 		}
		                 		
		                 	  if(calcType=="FD")
		                 	 {
			                 		 
				                 	 $("#fdAmount").val("200000").change();
				                 	 $("#fdbankName").siblings(".errormsg").hide();
				                 	 $('.a_part_4').hide();
				                     $('.a_part_5').show();
				                	 $('#panform .a_blueBtnPart button .fd_sdp_loder').hide();
				                	
				                	 if(bankDetailsFlag){
					                	 $('select[name="fdbankName"]').val(bankName).change();
					                	 $('#fdbankName').parents('.a_ReInput').siblings('.a_opplsLink').slideUp(200);
					                	 $("#fdaccountNumber").val(bankAccNumber);
					                	 $("#fdifscCode").val(bankifscCode); 
				                	 }
				             }
		                 	  if(calcType=="SDP")
		                  	 {
		                 	  $("#sdpAmount").val("5000").change();	
		                 	 $("#sdpbankName").siblings(".errormsg").hide();
		                 	  $('.a_part_4').hide();
		                      $('.a_part_6').show();
		                 	 $('#panform .a_blueBtnPart button .fd_sdp_loder').hide();
		                 	 
		                 	if(bankDetailsFlag){
		                 		$('select[name="sdpbankName"]').find('option[value="'+bankName+'"]').attr("selected",true).change();
			                 	$('#sdpbankName').parents('.a_ReInput').siblings('.a_opplsLink').slideUp(200)
			                	 $("#sdpaccountNumber").val(bankAccNumber);
			                	 $("#sdpifscCode").val(bankifscCode);
	                 		  }
		                 
		                 	else
		                 	{
		                 	$('#SdpNetBankCheck input').prop('checked', true);
		                 	$('#sdpbankName').parents('.a_ReInput').siblings('.bnkMethod').show();
		                 	$("#sdpnomeeni  .a_radiodeposit_2").show();
		                 	$('#sdpbankName').parents('.a_ReInput').siblings('.a_opplsLink').hide();
		                 	}
		                 	$('#investType').html("Book new SDP");
		                 	$('#investment').html("SDP");
		                  	 }
		                 	 $('#hiddenPayOutTypeCheck').val('');
		                 	 dataLayerCall("form_submission_Loan_ApplicationPage_2","Banking Datails SUBMIT","True");
		                 	} 
		                 	else if(response.validationStatus == "fail")
		                 		{
		                   	 $('#panform .a_blueBtnPart button .fd_sdp_loder').hide();
		
		                    	var failMsg =	response.validationMsg;
		                 	   $.each(failMsg, function(key, value){ 
		                 		   
		               		   if(key=="nomineedob")
		               			   {
		               			   
		               			 $("#nomineedd").siblings(".errormsg").text("Please enter valid nominee dob").show(); 
		               			   }else
		               				   {
		               				 $("input[name="+key+"]").siblings(".errormsg").text(value).show();
		               				   }
		              		 });
		                 	  dataLayerCall("form_submission_Loan_ApplicationPage_2","Banking Datails SUBMIT","False");
		                 	  
		                 	newRelicAlert("Fail in addUserData","'/fixed-deposit-application-form/addUserData'"," ",custId);
		                 		}
		                 	else if(response.apiStatus== "sessionExpired")
		             		{
		                 		newRelicAlert("session expired in addUserData","'/fixed-deposit-application-form/addUserData'",response,fdcNumber);
			             		$('.maxcontainer div:visible').hide();
			             		$('.SessionErrorPage').show();
		             		}
		                 	else
		                 		{
		                 		newRelicAlert("Technical Error Page in addUserData","'/fixed-deposit-application-form/addUserData'"," ",custId);
		                 		$('.maxcontainer div:visible').hide();
				                $('.ErrorPage').show();
		                 		
		                 		}
		                 	
		             	}   
		          });
		    	}
		    	
		    /********************************************Personal and Bank Details functionality End ****************************************************/
		    	
		    	
		    /*****************************************FD and SDP Calculator and Nominee functionality Start****************************************************/
		    	
		    	 var url_string = window.location.href;
				  var url = new URL(url_string);
				  var productName = url.searchParams.get("ProductName");
				  var okystatus =  okycstatusIDValue;
				  var investmenttype =  investmentTypeOkyc;
				  var partnerCode = url.searchParams.get("PartnerCode");
				  var experiaData = url.searchParams.get("experiaResponse");
				  
			    	//productName ="FD"
			    	if(productName=="FD")
			    		{
			    		fdContent();
			    		  $('#calc').val("FD");	    
			    		  $('.track_step').removeClass('track_step_Sdp');
			    		  $(".beingAssistedForm #pName").siblings('.errormsg').hide();
			    		  $('.a_part_2').show();
			    		  $( "#mobileNO" ).focus();
			    		  sdpCampaign();
			    		}
			    	else if(productName=="SDP")
		    		{
			    		sdpContent();
		    		  $('#calc').val("SDP");
		    		  $('#investType').html("Book new SDP");
		    		  $('#investment').html("SDP");
		    		  $(".beingAssistedForm #pName").siblings('.errormsg').hide();
		    		  $('.a_part_2').show();
		    		  $( "#mobileNO" ).focus();
		    		  sdpCampaign();
		    		}
			    	else if(productName=="resume")
		    		{
			    		$('.a_part_2').show();
			    		$('#calc').val("FD");	
						  $('.tab_ul li a[ data-tab="resume_applicatIon"]').addClass('active');
						  $('#resume_applicatIon').css('display', 'block');
			    		  $( "#mobileNO" ).focus();
						  $('.tab_ul li a[ data-tab="apply_for_new_fd"]').removeClass('active');
						  $('#apply_for_new_fd').css('display', 'none');
						  tokenCall();
			    	}
			    	else if(partnerCode=="97747")
		    		{
			    		  fdContent();
			    		  $('#calc').val("FD");	    
			    		  $('.track_step').removeClass('track_step_Sdp');
			    		  $('.a_part_2').show();
			    		  $( "#mobileNO" ).focus();
			    		}
			    	else if(productName=="FD_paid")
			    	{
			    		fdContent();
			    		  $('#calc').val("FD");	    
			    		  $('.track_step').removeClass('track_step_Sdp');
			    		  $('.beingAssisted').remove();
			    		  $('.beingAssistedForm').remove();
			    		  $('.a_part_2').show();
			    		  $( "#mobileNO" ).focus();
			    		  sdpCampaign();
			    		}
			    	else
			    		{
			    		  $('.a_part_1').show();	    		 
			    		}
			    	
			    	
			    	if(partnerCode != null && partnerCode != undefined){
			    		
			    		$('.beingAssistedFormShowOrHide a').click();
			    		
			    		$('#pCode').val(partnerCode).prop('readonly', true);
			    		  $('#pCode').trigger('blur');
			    		}
		
			    	function sdpCampaign()
			    	{
			    		 var fdsdpUserDeatils = GetCookie('sdpCampCookie');
			    		  
			    		  if(fdsdpUserDeatils != null){
			    			  
			    			 	var jsob = JSON.parse(fdsdpUserDeatils);
			    			 	var decrypted = decryptdataFunction(jsob.encryptCookie,jsob.encryptCookiesalt);
				    		 	fdsdpUserDeatils = JSON.parse(decrypted);
				    		 	var sdpCamDOB = fdsdpUserDeatils.dob;
				    		 	var sdpCamMobileval = fdsdpUserDeatils.mobile;  
				    		 	var fdsdpDOBsplit = sdpCamDOB.split("/");
				    		 	$('#mobileNO').val(sdpCamMobileval).prop('readonly', true);
				    		 	$('#dd').val(fdsdpDOBsplit[0]).prop('readonly', true);
				    		 	$('#mm').val(fdsdpDOBsplit[1]).prop('readonly', true);
				    		 	$('#yyyy').val(fdsdpDOBsplit[2]).prop('readonly', true);
			    		  
				    		 	delete_cookie('sdpCampCookie');
			    		  
						  }
			    	}
			    	
    /*****************************************SSO on load Function start here**************************************************************/
			    	function decryptParam (encStr){
			    		  
				         var key = CryptoJS.enc.Utf8.parse('cBnfZX3atf4PQRgg');
			   
				         var iv = CryptoJS.enc.Utf8.parse('1234567887654321');

				         encStr = encStr.replace(/~/g,"+");//replace(/~/g, "+");

				         return CryptoJS.AES.decrypt(encStr, key, {iv: iv}).toString(CryptoJS.enc.Utf8);

				     }
			    	if(!(experiaData ==null && experiaData== undefined))
		    		{
			    		   $('.loaderslide div').html('');
		            	   $('.loaderslide div').html('Please wait....');
		            	   $('.loadoverlay').show();
		            	   
			    		getIpAddress();
			    		var timeOfLogging = new Date().toLocaleTimeString('en-GB');
						var timeOfLoggingday=new Date().toLocaleDateString();
						timeOfLogging=timeOfLoggingday+" "+timeOfLogging;
			    	
				        var ipAdress = dataVal;
				        if (ipAdress != null || ipAdress != undefined) {
				            ipAdress = ipAdress.replace(/\s/g, '');
				        } else {
				            ipAdress = "NA"
				        }
				        
		    		var token = url.searchParams.get("sessionId");
		    		//console.log("----experiaData----" + experiaData);
		    		var experiaEncryptedData = experiaData.replace(/ /g, "+");
		    		var DecryptedData = decryptParam(experiaEncryptedData);
		    		DecryptedData = DecryptedData.split("&");

		    		var obj = {};
		    		for (var i = 0; i < DecryptedData.length; i++) {
		    		    var keyValue = DecryptedData[i].split("=");
		    		    obj[keyValue[0]] = keyValue[1];
		    		}
		    		//console.log("---- obj_appId----" + obj["appId"]);
		    		//console.log("----obj_source----" + obj["source"]);
		    		if ((obj["appId"] != null && obj["appId"] != 'blank' ) && obj["source"] == 'experialogin') {
		    		  //  console.log("--Experia Condtion true;--");
		    		    var appIdval=obj["appId"];
		    		    var tokenValue=token;
		    		    
		    		    var data = {
			    		        "fdcNumber": appIdval,
			    		        "token": tokenValue
			    		    };
		    		    
		    		    var device;
		    		    var windowwidth = $(window).width();
				        if (windowwidth < 600) {
				            device = "Mobile";
				        } else if (windowwidth > 640 && windowwidth < 992) {
				            device = "Tablet";
				        } else {
				            device = "Desktop";
				        }
		    		    var pageURL = window.location.href;
		    		    data.device = device;
		    		    data.pageURL = pageURL;
				        data.formName = "DigitalFixedDepositForm";
				        data.formID = "1501038808633";
				        data.device = device;
				        data.ipAdress=ipAdress;
				        data.timeOfLogging=timeOfLogging;

				        var utm_source = getUrlValue("utm_source");
				        var utm_medium = getUrlValue("utm_medium");
				        var utm_campaign = getUrlValue("utm_campaign");
				        if (utm_source != null || utm_medium != null  || utm_campaign != null) {
				        	 if (utm_campaign == null || utm_campaign === 'null') {
				        		 utm_campaign= 'NA';
					            } else {
					            	utm_campaign= utm_campaign;
					            }

					            if (utm_medium == null || utm_medium === 'null') {

					            	utm_medium= 'NA';
					            } else {
					            	utm_medium= utm_medium;
					            }
					            
					            if (utm_source == null || utm_source === 'null') {
					            	utm_source= 'experia';
					            } else {
					            	utm_source = utm_source;
					            }
				        }
				        data.utm_source = utm_source;
				        data.utm_medium = utm_medium;
				        data.utm_campaign = utm_campaign;
				        
		    		    $.ajax({

		    		        url: "/fixed-deposit-application-form/getdatasinglesign",
		    		        type: "POST",
		    		        data: JSON.stringify(data),
		    		        contentType: 'application/json',
		    		        error: function(response) {
		    		        	newRelicAlert("Technical Error Page in getdatasinglesign | Fail Ajax call","'/fixed-deposit-application-form/getdatasinglesign'",response.status," ");
		    		            console.log("inside errror function");
		    		        },
		    		        success: function(response) {

		    		            var encResponse = JSON.parse(response);
		    		            var decResponse = getOfferobje(encResponse);
		    		            var response = JSON.parse(decResponse);
		    		            //console.log("--response--"+response);
		    		            if (response.status == "success")
		    		            {
		    		            	
		    		                var userDetails = response.UserDetails;
		    		               // userDetails = JSON.parse(userDetails);
		    		               

		    		                var fullName = userDetails.fullName;
		    		                var mobNumber = userDetails.mobileNumber;
		    		                var dob = userDetails.dateOfBirth;
		    		                var pan = userDetails.pan;
		    		                var address = userDetails.address;
		    		                var pin = userDetails.pin;
		    		                var custIDval = userDetails.existingCustId;
		    		                custTypeFD =userDetails.custType;
		    		                fdcNumber=userDetails.customerId;
		    		                var email =userDetails.email;
		    		                $('#calc').val("FD");
		    		                $('.a_part_1').hide();
		    		                $('.a_part_2').hide();
		    		                
		    		                $('.part_3').hide();
		    		                $('.a_part_4').show();
		    		                $('#prefillSelectinput').remove();
		    		                $('#prefillSelectoops').remove();
		    		                $('#genderDiv').remove();
		    		                $('#newCustline').hide();
		    		                $('#existedCustLine').hide();
		    		                $('#ntbToolTipPan').show();
		    		                $('#etbToolTipPan').hide();
		    		                $('#ntbCustleble').hide();
	    	             			$('#etbCustleble').show();
	    	             			$('.ckycHide').show();
	    	             			
		    		                if (custTypeFD == "STB") {
		    		                    custTypeFD = "STB";
		    		                }else{
		    		                	 custTypeFD =custTypeFD;
		    		                }

		    		                var custCkycIDVD = userDetails.existingCustId;
									   if(!(custCkycIDVD== "NA" || custCkycIDVD == "")) 
				 		               {
										   $('#custCkycIDVD').val(custCkycIDVD);
										   $('#custCkycIDVD').parent().addClass('valid');
				                       }
									   
		    		                if (!(fullName == "NA" || fullName == "")) {
		    		                    $('#fullNamePD').val(fullName).prop('readonly', true);
		    		                    $('#fullNamePD').siblings('label').addClass('active');

		    		                    var name = fullName.split(" ");	
    		  	                        var fname =name[0];
    		  	                        var fchar = fname.charAt(0);
    		  	                    	$('.a_alphaFont').append(fchar);
    		  	                    	$('.a_alphaFont').siblings("h2").append(" "+fname);

		    		                }
		    		                if (!(mobNumber == "NA" || mobNumber == "")) {
		    		                    $('#mobilePD').val(mobNumber).prop('readonly', true);
		    		                    $('#mobilePD').siblings('label').addClass('active');
		    		                    $('#mobileNO').val(mobNumber).prop('readonly', true);
		    		                }

		    		                if (!(dob == "NA" || dob == "")) {
		    		                    $('#dobPD').val("").prop('readonly', true);
		    		                    $('#dobPD').val(dob).prop('readonly', true);
		    		                    $('#dobPD').siblings('label').addClass('active');

		    		                }
		    		                if(!(email== "NA" || email == "" || email == null)) 
	             		               { 
	    								   $('#emailPD').val(email);
	    								   $('#emailPD').parent().addClass('valid');
	    								}
		    		                if (!(pan == "NA" || pan == "")) {
		    		                    $('#panPD').val(pan).prop('readonly', true);
		    		                    $('#panPD').siblings('label').addClass('active');
		    		                    $("#panPD").prop("readonly", true);
		    		                    $('#panPD').parent().addClass('valid');
		    		                    
		    		                }
		    		                
		    		                if (!(pin == "NA" || pin == "")) {
		    		                    $('#pinCodePD').val(pin).prop('readonly', true);
		    		                    $('#pinCodePD').siblings('label').addClass('active');
		    		                }

		    		                if(!(address== "NA" || address == "")) 
	             		               {
	    								   
	    								   address = address.replace('-', ' ');   
	    								   address = address.replace('.', ' ');
	    		  	                       $('#addressPD').val(address);
	    		  	                       $('#addressPD').parent().addClass('valid');
	    		  	                      	
	     	                           }
		    		                $('#adderssLock').remove();
		    		                $('.loadoverlay').hide();

		    		            } else if (response.status == "fail") {
		    		                if (response.apiStatus == "tokenValidation") {

		    		                	alert("Token validation failed.Please refresh the page and initiate application again.");
		    		                	var redirectUrl=url_string.slice(0,url_string.indexOf("experiaResponse"));
		    		                	redirectUrl=redirectUrl+"ProductName=FD";
		    		 	    		    window.location=redirectUrl;
	    					     		 
		    		                } else if (response.apiStatus == "Exception") {
		    		                	alert("Please refresh the page and initiate application again.");
		    		                	var redirectUrl=url_string.slice(0,url_string.indexOf("experiaResponse"));
		    		                	redirectUrl=redirectUrl+"ProductName=FD";
		    		 	    		    window.location=redirectUrl;
		    		                } else if (response.apiStatus == "tokentimeExceeded") {

		    		                	alert("Token time limit exceeded.Please refresh the page and initiate application again.");
		    		                	var redirectUrl=url_string.slice(0,url_string.indexOf("experiaResponse"));
		    		                	redirectUrl=redirectUrl+"ProductName=FD";
		    		 	    		    window.location=redirectUrl;
		    		                } else if (response.apiStatus == "norequestId") {
		    		                	alert("requested session not valid.Please refresh the page and initiate application again.");
		    		                	var redirectUrl=url_string.slice(0,url_string.indexOf("experiaResponse"));
		    		                	redirectUrl=redirectUrl+"ProductName=FD";
		    		 	    		    window.location=redirectUrl;
		    		                } else if (response.apiStatus == "fetchOncessoDone") {
		    		                	alert("Please refresh the page and initiate application again.");
		    		                	var redirectUrl=url_string.slice(0,url_string.indexOf("experiaResponse"));
		    		                	redirectUrl=redirectUrl+"ProductName=FD";
		    		 	    		    window.location=redirectUrl;
		    		                } 



		    		            }
		    		        }
		    		    });
		    		}else if (obj["appId"] == 'blank' && obj["source"] == 'experialogin')
		    			{
		    			fdContent();
			    		  $('#calc').val("FD");	    
			    		  $('.track_step').removeClass('track_step_Sdp');
			    		  $(".beingAssistedForm #pName").siblings('.errormsg').hide();
			    		  $('.a_part_2').show();
			    		  $( "#mobileNO" ).focus();
			    		  tokenCall();
			    		  $('.loadoverlay').hide();
		    			}
		    		}
	/*****************************************SSO on load Function End here**************************************************************/
		    /********* for showing fd and sdp page*****/	
		    	$("#FD a").click(function(){
		            $('#calc').val("FD");
		            $('.otpTncPdf').attr('href','/fixed-deposit-application-form/resources/pdf/TCs-RI.pdf');
		        });
		     $("#SDP a").click(function(){
		         $('#calc').val("SDP");
		         $('#investType').html("Book new SDP");
		         $('#investment').html("SDP");
		         $('.otpTncPdf').attr('href','/fixed-deposit-application-form/resources/pdf/TCs-RI.pdf');
		     });
		    	
		     /****** for showing fd and sdp page functionality end*****/	
		    
		     /****** for showing fd and sdp page functionality For OKYC Start*****/
		     if(okystatus== "okycstatusSucess"){
		    		$('#prefillSelectinput').remove();
		    		$('#prefillSelectoops').remove();
		    	 $('#ntbCustleble').hide();
		    	 $('#etbCustleble').hide();
		    	 $('#okyxAdharSection').hide();
		    	 trackPageviewScript('Aadhar_Proceed');
		    	 var mobNo=$("#fdUserMobileNumberNew").val();
	    		 $("#mobileNO").val(mobNo);
	    		 customerIdforDocUpload=$("#fdExistingCustIdNew").val();
		    	 if(investmenttype=="FD")
		 		{
		 		 $('#calc').val("FD");	 
		 		 
		 		 $('.a_part_1').hide();
		 		 $('.a_part_2').hide();
		 		 $('.a_part_3').hide();
		 		 $('.a_part_3-1').hide();	
		         $('.a_part_4').show();
		 		}
		 	else if(investmenttype=="SDP")
				{
				  $('#calc').val("SDP");
				  $('.a_part_1').hide();
			 	  $('.a_part_2').hide();
			 	  $('.a_part_3').hide();
			 	  $('.a_part_3-1').hide();	
			      $('.a_part_4').show();
			      $('#investType').html("Book new SDP");
			      $('#investment').html("SDP");
			 		}
		    	 
		    	 var json = JSON.parse(GetCookie('thankYouData'));
				 var thankYouDataCookie = GetCookie('thankYouData'); 
				 if(thankYouDataCookie == null){
					// location.reload();
					 window.location="/fixed-deposit-application-form/thank-you-page"
				 }
				 
				 var encResponse = JSON.parse(thankYouDataCookie);
			     var decResponse = getOfferobje(encResponse);
			     
			     var response = JSON.parse(decResponse);
			     fdcNumber=response.customerId;
			     if(response.validationStatus == "success")
		      	{ 
		      		dataLayerCall("form_submission_Loan_ApplicationPage_2","CKYC SUBMIT","True");
		      		
		      		var userDetails = response.userDetails;
		      		
		      		userDetails = JSON.parse(userDetails);
		      		
		      		userDetails = userDetails.userdata;
		      		fdcNumber=userDetails.customerId;
		      		if(userDetails.dedupeCustType=="OKYCETB")
						{
		      			$('#cKycpanLock').show();
		      			$('#newCustline').hide();
		      			$('#okycCustLine').show();
		      			$('#cKycpinLock').show();
		      			$('#existedCustLine').hide();
						$('#ntbToolTipPan').show();
		      			$('#etbToolTipPan').hide();
		      			$('#genderDiv').remove();
						  // var userDetails = JSON.parse(response.userDetails);
						   var fullName =userDetails.fullName;
						   var mobNumber =userDetails.mobileNumber;
						   var dob =userDetails.dateOfBirth;
						    pan =userDetails.pan;
						    address =userDetails.address;
						    pin =userDetails.pin;
						    var custIDval = userDetails.existingCustId;
						   /*$("#custType").val(userDetails.custType);*/
						   var okyccustTypeFD= userDetails.custType;
						    if(okyccustTypeFD == "STB"){
						    	custTypeFD = "STB";
						    }else{
						    	custTypeFD = "CKYCETB";	
						    }
						    
						    
						   
						if(custTypeFD=="ETB")
						{
							//$('#etbExclusive').show();
						}
						if(custTypeFD=="STB")
						{
							
							$('#stbExclusive').show();
						}
						if(custTypeFD=="CKYCETB")
						{
							
							//$('#newExclusive').show();
						}
						
						if(custTypeFD=="OKYCETB")
						{
							
							//$('#newExclusive').show();
						}
						   
						   var custCkycIDVD = userDetails.existingCustId;
						   if(!(custCkycIDVD== "NA" || custCkycIDVD == "")) 
			               {
							   $('#custCkycIDVD').val(custCkycIDVD);
							   $('#custCkycIDVD').parent().addClass('valid');
		                }
						   
						   if(!(fullName== "NA" || fullName == "")) 
			               {
		                     	$('#fullNamePD').val(fullName).prop('readonly', true);
			                        $('#fullNamePD').parent().addClass('valid');

		                       var name = fullName.split(" ");	
		                       var fname =name[0];
		                       var fchar = fname.charAt(0);
		                   $('.a_alphaFont').append(fchar);
		               	 $('.a_alphaFont').siblings("h2").append(" "+fname);
		                      	    
		                 }
						   if(!(mobNumber== "NA" || mobNumber == "")) 
			               {
							   $('#mobilePD').val(mobNumber).prop('readonly', true); 
							   $('#mobilePD').parent().addClass('valid');
							   $('#mobileNO').val(mobNumber).prop('readonly', true); 
							}
						  
						   if(!(dob == "NA" || dob == "")) 
			               { $('#dobPD').val("").prop('readonly', true);
							   $('#dobPD').val(dob).prop('readonly', true);
							   $('#dobPD').parent().addClass('valid');
							   
							}
						
						   if(!(pan== "NA" || pan == "" || pan == null)) 
     		               {
							   $('#panPD').val(pan);
							   $("#panPD").prop("readonly", false); 
							   $("#cKycpanLock").hide();
	                           }
						   
						   if(!(address== "NA" || address == "")) 
			               {
							   address = address.replace('-', ' ');   
							   address = address.replace('.', ' ');
		                      $('#addressPD').val(address).prop('readonly', true);
		                      $('#addressPD').parent().addClass('valid');
		                     	
		                 }
						   if(!(pin== "NA" || pin == "")) 
			               {
		                     $('#pinCodePD').val(pin).prop('readonly', true);
		                     $('#pinCodePD').parent().addClass('valid');
		                 }
						 						 
							$('#accTypeBD').val("Savings").prop('readonly', true);
							  $('#accTypeBD').parent().addClass('valid');
							  
							  delete_cookie('thankYouData');
							  
							//$('.a_part_3').hide();
							$('.a_part_3-1').hide();	
			                $('.a_part_4').show();
			              	$('#receiveOtp .p_reqotp button .fd_sdp_loder').hide();	
			              	$('#panCarVerify .p_reqotp button .fd_sdp_loder').hide();
			              //  $('html, body').animate({ scrollTop: 70 }, 200);		   
						   
					
						}
					else if(userDetails.dedupeCustType=="NTB"){
						
					 	
		            $('.a_part_3-1').hide();	
		        	$('#receiveOtp .p_reqotp button .fd_sdp_loder').hide();	
		             
		        	window.location="/fixed-deposit-application-form/thank-you-page"
		        		$('#panCarVerify .p_reqotp button .fd_sdp_loder').hide();					
						
					}
		      		
		      	//	window.location="/fixed-deposit-application-form/thank-you-page"
		        	 $('#panCarVerify .p_reqotp button .fd_sdp_loder').hide();
		      		
		           //   $('html, body').animate({ scrollTop: 70 }, 200);	
		        	 
		        	 
		      	            	  
		      	} 
		      	else if(response.validationStatus == "fail")
		      		{
		        	 $('#panCarVerify .p_reqotp button .fd_sdp_loder').hide();
		         	var failMsg =	response.validationMsg ;
		      	   $.each(failMsg, function(key, value)
		      			   { 
		    		   $("input[name="+key+"]").siblings(".errormsg").text(value).show(); 
		   		 });
		      	  dataLayerCall("form_submission_Loan_ApplicationPage_2","CKYC SUBMIT","False");
		      		}
		      	else
		      		{
		      		
		      		alert("Oops, there seems to be some technical issue. Our team is working on it. Sorry for inconvenience. Please try after sometime. Thank you");
			     				location.reload(); 
		      		}
			     
			     
		     }else if(okystatus== "okycstatusFail")
	    	 {
		    	 $("#uploadDocument").addClass("disabledbutton");
		    	 $('.loaderslide div').html('');
			     $('.loaderslide div').html('Your OKYC Process was unsuccessful, Kindly proceed with Doc Upload');
			     $('.loadoverlay').show();
			     var timeleft = 5; 
			     var downloadTimer = setInterval(function(){
		                if(timeleft <= 0){
		                    clearInterval(downloadTimer);
		                    $('.loadoverlay').hide();
		                    $("#uploadDocument").removeClass("disabledbutton");
		                }
		                timeleft -= 1;
		                }, 1000);
		    	 var thankYouDataCookiee = GetCookie('thankYouData');
		    		if(thankYouDataCookiee == null) {
		    			window.location = "/fixed-deposit-application-form/thank-you-page"
		    		}
		    		var encResponsee = JSON.parse(thankYouDataCookiee);
		    		var decResponsee = getOfferobje(encResponsee);
		    		var responsee = JSON.parse(decResponsee);
		    		var mobileNum=responsee.fdUserMobileNumber;
		    		var investtype=responsee.investmentType;
		    		customerIdforDocUpload=responsee.fdExistingCustId;
		    		 $("#mobileNO").val(mobileNum);
		    		 $("#fdExistingCustIdNew").val(customerIdforDocUpload);
		    		 fdcNumber=customerIdforDocUpload;
		    		 custTypeFD="NTBDocumentUpload";
		    		 
			 		$('#prefillSelectinput').remove();
		    		$('#prefillSelectoops').remove();
		    		$('#ntbCustleble').hide();
		    		$('#etbCustleble').hide();
		    		$('#okyxAdharSection').hide();
		    		$('.a_part_1').hide();
		    		$('.a_part_2').hide();
		    		$('.a_part_3').hide();
		    		$('.a_part_3-1').hide();
		    		$('.a_part_3-2').hide();
		    		$('.a_part_4').hide();
		    		$('.a_part_document').show();
		    		$('.identity .uploadbtn').find('input:file').prop('disabled', true);
		    		
		    		 delete_cookie('thankYouData');
			    		if(investtype == "FD") {
			    			$('#calc').val("FD");
			    		} else if(investtype == "SDP") {
			    			$('#calc').val("SDP");
			    			$('#investType').html("Book new SDP");
							  $('#investment').html("SDP");
			    		}
		    		
		    	 if(customerIdforDocUpload == null || mobileNum==null || customerIdforDocUpload == undefined || mobileNum==undefined ){
						 window.location="/fixed-deposit-application-form/thank-you-page"
					 }    
			    	 }
		     
		     
		     
		     /****** for showing fd and sdp page functionality For OKYC end*****/
		     
 /****** for showing fd and sdp page functionality For Fixed Deposit Short Lead Form Start*****/
		     
		     if(fdslIDNew == "fdslSucess"){
		    	 
		    	 $('#ntbCustleble').hide();
		    	 $('#etbCustleble').hide();
		    	 $('#okyxAdharSection').hide();
		    	 
		    	 
		 		 $('#calc').val("FD");	 
		 		 
		 		 $('.a_part_1').hide();
		 		 $('.a_part_2').hide();
		 		 $('.a_part_3').hide();
		 		 $('.a_part_document').hide();
		 		 //$('.a_part_3-1').hide();	
		         //$('.a_part_4').show();
		 	
		 		 
		 		var ntbfullName =fdFullnameNew;
		 		var ntbpin =fdPincodeNew;
		 		var ntbmobNumber =fdUserMobileNumberNew;
		 		fdcNumber=fdSlfFdcNum;    		
		      		if(fdDedupeCustTypeNew=="ETB")
						{
							$('#ntbCustleble').hide();
		    	            $('#etbCustleble').show();
		    	            $('#genderDiv').remove();
										 
		    							   var fullName =fdFullnameNew;
		    							   var mobNumber =fdUserMobileNumberNew;
		    							   var email =fdEmailNew;
		    							   var dob =fdDateOfBirthNew;
		    							   var pan =fdPANnew;
		    							   var address =fdAddressNew;
		    							   var pin =fdPincodeNew;
		    							   custTypeFD = fdCustTypeNew;
		    							   var product =fdproductForIDNew;
		    							   var flag = true;
							
							
							if(custTypeFD=="ETB")
		    								{
		    									//$('#etbExclusive').show();
		    								}
		    								if(custTypeFD=="STB")
		    								{
		    									
		    									$('#stbExclusive').show();
		    								}
		    								if(custTypeFD=="CKYCETB")
		    								{
		    									
		    									//$('#newExclusive').show();
		    								}
		    								 if(!(dob == "NA" || dob == "")) 
			             		               { 
			    								   $('#dobPD').val(dob);
			    								   $('#dobPD').parent().addClass('valid');
			    								   
			    								}else{
			        	                        	  flag =false;
			      	                           }
		    								 if(!(mobNumber== "NA" || mobNumber == "")) 
			             		               {
			    								   $('#mobilePD').val(mobNumber); 
			    								   $('#mobilePD').parent().addClass('valid');
			    								   $('#mobileNO').val(mobNumber).prop('readonly', true);
			    								}else{
			        	                        	  flag =false;
			      	                           }

			    								
			    								console.log(JSON.stringify(customIDForManuplationNew));
			    								
			    								customIDForManuplationNew = JSON.stringify(customIDForManuplationNew);
			    								var custArray=JSON.parse(customIDForManuplationNew);
			    								
			    								custArray=JSON.parse(custArray);
			    								
			    								var uniqueId = [];
			    								$.each(custArray, function(i, el){
			    								    if($.inArray(el, uniqueId) === -1) uniqueId.push(el);
			    								});
			    								console.log("custArray>>"+custArray);
				    							   $('#costomerId').empty();
				    							   $.each(uniqueId, function(i, p) {
				    							       $('#costomerId').append($('<option></option>').val(p).html(p));
				    							   });
		    								if(product=="FD" || product=="FD_BLANK" )
		    								{
		    							   var custCkycIDVD = fdExistingCustIdNew;
		    							   if(!(custCkycIDVD== "NA" || custCkycIDVD == "")) 
		             		               {
		    								   $('#custCkycIDVD').val(custCkycIDVD);
		    								   $('#custCkycIDVD').parent().addClass('valid');
		    								   selectedCust=false;
		    								   $("#costomerId").val(custCkycIDVD).attr("selected","selected");
		    								   if(product=="FD")
		    						        	{
		    						        		$('#prefillSelectoops').show();
		    						        		productFDCustId=custCkycIDVD;
		    						        	}else
		    						        	{
		    						        		$('#prefillSelectoops').hide();
		    						        	
		    						        	}
		             		               }else{
		     	                        	  flag =false;
		     	                           }
		    							   
		    							   if(!(fullName== "NA" || fullName == "")) 
		             		               {
		    		  	                      	$('#fullNamePD').val(fullName);
		      		  	                        $('#fullNamePD').parent().addClass('valid');
		
		    		  	                        var name = fullName.split(" ");	
		    		  	                        var fname =name[0];
		    		  	                        var fchar = fname.charAt(0);
		    		  	                    $('.a_alphaFont').append(fchar);
		    		  	                	 $('.a_alphaFont').siblings("h2").append(" "+fname);
		    	  	                         	    
		     	                           }else{
		      	                        	  flag =false;
		     	                           }
		    							  
		    							   if(!(email== "NA" || email == "")) 
		             		               { 
		    								   $('#emailPD').val(email);
		    								   $('#emailPD').parent().addClass('valid');
		    								}else{
		        	                        	  flag =false;
		      	                           }
		    							   
		    							  
		    							
		    							   if(!(pan== "NA" || pan == "" || pan == null)) 
		             		               {
		    								   $('#panPD').val(pan);
		    								   $('#panPD').parent().addClass('valid');
		    								   $("#panPD").prop("readonly", true); 
		     	                           }else{
		      	                        	  flag =false;
		     	                           }
		    							   
		    							   if(!(address== "NA" || address == "")) 
		             		               {
		    								   address = address.replace('-', ' ');   
		    								   address = address.replace('.', ' ');
		    		  	                       $('#addressPD').val(address);
		    		  	                       $('#addressPD').parent().addClass('valid');
		    		  	                      	
		     	                           }else{
		      	                        	  flag =false;
		     	                           }
		    							   
		    							   if(!(pin== "NA" || pin == "" || pin ==null)) 
		             		               {
		    		  	                      $('#pinCodePD').val(pin);
		    		  	                      $('#pinCodePD').parent().addClass('valid');
		    		  	                       $("#pinCodePD").prop("readonly", true); 
		    								   $("#cKycpinLock").show();
		     	                           }else{
		      	                        	  flag =false;
		     	                           }
		    							   
		    							   if(flag){
		    								   $('.a_topBoxbord .a_halfBox a[data-tab="Personal_Details"] ~ .tickNdisimg').attr('src','/fixed-deposit-application-form/resources/images/tick-round.png');
		    								   
		    							   }
		    							   
		    							   
		    							}else
		    								{
		    								$("#fullNamePD").removeAttr("readonly");
		    								$("#emailPD").removeAttr("readonly");
		    								$("#panPD").removeAttr("readonly");
		    								$("#addressPD").removeAttr("readonly");
		    								$("#pinCodePD").removeAttr("readonly");
		    								
		    								}
		    								
		    								$('#accTypeBD').val("Savings").prop('readonly', true);
		    								$('#accTypeBD').parent().addClass('valid');
		    				                $('.a_part_3').hide();
											$('.a_part_3-1').hide();
											$('.a_part_document').hide();
		    				                $('.a_part_4').show();
		    				              	$('#receiveOtp .p_reqotp button .fd_sdp_loder').hide();	
		    				          
		      			}
					else if(fdDedupeCustTypeNew=="NTB")
					{
						if(!(ntbfullName== "NA" || ntbfullName == "")) 
  		               {
							   $('#fullNamePDNew').val(ntbfullName); 
							   $('#fullNamePDNew').parent().addClass('valid');
							   
							  
							}
						
						
						if(!(ntbpin== "NA" || ntbpin == "")) 
  		               {
							   $('#pinCodePV').val(ntbpin); 
							   $('#pinCodePV').parent().addClass('valid');
							  
						}
		    			
						$('#mobileNO').val(ntbmobNumber).prop('readonly', true);
						$('.a_part_3').hide();   			              
		    			$('.a_part_3-1').show();
		    			$('#receiveOtp .p_reqotp button .fd_sdp_loder').hide();	
		    		}
		     }
		     
		     
		     
		     
		     /****** for showing fd and sdp page functionality For Fixed Deposit Short Lead Form end*****/
		     
		     
		     
		    	function fdCalculatorDetails()
		    	{
		    		
		    		var fdAmount = $("#fdAmount").val().trim();		
		    	    var fdTenor = $('input[name="tenor"]:checked').val();		
		    	    
		    	   
		    	    if(fdTenor==undefined)
		             {fdTenor=$("#fdCustomTenor").val().trim();}	    
		    	    var fdpayOut = $('input[name="payOut"]:checked').val();	  
		    	    var fdInterestRate = $("#FDInterestRate").text().replace('%', '').trim();
		    	    
		    	        var fdMaturityDate = $("#FDmaturityDate").text().trim();
		    	        var fdInterestAmt = $("#InterestFD").text().trim();
		    	        var fdmaturityAmnt = $("#FDmaturityAmnt").text().trim();
		    	        var totPayoutAmtHiddenVal=$("#hiddenPayOutTypeCheck").val().trim();
		    	    var fdRenewel ="No";
		    	    var renewelType = "NA";
		    	 
		    	   if( $('#fdRenew').prop('checked'))
		    		   { fdRenewel ="Yes";	
		    		     renewelType = $('input[name="princiAmount"]:checked').val();
		    		   }
		    	    
		        
		    	    var fullBankName=$('#fdbankName').find('option:selected').html();
		    	    if(fullBankName=="" || fullBankName ==null)
		 	    	{fullBankName = "NA"
		 	    		}
		    	    
		    	    var paymentOption =$('input[name="banking_det_1"]:checked').val();
		     	   if(paymentOption=="" || paymentOption ==null)
		 	    	{paymentOption = "NA"}
		     	   var bankName = $("#fdbankName").val().trim()
		     	   var ifscCode = $("#fdifscCode").val().trim()
		     	   var accountNumber = $("#fdaccountNumber").val().trim()
		    	     var maturityScheme ="NA";
		    	    var customerType = custTypeFD;
		    	    
		    		var custId=fdcNumber;
				    
				    if(custId==null || custId == undefined)
		    		{
				    	custId="NA";
				    	
		    		}
		    	    var data = {
		    	    	"investmentType":"FD",
		    	    	"fdMaturityDate":fdMaturityDate,        
		                "fdInterestAmnt":fdInterestAmt,
		                "fdmaturityAmnt":fdmaturityAmnt,
		                "customerType":customerType,        
		                "depositAmount":fdAmount,
		                "fdPayoutType":fdpayOut,
		                "fdRenewalType":renewelType,
		                "tenor":fdTenor,
		                "interestRate":fdInterestRate,         
		                "paymentOption":paymentOption,        
		                "bankName":bankName,
		                "ifscCode":ifscCode,
		                "accountNumber":accountNumber,
		                "sdpTotalPriAmnt":"NA",        
		                "sdptotalPayoutAmnt":"NA",
		                "sdpDepositNO":"NA",
		                "sdpDepositDate":"NA",
		                "maturityScheme":maturityScheme,
		                "fullBankName":fullBankName,
		                "fdcNumber":custId
		                
		        };     
		    	    var mobNo = $("#mobileNO").val().trim();
		    	    var custIdselected=$("#custCkycIDVD").val();
		    	    vizLayer("STEP5A",fdAmount,mobNo,customerType,custIdselected);
		    	    calculatorAjaxCall(data)
		    	}
		    	
		    	
		    	function sdpCalculatorDetails()
		    	{
		    		
		    		var sdpAmount = $("#sdpAmount").val().trim();
		    		var sdpDeposit = $("#sdpDeposit").val().trim();
		    	    var sdpTenor = $('input[name="sdptenor"]:checked').val();
		    		
		    	    if(sdpTenor==undefined)
		               {
		    	    	sdpTenor=$("#sdpCustomTenor").val().trim();
		    	    	
		               }
		    	    
		    	    var sdpDepositeDate = $('input[name="sdpDeposite"]:checked').val().trim();
		    	   
		    	    var sdpInterestRate = $("#sdpInterestRate").text().replace('%', '').trim();
		    	    var maturityScheme = $('.a_radiodeposit label input[name="maturitysdheme"]:checked').val().trim();
		    	    
		    	    if(maturityScheme=="Monthly Maturity"){
		    	    	maturityScheme = "Monthly Maturity";
		    	    }else{
		    	    	maturityScheme = "Single Maturity"; 
		    	    }
		    	    
		    	    
		    	    var sdpTotalPriAmt = $("#sdpTotPriAmt").text().trim();
		    	    var totPayoutAmt = $("#totPayAmt").text().trim();  
		    	    var fullBankName=$('#sdpbankName').find('option:selected').html();
		    	    if(fullBankName=="" || fullBankName ==null)
		 	    	{
		    	    	fullBankName = "NA"
		 	    		
		 	    	}
		    	    var paymentOption = $('input[name="banking_det_2"]:checked').val();
		    	    if(paymentOption=="" || paymentOption ==null)
			    	{paymentOption = "NA"}
		     	   var bankName = $("#sdpbankName").val().trim();
		     	   var ifscCode = $("#sdpifscCode").val().trim();
		     	   var accountNumber = $("#sdpaccountNumber").val().trim();
		    	
		    	var customerType =custTypeFD;
		    	var custId=fdcNumber;
			    
			    if(custId==null || custId == undefined)
	    		{
			    	custId="NA";
			    	
	    		}
		        var data = {          
		        		
		        		"investmentType":"SDP",
		        		
		        		"customerType":customerType,        
		                "depositAmount":sdpAmount,
		                "fdPayoutType":"NA",
		                "tenor":sdpTenor,
		                "interestRate":sdpInterestRate,
		                "fdRenewalType":"NA",
		                "paymentOption":paymentOption,        
		                "bankName":bankName,
		                "ifscCode":ifscCode, 
		                "accountNumber":accountNumber,         
		                "sdpDepositNO":sdpDeposit,
		                "sdpDepositDate":sdpDepositeDate,
		                "sdpTotalPriAmnt":sdpTotalPriAmt,        
		                "sdptotalPayoutAmnt":totPayoutAmt,
		                "fdMaturityDate":"NA",        
		                "fdInterestAmnt":"NA",
		                "fdmaturityAmnt":"NA",
		                "maturityScheme":maturityScheme,
		                "fullBankName":fullBankName,
		                "fdcNumber":custId
		                
		        };
		        var mobNo = $("#mobileNO").val().trim();
	    	    var custIdselected=$("#custCkycIDVD").val();
	    	    vizLayer("STEP5B",sdpAmount,mobNo,customerType,custIdselected);
		    	    calculatorAjaxCall(data);
		    	}
		
		
		    	function calculatorAjaxCall(data){
		    			         $.ajax({ 
		    			                                      
		    			             url: "/fixed-deposit-application-form/calculatorRequest", 
		    			             type: "POST",
		    			             data:JSON.stringify(data),
		    			             contentType: 'application/json',
		    			             error: function(response)
			 		    	 			{
		    			            	 newRelicAlert("Technical Error Page in calculatorRequest | Fail Ajax call","'/fixed-deposit-application-form/calculatorRequest'",response.status,fdcNumber);
		    			            	 $('.maxcontainer div:visible').hide();
		    			                 $('.ErrorPage').show();  
			 		    	             }  ,
		    			             success: function(response) {            
		    			            	
		    			            	 var response = JSON.parse(response);          
		    			            	 if((response.validationStatus == "success")){
		    			            		 $('.a_blackoverlay').toggle();
		    			                     $('.a_termsAndConPart').toggleClass('a_termsAndConPartShow');
		    			                     $('#fdNominee button .fd_sdp_loder').hide();
		    			                     $('#sdpnomeeni button .fd_sdp_loder').hide();
		    			                     $('.a_termsAndConPart').animate({
			 					                    'scrollTop' : $("#fdPayment").offset().top
			 					                })
			 					               $('#fdPayment').addClass('floatCssButton');    
		    			            	 }else if(response.validationStatus == "fail"){
		    			           
		    			            		 $('#fdNominee button .fd_sdp_loder').hide();
		    			                     $('#sdpnomeeni button .fd_sdp_loder').hide();
		    			                     isFDPayActionInProgress= 'No';
		    			                     isSDPPayActionInProgress= 'No';
		        			            	 var failMsg =	response.validationMsg ;
		    			            			
		    			            			if(failMsg.hasOwnProperty('FieldvalidationMsg'))
		    			            					{
		    			            				var FieldfailMsg =	failMsg.FieldvalidationMsg ;
		    			            				  $.each(FieldfailMsg, function(key, value)
		    			            						  { 
		     			                		   $("input[name="+key+"]").siblings(".errormsg").text(value).show(); 
		     			               		 });
		    			            					}
		    			            		
		    			            		newRelicAlert("Fail in calculatorRequest","'/fixed-deposit-application-form/calculatorRequest'"," ",fdcNumber);
		    			            	    }
		    			            	 else if(response.apiStatus== "sessionExpired")
		    			             		{
		    			            		 newRelicAlert("session Expired in calculatorRequest","'/fixed-deposit-application-form/calculatorRequest'",response,fdcNumber);
			    			             		$('.maxcontainer div:visible').hide();
			    			             		$('.SessionErrorPage').show(); 
		    			             		}
		    			            	 else
		    			             		{
		    			            		 newRelicAlert("Technical Error Page in calculatorRequest","'/fixed-deposit-application-form/calculatorRequest'"," ",fdcNumber);
		    			            		 $('.maxcontainer div:visible').hide();
		    				                 $('.ErrorPage').show(); 
		    			             		}
		    			            	 
		    			        					
		    			            	 }   
		    			      });
		    			
		    			}
		    	
		    /*****************************************FD and SDP Calculator and Nominee  functionality End****************************************************/
		    	
		    /*****************************************Payment functionality Start****************************************************/
		
		    	
		    	function fdPaymentRequest()
		    	{
		    		var mobNo = $("#mobileNO").val().trim();
		    		var custIdselected=$("#custCkycIDVD").val();
			    	 vizLayer("STEP6","",mobNo,"ETB",custIdselected);
			    	    
		    	    var directorOrPromoter = $('input[name="dirOrProm"]:checked').val()=="on"?"YES":"NO";
		    	    var relativeOfDirector = $('input[name="relativeOfD"]:checked').val()=="on"?"YES":"NO";
		    	    var shareholder = $('input[name="shareholder"]:checked').val()=="on"?"YES":"NO";
		    	    var foreignCitizen = $('input[name="foreignCitizen"]:checked').val()=="on"?"YES":"NO";
		    	    var foreignTaxPayer = $('input[name="foreignTax"]:checked').val()=="on"?"YES":"NO";
		    	    var custId=fdcNumber;
				    
				    if(custId==null || custId == undefined)
		    		{
				    	custId="NA";
				    	
		    		}
		    		 var data = {          
		    		    		
		    		    		"directorOrPromoter":directorOrPromoter, 
		    		    		"relativeOfDirector":relativeOfDirector,        
		    		            "shareholder":shareholder,
		    		            "foreignCitizen":foreignCitizen,
		    		            "foreignTaxPayer":foreignTaxPayer,		           
		    		             "mobileNumber":mobNo,
		    		             "fdcNumber":custId
		    		            
		    		    };
		    		
		    		
		    		
		    		  $.ajax({ 
		    	                                    
		    	             url: "/fixed-deposit-application-form/paymentRequest", 
		    	             type: "POST",
		    	             data:JSON.stringify(data),
		    	            // async: false,
		    	             contentType: 'application/json',
		    	             error: function(response)
		    	 			{
		    	            	 $('#fdPaymentButton').hide(); 
		    	            	 newRelicAlert("Technical Error Page in paymentRequest | Fail Ajax call","'/fixed-deposit-application-form/paymentRequest'",response.status,fdcNumber);
		    	            	 $('.maxcontainer div:visible').hide();
				                 $('.ErrorPage').show();  
		    	             }  ,
		    	             success: function(response) {            
		    	            	 var response = JSON.parse(response);          
		    	            	 if((response.status == "success")){
		    	            		 if((response.checksum != null)){
		    	            			 
		    	            			 	dataLayerCall("form_pay_button","Pay Via Netbanking","True");
		    	            			 	newRelicAlert("Redirection on paymentRequest","'/fixed-deposit-application-form/paymentRequest'"," ",fdcNumber);
		    		             			$("#fdmsg").val(response.checksum);
		    		             		    $("#fdbilldesk").submit();
		    	            		 }else if(response.checksum== "noCalculatorStageFound")
		 			             		{
		    	            			 newRelicAlert("Technical Error Page in paymentRequest | noChecksumStageFound","'/fixed-deposit-application-form/paymentRequest'"," ",fdcNumber);
		    	            			 $('.maxcontainer div:visible').hide();
		    			                 $('.ErrorPage').show(); 
		 			             		}
		    	            		 
		    	            		
		    	            	 }
		    	            	 else if(response.apiStatus== "sessionExpired")
	 			             		{
		    	            		 newRelicAlert("session Expired in paymentRequest","'/fixed-deposit-application-form/paymentRequest'",response,fdcNumber);
	 			             		 $('.maxcontainer div:visible').hide();
	 			             		 $('.SessionErrorPage').show();
	 			             		}
		    	            	 else{
		    	            		 $('#fdPaymentButton').hide(); 
		    	            		 isPayActionInProgress = 'No';
		    	            		 newRelicAlert("session Expired in paymentRequest","'/fixed-deposit-application-form/paymentRequest'"," ",fdcNumber);
		        	         		dataLayerCall("form_pay_button","Pay Via Netbanking","False");
		        	         		$('.maxcontainer div:visible').hide();
					                 $('.ErrorPage').show();
		    	            		
		    	            	    }		
		    	        					
		    	            	 }   
		    	      });	
		    		
		    		
		    		
		    		
		    		
		    		
		    	}
		    /*****************************************Payment functionality End****************************************************/
		    	
		    	
		    /********************************************Partners name functionality start*****************************************************/
		
		
		    	$("#pCode").blur(function(){
	    			
	    			
		    		
	    			var partnerCode = $("#pCode").val().trim();      
	    			
	    			if(partnerCode != ""){
	    				
	    				  var data =
	    					{ 
	    					"partnerCode":partnerCode,
	    					}; 
	    	
	    	
	    	$.ajax({  
	    	 		     
	    	 		       url: "/fixed-deposit-application-form/getPartnerDetail",
	    	 		       type: "POST",
	    	 		       data:JSON.stringify(data),  
	    	 		       contentType: 'application/json',
	    	 		      error: function(response)
	    		 			{
	    	 		    	  newRelicAlert("Technical Error Page in getPartnerDetail | Fail Ajax call","'/fixed-deposit-application-form/getPartnerDetail'",response.status," ");
	    	 		    	  console.log("inside error function") ;
	    		             }  ,
	    	           	   success: function(response)
	    					{
	    	           	
	    	           		   
	          	        	    				             	
	          	       response = JSON.parse(response); 
	          	         
	          	       checkPCodeglobal=response.checkPCode;
	          	     console.log("checkPCodeglobal after success"+checkPCodeglobal);
	    	 		          	if(response.status=="success")
	    						{
	    	 		               var partnerName = response.partnerName;
	    	 		              $('#pName').val(partnerName);
		    	 		          $('#pName').siblings('label').addClass('active');
		    	 		       	  $('#pName').parent('.inputMainBlock').addClass('valid');
		    	 		       	  $(".beingAssistedForm #pName").siblings('.errormsg').hide();
	    	 		          
	    	 		          		}
	    	 		          	else if(response.status == "fail"){ 
	    	 		         		
	    	 		          		/*alert("Please enter valid Partner code or close this section");*/
	    	 		          		$('#pName').val("");
	    	 		          		$("#pName").prop('readonly', true);
	    	 		          		$(".beingAssistedForm #pCode").siblings('.errormsg').text("Please enter valid employee/partner code").show();
	    	 		          		$(".beingAssistedForm #pName").siblings('.errormsg').text("Please enter valid employee/partner code").show();
	
	    	 		         	         
	    	 		                 	}
	    	 		          	 
	    	 		          else
	    	 		          		{
	    	 		        	 newRelicAlert("Technical Error Page in getPartnerDetail","'/fixed-deposit-application-form/getPartnerDetail'","Fail"," ");
	    	 		        	 $('.maxcontainer div:visible').hide();
				                 $('.ErrorPage').show(); 
	    	 		          		}
	    	          	 },
	    	             
	    	         
	    	       	});
	    			  /*}*/
	    				
	    			}else{
	    				$(".beingAssistedForm #pName").siblings('.errormsg').hide();
	    			}
	    		 	     
	    				}).blur();
		    	
		    	
		
		    /********************************************Partners name functionality end*****************************************************/
		    	
		
		
		    	/********************************************decryption function start****************************************************/


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

		        function generateSalt(length){
		      		var chars = "0123456789ABCDEFabcdef";
		      		var salt = '';
		      		for (var i = 0; i < length; i++) {
		      			var appendchar = chars.charAt(Math.floor(Math.random() * (chars.length)));
		      			//console.log("::::: "+ i + " :::::: " + appendchar)
		      			salt = salt + appendchar;
		      		}
		      		return salt;
		      	}    

		      	function encryptdata(s,data) {
		      		//var s = CryptoJS.lib.WordArray.random(128/8);
		      		//console.log("Salt ::::::: " + s);
		      		return iv = "F27D5C9927726BCEFE7510B1BDD3D137", 
		      		passphrase = "abcdef",
		      		salt = s,
		      		new AesUtil(128, 1000).encrypt(salt, iv, passphrase, data)     
		      	}

		      	 function decryptdataFunction(e,s) {
		      		return iv = "F27D5C9927726BCEFE7510B1BDD3D137", 
		      		passphrase = "abcdef",
		      		salt = s,
		      		new AesUtil(128, 1000).decrypt(salt, iv, passphrase, e)
		      	}

		        /********************************************decryption function end****************************************************/

		
		    /********************************************resend otp call***************************************************/
		
		    $('body').on('click', '.blueClick', function() {
		        $(this).removeClass('blueClick');
		        $('.j_resendDes').hide(); 
		        $('.a_resendTime').show();
		        $("#receiveOtp .validBtn").prop("disabled", true);
		     //   $('.a_blueBtnPart button .fd_sdp_loder').show();
		        if(!resumeJourneyFlag && !$('#preapp').prop('checked'))
	    		{
		        	fdResendOTP();
				}else
	    			{
					resumeResendOTP();
	    			}
		        count3minut();
		        $("#receiveOtp .validBtn").prop("disabled", true);
		    });
		
		    /********************************************resend otp call end***************************************************/
		
		    /********************************************counter function***************************************************/
		
		   /* var interval;
		    function count3minut() {
		        var timer2 = "00:60";
		        interval = setInterval(function () {
		            
		            var timer = timer2.split(':');
		            
		            var minutes = parseInt(timer[0], 10);
		            var seconds = parseInt(timer[1], 10);
		            
		            --seconds;
		            minutes = (seconds < 0) ? --minutes : minutes;
		            seconds = (seconds < 0) ? 59 : seconds;
		            seconds = (seconds < 10) ? '0' + seconds : seconds;
		            
		            $('.otpCounter > i').html('in 0' + minutes + ':' + seconds);
		            if (minutes < 0) clearInterval(interval);
		            if ((seconds <= 0) && (minutes <= 0)) clearInterval(interval);
		            timer2 = minutes + ':' + seconds;
		            
		            if ((seconds <= 0) && (minutes <= 0)) {
		                $('.otpCounter > a').addClass('resendhighlight');
		            }

		        }, 1000);

		    }*/
		
		    /********************************************counter function end***************************************************/
		    /********************************************PAGE view function Start***************************************************/
		    
		    function trackPageviewScript(para)
			{
				
				window._uxa = window._uxa || [];
				window._uxa.push(['trackPageview', window.location.pathname+window.location.hash.replace('#','?__') + '?cs-form-step='+para+'']);

			}
		    /********************************************PAGE view function end***************************************************/
		    /********************************************25/09/ nominee section ****************************************************/

			$('.addNomineeBtn .a_swichTgl label input').change(function()
			{
			        if($(this).is(':checked'))
			        {
			            $('#Banking_Details .a_radiodeposit_2').slideDown(200);
			            $('#Banking_Details .a_radiodeposit_2 .a_ReInput input').removeClass('nomandetory');
			            $('#Banking_Details .a_radiodeposit_2 .a_ReInput select').removeClass('nomandetory');
			            
			            $("#nomineeCheck").attr("checked", true);
			            $(this).parents('.a_ourParner').find('.a_ReInput').slideDown(200); 
			            $(this).parents('.a_ourParner').find('.a_ReInput input').removeClass('nomandetory');
			        }else
			        {
			            $('#Banking_Details .a_radiodeposit_2').slideUp(200);
			            $('#Banking_Details .a_radiodeposit_2 .a_ReInput input').addClass('nomandetory');
			            $('#Banking_Details .a_radiodeposit_2 .a_ReInput select').addClass('nomandetory');
			            $('#Banking_Details .a_radiodeposit_2 .a_ReInput .errormsg').hide();
			            
			            $("#nomineeCheck").attr("checked", false);
			            $(this).parents('.a_ourParner').find('.a_ReInput').slideUp(200);
			            $(this).parents('.a_ourParner').find('.a_ReInput input').addClass('nomandetory');
			            $(this).parents('.a_ourParner').find('.errormsg').hide();
			        }
			        $('.get18age').change();
			        $('.a_primartAppli .a_CKBoxTgl label input').change();
			        
			    });
			    
			    $('.a_ourParner .a_swichTgl label input').change(function(){
			        if($(this).is(':checked')){
			            $(this).parents('.a_ourParner').find('.a_ReInput').slideDown(200); 
			            $(this).parents('.a_ourParner').find('.a_ReInput input').removeClass('nomandetory');
			        }else{
			            $(this).parents('.a_ourParner').find('.a_ReInput').slideUp(200);
			            $(this).parents('.a_ourParner').find('.a_ReInput input').addClass('nomandetory');
			            $(this).parents('.a_ourParner').find('.errormsg').hide();
			        }
			    });
			    
			    $('.a_ourCommAdd .a_swichTgl label input').change(function(){
			        if($(this).is(':checked')){
			        	$(this).parents('.a_ourCommAdd').find('.a_ReInput').slideUp(200);
			            $(this).parents('.a_ourCommAdd').find('.a_ReInput input').addClass('nomandetory');
			            $(this).parents('.a_ourCommAdd').find('.errormsg').hide();
			            $(this).val("No");
			        }else{
			            $(this).parents('.a_ourCommAdd').find('.a_ReInput').slideDown(200); 
			            $(this).parents('.a_ourCommAdd').find('.a_ReInput input').removeClass('nomandetory');
			            $(this).val("Yes");
			        }
			    });
				
			    
			    $('.a_part_5 .a_ReInput2 label a').click(function(){
			        $('.a_blackoverlay').toggle();
			        $('.a_termsAndConPart_2').toggleClass('a_termsAndConPart_2Show');
			        $('.tncSafaricheck').toggleClass('Adreltive');
			    });
				
				$('.a_part_6 .a_ReInput2 label a').click(function(){
			        $('.a_blackoverlay').toggle();
			        $('.a_termsAndConPart_2-1').toggleClass('a_termsAndConPart_2-1Show');
			        $('.tncSafaricheck').toggleClass('Adreltive');
			    });
				
				
				$('.a_blackoverlay').click(function(){
			        
			        $('.a_blackoverlay').hide();
			        $('.a_termsAndConPart').removeClass('a_termsAndConPartShow');
			        $('.a_termsAndConPart_2').removeClass('a_termsAndConPart_2Show');
			        $('.a_termsAndConPart_2-1').removeClass('a_termsAndConPart_2-1Show');
			        $('.a_termsAndConPart_3').removeClass('a_termsAndConPart_3Show');
			        $('.a_termsAndConPart_4').removeClass('a_termsAndConPart_4Show');
			        $('.a_termsAndConPart_2-2').removeClass('a_termsAndConPart_2-2Show');
			        $('.tncSafaricheck').removeClass('Adreltive');
			        
			        isFDPayActionInProgress= 'No';
                    isSDPPayActionInProgress= 'No';
                    $('#fdPayment').removeClass('floatCssButton');
			    });   
			    
			   
			    
			    $('.a_part_5 .a_investmentTbl h2 a').click(function(){
			        $('.a_blackoverlay').toggle();
			        $('.a_termsAndConPart_2').toggleClass('a_termsAndConPart_2Show');
			        $('.tncSafaricheck').toggleClass('Adreltive');
			    });
				$('.a_part_6 .a_investmentTbl h2 a').click(function(){
			        $('.a_blackoverlay').toggle();
			        $('.a_termsAndConPart_2-1').toggleClass('a_termsAndConPart_2-1Show');
			        $('.tncSafaricheck').toggleClass('Adreltive');
			    });
			
			
			
				$('.get18age').change(function()
						{
					
					var dateOne = $("#nomineedd").val().trim();
		        	var monthOne = $("#nomineemm").val().trim();
		        	var yearOne = $("#nomineeyy").val().trim();
					var today = new Date(); 
						 var birthDate = new Date(yearOne+'/'+monthOne+'/'+dateOne); 
					var age = today.getFullYear() - birthDate.getFullYear();
					var m = today.getMonth() - birthDate.getMonth();
					if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
						age--;
					} 
					if(age < 18)
					{ 
						$(this).parents('#Banking_Details').find('.a_gardianDet').slideDown(200); 
						$(this).parents('#Banking_Details').find('.a_summaryView').show(); 
						$(this).parents('#Banking_Details').find('.a_investmentTbl ul').slideUp(200); 
						
						$(this).parents('#Banking_Details').find('.a_gardianDet').find('input,select').removeClass('nomandetory'); 
						$(this).parents('#Banking_Details').find('.a_investmentTbl > p').hide(); 
						$(this).parents('#Banking_Details').find('.a_investmentTbl h2').addClass('hideIntrates');
						
						if(!($('.addNomineeBtn .a_swichTgl label input').is(':checked'))){
							$('.a_gardianDet .a_ReInput input').addClass('nomandetory');
							   $('.a_gardianDet .a_ReInput select').addClass('nomandetory');
						}
						
					   }else{
						   $(this).parents('#Banking_Details').find('.a_gardianDet').slideUp(200); 
						   $(this).parents('#Banking_Details').find('.a_gardianDet').find('input,select').addClass('nomandetory'); 
						   $(this).parents('#Banking_Details').find('.a_gardianDet').find('input,select').siblings('.errormsg').hide();
					   }
				});
				
			    $('.a_primartAppli .a_CKBoxTgl label input').change(function()
			    		{
			        if($(this).is(':checked')){
			            $('.primartAdd').slideUp(200);
			            $('.primartAdd').children('input').addClass('nomandetory');
			            $('.primartAdd').children('input').siblings('.errormsg').hide();
			            $('#nomineeAddrsCheck').val("No");
			        }else{
			            $('.primartAdd').slideDown(200);
			            $('.primartAdd').children('input').removeClass('nomandetory');
			            $('#nomineeAddrsCheck').val("Yes");
			        }
			    });
			    
			    $('#fdbankName').change(function()
			    	    {
			    	
			    	
			    	var className=$(this).find('option:selected').attr('class');
			    	
			    	var sliderAmunt =	$('.sliderAmunt').val();
				    sliderAmunt = sliderAmunt.replace(/\,/g,'');
				    sliderAmunt = Number(sliderAmunt);
				   
				    $(this).parents('.bnkMethod').siblings().slideUp(200);
				   
				    var bankSelected=$(this).find('option:selected').html();
				    	
				   
				    
				    if(className=="bothBankOption" && sliderAmunt <= 100000)
				    	{
				    		$('#fdupiCheck').show();
				    		$('#fdNetBankCheck').show();
				    		$('#fdNetBankCheck input').prop('checked', true).change();
				    		$(this).parents('.a_ReInput').siblings('.bnkMethod').show();
				    		 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
				    		 if(bankSelected==bankName)
					    	 {	 
					         $('#fdaccountNumber').val(bankAccNumber);
					    	 $('#fdifscCode').val(bankifscCode);
					    	 
	                          }
				    	}
				    else if(className=="bothBankOption" && sliderAmunt >100000)
			    	{
			    	     $('#fdNetBankCheck').show();
			    	     $('#fdupiCheck').hide();
			    	     $('#fdNetBankCheck input').prop('checked', true).change();
			    		 if(bankSelected==bankName)
				    	 {	 
				         $('#fdaccountNumber').val(bankAccNumber);
				    	 $('#fdifscCode').val(bankifscCode);
				    	 
                         }
			    		 $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
			    		 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
			    	}
				     else if(className=="netBankShow" && sliderAmunt>=100001)
				    {
				    		$('#fdNetBankCheck').show();
				    	    $('#fdupiCheck').hide();
				    	    $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
				    	 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
				    	 $('#fdNetBankCheck input').prop('checked', true).change();
				    	 if(bankSelected==bankName)
				    	 {	 
				         $('#fdaccountNumber').val(bankAccNumber);
				    	 $('#fdifscCode').val(bankifscCode);
				    	 }
				    }
				     else if(className=="netBankShow" && sliderAmunt<=100000)
				     {
				     		$('#fdNetBankCheck').show();
				     	     $('#fdupiCheck').hide();
				     	    $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
				     	 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
				     	 $('#fdNetBankCheck input').prop('checked', true).change();
				     	if(bankSelected==bankName)
				    	 {	 
				         $('#fdaccountNumber').val(bankAccNumber);
				    	 $('#fdifscCode').val(bankifscCode);
				    	}
				     }
				    else if(className=="upihide" && sliderAmunt>=100001)
				    	{
				    	  $(this).parents('.a_ReInput').siblings('.a_opplsLink').slideDown(200);
				    	  $(this).parents('.a_ReInput').siblings('.bnkMethod').hide();
				    	  $("#fdNominee .a_radiodeposit_2").hide();
				    	  $("#fdaccountNumber").val("");
				    	  $("#fdifscCode").val("");
				    	
				    	  $('input[name="banking_det_1"]').attr('checked',false);
				    	}
				    else if(className=="upihide" && sliderAmunt<=100000)
				    	{
				    	
				    		$('#fdupiCheck').show();
				    		$('#fdNetBankCheck').hide();
				    		 $('#fdupiCheck input').prop('checked', true).change();
				    		 $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
				    		 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
				    	}
				    else if(className=="noBankSelected")
				    	{
				    	 
				    	 $("#fdNominee .a_radiodeposit_2 input").val("");
				    	$(this).parents('.a_ReInput').siblings('.bnkMethod').show();
				    	$("#fdNominee .a_radiodeposit_2").show();
				    	 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
				    	 $('#fdNetBankCheck input').prop('checked', true).change();
				    	}
				    
			    	    });
			    /****************SDP BANK CHANGE************/
			    $('#sdpbankName').change(function()
			    	    {
			    	var className=$(this).find('option:selected').attr('class');
			    	console.log(className);
			    	 var sliderAmunt =	$('.sliderAmunt_2').val();
			    	 sliderAmunt = sliderAmunt.replace(/\,/g,'');
			    	 sliderAmunt = Number(sliderAmunt);
			    	 console.log(sliderAmunt);
			    	 $(this).parents('.bnkMethod').siblings().slideUp(200);
			    	  var bankSelected=$(this).find('option:selected').html();
			    	  
			    	if(className=="bothBankOption" && sliderAmunt <= 100000)
			    	{
			    	
			    		$('#SdpupiCheck').show();
			    		$('#SdpNetBankCheck').show();
			    		  $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
			    		 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
			    		 $('#SdpNetBankCheck input').prop('checked', true).change(); 
			    		 if(bankSelected==bankName)
				    	 {	 
			    			 $('#sdpaccountNumber').val(bankAccNumber);
					    	  $('#sdpifscCode').val(bankifscCode);
					    	 
					    }
			    	}
			    	 else if(className=="bothBankOption" && sliderAmunt >=100001)
			     	{
			    		 $('#SdpupiCheck').hide();
			     		$('#SdpNetBankCheck').show();
			     		  $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
			     		 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
			     		$('#SdpNetBankCheck input').prop('checked', true).change();
			     		if(bankSelected==bankName)
				    	 {	 
			    			 $('#sdpaccountNumber').val(bankAccNumber);
					    	  $('#sdpifscCode').val(bankifscCode);
					    	   }
			     	}
			 	 else if(className=="netBankShow" && sliderAmunt>=100001)
			    {
			    		$('#SdpNetBankCheck').show();
			    	    $('#SdpupiCheck').hide();
			    	   $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
			    	 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
			    	 $('#SdpNetBankCheck input').prop('checked', true).change();
			    	 if(bankSelected==bankName)
			    	 {	 
		    			 $('#sdpaccountNumber').val(bankAccNumber);
				    	  $('#sdpifscCode').val(bankifscCode);
				    	   }
			    }
			 	 else if(className=="netBankShow" && sliderAmunt<=100000)
			     {
			     		$('#SdpNetBankCheck').show();
			     	    $('#SdpupiCheck').hide();
			     	   $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
			     	 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
			     	$('#SdpNetBankCheck input').prop('checked', true).change(); 
			     	if(bankSelected==bankName)
			    	 {	 
		    			 $('#sdpaccountNumber').val(bankAccNumber);
				    	  $('#sdpifscCode').val(bankifscCode);
				    	  }
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
			    else if(className=="noBankSelected")
				{
			    	
			  	 $("#sdpnomeeni .a_radiodeposit_2 input").val("");
			     $(this).parents('.a_ReInput').siblings('.bnkMethod').show();
			     $("#fdNominee .a_radiodeposit_2").show();
			  	 $(this).parents('.a_ReInput').siblings('.a_opplsLink').hide();
			  	$('#SdpNetBankCheck input').prop('checked', true).change();
				}
			    	
			    	
			    	    });
			    
			    
			    $('.a_ReInput label a.a_viewPament').click(function(){
			        $('.a_blackoverlay').toggle();
			        $('.a_termsAndConPart_4').toggleClass('a_termsAndConPart_4Show');
			    });
			        $('.a_termsAndConPart_4 .a_closePop').click(function(){
			            $('.a_blackoverlay').hide();
			            $('.a_termsAndConPart_4').removeClass('a_termsAndConPart_4Show');
			            $('#fdPayment').removeClass('floatCssButton');
			        });
			        $('.a_leftSide .a_opplsLink a').click(function(){
			            $(this).parents('.a_part_5').hide();
			            $(this).parents('.a_part_5').siblings('.a_part_6').show();
			            $("#sdpAmount").val("5000").change();	
	                 	 $("#sdpbankName").siblings(".errormsg").hide();
	                 	$('#hiddenPayOutTypeCheck').val('');
			            $('.otpTncPdf').attr('href','/fixed-deposit-application-form/resources/pdf/SDP-TCs.pdf');
			        });

			    $('.a_rightSide .a_opplsLink a').click(function(){
			            $('.a_blackoverlay').toggle();
			            $('.a_termsAndConPart_4').toggleClass('a_termsAndConPart_4Show');
			        });   
			        $('input[name="banking_det_1"],input[name="banking_det_2"]').change(function(){
			            $(this).parents('.a_radiodeposit').siblings('.errormsg').hide();
			            $(this).parents('.bnkMethod').siblings().slideDown(200);
			            $(".bnkMethod").siblings(".a_opplsLink").css('display','none');
			            
			           
			            
			            if($(this).attr("name")=="banking_det_1"){$("#fdNominee .a_radiodeposit_2").show();}else{ 
			            $("#sdpnomeeni .a_radiodeposit_2").show();};
				    	if($(this).attr('value')=='UPI')
				    		{
				    		
				    		$('#fdPayment').text("PAY VIA UPI");
				    		$('#fdPayment').append('<div class="fd_sdp_loder" id="fdPaymentButton"></div>'); 
				    		}
				    	else{
				    		
				    		$('#fdPayment').text("PAY VIA NETBANKING");
				    		$('#fdPayment').append('<div class="fd_sdp_loder" id="fdPaymentButton"></div>'); 
				    	}
			        });
			        
			        
			 /********************************************25/09/ nominee section ****************************************************/
			        /********************************************26/11/ 2020 ****************************************************/
			        $('#panform input,select').on('blur change', function () {
			            var cerror = 0;
			            $("#panform .errormsg").each(function (i) {
			                if ($(this).css("display") == "block") {
			                    cerror++
			                }
			            });
			            if (cerror == 0) {
			                $('.allfieldvali').hide();
			            } else {
			                $('.allfieldvali').show();
			            }
			        });


			        if ($(window).width() < 768) {
			            $('.tipstool').click(function () {
			                $(this).children('.custooltip').addClass('showtip');
			                var th = $(this);
			                setTimeout(function () {
			                    th.children('.custooltip').removeClass('showtip');
			                }, 5000);
			            });
			        } else {
			            $('.tipstool').hover(function () {
			                $(this).children('.custooltip').toggleClass('showtip');
			            });
			        }
			        
			        
			        /********************************************26/11/2020****************************************************/
			        
			        /***************************************************VIZ layer call**************************************/
			        
			        function vizLayer(vizoffer,vizofferamount,vizofferId,vizofferflag,vizoffercustomerId)
			        {
			        
			       	 window.vizLayer = [];
			        	
			        	try {

			        		window.vizLayer.push({  

			        		offer_title: "FD",
			        		
			        		step_No: vizoffer,

			        		offer_amount: vizofferamount,

			        		offerId: vizofferId,

			        		api_flag: vizofferflag,

			        		customerId: vizoffercustomerId

			        		}), pixel.parse("ajax call")

			        		} catch (e) {

			        		console.log(e.message)

			        		}

			        		
			        }
			        
			        /***************************************************VIZ layer call**************************************/
			        /***************************************************Global site tag event Start**************************************/
			      function googleEventGenerateOTP(){/*
			        	
			        		  gtag('event', 'conversion', {
			        		      'send_to': 'AW-802197272/MgcpCOSct-4BEJiewv4C',
			        		      'event_callback': "GET_OTP"
			        		  });
			        		  
			        	   
			        */}
			        
			        function googleEventSubmitOTP(){/*
			        	

			        	
			        	  gtag('event', 'conversion', {
			        	      'send_to': 'AW-802197272/X16lCLrusYYBEJiewv4C',
			        	      'event_callback': "VALIDATE_OTP"
			        	  });
			        
			        	 
			        	
			        */}
			        
			        
			        function googleEventUserDetails(){/*
			        	
			        	
			        	  gtag('event', 'conversion', {
			        	      'send_to': 'AW-802197272/6eiOCO3Ol-4BEJiewv4C',
			        	      'event_callback': "USERDEATILS"
			        	  });
			        	 
			        	 
			        	
			        */}
			        function googleEventBankDetails(){/*
			        	
			        	
			        	  gtag('event', 'conversion', {
			        	      'send_to': 'AW-802197272/gf6LCKPSl-4BEJiewv4C',
			        	      'event_callback': "BANKDETAILS"
			        	  });
			        	  console.log("personal Details googleEvent done");
			        
			        	
			        */}
			        function googleEventPaymentProceed(){/*
			        
			        	  gtag('event', 'conversion', {
			        	      'send_to': 'AW-802197272/2YlXCK7up-4BEJiewv4C',
			        	      'event_callback': "PAYMENTPROCEED"
			        	  });
			        	
			        	 
			      
			        */}
			        function googleEventPaymentCheckout(){/*
			        	
			        	
			        	  gtag('event', 'conversion', {
			        	      'send_to': 'AW-802197272/QNS6CI7Kt-4BEJiewv4C',
			        	      'event_callback': "PAYMENTCHECKOUT"
			        	  });
			        	 
			      
			        */}

			        
			        function googleEventBankDetailsPaymentMode(){/*
			        	
			        	
			        	 gtag('event', 'conversion', {
			        	      'send_to': 'AW-802197272/gf6LCKPSl-4BEJiewv4C',
			        	      'event_callback': "BANKDETAILSCHOOSEPAYMENTMODE"
			        	  });
			        	 
			      
			        */}
			          
			        /***************************************************Global site tag event End**************************************/
			        /***************************************************FB pixel tag start**************************************/
				      function fbPixeltagGetOTP()
				      {
				    	 
				    	  !function(f,b,e,v,n,t,s)
				    	  {if(f.fbq)return;n=f.fbq=function(){n.callMethod?
				    	  n.callMethod.apply(n,arguments):n.queue.push(arguments)};
				    	  if(!f._fbq)f._fbq=n;n.push=n;n.loaded=!0;n.version='2.0';
				    	  n.queue=[];t=b.createElement(e);t.async=!0;
				    	  t.src=v;s=b.getElementsByTagName(e)[0];
				    	  s.parentNode.insertBefore(t,s)}(window, document,'script',
				    	  'https://connect.facebook.net/en_US/fbevents.js');
				    	  fbq('init', '188490321873243');
				    	  fbq('track', 'GENERATE_OTP');
				    	  
				      }
				      
				      function fbPixeltagValidateOTP()
				      {
				    	  !function(f,b,e,v,n,t,s)
				    	  {if(f.fbq)return;n=f.fbq=function(){n.callMethod?
				    	  n.callMethod.apply(n,arguments):n.queue.push(arguments)};
				    	  if(!f._fbq)f._fbq=n;n.push=n;n.loaded=!0;n.version='2.0';
				    	  n.queue=[];t=b.createElement(e);t.async=!0;
				    	  t.src=v;s=b.getElementsByTagName(e)[0];
				    	  s.parentNode.insertBefore(t,s)}(window, document,'script',
				    	  'https://connect.facebook.net/en_US/fbevents.js');
				    	  fbq('init', '188490321873243');
				    	  fbq('track', 'SUBMIT_OTP');
				    	  
				      }
				      
				      function fbPixeltagCKYCStep()
				      {
				    	  !function(f,b,e,v,n,t,s)
				    	  {if(f.fbq)return;n=f.fbq=function(){n.callMethod?
				    	  n.callMethod.apply(n,arguments):n.queue.push(arguments)};
				    	  if(!f._fbq)f._fbq=n;n.push=n;n.loaded=!0;n.version='2.0';
				    	  n.queue=[];t=b.createElement(e);t.async=!0;
				    	  t.src=v;s=b.getElementsByTagName(e)[0];
				    	  s.parentNode.insertBefore(t,s)}(window, document,'script',
				    	  'https://connect.facebook.net/en_US/fbevents.js');
				    	  fbq('init', '188490321873243');
				    	  fbq('track', 'USER_DETAILS');
				    	  
				      }
				      
				      function fbPixeltagPersonalDetails()
				      {
				    	  !function(f,b,e,v,n,t,s)
				    	  {if(f.fbq)return;n=f.fbq=function(){n.callMethod?
				    	  n.callMethod.apply(n,arguments):n.queue.push(arguments)};
				    	  if(!f._fbq)f._fbq=n;n.push=n;n.loaded=!0;n.version='2.0';
				    	  n.queue=[];t=b.createElement(e);t.async=!0;
				    	  t.src=v;s=b.getElementsByTagName(e)[0];
				    	  s.parentNode.insertBefore(t,s)}(window, document,'script',
				    	  'https://connect.facebook.net/en_US/fbevents.js');
				    	  fbq('init', '188490321873243');
				    	  fbq('track', 'BANK_DETAILS_CONFIRM');
				    	  console.log("personal Details fb pixel tag event done");
				      }
				      
				      function fbPixeltagBankDetailsProeed()
				      {
				    	  !function(f,b,e,v,n,t,s)
				    	  {if(f.fbq)return;n=f.fbq=function(){n.callMethod?
				    	  n.callMethod.apply(n,arguments):n.queue.push(arguments)};
				    	  if(!f._fbq)f._fbq=n;n.push=n;n.loaded=!0;n.version='2.0';
				    	  n.queue=[];t=b.createElement(e);t.async=!0;
				    	  t.src=v;s=b.getElementsByTagName(e)[0];
				    	  s.parentNode.insertBefore(t,s)}(window, document,'script',
				    	  'https://connect.facebook.net/en_US/fbevents.js');
				    	  fbq('init', '188490321873243');
				    	  fbq('track', 'CONTINUE_TO_PAY', {
				    	    value: 0000,
				    	    currency: 'INR',
				    	    content_name: 'Value',
				    	    content_ids: 'Value',
				    	    content_type: 'Product',
				    	    Order_id: 'Value',
				    	  });
				    	  
				      }
				      
				      function fbPixeltagPaymentProeed()
				      {
				    	  !function(f,b,e,v,n,t,s)
				    	  {if(f.fbq)return;n=f.fbq=function(){n.callMethod?
				    	  n.callMethod.apply(n,arguments):n.queue.push(arguments)};
				    	  if(!f._fbq)f._fbq=n;n.push=n;n.loaded=!0;n.version='2.0';
				    	  n.queue=[];t=b.createElement(e);t.async=!0;
				    	  t.src=v;s=b.getElementsByTagName(e)[0];
				    	  s.parentNode.insertBefore(t,s)}(window, document,'script',
				    	  'https://connect.facebook.net/en_US/fbevents.js');
				    	  fbq('init', '188490321873243');
				    	  fbq('track', 'PAYMENT_CHECKOUT');
				    	  
				      }
				      
				        /***************************************************FB pixel tag End**************************************/
				        
			        			function getIp()
			        			{
			        				$.get('https://www.cloudflare.com/cdn-cgi/trace', function(data) {
			        					   
			        					
			        					var ipdata=data.substr(data.indexOf("ip="),data.indexOf("ts="))
			        					ipdata=data.substr(data.indexOf("ip="),data.indexOf("ts="));
			        					dataVal=ipdata.substr(ipdata.indexOf("ip="),ipdata.indexOf("ts="));
			        					
			        				})	
			        			}
			        			
			        			function getIpAddress() {
			        			    $.ajax({
			        			        url: "https://www.cloudflare.com/cdn-cgi/trace",
			        			        type: 'GET',
			        			        dataType: "Text",
			        			        success: function(data) {

			        			            var ipdata = data.substr(data.indexOf("ip="), data.indexOf("ts="))
			        			            ipdata = data.substr(data.indexOf("ip="), data.indexOf("ts="));
			        			            dataVal = ipdata.substr(ipdata.indexOf("ip="), ipdata.indexOf("ts="));

			        			        },
			        			        error: function(e) {

			        			        }
			        			    });
			        			}
			        			
			        			/**********03/12/2020**********/
			        			
			        			
			        			
			        			$('.addfdrCheckBtn .a_swichTgl label input').change(function()
			        					{
			        					        if($(this).is(':checked'))
			        					        {
			        					            $("#fdrCHeck").attr("checked", true);
			        					            $('#fdrCHeck').val("Yes");
			        					            console.log("FDR required copy"+$('#fdrCHeck').val());
			        					          }else
			        					        {
			        					           $("#fdrCHeck").attr("checked", false);
			        					           $('#fdrCHeck').val("No");
			        					           console.log("FDR required copy"+$('#fdrCHeck').val());
			        					        }
			        					    });
			        			/**********03/12/2020*************/
			        			/**********Custom prefill*************/

						        $('#costomerId').change(function()
							    	    {
						        	
						        	var selectedCustId=$(this).find('option:selected').val();
						        	
						        	var hiddenExistingID=$('#custCkycIDVD').val();
						        	console.log("productFDCustId"+productFDCustId);
						        	if(selectedCustId==hiddenExistingID){selectedCust=false;}else{selectedCust=true;}
						        	if(selectedCustId==productFDCustId){$('#prefillSelectoops').show();}else{$('#prefillSelectoops').hide();}
						        	
						        	if(selectedCust)
						        	{
						        
						        	var mobileNumber=$('#mobilePD').val();
						        	var dob=$('#dobPD').val();
						        	if(selectedCustId.length>0){
						        	
						        		   $('.loadoverlay').show();
						        		   
						        		   var custId=fdcNumber;
						    			    
						    			    if(custId==null || custId == undefined)
						    	    		{
						    			    	custId="NA";
						    			    	
						    	    		}
						        	var data = {
			           					 "prefillCustID":selectedCustId,
			           					"mobileNumber":mobileNumber,
			           					"dob":dob,
			           			        "fdcNumber":custId
			           					 };
			           			 
			           				       
			           			         $.ajax({ 
			           			                                    
			           			             url: "/fixed-deposit-application-form/cutsomPrefill", 
			           			             type: "POST",
			           			             data:JSON.stringify(data),
			           			            // async: false,
			           			             contentType: 'application/json',
			           			             error: function(response)
			           				 			{
			           				          	console.log("inside errror function");
			           				         newRelicAlert("Technical Error Page in cutsomPrefill | Fail Ajax call","'/fixed-deposit-application-form/cutsomPrefill'",response.status," ");
			           				         setTimeout(function(){
			           				            $('.loadoverlay').hide();
			           				        },3000);
				           				      $('.maxcontainer div:visible').hide();
				     	                      $('.ErrorPage').show();
			           				         
			           				             }  ,
			           			             success: function(response) {            
			           			            
			           			            	var encResponse = JSON.parse(response);
			        		    	           	var decResponse = getOfferobje(encResponse);      	        	    				             	
			        		          	         var response = JSON.parse(decResponse);
			           			       	var prefillDataStatus=response.status;
			           			       	
			           			       	if(prefillDataStatus=="success")
			           			       	{
			           			       	 var userDetails = JSON.parse(response.userDetails);
			           			          var fullName =userDetails.fullName;
									      var email =userDetails.email;
									  
									    var panVal =userDetails.pan;
									    var addressVal =userDetails.address;
									    var pincode =userDetails.pin;
									    var flag = true;
									    var custCkycIDVD = userDetails.existingCustId;
										   if(!(custCkycIDVD== "NA" || custCkycIDVD == "")) 
			      		               {
											   $('#custCkycIDVD').val(selectedCustId);
											   $('#custCkycIDVD').parent().addClass('valid');
				                           }else{
				                        	  flag =false;
				                           }
										   
										   if(!(fullName== "NA" || fullName == "")) 
			      		               {
					  	                      	$('#fullNamePD').val(fullName);
					  	                        $('#fullNamePD').parent().addClass('valid');

					  	                	 $("#fullNamePD").prop("readonly", true);	    
				                           }else{
				                        	  flag =false;
				                           }
										
										   if(!(email== "NA" || email == "" || email == null)) 
			      		               { 
											   $('#emailPD').val(email);
											   $('#emailPD').parent().addClass('valid');
											}else{
			 	                        	  flag =false;
				                           }
										   
										
										   if(!(panVal== "NA" || panVal == "")) 
			      		               {
											   $('#panPD').val(panVal);
											   $('#panPD').parent().addClass('valid');
											  // $("#panPD").prop("readonly", true);
				                           }else{
				                        	  flag =false;
				                           }
										   
										   if(!(addressVal== "NA" || addressVal == "")) 
			      		               {
										   
											   addressVal = addressVal.replace('-', ' ');   
											   addressVal = addressVal.replace('.', ' ');
					  	                       $('#addressPD').val(addressVal);
					  	                       $('#addressPD').parent().addClass('valid');
					  	                     $("#addressPD").prop("readonly", true);
					  	                      	
				                           }else
				                           {
				                        	  flag =false;
				                           }
										   
										   if(!(pincode== "NA" || pincode == "" || pincode ==null)) 
			      		               {
					  	                      $('#pinCodePD').val(pincode);
					  	                      $('#pinCodePD').parent().addClass('valid');
					  	                      $("#pinCodePD").prop("readonly", true);
					  	                      $("#cKycpinLock").show();
				                           }else{
				                        	  flag =false;
				                           }
										   
										
										   
										   if(flag){
											   $('.a_topBoxbord .a_halfBox a[data-tab="Personal_Details"] ~ .tickNdisimg').attr('src','/fixed-deposit-application-form/resources/images/tick-round.png');
											   
										   }
										   
										   
										   //$('#prefillSelectoops').hide();
										   setTimeout(function(){
									            $('.loadoverlay').hide();
									        },3000);
							        	  
											    
											
											
			           			       		}else
			           			       		{
			           			       			if(response.hasOwnProperty('sessionFieldValidationMsg'))
			            					{
			           			       		 var failMsg =	response.sessionFieldValidationMsg ;
						            			
						            				  $.each(failMsg, function(key, value)
						            						  { 
						                		   $("input[name="+key+"]").siblings(".errormsg").text(value).show(); 
						               		 });}else{
						               			newRelicAlert("Technical Error Page in cutsomPrefill","'/fixed-deposit-application-form/cutsomPrefill'",decResponse," ");
						               			$('.maxcontainer div:visible').hide();
					     	                    $('.ErrorPage').show();
						               		 
						               		 }
			           			       		 setTimeout(function(){
			           			              $('.loadoverlay').hide();
			           			          },3000);
			       		        		  		
			           			       		}
			           			    
									   		
			           			            	 }   
			           			      });
			           			         }
							    	    }
						        	selectedCust=true;
							    	    });
						        
						        
			        			/**********Custom prefill*************/
						        /***********************8*Document Upload************************************/
						        $(".uploadbtn input").click(function (e) {
						        	
					            	  $(this).siblings(".removedoc").hide();
						               $(this).val(null).hide();
						               $(this).siblings("input").val('').hide();
						               $(this).siblings(".upload").removeClass("valid");
						               
					            	
							       });
						        $(".uploadbtn input").change(function () {

						            if (!$(this).siblings(".upload").hasClass("removedoc")) {


						            	var fileexist = $(this)[0].files[0].size;
						                var filename = $(this).val();


						                 if (/^\s*$/.test(filename)) {
						                 }
						                 else {
						                   var finalPath = filename.replace("C:\\fakepath\\", ""); 
						                 }

						                var extension = finalPath.replace(/^.*\./, '');
						                if (extension == finalPath) {
						                    extension = '';
						                } else {
						                    extension = extension.toLowerCase();
						                }

						                var ext = 0;
						                if (extension == "jpg") {
						                    $(this).siblings(".errormsg").hide();
						                } else if (extension == "jpeg") {
						                    $(this).siblings(".errormsg").hide();
						                } else if (extension == "pdf") {
						                    $(this).siblings(".errormsg").hide();
						                } else {
						                    ext = 1;
						                    $(this).siblings(".errormsg").show().html("File format allowed are .pdf, .jpg, .jpeg");
						                    $(this).click();
						                }
						                if (ext == 0) {
						                    if (fileexist < 4194305) {
						                        $(this).siblings('.removedoc').show();
						                    $(this).siblings(".upload").addClass("valid");
						                        $(this).siblings("input").show().val(finalPath);
						                        $(this).siblings(".errormsg").hide();
						                    } else {
						                        $(this).siblings(".errormsg").show().html("<p>Max file size allowed is 4mb</p>");
						                    }
						                }
						            }
						        });

						       $(".uploadbtn .removedoc").click(function (e) {
						           if ($(this).siblings(".upload").hasClass("valid")) {
						               e.preventDefault();
						               $(this).hide();
						               $(this).siblings("input").val(null).hide();
						               $(this).siblings(".upload").removeClass("valid");

						           }
						       });


						       $('#uploadDocument .validBtn').click(function (e) {
						           e.preventDefault();
						           trackPageviewScript('Upload_Document');
						           setTimeout(function () {
						               var totalerror = 0;
						               var firsterr = 0;
						               $("#uploadDocument .errormsg").each(function (i) {
						                   if ($(this).css("display") == "block") {
						                       totalerror++;
						                       if (firsterr == 0) {
						                           firsterr = $(this).parent(".a_ReInput").offset().top;
						                       }
						                       var thh = $(this);

						                       if (thh.siblings('input')) {
						                           thh.siblings('input').addClass('field-blink');
						                           setTimeout(function () {
						                               thh.siblings('input').removeClass('field-blink');
						                           }, 4000);
						                       }
						                       if (thh.siblings('select')) {
						                           thh.siblings('select').addClass('field-blink');
						                           setTimeout(function () {
						                               thh.siblings('select').removeClass('field-blink');
						                           }, 4000);
						                       }
						                   }
						               });
						               if (totalerror == 0) {
						            	   $("#uploadDocument").addClass("disabledbutton");
						            	   $('.loaderslide div').html('');
						            	   $('.loaderslide div').html('Hang on for just a moment!');
						            	   $('.loadoverlay').show();
						            	   dataLayerCall("form_submission_Loan_ApplicationPage_2", "Upload Documents SUBMIT", "True");
						            	   //$('.loaderslide').hide();
						            	  
						            	   documentUpload();
						               } else {
						            	   dataLayerCall("form_submission_Loan_ApplicationPage_2", "Upload Documents SUBMIT", "False");
						                   if ($(window).width() < 900) {
						                       $('html, body').animate({
						                           scrollTop: firsterr - 55
						                       }, 200);
						                   } else {
						                       $('html, body').animate({
						                           scrollTop: firsterr - 30
						                       }, 200);
						                   }
						                   $('html, body').animate({'scrollTop':$(".errormsg").offset().top -200 },3000);
						               }
						           }, 500);

						       });





						           $('.j_docs input[name="addprf"]').change(function () {
						               if ($(this).is(':checked')) {
						                   $('.addfield').find('input:file,select').prop("disabled", true);
						                   $('.addfield').children('.a_ReInput').find('.errormsg').hide();
						                   $('.addfield').hide();
						                   $(this).val("No");
						                   
						               } else {
						                   $('.addfield').find('input:file,select').prop("disabled", false);
						                   $('.addfield').show();
						                   $(this).val("Yes");
						               }
						               $('.addfield .uploadbtn').find('input:file').prop('disabled', true);
						               $('#addressDocumentSelected').find("option:contains('Select Address document')").prop("selected",true);
						           });


						           $('.j_docs input[name="signprf"]').change(function () {
						               if ($(this).is(':checked')) {
						                   $('.signpr').find('input:file,select').prop("disabled", true);
						                   $('.signpr').children('.a_ReInput').find('.errormsg').hide();
						                   $('.signpr').hide();
						                   $(this).val("No");
						               } else {

						                   $('.signpr').find('input:file,select').prop("disabled", false);
						                   $('.signpr').show();
						                   $(this).val("Yes");
						               }
						               
						               $('.signpr .uploadbtn').find('input:file').prop('disabled', true);
						               $('#signDocumentSelected').find("option:contains('Select Sign Proof')").prop("selected",true);
						           
						               
						           });

						           $('#signDocumentSelected').change(function () {
						        	   var getaddsel = $(this).val();
						        	   if(getaddsel=="")
						        	   {
						            	   $('.signpr .uploadbtn').find('input:file').prop('disabled', true);
						        	  
						        	   }else
						        		   {
						        		   $('.signpr .uploadbtn').find('input:file').prop('disabled', false);
						        		   }
						           });

						       $('.identityval').change(function () {
						           var getsel = $(this).val();
						           
						           $('.j_checkprf').show();
						           $('.identity .uploadbtn').show();
						           if (getsel == "VoterID" || getsel == "NREGA" || getsel == "Aadhaar") {
						               $('.hdprof input').prop('checked', false);
						               $('.signpr').find('input:file,select').prop("disabled", false);
						               $('.hdprof').hide();
						               $('.signpr').show();
						              // $('#signproof').parent('uploadbtn').css("display","block");
						               $('.uploadbtn').show();
						               $('.signpr .uploadbtn').show();
						           } else {
						               $('.hdprof').show();
						               $('.hdprof input').prop('checked', true);
						               $('.signpr').find('input:file,select').prop("disabled", true);
						               $('.signpr').children('.a_ReInput').find('.errormsg').hide();
						               $('.signpr').hide();
						               $('.uploadbtn').show();
						           }

						           if(getsel == "NREGA") {
						              $('input#backidproof').siblings('.errormsg').hide();
						              $('input#backidproof').parents('.uploadbtn').hide();
						              $('input#backidproof').addClass('nomandetory');
						              $('.signpr .uploadbtn').show();
						           }else{
						              $('input#backidproof').parents('.uploadbtn').show();
						              $('input#backidproof').removeClass('nomandetory');
						           }
						           if(getsel=="")
					        	   {
					        	   $('.identity .uploadbtn').find('input:file').prop('disabled', true);
					        	   $('.j_checkprf').hide();
					        	   }else
					        		   {
					        		   $('.identity .uploadbtn').find('input:file').prop('disabled', false);
					        		   }

						       });



						           $('.adddropdown').change(function () {
						               var getaddsel = $(this).val();
						               $('.addfield .uploadbtn').show();
						               if (getaddsel == "NREGA"){
						                    $('input#backadd').addClass('nomandetory');
						                  $('input#backadd').siblings('.errormsg').hide();

						                  $('input#backadd').parents('.uploadbtn').hide();

						               }else{
						                 $('input#backadd').parents('.uploadbtn').show();
						                  $('input#backadd').removeClass('nomandetory');
						               }
						              
						               
						               if(getaddsel=="")
						        	   {
						            	   $('.addfield .uploadbtn').find('input:file').prop('disabled', true);
						        	  
						        	   }else
						        		   {
						        		   $('.addfield .uploadbtn').find('input:file').prop('disabled', false);
						        		   }
						           });
						           
						           $(".okycandDocSplitChech .okycandDocSplitChechButton").click(function (e) {
						        		var val = $('.a_radiodeposit label input[name="authentication"]:checked').val();
						        		// alert("radio selcted"+val);
						        		if (val == "OKYC") {
						        			trackPageviewScript('Aadhar_Proceed');
						        			document.forms["instaform"].submit();
						        		} else {
						        			$('.a_part_3').hide();
						        			$('.a_part_3-1').hide();
						        			$('.a_part_3-2').hide();
						        			$('.identity .uploadbtn').find('input:file').prop('disabled', true);
						        			trackPageviewScript('KYC_Proceed');
						        			$('.a_part_document').show();

						        		}
						        	});
						           function multiPartFileObject(id)
						           {
						        	  
						        	   if($("#"+id)[0].files[0]==undefined)
						        		   {
						        		   if (/Edge/.test(navigator.userAgent)) {
						        			   return new Blob([""], {type : 'image/jpeg'});
						        			}else
						        				{
						        				   return new File([""], "dummyFile.jpg",{type: "image/jpeg"});
						        				}
						        		   }else
						        			   {
						        			   return $("#"+id)[0].files[0];
						        			   }
						        	   
						        	  
						           }
						           
						          
						           
						           function documentUpload()
						           {
							           var formId = "uploadDocument";
							           var formData = document.forms[formId];
							           var formVal = new FormData(formData);
							           var mobNo = $("#mobileNO").val().trim();
							           var custId = customerIdforDocUpload;
							           var addressDocumentSelected = $("#addressDocumentSelected").val();
							           var signDocumentSelected = $("#signDocumentSelected").val();
							           var identityval = $('.identityval').val();
							           if (signDocumentSelected == undefined || signDocumentSelected == "") {
							           	signDocumentSelected = "NA"
							           }
							           if (addressDocumentSelected == undefined || addressDocumentSelected == "") {
							           	addressDocumentSelected = "NA"
							           }


							           if (!$('.j_docs input[name="addprf"]').is(":checked")) {
							           	formVal.append('addprf', "Yes");
							           }

							           if (!$('.j_docs input[name="signprf"]').is(":checked") || identityval == "VoterID" || identityval == "NAREGA" || identityval == "Aadhaar") {
							           	formVal.append('signprf', "Yes");
							           }
							           formVal.append('mobileNumber', mobNo);
							           formVal.append('custId', custId);
							           formVal.append('signDocumentSelected', signDocumentSelected);
							           formVal.append('addressDocumentSelected', addressDocumentSelected);
							           // Display the key/value pairs
							           
							           formVal.append("idententiyDocFrontFile",multiPartFileObject("frontidproof"));
							           formVal.append("idententiyDocBackFile",multiPartFileObject("backidproof"));
							           formVal.append("addressDocFrontFile", multiPartFileObject("frontadd"));
							           formVal.append("addressDocBackFile",multiPartFileObject("backadd"));
							           formVal.append("SignDocFile",multiPartFileObject("signproof"));
							           formVal.append("PhotoDocFile", $("#photo")[0].files[0]);
							           
							           
							           $.ajax({
							           	url: '/fixed-deposit-application-form/documetUploadNTB',
							           	type: 'post',
							           	data: formVal,
							           	contentType: false,
							           	processData: false,

							           	failure: function (errMsg) {
							           		$('#uploadDocument  .a_blueBtnPart button .fd_sdp_loder').hide();
							           		newRelicAlert("Technical Error Page in documetUploadNTB | Fail Ajax call","'/fixed-deposit-application-form/documetUploadNTB'",errMsg.status," ");
							           		$('.maxcontainer div:visible').hide();
				     	                    $('.ErrorPage').show();
							           	},
							           	success: function (response) {

							           	 var encResponse = JSON.parse(response);
						    	           	var decResponse = getOfferobje(encResponse);      	        	    				             	
						          	         var response = JSON.parse(decResponse);
							           		if ((response.validationStatus == "success"))
							           		{
							           			var userDetails=JSON.parse(response.userDetails)
							           			
							           			if(userDetails.status=="success")
							           			{
							           			 var fullName =userDetails.fullName;
							           			 var mobNumber =userDetails.mobileNumber;
							           			 var dob =userDetails.dateOfBirth;
							           			 pan =userDetails.pan;
							           			 pin =userDetails.pin;
							           			
							           			var okyccustTypeFD= userDetails.custType;
											    if(okyccustTypeFD == "STB"){
											    	custTypeFD = "STB";
											    }else{
											    	custTypeFD="NTBDocumentUpload";
											    }
							           			if (!(fullName == "NA" || fullName == "")) {
							           			    $('#fullNamePD').val(fullName).prop('readonly', true);
							           			    $('#fullNamePD').parent().addClass('valid');

							           			    var name = fullName.split(" ");
							           			    var fname = name[0];
							           			    var fchar = fname.charAt(0);
							           			    $('.a_alphaFont').append(fchar);
							           			    $('.a_alphaFont').siblings("h2").append(" " + fname);

							           			}
							           			if (!(mobNumber == "NA" || mobNumber == "")) {
							           			    $('#mobilePD').val(mobNumber).prop('readonly', true);
							           			    $('#mobilePD').parent().addClass('valid');
							           			    $('#mobileNO').val(mobNumber).prop('readonly', true);
							           			}

							           			if (!(dob == "NA" || dob == "")) {
							           			    $('#dobPD').val("").prop('readonly', true);
							           			    $('#dobPD').val(dob).prop('readonly', true);
							           			    $('#dobPD').parent().addClass('valid');

							           			}

							           			if (!(pan == "NA" || pan == "" || pan == null )) {
							           					$('#panPD').val(pan).prop('readonly', false);
							           					$("#cKycpanLock").hide();
							           			}

							           			if (!(pin == "NA" || pin == "")) {
							           			    $('#pinCodePD').val(pin).prop('readonly', false);
							           			    $('#cKycpinLock').hide();
							           			}
							           			$("#addressPD").removeAttr("readonly");
							           			
							           			$('.address .p_clecnder').remove();
							           			$('.address .p_fixdepositstultip').remove();
							           			$('.address img').remove();
							           			$('.a_part_3').hide();
							           			$('.a_part_3-2').hide();
							           			
							           			
							           		   setTimeout(function(){
										            $('.loadoverlay').hide();
										            $('.a_part_3-1').hide();
										            $('.a_part_document').hide();
										            $('.a_part_4').show();
										        },3000);
							           			}else
							           			{
							           				newRelicAlert("Technical Error Page in documetUploadNTB | UserDetails Fail","'/fixed-deposit-application-form/documetUploadNTB'"," "," ");
							           				$('.maxcontainer div:visible').hide();
						     	                    $('.ErrorPage').show();
							           				}
							           			
							           		} else if(response.validationStatus == "fail")
					                 		{

						                    	var failMsg =	response.validationMsg ;
						                 	   $.each(failMsg, function(key, value){ 
						               		   $("input[name="+key+"]").siblings(".errormsg").text(value).show(); 
						              		 }); 
						                 	   setTimeout(function(){
										            $('.loadoverlay').hide();
										            $("#uploadDocument").removeClass("disabledbutton");
										        },3000);
						                 	   
							             		newRelicAlert("Fail in documetUploadNTB","'/fixed-deposit-application-form/documetUploadNTB'"," "," "); 
							           		}
							           		else if(response.apiStatus == "sessionExpired")
						             		{
							           			newRelicAlert("session expired in documetUploadNTB","'/fixed-deposit-application-form/documetUploadNTB'",response,fdcNumber);
							             		$('.maxcontainer div:visible').hide();
							             		$('.SessionErrorPage').show(); 
						             		}
							           		else
					                 		{
							           			newRelicAlert("Technical Error Page in documetUploadNTB","'/fixed-deposit-application-form/documetUploadNTB'"," "," ");
							           			$('.maxcontainer div:visible').hide();
					     	                    $('.ErrorPage').show();
					                 		 
					                 		}
							           	}

							           });
							           }
						           
						           

						           if ($(window).width() < 768) {
						                   $('.tipstool_1 .upload').click(function () {
						                       if($('.identityval').val() == "" || $('#signDocumentSelected').val() == ""|| $('.adddropdown').val() == ""){
						                           $(this).siblings('.custooltip_1').addClass('showtip_1');
						                           var th = $(this);
						                           setTimeout(function () {
						                               th.siblings('.custooltip_1').removeClass('showtip_1');
						                           }, 5000);
						                       }
						                   });
						               } else {
						                   $('.tipstool_1 #identityHoverup').hover(function () {
						                       if($('.identityval').val() == ""){$(this).siblings('.custooltip_1').toggleClass('showtip_1');}
						                       else
						                    	   {
						                    	   $(this).siblings('.custooltip_1').removeClass('showtip_1');
						                    	   }
						                      
						                   });
						                   $('.tipstool_1 #hoverUpadd').hover(function () {
						                       if($('.adddropdown').val() == ""){$(this).siblings('.custooltip_1').toggleClass('showtip_1');}
						                       else
						                    	   {
						                    	   $(this).siblings('.custooltip_1').removeClass('showtip_1');
						                    	   }
						                      
						                   });
						                   $('.tipstool_1 #hoverUpSign').hover(function () {
						                       if($('#signDocumentSelected').val() == ""){$(this).siblings('.custooltip_1').toggleClass('showtip_1');}
						                       else
						                    	   {
						                    	   $(this).siblings('.custooltip_1').removeClass('showtip_1');
						                    	   }
						                      
						                   });
						                   
						                   
						               }
						           
						           $("#panCarVerify .a_radiodeposit label b").click(function (e) {
						               e.stopPropagation();
						               e.preventDefault();
						               $(this).siblings('.p_fixdepositstultip').addClass('p_fixdepositstultipShow');
						           });
						           
						           $(".a_ReInput.cl-ReInput img").click(function (e) {
						               e.stopPropagation();
						               e.preventDefault();
						               $(this).siblings('.p_fixdepositstultip').addClass('p_fixdepositstultipShow');
						           });
						        /************************Document Upload************************************/
/************************Resume Journey Start********************************************************/
						           
						           $('.a_radiodeposit .j_hunpd input[type="radio"]').change(function () {
						        	     if (this.id == "preapp") {
						        	         $('#appNofield.a_ReInput input').prop('disabled', false);
						        	         $('#appNofield.a_ReInput input').removeClass('nomandetory');
						        	         $('#mobileNO').prop('disabled', true);
						        	         $('#SendOtp .datefield input').prop('disabled', true);
						        	         $('#mobileNO').addClass('nomandetory');
						        	         $('#SendOtp .datefield input').addClass('nomandetory');
						        	         $('#mobileNO').siblings('.errormsg').hide();
						        	         $('#SendOtp  .datefield input').siblings('.errormsg').hide();

						        	         $('#enterapp').prop('disabled', false);
						        	         $('#enterapp').removeClass('nomandetory');
						        	         
						        	       
						        	         
						        	     } else {
						        	         $('#appNofield .a_ReInput input').prop('disabled', true);
						        	         $('#appNofield .a_ReInput input').addClass('nomandetory');
						        	         $('#appNofield .a_ReInput input').siblings('.errormsg').hide();
						        	         $('#mobileNO').prop('disabled', false);
						        	         $('#SendOtp .datefield input').prop('disabled', false);
						        	         $('#mobileNO').removeClass('nomandetory');
						        	         $('#SendOtp .datefield input').removeClass('nomandetory');
						        	         
						        	         $('#enterapp').prop('disabled', true);
						        	         $('#enterapp').addClass('nomandetory');
						        	         $('#enterapp').siblings('.errormsg').hide();
						        	         fildValidation(this, "Please enter your 10 digit mobile number");
						        	     }
						        	 });
						           
						           
						          
						           function fdresumeotp() {
					    	  			
					    	  			var mobileNumber = $('#enterapp').val();
					    	  			 var otpTriggeredTimeday=new Date().toLocaleDateString();
						    		        var otpTriggeredTime = new Date().toLocaleTimeString('en-GB');
						    		        otpTriggeredTime=otpTriggeredTimeday+" "+otpTriggeredTime;
						    		        
						    		        /*if( $('#checkBox_step1').prop('checked')){
						    					partnerCode=$("#pCode").val().trim();
						        				partnerName=$("#pName").val().trim();
						    				}
						    				
						    				if(partnerCode=="" || partnerCode== null)
						    				{
						    					partnerCode ="NA";
						    					partnerName ="NA";
						    				}*/
						    				 var calcType = $("#calc").val();
						    				var campaignData = {
							    	  				"mobileNumber": mobileNumber,
					    							"investmentType":calcType,
					    							"otpTriggeredTime" :otpTriggeredTime
							    	  			}
						    				
						    				var campCookie=GetCookie('campaignCookie');
							    	     	var lastClickCookie = GetCookie('LastClickCookie'); 
							    	     	
							    	     	lastClickCookie= JSON.parse(lastClickCookie); 
							    	        campCookie= JSON.parse(campCookie);
							    	        
						    				var utm_source = getUrlValue("utm_source");
							    	       	var utm_medium = getUrlValue("utm_medium");
							    	       	var utm_keyword = getUrlValue("utm_keyword");
							    	       	var utm_campaign =	getUrlValue("utm_campaign");
							    	       	var utm_content = getUrlValue("utm_content");
							    	       
							    		
							    	        if(utm_source !=null || utm_medium !=null || utm_keyword !=null || utm_campaign !=null || utm_content !=null){
							    	     		
							    	     		 if(utm_campaign == null || utm_campaign ==='null'){ 
							    	     			 campaignData.utm_campaign_utmTrue='NA';    			 
							    	     		 }else{
							    	     			  campaignData.utm_campaign_utmTrue=utm_campaign;
							    	     		 }
							    	     		 
							    	     		 if(utm_medium == null || utm_medium ==='null'){   
							    	     			
							    	     			 campaignData.utm_medium_utmTrue='bfl';
							    	     		 }else{
							    	     			 campaignData.utm_medium_utmTrue=utm_medium;
							    	     		 }
							    	     		 
							    	     		
							    	     		   
							    	     		 if(utm_source ==null || utm_source ==='null'){
							    	     			 campaignData.utm_source_utmTrue='organic_bfl';
							    	     		 }else{
							    	     			 campaignData.utm_source_utmTrue=utm_source; 
							    	     		 }
							    	     		 
							    	     		
							    	     		
							    	     	
							    	     		              
							    	     	 }   
							    	     	 else if(campCookie != null){  
							    	    	       campaignData.utm_campaign_utmTrue=campCookie['utm_campaign'];   
							    	    	       campaignData.utm_medium_utmTrue=campCookie['utm_medium'];
							    	    	       
							    	    	       campaignData.utm_source_utmTrue=campCookie['utm_source']; 
							    	    	      
							    	    	       
							    	          }
							    	     	 else{
							    	     	  
							    	     	   campaignData.utm_campaign_utmTrue='NA';   
							    	   	       campaignData.utm_medium_utmTrue='bfl';
							    	   	     
							    	   	       campaignData.utm_source_utmTrue='organic_bfl'; 
							    	 		 }
							    	        if(campaignData.utm_source_utmTrue=="" || campaignData.utm_medium_utmTrue=="")
					 	    	    	   {
					 	    	    	   campaignData.utm_source_utmTrue='organic_bfl';
					 	    	    	   campaignData.utm_medium_utmTrue='bfl';
					 	    	    	   }
							    	        if(campaignData.utm_campaign_utmTrue=="")
						 	    	    	   {
							    	        	campaignData.utm_campaign_utmTrue="NA"
						 	    	    	   }
							    	        
							    	        var windowwidth =  $(window).width();
							    	        if(windowwidth<600){
							    	     	   device="Mobile";
							    	    	 }else if(windowwidth>640 && windowwidth<992 ){
							    	    		device="Tablet";  
							    	         }else{
							    	    		device="Desktop";
							    	         } 
							    	        campaignData.device=device;
						    				 var calcType = $("#calc").val();
					    	  			
					    	  			var token = $("#csrf").val();
					    	  			console.log(token);
					    	  			$.ajax({
					    	  				url: "/fixed-deposit-application-form/resumeFdcJourneygetOTP",
					    	  				type: "POST",
					    	  				data: JSON.stringify(campaignData),
					    	  				beforeSend: function (xhr) {
					    	  					xhr.setRequestHeader('X-CSRF-Token', token);
					    	  				},
					    	  				contentType: 'application/json',
					    	  				error: function (response) {
					    	  					newRelicAlert("Technical Error Page in resumeFdcJourneygetOTP | Fail Ajax call","'/fixed-deposit-application-form/resumeFdcJourneygetOTP'",response.status," ");
					    	  					$('#SendOtp .p_reqotp button .fd_sdp_loder').hide();
					    	  					$('.maxcontainer div:visible').hide();
					     	                    $('.ErrorPage').show();
					    	  				},
					    	  				success: function (response) {
					    	  					var encResponse = JSON.parse(response);
					    	  					var decResponse = getOfferobje(encResponse);
					    	  					var response = JSON.parse(decResponse);
					    	  					if (response.apiStatus == "success") {
					    	  						var mobileNumber = response.mobileNumber;
					    	  					$('#sent_Mo').html(' ' + mobileNumber);
					    	  					$('.forMobileform').hide();
					    	  					$("#otpForm .errormsg").hide();
			                                    $('.forOtpForm').show();
			                                    $('.loginFirstStep').hide();
			                                    $('.otpMainPart').show();
			                                    $('.otpPart input').first().focus();
			                                    $('.otpCounter').show();
			                                    $('.otpTnc').show();
	    		    	 		          		$('#otp1').val('');
	    		    	 		          		$('#receiveOtp').prop("disabled", false); 
	    		    	 		          		$('#receiveOtp').show();
			                                    $('#resume_applicatIon .validBtn i').hide();
					    	  						$("#digit-1").focus();
					    	  						clearInterval(interval)
					    	  						count3minut();
					    	  						dataLayerCall("form_submission_get_otp", "GET OTP", "True");
					    	  						resumeJourneyFlag=true;
					    	  						fdcNumber=response.customerId;
					    	  					} else if (response.apiStatus == "fail") {  
					    	  						$('#resume_applicatIon .validBtn i').hide();
					    	  						$('#resumeGenerateOtpButton').prop("disabled", false); 
					    	  						if (response.hasOwnProperty('tokenStatus')) {
					    	  							alert("Please Refresh the page and try again.");
					    	  							location.reload();
					    	  						} else if (response.errorCode == "91") {
					    	  							alert(response.errorMsg);
					    	  							location.reload();
					    	  						} else {
					    	  							newRelicAlert("Technical Error Page in resumeFdcJourneygetOTP | Api Status Fail","'/fixed-deposit-application-form/resumeFdcJourneygetOTP'"," "," ");
					    	  							$('.maxcontainer div:visible').hide();
							     	                    $('.ErrorPage').show();
					    	  						}
					    	  						dataLayerCall("form_submission_get_otp", "GET OTP", "False");
					    	  						
					    	  					}else if(response.apiStatus == "sessionExpired")
					    	             		{
					    	  						$('.maxcontainer div:visible').hide();
						    	             		$('.SessionErrorPage').show();
					    	             		}
					    	  					else
					    	  					{
					    	  						newRelicAlert("Technical Error Page in resumeFdcJourneygetOTP","'/fixed-deposit-application-form/resumeFdcJourneygetOTP'"," "," ");
					    	  						$('.maxcontainer div:visible').hide();
						     	                    $('.ErrorPage').show();
					    	  					}
					    	  				}
					    	  			});
					    	
					    	 	
					    	  		}

					    	  		function resumeFdcJourneyValidateOtp()
					    		    {
					    		    	var mobileNumber=$('#enterapp').val();
					    		    	var otpNo =$("#otp1").val().trim();
					    		    	if(mobileNumber !="" && otpNo!="")
					    		    		{
					    		    	var otpSubmittedTime = new Date().toLocaleTimeString('en-GB');
					    				var otpSubmittedTimeDay=new Date().toLocaleDateString();
					    		       
					    				otpSubmittedTime=otpSubmittedTimeDay+" "+otpSubmittedTime;
					    				
					    				var custId=fdcNumber;
					    			    
					    			    if(custId==null || custId == undefined)
					    	    		{
					    			    	custId="NA";
					    			    	
					    	    		}
					    				var data ={
					    							"mobileNumber": mobileNumber,
					    							"userOtp":otpNo,
					    							"otpSubmittedTime":otpSubmittedTime,
					    				            "fdcNumber":custId
					    				}
					    		    	           $.ajax({  
					    		    	 		       url: "/fixed-deposit-application-form/resumeFdcJourneyValidateOtp",
					    		    	 		       type: "POST",
					    		    	 		       data:JSON.stringify(data),  
					    		    	 		       contentType: 'application/json',
					    		    	 		      error: function(response)
					    		    					{
					    		    	 		    	 newRelicAlert("Technical Error Page in resumeFdcJourneyValidateOtp | Fail Ajax call","'/fixed-deposit-application-form/resumeFdcJourneyValidateOtp'",response.status,custId);
					    		    	 		    	 $('#receiveOtp .p_reqotp button .fd_sdp_loder').hide();
					    		    	 		    	$('.maxcontainer div:visible').hide();
							     	                    $('.ErrorPage').show();
					    		    		            }  ,
					    		    	           	   success: function(response)
					    		    					{
					    		    	           	 var encResponse = JSON.parse(response);
					    		    	           	var decResponse = getOfferobje(encResponse);      	        	    				             	
					    		          	         var response = JSON.parse(decResponse); 
					    		    	 		         if(otpNo == response.userOtp)
					    		    	 		        	 {
					    		    	 		        	   $('#receiveOtp .validBtn').prop('disabled', true);
					    		          	            if(response.otpValidateStatus=="success")
					    		    						{
					    		          	            	$('#otpForm .validBtn i').hide();
					    		          	            	 var userDetails = JSON.parse(response.ResumeUserDetails);
					    		          	            	 var resumestep=userDetails.resumestep;
					    		          	            	 custTypeFD = userDetails.custType;
					    		          	            	 var mobNumber =userDetails.mobileNumber;
					    		          	            	 var dob =userDetails.dateOfBirth;
					    		          	            	var pin =userDetails.pin;
					    		          	            	 
					    		          	            	 var investmentType=userDetails.investmentType;
					    		          	            	  $('#calc').val(investmentType);
					    		          	            	  
					    		          	            	  $('#mobileNO').val(mobNumber);
					    		          	            	  $('#mobileNO').siblings('label').addClass('active');
					    		          	            	  
					    		          	            	 if(resumestep=="step1")
					    		          	            		 {
					    		          	            		  $('.otpMainPart').hide(); 
					    		          	            		 $('#apply_for_new_fd').fadeIn(200);
					    		          	            		 $('#apply_for_new_fd').siblings().hide();
					    		          	            		 $('.tab_ul li a[ data-tab="apply_for_new_fd"]').get(0).click();
					    		          	            		 $('.loginFirstStep').show(); 
					    		          	            		 $('#enterapp').val("");
					    		          	            		 $('#otpDigit').val("");
					    		          	            		
					    		          	            		 $("#preapp").prop('checked',false);
										    	  				 $("#new").prop('checked', 'checked');
										    	  				  $('#enterapp').prop('disabled', true);
											        	         $('#enterapp').addClass('nomandetory');
											        	         $('#enterapp').siblings('.errormsg').hide();
										    	  				//$('.a_radiodeposit .j_hunpd input[type="radio"]').change();
										    	  				  if(dob!="NA" && dob!=""){
										    	  				
										    	  				 $('#hldatepicker').val(dob);
										    	  				$('#hldatepicker').siblings('label').addClass('active');
										    	  				 }
										    	  				if(pin!="NA" && pin!=""){
										    	  					$('#pinCodePV').val(pin);
										    	  					$('#pinCodePV').siblings('label').addClass('active');
											    	  			 }
										    	  				 resumeJourneyFlag=false;
										    	  				tokenCall();
					    		          	            		 }
					    		          	            	 else if(resumestep=="step2")
					    		          	            		 {
					    		          	            		  var fullName =userDetails.fullName;
					    		          	            		  pan =userDetails.pan;
					    		          	            		 pin =userDetails.pin;
					    		          	            		 $('#panCarVerify #fullNamePDNew').val(fullName);
					    		          	            		 $('#panCarVerify #panCardPV').val(pan);
					    		          	            		 $('#panCarVerify #pinCodePV').val(pin);
					    		          	            		  $('.a_part_1').hide(); 
					    		          	            		  $('.a_part_2').hide();
					    		          	            		  $('.a_part_3').hide(); 
					    		          	            		  $("#otpForm .errormsg").hide();
					    		          	            		  $('.a_part_3-1').show();
					    		          	            		 }
					    		          	            	 else if(resumestep=="step3")
					    		          	            		 {
					    		          	            		var fullName =userDetails.fullName;
					 		    							   var email =userDetails.email;
					 		    							    pan =userDetails.pan;
					 		    							    address =userDetails.address;
					 		    							    pin =userDetails.pin;
					 		    							    var nomineAvailable=userDetails.isNomineeAvaiable;
					 		    							    var guardianAvailable=userDetails.isGaurdiunAvailable;
					 		    							    var nomineeAddressPresent=userDetails.isNomineeAddressAvailable;
					    		          	            		$('#prefillSelectinput').remove();
					    		    		             		$('#prefillSelectoops').remove();
					    		    		             		var gender=userDetails.gender;
					    		    		             		var mainTitle =userDetails.mainSalutation;
					    		    		             		var nomineeSalutation = userDetails.nomineeSalutation;
					    		    		             		etbFlag = userDetails.etbFlag;
					    		    		             		panNo = userDetails.pan;
					    		    		             		
					    		    		             		
					    		    		             		if(custTypeFD=="DEDUPE")
							    								{
					    		    		             			custTypeFD="ETB";
							    								}
					    		    		             		
					    		          	            		if(custTypeFD=="ETB")
							    								{
							    									//$('#etbExclusive').show();
							    								}
							    								if(custTypeFD=="STB")
							    								{
							    									$('#stbExclusive').show();
							    								}
							    								if(!(custTypeFD == 'NTBDocumentUpload'))
							    								{
							    									$('#genderDiv').remove();
							    								}
							    								
							    								if(custTypeFD == 'NTBDocumentUpload')
					    										{
					    									if (!(gender == "NA" || gender == "")) {
					    										$("#gender").find('option[value="' + gender + '"]').prop("selected", true);
					    										$('#gender').parent().addClass('valid');
						    								} else {
						    									flag = false;
						    								}
					    										}
							    								 if((custTypeFD == 'NTBDocumentUpload') || (custTypeFD == 'CKYC') || (custTypeFD == 'OKYC') ){
							    								    	custTypeFD = "CKYCETB";
							    								    }
							    								   var custCkycIDVD = userDetails.existingCustId;
							    								   if(!(custCkycIDVD== "NA" || custCkycIDVD == "")) 
							    			 		               {
							    									   $('#custCkycIDVD').val(custCkycIDVD);
							    									   $('#custCkycIDVD').parent().addClass('valid');
							    			                       }
							    								   
							    								   if (!(mainTitle == "NA" || mainTitle == "")) {
							    									   $("#titleMain").find('option[value="' + mainTitle + '"]').prop("selected", true);
								    								
								    									$('#titleMain').parent().addClass('valid');

								    								} else {
								    									flag = false;
								    								}
							    								   
							    								   
							    								if (!(dob == "NA" || dob == "")) {
							    									$('#dobPD').val(dob);
							    									$('#dobPD').parent().addClass('valid');

							    								} else {
							    									flag = false;
							    								}
							    								if (!(mobNumber == "NA" || mobNumber == "")) {
							    									$('#mobilePD').val(mobNumber);
							    								
							    									$('#mobilePD').parent().addClass('valid');
							    								} else {
							    									flag = false;
							    								}
							    								
							    								
							    								
							    								if (!(fullName == "NA" || fullName == "")) {
							    									$('#fullNamePD').val(fullName);
							    									$('#fullNamePD').parent().addClass('valid');

							    									var name = fullName.split(" ");
							    									var fname = name[0];
							    									var fchar = fname.charAt(0);
							    									$('.a_alphaFont').append(fchar);
							    									$('.a_alphaFont').siblings("h2").append(" " + fname);

							    								} else {
							    									flag = false;
							    								}

							    								if (!(email == "NA" || email == "" || email == null)) {
							    									$('#emailPD').val(email);
							    									$('#emailPD').parent().addClass('valid');
							    								} else {
							    									flag = false;
							    								}

							    								

							    								if (!(pan == "NA" || pan == "")) {
							    									$('#panPD').val(pan);
							    									$('#panPD').parent().addClass('valid');
							    								} else {
							    									flag = false;
							    								}

							    								if (!(address == "NA" || address == "" || address == null )) {

							    									address = address.replace('-', ' ');
							    									address = address.replace('.', ' ');
							    									$('#addressPD').val(address);
							    									$('#addressPD').parent().addClass('valid');

							    								} else {
							    									flag = false;
							    									 $("#addressPD").prop("readonly", false); 
							    									 $("#addressPD").next().next('.p_clecnder').remove();
							    									 
							    								}

							    								if (!(pin == "NA" || pin == "")) {
							    									$('#pinCodePD').val(pin);
							    									$('#pinCodePD').parent().addClass('valid');
							    								} else {
							    									flag = false;
							    								}

							    								if (nomineAvailable == "YES")
							    								{
							    									var nomineeName = userDetails.nomineeName;
							    									var nominneeDob = userDetails.nomineeDob;
							    									var nomineeRealation = userDetails.relationWithNominee;
							    									$('#nomineeCheck').prop('checked', true).change();
							    									
							    									if (!(nomineeSalutation == "NA" || nomineeSalutation == "")) {
								    									   $("#title").find('option[value="' + nomineeSalutation + '"]').prop("selected", true);
									    									$('#title').parent().addClass('valid');

									    								} else {
									    									flag = false;
									    								}
							    									
							    									if (!(nomineeName == "NA" || nomineeName == "")) {
							    										$('#nomineeName').val(nomineeName);
							    										$('#nomineeName').parent().addClass('valid');
							    									} else {
							    										flag = false;
							    									}
							    									if (!(nominneeDob == "NA" || nominneeDob == "")) {
							    										var day = nominneeDob.substring(0, 2);
							    										var month = nominneeDob.substring(3, 5);
							    										var year = nominneeDob.substring(6, 10);
							    										$('#nomineedd').val(day);
							    										$('#nomineemm').val(month);
							    										$('#nomineeyy').val(year);
							    										$('.datepickerVD').parent().addClass('valid');

							    									} else {
							    										flag = false;
							    									}
							    									if (!(nomineeRealation == "NA" || nomineeRealation == "")) {
							    										$("#sdprelationshipNominee").find('option[value="' + nomineeRealation + '"]').prop("selected", true);
							    										$('#sdprelationshipNominee').parent().addClass('valid');
							    									} else {
							    										flag = false;
							    									}
							    									if (nomineeAddressPresent == "YES") {
							    										var nomineeAddress = userDetails.nomineeAddres;
							    										var nomineePincode = userDetails.nomineePincode;

							    										if (!(nomineeAddress == "NA" || nomineeAddress == "")) {

							    											address = address.replace('-', ' ');
							    											address = address.replace('.', ' ');
							    											$('#scpnomineeAdd').val(nomineeAddress);
							    											$('#scpnomineeAdd').parent().addClass('valid');

							    										} else {
							    											flag = false;
							    										}

							    										if (!(nomineePincode == "NA" || nomineePincode == "")) {
							    											$('#nomineepinCodePV').val(nomineePincode);
							    											$('#nomineepinCodePV').parent().addClass('valid');
							    										} else {
							    											flag = false;
							    										}
							    										$('#nomineeAddrsCheck').prop('checked', false).change();
							    									}else
							    										{
							    										 $('#nomineepinCodePV').addClass('nomandetory');
							    										 $('#scpnomineeAdd').addClass('nomandetory');
							    										}

							    									if (guardianAvailable == "YES") {
							    										var nomineeGuardiunAddress = userDetails.gaurdianAddress;
							    										var nomineeGuardiunPincode = userDetails.gaurdianPincode;
							    										var gaurdianName = userDetails.gaurdianName;
							    										var gaurdianAge = userDetails.gaurdianAge;
							    										var gaurdianRealtion = userDetails.gaurdianRealtion;

							    										if (!(gaurdianName == "NA" || gaurdianName == "")) {


							    											$('#sdpguardianName').val(gaurdianName);
							    											$('#sdpguardianName').parent().addClass('valid');

							    										} else {
							    											flag = false;
							    										}

							    										if (!(gaurdianAge == "NA" || gaurdianAge == "")) {


							    											$('#gaurdianNomineeAge').val(gaurdianAge);
							    											$('#gaurdianNomineeAge').parent().addClass('valid');

							    										} else {
							    											flag = false;
							    										}

							    										if (!(gaurdianRealtion == "NA" || gaurdianRealtion == "")) {
							    											$("#sdprelationshipNominee2").find('option[value="' + gaurdianRealtion + '"]').prop("selected", true);
							    											$('#sdprelationshipNominee2').parent().addClass('valid');
							    										} else {
							    											flag = false;
							    										}
							    										if (!(nomineeGuardiunAddress == "NA" || nomineeGuardiunAddress == "")) {

							    											address = address.replace('-', ' ');
							    											address = address.replace('.', ' ');
							    											$('#sdpguardianAddress').val(nomineeGuardiunAddress);
							    											$('#sdpguardianAddress').parent().addClass('valid');

							    										} else {
							    											flag = false;
							    										}

							    										if (!(nomineeGuardiunPincode == "NA" || nomineeGuardiunPincode == "")) {
							    											$('#guardiunPincode').val(nomineeGuardiunPincode);
							    											$('#guardiunPincode').parent().addClass('valid');
							    										} else {
							    											flag = false;
							    										}


							    										$('.get18age').parents('#Banking_Details').find('.a_gardianDet').slideDown(200);
							    										$('.get18age').parents('#Banking_Details').find('.a_summaryView').show();
							    										$('.get18age').parents('#Banking_Details').find('.a_investmentTbl ul').slideUp(200);

							    										$('.get18age').parents('#Banking_Details').find('.a_gardianDet').find('input,select').removeClass('nomandetory');
							    										$('.get18age').parents('#Banking_Details').find('.a_investmentTbl > p').hide();
							    										$('.get18age').parents('#Banking_Details').find('.a_investmentTbl h2').addClass('hideIntrates');

							    									}else
							    										{
							    										$('#sdpguardianName').addClass('nomandetory'); 
							    										$('#gaurdianNomineeAge').addClass('nomandetory');
							    										$('#sdprelationshipNominee2').addClass('nomandetory');
							    										$('#sdpguardianAddress').addClass('nomandetory');
							    										$('#guardiunPincode').addClass('nomandetory');
							    										}
							    									
							    								}
                                                                     
							    								if (flag) {
							    								$('.a_topBoxbord .a_halfBox a[data-tab="Personal_Details"] ~ .tickNdisimg').attr('src', '/fixed-deposit-application-form/resources/images/tick-round.png');

							    								}
							    								
							    								 $("#addressPD").prop("readonly", false); 
						    									 $("#adderssLock").remove();
						    									 
							    								 $('.a_part_1').hide(); 
					    		          	            		  $('.a_part_2').hide();
					    		          	            		  $('.a_part_3').hide(); 
					    		          	            		  $('.a_part_3-1').hide();
					    		          	            		  $('.a_part_4').show();
					    		          	            		  
					    		          	            		 }
					    		          	            	$('#receiveOtp .p_reqotp button .fd_sdp_loder').hide();	
					    		    						}
					    		    	 		          	else if(response.otpValidateStatus=="fail"){
					    		    	 		          		$('#otpForm .validBtn i').hide();		  		
					    						                $('#otpForm .validBtn').prop('disabled', false);
					    						                
					    						              	if(response.hasOwnProperty('otpLimit'))
					    		    	 	 	            	{
					    		    	 		          		 alert(response.otpLimit);
					    		    	 		  				 location.reload();    
					    		    	 		 	          	}
					    		    	 		          	else if(response.responseCode == "92"){
					    		             					alert(response.responseMessage);
					    		             				}
					    		                           else if(response.responseCode == "93"){
					    		                        	   $("#otpForm .errormsg").text("Please enter valid OTP").show();
					    		                        	   //$('#receiveOtp .validBtn').attr('disabled', false);
					    		                        	   $('#otpForm .validBtn').prop('disabled', false);
					    		                           }
					    		                           else
					    		                        	   {
					    		                        	   newRelicAlert("Technical Error Page in resumeFdcJourneyValidateOtp","'/fixed-deposit-application-form/resumeFdcJourneyValidateOtp'"," ",custId);
					    		                        	   $('.maxcontainer div:visible').hide();
										                    	$('.ErrorPage').show();
					    		                        	   }
					    		    	 		          	}
					    		    	 		          	else if (response.otpValidateStatus == "transactionSuccess") 
								    	  					{
					    		    	 		        	    $('.otpCounter').hide();
					    		    	 		          		$('.otpTnc').hide();
					    		    	 		          		$('#receiveOtp').hide();
							 			             			$("#otpForm .errormsg").text('');
							 			             			$("#otpForm .errormsg").show().append("Your last FD application process was successful.<a href='/fixed-deposit-application-form?ProductName=FD' >Click here </a>to start a fresh application.");
							 			             			tokenCall();
									    	  					$('#otpForm .validBtn i').hide();
									    	  					$('#resume_applicatIon .validBtn i').hide();
								    	  					}else if(response.otpValidateStatus =="stepNotFound")
								    	  						{
									    	  						$('.otpCounter').hide();
						    		    	 		          		$('.otpTnc').hide();
						    		    	 		          		$('#receiveOtp').hide();
								 			             			$("#otpForm .errormsg").text('');
								 			             			$("#otpForm .errormsg").show().append("We could not find your previous application with us.<a href='/fixed-deposit-application-form?ProductName=FD' >Click here </a>to start a fresh application.");
								 			             			tokenCall();
										    	  					$('#otpForm .validBtn i').hide();
										    	  					$('#resume_applicatIon .validBtn i').hide();
								    	  						}
								    	  					else if(response.otpValidateStatus =="beforeTenDaysJourneyFound")
							    	  						{
								    	  						
								    	  						$('.otpCounter').hide();
					    		    	 		          		$('.otpTnc').hide();
					    		    	 		          		$('#receiveOtp').hide();
							 			             			$("#otpForm .errormsg").text('');
							 			             			$("#otpForm .errormsg").show().append("Your last FD application was initiated 10 days ago.<a href='/fixed-deposit-application-form?ProductName=FD' >Click here </a>to start a fresh application.");
							 			             			tokenCall();
									    	  					$('#otpForm .validBtn i').hide();
									    	  					$('#resume_applicatIon .validBtn i').hide();
							    	  						}
					    		    	 		          	else if(response.otpValidateStatus=="noDataFoundForuser"){
					    		    	 		          		$('.otpCounter').hide();
					    		    	 		          		$('.otpTnc').hide();
					    		    	 		          		$('#receiveOtp').hide();
							 			             			$("#otpForm .errormsg").text('');
							 			             			$("#otpForm .errormsg").show().append("No FD application found against your number,Please proceed with fresh application.<a href='/fixed-deposit-application-form?ProductName=FD' >Click here </a>to start a fresh application");
							 			             			tokenCall();
									    	  					$('#otpForm .validBtn i').hide();
									    	  					$('#resume_applicatIon .validBtn i').hide(); 
							 			             		}
					    		    	 		          	else if(response.apiStatus== "sessionExpired")
							 			             		{
					    		    	 		          		$('.maxcontainer div:visible').hide();
								 			             		$('.SessionErrorPage').show(); 
							 			             		}
					    		    	 		          	else
					    		    	 		          		{
					    		    	 		          		newRelicAlert("Technical Error Page in resumeFdcJourneyValidateOtp","'/fixed-deposit-application-form/resumeFdcJourneyValidateOtp'"," ",custId);
					    		    	 		          		$('.maxcontainer div:visible').hide();
										                    	$('.ErrorPage').show();
					    		    	 		          		}
					    		    	 		       }
					    		    	 		         else
					    		    	 		        	 {
					    						             $('#otpForm .p_reqotp button .fd_sdp_loder').hide();	
					    						         	 $("#otpForm .validBtn").prop("disabled", false);
					    						             $("#otpForm .errormsg").text("Please enter valid OTP").show();
					    		    	 		        	 }
					    		    	          	 }
					    		    	       	}); 
					    		    		}else{$("#otpForm .errormsg").show();	$("#otpForm .validBtn").prop("disabled", false);} 
					    		    }   
					    		    
					    		    
					    	  		function resumeResendOTP()
					    		    {
					    		    	var mobileNumber=$('#enterapp').val();
					    		    	var custId=fdcNumber;
					    			    
					    			    if(custId==null || custId == undefined)
					    	    		{
					    			    	custId="NA";
					    			    	
					    	    		}
					    				var campaignData ={
					    							"mobileNumber": mobileNumber,
					    				            "fdcNumber":custId
					    				}
					    		    	$.ajax({  
					    		    	 		       url: "/fixed-deposit-application-form/resumeregenerateOtp",
					    		    	 		       type: "POST",
					    		    	 		       data:JSON.stringify(campaignData),  
					    		    	 		     contentType: 'application/json',
					    		    	 		       error: function(response){
					    		    	 		    	  newRelicAlert("Technical Error Page in resumeregenerateOtp | Fail Ajax call","'/fixed-deposit-application-form/resumeregenerateOtp'",response.status,custId);
					    		    	 		    	  $('.maxcontainer div:visible').hide();
									                      $('.ErrorPage').show(); 
					    		    		                   },
					    		    		            success: function(response){
					    		    	           	 var encResponse = JSON.parse(response);
					    		    	           	var decResponse = getOfferobje(encResponse);
					    		          	         var response = JSON.parse(decResponse); 
					    		          	         var resendStatus = response.resendStatus;
					    		          	         $("#receiveOtp .validBtn").prop("disabled", true);
					    		    	 		          	if(response.apiStatus=="success")
					    		    						{
					    		    	 		          		var mobileNumber=response.mobileNumber;
					    		    	 		          		$("#otpForm .errormsg").hide();
					    		    	 		          	/* $('#sent_Mo').hide();
					    		    	 		          	  $('.resent_MO').show().html('We have resent another OTP to your mobile no. <i></i>'+mobileNumber);*/
					    		    	 		          		$('#sent_Mo').html(" " +mobNo);
					    		    	 		          	dataLayerCall("imp_body_text_click","Resend OTP","True")
					    		    	 		          		
					    		    						}
					    		    	 		          	else if(response.apiStatus == "fail")
					    		    	 		          	{ 
					    		    	 		                  if(response.errorCode == "91"){
					    		    	 		         					alert(response.errorMsg);
					    		    	 		         					location.reload(); 
					    		    	 		         				}
					    		    	 		         		else if(response.errorCode == "92"){
					    		    	 		         					console.log("errorCode 92 send otp");
					    		    	 		         					$("#mobileNO .errormsg").text(response.errorMsg).show();
					    		    	 		         				}
					    		    	 		         			
					    		    	 		         		else {
					    		    	 		         			dataLayerCall("imp_body_text_click","Resend OTP","False");
					    		    	 		         			newRelicAlert("Technical Error Page in resumeregenerateOtp | Api Status Fail","'/fixed-deposit-application-form/resumeregenerateOtp'"," ",custId);
					    		    	 		         			$('.maxcontainer div:visible').hide();
											                    	$('.ErrorPage').show(); 
					    		    	 		     		          }
					    		    	 		                 	}
					    		    	 		          	else  if(response.hasOwnProperty('otpResendTime'))
					    		    	 	 	            	{
					    		    	 		          		alert(response.otpResendTime);
					    		    	 		  				location.reload();    
					    		    	 		 	          	}
					    		    	 		          	else if(response.apiStatus == "sessionExpired")
					    		    	             		{
					    		    	 		          		$('.maxcontainer div:visible').hide();
						    		    	             		$('.SessionErrorPage').show();
					    		    	             		}
					    		    	 		          else
					    		    	 		          		{
					    		    	 		        	  	newRelicAlert("Technical Error Page in resumeregenerateOtp","'/fixed-deposit-application-form/resumeregenerateOtp'"," ",custId);
					    		    	 		        	    $('.maxcontainer div:visible').hide();
										                    	$('.ErrorPage').show(); 
					    		    	 		          		}
					    		    	          	 }
					    		    	       	});  
					    		    }
					    	  		
						           /******************************Resume Journey End********************************************************/
		   
		    /***************************************************FD Calculator JS Start Here**************************************/
		    
					    	  	  
					    	  		function inrFormat(x) { 
					    	  			x=x.toString();
					    	  			if (x.indexOf(',') > -1) {
					    	  					x = x.replace(/\,/g,'');
					    	  			}
					    	  			var lastThree = x.replace(/\D/g, '').substring(x.length-3);
					    	  			var otherNumbers = x.substring(0,x.length-3);
					    	  			if(otherNumbers != ''){
					    	  			lastThree = ',' + lastThree;}
					    	  			var res = otherNumbers.replace(/\D/g, '').replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree;
					    	  			return res;
					    	  		}
					    	  		
					    	  		
					    	  		$(".slctfild").change(function(){
					    	  			 $(this).css("color","#2b2b2b");
					    	  		});
					    	  		
					    	  		
					    	  		if($(".FDcal").length>0){
					    	  	    
					    	  	     $('#FDinvestAmount').slider({
					    	  	          formatter: function(value) {
					    	  	             var newcommaval = inrFormat(value);
					    	  	             $('.FDinvestAmount input').val(newcommaval);
					    	  	          } 
					    	  	     });
					    	  	    
					    	  	     $('#FDtenor').slider({
					    	  	          formatter: function(value) {
					    	  	             var newcommaval = inrFormat(value);
					    	  	             $('.FDtenor input').val(newcommaval);
					    	  	          } 
					    	  	     });
					    	  	    

					    	  		$(".FDcal .v1_SliderVal").blur(function(){
					    	  			var Sldrval = $(this).val();
					    	  			Sldrval =  Sldrval.replace(/\,/g,'');
					    	  			var parentID = $(this).parent('div').attr('class');
					    	  	        var sliderValueSet = $('#'+parentID).slider();
					    	  			sliderValueSet.slider('setValue', Sldrval);
					    	  			FDloanCalc();
					    	  		});					    	  	   
					    	  	     $(".sliderAmunt").change(function() {
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		        if (bankDetailsFlag) {
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		            $('select[name="fdbankName"]').find('option:contains(' + bankName + ')').prop("selected", true);
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		        } else {
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		            $("#fdNominee .a_radiodeposit_2 input").val('');
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		        }
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		        $("#fdbankName").change();
					    	  						    	  						    	  						    	  						    	  						    	  	        FDloanCalc();
					    	  						    	  						    	  						    	  						    	  						    	  	    }); 
					    	  	        
					    	  	    $(".sliderAmunt").blur(function(){
					    	  	        FDloanCalc();
					    	  	    });
					    	  	    
					    	  	    $(".customTenor").blur(function(){
					    	  	        FDloanCalc();
					    	  	    });
					    	  	    $("#fdCustomTenor").change(function() {
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		        FDloanCalc();
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		        $("#fdCustomTenor").change(function() {
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		            FDloanCalc();
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		            var tnrCheck = $("#fdCustomTenor").val();
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		            var dynamicT = 60;
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		            if (tnrCheck < 15) {
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		                dynamicT = 15;
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		            } else if (tnrCheck < 18 && tnrCheck >= 15) {
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		                dynamicT = 18;
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		            } else if (tnrCheck < 22 && tnrCheck >= 18) {
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		                dynamicT = 22;
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		            } else if (tnrCheck < 30 && tnrCheck >= 22) {
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		                dynamicT = 30;
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		            } else if (tnrCheck < 33 && tnrCheck >= 30) {
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		                dynamicT = 33;
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		            } else if (tnrCheck < 44 && tnrCheck >= 33) {
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		                dynamicT = 44;
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		            }

					    	  						    	  						    	  						    	  						    	  						    	  						    	  		            $('.a_fdTenorgreatChose1').html('Great choice!');
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		            $('.a_fdTenorgreatChose2').html('Why not choose for ' + dynamicT + ' months and get higher return');


					    	  						    	  						    	  						    	  						    	  						    	  	            });
					    	  						    	  						    	  						    	  						    	  						    	  						    	
					    	  						    	  						    	  						    	  						    	  						    	  	    });
					    	  	 
					    	  	    $(".a_selectblock input[name='tenor']").change(function(){    
					    	  	        FDloanCalc();
					    	  	    });
					    	  	    $(".a_selectblock input[name='payOut']").change(function(){    
					    	  	        FDloanCalc();
					    	  	    });
					    	  	        
					    	  	    $('.onewidral>h4').click(function(){
					    	  	        $(this).next().slideToggle(200);
					    	  	        $(this).toggleClass('active');
					    	  	    });
					    	  	    
					    	  	    	
					    	  		$('#professiontype .typeofbox').click(function(){
					    	  		
					    	  			 if(!$(this).hasClass("active")){
					    	  				 $('#professiontype .typeofbox').each(function(){
					    	  					 if($(this).hasClass("active")){
					    	  					  var actsrc = $(this).children("img").attr("data-active-src");
					    	  					  var normalsrc = $(this).children("img").attr("src");
					    	  					 $(this).children("img").attr("src",actsrc);
					    	  					  $(this).children("img").attr("data-active-src",normalsrc);
					    	  					 }
					    	  				 });
					    	  				 
					    	  				 $('#professiontype .typeofbox').removeClass("active");
					    	  				 $(this).addClass("active");
					    	  				 var actsrc = $(this).children("img").attr("data-active-src");
					    	  				  var normalsrc = $(this).children("img").attr("src");
					    	  				 $(this).children("img").attr("src",actsrc);
					    	  				  $(this).children("img").attr("data-active-src",normalsrc);
					    	  			 }
					    	  			  $(this).children("input").attr("checked","checked");
					    	  			
					    	  			  var custype = $(this).children("input").val();
					    	  		
					    	  			 if(custype=="New Customer"){
					    	  				 $(".customerText").hide();
					    	  				 $("#NewCustomer").show();
					    	  				 $('#professiontype ~ .errorMian').hide();
					    	  			 }else if(custype=="Existing Customer"){
					    	  				 $(".customerText").hide();
					    	  				 $("#ExsitingCustomer").show();
					    	  				 $('#professiontype ~ .errorMian').hide();
					    	  			 }else if(custype=="Bajaj Employee"){
					    	  				 $(".customerText").hide();
					    	  				 $("#GroupEmply").show();
					    	  				 $('#professiontype ~ .errorMian').hide();
					    	  			 }else if(custype=="Senior Citizen"){
					    	  				 $(".customerText").hide();
					    	  				 $("#SrCitizen").show();
					    	  				 $('#professiontype ~ .errorMian').hide();
					    	  			 }else{
					    	  				  $('#professiontype ~ .errorMian').show();
					    	  			 }
					    	  			 FDloanCalc();
					    	  		});
					    	  	    
					    	  	    
					    	  	    $("input[name='Profession']").focus(function(){
					    	  			$(this).parent(".typeofbox").css({"color":"#007aff","border-color":"#007aff","box-shadow":"0 0 3px #007aff"});
					    	  		});
					    	  		
					    	  		$("input[name='Profession']").blur(function(){
					    	  		   $(this).parent(".typeofbox").removeAttr("style");
					    	  		});
					    	  		 
					    	  		 
					    	  	    
					    	  	/*    $('#FDinvestAmount').slider().on('slideStop', function(ev){
					    	  	       FDloanCalc();
					    	  	    });
					    	  	    
					    	  	    $('#FDtenor').slider().on('slideStop', function(ev){
					    	  	       FDloanCalc();
					    	  	    });*/
					    	  	    
					    	  	    
					    	  	         
					    	  	  var monthArry = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"];
					    	  		
					    	  		
					    	  		function FDloanCalc(){  
					    	  	    
					    	  		var vintr = 0; 
					    	  		//var custyp = "ExsitingCustomer"; 
					    	  		
					    	  		
					    	  		var tnr;
					    	  	    var interestPay;
					    	  		 
					    	  		 var loanAmnt =	$('.sliderAmunt').val();
					    	  	     loanAmnt = loanAmnt.replace(/\,/g,'');
					    	  		 loanAmnt = Number(loanAmnt);
					    	  		 vAmt = parseInt(loanAmnt);

					    	  	     tnr = $('input[name="tenor"]:checked').val();
					    	  	    if (tnr == 'Custom' || tnr == undefined) {
					    	  	       tnr = $('.customTenor').val();
					    	  	    }
					    	  	        interestPay = $('input[name="payOut"]:checked').val();
					    	  	     var custyp = custTypeFD;

					    	  						    	  	     				    	  						    	  						    	  						    	  		        if (custyp == "ETB" || custyp == "SSODEDUPE") {
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		            custyp = "ExsitingCustomer";
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		        }
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		        if (custyp == "STB") {
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		            custyp = "SrCitizen";
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		        }
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		        if (custyp == "CKYCETB" || custyp == "NTBDocumentUpload") {
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		            custyp = "NewCustomer";
					    	  						    	  						    	  						    	  						    	  						    	  						    	  		        }

					    	  						    	  						    	  						    	  						    	  						    	  						    	  		        console.log(custyp);

					    	  	      
					    	  		
					    	  		if(custyp=="NewCustomer"){
					    	  	        if(tnr>=12 && tnr<15){
					    	  	             vintr = 7.40;
					    	  			}else if(tnr>=15 && tnr<24){
					    	  				
					    	  				if(tnr == 15){
					    	  					  vintr = 7.45;
					    	  				}else if(tnr == 18){
					    	  					  vintr = 7.40;
					    	  				}else if(tnr == 22){
					    	  					  vintr = 7.50;
					    	  				}else{	
					    	  				    vintr = 7.50;
					    	  				}
					    	  				
					    	  			}else if(tnr>=24 && tnr<36){
					    	  	            
					    	  				if(tnr == 24){
					    	  					vintr = 7.55;
					    	  				}else if(tnr == 30){
					    	  					vintr = 7.45;
					    	  				}else if(tnr == 33){
					    	  					vintr = 7.75;
					    	  				}else{
					    	  					vintr = 7.35;
					    	  				
					    	  				}
					    	  				
					    	  			}else if(tnr>=36 && tnr<61){
					    	  				
					    	  				if(tnr == 44){
					    	  					vintr = 7.95;
					    	  				}else{
					    	  					vintr = 7.65;
					    	  				}
					    	  			}
					    	  		}else if(custyp=="ExsitingCustomer"){
					    	  			if(tnr>=12 && tnr<15){
					    	  	             vintr = 7.40;
					    	  			}else if(tnr>=15 && tnr<24){
					    	  				
					    	  				if(tnr == 15){
					    	  					  vintr = 7.45;
					    	  				}else if(tnr == 18){
					    	  					  vintr = 7.40;
					    	  				}else if(tnr == 22){
					    	  					  vintr = 7.50;
					    	  				}else{	
					    	  				    vintr = 7.50;
					    	  				}
					    	  				
					    	  			}else if(tnr>=24 && tnr<36){
					    	  	            
					    	  				if(tnr == 24){
					    	  					vintr = 7.55;
					    	  				}else if(tnr == 30){
					    	  					vintr = 7.45;
					    	  				}else if(tnr == 33){
					    	  					vintr = 7.75;
					    	  				}else{
					    	  					vintr = 7.35;
					    	  				
					    	  				}
					    	  				
					    	  			}else if(tnr>=36 && tnr<61){
					    	  				
					    	  				if(tnr == 44){
					    	  					vintr = 7.95;
					    	  				}else{
					    	  					vintr = 7.65;
					    	  				}
					    	  			}
					    	  		}else if(custyp=="GroupEmply"){
					    	  			if(tnr>=12 && tnr<24){
					    	  				//vintr = 8.25;
					    	  				vintr = 7.50;
					    	  			}else if(tnr>=24 && tnr<36){
					    	  				vintr = 7.55;
					    	  			}else if(tnr>=36 && tnr<48){
					    	  				vintr = 7.60;
					    	  			}else if(tnr>=48 && tnr<61){
					    	  				vintr = 7.70;
					    	  			}
					    	  		}else if(custyp=="SrCitizen"){
					    	  			if(tnr>=12 && tnr<15){
					    	  	             vintr = 7.65;
					    	  			}else if(tnr>=15 && tnr<24){
					    	  				if(tnr == 15){
					    	  					  vintr = 7.70;
					    	  				}else if(tnr == 18){
					    	  					  vintr = 7.65;
					    	  				}else if(tnr == 22){
					    	  					  vintr = 7.75;
					    	  				}else{
					    	  				   vintr = 7.75;
					    	  				}
					    	  				
					    	  			}else if(tnr>=24 && tnr<36){
					    	  				
					    	  				if(tnr == 24){
					    	  					vintr = 7.80;
					    	  				}else if(tnr == 30){
					    	  					vintr = 7.70;
					    	  				}else if(tnr == 33){
					    	  					vintr = 8.00;
					    	  				}else{
					    	  					vintr = 7.60;
					    	  				
					    	  				}
					    	  			}else if(tnr>=36 && tnr<61){
					    	  				if(tnr == 44){
					    	  					vintr = 8.20;
					    	  				}else{
					    	  					vintr = 7.90;
					    	  				}
					    	  			}
					    	  		}
					    	  		
					    	  		if(tnr>=12){
					    	  			 var crntdate = new Date(); 
					    	  	         crntdate.setMonth(parseInt(crntdate.getMonth()) + parseInt(tnr));
					    	  			 var maturityformat = monthArry[crntdate.getMonth()] +" "+ crntdate.getFullYear();
					    	  			 $("#FDmaturityDate").text(maturityformat);
					    	  		}
					    	  	    vintr = vintr.toFixed(2);
					    	  		
					    	  		 $(".customerText p").text(vintr+"%");
					    	  		    $("#FDInterestRate").text(vintr+"%");
					    	  	        
					    	  	        //alert(vintr);
					    	  		   
					    	  		  if(vAmt>0){
					    	  		
					    	  			 var FDtenor = tnr;
					    	  			 var FDtenor_2 = 60;
					    	  			 var  FDTotal_2, FDTotal, TotalIntEarn;
					    	  			
					    	  			vmtor = FDtenor / 12;
					    	  			vmtor_2 = FDtenor_2 / 12;
					    	  			vintr = (vintr / 100);
					    	  	          
					    	  	          var vintr_2 = (7.20 / 100);
					    	  		   
					    	  			if (vAmt < 15000) {
					    	  				$('#FDmaturityAmnt').text('--'); /* Maturity Amount */
					    	  				$('.a_greatCamnt').text('--'); /* Maturity Amount */
					    	  				$('.a_fdTenorgreatChose').text('--'); /* Maturity Amount */
					    	  				$('#InterestFD').text('--'); /* Interest Amount */
					    	  				$('#growFDamt').text('--'); /* Interest Amount */
					    	  			
					    	  			}else if (vAmt > 50000000) {
					    	  				$('#FDmaturityAmnt').text('--'); /* Maturity Amount */
					    	  				$('.a_greatCamnt').text('--'); /* Maturity Amount */
					    	  				$('.a_fdTenorgreatChose').text('--'); /* Maturity Amount */
					    	  				$('#InterestFD').text('--'); /* Interest Amount */
					    	  				$('#growFDamt').text('--'); /* Interest Amount */

					    	  			}else{ 
					    	  				FDTotal = vAmt * Math.pow(((1 + vintr)), vmtor); 
					    	  	            //alert(vmtor);
					    	  	            /*FDTotal = FDTotal.toFixed(2);
					    	  	            console.log(FDTotal);*/
					    	  	            
					    	  				FDTotal = Math.round(FDTotal);
					    	  				
					    	  	            
					    	  	            FDTotal_2 = vAmt * Math.pow(((1 + vintr_2)), vmtor_2);  
					    	  				FDTotal_2 = Math.round(FDTotal_2);
					    	  				TotalIntEarn = FDTotal - vAmt;
					    	  	             
					    	  	            //console.log(FDTotal_2);
					    	  	            
					    	  				$('#FDmaturityAmnt').html('<i class="fas fa-rupee"></i> '+inrFormat(FDTotal));
					    	  	            $('#hiddenPayOutTypeCheck').val('');
					    	  				$('#growFDamt').html('Grow your savings upto <i class="fas fa-rupee"></i> '+inrFormat(FDTotal_2));
					    	  				$('.a_greatCamnt').html('See your savings grow upto  <i class="fas fa-rupee"></i> '+inrFormat(FDTotal));
					    	  				$('.a_fdTenorgreatChose').html('You are getting the best returns on your deposit   <i class="fas fa-rupee"></i> '+inrFormat(FDTotal));
					    	  				$('#InterestFD').html('<i class="fas fa-rupee"></i> '+inrFormat(TotalIntEarn));
					    	  	            $('.pDeposit').html('<i class="fas fa-rupee"></i> ' + inrFormat(vAmt));
					    	  						    	  						    	  						    	  						    	  				$('.totalAmount').html('<i class="fas fa-rupee"></i> ' + inrFormat(FDTotal));
					    	  	            //alert(TotalIntEarn);
					    	  	            $('.intPay').text('('+interestPay+')'); 
					    	  	            if (tnr == 33) {
					    	  						    	  						    	  						    	  						    	  						    	  							    						    $('.a_fdTenorgreatChose1').html('Choose more!');
					    	  						    	  						    	  						    	  						    	  						    	  							    						    $('.a_fdTenorgreatChose2').html('Invest for 44 months to earn the highest returns.');
					    	  						    	  						    	  						    	  						    	  						    	  							    						} else if (tnr == 44) {
					    	  						    	  						    	  						    	  						    	  						    	  							    						    $('.a_fdTenorgreatChose1').html('Great choice!');
					    	  						    	  						    	  						    	  						    	  						    	  							    						    $('.a_fdTenorgreatChose2').html(' Choosing this tenor will help you earn the highest returns on your deposit.');
					    	  						    	  						    	  						    	  						    	  						    	  							    						} else if (tnr == 60) {
					    	  						    	  						    	  						    	  						    	  						    	  							    						    $('.a_fdTenorgreatChose').html('Nice! by choosing this tenor, you will earn Rs. ' + inrFormat(TotalIntEarn) + ' as a total payout on your deposit.');
					    	  						    	  						    	  						    	  						    	  						    	  							    						} else {
					    	  						    	  						    	  						    	  						    	  						    	  							    						    $('.a_fdTenorgreatChose1').html('More is better!');
					    	  						    	  						    	  						    	  						    	  						    	  							    						    $('.a_fdTenorgreatChose2').html('Stay invested for 60 months to get maximum returns');
					    	  						    	  						    	  						    	  						    	  						    	  							    						}
					    	  			}
					    	  			
					    	  		   var pr1 =0;
					    	  		   var pr2 =0;
					    	  		   var pr3 =0;
					    	  		   var pr4 =0;

					    	  	          
					    	  	        var ncbasepr1 = 0;
					    	  			var ncbasepr2 = 0;
					    	  			var ncbasepr3 = 0;
					    	  			var ncbasepr3_1 = 0;
					    	  			
					    	  			var ncbasepr4 = 0;
					    	  			var ncbasepr5 = 0;
					    	  			var ncbasepr6 = 0;
					    	  			var ncbasepr6_1 = 0;
					    	  			
					    	  			var ncbasepr7 = 0;
					    	  			var ncbasepr8 = 0;
					    	  			var ncbasepr9 = 0;
					    	  			var ncbasepr9_1 = 0;
					    	  			
					    	  			var ncbasepr10 = 0;
					    	  			var ncbasepr11 = 0;
					    	  			var ncbasepr12 = 0;
					    	  			var ncbasepr12_1 = 0;
					    	  		
					    	  		   if(custyp=="NewCustomer"){
					    	  			   
					    	  			    if(tnr>=12 && tnr<15){
					    	  	                pr1 = 7.16;
					    	  			    }else if(tnr>=15 && tnr<24){
					    	  					 
					    	  	                if(tnr == 15){
					    	  	                    pr1 = 7.21;
					    	  	                }else if(tnr == 18){
					    	  	                    pr1 = 7.16;
					    	  	                }else if(tnr == 22){
					    	  	                    pr1 = 7.25;
					    	  	                }else{
					    	  	                    pr1 = 7.25;   
					    	  	                }
					    	  	                
					    	  				}else if(tnr>=24 && tnr<36){
					    	  	                
					    	  	                if(tnr == 24){
					    	  	                    pr1 = 7.30;
					    	  	                }else if(tnr == 30){
					    	  	                    pr1 = 7.21;
					    	  	                }else if(tnr == 33){
					    	  	                    pr1 = 7.49;
					    	  	                }else{
					    	  	                    pr1 = 7.35;   
					    	  	                }
					    	  	                
					    	  				}else if(tnr>=36 && tnr<61){
					    	  	                
					    	  	                if(tnr == 44){
					    	  	                    pr1 = 7.67;
					    	  	                }else{
					    	  	                    pr1 = 7.39;  
					    	  	                }
					    	  					 
					    	  				}
					    	  				
					    	  				if(tnr>=12 && tnr<15){
					    	  	                pr2 = 7.20;
					    	  			    }else if(tnr>=15 && tnr<24){
					    	  	                
					    	  	                if(tnr == 15){
					    	  	                    pr2 = 7.25;
					    	  	                }else if(tnr == 18){
					    	  	                    pr2 = 7.20;
					    	  	                }else if(tnr == 22){
					    	  	                    pr2 = 7.30;
					    	  	                }else{
					    	  	                    pr2 = 7.30;   
					    	  	                }
					    	  					 
					    	  				}else if(tnr>=24 && tnr<36){
					    	  	                
					    	  	                if(tnr == 24){
					    	  	                    pr2 = 7.35;
					    	  	                }else if(tnr == 30){
					    	  	                    pr2 = 7.25;
					    	  	                }else if(tnr == 33){
					    	  	                    pr2 = 7.53;
					    	  	                }else{
					    	  	                    pr2 = 7.11;   
					    	  	                }
					    	  					 
					    	  				}else if(tnr>=36 && tnr<61){
					    	  	                
					    	  	                if(tnr == 44){
					    	  	                    pr2 = 7.72;
					    	  	                }else{
					    	  	                    pr2 = 7.44;   
					    	  	                }
					    	  					 
					    	  				}
					    	  				
					    	  				if(tnr>=12 && tnr<15){
					    	  	                pr3 = 7.27;
					    	  			    }else if(tnr>=15 && tnr<24){
					    	  	                
					    	  	                if(tnr == 15){
					    	  	                    pr3 = 7.32;
					    	  	                }else if(tnr == 18){
					    	  	                    pr3 = 7.27;
					    	  	                }else if(tnr == 22){
					    	  	                    pr3 = 7.36;
					    	  	                }else{
					    	  	                    pr3 = 7.36;  
					    	  	                }
					    	  					 
					    	  				}else if(tnr>=24 && tnr<36){
					    	  	                
					    	  	                if(tnr == 24){
					    	  	                    pr3 = 7.41;
					    	  	                }else if(tnr == 30){
					    	  	                    pr3 = 7.32;
					    	  	                }else if(tnr == 33){
					    	  	                    pr3 = 7.61;
					    	  	                }else{
					    	  	                    pr3 = 7.22;
					    	  	                }
					    	  					 
					    	  				}else if(tnr>=36 && tnr<61){
					    	  	                
					    	  	                if(tnr == 44){
					    	  	                    pr3 = 7.80;
					    	  	                }else{
					    	  	                    pr3 = 7.51;
					    	  	                }
					    	  					 
					    	  				}
					    	  				
					    	  				if(tnr>=12 && tnr<15){
					    	  	                pr4 = 7.40;
					    	  			    }else if(tnr>=15 && tnr<24){
					    	  	                
					    	  	                if(tnr == 15){
					    	  	                    pr4 = 7.45;
					    	  	                }else if(tnr == 18){
					    	  	                    pr4 = 7.40;
					    	  	                }else if(tnr == 22){
					    	  	                    pr4 = 7.50;
					    	  	                }else{
					    	  	                    pr4 = 7.50;  
					    	  	                }
					    	  					 
					    	  				}else if(tnr>=24 && tnr<36){
					    	  	                
					    	  	                if(tnr == 24){
					    	  	                    pr4 = 7.55;
					    	  	                }else if(tnr == 30){
					    	  	                    pr4 = 7.45;
					    	  	                }else if(tnr == 33){
					    	  	                    pr4 = 7.75;
					    	  	                }else{
					    	  	                    pr4 = 7.35;  
					    	  	                }
					    	  					 
					    	  				}else if(tnr>=36 && tnr<61){
					    	  	                
					    	  	                if(tnr == 44){
					    	  	                    pr4 = 7.95;
					    	  	                }else{
					    	  	                    pr4 = 7.65;  
					    	  	                }
					    	  					 
					    	  				}
					    	  			   
					    	  	       }else if(custyp=="ExsitingCustomer"){
					    	  			   
					    	  			    if(tnr>=12 && tnr<15){
					    	  	                pr1 = 7.16;
					    	  			    }else if(tnr>=15 && tnr<24){
					    	  					 
					    	  	                if(tnr == 15){
					    	  	                    pr1 = 7.21;
					    	  	                }else if(tnr == 18){
					    	  	                    pr1 = 7.16;
					    	  	                }else if(tnr == 22){
					    	  	                    pr1 = 7.25;
					    	  	                }else{
					    	  	                    pr1 = 7.25;   
					    	  	                }
					    	  	                
					    	  				}else if(tnr>=24 && tnr<36){
					    	  	                
					    	  	                if(tnr == 24){
					    	  	                    pr1 = 7.30;
					    	  	                }else if(tnr == 30){
					    	  	                    pr1 = 7.21;
					    	  	                }else if(tnr == 33){
					    	  	                    pr1 = 7.49;
					    	  	                }else{
					    	  	                    pr1 = 7.35;   
					    	  	                }
					    	  	                
					    	  				}else if(tnr>=36 && tnr<61){
					    	  	                
					    	  	                if(tnr == 44){
					    	  	                    pr1 = 7.67;
					    	  	                }else{
					    	  	                    pr1 = 7.39;  
					    	  	                }
					    	  					 
					    	  				}
					    	  				
					    	  				if(tnr>=12 && tnr<15){
					    	  	                pr2 = 7.20;
					    	  			    }else if(tnr>=15 && tnr<24){
					    	  	                
					    	  	                if(tnr == 15){
					    	  	                    pr2 = 7.25;
					    	  	                }else if(tnr == 18){
					    	  	                    pr2 = 7.20;
					    	  	                }else if(tnr == 22){
					    	  	                    pr2 = 7.30;
					    	  	                }else{
					    	  	                    pr2 = 7.30;   
					    	  	                }
					    	  					 
					    	  				}else if(tnr>=24 && tnr<36){
					    	  	                
					    	  	                if(tnr == 24){
					    	  	                    pr2 = 7.35;
					    	  	                }else if(tnr == 30){
					    	  	                    pr2 = 7.25;
					    	  	                }else if(tnr == 33){
					    	  	                    pr2 = 7.53;
					    	  	                }else{
					    	  	                    pr2 = 7.11;   
					    	  	                }
					    	  					 
					    	  				}else if(tnr>=36 && tnr<61){
					    	  	                
					    	  	                if(tnr == 44){
					    	  	                    pr2 = 7.72;
					    	  	                }else{
					    	  	                    pr2 = 7.44;   
					    	  	                }
					    	  					 
					    	  				}
					    	  				
					    	  				if(tnr>=12 && tnr<15){
					    	  	                pr3 = 7.27;
					    	  			    }else if(tnr>=15 && tnr<24){
					    	  	                
					    	  	                if(tnr == 15){
					    	  	                    pr3 = 7.32;
					    	  	                }else if(tnr == 18){
					    	  	                    pr3 = 7.27;
					    	  	                }else if(tnr == 22){
					    	  	                    pr3 = 7.36;
					    	  	                }else{
					    	  	                    pr3 = 7.36;  
					    	  	                }
					    	  					 
					    	  				}else if(tnr>=24 && tnr<36){
					    	  	                
					    	  	                if(tnr == 24){
					    	  	                    pr3 = 7.41;
					    	  	                }else if(tnr == 30){
					    	  	                    pr3 = 7.32;
					    	  	                }else if(tnr == 33){
					    	  	                    pr3 = 7.61;
					    	  	                }else{
					    	  	                    pr3 = 7.22;
					    	  	                }
					    	  					 
					    	  				}else if(tnr>=36 && tnr<61){
					    	  	                
					    	  	                if(tnr == 44){
					    	  	                    pr3 = 7.80;
					    	  	                }else{
					    	  	                    pr3 = 7.51;
					    	  	                }
					    	  					 
					    	  				}
					    	  				
					    	  				if(tnr>=12 && tnr<15){
					    	  	                pr4 = 7.40;
					    	  			    }else if(tnr>=15 && tnr<24){
					    	  	                
					    	  	                if(tnr == 15){
					    	  	                    pr4 = 7.45;
					    	  	                }else if(tnr == 18){
					    	  	                    pr4 = 7.40;
					    	  	                }else if(tnr == 22){
					    	  	                    pr4 = 7.50;
					    	  	                }else{
					    	  	                    pr4 = 7.50;  
					    	  	                }
					    	  					 
					    	  				}else if(tnr>=24 && tnr<36){
					    	  	                
					    	  	                if(tnr == 24){
					    	  	                    pr4 = 7.55;
					    	  	                }else if(tnr == 30){
					    	  	                    pr4 = 7.45;
					    	  	                }else if(tnr == 33){
					    	  	                    pr4 = 7.75;
					    	  	                }else{
					    	  	                    pr4 = 7.35;  
					    	  	                }
					    	  					 
					    	  				}else if(tnr>=36 && tnr<61){
					    	  	                
					    	  	                if(tnr == 44){
					    	  	                    pr4 = 7.95;
					    	  	                }else{
					    	  	                    pr4 = 7.65;  
					    	  	                }
					    	  					 
					    	  				}
					    	  			 
					    	  	       }else if(custyp=="GroupEmply"){
					    	  			    if(tnr>=12 && tnr<24){
					    	  					 pr1 = ncbasepr1+0.09;
					    	  				}else if(tnr>=24 && tnr<36){
					    	  					 pr1 = ncbasepr2+0.09;
					    	  				}else if(tnr>=36 && tnr<48){
					    	  					 pr1 = ncbasepr3+0.10;
					    	  				}else if(tnr>=48 && tnr<61){
					    	  					 pr1 = ncbasepr3_1+0.09;
					    	  				}
					    	  				
					    	  				if(tnr>=12 && tnr<24){
					    	  					 pr2 = ncbasepr4+0.10;
					    	  				}else if(tnr>=24 && tnr<36){
					    	  					 pr2 = ncbasepr5+0.10;
					    	  				}else if(tnr>=36 && tnr<48){
					    	  					 pr2 = ncbasepr6+0.09;
					    	  				}else if(tnr>=48 && tnr<61){
					    	  					 pr2 = ncbasepr6_1+0.10;
					    	  				}
					    	  				
					    	  				if(tnr>=12 && tnr<24){
					    	  					 pr3 = ncbasepr7+0.09;
					    	  				}else if(tnr>=24 && tnr<36){
					    	  					 pr3 = ncbasepr8+0.09;
					    	  				}else if(tnr>=36 && tnr<48){
					    	  					 pr3 = ncbasepr9+0.10;
					    	  				}else if(tnr>=48 && tnr<61){
					    	  					 pr3 = ncbasepr9_1+0.10;
					    	  				}
					    	  				
					    	  				if(tnr>=12 && tnr<24){
					    	  					 pr4 = ncbasepr10+0.10;
					    	  				}else if(tnr>=24 && tnr<36){
					    	  					 pr4 = ncbasepr11+0.10;
					    	  				}else if(tnr>=36 && tnr<48){
					    	  					 pr4 = ncbasepr12+0.10;
					    	  				}else if(tnr>=48 && tnr<61){
					    	  					 pr4 = ncbasepr12_1+0.10;
					    	  				}
					    	  			   
					    	  		   }else if(custyp=="SrCitizen"){
					    	  			   
					    	  			    if(tnr>=12 && tnr<15){
					    	  	                pr1 = 7.39;
					    	  			    }else if(tnr>=15 && tnr<24){
					    	  					 
					    	  	                if(tnr == 15){
					    	  	                    pr1 = 7.44;
					    	  	                }else if(tnr == 18){
					    	  	                    pr1 = 7.39;
					    	  	                }else if(tnr == 22){
					    	  	                    pr1 = 7.49;
					    	  	                }else{
					    	  	                    pr1 = 7.49;   
					    	  	                }
					    	  	                
					    	  				}else if(tnr>=24 && tnr<36){
					    	  	                
					    	  	                if(tnr == 24){
					    	  	                    pr1 = 7.53;
					    	  	                }else if(tnr == 30){
					    	  	                    pr1 = 7.44;
					    	  	                }else if(tnr == 33){
					    	  	                    pr1 = 7.72;
					    	  	                }else{
					    	  	                    pr1 = 7.35;   
					    	  	                }
					    	  	                
					    	  				}else if(tnr>=36 && tnr<61){
					    	  	                
					    	  	                if(tnr == 44){
					    	  	                    pr1 = 7.91;
					    	  	                }else{
					    	  	                    pr1 = 7.63;  
					    	  	                }
					    	  					 
					    	  				}
					    	  				
					    	  				if(tnr>=12 && tnr<15){
					    	  	                pr2 = 7.44;
					    	  			    }else if(tnr>=15 && tnr<24){
					    	  	                
					    	  	                if(tnr == 15){
					    	  	                    pr2 = 7.49;
					    	  	                }else if(tnr == 18){
					    	  	                    pr2 = 7.44;
					    	  	                }else if(tnr == 22){
					    	  	                    pr2 = 7.53;
					    	  	                }else{
					    	  	                    pr2 = 7.53;   
					    	  	                }
					    	  					 
					    	  				}else if(tnr>=24 && tnr<36){
					    	  	                
					    	  	                if(tnr == 24){
					    	  	                    pr2 = 7.58;
					    	  	                }else if(tnr == 30){
					    	  	                    pr2 = 7.49;
					    	  	                }else if(tnr == 33){
					    	  	                    pr2 = 7.77;
					    	  	                }else{
					    	  	                    pr2 = 7.39;   
					    	  	                }
					    	  					 
					    	  				}else if(tnr>=36 && tnr<61){
					    	  	                
					    	  	                if(tnr == 44){
					    	  	                    pr2 = 7.96;
					    	  	                }else{
					    	  	                    pr2 = 7.68;   
					    	  	                }
					    	  					 
					    	  				}
					    	  				
					    	  				if(tnr>=12 && tnr<15){
					    	  	                pr3 = 7.51;
					    	  			    }else if(tnr>=15 && tnr<24){
					    	  	                
					    	  	                if(tnr == 15){
					    	  	                    pr3 = 7.56;
					    	  	                }else if(tnr == 18){
					    	  	                    pr3 = 7.51;
					    	  	                }else if(tnr == 22){
					    	  	                    pr3 = 7.61;
					    	  	                }else{
					    	  	                    pr3 = 7.61;  
					    	  	                }
					    	  					 
					    	  				}else if(tnr>=24 && tnr<36){
					    	  	                
					    	  	                if(tnr == 24){
					    	  	                    pr3 = 7.65;
					    	  	                }else if(tnr == 30){
					    	  	                    pr3 = 7.56;
					    	  	                }else if(tnr == 33){
					    	  	                    pr3 = 7.85;
					    	  	                }else{
					    	  	                    pr3 = 7.46;
					    	  	                }
					    	  					 
					    	  				}else if(tnr>=36 && tnr<61){
					    	  	                
					    	  	                if(tnr == 44){
					    	  	                    pr3 = 8.04;
					    	  	                }else{
					    	  	                    pr3 = 7.75;
					    	  	                }
					    	  					 
					    	  				}
					    	  				
					    	  				if(tnr>=12 && tnr<15){
					    	  	                pr4 = 7.65;
					    	  			    }else if(tnr>=15 && tnr<24){
					    	  	                
					    	  	                if(tnr == 15){
					    	  	                    pr4 = 7.70;
					    	  	                }else if(tnr == 18){
					    	  	                    pr4 = 7.65;
					    	  	                }else if(tnr == 22){
					    	  	                    pr4 = 7.75;
					    	  	                }else{
					    	  	                    pr4 = 7.75;  
					    	  	                }
					    	  					 
					    	  				}else if(tnr>=24 && tnr<36){
					    	  	                
					    	  	                if(tnr == 24){
					    	  	                    pr4 = 7.80;
					    	  	                }else if(tnr == 30){
					    	  	                    pr4 = 7.70;
					    	  	                }else if(tnr == 33){
					    	  	                    pr4 = 8.00;
					    	  	                }else{
					    	  	                    pr4 = 7.60;  
					    	  	                }
					    	  					 
					    	  				}else if(tnr>=36 && tnr<61){
					    	  	                
					    	  	                if(tnr == 44){
					    	  	                    pr4 = 8.20;
					    	  	                }else{
					    	  	                    pr4 = 7.90;  
					    	  	                }
					    	  					 
					    	  				}
					    	  			   
					    	  			   
					    	  	       }	   
					    	  		   
					    	  		   
					    	  			pr1 = pr1.toFixed(2);
					    	  			pr2 = pr2.toFixed(2);
					    	  			pr3 = pr3.toFixed(2);
					    	  			pr4 = pr4.toFixed(2);

					    	  			
					    	  			if(interestPay == 'Monthly'){ 
					    	  	            $('#FDInterestRate').text(pr1+"%");
					    	  	            
					    	  	            var  FDTotal1, TotalIntEarn1;
					    	  	            pr1 = (pr1 / 100); 
					    	  			   FDTotal1 = vAmt *pr1;
					    	  	            
					    	  	            var maturityAmt = vAmt * (pr1 / 12);
					    	  	            maturityAmt = Math.round(maturityAmt);
					    	  	            
					    	  	            maturityAmt = maturityAmt.toString().split(".")[0];
					    	  	            $('#InterestFD').html('<i class="fas fa-rupee"></i> '+inrFormat(maturityAmt)); 
					    	  	            FDTotal1 = Math.round(FDTotal1); 
					    	  			   TotalIntEarn1 = FDTotal1 * vmtor;
					    	  	            
					    	  			   TotalIntEarn1 = TotalIntEarn1.toString().split(".")[0]; 
					    	  	            var intAmt = Number(vAmt)  + Number(TotalIntEarn1);
					    	  	            $('#FDmaturityAmnt').html('<i class="fas fa-rupee"></i> '+inrFormat(intAmt));
					    	  	            
					    	  	            $('.intPay').text('('+interestPay+')');
					    	  	             $('#hiddenPayOutTypeCheck').val('');
					    	  				$('#hiddenPayOutTypeCheck').val(inrFormat(intAmt));
					    	  	        }else if(interestPay == 'Quarterly'){
					    	  	            $('#FDInterestRate').text(pr2+"%");
					    	  	            
					    	  	            var  FDTotal2, TotalIntEarn2;
					    	  	            pr2 = (pr2 / 100); 
					    	  			    FDTotal2 = vAmt * pr2; 
					    	  	            
					    	  	            var maturityAmt = vAmt * (pr2 / 4);
					    	  	            maturityAmt = Math.round(maturityAmt);
					    	  	            
					    	  	            maturityAmt = maturityAmt.toString().split(".")[0];
					    	  	            $('#InterestFD').html('<i class="fas fa-rupee"></i> '+inrFormat(maturityAmt));
					    	  	            
					    	  	            FDTotal2 = Math.round(FDTotal2); 
					    	  			   TotalIntEarn2 = FDTotal2 *vmtor;
					    	  			   TotalIntEarn2 = TotalIntEarn2.toString().split(".")[0];
					    	  	            
					    	  	            var intAmt = Number(vAmt)  + Number(TotalIntEarn2);
					    	  	            $('#FDmaturityAmnt').html('<i class="fas fa-rupee"></i> '+inrFormat(intAmt));
					    	  	            
					    	  	            $('.intPay').text('('+interestPay+')');
					    	  	            $('#hiddenPayOutTypeCheck').val('');
					    	  						    	  		    $('#hiddenPayOutTypeCheck').val(inrFormat(intAmt));
					    	  	        }else if(interestPay == 'HalfYearly'){
					    	  	            $('#FDInterestRate').text(pr3+"%");
					    	  	            
					    	  	            var  FDTotal3, TotalIntEarn3;
					    	  	            pr3 = (pr3 / 100);
					    	  			    FDTotal3 = vAmt *pr3;
					    	  	            var maturityAmt = vAmt * (pr3 / 2);
					    	  	            maturityAmt = Math.round(maturityAmt);
					    	  	            
					    	  	            maturityAmt = maturityAmt.toString().split(".")[0];
					    	  	            $('#InterestFD').html('<i class="fas fa-rupee"></i> '+inrFormat(maturityAmt));
					    	  	            FDTotal3 = Math.round(FDTotal3);
					    	  				 TotalIntEarn3 = FDTotal3 * vmtor;
					    	  	            TotalIntEarn3 = TotalIntEarn3.toString().split(".")[0];
					    	  	            
					    	  	            var intAmt = Number(vAmt)  + Number(TotalIntEarn3);
					    	  	            $('#FDmaturityAmnt').html('<i class="fas fa-rupee"></i> '+inrFormat(intAmt));
					    	  	            
					    	  	            $('.intPay').text('('+interestPay+')');
					    	  	            $('#hiddenPayOutTypeCheck').val('');
					    	  	            $('#hiddenPayOutTypeCheck').val(inrFormat(intAmt));
					    	  	            
					    	  	        }else if(interestPay == 'Annually'){
					    	  	            $('#FDInterestRate').text(pr4+"%");
					    	  	            
					    	  	            var  FDTotal4, TotalIntEarn4;
					    	  	            pr4 = (pr4 / 100);
					    	  			    FDTotal4 = vAmt * pr4;
					    	  			      
					    	  	            var maturityAmt = vAmt * (pr4 / 1);
					    	  	            maturityAmt = Math.round(maturityAmt);
					    	  	            
					    	  	            maturityAmt = maturityAmt.toString().split(".")[0];
					    	  	            $('#InterestFD').html('<i class="fas fa-rupee"></i> '+inrFormat(maturityAmt));
					    	  	            
					    	  	            FDTotal4 = Math.round(FDTotal4);
					    	  			
					    	  	            TotalIntEarn4 = FDTotal4 * vmtor;
					    	  	            TotalIntEarn4 = TotalIntEarn4.toString().split(".")[0];
					    	  	            
					    	  	            var intAmt = Number(vAmt)  + Number(TotalIntEarn4);
					    	  	            $('#FDmaturityAmnt').html('<i class="fas fa-rupee"></i> '+inrFormat(intAmt));
					    	  	            
					    	  	            $('.intPay').text('('+interestPay+')');
					    	  	            $('#hiddenPayOutTypeCheck').val('');
					    	  						    	  						    	  						    	  						    	  				$('#hiddenPayOutTypeCheck').val(inrFormat(intAmt));
					    	  	        }else if(interestPay == 'OnMaturity'){
					    	  	            $('#FDInterestRate').text(pr4+"%");
					    	  	            
					    	  	            var  FDTotal4, TotalIntEarn4;
					    	  	            pr4 = (pr4 / 100);
					    	  			    FDTotal4 = vAmt * pr4;
					    	  			      
					    	  	            FDTotal4 = Math.round(FDTotal4);
					    	  			
					    	  	            TotalIntEarn4 = FDTotal4 * vmtor;
					    	  	            TotalIntEarn4 = TotalIntEarn4.toString().split(".")[0];
					    	  	            // $('#InterestFD').html('<i class="fas fa-rupee"></i> '+inrFormat(TotalIntEarn4));
					    	  	            
					    	  	            
					    	  	            var intAmt = FDTotal;
					    	  	            $('#FDmaturityAmnt').html('<i class="fas fa-rupee"></i> '+inrFormat(intAmt));
					    	  	            
					    	  	            $('.intPay').text('('+interestPay+')');
					    	  	           
					    	  	            
					    	  	            var amtFTime = FDTotal - vAmt;
					    	  	            $('#InterestFD').html('<i class="fas fa-rupee"></i> '+inrFormat(amtFTime));
					    	  	             //console.log(amtFTime);
					    	  	        }
					    	  			
					    	  	    }
					    	  	        
					    	  	        
					    	  	        
					    	  	        
					    	  	        
					    	  	        
					    	  		
					    	  	      
					    	  	  }
					    	  		  
					    	  	    FDloanCalc();    
					    	  	}



					    	  	 

					    	  		

					    	  							    	     
					    	  	
					    	  		
					    		   		    
		    /***************************************************FD Calculator JS End Here****************************************/
			
			
			/***************************************************SDP calculator JS Start Here**************************************/
			



					    	  		 function inrFormat(x) {  
					    	  		          x=x.toString();
					    	  		          if (x.indexOf(',') > -1) {
					    	  		                x = x.replace(/\,/g,'');
					    	  		            }

					    	  		        var lastThree = x.replace(/\D/g, '').substring(x.length-3);
					    	  		        var otherNumbers = x.substring(0,x.length-3);
					    	  		        if(otherNumbers != ''){  lastThree = ',' + lastThree;}
					    	  		        var res = otherNumbers.replace(/\D/g, '').replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree;
					    	  		       return res;
					    	  		    }
					    	  			
					    	  			
					    	  			$(".slctfild").change(function(){
					    	  				 $(this).css("color","#2b2b2b");
					    	  			});
					    	  			
					    	  			
					    	  			if($(".FDcal").length>0){

					    	  		    $(".sliderAmunt_2").blur(function(){
					    	  		        SDPloanCalc();
					    	  		    });
					    	  		    $(".customTenor_2").blur(function(){
					    	  		        SDPloanCalc();
					    	  		    });
					    	  		    $(".p_relation").change(function(){
					    	  		        SDPloanCalc();
					    	  		    });
					    	  		 
					    	  		    $(".a_selectblock input[name='sdptenor']").change(function(){
					    	  		        $('.p_relation option[value=""] + option').prop('selected','selected');
					    	  		        SDPloanCalc();
					    	  		    });
					    	  		     $("#sdpCustomTenor").change(function(){
					    	  							    	  		 SDPloanCalc();
					    	  							    	  		 });    
					    	  		    $(".a_radiodeposit label input[name='maturitysdheme']").change(function(){   
					    	  		        $('.p_relation option[value=""] + option').prop('selected','selected');
					    	  		        $('.a_selectblock input[value="60"]').prop('checked','checked');
					    	  		       $('.a_monthCustom .customTenor_2').val(""); 
					    	  							    	  						    	  					    	  					    	  		        var maturityScheme = $('.a_radiodeposit label input[name="maturitysdheme"]:checked').val();	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  									  		if(maturityScheme=="Monthly Maturity"){	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  									  		maturityScheme = "Monthly Maturity";	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  									  		}else{	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  									  		maturityScheme = "Single Maturity";	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  									  		}	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  									  		if(maturityScheme=="Monthly Maturity")	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  									  		{	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  									  		$('#sdpTenOfEacDepText').html("Tenor Of Each Deposit");	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  									  		$("#tenorText").html("TENOR OF EACH DEPOSIT (MONTHS)");	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  									  		}else	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  									  		{	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  									  		$('#sdpTenOfEacDepText').html("Tenor");	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  									  		$("#tenorText").html("TENOR (MONTHS)");	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  									  		}  
					    	  		        SDPloanCalc();
					    	  		        
					    	  		        $('.a_monthCustom .customTenor_2').val(""); 
					    	  		    });
					    	  		    $(".sliderAmunt_2").change(function () {
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  				  							if(bankDetailsFlag)
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  				  							{
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  				  							$('select[name="sdpbankName"]').find('option:contains('+bankName+')').prop("selected",true);
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  				  							}else{
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  				  							$("#sdpnomeeni .a_radiodeposit_2 input").val('');
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  				  							}
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  				  							$("#sdpbankName").change();
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  				  							SDPloanCalc();
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  				  							});
					    	  		        
					    	  		        
					    	  		    $('.onewidral>h4').click(function(){
					    	  		        $(this).next().slideToggle(200);
					    	  		        $(this).toggleClass('active');
					    	  		    });
					    	  		    
					    	  		    	
					    	  			$('#professiontype .typeofbox').click(function(){
					    	  			
					    	  				 if(!$(this).hasClass("active")){
					    	  					 $('#professiontype .typeofbox').each(function(){
					    	  						 if($(this).hasClass("active")){
					    	  						  var actsrc = $(this).children("img").attr("data-active-src");
					    	  						  var normalsrc = $(this).children("img").attr("src");
					    	  						 $(this).children("img").attr("src",actsrc);
					    	  						  $(this).children("img").attr("data-active-src",normalsrc);
					    	  						 }
					    	  					 });
					    	  					 
					    	  					 $('#professiontype .typeofbox').removeClass("active");
					    	  					 $(this).addClass("active");
					    	  					 var actsrc = $(this).children("img").attr("data-active-src");
					    	  					  var normalsrc = $(this).children("img").attr("src");
					    	  					 $(this).children("img").attr("src",actsrc);
					    	  					  $(this).children("img").attr("data-active-src",normalsrc);
					    	  				 }
					    	  				  $(this).children("input").attr("checked","checked");
					    	  				
					    	  				  var custype = $(this).children("input").val();
					    	  			
					    	  				 if(custype=="New Customer"){
					    	  					 $(".customerText").hide();
					    	  					 $("#NewCustomer").show();
					    	  					 $('#professiontype ~ .errorMian').hide();
					    	  				 }else if(custype=="Existing Customer"){
					    	  					 $(".customerText").hide();
					    	  					 $("#ExsitingCustomer").show();
					    	  					 $('#professiontype ~ .errorMian').hide();
					    	  				 }else if(custype=="Bajaj Employee"){
					    	  					 $(".customerText").hide();
					    	  					 $("#GroupEmply").show();
					    	  					 $('#professiontype ~ .errorMian').hide();
					    	  				 }else if(custype=="Senior Citizen"){
					    	  					 $(".customerText").hide();
					    	  					 $("#SrCitizen").show();
					    	  					 $('#professiontype ~ .errorMian').hide();
					    	  				 }else{
					    	  					  $('#professiontype ~ .errorMian').show();
					    	  				 }
					    	  				 SDPloanCalc();
					    	  			});
					    	  		    
					    	  		    
					    	  		    $("input[name='Profession']").focus(function(){
					    	  				$(this).parent(".typeofbox").css({"color":"#007aff","border-color":"#007aff","box-shadow":"0 0 3px #007aff"});
					    	  			});
					    	  			
					    	  			$("input[name='Profession']").blur(function(){
					    	  			   $(this).parent(".typeofbox").removeAttr("style");
					    	  			});
					    	  			 
					    	  		 
					    	  		    
					    	  		         
					    	  		  var monthArry = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"];
					    	  			
					    	  			var vintrTwo = 0; 
					    	  		        var eachDeposite;
					    	  		        var vintr = 0;
					    	  			function SDPloanCalc(){ 
					    	  		         vintr = 0;  

					    	  							    	  	    var custyp = custTypeFD;
					    	  							    	  		
					    	  							    	  			if(custyp=="ETB" || custyp =="SSODEDUPE")
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  							  								{
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  							  							  custyp = "ExsitingCustomer";
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  							  								}
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  							  							if(custyp=="STB")
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  							  							{
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  							  							custyp = "SrCitizen";
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  							  							}
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  							  							if (custyp == "CKYCETB" || custyp == "NTBDocumentUpload") {
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  							  								custyp = "NewCustomer";
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  							  							}
					    	  							    	  						    	  					    	  					    	  							    	  	console.log(custyp); 
					    	  		    
					    	  			
					    	  			
					    	  			var tnr;
					    	  		    
					    	  		    var eachDeposite_2;
					    	  		        
					    	  			 var loanAmnt =	$('.sliderAmunt_2').val();
					    	  		        $('#sdpDepAmtmon').html('<i class="fas fa-rupee"></i> '+inrFormat(loanAmnt));
					    	  		     loanAmnt = loanAmnt.replace(/\,/g,'');
					    	  			 loanAmnt = Number(loanAmnt);
					    	  			 vAmt = parseInt(loanAmnt);

					    	  		     tnr = $('input[name="sdptenor"]:checked').val();
					    	  		        
					    	  		         
					    	  		    if(tnr == 'Custom'  || tnr == undefined ){
					    	  		       tnr = $('.customTenor_2').val();
					    	  		    }
					    	  		        $('#sdpTenOfEacDep').html(tnr);
					    	  		        eachDeposite = Number($('.p_relation').val()) + 1;
					    	  		        eachDeposite_2 = 48;
					    	  		        
					    	  		        $('#sdpNumOfDep').html(eachDeposite);
					    	  		        $('#sdpTotPriAmt').html('<i class="fas fa-rupee"></i> '+inrFormat(vAmt * eachDeposite));


					    	  		        
					    	  		        var maturityScheme = $('.a_radiodeposit label input[name="maturitysdheme"]:checked').val(); 
					    	  		        if(maturityScheme=="Monthly Maturity"){	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  										  		maturityScheme = "Monthly Maturity";	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  										  		}else{	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  										  		maturityScheme = "Single Maturity";	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  										  		}
					    	  		    
					    	  		      
					    	  			if(maturityScheme == 'Single Maturity'){
					    	  		        
					    	  		        
					    	  		        if($('.a_monthCustom .customTenor_2').val()){
					    	  		            if($('.a_monthCustom .customTenor_2').val() < 19){
					    	  		                $('.a_monthCustom .customTenor_2').val(19);
					    	  		                tnr = 19;
					    	  		            }else if($('.a_monthCustom .customTenor_2').val() > 60){
					    	  		                $('.a_monthCustom .customTenor_2').val(60);
					    	  		                tnr = 60;
					    	  		            }
					    	  		        }
					    	  		        
					    	  		        
					    	  		        if(custyp=="NewCustomer"){
					    	  		            
					    	  		            if(tnr>=12 && tnr<15){
					    	  		                vintr = 7.40;
					    	  		            }else if(tnr>=15 && tnr<24){
					    	  		                
					    	  		                if(tnr == 15){
					    	  		                    vintr = 7.45;
					    	  		                }else if(tnr == 18){
					    	  		                    vintr = 7.40;
					    	  		                }else if(tnr == 22){
					    	  		                    vintr = 7.50;
					    	  		                }else{
					    	  		                   vintr = 7.50;
					    	  		                }
					    	  		                
					    	  		            }else if(tnr>=24 && tnr<36){
					    	  		                
					    	  		                if(tnr == 24){
					    	  		                    vintr = 7.55;
					    	  		                }else if(tnr == 30){
					    	  		                    vintr = 7.45;
					    	  		                }else if(tnr == 33){
					    	  		                    vintr = 7.75;
					    	  		                }else{
					    	  		                   vintr = 7.35;
					    	  		                }
					    	  		                
					    	  		            }else if(tnr>=36 && tnr<61){
					    	  		                
					    	  		                if(tnr == 44){
					    	  		                    vintr = 7.95;
					    	  		                }else{
					    	  		                   vintr = 7.65;
					    	  		                }
					    	  		                
					    	  		            }
					    	  		        
					    	  		        }else if(custyp=="ExsitingCustomer"){
					    	  		            
					    	  		            if(tnr>=12 && tnr<15){
					    	  		                vintr = 7.40;
					    	  		            }else if(tnr>=15 && tnr<24){
					    	  		                
					    	  		                if(tnr == 15){
					    	  		                    vintr = 7.45;
					    	  		                }else if(tnr == 18){
					    	  		                    vintr = 7.40;
					    	  		                }else if(tnr == 22){
					    	  		                    vintr = 7.50;
					    	  		                }else{
					    	  		                   vintr = 7.50;
					    	  		                }
					    	  		                
					    	  		            }else if(tnr>=24 && tnr<36){
					    	  		                
					    	  		                if(tnr == 24){
					    	  		                    vintr = 7.55;
					    	  		                }else if(tnr == 30){
					    	  		                    vintr = 7.45;
					    	  		                }else if(tnr == 33){
					    	  		                    vintr = 7.75;
					    	  		                }else{
					    	  		                   vintr = 7.35;
					    	  		                }
					    	  		                
					    	  		            }else if(tnr>=36 && tnr<61){
					    	  		                
					    	  		                if(tnr == 44){
					    	  		                    vintr = 7.95;
					    	  		                }else{
					    	  		                   vintr = 7.65;
					    	  		                }
					    	  		                
					    	  		            }
					    	  		            
					    	  		            }else if(custyp=="GroupEmply"){
					    	  		            if(tnr>=12 && tnr<24){
					    	  		                vintr = 8.25;
					    	  		            }else if(tnr>=24 && tnr<36){
					    	  		                vintr = 8.40;
					    	  		            }else if(tnr>=36 && tnr<61){
					    	  		                vintr = 9.00;
					    	  		            }
					    	  		        }else if(custyp=="SrCitizen"){
					    	  		            
					    	  		            if(tnr>=12 && tnr<15){
					    	  		                vintr = 7.65;
					    	  		            }else if(tnr>=15 && tnr<24){
					    	  		                
					    	  		                if(tnr == 15){
					    	  		                    vintr = 7.70;
					    	  		                }else if(tnr == 18){
					    	  		                    vintr = 7.65;
					    	  		                }else if(tnr == 22){
					    	  		                    vintr = 7.75;
					    	  		                }else{
					    	  		                   vintr = 7.75;
					    	  		                }
					    	  		                
					    	  		            }else if(tnr>=24 && tnr<36){
					    	  		                
					    	  		                if(tnr == 24){
					    	  		                    vintr = 7.80;
					    	  		                }else if(tnr == 30){
					    	  		                    vintr = 7.70;
					    	  		                }else if(tnr == 33){
					    	  		                    vintr = 8.00;
					    	  		                }else{
					    	  		                   vintr = 7.60;
					    	  		                }
					    	  		                
					    	  		            }else if(tnr>=36 && tnr<61){
					    	  		                
					    	  		                if(tnr == 44){
					    	  		                    vintr = 8.20;
					    	  		                }else{
					    	  		                   vintr = 7.90;
					    	  		                }
					    	  		                
					    	  		            }
					    	  		            
					    	  		        }
					    	  		        
					    	  		        
					    	  		        $('.p_relation option').hide();
					    	  		        $('.showmonthly').hide(); 
					    	  		        //$('.a_monthCustom').hide();
					    	  		        
					    	  		        
					    	  		        
					    	  		        
					    	  		        
					    	  		 
					    	  		        
					    	  		        var singleMat = tnr - 13;
					    	  		        $('.p_relation option').each(function(){
					    	  		            if($(this).val() <= singleMat){
					    	  		                $(this).show();
					    	  		            }
					    	  		        });
					    	  		        
					    	  		        
					    	  		        
					    	  		        $('.onlyMonth').hide();
					    	  		        $('.onlySingal').show();
					    	  		    }else{
					    	  		        $('.onlyMonth').show();
					    	  		        $('.onlySingal').hide();
					    	  		        $('.showmonthly').show();
					    	  		        $('.p_relation option').show();
					    	  		          
					    	  		        
					    	  		        if(custyp=="NewCustomer"){
					    	  		            
					    	  		            if(tnr>=12 && tnr<15){
					    	  		                vintr = 7.40;
					    	  		            }else if(tnr>=15 && tnr<24){
					    	  		                
					    	  		                if(tnr == 15){
					    	  		                    vintr = 7.45;
					    	  		                }else if(tnr == 18){
					    	  		                    vintr = 7.40;
					    	  		                }else if(tnr == 22){
					    	  		                    vintr = 7.50;
					    	  		                }else{
					    	  		                   vintr = 7.50;
					    	  		                }
					    	  		                
					    	  		            }else if(tnr>=24 && tnr<36){
					    	  		                
					    	  		                if(tnr == 24){
					    	  		                    vintr = 7.55;
					    	  		                }else if(tnr == 30){
					    	  		                    vintr = 7.45;
					    	  		                }else if(tnr == 33){
					    	  		                    vintr = 7.75;
					    	  		                }else{
					    	  		                   vintr = 7.35;
					    	  		                }
					    	  		                
					    	  		            }else if(tnr>=36 && tnr<61){
					    	  		                
					    	  		                if(tnr == 44){
					    	  		                    vintr = 7.95;
					    	  		                }else{
					    	  		                   vintr = 7.65;
					    	  		                }
					    	  		                
					    	  		            }
					    	  		        
					    	  		        }else if(custyp=="ExsitingCustomer"){
					    	  		            
					    	  		            if(tnr>=12 && tnr<15){
					    	  		                vintr = 7.40;
					    	  		            }else if(tnr>=15 && tnr<24){
					    	  		                
					    	  		                if(tnr == 15){
					    	  		                    vintr = 7.45;
					    	  		                }else if(tnr == 18){
					    	  		                    vintr = 7.40;
					    	  		                }else if(tnr == 22){
					    	  		                    vintr = 7.50;
					    	  		                }else{
					    	  		                   vintr = 7.50;
					    	  		                }
					    	  		                
					    	  		            }else if(tnr>=24 && tnr<36){
					    	  		                
					    	  		                if(tnr == 24){
					    	  		                    vintr = 7.55;
					    	  		                }else if(tnr == 30){
					    	  		                    vintr = 7.45;
					    	  		                }else if(tnr == 33){
					    	  		                    vintr = 7.75;
					    	  		                }else{
					    	  		                   vintr = 7.35;
					    	  		                }
					    	  		                
					    	  		            }else if(tnr>=36 && tnr<61){
					    	  		                
					    	  		                if(tnr == 44){
					    	  		                    vintr = 7.95;
					    	  		                }else{
					    	  		                   vintr = 7.65;
					    	  		                }
					    	  		                
					    	  		            }
					    	  		            
					    	  		            }else if(custyp=="GroupEmply"){
					    	  		            if(tnr>=12 && tnr<24){
					    	  		                vintr = 8.25;
					    	  		            }else if(tnr>=24 && tnr<36){
					    	  		                vintr = 8.40;
					    	  		            }else if(tnr>=36 && tnr<61){
					    	  		                vintr = 9.00;
					    	  		            }
					    	  		        }else if(custyp=="SrCitizen"){
					    	  		            
					    	  		            if(tnr>=12 && tnr<15){
					    	  		                vintr = 7.65;
					    	  		            }else if(tnr>=15 && tnr<24){
					    	  		                
					    	  		                if(tnr == 15){
					    	  		                    vintr = 7.70;
					    	  		                }else if(tnr == 18){
					    	  		                    vintr = 7.65;
					    	  		                }else if(tnr == 22){
					    	  		                    vintr = 7.75;
					    	  		                }else{
					    	  		                   vintr = 7.75;
					    	  		                }
					    	  		                
					    	  		            }else if(tnr>=24 && tnr<36){
					    	  		                
					    	  		                if(tnr == 24){
					    	  		                    vintr = 7.80;
					    	  		                }else if(tnr == 30){
					    	  		                    vintr = 7.70;
					    	  		                }else if(tnr == 33){
					    	  		                    vintr = 8.00;
					    	  		                }else{
					    	  		                   vintr = 7.60;
					    	  		                }
					    	  		                
					    	  		            }else if(tnr>=36 && tnr<61){
					    	  		                
					    	  		                if(tnr == 44){
					    	  		                    vintr = 8.20;
					    	  		                }else{
					    	  		                   vintr = 7.90;
					    	  		                }
					    	  		                
					    	  		            }
					    	  		            
					    	  		        }

					    	  		    }
					    	  		    
					    	  			vintrTwo = vintr;
					    	  		        
					    	  		        
					    	  		        
					    	  			if(tnr>=12){
					    	  				 var crntdate = new Date(); 
					    	  		         crntdate.setMonth(parseInt(crntdate.getMonth()) + parseInt(tnr));
					    	  				 var maturityformat = /*crntdate.getDate() +"-"+ */monthArry[crntdate.getMonth()] +" "+ crntdate.getFullYear(); 
					    	  			}
					    	  			
					    	  			 $(".customerText p").text(vintr+"%");
					    	  			    $("#sdpInterestRate").text(vintr.toFixed(2)+"%");
					    	  			    
					    	  			  if(vAmt>0){
					    	  			
					    	  				 var FDtenor = tnr;
					    	  				 var FDtenor_2 = 60;
					    	  				 var  FDTotal, FDTotal_2, TotalIntEarn, TotalIntEarn_2;
					    	  		          
					    	  		          
					    	  				
					    	  				vmtor = FDtenor / 12;
					    	  		          vmtor_2 = FDtenor_2 / 12;
					    	  				vintr = (vintr / 100);
					    	  		          
					    	  		          var vintr_2 = (7.2 / 100);
					    	  			   
					    	  				if (vAmt < 5000) {
					    	  					$('#payPerMon').text('--'); /* Maturity Amount */
					    	  					$('#monthIntAmt').text('--'); /* Interest Amount */
					    	  				
					    	  				}else if (vAmt > 50000000) {
					    	  					$('#payPerMon').text('--'); /* Maturity Amount */
					    	  					$('#monthIntAmt').text('--'); /* Interest Amount */
					    	  					//$('#totPayAmt').text('--');  
					    	  					//$('#totIntEar').text('--');  
					    	  					$('.sdpsavingGrow').text('--');  
					    	  					$('.sdpsavingGrow_2').text('--');  

					    	  				}else{ 
					    	  					FDTotal = vAmt * Math.pow(((1 + vintr)), vmtor); 
					    	  					FDTotal = Math.round(FDTotal);
					    	  					TotalIntEarn = FDTotal - vAmt;
					    	  		            
					    	  		            FDTotal_2 = vAmt * Math.pow(((1 + vintr_2)), vmtor_2); 
					    	  					FDTotal_2 = Math.round(FDTotal_2);
					    	  					TotalIntEarn_2 = FDTotal_2 - vAmt;
					    	  		            
					    	  		            
					    	  					$('#payPerMon').html('<i class="fas fa-rupee"></i> '+inrFormat(FDTotal));
					    	  					$('#monthIntAmt').html('<i class="fas fa-rupee"></i> '+inrFormat(TotalIntEarn));
					    	  		            //eachDeposite
					    	  		            //$('#totPayAmt').html('<i class="fas fa-rupee"></i> '+inrFormat(FDTotal * eachDeposite));
					    	  		            //$('#totIntEar').html('<i class="fas fa-rupee"></i> '+inrFormat(TotalIntEarn * eachDeposite));
					    	  		            $('#growFDamt_2').html('Grow your savings upto <i class="fas fa-rupee"></i> '+inrFormat(TotalIntEarn_2 * eachDeposite_2));
					    	  		            $('.sdpsavingGrow').html('See your savings grow upto  <i class="fas fa-rupee"></i> '+inrFormat(TotalIntEarn * eachDeposite));
					    	  		            $('.sdpsavingGrow_2').html('You are getting the best returns on your deposit  <i class="fas fa-rupee"></i> '+inrFormat(TotalIntEarn * eachDeposite));
					    	  		            
					    	  				}
					    	  				
					    	  			   var pr1 =0;
					    	  			   var pr2 =0;
					    	  			   var pr3 =0;
					    	  			   var pr4 =0;
					    	  			   
					    	  				var ncbasepr1 = 7.19;
					    	  				var ncbasepr2 = 7.24;
					    	  				var ncbasepr3 = 7.28;
					    	  				var ncbasepr3_1 = 7.38;
					    	  				
					    	  				var ncbasepr4 = 7.24;
					    	  				var ncbasepr5 = 7.28;
					    	  				var ncbasepr6 = 7.33;
					    	  				var ncbasepr6_1 = 7.43;
					    	  				
					    	  				var ncbasepr7 = 7.31;
					    	  				var ncbasepr8 = 7.36;
					    	  				var ncbasepr9 = 7.4;
					    	  				var ncbasepr9_1 = 7.5;
					    	  				
					    	  				var ncbasepr10 = 7.45;
					    	  				var ncbasepr11 = 7.5;
					    	  				var ncbasepr12 = 7.55;
					    	  				var ncbasepr12_1 = 7.65;
					    	  			
					    	  			   if(custyp=="NewCustomer"){		
					    	  			       if(tnr>=12 && tnr<24){
					    	  						 pr1 = ncbasepr1;
					    	  					}else if(tnr>=24 && tnr<36){
					    	  						 pr1 = ncbasepr2;
					    	  					}else if(tnr>=36 && tnr<61){
					    	  						 pr1 = ncbasepr3;
					    	  					}
					    	  		           
					    	  					
					    	  					if(tnr>=12 && tnr<24){
					    	  						 pr2 = ncbasepr4;
					    	  					}else if(tnr>=24 && tnr<36){
					    	  						 pr2 = ncbasepr5;
					    	  					}else if(tnr>=36 && tnr<61){
					    	  						 pr2 = ncbasepr6;
					    	  					}
					    	  					
					    	  					if(tnr>=12 && tnr<24){
					    	  						 pr3 = ncbasepr7;
					    	  					}else if(tnr>=24 && tnr<36){
					    	  						 pr3 = ncbasepr8;
					    	  					}else if(tnr>=36 && tnr<61){
					    	  						 pr3 = ncbasepr9;
					    	  					}
					    	  					
					    	  					if(tnr>=12 && tnr<24){
					    	  						 pr4 = ncbasepr10;
					    	  					}else if(tnr>=24 && tnr<36){
					    	  						 pr4 = ncbasepr11;
					    	  					}else if(tnr>=36 && tnr<61){
					    	  						 pr4 = ncbasepr12;
					    	  					}

					    	  					
					    	  			   }else if(custyp=="ExsitingCustomer"){
					    	  				   
					    	  				    if(tnr>=12 && tnr<24){
					    	  						 pr1 = ncbasepr1+0.25;
					    	  					}else if(tnr>=24 && tnr<36){
					    	  						 pr1 = ncbasepr2+0.25;
					    	  					}else if(tnr>=36 && tnr<48){
					    	  						 pr1 = ncbasepr3+0.25;
					    	  					}else if(tnr>=48 && tnr<61){
					    	  						 pr1 = ncbasepr3_1+0.25;
					    	  					}
					    	  					
					    	  					if(tnr>=12 && tnr<24){
					    	  						 pr2 = ncbasepr4+0.25;
					    	  					}else if(tnr>=24 && tnr<36){
					    	  						 pr2 = ncbasepr5+0.25;
					    	  					}else if(tnr>=36 && tnr<48){
					    	  						 pr2 = ncbasepr6+0.25;
					    	  					}else if(tnr>=48 && tnr<61){
					    	  						 pr2 = ncbasepr6_1+0.25;
					    	  					}
					    	  					
					    	  					if(tnr>=12 && tnr<24){
					    	  						 pr3 = ncbasepr7+0.25;
					    	  					}else if(tnr>=24 && tnr<36){
					    	  						 pr3 = ncbasepr8+0.25;
					    	  					}else if(tnr>=36 && tnr<48){
					    	  						 pr3 = ncbasepr9+0.25;
					    	  					}else if(tnr>=48 && tnr<61){
					    	  						 pr3 = ncbasepr9_1+0.25;
					    	  					}
					    	  					
					    	  					if(tnr>=12 && tnr<24){
					    	  						 pr4 = ncbasepr10+0.25;
					    	  					}else if(tnr>=24 && tnr<36){
					    	  						 pr4 = ncbasepr11+0.25;
					    	  					}else if(tnr>=36 && tnr<48){
					    	  						 pr4 = ncbasepr12+0.25;
					    	  					}else if(tnr>=48 && tnr<61){
					    	  						 pr4 = ncbasepr12_1+0.25;
					    	  					}
					    	  				   
					    	  		       }else if(custyp=="GroupEmply"){
					    	  				    if(tnr>=12 && tnr<24){
					    	  						 pr1 = ncbasepr1+0.25;
					    	  					}else if(tnr>=24 && tnr<36){
					    	  						 pr1 = ncbasepr2+0.25;
					    	  					}else if(tnr>=36 && tnr<61){
					    	  						 pr1 = ncbasepr3+0.25;
					    	  					}
					    	  					
					    	  					if(tnr>=12 && tnr<24){
					    	  						 pr2 = ncbasepr4+0.25;
					    	  					}else if(tnr>=24 && tnr<36){
					    	  						 pr2 = ncbasepr5+0.25;
					    	  					}else if(tnr>=36 && tnr<61){
					    	  						 pr2 = ncbasepr6+0.25;
					    	  					}
					    	  					
					    	  					if(tnr>=12 && tnr<24){
					    	  						 pr3 = ncbasepr7+0.25;
					    	  					}else if(tnr>=24 && tnr<36){
					    	  						 pr3 = ncbasepr8+0.25;
					    	  					}else if(tnr>=36 && tnr<61){
					    	  						 pr3 = ncbasepr9+0.25;
					    	  					}
					    	  					
					    	  					if(tnr>=12 && tnr<24){
					    	  						 pr4 = ncbasepr10+0.25;
					    	  					}else if(tnr>=24 && tnr<36){
					    	  						 pr4 = ncbasepr11+0.25;
					    	  					}else if(tnr>=36 && tnr<61){
					    	  						 pr4 = ncbasepr12+0.25;
					    	  					}
					    	  				   
					    	  			   }else if(custyp=="SrCitizen"){
					    	  				    if(tnr>=12 && tnr<24){
					    	  						 pr1 = ncbasepr1+0.35;
					    	  					}else if(tnr>=24 && tnr<36){
					    	  						 pr1 = ncbasepr2+0.35;
					    	  					}else if(tnr>=36 && tnr<61){
					    	  						 pr1 = ncbasepr3+0.35;
					    	  					}
					    	  					
					    	  					if(tnr>=12 && tnr<24){
					    	  						 pr2 = ncbasepr4+0.35;
					    	  					}else if(tnr>=24 && tnr<36){
					    	  						 pr2 = ncbasepr5+0.35;
					    	  					}else if(tnr>=36 && tnr<61){
					    	  						 pr2 = ncbasepr6+0.35;
					    	  					}
					    	  					
					    	  					if(tnr>=12 && tnr<24){
					    	  						 pr3 = ncbasepr7+0.35;
					    	  					}else if(tnr>=24 && tnr<36){
					    	  						 pr3 = ncbasepr8+0.35;
					    	  					}else if(tnr>=36 && tnr<61){
					    	  						 pr3 = ncbasepr9+0.35;
					    	  					}
					    	  					
					    	  					if(tnr>=12 && tnr<24){
					    	  						 pr4 = ncbasepr10+0.35;
					    	  					}else if(tnr>=24 && tnr<36){
					    	  						 pr4 = ncbasepr11+0.35;
					    	  					}else if(tnr>=36 && tnr<61){
					    	  						 pr4 = ncbasepr12+0.35;
					    	  					}

					    	  			   }	   
					    	  				
					    	  				pr1 = pr1.toFixed(2);
					    	  				pr2 = pr2.toFixed(2);
					    	  				pr3 = pr3.toFixed(2);
					    	  				pr4 = pr4.toFixed(2);
					    	  			
					    	  			
					    	  		    }
					    	  			
					    	  		      totalPayout()
					    	  		  }
					    	  			  
					    	  		    SDPloanCalc();    
					    	  		        
					    	  		        
					    	  		        
					    	  		            function totalPayout(){
					    	  		        var loanAmnt =	$('.sliderAmunt_2').val();
					    	  		                
					    	  		         loanAmnt = loanAmnt.replace(/\,/g,'');
					    	  		         loanAmnt = Number(loanAmnt);
					    	  		         vAmt = parseInt(loanAmnt);
					    	  		               
					    	  		         tnr = $('input[name="sdptenor"]:checked').val();
					    	  		                
					    	  		        if(tnr == 'Custom' || tnr == undefined){
					    	  					tnr = $('#sdpCustomTenor').val();
					    	  		        }
					    	  		                
					    	  		         nd = Number($(".p_relation").val()) + 1;
					    	  		            var FDtenor = Number(tnr);
					    	  		        
					    	  		        var vmtor = FDtenor / 12;
					    	  				var vintr1 = (vintrTwo / 100);
					    	  		        var FDTotal;
					    	  		        
					    	  		        
					    	  		        
					    	  		        var nodTnr = Number(tnr);
					    	  		          var sims = Number(tnr) - nd;
					    	  		                
					    	  		        var sumofpayOut = 0;  
					    	  		                var thatTnt;
					    	  		                var tpa;
					    	  		            var maturityScheme = $('.a_radiodeposit label input[name="maturitysdheme"]:checked').val();
					    	  							    	  						    	  					    	  					    	  		            if(maturityScheme=="Monthly Maturity"){	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  						    	      										  		maturityScheme = "Monthly Maturity";	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  						    	      										  		}else{	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  						    	      										  		maturityScheme = "Single Maturity";	
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  						    	      										  		}
					    	  							    	  						    	  					    	  					    	  							    	  					    	  							    	  						    	      										  		     if(maturityScheme == 'Single Maturity'){
					    	  		                
					    	  		                while (sims < nodTnr) {
					    	  		                    thatTnt = nodTnr--;
					    	  		                    //thatTnt = thatTnt / 12;
					    	  		                    FDTotal = vAmt * Math.pow(((1 + vintr1)), thatTnt);
					    	  		                    FDTotal = Math.round(FDTotal);
					    	  		                    //sumofpayOut = sumofpayOut + FDTotal;
					    	  		                    tpa = (vAmt * eachDeposite) + (FDTotal - vAmt) * nd;
					    	  		                    $('#totPayAmt').html('<i class="fas fa-rupee"></i> '+inrFormat(tpa));
					    	  		                    
					    	  		                    //console.log(FDTotal);
					    	  		                    
					    	  		                    //alert(vAmt);
					    	  		                    var rate = vintr1 / 12;
					    	  		                    var nper = thatTnt;
					    	  		                    var pmt = "";
					    	  		                    var pv = vAmt;
					    	  		                    var type = -1;
					    	  		                    
					    	  		                    function FV(rate, nper, pmt, pv, type) {
					    	  		                      var pow = Math.pow(1 + rate, nper),
					    	  		                         fv;
					    	  		                      if (rate) {
					    	  		                       fv = (pmt*(1+rate*type)*(1-pow)/rate)-pv*pow;
					    	  		                      } else {
					    	  		                       fv = -1 * (pv + pmt * nper);
					    	  		                      }
					    	  		                      //return fv.toFixed(2);
					    	  		                      return Math.round(convert_positive(fv));
					    	  		                        
					    	  		                    }
					    	  		                    
					    	  		                    var getSingleMatu = FV(rate, nper, pmt, pv, type);
					    	  		                    
					    	  		                    sumofpayOut = sumofpayOut+getSingleMatu;
					    	  		                    
					    	  		                    
					    	  		                    function convert_positive(a) {
					    	  		                        if (a < 0) {
					    	  		                            a = a * -1;
					    	  		                        }
					    	  		                        return a;
					    	  		                    }
					    	  		                    
					    	  		                    //sumofpayOut = convert_positive(sumofpayOut);
					    	  		                    
					    	  		                    //console.log(sumofpayOut);
					    	  		                    

					    	  		                }

					    	  		               $('#totPayAmt').html('<i class="fas fa-rupee"></i> '+inrFormat(sumofpayOut)); 
					    	  		                
					    	  		            }else{
					    	  		                thatTnt = nodTnr--;
					    	  		                thatTnt = thatTnt / 12;
					    	  		                FDTotal = vAmt * Math.pow(((1 + vintr1)), thatTnt);
					    	  		                FDTotal = Math.round(FDTotal);
					    	  		                sumofpayOut = sumofpayOut + FDTotal;
					    	  		                tpa = (vAmt * eachDeposite) + (FDTotal - vAmt) * nd;
					    	  		                $('#totPayAmt').html('<i class="fas fa-rupee"></i> '+inrFormat(tpa));
					    	  		            }
					    	  		                
					    	  		                if (maturityScheme == 'Single Maturity'){
					    	  		                    var intEarned = sumofpayOut - vAmt * nd;        
					    	  		                    intEarned = intEarned.toString().split(".")[0];
					    	  		                }else{
					    	  		                    FDTotal = vAmt * Math.pow(((1 + vintr)), vmtor); 
					    	  		                    FDTotal = Math.round(FDTotal);
					    	  		                    var intEarned = (FDTotal - vAmt) * nd;
					    	  		                    
					    	  		                    
					    	  		                }
					    	  		                
					    	  		                
					    	  		                
					    	  		                
					    	  		             $('#totIntEar').html('<i class="fas fa-rupee"></i> '+inrFormat(intEarned)); 
					    	  		    }
					    	  		    totalPayout();
					    	  		}

					    	  		
  

					    	  	 
			
			/***************************************************SDP Calculator JS End Here****************************************/
			/** **************************************************Communication Document Upload JS Start Here****************************************/
						    	  	 
						    	    $('.openDrop.a_ReInput').click(function(){
						    	        $(this).children('.uploadDropdown').slideToggle(200);
						    	    });
						    	    
						    	    $('.uploadDropdown ul li label input[type="file"]').change(function(e){
						    	        $('.uploadDropdown').hide();
						    	        
						    	        var fileName = e.target.files[0].name;
						    	        var fileSize = e.target.files[0].size; 
						    	        var fileExtension = ['jpeg', 'jpg', 'pdf'];
						    	        if ($.inArray($(this).val().split('.').pop().toLowerCase(), fileExtension) == -1) {
						    	            $(this).parents('.uploadDropdown').siblings('.errormsg').text('File format allowed are pdf, .jpg, .jpeg').show(); 
						    	            $(this).parents('.uploadDropdown').siblings('input').val("");
						    	            $(this).parents('.uploadDropdown').siblings('label').removeClass('active');
						    	        }else if(fileSize > 4000000){
						    	            $(this).parents('.uploadDropdown').siblings('.errormsg').text('Maximum file size 4Mb').show(); 
						    	            $(this).parents('.uploadDropdown').siblings('input').val("");
						    	            $(this).parents('.uploadDropdown').siblings('label').removeClass('active');
						    	        }else{
						    	            $(this).parents('.uploadDropdown').siblings('.errormsg').hide();
						    	            var doxText = $(this).parent().text(); 
						    	            $(this).parents('.uploadDropdown').siblings('input').val(doxText);
						    	            $(this).parents('.uploadDropdown').siblings('input').css('color','#000');
						    	            $(this).parents('.uploadDropdown').siblings('label').addClass('active'); 
						    	            
						    	        }
						    	        
						    	    });
						    					    	  	
	  /** * **************************************************Communication Document Upload JS End Here***************************************/					    	  		
		
						    	    /******* on blur pincode functionality *******/
						    	    
						    	    function pincodeValidator(userInput){
										console.log("pincodeValidator "+userInput);
	                        			
	                        			var data = {
	                        					 "pincode":userInput
	                        					 };
	                        			 
	                        				       
	                        			         $.ajax({ 
	                        			                                    
	                        			             url: "/fixed-deposit-online/pincodeValidator", 
	                        			             type: "POST",
	                        			             data:JSON.stringify(data),
	                        			             contentType: 'application/json',
	                        			             error: function(response)
	                        				 			{
	                        			            	 	newRelicAlert("Technical Error Page in pincodeValidator | Fail Ajax call","'/fixed-deposit-online/pincodeValidator'",response.status,userInput);
	                        			            	 	//$("#pinCodePV").parents('.inputMainBlock').removeClass("valid").addClass("invalid");
	                        				          		console.log("inside errror function"+userInput);
	                        				             }  ,
	                        			             success: function(response) {            
	                        			            
	                        			            	var response = JSON.parse(response);
	                        			            	console.log("inside pincode validator",response);
	                        			            	 if((response.status == "success")){
	             							            	 
	                        			            		 $("#pinCodePV").siblings(".errormsg").hide();
	                        			            		 
	                        			            		 
	                        			            		 setTimeout(function (){
	                        			 			            var totalerror = 0;
	                        			 			            
	                        			 			            $("#apply_for_new_fd_form .errormsg").each(function (i) {
	                        			 			                if ($(this).css("display") == "block") {
	                        			 			                    totalerror++;
	                        			 			                }
	                        			 			            });
	                        			 			            if (totalerror == 0){
	                        			 			                
	                        			 			                $('#apply_for_new_fd_form .validBtn i').show();
	                        			 			                
	                        			 			                if (isActionInProgress == 'No') {
	                        			 			                    
	                        			 			                	isActionInProgress = 'Yes';
	                        			 			                    fdSendOTP();
	                        			 			                   
	                        			 			                    console.log("pincode function invoke");
	                        			 			                }
	                        			 							
	                        			 							}
	                        			 			        }, 500);
	                        			            		 
	                        			            		
	                        			            		 
	                        			            		   
	                        			            	 }else{
	                        			            		 $("#pinCodePV").siblings(".errormsg").text("Please enter valid pincode").show();
	                        			            		 $('#apply_for_new_fd_form .validBtn i').hide();
	                        			            		 $("#pinCodePV").parents('.inputMainBlock').removeClass("valid").addClass("invalid");
	                        			            	    }		
	                        			            	 }   
	                        			      });
	                        			
	                        			}
						    	    
						    	    
						    	    /****************** City Prepopulate Pincode Blur Function for City*********************/
	                                $("#pinCodePD").blur(function() {
						    	    	
						    	    	var value = $(this).val();
				                        var length = value.length;
										  if(length == 6){
					                        	CityPrepopulateWithPincode('pinCodePD');
					                        	
					                        }
										  
	                                });	
	                                
	                                
	                                function CityPrepopulateWithPincode(userInput,totalError){
	 					    	    	
	                                	  var custId=fdcNumber;
	  								    
	  								    if(custId==null || custId == undefined || custId=="")
	  						    		{
	  								    	custId="NA";
	  								    	
	  						    		}
	  								    
	  								    console.log(userInput+" : ",$('#'+userInput).val());
	                          			var data = { "pinCodePD":$('#'+userInput).val(), "fdcNumber":custId, "evnt":userInput};
	                          			
	                          			$.ajax({ 
	  	                                    
	                 			             url: "/fixed-deposit-application-form/getPrepopulateCityChangeOnPincode", 
	                 			             type: "POST",
	                 			             data:JSON.stringify(data),
	                 			             contentType: 'application/json',
	                 			             async : false,
	                 			             error: function(response)
	                 				 			{
	                 			            	 	newRelicAlert("Technical Error Page in getPrepopulateCityChangeOnPincode "+userInput+" | Fail Ajax call","'/fixed-deposit-online/getPrepopulateCityChangeOnPincode'",response.status,custId);
	                 				          		console.log("inside errror function"+userInput);
	                 				             }  ,
	                 			             success: function(response) {            
	                 			            	console.log("inside getPrepopulateCityChangeOnPincode "+response);
	                 			            	var response = JSON.parse(response);
	                 			            	
	                 			            	 if((response.status == "success")){
	                 			            		 
	                 			            		 if(userInput=='pinCodePD'){
	                 			            			$("#pinCodePD").siblings(".errormsg").hide();
	                 			            		 }
	                 			            		 else if(userInput=='nomineepinCodePV'){
	                 			            			$("#nomineepinCodePV").siblings(".errormsg").hide();
	                 			            		 }
	                 			            		 else if(userInput=='guardiunPincode'){
	                 			            			 $("#guardiunPincode").siblings(".errormsg").hide();
	                 			            		 }else if(userInput=='commPincode'){
	                 			            			 $("#commPincode").siblings(".errormsg").hide();
	                 			            		 }
	                 			            		   
	                 			            	 }else{
	                 			            		 
	                 			            		 if(userInput=='pinCodePD'){
	                 			            			 $("#pinCodePD").parent().removeClass('valid');
	                    			            		 $("#pinCodePD").siblings(".errormsg").text("Please enter valid pincode").show();
	                 			            		 }
	                 			            		 else if(userInput=='nomineepinCodePV'){
	                 			            			totalError++;
	                 			            			 $("#nomineepinCodePV").parent().removeClass('valid');
	                    			            		 $("#nomineepinCodePV").siblings(".errormsg").text("Please enter valid pincode").show();
	                 			            		 }
	                 			            		 else if(userInput=='guardiunPincode'){
	                 			            			totalError++;
	                 			            			 $("#guardiunPincode").parent().removeClass('valid');
	                    			            		 $("#guardiunPincode").siblings(".errormsg").text("Please enter valid pincode").show();
	                 			            		 }
	                 			            		 else if(userInput=='commPincode'){
	                 			            			totalError++;
	                 			            			$("#commPincode").parent().removeClass('valid');
	                 			            			$("#commPincode").siblings(".errormsg").text("Please enter valid pincode").show();  
	                 			            		 }
	                 			            		 
	                 			            	
	                 			            	 }		
	                 			            	 	}   
	                          				});
	                          			return totalError;
	                          			
	  					    	    	}	
	                                
	                                /******************Communication Address Pincode Blur Function for City*********************/                    
	                                
	                                $("#commPincode").blur(function() {
						    	    	
						    	    	var value = $(this).val();
				                        var length = value.length;
										  if(length == 6){
											  CityPrepopulateWithPincode('commPincode',0);
					                        	console.log("Inside blur commPincode"+value);
					                        }
										
						    	    });	
	                                
	                                
	                                /****************** Nominee Pincode Blur Function*********************/
	                                
	                                
	                                $("#nomineepinCodePV").blur(function() {
						    	    	
						    	    	var value = $(this).val();
				                        var length = value.length;
										  if(length == 6){
											  CityPrepopulateWithPincode('nomineepinCodePV',0);
					                        	
					                        }
										  
	                                });
	                                
	                                /******************Nominee Guardians Pincode Blur Function *********************/
	                                
	                                $("#guardiunPincode").blur(function() {
						    	    	
						    	    	var value = $(this).val();
				                        var length = value.length;
										  
				                        if(length == 6){
				                        	CityPrepopulateWithPincode('guardiunPincode',0);
					                    }
										  
	                                });
	                                
	                                
	                                
						    	    
		});
		
		
		/**************06-04-2023******************/
		