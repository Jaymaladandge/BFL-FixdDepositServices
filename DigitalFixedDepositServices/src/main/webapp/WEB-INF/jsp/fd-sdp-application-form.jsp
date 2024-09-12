<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false" import="java.util.ResourceBundle"%>
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
	content="Bajaj Finance Fixed Deposit offers attractive interest rates up to 8.10% p.a., so you can invest easily from the comfort of your home. Get assured returns with 0.25% higher interest rate for senior citizens.">
<meta name="keywords"
	content="Online fd application form, fixed deposit online, invest in fd online, open fd online">

<link rel="SHORTCUT ICON"
	href="${pageContext.request.contextPath}/resources/images/baflicon.ico" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/Style.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/newCss/style.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/newCss/daterangepicker.css" />
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

<%@ include file="wrapper.jsp"%>

<!-- Global site tag (gtag.js) - Google Ads: 802197272 -->
 <script async src="https://www.googletagmanager.com/gtag/js?id=AW-802197272"></script> 
 <script async src="https://assets.adobedtm.com/2d6d124a9338/1706dfea1c4d/launch-7fb3ad47deaf.min.js"></script>
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
<script type="text/javascript">
;window.NREUM||(NREUM={});NREUM.init={distributed_tracing:{enabled:true},privacy:{cookies_enabled:true},ajax:{deny_list:["bam.nr-data.net"]}};
window.NREUM||(NREUM={}),__nr_require=function(t,e,n){function r(n){if(!e[n]){var o=e[n]={exports:{}};t[n][0].call(o.exports,function(e){var o=t[n][1][e];return r(o||e)},o,o.exports)}return e[n].exports}if("function"==typeof __nr_require)return __nr_require;for(var o=0;o<n.length;o++)r(n[o]);return r}({1:[function(t,e,n){function r(t){try{s.console&&console.log(t)}catch(e){}}var o,i=t("ee"),a=t(30),s={};try{o=localStorage.getItem("__nr_flags").split(","),console&&"function"==typeof console.log&&(s.console=!0,o.indexOf("dev")!==-1&&(s.dev=!0),o.indexOf("nr_dev")!==-1&&(s.nrDev=!0))}catch(c){}s.nrDev&&i.on("internal-error",function(t){r(t.stack)}),s.dev&&i.on("fn-err",function(t,e,n){r(n.stack)}),s.dev&&(r("NR AGENT IN DEVELOPMENT MODE"),r("flags: "+a(s,function(t,e){return t}).join(", ")))},{}],2:[function(t,e,n){function r(t,e,n,r,s){try{l?l-=1:o(s||new UncaughtException(t,e,n),!0)}catch(f){try{i("ierr",[f,c.now(),!0])}catch(d){}}return"function"==typeof u&&u.apply(this,a(arguments))}function UncaughtException(t,e,n){this.message=t||"Uncaught error with no additional information",this.sourceURL=e,this.line=n}function o(t,e){var n=e?null:c.now();i("err",[t,n])}var i=t("handle"),a=t(31),s=t("ee"),c=t("loader"),f=t("gos"),u=window.onerror,d=!1,p="nr@seenError";if(!c.disabled){var l=0;c.features.err=!0,t(1),window.onerror=r;try{throw new Error}catch(h){"stack"in h&&(t(14),t(13),"addEventListener"in window&&t(7),c.xhrWrappable&&t(15),d=!0)}s.on("fn-start",function(t,e,n){d&&(l+=1)}),s.on("fn-err",function(t,e,n){d&&!n[p]&&(f(n,p,function(){return!0}),this.thrown=!0,o(n))}),s.on("fn-end",function(){d&&!this.thrown&&l>0&&(l-=1)}),s.on("internal-error",function(t){i("ierr",[t,c.now(),!0])})}},{}],3:[function(t,e,n){var r=t("loader");r.disabled||(r.features.ins=!0)},{}],4:[function(t,e,n){function r(){L++,C=g.hash,this[u]=y.now()}function o(){L--,g.hash!==C&&i(0,!0);var t=y.now();this[h]=~~this[h]+t-this[u],this[d]=t}function i(t,e){E.emit("newURL",[""+g,e])}function a(t,e){t.on(e,function(){this[e]=y.now()})}var s="-start",c="-end",f="-body",u="fn"+s,d="fn"+c,p="cb"+s,l="cb"+c,h="jsTime",m="fetch",v="addEventListener",w=window,g=w.location,y=t("loader");if(w[v]&&y.xhrWrappable&&!y.disabled){var x=t(11),b=t(12),E=t(9),R=t(7),O=t(14),S=t(8),T=t(15),N=t(10),P=t("ee"),M=P.get("tracer");t(17),y.features.spa=!0;var C,L=0;P.on(u,r),b.on(p,r),N.on(p,r),P.on(d,o),b.on(l,o),N.on(l,o),P.buffer([u,d,"xhr-resolved"]),R.buffer([u]),O.buffer(["setTimeout"+c,"clearTimeout"+s,u]),T.buffer([u,"new-xhr","send-xhr"+s]),S.buffer([m+s,m+"-done",m+f+s,m+f+c]),E.buffer(["newURL"]),x.buffer([u]),b.buffer(["propagate",p,l,"executor-err","resolve"+s]),M.buffer([u,"no-"+u]),N.buffer(["new-jsonp","cb-start","jsonp-error","jsonp-end"]),a(S,m+s),a(S,m+"-done"),a(N,"new-jsonp"),a(N,"jsonp-end"),a(N,"cb-start"),E.on("pushState-end",i),E.on("replaceState-end",i),w[v]("hashchange",i,!0),w[v]("load",i,!0),w[v]("popstate",function(){i(0,L>1)},!0)}},{}],5:[function(t,e,n){function r(){var t=new PerformanceObserver(function(t,e){var n=t.getEntries();s(m,[n])});try{t.observe({entryTypes:["resource"]})}catch(e){}}function o(t){if(s(m,[window.performance.getEntriesByType(v)]),window.performance["c"+d])try{window.performance[l](h,o,!1)}catch(t){}else try{window.performance[l]("webkit"+h,o,!1)}catch(t){}}function i(t){}if(window.performance&&window.performance.timing&&window.performance.getEntriesByType){var a=t("ee"),s=t("handle"),c=t(14),f=t(13),u=t(6),d="learResourceTimings",p="addEventListener",l="removeEventListener",h="resourcetimingbufferfull",m="bstResource",v="resource",w="-start",g="-end",y="fn"+w,x="fn"+g,b="bstTimer",E="pushState",R=t("loader");if(!R.disabled){R.features.stn=!0,t(9),"addEventListener"in window&&t(7);var O=NREUM.o.EV;a.on(y,function(t,e){var n=t[0];n instanceof O&&(this.bstStart=R.now())}),a.on(x,function(t,e){var n=t[0];n instanceof O&&s("bst",[n,e,this.bstStart,R.now()])}),c.on(y,function(t,e,n){this.bstStart=R.now(),this.bstType=n}),c.on(x,function(t,e){s(b,[e,this.bstStart,R.now(),this.bstType])}),f.on(y,function(){this.bstStart=R.now()}),f.on(x,function(t,e){s(b,[e,this.bstStart,R.now(),"requestAnimationFrame"])}),a.on(E+w,function(t){this.time=R.now(),this.startPath=location.pathname+location.hash}),a.on(E+g,function(t){s("bstHist",[location.pathname+location.hash,this.startPath,this.time])}),u()?(s(m,[window.performance.getEntriesByType("resource")]),r()):p in window.performance&&(window.performance["c"+d]?window.performance[p](h,o,!1):window.performance[p]("webkit"+h,o,!1)),document[p]("scroll",i,{passive:!0}),document[p]("keypress",i,!1),document[p]("click",i,!1)}}},{}],6:[function(t,e,n){e.exports=function(){return"PerformanceObserver"in window&&"function"==typeof window.PerformanceObserver}},{}],7:[function(t,e,n){function r(t){for(var e=t;e&&!e.hasOwnProperty(u);)e=Object.getPrototypeOf(e);e&&o(e)}function o(t){s.inPlace(t,[u,d],"-",i)}function i(t,e){return t[1]}var a=t("ee").get("events"),s=t("wrap-function")(a,!0),c=t("gos"),f=XMLHttpRequest,u="addEventListener",d="removeEventListener";e.exports=a,"getPrototypeOf"in Object?(r(document),r(window),r(f.prototype)):f.prototype.hasOwnProperty(u)&&(o(window),o(f.prototype)),a.on(u+"-start",function(t,e){var n=t[1],r=c(n,"nr@wrapped",function(){function t(){if("function"==typeof n.handleEvent)return n.handleEvent.apply(n,arguments)}var e={object:t,"function":n}[typeof n];return e?s(e,"fn-",null,e.name||"anonymous"):n});this.wrapped=t[1]=r}),a.on(d+"-start",function(t){t[1]=this.wrapped||t[1]})},{}],8:[function(t,e,n){function r(t,e,n){var r=t[e];"function"==typeof r&&(t[e]=function(){var t=i(arguments),e={};o.emit(n+"before-start",[t],e);var a;e[m]&&e[m].dt&&(a=e[m].dt);var s=r.apply(this,t);return o.emit(n+"start",[t,a],s),s.then(function(t){return o.emit(n+"end",[null,t],s),t},function(t){throw o.emit(n+"end",[t],s),t})})}var o=t("ee").get("fetch"),i=t(31),a=t(30);e.exports=o;var s=window,c="fetch-",f=c+"body-",u=["arrayBuffer","blob","json","text","formData"],d=s.Request,p=s.Response,l=s.fetch,h="prototype",m="nr@context";d&&p&&l&&(a(u,function(t,e){r(d[h],e,f),r(p[h],e,f)}),r(s,"fetch",c),o.on(c+"end",function(t,e){var n=this;if(e){var r=e.headers.get("content-length");null!==r&&(n.rxSize=r),o.emit(c+"done",[null,e],n)}else o.emit(c+"done",[t],n)}))},{}],9:[function(t,e,n){var r=t("ee").get("history"),o=t("wrap-function")(r);e.exports=r;var i=window.history&&window.history.constructor&&window.history.constructor.prototype,a=window.history;i&&i.pushState&&i.replaceState&&(a=i),o.inPlace(a,["pushState","replaceState"],"-")},{}],10:[function(t,e,n){function r(t){function e(){c.emit("jsonp-end",[],p),t.removeEventListener("load",e,!1),t.removeEventListener("error",n,!1)}function n(){c.emit("jsonp-error",[],p),c.emit("jsonp-end",[],p),t.removeEventListener("load",e,!1),t.removeEventListener("error",n,!1)}var r=t&&"string"==typeof t.nodeName&&"script"===t.nodeName.toLowerCase();if(r){var o="function"==typeof t.addEventListener;if(o){var a=i(t.src);if(a){var u=s(a),d="function"==typeof u.parent[u.key];if(d){var p={};f.inPlace(u.parent,[u.key],"cb-",p),t.addEventListener("load",e,!1),t.addEventListener("error",n,!1),c.emit("new-jsonp",[t.src],p)}}}}}function o(){return"addEventListener"in window}function i(t){var e=t.match(u);return e?e[1]:null}function a(t,e){var n=t.match(p),r=n[1],o=n[3];return o?a(o,e[r]):e[r]}function s(t){var e=t.match(d);return e&&e.length>=3?{key:e[2],parent:a(e[1],window)}:{key:t,parent:window}}var c=t("ee").get("jsonp"),f=t("wrap-function")(c);if(e.exports=c,o()){var u=/[?&](?:callback|cb)=([^&#]+)/,d=/(.*)\.([^.]+)/,p=/^(\w+)(\.|$)(.*)$/,l=["appendChild","insertBefore","replaceChild"];Node&&Node.prototype&&Node.prototype.appendChild?f.inPlace(Node.prototype,l,"dom-"):(f.inPlace(HTMLElement.prototype,l,"dom-"),f.inPlace(HTMLHeadElement.prototype,l,"dom-"),f.inPlace(HTMLBodyElement.prototype,l,"dom-")),c.on("dom-start",function(t){r(t[0])})}},{}],11:[function(t,e,n){var r=t("ee").get("mutation"),o=t("wrap-function")(r),i=NREUM.o.MO;e.exports=r,i&&(window.MutationObserver=function(t){return this instanceof i?new i(o(t,"fn-")):i.apply(this,arguments)},MutationObserver.prototype=i.prototype)},{}],12:[function(t,e,n){function r(t){var e=i.context(),n=s(t,"executor-",e,null,!1),r=new f(n);return i.context(r).getCtx=function(){return e},r}var o=t("wrap-function"),i=t("ee").get("promise"),a=t("ee").getOrSetContext,s=o(i),c=t(30),f=NREUM.o.PR;e.exports=i,f&&(window.Promise=r,["all","race"].forEach(function(t){var e=f[t];f[t]=function(n){function r(t){return function(){i.emit("propagate",[null,!o],a,!1,!1),o=o||!t}}var o=!1;c(n,function(e,n){Promise.resolve(n).then(r("all"===t),r(!1))});var a=e.apply(f,arguments),s=f.resolve(a);return s}}),["resolve","reject"].forEach(function(t){var e=f[t];f[t]=function(t){var n=e.apply(f,arguments);return t!==n&&i.emit("propagate",[t,!0],n,!1,!1),n}}),f.prototype["catch"]=function(t){return this.then(null,t)},f.prototype=Object.create(f.prototype,{constructor:{value:r}}),c(Object.getOwnPropertyNames(f),function(t,e){try{r[e]=f[e]}catch(n){}}),o.wrapInPlace(f.prototype,"then",function(t){return function(){var e=this,n=o.argsToArray.apply(this,arguments),r=a(e);r.promise=e,n[0]=s(n[0],"cb-",r,null,!1),n[1]=s(n[1],"cb-",r,null,!1);var c=t.apply(this,n);return r.nextPromise=c,i.emit("propagate",[e,!0],c,!1,!1),c}}),i.on("executor-start",function(t){t[0]=s(t[0],"resolve-",this,null,!1),t[1]=s(t[1],"resolve-",this,null,!1)}),i.on("executor-err",function(t,e,n){t[1](n)}),i.on("cb-end",function(t,e,n){i.emit("propagate",[n,!0],this.nextPromise,!1,!1)}),i.on("propagate",function(t,e,n){this.getCtx&&!e||(this.getCtx=function(){if(t instanceof Promise)var e=i.context(t);return e&&e.getCtx?e.getCtx():this})}),r.toString=function(){return""+f})},{}],13:[function(t,e,n){var r=t("ee").get("raf"),o=t("wrap-function")(r),i="equestAnimationFrame";e.exports=r,o.inPlace(window,["r"+i,"mozR"+i,"webkitR"+i,"msR"+i],"raf-"),r.on("raf-start",function(t){t[0]=o(t[0],"fn-")})},{}],14:[function(t,e,n){function r(t,e,n){t[0]=a(t[0],"fn-",null,n)}function o(t,e,n){this.method=n,this.timerDuration=isNaN(t[1])?0:+t[1],t[0]=a(t[0],"fn-",this,n)}var i=t("ee").get("timer"),a=t("wrap-function")(i),s="setTimeout",c="setInterval",f="clearTimeout",u="-start",d="-";e.exports=i,a.inPlace(window,[s,"setImmediate"],s+d),a.inPlace(window,[c],c+d),a.inPlace(window,[f,"clearImmediate"],f+d),i.on(c+u,r),i.on(s+u,o)},{}],15:[function(t,e,n){function r(t,e){d.inPlace(e,["onreadystatechange"],"fn-",s)}function o(){var t=this,e=u.context(t);t.readyState>3&&!e.resolved&&(e.resolved=!0,u.emit("xhr-resolved",[],t)),d.inPlace(t,g,"fn-",s)}function i(t){y.push(t),h&&(b?b.then(a):v?v(a):(E=-E,R.data=E))}function a(){for(var t=0;t<y.length;t++)r([],y[t]);y.length&&(y=[])}function s(t,e){return e}function c(t,e){for(var n in t)e[n]=t[n];return e}t(7);var f=t("ee"),u=f.get("xhr"),d=t("wrap-function")(u),p=NREUM.o,l=p.XHR,h=p.MO,m=p.PR,v=p.SI,w="readystatechange",g=["onload","onerror","onabort","onloadstart","onloadend","onprogress","ontimeout"],y=[];e.exports=u;var x=window.XMLHttpRequest=function(t){var e=new l(t);try{u.emit("new-xhr",[e],e),e.addEventListener(w,o,!1)}catch(n){try{u.emit("internal-error",[n])}catch(r){}}return e};if(c(l,x),x.prototype=l.prototype,d.inPlace(x.prototype,["open","send"],"-xhr-",s),u.on("send-xhr-start",function(t,e){r(t,e),i(e)}),u.on("open-xhr-start",r),h){var b=m&&m.resolve();if(!v&&!m){var E=1,R=document.createTextNode(E);new h(a).observe(R,{characterData:!0})}}else f.on("fn-end",function(t){t[0]&&t[0].type===w||a()})},{}],16:[function(t,e,n){function r(t){if(!s(t))return null;var e=window.NREUM;if(!e.loader_config)return null;var n=(e.loader_config.accountID||"").toString()||null,r=(e.loader_config.agentID||"").toString()||null,f=(e.loader_config.trustKey||"").toString()||null;if(!n||!r)return null;var h=l.generateSpanId(),m=l.generateTraceId(),v=Date.now(),w={spanId:h,traceId:m,timestamp:v};return(t.sameOrigin||c(t)&&p())&&(w.traceContextParentHeader=o(h,m),w.traceContextStateHeader=i(h,v,n,r,f)),(t.sameOrigin&&!u()||!t.sameOrigin&&c(t)&&d())&&(w.newrelicHeader=a(h,m,v,n,r,f)),w}function o(t,e){return"00-"+e+"-"+t+"-01"}function i(t,e,n,r,o){var i=0,a="",s=1,c="",f="";return o+"@nr="+i+"-"+s+"-"+n+"-"+r+"-"+t+"-"+a+"-"+c+"-"+f+"-"+e}function a(t,e,n,r,o,i){var a="btoa"in window&&"function"==typeof window.btoa;if(!a)return null;var s={v:[0,1],d:{ty:"Browser",ac:r,ap:o,id:t,tr:e,ti:n}};return i&&r!==i&&(s.d.tk=i),btoa(JSON.stringify(s))}function s(t){return f()&&c(t)}function c(t){var e=!1,n={};if("init"in NREUM&&"distributed_tracing"in NREUM.init&&(n=NREUM.init.distributed_tracing),t.sameOrigin)e=!0;else if(n.allowed_origins instanceof Array)for(var r=0;r<n.allowed_origins.length;r++){var o=h(n.allowed_origins[r]);if(t.hostname===o.hostname&&t.protocol===o.protocol&&t.port===o.port){e=!0;break}}return e}function f(){return"init"in NREUM&&"distributed_tracing"in NREUM.init&&!!NREUM.init.distributed_tracing.enabled}function u(){return"init"in NREUM&&"distributed_tracing"in NREUM.init&&!!NREUM.init.distributed_tracing.exclude_newrelic_header}function d(){return"init"in NREUM&&"distributed_tracing"in NREUM.init&&NREUM.init.distributed_tracing.cors_use_newrelic_header!==!1}function p(){return"init"in NREUM&&"distributed_tracing"in NREUM.init&&!!NREUM.init.distributed_tracing.cors_use_tracecontext_headers}var l=t(27),h=t(18);e.exports={generateTracePayload:r,shouldGenerateTrace:s}},{}],17:[function(t,e,n){function r(t){var e=this.params,n=this.metrics;if(!this.ended){this.ended=!0;for(var r=0;r<p;r++)t.removeEventListener(d[r],this.listener,!1);e.aborted||(n.duration=a.now()-this.startTime,this.loadCaptureCalled||4!==t.readyState?null==e.status&&(e.status=0):i(this,t),n.cbTime=this.cbTime,s("xhr",[e,n,this.startTime,this.endTime,"xhr"],this))}}function o(t,e){var n=c(e),r=t.params;r.hostname=n.hostname,r.port=n.port,r.protocol=n.protocol,r.host=n.hostname+":"+n.port,r.pathname=n.pathname,t.parsedOrigin=n,t.sameOrigin=n.sameOrigin}function i(t,e){t.params.status=e.status;var n=v(e,t.lastSize);if(n&&(t.metrics.rxSize=n),t.sameOrigin){var r=e.getResponseHeader("X-NewRelic-App-Data");r&&(t.params.cat=r.split(", ").pop())}t.loadCaptureCalled=!0}var a=t("loader");if(a.xhrWrappable&&!a.disabled){var s=t("handle"),c=t(18),f=t(16).generateTracePayload,u=t("ee"),d=["load","error","abort","timeout"],p=d.length,l=t("id"),h=t(23),m=t(22),v=t(19),w=NREUM.o.REQ,g=window.XMLHttpRequest;a.features.xhr=!0,t(15),t(8),u.on("new-xhr",function(t){var e=this;e.totalCbs=0,e.called=0,e.cbTime=0,e.end=r,e.ended=!1,e.xhrGuids={},e.lastSize=null,e.loadCaptureCalled=!1,e.params=this.params||{},e.metrics=this.metrics||{},t.addEventListener("load",function(n){i(e,t)},!1),h&&(h>34||h<10)||t.addEventListener("progress",function(t){e.lastSize=t.loaded},!1)}),u.on("open-xhr-start",function(t){this.params={method:t[0]},o(this,t[1]),this.metrics={}}),u.on("open-xhr-end",function(t,e){"loader_config"in NREUM&&"xpid"in NREUM.loader_config&&this.sameOrigin&&e.setRequestHeader("X-NewRelic-ID",NREUM.loader_config.xpid);var n=f(this.parsedOrigin);if(n){var r=!1;n.newrelicHeader&&(e.setRequestHeader("newrelic",n.newrelicHeader),r=!0),n.traceContextParentHeader&&(e.setRequestHeader("traceparent",n.traceContextParentHeader),n.traceContextStateHeader&&e.setRequestHeader("tracestate",n.traceContextStateHeader),r=!0),r&&(this.dt=n)}}),u.on("send-xhr-start",function(t,e){var n=this.metrics,r=t[0],o=this;if(n&&r){var i=m(r);i&&(n.txSize=i)}this.startTime=a.now(),this.listener=function(t){try{"abort"!==t.type||o.loadCaptureCalled||(o.params.aborted=!0),("load"!==t.type||o.called===o.totalCbs&&(o.onloadCalled||"function"!=typeof e.onload))&&o.end(e)}catch(n){try{u.emit("internal-error",[n])}catch(r){}}};for(var s=0;s<p;s++)e.addEventListener(d[s],this.listener,!1)}),u.on("xhr-cb-time",function(t,e,n){this.cbTime+=t,e?this.onloadCalled=!0:this.called+=1,this.called!==this.totalCbs||!this.onloadCalled&&"function"==typeof n.onload||this.end(n)}),u.on("xhr-load-added",function(t,e){var n=""+l(t)+!!e;this.xhrGuids&&!this.xhrGuids[n]&&(this.xhrGuids[n]=!0,this.totalCbs+=1)}),u.on("xhr-load-removed",function(t,e){var n=""+l(t)+!!e;this.xhrGuids&&this.xhrGuids[n]&&(delete this.xhrGuids[n],this.totalCbs-=1)}),u.on("xhr-resolved",function(){this.endTime=a.now()}),u.on("addEventListener-end",function(t,e){e instanceof g&&"load"===t[0]&&u.emit("xhr-load-added",[t[1],t[2]],e)}),u.on("removeEventListener-end",function(t,e){e instanceof g&&"load"===t[0]&&u.emit("xhr-load-removed",[t[1],t[2]],e)}),u.on("fn-start",function(t,e,n){e instanceof g&&("onload"===n&&(this.onload=!0),("load"===(t[0]&&t[0].type)||this.onload)&&(this.xhrCbStart=a.now()))}),u.on("fn-end",function(t,e){this.xhrCbStart&&u.emit("xhr-cb-time",[a.now()-this.xhrCbStart,this.onload,e],e)}),u.on("fetch-before-start",function(t){function e(t,e){var n=!1;return e.newrelicHeader&&(t.set("newrelic",e.newrelicHeader),n=!0),e.traceContextParentHeader&&(t.set("traceparent",e.traceContextParentHeader),e.traceContextStateHeader&&t.set("tracestate",e.traceContextStateHeader),n=!0),n}var n,r=t[1]||{};"string"==typeof t[0]?n=t[0]:t[0]&&t[0].url?n=t[0].url:window.URL&&t[0]&&t[0]instanceof URL&&(n=t[0].href),n&&(this.parsedOrigin=c(n),this.sameOrigin=this.parsedOrigin.sameOrigin);var o=f(this.parsedOrigin);if(o&&(o.newrelicHeader||o.traceContextParentHeader))if("string"==typeof t[0]||window.URL&&t[0]&&t[0]instanceof URL){var i={};for(var a in r)i[a]=r[a];i.headers=new Headers(r.headers||{}),e(i.headers,o)&&(this.dt=o),t.length>1?t[1]=i:t.push(i)}else t[0]&&t[0].headers&&e(t[0].headers,o)&&(this.dt=o)}),u.on("fetch-start",function(t,e){this.params={},this.metrics={},this.startTime=a.now(),this.dt=e,t.length>=1&&(this.target=t[0]),t.length>=2&&(this.opts=t[1]);var n,r=this.opts||{},i=this.target;"string"==typeof i?n=i:"object"==typeof i&&i instanceof w?n=i.url:window.URL&&"object"==typeof i&&i instanceof URL&&(n=i.href),o(this,n);var s=(""+(i&&i instanceof w&&i.method||r.method||"GET")).toUpperCase();this.params.method=s,this.txSize=m(r.body)||0}),u.on("fetch-done",function(t,e){this.endTime=a.now(),this.params||(this.params={}),this.params.status=e?e.status:0;var n;"string"==typeof this.rxSize&&this.rxSize.length>0&&(n=+this.rxSize);var r={txSize:this.txSize,rxSize:n,duration:a.now()-this.startTime};s("xhr",[this.params,r,this.startTime,this.endTime,"fetch"],this)})}},{}],18:[function(t,e,n){var r={};e.exports=function(t){if(t in r)return r[t];var e=document.createElement("a"),n=window.location,o={};e.href=t,o.port=e.port;var i=e.href.split("://");!o.port&&i[1]&&(o.port=i[1].split("/")[0].split("@").pop().split(":")[1]),o.port&&"0"!==o.port||(o.port="https"===i[0]?"443":"80"),o.hostname=e.hostname||n.hostname,o.pathname=e.pathname,o.protocol=i[0],"/"!==o.pathname.charAt(0)&&(o.pathname="/"+o.pathname);var a=!e.protocol||":"===e.protocol||e.protocol===n.protocol,s=e.hostname===document.domain&&e.port===n.port;return o.sameOrigin=a&&(!e.hostname||s),"/"===o.pathname&&(r[t]=o),o}},{}],19:[function(t,e,n){function r(t,e){var n=t.responseType;return"json"===n&&null!==e?e:"arraybuffer"===n||"blob"===n||"json"===n?o(t.response):"text"===n||""===n||void 0===n?o(t.responseText):void 0}var o=t(22);e.exports=r},{}],20:[function(t,e,n){function r(){}function o(t,e,n){return function(){return i(t,[f.now()].concat(s(arguments)),e?null:this,n),e?void 0:this}}var i=t("handle"),a=t(30),s=t(31),c=t("ee").get("tracer"),f=t("loader"),u=NREUM;"undefined"==typeof window.newrelic&&(newrelic=u);var d=["setPageViewName","setCustomAttribute","setErrorHandler","finished","addToTrace","inlineHit","addRelease"],p="api-",l=p+"ixn-";a(d,function(t,e){u[e]=o(p+e,!0,"api")}),u.addPageAction=o(p+"addPageAction",!0),u.setCurrentRouteName=o(p+"routeName",!0),e.exports=newrelic,u.interaction=function(){return(new r).get()};var h=r.prototype={createTracer:function(t,e){var n={},r=this,o="function"==typeof e;return i(l+"tracer",[f.now(),t,n],r),function(){if(c.emit((o?"":"no-")+"fn-start",[f.now(),r,o],n),o)try{return e.apply(this,arguments)}catch(t){throw c.emit("fn-err",[arguments,this,t],n),t}finally{c.emit("fn-end",[f.now()],n)}}}};a("actionText,setName,setAttribute,save,ignore,onEnd,getContext,end,get".split(","),function(t,e){h[e]=o(l+e)}),newrelic.noticeError=function(t,e){"string"==typeof t&&(t=new Error(t)),i("err",[t,f.now(),!1,e])}},{}],21:[function(t,e,n){function r(t){if(NREUM.init){for(var e=NREUM.init,n=t.split("."),r=0;r<n.length-1;r++)if(e=e[n[r]],"object"!=typeof e)return;return e=e[n[n.length-1]]}}e.exports={getConfiguration:r}},{}],22:[function(t,e,n){e.exports=function(t){if("string"==typeof t&&t.length)return t.length;if("object"==typeof t){if("undefined"!=typeof ArrayBuffer&&t instanceof ArrayBuffer&&t.byteLength)return t.byteLength;if("undefined"!=typeof Blob&&t instanceof Blob&&t.size)return t.size;if(!("undefined"!=typeof FormData&&t instanceof FormData))try{return JSON.stringify(t).length}catch(e){return}}}},{}],23:[function(t,e,n){var r=0,o=navigator.userAgent.match(/Firefox[\/\s](\d+\.\d+)/);o&&(r=+o[1]),e.exports=r},{}],24:[function(t,e,n){function r(){return s.exists&&performance.now?Math.round(performance.now()):(i=Math.max((new Date).getTime(),i))-a}function o(){return i}var i=(new Date).getTime(),a=i,s=t(32);e.exports=r,e.exports.offset=a,e.exports.getLastTimestamp=o},{}],25:[function(t,e,n){function r(t){return!(!t||!t.protocol||"file:"===t.protocol)}e.exports=r},{}],26:[function(t,e,n){function r(t,e){var n=t.getEntries();n.forEach(function(t){"first-paint"===t.name?d("timing",["fp",Math.floor(t.startTime)]):"first-contentful-paint"===t.name&&d("timing",["fcp",Math.floor(t.startTime)])})}function o(t,e){var n=t.getEntries();n.length>0&&d("lcp",[n[n.length-1]])}function i(t){t.getEntries().forEach(function(t){t.hadRecentInput||d("cls",[t])})}function a(t){if(t instanceof h&&!v){var e=Math.round(t.timeStamp),n={type:t.type};e<=p.now()?n.fid=p.now()-e:e>p.offset&&e<=Date.now()?(e-=p.offset,n.fid=p.now()-e):e=p.now(),v=!0,d("timing",["fi",e,n])}}function s(t){"hidden"===t&&d("pageHide",[p.now()])}if(!("init"in NREUM&&"page_view_timing"in NREUM.init&&"enabled"in NREUM.init.page_view_timing&&NREUM.init.page_view_timing.enabled===!1)){var c,f,u,d=t("handle"),p=t("loader"),l=t(29),h=NREUM.o.EV;if("PerformanceObserver"in window&&"function"==typeof window.PerformanceObserver){c=new PerformanceObserver(r);try{c.observe({entryTypes:["paint"]})}catch(m){}f=new PerformanceObserver(o);try{f.observe({entryTypes:["largest-contentful-paint"]})}catch(m){}u=new PerformanceObserver(i);try{u.observe({type:"layout-shift",buffered:!0})}catch(m){}}if("addEventListener"in document){var v=!1,w=["click","keydown","mousedown","pointerdown","touchstart"];w.forEach(function(t){document.addEventListener(t,a,!1)})}l(s)}},{}],27:[function(t,e,n){function r(){function t(){return e?15&e[n++]:16*Math.random()|0}var e=null,n=0,r=window.crypto||window.msCrypto;r&&r.getRandomValues&&(e=r.getRandomValues(new Uint8Array(31)));for(var o,i="xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx",a="",s=0;s<i.length;s++)o=i[s],"x"===o?a+=t().toString(16):"y"===o?(o=3&t()|8,a+=o.toString(16)):a+=o;return a}function o(){return a(16)}function i(){return a(32)}function a(t){function e(){return n?15&n[r++]:16*Math.random()|0}var n=null,r=0,o=window.crypto||window.msCrypto;o&&o.getRandomValues&&Uint8Array&&(n=o.getRandomValues(new Uint8Array(31)));for(var i=[],a=0;a<t;a++)i.push(e().toString(16));return i.join("")}e.exports={generateUuid:r,generateSpanId:o,generateTraceId:i}},{}],28:[function(t,e,n){function r(t,e){if(!o)return!1;if(t!==o)return!1;if(!e)return!0;if(!i)return!1;for(var n=i.split("."),r=e.split("."),a=0;a<r.length;a++)if(r[a]!==n[a])return!1;return!0}var o=null,i=null,a=/Version\/(\S+)\s+Safari/;if(navigator.userAgent){var s=navigator.userAgent,c=s.match(a);c&&s.indexOf("Chrome")===-1&&s.indexOf("Chromium")===-1&&(o="Safari",i=c[1])}e.exports={agent:o,version:i,match:r}},{}],29:[function(t,e,n){function r(t){function e(){t(a&&document[a]?document[a]:document[o]?"hidden":"visible")}"addEventListener"in document&&i&&document.addEventListener(i,e,!1)}e.exports=r;var o,i,a;"undefined"!=typeof document.hidden?(o="hidden",i="visibilitychange",a="visibilityState"):"undefined"!=typeof document.msHidden?(o="msHidden",i="msvisibilitychange"):"undefined"!=typeof document.webkitHidden&&(o="webkitHidden",i="webkitvisibilitychange",a="webkitVisibilityState")},{}],30:[function(t,e,n){function r(t,e){var n=[],r="",i=0;for(r in t)o.call(t,r)&&(n[i]=e(r,t[r]),i+=1);return n}var o=Object.prototype.hasOwnProperty;e.exports=r},{}],31:[function(t,e,n){function r(t,e,n){e||(e=0),"undefined"==typeof n&&(n=t?t.length:0);for(var r=-1,o=n-e||0,i=Array(o<0?0:o);++r<o;)i[r]=t[e+r];return i}e.exports=r},{}],32:[function(t,e,n){e.exports={exists:"undefined"!=typeof window.performance&&window.performance.timing&&"undefined"!=typeof window.performance.timing.navigationStart}},{}],ee:[function(t,e,n){function r(){}function o(t){function e(t){return t&&t instanceof r?t:t?f(t,c,a):a()}function n(n,r,o,i,a){if(a!==!1&&(a=!0),!l.aborted||i){t&&a&&t(n,r,o);for(var s=e(o),c=m(n),f=c.length,u=0;u<f;u++)c[u].apply(s,r);var p=d[y[n]];return p&&p.push([x,n,r,s]),s}}function i(t,e){g[t]=m(t).concat(e)}function h(t,e){var n=g[t];if(n)for(var r=0;r<n.length;r++)n[r]===e&&n.splice(r,1)}function m(t){return g[t]||[]}function v(t){return p[t]=p[t]||o(n)}function w(t,e){l.aborted||u(t,function(t,n){e=e||"feature",y[n]=e,e in d||(d[e]=[])})}var g={},y={},x={on:i,addEventListener:i,removeEventListener:h,emit:n,get:v,listeners:m,context:e,buffer:w,abort:s,aborted:!1};return x}function i(t){return f(t,c,a)}function a(){return new r}function s(){(d.api||d.feature)&&(l.aborted=!0,d=l.backlog={})}var c="nr@context",f=t("gos"),u=t(30),d={},p={},l=e.exports=o();e.exports.getOrSetContext=i,l.backlog=d},{}],gos:[function(t,e,n){function r(t,e,n){if(o.call(t,e))return t[e];var r=n();if(Object.defineProperty&&Object.keys)try{return Object.defineProperty(t,e,{value:r,writable:!0,enumerable:!1}),r}catch(i){}return t[e]=r,r}var o=Object.prototype.hasOwnProperty;e.exports=r},{}],handle:[function(t,e,n){function r(t,e,n,r){o.buffer([t],r),o.emit(t,e,n)}var o=t("ee").get("handle");e.exports=r,r.ee=o},{}],id:[function(t,e,n){function r(t){var e=typeof t;return!t||"object"!==e&&"function"!==e?-1:t===window?0:a(t,i,function(){return o++})}var o=1,i="nr@id",a=t("gos");e.exports=r},{}],loader:[function(t,e,n){function r(){if(!S++){var t=O.info=NREUM.info,e=m.getElementsByTagName("script")[0];if(setTimeout(f.abort,3e4),!(t&&t.licenseKey&&t.applicationID&&e))return f.abort();c(E,function(e,n){t[e]||(t[e]=n)});var n=a();s("mark",["onload",n+O.offset],null,"api"),s("timing",["load",n]);var r=m.createElement("script");0===t.agent.indexOf("http://")||0===t.agent.indexOf("https://")?r.src=t.agent:r.src=l+"://"+t.agent,e.parentNode.insertBefore(r,e)}}function o(){"complete"===m.readyState&&i()}function i(){s("mark",["domContent",a()+O.offset],null,"api")}var a=t(24),s=t("handle"),c=t(30),f=t("ee"),u=t(28),d=t(25),p=t(21),l=p.getConfiguration("ssl")===!1?"http":"https",h=window,m=h.document,v="addEventListener",w="attachEvent",g=h.XMLHttpRequest,y=g&&g.prototype,x=!d(h.location);NREUM.o={ST:setTimeout,SI:h.setImmediate,CT:clearTimeout,XHR:g,REQ:h.Request,EV:h.Event,PR:h.Promise,MO:h.MutationObserver};var b=""+location,E={beacon:"bam.nr-data.net",errorBeacon:"bam.nr-data.net",agent:"js-agent.newrelic.com/nr-spa-1211.min.js"},R=g&&y&&y[v]&&!/CriOS/.test(navigator.userAgent),O=e.exports={offset:a.getLastTimestamp(),now:a,origin:b,features:{},xhrWrappable:R,userAgent:u,disabled:x};if(!x){t(20),t(26),m[v]?(m[v]("DOMContentLoaded",i,!1),h[v]("load",r,!1)):(m[w]("onreadystatechange",o),h[w]("onload",r)),s("mark",["firstbyte",a.getLastTimestamp()],null,"api");var S=0}},{}],"wrap-function":[function(t,e,n){function r(t,e){function n(e,n,r,c,f){function nrWrapper(){var i,a,u,p;try{a=this,i=d(arguments),u="function"==typeof r?r(i,a):r||{}}catch(l){o([l,"",[i,a,c],u],t)}s(n+"start",[i,a,c],u,f);try{return p=e.apply(a,i)}catch(h){throw s(n+"err",[i,a,h],u,f),h}finally{s(n+"end",[i,a,p],u,f)}}return a(e)?e:(n||(n=""),nrWrapper[p]=e,i(e,nrWrapper,t),nrWrapper)}function r(t,e,r,o,i){r||(r="");var s,c,f,u="-"===r.charAt(0);for(f=0;f<e.length;f++)c=e[f],s=t[c],a(s)||(t[c]=n(s,u?c+r:r,o,c,i))}function s(n,r,i,a){if(!h||e){var s=h;h=!0;try{t.emit(n,r,i,e,a)}catch(c){o([c,n,r,i],t)}h=s}}return t||(t=u),n.inPlace=r,n.flag=p,n}function o(t,e){e||(e=u);try{e.emit("internal-error",t)}catch(n){}}function i(t,e,n){if(Object.defineProperty&&Object.keys)try{var r=Object.keys(t);return r.forEach(function(n){Object.defineProperty(e,n,{get:function(){return t[n]},set:function(e){return t[n]=e,e}})}),e}catch(i){o([i],n)}for(var a in t)l.call(t,a)&&(e[a]=t[a]);return e}function a(t){return!(t&&t instanceof Function&&t.apply&&!t[p])}function s(t,e){var n=e(t);return n[p]=t,i(t,n,u),n}function c(t,e,n){var r=t[e];t[e]=s(r,n)}function f(){for(var t=arguments.length,e=new Array(t),n=0;n<t;++n)e[n]=arguments[n];return e}var u=t("ee"),d=t(31),p="nr@original",l=Object.prototype.hasOwnProperty,h=!1;e.exports=r,e.exports.wrapFunction=s,e.exports.wrapInPlace=c,e.exports.argsToArray=f},{}]},{},["loader",2,17,5,3,4]);
;NREUM.loader_config={accountID:"2364187",trustKey:"2442591",agentID:"1119998627",licenseKey:"a26b546289",applicationID:"1119998627"}
;NREUM.info={beacon:"bam.nr-data.net",errorBeacon:"bam.nr-data.net",licenseKey:"a26b546289",applicationID:"1119998627",sa:1}
</script>
</head>



<body>

<% ResourceBundle resource = ResourceBundle.getBundle("fdrates"); %>

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
		
		
<header class="fd_header">
    <div class="container">
        <a href="#;">
            <img src="${pageContext.request.contextPath}/resources/newImages/bajaj_logo_png.png" alt=""> 
        </a>
    </div>
</header>

<section class="fd_com_sec">
    <div class="container">
        <div class="fd_white_box">
            <h1>Bajaj Finance Deposits</h1>
            <p>Interest rates up to <%=resource.getString("interest_rate_senior")%>% p.a. for senior citizens and up to <%=resource.getString("interest_rate")%>% p.a. for non-senior citizens</p><br>
        </div>
        <div class="grow_saving_part">
            <h2 class="fd_com_title">Choose how to grow your savings <!--<a href="#;">Help me decide</a>--></h2>
            <div class="tooltipBox">
                <a href="#;">Help me decide</a>
                <div class="tooltipMain">
                    <div class="tooltipCon">
                        <a href="#;" class="cross"><img src="${pageContext.request.contextPath}/resources/newImages/cross.png" alt=""> </a>
                       <strong>Fixed Deposit is a great choice for investors looking to:</strong>
                            <ul>
                                <li>Invest a lumpsum amount in a single go</li>
                                <li>Stay ready for financial emergencies</li>
                                <li>Earn fixed returns, irrespective of market fluctuations</li>
                            </ul>
						<strong>Systematic Deposit Plan is a great choice for investors looking to:</strong>
                            <ul>
                                <li>Earn attractive interest rates on small monthly savings</li>
                                <li>Fast-track investment goals without breaking their budget</li>
                                <li>Get assured returns, similar to fixed deposit</li>
                            </ul>
                            <a href="#;" class="gotIt">Got It</a>
                       </div>
                </div>
            </div>
            <div class="fullSavingCardPart">
                <div class="fd_falfpart">
                    <div class="green_card_main" >
                        <div class="f_block">
                            <img src="${pageContext.request.contextPath}/resources/newImages/fd_img.png" alt="">
                            <p>Fixed Deposit</p>
                            <i>Choose to invest in a single go with our 4-step FD. Invest a lumpsum amount for a specific tenor.</i>
                        </div>
                        <div class="s_block">
                            <div>
                                <p>RATE OF INTEREST</p>
                                <strong><i>Up to</i><%=resource.getString("interest_rate_senior")%>%* p.a.</strong>
                            </div>
                            <div>
                                <p>MIN DEPOSIT</p>
                                <strong>Rs. 15,000</strong>
                            </div>
                            <a href="#;" id="fdInvest" data-href="a_part_2">INVEST NOW</a>
                        </div>
                    </div>
                </div>
                <div class="fd_falfpart">
                    <div class="green_card_main sdpCardInMob">
                        <div class="f_block">
                            <img src="${pageContext.request.contextPath}/resources/newImages/sdp_img.png" alt="">
                            <p>Systematic Deposit Plan <!-- <i>INDUSTRY FIRST</i> --></p>
                            <i>Achieve your financial goals by making small monthly deposits. Get lumpsum amount on maturity.</i>
                        </div>
                        <div class="s_block">
                            <ul class="tab_ul">
                                <li><a href="#;" class="active">Single Maturity</a></li>
                                <li><a href="#;">Monthly Maturity</a></li>
                            </ul>
                            <div>
                                <p>RATE OF INTEREST</p>
                                <strong><i>Up to</i><%=resource.getString("interest_rate_senior")%>%* p.a.</strong>
                            </div>
                            <div>
                                <p>MIN DEPOSIT</p>
                                <strong>Rs. 5000 p.m.</strong>
                            </div>
                            <a href="#;" id="sdpInvest" data-href="a_part_2">INVEST NOW</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="unmatched_benefits">
            <h2 class="fd_com_title">Explore the unmatched benefits</h2>
            <div class="benefits_box_main">
                <div class="benefits_box_inner">
                    <div class="tooltipBox">
                        <a href="#;" data-tab="First" ><img src="${pageContext.request.contextPath}/resources/newImages/info.png" alt=""> </a>
                        <div class="tooltipMain">
                            <div class="tooltipCon">
                                <a href="#;" class="cross"><img src="${pageContext.request.contextPath}/resources/newImages/cross.png" alt=""> </a>
                                <strong>Interest Rates </strong>
                                <p>Rate of interest per annum, applicable on a cumulative scheme tenor of 44 months for senior citizens</p>
                                <a href="#;" class="gotIt">Got It</a>
                            </div>
                        </div>
                    </div>
                    
                    <i class="forDataLayerI" >Interest Rates</i>
                    <strong class="forDataLayerStrong" ><%=resource.getString("interest_rate_senior")%>% <sub>p.a.*</sub></strong>
                    <p class="forDataLayerP" >For Senior Citizen</p>
                </div>
                <div class="benefits_box_inner">
                    <div class="tooltipBox">
                        <a href="#;" data-tab="second" ><img src="${pageContext.request.contextPath}/resources/newImages/info.png" alt=""> </a>
                        <div class="tooltipMain">
                            <div class="tooltipCon">
                                <a href="#;" class="cross"><img src="${pageContext.request.contextPath}/resources/newImages/cross.png" alt=""> </a>
                                <strong>Interest Rates </strong>
                                <p>Rate of interest per annum, applicable on a cumulative scheme tenor of 44 months for non-senior citizens.</p>
                                <a href="#;" class="gotIt">Got It</a>
                            </div>
                        </div>
                    </div>
                    
                    <i class="forDataLayerI" >Interest Rates</i>
                    <strong class="forDataLayerStrong" ><%=resource.getString("interest_rate")%>% <sub>p.a**</sub></strong>
                    <p class="forDataLayerP" >For Non Senior Citizen</p>
                </div>
                <!-- <div class="benefits_box_inner">
                    <i>Additional</i>
                    <strong>0.10% <sub>p.a.</sub></strong>
                    <p>On renewal</p>
                </div> -->
                <div class="benefits_box_inner">
                  <div class="tooltipBox">
                        <a href="#;" data-tab="third" ><img src="${pageContext.request.contextPath}/resources/newImages/info.png" alt=""> </a>
                        <div class="tooltipMain">
                            <div class="tooltipCon">
                                <a href="#;" class="cross"><img src="${pageContext.request.contextPath}/resources/newImages/cross.png" alt=""> </a>
                                <strong>Flexible payout option</strong>
                                <p>Choose to receive interest payouts on a monthly, quarterly, half-yearly, or annual basis</p>
                                <a href="#;" class="gotIt">Got It</a>
                            </div>
                        </div>
                    </div>
                    <i class="forDataLayerI">Choose</i>
                    <strong class="forDataLayerStrong" >Flexible</strong>
                    <p class="forDataLayerP">Payout Interest</p>
                </div>
                <div class="benefits_box_inner">
                    <div class="tooltipBox">
                        <a href="#;" data-tab="fourth" ><img src="${pageContext.request.contextPath}/resources/newImages/info.png" alt=""> </a>
                        <div class="tooltipMain">
                            <div class="tooltipCon">
                                <a href="#;" class="cross"><img src="${pageContext.request.contextPath}/resources/newImages/cross.png" alt=""> </a>
                                <strong>Loan against FD facility</strong>
                                <p>Apply for a loan against FD and take a loan up to 75% of FD amount in case of emergencies</p>
                                <a href="#;" class="gotIt">Got It</a>
                            </div>
                        </div>
                    </div>
                    
                   <i class="forDataLayerI">Get a loan up to</i>
                    <strong class="forDataLayerStrong" >75%</strong>
                    <p class="forDataLayerP">Of FD amount</p>
                </div>
                <div class="benefits_box_inner">
                    <div class="tooltipBox">
                        <a href="#;" data-tab="fifth" ><img src="${pageContext.request.contextPath}/resources/newImages/info.png" alt=""> </a>
                        <div class="tooltipMain">
                            <div class="tooltipCon">
                                <a href="#;" class="cross"><img src="${pageContext.request.contextPath}/resources/newImages/cross.png" alt=""> </a>
                                <strong>Flexible tenor</strong>
                                <p>Staying invested for longer can help you earn higher returns on your deposit. The tenor for Single Maturity Scheme of SDP is 19 to 60 months.</p>
                                <a href="#;" class="gotIt">Got It</a>
                            </div>
                        </div>
                    </div>
                    <i class="forDataLayerI">Months</i>
                    <strong class="forDataLayerStrong" >12-60</strong>
                    <p class="forDataLayerP">Flexible tenor</p>
                </div>
            </div>
        </div>
        <div class="why_invest_fd">
            <h2 class="fd_com_title">Why invest in Bajaj Finance FD </h2>
            <div class="why_invest_box_main">
                <div class="why_invest_box_pad">
                    <div class="why_invest_box_inner">
                    <div class="tooltipBox">
                            <a href="#;" data-tab="Sixth" ><img src="${pageContext.request.contextPath}/resources/newImages/info_orange.png" alt=""> </a>
                            <div class="tooltipMain">
                                <div class="tooltipCon">
                                    <a href="#;" class="cross"><img src="${pageContext.request.contextPath}/resources/newImages/cross.png" alt=""> </a>
                                    <strong>Strong Credibility</strong>
                                    <p>Rated FAAA/Stable by CRISIL and MAAA/Stable by ICRA, which means highest safety of your money.</p>
                                    <a href="#;" class="gotIt">Got It</a>
                                </div>
                            </div>
                        </div>
                        <img src="${pageContext.request.contextPath}/resources/newImages/why_invest_fd_1.png" alt="">
                        <p class="forDataLayerP" >Strong Credibility</p>
                         <i>Bajaj Finance FD has credit ratings of CRISIL AAA/STABLE and [ICRA]AAA(Stable)</i>
                    </div>
                </div>
                <div class="why_invest_box_pad">
                    <div class="why_invest_box_inner">
                        <img src="${pageContext.request.contextPath}/resources/newImages/why_invest_fd_4.png" alt="">
                        <p>Additional rate benefits</p>
                       <ul>
                            <!-- <li>0.10% p.a. for non-senior citizens investing online</li>
                            <li>0.10% p.a. for renewal</li> -->
                            <li>Up to 0.25% p.a. for senior citizens</li>
                        </ul>
                    </div>
                </div>
                <div class="why_invest_box_pad">
                    <div class="why_invest_box_inner">
                        <img src="${pageContext.request.contextPath}/resources/newImages/why_invest_fd_2.png" alt="">
                        <p>Online account management</p>
                        <i>Access all product details 24x7 on our customer portal - Experia</i>
                    </div>
                </div>
                <div class="why_invest_box_pad">
                    <div class="why_invest_box_inner">
                        <div class="tooltipBox">
                            <a href="#;"  data-tab="seven" ><img src="${pageContext.request.contextPath}/resources/newImages/info_orange.png" alt=""> </a>
                            <div class="tooltipMain">
                                <div class="tooltipCon">
                                    <a href="#;" class="cross"><img src="${pageContext.request.contextPath}/resources/newImages/cross.png" alt=""> </a>
                                    <strong>Premature withdrawal facility</strong>
                                    <p>Fund emergencies with an easy premature withdrawal facility after a minimum lock-in period of 3 months. You can also take an easy Loan against FD, instead of liquidating your savings prematurely.</p>
                                    <a href="#;" class="gotIt">Got It</a>
                                </div>
                            </div>
                        </div>
                        <img src="${pageContext.request.contextPath}/resources/newImages/why_invest_fd_3.png" alt="">
                        <p class="forDataLayerP" >Premature withdrawal facility</p>
                        <i>Access your savings instantly, during emergencies</i>
                    </div>
                </div>
            </div>
        </div>
        <div class="rate_of_interest_part">
            <h2 class="fd_com_title">Rate of interest</h2>
            <div class="rate_of_interest_tab">
                <ul class="tab_ul">
                </ul>
            </div>
            
            <div class="interest_main_content">
							<div class="interest_content_tab" id="offline_deposits">
								<div class="deposit_type">
									<ul class="radio_ul">
										<li><label> <input type="radio"
												name="depositType_1" data-tab="offline_fixed_deposit"
												checked> <i></i> <strong>Fixed Deposit</strong>
										</label></li>


										<li><label> <input type="radio"
												name="depositType_1" data-tab="offline_systematic_deposit">
												<i></i> <strong>Systematic Deposit Plan</strong>
										</label></li>
									</ul>
								</div>
								<div class="tableDisc">
									<p>Rate of interest per annum valid for deposits up to Rs.
										5 Crore (W.e.f. <%=resource.getString("date")%>)</p>
								</div>
								<div class="interest_table_main">
   <div class="table_chane" id="offline_fixed_deposit">
      <div class="table_chane_inner">
         <div class="interest_table_width">
            <p>Senior Citizens</p>
            <div class="interest_table_inner">
               <table>
                  <tr>
                     <th rowspan="2">Tenor in months</th>
                     <th colspan="5">Interest Payout</th>
                  </tr>
                  <tr>
                     <th>Cumulative</th>
                     <th colspan="4">Non Cumulative</th>
                  </tr>
                  <tr class="tbBlueBold">
                     <td></td>
                     <td>At Maturity</td>
                     <td>Monthly</td>
                     <td>Quarterly</td>
                     <td>Half-yearly</td>
                     <td>Annually</td>
                  </tr>
                  <tr>
                     <td>12-14</td>
                     <td><%=resource.getString("regular_sen_12to14_maturity_annually")%>%</td>
                     <td><%=resource.getString("regular_sen_12to14_monthly")%>%</td>
                     <td><%=resource.getString("regular_sen_12to14_quarterly")%>%</td>
                     <td><%=resource.getString("regular_sen_12to14_halfyearly")%>%</td>
                     <td><%=resource.getString("regular_sen_12to14_maturity_annually")%>%</td>
                  </tr>
                  <tr>
                     <td>>15-23</td>
                     <td><%=resource.getString("regular_sen_15to23_maturity_annually")%>%</td>
                     <td><%=resource.getString("regular_sen_15to23_monthly")%>%</td>
                     <td><%=resource.getString("regular_sen_15to23_quarterly")%>%</td>
                     <td><%=resource.getString("regular_sen_15to23_halfyearly")%>%</td>
                     <td><%=resource.getString("regular_sen_15to23_maturity_annually")%>%</td>
                  </tr>
                  <tr>
                     <td>24</td>
                     <td><%=resource.getString("regular_sen_24_maturity_annually")%>%</td>
                     <td><%=resource.getString("regular_sen_24_monthly")%>%</td>
                     <td><%=resource.getString("regular_sen_24_quarterly")%>%</td>
                     <td><%=resource.getString("regular_sen_24_halfyearly")%>%</td>
                     <td><%=resource.getString("regular_sen_24_maturity_annually")%>%</td>
                  </tr>
                  <tr>
                     <td>25-35</td>
                     <td><%=resource.getString("regular_sen_25to35_maturity_annually")%>%</td>
                     <td><%=resource.getString("regular_sen_25to35_monthly")%>%</td>
                     <td><%=resource.getString("regular_sen_25to35_quarterly")%>%</td>
                     <td><%=resource.getString("regular_sen_25to35_halfyearly")%>%</td>
                     <td><%=resource.getString("regular_sen_25to35_maturity_annually")%>%</td>
                  </tr>
                  <tr>
                     <td>36-60</td>
                     <td><%=resource.getString("regular_sen_36to60_maturity_annually")%>%</td>
                     <td><%=resource.getString("regular_sen_36to60_monthly")%>%</td>
                     <td><%=resource.getString("regular_sen_36to60_quarterly")%>%</td>
                     <td><%=resource.getString("regular_sen_36to60_halfyearly")%>%</td>
                     <td><%=resource.getString("regular_sen_36to60_maturity_annually")%>%</td>
                  </tr>
               </table>
            </div>
         </div>
         <div class="interest_table_width">
            <p>Non Senior Citizens</p>
            <div class="interest_table_inner">
               <table>
                  <tr>
                     <th rowspan="2">Tenor in months</th>
                     <th colspan="5">Interest Payout</th>
                  </tr>
                  <tr>
                     <th>Cumulative</th>
                     <th colspan="4">Non Cumulative</th>
                  </tr>
                  <tr class="tbBlueBold">
                     <td></td>
                     <td>At Maturity</td>
                     <td>Monthly</td>
                     <td>Quarterly</td>
                     <td>Half-yearly</td>
                     <td>Annually</td>
                  </tr>
                  <tr>
                     <td>12-14</td>
                     <td><%=resource.getString("regular_12to14_maturity_annually")%>%</td>
                     <td><%=resource.getString("regular_12to14_monthly")%>%</td>
                     <td><%=resource.getString("regular_12to14_quarterly")%>%</td>
                     <td><%=resource.getString("regular_12to14_halfyearly")%>%</td>
                     <td><%=resource.getString("regular_12to14_maturity_annually")%>%</td>
                  </tr>
                  <tr>
                     <td>>15-23</td>
                     <td><%=resource.getString("regular_15to23_maturity_annually")%>%</td>
                     <td><%=resource.getString("regular_15to23_monthly")%>%</td>
                     <td><%=resource.getString("regular_15to23_quarterly")%>%</td>
                     <td><%=resource.getString("regular_15to23_halfyearly")%>%</td>
                     <td><%=resource.getString("regular_15to23_maturity_annually")%>%</td>
                  </tr>
                  <tr>
                     <td>24</td>
                     <td><%=resource.getString("regular_24_maturity_annually")%>%</td>
                     <td><%=resource.getString("regular_24_monthly")%>%</td>
                     <td><%=resource.getString("regular_24_quarterly")%>%</td>
                     <td><%=resource.getString("regular_24_halfyearly")%>%</td>
                     <td><%=resource.getString("regular_24_maturity_annually")%>%</td>
                  </tr>
                  <tr>
                     <td>25-35</td>
                     <td><%=resource.getString("regular_25to35_maturity_annually")%>%</td>
                     <td><%=resource.getString("regular_25to35_monthly")%>%</td>
                     <td><%=resource.getString("regular_25to35_quarterly")%>%</td>
                     <td><%=resource.getString("regular_25to35_halfyearly")%>%</td>
                     <td><%=resource.getString("regular_25to35_maturity_annually")%>%</td>
                  </tr>
                  <tr>
                     <td>36-60</td>
                     <td><%=resource.getString("regular_36to60_maturity_annually")%>%</td>
                     <td><%=resource.getString("regular_36to60_monthly")%>%</td>
                     <td><%=resource.getString("regular_36to60_quarterly")%>%</td>
                     <td><%=resource.getString("regular_36to60_halfyearly")%>%</td>
                     <td><%=resource.getString("regular_36to60_maturity_annually")%>%</td>
                  </tr>
               </table>
            </div>
         </div>
      </div>
      
      
      
      <div class="table_chane_inner">
                                <div class="interest_table_width">
                                    <p></p>
                             <div class="interest_table_inner">
													<table>
														<tbody><tr>
															<th rowspan="2">Tenor in months</th>
															<th colspan="5">Interest Payout</th>
														</tr>
														<tr>
															<th>Cumulative</th>
															<th colspan="4">Non Cumulative</th>
														</tr>
														<tr class="tbBlueBold">
															<td></td>
															<td>At Maturity</td>
															<td>Monthly</td>
															<td>Quarterly</td>
															<td>Half-yearly</td>
															<td>Annually</td>
														</tr>
															<tr>
									<td>15</td>
									<td><%=resource.getString("special_sen_15_maturity_annually")%>%</td>
									<td><%=resource.getString("special_sen_15_monthly")%>%</td>
									<td><%=resource.getString("special_sen_15_quarterly")%>%</td>
									<td><%=resource.getString("special_sen_15_halfyearly")%>%</td>
									<td><%=resource.getString("special_sen_15_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>18</td>
									<td><%=resource.getString("special_sen_18_maturity_annually")%>%</td>
									<td><%=resource.getString("special_sen_18_monthly")%>%</td>
									<td><%=resource.getString("special_sen_18_quarterly")%>%</td>
									<td><%=resource.getString("special_sen_18_halfyearly")%>%</td>
									<td><%=resource.getString("special_sen_18_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>22</td>
									<td><%=resource.getString("special_sen_22_maturity_annually")%>%</td>
									<td><%=resource.getString("special_sen_22_monthly")%>%</td>
									<td><%=resource.getString("special_sen_22_quarterly")%>%</td>
									<td><%=resource.getString("special_sen_22_halfyearly")%>%</td>
									<td><%=resource.getString("special_sen_22_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>30</td>
									<td><%=resource.getString("special_sen_30_maturity_annually")%>%</td>
									<td><%=resource.getString("special_sen_30_monthly")%>%</td>
									<td><%=resource.getString("special_sen_30_quarterly")%>%</td>
									<td><%=resource.getString("special_sen_30_halfyearly")%>%</td>
									<td><%=resource.getString("special_sen_30_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>33</td>
									<td><%=resource.getString("special_sen_33_maturity_annually")%>%</td>
									<td><%=resource.getString("special_sen_33_monthly")%>%</td>
									<td><%=resource.getString("special_sen_33_quarterly")%>%</td>
									<td><%=resource.getString("special_sen_33_halfyearly")%>%</td>
									<td><%=resource.getString("special_sen_33_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>44</td>
									<td><%=resource.getString("special_sen_44_maturity_annually")%>%</td>
									<td><%=resource.getString("special_sen_44_monthly")%>%</td>
									<td><%=resource.getString("special_sen_44_quarterly")%>%</td>
									<td><%=resource.getString("special_sen_44_halfyearly")%>%</td>
									<td><%=resource.getString("special_sen_44_maturity_annually")%>%</td>
								</tr>
													</tbody></table>
												</div>
											</div>
                                <div class="interest_table_width">
                                    <p></p>
                                    <div class="interest_table_inner">
													<table>
														<tbody><tr>
															<th rowspan="2">Tenor in months</th>
															<th colspan="5">Interest Payout</th>
														</tr>
														<tr>
															<th>Cumulative</th>
															<th colspan="4">Non Cumulative</th>
														</tr>
														<tr class="tbBlueBold">
															<td></td>
															<td>At Maturity</td>
															<td>Monthly</td>
															<td>Quarterly</td>
															<td>Half-yearly</td>
															<td>Annually</td>
														</tr>
														<tr>
									<td>15</td>
									<td><%=resource.getString("special_15_maturity_annually")%>%</td>
									<td><%=resource.getString("special_15_monthly")%>%</td>
									<td><%=resource.getString("special_15_quarterly")%>%</td>
									<td><%=resource.getString("special_15_halfyearly")%>%</td>
									<td><%=resource.getString("special_15_maturity_annually")%>%</td>
								</tr>
														<tr>
									<td>18</td>
									<td><%=resource.getString("special_18_maturity_annually")%>%</td>
									<td><%=resource.getString("special_18_monthly")%>%</td>
									<td><%=resource.getString("special_18_quarterly")%>%</td>
									<td><%=resource.getString("special_18_halfyearly")%>%</td>
									<td><%=resource.getString("special_18_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>22</td>
									<td><%=resource.getString("special_22_maturity_annually")%>%</td>
									<td><%=resource.getString("special_22_monthly")%>%</td>
									<td><%=resource.getString("special_22_quarterly")%>%</td>
									<td><%=resource.getString("special_22_halfyearly")%>%</td>
									<td><%=resource.getString("special_22_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>30</td>
									<td><%=resource.getString("special_30_maturity_annually")%>%</td>
									<td><%=resource.getString("special_30_monthly")%>%</td>
									<td><%=resource.getString("special_30_quarterly")%>%</td>
									<td><%=resource.getString("special_30_halfyearly")%>%</td>
									<td><%=resource.getString("special_30_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>33</td>
									<td><%=resource.getString("special_33_maturity_annually")%>%</td>
									<td><%=resource.getString("special_33_monthly")%>%</td>
									<td><%=resource.getString("special_33_quarterly")%>%</td>
									<td><%=resource.getString("special_33_halfyearly")%>%</td>
									<td><%=resource.getString("special_33_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>44</td>
									<td><%=resource.getString("special_44_maturity_annually")%>%</td>
									<td><%=resource.getString("special_44_monthly")%>%</td>
									<td><%=resource.getString("special_44_quarterly")%>%</td>
									<td><%=resource.getString("special_44_halfyearly")%>%</td>
									<td><%=resource.getString("special_44_maturity_annually")%>%</td>
								</tr>
													</tbody></table>
												</div>
											</div>
										</div>
										
   </div>
   <div class="table_chane" id="offline_systematic_deposit">
      <div class="table_chane_inner">
         <div class="interest_table_width">
            <p>Senior Citizens</p>
            <div class="interest_table_inner">
               <table>
                  <tr>
                     <th rowspan="2">Tenor in months</th>
                     <th colspan="5">Interest Payout</th>
                  </tr>
                  <tr>
                     <th>Cumulative</th>
                  </tr>
                  <tr class="tbBlueBold">
                     <td></td>
                     <td>At Maturity</td>
                  </tr>
                  <tr>
                     <td>12-14</td>
                     <td><%=resource.getString("regular_sen_sdp_12to14_maturity")%>%</td>
                  </tr>
                  <tr>
                     <td>>15-23</td>
                     <td><%=resource.getString("regular_sen_sdp_15to23_maturity")%>%</td>
                  </tr>
                  <tr>
                     <td>24</td>
                     <td><%=resource.getString("regular_sen_sdp_24_maturity")%>%</td>
                  </tr>
                  <tr>
                     <td>25-35</td>
                     <td><%=resource.getString("regular_sen_sdp_25to35_maturity")%>%</td>
                  </tr>
                  <tr>
                     <td>36-60</td>
                     <td><%=resource.getString("regular_sen_sdp_36to60_maturity")%>%</td>
                  </tr>
               </table>
            </div>
         </div>
         <div class="interest_table_width">
            <p>Non Senior Citizens</p>
            <div class="interest_table_inner">
               <table>
                  <tr>
                     <th rowspan="2">Tenor in months</th>
                     <th colspan="5">Interest Payout</th>
                  </tr>
                  <tr>
                     <th>Cumulative</th>
                  </tr>
                  <tr class="tbBlueBold">
                     <td></td>
                     <td>At Maturity</td>
                  </tr>
                 <tr>
                     <td>12-14</td>
                     <td><%=resource.getString("regular_sdp_12to14_maturity")%>%</td>
                  </tr>
                  <tr>
                     <td>>15-23</td>
                     <td><%=resource.getString("regular_sdp_15to23_maturity")%>%</td>
                  </tr>
                  <tr>
                     <td>24</td>
                     <td><%=resource.getString("regular_sdp_24_maturity")%>%</td>
                  </tr>
                  <tr>
                     <td>25-35</td>
                     <td><%=resource.getString("regular_sdp_25to35_maturity")%>%</td>
                  </tr>
                  <tr>
                     <td>36-60</td>
                     <td><%=resource.getString("regular_sdp_36to60_maturity")%>%</td>
                  </tr>
               </table>
            </div>
         </div>
      </div>
      
      
      <div class="table_chane_inner">
                                <div class="interest_table_width">
                                        <p></p>
                                        <div class="interest_table_inner">
													<table>
														<tbody><tr>
															<th rowspan="2">Tenor in months</th>
															<th colspan="5">Interest Payout</th>
														</tr>
														<tr>
															<th>Cumulative</th>
														</tr>
														<tr class="tbBlueBold">
															<td></td>
															<td>At Maturity</td>
														</tr>
														<tr>
									<td>15</td>
									<td><%=resource.getString("special_sen_sdp_15_maturity")%>%</td>
								</tr>
								<tr>
									<td>18</td>
									<td><%=resource.getString("special_sen_sdp_18_maturity")%>%</td>
								</tr>
								<tr>
									<td>22</td>
									<td><%=resource.getString("special_sen_sdp_22_maturity")%>%</td>
								</tr>
								<tr>
									<td>30</td>
									<td><%=resource.getString("special_sen_sdp_30_maturity")%>%</td>
								</tr>
								<tr>
									<td>33</td>
									<td><%=resource.getString("special_sen_sdp_33_maturity")%>%</td>
								</tr>
								<tr>
									<td>44</td>
									<td><%=resource.getString("special_sen_sdp_44_maturity")%>%</td>
								</tr></tbody></table>
												</div>
											</div>
                                    <div class="interest_table_width">
                                        <p></p>
                                       <div class="interest_table_inner">
													<table>
														<tbody><tr>
															<th rowspan="2">Tenor in months</th>
															<th colspan="5">Interest Payout</th>
														</tr>
														<tr>
															<th>Cumulative</th>
														</tr>
														<tr class="tbBlueBold">
															<td></td>
															<td>At Maturity</td>
														</tr>
														<tr>
									<td>15</td>
									<td><%=resource.getString("special_sdp_15_maturity")%>%</td>
								</tr>
								<tr>
									<td>18</td>
									<td><%=resource.getString("special_sdp_18_maturity")%>%</td>
								</tr>
								<tr>
									<td>22</td>
									<td><%=resource.getString("special_sdp_22_maturity")%>%</td>
								</tr>
								<tr>
									<td>30</td>
									<td><%=resource.getString("special_sdp_30_maturity")%>%</td>
								</tr>
								<tr>
									<td>33</td>
									<td><%=resource.getString("special_sdp_33_maturity")%>%</td>
								</tr>
								<tr>
									<td>44</td>
									<td><%=resource.getString("special_sdp_44_maturity")%>%</td>
								</tr>		</tbody></table>
												</div>
											</div>
										</div>
										
   </div>
</div>
							</div>
							
						</div>
        
        <div class="our_word_testimonial">
                <h2 class="fd_com_title">Don't just take our word for it <i>Read what our customers are saying</i></h2>
                <div class="testi_mobile_back">
                    <div class="testi_mobile_back_inner">
                        <img src="${pageContext.request.contextPath}/resources/newImages/testi_back.png" alt="">
                    </div>
                </div>
                <div class="word_testimonial_main">
                    <div class="word_testimonial_pad">
                        <div class="word_testimonial_inner">
                            <div class="testimonial_top">
                                <div class="testiNameLatter">--</div>
                                <!--<img src="${pageContext.request.contextPath}/resources/newImages/testi_img.png" alt="">-->
                                <div class="name_post">
                                    <p>Vipin Chauhan</p>
                                    <i>Sr manager - ET money</i>
                                </div>
                                <ul>
                                    <li><img src="${pageContext.request.contextPath}/resources/newImages/star_yellow.png" alt=""></li>
                                    <li><img src="${pageContext.request.contextPath}/resources/newImages/star_yellow.png" alt=""></li>
                                    <li><img src="${pageContext.request.contextPath}/resources/newImages/star_yellow.png" alt=""></li>
                                    <li><img src="${pageContext.request.contextPath}/resources/newImages/star_yellow.png" alt=""></li>
                                    <li><img src="${pageContext.request.contextPath}/resources/newImages/star_half_yellow.png" alt=""></li>
                                </ul>
                            </div>
                            <div class="testimonial_bottom">
                                <p>The online investment journey was smooth, and I found it very convenient to invest from the safety of my home, during these times.</p>
                            </div>
                        </div>
                    </div>
                    <div class="word_testimonial_pad">
                        <div class="word_testimonial_inner">
                            <div class="testimonial_top">
                                <div class="testiNameLatter">--</div>
                                <div class="name_post">
                                    <p>Siddharth Shrivastava</p>
                                    <i>Manager</i>
                                </div>
                                <ul>
                                    <li><img src="${pageContext.request.contextPath}/resources/newImages/star_yellow.png" alt=""></li>
                                    <li><img src="${pageContext.request.contextPath}/resources/newImages/star_yellow.png" alt=""></li>
                                    <li><img src="${pageContext.request.contextPath}/resources/newImages/star_yellow.png" alt=""></li>
                                    <li><img src="${pageContext.request.contextPath}/resources/newImages/star_yellow.png" alt=""></li>
                                    <li><img src="${pageContext.request.contextPath}/resources/newImages/star_gray.png" alt=""></li>
                                </ul>
                            </div>
                            <div class="testimonial_bottom">
                                <p>I decided to invest because, even though the market sentiments are uncertain, I'm getting assured attractive returns on my FD.</p>
                            </div>
                        </div>
                    </div>
                    <div class="word_testimonial_pad">
                        <div class="word_testimonial_inner">
                            <div class="testimonial_top">
                                <div class="testiNameLatter">--</div>
                                <div class="name_post">
                                    <p>Vivek Pandey</p>
                                    <i>Self employed</i>
                                </div>
                                <ul>
                                    <li><img src="${pageContext.request.contextPath}/resources/newImages/star_yellow.png" alt=""></li>
                                    <li><img src="${pageContext.request.contextPath}/resources/newImages/star_yellow.png" alt=""></li>
                                    <li><img src="${pageContext.request.contextPath}/resources/newImages/star_yellow.png" alt=""></li>
                                    <li><img src="${pageContext.request.contextPath}/resources/newImages/star_yellow.png" alt=""></li>
                                    <li><img src="${pageContext.request.contextPath}/resources/newImages/star_yellow.png" alt=""></li>
                                </ul>
                            </div>
                            <div class="testimonial_bottom">
                                <p>Small monthly investments with assured returns! Enjoyed the easy process during investing in SDP.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <div class="investOnlinePart">
            <h2 class="fd_com_title">Invest online in 4 simple steps </h2>
            <div class="investOnlineTab">
                <ul class="tab_ul">
                    <li><a href="#;" class="active" data-tab="investOnlineFd">Fixed Deposit</a></li>
                    <li><a href="#;" data-tab="investOnlineSdp">Systematic Deposit Plan</a></li>
                </ul>
            </div>
            <div class="investOnlinemain">
                <div class="investOnlinecontent" id="investOnlineFd">
                    <div class="leftPart">
                        <img src="${pageContext.request.contextPath}/resources/newImages/fd_step_1.png" alt="">
                    </div>
                    <div class="rightPart">
                        <div class="investOnlineStepBox active">
                            <div class="stepnum step_1_Clr">1</div>
                            <p><strong>Step 1 - </strong>Verify your mobile number</p>
                            <i>Enter mobile number, date of birth, and verify OTP to continue. </i>
                        </div>
                        <div class="investOnlineStepBox">
                            <div class="tooltipBox">
                                <a href="#;"><img src="${pageContext.request.contextPath}/resources/newImages/info_orange.png" alt=""> </a>
                                <div class="tooltipMain">
                                    <div class="tooltipCon">
                                        <a href="#;" class="cross"><img src="${pageContext.request.contextPath}/resources/newImages/cross.png" alt=""> </a>
                                        <p>For CKYC</p>
                                        	<i>CKYC is a one-time KYC compliance process to avail of multiple financial services across institutions. To be mentioned as a header for CKYC card</i>
                                            <i>Here are the documents you need to keep handy: </i>
                                            <ul>
                                                <li>PAN Card</li>
                                            </ul>
                                            <i>Please follow these steps to complete your CKYC: </i>
                                            <ul>
                                                <li>Enter your full name, pin code, and PAN number</li>
                                            </ul>
                                            <p>For OKYC (Offline Aadhar)</p>
                                            <i>Here are the documents you need to keep handy: </i>
                                            <ul>
                                                <li>Aadhaar Card</li>
                                                <li>PAN Card</li>
                                            </ul>
                                            <i>Please follow these steps to complete your OKYC:</i>
                                            <ul>
                                                <li>Enter your full name, pin code, and PAN number</li>
                                                <li>Enter your Aadhaar number and the displayed security code</li>
                                                <li>Enter the OTP sent on your registered number, and create a share code</li>
                                                <li>Confirm your details fetched from CERSAI/OKYC and proceed</li>
                                            </ul>
                                            <p>For KYC through Documents upload Option</p>
                                            <i>Here are the documents you need to keep handy to upload the following:</i>
                                            <ol>
                                                <li>ID proof - Passport/Aadhaar/Driving License/Voter ID/NREGA</li>
                                                <li>Address proof - Passport/Aadhaar/Driving License/Voter ID/NREGA/NPR</li>
                                                <li>Photograph</li>
                                                <li>Signature proof</li>
                                            </ol>
                                            <i>*Same ID proof document can be uploaded for address proof as well.  </i>
                                            <a href="#;" class="gotIt">Got It</a>
                                    </div>
                                </div>
                            </div>
                            <div class="stepnum step_2_Clr">2</div>
                            <p><strong>Step 2 - </strong>Confirm your personal details</p>
                            <i>Existing customers can enter nominee details and proceed. New customers can proceed after completing their KYC online.</i>
                        </div>
                        <div class="investOnlineStepBox">
                            <div class="stepnum step_3_Clr">3</div>
                            <p><strong>Step 3 - </strong>Enter investment details</p>
                            <i>Enter deposit amount, tenor and interest payout type, along with bank account details. </i>
                        </div>
                        <div class="investOnlineStepBox">
                            <div class="stepnum step_4_Clr">4</div>
                            <p><strong>Step 4 - </strong>Complete payment</p>
                            <i>Choose to pay through Net Banking or UPI. UPI option is available for deposits up to Rs 1,00,000.</i>
                        </div>
                    </div>
                    <div class="buttonPart">
                        <a href="#;" id="fdInvestNew" data-href="a_part_2">INVEST NOW</a>
                    </div>
                </div>
                <div class="investOnlinecontent" id="investOnlineSdp">
                    <div class="leftPart">
                        <img src="${pageContext.request.contextPath}/resources/newImages/fd_step_2.png" alt="">
                    </div>
                    <div class="rightPart">
                        <div class="investOnlineStepBox active">
                            <div class="stepnum step_1_Clr">1</div>
                            <p><strong>Step 1 - </strong>Verify your mobile number</p>
                            <i>Enter mobile number, date of birth, and verify OTP to continue. </i>
                        </div>
                        <div class="investOnlineStepBox">
                        <div class="tooltipBox">
                                <a href="#;"><img src="${pageContext.request.contextPath}/resources/newImages/info_orange.png" alt=""> </a>
                                <div class="tooltipMain">
                                    <div class="tooltipCon">
                                        <a href="#;" class="cross"><img src="${pageContext.request.contextPath}/resources/newImages/cross.png" alt=""> </a>
                                        <p>For CKYC</p>
                                            <i>Here are the documents you need to keep handy: </i>
                                            <ul>
                                                <li>PAN Card</li>
                                            </ul>
                                            <i>Please follow these steps to complete your CKYC: </i>
                                            <ul>
                                                <li>Enter your full name, pin code, and PAN number</li>
                                            </ul>
                                            <p>For OKYC (Offline Aadhar)</p>
                                            <i>Here are the documents you need to keep handy: </i>
                                            <ul>
                                                <li>Aadhaar Card</li>
                                                <li>PAN Card</li>
                                            </ul>
                                            <i>Please follow these steps to complete your OKYC:</i>
                                            <ul>
                                                <li>Enter your full name, pin code, and PAN number</li>
                                                <li>Enter your Aadhaar number and the displayed security code</li>
                                                <li>Enter the OTP sent on your registered number, and create a share code</li>
                                                <li>Confirm your details fetched from CERSAI/OKYC and proceed</li>
                                            </ul>
                                            <p>For KYC through Documents upload Option</p>
                                            <i>Here are the documents you need to keep handy to upload the following:</i>
                                            <ol>
                                                <li>ID proof - Passport/Aadhaar/Driving License/Voter ID/NREGA</li>
                                                <li>Address proof - Passport/Aadhaar/Driving License/Voter ID/NREGA/NPR</li>
                                                <li>Photograph</li>
                                                <li>Signature proof</li>
                                            </ol>
                                            <i>*Same ID proof document can be uploaded for address proof as well.  </i>
                                            <a href="#;" class="gotIt">Got It</a>
                                    </div>
                                </div>
                            </div>
                            <div class="stepnum step_2_Clr">2</div>
                            <p><strong>Step 2 - </strong>Confirm your personal details</p>
                            <i>Existing customers can enter nominee details and proceed. New customers can proceed after completing their KYC online.</i>
                        </div>
                        <div class="investOnlineStepBox">
                            <div class="stepnum step_3_Clr">3</div>
                            <p><strong>Step 3 - </strong>Enter investment details</p>
                            <i>Choose your preferred scheme. Enter deposit amount, tenor, and deposit frequency, along with bank account details.  Select the auto-debit date for subsequent deposits.</i>
                        </div>
                        <div class="investOnlineStepBox">
                            <div class="stepnum step_4_Clr">4</div>
                            <p><strong>Step 4 - </strong>Complete payment</p>
                            <i>Choose to pay through Net Banking or UPI. UPI option is available for deposits up to Rs 1,00,000.</i>
                        </div>
                    </div>
                    <div class="buttonPart">
                        <a href="#;" id="sdpInvestNew" data-href="a_part_2">INVEST NOW</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="faqPart">
            <h2 class="fd_com_title">Still have questions? <i>Everything you need to know so you can invest in deposits</i></h2>
            <div class="faqTab">
                <ul class="tab_ul">
                    <li><a href="#;" class="active" data-tab="fdFaq">Fixed Deposit</a></li>
                    <li><a href="#;" data-tab="sdpFaq">Systematic Deposit Plan</a></li>
                </ul>
            </div>
            <x class="faqConMain">
                <div class="faqCon" id="fdFaq">
                    <div>
                        <div class="faqConBlock">
                            <h3>What is a fixed deposit? <i></i></h3>
                            <div class="faqAns">
                                <p>A fixed deposit is a savings option which helps you earn interest on savings parked with a financier of your choice.  You can choose to get returns on a periodic basis, or at maturity. The rates of interest are typically higher than money kept in savings accounts because the money is locked in for a specific period and cannot be withdrawn at will of the depositor, except in certain scenarios in which customer is ready to bear the penalty for premature withdrawal.</p>
                            </div>
                        </div>
                        <div class="faqConBlock">
                            <h3>Is there a minimum deposit amount or tenor for fixed deposit? <i></i></h3>
                            <div class="faqAns">
                                <p>The minimum amount for a fixed deposit is Rs. 15,000, while it's Rs. 5000 per month for Systematic Deposit Plan. The tenor can range from 12 months to 60 months.</p>
                            </div>
                        </div>
                        <div class="faqConBlock">
                            <h3>What interest rates does Bajaj Finance offer on fixed deposit? <i></i></h3>
                            <div class="faqAns">
                                <p>Bajaj Finance Limited offers attractive interest rates of up to <%=resource.getString("interest_rate_senior")%>%* p.a. for senior-citizens. Non-senior citizens will get returns of up to <%=resource.getString("interest_rate")%>%* p.a. on their deposits.</p>
                            </div>
                        </div>
                        <div class="faqConBlock">
                                <h3>Can I withdraw this FD before the tenor expiry? If yes, what is the impact on interest? <i></i></h3>
                                <div class="faqAns">
                                    <p>The lock-in period for any FD is three months, before which the FD cannot be withdrawn. For premature withdrawal there are penalty slabs as under:</p>
                                    <ul>
                                        <li>0-3 months: FD cannot be withdrawn (not applicable in death cases)</li>
                                        <li>3-6 months: No interest is payable on the deposit. Only the principal is payable</li>
                                        <li>>6 months: Interest payable is 2% lower than the interest rate applicable for the period for which the deposit has run for. In case of no interest specified for the period run, interest payable will be 3% lower than the lowest rate at which Bajaj Finance accepts deposits.</li>
                                    </ul>
                                </div>
                            </div>
                        <div class="faqConBlock">
                            <h3>When will TDS certificate be provided to the depositor? <i></i></h3>
                            <div class="faqAns">
                                <p>The TDS certificate will be emailed to the depositor every quarter.</p>
                            </div>
                        </div>
                        <div class="faqConBlock">
                            <h3>How soon will I receive my fixed deposit receipt?<i></i></h3>
                            <div class="faqAns">
                                <p>The depositor will receive the fixed deposit receipt by courier maximum within 3 weeks of creation of his/her deposit account.</p>
                            </div>
                        </div>
                        <div class="faqConBlock">
                            <h3>I am unable to track my fixed deposit receipt. Please assist.<i></i></h3>
                            <div class="faqAns">
                                <p>Fixed Deposit Receipt (FDR) tracking system will be made available shortly on our website. In the meanwhile, a virtual copy of the FD certificate is available online on our Customer Portal - Experia, hence the same can be viewed online.</p>
                            </div>
                        </div>
                        
                        <div class="faqConBlock">
                            <h3>What is the interest amount that will be credited to my account?<i></i></h3>
                            <div class="faqAns">
                                <p>Basis the scheme availed by the customer; the interest amount will be credited to the customer's bank account registered with us. After the interest is credit to a customer's account, communication for the same will be sent to the customer via SMS/email. Refer to your Statement of Account for the details on the interest scheme availed and interest payable details.</p>
                            </div>
                        </div>
                        <div class="faqConBlock">
                            <h3>How will the maturity amount be transferred?<i></i></h3>
                            <div class="faqAns">
                                <p>The maturity amount will be transferred to the bank account mentioned by the depositor in the application form through National Electronic Fund Transfer (NEFT)/Real Time Gross Settlement (RTGS) mode only. The entire amount shall be transferred on the date of maturity of the deposit. In case of electronic account transfer bounce, the depositor will be intimated through phone call, email and written letter requesting him to update the bank account details registered with us.</p>
                            </div>
                        </div>
                        <div class="faqConBlock">
                            <h3>How can I change my bank account details?<i></i></h3>
                            <div class="faqAns">
                                <p>To change your bank account details, download change of bank details form from <a href="https://www.bajajfinserv.in/forms-centre">https://www.bajajfinserv.in/forms-centre</a> and submit it along with a copy of FDR and cancelled cheque to your RM/broker.</p>
                            </div>
                        </div>
                    </div>
                    <div class="viewAllLink">
                        <a href="#;">View all</a>
                    </div>
                </div>
                <div class="faqCon" id="sdpFaq">
                    <div>
                        <div class="faqConBlock">
                            <h3>What is a Systematic Deposit Plan? <i></i></h3>
                            <div class="faqAns">
                                <p>Systematic Deposit Plan (SDP) is an industry-first savings plan offered by the Bajaj Finance Limited, which enables you to start growing your savings on a monthly basis. You can start saving with just Rs. 5000 per month, and choose to get your returns altogether, or on a monthly basis.<br><br>
								As a monthly savings option, SDP enables you to grow your savings on a monthly basis, without having to accumulate a lumpsum amount at once. Each deposit you make with SDP is a fixed deposit on its own, that accrues interest as per the rate of interest applicable on the date of deposit. Thus, you can gain assured returns on monthly deposits, which offers the convenience of SIPs and assurance of fixed deposit. </p>
                            </div>
                        </div>
                        <div class="faqConBlock">
                            <h3>What is the minimum deposit amount for SDP? <i></i></h3>
                            <div class="faqAns">
                                <p>To get started with a Systematic Deposit Plan, you only need to start with Rs. 5000 per month.</p>
                            </div>
                        </div>
                        <div class="faqConBlock">
                            <h3>How is the Systematic Deposit Plan different from Fixed Deposit? <i></i></h3>
                            <div class="faqAns">
                                <p>For those with lumpsum savings, fixed deposit is a great investment tool to get started with. However, if you're looking to save on a monthly basis, you can choose Systematic Deposit Plan (SDP). Under SDP, each of your deposits are separate FDs, which earn interest as per the interest rates prevailing on the date of booking.</p>
                            </div>
                        </div>
                        <div class="faqConBlock">
                            <h3>What is the Single Maturity Scheme?<i></i></h3>
                            <div class="faqAns">
                                <p>Single Maturity Scheme is a variant of Systematic Deposit Plan (SDP), where you receive maturity proceeds of all your deposits on a single day. Once you select the tenor for your first deposit, the tenor for each subsequent deposit placed, is regulated to mature on a single date. </p>
                            </div>
                        </div>
                        <div class="faqConBlock">
                            <h3>What is the Monthly Maturity Scheme?<i></i></h3>
                            <div class="faqAns">
                                <p>Monthly Maturity Scheme is another variant of the Systematic Deposit Plan, which offers maturity proceeds on a monthly basis. With this variant, you can invest in 6 to 48 deposits, and the tenor you choose, gets applied to all the deposits you invest in. The maturity date for each of these deposits is different, as your deposits mature as per the tenor you've selected.</p>
                            </div>
                        </div>
                        <div class="faqConBlock">
                            <h3>What are the number of deposits for Single Maturity Scheme and Monthly Maturity Scheme under SDP?<i></i></h3>
                            <div class="faqAns">
                                <p>With Single Maturity Scheme, you can make between 6 and 47 deposits; and with Monthly Maturity Scheme, you can choose to make between 6 and 48 deposits.</p>
                            </div>
                        </div>
                        <div class="faqConBlock">
                            <h3>What tenors can I choose with Single Maturity Scheme?<i></i></h3>
                            <div class="faqAns">
                                <p>When saving with Single Maturity Scheme, you can choose tenors ranging from 19 to 60 months.</p>
                            </div>
                        </div>
                        <div class="faqConBlock">
                            <h3>What tenors can I choose with Monthly Maturity Scheme?<i></i></h3>
                            <div class="faqAns">
                                <p>When saving with Monthly Maturity Scheme, you can choose tenors ranging from 12 to 60 months.</p>
                            </div>
                        </div>
                        <div class="faqConBlock">
                            <h3>How can I start saving with Systematic Deposit Plan?<i></i></h3>
                            <div class="faqAns">
                                <p>You can easily start saving with Systematic Deposit Plan through an end-to-end online process, from the comfort of your home. All you need is your Aadhaar or PAN to complete your KYC process.<br> 
									You can pay online through Net Banking or via UPI. Once your first deposit is made, you can register for NACH mandate, for auto-debit of the amount from your bank account, every month.</p>
                            </div>
                        </div>
                        <div class="faqConBlock">
                            <h3>Is there a penalty if I do not have money in my account for deposit?<i></i></h3>
                            <div class="faqAns">
                                <p>No bounce charges will be levied by Bajaj Finance Limited in case the depositor misses his/her monthly deposit. However, bounce charges may be levied by the Bank of the depositor for NACH dishonour and in that case BFL will not be held responsible.</p>
                            </div>
                        </div>
                    
                       <div class="faqConBlock">
                            <h3>Is a joint deposit possible?<i></i></h3>
                            <div class="faqAns">
                                <p>Yes. A joint deposit account can be opened under the Systematic Deposit Plan. It will follow the same guidelines as a regular Bajaj Finance Fixed Deposit.</p>
                            </div>
                        </div>
                        <div class="faqConBlock">
                            <h3>Will I get receipts for all deposits?<i></i></h3>
                            <div class="faqAns">
                                <p>Yes. You will be issued separate Fixed Deposit Receipt for each deposit in line with existing deposit program guidelines.</p>
                            </div>
                        </div>
                        <div class="faqConBlock">
                            <h3>Is the Auto-renew facility available?<i></i></h3>
                            <div class="faqAns">
                                <p>No, auto renewal option is not available for deposits through Systematic Deposit Plan. To renew every deposit under Monthly Maturity Scheme, a fresh renewal form needs to be filled and submitted.</p>
                            </div>
                        </div>
                        <div class="faqConBlock">
                            <h3>Can I stop the Deposit Plan anytime I want?<i></i></h3>
                            <div class="faqAns">
                                <p>Yes. In case you feel the need, you can cancel your NACH Mandate anytime in between and stop the Systematic Deposit payment.</p>
                            </div>
                        </div>
                        <div class="faqConBlock">
                            <h3>Can I get a loan against my deposits, made under Systematic Deposit Plan (SDP)?<i></i></h3>
                            <div class="faqAns">
                                <p>Yes, you can get a loan against any of the deposits made under SDP, after a minimum lock-in period of 3 months for each of your deposit(s).</p>
                            </div>
                        </div> 
                        
                        
                    </div>
                    <div class="viewAllLink">
                        <a href="#;">View all</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="senior_citizens_mark">
        <div class="container">
                <p>*Rate of interest per annum, applicable on a cumulative scheme tenor of 44 months for senior citizens</p>
                <p>**Rate of interest per annum, applicable on a cumulative scheme tenor of 44 months for non-senior citizens</p>
                <p>Regarding the company's deposit-taking activity, the viewers may refer to the advertisement in Indian Express and Loksatta, dated <%=resource.getString("disclaimer_date")%>, for soliciting public deposits. The company has a valid Certificate of Registration dated 05th March 1998 issued by the Reserve Bank of India under section 45 IA of the Reserve Bank of India Act, 1934. However, the RBI does not accept any responsibility or guarantee about the present position as to the company's financial soundness or for the correctness of any of the statements or representations made or opinions expressed by the company and for repayment of deposits/discharge of the liabilities by the company.</p>
            </div>
            </div>
        <div class="fdcopyright">
            <p>Copyright BajajFinserv  |  All Rights Reserved</p>
        </div>
</section>
		
		</div>
		<div class="a_part_2">
		
		<header class="fd_header">
        <div class="container">
            <a href="#;">
                <img src="${pageContext.request.contextPath}/resources/newImages/bajaj_logo_png.png" alt=""> 
            </a>
            
        </div>
    </header>    

    <section class="book_fd_step_sec">
        <div class="container">
            <div class="track_part_right">
                <div class="track_part_inner">
                    <div class="track_title">
                        <img src="${pageContext.request.contextPath}/resources/newImages/journey.png" alt="">
                        <p>Investment Journey</p>
                    </div>
                    <ul class="track_step track_step_Sdp">
                        <li class="active">
                            <div class="circle"></div>
                            <div class="line"></div>
                            <p>Mobile Verification</p>
                        </li>
						<li class="">
                            <div class="circle"></div>
                            <div class="line"></div>
                            <p>KYC Completion</p>
                            <div class="tooltipBox">
                                <a href="#;"><img src="${pageContext.request.contextPath}/resources/newImages/info.png" alt=""> </a>
                                <div class="tooltipMain">
                                    <div class="tooltipCon">
                                        <a href="#;" class="cross"><img src="${pageContext.request.contextPath}/resources/newImages/cross.png" alt=""> </a>
                                        <strong>KYC Completion</strong>
                                        <p>Existing customers do not require KYC.</p> 
                                        <p>For new customers, there are 3 ways to complete KYC:</p><p>1) CKYC  2) OKYC (offline Aadhar) 3) Upload documents</p>
                                        <a href="#;" class="gotIt">Got It</a>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="">
                            <div class="circle"></div>
                            <div class="line"></div>
                            <p>Personal Details</p>
                        </li>
                        <li class="">
                            <div class="circle"></div>
                            <div class="line"></div>
                            <p>Investment Details</p>
                        </li>
                        <li class="onlypayfd">
                            <div class="circle"></div>
                            <div class="line"></div>
                            <p> Payment</p>
                        </li>
                        <li class="onlySdp">
                            <div class="circle"></div>
                            <div class="line"></div>
                            <p>E-mandate</p>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="contentPart_left">
                <div class="bookCon_1 forMobileform">
                    <h1>Start saving with Bajaj Finance FD in few steps</h1>
                    <p>Enter your mobile number, date of birth and request OTP to continue</p>
                </div>
                <div class="bookCon_OTP forOtpForm">
                    <h1>OTP Sent</h1>
                    <p>Please enter the OTP received on your mobile</p>
                </div>
                <div class="login_block">
                   <div class="loginFirstStep firstChild">
                        <div class="login_tab">
                            <ul class="tab_ul">
                                <li><a href="#;" class="active" value="apply_for_new_fd"  data-tab='apply_for_new_fd'><span id="investType">Book new FD</span></a></li>
                                <li><a href="#;" value="resume_applicatIon" data-tab="resume_applicatIon">Resume Application</a></li>
                            </ul>
                            
                            <a href="#;" class="openIntTable">View Interest Rates </a>
                            
                        </div>
                        <div class="setFormImg">
                            <div class="setform_1">
                                <div class="formPart tax-data inspectlet-sensitive" id="apply_for_new_fd">
                                    <form id="apply_for_new_fd_form">
                                        <div class="inputMainBlock">
                                            <label>Mobile number</label>
                                            <div class="countryCode">+91</div>
                                            <input type="tel" class="mobileVD inspectletIgnore" id="mobileNO" maxlength=10 autocomplete="off">
                                            <div class="errormsg">Enter your mobile number</div>
                                            <div class="textHint">An OTP will be sent to this number for verification</div>
                                            <div class="tooltipBox">
                                                <a href="#;"><img src="${pageContext.request.contextPath}/resources/newImages/info_orange.png" alt=""> </a>
                                                <div class="tooltipMain">
                                                    <div class="tooltipCon">
                                                        <a href="#;" class="cross"><img src="${pageContext.request.contextPath}/resources/newImages/cross.png" alt=""> </a>
                                                        <strong>Mobile number</strong>
                                                        <p>Enter your mobile no. registered with us. If you are new to Bajaj Finance Limited then enter your mobile number registered with cKYC/Aadhaar.</p>
                                                        <a href="#;" class="gotIt">Got It</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                       <%--  <div class="inputMainBlock">
                                            <label>Date of Birth</label>
                                            <input type="text" class="dobVD inspectletIgnore" placeholder="DD/MM/YYYY" id="hldatepicker" autocomplete="off">
                                            <div class="errormsg">Enter your birth date</div>
                                            <div class="textHint">Enter date of birth as per PAN card</div>
                                            <div class="calendericon">
                                                <a href="#;">
                                                    <img src="${pageContext.request.contextPath}/resources/newImages/calender.png" alt="">
                                                </a>
                                            </div>
                                            <div class="tooltipBox">
                                                <a href="#;"><img src="${pageContext.request.contextPath}/resources/newImages/info_orange.png" alt=""> </a>
                                                <div class="tooltipMain">
                                                    <div class="tooltipCon">
                                                        <a href="#;" class="cross"><img src="${pageContext.request.contextPath}/resources/newImages/cross.png" alt=""> </a>
                                                        <strong>Date of birth</strong>
                                                        <p>Enter the date of birth, as per your PAN card. To invest online, you need to be at least 18 years of age. </p>
                                                        <a href="#;" class="gotIt">Got It</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div> --%>
                                        
                                        	<!-- new dob requirement 3April2023 -->
		<div class="inputMainBlock">																		
        <label>Date of Birth</label>
        <input type="tel" class="dobVD" placeholder="DD/MM/YYYY" id="hldatepicker" autocomplete="off">
        <div class="errormsg">Please enter Date of Birth as it appears on your PAN Card</div>
        <div class="textHint">Enter date of birth as per PAN card</div>

        <div class="tooltipBox">
            <a href="#;"><img src="${pageContext.request.contextPath}/resources/newImages/info_orange.png" alt="" loading="lazy"> </a>
            <div class="tooltipMain">
                <div class="tooltipCon">
                    <a href="#;" class="cross"><img src="${pageContext.request.contextPath}/resources/newImages/cross.png" alt="" loading="lazy"> </a>
                    <strong>Date of birth</strong>
                    <p>Enter the date of birth, as per your PAN card. To invest online, you need to be at least 18 years of age. </p>
                    <a href="#;" class="gotIt">Got It</a>
                </div>
            </div>
        </div>
    </div>		
                                                        <!-- new dob requirement 3April2023 -->
                                        <div class="a_ReInputPincode">
                                        <div class="inputMainBlock">
                                            <label>Pincode</label>
                                            <input type="number" class="PinCodeVD inspectletIgnore" id="pinCodePV" maxlength=6 autocomplete="off">
                                            <div class="errormsg">Enter your pin code</div>
                                            <div class="textHint">Enter pin code as per your current address.</div>
                                            <div class="tooltipBox">
                                                <a href="#;"><img src="${pageContext.request.contextPath}/resources/newImages/info_orange.png" alt=""> </a>
                                                <div class="tooltipMain">
                                                    <div class="tooltipCon">
                                                        <a href="#;" class="cross"><img src="${pageContext.request.contextPath}/resources/newImages/cross.png" alt=""> </a>
                                                        <strong>Pincode</strong>
                                                        <p>Enter the 6-digit pin code, as per your address proof.</p>
                                                        <a href="#;" class="gotIt">Got It</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        </div>
                                        <div class="beingAssisted">
                                            <p>Are you being assisted?</p>
                                            <!--<a href="#;"><img src="${pageContext.request.contextPath}/resources/newImages/info_orange.png" alt="" /></a>-->
                                            <div class="tooltipBox">
                                                <a href="#;"><img src="${pageContext.request.contextPath}/resources/newImages/info_orange.png" alt=""> </a>
                                                <div class="tooltipMain">
                                                    <div class="tooltipCon">
                                                        <a href="#;" class="cross"><img src="${pageContext.request.contextPath}/resources/newImages/cross.png" alt=""> </a>
                                                        
                                                        <p>Enter the employee/partner sourcing code, only if you're being assisted by a Bajaj Finance employee or authorized partner.</p>
                                                        <p>Once you enter the code, the name of employee/partner will be pre-populated against the sourcing code.</p>
                                                        <a href="#;" class="gotIt">Got It</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <ul class="tab_ul beingAssistedFormShowOrHide">
                                                <li><a href="#;" class="active">NO</a></li>
                                                <li><a href="#;">YES</a></li>
                                            </ul>
                                        </div>
                                        <div class="beingAssistedForm">
                                            <div class="inputMainBlock">
                                                <label>Employee/Partner Code</label>
                                                <input type="number" class="AnyValueVD nomandetory inspectletIgnore" id="pCode" autocomplete="off">
                                                <div class="errormsg">Enter valid employee/partner code</div>
                                                <div class="textHint">Enter sourcing code of the employee/partner assisting you</div>
                                            </div>
                                            <div class="inputMainBlock">
                                                <label>Employee/Partner Name</label>
                                                <input type="text" class="AnyValueVD nomandetory inspectletIgnore" id="pName" autocomplete="off" disabled>
                                                <div class="errormsg">Enter valid employee/partner name</div>
                                            </div>
                                        </div>
                                        <div class="checkTNC">
                                            <label>
                                                <input type="checkbox" value="tNc" id="t&ccheckbox">
                                                <i>I authorise BFL to fetch my CKYC/offline Aadhar KYC details available with CERSAI/UIDAI. <a class="newPDFtnc" href="${pageContext.request.contextPath}/resources/pdf/FD_T&C.pdf" target="_blank">Terms & conditions</a> |<a href="https://www.bajajfinserv.in/privacy-policy" target="_blank"> Privacy policy</a></i>
                                                <strong class="errormsg">Please accept Terms and Conditions</strong>
                                            </label>
                                        </div>
                                        
                                        <div class="buttonPart">
                                            <button class="submitBTN_New validBtn" id="generateOtpButton" >GENERATE OTP <i></i></button>
                                        </div>

                                    </form>
                                </div>
                                <div class="formPart tax-data inspectlet-sensitive" id="resume_applicatIon">
                                    <form id="resume_applicatIon_form">
                                        <div class="inputMainBlock">
                                            <label>Mobile number</label>
                                            <div class="countryCode">+91</div>
                                            <input type="tel" class="mobileVD inspectletIgnore" id="enterapp" autocomplete="off">
                                            <div class="errormsg">Enter your mobile number</div>
                                            <div class="textHint">An OTP will be sent to this number for verification.</div>
                                            <div class="tooltipBox">
                                                <a href="#;"><img src="${pageContext.request.contextPath}/resources/newImages/info_orange.png" alt=""> </a>
                                                <div class="tooltipMain">
                                                    <div class="tooltipCon">
                                                        <a href="#;" class="cross"><img src="${pageContext.request.contextPath}/resources/newImages/cross.png" alt=""> </a>
                                                        <strong>Mobile number</strong>
                                                        <p>Enter the mobile number you entered during your previous application, to continue from where you left.</p>
                                                        <a href="#;" class="gotIt">Got It</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="checkTNC">
                                            <label>
                                                <input type="checkbox">
                                                <i>I authorise BFL to fetch my CKYC/offline Aadhar KYC details available with CERSAI/UIDAI. <a class="newPDFtnc" href="${pageContext.request.contextPath}/resources/pdf/FD_T&C.pdf" target="_blank">Terms & conditions</a> | <a href="https://www.bajajfinserv.in/privacy-policy" target="_blank">Privacy policy</a></i>
                                                <strong class="errormsg">Please accept Terms and Conditions</strong>
                                            </label>
                                        </div>
                                        <div class="buttonPart">
                                            <button class="submitBTN_New validBtn" id="resumeGenerateOtpButton" >GENERATE OTP <i></i></button>
                                        </div>

                                    </form>
                                </div>
                            </div>
                            <div class="representIcon">
                                <img src="${pageContext.request.contextPath}/resources/newImages/Form_IGG.jpg" alt="">
                            </div>
                        </div>
                    </div>
                    <div class="otpMainPart secondChild">
                        <div class="setFormImg">
                            <div class="setform_1">
                                <div class="formPart">
                                    <div class="optTopDetails">
                                        <img src="${pageContext.request.contextPath}/resources/newImages/otp_lock_vector.png" alt="">
                                        <p>Enter One Time Password (OTP)</p>
                                        <i>Please enter the OTP received on mobile <span id = "sent_Mo"> 7972236634 </span></i>
                                        <p>Entered a wrong number ? <a href="#;">Change here</a></p>
                                    </div>
                                    <form id="otpForm">
                                        <div class="inputMainBlock">
                                             <input type="number" placeholder="" id="otp1" class="otpVD" autocomplete="off" >
                                             <label for="otp1"></label>
                                             <div class="errormsg">Enter valid OTP</div>
                                        </div>
                                        
                                        <div class="otpCounter">
                                            <i>In 00:00</i>
                                            <a href="#;">Resend OTP</a>
                                        </div>
                                        <div class="otpTnc">
                                            <p>By validating OTP, I accept all the T&C</p>
                                        </div>
                                        <div class="buttonPart">
                                            <button id="receiveOtp" class="submitBTN_New validBtn">SUBMIT <i></i></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="representIcon_2">
                                <img src="${pageContext.request.contextPath}/resources/newImages/otp_vactor.png" alt="">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="earnMoreFd">
                    <h2 class="fd_com_title">Earn more with our Fixed Deposit <a href="#;" class="openIntTable">View Interest Rates </a></h2>
                    <div class="earnMoreFd_block">
                        <%-- <div class="earnMoreFd_pad">
                            <img src="${pageContext.request.contextPath}/resources/newImages/why_invest_fd_4.png" alt="">
                            <p class="moreDet1">Additional 0.10% p.a. Interest if you book an FD Online</p>
                        </div> --%>
                        <div class="earnMoreFd_pad">
                            <img src="${pageContext.request.contextPath}/resources/newImages/why_invest_fd_2.png" alt="">
                            <p class="moreDet2">100% Online process. Quickest ways to book an FD</p>
                        </div>
                        <div class="earnMoreFd_pad">
                            <img src="${pageContext.request.contextPath}/resources/newImages/why_invest_fd_1.png" alt="">
                            <p class="moreDet3">Brand trusted by over 4 crore customers</p>
                        </div>
                        <div class="earnMoreFd_pad">
                            <img src="${pageContext.request.contextPath}/resources/newImages/why_invest_fd_6.png" alt="">
                            <p class="moreDet4">Highest stability ratings of FAAA and MAAA</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

   	
		<div class="interestRatePop">
				<div class="interestRatePopMiddle">
					<a href="#;"><img
						src="${pageContext.request.contextPath}/resources/newImages/cross.png"
						alt=""> </a>
					<div class="interestRatePopInner" id="fdInterestTable">
						<h3>Rate of Interest effective <%=resource.getString("interest_date")%></h3>
						<i>All the rates of interests mentioned are annualized and
							from Rs. 15,000 to Rs. 5 Crore per deposit. Interest rates will
							be applicable as on date of deposit amount realisation in BFL
							account.</i>

						<p class="tableTitlepop">For senior citizens* (Inclusive of
							additional rate benefit of 0.25% p.a.):</p>
						
	<div class="interest_table_inner">
							<table>
								<tbody><tr>
									<th rowspan="2">Tenor in months</th>
									<th colspan="5">Interest Payout</th>
								</tr>
								<tr>
									<th>Cumulative</th>
									<th colspan="4">Non Cumulative</th>
								</tr>
								<tr class="tbBlueBold">
									<td></td>
									<td>At Maturity</td>
									<td>Monthly</td>
									<td>Quarterly</td>
									<td>Half-yearly</td>
									<td>Annually</td>
								</tr>
								<tr>
									<td>15</td>
									<td><%=resource.getString("special_sen_15_maturity_annually")%>%</td>
									<td><%=resource.getString("special_sen_15_monthly")%>%</td>
									<td><%=resource.getString("special_sen_15_quarterly")%>%</td>
									<td><%=resource.getString("special_sen_15_halfyearly")%>%</td>
									<td><%=resource.getString("special_sen_15_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>18</td>
									<td><%=resource.getString("special_sen_18_maturity_annually")%>%</td>
									<td><%=resource.getString("special_sen_18_monthly")%>%</td>
									<td><%=resource.getString("special_sen_18_quarterly")%>%</td>
									<td><%=resource.getString("special_sen_18_halfyearly")%>%</td>
									<td><%=resource.getString("special_sen_18_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>22</td>
									<td><%=resource.getString("special_sen_22_maturity_annually")%>%</td>
									<td><%=resource.getString("special_sen_22_monthly")%>%</td>
									<td><%=resource.getString("special_sen_22_quarterly")%>%</td>
									<td><%=resource.getString("special_sen_22_halfyearly")%>%</td>
									<td><%=resource.getString("special_sen_22_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>30</td>
									<td><%=resource.getString("special_sen_30_maturity_annually")%>%</td>
									<td><%=resource.getString("special_sen_30_monthly")%>%</td>
									<td><%=resource.getString("special_sen_30_quarterly")%>%</td>
									<td><%=resource.getString("special_sen_30_halfyearly")%>%</td>
									<td><%=resource.getString("special_sen_30_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>33</td>
									<td><%=resource.getString("special_sen_33_maturity_annually")%>%</td>
									<td><%=resource.getString("special_sen_33_monthly")%>%</td>
									<td><%=resource.getString("special_sen_33_quarterly")%>%</td>
									<td><%=resource.getString("special_sen_33_halfyearly")%>%</td>
									<td><%=resource.getString("special_sen_33_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>44</td>
									<td><%=resource.getString("special_sen_44_maturity_annually")%>%</td>
									<td><%=resource.getString("special_sen_44_monthly")%>%</td>
									<td><%=resource.getString("special_sen_44_quarterly")%>%</td>
									<td><%=resource.getString("special_sen_44_halfyearly")%>%</td>
									<td><%=resource.getString("special_sen_44_maturity_annually")%>%</td>
								</tr>
							</tbody></table>
						</div>
											
<p class="tableBottonLine"></p>

						<div class="interest_table_inner">
							<table>
								<tr>
									<th rowspan="2">Tenor in months</th>
									<th colspan="5">Interest Payout</th>
								</tr>
								<tr>
									<th>Cumulative</th>
									<th colspan="4">Non Cumulative</th>
								</tr>
								<tr class="tbBlueBold">
									<td></td>
									<td>At Maturity</td>
									<td>Monthly</td>
									<td>Quarterly</td>
									<td>Half-yearly</td>
									<td>Annually</td>
								</tr>
								 <tr>
                     				<td>12-14</td>
                     				<td><%=resource.getString("regular_sen_12to14_maturity_annually")%>%</td>
									<td><%=resource.getString("regular_sen_12to14_monthly")%>%</td>
									<td><%=resource.getString("regular_sen_12to14_quarterly")%>%</td>
									<td><%=resource.getString("regular_sen_12to14_halfyearly")%>%</td>
									<td><%=resource.getString("regular_sen_12to14_maturity_annually")%>%</td>
                  				</tr>
                  				<tr>
                     				<td>>15-23</td>
                     				<td><%=resource.getString("regular_sen_15to23_maturity_annually")%>%</td>
									<td><%=resource.getString("regular_sen_15to23_monthly")%>%</td>
									<td><%=resource.getString("regular_sen_15to23_quarterly")%>%</td>
									<td><%=resource.getString("regular_sen_15to23_halfyearly")%>%</td>
									<td><%=resource.getString("regular_sen_15to23_maturity_annually")%>%</td>
                  				</tr>
                  				<tr>
                     				<td>24</td>
                     				<td><%=resource.getString("regular_sen_24_maturity_annually")%>%</td>
									<td><%=resource.getString("regular_sen_24_monthly")%>%</td>
									<td><%=resource.getString("regular_sen_24_quarterly")%>%</td>
									<td><%=resource.getString("regular_sen_24_halfyearly")%>%</td>
									<td><%=resource.getString("regular_sen_24_maturity_annually")%>%</td>
                  				</tr>
                  				<tr>
                     				<td>25-35</td>
                     				<td><%=resource.getString("regular_sen_25to35_maturity_annually")%>%</td>
									<td><%=resource.getString("regular_sen_25to35_monthly")%>%</td>
									<td><%=resource.getString("regular_sen_25to35_quarterly")%>%</td>
									<td><%=resource.getString("regular_sen_25to35_halfyearly")%>%</td>
									<td><%=resource.getString("regular_sen_25to35_maturity_annually")%>%</td>
                 				 </tr>
                  				<tr>
                     				<td>36-60</td>
                     				<td><%=resource.getString("regular_sen_36to60_maturity_annually")%>%</td>
									<td><%=resource.getString("regular_sen_36to60_monthly")%>%</td>
									<td><%=resource.getString("regular_sen_36to60_quarterly")%>%</td>
									<td><%=resource.getString("regular_sen_36to60_halfyearly")%>%</td>
									<td><%=resource.getString("regular_sen_36to60_maturity_annually")%>%</td>
                  				</tr>
							</table>
						</div>
                <p class="tableBottonLine"></p>

						<p class="tableTitlepop">For Non senior citizens</p>
						<div class="interest_table_inner">
							<table>
								<tbody><tr>
									<th rowspan="2">Tenor in months</th>
									<th colspan="5">Interest Payout</th>
								</tr>
								<tr>
									<th>Cumulative</th>
									<th colspan="4">Non Cumulative</th>
								</tr>
								<tr class="tbBlueBold">
									<td></td>
									<td>At Maturity</td>
									<td>Monthly</td>
									<td>Quarterly</td>
									<td>Half-yearly</td>
									<td>Annually</td>
								</tr>
								<tr>
									<td>15</td>
									<td><%=resource.getString("special_15_maturity_annually")%>%</td>
									<td><%=resource.getString("special_15_monthly")%>%</td>
									<td><%=resource.getString("special_15_quarterly")%>%</td>
									<td><%=resource.getString("special_15_halfyearly")%>%</td>
									<td><%=resource.getString("special_15_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>18</td>
									<td><%=resource.getString("special_18_maturity_annually")%>%</td>
									<td><%=resource.getString("special_18_monthly")%>%</td>
									<td><%=resource.getString("special_18_quarterly")%>%</td>
									<td><%=resource.getString("special_18_halfyearly")%>%</td>
									<td><%=resource.getString("special_18_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>22</td>
									<td><%=resource.getString("special_22_maturity_annually")%>%</td>
									<td><%=resource.getString("special_22_monthly")%>%</td>
									<td><%=resource.getString("special_22_quarterly")%>%</td>
									<td><%=resource.getString("special_22_halfyearly")%>%</td>
									<td><%=resource.getString("special_22_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>30</td>
									<td><%=resource.getString("special_30_maturity_annually")%>%</td>
									<td><%=resource.getString("special_30_monthly")%>%</td>
									<td><%=resource.getString("special_30_quarterly")%>%</td>
									<td><%=resource.getString("special_30_halfyearly")%>%</td>
									<td><%=resource.getString("special_30_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>33</td>
									<td><%=resource.getString("special_33_maturity_annually")%>%</td>
									<td><%=resource.getString("special_33_monthly")%>%</td>
									<td><%=resource.getString("special_33_quarterly")%>%</td>
									<td><%=resource.getString("special_33_halfyearly")%>%</td>
									<td><%=resource.getString("special_33_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>44</td>
									<td><%=resource.getString("special_44_maturity_annually")%>%</td>
									<td><%=resource.getString("special_44_monthly")%>%</td>
									<td><%=resource.getString("special_44_quarterly")%>%</td>
									<td><%=resource.getString("special_44_halfyearly")%>%</td>
									<td><%=resource.getString("special_44_maturity_annually")%>%</td>
								</tr>
							</tbody></table>
						</div>
						<p class="tableBottonLine"></p>
<div class="interest_table_inner">
							<table>
								<tr>
									<th rowspan="2">Tenor in months</th>
									<th colspan="5">Interest Payout</th>
								</tr>
								<tr>
									<th>Cumulative</th>
									<th colspan="4">Non Cumulative</th>
								</tr>
								<tr class="tbBlueBold">
									<td></td>
									<td>At Maturity</td>
									<td>Monthly</td>
									<td>Quarterly</td>
									<td>Half-yearly</td>
									<td>Annually</td>
								</tr>
								 <tr>
                     				<td>12-14</td>
                     				<td><%=resource.getString("regular_12to14_maturity_annually")%>%</td>
									<td><%=resource.getString("regular_12to14_monthly")%>%</td>
									<td><%=resource.getString("regular_12to14_quarterly")%>%</td>
									<td><%=resource.getString("regular_12to14_halfyearly")%>%</td>
									<td><%=resource.getString("regular_12to14_maturity_annually")%>%</td>
                  </tr>
                  <tr>
                     				<td>>15-23</td>
                     				<td><%=resource.getString("regular_15to23_maturity_annually")%>%</td>
									<td><%=resource.getString("regular_15to23_monthly")%>%</td>
									<td><%=resource.getString("regular_15to23_quarterly")%>%</td>
									<td><%=resource.getString("regular_15to23_halfyearly")%>%</td>
									<td><%=resource.getString("regular_15to23_maturity_annually")%>%</td>
                  </tr>
                  <tr>
                     				<td>24</td>
                     				<td><%=resource.getString("regular_24_maturity_annually")%>%</td>
									<td><%=resource.getString("regular_24_monthly")%>%</td>
									<td><%=resource.getString("regular_24_quarterly")%>%</td>
									<td><%=resource.getString("regular_24_halfyearly")%>%</td>
									<td><%=resource.getString("regular_24_maturity_annually")%>%</td>
                  </tr>
                  <tr>
                     				<td>25-35</td>
                     				<td><%=resource.getString("regular_25to35_maturity_annually")%>%</td>
									<td><%=resource.getString("regular_25to35_monthly")%>%</td>
									<td><%=resource.getString("regular_25to35_quarterly")%>%</td>
									<td><%=resource.getString("regular_25to35_halfyearly")%>%</td>
									<td><%=resource.getString("regular_25to35_maturity_annually")%>%</td>
                  </tr>
                  <tr>
                     				<td>36-60</td>
                     				<td><%=resource.getString("regular_36to60_maturity_annually")%>%</td>
									<td><%=resource.getString("regular_36to60_monthly")%>%</td>
									<td><%=resource.getString("regular_36to60_quarterly")%>%</td>
									<td><%=resource.getString("regular_36to60_halfyearly")%>%</td>
									<td><%=resource.getString("regular_36to60_maturity_annually")%>%</td>
                  </tr>
							</table>
						</div>
                <p class="tableBottonLine"></p>
					</div>
					<div class="interestRatePopInner" id="sdpInterestTable">
						<h3>Rate of Interest effective <%=resource.getString("interest_date")%></h3>
						<i>All the rates of interests mentioned are annualized and
							from Rs. 5,000 to Rs. 5 Crore per deposit. Interest rates will be
							applicable as on date of deposit amount realisation in BFL
							account.</i>

						<p class="tableTitlepop">Senior Citizens* (Inclusive of
							additional rate benefit of 0.25% p.a.)</p>
						 <div class="interest_table_inner">
							<table>
								<tbody><tr>
									<th>Tenor in months</th>
									<th>Cumulative rates of interest</th>
								</tr>
								<tr class="tbBlueBold">
									<td></td>
									<td>At Maturity</td>
								</tr>
								<tr>
									<td>15</td>
									<td><%=resource.getString("special_sen_sdp_15_maturity")%>%</td>
								</tr>
								<tr>
									<td>18</td>
									<td><%=resource.getString("special_sen_sdp_18_maturity")%>%</td>
								</tr>
								<tr>
									<td>22</td>
									<td><%=resource.getString("special_sen_sdp_22_maturity")%>%</td>
								</tr>
								<tr>
									<td>30</td>
									<td><%=resource.getString("special_sen_sdp_30_maturity")%>%</td>
								</tr>
								<tr>
									<td>33</td>
									<td><%=resource.getString("special_sen_sdp_33_maturity")%>%</td>
								</tr>
								<tr>
									<td>44</td>
									<td><%=resource.getString("special_sen_sdp_44_maturity")%>%</td>
								</tr>
							</tbody></table>
						</div>
						
<p class="tableBottonLine"></p>
<div class="interest_table_inner">
							<table>
								<tr>
									<th>Tenor in months</th>
									<th>Cumulative rates of interest</th>
								</tr>
								<tr class="tbBlueBold">
									<td></td>
									<td>At Maturity</td>
								</tr>
								<tr>
                     <td>12-14</td>
                     <td><%=resource.getString("regular_sen_sdp_12to14_maturity")%>%</td>
                  </tr>
                  <tr>
                     <td>>15-23</td>
                     <td><%=resource.getString("regular_sen_sdp_15to23_maturity")%>%</td>
                  </tr>
                  <tr>
                     <td>24</td>
                     <td><%=resource.getString("regular_sen_sdp_24_maturity")%>%</td>
                  </tr>
                  <tr>
                     <td>25-35</td>
                     <td><%=resource.getString("regular_sen_sdp_25to35_maturity")%>%</td>
                  </tr>
                  <tr>
                     <td>36-60</td>
                     <td><%=resource.getString("regular_sen_sdp_36to60_maturity")%>%</td>
                  </tr>
							</table>
						</div>
                <p class="tableBottonLine"></p>
						<p class="tableTitlepop">Non - Senior Citizens</p>
						
						 					<div class="interest_table_inner">
							<table>
								<tbody><tr>
									<th>Tenor in months</th>
									<th>Cumulative rates of interest</th>
								</tr>
								<tr class="tbBlueBold">
									<td></td>
									<td>At Maturity</td>
								</tr>
								<tr>
									<td>15</td>
									<td><%=resource.getString("special_sdp_15_maturity")%>%</td>
								</tr>
								<tr>
									<td>18</td>
									<td><%=resource.getString("special_sdp_18_maturity")%>%</td>
								</tr>
								<tr>
									<td>22</td>
									<td><%=resource.getString("special_sdp_22_maturity")%>%</td>
								</tr>
								<tr>
									<td>30</td>
									<td><%=resource.getString("special_sdp_30_maturity")%>%</td>
								</tr>
								<tr>
									<td>33</td>
									<td><%=resource.getString("special_sdp_33_maturity")%>%</td>
								</tr>
								<tr>
									<td>44</td>
									<td><%=resource.getString("special_sdp_44_maturity")%>%</td>
								</tr>	
							</tbody></table>
						</div> 
						
						<p class="tableBottonLine"></p>
 
  						<div class="interest_table_inner">
							<table>
								<tr>
									<th>Tenor in months</th>
									<th>Cumulative rates of interest</th>
								</tr>
								<tr class="tbBlueBold">
									<td></td>
									<td>At Maturity</td>
								</tr>
								<tr>
                     <td>12-14</td>
                     <td><%=resource.getString("regular_sdp_12to14_maturity")%>%</td>
                  </tr>
                  <tr>
                     <td>>15-23</td>
                     <td><%=resource.getString("regular_sdp_15to23_maturity")%>%</td>
                  </tr>
                  <tr>
                     <td>24</td>
                     <td><%=resource.getString("regular_sdp_24_maturity")%>%</td>
                  </tr>
                  <tr>
                     <td>25-35</td>
                     <td><%=resource.getString("regular_sdp_25to35_maturity")%>%</td>
                  </tr>
                  <tr>
                     <td>36-60</td>
                     <td><%=resource.getString("regular_sdp_36to60_maturity")%>%</td>
                  </tr>
							</table>
						</div>
						
						<p class="tableBottonLine"></p>
					</div>
				</div>
			</div>
		</div>

		<div class="a_part_3">
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

							<!-- <div class="a_ReInput">
								<label for="pinCodePV">PIN CODE</label> <input type="number"
									name="pinCode" id="pinCodePV" class="PinCodeVD"
									autocomplete="off">
								<div class="errormsg">Enter your pincode</div>
							</div> -->
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
            <strong>Book your <span id="investment">FD</span> in 4 simple steps</strong>
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
                    <div class="a_ReInput cl-ReInput">
							  <p class="topdesc">Please upload your KYC documents to proceed further</p>
								<img src="/fixed-deposit-application-form/resources/images/information_black.png" alt="information.png">
									<div class="p_fixdepositstultip">
										<div class="p_fixdepositBG">
											<p>The data shared by you is safe with us and will only be used to verify your identity.</p>
											<div class="p_gotitbtn">
												<button class="gotitone">Got it</button>
											</div>
										</div>
									</div>
							</div>
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
                            <label class="piclab paddingPhoto">PLEASE UPLOAD RECENT PHOTOGRAPH</label>
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
						<p>Review your personal details to book an FD</p>
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
									name="dateOfBirth" class="datepickerVD nomandetory inspectletIgnore" id="dobPD"
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
								<label for="addressPD">Address
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
									id="addressPD" autocomplete="off" readonly >

								<div class="errormsg">Enter your address</div>
								<div class="p_clecnder" id="adderssLock">
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
								<div class="a_ReInput openDrop">
                                <label>Upload Address Proof</label>
                                <input type="text" class="CommUploadVD nomandetory" autocomplete="off" id="commAddressattache" disabled  >
                                <i class="fas fa-caret-down"></i>
                                <div class="errormsg">'Upload Address Proof</div>
                                <div class="uploadDropdown">
                                    <ul>
                                        <li>
                                            <label> Aadhaar Card <input type="file" class="commAddressDoc" id="addharcardDoc"> </label>
                                        </li>
                                        <li>
                                            <label>NREGA Job Card <input type="file" class="commAddressDoc" id="nrjobcardDoc" > </label>
                                        </li>
                                        <li>
                                            <label>Valid Passport <input type="file"class="commAddressDoc" id="passportDoc"> </label>
                                        </li>
                                        <li>
                                            <label>Valid Driving Licence <input type="file" class="commAddressDoc" id="drivinglicenceDoc"> </label>
                                        </li>
                                        <li>
                                            <label>Voter ID Card <input type="file"class="commAddressDoc" id="voterIdDoc"> </label>
                                        </li>
                                        <li>
                                            <label>Letter issued by National Population Register <input type="file" class="commAddressDoc" id="letterNPRDoc"> </label>
                                        </li>
                                    </ul>
                                </div>
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
										type="number" class="datepickerVD get18age inspectletIgnore" id="nomineedd"
										placeholder="DD" autocomplete="off"
										aria-labelledby="sdpdateOfBirth">
									<div class="dateslace">/</div>
									<input type="number" class="datepickerVD get18age inspectletIgnore"
										id="nomineemm" placeholder="MM" autocomplete="off"
										aria-labelledby="sdpdateOfBirth">
									<div class="dateslace">/</div>
									<input type="number" class="datepickerVD nomineeYear get18age inspectletIgnore"
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
									<input type="text" pattern="[0-9]*" inputmode="numeric" name="depositAmount" class="sliderAmunt"
										id="fdAmount" placeholder="Enter amount" autocomplete="off"
										value="">
									<div class="errormsg">Please enter the amount</div>
									<a href="#;" class="infored"><img
										src="${pageContext.request.contextPath}/resources/images/errorRed.png"
										alt="errorRed"></a>
									<div class="inputDesc">
										Min <i class="fas fa-rupee"></i> 15,000 & Max <i
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
									<p>Invest more to earn more.</p>
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
											class="fas fa-rupee"></i> 15,000
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

									<p>Choosing a longer tenor helps you earn higher returns</p>
									<a href="#;">GOT IT</a>
								</div>
							</div>

							<div class="a_selectblock a_ReInput specialTenor">
								<div class="row4">
									<div class="a_part20 tenorPeriod">
										<label for="tenor1"> <input type="radio" name="tenor"
											value="60" id="tenor1"  >
											<p>60</p> <i></i> <strong class="fas fa-star"></strong>
										</label>
									</div>
									<div class="a_part20 tenorPeriod">
										<label for="tenor2"> <input type="radio" name="tenor"
											id="tenor2" value="44" checked>
											<p>44</p> <i></i>
										</label>
									</div>
									<!-- <div class="a_part20 tenorPeriod">
										<label for="tenor3"> <input type="radio" name="tenor"
											id="tenor3" value="39">
											<p>39</p> <i></i>
										</label>
									</div> -->
									<div class="a_part20 tenorPeriod">
										<label for="tenor4"> <input type="radio" name="tenor"
											id="tenor4" value="30">
											<p>30</p> <i></i>
										</label>
									</div>
									<div class="a_part20 tenorPeriod">
										<label for="tenor5"> <input type="radio" name="tenor"
											id="tenor5" value="22">
											<p>22</p> <i></i>
										</label>
									</div>
									
									<div class="a_part20 tenorPeriod">
										<label for="tenor6"> <input type="radio" name="tenor"
											id="tenor6" value="18">
											<p>18</p> <i></i>
										</label>
									</div>
									
									<div class="a_part20 tenorPeriod">
										<label for="tenor7"> <input type="radio" name="tenor"
											id="tenor7" value="15">
											<p>15</p> <i></i>
										</label>
									</div>
									
									
									<div class="a_part20 custmTenor tenorPeriod">
										<label for="tenor8"> <input type="radio" name="tenor"
											id="tenor8" value="Custom">
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
									<p class="a_fdTenorgreatChose1">More is better!</p>
									<p class="a_fdTenorgreatChose2">Stay invested for 60 months to get maximum returns</p>
								</div>
								<div class="a_greensataus2">
									<img
										src="${pageContext.request.contextPath}/resources/images/like.png"
										alt="like">
									<p>Great choice!</p>
									<p class="a_fdTenorgreatChose">By choosing interest payout on maturity, you are getting returns of Rs. <i class="fas fa-rupee"></i> on your deposit
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
							<p>Disclaimer: Actual returns may vary slightly if deposit includes a leap year.</p>
							<p>*Interest start date will be at transaction date subject to fund realisation in BFL's account from payment gateway. The working days exclude public holidays or Saturday (Second & Fourth) or Sunday.</p>
							<p>*If the date of transaction falls on any banking holiday, then your deposit will be booked on the next working day. Accordingly, there could be a slight delay in your Fixed Deposit Receipt. However, your interest start date will be from the date you transfer the funds.</p>
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

									<option class="bothBankOption" value="AU Small Finance Bank">AU
										SMALL FINANCE BANK LIMITED</option>
										<option class="bothBankOption" value="Axis Bank">AXIS
										BANK </option>
								 <option class="upihide" value="Abhyudaya Cooperative Bank Limited">
									ABHYUDAYA COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="Ahmedabad Mercantile Cooperative Bank">
									AHMEDABAD MERCANTILE COOPERATIVE BANK</option>
									<option class="upihide" value="Ahmednagar Merchants Co-Op Bank Ltd">
									AHMEDNAGAR MERCHANTS CO-OP BANK LTD</option>
									<option class="upihide" value="Akola Janata Commercial Cooperative Bank">
									AKOLA JANATA COMMERCIAL COOPERATIVE BANK</option>
									<option class="upihide" value="Andhra Pradesh Grameena Vikas Bank">
									ANDHRA PRADESH GRAMEENA VIKAS BANK</option>
									<option class="upihide" value="Andhra Pragathi Grameena Bank">ANDHRA PRAGATHI GRAMEENA BANK</option>
									<option class="upihide" value="Apna Sahakari Bank Limited">APNA SAHAKARI BANK LIMITED
										</option>
									<option class="upihide" value="Arvind Sahakari Bank Ltd">ARVIND SAHAKARI BANK LTD </option>
									
									<option class="upihide" value="Bank Of America">BANK OF AMERICA</option>
									<option class="bothBankOption" value="Bandhan Bank">BANDHAN BANK LIMITED </option>
										<option class="bothBankOption" value="Bank of Baroda Retail">BANK OF BARODA</option>
									<option class="bothBankOption" value="Bank of Baroda Retail">BANK OF BARODA-RETAIL BANKING</option>
									<option class="bothBankOption" value="BANK OF BARODA - CORPORATE BANKING">BANK OF BARODA-CORPORATE BANKING</option> 
									<option class="bothBankOption" value="Bank Of India">BANK OF INDIA</option>
									<option class="bothBankOption" value="Bank of Maharashtra">BANK OF MAHARASHTRA</option>
									
									
									<option class="upihide" value="Bassein Catholic Cooperative Bank Limitedk">
									BASSEIN CATHOLIC COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="Bhagini Nivedita Sahakari Bank Ltd Pune">
								    BHAGINI NIVEDITA SAHAKARI BANK LTD PUNE</option>
									<option class="upihide" value="Bombay Mercantile Cooperative Bank Ltd">
									BOMBAY MERCANTILE COOPERATIVE BANK LTD</option>
									<option class="bothBankOption" value="Canara Bank">CANARA
										BANK</option>
										<option class="bothBankOption" value="Central Bank of India">CENTRAL BANK OF INDIA</option>
									<option class="bothBankOption" value="City Union Bank">CITY UNION BANK</option>
									<option class="bothBankOption" value="CAPITAL SMALL FINANCE BANK LIMITED">CAPITAL SMALL FINANCE BANK LIMITED</option>
									<option class="bothBankOption" value="THE COSMOS CO OPERATIVE BANK LIMITED"> COSMOS BANK </option>
									<option class="upihide" value="Chhattisgarh Rajya Gramin Bank">CHHATTISGARH RAJYA GRAMIN BANK</option>
									<option class="upihide" value="Citi Bank">CITI BANK</option>
									<option class="upihide" value="Citizen Credit Cooperative Bank Limited">
									CITIZEN CREDIT COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="Coastal Local Area Bank Ltd">COASTAL LOCAL AREA BANK LTD</option>
									<option class="bothBankOption" value="DEUSTCHE BANK">DEUSTCHE BANK</option>
									<option class="bothBankOption" value="Dhanlaxmi Bank">DHANALAKSHMI
									BANK</option>
									<option class="upihide" value="Darussalam Co Operative Urban Bank Ltd">
									Darussalam Co Operative Urban Bank Ltd</option>
									<option class="upihide" value="DBS Bank India Limited">DBS BANK INDIA LIMITED</option>
									<option class="upihide" value="Deogiri Nagari Sahakari Bank Ltd. Aurangabad">
									DEOGIRI NAGARI SAHAKARI BANK LTD. AURANGABAD</option>
									<option class="upihide" value="Esaf Small Finance Bank Limited">ESAF SMALL FINANCE BANK LIMITED</option>
									
										
										<option class="bothBankOption" value="Equitas Small finance bank">EQUITAS SMALL FINANCE BANK LIMITED
										</option>
										<option class="bothBankOption" value="FEDERAL BANK">FEDERAL
										BANK </option>
									<option class="upihide" value="Fincare Small Finance Bank Ltd">FINCARE SMALL FINANCE BANK LTD</option>
									<option class="upihide" value="Himachal Pradesh State Cooperative Bank Ltd">
									HIMACHAL PRADESH STATE COOPERATIVE BANK LTD</option>
									<option class="upihide" value="Hutatma Sahakari Bank Ltd">HUTATMA SAHAKARI BANK LTD</option>
									<option class="bothBankOption" value="HDFC Bank">HDFC
										BANK</option>
										<option class="bothBankOption" value="HSBC BANK">HSBC BANK</option>
											<option class="bothBankOption" value="ICICI Bank">ICICI BANK LIMITED</option>
										
										<option class="bothBankOption" value="IDB">IDBI BANK</option>
										
										<option class="bothBankOption" value="IDS">INDUSIND
										BANK</option>
									<option class="bothBankOption" value="IDN">IDFC FIRST BANK</option> 
									<option class="upihide" value="Indian Bank">INDIAN BANK</option>
									<option class="upihide" value="Jammu And Kashmir Bank Limited">JAMMU AND KASHMIR BANK LIMITED</option>
									<option class="upihide" value="Jana Small Finance Bank Ltd">JANA SMALL FINANCE BANK LTD</option>
									<option class="upihide" value="Janakalyan Sahakari Bank Limited">JANAKALYAN SAHAKARI BANK LIMITED</option>
									<option class="upihide" value="Janaseva Sahakari Bank Limited">JANASEVA SAHAKARI BANK LIMITED</option>
									<option class="upihide" value="Janatha Seva Cooperative Bank Ltd">JANATHA SEVA COOPERATIVE BANK LTD</option>
									<option class="upihide" value="Kalupur Commercial Cooperative Bank">KALUPUR COMMERCIAL COOPERATIVE BANK</option>
									<option class="upihide" value="Karnataka Bank Limited">KARNATAKA BANK LIMITED</option>
									<option class="bothBankOption" value="162">KOTAK MAHINDRA BANK LIMITED</option>
									<option class="upihide" value="Karnataka Vikas Grameena Bank">KARNATAKA VIKAS GRAMEENA BANK</option>
									<option class="bothBankOption" value="Lakshmi Vilas Bank">LAXMI VILAS BANK</option>
									<option class="bothBankOption" value="NKGSB Co-op Bank Ltd">NKGSB COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="Maharashtra Gramin Bank">MAHARASHTRA GRAMIN BANK</option>
									<option class="upihide" value="MAHARASHTRA STATE COOPERATIVE BANK">MAHARASHTRA STATE COOPERATIVE BANK</option>
									<option class="upihide" value="Mahesh Sahakari Bank Ltd Pune">MAHESH SAHAKARI BANK LTD PUNE</option>
									<option class="upihide" value="MODEL COOPERATIVE BANK LTD">MODEL COOPERATIVE BANK LTD</option>
									<option class="upihide" value="NORTH EAST SMALL FINANCE BANK LIMITED">NORTH EAST SMALL FINANCE BANK LIMITED</option>
									<option class="upihide" value="NUTAN NAGARIK SAHAKARI BANK LIMITED">NUTAN NAGARIK SAHAKARI BANK LIMITED</option>
									<option class="bothBankOption" value="Punjab National Bank [Retail]">
									PUNJAB NATIONAL BANK-RETAIL BANKING</option>
									<option class="bothBankOption" value="PUNJAB NATIONAL BANK (CORPORATE)">
									PUNJAB NATIONAL BANK-CORPORATE BANKING</option>
										<option class="bothBankOption" value="PUNJAB AND SIND BANK">PUNJAB AND SIND BANK</option>
									<option class="upihide" value="Pavana Sahakari Bank LTD">PAVANA SAHAKARI BANK LTD</option>
									<option class="upihide" value="PRIME COOPERATIVE BANK LIMITED">PRIME COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="RAJARAMBAPU SAHAKARI BANK LIMITED">RAJARAMBAPU SAHAKARI BANK LIMITED</option>
									<option class="upihide" value="RAJARSHI SHAHU SAHAKARI BANK LTD  PUNE">RAJARSHI SHAHU SAHAKARI BANK LTD  PUNE</option>
									<option class="upihide" value="RAJGURUNAGAR SAHAKARI BANK LIMITED">RAJGURUNAGAR SAHAKARI BANK LIMITED</option>
									<option class="upihide" value="RAJKOT NAGRIK SAHAKARI BANK LIMITED">RAJKOT NAGRIK SAHAKARI BANK LIMITED</option>
									<option class="upihide" value="RBL Bank Limited">RBL BANK LIMITED</option>
									<option class="upihide" value="SAMARTH SAHAKARI BANK LTD">SAMARTH SAHAKARI BANK LTD</option>
									<option class="upihide" value="SANT SOPANKAKA SAHAKARI BANK LTD">SANT SOPANKAKA SAHAKARI BANK LTD</option>
									
									<option class="upihide" value="Satara Sahakari Bank Ltd">SATARA SAHAKARI BANK LTD</option>
									<option class="upihide" value="Saurashtra Gramin Bank">SAURASHTRA GRAMIN BANK</option>
								
									<option class="upihide" value="Shivalik Small Finance Bank Limited">SHIVALIK SMALL FINANCE BANK LIMITED</option>
									<option class="upihide" value="Shree Kadi Nagarik Sahakari Bank Limited">SHREE KADI NAGARIK SAHAKARI BANK LIMITED</option>
									<option class="upihide" value="SHRI CHHATRAPATI RAJASHRI SHAHU URBAN COOPERATIVE BANK LIMITED">
									SHRI CHHATRAPATI RAJASHRI SHAHU URBAN COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="Shri Veershaiv Co Op Bank Ltd">SHRI VEERSHAIV CO OP BANK LTD</option>
									<option class="upihide" value="Smriti Nagrik Sahakari Bank Maryadit">SMRITI NAGRIK SAHAKARI BANK MARYADIT</option>
									
									<option class="upihide" value="Sree Charan Souhardha Co Operative Bank Ltd">
									SREE CHARAN SOUHARDHA CO OPERATIVE BANK LTD</option>
									<option class="bothBankOption" value="Standard Chartered Bank">STANDARD
										CHARTERED BANK</option>
										<option class="bothBankOption" value="State Bank of India">STATE
										BANK OF INDIA</option>	
										<option class="bothBankOption" value="Suryoday Small Finance Bank Ltd">SURYODAY SMALL FINANCE BANK LIMITED</option>
										<option class="bothBankOption" value="SIB">SOUTH INDIAN BANK</option>
										<option class="bothBankOption" value="SBM BANK">SBM BANK</option>
										<option class="bothBankOption" value="THE SURAT PEOPLES CO-OPERATIVE BANK LIMITED">THE SURAT PEOPLES CO-OPERATIVE BANK LIMITED</option>
										<option class="bothBankOption" value="THE SUTEX CO-OP BANK LTD">THE SUTEX CO-OP BANK LTD</option>
										<option class="bothBankOption" value="SARASWAT COOPERATIVE BANK LIMITED">SARASWAT COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="Suco Souharda Sahakari Bank Ltd">SUCO SOUHARDA SAHAKARI BANK LTD</option>
									<option class="upihide" value="Surat National Cooperative Bank Limited">SURAT NATIONAL COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="Sutex Cooperative Bank Limited">SUTEX COOPERATIVE BANK LIMITED</option>
									
									<option class="upihide" value="Tamilnad Mercantile Bank Limited">TAMILNAD MERCANTILE BANK LIMITED</option>
									<option class="upihide" value="Telangana State Coop Apex Bank">TELANGANA STATE COOP APEX BANK</option>
									<option class="upihide" value="Textile Traders Co Operative Bank Ltd">TEXTILE TRADERS CO OPERATIVE BANK LTD</option>
									<option class="upihide" value="The A.P. Mahesh Cooperative Urban Bank Limited">
									THE A.P. MAHESH COOPERATIVE URBAN BANK LIMITED</option>
									<option class="upihide" value="The Akola District Central Cooperative Bank">
									THE AKOLA DISTRICT CENTRAL COOPERATIVE BANK</option>
									<option class="upihide" value="TamiThe Banaskantha Mercantile Cooperative Bank Ltd">
								    THE BANASKANTHA MERCANTILE COOPERATIVE BANK LTD</option>
									<option class="upihide" value="The Baramati Sahakari Bank Ltd">THE BARAMATI SAHAKARI BANK LTD</option>
									<option class="upihide" value="The Cosmos Co Operative Bank Limited">THE COSMOS CO OPERATIVE BANK LIMITED</option>
									<option class="upihide" value="The Gadchiroli District Central Cooperative Bank Limited">
									THE GADCHIROLI DISTRICT CENTRAL COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="The Greater Bombay Cooperative Bank Limited">
									THE GREATER BOMBAY COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="The Gujarat State Cooperative Bank Limited">
									THE GUJARAT STATE COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="The Jalgaon Peopels Cooperative Bank Limited">
									THE JALGAON PEOPELS COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="The Kolhapur Urban Co-Op Bank Ltd">THE KOLHAPUR URBAN CO-OP BANK LTD</option>
									<option class="upihide" value="The Malad Sahakari Bank Ltd">THE MALAD SAHAKARI BANK LTD</option>
									<option class="upihide" value="The Municipal Cooperative Bank Limited">THE MUNICIPAL COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="The Nainital Bank Limited">THE NAINITAL BANK LIMITED</option>
									<option class="upihide" value="The Nawanagar Cooperative Bank Ltd">THE NAWANAGAR COOPERATIVE BANK LTD</option>
									<option class="upihide" value="The Rajasthan State Cooperative Bank Limited">
									THE RAJASTHAN STATE COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="The Satara District Central Cooperative Bank Ltd">
									THE SATARA DISTRICT CENTRAL COOPERATIVE BANK LTD</option>
									<option class="upihide" value="The Surat District Cooperative Bank Limited">
									THE SURAT DISTRICT COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="The Tamil Nadu State Apex Cooperative Bank">
									THE TAMIL NADU STATE APEX COOPERATIVE BANK</option>
									<option class="upihide" value="The Thane Bharat Sahakari Bank Limited">
									THE THANE BHARAT SAHAKARI BANK LIMITED</option>
									<option class="upihide" value="The Udaipur Urban Co Operative Bank Ltd">
									THE UDAIPUR URBAN CO OPERATIVE BANK LTD</option>
									<option class="upihide" value="The Vishweshwar Sahakari Bank Limited">THE VISHWESHWAR SAHAKARI BANK LIMITED</option>
				
				<option class="upihide" value="Uttar Pradesh Cooperative Bank Ltd">UTTAR PRADESH COOPERATIVE BANK LTD</option>
								
									<option class="bothBankOption" value="UCO Bank">UCO BANK</option>
										<option class="bothBankOption" value="Union Bank of India">UNION BANK OF INDIA</option>
										<option class="bothBankOption" value="Ujjivan Small Finance Bank Limited">UJJIVAN SMALL FINANCE BANK LIMITED</option>
										<option class="bothBankOption" value="UTKARSH SMALL FINANCE BANK">UTKARSH SMALL FINANCE BANK</option>
										<!-- <option class="bothBankOption" value="Catholic Syrian Bank">CATHOLIC
										SYRIAN BANK</option> -->
											<option class="upihide" value="Vasai Janata Sahakari Bank Ltd">VASAI JANATA SAHAKARI BANK LTD</option>
									<option class="upihide" value="Vasai Vikas Sahakari Bank Limited">VASAI VIKAS SAHAKARI BANK LIMITED</option>
									<option class="bothBankOption" value="YES BANK">YES BANK</option>
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
										<p>UPI<span> (for FD upto 1 lakh)</span></p>
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
In case of any mismatch in bank account number / account holder name from where FD funds are received, refund shall be processed in 10 working days without any interest
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
								<input type="text" pattern="[0-9]*" inputmode="numeric" name="depositAmount" class="sliderAmunt_2"
									id="sdpAmount" autocomplete="off" placeholder="Enter amount"
									value="">
								<div class="errormsg">Please enter the amount</div>
								<a href="#;" class="infored"><img
									src="${pageContext.request.contextPath}/resources/images/errorRed.png"
									alt="errorRed"></a>
								<div class="inputDesc">
									Min <i class="fas fa-rupee"></i> 5,000 &amp; Max <i class="fas fa-rupee"></i> 5,00,00,000
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

						<div class="a_selectblock a_ReInput specialTenor">
							<div class="row4">
								<div class="a_part20 sdptenorPeriod">
									<label for="sdptenor1"> <input type="radio" class="inspectletIgnore"
										name="sdptenor" id="sdptenor1" value="60" >
										<p>60</p> <i></i> <strong class="fas fa-star"></strong>
									</label>
								</div>
								<div class="a_part20 sdptenorPeriod">
									<label for="sdptenor2"> <input type="radio" class="inspectletIgnore"
										name="sdptenor" id="sdptenor2" value="44" checked>
										<p>44</p> <i></i>
									</label>
								</div>
								<!-- <div class="a_part20 sdptenorPeriod">
									<label for="sdptenor3"> <input type="radio" class="inspectletIgnore"
										name="sdptenor" id="sdptenor3" value="39">
										<p>39</p> <i></i>
									</label>
								</div> -->
								<div class="a_part20 sdptenorPeriod">
									<label for="sdptenor4"> <input type="radio" class="inspectletIgnore"
										name="sdptenor" id="sdptenor4" value="30">
										<p>30</p> <i></i>
									</label>
								</div>
								
								<div class="a_part20 sdptenorPeriod">
									<label for="sdptenor5"> <input type="radio" class="inspectletIgnore"
										name="sdptenor" id="sdptenor5" value="22">
										<p>22</p> <i></i>
									</label>
								</div>
								<div class="a_part20 onlyMonth">
                                    <label for="sdptenor6">
                                        <input type="radio" class="inspectletIgnore" name="sdptenor" id="sdptenor6" value="18">
                                        <p>18</p>
                                        <i></i>
                                    </label>
                                </div>
                                
                                <div class="a_part20 onlyMonth">
                                    <label for="sdptenor7">
                                        <input type="radio" class="inspectletIgnore" name="sdptenor" id="sdptenor7" value="15">
                                        <p>15</p>
                                        <i></i>
                                    </label>
                                </div>
                                <div class="a_part20 custmTenor onlyMonth">
                                    <label for="sdptenor8">
                                        <input type="radio" class="inspectletIgnore" name="sdptenor" id="sdptenor8" value="Custom">
                                        <p>Custom</p>
                                        <i></i>
                                    </label>
                                </div>
                                
                                <div class="a_part20 onlySingal">
                                    <label for="sdptenor9">
                                        <input type="radio" class="inspectletIgnore" name="sdptenor" id="sdptenor9" value="19">
                                        <p>19</p>
                                        <i></i>
                                    </label>
                                </div>
                                <div class="a_part20 custmTenor onlySingal">
                                    <label for="sdptenor10">
                                        <input type="radio" class="inspectletIgnore" name="sdptenor" id="sdptenor10" value="Custom">
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

							<%-- <div class="sliderBotDesc statusgrip">
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
							</div> --%>
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
						<p>Disclaimer: Number of deposits appearing in the deposit summary section are including your 1st deposit you would be paying through NetBanking/UPI.</p>
						<p>*The returns are indicative and computed on the basis of the assumption that ROI will not change. Hence, this amount may vary. In practice, interest rate prevailing on date of each deposit will be applicable to that particular deposit.</p>
						<p>* Interest start date will be at transaction date subject to fund realisation in BFL's account from payment gateway. The working days exclude public holidays or Saturday (Second & Fourth) or Sunday.</p>
						<p>*If the date of transaction falls on any banking holiday, then your deposit will be booked on the next working day. Accordingly, there could be a slight delay in your Deposit Receipt. However, your interest start date will be from the date you transfer the funds.</p>
					</div>
					<div class="a_renewFDeposite">
						<div class="a_afterMarurity">
							<strong>Banking Details</strong>

						</div>


						<div class="a_ReInput">
                    <label for="bankName_2">Select Bank Name <a href="#;" class="a_viewPament">View available payment modes</a></label>
                    <select id="sdpbankName" name="sdpbankName" class="bankNameSelect BankVD">
                       <option class="noBankSelected" value="">Bank Name</option>
									<option class="bothBankOption" value="AU Small Finance Bank">AU
										SMALL FINANCE BANK LIMITED</option>
										<option class="bothBankOption" value="Axis Bank">AXIS
										BANK </option>
								 <option class="upihide" value="Abhyudaya Cooperative Bank Limited">
									ABHYUDAYA COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="Ahmedabad Mercantile Cooperative Bank">
									AHMEDABAD MERCANTILE COOPERATIVE BANK</option>
									<option class="upihide" value="Ahmednagar Merchants Co-Op Bank Ltd">
									AHMEDNAGAR MERCHANTS CO-OP BANK LTD</option>
									<option class="upihide" value="Akola Janata Commercial Cooperative Bank">
									AKOLA JANATA COMMERCIAL COOPERATIVE BANK</option>
									<option class="upihide" value="Andhra Pradesh Grameena Vikas Bank">
									ANDHRA PRADESH GRAMEENA VIKAS BANK</option>
									<option class="upihide" value="Andhra Pragathi Grameena Bank">ANDHRA PRAGATHI GRAMEENA BANK</option>
									<option class="upihide" value="Apna Sahakari Bank Limited">APNA SAHAKARI BANK LIMITED
										</option>
									<option class="upihide" value="Arvind Sahakari Bank Ltd">ARVIND SAHAKARI BANK LTD </option>
									
									<option class="upihide" value="Bank Of America">BANK OF AMERICA</option>
									<option class="bothBankOption" value="Bandhan Bank">BANDHAN BANK LIMITED </option>
										<option class="bothBankOption" value="Bank of Baroda Retail">BANK OF BARODA</option>
									<option class="bothBankOption" value="Bank of Baroda Retail">BANK OF BARODA-RETAIL BANKING</option>
									<option class="bothBankOption" value="BANK OF BARODA - CORPORATE BANKING">BANK OF BARODA-CORPORATE BANKING</option> 
									<option class="bothBankOption" value="Bank Of India">BANK OF INDIA</option>
									<option class="bothBankOption" value="Bank of Maharashtra">BANK OF MAHARASHTRA</option>
									
									
									<option class="upihide" value="Bassein Catholic Cooperative Bank Limitedk">
									BASSEIN CATHOLIC COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="Bhagini Nivedita Sahakari Bank Ltd Pune">
								    BHAGINI NIVEDITA SAHAKARI BANK LTD PUNE</option>
									<option class="upihide" value="Bombay Mercantile Cooperative Bank Ltd">
									BOMBAY MERCANTILE COOPERATIVE BANK LTD</option>
									<option class="bothBankOption" value="Canara Bank">CANARA
										BANK</option>
										<option class="bothBankOption" value="Central Bank of India">CENTRAL BANK OF INDIA</option>
									<option class="bothBankOption" value="City Union Bank">CITY UNION BANK</option>
									<option class="bothBankOption" value="CAPITAL SMALL FINANCE BANK LIMITED">CAPITAL SMALL FINANCE BANK LIMITED</option>
									<option class="bothBankOption" value="THE COSMOS CO OPERATIVE BANK LIMITED"> COSMOS BANK </option>
									<option class="upihide" value="Chhattisgarh Rajya Gramin Bank">CHHATTISGARH RAJYA GRAMIN BANK</option>
									<option class="upihide" value="Citi Bank">CITI BANK</option>
									<option class="upihide" value="Citizen Credit Cooperative Bank Limited">
									CITIZEN CREDIT COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="Coastal Local Area Bank Ltd">COASTAL LOCAL AREA BANK LTD</option>
									<option class="bothBankOption" value="DEUSTCHE BANK">DEUSTCHE BANK</option>
									<option class="bothBankOption" value="Dhanlaxmi Bank">DHANALAKSHMI
									BANK</option>
									<option class="upihide" value="Darussalam Co Operative Urban Bank Ltd">
									Darussalam Co Operative Urban Bank Ltd</option>
									<option class="upihide" value="DBS Bank India Limited">DBS BANK INDIA LIMITED</option>
									<option class="upihide" value="Deogiri Nagari Sahakari Bank Ltd. Aurangabad">
									DEOGIRI NAGARI SAHAKARI BANK LTD. AURANGABAD</option>
									<option class="upihide" value="Esaf Small Finance Bank Limited">ESAF SMALL FINANCE BANK LIMITED</option>
								
										
										<option class="bothBankOption" value="Equitas Small finance bank">EQUITAS SMALL FINANCE BANK LIMITED
										</option>
										<option class="bothBankOption" value="FEDERAL BANK">FEDERAL
										BANK </option>
									<option class="upihide" value="Fincare Small Finance Bank Ltd">FINCARE SMALL FINANCE BANK LTD</option>
									<option class="upihide" value="Himachal Pradesh State Cooperative Bank Ltd">
									HIMACHAL PRADESH STATE COOPERATIVE BANK LTD</option>
									<option class="upihide" value="Hutatma Sahakari Bank Ltd">HUTATMA SAHAKARI BANK LTD</option>
									<option class="bothBankOption" value="HDFC Bank">HDFC
										BANK</option>
										<option class="bothBankOption" value="HSBC BANK">HSBC BANK</option>
											<option class="bothBankOption" value="ICICI Bank">ICICI BANK LIMITED</option>
										
										<option class="bothBankOption" value="IDB">IDBI BANK</option>
										
										<option class="bothBankOption" value="IDS">INDUSIND
										BANK</option>
									<option class="bothBankOption" value="IDN">IDFC FIRST BANK</option> 
									<option class="upihide" value="Indian Bank">INDIAN BANK</option>
									<option class="upihide" value="Jammu And Kashmir Bank Limited">JAMMU AND KASHMIR BANK LIMITED</option>
									<option class="upihide" value="Jana Small Finance Bank Ltd">JANA SMALL FINANCE BANK LTD</option>
									<option class="upihide" value="Janakalyan Sahakari Bank Limited">JANAKALYAN SAHAKARI BANK LIMITED</option>
									<option class="upihide" value="Janaseva Sahakari Bank Limited">JANASEVA SAHAKARI BANK LIMITED</option>
									<option class="upihide" value="Janatha Seva Cooperative Bank Ltd">JANATHA SEVA COOPERATIVE BANK LTD</option>
									<option class="upihide" value="Kalupur Commercial Cooperative Bank">KALUPUR COMMERCIAL COOPERATIVE BANK</option>
									<option class="upihide" value="Karnataka Bank Limited">KARNATAKA BANK LIMITED</option>
									<option class="bothBankOption" value="162">KOTAK MAHINDRA BANK LIMITED</option>
									<option class="upihide" value="Karnataka Vikas Grameena Bank">KARNATAKA VIKAS GRAMEENA BANK</option>
									<option class="bothBankOption" value="Lakshmi Vilas Bank">LAXMI VILAS BANK</option>
									<option class="bothBankOption" value="NKGSB Co-op Bank Ltd">NKGSB COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="Maharashtra Gramin Bank">MAHARASHTRA GRAMIN BANK</option>
									<option class="upihide" value="MAHARASHTRA STATE COOPERATIVE BANK">MAHARASHTRA STATE COOPERATIVE BANK</option>
									<option class="upihide" value="Mahesh Sahakari Bank Ltd Pune">MAHESH SAHAKARI BANK LTD PUNE</option>
									<option class="upihide" value="MODEL COOPERATIVE BANK LTD">MODEL COOPERATIVE BANK LTD</option>
									<option class="upihide" value="NORTH EAST SMALL FINANCE BANK LIMITED">NORTH EAST SMALL FINANCE BANK LIMITED</option>
									<option class="upihide" value="NUTAN NAGARIK SAHAKARI BANK LIMITED">NUTAN NAGARIK SAHAKARI BANK LIMITED</option>
									<option class="bothBankOption" value="Punjab National Bank [Retail]">
									PUNJAB NATIONAL BANK-RETAIL BANKING</option>
									<option class="bothBankOption" value="PUNJAB NATIONAL BANK (CORPORATE)">
									PUNJAB NATIONAL BANK-CORPORATE BANKING</option>
										<option class="bothBankOption" value="PUNJAB AND SIND BANK">PUNJAB AND SIND BANK</option>
									<option class="upihide" value="Pavana Sahakari Bank LTD">PAVANA SAHAKARI BANK LTD</option>
									<option class="upihide" value="PRIME COOPERATIVE BANK LIMITED">PRIME COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="RAJARAMBAPU SAHAKARI BANK LIMITED">RAJARAMBAPU SAHAKARI BANK LIMITED</option>
									<option class="upihide" value="RAJARSHI SHAHU SAHAKARI BANK LTD  PUNE">RAJARSHI SHAHU SAHAKARI BANK LTD  PUNE</option>
									<option class="upihide" value="RAJGURUNAGAR SAHAKARI BANK LIMITED">RAJGURUNAGAR SAHAKARI BANK LIMITED</option>
									<option class="upihide" value="RAJKOT NAGRIK SAHAKARI BANK LIMITED">RAJKOT NAGRIK SAHAKARI BANK LIMITED</option>
									<option class="upihide" value="RBL Bank Limited">RBL BANK LIMITED</option>
									<option class="upihide" value="SAMARTH SAHAKARI BANK LTD">SAMARTH SAHAKARI BANK LTD</option>
									<option class="upihide" value="SANT SOPANKAKA SAHAKARI BANK LTD">SANT SOPANKAKA SAHAKARI BANK LTD</option>
									
									<option class="upihide" value="Satara Sahakari Bank Ltd">SATARA SAHAKARI BANK LTD</option>
									<option class="upihide" value="Saurashtra Gramin Bank">SAURASHTRA GRAMIN BANK</option>
								
									<option class="upihide" value="Shivalik Small Finance Bank Limited">SHIVALIK SMALL FINANCE BANK LIMITED</option>
									<option class="upihide" value="Shree Kadi Nagarik Sahakari Bank Limited">SHREE KADI NAGARIK SAHAKARI BANK LIMITED</option>
									<option class="upihide" value="SHRI CHHATRAPATI RAJASHRI SHAHU URBAN COOPERATIVE BANK LIMITED">
									SHRI CHHATRAPATI RAJASHRI SHAHU URBAN COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="Shri Veershaiv Co Op Bank Ltd">SHRI VEERSHAIV CO OP BANK LTD</option>
									<option class="upihide" value="Smriti Nagrik Sahakari Bank Maryadit">SMRITI NAGRIK SAHAKARI BANK MARYADIT</option>
									
									<option class="upihide" value="Sree Charan Souhardha Co Operative Bank Ltd">
									SREE CHARAN SOUHARDHA CO OPERATIVE BANK LTD</option>
									<option class="bothBankOption" value="Standard Chartered Bank">STANDARD
										CHARTERED BANK</option>
										<option class="bothBankOption" value="State Bank of India">STATE
										BANK OF INDIA</option>	
										<option class="bothBankOption" value="Suryoday Small Finance Bank Ltd">SURYODAY SMALL FINANCE BANK LIMITED</option>
										<option class="bothBankOption" value="SIB">SOUTH INDIAN BANK</option>
										<option class="bothBankOption" value="SBM BANK">SBM BANK</option>
										<option class="bothBankOption" value="THE SURAT PEOPLES CO-OPERATIVE BANK LIMITED">THE SURAT PEOPLES CO-OPERATIVE BANK LIMITED</option>
										<option class="bothBankOption" value="THE SUTEX CO-OP BANK LTD">THE SUTEX CO-OP BANK LTD</option>
										<option class="bothBankOption" value="SARASWAT COOPERATIVE BANK LIMITED">SARASWAT COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="Suco Souharda Sahakari Bank Ltd">SUCO SOUHARDA SAHAKARI BANK LTD</option>
									<option class="upihide" value="Surat National Cooperative Bank Limited">SURAT NATIONAL COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="Sutex Cooperative Bank Limited">SUTEX COOPERATIVE BANK LIMITED</option>
									
									<option class="upihide" value="Tamilnad Mercantile Bank Limited">TAMILNAD MERCANTILE BANK LIMITED</option>
									<option class="upihide" value="Telangana State Coop Apex Bank">TELANGANA STATE COOP APEX BANK</option>
									<option class="upihide" value="Textile Traders Co Operative Bank Ltd">TEXTILE TRADERS CO OPERATIVE BANK LTD</option>
									<option class="upihide" value="The A.P. Mahesh Cooperative Urban Bank Limited">
									THE A.P. MAHESH COOPERATIVE URBAN BANK LIMITED</option>
									<option class="upihide" value="The Akola District Central Cooperative Bank">
									THE AKOLA DISTRICT CENTRAL COOPERATIVE BANK</option>
									<option class="upihide" value="TamiThe Banaskantha Mercantile Cooperative Bank Ltd">
								    THE BANASKANTHA MERCANTILE COOPERATIVE BANK LTD</option>
									<option class="upihide" value="The Baramati Sahakari Bank Ltd">THE BARAMATI SAHAKARI BANK LTD</option>
									<option class="upihide" value="The Cosmos Co Operative Bank Limited">THE COSMOS CO OPERATIVE BANK LIMITED</option>
									<option class="upihide" value="The Gadchiroli District Central Cooperative Bank Limited">
									THE GADCHIROLI DISTRICT CENTRAL COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="The Greater Bombay Cooperative Bank Limited">
									THE GREATER BOMBAY COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="The Gujarat State Cooperative Bank Limited">
									THE GUJARAT STATE COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="The Jalgaon Peopels Cooperative Bank Limited">
									THE JALGAON PEOPELS COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="The Kolhapur Urban Co-Op Bank Ltd">THE KOLHAPUR URBAN CO-OP BANK LTD</option>
									<option class="upihide" value="The Malad Sahakari Bank Ltd">THE MALAD SAHAKARI BANK LTD</option>
									<option class="upihide" value="The Municipal Cooperative Bank Limited">THE MUNICIPAL COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="The Nainital Bank Limited">THE NAINITAL BANK LIMITED</option>
									<option class="upihide" value="The Nawanagar Cooperative Bank Ltd">THE NAWANAGAR COOPERATIVE BANK LTD</option>
									<option class="upihide" value="The Rajasthan State Cooperative Bank Limited">
									THE RAJASTHAN STATE COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="The Satara District Central Cooperative Bank Ltd">
									THE SATARA DISTRICT CENTRAL COOPERATIVE BANK LTD</option>
									<option class="upihide" value="The Surat District Cooperative Bank Limited">
									THE SURAT DISTRICT COOPERATIVE BANK LIMITED</option>
									<option class="upihide" value="The Tamil Nadu State Apex Cooperative Bank">
									THE TAMIL NADU STATE APEX COOPERATIVE BANK</option>
									<option class="upihide" value="The Thane Bharat Sahakari Bank Limited">
									THE THANE BHARAT SAHAKARI BANK LIMITED</option>
									<option class="upihide" value="The Udaipur Urban Co Operative Bank Ltd">
									THE UDAIPUR URBAN CO OPERATIVE BANK LTD</option>
									<option class="upihide" value="The Vishweshwar Sahakari Bank Limited">THE VISHWESHWAR SAHAKARI BANK LIMITED</option>
				
				<option class="upihide" value="Uttar Pradesh Cooperative Bank Ltd">UTTAR PRADESH COOPERATIVE BANK LTD</option>
								
									<option class="bothBankOption" value="UCO Bank">UCO BANK</option>
										<option class="bothBankOption" value="Union Bank of India">UNION BANK OF INDIA</option>
										<option class="bothBankOption" value="Ujjivan Small Finance Bank Limited">UJJIVAN SMALL FINANCE BANK LIMITED</option>
										<option class="bothBankOption" value="UTKARSH SMALL FINANCE BANK">UTKARSH SMALL FINANCE BANK</option>
										<!-- <option class="bothBankOption" value="Catholic Syrian Bank">CATHOLIC
										SYRIAN BANK</option> -->
											<option class="upihide" value="Vasai Janata Sahakari Bank Ltd">VASAI JANATA SAHAKARI BANK LTD</option>
									<option class="upihide" value="Vasai Vikas Sahakari Bank Limited">VASAI VIKAS SAHAKARI BANK LIMITED</option>
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
                            <p>UPI<span> (for FD upto 1 lakh)</span></p>
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
In case of any mismatch in bank account number / account holder name from where SDP funds are received, refund shall be processed in 10 working days without any interest
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

				<div  class="height-10">
            <p class="tctext">I undertake to inform company any change in status of my nationality or tax residence. I am making investment from my Indian resident Individual Savings bank account. I/We confirm that I/we have read and understood the detailed terms and conditions annexed to this Application including the interest rate and other charges. I have gone through the financials and other statements/particulars/representations furnished/made by the company and after careful consideration I am making the deposit with the company at my own risk and volition. I have read and agree to the  <a  class="otpTncPdf" href="${pageContext.request.contextPath}/resources/pdf/TCs-RI.pdf" target="_blank">Terms & Conditions</a></p>
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
				Valid for deposits up to Rs.5 crore (w.e.f. <%=resource.getString("date")%>)
			</h2>
			<strong>For Non Senior Citizens</strong>
			
			
			<table >
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
									<td>15</td>
									<td><%=resource.getString("special_15_maturity_annually")%>%</td>
									<td><%=resource.getString("special_15_monthly")%>%</td>
									<td><%=resource.getString("special_15_quarterly")%>%</td>
									<td><%=resource.getString("special_15_halfyearly")%>%</td>
									<td><%=resource.getString("special_15_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>18</td>
									<td><%=resource.getString("special_18_maturity_annually")%>%</td>
									<td><%=resource.getString("special_18_monthly")%>%</td>
									<td><%=resource.getString("special_18_quarterly")%>%</td>
									<td><%=resource.getString("special_18_halfyearly")%>%</td>
									<td><%=resource.getString("special_18_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>22</td>
									<td><%=resource.getString("special_22_maturity_annually")%>%</td>
									<td><%=resource.getString("special_22_monthly")%>%</td>
									<td><%=resource.getString("special_22_quarterly")%>%</td>
									<td><%=resource.getString("special_22_halfyearly")%>%</td>
									<td><%=resource.getString("special_22_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>30</td>
									<td><%=resource.getString("special_30_maturity_annually")%>%</td>
									<td><%=resource.getString("special_30_monthly")%>%</td>
									<td><%=resource.getString("special_30_quarterly")%>%</td>
									<td><%=resource.getString("special_30_halfyearly")%>%</td>
									<td><%=resource.getString("special_30_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>33</td>
									<td><%=resource.getString("special_33_maturity_annually")%>%</td>
									<td><%=resource.getString("special_33_monthly")%>%</td>
									<td><%=resource.getString("special_33_quarterly")%>%</td>
									<td><%=resource.getString("special_33_halfyearly")%>%</td>
									<td><%=resource.getString("special_33_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>44</td>
									<td><%=resource.getString("special_44_maturity_annually")%>%</td>
									<td><%=resource.getString("special_44_monthly")%>%</td>
									<td><%=resource.getString("special_44_quarterly")%>%</td>
									<td><%=resource.getString("special_44_halfyearly")%>%</td>
									<td><%=resource.getString("special_44_maturity_annually")%>%</td>
								</tr>
                </tbody>
            </table>

			
			
			<table class="taboveSpace">
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
                   					<td>12 &ndash; 14</td>
                     				<td><%=resource.getString("regular_12to14_maturity_annually")%>%</td>
									<td><%=resource.getString("regular_12to14_monthly")%>%</td>
									<td><%=resource.getString("regular_12to14_quarterly")%>%</td>
									<td><%=resource.getString("regular_12to14_halfyearly")%>%</td>
									<td><%=resource.getString("regular_12to14_maturity_annually")%>%</td>
                  </tr>
                  <tr>
                   					<td>>15 &ndash; 23</td>
                     				<td><%=resource.getString("regular_15to23_maturity_annually")%>%</td>
									<td><%=resource.getString("regular_15to23_monthly")%>%</td>
									<td><%=resource.getString("regular_15to23_quarterly")%>%</td>
									<td><%=resource.getString("regular_15to23_halfyearly")%>%</td>
									<td><%=resource.getString("regular_15to23_maturity_annually")%>%</td>
                  </tr>
                  <tr>
                   					<td>24</td>
                     				<td><%=resource.getString("regular_24_maturity_annually")%>%</td>
									<td><%=resource.getString("regular_24_monthly")%>%</td>
									<td><%=resource.getString("regular_24_quarterly")%>%</td>
									<td><%=resource.getString("regular_24_halfyearly")%>%</td>
									<td><%=resource.getString("regular_24_maturity_annually")%>%</td>
                  </tr>
                  <tr>
                     				<td>25&ndash;35</td>
                     				<td><%=resource.getString("regular_25to35_maturity_annually")%>%</td>
									<td><%=resource.getString("regular_25to35_monthly")%>%</td>
									<td><%=resource.getString("regular_25to35_quarterly")%>%</td>
									<td><%=resource.getString("regular_25to35_halfyearly")%>%</td>
									<td><%=resource.getString("regular_25to35_maturity_annually")%>%</td>
                  </tr>
                  <tr>
                     				<td>36&ndash;60</td>
                     				<td><%=resource.getString("regular_36to60_maturity_annually")%>%</td>
									<td><%=resource.getString("regular_36to60_monthly")%>%</td>
									<td><%=resource.getString("regular_36to60_quarterly")%>%</td>
									<td><%=resource.getString("regular_36to60_halfyearly")%>%</td>
									<td><%=resource.getString("regular_36to60_maturity_annually")%>%</td>
                  </tr>
				</tbody>
			</table>


			<strong class="taboveSpace">For Senior citizens</strong>
            <table >
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
									<td>15</td>
									<td><%=resource.getString("special_sen_15_maturity_annually")%>%</td>
									<td><%=resource.getString("special_sen_15_monthly")%>%</td>
									<td><%=resource.getString("special_sen_15_quarterly")%>%</td>
									<td><%=resource.getString("special_sen_15_halfyearly")%>%</td>
									<td><%=resource.getString("special_sen_15_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>18</td>
									<td><%=resource.getString("special_sen_18_maturity_annually")%>%</td>
									<td><%=resource.getString("special_sen_18_monthly")%>%</td>
									<td><%=resource.getString("special_sen_18_quarterly")%>%</td>
									<td><%=resource.getString("special_sen_18_halfyearly")%>%</td>
									<td><%=resource.getString("special_sen_18_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>22</td>
									<td><%=resource.getString("special_sen_22_maturity_annually")%>%</td>
									<td><%=resource.getString("special_sen_22_monthly")%>%</td>
									<td><%=resource.getString("special_sen_22_quarterly")%>%</td>
									<td><%=resource.getString("special_sen_22_halfyearly")%>%</td>
									<td><%=resource.getString("special_sen_22_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>30</td>
									<td><%=resource.getString("special_sen_30_maturity_annually")%>%</td>
									<td><%=resource.getString("special_sen_30_monthly")%>%</td>
									<td><%=resource.getString("special_sen_30_quarterly")%>%</td>
									<td><%=resource.getString("special_sen_30_halfyearly")%>%</td>
									<td><%=resource.getString("special_sen_30_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>33</td>
									<td><%=resource.getString("special_sen_33_maturity_annually")%>%</td>
									<td><%=resource.getString("special_sen_33_monthly")%>%</td>
									<td><%=resource.getString("special_sen_33_quarterly")%>%</td>
									<td><%=resource.getString("special_sen_33_halfyearly")%>%</td>
									<td><%=resource.getString("special_sen_33_maturity_annually")%>%</td>
								</tr>
								<tr>
									<td>44</td>
									<td><%=resource.getString("special_sen_44_maturity_annually")%>%</td>
									<td><%=resource.getString("special_sen_44_monthly")%>%</td>
									<td><%=resource.getString("special_sen_44_quarterly")%>%</td>
									<td><%=resource.getString("special_sen_44_halfyearly")%>%</td>
									<td><%=resource.getString("special_sen_44_maturity_annually")%>%</td>
								</tr>
                </tbody>
            </table>
            
            
            <table class="taboveSpace">
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
					 				<td>12 &ndash; 14</td>
					 				<td><%=resource.getString("regular_sen_12to14_maturity_annually")%>%</td>
									<td><%=resource.getString("regular_sen_12to14_monthly")%>%</td>
									<td><%=resource.getString("regular_sen_12to14_quarterly")%>%</td>
									<td><%=resource.getString("regular_sen_12to14_halfyearly")%>%</td>
									<td><%=resource.getString("regular_sen_12to14_maturity_annually")%>%</td>
					</tr>
					<tr>
					 				<td>>15 &ndash; 23</td>
					 				<td><%=resource.getString("regular_sen_15to23_maturity_annually")%>%</td>
									<td><%=resource.getString("regular_sen_15to23_monthly")%>%</td>
									<td><%=resource.getString("regular_sen_15to23_quarterly")%>%</td>
									<td><%=resource.getString("regular_sen_15to23_halfyearly")%>%</td>
									<td><%=resource.getString("regular_sen_15to23_maturity_annually")%>%</td>
					</tr>
					<tr>
					 				<td>24</td>
					 				<td><%=resource.getString("regular_sen_24_maturity_annually")%>%</td>
									<td><%=resource.getString("regular_sen_24_monthly")%>%</td>
									<td><%=resource.getString("regular_sen_24_quarterly")%>%</td>
									<td><%=resource.getString("regular_sen_24_halfyearly")%>%</td>
									<td><%=resource.getString("regular_sen_24_maturity_annually")%>%</td>
					</tr>
					<tr>
                     				<td>25 &ndash; 35</td>
                     				<td><%=resource.getString("regular_sen_25to35_maturity_annually")%>%</td>
									<td><%=resource.getString("regular_sen_25to35_monthly")%>%</td>
									<td><%=resource.getString("regular_sen_25to35_quarterly")%>%</td>
									<td><%=resource.getString("regular_sen_25to35_halfyearly")%>%</td>
									<td><%=resource.getString("regular_sen_25to35_maturity_annually")%>%</td>
                  </tr>
                  <tr>
                     				<td>36 &ndash; 60</td>
                     				<td><%=resource.getString("regular_sen_36to60_maturity_annually")%>%</td>
									<td><%=resource.getString("regular_sen_36to60_monthly")%>%</td>
									<td><%=resource.getString("regular_sen_36to60_quarterly")%>%</td>
									<td><%=resource.getString("regular_sen_36to60_halfyearly")%>%</td>
									<td><%=resource.getString("regular_sen_36to60_maturity_annually")%>%</td>
                  </tr>
				</tbody>
			</table>
		</div>


		<div class="a_termsAndConPart_2-1">
			<a href="#;" class="a_closePop"><i class="fas fa-times"></i></a>
			<h2>
				<p>Annualized Rate of Interest</p>
				Valid for deposits up to Rs.5 crore (w.e.f. <%=resource.getString("date")%>)
			</h2>
			<table border="1">
				<tbody>
					<tr>
						<th class="text-center" rowspan="2">Tenor in months</th>
						<th class="text-center" colspan="3">Cumulative rates of
							interest</th>
					</tr>
					<tr>
						<th>For Senior citizens</th>
						<th>For Non Senior Citizens</th>
					</tr>
					<tr>
						<td>12 - 14</td>
						<td><%=resource.getString("regular_sen_sdp_12to14_maturity")%>%</td>
						<td><%=resource.getString("regular_sdp_12to14_maturity")%>%</td>
					</tr>
					<tr>
						<td>>15 - 23</td>
						<td><%=resource.getString("regular_sen_sdp_15to23_maturity")%>%</td>
						<td><%=resource.getString("regular_sdp_15to23_maturity")%>%</td>
					</tr>
					<tr>
						<td>24</td>
						<td><%=resource.getString("regular_sen_sdp_24_maturity")%>%</td>
						<td><%=resource.getString("regular_sdp_24_maturity")%>%</td>
					</tr>
					<tr>
						<td>25 - 35</td>
						<td><%=resource.getString("regular_sen_sdp_25to35_maturity")%>%</td>
						<td><%=resource.getString("regular_sdp_25to35_maturity")%>%</td>
					</tr>
					<tr>
						<td>36 - 60</td>
						<td><%=resource.getString("regular_sen_sdp_36to60_maturity")%>%</td>
						<td><%=resource.getString("regular_sdp_36to60_maturity")%>%</td>
					</tr>
				</tbody>
			</table>
			
			
			 <table border="1" class="taboveSpace">
                <tbody>
                    <tr>
                        <th class="text-center" rowspan="2">Tenor in months</th>
                        <th class="text-center" colspan="3">Cumulative rates of interest</th>
                    </tr>
                    <tr>
                        <th>For Senior citizens</th>
                        <th>For Non Senior Citizens</th>
                    </tr>
                    <tr>
									<td>15</td>
									<td><%=resource.getString("special_sen_sdp_15_maturity")%>%</td>
									<td><%=resource.getString("special_sdp_15_maturity")%>%</td>
								</tr>
								<tr>
									<td>18</td>
									<td><%=resource.getString("special_sen_sdp_18_maturity")%>%</td>
									<td><%=resource.getString("special_sdp_18_maturity")%>%</td>
								</tr>
								<tr>
									<td>22</td>
									<td><%=resource.getString("special_sen_sdp_22_maturity")%>%</td>
									<td><%=resource.getString("special_sdp_22_maturity")%>%</td>
								</tr>
								<tr>
									<td>30</td>
									<td><%=resource.getString("special_sen_sdp_30_maturity")%>%</td>
									<td><%=resource.getString("special_sdp_30_maturity")%>%</td>
								</tr>
								<tr>
									<td>33</td>
									<td><%=resource.getString("special_sen_sdp_33_maturity")%>%</td>
									<td><%=resource.getString("special_sdp_33_maturity")%>%</td>
								</tr>
								<tr>
									<td>44</td>
									<td><%=resource.getString("special_sen_sdp_44_maturity")%>%</td>
									<td><%=resource.getString("special_sdp_44_maturity")%>%</td>
								</tr>
                </tbody>
            </table>

		</div>
	</div>
	

<div class="a_termsAndConPart_4">
		<a href="#;" class="a_closePop"><i class="fas fa-times"></i></a>
		<table border="1">
			<tr>
				<th>Bank Name</th>
				<th>Available Payment Modes</th>
			</tr>
						<tr>
				<td>AU SMALL FINANCE BANK LIMITED</td>
				<td>Net Banking , UPI</td>
			</tr>
			
			<tr>
				<td>AXIS BANK</td>
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
				<td>AHMEDNAGAR MERCHANTS CO-OP BANK LTD</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>AKOLA JANATA COMMERCIAL COOPERATIVE BANK</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>ANDHRA PRADESH GRAMEENA VIKAS BANK</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>ANDHRA PRAGATHI GRAMEENA BANK</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>APNA SAHAKARI BANK LIMITED</td>
				<td>UPI</td>
			</tr>

			<tr>
				<td>ARVIND SAHAKARI BANK LTD</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>BANDHAN BANK LIMITED</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>BANK OF BARODA-RETAIL BANKING</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>BANK OF BARODA-CORPORATE BANKING</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>BANK OF INDIA</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>BANK OF MAHARASHTRA</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>BANK OF AMERICA</td>
				<td>UPI</td>
			</tr>
		
			<tr>
				<td>BASSEIN CATHOLIC COOPERATIVE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>BHAGINI NIVEDITA SAHAKARI BANK LTD PUNE</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>BOMBAY MERCANTILE COOPERATIVE BANK LTD</td>
				<td>UPI</td>
			</tr>
				<tr>
				<td>CANARA BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>CENTRAL BANK OF INDIA</td>
				<td>Net Banking , UPI</td>
			</tr>
			
			<tr>
				<td>CITY UNION BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>CAPITAL SMALL FINANCE BANK LIMITED</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>COSMOS BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>CHHATTISGARH RAJYA GRAMIN BANK</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>CITI BANK</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>CITIZEN CREDIT COOPERATIVE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>COASTAL LOCAL AREA BANK LTD</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>DEUSTCHE BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>DHANLAXMI BANK</td>
				<td>Net Banking ,UPI</td>
			</tr>
			
			
			
			<tr>
				<td>DARUSSALAM CO OPERATIVE URBAN BANK LTD</td>
				<td>UPI</td>
			</tr>

			<tr>
				<td>DBS BANK INDIA LIMITED</td>
				<td>UPI</td>
			</tr>
			
			
			<tr>
				<td>DEOGIRI NAGARI SAHAKARI BANK LTD. AURANGABAD</td>
				<td>UPI</td>
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
				<td>Net Banking , UPI</td>
			</tr>
			
			
			
			<tr>
				<td>FINCARE SMALL FINANCE BANK LTD</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>HDFC BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>HSBC BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>HIMACHAL PRADESH STATE COOPERATIVE BANK LTD</td>
				<td>UPI</td>
			</tr>
		
			<tr>
				<td>HUTATMA SAHAKARI BANK LTD</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>ICICI BANK LIMITED</td>
				<td>Net Banking , UPI</td>
			</tr>
			 <tr>
				<td>IDFC FRIST BANK LTD</td>
				<td>Net Banking , UPI</td>
			</tr> 
			
			<tr>
				<td>IDBI BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			
			<tr>
				<td>INDUSLND BANK</td>
				<td>Net Banking ,UPI</td>
			</tr>
			<tr>
				<td>INDIAN BANK</td>
				<td>UPI</td>
			</tr>
			
			
			<tr>
				<td>JAMMU AND KASHMIR BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>JANA SMALL FINANCE BANK LTD</td>
				<td>UPI</td>
			</tr>

			<tr>
				<td>JANAKALYAN SAHAKARI BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>JANASEVA SAHAKARI BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>JANATHA SEVA COOPERATIVE BANK LTD</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>KARUR VYSYA BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			 <tr>
				<td>KOTAK MAHINDRA BANK</td>
				<td>Net Banking , UPI</td>
			</tr> 
			<tr>
				<td>KERALA GRAMIN BANK</td>
				<td>Net Banking , UPI</td>
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
				<td>LAKSHMI VILAS BANK</td>
				<td>Net Banking ,UPI</td>
			</tr>
			
			<tr>
				<td>NKGSB COOPERATIVE BANK LIMITED</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>MAHARASHTRA GRAMIN BANK</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>MAHARASHTRA STATE COOPERATIVE BANK</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>MAHESH SAHAKARI BANK LTD PUNE</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>MODEL COOPERATIVE BANK LTD</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>NORTH EAST SMALL FINANCE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>NUTAN NAGARIK SAHAKARI BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>PUNJAB NATIONAL BANK - RETAIL BANKING</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>PUNJAB NATIONAL BANK-CORPORATE BANKING</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>PUNJAB AND SIND BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>PAVANA SAHAKARI BANK LTD</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>PRIME COOPERATIVE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			
			
			<tr>
				<td>RAJARAMBAPU SAHAKARI BANK LIMITED</td>
				<td>UPI</td>
			</tr>

			<tr>
				<td>RAJARSHI SHAHU SAHAKARI BANK LTD  PUNE</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>RAJGURUNAGAR SAHAKARI BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>RBL BANK LIMITED</td>
				<td>UPI</td>
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
				<td>SURYODAY SMALL FINANCE BANK LIMITED</td>
				<td>Net Banking , UPI</td>
			</tr>
			
			<tr>
				<td>SOUTH INDIAN BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
		<tr>
				<td>SBM BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>SAMARTH SAHAKARI BANK LTD</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>SANT SOPANKAKA SAHAKARI BANK LTD</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>SATARA SAHAKARI BANK LTD</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>SAURASHTRA GRAMIN BANK</td>
				<td>UPI</td>
			</tr>
		
			<tr>
				<td>SHIVALIK SMALL FINANCE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>SHREE KADI NAGARIK SAHAKARI BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>SHRI CHHATRAPATI RAJASHRI SHAHU URBAN COOPERATIVE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>SHRI VEERSHAIV CO OP BANK LTD</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>SMRITI NAGRIK SAHAKARI BANK MARYADIT</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>SREE CHARAN SOUHARDHA CO OPERATIVE BANK LTD</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>SUCO SOUHARDA SAHAKARI BANK LTD</td>
				<td>UPI</td>
			</tr>
				
			<tr>
				<td>SARASWAT COOPERATIVE BANK LIMITED</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>SURAT NATIONAL COOPERATIVE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>SUTEX COOPERATIVE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>THE SURAT PEOPLES CO-OPERATIVE BANK LIMITED</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>THE SUTEX CO-OP BANK LTD</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>TAMILNAD MERCANTILE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>TELANGANA STATE COOP APEX BANK</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>TEXTILE TRADERS CO OPERATIVE BANK LTD</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>THE A.P. MAHESH COOPERATIVE URBAN BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>THE BANASKANTHA MERCANTILE COOPERATIVE BANK LTD</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>THE BARAMATI SAHAKARI BANK LTD</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>THE COSMOS CO OPERATIVE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>SBM BANK INDIA LIMITED</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>THE GADCHIROLI DISTRICT CENTRAL COOPERATIVE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>THE GREATER BOMBAY COOPERATIVE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>THE JALGAON PEOPELS COOPERATIVE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>THE KOLHAPUR URBAN CO-OP BANK LTD</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>THE MALAD SAHAKARI BANK LTD</td>
				<td>UPI</td>
			</tr>
			
				
			<tr>
				<td>THE MUNICIPAL COOPERATIVE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>THE NAINITAL BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>THE NAWANAGAR COOPERATIVE BANK LTD</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>THE RAJASTHAN STATE COOPERATIVE BANK LIMITED</td>
				<td>UPI</td>
			</tr>

			<tr>
				<td>THE SATARA DISTRICT CENTRAL COOPERATIVE BANK LTD</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>THE SURAT DISTRICT COOPERATIVE BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>THE TAMIL NADU STATE APEX COOPERATIVE BANK</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>THE THANE BHARAT SAHAKARI BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>THE UDAIPUR URBAN CO OPERATIVE BANK LTD</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>THE VISHWESHWAR SAHAKARI BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>UCO BANK</td>
				<td>Net Banking ,UPI</td>
			</tr>
			<tr>
				<td>UNION BANK OF INDIA</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>UJJIVAN SMALL FINANCE BANK LIMITED</td>
				<td>Net Banking , UPI</td>
			</tr>
			
			<tr>
				<td>UTKARSH SMALL FINANCE BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			<tr>
				<td>UTTAR PRADESH COOPERATIVE BANK LTD</td>
				<td>UPI</td>
			</tr>
			
			<tr>
				<td>VASAI JANATA SAHAKARI BANK LTD</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>VASAI VIKAS SAHAKARI BANK LIMITED</td>
				<td>UPI</td>
			</tr>
			<tr>
				<td>YES BANK</td>
				<td>Net Banking , UPI</td>
			</tr>
			
			
		</table>
	</div>
	
	
	<div class="fdbilldesk" style="display: none;">
		<form
			action="https://payment.bajajfinserv.in/Payments/FD_Payment.aspx"
			method="POST" id="fdbilldesk">
			<input type="text" name="msg" value="" id="fdmsg"> 
			<input type="submit" value="Submit">
		</form>
	</div>


	<div class="instaformClass" style="display: none;">
		<form name="instaform" id="instaform"
			action="https://bfin.in/AadhaarOkyc/OkycProcess.aspx" method="POST">
			<input type="hidden" name="Request" id="RequestnewValue" value="">
			<button type="submit" name="save"></button>
		</form>
	</div>
	
		<div class="ErrorPage">
    <header class="mainHeader">
        <div class="container">
            <div class="logoName">
                    <a href="#;">
            		<img src="${pageContext.request.contextPath}/resources/newImages/bajaj_logo_png.png" alt=""> 
					</a>                
            </div>
        </div>
    </header>
    <div class="mainSection">
        <div class="container">
            
            <div class="leftPart">
                <div class="bluePart" data-gtm-vis-recent-on-screen-6790938_3816="2540772" data-gtm-vis-first-on-screen-6790938_3816="2540772" data-gtm-vis-total-visible-time-6790938_3816="100" data-gtm-vis-has-fired-6790938_3816="1">
                    <img src="${pageContext.request.contextPath}/resources/newImages/uh_oh.svg" alt="" loading="lazy">
                    <strong>Oh no!</strong>
                    <p>We had a technical hiccup, We expect to be back soon. Thanks for your Patience</p>
                </div> 
                <div class="buttonPart_1">
                    <button class="active">Retry</button>
                </div>                               
            </div>
        </div>
    </div>
</div>
	
<div class="SessionErrorPage">
    <header class="mainHeader">
        <div class="container">
            <div class="logoName">
                    <a href="#;">
            		<img src="${pageContext.request.contextPath}/resources/newImages/bajaj_logo_png.png" alt=""> 
					</a>                
            </div>
        </div>
    </header>
    <div class="mainSection">
        <div class="container">
            
            <div class="leftPart">
                <div class="bluePart" data-gtm-vis-recent-on-screen-6790938_3816="2540772" data-gtm-vis-first-on-screen-6790938_3816="2540772" data-gtm-vis-total-visible-time-6790938_3816="100" data-gtm-vis-has-fired-6790938_3816="1">
                    <img src="${pageContext.request.contextPath}/resources/newImages/uh_oh.png" alt="" loading="lazy">
                   
                </div> 
                <div class="buttonPart_1">
                    <button class="active">Retry</button>
                </div>                               
            </div>
        </div>
    </div>
</div>	
	
	<div class="loadoverlay">
<div class="loader">Loading...</div>
<div class="slidermain">
<div class="loaderslide">
<div>Hang on for just a moment! We are fetching your Details.</div>
</div>
</div>
</div>

	<script src="${pageContext.request.contextPath}/resources/js/system.js" defer></script>
	<script src="${pageContext.request.contextPath}/resources/js/common.js" defer></script>
	<script src="${pageContext.request.contextPath}/resources/newJS/moment.min.js" defer></script>
	<script src="${pageContext.request.contextPath}/resources/newJS/daterangepicker.js" defer></script>	
	<script src="${pageContext.request.contextPath}/resources/js/AES.js" defer></script>
	<script src="${pageContext.request.contextPath}/resources/js/PBKDF2.js" defer></script>
	<script src="${pageContext.request.contextPath}/resources/js/AESUtil.js" defer></script>
	  
	
  <script type="text/javascript" src="/sites/bajaj/15Sep2017/js/bfl.js"></script>
</body>


</html>