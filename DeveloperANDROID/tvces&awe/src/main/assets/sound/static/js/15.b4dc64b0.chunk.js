webpackJsonp([15],{274:function(e,t,o){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}function r(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function i(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!==("undefined"===typeof t?"undefined":a(t))&&"function"!==typeof t?e:t}function l(e,t){if("function"!==typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+("undefined"===typeof t?"undefined":a(t)));e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(t,"__esModule",{value:!0});var a="function"===typeof Symbol&&"symbol"===typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"===typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e};o(60);var s=o(59),c=n(s),f=o(0),u=n(f),d=o(58);o(96);var g=o(103),m=n(g),p=function(){function e(e,t){for(var o=0;o<t.length;o++){var n=t[o];n.enumerable=n.enumerable||!1,n.configurable=!0,"value"in n&&(n.writable=!0),Object.defineProperty(e,n.key,n)}}return function(t,o,n){return o&&e(t.prototype,o),n&&e(t,n),t}}(),y=function(e){function t(e){r(this,t);var o=i(this,(t.__proto__||Object.getPrototypeOf(t)).call(this,e));return o.checkOthersLoginToUserCenterForFridge=function(e,t){var n=u.default.createElement("div",null,u.default.createElement("img",{src:m.default,alt:""}),u.default.createElement("p",{style:{fontSize:20}}," \u52a0\u8f7d\u4e2d... "));c.default.info(n,5);var r={code:t,redirect_url:e};console.log("params\u503c\u4e3a\uff1a"+JSON.stringify(r)),console.log("\u5f00\u59cb\u514d\u767b\u9646"),console.log("\u767b\u9646\u7684\u53c2\u6570\u4e3a\uff1a"+JSON.stringify(r));var i=setTimeout(function(){var e=u.default.createElement("div",{style:{width:120}},u.default.createElement("img",{src:m.default}),u.default.createElement("p",{style:{fontSize:24}}," \u52a0\u8f7d\u5931\u8d25"),u.default.createElement("p",{style:{fontSize:24}}," \u8bf7\u9000\u51fa\u91cd\u8bd5 "));c.default.info(e,120)},15e3);(0,d.checkOthersLoginToUserCenter)(r).then(function(e){clearTimeout(i),console.log("\u514d\u767b\u9646\u540e\u8fd4\u56de\u7684\u4fe1\u606f\u4e3a\uff1a"+JSON.stringify(e)),console.log("\u514d\u767b\u9646\u5b8c\u6210\uff1a"+e.msg);var t=e.data;console.log("\u5f53\u524d\u767b\u5f55\u7528\u6237\u7684\u6570\u636e\u4e3a\uff1a"+JSON.stringify(t)),t&&(console.log("result\u7684\u503c\u4e3a\uff1a"+JSON.stringify(t)),t.isActive=!0,t.sortIndex=0,localStorage.setItem("currentUser",JSON.stringify(t))),"C0000"===e.code&&(console.log("\u767b\u5f55\u6210\u529f\uff0c\u51c6\u5907\u83b7\u53d6\u5f53\u524d\u767b\u5f55\u4eba\u4fe1\u606f"),console.log("\u767b\u5f55\u6210\u529f\uff0c\u767b\u9646\u540e\u7684openId\u4e3a\uff1a"+e.openId),localStorage.setItem("openId",e.openId),localStorage.setItem("loginCode",e.data.login_code),e.data.privacy_aggrement=e.data.privacy_aggrement.slice(10),localStorage.setItem("privacy_aggrement",e.data.privacy_aggrement),console.log("\u51c6\u5907\u8c03\u7528android\u65b9\u6cd5window.user.getFamilyDetail()"),o.getMember()),c.default.hide()}).catch(function(e){console.log("checkOthersLoginToUserCenter\u7684\u9519\u8bef\u4fe1\u606f\u4e3a\uff1a"+e),c.default.hide()})},o.getMember=function(){var e=localStorage.getItem("openId"),t={open_id:e};(0,d.getMemberList)(t).then(function(e){console.log("getMemberList\u65b9\u6cd5\u8fd4\u56de\u7684\u6570\u636e\u4e3a\uff1a"+JSON.stringify(e));var t=e.data;if(t&&t.length)for(var n=0;n<t.length;n++)t[n].isActive=!1,t[n].sortIndex=n+1;console.log("getMemberList\u65b9\u6cd5\u5904\u7406\u540e\u7684\u6570\u636e\u4e3a",JSON.stringify(t)),localStorage.setItem("family",JSON.stringify(t)),o.unionInfo(t),c.default.hide()}).catch(function(e){c.default.info(u.default.createElement("div",{style:{fontSize:30,width:300}},"\u83b7\u53d6\u4fe1\u606f\u5931\u8d25"),2)})},o.unionInfo=function(e){console.log("\u5f00\u59cb\u5408\u5e76\u5f53\u524d\u767b\u5f55\u4eba\u4e0e\u5bb6\u4eba\u4fe1\u606f");var t=JSON.parse(localStorage.getItem("currentUser"));console.log("user\u7684\u6570\u636e\u4e3a\uff1a"+JSON.stringify(t)),console.log("unionInfo\u65b9\u6cd5\u4e2dfamily\u7684\u4fe1\u606f\u4e3a\uff1a"+JSON.stringify(e)),e?e.unshift(t):e.push(t),console.log("\u5408\u5e76\u5b8c\u6bd5\uff0cfamily\u4fe1\u606f\u5b58\u5165\u672c\u5730\u7f13\u5b58\u4e2d\uff0c\u5408\u5e76\u540e\u7684family\u4fe1\u606f\u4e3a\uff1a"+JSON.stringify(e)),localStorage.setItem("family",JSON.stringify(e));var n={pathname:"",query:{history:o.props.history}};"N"==JSON.parse(localStorage.getItem("currentUser")).user_flag?(console.log("\u51c6\u5907\u8df3\u8f6chome\u9875\u9762"),n.pathname="/sound/home"):(console.log("\u51c6\u5907\u8df3\u8f6cinstroduction\u9875\u9762"),n.pathname="/sound/instroduction"),o.props.history.push(n)},o.state={userInfo:{},family:[],privacy_aggrement:"-1",msgModal:!1},o.checkOthersLoginToUserCenterForFridge=o.checkOthersLoginToUserCenterForFridge.bind(o),o.unionInfo=o.unionInfo.bind(o),o.getMember=o.getMember.bind(o),o}return l(t,e),p(t,[{key:"componentWillMount",value:function(){var e=!1;sessionStorage.getItem("isExitApp")&&(e=sessionStorage.getItem("isExitApp")),1!=e&&"true"!=e||sessionStorage.setItem("isExitApp",!0)}},{key:"componentDidMount",value:function(){if(window.Login=this,"development"!==window.process.env.NODE_ENV){var e="http://127.0.0.1:8888/sound/index.html",t=window.location.href.substring(window.location.href.indexOf("=")+1,window.location.href.indexOf("&"));t?(localStorage.setItem("code:+",t),this.checkOthersLoginToUserCenterForFridge(e,t)):(window.location.replace("https://account.haier.com/oauth/authorize?client_id=jiangkangyun&response_type=code&state=qazwsx&redirect_uri="+e),setTimeout(function(){var e=u.default.createElement("div",{style:{width:120}},u.default.createElement("img",{src:m.default,alt:""}),u.default.createElement("p",{style:{fontSize:24}}," \u52a0\u8f7d\u5931\u8d25"),u.default.createElement("p",{style:{fontSize:24}}," \u8bf7\u9000\u51fa\u91cd\u8bd5 "));c.default.info(e,120)},1e4))}}},{key:"render",value:function(){return u.default.createElement("div",null)}}]),t}(u.default.Component);t.default=y}});