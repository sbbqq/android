webpackJsonp([1],{"/n6Q":function(t,e,n){n("zQR9"),n("+tPU"),t.exports=n("Kh4W").f("iterator")},"06OY":function(t,e,n){var o=n("3Eo+")("meta"),i=n("EqjI"),r=n("D2L2"),a=n("evD5").f,s=0,c=Object.isExtensible||function(){return!0},u=!n("S82l")(function(){return c(Object.preventExtensions({}))}),l=function(t){a(t,o,{value:{i:"O"+ ++s,w:{}}})},f=t.exports={KEY:o,NEED:!1,fastKey:function(t,e){if(!i(t))return"symbol"==typeof t?t:("string"==typeof t?"S":"P")+t;if(!r(t,o)){if(!c(t))return"F";if(!e)return"E";l(t)}return t[o].i},getWeak:function(t,e){if(!r(t,o)){if(!c(t))return!0;if(!e)return!1;l(t)}return t[o].w},onFreeze:function(t){return u&&f.NEED&&c(t)&&!r(t,o)&&l(t),t}}},"1kS7":function(t,e){e.f=Object.getOwnPropertySymbols},"5QVw":function(t,e,n){t.exports={default:n("BwfY"),__esModule:!0}},"7UMu":function(t,e,n){var o=n("R9M2");t.exports=Array.isArray||function(t){return"Array"==o(t)}},BuRH:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});n("mtWM"),n("zlE8");var o=n("Xxa5"),i=n.n(o),r=n("exGp"),a=n.n(r),s={hasClass:function(t,e){return new RegExp("(\\s|^)"+e+"(\\s|$)").test(t.className)},addClass:function(t,e){t&&(t.classList?t.classList.add(e):this.hasClass(t,e)||(t.className+=""+e))},removeClass:function(t,e){t&&(t.classList?t.classList.remove(e):this.hasClass(t,e)&&(t.className=t.className.replace(new RegExp("(\\s|^)"+e+"(\\s|$)")," ").replace(/^\s+|\s+$/g,"")))}},c={methods:{getLayout:function(){return"undefined"!=typeof window&&window.VUX_CONFIG&&"VIEW_BOX"===window.VUX_CONFIG.$layout?"VIEW_BOX":""},addModalClassName:function(){"function"==typeof this.shouldPreventScroll&&this.shouldPreventScroll()||"VIEW_BOX"===this.getLayout()&&(s.addClass(document.body,"vux-modal-open"),s.addClass(document.querySelector("#vux_view_box_body"),"vux-modal-open-for-container"))},removeModalClassName:function(){"VIEW_BOX"===this.getLayout()&&(s.removeClass(document.body,"vux-modal-open"),s.removeClass(document.querySelector("#vux_view_box_body"),"vux-modal-open-for-container"))}},beforeDestroy:function(){this.removeModalClassName()},deactivated:function(){this.removeModalClassName()}},u=(Boolean,String,String,Number,String,String,Boolean,Object,Boolean,{mixins:[c],name:"x-dialog",model:{prop:"show",event:"change"},props:{show:{type:Boolean,default:!1},maskTransition:{type:String,default:"vux-mask"},maskZIndex:[String,Number],dialogTransition:{type:String,default:"vux-dialog"},dialogClass:{type:String,default:"weui-dialog"},hideOnBlur:Boolean,dialogStyle:Object,scroll:{type:Boolean,default:!0,validator:function(t){return!0}}},computed:{maskStyle:function(){if(void 0!==this.maskZIndex)return{zIndex:this.maskZIndex}}},mounted:function(){"undefined"!=typeof window&&window.VUX_CONFIG&&"VIEW_BOX"===window.VUX_CONFIG.$layout&&(this.layout="VIEW_BOX")},watch:{show:function(t){this.$emit("update:show",t),this.$emit(t?"on-show":"on-hide"),t?this.addModalClassName():this.removeModalClassName()}},methods:{shouldPreventScroll:function(){var t=/iPad|iPhone|iPod/i.test(window.navigator.userAgent),e=this.$el.querySelector("input")||this.$el.querySelector("textarea");if(t&&e)return!0},hide:function(){this.hideOnBlur&&(this.$emit("update:show",!1),this.$emit("change",!1),this.$emit("on-click-mask"))}},data:function(){return{layout:""}}}),l={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"vux-x-dialog",class:{"vux-x-dialog-absolute":"VIEW_BOX"===t.layout}},[n("transition",{attrs:{name:t.maskTransition}},[n("div",{directives:[{name:"show",rawName:"v-show",value:t.show,expression:"show"}],staticClass:"weui-mask",style:t.maskStyle,on:{click:t.hide}})]),t._v(" "),n("transition",{attrs:{name:t.dialogTransition}},[n("div",{directives:[{name:"show",rawName:"v-show",value:t.show,expression:"show"}],class:t.dialogClass,style:t.dialogStyle},[t._t("default")],2)])],1)},staticRenderFns:[]};var f=n("VU/8")(u,l,!1,function(t){n("o2OU")},null,null).exports,d=n("pFYg"),p=n.n(d);function h(t,e){!/^javas/.test(t)&&t&&("object"===(void 0===t?"undefined":p()(t))||e&&"string"==typeof t&&!/http/.test(t)?"object"===(void 0===t?"undefined":p()(t))&&!0===t.replace?e.replace(t):"BACK"===t?e.go(-1):e.push(t):window.location.href=t)}Boolean,Boolean,Boolean,String,String,Boolean,String,Object,Array;var v={name:"x-button",props:{type:{default:"default"},disabled:Boolean,mini:Boolean,plain:Boolean,text:String,actionType:String,showLoading:Boolean,link:[String,Object],gradients:{type:Array,validator:function(t){return 2===t.length}}},methods:{onClick:function(){!this.disabled&&h(this.link,this.$router)}},computed:{noBorder:function(){return Array.isArray(this.gradients)},buttonStyle:function(){if(this.gradients)return{background:"linear-gradient(90deg, "+this.gradients[0]+", "+this.gradients[1]+")",color:"#FFFFFF"}},classes:function(){return[{"weui-btn_disabled":!this.plain&&this.disabled,"weui-btn_plain-disabled":this.plain&&this.disabled,"weui-btn_mini":this.mini,"vux-x-button-no-border":this.noBorder},this.plain?"":"weui-btn_"+this.type,this.plain?"weui-btn_plain-"+this.type:"",this.showLoading?"weui-btn_loading":""]}}},y={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("button",{staticClass:"weui-btn",class:t.classes,style:t.buttonStyle,attrs:{disabled:t.disabled,type:t.actionType},on:{click:t.onClick}},[t.showLoading?n("i",{staticClass:"weui-loading"}):t._e(),t._v(" "),t._t("default",[t._v(t._s(t.text))])],2)},staticRenderFns:[]};var m=n("VU/8")(v,y,!1,function(t){n("LvJI")},null,null).exports,g=(Boolean,{name:"privacy",props:{show:Boolean},components:{XDialog:f,XButton:m},data:function(){return{privacy_show:this.show,privacy_again:!1,itemSelect:"agree"}},mounted:function(){var t=document.getElementsByTagName("html")[0];"1"===window.localStorage.TV_UHOME_PRIVACY?(this.privacy_show=!1,t.style.overflow="auto"):t.style.overflow="hidden"},watch:{show:function(){this.privacy_show=this.show}},methods:{$_agree:function(){var t=this;return a()(i.a.mark(function e(){return i.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,t.$axios.post(t.$configHttp.path+"/user/aggreePrivaceAgreement",{type:"2",flag:"1"});case 3:e.sent,t.privacy_show=!1,window.localStorage.TV_UHOME_PRIVACY="1",e.next=11;break;case 8:e.prev=8,e.t0=e.catch(0),console.log("Whoops, something went wrong....",e.t0);case 11:case"end":return e.stop()}},e,t,[[0,8]])}))()},$_quit:function(){this.privacy_show=!1,this.privacy_again=!0,this.itemSelect="again"},$_again:function(){var t=this;return a()(i.a.mark(function e(){return i.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,t.$axios.post(t.$configHttp.path+"/user/aggreePrivaceAgreement",{type:"2",flag:"1"});case 3:e.sent,t.privacy_again=!1,window.localStorage.TV_UHOME_PRIVACY="1",e.next=11;break;case 8:e.prev=8,e.t0=e.catch(0),console.log("Whoops, something went wrong....",e.t0);case 11:case"end":return e.stop()}},e,t,[[0,8]])}))()},$_exit:function(){var t=this;return a()(i.a.mark(function e(){return i.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,t.$axios.post(t.$configHttp.path+"/user/aggreePrivaceAgreement",{type:"2",flag:"-1"});case 3:e.sent,t.privacy_again=!1,t.$router.go(-1),window.localStorage.TV_UHOME_PRIVACY="0",e.next=12;break;case 9:e.prev=9,e.t0=e.catch(0),console.log("Whoops, something went wrong....",e.t0);case 12:case"end":return e.stop()}},e,t,[[0,9]])}))()},$_setStyle:function(t){this.itemSelect=t}}}),w={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("x-dialog",{staticClass:"dialog-demo",model:{value:t.privacy_show,callback:function(e){t.privacy_show=e},expression:"privacy_show"}},[n("div",{staticClass:"dialog-content"},[n("h2",[t._v("健康档案隐私权政策")]),t._v(" "),n("p",[t._v("\n                感谢您使用“健康档案”，健康档案尊重用户的个人信息和用户对服务的知情权利，并为此发布了隐私权政策。 为了向您提供高效优质的服务，我们需要搜集：您的手机号码和个人信息（可能涉及账号、设备、服务使用等相关信息），以用于注册您的会员账号等。我们不会向任何第三方提供您的信息，除非得到您的授权。此外，我们还将升级服务，为您提供“一个账号”管理海尔旗下不同应用端的服务便利。 详情请您仔细阅读：\n                "),n("router-link",{attrs:{to:"/tv/privacy"},nativeOn:{focus:function(t){this.blur()}}},[t._v("《海尔家电隐私权政策》")])],1),t._v(" "),n("div",{staticStyle:{display:"inline-flex","margin-top":"8rem"}},[n("a",{class:"quit"===t.itemSelect?"item-focus":"",staticStyle:{margin:"0 2.5rem"},on:{focus:function(e){t.$_setStyle("quit")},click:t.$_quit}},[t._v("不同意并退出")]),t._v(" "),n("a",{class:"agree"===t.itemSelect?"item-focus":"",staticStyle:{margin:"0 2.5rem"},on:{focus:function(e){t.$_setStyle("agree")},click:t.$_agree}},[t._v("同意")])])])]),t._v(" "),n("x-dialog",{staticClass:"dialog-demo",model:{value:t.privacy_again,callback:function(e){t.privacy_again=e},expression:"privacy_again"}},[n("div",{staticClass:"dialog-content"},[n("h2",[t._v("确定不同意并退出吗？")]),t._v(" "),n("p",[t._v("我们需要获得您的同意才能为您提供服务。请您放心，我们一直尽力采取行业领先的安全防护措施来保护您提供的个人信息。我们不会向任何第三方提供您的信息，如果某些特定服务需要提供特定信息的，我们会单独征求您的意见。详情请您仔细阅读：\n                "),n("router-link",{attrs:{to:"/tv/privacy"},nativeOn:{focus:function(t){this.blur()}}},[t._v("《海尔家电隐私权政策》")])],1),t._v(" "),n("div",{staticStyle:{display:"inline-flex","margin-top":"8rem"}},[n("a",{class:"exit"===t.itemSelect?"item-focus":"",staticStyle:{margin:"0 2.5rem"},on:{focus:function(e){t.$_setStyle("exit")},click:t.$_exit}},[t._v("不同意并退出")]),t._v(" "),n("a",{class:"again"===t.itemSelect?"item-focus":"",staticStyle:{margin:"0 2.5rem"},on:{focus:function(e){t.$_setStyle("again")},click:t.$_again}},[t._v("同意")])])])])],1)},staticRenderFns:[]};var _=n("VU/8")(g,w,!1,function(t){n("ojbM")},"data-v-1ea7566a",null).exports,b={name:"IntroductionComponent",components:{privacy:_},data:function(){return{show:!1}},mounted:function(){},methods:{}},S={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",[e("div",{staticClass:"introduction-bg"}),this._v(" "),e("privacy",{attrs:{show:this.show}})],1)},staticRenderFns:[]};var x=n("VU/8")(b,S,!1,function(t){n("VLfZ")},"data-v-84cf9882",null);e.default=x.exports},BwfY:function(t,e,n){n("fWfb"),n("M6a0"),n("OYls"),n("QWe/"),t.exports=n("FeBl").Symbol},Kh4W:function(t,e,n){e.f=n("dSzd")},LKZe:function(t,e,n){var o=n("NpIQ"),i=n("X8DO"),r=n("TcQ7"),a=n("MmMw"),s=n("D2L2"),c=n("SfB7"),u=Object.getOwnPropertyDescriptor;e.f=n("+E39")?u:function(t,e){if(t=r(t),e=a(e,!0),c)try{return u(t,e)}catch(t){}if(s(t,e))return i(!o.f.call(t,e),t[e])}},LvJI:function(t,e){},NpIQ:function(t,e){e.f={}.propertyIsEnumerable},OYls:function(t,e,n){n("crlp")("asyncIterator")},"QWe/":function(t,e,n){n("crlp")("observable")},Rrel:function(t,e,n){var o=n("TcQ7"),i=n("n0T6").f,r={}.toString,a="object"==typeof window&&window&&Object.getOwnPropertyNames?Object.getOwnPropertyNames(window):[];t.exports.f=function(t){return a&&"[object Window]"==r.call(t)?function(t){try{return i(t)}catch(t){return a.slice()}}(t):i(o(t))}},VLfZ:function(t,e){},Xc4G:function(t,e,n){var o=n("lktj"),i=n("1kS7"),r=n("NpIQ");t.exports=function(t){var e=o(t),n=i.f;if(n)for(var a,s=n(t),c=r.f,u=0;s.length>u;)c.call(t,a=s[u++])&&e.push(a);return e}},Zzip:function(t,e,n){t.exports={default:n("/n6Q"),__esModule:!0}},crlp:function(t,e,n){var o=n("7KvD"),i=n("FeBl"),r=n("O4g8"),a=n("Kh4W"),s=n("evD5").f;t.exports=function(t){var e=i.Symbol||(i.Symbol=r?{}:o.Symbol||{});"_"==t.charAt(0)||t in e||s(e,t,{value:a.f(t)})}},fWfb:function(t,e,n){"use strict";var o=n("7KvD"),i=n("D2L2"),r=n("+E39"),a=n("kM2E"),s=n("880/"),c=n("06OY").KEY,u=n("S82l"),l=n("e8AB"),f=n("e6n0"),d=n("3Eo+"),p=n("dSzd"),h=n("Kh4W"),v=n("crlp"),y=n("Xc4G"),m=n("7UMu"),g=n("77Pl"),w=n("EqjI"),_=n("TcQ7"),b=n("MmMw"),S=n("X8DO"),x=n("Yobk"),O=n("Rrel"),k=n("LKZe"),C=n("evD5"),E=n("lktj"),B=k.f,$=C.f,I=O.f,N=o.Symbol,M=o.JSON,P=M&&M.stringify,j=p("_hidden"),V=p("toPrimitive"),F={}.propertyIsEnumerable,W=l("symbol-registry"),T=l("symbols"),L=l("op-symbols"),A=Object.prototype,X="function"==typeof N,U=o.QObject,D=!U||!U.prototype||!U.prototype.findChild,Q=r&&u(function(){return 7!=x($({},"a",{get:function(){return $(this,"a",{value:7}).a}})).a})?function(t,e,n){var o=B(A,e);o&&delete A[e],$(t,e,n),o&&t!==A&&$(A,e,o)}:$,R=function(t){var e=T[t]=x(N.prototype);return e._k=t,e},Y=X&&"symbol"==typeof N.iterator?function(t){return"symbol"==typeof t}:function(t){return t instanceof N},K=function(t,e,n){return t===A&&K(L,e,n),g(t),e=b(e,!0),g(n),i(T,e)?(n.enumerable?(i(t,j)&&t[j][e]&&(t[j][e]=!1),n=x(n,{enumerable:S(0,!1)})):(i(t,j)||$(t,j,S(1,{})),t[j][e]=!0),Q(t,e,n)):$(t,e,n)},q=function(t,e){g(t);for(var n,o=y(e=_(e)),i=0,r=o.length;r>i;)K(t,n=o[i++],e[n]);return t},Z=function(t){var e=F.call(this,t=b(t,!0));return!(this===A&&i(T,t)&&!i(L,t))&&(!(e||!i(this,t)||!i(T,t)||i(this,j)&&this[j][t])||e)},z=function(t,e){if(t=_(t),e=b(e,!0),t!==A||!i(T,e)||i(L,e)){var n=B(t,e);return!n||!i(T,e)||i(t,j)&&t[j][e]||(n.enumerable=!0),n}},G=function(t){for(var e,n=I(_(t)),o=[],r=0;n.length>r;)i(T,e=n[r++])||e==j||e==c||o.push(e);return o},H=function(t){for(var e,n=t===A,o=I(n?L:_(t)),r=[],a=0;o.length>a;)!i(T,e=o[a++])||n&&!i(A,e)||r.push(T[e]);return r};X||(s((N=function(){if(this instanceof N)throw TypeError("Symbol is not a constructor!");var t=d(arguments.length>0?arguments[0]:void 0),e=function(n){this===A&&e.call(L,n),i(this,j)&&i(this[j],t)&&(this[j][t]=!1),Q(this,t,S(1,n))};return r&&D&&Q(A,t,{configurable:!0,set:e}),R(t)}).prototype,"toString",function(){return this._k}),k.f=z,C.f=K,n("n0T6").f=O.f=G,n("NpIQ").f=Z,n("1kS7").f=H,r&&!n("O4g8")&&s(A,"propertyIsEnumerable",Z,!0),h.f=function(t){return R(p(t))}),a(a.G+a.W+a.F*!X,{Symbol:N});for(var J="hasInstance,isConcatSpreadable,iterator,match,replace,search,species,split,toPrimitive,toStringTag,unscopables".split(","),tt=0;J.length>tt;)p(J[tt++]);for(var et=E(p.store),nt=0;et.length>nt;)v(et[nt++]);a(a.S+a.F*!X,"Symbol",{for:function(t){return i(W,t+="")?W[t]:W[t]=N(t)},keyFor:function(t){if(!Y(t))throw TypeError(t+" is not a symbol!");for(var e in W)if(W[e]===t)return e},useSetter:function(){D=!0},useSimple:function(){D=!1}}),a(a.S+a.F*!X,"Object",{create:function(t,e){return void 0===e?x(t):q(x(t),e)},defineProperty:K,defineProperties:q,getOwnPropertyDescriptor:z,getOwnPropertyNames:G,getOwnPropertySymbols:H}),M&&a(a.S+a.F*(!X||u(function(){var t=N();return"[null]"!=P([t])||"{}"!=P({a:t})||"{}"!=P(Object(t))})),"JSON",{stringify:function(t){for(var e,n,o=[t],i=1;arguments.length>i;)o.push(arguments[i++]);if(n=e=o[1],(w(e)||void 0!==t)&&!Y(t))return m(e)||(e=function(t,e){if("function"==typeof n&&(e=n.call(this,t,e)),!Y(e))return e}),o[1]=e,P.apply(M,o)}}),N.prototype[V]||n("hJx8")(N.prototype,V,N.prototype.valueOf),f(N,"Symbol"),f(Math,"Math",!0),f(o.JSON,"JSON",!0)},n0T6:function(t,e,n){var o=n("Ibhu"),i=n("xnc9").concat("length","prototype");e.f=Object.getOwnPropertyNames||function(t){return o(t,i)}},o2OU:function(t,e){},ojbM:function(t,e){},pFYg:function(t,e,n){"use strict";e.__esModule=!0;var o=a(n("Zzip")),i=a(n("5QVw")),r="function"==typeof i.default&&"symbol"==typeof o.default?function(t){return typeof t}:function(t){return t&&"function"==typeof i.default&&t.constructor===i.default&&t!==i.default.prototype?"symbol":typeof t};function a(t){return t&&t.__esModule?t:{default:t}}e.default="function"==typeof i.default&&"symbol"===r(o.default)?function(t){return void 0===t?"undefined":r(t)}:function(t){return t&&"function"==typeof i.default&&t.constructor===i.default&&t!==i.default.prototype?"symbol":void 0===t?"undefined":r(t)}}});