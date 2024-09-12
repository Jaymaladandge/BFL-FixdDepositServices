<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
   <% 
   String utm_source=request.getParameter("utm_source");
   String utm_medium=request.getParameter("utm_medium");
   String utm_keyword=request.getParameter("utm_keyword");
   String utm_campaign=request.getParameter("utm_campaign");
   String utm_content=request.getParameter("utm_content");
   %>       

	<input id="utm_source" type="hidden" value='<c:out value="${utm_source}"/>'/>
	<input id="utm_medium" type="hidden" value='<c:out value="${utm_medium}"/>'/>
	<input id="utm_keyword" type="hidden" value='<c:out value="${utm_keyword}"/>'/>
	<input id="utm_campaign" type="hidden" value='<c:out value="${utm_campaign}"/>'/>
	<input id="utm_content" type="hidden" value='<c:out value="${utm_content}"/>'/>
	
 <script>

 

var utm_source;
var utm_medium;
var utm_keyword;
var utm_campaign;
var utm_content;  
var referrer;

/* 	Extract value form url starts*/   
function getUrlValue(name){             
	   if(name=(new RegExp('[?&]'+encodeURIComponent(name)+'=([^&]*)')).exec(location.search))
	      return decodeURIComponent(name[1]);
	}   
/* 	Extract value form url ends*/

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

$(document).ready(function(){
	



	<%!	
	public String ReplaceUTM(String UTMparater)
	{
	 
	 UTMparater=( UTMparater != null ? UTMparater.replaceAll("[\\\\?'\";:/<>)(*^%$#@!]","") : UTMparater  );
	 return UTMparater;
	}
	%>


	utm_source= $('#utm_source').val().trim();
	utm_medium= $('#utm_medium').val().trim();
	utm_keyword= $('#utm_keyword').val().trim(); 
	utm_campaign= $('#utm_campaign').val().trim();
	utm_content= $('#utm_content').val().trim();       
	     
			if(document.referrer=== undefined || document.referrer === null )  
			{
				 referrer='NA';
				 console.log("Unknown");
			}
			else if(document.referrer.split('/')[2] === window.location.hostname)
			{
			  
				referrer='samedomain';
				console.log(document.referrer.split('/')[2]);
			}
			else
			{
				referrer=document.referrer;
				console.log(referrer);
			}
			
			var campaignCookie=GetCookie('campaignCookie');
			
			
			if(campaignCookie !=null)
			{
				campaignCookie=JSON.parse(campaignCookie);
				 var campaign={};
				
				 /* 1. When user directly land on site within 30 mins of cookie exist */
					if(referrer=='NA')	
						{
						console.log("--- Direct Landing -----");
						 campaign.utm_medium='bfl';
						 campaign.utm_campaign='NA';
						 campaign.utm_keyword='NA';
						 campaign.utm_source='organic_bfl';
						}  
				 
				 
					/*  2. When user travers from same domain to another page  */
					
					
						else if(referrer=='samedomain')
						{
							console.log("--- Same Domain -----");
							if(utm_medium !=null || utm_campaign!= null || utm_keyword!= null || utm_source!= null || utm_content!= null )
							{
								 if(utm_medium==='null' || utm_medium===null)
								 {
									 if(campaignCookie.utm_medium!='bfl')
									 {
										 campaign.utm_medium=campaignCookie.utm_medium;
									 }
									 else
									 {
										 campaign.utm_medium='bfl';
									 }
									
								 }
								 else {
									
									 campaign.utm_medium=utm_medium;
								 }
								 if(utm_campaign==='null' || utm_campaign===null){
									 if(campaignCookie.utm_campaign!='NA')
									 {
										 campaign.utm_campaign=campaignCookie.utm_campaign;
									 }
									 else
									 {
										 campaign.utm_campaign='NA';
									 }
								 }
								 else {
									 campaign.utm_campaign=utm_campaign;
								 }
								 if(utm_keyword==='null' || utm_keyword===null){
									 if(campaignCookie.utm_keyword!='NA')
									 {
										 campaign.utm_keyword=campaignCookie.utm_keyword;
									 }
									 else
									 {
										 campaign.utm_keyword='NA';
									 }
								 }
								 else {
									 campaign.utm_keyword=utm_keyword;
								 }
								 
								 if(utm_source==='null' || utm_source===null){
									 if(campaignCookie.utm_source!='organic_bfl')
									 {
										 campaign.utm_source=campaignCookie.utm_source;
									 }
									 else
									 {
										 campaign.utm_source='organic_bfl';
									 }

								 }
								 else {
									 campaign.utm_source=utm_source;
								 }
								 
								 
								 if(utm_content==='null' || utm_content ===null){
									 if(campaignCookie.utm_content!='NA')
									 {
										 campaign.utm_content=campaignCookie.utm_content;
									 }
									 else
									 {
										 campaign.utm_content='NA';
									 }

								 }
								 else {
									 campaign.utm_content=utm_content;  
								 }
							  
							}
						}
				 
					 /* 3. When user lands through campaign page    */
					 
						else
							{
							console.log("--- Campaign Page  -----");
								if(utm_medium !=null || utm_campaign!= null || utm_keyword!= null || utm_source!= null || utm_content!= null )
								{
								 if(utm_medium==='null' || utm_medium===null)
								 {
									 if(campaignCookie.utm_medium!='bfl')
									 {
										 campaign.utm_medium=campaignCookie.utm_medium;
									 }
									 else
									 {
										 campaign.utm_medium='bfl';
									 }
									
								 }
								 else {
									
									 campaign.utm_medium=utm_medium;
								 }
								 if(utm_campaign==='null' || utm_campaign===null){
									 if(campaignCookie.utm_campaign!='NA')
									 {
										 campaign.utm_campaign=campaignCookie.utm_campaign;
									 }
									 else
									 {
										 campaign.utm_campaign='NA';
									 }
								 }
								 else {
									 campaign.utm_campaign=utm_campaign;
								 }
								 if(utm_keyword==='null' || utm_keyword===null){
									 if(campaignCookie.utm_keyword!='NA')
									 {
										 campaign.utm_keyword=campaignCookie.utm_keyword;
									 }
									 else
									 {
										 campaign.utm_keyword='NA';
									 }
								 }  
								 else {
									 campaign.utm_keyword=utm_keyword;
								 }
								 
								 if(utm_source==='null' || utm_source===null){
									 if(campaignCookie.utm_source!='organic_bfl')
									 {
										 campaign.utm_source=campaignCookie.utm_source;
									 }
									 else
									 {
										 campaign.utm_source='organic_bfl';
									 }

								 }
								 else {
									 campaign.utm_source=utm_source;
								 }
								 
								 
								 if(utm_content==='null' || utm_content ===null){
									 if(campaignCookie.utm_content!='NA')
									 {
										 campaign.utm_content=campaignCookie.utm_content;
									 }
									 else
									 {
										 campaign.utm_content='NA';
									 }

								 }
								 else {
									 campaign.utm_content=utm_content;  
								 }
							  
							}
						}
						
						 var date=new Date();
						  date.setTime(date.getTime()+(30*60*1000));
						  document.cookie = "campaignCookie="+JSON.stringify(campaign)+"; expires="+date.toGMTString()+"; path=/"+ "; secure";
				
				
			}else{
				 var date=new Date();
				  date.setTime(date.getTime()+(30*60*1000));
				  var campaign={};
				  if(utm_medium==null || utm_medium==='null')
				  {
					  campaign.utm_medium='bfl';  
				  }
				  else{
					  campaign.utm_medium=utm_medium;
				  }					  
				  if(utm_campaign==null || utm_campaign==='null')
				  {
				  	campaign.utm_campaign='NA';
				  }
				  else{
					  campaign.utm_campaign=utm_campaign;
				  }
				  if(utm_keyword==null || utm_keyword==='null'){  
					  	campaign.utm_keyword='NA';
				  }
					  else{
						  campaign.utm_keyword=utm_keyword;
					  }
				  if(utm_source==null || utm_source==='null'){  
					  	campaign.utm_source='organic_bfl';
				  }
					  else{
						  campaign.utm_source=utm_source;  
					  }
				  if(utm_content==null || utm_content==='null'){  
					  	campaign.utm_content='NA';
				  }
					  else{
						  campaign.utm_content=utm_content;
					  }
				  console.log(JSON.stringify(campaign) + " "+utm_medium+" "+utm_campaign+" "+utm_keyword+" "+utm_source);
					document.cookie = "campaignCookie="+JSON.stringify(campaign)+";expires="+date.toGMTString()+";  path=/"+ "; secure";  
			}
			
});  		
			   
</script>
 
</head>


