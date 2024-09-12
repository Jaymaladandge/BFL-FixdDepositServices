<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
  
  
  <!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,  minimal-ui" />
    <meta name="author" content="Bajaj" />
    <meta name="robots" content="noindex,nofollow" />  
    <meta http-equiv="content-language" content="en,de,fr" />

  <title>Fixed Deposit and SDP application form</title>
    <meta name="description" content="Bajaj Finance Fixed Deposit offers attractive interest rates up to 8.10%, so you can invest easily from the comfort of your home. Get assured returns with 0.25% higher interest rate for senior citizens.">
      <meta name="keywords" content="Online fd application form, fixed deposit online, invest in fd online, open fd online"> 
    <link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/resources/images/baflicon.ico" /> 
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Style.css" />
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js" ></script>
    <script src="${pageContext.request.contextPath}/resources/js/system.js" defer></script>
    <script src="${pageContext.request.contextPath}/resources/js/thank-you.js" defer></script>
      
    <script async src="https://assets.adobedtm.com/2d6d124a9338/1706dfea1c4d/launch-7fb3ad47deaf.min.js"></script>  
<!-- Global site tag (gtag.js) - Google Ads: 802197272 -->
   
   <style type="text/css">
    .a_bannerVidModem div[class^=player-wrapper] iframe[id^=video-gcc]{left:0}.a_bannerVidModem iframe{width:80%;margin:auto;height:100%}@media only screen and (max-width:767px) and (min-width:320px){.p_recommvideoslide .p_videodata{width:72%;float:inherit;display:inline-block!important}div[class^=player-wrapper] iframe[id^=video-gcc]{left:0!important}}</style>
   
 
 <%@ include file="wrapper.jsp" %> 
   
 <%
response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
 %>
  
<!-- Google Tag Manager -->
<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
})(window,document,'script','dataLayer','GTM-5VKBRT6');</script>
<!-- End Google Tag Manager -->
<script type="text/javascript">
;window.NREUM||(NREUM={});NREUM.init={distributed_tracing:{enabled:true},privacy:{cookies_enabled:true},ajax:{deny_list:["bam.nr-data.net"]}};
window.NREUM||(NREUM={}),__nr_require=function(t,e,n){function r(n){if(!e[n]){var o=e[n]={exports:{}};t[n][0].call(o.exports,function(e){var o=t[n][1][e];return r(o||e)},o,o.exports)}return e[n].exports}if("function"==typeof __nr_require)return __nr_require;for(var o=0;o<n.length;o++)r(n[o]);return r}({1:[function(t,e,n){function r(t){try{s.console&&console.log(t)}catch(e){}}var o,i=t("ee"),a=t(30),s={};try{o=localStorage.getItem("__nr_flags").split(","),console&&"function"==typeof console.log&&(s.console=!0,o.indexOf("dev")!==-1&&(s.dev=!0),o.indexOf("nr_dev")!==-1&&(s.nrDev=!0))}catch(c){}s.nrDev&&i.on("internal-error",function(t){r(t.stack)}),s.dev&&i.on("fn-err",function(t,e,n){r(n.stack)}),s.dev&&(r("NR AGENT IN DEVELOPMENT MODE"),r("flags: "+a(s,function(t,e){return t}).join(", ")))},{}],2:[function(t,e,n){function r(t,e,n,r,s){try{l?l-=1:o(s||new UncaughtException(t,e,n),!0)}catch(f){try{i("ierr",[f,c.now(),!0])}catch(d){}}return"function"==typeof u&&u.apply(this,a(arguments))}function UncaughtException(t,e,n){this.message=t||"Uncaught error with no additional information",this.sourceURL=e,this.line=n}function o(t,e){var n=e?null:c.now();i("err",[t,n])}var i=t("handle"),a=t(31),s=t("ee"),c=t("loader"),f=t("gos"),u=window.onerror,d=!1,p="nr@seenError";if(!c.disabled){var l=0;c.features.err=!0,t(1),window.onerror=r;try{throw new Error}catch(h){"stack"in h&&(t(14),t(13),"addEventListener"in window&&t(7),c.xhrWrappable&&t(15),d=!0)}s.on("fn-start",function(t,e,n){d&&(l+=1)}),s.on("fn-err",function(t,e,n){d&&!n[p]&&(f(n,p,function(){return!0}),this.thrown=!0,o(n))}),s.on("fn-end",function(){d&&!this.thrown&&l>0&&(l-=1)}),s.on("internal-error",function(t){i("ierr",[t,c.now(),!0])})}},{}],3:[function(t,e,n){var r=t("loader");r.disabled||(r.features.ins=!0)},{}],4:[function(t,e,n){function r(){L++,C=g.hash,this[u]=y.now()}function o(){L--,g.hash!==C&&i(0,!0);var t=y.now();this[h]=~~this[h]+t-this[u],this[d]=t}function i(t,e){E.emit("newURL",[""+g,e])}function a(t,e){t.on(e,function(){this[e]=y.now()})}var s="-start",c="-end",f="-body",u="fn"+s,d="fn"+c,p="cb"+s,l="cb"+c,h="jsTime",m="fetch",v="addEventListener",w=window,g=w.location,y=t("loader");if(w[v]&&y.xhrWrappable&&!y.disabled){var x=t(11),b=t(12),E=t(9),R=t(7),O=t(14),S=t(8),T=t(15),N=t(10),P=t("ee"),M=P.get("tracer");t(17),y.features.spa=!0;var C,L=0;P.on(u,r),b.on(p,r),N.on(p,r),P.on(d,o),b.on(l,o),N.on(l,o),P.buffer([u,d,"xhr-resolved"]),R.buffer([u]),O.buffer(["setTimeout"+c,"clearTimeout"+s,u]),T.buffer([u,"new-xhr","send-xhr"+s]),S.buffer([m+s,m+"-done",m+f+s,m+f+c]),E.buffer(["newURL"]),x.buffer([u]),b.buffer(["propagate",p,l,"executor-err","resolve"+s]),M.buffer([u,"no-"+u]),N.buffer(["new-jsonp","cb-start","jsonp-error","jsonp-end"]),a(S,m+s),a(S,m+"-done"),a(N,"new-jsonp"),a(N,"jsonp-end"),a(N,"cb-start"),E.on("pushState-end",i),E.on("replaceState-end",i),w[v]("hashchange",i,!0),w[v]("load",i,!0),w[v]("popstate",function(){i(0,L>1)},!0)}},{}],5:[function(t,e,n){function r(){var t=new PerformanceObserver(function(t,e){var n=t.getEntries();s(m,[n])});try{t.observe({entryTypes:["resource"]})}catch(e){}}function o(t){if(s(m,[window.performance.getEntriesByType(v)]),window.performance["c"+d])try{window.performance[l](h,o,!1)}catch(t){}else try{window.performance[l]("webkit"+h,o,!1)}catch(t){}}function i(t){}if(window.performance&&window.performance.timing&&window.performance.getEntriesByType){var a=t("ee"),s=t("handle"),c=t(14),f=t(13),u=t(6),d="learResourceTimings",p="addEventListener",l="removeEventListener",h="resourcetimingbufferfull",m="bstResource",v="resource",w="-start",g="-end",y="fn"+w,x="fn"+g,b="bstTimer",E="pushState",R=t("loader");if(!R.disabled){R.features.stn=!0,t(9),"addEventListener"in window&&t(7);var O=NREUM.o.EV;a.on(y,function(t,e){var n=t[0];n instanceof O&&(this.bstStart=R.now())}),a.on(x,function(t,e){var n=t[0];n instanceof O&&s("bst",[n,e,this.bstStart,R.now()])}),c.on(y,function(t,e,n){this.bstStart=R.now(),this.bstType=n}),c.on(x,function(t,e){s(b,[e,this.bstStart,R.now(),this.bstType])}),f.on(y,function(){this.bstStart=R.now()}),f.on(x,function(t,e){s(b,[e,this.bstStart,R.now(),"requestAnimationFrame"])}),a.on(E+w,function(t){this.time=R.now(),this.startPath=location.pathname+location.hash}),a.on(E+g,function(t){s("bstHist",[location.pathname+location.hash,this.startPath,this.time])}),u()?(s(m,[window.performance.getEntriesByType("resource")]),r()):p in window.performance&&(window.performance["c"+d]?window.performance[p](h,o,!1):window.performance[p]("webkit"+h,o,!1)),document[p]("scroll",i,{passive:!0}),document[p]("keypress",i,!1),document[p]("click",i,!1)}}},{}],6:[function(t,e,n){e.exports=function(){return"PerformanceObserver"in window&&"function"==typeof window.PerformanceObserver}},{}],7:[function(t,e,n){function r(t){for(var e=t;e&&!e.hasOwnProperty(u);)e=Object.getPrototypeOf(e);e&&o(e)}function o(t){s.inPlace(t,[u,d],"-",i)}function i(t,e){return t[1]}var a=t("ee").get("events"),s=t("wrap-function")(a,!0),c=t("gos"),f=XMLHttpRequest,u="addEventListener",d="removeEventListener";e.exports=a,"getPrototypeOf"in Object?(r(document),r(window),r(f.prototype)):f.prototype.hasOwnProperty(u)&&(o(window),o(f.prototype)),a.on(u+"-start",function(t,e){var n=t[1],r=c(n,"nr@wrapped",function(){function t(){if("function"==typeof n.handleEvent)return n.handleEvent.apply(n,arguments)}var e={object:t,"function":n}[typeof n];return e?s(e,"fn-",null,e.name||"anonymous"):n});this.wrapped=t[1]=r}),a.on(d+"-start",function(t){t[1]=this.wrapped||t[1]})},{}],8:[function(t,e,n){function r(t,e,n){var r=t[e];"function"==typeof r&&(t[e]=function(){var t=i(arguments),e={};o.emit(n+"before-start",[t],e);var a;e[m]&&e[m].dt&&(a=e[m].dt);var s=r.apply(this,t);return o.emit(n+"start",[t,a],s),s.then(function(t){return o.emit(n+"end",[null,t],s),t},function(t){throw o.emit(n+"end",[t],s),t})})}var o=t("ee").get("fetch"),i=t(31),a=t(30);e.exports=o;var s=window,c="fetch-",f=c+"body-",u=["arrayBuffer","blob","json","text","formData"],d=s.Request,p=s.Response,l=s.fetch,h="prototype",m="nr@context";d&&p&&l&&(a(u,function(t,e){r(d[h],e,f),r(p[h],e,f)}),r(s,"fetch",c),o.on(c+"end",function(t,e){var n=this;if(e){var r=e.headers.get("content-length");null!==r&&(n.rxSize=r),o.emit(c+"done",[null,e],n)}else o.emit(c+"done",[t],n)}))},{}],9:[function(t,e,n){var r=t("ee").get("history"),o=t("wrap-function")(r);e.exports=r;var i=window.history&&window.history.constructor&&window.history.constructor.prototype,a=window.history;i&&i.pushState&&i.replaceState&&(a=i),o.inPlace(a,["pushState","replaceState"],"-")},{}],10:[function(t,e,n){function r(t){function e(){c.emit("jsonp-end",[],p),t.removeEventListener("load",e,!1),t.removeEventListener("error",n,!1)}function n(){c.emit("jsonp-error",[],p),c.emit("jsonp-end",[],p),t.removeEventListener("load",e,!1),t.removeEventListener("error",n,!1)}var r=t&&"string"==typeof t.nodeName&&"script"===t.nodeName.toLowerCase();if(r){var o="function"==typeof t.addEventListener;if(o){var a=i(t.src);if(a){var u=s(a),d="function"==typeof u.parent[u.key];if(d){var p={};f.inPlace(u.parent,[u.key],"cb-",p),t.addEventListener("load",e,!1),t.addEventListener("error",n,!1),c.emit("new-jsonp",[t.src],p)}}}}}function o(){return"addEventListener"in window}function i(t){var e=t.match(u);return e?e[1]:null}function a(t,e){var n=t.match(p),r=n[1],o=n[3];return o?a(o,e[r]):e[r]}function s(t){var e=t.match(d);return e&&e.length>=3?{key:e[2],parent:a(e[1],window)}:{key:t,parent:window}}var c=t("ee").get("jsonp"),f=t("wrap-function")(c);if(e.exports=c,o()){var u=/[?&](?:callback|cb)=([^&#]+)/,d=/(.*)\.([^.]+)/,p=/^(\w+)(\.|$)(.*)$/,l=["appendChild","insertBefore","replaceChild"];Node&&Node.prototype&&Node.prototype.appendChild?f.inPlace(Node.prototype,l,"dom-"):(f.inPlace(HTMLElement.prototype,l,"dom-"),f.inPlace(HTMLHeadElement.prototype,l,"dom-"),f.inPlace(HTMLBodyElement.prototype,l,"dom-")),c.on("dom-start",function(t){r(t[0])})}},{}],11:[function(t,e,n){var r=t("ee").get("mutation"),o=t("wrap-function")(r),i=NREUM.o.MO;e.exports=r,i&&(window.MutationObserver=function(t){return this instanceof i?new i(o(t,"fn-")):i.apply(this,arguments)},MutationObserver.prototype=i.prototype)},{}],12:[function(t,e,n){function r(t){var e=i.context(),n=s(t,"executor-",e,null,!1),r=new f(n);return i.context(r).getCtx=function(){return e},r}var o=t("wrap-function"),i=t("ee").get("promise"),a=t("ee").getOrSetContext,s=o(i),c=t(30),f=NREUM.o.PR;e.exports=i,f&&(window.Promise=r,["all","race"].forEach(function(t){var e=f[t];f[t]=function(n){function r(t){return function(){i.emit("propagate",[null,!o],a,!1,!1),o=o||!t}}var o=!1;c(n,function(e,n){Promise.resolve(n).then(r("all"===t),r(!1))});var a=e.apply(f,arguments),s=f.resolve(a);return s}}),["resolve","reject"].forEach(function(t){var e=f[t];f[t]=function(t){var n=e.apply(f,arguments);return t!==n&&i.emit("propagate",[t,!0],n,!1,!1),n}}),f.prototype["catch"]=function(t){return this.then(null,t)},f.prototype=Object.create(f.prototype,{constructor:{value:r}}),c(Object.getOwnPropertyNames(f),function(t,e){try{r[e]=f[e]}catch(n){}}),o.wrapInPlace(f.prototype,"then",function(t){return function(){var e=this,n=o.argsToArray.apply(this,arguments),r=a(e);r.promise=e,n[0]=s(n[0],"cb-",r,null,!1),n[1]=s(n[1],"cb-",r,null,!1);var c=t.apply(this,n);return r.nextPromise=c,i.emit("propagate",[e,!0],c,!1,!1),c}}),i.on("executor-start",function(t){t[0]=s(t[0],"resolve-",this,null,!1),t[1]=s(t[1],"resolve-",this,null,!1)}),i.on("executor-err",function(t,e,n){t[1](n)}),i.on("cb-end",function(t,e,n){i.emit("propagate",[n,!0],this.nextPromise,!1,!1)}),i.on("propagate",function(t,e,n){this.getCtx&&!e||(this.getCtx=function(){if(t instanceof Promise)var e=i.context(t);return e&&e.getCtx?e.getCtx():this})}),r.toString=function(){return""+f})},{}],13:[function(t,e,n){var r=t("ee").get("raf"),o=t("wrap-function")(r),i="equestAnimationFrame";e.exports=r,o.inPlace(window,["r"+i,"mozR"+i,"webkitR"+i,"msR"+i],"raf-"),r.on("raf-start",function(t){t[0]=o(t[0],"fn-")})},{}],14:[function(t,e,n){function r(t,e,n){t[0]=a(t[0],"fn-",null,n)}function o(t,e,n){this.method=n,this.timerDuration=isNaN(t[1])?0:+t[1],t[0]=a(t[0],"fn-",this,n)}var i=t("ee").get("timer"),a=t("wrap-function")(i),s="setTimeout",c="setInterval",f="clearTimeout",u="-start",d="-";e.exports=i,a.inPlace(window,[s,"setImmediate"],s+d),a.inPlace(window,[c],c+d),a.inPlace(window,[f,"clearImmediate"],f+d),i.on(c+u,r),i.on(s+u,o)},{}],15:[function(t,e,n){function r(t,e){d.inPlace(e,["onreadystatechange"],"fn-",s)}function o(){var t=this,e=u.context(t);t.readyState>3&&!e.resolved&&(e.resolved=!0,u.emit("xhr-resolved",[],t)),d.inPlace(t,g,"fn-",s)}function i(t){y.push(t),h&&(b?b.then(a):v?v(a):(E=-E,R.data=E))}function a(){for(var t=0;t<y.length;t++)r([],y[t]);y.length&&(y=[])}function s(t,e){return e}function c(t,e){for(var n in t)e[n]=t[n];return e}t(7);var f=t("ee"),u=f.get("xhr"),d=t("wrap-function")(u),p=NREUM.o,l=p.XHR,h=p.MO,m=p.PR,v=p.SI,w="readystatechange",g=["onload","onerror","onabort","onloadstart","onloadend","onprogress","ontimeout"],y=[];e.exports=u;var x=window.XMLHttpRequest=function(t){var e=new l(t);try{u.emit("new-xhr",[e],e),e.addEventListener(w,o,!1)}catch(n){try{u.emit("internal-error",[n])}catch(r){}}return e};if(c(l,x),x.prototype=l.prototype,d.inPlace(x.prototype,["open","send"],"-xhr-",s),u.on("send-xhr-start",function(t,e){r(t,e),i(e)}),u.on("open-xhr-start",r),h){var b=m&&m.resolve();if(!v&&!m){var E=1,R=document.createTextNode(E);new h(a).observe(R,{characterData:!0})}}else f.on("fn-end",function(t){t[0]&&t[0].type===w||a()})},{}],16:[function(t,e,n){function r(t){if(!s(t))return null;var e=window.NREUM;if(!e.loader_config)return null;var n=(e.loader_config.accountID||"").toString()||null,r=(e.loader_config.agentID||"").toString()||null,f=(e.loader_config.trustKey||"").toString()||null;if(!n||!r)return null;var h=l.generateSpanId(),m=l.generateTraceId(),v=Date.now(),w={spanId:h,traceId:m,timestamp:v};return(t.sameOrigin||c(t)&&p())&&(w.traceContextParentHeader=o(h,m),w.traceContextStateHeader=i(h,v,n,r,f)),(t.sameOrigin&&!u()||!t.sameOrigin&&c(t)&&d())&&(w.newrelicHeader=a(h,m,v,n,r,f)),w}function o(t,e){return"00-"+e+"-"+t+"-01"}function i(t,e,n,r,o){var i=0,a="",s=1,c="",f="";return o+"@nr="+i+"-"+s+"-"+n+"-"+r+"-"+t+"-"+a+"-"+c+"-"+f+"-"+e}function a(t,e,n,r,o,i){var a="btoa"in window&&"function"==typeof window.btoa;if(!a)return null;var s={v:[0,1],d:{ty:"Browser",ac:r,ap:o,id:t,tr:e,ti:n}};return i&&r!==i&&(s.d.tk=i),btoa(JSON.stringify(s))}function s(t){return f()&&c(t)}function c(t){var e=!1,n={};if("init"in NREUM&&"distributed_tracing"in NREUM.init&&(n=NREUM.init.distributed_tracing),t.sameOrigin)e=!0;else if(n.allowed_origins instanceof Array)for(var r=0;r<n.allowed_origins.length;r++){var o=h(n.allowed_origins[r]);if(t.hostname===o.hostname&&t.protocol===o.protocol&&t.port===o.port){e=!0;break}}return e}function f(){return"init"in NREUM&&"distributed_tracing"in NREUM.init&&!!NREUM.init.distributed_tracing.enabled}function u(){return"init"in NREUM&&"distributed_tracing"in NREUM.init&&!!NREUM.init.distributed_tracing.exclude_newrelic_header}function d(){return"init"in NREUM&&"distributed_tracing"in NREUM.init&&NREUM.init.distributed_tracing.cors_use_newrelic_header!==!1}function p(){return"init"in NREUM&&"distributed_tracing"in NREUM.init&&!!NREUM.init.distributed_tracing.cors_use_tracecontext_headers}var l=t(27),h=t(18);e.exports={generateTracePayload:r,shouldGenerateTrace:s}},{}],17:[function(t,e,n){function r(t){var e=this.params,n=this.metrics;if(!this.ended){this.ended=!0;for(var r=0;r<p;r++)t.removeEventListener(d[r],this.listener,!1);e.aborted||(n.duration=a.now()-this.startTime,this.loadCaptureCalled||4!==t.readyState?null==e.status&&(e.status=0):i(this,t),n.cbTime=this.cbTime,s("xhr",[e,n,this.startTime,this.endTime,"xhr"],this))}}function o(t,e){var n=c(e),r=t.params;r.hostname=n.hostname,r.port=n.port,r.protocol=n.protocol,r.host=n.hostname+":"+n.port,r.pathname=n.pathname,t.parsedOrigin=n,t.sameOrigin=n.sameOrigin}function i(t,e){t.params.status=e.status;var n=v(e,t.lastSize);if(n&&(t.metrics.rxSize=n),t.sameOrigin){var r=e.getResponseHeader("X-NewRelic-App-Data");r&&(t.params.cat=r.split(", ").pop())}t.loadCaptureCalled=!0}var a=t("loader");if(a.xhrWrappable&&!a.disabled){var s=t("handle"),c=t(18),f=t(16).generateTracePayload,u=t("ee"),d=["load","error","abort","timeout"],p=d.length,l=t("id"),h=t(23),m=t(22),v=t(19),w=NREUM.o.REQ,g=window.XMLHttpRequest;a.features.xhr=!0,t(15),t(8),u.on("new-xhr",function(t){var e=this;e.totalCbs=0,e.called=0,e.cbTime=0,e.end=r,e.ended=!1,e.xhrGuids={},e.lastSize=null,e.loadCaptureCalled=!1,e.params=this.params||{},e.metrics=this.metrics||{},t.addEventListener("load",function(n){i(e,t)},!1),h&&(h>34||h<10)||t.addEventListener("progress",function(t){e.lastSize=t.loaded},!1)}),u.on("open-xhr-start",function(t){this.params={method:t[0]},o(this,t[1]),this.metrics={}}),u.on("open-xhr-end",function(t,e){"loader_config"in NREUM&&"xpid"in NREUM.loader_config&&this.sameOrigin&&e.setRequestHeader("X-NewRelic-ID",NREUM.loader_config.xpid);var n=f(this.parsedOrigin);if(n){var r=!1;n.newrelicHeader&&(e.setRequestHeader("newrelic",n.newrelicHeader),r=!0),n.traceContextParentHeader&&(e.setRequestHeader("traceparent",n.traceContextParentHeader),n.traceContextStateHeader&&e.setRequestHeader("tracestate",n.traceContextStateHeader),r=!0),r&&(this.dt=n)}}),u.on("send-xhr-start",function(t,e){var n=this.metrics,r=t[0],o=this;if(n&&r){var i=m(r);i&&(n.txSize=i)}this.startTime=a.now(),this.listener=function(t){try{"abort"!==t.type||o.loadCaptureCalled||(o.params.aborted=!0),("load"!==t.type||o.called===o.totalCbs&&(o.onloadCalled||"function"!=typeof e.onload))&&o.end(e)}catch(n){try{u.emit("internal-error",[n])}catch(r){}}};for(var s=0;s<p;s++)e.addEventListener(d[s],this.listener,!1)}),u.on("xhr-cb-time",function(t,e,n){this.cbTime+=t,e?this.onloadCalled=!0:this.called+=1,this.called!==this.totalCbs||!this.onloadCalled&&"function"==typeof n.onload||this.end(n)}),u.on("xhr-load-added",function(t,e){var n=""+l(t)+!!e;this.xhrGuids&&!this.xhrGuids[n]&&(this.xhrGuids[n]=!0,this.totalCbs+=1)}),u.on("xhr-load-removed",function(t,e){var n=""+l(t)+!!e;this.xhrGuids&&this.xhrGuids[n]&&(delete this.xhrGuids[n],this.totalCbs-=1)}),u.on("xhr-resolved",function(){this.endTime=a.now()}),u.on("addEventListener-end",function(t,e){e instanceof g&&"load"===t[0]&&u.emit("xhr-load-added",[t[1],t[2]],e)}),u.on("removeEventListener-end",function(t,e){e instanceof g&&"load"===t[0]&&u.emit("xhr-load-removed",[t[1],t[2]],e)}),u.on("fn-start",function(t,e,n){e instanceof g&&("onload"===n&&(this.onload=!0),("load"===(t[0]&&t[0].type)||this.onload)&&(this.xhrCbStart=a.now()))}),u.on("fn-end",function(t,e){this.xhrCbStart&&u.emit("xhr-cb-time",[a.now()-this.xhrCbStart,this.onload,e],e)}),u.on("fetch-before-start",function(t){function e(t,e){var n=!1;return e.newrelicHeader&&(t.set("newrelic",e.newrelicHeader),n=!0),e.traceContextParentHeader&&(t.set("traceparent",e.traceContextParentHeader),e.traceContextStateHeader&&t.set("tracestate",e.traceContextStateHeader),n=!0),n}var n,r=t[1]||{};"string"==typeof t[0]?n=t[0]:t[0]&&t[0].url?n=t[0].url:window.URL&&t[0]&&t[0]instanceof URL&&(n=t[0].href),n&&(this.parsedOrigin=c(n),this.sameOrigin=this.parsedOrigin.sameOrigin);var o=f(this.parsedOrigin);if(o&&(o.newrelicHeader||o.traceContextParentHeader))if("string"==typeof t[0]||window.URL&&t[0]&&t[0]instanceof URL){var i={};for(var a in r)i[a]=r[a];i.headers=new Headers(r.headers||{}),e(i.headers,o)&&(this.dt=o),t.length>1?t[1]=i:t.push(i)}else t[0]&&t[0].headers&&e(t[0].headers,o)&&(this.dt=o)}),u.on("fetch-start",function(t,e){this.params={},this.metrics={},this.startTime=a.now(),this.dt=e,t.length>=1&&(this.target=t[0]),t.length>=2&&(this.opts=t[1]);var n,r=this.opts||{},i=this.target;"string"==typeof i?n=i:"object"==typeof i&&i instanceof w?n=i.url:window.URL&&"object"==typeof i&&i instanceof URL&&(n=i.href),o(this,n);var s=(""+(i&&i instanceof w&&i.method||r.method||"GET")).toUpperCase();this.params.method=s,this.txSize=m(r.body)||0}),u.on("fetch-done",function(t,e){this.endTime=a.now(),this.params||(this.params={}),this.params.status=e?e.status:0;var n;"string"==typeof this.rxSize&&this.rxSize.length>0&&(n=+this.rxSize);var r={txSize:this.txSize,rxSize:n,duration:a.now()-this.startTime};s("xhr",[this.params,r,this.startTime,this.endTime,"fetch"],this)})}},{}],18:[function(t,e,n){var r={};e.exports=function(t){if(t in r)return r[t];var e=document.createElement("a"),n=window.location,o={};e.href=t,o.port=e.port;var i=e.href.split("://");!o.port&&i[1]&&(o.port=i[1].split("/")[0].split("@").pop().split(":")[1]),o.port&&"0"!==o.port||(o.port="https"===i[0]?"443":"80"),o.hostname=e.hostname||n.hostname,o.pathname=e.pathname,o.protocol=i[0],"/"!==o.pathname.charAt(0)&&(o.pathname="/"+o.pathname);var a=!e.protocol||":"===e.protocol||e.protocol===n.protocol,s=e.hostname===document.domain&&e.port===n.port;return o.sameOrigin=a&&(!e.hostname||s),"/"===o.pathname&&(r[t]=o),o}},{}],19:[function(t,e,n){function r(t,e){var n=t.responseType;return"json"===n&&null!==e?e:"arraybuffer"===n||"blob"===n||"json"===n?o(t.response):"text"===n||""===n||void 0===n?o(t.responseText):void 0}var o=t(22);e.exports=r},{}],20:[function(t,e,n){function r(){}function o(t,e,n){return function(){return i(t,[f.now()].concat(s(arguments)),e?null:this,n),e?void 0:this}}var i=t("handle"),a=t(30),s=t(31),c=t("ee").get("tracer"),f=t("loader"),u=NREUM;"undefined"==typeof window.newrelic&&(newrelic=u);var d=["setPageViewName","setCustomAttribute","setErrorHandler","finished","addToTrace","inlineHit","addRelease"],p="api-",l=p+"ixn-";a(d,function(t,e){u[e]=o(p+e,!0,"api")}),u.addPageAction=o(p+"addPageAction",!0),u.setCurrentRouteName=o(p+"routeName",!0),e.exports=newrelic,u.interaction=function(){return(new r).get()};var h=r.prototype={createTracer:function(t,e){var n={},r=this,o="function"==typeof e;return i(l+"tracer",[f.now(),t,n],r),function(){if(c.emit((o?"":"no-")+"fn-start",[f.now(),r,o],n),o)try{return e.apply(this,arguments)}catch(t){throw c.emit("fn-err",[arguments,this,t],n),t}finally{c.emit("fn-end",[f.now()],n)}}}};a("actionText,setName,setAttribute,save,ignore,onEnd,getContext,end,get".split(","),function(t,e){h[e]=o(l+e)}),newrelic.noticeError=function(t,e){"string"==typeof t&&(t=new Error(t)),i("err",[t,f.now(),!1,e])}},{}],21:[function(t,e,n){function r(t){if(NREUM.init){for(var e=NREUM.init,n=t.split("."),r=0;r<n.length-1;r++)if(e=e[n[r]],"object"!=typeof e)return;return e=e[n[n.length-1]]}}e.exports={getConfiguration:r}},{}],22:[function(t,e,n){e.exports=function(t){if("string"==typeof t&&t.length)return t.length;if("object"==typeof t){if("undefined"!=typeof ArrayBuffer&&t instanceof ArrayBuffer&&t.byteLength)return t.byteLength;if("undefined"!=typeof Blob&&t instanceof Blob&&t.size)return t.size;if(!("undefined"!=typeof FormData&&t instanceof FormData))try{return JSON.stringify(t).length}catch(e){return}}}},{}],23:[function(t,e,n){var r=0,o=navigator.userAgent.match(/Firefox[\/\s](\d+\.\d+)/);o&&(r=+o[1]),e.exports=r},{}],24:[function(t,e,n){function r(){return s.exists&&performance.now?Math.round(performance.now()):(i=Math.max((new Date).getTime(),i))-a}function o(){return i}var i=(new Date).getTime(),a=i,s=t(32);e.exports=r,e.exports.offset=a,e.exports.getLastTimestamp=o},{}],25:[function(t,e,n){function r(t){return!(!t||!t.protocol||"file:"===t.protocol)}e.exports=r},{}],26:[function(t,e,n){function r(t,e){var n=t.getEntries();n.forEach(function(t){"first-paint"===t.name?d("timing",["fp",Math.floor(t.startTime)]):"first-contentful-paint"===t.name&&d("timing",["fcp",Math.floor(t.startTime)])})}function o(t,e){var n=t.getEntries();n.length>0&&d("lcp",[n[n.length-1]])}function i(t){t.getEntries().forEach(function(t){t.hadRecentInput||d("cls",[t])})}function a(t){if(t instanceof h&&!v){var e=Math.round(t.timeStamp),n={type:t.type};e<=p.now()?n.fid=p.now()-e:e>p.offset&&e<=Date.now()?(e-=p.offset,n.fid=p.now()-e):e=p.now(),v=!0,d("timing",["fi",e,n])}}function s(t){"hidden"===t&&d("pageHide",[p.now()])}if(!("init"in NREUM&&"page_view_timing"in NREUM.init&&"enabled"in NREUM.init.page_view_timing&&NREUM.init.page_view_timing.enabled===!1)){var c,f,u,d=t("handle"),p=t("loader"),l=t(29),h=NREUM.o.EV;if("PerformanceObserver"in window&&"function"==typeof window.PerformanceObserver){c=new PerformanceObserver(r);try{c.observe({entryTypes:["paint"]})}catch(m){}f=new PerformanceObserver(o);try{f.observe({entryTypes:["largest-contentful-paint"]})}catch(m){}u=new PerformanceObserver(i);try{u.observe({type:"layout-shift",buffered:!0})}catch(m){}}if("addEventListener"in document){var v=!1,w=["click","keydown","mousedown","pointerdown","touchstart"];w.forEach(function(t){document.addEventListener(t,a,!1)})}l(s)}},{}],27:[function(t,e,n){function r(){function t(){return e?15&e[n++]:16*Math.random()|0}var e=null,n=0,r=window.crypto||window.msCrypto;r&&r.getRandomValues&&(e=r.getRandomValues(new Uint8Array(31)));for(var o,i="xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx",a="",s=0;s<i.length;s++)o=i[s],"x"===o?a+=t().toString(16):"y"===o?(o=3&t()|8,a+=o.toString(16)):a+=o;return a}function o(){return a(16)}function i(){return a(32)}function a(t){function e(){return n?15&n[r++]:16*Math.random()|0}var n=null,r=0,o=window.crypto||window.msCrypto;o&&o.getRandomValues&&Uint8Array&&(n=o.getRandomValues(new Uint8Array(31)));for(var i=[],a=0;a<t;a++)i.push(e().toString(16));return i.join("")}e.exports={generateUuid:r,generateSpanId:o,generateTraceId:i}},{}],28:[function(t,e,n){function r(t,e){if(!o)return!1;if(t!==o)return!1;if(!e)return!0;if(!i)return!1;for(var n=i.split("."),r=e.split("."),a=0;a<r.length;a++)if(r[a]!==n[a])return!1;return!0}var o=null,i=null,a=/Version\/(\S+)\s+Safari/;if(navigator.userAgent){var s=navigator.userAgent,c=s.match(a);c&&s.indexOf("Chrome")===-1&&s.indexOf("Chromium")===-1&&(o="Safari",i=c[1])}e.exports={agent:o,version:i,match:r}},{}],29:[function(t,e,n){function r(t){function e(){t(a&&document[a]?document[a]:document[o]?"hidden":"visible")}"addEventListener"in document&&i&&document.addEventListener(i,e,!1)}e.exports=r;var o,i,a;"undefined"!=typeof document.hidden?(o="hidden",i="visibilitychange",a="visibilityState"):"undefined"!=typeof document.msHidden?(o="msHidden",i="msvisibilitychange"):"undefined"!=typeof document.webkitHidden&&(o="webkitHidden",i="webkitvisibilitychange",a="webkitVisibilityState")},{}],30:[function(t,e,n){function r(t,e){var n=[],r="",i=0;for(r in t)o.call(t,r)&&(n[i]=e(r,t[r]),i+=1);return n}var o=Object.prototype.hasOwnProperty;e.exports=r},{}],31:[function(t,e,n){function r(t,e,n){e||(e=0),"undefined"==typeof n&&(n=t?t.length:0);for(var r=-1,o=n-e||0,i=Array(o<0?0:o);++r<o;)i[r]=t[e+r];return i}e.exports=r},{}],32:[function(t,e,n){e.exports={exists:"undefined"!=typeof window.performance&&window.performance.timing&&"undefined"!=typeof window.performance.timing.navigationStart}},{}],ee:[function(t,e,n){function r(){}function o(t){function e(t){return t&&t instanceof r?t:t?f(t,c,a):a()}function n(n,r,o,i,a){if(a!==!1&&(a=!0),!l.aborted||i){t&&a&&t(n,r,o);for(var s=e(o),c=m(n),f=c.length,u=0;u<f;u++)c[u].apply(s,r);var p=d[y[n]];return p&&p.push([x,n,r,s]),s}}function i(t,e){g[t]=m(t).concat(e)}function h(t,e){var n=g[t];if(n)for(var r=0;r<n.length;r++)n[r]===e&&n.splice(r,1)}function m(t){return g[t]||[]}function v(t){return p[t]=p[t]||o(n)}function w(t,e){l.aborted||u(t,function(t,n){e=e||"feature",y[n]=e,e in d||(d[e]=[])})}var g={},y={},x={on:i,addEventListener:i,removeEventListener:h,emit:n,get:v,listeners:m,context:e,buffer:w,abort:s,aborted:!1};return x}function i(t){return f(t,c,a)}function a(){return new r}function s(){(d.api||d.feature)&&(l.aborted=!0,d=l.backlog={})}var c="nr@context",f=t("gos"),u=t(30),d={},p={},l=e.exports=o();e.exports.getOrSetContext=i,l.backlog=d},{}],gos:[function(t,e,n){function r(t,e,n){if(o.call(t,e))return t[e];var r=n();if(Object.defineProperty&&Object.keys)try{return Object.defineProperty(t,e,{value:r,writable:!0,enumerable:!1}),r}catch(i){}return t[e]=r,r}var o=Object.prototype.hasOwnProperty;e.exports=r},{}],handle:[function(t,e,n){function r(t,e,n,r){o.buffer([t],r),o.emit(t,e,n)}var o=t("ee").get("handle");e.exports=r,r.ee=o},{}],id:[function(t,e,n){function r(t){var e=typeof t;return!t||"object"!==e&&"function"!==e?-1:t===window?0:a(t,i,function(){return o++})}var o=1,i="nr@id",a=t("gos");e.exports=r},{}],loader:[function(t,e,n){function r(){if(!S++){var t=O.info=NREUM.info,e=m.getElementsByTagName("script")[0];if(setTimeout(f.abort,3e4),!(t&&t.licenseKey&&t.applicationID&&e))return f.abort();c(E,function(e,n){t[e]||(t[e]=n)});var n=a();s("mark",["onload",n+O.offset],null,"api"),s("timing",["load",n]);var r=m.createElement("script");0===t.agent.indexOf("http://")||0===t.agent.indexOf("https://")?r.src=t.agent:r.src=l+"://"+t.agent,e.parentNode.insertBefore(r,e)}}function o(){"complete"===m.readyState&&i()}function i(){s("mark",["domContent",a()+O.offset],null,"api")}var a=t(24),s=t("handle"),c=t(30),f=t("ee"),u=t(28),d=t(25),p=t(21),l=p.getConfiguration("ssl")===!1?"http":"https",h=window,m=h.document,v="addEventListener",w="attachEvent",g=h.XMLHttpRequest,y=g&&g.prototype,x=!d(h.location);NREUM.o={ST:setTimeout,SI:h.setImmediate,CT:clearTimeout,XHR:g,REQ:h.Request,EV:h.Event,PR:h.Promise,MO:h.MutationObserver};var b=""+location,E={beacon:"bam.nr-data.net",errorBeacon:"bam.nr-data.net",agent:"js-agent.newrelic.com/nr-spa-1211.min.js"},R=g&&y&&y[v]&&!/CriOS/.test(navigator.userAgent),O=e.exports={offset:a.getLastTimestamp(),now:a,origin:b,features:{},xhrWrappable:R,userAgent:u,disabled:x};if(!x){t(20),t(26),m[v]?(m[v]("DOMContentLoaded",i,!1),h[v]("load",r,!1)):(m[w]("onreadystatechange",o),h[w]("onload",r)),s("mark",["firstbyte",a.getLastTimestamp()],null,"api");var S=0}},{}],"wrap-function":[function(t,e,n){function r(t,e){function n(e,n,r,c,f){function nrWrapper(){var i,a,u,p;try{a=this,i=d(arguments),u="function"==typeof r?r(i,a):r||{}}catch(l){o([l,"",[i,a,c],u],t)}s(n+"start",[i,a,c],u,f);try{return p=e.apply(a,i)}catch(h){throw s(n+"err",[i,a,h],u,f),h}finally{s(n+"end",[i,a,p],u,f)}}return a(e)?e:(n||(n=""),nrWrapper[p]=e,i(e,nrWrapper,t),nrWrapper)}function r(t,e,r,o,i){r||(r="");var s,c,f,u="-"===r.charAt(0);for(f=0;f<e.length;f++)c=e[f],s=t[c],a(s)||(t[c]=n(s,u?c+r:r,o,c,i))}function s(n,r,i,a){if(!h||e){var s=h;h=!0;try{t.emit(n,r,i,e,a)}catch(c){o([c,n,r,i],t)}h=s}}return t||(t=u),n.inPlace=r,n.flag=p,n}function o(t,e){e||(e=u);try{e.emit("internal-error",t)}catch(n){}}function i(t,e,n){if(Object.defineProperty&&Object.keys)try{var r=Object.keys(t);return r.forEach(function(n){Object.defineProperty(e,n,{get:function(){return t[n]},set:function(e){return t[n]=e,e}})}),e}catch(i){o([i],n)}for(var a in t)l.call(t,a)&&(e[a]=t[a]);return e}function a(t){return!(t&&t instanceof Function&&t.apply&&!t[p])}function s(t,e){var n=e(t);return n[p]=t,i(t,n,u),n}function c(t,e,n){var r=t[e];t[e]=s(r,n)}function f(){for(var t=arguments.length,e=new Array(t),n=0;n<t;++n)e[n]=arguments[n];return e}var u=t("ee"),d=t(31),p="nr@original",l=Object.prototype.hasOwnProperty,h=!1;e.exports=r,e.exports.wrapFunction=s,e.exports.wrapInPlace=c,e.exports.argsToArray=f},{}]},{},["loader",2,17,5,3,4]);
;NREUM.loader_config={accountID:"2364187",trustKey:"2442591",agentID:"1119998627",licenseKey:"a26b546289",applicationID:"1119998627"}
;NREUM.info={beacon:"bam.nr-data.net",errorBeacon:"bam.nr-data.net",licenseKey:"a26b546289",applicationID:"1119998627",sa:1}
</script>  
</head>

<body >
<!-- Google Tag Manager (noscript) -->
<noscript><iframe src="https://www.googletagmanager.com/ns.html?id=GTM-5VKBRT6"
height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
<!-- End Google Tag Manager (noscript) -->   
<% 

String custType= "";
String transStatus="";
String customerId="";
String customerName="";
String investmentType="";

String interestRate="";
String maturityDate="";
String interestEarned="";
String depositAmount="";
String maturityAmount="";


String sdpTotalPriAmnt = ""; 
String monthIntrestAmnt = ""; 
String noOfDeposit =""; 
String totPayoutAmnt = ""; 
String tenorOfDeposit ="";
String applicationNumber ="";
String utrNumber ="";
String ThankYouText="";
String Thankyoumainline="";
String ThankYouLastline="";
String newnumberOfDeposit="";
String transactionMsg= "";
String pocketActiveflag ="";
String ThankYouLastlinetwo="";
String pdfLine="";
String interestPayoutType="";
String txnAmountForFail="";
String partnercodeFlag="";
String partnercode="";
String paymentMode="";


if(session != null){
	
if(session.getAttribute("custType") != null)  
{		    
	 custType = session.getAttribute("custType").toString();         
	   
}

if(session.getAttribute("utrNumber") != null){
	utrNumber= session.getAttribute("utrNumber").toString();	
}

if(session.getAttribute("transactionMsg") != null){
	transactionMsg = session.getAttribute("transactionMsg").toString();	
}

if(session.getAttribute("pocketActiveflag") != null){
	pocketActiveflag = session.getAttribute("pocketActiveflag").toString();	
}
if(session.getAttribute("txnAmountForFail") != null){
	txnAmountForFail = session.getAttribute("txnAmountForFail").toString();	
}

if(session.getAttribute("investmentType") != null){
	investmentType = session.getAttribute("investmentType").toString();	
}

if(custType.equals("ETB")){
	
	
	
	if(session.getAttribute("customerId") != null)  
	{		    
		customerId = session.getAttribute("customerId").toString();         
		   
	}
	if(session.getAttribute("customerName") != null)  
	{		    
		customerName = session.getAttribute("customerName").toString();         
		   
	}
	if(session.getAttribute("transStatus") != null)  
	{		    
		transStatus = session.getAttribute("transStatus").toString();         
		   
	}
	if(transStatus.equals("success"))  
	{
		investmentType = session.getAttribute("investmentType").toString();
		applicationNumber = session.getAttribute("applicationNumber").toString();
		
		
		if(investmentType.equals("FD") ) 
		{
		interestRate = session.getAttribute("interestRate").toString(); 
		maturityDate = session.getAttribute("maturityDate").toString(); 
	    interestEarned = session.getAttribute("interestEarned").toString(); 
		depositAmount = session.getAttribute("depositAmount").toString(); 
		maturityAmount = session.getAttribute("maturityAmount").toString();
		Thankyoumainline ="Congratulations! You have successfully made your deposit.Your deposit will be booked shortly, subject to payment clearance";
		pdfLine="For each financial year, you will have to submit form 15G (for non senior citizens) or 15H (for senior citizens) to Bajaj Finance Limited to save your tax deduction at source. These forms are available on our customer portal Experia, once your deposit is booked. These can be filled-in and submitted online in a hassle-free manner.";
		interestPayoutType=session.getAttribute("interestPayoutType").toString();
		}
		if(investmentType.equals("SDP") ) 
		{
			
			
		sdpTotalPriAmnt = session.getAttribute("sdpTotalPriAmnt").toString(); 
		monthIntrestAmnt = session.getAttribute("monthIntrestAmnt").toString(); 
		noOfDeposit = session.getAttribute("noOfDeposit").toString(); 
		
		int number = Integer.parseInt(noOfDeposit);
		newnumberOfDeposit = String.valueOf(number);
		
		totPayoutAmnt = session.getAttribute("totPayoutAmnt").toString(); 
		tenorOfDeposit = session.getAttribute("tenorOfDeposit").toString();
		Thankyoumainline ="Congratulations! You have successfully made your first systematic deposit. Your systematic deposit will be booked shortly, subject to payment clearance from Payment Gateway and e-mandate registration.";
		ThankYouLastline ="You will shortly receive an SMS to register for e-mandate to auto debit your account for subsequent "+ newnumberOfDeposit +" deposits. Please note that it is mandatory to complete the e-mandate registration process for booking your deposit.";
		//ThankYouLastlinetwo ="We will refund the money in case there is no registration of e-mandate within 9 days of the first transaction.";
		pdfLine="For each financial year, you will have to submit form 15G (for non senior citizens) or 15H (for senior citizens) to Bajaj Finance Limited to save your tax deduction at source. These forms are available on our customer portal Experia, once your deposit is booked. These can be filled-in and submitted online in a hassle-free manner.";
		
		}
	

	}
}



}

 


%>
  	<input id="sdpTotalPriAmnt" type="hidden" value='<c:out value="${sdpTotalPriAmnt}"/>'/>
   	<input id="monthIntrestAmnt" type="hidden" value='<c:out value="${monthIntrestAmnt}"/>'/>
  	<input id="noOfDeposit" type="hidden" value='<c:out value="${noOfDeposit}"/>'/>
   	<input id="totPayoutAmnt" type="hidden" value='<c:out value="${totPayoutAmnt}"/>'/>
   	<input id="tenorOfDeposit" type="hidden" value='<c:out value="${tenorOfDeposit}"/>'/>
   
   
  	<input id="maturityAmount" type="hidden" value='<c:out value="${maturityAmount}"/>'/>
 	<input id="interestEarned" type="hidden" value='<c:out value="${interestEarned}"/>'/>
  	<input id="maturityDate" type="hidden" value='<c:out value="${maturityDate}"/>'/>
   	<input id="depositAmount" type="hidden" value='<c:out value="${depositAmount}"/>'/>
 	<input id="interestRate" type="hidden" value='<c:out value="${interestRate}"/>'/>
 
  	<input id="custType" type="hidden" value='<c:out value="${custType}"/>'/> 
   	<input id="transStatus" type="hidden" value='<c:out value="${transStatus}"/>'/> 
    <input id="investmentType" type="hidden" value='<c:out value="${investmentType}"/>'/> 
    <input id="customerId" type="hidden" value='<c:out value="${customerId}"/>'/> 
    <input id="pocketActiveflag" type="hidden" value='<c:out value="${pocketActiveflag}"/>'/>
    <input id="txnAmountForFail" type="hidden" value='<c:out value="${txnAmountForFail}"/>'/> 
    <input id="interestPayoutType" type="hidden" value='<c:out value="${interestPayoutType}"/>'/>   
    <input id="utrNumber" type="hidden" value='<c:out value="${utrNumber}"/>'/> 
    
    <input id="partnerCode" type="hidden" value='<c:out value="${partnerCode}"/>'/> 
    <input id="partnercodeFlage" type="hidden" value='<c:out value="${partnercodeFlage}"/>'/>
    <input id="paymentMode" type="hidden" value='<c:out value="${paymentMode}"/>'/> 
    
      
    <div class="thank_success" style="display:none" >
            
        <section class="p_fdheadtitle thnakupdd">
            <div class="p_fdtextname">
            <img   src="${pageContext.request.contextPath}/resources/images/allicon-new.png" alt="logo" class="logoimg" >
            <p>Thank you for investing with us!</p></div>
        </section>

        <section class="p_thankuparts">

            <div class="p_leftthankupart">
                <div class="p_leftalldata">
                    <div class="p_congratulations">
                        <img src="${pageContext.request.contextPath}/resources/images/right.jpg" alt="right.png">
                        <p><%=Thankyoumainline%></p>
                            <p>Your Application Number is <c:out value="${applicationNumber}"/> and your Transaction Reference Number is <c:out value="${utrNumber}"/></p>
                                  
                                  <P><c:out value="<%=ThankYouLastline%>"/></P><br>
                                  <%-- <P><c:out value="<%=ThankYouLastlinetwo%>"/></P><br> --%> 
                                  <P><c:out value="<%=pdfLine%>"/></P>
                                  
      		        </div>
                    <div class="p_depositsummery">
                        <div class="p_titledeposit">
                            <h2>Your deposit summary</h2>
                        </div>
                        <div class="p_maturityamount">
                            <p id="totalAmount">Maturity amount</p>
                            <strong id="fata"><i class="fas fa-rupee"></i> 4,625</strong>
                        </div>
                        <div class="p_listmaturariy">
                            <ul>
                                <li><p id="ir">Interest rate(% p.a.)</p><strong>7.9%</strong></li>
                                <li><p id="tir">Total number of deposits</p><strong>7.9%</strong></li>
                                <li><p id="md">Maturity Month</p><strong>04-Mar-2025</strong></li>
                                <li><p  id="ie" >Interest amount(Rs)</p><strong><i class="fas fa-rupee"></i> 4,625</strong></li>
                                <li><p id ="da">Deposit amount(Rs.)</p><strong><i class="fas fa-rupee"></i> 10,000</strong></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>


            <div class="p_rightuserpart">
                <div class="p_rightalldata">

                    

                    <div class="p_depositinfo">
                        <div class="p_deposittext">
                        <p>
                        We will soon send you all your deposit-related information on your registered contact details</p>
                        </div>
                       <!--  <div class="row">
                            <div class="p_commen"><button style="cursor: pointer">DOWNLOAD REFERECE FORM</button></div>
                           
                        </div> -->
                    </div>

                </div>
            </div>

        </section>
      <section class="a_existOffer">
			<h1>We have got some exciting offers for you</h1>
			<div class="cardPart">
				<div class="cardMain">
					<div class="shapePart">
						<h2>Wallet Care</h2>    
						<img src="${pageContext.request.contextPath}/resources/images/allicon-new.png" alt="logo">
						<p><c:out value="${customerName}"/></p>
						<ul>
							<li>
								<strong><i class="fas fa-rupee"></i> 599</strong>
								<p>PREMIUM</p>
							</li>
							<li>
								<strong><i class="fas fa-rupee"></i> 2,00,000</strong>
								<p>SUM INSURED</p>
							</li>
							<li>
								<strong>1 YEAR</strong>
								<p>VALIDITY</p>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="coverdPart">
				<h2>What's covered</h2>
				<ul>
					<li>Instantly block lost credit/debit cards. You can avail this service 24X7.</li>
					<li>Emergency travel and hotel assistance up to Rs. 1 lakh (abroad) and Rs. 50,000 (within India). This advance is interest free up to 1 month</li> 
					<li>Coverage against debit/credit card fraud up to Rs. 1 lakh. Conditions apply*.</li> 
					<li>Free of cost assistance to replace lost PAN Card.</li> 
				</ul>
			</div>
			<div class="coverdPart">
				<h2>What's not covered</h2>
				<ul>
					<li>Any loss caused due to negligence by the registered card holder.</li>
					<li>Deliberately caused losses.</li> 
				</ul>
			</div>
			<div class="a_tNcpart"> 
                <div class="etbterm">
                        <div class="chechTC">
                            <label class="turmcon" >
                                    <a href="#;">I hereby declare that all the information provided by me is correct and I have understood the terms and conditions of the plan.</a>
                                    <input type="checkbox" name="pocketTC" id="pocketTC" value="tNc" class="checkVD">
                                    <i class="checkmark"></i>
                            </label>
                            <div class="errormsg" id="tcerror" style="display: none">Please check</div>
                       </div>
                </div>
                
                <div class="a_radiodeposit">
                <label>
                    <input type="radio" value="Pay online" id="pay" name="paytype">
                    <i></i>
                    <p>Pay online</p>
                </label>
                <!-- <label id ="autoDebitPayout" style="display: none">
                    <input type="radio" value="Auto debit" id="debit" name="paytype" checked>
                    <i></i>
                    <p>Auto debit</p>
                </label> -->
            </div>
            </div>
			<div class="planCta">
				<a>Buy Protection Plan</a>
			</div>
						<div class="p_recommendvideo recomfull">
            <div class="p_titlerecommnd"><h2>Recommended Videos</h2></div>
            <div class="p_recommvideoslide">

                <div class="row">
                <div class="videoSliderPart">
                <div class="p_videodata">
                    <div class="p_videobox">
                    <img src="${pageContext.request.contextPath}/resources/images/FD.jpg" alt="">
                    </div>
                    <div class="p_videodtatext">
                        
                        <div class="p_videoplayicon">
                             <img src="${pageContext.request.contextPath}/resources/images/play.png" alt="">
							 <div class="videoUrlId" style="display:none">
                             <div data-video-host='videos.bajajfinserv.in' data-kvideo-id='gcc-a30d6502-aa52-4024-b801-fa2b66155488' data-video-params='{"cookies": "_ga, _page"}'></div>  
                            </div>
                        </div>
                    </div>
                </div>
                <div class="p_videodata">
                        <div class="p_videobox">
                        <img src="${pageContext.request.contextPath}/resources/images/SDP .jpg" alt="">
                        </div>
                        <div class="p_videodtatext">
                             
                            <div class="p_videoplayicon">
                                 <img src="${pageContext.request.contextPath}/resources/images/play.png" alt="">
								 <div class="videoUrlId" style="display:none">
                            <div data-video-host='videos.bajajfinserv.in'  data-kvideo-id='gcc-f7b92f2a-82b2-4bb3-8959-4b1b7f488ea6' data-video-params='{"cookies": "_ga, _page"}'>
                            </div> 
                            </div>
                            </div>
                        </div>
                </div>
                    <div class="p_videodata">
                        <div class="p_videobox">
                         <img src="${pageContext.request.contextPath}/resources/images/Pedal Insurance.jpg" alt="">
                        </div>
                        <div class="p_videodtatext">
                            
                            <div class="p_videoplayicon">
                                 <img src="${pageContext.request.contextPath}/resources/images/play.png" alt="">
								 <div class="videoUrlId" style="display:none">
							<div data-video-host='videos.bajajfinserv.in' data-kvideo-id='gcc-10a2b170-8a8e-4dd8-b451-5a4fac8710d2' data-video-params='{"cookies": "_ga, _page"}'></div>
                           </div>
                            </div>
                        </div>
                </div>
                    <div class="p_videodata">
                        <div class="p_videobox">
                         <img src="${pageContext.request.contextPath}/resources/images/headphone.jpg" alt="">
                        </div>
                        <div class="p_videodtatext">
                            
                            <div class="p_videoplayicon">
                                 <img src="${pageContext.request.contextPath}/resources/images/play.png" alt="">
								  <div class="videoUrlId" style="display:none">
							<div data-video-host='videos.bajajfinserv.in' data-kvideo-id='gcc-d544a7f4-e62f-44df-b024-87f878f851b7' data-video-params='{"cookies": "_ga, _page"}'></div> 
                            </div>
                            </div>
                        </div>
                </div>
                    
				<div class="p_videodata">
                        <div class="p_videobox">
                        <img src="${pageContext.request.contextPath}/resources/images/heart surgery.jpg" alt="">
                        </div>
                        <div class="p_videodtatext">
                            
                            <div class="p_videoplayicon">
                                 <img src="${pageContext.request.contextPath}/resources/images/play.png" alt="">
								 <div class="videoUrlId" style="display:none">
                            <div data-video-host='videos.bajajfinserv.in' data-kvideo-id='gcc-b8ee1051-7d32-410d-ae94-807a47c7eed7' data-video-params='{"cookies": "_ga, _page"}'></div>
							</div>
                            </div>
                        </div>
                </div>
                    </div>
                </div>


            </div>
    
        </div> 
		</section>
       
    </div>
           
   
       
  
    <div class="failTrans" style="display:none">
   <section class="p_fdheadtitle thnakupdd">
            <div class="p_fdtextname"><p>Payment Failed</p>
            <img   src="${pageContext.request.contextPath}/resources/images/allicon-new.png" alt="logo" class="logoimg" >
            </div>
        </section>

        <section class="p_thankuparts">

            <div class="p_leftthankupart">
                <div class="p_leftalldata">
                    <div class="p_congratulations">
                        <img src="${pageContext.request.contextPath}/resources/images/paymentCross.jpg" alt="paymentCross">
     <p>Oops ! Your payment has failed. The reason for payment failure is "<c:out value="${transactionMsg}"/>".<br><br>
    	Rest assured, if any amount has been deducted, it is safe with us.<br><br>
    	Our representative will get in touch with you shortly to resolve the issue and take you through the next steps. Your transaction reference number is <c:out value="${utrNumber}"/>.<br><br>
    	If amount has not been deducted from your bank account, then you can retry payment below.<br><br>
    	If you have any further queries, please write to us at - fd@bajajfinserv.in</p>
                    </div> 
                      
                </div>
                                     <div class="a_renewFDeposite" >
                      <form id="panCarVerify">
                      <input id="reactRetryone" type="hidden" value="u/Gu5posvwDsXUnV5Zaq4g==" />
                      <input id="reactRetryTwo" type="hidden" value="5D9r9ZVzEYYgha93/aUK2w=="  />
							<div class="a_afterMarurity tyPadd">
								<strong>Banking Details</strong>

							</div>

							<div class="a_ReInput" id ="fdbankNameID">
								<label class="tyPadd" for="bankName">Select Bank Name <a href="#;"
									class="a_viewPament">View available payment modes</a></label> <select
									id="fdbankName" class="bankNameSelect BankVD">
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
										
											<option class="upihide" value="Vasai Janata Sahakari Bank Ltd">VASAI JANATA SAHAKARI BANK LTD</option>
									<option class="upihide" value="Vasai Vikas Sahakari Bank Limited">VASAI VIKAS SAHAKARI BANK LIMITED</option>
									<option class="bothBankOption" value="YES BANK">YES BANK</option>

</select>
								<div class="errormsg">Select Bank Name</div>
								<i class="fas fa-caret-down"></i>
							</div>
							
							
				<div class="a_ReInput" id ="sdpbankNameID">
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
										
											<option class="upihide" value="Vasai Janata Sahakari Bank Ltd">VASAI JANATA SAHAKARI BANK LTD</option>
									<option class="upihide" value="Vasai Vikas Sahakari Bank Limited">VASAI VIKAS SAHAKARI BANK LIMITED</option>
									<option class="bothBankOption" value="YES BANK">YES BANK</option>

</select>
                    <div class="errormsg">Select Bank Name</div>
                    <i class="fas fa-caret-down"></i>
                </div>
							
							
							<div class="a_opplsLink statusgrip">
								<div class="a_oopsPlan">
									<img src="${pageContext.request.contextPath}/resources/images/error.png" alt="">
									<p>Oops! Only UPI payment mode is available for the bank selected and UPI payments do not support transaction amounts greater than 1 lakh in a day. Hence, please select another bank from the list which has Netbanking payment mode available. You can view available payment modes <a href="#;" >here</a></p>
								</div>
							</div>
							<div class="bnkMethod">
								<p>Choose payment mode</p>
								<div class="a_radiodeposit">
									<label id="fdNetBankCheck"> <input type="radio" name="banking_det_1"
										value="Netbanking"> <i></i>
										<p>Netbanking</p>
									</label> <label id="fdupiCheck"> <input type="radio"  id="fdnetBank" name="banking_det_1"
										value="UPI"> <i></i>
										<p>UPI</p>
									</label> 
								</div>
								<div class="errormsg">Please select payment mode</div>
							</div>
							<div class="a_radiodeposit_2">
								<div class="a_ReInput">
									<label for="accountNumber">Account Number <i>(Should
											be in the name of FD applicant)</i></label> <input type="number"
										class="accountNumVD inspectletIgnore" id="fdaccountNumber" name="accountNumber"  autocomplete="off">
									<div class="errormsg">Enter your account number</div>
								</div>
								<div class="a_ReInput">
									<label for="ifscCode">IFSC</label> <input type="text"
										class="ifscCodeVD ifscCheck inspectletIgnore" name="ifscCode" id="fdifscCode" autocomplete="off">
									<div class="errormsg">Please enter IFSC code</div>
								</div>
							
							
							</div>
							<div class="a_blueBtnPart">
								<button class="submitBTN validBtn">RETRY PAYMENT
								<div class="fd_sdp_loder"></div>
								</button>
							</div>
							
							</form>
				</div>
                      
            </div>
            
        </section>
	
    
</div>     

 <div class="ntb" style="display:none">
            
        <section class="p_fdheadtitle thnakupdd">
            <div class="p_fdtextname">
            <img   src="${pageContext.request.contextPath}/resources/images/allicon-new.png" alt="logo" class="logoimg">
            <p>Thank you for contacting us</p></div>
        </section>

        <section class="p_thankuparts">

            <div class="p_leftthankupart">
                <div class="p_leftalldata">
                    <div class="p_congratulations">
                        <img src="${pageContext.request.contextPath}/resources/images/right.jpg" alt="right.png">
                        <p>We couldn't find records matching your mobile number at CERSAI CKYC//UIDAI Aadhaar. Do not worry, our representative will get in touch with you within the next 24 hours to complete your FD booking.</p>
                    </div> 
                </div>
            </div>
        </section>
	
    </div>
 <div class="fdbilldesk" style="display: none;">
		<form
			action="https://payment.bajajfinserv.in/Payments/FD_Payment.aspx"
			method="POST" id="fdbilldesk">
			<input type="text" name="msg" value="" id="fdmsg"> <input
				type="submit" value="Submit">
		</form>
</div>
    
      <div class="a_bannerVidModem">
    <div>
    </div>
    
    <a href="#;">X</a>
</div>
          <div class="a_blackoverlay"></div>
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
    
 

   <script type='text/javascript' src="//assets.kpoint.com/orca/media/embed/player-cdn.js" defer></script>
    <script src="${pageContext.request.contextPath}/resources/js/AES.js" defer></script>
	<script src="${pageContext.request.contextPath}/resources/js/PBKDF2.js" defer></script>
	<script src="${pageContext.request.contextPath}/resources/js/AESUtil.js" defer></script>
 	<script src="${pageContext.request.contextPath}/resources/js/Crypto.js" defer></script>
 

</body>

</html>