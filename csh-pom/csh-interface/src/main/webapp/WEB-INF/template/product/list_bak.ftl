<html style="font-size: 142.3px;">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<meta name="msapplication-tap-highlight" content="no">
<title>车品商城</title>
<script type="text/javascript" async="" src="http://dn-growing.qbox.me/vds.js"></script>
<script src="//hm.baidu.com/hm.js?532f8c94531d6f5185c9b2b2398d09bf"></script>
<script type="text/javascript">var Zepto=function(){function t(t){return null==t?String(t):Y[J.call(t)]||"object"}function e(e){return"function"==t(e)}function n(t){return null!=t&&t==t.window}function i(t){return null!=t&&t.nodeType==t.DOCUMENT_NODE}function r(e){return"object"==t(e)}function o(t){return r(t)&&!n(t)&&Object.getPrototypeOf(t)==Object.prototype}function a(t){return"number"==typeof t.length}function s(t){return M.call(t,function(t){return null!=t})}function u(t){return t.length>0?j.fn.concat.apply([],t):t}function c(t){return t.replace(/::/g,"/").replace(/([A-Z]+)([A-Z][a-z])/g,"$1_$2").replace(/([a-z\d])([A-Z])/g,"$1_$2").replace(/_/g,"-").toLowerCase()}function l(t){return t in A?A[t]:A[t]=new RegExp("(^|\\s)"+t+"(\\s|$)")}function f(t,e){return"number"!=typeof e||$[c(t)]?e:e+"px"}function h(t){var e,n;return Z[t]||(e=L.createElement(t),L.body.appendChild(e),n=getComputedStyle(e,"").getPropertyValue("display"),e.parentNode.removeChild(e),"none"==n&&(n="block"),Z[t]=n),Z[t]}function p(t){return"children"in t?D.call(t.children):j.map(t.childNodes,function(t){return 1==t.nodeType?t:void 0})}function d(t,e){var n,i=t?t.length:0;for(n=0;i>n;n++)this[n]=t[n];this.length=i,this.selector=e||""}function m(t,e,n){for(T in e)n&&(o(e[T])||Q(e[T]))?(o(e[T])&&!o(t[T])&&(t[T]={}),Q(e[T])&&!Q(t[T])&&(t[T]=[]),m(t[T],e[T],n)):e[T]!==E&&(t[T]=e[T])}function v(t,e){return null==e?j(t):j(t).filter(e)}function g(t,n,i,r){return e(n)?n.call(t,i,r):n}function y(t,e,n){null==n?t.removeAttribute(e):t.setAttribute(e,n)}function x(t,e){var n=t.className||"",i=n&&n.baseVal!==E;return e===E?i?n.baseVal:n:void(i?n.baseVal=e:t.className=e)}function w(t){try{return t?"true"==t||("false"==t?!1:"null"==t?null:+t+""==t?+t:/^[\[\{]/.test(t)?j.parseJSON(t):t):t}catch(e){return t}}function b(t,e){e(t);for(var n=0,i=t.childNodes.length;i>n;n++)b(t.childNodes[n],e)}var E,T,j,C,S,N,O=[],P=O.concat,M=O.filter,D=O.slice,L=window.document,Z={},A={},$={"column-count":1,columns:1,"font-weight":1,"line-height":1,opacity:1,"z-index":1,zoom:1},k=/^\s*<(\w+|!)[^>]*>/,R=/^<(\w+)\s*\/?>(?:<\/\1>|)$/,z=/<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/gi,q=/^(?:body|html)$/i,F=/([A-Z])/g,_=["val","css","html","text","data","width","height","offset"],I=["after","prepend","before","append"],H=L.createElement("table"),U=L.createElement("tr"),X={tr:L.createElement("tbody"),tbody:H,thead:H,tfoot:H,td:U,th:U,"*":L.createElement("div")},V=/complete|loaded|interactive/,B=/^[\w-]*$/,Y={},J=Y.toString,W={},G=L.createElement("div"),K={tabindex:"tabIndex",readonly:"readOnly","for":"htmlFor","class":"className",maxlength:"maxLength",cellspacing:"cellSpacing",cellpadding:"cellPadding",rowspan:"rowSpan",colspan:"colSpan",usemap:"useMap",frameborder:"frameBorder",contenteditable:"contentEditable"},Q=Array.isArray||function(t){return t instanceof Array};return W.matches=function(t,e){if(!e||!t||1!==t.nodeType)return!1;var n=t.webkitMatchesSelector||t.mozMatchesSelector||t.oMatchesSelector||t.matchesSelector;if(n)return n.call(t,e);var i,r=t.parentNode,o=!r;return o&&(r=G).appendChild(t),i=~W.qsa(r,e).indexOf(t),o&&G.removeChild(t),i},S=function(t){return t.replace(/-+(.)?/g,function(t,e){return e?e.toUpperCase():""})},N=function(t){return M.call(t,function(e,n){return t.indexOf(e)==n})},W.fragment=function(t,e,n){var i,r,a;return R.test(t)&&(i=j(L.createElement(RegExp.$1))),i||(t.replace&&(t=t.replace(z,"<$1></$2>")),e===E&&(e=k.test(t)&&RegExp.$1),e in X||(e="*"),a=X[e],a.innerHTML=""+t,i=j.each(D.call(a.childNodes),function(){a.removeChild(this)})),o(n)&&(r=j(i),j.each(n,function(t,e){_.indexOf(t)>-1?r[t](e):r.attr(t,e)})),i},W.Z=function(t,e){return new d(t,e)},W.isZ=function(t){return t instanceof W.Z},W.init=function(t,n){var i;if(!t)return W.Z();if("string"==typeof t)if(t=t.trim(),"<"==t[0]&&k.test(t))i=W.fragment(t,RegExp.$1,n),t=null;else{if(n!==E)return j(n).find(t);i=W.qsa(L,t)}else{if(e(t))return j(L).ready(t);if(W.isZ(t))return t;if(Q(t))i=s(t);else if(r(t))i=[t],t=null;else if(k.test(t))i=W.fragment(t.trim(),RegExp.$1,n),t=null;else{if(n!==E)return j(n).find(t);i=W.qsa(L,t)}}return W.Z(i,t)},j=function(t,e){return W.init(t,e)},j.extend=function(t){var e,n=D.call(arguments,1);return"boolean"==typeof t&&(e=t,t=n.shift()),n.forEach(function(n){m(t,n,e)}),t},W.qsa=function(t,e){var n,i="#"==e[0],r=!i&&"."==e[0],o=i||r?e.slice(1):e,a=B.test(o);return t.getElementById&&a&&i?(n=t.getElementById(o))?[n]:[]:1!==t.nodeType&&9!==t.nodeType&&11!==t.nodeType?[]:D.call(a&&!i&&t.getElementsByClassName?r?t.getElementsByClassName(o):t.getElementsByTagName(e):t.querySelectorAll(e))},j.contains=L.documentElement.contains?function(t,e){return t!==e&&t.contains(e)}:function(t,e){for(;e&&(e=e.parentNode);)if(e===t)return!0;return!1},j.type=t,j.isFunction=e,j.isWindow=n,j.isArray=Q,j.isPlainObject=o,j.isEmptyObject=function(t){var e;for(e in t)return!1;return!0},j.inArray=function(t,e,n){return O.indexOf.call(e,t,n)},j.camelCase=S,j.trim=function(t){return null==t?"":String.prototype.trim.call(t)},j.uuid=0,j.support={},j.expr={},j.noop=function(){},j.map=function(t,e){var n,i,r,o=[];if(a(t))for(i=0;i<t.length;i++)n=e(t[i],i),null!=n&&o.push(n);else for(r in t)n=e(t[r],r),null!=n&&o.push(n);return u(o)},j.each=function(t,e){var n,i;if(a(t)){for(n=0;n<t.length;n++)if(e.call(t[n],n,t[n])===!1)return t}else for(i in t)if(e.call(t[i],i,t[i])===!1)return t;return t},j.grep=function(t,e){return M.call(t,e)},window.JSON&&(j.parseJSON=JSON.parse),j.each("Boolean Number String Function Array Date RegExp Object Error".split(" "),function(t,e){Y["[object "+e+"]"]=e.toLowerCase()}),j.fn={constructor:W.Z,length:0,forEach:O.forEach,reduce:O.reduce,push:O.push,sort:O.sort,splice:O.splice,indexOf:O.indexOf,concat:function(){var t,e,n=[];for(t=0;t<arguments.length;t++)e=arguments[t],n[t]=W.isZ(e)?e.toArray():e;return P.apply(W.isZ(this)?this.toArray():this,n)},map:function(t){return j(j.map(this,function(e,n){return t.call(e,n,e)}))},slice:function(){return j(D.apply(this,arguments))},ready:function(t){return V.test(L.readyState)&&L.body?t(j):L.addEventListener("DOMContentLoaded",function(){t(j)},!1),this},get:function(t){return t===E?D.call(this):this[t>=0?t:t+this.length]},toArray:function(){return this.get()},size:function(){return this.length},remove:function(){return this.each(function(){null!=this.parentNode&&this.parentNode.removeChild(this)})},each:function(t){return O.every.call(this,function(e,n){return t.call(e,n,e)!==!1}),this},filter:function(t){return e(t)?this.not(this.not(t)):j(M.call(this,function(e){return W.matches(e,t)}))},add:function(t,e){return j(N(this.concat(j(t,e))))},is:function(t){return this.length>0&&W.matches(this[0],t)},not:function(t){var n=[];if(e(t)&&t.call!==E)this.each(function(e){t.call(this,e)||n.push(this)});else{var i="string"==typeof t?this.filter(t):a(t)&&e(t.item)?D.call(t):j(t);this.forEach(function(t){i.indexOf(t)<0&&n.push(t)})}return j(n)},has:function(t){return this.filter(function(){return r(t)?j.contains(this,t):j(this).find(t).size()})},eq:function(t){return-1===t?this.slice(t):this.slice(t,+t+1)},first:function(){var t=this[0];return t&&!r(t)?t:j(t)},last:function(){var t=this[this.length-1];return t&&!r(t)?t:j(t)},find:function(t){var e,n=this;return e=t?"object"==typeof t?j(t).filter(function(){var t=this;return O.some.call(n,function(e){return j.contains(e,t)})}):1==this.length?j(W.qsa(this[0],t)):this.map(function(){return W.qsa(this,t)}):j()},closest:function(t,e){var n=this[0],r=!1;for("object"==typeof t&&(r=j(t));n&&!(r?r.indexOf(n)>=0:W.matches(n,t));)n=n!==e&&!i(n)&&n.parentNode;return j(n)},parents:function(t){for(var e=[],n=this;n.length>0;)n=j.map(n,function(t){return(t=t.parentNode)&&!i(t)&&e.indexOf(t)<0?(e.push(t),t):void 0});return v(e,t)},parent:function(t){return v(N(this.pluck("parentNode")),t)},children:function(t){return v(this.map(function(){return p(this)}),t)},contents:function(){return this.map(function(){return this.contentDocument||D.call(this.childNodes)})},siblings:function(t){return v(this.map(function(t,e){return M.call(p(e.parentNode),function(t){return t!==e})}),t)},empty:function(){return this.each(function(){this.innerHTML=""})},pluck:function(t){return j.map(this,function(e){return e[t]})},show:function(){return this.each(function(){"none"==this.style.display&&(this.style.display=""),"none"==getComputedStyle(this,"").getPropertyValue("display")&&(this.style.display=h(this.nodeName))})},replaceWith:function(t){return this.before(t).remove()},wrap:function(t){var n=e(t);if(this[0]&&!n)var i=j(t).get(0),r=i.parentNode||this.length>1;return this.each(function(e){j(this).wrapAll(n?t.call(this,e):r?i.cloneNode(!0):i)})},wrapAll:function(t){if(this[0]){j(this[0]).before(t=j(t));for(var e;(e=t.children()).length;)t=e.first();j(t).append(this)}return this},wrapInner:function(t){var n=e(t);return this.each(function(e){var i=j(this),r=i.contents(),o=n?t.call(this,e):t;r.length?r.wrapAll(o):i.append(o)})},unwrap:function(){return this.parent().each(function(){j(this).replaceWith(j(this).children())}),this},clone:function(){return this.map(function(){return this.cloneNode(!0)})},hide:function(){return this.css("display","none")},toggle:function(t){return this.each(function(){var e=j(this);(t===E?"none"==e.css("display"):t)?e.show():e.hide()})},prev:function(t){return j(this.pluck("previousElementSibling")).filter(t||"*")},next:function(t){return j(this.pluck("nextElementSibling")).filter(t||"*")},html:function(t){return 0 in arguments?this.each(function(e){var n=this.innerHTML;j(this).empty().append(g(this,t,e,n))}):0 in this?this[0].innerHTML:null},text:function(t){return 0 in arguments?this.each(function(e){var n=g(this,t,e,this.textContent);this.textContent=null==n?"":""+n}):0 in this?this.pluck("textContent").join(""):null},attr:function(t,e){var n;return"string"!=typeof t||1 in arguments?this.each(function(n){if(1===this.nodeType)if(r(t))for(T in t)y(this,T,t[T]);else y(this,t,g(this,e,n,this.getAttribute(t)))}):this.length&&1===this[0].nodeType?!(n=this[0].getAttribute(t))&&t in this[0]?this[0][t]:n:E},removeAttr:function(t){return this.each(function(){1===this.nodeType&&t.split(" ").forEach(function(t){y(this,t)},this)})},prop:function(t,e){return t=K[t]||t,1 in arguments?this.each(function(n){this[t]=g(this,e,n,this[t])}):this[0]&&this[0][t]},data:function(t,e){var n="data-"+t.replace(F,"-$1").toLowerCase(),i=1 in arguments?this.attr(n,e):this.attr(n);return null!==i?w(i):E},val:function(t){return 0 in arguments?this.each(function(e){this.value=g(this,t,e,this.value)}):this[0]&&(this[0].multiple?j(this[0]).find("option").filter(function(){return this.selected}).pluck("value"):this[0].value)},offset:function(t){if(t)return this.each(function(e){var n=j(this),i=g(this,t,e,n.offset()),r=n.offsetParent().offset(),o={top:i.top-r.top,left:i.left-r.left};"static"==n.css("position")&&(o.position="relative"),n.css(o)});if(!this.length)return null;if(!j.contains(L.documentElement,this[0]))return{top:0,left:0};var e=this[0].getBoundingClientRect();return{left:e.left+window.pageXOffset,top:e.top+window.pageYOffset,width:Math.round(e.width),height:Math.round(e.height)}},css:function(e,n){if(arguments.length<2){var i,r=this[0];if(!r)return;if(i=getComputedStyle(r,""),"string"==typeof e)return r.style[S(e)]||i.getPropertyValue(e);if(Q(e)){var o={};return j.each(e,function(t,e){o[e]=r.style[S(e)]||i.getPropertyValue(e)}),o}}var a="";if("string"==t(e))n||0===n?a=c(e)+":"+f(e,n):this.each(function(){this.style.removeProperty(c(e))});else for(T in e)e[T]||0===e[T]?a+=c(T)+":"+f(T,e[T])+";":this.each(function(){this.style.removeProperty(c(T))});return this.each(function(){this.style.cssText+=";"+a})},index:function(t){return t?this.indexOf(j(t)[0]):this.parent().children().indexOf(this[0])},hasClass:function(t){return t?O.some.call(this,function(t){return this.test(x(t))},l(t)):!1},addClass:function(t){return t?this.each(function(e){if("className"in this){C=[];var n=x(this),i=g(this,t,e,n);i.split(/\s+/g).forEach(function(t){j(this).hasClass(t)||C.push(t)},this),C.length&&x(this,n+(n?" ":"")+C.join(" "))}}):this},removeClass:function(t){return this.each(function(e){if("className"in this){if(t===E)return x(this,"");C=x(this),g(this,t,e,C).split(/\s+/g).forEach(function(t){C=C.replace(l(t)," ")}),x(this,C.trim())}})},toggleClass:function(t,e){return t?this.each(function(n){var i=j(this),r=g(this,t,n,x(this));r.split(/\s+/g).forEach(function(t){(e===E?!i.hasClass(t):e)?i.addClass(t):i.removeClass(t)})}):this},scrollTop:function(t){if(this.length){var e="scrollTop"in this[0];return t===E?e?this[0].scrollTop:this[0].pageYOffset:this.each(e?function(){this.scrollTop=t}:function(){this.scrollTo(this.scrollX,t)})}},scrollLeft:function(t){if(this.length){var e="scrollLeft"in this[0];return t===E?e?this[0].scrollLeft:this[0].pageXOffset:this.each(e?function(){this.scrollLeft=t}:function(){this.scrollTo(t,this.scrollY)})}},position:function(){if(this.length){var t=this[0],e=this.offsetParent(),n=this.offset(),i=q.test(e[0].nodeName)?{top:0,left:0}:e.offset();return n.top-=parseFloat(j(t).css("margin-top"))||0,n.left-=parseFloat(j(t).css("margin-left"))||0,i.top+=parseFloat(j(e[0]).css("border-top-width"))||0,i.left+=parseFloat(j(e[0]).css("border-left-width"))||0,{top:n.top-i.top,left:n.left-i.left}}},offsetParent:function(){return this.map(function(){for(var t=this.offsetParent||L.body;t&&!q.test(t.nodeName)&&"static"==j(t).css("position");)t=t.offsetParent;return t})}},j.fn.detach=j.fn.remove,["width","height"].forEach(function(t){var e=t.replace(/./,function(t){return t[0].toUpperCase()});j.fn[t]=function(r){var o,a=this[0];return r===E?n(a)?a["inner"+e]:i(a)?a.documentElement["scroll"+e]:(o=this.offset())&&o[t]:this.each(function(e){a=j(this),a.css(t,g(this,r,e,a[t]()))})}}),I.forEach(function(e,n){var i=n%2;j.fn[e]=function(){var e,r,o=j.map(arguments,function(n){return e=t(n),"object"==e||"array"==e||null==n?n:W.fragment(n)}),a=this.length>1;return o.length<1?this:this.each(function(t,e){r=i?e:e.parentNode,e=0==n?e.nextSibling:1==n?e.firstChild:2==n?e:null;var s=j.contains(L.documentElement,r);o.forEach(function(t){if(a)t=t.cloneNode(!0);else if(!r)return j(t).remove();r.insertBefore(t,e),s&&b(t,function(t){null==t.nodeName||"SCRIPT"!==t.nodeName.toUpperCase()||t.type&&"text/javascript"!==t.type||t.src||window.eval.call(window,t.innerHTML)})})})},j.fn[i?e+"To":"insert"+(n?"Before":"After")]=function(t){return j(t)[e](this),this}}),W.Z.prototype=d.prototype=j.fn,W.uniq=N,W.deserializeValue=w,j.zepto=W,j}();window.Zepto=Zepto,void 0===window.$&&(window.$=Zepto),function(t){function e(t){return t._zid||(t._zid=h++)}function n(t,n,o,a){if(n=i(n),n.ns)var s=r(n.ns);return(v[e(t)]||[]).filter(function(t){return!(!t||n.e&&t.e!=n.e||n.ns&&!s.test(t.ns)||o&&e(t.fn)!==e(o)||a&&t.sel!=a)})}function i(t){var e=(""+t).split(".");return{e:e[0],ns:e.slice(1).sort().join(" ")}}function r(t){return new RegExp("(?:^| )"+t.replace(" "," .* ?")+"(?: |$)")}function o(t,e){return t.del&&!y&&t.e in x||!!e}function a(t){return w[t]||y&&x[t]||t}function s(n,r,s,u,l,h,p){var d=e(n),m=v[d]||(v[d]=[]);r.split(/\s/).forEach(function(e){if("ready"==e)return t(document).ready(s);var r=i(e);r.fn=s,r.sel=l,r.e in w&&(s=function(e){var n=e.relatedTarget;return!n||n!==this&&!t.contains(this,n)?r.fn.apply(this,arguments):void 0}),r.del=h;var d=h||s;r.proxy=function(t){if(t=c(t),!t.isImmediatePropagationStopped()){t.data=u;var e=d.apply(n,t._args==f?[t]:[t].concat(t._args));return e===!1&&(t.preventDefault(),t.stopPropagation()),e}},r.i=m.length,m.push(r),"addEventListener"in n&&n.addEventListener(a(r.e),r.proxy,o(r,p))})}function u(t,i,r,s,u){var c=e(t);(i||"").split(/\s/).forEach(function(e){n(t,e,r,s).forEach(function(e){delete v[c][e.i],"removeEventListener"in t&&t.removeEventListener(a(e.e),e.proxy,o(e,u))})})}function c(e,n){return(n||!e.isDefaultPrevented)&&(n||(n=e),t.each(j,function(t,i){var r=n[t];e[t]=function(){return this[i]=b,r&&r.apply(n,arguments)},e[i]=E}),(n.defaultPrevented!==f?n.defaultPrevented:"returnValue"in n?n.returnValue===!1:n.getPreventDefault&&n.getPreventDefault())&&(e.isDefaultPrevented=b)),e}function l(t){var e,n={originalEvent:t};for(e in t)T.test(e)||t[e]===f||(n[e]=t[e]);return c(n,t)}var f,h=1,p=Array.prototype.slice,d=t.isFunction,m=function(t){return"string"==typeof t},v={},g={},y="onfocusin"in window,x={focus:"focusin",blur:"focusout"},w={mouseenter:"mouseover",mouseleave:"mouseout"};g.click=g.mousedown=g.mouseup=g.mousemove="MouseEvents",t.event={add:s,remove:u},t.proxy=function(n,i){var r=2 in arguments&&p.call(arguments,2);if(d(n)){var o=function(){return n.apply(i,r?r.concat(p.call(arguments)):arguments)};return o._zid=e(n),o}if(m(i))return r?(r.unshift(n[i],n),t.proxy.apply(null,r)):t.proxy(n[i],n);throw new TypeError("expected function")},t.fn.bind=function(t,e,n){return this.on(t,e,n)},t.fn.unbind=function(t,e){return this.off(t,e)},t.fn.one=function(t,e,n,i){return this.on(t,e,n,i,1)};var b=function(){return!0},E=function(){return!1},T=/^([A-Z]|returnValue$|layer[XY]$)/,j={preventDefault:"isDefaultPrevented",stopImmediatePropagation:"isImmediatePropagationStopped",stopPropagation:"isPropagationStopped"};t.fn.delegate=function(t,e,n){return this.on(e,t,n)},t.fn.undelegate=function(t,e,n){return this.off(e,t,n)},t.fn.live=function(e,n){return t(document.body).delegate(this.selector,e,n),this},t.fn.die=function(e,n){return t(document.body).undelegate(this.selector,e,n),this},t.fn.on=function(e,n,i,r,o){var a,c,h=this;return e&&!m(e)?(t.each(e,function(t,e){h.on(t,n,i,e,o)}),h):(m(n)||d(r)||r===!1||(r=i,i=n,n=f),(r===f||i===!1)&&(r=i,i=f),r===!1&&(r=E),h.each(function(f,h){o&&(a=function(t){return u(h,t.type,r),r.apply(this,arguments)}),n&&(c=function(e){var i,o=t(e.target).closest(n,h).get(0);return o&&o!==h?(i=t.extend(l(e),{currentTarget:o,liveFired:h}),(a||r).apply(o,[i].concat(p.call(arguments,1)))):void 0}),s(h,e,r,i,n,c||a)}))},t.fn.off=function(e,n,i){var r=this;return e&&!m(e)?(t.each(e,function(t,e){r.off(t,n,e)}),r):(m(n)||d(i)||i===!1||(i=n,n=f),i===!1&&(i=E),r.each(function(){u(this,e,i,n)}))},t.fn.trigger=function(e,n){return e=m(e)||t.isPlainObject(e)?t.Event(e):c(e),e._args=n,this.each(function(){e.type in x&&"function"==typeof this[e.type]?this[e.type]():"dispatchEvent"in this?this.dispatchEvent(e):t(this).triggerHandler(e,n)})},t.fn.triggerHandler=function(e,i){var r,o;return this.each(function(a,s){r=l(m(e)?t.Event(e):e),r._args=i,r.target=s,t.each(n(s,e.type||e),function(t,e){return o=e.proxy(r),r.isImmediatePropagationStopped()?!1:void 0})}),o},"focusin focusout focus blur load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select keydown keypress keyup error".split(" ").forEach(function(e){t.fn[e]=function(t){return 0 in arguments?this.bind(e,t):this.trigger(e)}}),t.Event=function(t,e){m(t)||(e=t,t=e.type);var n=document.createEvent(g[t]||"Events"),i=!0;if(e)for(var r in e)"bubbles"==r?i=!!e[r]:n[r]=e[r];return n.initEvent(t,i,!0),c(n)}}(Zepto),function(t){function e(e,n,i){var r=t.Event(n);return t(e).trigger(r,i),!r.isDefaultPrevented()}function n(t,n,i,r){return t.global?e(n||y,i,r):void 0}function i(e){e.global&&0===t.active++&&n(e,null,"ajaxStart")}function r(e){e.global&&!--t.active&&n(e,null,"ajaxStop")}function o(t,e){var i=e.context;return e.beforeSend.call(i,t,e)===!1||n(e,i,"ajaxBeforeSend",[t,e])===!1?!1:void n(e,i,"ajaxSend",[t,e])}function a(t,e,i,r){var o=i.context,a="success";i.success.call(o,t,a,e),r&&r.resolveWith(o,[t,a,e]),n(i,o,"ajaxSuccess",[e,i,t]),u(a,e,i)}function s(t,e,i,r,o){var a=r.context;r.error.call(a,i,e,t),o&&o.rejectWith(a,[i,e,t]),n(r,a,"ajaxError",[i,r,t||e]),u(e,i,r)}function u(t,e,i){var o=i.context;i.complete.call(o,e,t),n(i,o,"ajaxComplete",[e,i]),r(i)}function c(){}function l(t){return t&&(t=t.split(";",2)[0]),t&&(t==T?"html":t==E?"json":w.test(t)?"script":b.test(t)&&"xml")||"text"}function f(t,e){return""==e?t:(t+"&"+e).replace(/[&?]{1,2}/,"?")}function h(e){e.processData&&e.data&&"string"!=t.type(e.data)&&(e.data=t.param(e.data,e.traditional)),!e.data||e.type&&"GET"!=e.type.toUpperCase()||(e.url=f(e.url,e.data),e.data=void 0)}function p(e,n,i,r){return t.isFunction(n)&&(r=i,i=n,n=void 0),t.isFunction(i)||(r=i,i=void 0),{url:e,data:n,success:i,dataType:r}}function d(e,n,i,r){var o,a=t.isArray(n),s=t.isPlainObject(n);t.each(n,function(n,u){o=t.type(u),r&&(n=i?r:r+"["+(s||"object"==o||"array"==o?n:"")+"]"),!r&&a?e.add(u.name,u.value):"array"==o||!i&&"object"==o?d(e,u,i,n):e.add(n,u)})}var m,v,g=0,y=window.document,x=/<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/gi,w=/^(?:text|application)\/javascript/i,b=/^(?:text|application)\/xml/i,E="application/json",T="text/html",j=/^\s*$/,C=y.createElement("a");C.href=window.location.href,t.active=0,t.ajaxJSONP=function(e,n){if(!("type"in e))return t.ajax(e);var i,r,u=e.jsonpCallback,c=(t.isFunction(u)?u():u)||"jsonp"+ ++g,l=y.createElement("script"),f=window[c],h=function(e){t(l).triggerHandler("error",e||"abort")},p={abort:h};return n&&n.promise(p),t(l).on("load error",function(o,u){clearTimeout(r),t(l).off().remove(),"error"!=o.type&&i?a(i[0],p,e,n):s(null,u||"error",p,e,n),window[c]=f,i&&t.isFunction(f)&&f(i[0]),f=i=void 0}),o(p,e)===!1?(h("abort"),p):(window[c]=function(){i=arguments},l.src=e.url.replace(/\?(.+)=\?/,"?$1="+c),y.head.appendChild(l),e.timeout>0&&(r=setTimeout(function(){h("timeout")},e.timeout)),p)},t.ajaxSettings={type:"GET",beforeSend:c,success:c,error:c,complete:c,context:null,global:!0,xhr:function(){return new window.XMLHttpRequest},accepts:{script:"text/javascript, application/javascript, application/x-javascript",json:E,xml:"application/xml, text/xml",html:T,text:"text/plain"},crossDomain:!1,timeout:0,processData:!0,cache:!0},t.ajax=function(e){var n,r,u=t.extend({},e||{}),p=t.Deferred&&t.Deferred();for(m in t.ajaxSettings)void 0===u[m]&&(u[m]=t.ajaxSettings[m]);i(u),u.crossDomain||(n=y.createElement("a"),n.href=u.url,n.href=n.href,u.crossDomain=C.protocol+"//"+C.host!=n.protocol+"//"+n.host),u.url||(u.url=window.location.toString()),(r=u.url.indexOf("#"))>-1&&(u.url=u.url.slice(0,r)),h(u);var d=u.dataType,g=/\?.+=\?/.test(u.url);if(g&&(d="jsonp"),u.cache!==!1&&(e&&e.cache===!0||"script"!=d&&"jsonp"!=d)||(u.url=f(u.url,"_="+Date.now())),"jsonp"==d)return g||(u.url=f(u.url,u.jsonp?u.jsonp+"=?":u.jsonp===!1?"":"callback=?")),t.ajaxJSONP(u,p);var x,w=u.accepts[d],b={},E=function(t,e){b[t.toLowerCase()]=[t,e]},T=/^([\w-]+:)\/\//.test(u.url)?RegExp.$1:window.location.protocol,S=u.xhr(),N=S.setRequestHeader;if(p&&p.promise(S),u.crossDomain||E("X-Requested-With","XMLHttpRequest"),E("Accept",w||"*/*"),(w=u.mimeType||w)&&(w.indexOf(",")>-1&&(w=w.split(",",2)[0]),S.overrideMimeType&&S.overrideMimeType(w)),(u.contentType||u.contentType!==!1&&u.data&&"GET"!=u.type.toUpperCase())&&E("Content-Type",u.contentType||"application/x-www-form-urlencoded"),u.headers)for(v in u.headers)E(v,u.headers[v]);if(S.setRequestHeader=E,S.onreadystatechange=function(){if(4==S.readyState){S.onreadystatechange=c,clearTimeout(x);var e,n=!1;if(S.status>=200&&S.status<300||304==S.status||0==S.status&&"file:"==T){if(d=d||l(u.mimeType||S.getResponseHeader("content-type")),"arraybuffer"==S.responseType||"blob"==S.responseType)e=S.response;else{e=S.responseText;try{"script"==d?(1,eval)(e):"xml"==d?e=S.responseXML:"json"==d&&(e=j.test(e)?null:t.parseJSON(e))}catch(i){n=i}if(n)return s(n,"parsererror",S,u,p)}a(e,S,u,p)}else s(S.statusText||null,S.status?"error":"abort",S,u,p)}},o(S,u)===!1)return S.abort(),s(null,"abort",S,u,p),S;if(u.xhrFields)for(v in u.xhrFields)S[v]=u.xhrFields[v];var O="async"in u?u.async:!0;S.open(u.type,u.url,O,u.username,u.password);for(v in b)N.apply(S,b[v]);return u.timeout>0&&(x=setTimeout(function(){S.onreadystatechange=c,S.abort(),s(null,"timeout",S,u,p)},u.timeout)),S.send(u.data?u.data:null),S},t.get=function(){return t.ajax(p.apply(null,arguments))},t.post=function(){var e=p.apply(null,arguments);return e.type="POST",t.ajax(e)},t.getJSON=function(){var e=p.apply(null,arguments);return e.dataType="json",t.ajax(e)},t.fn.load=function(e,n,i){if(!this.length)return this;var r,o=this,a=e.split(/\s/),s=p(e,n,i),u=s.success;return a.length>1&&(s.url=a[0],r=a[1]),s.success=function(e){o.html(r?t("<div>").html(e.replace(x,"")).find(r):e),u&&u.apply(o,arguments)},t.ajax(s),this};var S=encodeURIComponent;t.param=function(e,n){var i=[];return i.add=function(e,n){t.isFunction(n)&&(n=n()),null==n&&(n=""),this.push(S(e)+"="+S(n))},d(i,e,n),i.join("&").replace(/%20/g,"+")}}(Zepto),function(t){t.fn.serializeArray=function(){var e,n,i=[],r=function(t){return t.forEach?t.forEach(r):void i.push({name:e,value:t})};return this[0]&&t.each(this[0].elements,function(i,o){n=o.type,e=o.name,e&&"fieldset"!=o.nodeName.toLowerCase()&&!o.disabled&&"submit"!=n&&"reset"!=n&&"button"!=n&&"file"!=n&&("radio"!=n&&"checkbox"!=n||o.checked)&&r(t(o).val())}),i},t.fn.serialize=function(){var t=[];return this.serializeArray().forEach(function(e){t.push(encodeURIComponent(e.name)+"="+encodeURIComponent(e.value))}),t.join("&")},t.fn.submit=function(e){if(0 in arguments)this.bind("submit",e);else if(this.length){var n=t.Event("submit");this.eq(0).trigger(n),n.isDefaultPrevented()||this.get(0).submit()}return this}}(Zepto),function(){try{getComputedStyle(void 0)}catch(t){var e=getComputedStyle;window.getComputedStyle=function(t){try{return e(t)}catch(n){return null}}}}(),function(t,e){function n(t){return t.replace(/([a-z])([A-Z])/,"$1-$2").toLowerCase()}function i(t){return r?r+t:t.toLowerCase()}var r,o,a,s,u,c,l,f,h,p,d="",m={Webkit:"webkit",Moz:"",O:"o"},v=document.createElement("div"),g=/^((translate|rotate|scale)(X|Y|Z|3d)?|matrix(3d)?|perspective|skew(X|Y)?)$/i,y={};t.each(m,function(t,n){return v.style[t+"TransitionProperty"]!==e?(d="-"+t.toLowerCase()+"-",r=n,!1):void 0}),o=d+"transform",y[a=d+"transition-property"]=y[s=d+"transition-duration"]=y[c=d+"transition-delay"]=y[u=d+"transition-timing-function"]=y[l=d+"animation-name"]=y[f=d+"animation-duration"]=y[p=d+"animation-delay"]=y[h=d+"animation-timing-function"]="",t.fx={off:r===e&&v.style.transitionProperty===e,speeds:{_default:400,fast:200,slow:600},cssPrefix:d,transitionEnd:i("TransitionEnd"),animationEnd:i("AnimationEnd")},t.fn.animate=function(n,i,r,o,a){return t.isFunction(i)&&(o=i,r=e,i=e),t.isFunction(r)&&(o=r,r=e),t.isPlainObject(i)&&(r=i.easing,o=i.complete,a=i.delay,i=i.duration),i&&(i=("number"==typeof i?i:t.fx.speeds[i]||t.fx.speeds._default)/1e3),a&&(a=parseFloat(a)/1e3),this.anim(n,i,r,o,a)},t.fn.anim=function(i,r,d,m,v){var x,w,b,E={},T="",j=this,C=t.fx.transitionEnd,S=!1;if(r===e&&(r=t.fx.speeds._default/1e3),v===e&&(v=0),t.fx.off&&(r=0),"string"==typeof i)E[l]=i,E[f]=r+"s",E[p]=v+"s",E[h]=d||"linear",C=t.fx.animationEnd;else{w=[];for(x in i)g.test(x)?T+=x+"("+i[x]+") ":(E[x]=i[x],w.push(n(x)));T&&(E[o]=T,w.push(o)),r>0&&"object"==typeof i&&(E[a]=w.join(", "),E[s]=r+"s",E[c]=v+"s",E[u]=d||"linear")}return b=function(e){if("undefined"!=typeof e){if(e.target!==e.currentTarget)return;t(e.target).unbind(C,b)}else t(this).unbind(C,b);S=!0,t(this).css(y),m&&m.call(this)},r>0&&(this.bind(C,b),setTimeout(function(){S||b.call(j)},1e3*(r+v)+25)),this.size()&&this.get(0).clientLeft,this.css(E),0>=r&&setTimeout(function(){j.each(function(){b.call(this)})},0),this},v=null}(Zepto),function(t,e){function n(n,i,r,o,a){"function"!=typeof i||a||(a=i,i=e);var s={opacity:r};return o&&(s.scale=o,n.css(t.fx.cssPrefix+"transform-origin","0 0")),n.animate(s,i,null,a)}function i(e,i,r,o){return n(e,i,0,r,function(){a.call(t(this)),o&&o.call(this)})}var r=window.document,o=(r.documentElement,t.fn.show),a=t.fn.hide,s=t.fn.toggle;t.fn.show=function(t,i){return o.call(this),t===e?t=0:this.css("opacity",0),n(this,t,1,"1,1",i)},t.fn.hide=function(t,n){return t===e?a.call(this):i(this,t,"0,0",n)},t.fn.toggle=function(n,i){return n===e||"boolean"==typeof n?s.call(this,n):this.each(function(){var e=t(this);e["none"==e.css("display")?"show":"hide"](n,i)})},t.fn.fadeTo=function(t,e,i){return n(this,t,e,null,i)},t.fn.fadeIn=function(t,e){var n=this.css("opacity");return n>0?this.css("opacity",0):n=1,o.call(this).fadeTo(t,n,e)},t.fn.fadeOut=function(t,e){return i(this,t,null,e)},t.fn.fadeToggle=function(e,n){return this.each(function(){var i=t(this);i[0==i.css("opacity")||"none"==i.css("display")?"fadeIn":"fadeOut"](e,n)})}}(Zepto),function(t){function e(e,i){var u=e[s],c=u&&r[u];if(void 0===i)return c||n(e);if(c){if(i in c)return c[i];var l=a(i);if(l in c)return c[l]}return o.call(t(e),i)}function n(e,n,o){var u=e[s]||(e[s]=++t.uuid),c=r[u]||(r[u]=i(e));return void 0!==n&&(c[a(n)]=o),c}function i(e){var n={};return t.each(e.attributes||u,function(e,i){0==i.name.indexOf("data-")&&(n[a(i.name.replace("data-",""))]=t.zepto.deserializeValue(i.value))}),n}var r={},o=t.fn.data,a=t.camelCase,s=t.expando="Zepto"+ +new Date,u=[];t.fn.data=function(i,r){return void 0===r?t.isPlainObject(i)?this.each(function(e,r){t.each(i,function(t,e){n(r,t,e)})}):0 in this?e(this[0],i):void 0:this.each(function(){n(this,i,r)})},t.fn.removeData=function(e){return"string"==typeof e&&(e=e.split(/\s+/)),this.each(function(){var n=this[s],i=n&&r[n];i&&t.each(e||i,function(t){delete i[e?a(this):t]})})},["remove","empty"].forEach(function(e){var n=t.fn[e];t.fn[e]=function(){var t=this.find("*");return"remove"===e&&(t=t.add(this)),t.removeData(),n.call(this)}})}(Zepto),function(t){function e(e){return e=t(e),!(!e.width()&&!e.height())&&"none"!==e.css("display")}function n(t,e){t=t.replace(/=#\]/g,'="#"]');var n,i,r=s.exec(t);if(r&&r[2]in a&&(n=a[r[2]],i=r[3],t=r[1],i)){var o=Number(i);i=isNaN(o)?i.replace(/^["']|["']$/g,""):o}return e(t,n,i)}var i=t.zepto,r=i.qsa,o=i.matches,a=t.expr[":"]={visible:function(){return e(this)?this:void 0},hidden:function(){return e(this)?void 0:this},selected:function(){return this.selected?this:void 0},checked:function(){return this.checked?this:void 0},parent:function(){return this.parentNode},first:function(t){return 0===t?this:void 0},last:function(t,e){return t===e.length-1?this:void 0},eq:function(t,e,n){return t===n?this:void 0},contains:function(e,n,i){return t(this).text().indexOf(i)>-1?this:void 0},has:function(t,e,n){return i.qsa(this,n).length?this:void 0}},s=new RegExp("(.*):(\\w+)(?:\\(([^)]+)\\))?$\\s*"),u=/^\s*>/,c="Zepto"+ +new Date;i.qsa=function(e,o){return n(o,function(n,a,s){try{var l;!n&&a?n="*":u.test(n)&&(l=t(e).addClass(c),n="."+c+" "+n);var f=r(e,n)}catch(h){throw console.error("error performing selector: %o",o),h}finally{l&&l.removeClass(c)}return a?i.uniq(t.map(f,function(t,e){return a.call(t,e,f,s)})):f})},i.matches=function(t,e){return n(e,function(e,n,i){return!(e&&!o(t,e)||n&&n.call(t,null,i)!==t)})}}(Zepto),function(t){function e(t,e,n,i){return Math.abs(t-e)>=Math.abs(n-i)?t-e>0?"Left":"Right":n-i>0?"Up":"Down"}function n(){l=null,h.last&&(h.el.trigger("longTap"),h={})}function i(){l&&clearTimeout(l),l=null}function r(){s&&clearTimeout(s),u&&clearTimeout(u),c&&clearTimeout(c),l&&clearTimeout(l),s=u=c=l=null,h={}}function o(t){return("touch"==t.pointerType||t.pointerType==t.MSPOINTER_TYPE_TOUCH)&&t.isPrimary}function a(t,e){return t.type=="pointer"+e||t.type.toLowerCase()=="mspointer"+e}var s,u,c,l,f,h={},p=750;t(document).ready(function(){var d,m,v,g,y=0,x=0;"MSGesture"in window&&(f=new MSGesture,f.target=document.body),t(document).bind("MSGestureEnd",function(t){var e=t.velocityX>1?"Right":t.velocityX<-1?"Left":t.velocityY>1?"Down":t.velocityY<-1?"Up":null;e&&(h.el.trigger("swipe"),h.el.trigger("swipe"+e))}).on("touchstart MSPointerDown pointerdown",function(e){(!(g=a(e,"down"))||o(e))&&(v=g?e:e.touches[0],e.touches&&1===e.touches.length&&h.x2&&(h.x2=void 0,h.y2=void 0),d=Date.now(),m=d-(h.last||d),h.el=t("tagName"in v.target?v.target:v.target.parentNode),s&&clearTimeout(s),h.x1=v.pageX,h.y1=v.pageY,m>0&&250>=m&&(h.isDoubleTap=!0),h.last=d,l=setTimeout(n,p),f&&g&&f.addPointer(e.pointerId))
}).on("touchmove MSPointerMove pointermove",function(t){(!(g=a(t,"move"))||o(t))&&(v=g?t:t.touches[0],i(),h.x2=v.pageX,h.y2=v.pageY,y+=Math.abs(h.x1-h.x2),x+=Math.abs(h.y1-h.y2))}).on("touchend MSPointerUp pointerup",function(n){(!(g=a(n,"up"))||o(n))&&(i(),h.x2&&Math.abs(h.x1-h.x2)>30||h.y2&&Math.abs(h.y1-h.y2)>30?c=setTimeout(function(){h.el.trigger("swipe"),h.el.trigger("swipe"+e(h.x1,h.x2,h.y1,h.y2)),h={}},0):"last"in h&&(30>y&&30>x?u=setTimeout(function(){var e=t.Event("tap");e.cancelTouch=r,h.el.trigger(e),h.isDoubleTap?(h.el&&h.el.trigger("doubleTap"),h={}):s=setTimeout(function(){s=null,h.el&&h.el.trigger("singleTap"),h={}},250)},0):h={}),y=x=0)}).on("touchcancel MSPointerCancel pointercancel",r),t(window).on("scroll",r)}),["swipe","swipeLeft","swipeRight","swipeUp","swipeDown","doubleTap","tap","singleTap","longTap"].forEach(function(e){t.fn[e]=function(t){return this.on(e,t)}})}(Zepto),function(t){t.fn.end=function(){return this.prevObject||t()},t.fn.andSelf=function(){return this.add(this.prevObject||t())},"filter,add,not,eq,first,last,find,closest,parents,parent,children,siblings".split(",").forEach(function(e){var n=t.fn[e];t.fn[e]=function(){var t=n.apply(this,arguments);return t.prevObject=this,t}})}(Zepto);</script>
<script type="text/javascript" src="http://sale.qccr.com/js/end.js"></script>
<style type="text/css">
.swiper-container {
	margin: 0 auto;
	position: relative;
	overflow: hidden;
	z-index: 1
}

.swiper-container-no-flexbox .swiper-slide {
	float: left
}

.swiper-container-vertical>.swiper-wrapper {
	-webkit-box-orient: vertical;
	-moz-box-orient: vertical;
	-ms-flex-direction: column;
	-webkit-flex-direction: column;
	flex-direction: column
}

.swiper-wrapper {
	position: relative;
	width: 100%;
	height: 100%;
	z-index: 1;
	display: -webkit-box;
	display: -moz-box;
	display: -ms-flexbox;
	display: -webkit-flex;
	display: flex;
	-webkit-transition-property: -webkit-transform;
	-moz-transition-property: -moz-transform;
	-o-transition-property: -o-transform;
	-ms-transition-property: -ms-transform;
	transition-property: transform;
	-webkit-box-sizing: content-box;
	-moz-box-sizing: content-box;
	box-sizing: content-box
}

.swiper-container-android .swiper-slide, .swiper-wrapper {
	-webkit-transform: translate3d(0, 0, 0);
	-moz-transform: translate3d(0, 0, 0);
	-o-transform: translate(0, 0);
	-ms-transform: translate3d(0, 0, 0);
	transform: translate3d(0, 0, 0)
}

.swiper-container-multirow>.swiper-wrapper {
	-webkit-box-lines: multiple;
	-moz-box-lines: multiple;
	-ms-flex-wrap: wrap;
	-webkit-flex-wrap: wrap;
	flex-wrap: wrap
}

.swiper-container-free-mode>.swiper-wrapper {
	-webkit-transition-timing-function: ease-out;
	-moz-transition-timing-function: ease-out;
	-ms-transition-timing-function: ease-out;
	-o-transition-timing-function: ease-out;
	transition-timing-function: ease-out;
	margin: 0 auto
}

.swiper-slide {
	-webkit-flex-shrink: 0;
	-ms-flex: 0 0 auto;
	flex-shrink: 0;
	width: 100%;
	height: 100%;
	position: relative
}

.swiper-container-autoheight, .swiper-container-autoheight .swiper-slide
	{
	height: auto
}

.swiper-container-autoheight .swiper-wrapper {
	-webkit-box-align: start;
	-ms-flex-align: start;
	-webkit-align-items: flex-start;
	align-items: flex-start;
	-webkit-transition-property: -webkit-transform, height;
	-moz-transition-property: -moz-transform;
	-o-transition-property: -o-transform;
	-ms-transition-property: -ms-transform;
	transition-property: transform, height
}

.swiper-container .swiper-notification {
	position: absolute;
	left: 0;
	top: 0;
	pointer-events: none;
	opacity: 0;
	z-index: -1000
}

.swiper-wp8-horizontal {
	-ms-touch-action: pan-y;
	touch-action: pan-y
}

.swiper-wp8-vertical {
	-ms-touch-action: pan-x;
	touch-action: pan-x
}

.swiper-button-next, .swiper-button-prev {
	position: absolute;
	top: 50%;
	width: 27px;
	height: 44px;
	margin-top: -22px;
	z-index: 10;
	cursor: pointer;
	-moz-background-size: 27px 44px;
	-webkit-background-size: 27px 44px;
	background-size: 27px 44px;
	background-position: center;
	background-repeat: no-repeat
}

.swiper-button-next.swiper-button-disabled, .swiper-button-prev.swiper-button-disabled
	{
	opacity: .35;
	cursor: auto;
	pointer-events: none
}

.swiper-button-prev, .swiper-container-rtl .swiper-button-next {
	background-image:
		url("data:image/svg+xml;charset=utf-8,%3Csvg%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20viewBox%3D'0%200%2027%2044'%3E%3Cpath%20d%3D'M0%2C22L22%2C0l2.1%2C2.1L4.2%2C22l19.9%2C19.9L22%2C44L0%2C22L0%2C22L0%2C22z'%20fill%3D'%23007aff'%2F%3E%3C%2Fsvg%3E");
	left: 10px;
	right: auto
}

.swiper-button-prev.swiper-button-black, .swiper-container-rtl .swiper-button-next.swiper-button-black
	{
	background-image:
		url("data:image/svg+xml;charset=utf-8,%3Csvg%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20viewBox%3D'0%200%2027%2044'%3E%3Cpath%20d%3D'M0%2C22L22%2C0l2.1%2C2.1L4.2%2C22l19.9%2C19.9L22%2C44L0%2C22L0%2C22L0%2C22z'%20fill%3D'%23000000'%2F%3E%3C%2Fsvg%3E")
}

.swiper-button-prev.swiper-button-white, .swiper-container-rtl .swiper-button-next.swiper-button-white
	{
	background-image:
		url("data:image/svg+xml;charset=utf-8,%3Csvg%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20viewBox%3D'0%200%2027%2044'%3E%3Cpath%20d%3D'M0%2C22L22%2C0l2.1%2C2.1L4.2%2C22l19.9%2C19.9L22%2C44L0%2C22L0%2C22L0%2C22z'%20fill%3D'%23ffffff'%2F%3E%3C%2Fsvg%3E")
}

.swiper-button-next, .swiper-container-rtl .swiper-button-prev {
	background-image:
		url("data:image/svg+xml;charset=utf-8,%3Csvg%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20viewBox%3D'0%200%2027%2044'%3E%3Cpath%20d%3D'M27%2C22L27%2C22L5%2C44l-2.1-2.1L22.8%2C22L2.9%2C2.1L5%2C0L27%2C22L27%2C22z'%20fill%3D'%23007aff'%2F%3E%3C%2Fsvg%3E");
	right: 10px;
	left: auto
}

.swiper-button-next.swiper-button-black, .swiper-container-rtl .swiper-button-prev.swiper-button-black
	{
	background-image:
		url("data:image/svg+xml;charset=utf-8,%3Csvg%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20viewBox%3D'0%200%2027%2044'%3E%3Cpath%20d%3D'M27%2C22L27%2C22L5%2C44l-2.1-2.1L22.8%2C22L2.9%2C2.1L5%2C0L27%2C22L27%2C22z'%20fill%3D'%23000000'%2F%3E%3C%2Fsvg%3E")
}

.swiper-button-next.swiper-button-white, .swiper-container-rtl .swiper-button-prev.swiper-button-white
	{
	background-image:
		url("data:image/svg+xml;charset=utf-8,%3Csvg%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20viewBox%3D'0%200%2027%2044'%3E%3Cpath%20d%3D'M27%2C22L27%2C22L5%2C44l-2.1-2.1L22.8%2C22L2.9%2C2.1L5%2C0L27%2C22L27%2C22z'%20fill%3D'%23ffffff'%2F%3E%3C%2Fsvg%3E")
}

.swiper-pagination {
	position: absolute;
	text-align: center;
	-webkit-transition: .3s;
	-moz-transition: .3s;
	-o-transition: .3s;
	transition: .3s;
	-webkit-transform: translate3d(0, 0, 0);
	-ms-transform: translate3d(0, 0, 0);
	-o-transform: translate3d(0, 0, 0);
	transform: translate3d(0, 0, 0);
	z-index: 10
}

.swiper-pagination.swiper-pagination-hidden {
	opacity: 0
}

.swiper-container-horizontal>.swiper-pagination-bullets,
	.swiper-pagination-custom, .swiper-pagination-fraction {
	bottom: 10px;
	left: 0;
	width: 100%
}

.swiper-pagination-bullet {
	width: 8px;
	height: 8px;
	display: inline-block;
	border-radius: 100%;
	background: #000;
	opacity: .2
}

button.swiper-pagination-bullet {
	border: 0;
	margin: 0;
	padding: 0;
	box-shadow: none;
	-moz-appearance: none;
	-ms-appearance: none;
	-webkit-appearance: none;
	appearance: none
}

.swiper-pagination-clickable .swiper-pagination-bullet {
	cursor: pointer
}

.swiper-pagination-white .swiper-pagination-bullet {
	background: #fff
}

.swiper-pagination-bullet-active {
	opacity: 1;
	background: #007aff
}

.swiper-pagination-white .swiper-pagination-bullet-active {
	background: #fff
}

.swiper-pagination-black .swiper-pagination-bullet-active {
	background: #000
}

.swiper-container-vertical>.swiper-pagination-bullets {
	right: 10px;
	top: 50%;
	-webkit-transform: translate3d(0, -50%, 0);
	-moz-transform: translate3d(0, -50%, 0);
	-o-transform: translate(0, -50%);
	-ms-transform: translate3d(0, -50%, 0);
	transform: translate3d(0, -50%, 0)
}

.swiper-container-vertical>.swiper-pagination-bullets .swiper-pagination-bullet
	{
	margin: 5px 0;
	display: block
}

.swiper-container-horizontal>.swiper-pagination-bullets .swiper-pagination-bullet
	{
	margin: 0 5px
}

.swiper-pagination-progress {
	background: rgba(0, 0, 0, .25);
	position: absolute
}

.swiper-pagination-progress .swiper-pagination-progressbar {
	background: #007aff;
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	-webkit-transform: scale(0);
	-ms-transform: scale(0);
	-o-transform: scale(0);
	transform: scale(0);
	-webkit-transform-origin: left top;
	-moz-transform-origin: left top;
	-ms-transform-origin: left top;
	-o-transform-origin: left top;
	transform-origin: left top
}

.swiper-container-rtl .swiper-pagination-progress .swiper-pagination-progressbar
	{
	-webkit-transform-origin: right top;
	-moz-transform-origin: right top;
	-ms-transform-origin: right top;
	-o-transform-origin: right top;
	transform-origin: right top
}

.swiper-container-horizontal>.swiper-pagination-progress {
	width: 100%;
	height: 4px;
	left: 0;
	top: 0
}

.swiper-container-vertical>.swiper-pagination-progress {
	width: 4px;
	height: 100%;
	left: 0;
	top: 0
}

.swiper-pagination-progress.swiper-pagination-white {
	background: rgba(255, 255, 255, .5)
}

.swiper-pagination-progress.swiper-pagination-white .swiper-pagination-progressbar
	{
	background: #fff
}

.swiper-pagination-progress.swiper-pagination-black .swiper-pagination-progressbar
	{
	background: #000
}

.swiper-container-3d {
	-webkit-perspective: 1200px;
	-moz-perspective: 1200px;
	-o-perspective: 1200px;
	perspective: 1200px
}

.swiper-container-3d .swiper-cube-shadow, .swiper-container-3d .swiper-slide,
	.swiper-container-3d .swiper-slide-shadow-bottom, .swiper-container-3d .swiper-slide-shadow-left,
	.swiper-container-3d .swiper-slide-shadow-right, .swiper-container-3d .swiper-slide-shadow-top,
	.swiper-container-3d .swiper-wrapper {
	-webkit-transform-style: preserve-3d;
	-moz-transform-style: preserve-3d;
	-ms-transform-style: preserve-3d;
	transform-style: preserve-3d
}

.swiper-container-3d .swiper-slide-shadow-bottom, .swiper-container-3d .swiper-slide-shadow-left,
	.swiper-container-3d .swiper-slide-shadow-right, .swiper-container-3d .swiper-slide-shadow-top
	{
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	pointer-events: none;
	z-index: 10
}

.swiper-container-3d .swiper-slide-shadow-left {
	background-image: -webkit-gradient(linear, left top, right top, from(rgba(0, 0, 0, .5)),
		to(rgba(0, 0, 0, 0)));
	background-image: -webkit-linear-gradient(right, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: -moz-linear-gradient(right, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: -o-linear-gradient(right, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: linear-gradient(to left, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0))
}

.swiper-container-3d .swiper-slide-shadow-right {
	background-image: -webkit-gradient(linear, right top, left top, from(rgba(0, 0, 0, .5)),
		to(rgba(0, 0, 0, 0)));
	background-image: -webkit-linear-gradient(left, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: -moz-linear-gradient(left, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: -o-linear-gradient(left, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: linear-gradient(to right, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0))
}

.swiper-container-3d .swiper-slide-shadow-top {
	background-image: -webkit-gradient(linear, left top, left bottom, from(rgba(0, 0, 0, .5)),
		to(rgba(0, 0, 0, 0)));
	background-image: -webkit-linear-gradient(bottom, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: -moz-linear-gradient(bottom, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: -o-linear-gradient(bottom, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: linear-gradient(to top, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0))
}

.swiper-container-3d .swiper-slide-shadow-bottom {
	background-image: -webkit-gradient(linear, left bottom, left top, from(rgba(0, 0, 0, .5)),
		to(rgba(0, 0, 0, 0)));
	background-image: -webkit-linear-gradient(top, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: -moz-linear-gradient(top, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: -o-linear-gradient(top, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0));
	background-image: linear-gradient(to bottom, rgba(0, 0, 0, .5),
		rgba(0, 0, 0, 0))
}

.swiper-container-coverflow .swiper-wrapper, .swiper-container-flip .swiper-wrapper
	{
	-ms-perspective: 1200px
}

.swiper-container-cube, .swiper-container-flip {
	overflow: visible
}

.swiper-container-cube .swiper-slide, .swiper-container-flip .swiper-slide
	{
	pointer-events: none;
	-webkit-backface-visibility: hidden;
	-moz-backface-visibility: hidden;
	-ms-backface-visibility: hidden;
	backface-visibility: hidden;
	z-index: 1
}

.swiper-container-cube .swiper-slide .swiper-slide,
	.swiper-container-flip .swiper-slide .swiper-slide {
	pointer-events: none
}

.swiper-container-cube .swiper-slide-active, .swiper-container-cube .swiper-slide-active .swiper-slide-active,
	.swiper-container-flip .swiper-slide-active, .swiper-container-flip .swiper-slide-active .swiper-slide-active
	{
	pointer-events: auto
}

.swiper-container-cube .swiper-slide-shadow-bottom,
	.swiper-container-cube .swiper-slide-shadow-left,
	.swiper-container-cube .swiper-slide-shadow-right,
	.swiper-container-cube .swiper-slide-shadow-top, .swiper-container-flip .swiper-slide-shadow-bottom,
	.swiper-container-flip .swiper-slide-shadow-left,
	.swiper-container-flip .swiper-slide-shadow-right,
	.swiper-container-flip .swiper-slide-shadow-top {
	z-index: 0;
	-webkit-backface-visibility: hidden;
	-moz-backface-visibility: hidden;
	-ms-backface-visibility: hidden;
	backface-visibility: hidden
}

.swiper-container-cube .swiper-slide {
	visibility: hidden;
	-webkit-transform-origin: 0 0;
	-moz-transform-origin: 0 0;
	-ms-transform-origin: 0 0;
	transform-origin: 0 0;
	width: 100%;
	height: 100%
}

.swiper-container-cube.swiper-container-rtl .swiper-slide {
	-webkit-transform-origin: 100% 0;
	-moz-transform-origin: 100% 0;
	-ms-transform-origin: 100% 0;
	transform-origin: 100% 0
}

.swiper-container-cube .swiper-slide-active, .swiper-container-cube .swiper-slide-next,
	.swiper-container-cube .swiper-slide-next+.swiper-slide,
	.swiper-container-cube .swiper-slide-prev {
	pointer-events: auto;
	visibility: visible
}

.swiper-container-cube .swiper-cube-shadow {
	position: absolute;
	left: 0;
	bottom: 0;
	width: 100%;
	height: 100%;
	background: #000;
	opacity: .6;
	-webkit-filter: blur(50px);
	filter: blur(50px);
	z-index: 0
}

.swiper-container-fade.swiper-container-free-mode .swiper-slide {
	-webkit-transition-timing-function: ease-out;
	-moz-transition-timing-function: ease-out;
	-ms-transition-timing-function: ease-out;
	-o-transition-timing-function: ease-out;
	transition-timing-function: ease-out
}

.swiper-container-fade .swiper-slide {
	pointer-events: none;
	-webkit-transition-property: opacity;
	-moz-transition-property: opacity;
	-o-transition-property: opacity;
	transition-property: opacity
}

.swiper-container-fade .swiper-slide .swiper-slide {
	pointer-events: none
}

.swiper-container-fade .swiper-slide-active, .swiper-container-fade .swiper-slide-active .swiper-slide-active
	{
	pointer-events: auto
}

.swiper-scrollbar {
	border-radius: 10px;
	position: relative;
	-ms-touch-action: none;
	background: rgba(0, 0, 0, .1)
}

.swiper-container-horizontal>.swiper-scrollbar {
	position: absolute;
	left: 1%;
	bottom: 3px;
	z-index: 50;
	height: 5px;
	width: 98%
}

.swiper-container-vertical>.swiper-scrollbar {
	position: absolute;
	right: 3px;
	top: 1%;
	z-index: 50;
	width: 5px;
	height: 98%
}

.swiper-scrollbar-drag {
	height: 100%;
	width: 100%;
	position: relative;
	background: rgba(0, 0, 0, .5);
	border-radius: 10px;
	left: 0;
	top: 0
}

.swiper-scrollbar-cursor-drag {
	cursor: move
}

.swiper-lazy-preloader {
	width: 42px;
	height: 42px;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -21px;
	margin-top: -21px;
	z-index: 10;
	-webkit-transform-origin: 50%;
	-moz-transform-origin: 50%;
	transform-origin: 50%;
	-webkit-animation: swiper-preloader-spin 1s steps(12, end) infinite;
	-moz-animation: swiper-preloader-spin 1s steps(12, end) infinite;
	animation: swiper-preloader-spin 1s steps(12, end) infinite
}

.swiper-lazy-preloader:after {
	display: block;
	content: "";
	width: 100%;
	height: 100%;
	background-image:
		url("data:image/svg+xml;charset=utf-8,%3Csvg%20viewBox%3D'0%200%20120%20120'%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20xmlns%3Axlink%3D'http%3A%2F%2Fwww.w3.org%2F1999%2Fxlink'%3E%3Cdefs%3E%3Cline%20id%3D'l'%20x1%3D'60'%20x2%3D'60'%20y1%3D'7'%20y2%3D'27'%20stroke%3D'%236c6c6c'%20stroke-width%3D'11'%20stroke-linecap%3D'round'%2F%3E%3C%2Fdefs%3E%3Cg%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%20transform%3D'rotate(30%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%20transform%3D'rotate(60%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%20transform%3D'rotate(90%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%20transform%3D'rotate(120%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%20transform%3D'rotate(150%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.37'%20transform%3D'rotate(180%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.46'%20transform%3D'rotate(210%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.56'%20transform%3D'rotate(240%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.66'%20transform%3D'rotate(270%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.75'%20transform%3D'rotate(300%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.85'%20transform%3D'rotate(330%2060%2C60)'%2F%3E%3C%2Fg%3E%3C%2Fsvg%3E");
	background-position: 50%;
	-webkit-background-size: 100%;
	background-size: 100%;
	background-repeat: no-repeat
}

.swiper-lazy-preloader-white:after {
	background-image:
		url("data:image/svg+xml;charset=utf-8,%3Csvg%20viewBox%3D'0%200%20120%20120'%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20xmlns%3Axlink%3D'http%3A%2F%2Fwww.w3.org%2F1999%2Fxlink'%3E%3Cdefs%3E%3Cline%20id%3D'l'%20x1%3D'60'%20x2%3D'60'%20y1%3D'7'%20y2%3D'27'%20stroke%3D'%23fff'%20stroke-width%3D'11'%20stroke-linecap%3D'round'%2F%3E%3C%2Fdefs%3E%3Cg%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%20transform%3D'rotate(30%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%20transform%3D'rotate(60%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%20transform%3D'rotate(90%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%20transform%3D'rotate(120%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.27'%20transform%3D'rotate(150%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.37'%20transform%3D'rotate(180%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.46'%20transform%3D'rotate(210%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.56'%20transform%3D'rotate(240%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.66'%20transform%3D'rotate(270%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.75'%20transform%3D'rotate(300%2060%2C60)'%2F%3E%3Cuse%20xlink%3Ahref%3D'%23l'%20opacity%3D'.85'%20transform%3D'rotate(330%2060%2C60)'%2F%3E%3C%2Fg%3E%3C%2Fsvg%3E")
}

@
-webkit-keyframes swiper-preloader-spin { 100%{
	-webkit-transform: rotate(360deg)
}

}
@
keyframes swiper-preloader-spin { 100%{
	transform: rotate(360deg)
}
}
</style>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0)
}

body {
	background: #efeff4
}

body, button, input, select, textarea {
	font: 12px/1.5 hiragino sans gb, microsoft yahei, simsun, sans-serif
}

img {
	border: 0 0
}

ul, li {
	list-style: none
}

a {
	text-decoration: none
}

em, i, h1, h2, h3, h4, h5 {
	font-style: normal;
	font-weight: 400
}

.clear {
	clear: both;
	overflow: hidden
}

.load {
	width: 4rem;
	height: 2rem;
	background: url(//sale.qccr.com/appstatic/carmall/images/load.png)
		no-repeat center center;
	background-size: 100%;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-left: -2rem;
	margin-top: -1rem
}

.top {
	width: 100%;
	height: 1.093rem;
	text-align: center;
	z-index: 100;
	position: relative;
	background: #efeff4
}

.top a {
	display: block
}

.top ul {
	margin-left: .35rem
}

.top ul li {
	width: 33.333%;
	height: 1.093rem;
	line-height: 1.093rem;
	float: left;
	text-align: center;
	color: #797979;
	font-size: .4rem
}

.top .hanghuo {
	display: inline-block;
	background: url(//sale.qccr.com/appstatic/carmall/images/1.png)
		no-repeat 5% center;
	background-size: 19%
}

.top .invoice {
	display: inline-block;
	background: url(//sale.qccr.com/appstatic/carmall/images/3.png)
		no-repeat 5% center;
	background-size: 19%
}

.top .baoyou {
	display: inline-block;
	background: url(//sale.qccr.com/appstatic/carmall/images/2.png)
		no-repeat 5% center;
	background-size: 19%
}

.tips {
	width: 100%;
	background: #fff;
	color: #333;
	font-size: .373rem;
	margin: 0 auto;
	overflow: hidden
}

.tips .m-tips {
	margin: 0 auto;
	position: relative;
	overflow: auto
}

.ttt {
	position: relative;
	height: 100%
}

.tips .m-tips dl {
	padding: 0 .36rem;
	border-bottom: #ddd 1px solid;
	padding-top: .4rem
}

.tips .m-tips dl:last-child {
	border-bottom: 0 none
}

.tips .m-tips dt {
	margin-bottom: .1rem
}

.tips .m-tips dd {
	margin-left: .8rem;
	padding-bottom: .4rem
}

.m-tips .hanghuo, .m-tips .invoice, .m-tips .baoyou, .m-tips .seven {
	display: inline-block;
	font-size: .426rem;
	color: #f22550;
	font-weight: 700;
	text-indent: .8rem
}

.m-tips .hanghuo {
	background: url(//sale.qccr.com/appstatic/carmall/images/1.png)
		no-repeat 5% center;
	background-size: 25%
}

.m-tips .invoice {
	background: url(//sale.qccr.com/appstatic/carmall/images/2.png)
		no-repeat 5% center;
	background-size: 25%
}

.m-tips .baoyou {
	background: url(//sale.qccr.com/appstatic/carmall/images/2.png)
		no-repeat 5% center;
	background-size: 25%
}

.m-tips .seven {
	background: url(//sale.qccr.com/appstatic/carmall/images/3.png)
		no-repeat 5% center;
	background-size: 25%
}

.m-tips .u-one {
	display: inline-block;
	background: url(//sale.qccr.com/appstatic/carmall/images/5.png)
		no-repeat 0 8%;
	background-size: 6%
}

.m-tips .u-two {
	display: inline-block;
	background: url(//sale.qccr.com/appstatic/carmall/images/6.png)
		no-repeat 0 4%;
	background-size: 6%
}

.m-tips .u-three {
	display: inline-block;
	background: url(//sale.qccr.com/appstatic/carmall/images/7.png)
		no-repeat 0 4%;
	background-size: 6%
}

.m-tips .u-one em, .m-tips .u-two em, .m-tips .u-three em {
	float: right;
	margin-left: 8%
}

.close {
	background-image:
		url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADgAAAA4CAYAAACohjseAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA3hpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo5MDVmYzQxMy04MjFmLTQ3NDAtYTk0NS1lMDM0ODkyMzkwMmIiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6MTE2Q0QzOTRFQ0I4MTFFNUI0RENGMDVDNzc1QzNFODQiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6MTE2Q0QzOTNFQ0I4MTFFNUI0RENGMDVDNzc1QzNFODQiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTUgKE1hY2ludG9zaCkiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDozMWI4MWQzOC1iZDZjLTQzYzgtYmZmZS02NjkxYjg4OTA0NmMiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6OTA1ZmM0MTMtODIxZi00NzQwLWE5NDUtZTAzNDg5MjM5MDJiIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+nsmRwAAABRxJREFUeNrkm0tIVUEYx+eesJeXXosicJEkVrsIFMFAAtEWYheEBC03LhTcCSFUrgQhhHaCLoQoExQCAxcmQhouJEHa+cCIQIja9MDbw57/T/9Hh+O53nPunXPuqQb+DOdy78z3m5kzM983c2PKcDpTXLofWRl0UR6hc1ABFIfy+bUktAatQgvQEjQDzS4tP/9q0p6YIaiDyBLQdagCOuDyte+EUoTNc/mOwE1BD6BRwH7OKSDATiLrgJpptA0yCz2FXkDL0AqM/eb47T5kRVAxdB66xJ63waUxBqA7+O2bUAFh3GFkXVArDfoFTUL32fLJDMvN50hogiohiw3WB3Wi3I+BA8KIRmR3oeOsfBDqRuUrht9l6d1bUCMb8R3UjnoeBgKICuNsyUZ+9ARqQ4UvVYAJ9Z5G1gtV8yMBbEW9a8YAWckYdBb6ALWgghEVYoIN9WzgI9AiVOOlcWMeCpYJYBw6Ac1B9Sj4lcpBgi2FyIahEugtdBm2vMgYkHDT0CHoMdRgYuo2sCQNQVegT7Is7QZppRmW44STiaQu13CSaEMdbRLbxmmr9x7khDLHd+4x4X6qCCXYuAfZI/akvJMlbhNPqh7sI9wch2Wk4NiTYlOD1hF9nnqQ69wgZ8sLuZpQfE4885xdrznXyZjLDmWZi3h92EtBFpBXObvKZqBY3/E4h2gX4Z78LXAcriPceBwnw853kBvnVm6/2ny2YCyAXvFbZhttbyHLjh7s4J5v0M/2C4XVInvG4W0K7jDLrPXRiy85d+wlyzYgF89megXdPlv5Bp3bCROQLGOCZd7w2ZPdZGgm01YPJujPTfrxCvDd37InhJ5DpdlCanClLLOGdXi1Z4VuW5xMW4DXmd/P4AWXGasqW0gXuKpM/D+NYYMpxhjKe0h2BkezcFYzNtAgnO00C88P6JjFMMF+BnySmQ6vTHvSJBztSDJkInGhMosvs2IMJdv1yBekaTgt2SwXLYb2FANEKizIAOF0ljMW45aKWzQVBmTAcBsmMD8ngAV8MBo0SgUZApzOUmBxzfjujFsGBRkCnNS7zm1bXABlWl1TASUXyEDhtCRM+Zb6x5MAJtV22D0IX835zhnZ1nlIwpS02JV5PCsIGq7KxLbOQ7176RmtCeAqPy8KGk7eOVN71zTJZlkVwAU+FAcNZ3qDvpsJzBcsbVE8HwZcSJA2y5IAzvDhUlhwIUDaLDMWd95yslpGVyMUuKAgySAe0hdhs3gmPsVZJxG2LxgAZIIs08JmL/QPmDflyhM3CNmkM9mAo1wPK3my6ifoNGbQWXVCjvkJOtH2SrKMbgHyxGaAzzd9Bp16OFEZ2VtqkFJmj5+gE20XhgH7JCym0Uuw9LXtR/mMjcZ8GmK8TB6hyZouvzll38ywtJaTD/r5gvb6bHWjcBmW2Uvb+/VrJ05v4rbaPMCo5oHGX5FoazVt73R6E87x387Hfh5NRR2ukCNPUrtzHrBchoacr4nkvG3YDoFHFE5sG6atD93u0KRyeOWUaeNYGBricXHU4MSmIdq4SJuVJ0CedcuZg1zVkDPwe1GCpC33aJvYWJPqYtD/e42EPSk/rNB6ciqXEw/rntJ6riLdRaC0QScWUK69k/O5WEJY57z2zpWng0s7RB0V/LuX8RwVhnmdUvaW11QY1yldXCS5zdCiNs/Eg7wQu86FPJwLsQ6DvFxplpjPCsPpztBeEQNE0brSnGLqNnEp/QuXpWhcSk8BG6m/FfwRYABcB9pXktFH3QAAAABJRU5ErkJggg==");
	width: .746rem;
	height: .746rem;
	position: absolute;
	bottom: .5333rem;
	left: 46.2666%;
	margin: 0 auto;
	background-repeat: no-repeat;
	background-size: 100%
}

.fadein {
	-webkit-animation: fadein 1s forwards
}

.fadeout {
	-webkit-animation: fadeout 1s forwards
}

@
-webkit-keyframes fadein {
	from {opacity: 0
}

to {
	opacity: 1
}

}
@
-webkit-keyframes fadeout {
	from {opacity: 1
}

to {
	opacity: 0
}

}
.banner {
	width: 100%;
	height: 2.016666rem;
	overflow: hidden;
	margin-bottom: .266rem;
	z-index: 100;
	position: relative
}

.banner .swiper-container {
	height: 2.016666rem
}

.banner img {
	width: 100%;
	height: 2.016666rem
}

.g-qianggou {
	width: 100%;
	margin-top: .266rem;
	background: #fff;
	overflow: hidden;
	z-index: 100;
	margin-bottom: .266rem
}

.g-qianggou .qg-top {
	width: 100%;
	height: 1.04rem;
	line-height: 1.04rem;
	background: url(//sale.qccr.com/appstatic/carmall/images/1_02.png)
		no-repeat left center;
	background-size: 1.5%
}

.g-qianggou .qg-tt {
	float: left;
	padding-left: .29333rem;
	color: #424242;
	font-size: .4rem;
	font-weight: 700
}

.g-qianggou .qg-tip {
	float: left;
	padding-left: .29333rem;
	color: #797979;
	font-size: .34rem;
	padding-top: .02rem
}

.g-qianggou .red {
	color: #fc4162
}

.g-qianggou #fnTimeCountDown, .g-qianggou .sold, .g-qianggou #fnTimeCountDown2
	{
	float: right;
	padding-right: .2666rem;
	font-size: .34rem;
	padding-top: .02rem
}

.g-qianggou .status {
	float: left;
	padding-right: .1333333rem;
	color: #999
}

.g-qianggou .hour, .g-qianggou .mini, .g-qianggou .sec {
	display: inline-block;
	width: .52rem;
	height: .58666rem;
	line-height: .58666rem;
	background: #000;
	color: #fff;
	border-radius: 4px;
	text-align: center
}

.qg-bottom {
	width: 100%;
	border-top: #EDEDED 1px solid;
	border-bottom: #EDEDED 1px solid;
	height: 4.67rem
}

.threegoods {
	width: 100%;
	height: 4.67rem
}

.threegoods .gooslist {
	float: left;
	position: relative;
	width: 33%;
	border-right: #EDEDED 1px solid;
	height: 4.67rem
}

.threegoods .gooslist:last-child {
	width: 33%;
	border-right: 0 none
}

.threegoods .gooslist .m-goopic {
	width: 60.8%;
	padding-top: .21333rem;
	padding-bottom: .21333rem;
	margin: 0 auto;
	height: 2.1rem;
	display: table
}

.threegoods .gooslist .m-goopic span {
	display: table-cell;
	vertical-align: middle
}

.threegoods .gooslist .m-goopic a {
	display: block
}

.threegoods .gooslist .m-goopic .u-goopic {
	width: 100%
}

.threegoods .gooslist .m-intro {
	padding: 0 .16rem;
	overflow: auto
}

.threegoods .gooslist .greentips {
	padding: 0 .16rem;
	overflow: auto;
	color: #0ac300;
	font-size: .3rem
}

.threegoods .gooslist .u-intrott {
	width: 2.8rem;
	height: .55rem;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	font-size: .32rem;
	color: #666
}

.threegoods .gooslist .u-introprice {
	color: #e62d46;
	font-size: .37333rem
}

.threegoods .gooslist .u-introprice em {
	color: #e62d46;
	font-size: .53333rem
}

.threegoods .gooslist del {
	color: #999;
	font-size: .37333rem;
	font-weight: 400;
	padding-left: .1rem
}

.threegoods .m-process {
	padding: 0 .16rem .16rem
}

.threegoods .ui-progressbar {
	display: inline-block;
	width: 42%;
	border-radius: 10px;
	border: 1px solid #f42f34;
	background: #fff;
	color: #222;
	height: .21333rem;
	text-align: left;
	overflow: hidden;
	margin-right: 1%
}

.threegoods .ui-progressbar-value {
	border-radius: 10px;
	margin: -1px;
	height: 100%;
	border: 1px solid #f42f34;
	background: #f42f34;
	color: #222;
	font-weight: 700;
	text-align: left
}

.threegoods .m-process .u-sale {
	height: .21333rem;
	line-height: .21333rem;
	display: inline-block;
	color: #999;
	font-size: .3rem
}

.threegoods .m-process .totle {
	height: .21333rem;
	line-height: .21333rem;
	display: inline-block;
	color: #f42f34;
	font-size: .3rem;
	margin-left: .1rem
}

.threegoods .seal {
	width: 1.73333rem;
	height: 1.29333rem;
	position: absolute;
	top: 1.1rem;
	left: .6rem
}

.twogoods {
	width: 100%;
	height: 4.67rem
}

.twogoods .gooslist {
	float: left;
	position: relative;
	width: 49.8%;
	border-right: #EDEDED 1px solid;
	height: 4.67rem
}

.twogoods .gooslist:last-child {
	border-right: 0 none
}

.twogoods .gooslist .m-goopic {
	width: 40.1%;
	padding-top: .21333rem;
	padding-bottom: .21333rem;
	margin: 0 auto
}

.twogoods .gooslist .m-goopic a {
	display: block
}

.twogoods .gooslist .m-goopic .u-goopic {
	width: 100%
}

.twogoods .gooslist .m-intro {
	padding: 0 .7rem;
	overflow: auto
}

.twogoods .gooslist .greentips {
	padding: 0 .7rem;
	overflow: auto;
	color: #0AC300;
	font-size: .3rem
}

.twogoods .gooslist .u-intrott {
	width: 3.6rem;
	height: .55rem;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	font-size: .32rem;
	color: #666
}

.twogoods .gooslist .u-introprice {
	color: #e62d46;
	font-size: .37333rem
}

.twogoods .gooslist .u-introprice em {
	color: #e62d46;
	font-size: .53333rem
}

.twogoods .gooslist del {
	color: #999;
	font-size: .37333rem;
	font-weight: 400;
	padding-left: .21333rem
}

.twogoods .m-process {
	padding: 0 .7rem .16rem
}

.twogoods .ui-progressbar {
	display: inline-block;
	width: 50%;
	border-radius: 10px;
	border: 1px solid #f42f34;
	background: #fff;
	color: #222;
	height: .21333rem;
	text-align: left;
	overflow: hidden;
	margin-right: 1%
}

.twogoods .ui-progressbar-value {
	border-radius: 10px;
	margin: -1px;
	height: 100%;
	border: 1px solid #f42f34;
	background: #f42f34;
	color: #222;
	font-weight: 700;
	text-align: left
}

.twogoods .m-process .u-sale {
	height: .21333rem;
	line-height: .21333rem;
	display: inline-block;
	color: #999;
	font-size: .3rem
}

.twogoods .m-process .totle {
	height: .21333rem;
	line-height: .21333rem;
	display: inline-block;
	color: #f42f34;
	font-size: .3rem;
	margin-left: .1rem
}

.twogoods .seal {
	width: 1.73333rem;
	height: 1.29333rem;
	position: absolute;
	top: 1.1rem;
	left: 1.45rem
}

.onegoods {
	width: 100%;
	height: 4.67rem
}

.onegoods .gooslist {
	position: relative;
	width: 100%;
	height: 4.67rem
}

.onegoods .gooslist .m-goopic {
	float: right;
	width: 3.4rem;
	padding-top: .6rem;
	padding-bottom: .6rem
}

.onegoods .gooslist .m-goopic a {
	display: block
}

.onegoods .gooslist .m-goopic .u-goopic {
	width: 3.4rem;
	height: 3.4rem
}

.onegoods .gooslist .m-intro {
	padding: 0 .6rem;
	overflow: auto
}

.onegoods .gooslist .greentips {
	padding: 0 .6rem;
	overflow: auto;
	color: #0ac300;
	font-size: .3rem
}

.onegoods .gooslist .u-intrott {
	width: 3.6rem;
	height: .55rem;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	font-size: .32rem;
	color: #666
}

.onegoods .gooslist .u-introprice {
	color: #e62d46;
	font-size: .37333rem
}

.onegoods .gooslist .u-introprice em {
	color: #e62d46;
	font-size: .53333rem
}

.onegoods .gooslist del {
	color: #999;
	font-size: .37333rem;
	font-weight: 400;
	padding-left: .21333rem
}

.onegoods .m-process {
	padding: 0 .6rem .16rem
}

.onegoods .ui-progressbar {
	display: inline-block;
	width: 2rem;
	border-radius: 10px;
	border: 1px solid #f42f34;
	background: #fff;
	color: #222;
	height: .21333rem;
	text-align: left;
	overflow: hidden;
	margin-right: 1%
}

.onegoods .ui-progressbar-value {
	border-radius: 10px;
	margin: -1px;
	height: 100%;
	border: 1px solid #f42f34;
	background: #f42f34;
	color: #222;
	font-weight: 700;
	text-align: left
}

.onegoods .m-process .u-sale {
	line-height: .45rem;
	display: block;
	color: #999;
	font-size: .3rem
}

.onegoods .m-process .totle {
	height: .21333rem;
	line-height: .45rem;
	display: block;
	color: #f42f34;
	font-size: .3rem
}

.onegoods .seal {
	width: 1.73333rem;
	height: 1.29333rem;
	position: absolute;
	top: 1.1rem;
	right: 1.45rem
}

.fr {
	width: 6rem;
	float: right;
	margin-top: .4rem
}

.navtop {
	
}

.nav {
	position: relative;
	top: 0;
	width: 100%;
	height: 1.0666rem;
	background-color: #fff;
	border-bottom: #e2e2e2 1px solid
}

.navcontent {
	background: #fff;
	width: 100%;
	height: 1.0666rem;
	border-bottom: #e2e2e2 1px solid
}

.m-nav {
	position: relative
}

.nav .slidewrap {
	width: 86.6%;
	overflow: hidden;
	height: 1.0666rem;
	line-height: 1.0666rem;
	overflow-x: auto;
	border-bottom: #e2e2e2 1px solid;
	margin: 0;
	z-index: 30
}

.slidewrap::-webkit-scrollbar {
	display: none
}

.nav .slidewrap ul {
	width: 100%;
	height: 1.0666rem;
	line-height: 1.0666rem;
	white-space: nowrap
}

.nav .slidewrap ul li {
	display: inline-block;
	width: 2.16rem;
	height: 1.0666rem;
	line-height: 1.0666rem;
	overflow: hidden;
	position: relative;
	text-align: center;
	font-size: .4rem;
	color: #535353;
	background: #fff;
	border-bottom: #e2e2e2 1px solid
}

.nav .slidewrap ul li a {
	height: 1.01rem;
	display: block;
	color: #797979;
	line-height: 1rem
}

.nav .dropdown {
	width: 13.4%;
	position: absolute;
	z-index: 40;
	top: 0;
	right: 0;
	height: 1.0666rem;
	line-height: 1.0666rem;
	text-align: center;
	box-shadow: -.1rem 0 10px #e4e4e4;
	background: #fff;
	-webkit-box-shadow: -.1rem 0 10px #e4e4e4;
	border-bottom: #e2e2e2 1px solid
}

.jiantou {
	-webkit-animation: jiantou .5s forwards
}

.jiantou2 {
	-webkit-animation: jiantou2 .5s forwards
}

@
-webkit-keyframes jiantou { 0%{
	transform: rotate(0deg)
}

100%{
transform


:rotate(180deg)


}
}
@
-webkit-keyframes jiantou2 { 0%{
	transform: rotate(180deg)
}

100%{
transform


:rotate(0deg)


}
}
.nav .downlist {
	width: 100%;
	opacity: 0;
	position: absolute;
	background: rgba(255, 255, 255, .9);
	box-shadow: 0 2px 8px #e4e4e4;
	z-index: 41;
	top: 1.09rem;
	-webkit-transition: all .7s ease;
	-webkit-transform: translateY(-4rem) translateZ(0)
}

.nav .fromtop {
	opacity: 1;
	-webkit-transform: translateY(0rem) translateZ(0)
}

.nav .downlist-hide {
	display: none
}

.nav .downlist ul li {
	display: inline-block;
	width: 24.1%;
	height: 1.1rem;
	line-height: 1.1rem;
	overflow: hidden;
	position: relative;
	text-align: center;
	font-size: .346rem;
	color: #666
}

.nav .downlist ul li a {
	color: #666;
	display: block
}

.nav .slidewrap .current {
	color: #f22550;
	border-bottom: #f22550 2px solid
}

.nav .downlist .current {
	color: #f22550
}

#wrapper {
	position: absolute;
	z-index: 1;
	top: 420px;
	bottom: 0;
	left: 0;
	width: 100%;
	background: #fff
}

.snav {
	width: 100%;
	overflow: hidden;
	background: #fff;
	z-index: 1
}

.snavwrap {
	overflow: hidden;
	background: #fff;
	margin: .25333rem 0 0rem 3.3333%
}

.snavwrap li {
	display: inline-block;
	width: 20.266%;
	height: .88rem;
	line-height: .88rem;
	text-align: center;
	margin-right: 3.4666%;
	font-size: .34666rem;
	color: #797979;
	margin-bottom: .25333rem;
	border: #e4e4e4 1px solid;
	border-radius: 5px
}

.snavwrap li a {
	display: block;
	color: #797979;
	border: #e4e4e4 1px solid;
	border-radius: 5px
}

.snavwrap .current {
	border: #f22550 1px solid;
	color: #f22550
}

.g-car {
	margin: .2666rem 0;
	background: #fff;
	width: 100%;
	height: 1.17333rem;
	position: relative;
	z-index: 19
}

.g-car .m-cartt {
	width: 68.333%;
	height: 1.17333rem;
	line-height: 1.17333rem;
	margin-left: 2.6666%;
	color: #797979;
	font-size: .37333rem
}

.g-car .addcarpic {
	width: .537777rem;
	height: .50666rem;
	vertical-align: middle;
	padding-right: .22666rem;
	position: absolute;
	left: .333rem;
	top: .36rem
}

.g-car .nocar1 {
	width: 218px;
	margin-left: .8rem;
	display: inline-block;
	font-size: 14px
}

.g-car .unload img {
	width: .74666rem;
	height: .74666rem;
	vertical-align: middle;
	padding-right: .22666rem
}

.g-car .nocar img {
	width: .537777rem;
	height: .50666rem;
	vertical-align: middle;
	padding-right: .22666rem
}

.g-car .hascar img {
	width: .69333rem;
	height: .69333rem;
	vertical-align: middle;
	padding-right: .22666rem
}

.g-car .m-handle {
	width: 26%;
	height: 1.17333rem;
	line-height: 1.17333rem;
	position: absolute;
	right: .333rem;
	top: 0;
	color: #a9a9a9;
	font-size: .34rem;
	text-align: right
}

.g-car .m-handle a {
	display: block;
	color: #a9a9a9
}

.g-car .m-handle img {
	width: .18rem;
	padding-left: .16rem
}

.pipei {
	width: 100%;
	height: 1rem;
	line-height: .8rem;
	text-align: center
}

.pipei img {
	width: 100%
}

.pipei hr {
	display: inline-block;
	width: 2rem;
	height: 1px;
	border: 0;
	border-top: 1px solid #d7d7d7;
	margin: .1rem .2rem
}

.pipei span {
	display: inline-block;
	color: #333;
	font-size: .373333rem
}

.g-main {
	width: 100%;
	overflow: hidden;
	margin-top: .08rem
}

.g-mainc {
	float: left;
	width: 49.6%;
	overflow: hidden;
	background: #fff;
	border-bottom: #d7d7d7 1px solid;
	margin-bottom: .08rem;
	padding-bottom: .06rem;
	position: relative
}

.g-mainc:nth-child(odd) {
	margin-right: .8%
}

.g-main .m-content {
	width: 86.9565%;
	height: 4.44rem;
	padding: .32rem;
	overflow: hidden
}

.g-main .m-content img {
	width: 100%;
	height: 4.33rem
}

.g-main .m-goods {
	width: 89%;
	margin: 0 auto
}

.g-main .m-goods .u-goodsname {
	overflow: hidden;
	height: .96rem;
	line-height: .5rem;
	color: #666;
	font-size: .3733333rem;
	display: -webkit-box;
	text-overflow: ellipsis;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
	width: 100%
}

.g-main .m-goods .u-price {
	color: #999
}

.g-main .m-goods .price {
	float: left;
	height: .76rem;
	line-height: .76rem;
	color: #e62d46;
	font-size: .32rem;
	font-weight: 700
}

.g-main .m-goods .price em {
	height: .76rem;
	line-height: .76rem;
	color: #e62d46;
	font-size: .64rem
}

.g-main .m-goods .del {
	font-size: .3rem;
	padding-left: .1rem
}

.g-main .m-goods .hassold {
	float: right;
	display: inline-block;
	text-align: right;
	font-size: .32rem;
	padding-top: .19rem
}

.g-main .label {
	display: block;
	position: absolute;
	top: 0;
	right: 0;
	width: .666666rem;
	height: .56rem;
	background-image:
		url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAqCAYAAADxughHAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyhpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTUgKE1hY2ludG9zaCkiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NThDN0U3MzIwNTNFMTFFNkIzOUNFNzE5MDNDQTAzQTIiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NThDN0U3MzMwNTNFMTFFNkIzOUNFNzE5MDNDQTAzQTIiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo1OEM3RTczMDA1M0UxMUU2QjM5Q0U3MTkwM0NBMDNBMiIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo1OEM3RTczMTA1M0UxMUU2QjM5Q0U3MTkwM0NBMDNBMiIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PhhK6NAAAARNSURBVHja7FhpSFRRFD7PNzpTllhaGkgb/rEFW7QgKspsJYsIwoj6VyEUEhESJCJGCyb+CYLoRyVWf9rLwmwhqx/ZJqFBRVEYmUuL2aQz8+Z1zp37nrO89xxnKZN34XDfvLnv3vOd+53lXqE7M0uG/78JMTBEmgnEBGICMYGYQP5Js+hmmJSxAMlJoWeo0aPAXf+IPYt5q0BISwtbWdex4wMHIubmgHXP7rAWtm/aAnLTK7DkLAbL4kWGY+WODo8BkpMjC0Tu6mK94+w5cDe/CrQ4Wti6fSs4r14D6XGD9hxNnu8ch8qhd9cedXdsZaXQU1zC+l8zsn1YIH9pC5gntmg3xG3MD41aSiMQ0tWagPdxZSWsdx49prm4D6B+/h/ouJCAKJYaVn3K9x2ngP97Bu5WHTgPV2h/P3w466x7izw/6254du38RUPqRAQIWYoUdzU8Affbt4ZjtSigfn/3Hsjd3RCbtxpct++wngAQRaMWtbSa9Ox5v1Yz4rL0+g0A+R4CIL9igHA+BYiej4QXfhMSApVcvw4sC+aHZzqcV4lQAU69o4CBi+yOcCDy9+99jt/Wjhb7YjzjlAzDv8VZM9k8mhRG2imRMsCwI0aERy3567e+OF7/oF9qWV406AeNhJFgyc5iiioGitcY7x0oIkCtkZ6Hjk4fawpFoSfJ2LVrQLbbwXWyivnC752FICQmsnwSNWePSU1li7KIQ06IvBYnTWTiHYJpDJD4ZWit5rx8BeSWT6pDKyUMRBOIkJICcnuHGj7tuSvBUrANpAuXVEWIFqQcUUGYmgHiwgWG1CPjSF0/2Tx/rWiMmTgBpKbmPlphaUFh0oGU0+JwbP4GNeLogRHnzgFx+rSQFLVWlrNeKXWCAsKqVczAlDeU+G4t3Mloo+eIjuJSENDiBFZuadEsaxxV1Zog4w0ChL8hBnQeoWqVJbD79Z78sb+UlRY9pWWGCzn2lYD7w0dWfhDVInpwmjxZpXpQO0LWJ+QSFotUvdrOnAYRc0NveQULxUbZl973HqkA2+GDYDuwH36vXe9LD9ytYMoRopG7tbUPRHo6xEwYzyrt4H2EDlMYhZw8KUmNjeC6XgOu6nPMSf0VkdF5fapljEREITV8e58nsNZiZYq/4nxO17UaEDMzmSEtvLhka1DIxjqPKm3d4KR1ZUq0UM4SAbuFBy416+v4gtZ31sojzDha48kn3e/ea64Z7IFUMO9+zVsUE4gJxAQyqG4aw2hulKcoN1HoioRuK6jmWYqyAiUtGkAilUfo7FrLFa/lv/VaBge0DGUh3QhFAkeoQFwoj7jVSRr5Tgy02TiYXA5uejhA6Cw7OojBLV6K16H8iAJDxqEs57IEZUwQ33xFSSIfqUIp1BjQi/LQS/mXf8FnP6Oc5EKBaDb3LQI2T8enTyg+Eoc9Hb82o3R6Oek9FPsgCkxUTudwChIVk/gm0JHRQUCGRPj9I8AA7Xa3bdf/A6UAAAAASUVORK5CYII=");
	background-size: 100%
}

@font-face {
	font-family: iconfont;
	src: url(/appstatic/carmall/fonts/iconfont.eot?t=1458732982);
	src: url(/appstatic/carmall/fonts/iconfont.eot?t=1458732982#iefix)
		format('embedded-opentype'),
		url(/appstatic/carmall/fonts/iconfont.woff?t=1458732982)
		format('woff'),
		url(/appstatic/carmall/fonts/iconfont.ttf?t=1458732982)
		format('truetype'),
		url(/appstatic/carmall/fonts/iconfont.svg?t=1458732982#iconfont)
		format('svg')
}

.iconfont {
	font-family: iconfont !important;
	font-size: .45rem;
	font-style: normal;
	-webkit-font-smoothing: antialiased;
	-webkit-text-stroke-width: .2px;
	-moz-osx-font-smoothing: grayscale
}

.icon-zhankai:before {
	content: "\e63d"
}

.icon-unfold:before {
	content: "\e640"
}

#nav {
	position: absolute;
	top: 0;
	bottom: 48px;
	left: 0;
	overflow: hidden;
	background: #fff
}

#scroller {
	position: absolute;
	background: #fff;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
	width: auto;
	-webkit-transform: translateZ(0);
	-moz-transform: translateZ(0);
	-ms-transform: translateZ(0);
	-o-transform: translateZ(0);
	transform: translateZ(0);
	-webkit-touch-callout: none;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	-webkit-text-size-adjust: none;
	-moz-text-size-adjust: none;
	-ms-text-size-adjust: none;
	-o-text-size-adjust: none;
	text-size-adjust: none
}

.swiper-container .swiper-pagination {
	bottom: .1rem
}

.snavfix {
	position: fixed;
	top: 1.077777777rem;
	z-index: 20;
	width: 100%;
	background: #efeff4;
	-webkit-transform: translateZ(0px)
}

.nogoods {
	text-align: center;
	margin-top: .3rem
}

.nogoodspic {
	width: 2.13333rem;
	height: 2.106666rem;
	background-image:
		url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAKAAAACeCAYAAAC1vwHwAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA4ZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo1NDU5ZmZiNC1lOWQ0LTZjNGMtOWYyMy1iY2MzOTY4MTBjMDgiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6REEyMTQ0N0UxQkQ3MTFFNkIyNEVCQUM4RThGNDMxODYiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6REEyMTQ0N0QxQkQ3MTFFNkIyNEVCQUM4RThGNDMxODYiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTUgKE1hY2ludG9zaCkiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo4MzA3Njk4NC1kODlhLTRiYTItOWU4MS1hNTE4YjliMjZmOGIiIHN0UmVmOmRvY3VtZW50SUQ9ImFkb2JlOmRvY2lkOnBob3Rvc2hvcDpmMDg0YjQ0Ni02MzkyLTExNzgtYWVh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");
	background-size: 100%;
	margin: 0 auto;
	background-repeat: no-repeat
}

.nogoodstxt {
	display: block;
	color: #666;
	font-size: .373333rem;
	margin-top: .2rem
}

section {
	width: 100%;
	height: 100%;
	background: #FFF;
	text-align: center;
	font-size: .5rem;
	z-index: 99999;
	position: absolute;
	top: 0;
	left: 0
}

section p {
	width: 10rem;
	text-align: center;
	position: absolute;
	top: 48%;
	margin: 0 auto
}

section p span {
	display: inline-block;
	font-size: .35rem;
	border: #ededed 1px solid;
	border-radius: 5px;
	margin-top: .2rem;
	padding: .2rem;
	color: #999
}
</style>
<style type="text/css">
.dropload-up, .dropload-down {
	width: 100%;
	position: relative;
	height: 0;
	overflow: hidden;
	font-size: 12px;
	-webkit-transform: translateZ(0);
	transform: translateZ(0)
}

.dropload-down {
	width: 100%;
	height: 50px
}

.dropload-refresh, .dropload-update, .dropload-load, .dropload-noData {
	height: 50px;
	line-height: 50px;
	text-align: center
}

.dropload-load .loading {
	display: inline-block;
	height: 15px;
	width: 15px;
	border-radius: 100%;
	margin: 6px;
	border: 2px solid #1576e3;
	border-bottom-color: transparent;
	vertical-align: middle;
	-webkit-animation: rotate .75s linear infinite;
	animation: rotate .75s linear infinite
}

@
-webkit-keyframes rotate { 0%{
	-webkit-transform: rotate(0deg)
}

50%{
-webkit-transform


:rotate(180deg)


}
100%{
-webkit-transform


:rotate(360deg)


}
}
@
keyframes rotate { 0%{
	transform: rotate(0deg)
}
50%{
transform


:rotate(180deg)


}
100%{
transform


:rotate(360deg)


}
}
</style>
<script>
			function resize() {
				window.remFontSize = document.documentElement.clientWidth / 10;
				document.documentElement.style.fontSize = document.documentElement.clientWidth / 10 + "px";
				$("body").append('<p id="remset" style="width:10rem;"></p>');
			    var realrem= $("#remset").width()/10;
			     var rem=document.documentElement.clientWidth/10;
			     if(realrem!=rem)
			     {
			      $("html").css('font-size',(rem*rem)/realrem+"px");
			     }
			     $("#remset").remove();
			}
			var b = null;
			window.addEventListener("resize", function() {
				clearTimeout(b),
					b = setTimeout(resize, 300)
			}, !1);
			resize();
			
			
		</script>
<script type="text/javascript">
			window.addEventListener("load",function(){			
				$(".load").hide();
				try{
					toApp.showSearchButton(true,2);
				}catch(e){
					//TODO handle the exception
				}

				page.init();				
			},false)
			
		</script>
</head>
<body>

	<div class="reflesh">
		<div class="top">
			<a href="fwbz.shtml">
				<ul>
					<li class="hanghuo">正品保障</li>
					<li class="baoyou">全国包邮</li>
					<li class="invoice">无忧售后</li>
				</ul>
			</a>
		</div>
		<div class="banner">
			<div class="swiper-container swiper-container1 swiper-container-horizontal">
				<div class="swiper-wrapper">
					<div class="swiper-slide swiper-slide-active" style="width: 1423px;">
						<a href="http://sale.qccr.com/ssycx/activityJuly/summercp/app/index.shtml"><img
							src="http://static.qichechaoren.com/upload/shop/2016/07/858aeea02a2f5658.jpg"
							data-original="http://static.qichechaoren.com/upload/shop/2016/07/858aeea02a2f5658.jpg"></a>
					</div>
				</div>
				<div class="swiper-pagination"></div>
			</div>
		</div>
		<div class="waterfall" style="min-height: 774px;">
			<div class="nav">
				<div class="navcontent"
					style="position: fixed; top: 0px; z-index: 42; transform: translateZ(0px);">
					<div class="slidewrap" id="nav">
						<div id="scroller"
							style="transition-timing-function: cubic-bezier(0.1, 0.57, 0.1, 1); transition-duration: 0ms; transform: translate(0px, 0px) translateZ(0px);">
							<ul>
								<li><a href="javascript:;" data-index="0" data-catid="81" isneedscar="false"
									class="current">安全座椅</a></li>
								<li><a href="javascript:;" data-index="1" data-catid="83" isneedscar="false">行车记录</a></li>
								<li><a href="javascript:;" data-index="2" data-catid="85" isneedscar="false">车载音箱</a></li>
								<li><a href="javascript:;" data-index="3" data-catid="87" isneedscar="false">公仔摆件</a></li>
								<li><a href="javascript:;" data-index="4" data-catid="89" isneedscar="false">空气净化器</a></li>
								<li><a href="javascript:;" data-index="5" data-catid="91" isneedscar="false">车载吸尘器</a></li>
								<li><a href="javascript:;" data-index="6" data-catid="93" isneedscar="false">头枕腰靠</a></li>
								<li><a href="javascript:;" data-index="7" data-catid="95" isneedscar="false">车载香水</a></li>
								<li><a href="javascript:;" data-index="8" data-catid="97" isneedscar="false">3M车蜡</a></li>
								<li><a href="javascript:;" data-index="9" data-catid="99" isneedscar="false">专车脚垫</a></li>
								<li><a href="javascript:;" data-index="10" data-catid="127" isneedscar="false">车载冰箱</a></li>
							</ul>
						</div>
					</div>
					<div class="dropdown">
						<div class="iconfont icon-unfold"></div>
					</div>
					<div class="downlist" style="display: none">
						<ul>
							<li><a href="javascript:;" data-index="0" data-catid="81" isneedscar="false"
								class="current">安全座椅</a></li>
							<li><a href="javascript:;" data-index="1" data-catid="83" isneedscar="false">行车记录</a></li>
							<li><a href="javascript:;" data-index="2" data-catid="85" isneedscar="false">车载音箱</a></li>
							<li><a href="javascript:;" data-index="3" data-catid="87" isneedscar="false">公仔摆件</a></li>
							<li><a href="javascript:;" data-index="4" data-catid="89" isneedscar="false">空气净化器</a></li>
							<li><a href="javascript:;" data-index="5" data-catid="91" isneedscar="false">车载吸尘器</a></li>
							<li><a href="javascript:;" data-index="6" data-catid="93" isneedscar="false">头枕腰靠</a></li>
							<li><a href="javascript:;" data-index="7" data-catid="95" isneedscar="false">车载香水</a></li>
							<li><a href="javascript:;" data-index="8" data-catid="97" isneedscar="false">3M车蜡</a></li>
							<li><a href="javascript:;" data-index="9" data-catid="99" isneedscar="false">专车脚垫</a></li>
							<li><a href="javascript:;" data-index="10" data-catid="127" isneedscar="false">车载冰箱</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="fixtwo" style="transform: translateZ(0px);">
				<div class="snav">
					<div ="snavwrap"=""></div>
				</div>
				<div class="g-car" style="display: none;">
					<div class="m-cartt"></div>
					<div class="m-handle"></div>
				</div>
			</div>
			<div class="blockdiv" style="display: none;"></div>
			<div class="pipei" style="display: none;"></div>
			<div class="g-main" style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1);">
				<div class="g-mainc" id="8637">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/thumb/twl/shop/2016/02/831d9376ed036647,h_300,w_300.jpg"
							src="http://static.qichechaoren.com/thumb/twl/shop/2016/02/831d9376ed036647,h_300,w_300.jpg"
							id="8637" class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">环球娃娃 2015新款便携式加厚汽车儿童安全座椅1020-H 太空游侠玫瑰红【爱从安全“座”起】</p>
						<p class="u-price">
							<span class="price">￥<em>618</em></span><span class="hassold">79人已购</span>
						</p>
					</div>
					<div class=""></div>
				</div>
				<div class="g-mainc" id="8634">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/thumb/twl/shop/2016/06/63f749d8da71ebb1,h_300,w_300.jpg"
							src="http://static.qichechaoren.com/thumb/twl/shop/2016/06/63f749d8da71ebb1,h_300,w_300.jpg"
							id="8634" class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">2015新款环球娃娃儿童安全座椅1031-H 探险家橙色</p>
						<p class="u-price">
							<span class="price">￥<em>599</em></span><span class="hassold">19人已购</span>
						</p>
					</div>
					<div class=""></div>
				</div>
				<div class="g-mainc" id="8631">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/upload/shop/2015/12/d45e80bdc5a55e70.jpg"
							src="http://static.qichechaoren.com/upload/shop/2015/12/d45e80bdc5a55e70.jpg" id="8631"
							class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">2015新款环球娃娃儿童安全座椅1029B-H 神奇宝贝北极蓝</p>
						<p class="u-price">
							<span class="price">￥<em>799</em></span><span class="hassold">17人已购</span>
						</p>
					</div>
					<div class=""></div>
				</div>
				<div class="g-mainc" id="8632">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/upload/shop/2015/12/018057eea116b0a0.jpg"
							src="http://static.qichechaoren.com/upload/shop/2015/12/018057eea116b0a0.jpg" id="8632"
							class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">2015新款环球娃娃儿童安全座椅1029B-H 神奇宝贝玫瑰红</p>
						<p class="u-price">
							<span class="price">￥<em>799</em></span><span class="hassold">8人已购</span>
						</p>
					</div>
					<div class=""></div>
				</div>
				<div class="g-mainc" id="50345">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/thumb/twl/shop/2016/02/a5c5d2a9746d20af,h_300,w_300.jpg"
							src="http://static.qichechaoren.com/thumb/twl/shop/2016/02/a5c5d2a9746d20af,h_300,w_300.jpg"
							id="50345" class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">环球娃娃儿童安全座椅1031 探险家红黑</p>
						<p class="u-price">
							<span class="price">￥<em>829</em></span><span class="hassold">7人已购</span>
						</p>
					</div>
					<div class=""></div>
				</div>
				<div class="g-mainc" id="50351">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/thumb/twl/shop/2016/03/460ca613d2416519,h_300,w_300.jpg"
							src="http://static.qichechaoren.com/thumb/twl/shop/2016/03/460ca613d2416519,h_300,w_300.jpg"
							id="50351" class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">环球娃娃儿童安全座椅1028 月光宝贝红黑</p>
						<p class="u-price">
							<span class="price">￥<em>899</em></span><span class="hassold">6人已购</span>
						</p>
					</div>
					<div class=""></div>
				</div>
				<div class="g-mainc" id="50347">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/thumb/twl/shop/2016/02/06821ecd74dcd116,h_300,w_300.jpg"
							src="http://static.qichechaoren.com/thumb/twl/shop/2016/02/06821ecd74dcd116,h_300,w_300.jpg"
							id="50347" class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">环球娃娃儿童安全座椅1031 探险家斑马</p>
						<p class="u-price">
							<span class="price">￥<em>829</em></span><span class="hassold">5人已购</span>
						</p>
					</div>
					<div class=""></div>
				</div>
				<div class="g-mainc" id="50355">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/thumb/twl/shop/2016/03/d9388b8b95d9f08c,h_300,w_300.jpg"
							src="http://static.qichechaoren.com/thumb/twl/shop/2016/03/d9388b8b95d9f08c,h_300,w_300.jpg"
							id="50355" class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">环球娃娃便携式加厚汽车儿童安全座椅1020D 太空游侠红黑</p>
						<p class="u-price">
							<span class="price">￥<em>399</em></span><span class="hassold">4人已购</span>
						</p>
					</div>
					<div class=""></div>
				</div>
				<div class="g-mainc" id="50349">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/thumb/twl/shop/2016/02/e8475253f1ad1a54,h_300,w_300.jpg"
							src="http://static.qichechaoren.com/thumb/twl/shop/2016/02/e8475253f1ad1a54,h_300,w_300.jpg"
							id="50349" class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">环球娃娃儿童安全座椅1031 探险家橙黑</p>
						<p class="u-price">
							<span class="price">￥<em>829</em></span><span class="hassold">2人已购</span>
						</p>
					</div>
					<div class=""></div>
				</div>
				<div class="g-mainc" id="8642">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/thumb/twl/shop/2016/03/2ca373ff9f2fbf32,h_300,w_300.jpg"
							src="http://static.qichechaoren.com/thumb/twl/shop/2016/03/2ca373ff9f2fbf32,h_300,w_300.jpg"
							id="8642" class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">2015新品环球娃娃isofix支撑腿儿童安全座椅1027-H 钻石宝贝玫瑰红</p>
						<p class="u-price">
							<span class="price">￥<em>1900</em></span><span class="hassold">2人已购</span>
						</p>
					</div>
					<div class=""></div>
				</div>
				<div class="g-mainc" id="50359">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/thumb/twl/shop/2016/03/7256d71f63671400,h_300,w_300.jpg"
							src="http://static.qichechaoren.com/thumb/twl/shop/2016/03/7256d71f63671400,h_300,w_300.jpg"
							id="50359" class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">环球娃娃儿童安全座椅1041 皇家骑士红黑</p>
						<p class="u-price">
							<span class="price">￥<em>1399</em></span><span class="hassold">2人已购</span>
						</p>
					</div>
					<div class=""></div>
				</div>
				<div class="g-mainc" id="50357">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/thumb/twl/shop/2016/03/9f4e055d424d5087,h_300,w_300.jpg"
							src="http://static.qichechaoren.com/thumb/twl/shop/2016/03/9f4e055d424d5087,h_300,w_300.jpg"
							id="50357" class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">环球娃娃便携式加厚汽车儿童安全座椅1020D 太空游侠黑色</p>
						<p class="u-price">
							<span class="price">￥<em>399</em></span><span class="hassold">1人已购</span>
						</p>
					</div>
					<div class=""></div>
				</div>
				<div class="g-mainc" id="8644">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/thumb/twl/shop/2016/03/06b3cf7ef2871b98,h_300,w_300.jpg"
							src="http://static.qichechaoren.com/thumb/twl/shop/2016/03/06b3cf7ef2871b98,h_300,w_300.jpg"
							id="8644" class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">2015新款环球娃娃isofix汽车儿童安全座椅1033-H 航海家玫瑰红</p>
						<p class="u-price">
							<span class="price">￥<em>1099</em></span><span class="hassold">1人已购</span>
						</p>
					</div>
					<div class=""></div>
				</div>
				<div class="g-mainc" id="50361">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/thumb/twl/shop/2016/03/278d1a1facd5c323,h_300,w_300.jpg"
							src="http://static.qichechaoren.com/thumb/twl/shop/2016/03/278d1a1facd5c323,h_300,w_300.jpg"
							id="50361" class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">环球娃娃儿童安全座椅1041 皇家骑士黑色</p>
						<p class="u-price">
							<span class="price">￥<em>1399</em></span><span class="hassold"> </span>
						</p>
					</div>
					<div class=""></div>
				</div>
				<div class="g-mainc" id="8643">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/thumb/twl/shop/2016/03/47ca847fc8ca768d,h_300,w_300.jpg"
							src="http://static.qichechaoren.com/thumb/twl/shop/2016/03/47ca847fc8ca768d,h_300,w_300.jpg"
							id="8643" class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">2015新款环球娃娃isofix汽车儿童安全座椅1033-H 航海家北极蓝色</p>
						<p class="u-price">
							<span class="price">￥<em>1099</em></span><span class="hassold"> </span>
						</p>
					</div>
					<div class=""></div>
				</div>
				<div class="g-mainc" id="23643">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/thumb/twl/shop/2016/03/db46d7c152d4339e,h_300,w_300.jpg"
							src="http://static.qichechaoren.com/thumb/twl/shop/2016/03/db46d7c152d4339e,h_300,w_300.jpg"
							id="23643" class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">环球娃娃isofix汽车儿童安全座椅1033 航海家红黑</p>
						<p class="u-price">
							<span class="price">￥<em>968</em></span><span class="hassold"> </span>
						</p>
					</div>
					<div class=""></div>
				</div>
				<div class="g-mainc" id="50353">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/thumb/twl/shop/2016/03/71201ec05beb231e,h_300,w_300.jpg"
							src="http://static.qichechaoren.com/thumb/twl/shop/2016/03/71201ec05beb231e,h_300,w_300.jpg"
							id="50353" class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">环球娃娃儿童安全座椅1028 月光宝贝黑色</p>
						<p class="u-price">
							<span class="price">￥<em>899</em></span><span class="hassold"> </span>
						</p>
					</div>
					<div class=""></div>
				</div>
				<div class="g-mainc" id="8641">
					<div class="m-content">
						<a href="javascript:;"><img
							data-original="http://static.qichechaoren.com/thumb/twl/shop/2016/03/8b0a9fcd98489aa8,h_300,w_300.jpg"
							src="http://static.qichechaoren.com/thumb/twl/shop/2016/03/8b0a9fcd98489aa8,h_300,w_300.jpg"
							id="8641" class="lazy" style="opacity: 1;"></a>
					</div>
					<div class="m-goods">
						<p class="u-goodsname">2015新品环球娃娃isofix支撑腿儿童安全座椅1027-H 钻石宝贝北极蓝色</p>
						<p class="u-price">
							<span class="price">￥<em>1900</em></span><span class="hassold"> </span>
						</p>
					</div>
					<div class=""></div>
				</div>
			</div>
			<div class="dropload-down"
				style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1); display: none;">
				<div class="dropload-load" style="display: none;">
					<span class="loading"></span>加载中...
				</div>
			</div>
			<div class="dropload-noData dropload-noData1">
				<span>已经最后一页了</span>
			</div>
		</div>
	</div>
	<div class="tips"></div>
	<div class="load" style="display: none;"></div>
	<script type="text/javascript">!function(){"use strict";function e(e){e.fn.swiper=function(a){var s;return e(this).each(function(){var e=new t(this,a);s||(s=e)}),s}}var a,t=function(e,s){function r(e){return Math.floor(e)}function i(){y.autoplayTimeoutId=setTimeout(function(){y.params.loop?(y.fixLoop(),y._slideNext(),y.emit("onAutoplay",y)):y.isEnd?s.autoplayStopOnLast?y.stopAutoplay():(y._slideTo(0),y.emit("onAutoplay",y)):(y._slideNext(),y.emit("onAutoplay",y))},y.params.autoplay)}function n(e,t){var s=a(e.target);if(!s.is(t))if("string"==typeof t)s=s.parents(t);else if(t.nodeType){var r;return s.parents().each(function(e,a){a===t&&(r=t)}),r?t:void 0}return 0!==s.length?s[0]:void 0}function o(e,a){a=a||{};var t=window.MutationObserver||window.WebkitMutationObserver,s=new t(function(e){e.forEach(function(e){y.onResize(!0),y.emit("onObserverUpdate",y,e)})});s.observe(e,{attributes:"undefined"==typeof a.attributes?!0:a.attributes,childList:"undefined"==typeof a.childList?!0:a.childList,characterData:"undefined"==typeof a.characterData?!0:a.characterData}),y.observers.push(s)}function l(e){e.originalEvent&&(e=e.originalEvent);var a=e.keyCode||e.charCode;if(!y.params.allowSwipeToNext&&(y.isHorizontal()&&39===a||!y.isHorizontal()&&40===a))return!1;if(!y.params.allowSwipeToPrev&&(y.isHorizontal()&&37===a||!y.isHorizontal()&&38===a))return!1;if(!(e.shiftKey||e.altKey||e.ctrlKey||e.metaKey||document.activeElement&&document.activeElement.nodeName&&("input"===document.activeElement.nodeName.toLowerCase()||"textarea"===document.activeElement.nodeName.toLowerCase()))){if(37===a||39===a||38===a||40===a){var t=!1;if(y.container.parents(".swiper-slide").length>0&&0===y.container.parents(".swiper-slide-active").length)return;var s={left:window.pageXOffset,top:window.pageYOffset},r=window.innerWidth,i=window.innerHeight,n=y.container.offset();y.rtl&&(n.left=n.left-y.container[0].scrollLeft);for(var o=[[n.left,n.top],[n.left+y.width,n.top],[n.left,n.top+y.height],[n.left+y.width,n.top+y.height]],l=0;l<o.length;l++){var p=o[l];p[0]>=s.left&&p[0]<=s.left+r&&p[1]>=s.top&&p[1]<=s.top+i&&(t=!0)}if(!t)return}y.isHorizontal()?((37===a||39===a)&&(e.preventDefault?e.preventDefault():e.returnValue=!1),(39===a&&!y.rtl||37===a&&y.rtl)&&y.slideNext(),(37===a&&!y.rtl||39===a&&y.rtl)&&y.slidePrev()):((38===a||40===a)&&(e.preventDefault?e.preventDefault():e.returnValue=!1),40===a&&y.slideNext(),38===a&&y.slidePrev())}}function p(e){e.originalEvent&&(e=e.originalEvent);var a=y.mousewheel.event,t=0,s=y.rtl?-1:1;if("mousewheel"===a)if(y.params.mousewheelForceToAxis)if(y.isHorizontal()){if(!(Math.abs(e.wheelDeltaX)>Math.abs(e.wheelDeltaY)))return;t=e.wheelDeltaX*s}else{if(!(Math.abs(e.wheelDeltaY)>Math.abs(e.wheelDeltaX)))return;t=e.wheelDeltaY}else t=Math.abs(e.wheelDeltaX)>Math.abs(e.wheelDeltaY)?-e.wheelDeltaX*s:-e.wheelDeltaY;else if("DOMMouseScroll"===a)t=-e.detail;else if("wheel"===a)if(y.params.mousewheelForceToAxis)if(y.isHorizontal()){if(!(Math.abs(e.deltaX)>Math.abs(e.deltaY)))return;t=-e.deltaX*s}else{if(!(Math.abs(e.deltaY)>Math.abs(e.deltaX)))return;t=-e.deltaY}else t=Math.abs(e.deltaX)>Math.abs(e.deltaY)?-e.deltaX*s:-e.deltaY;if(0!==t){if(y.params.mousewheelInvert&&(t=-t),y.params.freeMode){var r=y.getWrapperTranslate()+t*y.params.mousewheelSensitivity,i=y.isBeginning,n=y.isEnd;if(r>=y.minTranslate()&&(r=y.minTranslate()),r<=y.maxTranslate()&&(r=y.maxTranslate()),y.setWrapperTransition(0),y.setWrapperTranslate(r),y.updateProgress(),y.updateActiveIndex(),(!i&&y.isBeginning||!n&&y.isEnd)&&y.updateClasses(),y.params.freeModeSticky?(clearTimeout(y.mousewheel.timeout),y.mousewheel.timeout=setTimeout(function(){y.slideReset()},300)):y.params.lazyLoading&&y.lazy&&y.lazy.load(),0===r||r===y.maxTranslate())return}else{if((new window.Date).getTime()-y.mousewheel.lastScrollTime>60)if(0>t)if(y.isEnd&&!y.params.loop||y.animating){if(y.params.mousewheelReleaseOnEdges)return!0}else y.slideNext();else if(y.isBeginning&&!y.params.loop||y.animating){if(y.params.mousewheelReleaseOnEdges)return!0}else y.slidePrev();y.mousewheel.lastScrollTime=(new window.Date).getTime()}return y.params.autoplay&&y.stopAutoplay(),e.preventDefault?e.preventDefault():e.returnValue=!1,!1}}function d(e,t){e=a(e);var s,r,i,n=y.rtl?-1:1;s=e.attr("data-swiper-parallax")||"0",r=e.attr("data-swiper-parallax-x"),i=e.attr("data-swiper-parallax-y"),r||i?(r=r||"0",i=i||"0"):y.isHorizontal()?(r=s,i="0"):(i=s,r="0"),r=r.indexOf("%")>=0?parseInt(r,10)*t*n+"%":r*t*n+"px",i=i.indexOf("%")>=0?parseInt(i,10)*t+"%":i*t+"px",e.transform("translate3d("+r+", "+i+",0px)")}function u(e){return 0!==e.indexOf("on")&&(e=e[0]!==e[0].toUpperCase()?"on"+e[0].toUpperCase()+e.substring(1):"on"+e),e}if(!(this instanceof t))return new t(e,s);var c={direction:"horizontal",touchEventsTarget:"container",initialSlide:0,speed:300,autoplay:!1,autoplayDisableOnInteraction:!0,autoplayStopOnLast:!1,iOSEdgeSwipeDetection:!1,iOSEdgeSwipeThreshold:20,freeMode:!1,freeModeMomentum:!0,freeModeMomentumRatio:1,freeModeMomentumBounce:!0,freeModeMomentumBounceRatio:1,freeModeSticky:!1,freeModeMinimumVelocity:.02,autoHeight:!1,setWrapperSize:!1,virtualTranslate:!1,effect:"slide",coverflow:{rotate:50,stretch:0,depth:100,modifier:1,slideShadows:!0},flip:{slideShadows:!0,limitRotation:!0},cube:{slideShadows:!0,shadow:!0,shadowOffset:20,shadowScale:.94},fade:{crossFade:!1},parallax:!1,scrollbar:null,scrollbarHide:!0,scrollbarDraggable:!1,scrollbarSnapOnRelease:!1,keyboardControl:!1,mousewheelControl:!1,mousewheelReleaseOnEdges:!1,mousewheelInvert:!1,mousewheelForceToAxis:!1,mousewheelSensitivity:1,hashnav:!1,breakpoints:void 0,spaceBetween:0,slidesPerView:1,slidesPerColumn:1,slidesPerColumnFill:"column",slidesPerGroup:1,centeredSlides:!1,slidesOffsetBefore:0,slidesOffsetAfter:0,roundLengths:!1,touchRatio:1,touchAngle:45,simulateTouch:!0,shortSwipes:!0,longSwipes:!0,longSwipesRatio:.5,longSwipesMs:300,followFinger:!0,onlyExternal:!1,threshold:0,touchMoveStopPropagation:!0,uniqueNavElements:!0,pagination:null,paginationElement:"span",paginationClickable:!1,paginationHide:!1,paginationBulletRender:null,paginationProgressRender:null,paginationFractionRender:null,paginationCustomRender:null,paginationType:"bullets",resistance:!0,resistanceRatio:.85,nextButton:null,prevButton:null,watchSlidesProgress:!1,watchSlidesVisibility:!1,grabCursor:!1,preventClicks:!0,preventClicksPropagation:!0,slideToClickedSlide:!1,lazyLoading:!1,lazyLoadingInPrevNext:!1,lazyLoadingInPrevNextAmount:1,lazyLoadingOnTransitionStart:!1,preloadImages:!0,updateOnImagesReady:!0,loop:!1,loopAdditionalSlides:0,loopedSlides:null,control:void 0,controlInverse:!1,controlBy:"slide",allowSwipeToPrev:!0,allowSwipeToNext:!0,swipeHandler:null,noSwiping:!0,noSwipingClass:"swiper-no-swiping",slideClass:"swiper-slide",slideActiveClass:"swiper-slide-active",slideVisibleClass:"swiper-slide-visible",slideDuplicateClass:"swiper-slide-duplicate",slideNextClass:"swiper-slide-next",slidePrevClass:"swiper-slide-prev",wrapperClass:"swiper-wrapper",bulletClass:"swiper-pagination-bullet",bulletActiveClass:"swiper-pagination-bullet-active",buttonDisabledClass:"swiper-button-disabled",paginationCurrentClass:"swiper-pagination-current",paginationTotalClass:"swiper-pagination-total",paginationHiddenClass:"swiper-pagination-hidden",paginationProgressbarClass:"swiper-pagination-progressbar",observer:!1,observeParents:!1,a11y:!1,prevSlideMessage:"Previous slide",nextSlideMessage:"Next slide",firstSlideMessage:"This is the first slide",lastSlideMessage:"This is the last slide",paginationBulletMessage:"Go to slide {{index}}",runCallbacksOnInit:!0},m=s&&s.virtualTranslate;s=s||{};var f={};for(var g in s)if("object"!=typeof s[g]||null===s[g]||s[g].nodeType||s[g]===window||s[g]===document||"undefined"!=typeof Dom7&&s[g]instanceof Dom7||"undefined"!=typeof jQuery&&s[g]instanceof jQuery)f[g]=s[g];else{f[g]={};for(var h in s[g])f[g][h]=s[g][h]}for(var v in c)if("undefined"==typeof s[v])s[v]=c[v];else if("object"==typeof s[v])for(var w in c[v])"undefined"==typeof s[v][w]&&(s[v][w]=c[v][w]);var y=this;if(y.params=s,y.originalParams=f,y.classNames=[],"undefined"!=typeof a&&"undefined"!=typeof Dom7&&(a=Dom7),("undefined"!=typeof a||(a="undefined"==typeof Dom7?window.Dom7||window.Zepto||window.jQuery:Dom7))&&(y.$=a,y.currentBreakpoint=void 0,y.getActiveBreakpoint=function(){if(!y.params.breakpoints)return!1;var e,a=!1,t=[];for(e in y.params.breakpoints)y.params.breakpoints.hasOwnProperty(e)&&t.push(e);t.sort(function(e,a){return parseInt(e,10)>parseInt(a,10)});for(var s=0;s<t.length;s++)e=t[s],e>=window.innerWidth&&!a&&(a=e);return a||"max"},y.setBreakpoint=function(){var e=y.getActiveBreakpoint();if(e&&y.currentBreakpoint!==e){var a=e in y.params.breakpoints?y.params.breakpoints[e]:y.originalParams,t=y.params.loop&&a.slidesPerView!==y.params.slidesPerView;for(var s in a)y.params[s]=a[s];y.currentBreakpoint=e,t&&y.destroyLoop&&y.reLoop(!0)}},y.params.breakpoints&&y.setBreakpoint(),y.container=a(e),0!==y.container.length)){if(y.container.length>1){var b=[];return y.container.each(function(){b.push(new t(this,s))}),b}y.container[0].swiper=y,y.container.data("swiper",y),y.classNames.push("swiper-container-"+y.params.direction),y.params.freeMode&&y.classNames.push("swiper-container-free-mode"),y.support.flexbox||(y.classNames.push("swiper-container-no-flexbox"),y.params.slidesPerColumn=1),y.params.autoHeight&&y.classNames.push("swiper-container-autoheight"),(y.params.parallax||y.params.watchSlidesVisibility)&&(y.params.watchSlidesProgress=!0),["cube","coverflow","flip"].indexOf(y.params.effect)>=0&&(y.support.transforms3d?(y.params.watchSlidesProgress=!0,y.classNames.push("swiper-container-3d")):y.params.effect="slide"),"slide"!==y.params.effect&&y.classNames.push("swiper-container-"+y.params.effect),"cube"===y.params.effect&&(y.params.resistanceRatio=0,y.params.slidesPerView=1,y.params.slidesPerColumn=1,y.params.slidesPerGroup=1,y.params.centeredSlides=!1,y.params.spaceBetween=0,y.params.virtualTranslate=!0,y.params.setWrapperSize=!1),("fade"===y.params.effect||"flip"===y.params.effect)&&(y.params.slidesPerView=1,y.params.slidesPerColumn=1,y.params.slidesPerGroup=1,y.params.watchSlidesProgress=!0,y.params.spaceBetween=0,y.params.setWrapperSize=!1,"undefined"==typeof m&&(y.params.virtualTranslate=!0)),y.params.grabCursor&&y.support.touch&&(y.params.grabCursor=!1),y.wrapper=y.container.children("."+y.params.wrapperClass),y.params.pagination&&(y.paginationContainer=a(y.params.pagination),y.params.uniqueNavElements&&"string"==typeof y.params.pagination&&y.paginationContainer.length>1&&1===y.container.find(y.params.pagination).length&&(y.paginationContainer=y.container.find(y.params.pagination)),"bullets"===y.params.paginationType&&y.params.paginationClickable?y.paginationContainer.addClass("swiper-pagination-clickable"):y.params.paginationClickable=!1,y.paginationContainer.addClass("swiper-pagination-"+y.params.paginationType)),(y.params.nextButton||y.params.prevButton)&&(y.params.nextButton&&(y.nextButton=a(y.params.nextButton),y.params.uniqueNavElements&&"string"==typeof y.params.nextButton&&y.nextButton.length>1&&1===y.container.find(y.params.nextButton).length&&(y.nextButton=y.container.find(y.params.nextButton))),y.params.prevButton&&(y.prevButton=a(y.params.prevButton),y.params.uniqueNavElements&&"string"==typeof y.params.prevButton&&y.prevButton.length>1&&1===y.container.find(y.params.prevButton).length&&(y.prevButton=y.container.find(y.params.prevButton)))),y.isHorizontal=function(){return"horizontal"===y.params.direction},y.rtl=y.isHorizontal()&&("rtl"===y.container[0].dir.toLowerCase()||"rtl"===y.container.css("direction")),y.rtl&&y.classNames.push("swiper-container-rtl"),y.rtl&&(y.wrongRTL="-webkit-box"===y.wrapper.css("display")),y.params.slidesPerColumn>1&&y.classNames.push("swiper-container-multirow"),y.device.android&&y.classNames.push("swiper-container-android"),y.container.addClass(y.classNames.join(" ")),y.translate=0,y.progress=0,y.velocity=0,y.lockSwipeToNext=function(){y.params.allowSwipeToNext=!1},y.lockSwipeToPrev=function(){y.params.allowSwipeToPrev=!1},y.lockSwipes=function(){y.params.allowSwipeToNext=y.params.allowSwipeToPrev=!1},y.unlockSwipeToNext=function(){y.params.allowSwipeToNext=!0},y.unlockSwipeToPrev=function(){y.params.allowSwipeToPrev=!0},y.unlockSwipes=function(){y.params.allowSwipeToNext=y.params.allowSwipeToPrev=!0},y.params.grabCursor&&(y.container[0].style.cursor="move",y.container[0].style.cursor="-webkit-grab",y.container[0].style.cursor="-moz-grab",y.container[0].style.cursor="grab"),y.imagesToLoad=[],y.imagesLoaded=0,y.loadImage=function(e,a,t,s,r){function i(){r&&r()}var n;e.complete&&s?i():a?(n=new window.Image,n.onload=i,n.onerror=i,t&&(n.srcset=t),a&&(n.src=a)):i()},y.preloadImages=function(){function e(){"undefined"!=typeof y&&null!==y&&(void 0!==y.imagesLoaded&&y.imagesLoaded++,y.imagesLoaded===y.imagesToLoad.length&&(y.params.updateOnImagesReady&&y.update(),y.emit("onImagesReady",y)))}y.imagesToLoad=y.container.find("img");for(var a=0;a<y.imagesToLoad.length;a++)y.loadImage(y.imagesToLoad[a],y.imagesToLoad[a].currentSrc||y.imagesToLoad[a].getAttribute("src"),y.imagesToLoad[a].srcset||y.imagesToLoad[a].getAttribute("srcset"),!0,e)},y.autoplayTimeoutId=void 0,y.autoplaying=!1,y.autoplayPaused=!1,y.startAutoplay=function(){return"undefined"!=typeof y.autoplayTimeoutId?!1:y.params.autoplay?y.autoplaying?!1:(y.autoplaying=!0,y.emit("onAutoplayStart",y),void i()):!1},y.stopAutoplay=function(){y.autoplayTimeoutId&&(y.autoplayTimeoutId&&clearTimeout(y.autoplayTimeoutId),y.autoplaying=!1,y.autoplayTimeoutId=void 0,y.emit("onAutoplayStop",y))},y.pauseAutoplay=function(e){y.autoplayPaused||(y.autoplayTimeoutId&&clearTimeout(y.autoplayTimeoutId),y.autoplayPaused=!0,0===e?(y.autoplayPaused=!1,i()):y.wrapper.transitionEnd(function(){y&&(y.autoplayPaused=!1,y.autoplaying?i():y.stopAutoplay())}))},y.minTranslate=function(){return-y.snapGrid[0]},y.maxTranslate=function(){return-y.snapGrid[y.snapGrid.length-1]},y.updateAutoHeight=function(){var e=y.slides.eq(y.activeIndex)[0];if("undefined"!=typeof e){var a=e.offsetHeight;a&&y.wrapper.css("height",a+"px")}},y.updateContainerSize=function(){var e,a;e="undefined"!=typeof y.params.width?y.params.width:y.container[0].clientWidth,a="undefined"!=typeof y.params.height?y.params.height:y.container[0].clientHeight,0===e&&y.isHorizontal()||0===a&&!y.isHorizontal()||(e=e-parseInt(y.container.css("padding-left"),10)-parseInt(y.container.css("padding-right"),10),a=a-parseInt(y.container.css("padding-top"),10)-parseInt(y.container.css("padding-bottom"),10),y.width=e,y.height=a,y.size=y.isHorizontal()?y.width:y.height)},y.updateSlidesSize=function(){y.slides=y.wrapper.children("."+y.params.slideClass),y.snapGrid=[],y.slidesGrid=[],y.slidesSizesGrid=[];var e,a=y.params.spaceBetween,t=-y.params.slidesOffsetBefore,s=0,i=0;if("undefined"!=typeof y.size){"string"==typeof a&&a.indexOf("%")>=0&&(a=parseFloat(a.replace("%",""))/100*y.size),y.virtualSize=-a,y.slides.css(y.rtl?{marginLeft:"",marginTop:""}:{marginRight:"",marginBottom:""});var n;y.params.slidesPerColumn>1&&(n=Math.floor(y.slides.length/y.params.slidesPerColumn)===y.slides.length/y.params.slidesPerColumn?y.slides.length:Math.ceil(y.slides.length/y.params.slidesPerColumn)*y.params.slidesPerColumn,"auto"!==y.params.slidesPerView&&"row"===y.params.slidesPerColumnFill&&(n=Math.max(n,y.params.slidesPerView*y.params.slidesPerColumn)));var o,l=y.params.slidesPerColumn,p=n/l,d=p-(y.params.slidesPerColumn*p-y.slides.length);for(e=0;e<y.slides.length;e++){o=0;var u=y.slides.eq(e);if(y.params.slidesPerColumn>1){var c,m,f;"column"===y.params.slidesPerColumnFill?(m=Math.floor(e/l),f=e-m*l,(m>d||m===d&&f===l-1)&&++f>=l&&(f=0,m++),c=m+f*n/l,u.css({"-webkit-box-ordinal-group":c,"-moz-box-ordinal-group":c,"-ms-flex-order":c,"-webkit-order":c,order:c})):(f=Math.floor(e/p),m=e-f*p),u.css({"margin-top":0!==f&&y.params.spaceBetween&&y.params.spaceBetween+"px"}).attr("data-swiper-column",m).attr("data-swiper-row",f)}"none"!==u.css("display")&&("auto"===y.params.slidesPerView?(o=y.isHorizontal()?u.outerWidth(!0):u.outerHeight(!0),y.params.roundLengths&&(o=r(o))):(o=(y.size-(y.params.slidesPerView-1)*a)/y.params.slidesPerView,y.params.roundLengths&&(o=r(o)),y.isHorizontal()?y.slides[e].style.width=o+"px":y.slides[e].style.height=o+"px"),y.slides[e].swiperSlideSize=o,y.slidesSizesGrid.push(o),y.params.centeredSlides?(t=t+o/2+s/2+a,0===e&&(t=t-y.size/2-a),Math.abs(t)<.001&&(t=0),i%y.params.slidesPerGroup===0&&y.snapGrid.push(t),y.slidesGrid.push(t)):(i%y.params.slidesPerGroup===0&&y.snapGrid.push(t),y.slidesGrid.push(t),t=t+o+a),y.virtualSize+=o+a,s=o,i++)}y.virtualSize=Math.max(y.virtualSize,y.size)+y.params.slidesOffsetAfter;var g;if(y.rtl&&y.wrongRTL&&("slide"===y.params.effect||"coverflow"===y.params.effect)&&y.wrapper.css({width:y.virtualSize+y.params.spaceBetween+"px"}),(!y.support.flexbox||y.params.setWrapperSize)&&y.wrapper.css(y.isHorizontal()?{width:y.virtualSize+y.params.spaceBetween+"px"}:{height:y.virtualSize+y.params.spaceBetween+"px"}),y.params.slidesPerColumn>1&&(y.virtualSize=(o+y.params.spaceBetween)*n,y.virtualSize=Math.ceil(y.virtualSize/y.params.slidesPerColumn)-y.params.spaceBetween,y.wrapper.css({width:y.virtualSize+y.params.spaceBetween+"px"}),y.params.centeredSlides)){for(g=[],e=0;e<y.snapGrid.length;e++)y.snapGrid[e]<y.virtualSize+y.snapGrid[0]&&g.push(y.snapGrid[e]);y.snapGrid=g}if(!y.params.centeredSlides){for(g=[],e=0;e<y.snapGrid.length;e++)y.snapGrid[e]<=y.virtualSize-y.size&&g.push(y.snapGrid[e]);y.snapGrid=g,Math.floor(y.virtualSize-y.size)-Math.floor(y.snapGrid[y.snapGrid.length-1])>1&&y.snapGrid.push(y.virtualSize-y.size)}0===y.snapGrid.length&&(y.snapGrid=[0]),0!==y.params.spaceBetween&&y.slides.css(y.isHorizontal()?y.rtl?{marginLeft:a+"px"}:{marginRight:a+"px"}:{marginBottom:a+"px"}),y.params.watchSlidesProgress&&y.updateSlidesOffset()}},y.updateSlidesOffset=function(){for(var e=0;e<y.slides.length;e++)y.slides[e].swiperSlideOffset=y.isHorizontal()?y.slides[e].offsetLeft:y.slides[e].offsetTop},y.updateSlidesProgress=function(e){if("undefined"==typeof e&&(e=y.translate||0),0!==y.slides.length){"undefined"==typeof y.slides[0].swiperSlideOffset&&y.updateSlidesOffset();var a=-e;y.rtl&&(a=e),y.slides.removeClass(y.params.slideVisibleClass);for(var t=0;t<y.slides.length;t++){var s=y.slides[t],r=(a-s.swiperSlideOffset)/(s.swiperSlideSize+y.params.spaceBetween);if(y.params.watchSlidesVisibility){var i=-(a-s.swiperSlideOffset),n=i+y.slidesSizesGrid[t],o=i>=0&&i<y.size||n>0&&n<=y.size||0>=i&&n>=y.size;o&&y.slides.eq(t).addClass(y.params.slideVisibleClass)}s.progress=y.rtl?-r:r}}},y.updateProgress=function(e){"undefined"==typeof e&&(e=y.translate||0);var a=y.maxTranslate()-y.minTranslate(),t=y.isBeginning,s=y.isEnd;0===a?(y.progress=0,y.isBeginning=y.isEnd=!0):(y.progress=(e-y.minTranslate())/a,y.isBeginning=y.progress<=0,y.isEnd=y.progress>=1),y.isBeginning&&!t&&y.emit("onReachBeginning",y),y.isEnd&&!s&&y.emit("onReachEnd",y),y.params.watchSlidesProgress&&y.updateSlidesProgress(e),y.emit("onProgress",y,y.progress)},y.updateActiveIndex=function(){var e,a,t,s=y.rtl?y.translate:-y.translate;for(a=0;a<y.slidesGrid.length;a++)"undefined"!=typeof y.slidesGrid[a+1]?s>=y.slidesGrid[a]&&s<y.slidesGrid[a+1]-(y.slidesGrid[a+1]-y.slidesGrid[a])/2?e=a:s>=y.slidesGrid[a]&&s<y.slidesGrid[a+1]&&(e=a+1):s>=y.slidesGrid[a]&&(e=a);(0>e||"undefined"==typeof e)&&(e=0),t=Math.floor(e/y.params.slidesPerGroup),t>=y.snapGrid.length&&(t=y.snapGrid.length-1),e!==y.activeIndex&&(y.snapIndex=t,y.previousIndex=y.activeIndex,y.activeIndex=e,y.updateClasses())},y.updateClasses=function(){y.slides.removeClass(y.params.slideActiveClass+" "+y.params.slideNextClass+" "+y.params.slidePrevClass);var e=y.slides.eq(y.activeIndex);e.addClass(y.params.slideActiveClass);var t=e.next("."+y.params.slideClass).addClass(y.params.slideNextClass);y.params.loop&&0===t.length&&y.slides.eq(0).addClass(y.params.slideNextClass);var s=e.prev("."+y.params.slideClass).addClass(y.params.slidePrevClass);if(y.params.loop&&0===s.length&&y.slides.eq(-1).addClass(y.params.slidePrevClass),y.paginationContainer&&y.paginationContainer.length>0){var r,i=y.params.loop?Math.ceil((y.slides.length-2*y.loopedSlides)/y.params.slidesPerGroup):y.snapGrid.length;if(y.params.loop?(r=Math.ceil((y.activeIndex-y.loopedSlides)/y.params.slidesPerGroup),r>y.slides.length-1-2*y.loopedSlides&&(r-=y.slides.length-2*y.loopedSlides),r>i-1&&(r-=i),0>r&&"bullets"!==y.params.paginationType&&(r=i+r)):r="undefined"!=typeof y.snapIndex?y.snapIndex:y.activeIndex||0,"bullets"===y.params.paginationType&&y.bullets&&y.bullets.length>0&&(y.bullets.removeClass(y.params.bulletActiveClass),y.paginationContainer.length>1?y.bullets.each(function(){a(this).index()===r&&a(this).addClass(y.params.bulletActiveClass)}):y.bullets.eq(r).addClass(y.params.bulletActiveClass)),"fraction"===y.params.paginationType&&(y.paginationContainer.find("."+y.params.paginationCurrentClass).text(r+1),y.paginationContainer.find("."+y.params.paginationTotalClass).text(i)),"progress"===y.params.paginationType){var n=(r+1)/i,o=n,l=1;y.isHorizontal()||(l=n,o=1),y.paginationContainer.find("."+y.params.paginationProgressbarClass).transform("translate3d(0,0,0) scaleX("+o+") scaleY("+l+")").transition(y.params.speed)}"custom"===y.params.paginationType&&y.params.paginationCustomRender&&(y.paginationContainer.html(y.params.paginationCustomRender(y,r+1,i)),y.emit("onPaginationRendered",y,y.paginationContainer[0]))}y.params.loop||(y.params.prevButton&&y.prevButton&&y.prevButton.length>0&&(y.isBeginning?(y.prevButton.addClass(y.params.buttonDisabledClass),y.params.a11y&&y.a11y&&y.a11y.disable(y.prevButton)):(y.prevButton.removeClass(y.params.buttonDisabledClass),y.params.a11y&&y.a11y&&y.a11y.enable(y.prevButton))),y.params.nextButton&&y.nextButton&&y.nextButton.length>0&&(y.isEnd?(y.nextButton.addClass(y.params.buttonDisabledClass),y.params.a11y&&y.a11y&&y.a11y.disable(y.nextButton)):(y.nextButton.removeClass(y.params.buttonDisabledClass),y.params.a11y&&y.a11y&&y.a11y.enable(y.nextButton))))},y.updatePagination=function(){if(y.params.pagination&&y.paginationContainer&&y.paginationContainer.length>0){var e="";if("bullets"===y.params.paginationType){for(var a=y.params.loop?Math.ceil((y.slides.length-2*y.loopedSlides)/y.params.slidesPerGroup):y.snapGrid.length,t=0;a>t;t++)e+=y.params.paginationBulletRender?y.params.paginationBulletRender(t,y.params.bulletClass):"<"+y.params.paginationElement+' class="'+y.params.bulletClass+'"></'+y.params.paginationElement+">";y.paginationContainer.html(e),y.bullets=y.paginationContainer.find("."+y.params.bulletClass),y.params.paginationClickable&&y.params.a11y&&y.a11y&&y.a11y.initPagination()}"fraction"===y.params.paginationType&&(e=y.params.paginationFractionRender?y.params.paginationFractionRender(y,y.params.paginationCurrentClass,y.params.paginationTotalClass):'<span class="'+y.params.paginationCurrentClass+'"></span> / <span class="'+y.params.paginationTotalClass+'"></span>',y.paginationContainer.html(e)),"progress"===y.params.paginationType&&(e=y.params.paginationProgressRender?y.params.paginationProgressRender(y,y.params.paginationProgressbarClass):'<span class="'+y.params.paginationProgressbarClass+'"></span>',y.paginationContainer.html(e)),"custom"!==y.params.paginationType&&y.emit("onPaginationRendered",y,y.paginationContainer[0])}},y.update=function(e){function a(){s=Math.min(Math.max(y.translate,y.maxTranslate()),y.minTranslate()),y.setWrapperTranslate(s),y.updateActiveIndex(),y.updateClasses()}if(y.updateContainerSize(),y.updateSlidesSize(),y.updateProgress(),y.updatePagination(),y.updateClasses(),y.params.scrollbar&&y.scrollbar&&y.scrollbar.set(),e){var t,s;y.controller&&y.controller.spline&&(y.controller.spline=void 0),y.params.freeMode?(a(),y.params.autoHeight&&y.updateAutoHeight()):(t=("auto"===y.params.slidesPerView||y.params.slidesPerView>1)&&y.isEnd&&!y.params.centeredSlides?y.slideTo(y.slides.length-1,0,!1,!0):y.slideTo(y.activeIndex,0,!1,!0),t||a())}else y.params.autoHeight&&y.updateAutoHeight()},y.onResize=function(e){y.params.breakpoints&&y.setBreakpoint();var a=y.params.allowSwipeToPrev,t=y.params.allowSwipeToNext;y.params.allowSwipeToPrev=y.params.allowSwipeToNext=!0,y.updateContainerSize(),y.updateSlidesSize(),("auto"===y.params.slidesPerView||y.params.freeMode||e)&&y.updatePagination(),y.params.scrollbar&&y.scrollbar&&y.scrollbar.set(),y.controller&&y.controller.spline&&(y.controller.spline=void 0);var s=!1;if(y.params.freeMode){var r=Math.min(Math.max(y.translate,y.maxTranslate()),y.minTranslate());y.setWrapperTranslate(r),y.updateActiveIndex(),y.updateClasses(),y.params.autoHeight&&y.updateAutoHeight()}else y.updateClasses(),s=("auto"===y.params.slidesPerView||y.params.slidesPerView>1)&&y.isEnd&&!y.params.centeredSlides?y.slideTo(y.slides.length-1,0,!1,!0):y.slideTo(y.activeIndex,0,!1,!0);y.params.lazyLoading&&!s&&y.lazy&&y.lazy.load(),y.params.allowSwipeToPrev=a,y.params.allowSwipeToNext=t};var x=["mousedown","mousemove","mouseup"];window.navigator.pointerEnabled?x=["pointerdown","pointermove","pointerup"]:window.navigator.msPointerEnabled&&(x=["MSPointerDown","MSPointerMove","MSPointerUp"]),y.touchEvents={start:y.support.touch||!y.params.simulateTouch?"touchstart":x[0],move:y.support.touch||!y.params.simulateTouch?"touchmove":x[1],end:y.support.touch||!y.params.simulateTouch?"touchend":x[2]},(window.navigator.pointerEnabled||window.navigator.msPointerEnabled)&&("container"===y.params.touchEventsTarget?y.container:y.wrapper).addClass("swiper-wp8-"+y.params.direction),y.initEvents=function(e){var a=e?"off":"on",t=e?"removeEventListener":"addEventListener",r="container"===y.params.touchEventsTarget?y.container[0]:y.wrapper[0],i=y.support.touch?r:document,n=y.params.nested?!0:!1;y.browser.ie?(r[t](y.touchEvents.start,y.onTouchStart,!1),i[t](y.touchEvents.move,y.onTouchMove,n),i[t](y.touchEvents.end,y.onTouchEnd,!1)):(y.support.touch&&(r[t](y.touchEvents.start,y.onTouchStart,!1),r[t](y.touchEvents.move,y.onTouchMove,n),r[t](y.touchEvents.end,y.onTouchEnd,!1)),!s.simulateTouch||y.device.ios||y.device.android||(r[t]("mousedown",y.onTouchStart,!1),document[t]("mousemove",y.onTouchMove,n),document[t]("mouseup",y.onTouchEnd,!1))),window[t]("resize",y.onResize),y.params.nextButton&&y.nextButton&&y.nextButton.length>0&&(y.nextButton[a]("click",y.onClickNext),y.params.a11y&&y.a11y&&y.nextButton[a]("keydown",y.a11y.onEnterKey)),y.params.prevButton&&y.prevButton&&y.prevButton.length>0&&(y.prevButton[a]("click",y.onClickPrev),y.params.a11y&&y.a11y&&y.prevButton[a]("keydown",y.a11y.onEnterKey)),y.params.pagination&&y.params.paginationClickable&&(y.paginationContainer[a]("click","."+y.params.bulletClass,y.onClickIndex),y.params.a11y&&y.a11y&&y.paginationContainer[a]("keydown","."+y.params.bulletClass,y.a11y.onEnterKey)),(y.params.preventClicks||y.params.preventClicksPropagation)&&r[t]("click",y.preventClicks,!0)},y.attachEvents=function(){y.initEvents()},y.detachEvents=function(){y.initEvents(!0)},y.allowClick=!0,y.preventClicks=function(e){y.allowClick||(y.params.preventClicks&&e.preventDefault(),y.params.preventClicksPropagation&&y.animating&&(e.stopPropagation(),e.stopImmediatePropagation()))},y.onClickNext=function(e){e.preventDefault(),(!y.isEnd||y.params.loop)&&y.slideNext()},y.onClickPrev=function(e){e.preventDefault(),(!y.isBeginning||y.params.loop)&&y.slidePrev()},y.onClickIndex=function(e){e.preventDefault();var t=a(this).index()*y.params.slidesPerGroup;y.params.loop&&(t+=y.loopedSlides),y.slideTo(t)},y.updateClickedSlide=function(e){var t=n(e,"."+y.params.slideClass),s=!1;if(t)for(var r=0;r<y.slides.length;r++)y.slides[r]===t&&(s=!0);if(!t||!s)return y.clickedSlide=void 0,void(y.clickedIndex=void 0);if(y.clickedSlide=t,y.clickedIndex=a(t).index(),y.params.slideToClickedSlide&&void 0!==y.clickedIndex&&y.clickedIndex!==y.activeIndex){var i,o=y.clickedIndex;if(y.params.loop){if(y.animating)return;i=a(y.clickedSlide).attr("data-swiper-slide-index"),y.params.centeredSlides?o<y.loopedSlides-y.params.slidesPerView/2||o>y.slides.length-y.loopedSlides+y.params.slidesPerView/2?(y.fixLoop(),o=y.wrapper.children("."+y.params.slideClass+'[data-swiper-slide-index="'+i+'"]:not(.swiper-slide-duplicate)').eq(0).index(),setTimeout(function(){y.slideTo(o)},0)):y.slideTo(o):o>y.slides.length-y.params.slidesPerView?(y.fixLoop(),o=y.wrapper.children("."+y.params.slideClass+'[data-swiper-slide-index="'+i+'"]:not(.swiper-slide-duplicate)').eq(0).index(),setTimeout(function(){y.slideTo(o)},0)):y.slideTo(o)}else y.slideTo(o)}};var T,S,C,z,M,P,I,k,E,B,D="input, select, textarea, button",L=Date.now(),H=[];y.animating=!1,y.touches={startX:0,startY:0,currentX:0,currentY:0,diff:0};var G,A;if(y.onTouchStart=function(e){if(e.originalEvent&&(e=e.originalEvent),G="touchstart"===e.type,G||!("which"in e)||3!==e.which){if(y.params.noSwiping&&n(e,"."+y.params.noSwipingClass))return void(y.allowClick=!0);if(!y.params.swipeHandler||n(e,y.params.swipeHandler)){var t=y.touches.currentX="touchstart"===e.type?e.targetTouches[0].pageX:e.pageX,s=y.touches.currentY="touchstart"===e.type?e.targetTouches[0].pageY:e.pageY;if(!(y.device.ios&&y.params.iOSEdgeSwipeDetection&&t<=y.params.iOSEdgeSwipeThreshold)){if(T=!0,S=!1,C=!0,M=void 0,A=void 0,y.touches.startX=t,y.touches.startY=s,z=Date.now(),y.allowClick=!0,y.updateContainerSize(),y.swipeDirection=void 0,y.params.threshold>0&&(k=!1),"touchstart"!==e.type){var r=!0;a(e.target).is(D)&&(r=!1),document.activeElement&&a(document.activeElement).is(D)&&document.activeElement.blur(),r&&e.preventDefault()}y.emit("onTouchStart",y,e)}}}},y.onTouchMove=function(e){if(e.originalEvent&&(e=e.originalEvent),!G||"mousemove"!==e.type){if(e.preventedByNestedSwiper)return y.touches.startX="touchmove"===e.type?e.targetTouches[0].pageX:e.pageX,void(y.touches.startY="touchmove"===e.type?e.targetTouches[0].pageY:e.pageY);if(y.params.onlyExternal)return y.allowClick=!1,void(T&&(y.touches.startX=y.touches.currentX="touchmove"===e.type?e.targetTouches[0].pageX:e.pageX,y.touches.startY=y.touches.currentY="touchmove"===e.type?e.targetTouches[0].pageY:e.pageY,z=Date.now()));if(G&&document.activeElement&&e.target===document.activeElement&&a(e.target).is(D))return S=!0,void(y.allowClick=!1);if(C&&y.emit("onTouchMove",y,e),!(e.targetTouches&&e.targetTouches.length>1)){if(y.touches.currentX="touchmove"===e.type?e.targetTouches[0].pageX:e.pageX,y.touches.currentY="touchmove"===e.type?e.targetTouches[0].pageY:e.pageY,"undefined"==typeof M){var t=180*Math.atan2(Math.abs(y.touches.currentY-y.touches.startY),Math.abs(y.touches.currentX-y.touches.startX))/Math.PI;M=y.isHorizontal()?t>y.params.touchAngle:90-t>y.params.touchAngle}if(M&&y.emit("onTouchMoveOpposite",y,e),"undefined"==typeof A&&y.browser.ieTouch&&(y.touches.currentX!==y.touches.startX||y.touches.currentY!==y.touches.startY)&&(A=!0),T){if(M)return void(T=!1);if(A||!y.browser.ieTouch){y.allowClick=!1,y.emit("onSliderMove",y,e),e.preventDefault(),y.params.touchMoveStopPropagation&&!y.params.nested&&e.stopPropagation(),S||(s.loop&&y.fixLoop(),I=y.getWrapperTranslate(),y.setWrapperTransition(0),y.animating&&y.wrapper.trigger("webkitTransitionEnd transitionend oTransitionEnd MSTransitionEnd msTransitionEnd"),y.params.autoplay&&y.autoplaying&&(y.params.autoplayDisableOnInteraction?y.stopAutoplay():y.pauseAutoplay()),B=!1,y.params.grabCursor&&(y.container[0].style.cursor="move",y.container[0].style.cursor="-webkit-grabbing",y.container[0].style.cursor="-moz-grabbin",y.container[0].style.cursor="grabbing")),S=!0;var r=y.touches.diff=y.isHorizontal()?y.touches.currentX-y.touches.startX:y.touches.currentY-y.touches.startY;r*=y.params.touchRatio,y.rtl&&(r=-r),y.swipeDirection=r>0?"prev":"next",P=r+I;var i=!0;if(r>0&&P>y.minTranslate()?(i=!1,y.params.resistance&&(P=y.minTranslate()-1+Math.pow(-y.minTranslate()+I+r,y.params.resistanceRatio))):0>r&&P<y.maxTranslate()&&(i=!1,y.params.resistance&&(P=y.maxTranslate()+1-Math.pow(y.maxTranslate()-I-r,y.params.resistanceRatio))),i&&(e.preventedByNestedSwiper=!0),!y.params.allowSwipeToNext&&"next"===y.swipeDirection&&I>P&&(P=I),!y.params.allowSwipeToPrev&&"prev"===y.swipeDirection&&P>I&&(P=I),y.params.followFinger){if(y.params.threshold>0){if(!(Math.abs(r)>y.params.threshold||k))return void(P=I);
if(!k)return k=!0,y.touches.startX=y.touches.currentX,y.touches.startY=y.touches.currentY,P=I,void(y.touches.diff=y.isHorizontal()?y.touches.currentX-y.touches.startX:y.touches.currentY-y.touches.startY)}(y.params.freeMode||y.params.watchSlidesProgress)&&y.updateActiveIndex(),y.params.freeMode&&(0===H.length&&H.push({position:y.touches[y.isHorizontal()?"startX":"startY"],time:z}),H.push({position:y.touches[y.isHorizontal()?"currentX":"currentY"],time:(new window.Date).getTime()})),y.updateProgress(P),y.setWrapperTranslate(P)}}}}}},y.onTouchEnd=function(e){if(e.originalEvent&&(e=e.originalEvent),C&&y.emit("onTouchEnd",y,e),C=!1,T){y.params.grabCursor&&S&&T&&(y.container[0].style.cursor="move",y.container[0].style.cursor="-webkit-grab",y.container[0].style.cursor="-moz-grab",y.container[0].style.cursor="grab");var t=Date.now(),s=t-z;if(y.allowClick&&(y.updateClickedSlide(e),y.emit("onTap",y,e),300>s&&t-L>300&&(E&&clearTimeout(E),E=setTimeout(function(){y&&(y.params.paginationHide&&y.paginationContainer.length>0&&!a(e.target).hasClass(y.params.bulletClass)&&y.paginationContainer.toggleClass(y.params.paginationHiddenClass),y.emit("onClick",y,e))},300)),300>s&&300>t-L&&(E&&clearTimeout(E),y.emit("onDoubleTap",y,e))),L=Date.now(),setTimeout(function(){y&&(y.allowClick=!0)},0),!T||!S||!y.swipeDirection||0===y.touches.diff||P===I)return void(T=S=!1);T=S=!1;var r;if(r=y.params.followFinger?y.rtl?y.translate:-y.translate:-P,y.params.freeMode){if(r<-y.minTranslate())return void y.slideTo(y.activeIndex);if(r>-y.maxTranslate())return void y.slideTo(y.slides.length<y.snapGrid.length?y.snapGrid.length-1:y.slides.length-1);if(y.params.freeModeMomentum){if(H.length>1){var i=H.pop(),n=H.pop(),o=i.position-n.position,l=i.time-n.time;y.velocity=o/l,y.velocity=y.velocity/2,Math.abs(y.velocity)<y.params.freeModeMinimumVelocity&&(y.velocity=0),(l>150||(new window.Date).getTime()-i.time>300)&&(y.velocity=0)}else y.velocity=0;H.length=0;var p=1e3*y.params.freeModeMomentumRatio,d=y.velocity*p,u=y.translate+d;y.rtl&&(u=-u);var c,m=!1,f=20*Math.abs(y.velocity)*y.params.freeModeMomentumBounceRatio;if(u<y.maxTranslate())y.params.freeModeMomentumBounce?(u+y.maxTranslate()<-f&&(u=y.maxTranslate()-f),c=y.maxTranslate(),m=!0,B=!0):u=y.maxTranslate();else if(u>y.minTranslate())y.params.freeModeMomentumBounce?(u-y.minTranslate()>f&&(u=y.minTranslate()+f),c=y.minTranslate(),m=!0,B=!0):u=y.minTranslate();else if(y.params.freeModeSticky){var g,h=0;for(h=0;h<y.snapGrid.length;h+=1)if(y.snapGrid[h]>-u){g=h;break}u=Math.abs(y.snapGrid[g]-u)<Math.abs(y.snapGrid[g-1]-u)||"next"===y.swipeDirection?y.snapGrid[g]:y.snapGrid[g-1],y.rtl||(u=-u)}if(0!==y.velocity)p=Math.abs(y.rtl?(-u-y.translate)/y.velocity:(u-y.translate)/y.velocity);else if(y.params.freeModeSticky)return void y.slideReset();y.params.freeModeMomentumBounce&&m?(y.updateProgress(c),y.setWrapperTransition(p),y.setWrapperTranslate(u),y.onTransitionStart(),y.animating=!0,y.wrapper.transitionEnd(function(){y&&B&&(y.emit("onMomentumBounce",y),y.setWrapperTransition(y.params.speed),y.setWrapperTranslate(c),y.wrapper.transitionEnd(function(){y&&y.onTransitionEnd()}))})):y.velocity?(y.updateProgress(u),y.setWrapperTransition(p),y.setWrapperTranslate(u),y.onTransitionStart(),y.animating||(y.animating=!0,y.wrapper.transitionEnd(function(){y&&y.onTransitionEnd()}))):y.updateProgress(u),y.updateActiveIndex()}return void((!y.params.freeModeMomentum||s>=y.params.longSwipesMs)&&(y.updateProgress(),y.updateActiveIndex()))}var v,w=0,b=y.slidesSizesGrid[0];for(v=0;v<y.slidesGrid.length;v+=y.params.slidesPerGroup)"undefined"!=typeof y.slidesGrid[v+y.params.slidesPerGroup]?r>=y.slidesGrid[v]&&r<y.slidesGrid[v+y.params.slidesPerGroup]&&(w=v,b=y.slidesGrid[v+y.params.slidesPerGroup]-y.slidesGrid[v]):r>=y.slidesGrid[v]&&(w=v,b=y.slidesGrid[y.slidesGrid.length-1]-y.slidesGrid[y.slidesGrid.length-2]);var x=(r-y.slidesGrid[w])/b;if(s>y.params.longSwipesMs){if(!y.params.longSwipes)return void y.slideTo(y.activeIndex);"next"===y.swipeDirection&&y.slideTo(x>=y.params.longSwipesRatio?w+y.params.slidesPerGroup:w),"prev"===y.swipeDirection&&y.slideTo(x>1-y.params.longSwipesRatio?w+y.params.slidesPerGroup:w)}else{if(!y.params.shortSwipes)return void y.slideTo(y.activeIndex);"next"===y.swipeDirection&&y.slideTo(w+y.params.slidesPerGroup),"prev"===y.swipeDirection&&y.slideTo(w)}}},y._slideTo=function(e,a){return y.slideTo(e,a,!0,!0)},y.slideTo=function(e,a,t,s){"undefined"==typeof t&&(t=!0),"undefined"==typeof e&&(e=0),0>e&&(e=0),y.snapIndex=Math.floor(e/y.params.slidesPerGroup),y.snapIndex>=y.snapGrid.length&&(y.snapIndex=y.snapGrid.length-1);var r=-y.snapGrid[y.snapIndex];y.params.autoplay&&y.autoplaying&&(s||!y.params.autoplayDisableOnInteraction?y.pauseAutoplay(a):y.stopAutoplay()),y.updateProgress(r);for(var i=0;i<y.slidesGrid.length;i++)-Math.floor(100*r)>=Math.floor(100*y.slidesGrid[i])&&(e=i);return!y.params.allowSwipeToNext&&r<y.translate&&r<y.minTranslate()?!1:!y.params.allowSwipeToPrev&&r>y.translate&&r>y.maxTranslate()&&(y.activeIndex||0)!==e?!1:("undefined"==typeof a&&(a=y.params.speed),y.previousIndex=y.activeIndex||0,y.activeIndex=e,y.rtl&&-r===y.translate||!y.rtl&&r===y.translate?(y.params.autoHeight&&y.updateAutoHeight(),y.updateClasses(),"slide"!==y.params.effect&&y.setWrapperTranslate(r),!1):(y.updateClasses(),y.onTransitionStart(t),0===a?(y.setWrapperTranslate(r),y.setWrapperTransition(0),y.onTransitionEnd(t)):(y.setWrapperTranslate(r),y.setWrapperTransition(a),y.animating||(y.animating=!0,y.wrapper.transitionEnd(function(){y&&y.onTransitionEnd(t)}))),!0))},y.onTransitionStart=function(e){"undefined"==typeof e&&(e=!0),y.params.autoHeight&&y.updateAutoHeight(),y.lazy&&y.lazy.onTransitionStart(),e&&(y.emit("onTransitionStart",y),y.activeIndex!==y.previousIndex&&(y.emit("onSlideChangeStart",y),y.activeIndex>y.previousIndex?y.emit("onSlideNextStart",y):y.emit("onSlidePrevStart",y)))},y.onTransitionEnd=function(e){y.animating=!1,y.setWrapperTransition(0),"undefined"==typeof e&&(e=!0),y.lazy&&y.lazy.onTransitionEnd(),e&&(y.emit("onTransitionEnd",y),y.activeIndex!==y.previousIndex&&(y.emit("onSlideChangeEnd",y),y.activeIndex>y.previousIndex?y.emit("onSlideNextEnd",y):y.emit("onSlidePrevEnd",y))),y.params.hashnav&&y.hashnav&&y.hashnav.setHash()},y.slideNext=function(e,a,t){return y.params.loop?y.animating?!1:(y.fixLoop(),y.container[0].clientLeft,y.slideTo(y.activeIndex+y.params.slidesPerGroup,a,e,t)):y.slideTo(y.activeIndex+y.params.slidesPerGroup,a,e,t)},y._slideNext=function(e){return y.slideNext(!0,e,!0)},y.slidePrev=function(e,a,t){return y.params.loop?y.animating?!1:(y.fixLoop(),y.container[0].clientLeft,y.slideTo(y.activeIndex-1,a,e,t)):y.slideTo(y.activeIndex-1,a,e,t)},y._slidePrev=function(e){return y.slidePrev(!0,e,!0)},y.slideReset=function(e,a){return y.slideTo(y.activeIndex,a,e)},y.setWrapperTransition=function(e,a){y.wrapper.transition(e),"slide"!==y.params.effect&&y.effects[y.params.effect]&&y.effects[y.params.effect].setTransition(e),y.params.parallax&&y.parallax&&y.parallax.setTransition(e),y.params.scrollbar&&y.scrollbar&&y.scrollbar.setTransition(e),y.params.control&&y.controller&&y.controller.setTransition(e,a),y.emit("onSetTransition",y,e)},y.setWrapperTranslate=function(e,a,t){var s=0,i=0,n=0;y.isHorizontal()?s=y.rtl?-e:e:i=e,y.params.roundLengths&&(s=r(s),i=r(i)),y.params.virtualTranslate||y.wrapper.transform(y.support.transforms3d?"translate3d("+s+"px, "+i+"px, "+n+"px)":"translate("+s+"px, "+i+"px)"),y.translate=y.isHorizontal()?s:i;var o,l=y.maxTranslate()-y.minTranslate();o=0===l?0:(e-y.minTranslate())/l,o!==y.progress&&y.updateProgress(e),a&&y.updateActiveIndex(),"slide"!==y.params.effect&&y.effects[y.params.effect]&&y.effects[y.params.effect].setTranslate(y.translate),y.params.parallax&&y.parallax&&y.parallax.setTranslate(y.translate),y.params.scrollbar&&y.scrollbar&&y.scrollbar.setTranslate(y.translate),y.params.control&&y.controller&&y.controller.setTranslate(y.translate,t),y.emit("onSetTranslate",y,y.translate)},y.getTranslate=function(e,a){var t,s,r,i;return"undefined"==typeof a&&(a="x"),y.params.virtualTranslate?y.rtl?-y.translate:y.translate:(r=window.getComputedStyle(e,null),window.WebKitCSSMatrix?(s=r.transform||r.webkitTransform,s.split(",").length>6&&(s=s.split(", ").map(function(e){return e.replace(",",".")}).join(", ")),i=new window.WebKitCSSMatrix("none"===s?"":s)):(i=r.MozTransform||r.OTransform||r.MsTransform||r.msTransform||r.transform||r.getPropertyValue("transform").replace("translate(","matrix(1, 0, 0, 1,"),t=i.toString().split(",")),"x"===a&&(s=window.WebKitCSSMatrix?i.m41:parseFloat(16===t.length?t[12]:t[4])),"y"===a&&(s=window.WebKitCSSMatrix?i.m42:parseFloat(16===t.length?t[13]:t[5])),y.rtl&&s&&(s=-s),s||0)},y.getWrapperTranslate=function(e){return"undefined"==typeof e&&(e=y.isHorizontal()?"x":"y"),y.getTranslate(y.wrapper[0],e)},y.observers=[],y.initObservers=function(){if(y.params.observeParents)for(var e=y.container.parents(),a=0;a<e.length;a++)o(e[a]);o(y.container[0],{childList:!1}),o(y.wrapper[0],{attributes:!1})},y.disconnectObservers=function(){for(var e=0;e<y.observers.length;e++)y.observers[e].disconnect();y.observers=[]},y.createLoop=function(){y.wrapper.children("."+y.params.slideClass+"."+y.params.slideDuplicateClass).remove();var e=y.wrapper.children("."+y.params.slideClass);"auto"!==y.params.slidesPerView||y.params.loopedSlides||(y.params.loopedSlides=e.length),y.loopedSlides=parseInt(y.params.loopedSlides||y.params.slidesPerView,10),y.loopedSlides=y.loopedSlides+y.params.loopAdditionalSlides,y.loopedSlides>e.length&&(y.loopedSlides=e.length);var t,s=[],r=[];for(e.each(function(t,i){var n=a(this);t<y.loopedSlides&&r.push(i),t<e.length&&t>=e.length-y.loopedSlides&&s.push(i),n.attr("data-swiper-slide-index",t)}),t=0;t<r.length;t++)y.wrapper.append(a(r[t].cloneNode(!0)).addClass(y.params.slideDuplicateClass));for(t=s.length-1;t>=0;t--)y.wrapper.prepend(a(s[t].cloneNode(!0)).addClass(y.params.slideDuplicateClass))},y.destroyLoop=function(){y.wrapper.children("."+y.params.slideClass+"."+y.params.slideDuplicateClass).remove(),y.slides.removeAttr("data-swiper-slide-index")},y.reLoop=function(e){var a=y.activeIndex-y.loopedSlides;y.destroyLoop(),y.createLoop(),y.updateSlidesSize(),e&&y.slideTo(a+y.loopedSlides,0,!1)},y.fixLoop=function(){var e;y.activeIndex<y.loopedSlides?(e=y.slides.length-3*y.loopedSlides+y.activeIndex,e+=y.loopedSlides,y.slideTo(e,0,!1,!0)):("auto"===y.params.slidesPerView&&y.activeIndex>=2*y.loopedSlides||y.activeIndex>y.slides.length-2*y.params.slidesPerView)&&(e=-y.slides.length+y.activeIndex+y.loopedSlides,e+=y.loopedSlides,y.slideTo(e,0,!1,!0))},y.appendSlide=function(e){if(y.params.loop&&y.destroyLoop(),"object"==typeof e&&e.length)for(var a=0;a<e.length;a++)e[a]&&y.wrapper.append(e[a]);else y.wrapper.append(e);y.params.loop&&y.createLoop(),y.params.observer&&y.support.observer||y.update(!0)},y.prependSlide=function(e){y.params.loop&&y.destroyLoop();var a=y.activeIndex+1;if("object"==typeof e&&e.length){for(var t=0;t<e.length;t++)e[t]&&y.wrapper.prepend(e[t]);a=y.activeIndex+e.length}else y.wrapper.prepend(e);y.params.loop&&y.createLoop(),y.params.observer&&y.support.observer||y.update(!0),y.slideTo(a,0,!1)},y.removeSlide=function(e){y.params.loop&&(y.destroyLoop(),y.slides=y.wrapper.children("."+y.params.slideClass));var a,t=y.activeIndex;if("object"==typeof e&&e.length){for(var s=0;s<e.length;s++)a=e[s],y.slides[a]&&y.slides.eq(a).remove(),t>a&&t--;t=Math.max(t,0)}else a=e,y.slides[a]&&y.slides.eq(a).remove(),t>a&&t--,t=Math.max(t,0);y.params.loop&&y.createLoop(),y.params.observer&&y.support.observer||y.update(!0),y.params.loop?y.slideTo(t+y.loopedSlides,0,!1):y.slideTo(t,0,!1)},y.removeAllSlides=function(){for(var e=[],a=0;a<y.slides.length;a++)e.push(a);y.removeSlide(e)},y.effects={fade:{setTranslate:function(){for(var e=0;e<y.slides.length;e++){var a=y.slides.eq(e),t=a[0].swiperSlideOffset,s=-t;y.params.virtualTranslate||(s-=y.translate);var r=0;y.isHorizontal()||(r=s,s=0);var i=y.params.fade.crossFade?Math.max(1-Math.abs(a[0].progress),0):1+Math.min(Math.max(a[0].progress,-1),0);a.css({opacity:i}).transform("translate3d("+s+"px, "+r+"px, 0px)")}},setTransition:function(e){if(y.slides.transition(e),y.params.virtualTranslate&&0!==e){var a=!1;y.slides.transitionEnd(function(){if(!a&&y){a=!0,y.animating=!1;for(var e=["webkitTransitionEnd","transitionend","oTransitionEnd","MSTransitionEnd","msTransitionEnd"],t=0;t<e.length;t++)y.wrapper.trigger(e[t])}})}}},flip:{setTranslate:function(){for(var e=0;e<y.slides.length;e++){var t=y.slides.eq(e),s=t[0].progress;y.params.flip.limitRotation&&(s=Math.max(Math.min(t[0].progress,1),-1));var r=t[0].swiperSlideOffset,i=-180*s,n=i,o=0,l=-r,p=0;if(y.isHorizontal()?y.rtl&&(n=-n):(p=l,l=0,o=-n,n=0),t[0].style.zIndex=-Math.abs(Math.round(s))+y.slides.length,y.params.flip.slideShadows){var d=t.find(y.isHorizontal()?".swiper-slide-shadow-left":".swiper-slide-shadow-top"),u=t.find(y.isHorizontal()?".swiper-slide-shadow-right":".swiper-slide-shadow-bottom");0===d.length&&(d=a('<div class="swiper-slide-shadow-'+(y.isHorizontal()?"left":"top")+'"></div>'),t.append(d)),0===u.length&&(u=a('<div class="swiper-slide-shadow-'+(y.isHorizontal()?"right":"bottom")+'"></div>'),t.append(u)),d.length&&(d[0].style.opacity=Math.max(-s,0)),u.length&&(u[0].style.opacity=Math.max(s,0))}t.transform("translate3d("+l+"px, "+p+"px, 0px) rotateX("+o+"deg) rotateY("+n+"deg)")}},setTransition:function(e){if(y.slides.transition(e).find(".swiper-slide-shadow-top, .swiper-slide-shadow-right, .swiper-slide-shadow-bottom, .swiper-slide-shadow-left").transition(e),y.params.virtualTranslate&&0!==e){var t=!1;y.slides.eq(y.activeIndex).transitionEnd(function(){if(!t&&y&&a(this).hasClass(y.params.slideActiveClass)){t=!0,y.animating=!1;for(var e=["webkitTransitionEnd","transitionend","oTransitionEnd","MSTransitionEnd","msTransitionEnd"],s=0;s<e.length;s++)y.wrapper.trigger(e[s])}})}}},cube:{setTranslate:function(){var e,t=0;y.params.cube.shadow&&(y.isHorizontal()?(e=y.wrapper.find(".swiper-cube-shadow"),0===e.length&&(e=a('<div class="swiper-cube-shadow"></div>'),y.wrapper.append(e)),e.css({height:y.width+"px"})):(e=y.container.find(".swiper-cube-shadow"),0===e.length&&(e=a('<div class="swiper-cube-shadow"></div>'),y.container.append(e))));for(var s=0;s<y.slides.length;s++){var r=y.slides.eq(s),i=90*s,n=Math.floor(i/360);y.rtl&&(i=-i,n=Math.floor(-i/360));var o=Math.max(Math.min(r[0].progress,1),-1),l=0,p=0,d=0;s%4===0?(l=4*-n*y.size,d=0):(s-1)%4===0?(l=0,d=4*-n*y.size):(s-2)%4===0?(l=y.size+4*n*y.size,d=y.size):(s-3)%4===0&&(l=-y.size,d=3*y.size+4*y.size*n),y.rtl&&(l=-l),y.isHorizontal()||(p=l,l=0);var u="rotateX("+(y.isHorizontal()?0:-i)+"deg) rotateY("+(y.isHorizontal()?i:0)+"deg) translate3d("+l+"px, "+p+"px, "+d+"px)";if(1>=o&&o>-1&&(t=90*s+90*o,y.rtl&&(t=90*-s-90*o)),r.transform(u),y.params.cube.slideShadows){var c=r.find(y.isHorizontal()?".swiper-slide-shadow-left":".swiper-slide-shadow-top"),m=r.find(y.isHorizontal()?".swiper-slide-shadow-right":".swiper-slide-shadow-bottom");0===c.length&&(c=a('<div class="swiper-slide-shadow-'+(y.isHorizontal()?"left":"top")+'"></div>'),r.append(c)),0===m.length&&(m=a('<div class="swiper-slide-shadow-'+(y.isHorizontal()?"right":"bottom")+'"></div>'),r.append(m)),c.length&&(c[0].style.opacity=Math.max(-o,0)),m.length&&(m[0].style.opacity=Math.max(o,0))}}if(y.wrapper.css({"-webkit-transform-origin":"50% 50% -"+y.size/2+"px","-moz-transform-origin":"50% 50% -"+y.size/2+"px","-ms-transform-origin":"50% 50% -"+y.size/2+"px","transform-origin":"50% 50% -"+y.size/2+"px"}),y.params.cube.shadow)if(y.isHorizontal())e.transform("translate3d(0px, "+(y.width/2+y.params.cube.shadowOffset)+"px, "+-y.width/2+"px) rotateX(90deg) rotateZ(0deg) scale("+y.params.cube.shadowScale+")");else{var f=Math.abs(t)-90*Math.floor(Math.abs(t)/90),g=1.5-(Math.sin(2*f*Math.PI/360)/2+Math.cos(2*f*Math.PI/360)/2),h=y.params.cube.shadowScale,v=y.params.cube.shadowScale/g,w=y.params.cube.shadowOffset;e.transform("scale3d("+h+", 1, "+v+") translate3d(0px, "+(y.height/2+w)+"px, "+-y.height/2/v+"px) rotateX(-90deg)")}var b=y.isSafari||y.isUiWebView?-y.size/2:0;y.wrapper.transform("translate3d(0px,0,"+b+"px) rotateX("+(y.isHorizontal()?0:t)+"deg) rotateY("+(y.isHorizontal()?-t:0)+"deg)")},setTransition:function(e){y.slides.transition(e).find(".swiper-slide-shadow-top, .swiper-slide-shadow-right, .swiper-slide-shadow-bottom, .swiper-slide-shadow-left").transition(e),y.params.cube.shadow&&!y.isHorizontal()&&y.container.find(".swiper-cube-shadow").transition(e)}},coverflow:{setTranslate:function(){for(var e=y.translate,t=y.isHorizontal()?-e+y.width/2:-e+y.height/2,s=y.isHorizontal()?y.params.coverflow.rotate:-y.params.coverflow.rotate,r=y.params.coverflow.depth,i=0,n=y.slides.length;n>i;i++){var o=y.slides.eq(i),l=y.slidesSizesGrid[i],p=o[0].swiperSlideOffset,d=(t-p-l/2)/l*y.params.coverflow.modifier,u=y.isHorizontal()?s*d:0,c=y.isHorizontal()?0:s*d,m=-r*Math.abs(d),f=y.isHorizontal()?0:y.params.coverflow.stretch*d,g=y.isHorizontal()?y.params.coverflow.stretch*d:0;Math.abs(g)<.001&&(g=0),Math.abs(f)<.001&&(f=0),Math.abs(m)<.001&&(m=0),Math.abs(u)<.001&&(u=0),Math.abs(c)<.001&&(c=0);var h="translate3d("+g+"px,"+f+"px,"+m+"px)  rotateX("+c+"deg) rotateY("+u+"deg)";if(o.transform(h),o[0].style.zIndex=-Math.abs(Math.round(d))+1,y.params.coverflow.slideShadows){var v=o.find(y.isHorizontal()?".swiper-slide-shadow-left":".swiper-slide-shadow-top"),w=o.find(y.isHorizontal()?".swiper-slide-shadow-right":".swiper-slide-shadow-bottom");0===v.length&&(v=a('<div class="swiper-slide-shadow-'+(y.isHorizontal()?"left":"top")+'"></div>'),o.append(v)),0===w.length&&(w=a('<div class="swiper-slide-shadow-'+(y.isHorizontal()?"right":"bottom")+'"></div>'),o.append(w)),v.length&&(v[0].style.opacity=d>0?d:0),w.length&&(w[0].style.opacity=-d>0?-d:0)}}if(y.browser.ie){var b=y.wrapper[0].style;b.perspectiveOrigin=t+"px 50%"}},setTransition:function(e){y.slides.transition(e).find(".swiper-slide-shadow-top, .swiper-slide-shadow-right, .swiper-slide-shadow-bottom, .swiper-slide-shadow-left").transition(e)}}},y.lazy={initialImageLoaded:!1,loadImageInSlide:function(e,t){if("undefined"!=typeof e&&("undefined"==typeof t&&(t=!0),0!==y.slides.length)){var s=y.slides.eq(e),r=s.find(".swiper-lazy:not(.swiper-lazy-loaded):not(.swiper-lazy-loading)");!s.hasClass("swiper-lazy")||s.hasClass("swiper-lazy-loaded")||s.hasClass("swiper-lazy-loading")||(r=r.add(s[0])),0!==r.length&&r.each(function(){var e=a(this);e.addClass("swiper-lazy-loading");var r=e.attr("data-background"),i=e.attr("data-src"),n=e.attr("data-srcset");y.loadImage(e[0],i||r,n,!1,function(){if(r?(e.css("background-image",'url("'+r+'")'),e.removeAttr("data-background")):(n&&(e.attr("srcset",n),e.removeAttr("data-srcset")),i&&(e.attr("src",i),e.removeAttr("data-src"))),e.addClass("swiper-lazy-loaded").removeClass("swiper-lazy-loading"),s.find(".swiper-lazy-preloader, .preloader").remove(),y.params.loop&&t){var a=s.attr("data-swiper-slide-index");if(s.hasClass(y.params.slideDuplicateClass)){var o=y.wrapper.children('[data-swiper-slide-index="'+a+'"]:not(.'+y.params.slideDuplicateClass+")");y.lazy.loadImageInSlide(o.index(),!1)}else{var l=y.wrapper.children("."+y.params.slideDuplicateClass+'[data-swiper-slide-index="'+a+'"]');y.lazy.loadImageInSlide(l.index(),!1)}}y.emit("onLazyImageReady",y,s[0],e[0])}),y.emit("onLazyImageLoad",y,s[0],e[0])})}},load:function(){var e;if(y.params.watchSlidesVisibility)y.wrapper.children("."+y.params.slideVisibleClass).each(function(){y.lazy.loadImageInSlide(a(this).index())});else if(y.params.slidesPerView>1)for(e=y.activeIndex;e<y.activeIndex+y.params.slidesPerView;e++)y.slides[e]&&y.lazy.loadImageInSlide(e);else y.lazy.loadImageInSlide(y.activeIndex);if(y.params.lazyLoadingInPrevNext)if(y.params.slidesPerView>1||y.params.lazyLoadingInPrevNextAmount&&y.params.lazyLoadingInPrevNextAmount>1){var t=y.params.lazyLoadingInPrevNextAmount,s=y.params.slidesPerView,r=Math.min(y.activeIndex+s+Math.max(t,s),y.slides.length),i=Math.max(y.activeIndex-Math.max(s,t),0);for(e=y.activeIndex+y.params.slidesPerView;r>e;e++)y.slides[e]&&y.lazy.loadImageInSlide(e);for(e=i;e<y.activeIndex;e++)y.slides[e]&&y.lazy.loadImageInSlide(e)}else{var n=y.wrapper.children("."+y.params.slideNextClass);n.length>0&&y.lazy.loadImageInSlide(n.index());var o=y.wrapper.children("."+y.params.slidePrevClass);o.length>0&&y.lazy.loadImageInSlide(o.index())}},onTransitionStart:function(){y.params.lazyLoading&&(y.params.lazyLoadingOnTransitionStart||!y.params.lazyLoadingOnTransitionStart&&!y.lazy.initialImageLoaded)&&y.lazy.load()},onTransitionEnd:function(){y.params.lazyLoading&&!y.params.lazyLoadingOnTransitionStart&&y.lazy.load()}},y.scrollbar={isTouched:!1,setDragPosition:function(e){var a=y.scrollbar,t=y.isHorizontal()?"touchstart"===e.type||"touchmove"===e.type?e.targetTouches[0].pageX:e.pageX||e.clientX:"touchstart"===e.type||"touchmove"===e.type?e.targetTouches[0].pageY:e.pageY||e.clientY,s=t-a.track.offset()[y.isHorizontal()?"left":"top"]-a.dragSize/2,r=-y.minTranslate()*a.moveDivider,i=-y.maxTranslate()*a.moveDivider;r>s?s=r:s>i&&(s=i),s=-s/a.moveDivider,y.updateProgress(s),y.setWrapperTranslate(s,!0)},dragStart:function(e){var a=y.scrollbar;a.isTouched=!0,e.preventDefault(),e.stopPropagation(),a.setDragPosition(e),clearTimeout(a.dragTimeout),a.track.transition(0),y.params.scrollbarHide&&a.track.css("opacity",1),y.wrapper.transition(100),a.drag.transition(100),y.emit("onScrollbarDragStart",y)},dragMove:function(e){var a=y.scrollbar;a.isTouched&&(e.preventDefault?e.preventDefault():e.returnValue=!1,a.setDragPosition(e),y.wrapper.transition(0),a.track.transition(0),a.drag.transition(0),y.emit("onScrollbarDragMove",y))},dragEnd:function(){var e=y.scrollbar;e.isTouched&&(e.isTouched=!1,y.params.scrollbarHide&&(clearTimeout(e.dragTimeout),e.dragTimeout=setTimeout(function(){e.track.css("opacity",0),e.track.transition(400)},1e3)),y.emit("onScrollbarDragEnd",y),y.params.scrollbarSnapOnRelease&&y.slideReset())},enableDraggable:function(){var e=y.scrollbar,t=y.support.touch?e.track:document;a(e.track).on(y.touchEvents.start,e.dragStart),a(t).on(y.touchEvents.move,e.dragMove),a(t).on(y.touchEvents.end,e.dragEnd)},disableDraggable:function(){var e=y.scrollbar,t=y.support.touch?e.track:document;a(e.track).off(y.touchEvents.start,e.dragStart),a(t).off(y.touchEvents.move,e.dragMove),a(t).off(y.touchEvents.end,e.dragEnd)},set:function(){if(y.params.scrollbar){var e=y.scrollbar;e.track=a(y.params.scrollbar),y.params.uniqueNavElements&&"string"==typeof y.params.scrollbar&&e.track.length>1&&1===y.container.find(y.params.scrollbar).length&&(e.track=y.container.find(y.params.scrollbar)),e.drag=e.track.find(".swiper-scrollbar-drag"),0===e.drag.length&&(e.drag=a('<div class="swiper-scrollbar-drag"></div>'),e.track.append(e.drag)),e.drag[0].style.width="",e.drag[0].style.height="",e.trackSize=y.isHorizontal()?e.track[0].offsetWidth:e.track[0].offsetHeight,e.divider=y.size/y.virtualSize,e.moveDivider=e.divider*(e.trackSize/y.size),e.dragSize=e.trackSize*e.divider,y.isHorizontal()?e.drag[0].style.width=e.dragSize+"px":e.drag[0].style.height=e.dragSize+"px",e.track[0].style.display=e.divider>=1?"none":"",y.params.scrollbarHide&&(e.track[0].style.opacity=0)}},setTranslate:function(){if(y.params.scrollbar){var e,a=y.scrollbar,t=(y.translate||0,a.dragSize);e=(a.trackSize-a.dragSize)*y.progress,y.rtl&&y.isHorizontal()?(e=-e,e>0?(t=a.dragSize-e,e=0):-e+a.dragSize>a.trackSize&&(t=a.trackSize+e)):0>e?(t=a.dragSize+e,e=0):e+a.dragSize>a.trackSize&&(t=a.trackSize-e),y.isHorizontal()?(a.drag.transform(y.support.transforms3d?"translate3d("+e+"px, 0, 0)":"translateX("+e+"px)"),a.drag[0].style.width=t+"px"):(a.drag.transform(y.support.transforms3d?"translate3d(0px, "+e+"px, 0)":"translateY("+e+"px)"),a.drag[0].style.height=t+"px"),y.params.scrollbarHide&&(clearTimeout(a.timeout),a.track[0].style.opacity=1,a.timeout=setTimeout(function(){a.track[0].style.opacity=0,a.track.transition(400)},1e3))}},setTransition:function(e){y.params.scrollbar&&y.scrollbar.drag.transition(e)}},y.controller={LinearSpline:function(e,a){this.x=e,this.y=a,this.lastIndex=e.length-1;var t,s;this.x.length,this.interpolate=function(e){return e?(s=r(this.x,e),t=s-1,(e-this.x[t])*(this.y[s]-this.y[t])/(this.x[s]-this.x[t])+this.y[t]):0};var r=function(){var e,a,t;return function(s,r){for(a=-1,e=s.length;e-a>1;)s[t=e+a>>1]<=r?a=t:e=t;return e}}()},getInterpolateFunction:function(e){y.controller.spline||(y.controller.spline=y.params.loop?new y.controller.LinearSpline(y.slidesGrid,e.slidesGrid):new y.controller.LinearSpline(y.snapGrid,e.snapGrid))},setTranslate:function(e,a){function s(a){e=a.rtl&&"horizontal"===a.params.direction?-y.translate:y.translate,"slide"===y.params.controlBy&&(y.controller.getInterpolateFunction(a),i=-y.controller.spline.interpolate(-e)),i&&"container"!==y.params.controlBy||(r=(a.maxTranslate()-a.minTranslate())/(y.maxTranslate()-y.minTranslate()),i=(e-y.minTranslate())*r+a.minTranslate()),y.params.controlInverse&&(i=a.maxTranslate()-i),a.updateProgress(i),a.setWrapperTranslate(i,!1,y),a.updateActiveIndex()}var r,i,n=y.params.control;if(y.isArray(n))for(var o=0;o<n.length;o++)n[o]!==a&&n[o]instanceof t&&s(n[o]);else n instanceof t&&a!==n&&s(n)},setTransition:function(e,a){function s(a){a.setWrapperTransition(e,y),0!==e&&(a.onTransitionStart(),a.wrapper.transitionEnd(function(){i&&(a.params.loop&&"slide"===y.params.controlBy&&a.fixLoop(),a.onTransitionEnd())}))}var r,i=y.params.control;if(y.isArray(i))for(r=0;r<i.length;r++)i[r]!==a&&i[r]instanceof t&&s(i[r]);else i instanceof t&&a!==i&&s(i)}},y.hashnav={init:function(){if(y.params.hashnav){y.hashnav.initialized=!0;var e=document.location.hash.replace("#","");if(e)for(var a=0,t=0,s=y.slides.length;s>t;t++){var r=y.slides.eq(t),i=r.attr("data-hash");if(i===e&&!r.hasClass(y.params.slideDuplicateClass)){var n=r.index();y.slideTo(n,a,y.params.runCallbacksOnInit,!0)}}}},setHash:function(){y.hashnav.initialized&&y.params.hashnav&&(document.location.hash=y.slides.eq(y.activeIndex).attr("data-hash")||"")}},y.disableKeyboardControl=function(){y.params.keyboardControl=!1,a(document).off("keydown",l)},y.enableKeyboardControl=function(){y.params.keyboardControl=!0,a(document).on("keydown",l)},y.mousewheel={event:!1,lastScrollTime:(new window.Date).getTime()},y.params.mousewheelControl){try{new window.WheelEvent("wheel"),y.mousewheel.event="wheel"}catch(O){(window.WheelEvent||y.container[0]&&"wheel"in y.container[0])&&(y.mousewheel.event="wheel")}!y.mousewheel.event&&window.WheelEvent,y.mousewheel.event||void 0===document.onmousewheel||(y.mousewheel.event="mousewheel"),y.mousewheel.event||(y.mousewheel.event="DOMMouseScroll")}y.disableMousewheelControl=function(){return y.mousewheel.event?(y.container.off(y.mousewheel.event,p),!0):!1},y.enableMousewheelControl=function(){return y.mousewheel.event?(y.container.on(y.mousewheel.event,p),!0):!1},y.parallax={setTranslate:function(){y.container.children("[data-swiper-parallax], [data-swiper-parallax-x], [data-swiper-parallax-y]").each(function(){d(this,y.progress)}),y.slides.each(function(){var e=a(this);e.find("[data-swiper-parallax], [data-swiper-parallax-x], [data-swiper-parallax-y]").each(function(){var a=Math.min(Math.max(e[0].progress,-1),1);d(this,a)})})},setTransition:function(e){"undefined"==typeof e&&(e=y.params.speed),y.container.find("[data-swiper-parallax], [data-swiper-parallax-x], [data-swiper-parallax-y]").each(function(){var t=a(this),s=parseInt(t.attr("data-swiper-parallax-duration"),10)||e;0===e&&(s=0),t.transition(s)})}},y._plugins=[];for(var N in y.plugins){var R=y.plugins[N](y,y.params[N]);R&&y._plugins.push(R)}return y.callPlugins=function(e){for(var a=0;a<y._plugins.length;a++)e in y._plugins[a]&&y._plugins[a][e](arguments[1],arguments[2],arguments[3],arguments[4],arguments[5])},y.emitterEventListeners={},y.emit=function(e){y.params[e]&&y.params[e](arguments[1],arguments[2],arguments[3],arguments[4],arguments[5]);var a;if(y.emitterEventListeners[e])for(a=0;a<y.emitterEventListeners[e].length;a++)y.emitterEventListeners[e][a](arguments[1],arguments[2],arguments[3],arguments[4],arguments[5]);y.callPlugins&&y.callPlugins(e,arguments[1],arguments[2],arguments[3],arguments[4],arguments[5])},y.on=function(e,a){return e=u(e),y.emitterEventListeners[e]||(y.emitterEventListeners[e]=[]),y.emitterEventListeners[e].push(a),y},y.off=function(e,a){var t;if(e=u(e),"undefined"==typeof a)return y.emitterEventListeners[e]=[],y;if(y.emitterEventListeners[e]&&0!==y.emitterEventListeners[e].length){for(t=0;t<y.emitterEventListeners[e].length;t++)y.emitterEventListeners[e][t]===a&&y.emitterEventListeners[e].splice(t,1);return y}},y.once=function(e,a){e=u(e);var t=function(){a(arguments[0],arguments[1],arguments[2],arguments[3],arguments[4]),y.off(e,t)};return y.on(e,t),y},y.a11y={makeFocusable:function(e){return e.attr("tabIndex","0"),e},addRole:function(e,a){return e.attr("role",a),e},addLabel:function(e,a){return e.attr("aria-label",a),e},disable:function(e){return e.attr("aria-disabled",!0),e},enable:function(e){return e.attr("aria-disabled",!1),e},onEnterKey:function(e){13===e.keyCode&&(a(e.target).is(y.params.nextButton)?(y.onClickNext(e),y.a11y.notify(y.isEnd?y.params.lastSlideMessage:y.params.nextSlideMessage)):a(e.target).is(y.params.prevButton)&&(y.onClickPrev(e),y.a11y.notify(y.isBeginning?y.params.firstSlideMessage:y.params.prevSlideMessage)),a(e.target).is("."+y.params.bulletClass)&&a(e.target)[0].click())},liveRegion:a('<span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span>'),notify:function(e){var a=y.a11y.liveRegion;0!==a.length&&(a.html(""),a.html(e))},init:function(){y.params.nextButton&&y.nextButton&&y.nextButton.length>0&&(y.a11y.makeFocusable(y.nextButton),y.a11y.addRole(y.nextButton,"button"),y.a11y.addLabel(y.nextButton,y.params.nextSlideMessage)),y.params.prevButton&&y.prevButton&&y.prevButton.length>0&&(y.a11y.makeFocusable(y.prevButton),y.a11y.addRole(y.prevButton,"button"),y.a11y.addLabel(y.prevButton,y.params.prevSlideMessage)),a(y.container).append(y.a11y.liveRegion)},initPagination:function(){y.params.pagination&&y.params.paginationClickable&&y.bullets&&y.bullets.length&&y.bullets.each(function(){var e=a(this);y.a11y.makeFocusable(e),y.a11y.addRole(e,"button"),y.a11y.addLabel(e,y.params.paginationBulletMessage.replace(/{{index}}/,e.index()+1))})},destroy:function(){y.a11y.liveRegion&&y.a11y.liveRegion.length>0&&y.a11y.liveRegion.remove()}},y.init=function(){y.params.loop&&y.createLoop(),y.updateContainerSize(),y.updateSlidesSize(),y.updatePagination(),y.params.scrollbar&&y.scrollbar&&(y.scrollbar.set(),y.params.scrollbarDraggable&&y.scrollbar.enableDraggable()),"slide"!==y.params.effect&&y.effects[y.params.effect]&&(y.params.loop||y.updateProgress(),y.effects[y.params.effect].setTranslate()),y.params.loop?y.slideTo(y.params.initialSlide+y.loopedSlides,0,y.params.runCallbacksOnInit):(y.slideTo(y.params.initialSlide,0,y.params.runCallbacksOnInit),0===y.params.initialSlide&&(y.parallax&&y.params.parallax&&y.parallax.setTranslate(),y.lazy&&y.params.lazyLoading&&(y.lazy.load(),y.lazy.initialImageLoaded=!0))),y.attachEvents(),y.params.observer&&y.support.observer&&y.initObservers(),y.params.preloadImages&&!y.params.lazyLoading&&y.preloadImages(),y.params.autoplay&&y.startAutoplay(),y.params.keyboardControl&&y.enableKeyboardControl&&y.enableKeyboardControl(),y.params.mousewheelControl&&y.enableMousewheelControl&&y.enableMousewheelControl(),y.params.hashnav&&y.hashnav&&y.hashnav.init(),y.params.a11y&&y.a11y&&y.a11y.init(),y.emit("onInit",y)},y.cleanupStyles=function(){y.container.removeClass(y.classNames.join(" ")).removeAttr("style"),y.wrapper.removeAttr("style"),y.slides&&y.slides.length&&y.slides.removeClass([y.params.slideVisibleClass,y.params.slideActiveClass,y.params.slideNextClass,y.params.slidePrevClass].join(" ")).removeAttr("style").removeAttr("data-swiper-column").removeAttr("data-swiper-row"),y.paginationContainer&&y.paginationContainer.length&&y.paginationContainer.removeClass(y.params.paginationHiddenClass),y.bullets&&y.bullets.length&&y.bullets.removeClass(y.params.bulletActiveClass),y.params.prevButton&&a(y.params.prevButton).removeClass(y.params.buttonDisabledClass),y.params.nextButton&&a(y.params.nextButton).removeClass(y.params.buttonDisabledClass),y.params.scrollbar&&y.scrollbar&&(y.scrollbar.track&&y.scrollbar.track.length&&y.scrollbar.track.removeAttr("style"),y.scrollbar.drag&&y.scrollbar.drag.length&&y.scrollbar.drag.removeAttr("style"))
},y.destroy=function(e,a){y.detachEvents(),y.stopAutoplay(),y.params.scrollbar&&y.scrollbar&&y.params.scrollbarDraggable&&y.scrollbar.disableDraggable(),y.params.loop&&y.destroyLoop(),a&&y.cleanupStyles(),y.disconnectObservers(),y.params.keyboardControl&&y.disableKeyboardControl&&y.disableKeyboardControl(),y.params.mousewheelControl&&y.disableMousewheelControl&&y.disableMousewheelControl(),y.params.a11y&&y.a11y&&y.a11y.destroy(),y.emit("onDestroy"),e!==!1&&(y=null)},y.init(),y}};t.prototype={isSafari:function(){var e=navigator.userAgent.toLowerCase();return e.indexOf("safari")>=0&&e.indexOf("chrome")<0&&e.indexOf("android")<0}(),isUiWebView:/(iPhone|iPod|iPad).*AppleWebKit(?!.*Safari)/i.test(navigator.userAgent),isArray:function(e){return"[object Array]"===Object.prototype.toString.apply(e)},browser:{ie:window.navigator.pointerEnabled||window.navigator.msPointerEnabled,ieTouch:window.navigator.msPointerEnabled&&window.navigator.msMaxTouchPoints>1||window.navigator.pointerEnabled&&window.navigator.maxTouchPoints>1},device:function(){var e=navigator.userAgent,a=e.match(/(Android);?[\s\/]+([\d.]+)?/),t=e.match(/(iPad).*OS\s([\d_]+)/),s=e.match(/(iPod)(.*OS\s([\d_]+))?/),r=!t&&e.match(/(iPhone\sOS)\s([\d_]+)/);return{ios:t||r||s,android:a}}(),support:{touch:window.Modernizr&&Modernizr.touch===!0||function(){return!!("ontouchstart"in window||window.DocumentTouch&&document instanceof DocumentTouch)}(),transforms3d:window.Modernizr&&Modernizr.csstransforms3d===!0||function(){var e=document.createElement("div").style;return"webkitPerspective"in e||"MozPerspective"in e||"OPerspective"in e||"MsPerspective"in e||"perspective"in e}(),flexbox:function(){for(var e=document.createElement("div").style,a="alignItems webkitAlignItems webkitBoxAlign msFlexAlign mozBoxAlign webkitFlexDirection msFlexDirection mozBoxDirection mozBoxOrient webkitBoxDirection webkitBoxOrient".split(" "),t=0;t<a.length;t++)if(a[t]in e)return!0}(),observer:function(){return"MutationObserver"in window||"WebkitMutationObserver"in window}()},plugins:{}};for(var s=["jQuery","Zepto","Dom7"],r=0;r<s.length;r++)window[s[r]]&&e(window[s[r]]);var i;i="undefined"==typeof Dom7?window.Dom7||window.Zepto||window.jQuery:Dom7,i&&("transitionEnd"in i.fn||(i.fn.transitionEnd=function(e){function a(i){if(i.target===this)for(e.call(this,i),t=0;t<s.length;t++)r.off(s[t],a)}var t,s=["webkitTransitionEnd","transitionend","oTransitionEnd","MSTransitionEnd","msTransitionEnd"],r=this;if(e)for(t=0;t<s.length;t++)r.on(s[t],a);return this}),"transform"in i.fn||(i.fn.transform=function(e){for(var a=0;a<this.length;a++){var t=this[a].style;t.webkitTransform=t.MsTransform=t.msTransform=t.MozTransform=t.OTransform=t.transform=e}return this}),"transition"in i.fn||(i.fn.transition=function(e){"string"!=typeof e&&(e+="ms");for(var a=0;a<this.length;a++){var t=this[a].style;t.webkitTransitionDuration=t.MsTransitionDuration=t.msTransitionDuration=t.MozTransitionDuration=t.OTransitionDuration=t.transitionDuration=e}return this})),window.Swiper=t}(),"undefined"!=typeof module?module.exports=window.Swiper:"function"==typeof define&&define.amd&&define([],function(){"use strict";return window.Swiper});</script>
	<script type="text/javascript">!function(t){"function"==typeof define&&define.amd?define(["jquery"],t):t(window.jQuery||window.Zepto)}(function(t){function e(){}function a(t,e){var a;return a=e._$container==s?("innerHeight"in _?_.innerHeight:s.height())+s.scrollTop():e._$container.offset().top+e._$container.height(),a<=t.offset().top-e.threshold}function r(e,a){var r;return r=a._$container==s?s.width()+(t.fn.scrollLeft?s.scrollLeft():_.pageXOffset):a._$container.offset().left+a._$container.width(),r<=e.offset().left-a.threshold}function n(t,e){var a;return a=e._$container==s?s.scrollTop():e._$container.offset().top,a>=t.offset().top+e.threshold+t.height()}function o(e,a){var r;return r=a._$container==s?t.fn.scrollLeft?s.scrollLeft():_.pageXOffset:a._$container.offset().left,r>=e.offset().left+a.threshold+e.width()}function l(t,e){var l=0;t.each(function(i){function c(){f.trigger("_lazyload_appear"),l=0}var f=t.eq(i);if(!(f.width()<=0&&f.height()<=0||"none"===f.css("display")))if(e.vertical_only)if(n(f,e));else if(a(f,e)){if(++l>e.failure_limit)return!1}else c();else if(n(f,e)||o(f,e));else if(a(f,e)||r(f,e)){if(++l>e.failure_limit)return!1}else c()})}function i(t){return t.filter(function(e){return!t.eq(e)._lazyload_loadStarted})}function c(t,e){function a(){l=0,i=+new Date,o=t.apply(r,n),r=null,n=null}var r,n,o,l,i=0;return function(){r=this,n=arguments;var t=new Date-i;return l||(t>=e?a():l=setTimeout(a,e-t)),o}}var f,_=window,s=t(_),d={threshold:0,failure_limit:0,event:"scroll",effect:"show",effect_params:null,container:_,data_attribute:"original",data_srcset_attribute:"original-srcset",skip_invisible:!0,appear:e,load:e,vertical_only:!1,check_appear_throttle_time:300,url_rewriter_fn:e,no_fake_img_loader:!1,placeholder_data_img:"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAANSURBVBhXYzh8+PB/AAffA0nNPuCLAAAAAElFTkSuQmCC",placeholder_real_img:"http://ditu.baidu.cn/yyfm/lazyload/0.0.1/img/placeholder.png"};f=function(){var t=Object.prototype.toString;return function(e){return t.call(e).replace("[object ","").replace("]","")}}(),t.fn.hasOwnProperty("lazyload")||(t.fn.lazyload=function(a){var r,n,o,u=this;return t.isPlainObject(a)||(a={}),t.each(d,function(e,r){-1!=t.inArray(e,["threshold","failure_limit","check_appear_throttle_time"])?a[e]="String"==f(a[e])?parseInt(a[e],10):r:"container"==e?(a._$container=a.hasOwnProperty(e)?a[e]==_||a[e]==document?s:t(a[e]):s,delete a.container):!d.hasOwnProperty(e)||a.hasOwnProperty(e)&&f(a[e])==f(d[e])||(a[e]=r)}),r="scroll"==a.event,o=0==a.check_appear_throttle_time?l:c(l,a.check_appear_throttle_time),n=r||"scrollstart"==a.event||"scrollstop"==a.event,u.each(function(r){var o=this,l=u.eq(r),c=l.attr("src"),f=l.attr("data-"+a.data_attribute),_=a.url_rewriter_fn==e?f:a.url_rewriter_fn.call(o,l,f),s=l.attr("data-"+a.data_srcset_attribute),d=l.is("img");return 1==l._lazyload_loadStarted||c==_?(l._lazyload_loadStarted=!0,void(u=i(u))):(l._lazyload_loadStarted=!1,d&&!c&&l.one("error",function(){l.attr("src",a.placeholder_real_img)}).attr("src",a.placeholder_data_img),l.one("_lazyload_appear",function(){function r(){n&&l.hide(),d?(s&&l.attr("srcset",s),_&&l.attr("src",_)):l.css("background-image",'url("'+_+'")'),n&&l[a.effect].apply(l,c?a.effect_params:[]),u=i(u)}var n,c=t.isArray(a.effect_params);l._lazyload_loadStarted||(n="show"!=a.effect&&t.fn[a.effect]&&(!a.effect_params||c&&0==a.effect_params.length),a.appear!=e&&a.appear.call(o,l,u.length,a),l._lazyload_loadStarted=!0,a.no_fake_img_loader||s?(a.load!=e&&l.one("load",function(){a.load.call(o,l,u.length,a)}),r()):t("<img />").one("load",function(){r(),a.load!=e&&a.load.call(o,l,u.length,a)}).attr("src",_))}),void(n||l.on(a.event,function(){l._lazyload_loadStarted||l.trigger("_lazyload_appear")})))}),n&&a._$container.on(a.event,function(){o(u,a)}),s.on("resize load",function(){o(u,a)}),t(function(){o(u,a)}),this})});</script>
	<script type="text/javascript">!function(t,i,s){function e(t,s){this.wrapper="string"==typeof t?i.querySelector(t):t,this.scroller=this.wrapper.children[0],this.scrollerStyle=this.scroller.style,this.options={resizeScrollbars:!0,mouseWheelSpeed:20,snapThreshold:.334,disablePointer:!h.hasPointer,disableTouch:h.hasPointer||!h.hasTouch,disableMouse:h.hasPointer||h.hasTouch,startX:0,startY:0,scrollY:!0,directionLockThreshold:5,momentum:!0,bounce:!0,bounceTime:600,bounceEasing:"",preventDefault:!0,preventDefaultException:{tagName:/^(INPUT|TEXTAREA|BUTTON|SELECT)$/},HWCompositing:!0,useTransition:!0,useTransform:!0};for(var e in s)this.options[e]=s[e];this.translateZ=this.options.HWCompositing&&h.hasPerspective?" translateZ(0)":"",this.options.useTransition=h.hasTransition&&this.options.useTransition,this.options.useTransform=h.hasTransform&&this.options.useTransform,this.options.eventPassthrough=this.options.eventPassthrough===!0?"vertical":this.options.eventPassthrough,this.options.preventDefault=!this.options.eventPassthrough&&this.options.preventDefault,this.options.scrollY="vertical"==this.options.eventPassthrough?!1:this.options.scrollY,this.options.scrollX="horizontal"==this.options.eventPassthrough?!1:this.options.scrollX,this.options.freeScroll=this.options.freeScroll&&!this.options.eventPassthrough,this.options.directionLockThreshold=this.options.eventPassthrough?0:this.options.directionLockThreshold,this.options.bounceEasing="string"==typeof this.options.bounceEasing?h.ease[this.options.bounceEasing]||h.ease.circular:this.options.bounceEasing,this.options.resizePolling=void 0===this.options.resizePolling?60:this.options.resizePolling,this.options.tap===!0&&(this.options.tap="tap"),"scale"==this.options.shrinkScrollbars&&(this.options.useTransition=!1),this.options.invertWheelDirection=this.options.invertWheelDirection?-1:1,this.x=0,this.y=0,this.directionX=0,this.directionY=0,this._events={},this._init(),this.refresh(),this.scrollTo(this.options.startX,this.options.startY),this.enable()}function o(t,s,e){var o=i.createElement("div"),n=i.createElement("div");return e===!0&&(o.style.cssText="position:absolute;z-index:9999",n.style.cssText="-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;position:absolute;background:rgba(0,0,0,0.5);border:1px solid rgba(255,255,255,0.9);border-radius:3px"),n.className="iScrollIndicator","h"==t?(e===!0&&(o.style.cssText+=";height:7px;left:2px;right:2px;bottom:0",n.style.height="100%"),o.className="iScrollHorizontalScrollbar"):(e===!0&&(o.style.cssText+=";width:7px;bottom:2px;top:2px;right:1px",n.style.width="100%"),o.className="iScrollVerticalScrollbar"),o.style.cssText+=";overflow:hidden",s||(o.style.pointerEvents="none"),o.appendChild(n),o}function n(s,e){this.wrapper="string"==typeof e.el?i.querySelector(e.el):e.el,this.wrapperStyle=this.wrapper.style,this.indicator=this.wrapper.children[0],this.indicatorStyle=this.indicator.style,this.scroller=s,this.options={listenX:!0,listenY:!0,interactive:!1,resize:!0,defaultScrollbars:!1,shrink:!1,fade:!1,speedRatioX:0,speedRatioY:0};for(var o in e)this.options[o]=e[o];if(this.sizeRatioX=1,this.sizeRatioY=1,this.maxPosX=0,this.maxPosY=0,this.options.interactive&&(this.options.disableTouch||(h.addEvent(this.indicator,"touchstart",this),h.addEvent(t,"touchend",this)),this.options.disablePointer||(h.addEvent(this.indicator,h.prefixPointerEvent("pointerdown"),this),h.addEvent(t,h.prefixPointerEvent("pointerup"),this)),this.options.disableMouse||(h.addEvent(this.indicator,"mousedown",this),h.addEvent(t,"mouseup",this))),this.options.fade){this.wrapperStyle[h.style.transform]=this.scroller.translateZ;var n=h.style.transitionDuration;this.wrapperStyle[n]=h.isBadAndroid?"0.0001ms":"0ms";var a=this;h.isBadAndroid&&r(function(){"0.0001ms"===a.wrapperStyle[n]&&(a.wrapperStyle[n]="0s")}),this.wrapperStyle.opacity="0"}}var r=t.requestAnimationFrame||t.webkitRequestAnimationFrame||t.mozRequestAnimationFrame||t.oRequestAnimationFrame||t.msRequestAnimationFrame||function(i){t.setTimeout(i,1e3/60)},h=function(){function e(t){return r===!1?!1:""===r?t:r+t.charAt(0).toUpperCase()+t.substr(1)}var o={},n=i.createElement("div").style,r=function(){for(var t,i=["t","webkitT","MozT","msT","OT"],s=0,e=i.length;e>s;s++)if(t=i[s]+"ransform",t in n)return i[s].substr(0,i[s].length-1);return!1}();o.getTime=Date.now||function(){return(new Date).getTime()},o.extend=function(t,i){for(var s in i)t[s]=i[s]},o.addEvent=function(t,i,s,e){t.addEventListener(i,s,!!e)},o.removeEvent=function(t,i,s,e){t.removeEventListener(i,s,!!e)},o.prefixPointerEvent=function(i){return t.MSPointerEvent?"MSPointer"+i.charAt(7).toUpperCase()+i.substr(8):i},o.momentum=function(t,i,e,o,n,r){var h,a,l=t-i,c=s.abs(l)/e;return r=void 0===r?6e-4:r,h=t+c*c/(2*r)*(0>l?-1:1),a=c/r,o>h?(h=n?o-n/2.5*(c/8):o,l=s.abs(h-t),a=l/c):h>0&&(h=n?n/2.5*(c/8):0,l=s.abs(t)+h,a=l/c),{destination:s.round(h),duration:a}};var h=e("transform");return o.extend(o,{hasTransform:h!==!1,hasPerspective:e("perspective")in n,hasTouch:"ontouchstart"in t,hasPointer:!(!t.PointerEvent&&!t.MSPointerEvent),hasTransition:e("transition")in n}),o.isBadAndroid=function(){var i=t.navigator.appVersion;if(/Android/.test(i)&&!/Chrome\/\d/.test(i)){var s=i.match(/Safari\/(\d+.\d)/);return s&&"object"==typeof s&&s.length>=2?parseFloat(s[1])<535.19:!0}return!1}(),o.extend(o.style={},{transform:h,transitionTimingFunction:e("transitionTimingFunction"),transitionDuration:e("transitionDuration"),transitionDelay:e("transitionDelay"),transformOrigin:e("transformOrigin")}),o.hasClass=function(t,i){var s=new RegExp("(^|\\s)"+i+"(\\s|$)");return s.test(t.className)},o.addClass=function(t,i){if(!o.hasClass(t,i)){var s=t.className.split(" ");s.push(i),t.className=s.join(" ")}},o.removeClass=function(t,i){if(o.hasClass(t,i)){var s=new RegExp("(^|\\s)"+i+"(\\s|$)","g");t.className=t.className.replace(s," ")}},o.offset=function(t){for(var i=-t.offsetLeft,s=-t.offsetTop;t=t.offsetParent;)i-=t.offsetLeft,s-=t.offsetTop;return{left:i,top:s}},o.preventDefaultException=function(t,i){for(var s in i)if(i[s].test(t[s]))return!0;return!1},o.extend(o.eventType={},{touchstart:1,touchmove:1,touchend:1,mousedown:2,mousemove:2,mouseup:2,pointerdown:3,pointermove:3,pointerup:3,MSPointerDown:3,MSPointerMove:3,MSPointerUp:3}),o.extend(o.ease={},{quadratic:{style:"cubic-bezier(0.25, 0.46, 0.45, 0.94)",fn:function(t){return t*(2-t)}},circular:{style:"cubic-bezier(0.1, 0.57, 0.1, 1)",fn:function(t){return s.sqrt(1- --t*t)}},back:{style:"cubic-bezier(0.175, 0.885, 0.32, 1.275)",fn:function(t){var i=4;return(t-=1)*t*((i+1)*t+i)+1}},bounce:{style:"",fn:function(t){return(t/=1)<1/2.75?7.5625*t*t:2/2.75>t?7.5625*(t-=1.5/2.75)*t+.75:2.5/2.75>t?7.5625*(t-=2.25/2.75)*t+.9375:7.5625*(t-=2.625/2.75)*t+.984375}},elastic:{style:"",fn:function(t){var i=.22,e=.4;return 0===t?0:1==t?1:e*s.pow(2,-10*t)*s.sin(2*(t-i/4)*s.PI/i)+1}}}),o.tap=function(t,s){var e=i.createEvent("Event");e.initEvent(s,!0,!0),e.pageX=t.pageX,e.pageY=t.pageY,t.target.dispatchEvent(e)},o.click=function(t){var s,e=t.target;/(SELECT|INPUT|TEXTAREA)/i.test(e.tagName)||(s=i.createEvent("MouseEvents"),s.initMouseEvent("click",!0,!0,t.view,1,e.screenX,e.screenY,e.clientX,e.clientY,t.ctrlKey,t.altKey,t.shiftKey,t.metaKey,0,null),s._constructed=!0,e.dispatchEvent(s))},o}();e.prototype={version:"5.1.3",_init:function(){this._initEvents(),(this.options.scrollbars||this.options.indicators)&&this._initIndicators(),this.options.mouseWheel&&this._initWheel(),this.options.snap&&this._initSnap(),this.options.keyBindings&&this._initKeys()},destroy:function(){this._initEvents(!0),this._execEvent("destroy")},_transitionEnd:function(t){t.target==this.scroller&&this.isInTransition&&(this._transitionTime(),this.resetPosition(this.options.bounceTime)||(this.isInTransition=!1,this._execEvent("scrollEnd")))},_start:function(t){if(!(1!=h.eventType[t.type]&&0!==t.button||!this.enabled||this.initiated&&h.eventType[t.type]!==this.initiated)){!this.options.preventDefault||h.isBadAndroid||h.preventDefaultException(t.target,this.options.preventDefaultException)||t.preventDefault();var i,e=t.touches?t.touches[0]:t;this.initiated=h.eventType[t.type],this.moved=!1,this.distX=0,this.distY=0,this.directionX=0,this.directionY=0,this.directionLocked=0,this.startTime=h.getTime(),this.options.useTransition&&this.isInTransition?(this._transitionTime(),this.isInTransition=!1,i=this.getComputedPosition(),this._translate(s.round(i.x),s.round(i.y)),this._execEvent("scrollEnd")):!this.options.useTransition&&this.isAnimating&&(this.isAnimating=!1,this._execEvent("scrollEnd")),this.startX=this.x,this.startY=this.y,this.absStartX=this.x,this.absStartY=this.y,this.pointX=e.pageX,this.pointY=e.pageY,this._execEvent("beforeScrollStart")}},_move:function(t){if(this.enabled&&h.eventType[t.type]===this.initiated){this.options.preventDefault&&t.preventDefault();var i,e,o,n,r=t.touches?t.touches[0]:t,a=r.pageX-this.pointX,l=r.pageY-this.pointY,c=h.getTime();if(this.pointX=r.pageX,this.pointY=r.pageY,this.distX+=a,this.distY+=l,o=s.abs(this.distX),n=s.abs(this.distY),!(c-this.endTime>300&&10>o&&10>n)){if(this.directionLocked||this.options.freeScroll||(this.directionLocked=o>n+this.options.directionLockThreshold?"h":n>=o+this.options.directionLockThreshold?"v":"n"),"h"==this.directionLocked){if("vertical"==this.options.eventPassthrough)t.preventDefault();else if("horizontal"==this.options.eventPassthrough)return void(this.initiated=!1);l=0}else if("v"==this.directionLocked){if("horizontal"==this.options.eventPassthrough)t.preventDefault();else if("vertical"==this.options.eventPassthrough)return void(this.initiated=!1);a=0}a=this.hasHorizontalScroll?a:0,l=this.hasVerticalScroll?l:0,i=this.x+a,e=this.y+l,(i>0||i<this.maxScrollX)&&(i=this.options.bounce?this.x+a/3:i>0?0:this.maxScrollX),(e>0||e<this.maxScrollY)&&(e=this.options.bounce?this.y+l/3:e>0?0:this.maxScrollY),this.directionX=a>0?-1:0>a?1:0,this.directionY=l>0?-1:0>l?1:0,this.moved||this._execEvent("scrollStart"),this.moved=!0,this._translate(i,e),c-this.startTime>300&&(this.startTime=c,this.startX=this.x,this.startY=this.y)}}},_end:function(t){if(this.enabled&&h.eventType[t.type]===this.initiated){this.options.preventDefault&&!h.preventDefaultException(t.target,this.options.preventDefaultException)&&t.preventDefault();var i,e,o=(t.changedTouches?t.changedTouches[0]:t,h.getTime()-this.startTime),n=s.round(this.x),r=s.round(this.y),a=s.abs(n-this.startX),l=s.abs(r-this.startY),c=0,p="";if(this.isInTransition=0,this.initiated=0,this.endTime=h.getTime(),!this.resetPosition(this.options.bounceTime)){if(this.scrollTo(n,r),!this.moved)return this.options.tap&&h.tap(t,this.options.tap),this.options.click&&h.click(t),void this._execEvent("scrollCancel");if(this._events.flick&&200>o&&100>a&&100>l)return void this._execEvent("flick");if(this.options.momentum&&300>o&&(i=this.hasHorizontalScroll?h.momentum(this.x,this.startX,o,this.maxScrollX,this.options.bounce?this.wrapperWidth:0,this.options.deceleration):{destination:n,duration:0},e=this.hasVerticalScroll?h.momentum(this.y,this.startY,o,this.maxScrollY,this.options.bounce?this.wrapperHeight:0,this.options.deceleration):{destination:r,duration:0},n=i.destination,r=e.destination,c=s.max(i.duration,e.duration),this.isInTransition=1),this.options.snap){var d=this._nearestSnap(n,r);this.currentPage=d,c=this.options.snapSpeed||s.max(s.max(s.min(s.abs(n-d.x),1e3),s.min(s.abs(r-d.y),1e3)),300),n=d.x,r=d.y,this.directionX=0,this.directionY=0,p=this.options.bounceEasing}return n!=this.x||r!=this.y?((n>0||n<this.maxScrollX||r>0||r<this.maxScrollY)&&(p=h.ease.quadratic),void this.scrollTo(n,r,c,p)):void this._execEvent("scrollEnd")}}},_resize:function(){var t=this;clearTimeout(this.resizeTimeout),this.resizeTimeout=setTimeout(function(){t.refresh()},this.options.resizePolling)},resetPosition:function(t){var i=this.x,s=this.y;return t=t||0,!this.hasHorizontalScroll||this.x>0?i=0:this.x<this.maxScrollX&&(i=this.maxScrollX),!this.hasVerticalScroll||this.y>0?s=0:this.y<this.maxScrollY&&(s=this.maxScrollY),i==this.x&&s==this.y?!1:(this.scrollTo(i,s,t,this.options.bounceEasing),!0)},disable:function(){this.enabled=!1},enable:function(){this.enabled=!0},refresh:function(){this.wrapper.offsetHeight;this.wrapperWidth=this.wrapper.clientWidth,this.wrapperHeight=this.wrapper.clientHeight,this.scrollerWidth=this.scroller.offsetWidth,this.scrollerHeight=this.scroller.offsetHeight,this.maxScrollX=this.wrapperWidth-this.scrollerWidth,this.maxScrollY=this.wrapperHeight-this.scrollerHeight,this.hasHorizontalScroll=this.options.scrollX&&this.maxScrollX<0,this.hasVerticalScroll=this.options.scrollY&&this.maxScrollY<0,this.hasHorizontalScroll||(this.maxScrollX=0,this.scrollerWidth=this.wrapperWidth),this.hasVerticalScroll||(this.maxScrollY=0,this.scrollerHeight=this.wrapperHeight),this.endTime=0,this.directionX=0,this.directionY=0,this.wrapperOffset=h.offset(this.wrapper),this._execEvent("refresh"),this.resetPosition()},on:function(t,i){this._events[t]||(this._events[t]=[]),this._events[t].push(i)},off:function(t,i){if(this._events[t]){var s=this._events[t].indexOf(i);s>-1&&this._events[t].splice(s,1)}},_execEvent:function(t){if(this._events[t]){var i=0,s=this._events[t].length;if(s)for(;s>i;i++)this._events[t][i].apply(this,[].slice.call(arguments,1))}},scrollBy:function(t,i,s,e){t=this.x+t,i=this.y+i,s=s||0,this.scrollTo(t,i,s,e)},scrollTo:function(t,i,s,e){e=e||h.ease.circular,this.isInTransition=this.options.useTransition&&s>0;var o=this.options.useTransition&&e.style;!s||o?(o&&(this._transitionTimingFunction(e.style),this._transitionTime(s)),this._translate(t,i)):this._animate(t,i,s,e.fn)},scrollToElement:function(t,i,e,o,n){if(t=t.nodeType?t:this.scroller.querySelector(t)){var r=h.offset(t);r.left-=this.wrapperOffset.left,r.top-=this.wrapperOffset.top,e===!0&&(e=s.round(t.offsetWidth/2-this.wrapper.offsetWidth/2)),o===!0&&(o=s.round(t.offsetHeight/2-this.wrapper.offsetHeight/2)),r.left-=e||0,r.top-=o||0,r.left=r.left>0?0:r.left<this.maxScrollX?this.maxScrollX:r.left,r.top=r.top>0?0:r.top<this.maxScrollY?this.maxScrollY:r.top,i=void 0===i||null===i||"auto"===i?s.max(s.abs(this.x-r.left),s.abs(this.y-r.top)):i,this.scrollTo(r.left,r.top,i,n)}},_transitionTime:function(t){t=t||0;var i=h.style.transitionDuration;if(this.scrollerStyle[i]=t+"ms",!t&&h.isBadAndroid){this.scrollerStyle[i]="0.0001ms";var s=this;r(function(){"0.0001ms"===s.scrollerStyle[i]&&(s.scrollerStyle[i]="0s")})}if(this.indicators)for(var e=this.indicators.length;e--;)this.indicators[e].transitionTime(t)},_transitionTimingFunction:function(t){if(this.scrollerStyle[h.style.transitionTimingFunction]=t,this.indicators)for(var i=this.indicators.length;i--;)this.indicators[i].transitionTimingFunction(t)},_translate:function(t,i){if(this.options.useTransform?this.scrollerStyle[h.style.transform]="translate("+t+"px,"+i+"px)"+this.translateZ:(t=s.round(t),i=s.round(i),this.scrollerStyle.left=t+"px",this.scrollerStyle.top=i+"px"),this.x=t,this.y=i,this.indicators)for(var e=this.indicators.length;e--;)this.indicators[e].updatePosition()},_initEvents:function(i){var s=i?h.removeEvent:h.addEvent,e=this.options.bindToWrapper?this.wrapper:t;s(t,"orientationchange",this),s(t,"resize",this),this.options.click&&s(this.wrapper,"click",this,!0),this.options.disableMouse||(s(this.wrapper,"mousedown",this),s(e,"mousemove",this),s(e,"mousecancel",this),s(e,"mouseup",this)),h.hasPointer&&!this.options.disablePointer&&(s(this.wrapper,h.prefixPointerEvent("pointerdown"),this),s(e,h.prefixPointerEvent("pointermove"),this),s(e,h.prefixPointerEvent("pointercancel"),this),s(e,h.prefixPointerEvent("pointerup"),this)),h.hasTouch&&!this.options.disableTouch&&(s(this.wrapper,"touchstart",this),s(e,"touchmove",this),s(e,"touchcancel",this),s(e,"touchend",this)),s(this.scroller,"transitionend",this),s(this.scroller,"webkitTransitionEnd",this),s(this.scroller,"oTransitionEnd",this),s(this.scroller,"MSTransitionEnd",this)},getComputedPosition:function(){var i,s,e=t.getComputedStyle(this.scroller,null);return this.options.useTransform?(e=e[h.style.transform].split(")")[0].split(", "),i=+(e[12]||e[4]),s=+(e[13]||e[5])):(i=+e.left.replace(/[^-\d.]/g,""),s=+e.top.replace(/[^-\d.]/g,"")),{x:i,y:s}},_initIndicators:function(){function t(t){for(var i=h.indicators.length;i--;)t.call(h.indicators[i])}var i,s=this.options.interactiveScrollbars,e="string"!=typeof this.options.scrollbars,r=[],h=this;this.indicators=[],this.options.scrollbars&&(this.options.scrollY&&(i={el:o("v",s,this.options.scrollbars),interactive:s,defaultScrollbars:!0,customStyle:e,resize:this.options.resizeScrollbars,shrink:this.options.shrinkScrollbars,fade:this.options.fadeScrollbars,listenX:!1},this.wrapper.appendChild(i.el),r.push(i)),this.options.scrollX&&(i={el:o("h",s,this.options.scrollbars),interactive:s,defaultScrollbars:!0,customStyle:e,resize:this.options.resizeScrollbars,shrink:this.options.shrinkScrollbars,fade:this.options.fadeScrollbars,listenY:!1},this.wrapper.appendChild(i.el),r.push(i))),this.options.indicators&&(r=r.concat(this.options.indicators));for(var a=r.length;a--;)this.indicators.push(new n(this,r[a]));this.options.fadeScrollbars&&(this.on("scrollEnd",function(){t(function(){this.fade()})}),this.on("scrollCancel",function(){t(function(){this.fade()})}),this.on("scrollStart",function(){t(function(){this.fade(1)})}),this.on("beforeScrollStart",function(){t(function(){this.fade(1,!0)})})),this.on("refresh",function(){t(function(){this.refresh()})}),this.on("destroy",function(){t(function(){this.destroy()}),delete this.indicators})},_initWheel:function(){h.addEvent(this.wrapper,"wheel",this),h.addEvent(this.wrapper,"mousewheel",this),h.addEvent(this.wrapper,"DOMMouseScroll",this),this.on("destroy",function(){h.removeEvent(this.wrapper,"wheel",this),h.removeEvent(this.wrapper,"mousewheel",this),h.removeEvent(this.wrapper,"DOMMouseScroll",this)})},_wheel:function(t){if(this.enabled){t.preventDefault(),t.stopPropagation();var i,e,o,n,r=this;if(void 0===this.wheelTimeout&&r._execEvent("scrollStart"),clearTimeout(this.wheelTimeout),this.wheelTimeout=setTimeout(function(){r._execEvent("scrollEnd"),r.wheelTimeout=void 0},400),"deltaX"in t)1===t.deltaMode?(i=-t.deltaX*this.options.mouseWheelSpeed,e=-t.deltaY*this.options.mouseWheelSpeed):(i=-t.deltaX,e=-t.deltaY);else if("wheelDeltaX"in t)i=t.wheelDeltaX/120*this.options.mouseWheelSpeed,e=t.wheelDeltaY/120*this.options.mouseWheelSpeed;else if("wheelDelta"in t)i=e=t.wheelDelta/120*this.options.mouseWheelSpeed;else{if(!("detail"in t))return;i=e=-t.detail/3*this.options.mouseWheelSpeed}if(i*=this.options.invertWheelDirection,e*=this.options.invertWheelDirection,this.hasVerticalScroll||(i=e,e=0),this.options.snap)return o=this.currentPage.pageX,n=this.currentPage.pageY,i>0?o--:0>i&&o++,e>0?n--:0>e&&n++,void this.goToPage(o,n);o=this.x+s.round(this.hasHorizontalScroll?i:0),n=this.y+s.round(this.hasVerticalScroll?e:0),o>0?o=0:o<this.maxScrollX&&(o=this.maxScrollX),n>0?n=0:n<this.maxScrollY&&(n=this.maxScrollY),this.scrollTo(o,n,0)}},_initSnap:function(){this.currentPage={},"string"==typeof this.options.snap&&(this.options.snap=this.scroller.querySelectorAll(this.options.snap)),this.on("refresh",function(){var t,i,e,o,n,r,h=0,a=0,l=0,c=this.options.snapStepX||this.wrapperWidth,p=this.options.snapStepY||this.wrapperHeight;if(this.pages=[],this.wrapperWidth&&this.wrapperHeight&&this.scrollerWidth&&this.scrollerHeight){if(this.options.snap===!0)for(e=s.round(c/2),o=s.round(p/2);l>-this.scrollerWidth;){for(this.pages[h]=[],t=0,n=0;n>-this.scrollerHeight;)this.pages[h][t]={x:s.max(l,this.maxScrollX),y:s.max(n,this.maxScrollY),width:c,height:p,cx:l-e,cy:n-o},n-=p,t++;l-=c,h++}else for(r=this.options.snap,t=r.length,i=-1;t>h;h++)(0===h||r[h].offsetLeft<=r[h-1].offsetLeft)&&(a=0,i++),this.pages[a]||(this.pages[a]=[]),l=s.max(-r[h].offsetLeft,this.maxScrollX),n=s.max(-r[h].offsetTop,this.maxScrollY),e=l-s.round(r[h].offsetWidth/2),o=n-s.round(r[h].offsetHeight/2),this.pages[a][i]={x:l,y:n,width:r[h].offsetWidth,height:r[h].offsetHeight,cx:e,cy:o},l>this.maxScrollX&&a++;this.goToPage(this.currentPage.pageX||0,this.currentPage.pageY||0,0),this.options.snapThreshold%1===0?(this.snapThresholdX=this.options.snapThreshold,this.snapThresholdY=this.options.snapThreshold):(this.snapThresholdX=s.round(this.pages[this.currentPage.pageX][this.currentPage.pageY].width*this.options.snapThreshold),this.snapThresholdY=s.round(this.pages[this.currentPage.pageX][this.currentPage.pageY].height*this.options.snapThreshold))}}),this.on("flick",function(){var t=this.options.snapSpeed||s.max(s.max(s.min(s.abs(this.x-this.startX),1e3),s.min(s.abs(this.y-this.startY),1e3)),300);this.goToPage(this.currentPage.pageX+this.directionX,this.currentPage.pageY+this.directionY,t)})},_nearestSnap:function(t,i){if(!this.pages.length)return{x:0,y:0,pageX:0,pageY:0};var e=0,o=this.pages.length,n=0;if(s.abs(t-this.absStartX)<this.snapThresholdX&&s.abs(i-this.absStartY)<this.snapThresholdY)return this.currentPage;for(t>0?t=0:t<this.maxScrollX&&(t=this.maxScrollX),i>0?i=0:i<this.maxScrollY&&(i=this.maxScrollY);o>e;e++)if(t>=this.pages[e][0].cx){t=this.pages[e][0].x;break}for(o=this.pages[e].length;o>n;n++)if(i>=this.pages[0][n].cy){i=this.pages[0][n].y;break}return e==this.currentPage.pageX&&(e+=this.directionX,0>e?e=0:e>=this.pages.length&&(e=this.pages.length-1),t=this.pages[e][0].x),n==this.currentPage.pageY&&(n+=this.directionY,0>n?n=0:n>=this.pages[0].length&&(n=this.pages[0].length-1),i=this.pages[0][n].y),{x:t,y:i,pageX:e,pageY:n}},goToPage:function(t,i,e,o){o=o||this.options.bounceEasing,t>=this.pages.length?t=this.pages.length-1:0>t&&(t=0),i>=this.pages[t].length?i=this.pages[t].length-1:0>i&&(i=0);var n=this.pages[t][i].x,r=this.pages[t][i].y;e=void 0===e?this.options.snapSpeed||s.max(s.max(s.min(s.abs(n-this.x),1e3),s.min(s.abs(r-this.y),1e3)),300):e,this.currentPage={x:n,y:r,pageX:t,pageY:i},this.scrollTo(n,r,e,o)},next:function(t,i){var s=this.currentPage.pageX,e=this.currentPage.pageY;s++,s>=this.pages.length&&this.hasVerticalScroll&&(s=0,e++),this.goToPage(s,e,t,i)},prev:function(t,i){var s=this.currentPage.pageX,e=this.currentPage.pageY;s--,0>s&&this.hasVerticalScroll&&(s=0,e--),this.goToPage(s,e,t,i)},_initKeys:function(){var i,s={pageUp:33,pageDown:34,end:35,home:36,left:37,up:38,right:39,down:40};if("object"==typeof this.options.keyBindings)for(i in this.options.keyBindings)"string"==typeof this.options.keyBindings[i]&&(this.options.keyBindings[i]=this.options.keyBindings[i].toUpperCase().charCodeAt(0));else this.options.keyBindings={};for(i in s)this.options.keyBindings[i]=this.options.keyBindings[i]||s[i];h.addEvent(t,"keydown",this),this.on("destroy",function(){h.removeEvent(t,"keydown",this)})},_key:function(t){if(this.enabled){var i,e=this.options.snap,o=e?this.currentPage.pageX:this.x,n=e?this.currentPage.pageY:this.y,r=h.getTime(),a=this.keyTime||0,l=.25;switch(this.options.useTransition&&this.isInTransition&&(i=this.getComputedPosition(),this._translate(s.round(i.x),s.round(i.y)),this.isInTransition=!1),this.keyAcceleration=200>r-a?s.min(this.keyAcceleration+l,50):0,t.keyCode){case this.options.keyBindings.pageUp:this.hasHorizontalScroll&&!this.hasVerticalScroll?o+=e?1:this.wrapperWidth:n+=e?1:this.wrapperHeight;break;case this.options.keyBindings.pageDown:this.hasHorizontalScroll&&!this.hasVerticalScroll?o-=e?1:this.wrapperWidth:n-=e?1:this.wrapperHeight;break;case this.options.keyBindings.end:o=e?this.pages.length-1:this.maxScrollX,n=e?this.pages[0].length-1:this.maxScrollY;break;case this.options.keyBindings.home:o=0,n=0;break;case this.options.keyBindings.left:o+=e?-1:5+this.keyAcceleration>>0;break;case this.options.keyBindings.up:n+=e?1:5+this.keyAcceleration>>0;break;case this.options.keyBindings.right:o-=e?-1:5+this.keyAcceleration>>0;break;case this.options.keyBindings.down:n-=e?1:5+this.keyAcceleration>>0;break;default:return}if(e)return void this.goToPage(o,n);o>0?(o=0,this.keyAcceleration=0):o<this.maxScrollX&&(o=this.maxScrollX,this.keyAcceleration=0),n>0?(n=0,this.keyAcceleration=0):n<this.maxScrollY&&(n=this.maxScrollY,this.keyAcceleration=0),this.scrollTo(o,n,0),this.keyTime=r}},_animate:function(t,i,s,e){function o(){var d,u,m,f=h.getTime();return f>=p?(n.isAnimating=!1,n._translate(t,i),void(n.resetPosition(n.options.bounceTime)||n._execEvent("scrollEnd"))):(f=(f-c)/s,m=e(f),d=(t-a)*m+a,u=(i-l)*m+l,n._translate(d,u),void(n.isAnimating&&r(o)))}var n=this,a=this.x,l=this.y,c=h.getTime(),p=c+s;this.isAnimating=!0,o()},handleEvent:function(t){switch(t.type){case"touchstart":case"pointerdown":case"MSPointerDown":case"mousedown":this._start(t);break;case"touchmove":case"pointermove":case"MSPointerMove":case"mousemove":this._move(t);break;case"touchend":case"pointerup":case"MSPointerUp":case"mouseup":case"touchcancel":case"pointercancel":case"MSPointerCancel":case"mousecancel":this._end(t);break;case"orientationchange":case"resize":this._resize();break;case"transitionend":case"webkitTransitionEnd":case"oTransitionEnd":case"MSTransitionEnd":this._transitionEnd(t);break;case"wheel":case"DOMMouseScroll":case"mousewheel":this._wheel(t);break;case"keydown":this._key(t);break;case"click":t._constructed||(t.preventDefault(),t.stopPropagation())}}},n.prototype={handleEvent:function(t){switch(t.type){case"touchstart":case"pointerdown":case"MSPointerDown":case"mousedown":this._start(t);break;case"touchmove":case"pointermove":case"MSPointerMove":case"mousemove":this._move(t);break;case"touchend":case"pointerup":case"MSPointerUp":case"mouseup":case"touchcancel":case"pointercancel":case"MSPointerCancel":case"mousecancel":this._end(t)}},destroy:function(){this.options.interactive&&(h.removeEvent(this.indicator,"touchstart",this),h.removeEvent(this.indicator,h.prefixPointerEvent("pointerdown"),this),h.removeEvent(this.indicator,"mousedown",this),h.removeEvent(t,"touchmove",this),h.removeEvent(t,h.prefixPointerEvent("pointermove"),this),h.removeEvent(t,"mousemove",this),h.removeEvent(t,"touchend",this),h.removeEvent(t,h.prefixPointerEvent("pointerup"),this),h.removeEvent(t,"mouseup",this)),this.options.defaultScrollbars&&this.wrapper.parentNode.removeChild(this.wrapper)},_start:function(i){var s=i.touches?i.touches[0]:i;i.preventDefault(),i.stopPropagation(),this.transitionTime(),this.initiated=!0,this.moved=!1,this.lastPointX=s.pageX,this.lastPointY=s.pageY,this.startTime=h.getTime(),this.options.disableTouch||h.addEvent(t,"touchmove",this),this.options.disablePointer||h.addEvent(t,h.prefixPointerEvent("pointermove"),this),this.options.disableMouse||h.addEvent(t,"mousemove",this),this.scroller._execEvent("beforeScrollStart")},_move:function(t){{var i,s,e,o,n=t.touches?t.touches[0]:t;h.getTime()}this.moved||this.scroller._execEvent("scrollStart"),this.moved=!0,i=n.pageX-this.lastPointX,this.lastPointX=n.pageX,s=n.pageY-this.lastPointY,this.lastPointY=n.pageY,e=this.x+i,o=this.y+s,this._pos(e,o),t.preventDefault(),t.stopPropagation()},_end:function(i){if(this.initiated){if(this.initiated=!1,i.preventDefault(),i.stopPropagation(),h.removeEvent(t,"touchmove",this),h.removeEvent(t,h.prefixPointerEvent("pointermove"),this),h.removeEvent(t,"mousemove",this),this.scroller.options.snap){var e=this.scroller._nearestSnap(this.scroller.x,this.scroller.y),o=this.options.snapSpeed||s.max(s.max(s.min(s.abs(this.scroller.x-e.x),1e3),s.min(s.abs(this.scroller.y-e.y),1e3)),300);(this.scroller.x!=e.x||this.scroller.y!=e.y)&&(this.scroller.directionX=0,this.scroller.directionY=0,this.scroller.currentPage=e,this.scroller.scrollTo(e.x,e.y,o,this.scroller.options.bounceEasing))}this.moved&&this.scroller._execEvent("scrollEnd")}},transitionTime:function(t){t=t||0;var i=h.style.transitionDuration;if(this.indicatorStyle[i]=t+"ms",!t&&h.isBadAndroid){this.indicatorStyle[i]="0.0001ms";var s=this;r(function(){"0.0001ms"===s.indicatorStyle[i]&&(s.indicatorStyle[i]="0s")})}},transitionTimingFunction:function(t){this.indicatorStyle[h.style.transitionTimingFunction]=t},refresh:function(){this.transitionTime(),this.indicatorStyle.display=this.options.listenX&&!this.options.listenY?this.scroller.hasHorizontalScroll?"block":"none":this.options.listenY&&!this.options.listenX?this.scroller.hasVerticalScroll?"block":"none":this.scroller.hasHorizontalScroll||this.scroller.hasVerticalScroll?"block":"none",this.scroller.hasHorizontalScroll&&this.scroller.hasVerticalScroll?(h.addClass(this.wrapper,"iScrollBothScrollbars"),h.removeClass(this.wrapper,"iScrollLoneScrollbar"),this.options.defaultScrollbars&&this.options.customStyle&&(this.options.listenX?this.wrapper.style.right="8px":this.wrapper.style.bottom="8px")):(h.removeClass(this.wrapper,"iScrollBothScrollbars"),h.addClass(this.wrapper,"iScrollLoneScrollbar"),this.options.defaultScrollbars&&this.options.customStyle&&(this.options.listenX?this.wrapper.style.right="2px":this.wrapper.style.bottom="2px"));this.wrapper.offsetHeight;this.options.listenX&&(this.wrapperWidth=this.wrapper.clientWidth,this.options.resize?(this.indicatorWidth=s.max(s.round(this.wrapperWidth*this.wrapperWidth/(this.scroller.scrollerWidth||this.wrapperWidth||1)),8),this.indicatorStyle.width=this.indicatorWidth+"px"):this.indicatorWidth=this.indicator.clientWidth,this.maxPosX=this.wrapperWidth-this.indicatorWidth,"clip"==this.options.shrink?(this.minBoundaryX=-this.indicatorWidth+8,this.maxBoundaryX=this.wrapperWidth-8):(this.minBoundaryX=0,this.maxBoundaryX=this.maxPosX),this.sizeRatioX=this.options.speedRatioX||this.scroller.maxScrollX&&this.maxPosX/this.scroller.maxScrollX),this.options.listenY&&(this.wrapperHeight=this.wrapper.clientHeight,this.options.resize?(this.indicatorHeight=s.max(s.round(this.wrapperHeight*this.wrapperHeight/(this.scroller.scrollerHeight||this.wrapperHeight||1)),8),this.indicatorStyle.height=this.indicatorHeight+"px"):this.indicatorHeight=this.indicator.clientHeight,this.maxPosY=this.wrapperHeight-this.indicatorHeight,"clip"==this.options.shrink?(this.minBoundaryY=-this.indicatorHeight+8,this.maxBoundaryY=this.wrapperHeight-8):(this.minBoundaryY=0,this.maxBoundaryY=this.maxPosY),this.maxPosY=this.wrapperHeight-this.indicatorHeight,this.sizeRatioY=this.options.speedRatioY||this.scroller.maxScrollY&&this.maxPosY/this.scroller.maxScrollY),this.updatePosition()},updatePosition:function(){var t=this.options.listenX&&s.round(this.sizeRatioX*this.scroller.x)||0,i=this.options.listenY&&s.round(this.sizeRatioY*this.scroller.y)||0;this.options.ignoreBoundaries||(t<this.minBoundaryX?("scale"==this.options.shrink&&(this.width=s.max(this.indicatorWidth+t,8),this.indicatorStyle.width=this.width+"px"),t=this.minBoundaryX):t>this.maxBoundaryX?"scale"==this.options.shrink?(this.width=s.max(this.indicatorWidth-(t-this.maxPosX),8),this.indicatorStyle.width=this.width+"px",t=this.maxPosX+this.indicatorWidth-this.width):t=this.maxBoundaryX:"scale"==this.options.shrink&&this.width!=this.indicatorWidth&&(this.width=this.indicatorWidth,this.indicatorStyle.width=this.width+"px"),i<this.minBoundaryY?("scale"==this.options.shrink&&(this.height=s.max(this.indicatorHeight+3*i,8),this.indicatorStyle.height=this.height+"px"),i=this.minBoundaryY):i>this.maxBoundaryY?"scale"==this.options.shrink?(this.height=s.max(this.indicatorHeight-3*(i-this.maxPosY),8),this.indicatorStyle.height=this.height+"px",i=this.maxPosY+this.indicatorHeight-this.height):i=this.maxBoundaryY:"scale"==this.options.shrink&&this.height!=this.indicatorHeight&&(this.height=this.indicatorHeight,this.indicatorStyle.height=this.height+"px")),this.x=t,this.y=i,this.scroller.options.useTransform?this.indicatorStyle[h.style.transform]="translate("+t+"px,"+i+"px)"+this.scroller.translateZ:(this.indicatorStyle.left=t+"px",this.indicatorStyle.top=i+"px")},_pos:function(t,i){0>t?t=0:t>this.maxPosX&&(t=this.maxPosX),0>i?i=0:i>this.maxPosY&&(i=this.maxPosY),t=this.options.listenX?s.round(t/this.sizeRatioX):this.scroller.x,i=this.options.listenY?s.round(i/this.sizeRatioY):this.scroller.y,this.scroller.scrollTo(t,i)},fade:function(t,i){if(!i||this.visible){clearTimeout(this.fadeTimeout),this.fadeTimeout=null;
var s=t?250:500,e=t?0:300;t=t?"1":"0",this.wrapperStyle[h.style.transitionDuration]=s+"ms",this.fadeTimeout=setTimeout(function(t){this.wrapperStyle.opacity=t,this.visible=+t}.bind(this,t),e)}}},e.utils=h,"undefined"!=typeof module&&module.exports?module.exports=e:t.IScroll=e}(window,document,Math);</script>
	<script type="text/javascript">!function(o){"use strict";function t(o){o.touches||(o.touches=o.originalEvent.touches)}function n(o,t){t._startY=o.touches[0].pageY,t.touchScrollTop=t.$scrollArea.scrollTop()}function s(t,n){n._curY=t.touches[0].pageY,n._moveY=n._curY-n._startY,n._moveY>0?n.direction="down":n._moveY<0&&(n.direction="up");var s=Math.abs(n._moveY);""!=n.opts.loadUpFn&&n.touchScrollTop<=0&&"down"==n.direction&&!n.isLockUp&&(t.preventDefault(),n.$domUp=o("."+n.opts.domUp.domClass),n.upInsertDOM||(n.$element.prepend('<div class="'+n.opts.domUp.domClass+'"></div>'),n.upInsertDOM=!0),a(n.$domUp,0),s<=n.opts.distance?(n._offsetY=s,n.$domUp.html(n.opts.domUp.domRefresh)):s>n.opts.distance&&s<=2*n.opts.distance?(n._offsetY=n.opts.distance+.5*(s-n.opts.distance),n.$domUp.html(n.opts.domUp.domUpdate)):n._offsetY=n.opts.distance+.5*n.opts.distance+.2*(s-2*n.opts.distance),n.$domUp.css({height:n._offsetY}))}function e(t){var n=Math.abs(t._moveY);""!=t.opts.loadUpFn&&t.touchScrollTop<=0&&"down"==t.direction&&!t.isLockUp&&(a(t.$domUp,300),n>t.opts.distance?(t.$domUp.css({height:t.$domUp.children().height()}),t.$domUp.html(t.opts.domUp.domLoad),t.loading=!0,t.opts.loadUpFn(t)):t.$domUp.css({height:"0"}).on("webkitTransitionEnd mozTransitionEnd transitionend",function(){t.upInsertDOM=!1,o(this).remove()}),t._moveY=0)}function i(o){o.opts.autoLoad&&o._scrollContentHeight-o._threshold<=o._scrollWindowHeight&&l(o)}function d(o){o._scrollContentHeight=o.opts.scrollArea==r?h.height():o.$element[0].scrollHeight}function l(o){o.direction="up",o.$domDown.html(o.opts.domDown.domLoad),o.loading=!0,o.opts.loadDownFn(o)}function a(o,t){o.css({"-webkit-transition":"all "+t+"ms",transition:"all "+t+"ms"})}var r=window,c=document,p=o(r),h=o(c);o.fn.dropload=function(o){return new m(this,o)};var m=function(o,t){var n=this;n.$element=o,n.upInsertDOM=!1,n.loading=!1,n.isLockUp=!1,n.isLockDown=!1,n.isData=!0,n._scrollTop=0,n._threshold=0,n.init(t)};m.prototype.init=function(d){var a=this;a.opts=o.extend(!0,{},{scrollArea:a.$element,domUp:{domClass:"dropload-up",domRefresh:'<div class="dropload-refresh">↓下拉刷新</div>',domUpdate:'<div class="dropload-update">↑释放更新</div>',domLoad:'<div class="dropload-load"><span class="loading"></span>加载中...</div>'},domDown:{domClass:"dropload-down",domRefresh:'<div class="dropload-refresh">↑上拉加载更多</div>',domLoad:'<div class="dropload-load"><span class="loading"></span>加载中...</div>'},autoLoad:!0,distance:50,threshold:"100",loadUpFn:"",loadDownFn:""},d),""!=a.opts.loadDownFn&&(a.$element.append('<div class="'+a.opts.domDown.domClass+'">'+a.opts.domDown.domRefresh+"</div>"),a.$domDown=o("."+a.opts.domDown.domClass)),a._threshold=a.$domDown&&""===a.opts.threshold?Math.floor(1*a.$domDown.height()/3):a.opts.threshold,a.opts.scrollArea==r?(a.$scrollArea=p,a._scrollContentHeight=h.height(),a._scrollWindowHeight=c.documentElement.clientHeight):(a.$scrollArea=a.opts.scrollArea,a._scrollContentHeight=a.$element[0].scrollHeight,a._scrollWindowHeight=a.$element.height()),i(a),a.$element.off(),p.on("resize",function(){a._scrollWindowHeight=a.opts.scrollArea==r?r.innerHeight:a.$element.height()}),a.$element.on("touchstart",function(o){a.loading||(t(o),n(o,a))}),a.$element.on("touchmove",function(o){a.loading||(t(o,a),s(o,a))}),a.$element.on("touchend",function(){a.loading||e(a)}),a.$scrollArea.on("scroll",function(){a._scrollTop=a.$scrollArea.scrollTop(),""!=a.opts.loadDownFn&&!a.loading&&!a.isLockDown&&a._scrollContentHeight-a._threshold<=a._scrollWindowHeight+a._scrollTop&&l(a)})},m.prototype.lock=function(o){var t=this;void 0===o?"up"==t.direction?t.isLockDown=!0:"down"==t.direction?t.isLockUp=!0:(t.isLockUp=!0,t.isLockDown=!0):"up"==o?t.isLockUp=!0:"down"==o&&(t.isLockDown=!0,t.direction="up")},m.prototype.unlock=function(){var o=this;o.isLockUp=!1,o.isLockDown=!1,o.direction="up"},m.prototype.noData=function(o){var t=this;void 0===o||1==o?t.isData=!1:0==o&&(t.isData=!0)},m.prototype.resetload=function(){var t=this;"down"==t.direction&&t.upInsertDOM?t.$domUp.css({height:"0"}).on("webkitTransitionEnd mozTransitionEnd transitionend",function(){t.loading=!1,t.upInsertDOM=!1,o(this).remove(),d(t)}):"up"==t.direction&&(t.loading=!1,t.isData?(t.$domDown.html(t.opts.domDown.domRefresh),d(t),i(t)):t.$domDown.html(t.opts.domDown.domNoData))}}(window.Zepto||window.jQuery);</script>
	<script type="text/javascript">var commom={detectBrowser:function(){var e=navigator.userAgent.toLocaleLowerCase();return{ios:!!e.match(/\(i[^;]+;( u;)? cpu.+mac os x/),android:e.indexOf("android")>-1||e.indexOf("Linux")>-1,iPhone:e.indexOf("iphone")>-1,iPad:e.indexOf("ipad")>-1}},getQueryString:function(e){var n;n=window.location.href.indexOf("?")!=window.location.href.lastIndexOf("?")?window.location.href.replace(/\?/g,"&").replace(/^.*?&/,""):window.location.href.replace(/^.*\?/,"");var t=new RegExp("(^|&)"+e+"=([^&]*)(&|$)","i"),o=("?"+n).substr(1).match(t);return null!=o?decodeURI(o[2]):null},setCookie:function(e,n,t){if(arguments.length>1&&null!=e){if(null==t&&(t={}),null==n&&(t.expires=-1),"number"==typeof t.expires){var o=t.expires,i=t.expires=new Date;i.setTime(i.getTime()+1e3*o)}null==t.path&&(t.path="/"),null==t.domain&&(t.domain=""),document.cookie=encodeURIComponent(String(e))+"="+encodeURIComponent(String(n))+(null!=t.expires?"; expires="+t.expires.toUTCString():"")+"; path=/; domain="+t.domain+(null!=t.secure?"; secure":"")}},getCookie:function(e){if(null!=e){var n=new RegExp("(?:^|; )"+encodeURIComponent(String(e))+"=([^;]*)").exec(document.cookie);return n?decodeURIComponent(n[1]):null}},delCookie:function(e,n){util.setCookie(e,null,n)},replaceDate:function(e){e=e.replace(/-/g,"/");var n,t,o,i,r,a=new Date(e),l=new Date;return a.getTime()<l.getTime()?a.getDate()==l.getDate()?(i=a.getHours(),9>i&&(i="0"+i),r=a.getMinutes(),9>r&&(r="0"+r),"今天 "+i+":"+r):l.getDate()==parseInt(a.getDate()+1)?(i=a.getHours(),9>i&&(i="0"+i),r=a.getMinutes(),9>r&&(r="0"+r),"昨天 "+i+":"+r):(n=a.getFullYear(),t=a.getMonth()+1,o=a.getDate(),n+"-"+t+"-"+o):""},HTMLDecode:function(e){var n=document.createElement("div");n.innerHTML=e;var t=n.innerText||n.textContent;return n=null,t},storage:function(e,n){if(!n){var t=localStorage.getItem(e);return JSON.parse(t)}"object"==typeof n&&(n=JSON.stringify(n),localStorage.setItem(e,n))}};</script>
	<script type="text/javascript">var pageOnload={init:function(){this.render()},render:function(){var i="";i+='<div class= "reflesh">',i+='	<div class="top" ><a href="fwbz.shtml"></a>',i+="	</div>",i+='	<div class="banner">',i+="	</div>",i+='	<div class="g-qianggou">',i+='		<div class="qg-top">',i+="		</div>",i+='		<div class="qg-bottom">',i+="		</div>",i+="	</div>",i+='    <div class="waterfall">',i+='	<div class="nav">',i+='		<div class="navcontent">',i+='			<div class="slidewrap" id="nav">',i+='				<div id="scroller">',i+="				</div>",i+="			</div>",i+='			<div class="dropdown">',i+='				<div class="iconfont icon-unfold">',i+="				</div>",i+="			</div>",i+='			<div class="downlist" style="display:none">',i+="			</div>",i+="		</div>",i+="	</div>",i+='	<div class="fixtwo" style=" transform: translateZ(0px);">',i+='		<div class="snav">',i+='			<div  ="snavwrap">',i+="			</div>",i+="		</div>",i+='		<div class="g-car">',i+="		</div>",i+="	</div>",i+='	<div class="blockdiv" style="display:none">',i+="	</div>",i+='	<div class="pipei">',i+="	</div>",i+='	<div class="g-main">',i+="	</div>",i+="	</div>",i+="</div>",i+='<div class="tips">',i+="</div>",$(".load").before(i)}};</script>
	<script type="text/javascript">var header={data:{},state:1,init:function(){header.render()},render:function(){$(".top a").html(header.create())},create:function(){var e="";return e+="		<ul>",e+='			<li class="hanghuo">正品保障</li>',e+='			<li class="baoyou">全国包邮</li>',e+='			<li class="invoice">无忧售后</li>',e+="		</ul>"}};</script>
	<script type="text/javascript">var banner={config:{state:1},data:"",init:function(){$.ajax({type:"get",dataType:"jsonp",url:dataurl+"/mallProd/queryCategoryAndActivity",success:function(a){if(0==a.code&&a.info.adPositionROList&&a.info.adPositionROList[0].adList){$("section").remove(),banner.data=a.info.adPositionROList[0].adList;var n=banner.render();$(".banner").html(n);if(1==banner.data.length){new Swiper(".swiper-container1",{loop:!1,autoplay:0,autoplayDisableOnInteraction:!1,pagination:null,paginationClickable:!1})}else{new Swiper(".swiper-container1",{loop:!0,autoplay:5e3,autoplayDisableOnInteraction:!1,pagination:".swiper-pagination",paginationClickable:!1})}}else $(".banner").remove()},data:{adPositionId:113},error:function(){$("body").after('<section onclick="reload()"><p>亲,网络开小差了<br><span>重新加载</span></p></section>')}})},render:function(){var a="";a+='<div class="swiper-container swiper-container1">',a+='<div class="swiper-wrapper">';for(var n=0;n<banner.data.length;n++)a+='<div class="swiper-slide"><a href="'+banner.data[n].url+'"><img src="'+banner.data[n].path+'" data-original="'+banner.data[n].path+'"/></a></div>';return a+="</div>",a+='<div class="swiper-pagination"></div>',a+="</div>",a+="</div>"},fixd:function(a){for(var n=new Array,i=0;i<a.length;i++)n[i]={},n[i].src=a[i].img;return n}};</script>
	<script type="text/javascript">var picload={init:function(){$("img.lazy").lazyload({effect:"fadeIn"})}},saletitle={element:{picload:picload},data:"",datatime:"",state:1,init:function(){$.ajax({type:"get",dataType:"jsonp",url:dataurl+"/mallProd/queryCategoryAndActivity",success:function(t){saletitle.getservertime(function(){0==t.code&&t.info.rsp?(saletitle.data=t.info.rsp,saletitle.render(),saletitle.element.picload.init(),saletitle.bindevent(),saletitle.statuschange(),saletitle.back()):($(".g-qianggou").remove(),$("section").remove())})},data:{adPositionId:113,promotionId:commom.getQueryString("promotionId")},error:function(){$("body").after('<section onclick="reload()"><p>亲,网络开小差了<br><span>重新加载</span></p></section>')}})},create:function(){var t=(Date.parse(new Date),"");t=saletitle.datevalue(saletitle.userTime(saletitle.data.startTime));var e="";return e+='<div class="qg-tt">天天抢购</div>',e+='<div class="qg-tip">每天<span class="red">'+t[0]+"点</span>开抢</div>",e+='<div id="fnTimeCountDown" >',e+='	<p class="status">'+(saletitle.datatime<saletitle.data.startTime?"距开始仅剩":"距结束仅剩")+"</p>",e+='	<span class="hour"></span> :',e+='	<span class="mini"></span> :',e+='	<span class="sec"></span>',e+="</div>",e+='<div class="sold" style="display: none;">',e+='	<p class="status"><span class="red">今日抢光 明日请早</span></p>',e+="</div>"},singles:function(t){var e=t.soldNum/t.totalNum*100;e=saletitle.nub(e);var a="";return a+='			<div class="onegoods">',a+='				<div class="gooslist" id="'+t.goodsId+'">',a+='					<div class="fr">',a+='						<div class="m-intro">',a+='							<p class="u-intrott">',a+=t.name,a+="							</p>",a+='							<p class="u-introprice">',a+="								￥<em>"+t.actPrice+"</em>",a+="							</p>",a+="						</div>",a+='					<div class="greentips">',a+="					</div>",a+='						<div class="m-process clear">',a+='							<div id="progressbar" class="ui-progressbar" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="'+e+'">',a+='								<div class="ui-progressbar-value" style="width:'+e+'%">',a+="								</div>",a+="							</div>",a+='							<span class="u-sale">已抢'+e+'% </span><span class="totle">抢光了</span>',a+="						</div>",a+="					</div>",a+='					<div class="m-goopic">',a+="<span>",a+='						<img class="u-goopic lazy" src="images/blank.gif" data-original="'+t.image+'" id="'+t.goodsId+'">',a+="</span>",a+="					</div>",a+="				</div>",a+="			</div>"},doublle:function(t){var e="";e+='			<div class="twogoods">';for(var a=0;2>a;a++){var i=t[a].soldNum/t[a].totalNum*100;i=saletitle.nub(i),e+='				<div class="gooslist" id="'+t[a].goodsId+'">',e+='					<div class="m-goopic">',e+='						<img class="u-goopic lazy" src="images/blank.gif" data-original="'+t[a].image+'" id="'+t[a].goodsId+'">',e+="					</div>",e+='					<div class="m-intro">',e+='						<p class="u-intrott">',e+=t[a].name,e+="						</p>",e+='						<p class="u-introprice">',e+="							￥<em>"+t[a].actPrice+"</em>",e+="						</p>",e+="					</div>",e+='					<div class="greentips">',e+="					</div>",e+='					<div class="m-process clear">',e+='						<div id="progressbar" class="ui-progressbar" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="'+i+'">',e+='							<div class="ui-progressbar-value" style="width:'+i+'%">',e+="							</div>",e+="						</div>",e+='						<span class="u-sale">已抢'+i+'%</span><span class="totle">抢光了</span>',e+="					</div>",e+="				</div>"}return e+="			</div>"},defult:function(t){var e="";e+='			<div class="threegoods">';for(var a=0;3>a;a++){var i=t[a].soldNum/t[a].totalNum*100;i=saletitle.nub(i),e+='				<div class="gooslist" id="'+t[a].goodsId+'">',e+='					<div class="m-goopic">',e+="<span>",e+='						<img class="u-goopic lazy" src="images/blank.gif" data-original="'+t[a].image+'" id="'+t[a].goodsId+'">',e+="</span>",e+="					</div>",e+='					<div class="m-intro">',e+='						<p class="u-intrott">',e+=t[a].name,e+="						</p>",e+='						<p class="u-introprice">',e+="							￥<em>"+t[a].actPrice+"</em>",e+="						</p>",e+="					</div>",e+='					<div class="greentips">',e+="					</div>",e+='					<div class="m-process clear">',e+='						<div id="progressbar" class="ui-progressbar" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="'+i+'">',e+='							<div class="ui-progressbar-value" style="width:'+i+'%">',e+="							</div>",e+="						</div>",e+='						<span class="u-sale">已抢'+i+'%</span><span class="totle">抢光了</span>',e+="					</div>",e+="				</div>"}return e+="			</div>"},userTime:function(t){var e=new Date(t),a=e.getHours(),i=e.getMinutes(),s=e.getSeconds();return a+":"+i+":"+s},getservertime:function(t){$.ajax({type:"get",dataType:"jsonp",url:"http://market.qccr.com/seckill/serverInfo.jthml?format=json",dataType:"jsonp",success:function(e){saletitle.datatime=e.info.now,t()}})},ShowCountDown:function(t,e,a,i,s,n,l,o,r){var d=saletitle.datatime,c=new Date(t,e-1,a,i,s,n),u=c.getTime()-d,v=parseInt(u/1e3),p=Math.floor(v/86400),i=Math.floor((v-24*p*60*60)/3600),s=Math.floor((v-24*p*60*60-3600*i)/60),n=Math.floor(v-24*p*60*60-3600*i-60*s);i.toString().length<2&&(i="0"+i.toString()),s.toString().length<2&&(s="0"+s.toString()),n.toString().length<2&&(n="0"+n.toString()),$(l).html(i),$(o).html(s),$(r).html(n)},render:function(){new Date;$(".qg-top").html(saletitle.create()),setInterval(function(){saletitle.datatime+=1e3,$("#fnTimeCountDown .status").html(saletitle.datatime<saletitle.data.startTime?"距开始仅剩":"距结束仅剩"),saletitle.statuschange()},1e3);var t="";t=1==saletitle.data.goodsList.length?saletitle.singles(saletitle.data.goodsList[0]):2==saletitle.data.goodsList.length?saletitle.doublle(saletitle.data.goodsList):saletitle.defult(saletitle.data.goodsList),$(".qg-bottom").html(t)},bindevent:function(){$(".ui-progressbar").each(function(){"100"==$(this).attr("aria-valuenow")?$(this).next(".u-sale").hide():$(this).siblings(".totle").hide()}),$(".qg-bottom").on("tap",".gooslist",function(){var t=$(this).attr("id");toApp.previewGoodDetail(t,1)});var t="";$(".ui-progressbar").each(function(){t+=parseInt($(this).attr("aria-valuenow"))}),("100100100"==t||"100"==t||"100100"==t)&&($("#fnTimeCountDown").hide(),$(".sold").show())},statuschange:function(){var t=new Date;if(saletitle.datatime<saletitle.data.startTime){$(".m-process").hide(),$(".greentips").html("即将开抢  敬请期待");var e="";e=saletitle.datevalue(saletitle.userTime(saletitle.data.startTime)),saletitle.ShowCountDown(t.getFullYear(),t.getMonth()+1,t.getDate(),e[0],e[1],e[2],"#fnTimeCountDown .hour","#fnTimeCountDown .mini","#fnTimeCountDown .sec")}else{var e="";e=saletitle.datevalue(saletitle.userTime(saletitle.data.endTime)),saletitle.ShowCountDown(t.getFullYear(),t.getMonth()+1,t.getDate(),e[0],e[1],e[2],"#fnTimeCountDown .hour","#fnTimeCountDown .mini","#fnTimeCountDown .sec"),$(".m-process").show(),$(".greentips").remove()}},datevalue:function(t){var e="";return e=t,e=e.split(":")},nub:function(t){return t=t>=0&&1>t?1:t>99&&100>t?99:Math.round(t)},fixd:function(t){for(var e=new Array,a=0;a<t.length;a++)e[a]={},e[a].src=t[a].img;return e},back:function(){var t;"undefined"!=typeof document.hidden?t="visibilitychange":"undefined"!=typeof document.mozHidden?t="mozvisibilitychange":"undefined"!=typeof document.msHidden?t="msvisibilitychange":"undefined"!=typeof document.webkitHidden&&(t="webkitvisibilitychange");try{document.addEventListener(t,function(){document.hidden||saletitle.getservertime(function(){})},!1)}catch(e){}}};</script>
	<script type="text/javascript">function addCarEnd(a){a=JSON.parse(a),goodsList.level5(a.id,a.categoryId,a.icon,a.name),goodsList.dataThree.fiveLevelCarId=a.categoryId,goodsList.pipei(classify),$(".pipei").show(),firstCarId=a.id,$(".nav a").each(function(){$(this).hasClass("current")&&(oldnavId=$(this).attr("data-catid"),oldnavalue=$(this).html())}),$(".snav li").each(function(){$(this).hasClass("current")&&(oldsnavId=$(this).attr("data-catid"))}),$(".snav li").hasClass("current")?goodsList.element.snav.init(oldnavId,goodsList.dataThree.fiveLevelCarId,1):goodsList.element.snav.init(oldnavId,goodsList.dataThree.fiveLevelCarId,0),goodsList.dataThree.pageNum=1}function selectCarEnd(a){a=JSON.parse(a),goodsList.level5(a.id,a.categoryId,a.icon,a.name),goodsList.dataThree.fiveLevelCarId=a.categoryId,goodsList.pipei(classify),$(".pipei").show(),firstCarId=a.id,$(".nav a").each(function(){$(this).hasClass("current")&&(oldnavId=$(this).attr("data-catid"),oldnavalue=$(this).html())}),$(".snav li").each(function(){$(this).hasClass("current")&&(oldsnavId=$(this).attr("data-catid"))}),$(".snav li").hasClass("current")?goodsList.element.snav.init(oldnavId,goodsList.dataThree.fiveLevelCarId,1):goodsList.element.snav.init(oldnavId,goodsList.dataThree.fiveLevelCarId,0),goodsList.dataThree.pageNum=1}function editCarEnd(a){a=JSON.parse(a),goodsList.level5(a.id,a.categoryId,a.icon,a.name),goodsList.dataThree.fiveLevelCarId=a.categoryId,goodsList.pipei(classify),$(".pipei").show(),firstCarId=a.id,$(".nav a").each(function(){$(this).hasClass("current")&&(oldnavId=$(this).attr("data-catid"),oldnavalue=$(this).html())}),$(".snav li").each(function(){$(this).hasClass("current")&&(oldsnavId=$(this).attr("data-catid"))}),$(".snav li").hasClass("current")?goodsList.element.snav.init(oldnavId,goodsList.dataThree.fiveLevelCarId,1):goodsList.element.snav.init(oldnavId,goodsList.dataThree.fiveLevelCarId,0),goodsList.dataThree.pageNum=1}function reload(){window.location.reload()}var counter=0,addcar={init:function(){$(".g-car").append(addcar.create())},create:function(){var a="";return a+='		<div class="m-cartt">',a+="		</div>",a+='		<div class="m-handle">',a+="		</div>"}},tabLoadEnd=!1,isActive=!0,snav={config:{state:1},data:"",init:function(a,e,t){$.ajax({type:"get",url:dataurl+"/mallProd/queryCategoryList",dataType:"jsonp",success:function(a){0==a.code&&a.info?(snav.data=a.info,snav.render(),1==t&&snav.carmatch()):$(".snav").hide()},data:{parentId:a,fiveLevelCarId:e}})},render:function(){if(void 0==snav.data.length&&$(".snavwrap").css("display","none"),0==snav.data.length||1==snav.data.length)$(".snavwrap").css("display","none");else if(snav.data.length<=8){$(".snav").css("display","block"),$(".snavwrap").css("display","block");var a="";a+="<ul>";for(var e=0;e<snav.data.length;e++)a+='<li data-index="1" data-catid="'+snav.data[e].categoryId+'" isNeedsCar="'+snav.data[e].isNeedsCar+'">'+snav.data[e].categoryName+"</li>";a+="</ul>"}else{$(".snavwrap").css("display","block"),a="",a+="<ul>";for(var e=0;8>e;e++)a+='<li data-index="1" data-catid="'+snav.data[e].categoryId+'" isNeedsCar="'+snav.data[e].isNeedsCar+'">'+snav.data[e].categoryName+"</li>";a+="</ul>"}$(".snavwrap").html(""),$(".snavwrap").append(a);var t=goodsList.olddorpload?goodsList.olddorpload:goodsList.dropload(),o=!1;$(".g-main").html(""),counter=0,o?(t.lock("down"),t.noData()):(t.unlock(),t.noData(!1)),t.resetload()},carmatch:function(){$(".snav li").each(function(){return $(this).attr("data-catid")==oldsnavId?($(this).addClass("current"),goodsList.pipei($(this).html()),goodsList.dataThree.categoryId=$(this).attr("data-catid"),!1):(goodsList.pipei(oldnavalue),void(goodsList.dataThree.categoryId=oldnavId))});var a=goodsList.olddorpload?goodsList.olddorpload:goodsList.dropload(),e=!1;$(".g-main").html(""),counter=0,e?(a.lock("down"),a.noData()):(a.unlock(),a.noData(!1)),a.resetload()}},myScroll,classify="",temp="",tempcategoryId="",goodsList={config:{state:1},element:{addcar:addcar,snav:snav,userId:function(){try{return toApp.getUserId()}catch(a){return 111}}(),Id:commom.getQueryString("Id")},dataThree:{categoryId:"",fiveLevelCarId:0,parentId:"",pageNum:1},data:"",init:function(){$.ajax({type:"get",url:dataurl+"/mallProd/queryCategoryList",dataType:"jsonp",success:function(a){goodsList.data=a.info,goodsList.element.addcar.init(),-999==a.code?$("body").after('<section onclick="reload()"><p>亲,网络开小差了<br><span>重新加载</span></p></section>'):($("section").remove(),goodsList.render())},data:{parentId:0}})},pipei:function(a){$(".pipei").empty(),$(".pipei").append('<hr><span class="pipei-text"></span><hr>'),$(".pipei .pipei-text").html("您的爱车匹配如下"+a)},create:function(){var a="";a="<ul>";for(var e=0;e<goodsList.data.length;e++)a+='<li><a href="javascript:;" data-index="'+e+'" data-catid="'+goodsList.data[e].categoryId+'" isNeedsCar="'+goodsList.data[e].isNeedsCar+'">'+goodsList.data[e].categoryName+"</a></li>";return a+=" </ul>"},renderList:function(){var a=function(){try{return toApp.getUserId()}catch(a){return 111}}(),e=goodsList.element.Id;if(a)if(0!=e&&null!=e)$("#scroller a").each(function(){if($(this).attr("data-catid")==e){$(this).addClass("current"),jumpnavindex=$(this).attr("data-index"),classify=$(".nav a").eq(jumpnavindex).html();var a=$(".nav a").eq(jumpnavindex).width(),t=0;if(3>jumpnavindex?t=0:jumpnavindex<$("#scroller li").length-2?t=(jumpnavindex-1)*a:jumpnavindex==$("#scroller li").length-2?t=(jumpnavindex-2)*a:jumpnavindex==$("#scroller li").length-1&&(t=(jumpnavindex-3)*a),myScroll.scrollTo(-t,0,500),$(".downlist a").eq(jumpnavindex).addClass("current"),goodsList.dataThree.categoryId=$(this).attr("data-catid"),goodsList.dataThree.parentId=0,"false"==$(this).attr("isneedscar"))tempcategoryId=goodsList.dataThree.fiveLevelCarId,goodsList.dataThree.fiveLevelCarId=0,$(".g-car").hide(),$(".pipei").hide();else{try{var o=JSON.parse(toApp.getUserCar())}catch(s){var o=void 0}goodsList.getcarCallback(o,classify),$(".g-car").show()}goodsList.element.snav.init($(this).attr("data-catid"),goodsList.dataThree.fiveLevelCarId);var d=goodsList.olddorpload?goodsList.olddorpload:goodsList.dropload(),i=!1;$(".g-main").html(""),counter=0,i?(d.lock("down"),d.noData()):(d.unlock(),d.noData(!1)),d.resetload();var r=$(".nav").offset().top;setTimeout(function(){$("body,html").scrollTop(r)},100);var n=page.heivalue+"px";$(".waterfall").css("min-height",n)}});else if(0==e){if($("#scroller a").eq(0).addClass("current"),$(".downlist a").eq(0).addClass("current"),goodsList.dataThree.categoryId=$("#scroller a").eq(0).attr("data-catid"),temp=goodsList.dataThree.categoryId,classify=$("#scroller a").eq(0).html(),goodsList.dataThree.parentId=0,"false"==$("#scroller a").eq(0).attr("isneedscar"))tempcategoryId=goodsList.dataThree.fiveLevelCarId,goodsList.dataThree.fiveLevelCarId=0,$(".g-car").hide(),$(".pipei").hide();else{try{var t=JSON.parse(toApp.getUserCar())}catch(o){var t=void 0}goodsList.getcarCallback(t,$("#scroller a").eq(0).html()),$(".g-car").show(),goodsList.pipei(classify)}$(".icon-unfold").removeClass("jiantou2").addClass("jiantou"),$(".downlist").css("display",""),setTimeout(function(){$(".downlist").addClass("fromtop")},100),goodsList.element.snav.init($("#scroller a").eq(0).attr("data-catid"),goodsList.dataThree.fiveLevelCarId);var s=goodsList.olddorpload?goodsList.olddorpload:goodsList.dropload(),d=!1;$(".g-main").html(""),d?(s.lock("down"),s.noData()):(s.unlock(),s.noData(!1)),s.resetload();var i=$(".nav").offset().top;setTimeout(function(){$("body,html").scrollTop(i)},100);var r=page.heivalue+"px";$(".waterfall").css("min-height",r)}else{if($("#scroller a").eq(0).addClass("current"),$(".downlist a").eq(0).addClass("current"),goodsList.dataThree.categoryId=$("#scroller a").eq(0).attr("data-catid"),temp=goodsList.dataThree.categoryId,classify=$("#scroller a").eq(0).html(),goodsList.dataThree.parentId=0,"false"==$("#scroller a").eq(0).attr("isneedscar"))tempcategoryId=goodsList.dataThree.fiveLevelCarId,goodsList.dataThree.fiveLevelCarId=0,$(".g-car").hide(),$(".pipei").hide();else{try{var t=JSON.parse(toApp.getUserCar())}catch(o){var t=void 0}goodsList.getcarCallback(t,$("#scroller a").eq(0).html()),$(".g-car").show(),goodsList.pipei(classify)}goodsList.element.snav.init($("#scroller a").eq(0).attr("data-catid"),goodsList.dataThree.fiveLevelCarId);var s=goodsList.olddorpload?goodsList.olddorpload:goodsList.dropload(),d=!1;$(".g-main").html(""),d?(s.lock("down"),s.noData()):(s.unlock(),s.noData(!1)),s.resetload()}else{$(".pipei").hide(),goodsList.nocar(classify);var e=goodsList.element.Id;if(0!=e&&null!=e)$("#scroller a").each(function(){if($(this).attr("data-catid")==e){$(this).addClass("current"),jumpnavindex=$(this).attr("data-index"),classify=$(".nav a").eq(jumpnavindex).html();var a=$(".nav a").eq(jumpnavindex).width(),t=0;3>jumpnavindex?t=0:jumpnavindex<$("#scroller li").length-2?t=(jumpnavindex-1)*a:jumpnavindex==$("#scroller li").length-2?t=(jumpnavindex-2)*a:jumpnavindex==$("#scroller li").length-1&&(t=(jumpnavindex-3)*a),myScroll.scrollTo(-t,0,500),goodsList.element.snav.init($(this).attr("data-catid"),goodsList.dataThree.fiveLevelCarId),$(".downlist a").eq(jumpnavindex).addClass("current"),goodsList.dataThree.categoryId=$(this).attr("data-catid"),goodsList.dataThree.parentId=0,"false"==$(this).attr("isneedscar")?(tempcategoryId=goodsList.dataThree.fiveLevelCarId,goodsList.dataThree.fiveLevelCarId=0,$(".g-car").hide(),$(".pipei").hide()):(goodsList.dataThree.fiveLevelCarId=0,$(".g-car").show());var o=goodsList.olddorpload?goodsList.olddorpload:goodsList.dropload(),s=!1;$(".g-main").html(""),s?(o.lock("down"),o.noData()):(o.unlock(),o.noData(!1)),o.resetload();var d=$(".nav").offset().top;setTimeout(function(){$("body,html").scrollTop(d)},100);var i=page.heivalue+"px";$(".waterfall").css("min-height",i)}}),temp=goodsList.dataThree.categoryId;else if(0==e){$("#scroller a").eq(0).addClass("current"),$(".downlist a").eq(0).addClass("current"),goodsList.nocar($("#scroller a").eq(0).html()),goodsList.dataThree.categoryId=$("#scroller a").eq(0).attr("data-catid"),goodsList.dataThree.parentId=0,goodsList.element.snav.init($("#scroller a").eq(0).attr("data-catid"),goodsList.dataThree.fiveLevelCarId),"false"==$("#scroller a").eq(0).attr("isneedscar")?(tempcategoryId=goodsList.dataThree.fiveLevelCarId,goodsList.dataThree.fiveLevelCarId=0,$(".g-car").hide(),$(".pipei").hide()):(goodsList.dataThree.fiveLevelCarId=0,$(".g-car").show()),temp=goodsList.dataThree.categoryId,$(".g-main").html(""),$(".icon-unfold").removeClass("jiantou2").addClass("jiantou"),$(".downlist").css("display",""),setTimeout(function(){$(".downlist").addClass("fromtop")},100);var s=goodsList.olddorpload?goodsList.olddorpload:goodsList.dropload(),d=!1;$(".g-main").html(""),d?(s.lock("down"),s.noData()):(s.unlock(),s.noData(!1)),s.resetload();var i=$(".nav").offset().top;setTimeout(function(){$("body,html").scrollTop(i)},100);var r=page.heivalue+"px";$(".waterfall").css("min-height",r)}else{$("#scroller a").eq(0).addClass("current"),$(".downlist a").eq(0).addClass("current"),goodsList.nocar($("#scroller a").eq(0).html()),goodsList.dataThree.categoryId=$("#scroller a").eq(0).attr("data-catid"),goodsList.dataThree.parentId=0,goodsList.element.snav.init($("#scroller a").eq(0).attr("data-catid"),goodsList.dataThree.fiveLevelCarId),"false"==$("#scroller a").eq(0).attr("isneedscar")?(tempcategoryId=goodsList.dataThree.fiveLevelCarId,goodsList.dataThree.fiveLevelCarId=0,$(".g-car").hide(),$(".pipei").hide()):(goodsList.dataThree.fiveLevelCarId=0,$(".g-car").show()),temp=goodsList.dataThree.categoryId,$(".g-main").html("");var s=goodsList.olddorpload?goodsList.olddorpload:goodsList.dropload(),d=!1;$(".g-main").html(""),d?(s.lock("down"),s.noData()):(s.unlock(),s.noData(!1)),s.resetload()}}},getnavtext:function(){var a=!1,e=(goodsList.element.userId,goodsList.element.Id,goodsList.olddorpload?goodsList.olddorpload:goodsList.dropload());$(".slidewrap,.downlist").on("tap","li a",function(){goodsList.dataThree.pageNum=1,$(".blockdiv").css("height","0px").hide(),$(".nav .navcontent").css({position:"relative",top:"0px"}),$(".fixtwo").removeClass("snavfix");var t=$(this).parent("li").index(),o=$(".slidewrap li a").width(),s=0;3>t?s=0:t<$("#scroller li").length-2?s=(t-1)*o:t==$("#scroller li").length-2?s=(t-2)*o:t==$("#scroller li").length-1&&(s=(t-3)*o),myScroll.scrollTo(-s,0,500),classify=$(this).html();var d=$(this).attr("data-catid");if(goodsList.relevanceId(t),$(this).children("a").addClass("current").closest("li").siblings().children("a").removeClass("current"),$(".icon-unfold").removeClass("jiantou"),$(".downlist").removeClass("fromtop"),setTimeout(function(){$(".downlist").hide()},100),goodsList.pipei(classify),$(".pipei").show(),$(".snavwrap").html(""),goodsList.dataThree.categoryId=d,temp=d,goodsList.dataThree.parentId=0,"false"==$(this).attr("isneedscar"))goodsList.dataThree.fiveLevelCarId=0,$(".g-car").hide(),$(".pipei").hide();else if($(".g-car").show(),$(".g-car p").attr("categoryId"))goodsList.dataThree.fiveLevelCarId=$(".g-car p").attr("categoryId"),$(".g-car").show(),$(".pipei").show();else if($(".pipei").hide(),goodsList.element.userId){try{var i=JSON.parse(toApp.getUserCar())}catch(r){var i=void 0}goodsList.getcarCallback(i,classify),$(".g-car").show()}else $(".g-car p").attr("categoryId")||(goodsList.nocar(classify),$(".g-car").show());goodsList.element.snav.init(d,goodsList.dataThree.fiveLevelCarId,0),$(".dropload-noData1").remove(),$(".g-main").html(""),counter=0,a?(e.lock("down"),e.noData()):(e.unlock(),e.noData(!1)),e.resetload(),goodsList.dataThree.parentId=goodsList.dataThree.categoryId}),myScroll.refresh(),$(".snavwrap").on("tap","li",function(){$(".nav .navcontent").css({position:"relative",top:"0px"}),goodsList.dataThree.pageNum=1,$(".blockdiv").css("height","0px").hide(),$(".fixtwo").removeClass("snavfix");var t=($(this).index(),$(this).attr("data-catid")),o="",s=goodsList.dataThree.fiveLevelCarId;if(classify=$(this).html(),goodsList.dataThree.categoryId=t,o=t,$(this).toggleClass("current").siblings("li").removeClass("current"),"false"==$(this).attr("isneedscar"))goodsList.dataThree.fiveLevelCarId=0,$(".g-car").hide(),$(".pipei").hide();else if($(".g-car p").attr("categoryId"))goodsList.dataThree.fiveLevelCarId=$(".g-car p").attr("categoryId"),$(".g-car").show(),goodsList.pipei(classify),$(".pipei").show();else if(goodsList.element.userId){try{var d=JSON.parse(toApp.getUserCar())}catch(i){var d=void 0}goodsList.getcarCallback(d,classify),$(".g-car").show()}else $(".g-car").show(),$(".g-car p").attr("categoryId")||goodsList.nocar(classify);if($(".snavwrap li").hasClass("current"))goodsList.dataThree.categoryId=o,goodsList.dataThree.parentId=temp;else{goodsList.dataThree.categoryId=goodsList.dataThree.parentId,goodsList.dataThree.parentId=0,goodsList.dataThree.fiveLevelCarId=s;var r="";if($(".nav a").each(function(){$(this).attr("data-catid")==goodsList.dataThree.categoryId&&(r=$(this).attr("isneedscar"))}),console.log(r),"false"==r)goodsList.dataThree.fiveLevelCarId=0,$(".g-car").hide(),$(".pipei").hide();else if($(".g-car").show(),$(".g-car p").attr("categoryId"))goodsList.dataThree.fiveLevelCarId=$(".g-car p").attr("categoryId"),$(".g-car").show(),$(".pipei").show();else if($(".pipei").hide(),goodsList.element.userId){try{var d=JSON.parse(toApp.getUserCar())}catch(i){var d=void 0}goodsList.getcarCallback(d,classify),$(".g-car").show()}else $(".g-car p").attr("categoryId")||(goodsList.nocar(classify),$(".g-car").show())}$(".g-main").html(""),counter=0,a?(e.lock("down"),e.noData()):(e.unlock(),e.noData(!1)),e.resetload(),$(".dropload-noData1").remove()}),$("body").on("tap",".addcar",function(){$(".dropload-noData1").remove(),toApp.addCar()}),$("body").on("tap",".selectcar",function(){$(".dropload-noData1").remove(),toApp.selectCar(firstCarId)}),$("body").on("tap",".editcar",function(){$(".dropload-noData1").remove(),toApp.editCar(firstCarId)})},render:function(){$("#scroller").append(goodsList.create()),$(".downlist").append(goodsList.create()),myScroll=new IScroll("#nav",{scrollX:!0,scrollY:!1,freeScroll:!0}),nav.addEventListener("touchmove",function(a){a.preventDefault()},!1),goodsList.renderList(),goodsList.getnavtext(),goodsList.bindEvent()},relevanceId:function(a){$(this).addClass("current").parent("li").siblings("li").children("a").removeClass("current"),$(".slidewrap ul li a").each(function(){$(this).attr("data-index")==a&&$(this).addClass("current").parent("li").siblings("li").children("a").removeClass("current")}),$(".downlist ul li a").each(function(){$(this).attr("data-index")==a&&$(this).addClass("current").parent("li").siblings("li").children("a").removeClass("current")})},getcarCallback:function(a,e){"undefined"==a||void 0==a?($(".pipei").hide(),goodsList.nocar(e),goodsList.dataThree.fiveLevelCarId=0):0==a.complete?($(".pipei").hide(),1==a.onlyCarNo?goodsList.onlyCarnum():(goodsList.level2(a.id,a.categoryId,a.icon,a.name),firstCarId=a.id)):(goodsList.level5(a.id,a.categoryId,a.icon,a.name),firstCarId=a.id,goodsList.dataThree.fiveLevelCarId=a.categoryId,goodsList.pipei(e),$(".pipei").show())},nocar:function(a){$(".m-cartt").html(""),$(".m-handle").html(""),$(".g-car").removeClass("editcar").addClass("addcar"),$(".m-cartt").append('<p class="nocar"><img src="images/addcar.png" />添加爱车，精确匹配合适的'+a+"</p>"),$(".m-handle").append('<a href="javascript:;"><p class="addcar">完善爱车信息<img src="images/11.png" /></p></a>'),$(".pipei").hide()},onlyCarnum:function(){$(".m-cartt").html(""),$(".m-handle").html(""),$(".g-car").removeClass("addcar").addClass("editcar"),$(".m-cartt").append('<p class="nocar" ><img src="images/addcar.png"/>您的爱车信息不全无法匹配</p>'),$(".m-handle").append('<a href="javascript:;"><p class="editcar">完善爱车信息<img src="images/11.png" /></p></a>'),$(".pipei").hide()},level2:function(a,e,t,o){$(".m-cartt").html(""),$(".m-handle").html(""),$(".g-car").removeClass("addcar").addClass("editcar"),$(".m-cartt").append('<p class="nocar nocar1" ><img src="'+t+'" class="addcarpic"/><font>'+o+"，信息不全无法匹配</font></p>"),$(".m-handle").append('<a href="javascript:;"><p class="editcar">完善爱车信息<img src="images/11.png" /></p></a>'),$(".pipei").hide();for(var s=0,d=0;d<$(".nocar font").html().length;d++)$(".nocar font").html().charCodeAt(d)>127||94==$(".nocar font").html().charCodeAt(d)?s+=2:s++;s>$(".nocar font").width()/7||$(".nocar1").width()>219||$(".nocar1").height()>44?$(".m-cartt").css("line-height","0.6rem"):$(".m-cartt").css("line-height","1.17333rem")},level5:function(a,e,t,o,s){$(".m-cartt").html(""),$(".m-handle").html(""),$(".g-car").removeClass("addcar").removeClass("editcar"),$(".m-cartt").append('<p class="hascar" data-catid="'+a+'" categoryId="'+e+'"><img src="'+t+'"/>'+o+"</p>"),$(".m-handle").append('<a href="javascript:;"><p class="selectcar">换车<img src="images/11.png" /></p></a>'),goodsList.pipei(s),$(".m-cartt").css("line-height","1.17333rem")},bindEvent:function(){$(".g-main").on("tap",".g-mainc",function(){var a=$(this).attr("id");toApp.previewGoodDetail(a,2)}),$(".dropdown").on("tap",function(){$(".icon-unfold").hasClass("jiantou")?$(".icon-unfold").removeClass("jiantou").addClass("jiantou2"):$(".icon-unfold").removeClass("jiantou2").addClass("jiantou"),$(".downlist").hasClass("fromtop")?($(".downlist").removeClass("fromtop"),setTimeout(function(){$(".downlist").hide()},600)):($(".downlist").css("display",""),setTimeout(function(){$(".downlist").addClass("fromtop")},100))}),$(window).scroll(function(){var a=$(window).scrollTop(),e=$(".nav").offset().top;a>e?$(".nav .navcontent").css({position:"fixed",top:"0px","z-index":"42",transform:"translateZ(0px)"}):($(".blockdiv").hide(),$(".nav .navcontent").css({position:"relative",top:"0px"}),$(".fixtwo").removeClass("snavfix"))});var a=0,e=null,t=0;$(".g-main").on("touchstart",function(){$(".icon-unfold").hasClass("jiantou")&&$(".icon-unfold").removeClass("jiantou").addClass("jiantou2"),$(".downlist").hasClass("fromtop")&&($(".downlist").removeClass("fromtop"),setTimeout(function(){$(".downlist").hide()},600))}),$(".g-main").on("touchstart",function(e){a=e.touches[0].pageY}),$(".g-main").on("touchmove",function(o){var s=$(window).scrollTop(),d=$(".nav").offset().top;s>d?($(".nav .navcontent").css({position:"fixed",top:"0px","z-index":"42",transform:"translateZ(0px)"}),t=o.touches[0].pageY,t-a>0?(e="down",$(".fixtwo").addClass("snavfix"),$(".blockdiv").css({width:"100%",height:$(".fixtwo").height()}).show()):($(".fixtwo").removeClass("snavfix"),$(".blockdiv").hide())):($(".nav .navcontent").css({position:"relative",top:"0px"}),$(".fixtwo").removeClass("snavfix"),$(".blockdiv").hide()),$(".icon-unfold").hasClass("jiantou")&&$(".icon-unfold").removeClass("jiantou").addClass("jiantou2"),$(".downlist").hasClass("fromtop")&&($(".downlist").removeClass("fromtop"),setTimeout(function(){$(".downlist").hide()},600))}),$(".g-main").on("touchend",function(o){var s=$(window).scrollTop(),d=$(".nav").offset().top;d>s&&(t=o.changedTouches[0].pageY,t-a>0?(e="up",$(".fixtwo").hasClass("snavfix")&&($(".fixtwo").removeClass("snavfix"),$(".blockdiv").hide())):($(".fixtwo").removeClass("snavfix"),$(".blockdiv").hide())),$(".icon-unfold").hasClass("jiantou")&&$(".icon-unfold").removeClass("jiantou").addClass("jiantou2"),$(".downlist").hasClass("fromtop")&&($(".downlist").removeClass("fromtop"),setTimeout(function(){$(".downlist").hide()},600))}),$(window).on("scroll",function(){var a=$(window).scrollTop()+$(window).height()+100;if(a>$("body").height()){var e=!1,t=goodsList.olddorpload?goodsList.olddorpload:goodsList.dropload();counter=0,e?(t.lock("down"),t.noData()):(t.unlock(),t.noData(!1)),t.resetload()}})},dropload:function(){var a,e=0;return a=$(".waterfall").dropload({scrollArea:window,domDown:{domClass:"dropload-down",domRefresh:'<div class="dropload-refresh">↑上拉加载更多</div>',domLoad:'<div class="dropload-load"><span class="loading"></span>加载中...</div>',domNoData:'<div class="dropload-noData"><span></span></div>'},loadDownFn:function(a){isActive&&(isActive=!1,$.ajax({type:"get",url:dataurl+"/mallProd/carProdctList",dataType:"jsonp",data:goodsList.dataThree,success:function(t){if(t.info&&0!=t.info.length){isActive=!0,$(".g-main").show(),$(".dropload-down").show();var o=page.heivalue+"px";$(".waterfall").css("min-height",o),$(".nogoods").remove();for(var s="",d=e;d<t.info.length;d++)if(s+='<div class="g-mainc" id="'+t.info[d].itemId+'">',s+='<div class="m-content">',s+='<a href="javascript:;"><img data-original="'+t.info[d].images[0].medium+'" src="images/blank.gif" id="'+t.info[d].itemId+'" class="lazy"/>',s+="</a>",s+="</div>",s+='<div class="m-goods">',s+='<p class="u-goodsname">'+t.info[d].itemName+"</p>",s+='<p class="u-price">',s+='<span class="price">￥<em>'+t.info[d].salePrice+"</em></span>",s+='<span class="hassold">'+(0==t.info[d].itemStatistic.saleCount?" ":t.info[d].itemStatistic.saleCount+"人已购")+"</span>",s+="</p>",s+="</div>",s+='<div class="'+(1==t.info[d].isOriginal?"label":"")+'"></div>',s+="</div>",d+1>=t.info.length){tabLoadEnd=!0,a.lock(),a.noData();break}setTimeout(function(){$(".g-main").append(s),a.resetload(),picload.init()},0),goodsList.dataThree.pageNum+=1}else isActive=!0,$(".dropload-load").hide(),$(".nogoods").remove(),$(".g-main").height()<80?($(".dropload-down").after('<div class="nogoods"><p class="nogoodspic"></p><span class="nogoodstxt">目前没有相关商品</span></div>'),$(".dropload-noData1").remove(),$(".pipei").hide()):($(".dropload-noData1").remove(),$(".dropload-down").after('<div class="dropload-noData dropload-noData1"><span>已经最后一页了</span></div>')),$(".dropload-down").hide()},error:function(){console.log("加载出错"),a.resetload()}}))},threshold:1e4}),this.olddorpload=a,a}},firstCarId="",oldnavId="",oldnavalue="",oldsnavId="";</script>
	<script type="text/javascript">var page={heivalue:window.innerHeight,element:{pageOnload:pageOnload,header:header,banner:banner,saletitle:saletitle,goodsList:goodsList},init:function(){page.element.pageOnload.init(),page.element.header.init(),page.element.banner.init(),page.element.saletitle.init(),page.element.goodsList.init()}},dataurl="http://api.qichechaoren.com";</script>
	<script src="http://sale.qccr.com/js/traffics.js"></script>

</body>
</html>