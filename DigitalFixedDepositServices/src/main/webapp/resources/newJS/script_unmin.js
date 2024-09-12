$(document).ready(function(){
    
    
    $('.green_card_main .s_block > a').click(function(){
        $('.part_1').hide();
        $('.part_2').show();
    });
    
    
    $('.green_card_main .s_block .tab_ul li a').click(function(){
        if($(this).text() == 'Monthly Maturity'){
            $(this).parents('.s_block').siblings('.f_block').children('i').text('Achieve your financial goals by making small monthly deposits.');
        }else{
            $(this).parents('.s_block').siblings('.f_block').children('i').text('Achieve your financial goals by making small monthly deposits. Get lumpsum amount on maturity.');
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
    });
    
    $('.radio_ul li label input[type="radio"]').change(function(){
        var datatab = $(this).attr('data-tab');
        
        $('#'+datatab).fadeIn(200);
        $('#'+datatab).siblings().hide();
        
        
        $('#'+datatab).find('input').removeClass('nomandetory');
        $('#'+datatab).siblings().find('input').addClass('nomandetory');
        $('#'+datatab).siblings().find('.errormsg').hide();
        
    });
    
    $('.tab_ul li a').click(function(){
        var datatab = $(this).attr('data-tab');
        
        $('#'+datatab).fadeIn(200);
        $('#'+datatab).siblings().hide();
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
    });
    
    $('.viewAllLink a').click(function(){
        if($(this).text() == 'View all'){
            $(this).text('View less');
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
        if($(this).text() == 'YES'){
            $('.beingAssistedForm').slideDown(200);
            $('.beingAssistedForm input').removeClass('nomandetory');
        }else{
            $('.beingAssistedForm').slideUp(200);
            $('.beingAssistedForm input').addClass('nomandetory');
            $('.beingAssistedForm .errormsg').hide();
        }
    });
    
    
    
    if($('#hldatepicker').length>0){
    
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
    
    
    

    
    $('#hldatepicker').daterangepicker({
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

    
 
}
    
    
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
        var timer2 = "03:00";
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
        count3minut();
    });
    
    $('.optTopDetails a').click(function(e){
        e.preventDefault();
        $('.otpMainPart').hide();
        $('.loginFirstStep').show();
        
        $('.forOtpForm').hide();
        $('.forMobileform').show();
        
        $('.otpCounter > a').removeClass('resendhighlight');
        
        $('.otpCounter > i').text('in 00:00');
        
        clearInterval(interval);
    });
    
    
    $('.openIntTable').click(function(){
        $('.interestRatePop').css('display','flex');
    });
    $('.interestRatePopInner > a').click(function(){
        $(this).parents('.interestRatePop').hide();
    });
    
    $('.interestRatePop').click(function(){
        $(this).hide();
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
    
    $(document).click(function(){
        $('.tooltipMain').hide();
    });
/************************************************************************** Validation js *****************************************************************/
    
    $('.PanVD').blur(function (e) {
        var mo = $(this).val();
        mo = mo.toUpperCase();
        $(this).val(mo);

    });
    
     function fildValidation(th, ErrMSG) {
        if (ErrMSG) {
            $(th).siblings(".errormsg").text(ErrMSG);
        }
    }
    
    
    
 function allFormFieldValidationCheck(ThisObj, eventType) {
     
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
                                $(this).siblings('.otpBottomLine').addClass('invalidOTP');
                            } else {
                                $(this).siblings(".errormsg").hide();
                                $(this).siblings('.otpBottomLine').removeClass('invalidOTP');
                            }

                        }else  if ($(this).hasClass('dobVD')) {
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
                             fildValidation(this,"Minor not eligible");
                               }else{
                                            $(this).siblings(".errormsg").hide();
                                  fildValidation(this,"Enter valid date of birth");
                                        }
                        }
                        else {
                            $(this).siblings(".errormsg").show();
                             fildValidation(this,"Enter valid date of birth");
                        }
                    }else if ($(this).hasClass('FullNameVD')) {
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
                  
    $(".submitBTN").click(function (e) {
        e.preventDefault();
        var ThisObj = $(this);
        var NoError = allFormFieldValidationCheck(ThisObj, 1);
    });
     
    
    $(".inputMainBlock input").blur(function () {
        var ThisObj = $(this);

        if (!$(this).hasClass("nomandetory")) {
            if (!$(this).attr('disabled')) {
                if ($(this).val() == "") {
                        $(this).siblings(".errormsg").show();
                        fildValidation(this);
                     
                    
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
                            $(this).siblings('.otpBottomLine').addClass('invalidOTP');
                        } else {
                            $(this).siblings(".errormsg").hide();
                            $(this).siblings('.otpBottomLine').removeClass('invalidOTP');
                        }

                    }else  if ($(this).hasClass('dobVD')) {
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
                             fildValidation(this,"Minor not eligible");
                               }else{
                                            $(this).siblings(".errormsg").hide();
                                  fildValidation(this,"Enter valid date of birth");
                                        }
                                   
                        }
                        else {
                            
                            $(this).siblings(".errormsg").show();
                             fildValidation(this,"Enter valid date of birth");
                        }
                    }else if ($(this).hasClass('FullNameVD')) {
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
    
    
    function validornot(th) {
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
            validornot(th)
        }, 300);
    });
    $('.inputMainBlock select').change(function () {
        $(this).parent().removeClass('active');

        var th = $(this);
        setTimeout(function () {
            validornot(th)
        }, 300);
    });
    
    
    $('#apply_for_new_fd_form .validBtn').click(function (e) {
        e.preventDefault();
        
        setTimeout(function () {
            var totalerror = 0;
            $("#apply_for_new_fd_form .errormsg").each(function (i) {
                if ($(this).css("display") == "block") {
                    totalerror++;
                }
            });
            if (totalerror == 0) {
                $('.part_2 .forMobileform').hide();
                $('.part_2 .forOtpForm').show();
                
                $('.part_2 .loginFirstStep').hide();
                $('.part_2 .otpMainPart').show();
                
                $('.otpPart input').first().focus();
                
                count3minut();
            }
        }, 500);

    });

    $('#resume_applicatIon_form .validBtn').click(function (e) {
        e.preventDefault();
        
        setTimeout(function () {
            var totalerror = 0;
            $("#resume_applicatIon_form .errormsg").each(function (i) {
                if ($(this).css("display") == "block") {
                    totalerror++;
                }
            });
            if (totalerror == 0) {
                $('.part_2 .forMobileform').hide();
                $('.part_2 .forOtpForm').show();
                
                $('.part_2 .loginFirstStep').hide();
                $('.part_2 .otpMainPart').show();
                
                $('.otpPart input').first().focus();
                
                count3minut();
            }
        }, 500);

    });
    
    $('#otpForm .validBtn').click(function (e) {
        e.preventDefault();
        setTimeout(function () {

            $("#otpForm :input").each(function () {
                if ($(this).val() != '') {
                    $(this).removeClass('invalid').addClass('valid');
                } else {
                    $(this).addClass('invalid').removeClass('valid');
                }
            });

            var totalerror = 0;
            var firsterr = 0;
            
           var otperror =  parseInt($(".otpPart").find('.invalid').length);
            
            if(otperror == 0){
                $('.part_2').hide();
                $('.part_3').show();
            }else{
                return false;
            }
             
            if (totalerror == 0) {
            

            } else {}
        }, 500);
    });
    
    $('#personal_details_1 .validBtn').click(function (e) {
        e.preventDefault();
        
        setTimeout(function () {
            var totalerror = 0;
            $("#personal_details_1 .errormsg").each(function (i) {
                if ($(this).css("display") == "block") {
                    totalerror++;
                }
            });
            if (totalerror == 0) {
                $('.part_3').hide();
                $('.part_4').show();
            }
        }, 500);
    });
    
    $('#personal_details_2 .validBtn').click(function (e) {
        e.preventDefault();
        
        setTimeout(function () {
            var totalerror = 0;
            $("#personal_details_2 .errormsg").each(function (i) {
                if ($(this).css("display") == "block") {
                    totalerror++;
                }
            });
            if (totalerror == 0) {
                alert('personal_details_2');
            }
        }, 500);
    });
    
});




 
