
/*** 27 May 2021 ***/


$(document).ready(function(){if($(".green_card_main .s_block > a").click(function(){$(".part_1").hide(),$(".part_2").show()}),$(".green_card_main .s_block .tab_ul li a").click(function(){"Monthly Maturity"==$(this).text()?$(this).parents(".s_block").siblings(".f_block").children("i").text("Achieve your financial goals by making small monthly deposits."):$(this).parents(".s_block").siblings(".f_block").children("i").text("Achieve your financial goals by making small monthly deposits. Get lumpsum amount on maturity.")}),$(".word_testimonial_inner .testimonial_top > .name_post p").each(function(){var i=$(this).text().match(/\b(\w)/g).join("");$(this).parent().siblings(".testiNameLatter").text(i)}),$(".tab_ul li a").click(function(){$(this).addClass("active"),$(this).parent().siblings().children("a").removeClass("active")}),$('.radio_ul li label input[type="radio"]').change(function(){var i=$(this).attr("data-tab");$("#"+i).fadeIn(200),$("#"+i).siblings().hide(),$("#"+i).find("input").removeClass("nomandetory"),$("#"+i).siblings().find("input").addClass("nomandetory"),$("#"+i).siblings().find(".errormsg").hide()}),$(".tab_ul li a").click(function(){var i=$(this).attr("data-tab");$("#"+i).fadeIn(200),$("#"+i).siblings().hide()}),$(window).width()>768&&$(function(){count=0,fdImg=["step_1","step_2","step_3","step_4"],sdpImg=["step_1","step_2","step_3","step_4"],setInterval(function(){count++,$("#investOnlineFd .leftPart img").fadeOut(200,function(){$(this).attr("src","/fixed-deposit-application-form/resources/newImages/"+fdImg[count%fdImg.length]+".png").fadeIn(200),$("#investOnlineFd .investOnlineStepBox").eq(count%fdImg.length).addClass("active"),$("#investOnlineFd .investOnlineStepBox").eq(count%fdImg.length).siblings().removeClass("active")}),$("#investOnlineSdp .leftPart img").fadeOut(200,function(){$(this).attr("src","/fixed-deposit-application-form/resources/newImages/"+sdpImg[count%sdpImg.length]+".png").fadeIn(200),$("#investOnlineSdp .investOnlineStepBox").eq(count%sdpImg.length).addClass("active"),$("#investOnlineSdp .investOnlineStepBox").eq(count%sdpImg.length).siblings().removeClass("active")})},3e3),$(".investOnlineTab .tab_ul li").click(function(){count=0,$("#investOnlineFd .leftPart img").attr("src","/fixed-deposit-application-form/resources/newImages/"+fdImg[0]+".png").fadeIn(200),$("#investOnlineSdp .leftPart img").attr("src","/fixed-deposit-application-form/resources/newImages/"+sdpImg[0]+".png").fadeIn(200),$(".investOnlineStepBox").removeClass("active"),$("#investOnlineFd .investOnlineStepBox").eq(0).addClass("active"),$("#investOnlineSdp .investOnlineStepBox").eq(0).addClass("active")}),$(".investOnlineStepBox").click(function(){count=$(this).index(),$("#investOnlineFd .leftPart img").fadeOut(200,function(){$(this).attr("src","/fixed-deposit-application-form/resources/newImages/"+fdImg[count]+".png").fadeIn(200)}),$("#investOnlineSdp .leftPart img").fadeOut(200,function(){$(this).attr("src","/fixed-deposit-application-form/resources/newImages/"+sdpImg[count]+".png").fadeIn(200)}),$(this).addClass("active"),$(this).siblings().removeClass("active")})}),$(".faqConBlock h3").click(function(){$(this).toggleClass("active"),$(this).parent().siblings(".faqConBlock").children("h3").removeClass("active"),$(this).siblings(".faqAns").slideToggle(200),$(this).parent().siblings(".faqConBlock").children(".faqAns").slideUp(200)}),$(".viewAllLink a").click(function(){"View all"==$(this).text()?$(this).text("View less"):$(this).text("View all"),$(this).parent().siblings().children(".faqConBlock:nth-child(n + 6)").slideToggle(200)}),$(".tooltipBox .tooltipCon .cross, .tooltipBox .tooltipCon .gotIt").click(function(){$(this).parents(".tooltipMain").hide()}),$(".tooltipBox a").click(function(i){i.stopPropagation(),$(".tooltipMain").hide(),$(this).siblings(".tooltipMain").show()}),$(".inputMainBlock input").focus(function(){$(this).siblings("label").addClass("active")}),$(".inputMainBlock input").blur(function(){var i=$(this);setTimeout(function(){""==$(i).val()?$(i).siblings("label").removeClass("active"):$(i).siblings("label").addClass("active")},200)}),$(".inputMainBlock select").change(function(){""==$(this).val()?$(this).siblings("label").removeClass("active"):$(this).siblings("label").addClass("active")}),$(".beingAssistedFormShowOrHide a").click(function(){"YES"==$(this).text()?($(".beingAssistedForm").slideDown(200),$(".beingAssistedForm input").removeClass("nomandetory")):($(".beingAssistedForm").slideUp(200),$(".beingAssistedForm input").addClass("nomandetory"),$(".beingAssistedForm .errormsg").hide())}),$("#hldatepicker").length>0){var t=(n=new Date).getFullYear(),s=(t=Number(t),n.getMonth());s=Number(s),s+=1;var e=n.getDate()+"/"+s+"/"+t;$("#hldatepicker").daterangepicker({singleDatePicker:!0,showDropdowns:!0,autoUpdateInput:!1,autoApply:!0,minDate:"01/01/1900",maxDate:e,locale:{format:"DD/MM/YYYY",cancelLabel:"Clear"}},function(i){var t=e,s=$("#hldatepicker").val()?$("#hldatepicker").val():i.format("DD/MM/YYYY"),n="01/01/1900".split("/"),a=t.split("/"),l=s.split("/"),r=new Date(n[2],parseInt(n[1])-1,n[0]),o=new Date(a[2],parseInt(a[1])-1,a[0]),h=new Date(l[2],parseInt(l[1])-1,l[0]);console.log(h>r&&h<o),h>r&&h<o?$("#hldatepicker").val(i.format("DD/MM/YYYY")):$("#hldatepicker").val(""),setTimeout(function(){$(".dobVD").blur()},200)}),$("#hldatepicker").val(""),$("#hldatepicker").focus(function(i){i.preventDefault()}),$(".calenderBtn").click(function(i){i.preventDefault(),$(this).siblings("input").focus()}),$("#hldatepicker").blur(function(i){i.preventDefault(),"00/00/0000"==$(this).val()&&$(this).val("")})}if($("#hldatepicker_2").length>0){t=(n=new Date).getFullYear(),t=Number(t),s=n.getMonth();s=Number(s),s+=1;e=n.getDate()+"/"+s+"/"+t;$("#hldatepicker_2").daterangepicker({singleDatePicker:!0,showDropdowns:!0,autoUpdateInput:!1,autoApply:!0,minDate:"01/01/1900",maxDate:e,locale:{format:"DD/MM/YYYY",cancelLabel:"Clear"}},function(i){var t=e,s=$("#hldatepicker_2").val()?$("#hldatepicker_2").val():i.format("DD/MM/YYYY"),n="01/01/1900".split("/"),a=t.split("/"),l=s.split("/"),r=new Date(n[2],parseInt(n[1])-1,n[0]),o=new Date(a[2],parseInt(a[1])-1,a[0]),h=new Date(l[2],parseInt(l[1])-1,l[0]);console.log(h>r&&h<o),h>r&&h<o?$("#hldatepicker_2").val(i.format("DD/MM/YYYY")):$("#hldatepicker_2").val(""),setTimeout(function(){$(".dobVD").blur()},200)}),$("#hldatepicker_2").val(""),$("#hldatepicker_2").focus(function(i){i.preventDefault()}),$(".calenderBtn").click(function(i){i.preventDefault(),$(this).siblings("input").focus()}),$("#hldatepicker_2").blur(function(i){i.preventDefault(),"00/00/0000"==$(this).val()&&$(this).val("")})}if($("#hldatepicker_3").length>0){var n;t=(n=new Date).getFullYear(),t=Number(t),s=n.getMonth();s=Number(s),s+=1;e=n.getDate()+"/"+s+"/"+t;$("#hldatepicker_3").daterangepicker({singleDatePicker:!0,showDropdowns:!0,autoUpdateInput:!1,autoApply:!0,minDate:"01/01/1900",maxDate:e,locale:{format:"DD/MM/YYYY",cancelLabel:"Clear"}},function(i){var t=e,s=$("#hldatepicker_3").val()?$("#hldatepicker_3").val():i.format("DD/MM/YYYY"),n="01/01/1900".split("/"),a=t.split("/"),l=s.split("/"),r=new Date(n[2],parseInt(n[1])-1,n[0]),o=new Date(a[2],parseInt(a[1])-1,a[0]),h=new Date(l[2],parseInt(l[1])-1,l[0]);console.log(h>r&&h<o),h>r&&h<o?$("#hldatepicker_3").val(i.format("DD/MM/YYYY")):$("#hldatepicker_3").val(""),setTimeout(function(){$(".dobVD").blur()},200)}),$("#hldatepicker_3").val(""),$("#hldatepicker_3").focus(function(i){i.preventDefault()}),$(".calenderBtn").click(function(i){i.preventDefault(),$(this).siblings("input").focus()}),$("#hldatepicker_3").blur(function(i){i.preventDefault(),"00/00/0000"==$(this).val()&&$(this).val("")})}if($(".otpPart").length>0){var a=8,l=13,r=9,o=37,h=39;(function(t){var s=document.getElementById(t).children,e=null;function n(t,n){n.addEventListener("input",function(n){!function(t,n){var a=n.data||n.target.value,l=t;for(i=0;i<a.length;i++)if(i<s.length){if(!$(a[i])){s[l].value="";break}s[l++].value=a[i],l==s.length?c()&&e(d()):s[l].focus()}}(t,n)}),n.addEventListener("paste",function(n){!function(t,n){n.preventDefault();var a=t,l=(n.clipboardData||window.clipboardData).getData("Text");for(i=0;i<l.length;i++)if(i<s.length){if(!$(l[i]))break;s[a].value=l[i],a++}a==s.length?(s[a-1].focus(),e(d())):s[a].focus()}(t,n)}),n.addEventListener("keydown",function(i){!function(i,t){var n=t.keyCode||t.which;n==o&&i>0&&(t.preventDefault(),s[i-1].focus()),n==h&&i+1<s.length&&(t.preventDefault(),s[i+1].focus()),n==a&&i>0&&(""==s[i].value?(s[i-1].value="",s[i-1].focus()):s[i].value=""),n==l&&(t.preventDefault(),c()&&e(d())),n==r&&i==s.length-1&&(t.preventDefault(),c()&&e(d()))}(t,i)})}function d(){var t="";for(i=0;i<s.length;i++)t+=s[i].value;return t}function $(i){return i>="0"&&i<="9"}function c(){for(var i=!0,t=0;t<s.length&&i;)""==s[t].value&&(i=!1),t++;return i}return{init:function(t){for(e=t,i=0;i<s.length;i++)n(i,s[i])}}})("otp-inputs").init(function(i){$("#otp-inputs input").blur()})}var d;function c(){var i="03:00";d=setInterval(function(){var t=i.split(":"),s=parseInt(t[0],10),e=parseInt(t[1],10);s=--e<0?--s:s,e=(e=e<0?59:e)<10?"0"+e:e,$(".otpCounter > i").html("in 0"+s+":"+e),s<0&&clearInterval(d),e<=0&&s<=0&&clearInterval(d),i=s+":"+e,e<=0&&s<=0&&$(".otpCounter > a").addClass("resendhighlight")},1e3)}function u(i,t){t&&$(i).siblings(".errormsg").text(t)}$("#otpForm :input").blur(function(){""!=$(this).val()?$(this).removeClass("invalid").addClass("valid"):$(this).addClass("invalid").removeClass("valid")}),$("body").on("click",".otpCounter > a.resendhighlight",function(){$(this).removeClass("resendhighlight"),c()}),$(".optTopDetails a").click(function(i){i.preventDefault(),$(".otpMainPart").hide(),$(".loginFirstStep").show(),$(".forOtpForm").hide(),$(".forMobileform").show(),$(".otpCounter > a").removeClass("resendhighlight"),$(".otpCounter > i").text("in 00:00"),clearInterval(d)}),$(".openIntTable").click(function(){$(".interestRatePop").css("display","flex")}),$(".interestRatePopInner > a").click(function(){$(this).parents(".interestRatePop").hide()}),$(".interestRatePop").click(function(){$(this).hide()}),$(".interestRatePopInner").click(function(i){i.stopPropagation()}),$(".AddressConformToggle .tab_ul li a").click(function(){"NO"==$(this).text()?($(this).parents(".AddressConformToggle").siblings(".whenSelectNo").slideDown(200),$(this).parents(".AddressConformToggle").siblings(".whenSelectNo").find("input").removeClass("nomandetory")):($(this).parents(".AddressConformToggle").siblings(".whenSelectNo").slideUp(200),$(this).parents(".AddressConformToggle").siblings(".whenSelectNo").find("input").addClass("nomandetory"),$(this).parents(".AddressConformToggle").siblings(".whenSelectNo").find(".errormsg").hide())}),$(document).click(function(){$(".tooltipMain").hide()}),$(".PanVD").blur(function(i){var t=$(this).val();t=t.toUpperCase(),$(this).val(t)}),$(".inputMainBlock select").change(function(){$(this).hasClass("nomandetory")||$(this).attr("disabled")||(""==$(this).val()?($(this).siblings(".errormsg").show(),u(this)):($(this).siblings(".errormsg").hide(),u(this)))}),$(".checkTNC input[type='checkbox']").change(function(){$(this).hasClass("nomandetory")||$(this).attr("disabled")||($(this).is(":checked")?($(this).siblings(".errormsg").hide(),u(this)):($(this).siblings(".errormsg").show(),u(this)))}),$(".submitBTN").click(function(i){i.preventDefault();!function(i,t){var s=i.parents("form").attr("id");$("#"+s+" .inputMainBlock input").each(function(){if(!$(this).hasClass("nomandetory")&&!$(this).attr("disabled"))if(""==$(this).val())$(this).siblings(".errormsg").show(),u(this);else if($(this).hasClass("mobileVD"))(i=$(this).val()).length<10||i.length>10?1==t&&($(this).siblings(".errormsg").show(),u(this,"Please enter a valid 10-digit mobile number")):i.indexOf(".")>-1?($(this).siblings(".errormsg").show(),u(this,"Please enter a valid 10-digit mobile number")):9==i.substr(0,1)||8==i.substr(0,1)||7==i.substr(0,1)||6==i.substr(0,1)||5==i.substr(0,1)?($(this).siblings(".errormsg").hide(),u(this)):($(this).siblings(".errormsg").show(),u(this,"Mobile number should start with 9 or 8 or 7 or 6 or 5"));else if($(this).hasClass("AnyValueVD"))(i=$(this).val())?($(this).siblings(".errormsg").hide(),u(this)):($(this).siblings(".errormsg").show(),u(this));else if($(this).hasClass("PinCodeVD")){var i=$(this).val();/^[0-9]+$/.test($(this).val())&&6==$(this).val().length?0==i.substr(0,1)?($(this).siblings(".errormsg").show(),u(this,"Enter valid pin code")):0==i.substr(5,1)&&0==i.substr(4,1)&&0==i.substr(3,1)?($(this).siblings(".errormsg").show(),u(this,"Enter valid pin code")):$(this).siblings(".errormsg").hide():($(this).siblings(".errormsg").show(),u(this,"Enter valid pin code"))}else if($(this).hasClass("otpVD"))(i=$(this).val()).length<6||i.length>6?($(this).siblings(".errormsg").show(),u(this,"OTP entered is incorrect"),$(this).siblings(".otpBottomLine").addClass("invalidOTP")):($(this).siblings(".errormsg").hide(),$(this).siblings(".otpBottomLine").removeClass("invalidOTP"));else if($(this).hasClass("dobVD")){var s=(a=$(this).val()).split("/"),e=g(s[2]+"/"+s[1]+"/"+s[0]);/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/g.test(a)?e<18?($(this).siblings(".errormsg").show(),u(this,"Minor not eligible")):($(this).siblings(".errormsg").hide(),u(this,"Enter valid date of birth")):($(this).siblings(".errormsg").show(),u(this,"Enter valid date of birth"))}else if($(this).hasClass("FullNameVD")){var n=$(this).val().trim();$(this).val(n),/^[a-zA-Z ]*$/g.test($(this).val())?1==$(this).val().split(" ").length?$(this).siblings(".errormsg").show():$(this).val().split(" ").length>4?($(this).siblings(".errormsg").show(),u(this,"More than 3 space is not allowed")):($(this).siblings(".errormsg").hide(),u(this)):($(this).siblings(".errormsg").show(),u(this,"Only alphabets are allowed"))}else if($(this).hasClass("PanVD"))((i=$(this).val()).length<10||i.length>10)&&($(this).siblings(".errormsg").show(),u(this,"PAN number should be of 10 characters")),/^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/.test($(this).val())?"P"==$(this).val().substr(3,1)?($(this).siblings(".errormsg").hide(),u(this)):($(this).siblings(".errormsg").show(),u(this,"Enter valid PAN number; eg: ABCPC9999A")):($(this).siblings(".errormsg").show(),u(this,"Enter valid PAN number; eg: ABCHE9999A"));else if($(this).hasClass("emailVD")){n=$(this).val().trim(),$(this).val(n);var a=$(this).val();/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(a)?($(this).siblings(".errormsg").hide(),u(this)):($(this).siblings(".errormsg").show(),u(this,"Please enter your valid email ID"))}else $(this).hasClass("AddressVD")&&(i=$(this).val(),/[`~.<>;':"\/\[\]\|{}()=_+-]/g.test($(this).val())?($(this).siblings(".errormsg").show(),u(this,"<>?'=+-_^`~  not allowed")):($(this).siblings(".errormsg").hide(),u(this)))}),$("#"+s+" .inputMainBlock select").each(function(){$(this).hasClass("nomandetory")||$(this).attr("disabled")||(""==$(this).val()?($(this).siblings(".errormsg").show(),u(this)):($(this).siblings(".errormsg").hide(),u(this)))}),$("#"+s+" input[type='checkbox']").each(function(){$(this).hasClass("nomandetory")||$(this).attr("disabled")||($(this).is(":checked")?($(this).siblings(".errormsg").hide(),u(this)):($(this).siblings(".errormsg").show(),u(this)))})}($(this),1)}),$(".inputMainBlock input").blur(function(){$(this);if(!$(this).hasClass("nomandetory")&&!$(this).attr("disabled"))if(""==$(this).val())$(this).siblings(".errormsg").show(),u(this);else if($(this).hasClass("mobileVD"))(i=$(this).val()).length<10||i.length>10?($(this).siblings(".errormsg").show(),u(this,"Please enter a valid 10-digit mobile number")):i.indexOf(".")>-1?($(this).siblings(".errormsg").show(),u(this,"Please enter a valid 10-digit mobile number")):9==i.substr(0,1)||8==i.substr(0,1)||7==i.substr(0,1)||6==i.substr(0,1)||5==i.substr(0,1)?($(this).siblings(".errormsg").hide(),u(this)):($(this).siblings(".errormsg").show(),u(this,"Mobile number should start with 9 or 8 or 7 or 6 or 5"));else if($(this).hasClass("AnyValueVD")){(i=$(this).val())?($(this).siblings(".errormsg").hide(),u(this)):($(this).siblings(".errormsg").show(),u(this))}else if($(this).hasClass("PinCodeVD")){var i=$(this).val();/^[0-9]+$/.test($(this).val())&&6==$(this).val().length?0==i.substr(0,1)?($(this).siblings(".errormsg").show(),u(this,"Enter valid pin code")):0==i.substr(5,1)&&0==i.substr(4,1)&&0==i.substr(3,1)?($(this).siblings(".errormsg").show(),u(this,"Enter valid pin code")):$(this).siblings(".errormsg").hide():($(this).siblings(".errormsg").show(),u(this,"Enter valid pin code"))}else if($(this).hasClass("otpVD")){(i=$(this).val()).length<6||i.length>6?($(this).siblings(".errormsg").show(),u(this,"OTP entered is incorrect"),$(this).siblings(".otpBottomLine").addClass("invalidOTP")):($(this).siblings(".errormsg").hide(),$(this).siblings(".otpBottomLine").removeClass("invalidOTP"))}else if($(this).hasClass("dobVD")){var t=(n=$(this).val()).split("/"),s=g(t[2]+"/"+t[1]+"/"+t[0]);/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/g.test(n)?s<18?($(this).siblings(".errormsg").show(),u(this,"Minor not eligible")):($(this).siblings(".errormsg").hide(),u(this,"Enter valid date of birth")):($(this).siblings(".errormsg").show(),u(this,"Enter valid date of birth"))}else if($(this).hasClass("FullNameVD")){var e=$(this).val().trim();$(this).val(e),/^[a-zA-Z ]*$/g.test($(this).val())?1==$(this).val().split(" ").length?$(this).siblings(".errormsg").show():$(this).val().split(" ").length>4?($(this).siblings(".errormsg").show(),u(this,"More than 3 space is not allowed")):($(this).siblings(".errormsg").hide(),u(this)):($(this).siblings(".errormsg").show(),u(this,"Only alphabets are allowed"))}else if($(this).hasClass("PanVD")){((i=$(this).val()).length<10||i.length>10)&&($(this).siblings(".errormsg").show(),u(this,"PAN number should be of 10 characters")),/^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/.test($(this).val())?"P"==$(this).val().substr(3,1)?($(this).siblings(".errormsg").hide(),u(this)):($(this).siblings(".errormsg").show(),u(this,"Enter valid PAN number; eg: ABCPC9999A")):($(this).siblings(".errormsg").show(),u(this,"Enter valid PAN number; eg: ABCHE9999A"))}else if($(this).hasClass("emailVD")){e=$(this).val().trim();$(this).val(e);var n=$(this).val();/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(n)?($(this).siblings(".errormsg").hide(),u(this)):($(this).siblings(".errormsg").show(),u(this,"Please enter your valid email ID"))}else if($(this).hasClass("AddressVD")){i=$(this).val();/[`~.<>;':"\/\[\]\|{}()=_+-]/g.test($(this).val())?($(this).siblings(".errormsg").show(),u(this,"<>?'=+-_^`~  not allowed")):($(this).siblings(".errormsg").hide(),u(this))}}),$(".PinCodeVD").keyup(function(i){var t=$(this).val();t.length>6&&$(this).val(t.substr(0,6))}),$(".FullNameVD").bind("keypress keyup blur",function(){var i=$(this);i.val(i.val().replace(/[^A-Za-z ]/g,""))}),$(".mobileVD").keyup(function(i){var t=$(this).val();t.length>10&&$(this).val(t.substr(0,10)),10==t.length?($("#mobilenumber_input .submitBTN").prop("disabled",!1),$(".ind_number +a").addClass("numberver")):$(".ind_number +a").removeClass("numberver")});function g(i){var t=new Date,s=new Date(i),e=t.getFullYear()-s.getFullYear(),n=t.getMonth()-s.getMonth();return(n<0||0===n&&t.getDate()<s.getDate())&&e--,e}function p(i){i.hasClass("nomandetory")||("none"==i.siblings(".errormsg").css("display")?i.parents(".inputMainBlock").addClass("valid").removeClass("invalid"):i.parents(".inputMainBlock").removeClass("valid").addClass("invalid"))}$("#basicDetailsForm .inputMainBlock input").blur(function(){setTimeout(function(){var i=0;$("#basicDetailsForm .errormsg").each(function(t){"block"==$(this).css("display")&&i++}),$("#basicDetailsForm input").each(function(t){""==$(this).val()&&i++}),0==i?$(".Calc_part").slideDown(200):$(".Calc_part").slideUp(200)},500)}),$("#basicDetailsForm_2 .inputMainBlock input").blur(function(){setTimeout(function(){var i=0;$("#basicDetailsForm_2 .errormsg").each(function(t){"block"==$(this).css("display")&&i++}),$("#basicDetailsForm_2 input").each(function(t){""==$(this).val()&&i++}),0==i?$(".Calc_part").slideDown(200):$(".Calc_part").slideUp(200)},500)}),$(".dobVD").keyup(function(i){var t=$(this).val();t.length>10&&$(this).val(t.substr(0,10)),t.length,8!=i.keyCode&&(2==$(this).val().length?$(this).val($(this).val()+"/"):5==$(this).val().length&&$(this).val($(this).val()+"/"))}),$(".dobVD").keydown(function(i){return k=i.which,k>=48&&k<=57||k>=96&&k<=105||8==k||9==k?2==$(this).val().length||5==$(this).val().length||10==$(this).val().length?8==k||9==k||(i.preventDefault(),!1):void 0:(i.preventDefault(),!1)}),$(".calendarOpen").keyup(function(i){var t=$(this).val();t.length>10&&$(this).val(t.substr(0,10)),t.length,8!=i.keyCode&&(2==$(this).val().length?$(this).val($(this).val()+"/"):5==$(this).val().length&&$(this).val($(this).val()+"/"))}),$(".calendarOpen").keydown(function(i){return k=i.which,k>=48&&k<=57||k>=96&&k<=105||8==k||9==k?2==$(this).val().length||5==$(this).val().length||10==$(this).val().length?8==k||9==k||(i.preventDefault(),!1):void 0:(i.preventDefault(),!1)}),$(".inputMainBlock input").blur(function(){$(this).parent().removeClass("active");var i=$(this);setTimeout(function(){p(i)},300)}),$(".inputMainBlock select").change(function(){$(this).parent().removeClass("active");var i=$(this);setTimeout(function(){p(i)},300)}),$("#apply_for_new_fd_form .validBtn").click(function(i){i.preventDefault(),setTimeout(function(){var i=0;$("#apply_for_new_fd_form .errormsg").each(function(t){"block"==$(this).css("display")&&i++}),0==i&&($(".part_2 .forMobileform").hide(),$(".part_2 .forOtpForm").show(),$(".part_2 .loginFirstStep").hide(),$(".part_2 .otpMainPart").show(),$(".otpPart input").first().focus(),c())},500)}),$("#resume_applicatIon_form .validBtn").click(function(i){i.preventDefault(),setTimeout(function(){var i=0;$("#resume_applicatIon_form .errormsg").each(function(t){"block"==$(this).css("display")&&i++}),0==i&&($(".part_2 .forMobileform").hide(),$(".part_2 .forOtpForm").show(),$(".part_2 .loginFirstStep").hide(),$(".part_2 .otpMainPart").show(),$(".otpPart input").first().focus(),c())},500)}),$("#otpForm .validBtn").click(function(i){i.preventDefault(),setTimeout(function(){$("#otpForm :input").each(function(){""!=$(this).val()?$(this).removeClass("invalid").addClass("valid"):$(this).addClass("invalid").removeClass("valid")});if(0!=parseInt($(".otpPart").find(".invalid").length))return!1;$(".part_2").hide(),$(".part_3").show()},500)}),$("#personal_details_1 .validBtn").click(function(i){i.preventDefault(),setTimeout(function(){var i=0;$("#personal_details_1 .errormsg").each(function(t){"block"==$(this).css("display")&&i++}),0==i&&($(".part_3").hide(),$(".part_4").show())},500)}),$("#personal_details_2 .validBtn").click(function(i){i.preventDefault(),setTimeout(function(){var i=0;$("#personal_details_2 .errormsg").each(function(t){"block"==$(this).css("display")&&i++}),0==i&&alert("personal_details_2")},500)})});
