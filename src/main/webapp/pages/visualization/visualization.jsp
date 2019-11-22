<%--
  Created by IntelliJ IDEA.
  User: venkateswarlu.s
  Date: 14-05-2019
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<!-- begin::Head -->
<head>
    <meta charset="utf-8" />
    <title>GOSTARNext | Search Visualization</title>
    <meta name="description" content="Latest updates and statistic charts">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">

    <!--begin::Web font resource -->
    <script src="https://ajax.googleapis.com/ajax/libs/webfont/1.6.16/webfont.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.2.2/jszip.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Base64/1.0.2/base64.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@cryptolize/FileSaver@1.0.0/FileSaver.min.js" type="text/javascript"></script>
    <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>-->
    <script src=" https://cdn.jsdelivr.net/npm/base64toblob@0.0.2/example/main.js" type="text/javascript"></script>
    <script src="${contextPath}/assets/scripts/fonts/webfont.js"></script>
    <script>
        (function (global, factory) {
            if (typeof define === "function" && define.amd) {
                define([], factory);
            } else if (typeof exports !== "undefined") {
                factory();
            } else {
                var mod = {
                    exports: {}
                };
                factory();
                global.FileSaver = mod.exports;
            }
        })(this, function () {
            "use strict";

            /*
            * FileSaver.js
            * A saveAs() FileSaver implementation.
            *
            * By Eli Grey, http://eligrey.com
            *
            * License : https://github.com/eligrey/FileSaver.js/blob/master/LICENSE.md (MIT)
            * source  : http://purl.eligrey.com/github/FileSaver.js
            */
            // The one and only way of getting global scope in all environments
            // https://stackoverflow.com/q/3277182/1008999
            var _global = typeof window === 'object' && window.window === window ? window : typeof self === 'object' && self.self === self ? self : typeof global === 'object' && global.global === global ? global : void 0;

            function bom(blob, opts) {
                if (typeof opts === 'undefined') opts = {
                    autoBom: false
                };else if (typeof opts !== 'object') {
                    console.warn('Deprecated: Expected third argument to be a object');
                    opts = {
                        autoBom: !opts
                    };
                } // prepend BOM for UTF-8 XML and text/* types (including HTML)
                // note: your browser will automatically convert UTF-16 U+FEFF to EF BB BF

                if (opts.autoBom && /^\s*(?:text\/\S*|application\/xml|\S*\/\S*\+xml)\s*;.*charset\s*=\s*utf-8/i.test(blob.type)) {
                    return new Blob([String.fromCharCode(0xFEFF), blob], {
                        type: blob.type
                    });
                }

                return blob;
            }

            function download(url, name, opts) {
                var xhr = new XMLHttpRequest();
                xhr.open('GET', url);
                xhr.responseType = 'blob';

                xhr.onload = function () {
                    saveAs(xhr.response, name, opts);
                };

                xhr.onerror = function () {
                    console.error('could not download file');
                };

                xhr.send();
            }

            function corsEnabled(url) {
                var xhr = new XMLHttpRequest(); // use sync to avoid popup blocker

                xhr.open('HEAD', url, false);

                try {
                    xhr.send();
                } catch (e) {}

                return xhr.status >= 200 && xhr.status <= 299;
            } // `a.click()` doesn't work for all browsers (#465)


            function click(node) {
                try {
                    node.dispatchEvent(new MouseEvent('click'));
                } catch (e) {
                    var evt = document.createEvent('MouseEvents');
                    evt.initMouseEvent('click', true, true, window, 0, 0, 0, 80, 20, false, false, false, false, 0, null);
                    node.dispatchEvent(evt);
                }
            }

            var saveAs = _global.saveAs || ( // probably in some web worker
                typeof window !== 'object' || window !== _global ? function saveAs() {}
                    /* noop */
                    // Use download attribute first if possible (#193 Lumia mobile)
                    : 'download' in HTMLAnchorElement.prototype ? function saveAs(blob, name, opts) {
                        var URL = _global.URL || _global.webkitURL;
                        var a = document.createElement('a');
                        name = name || blob.name || 'download';
                        a.download = name;
                        a.rel = 'noopener'; // tabnabbing
                        // TODO: detect chrome extensions & packaged apps
                        // a.target = '_blank'

                        if (typeof blob === 'string') {
                            // Support regular links
                            a.href = blob;

                            if (a.origin !== location.origin) {
                                corsEnabled(a.href) ? download(blob, name, opts) : click(a, a.target = '_blank');
                            } else {
                                click(a);
                            }
                        } else {
                            // Support blobs
                            a.href = URL.createObjectURL(blob);
                            setTimeout(function () {
                                URL.revokeObjectURL(a.href);
                            }, 4E4); // 40s

                            setTimeout(function () {
                                click(a);
                            }, 0);
                        }
                    } // Use msSaveOrOpenBlob as a second approach
                    : 'msSaveOrOpenBlob' in navigator ? function saveAs(blob, name, opts) {
                            name = name || blob.name || 'download';

                            if (typeof blob === 'string') {
                                if (corsEnabled(blob)) {
                                    download(blob, name, opts);
                                } else {
                                    var a = document.createElement('a');
                                    a.href = blob;
                                    a.target = '_blank';
                                    setTimeout(function () {
                                        click(a);
                                    });
                                }
                            } else {
                                navigator.msSaveOrOpenBlob(bom(blob, opts), name);
                            }
                        } // Fallback to using FileReader and a popup
                        : function saveAs(blob, name, opts, popup) {
                            // Open a popup immediately do go around popup blocker
                            // Mostly only available on user interaction and the fileReader is async so...
                            popup = popup || open('', '_blank');

                            if (popup) {
                                popup.document.title = popup.document.body.innerText = 'downloading...';
                            }

                            if (typeof blob === 'string') return download(blob, name, opts);
                            var force = blob.type === 'application/octet-stream';

                            var isSafari = /constructor/i.test(_global.HTMLElement) || _global.safari;

                            var isChromeIOS = /CriOS\/[\d]+/.test(navigator.userAgent);

                            if ((isChromeIOS || force && isSafari) && typeof FileReader !== 'undefined') {
                                // Safari doesn't allow downloading of blob URLs
                                var reader = new FileReader();

                                reader.onloadend = function () {
                                    var url = reader.result;
                                    url = isChromeIOS ? url : url.replace(/^data:[^;]*;/, 'data:attachment/file;');
                                    if (popup) popup.location.href = url;else location = url;
                                    popup = null; // reverse-tabnabbing #460
                                };

                                reader.readAsDataURL(blob);
                            } else {
                                var URL = _global.URL || _global.webkitURL;
                                var url = URL.createObjectURL(blob);
                                if (popup) popup.location = url;else location.href = url;
                                popup = null; // reverse-tabnabbing #460

                                setTimeout(function () {
                                    URL.revokeObjectURL(url);
                                }, 4E4); // 40s
                            }
                        });
            _global.saveAs = saveAs.saveAs = saveAs;

            if (typeof module !== 'undefined') {
                module.exports = saveAs;
            }
        });
        WebFont.load({
            google: {
                "families": ["Poppins:300,400,500,600,700", "Roboto:300,400,500,600,700"]
            },
            active: function() {
                sessionStorage.fonts = true;
            }
        });
    </script>
    <!--end::Web font resource -->

    <!--begin::Base Styles -->
    <link href="${contextPath}/assets/vendors/base/vendors.bundle.css" rel="stylesheet" type="text/css" />
    <link href="${contextPath}/assets/scripts/base/style.bundle.css" rel="stylesheet" type="text/css" />

    <!--end::Base Styles -->
    <link rel="shortcut icon" href="${contextPath}/assets/images/logos/favicon.ico" />
    <!-- Do you need Fontawesome? -->
    <link
            href="${contextPath}/resources/css/fontawesome/css/all.min.css"
            rel="stylesheet"
    />
    <!-- Do you need all animations? -->
    <link href="${contextPath}/resources/css/animate.min.css" rel="stylesheet" />
    <!--
    Finally, add Quasar's CSS:
    Replace version below (1.0.0-beta.0) with your desired version of Quasar.
    Add ".rtl" for the RTL support (example: quasar.rtl.min.css).
    -->
    <link
            href="${contextPath}/resources/css/quasar.min.css"
            rel="stylesheet"
            type="text/css"
    />
    <link href="${contextPath}/resources/css/app.css" rel="stylesheet" />
    <!-- Add this to <head> -->
    <!-- Load required Bootstrap and BootstrapVue CSS -->
    <link type="text/css" rel="stylesheet"
          href="http://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.min.css" />
    <!-- Load polyfills to support older browsers -->
    <script
            src="http://polyfill.io/v3/polyfill.min.js?features=es2015%2CMutationObserver"
            crossorigin="anonymous"></script>
    <!-- Load Vue followed by BootstrapVue -->
    <script src="http://unpkg.com/vue@latest/dist/vue.js"></script>
    <script
            src="http://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.js"></script>



    <script src="https://cdn.jsdelivr.net/npm/echarts@4.1.0/dist/echarts.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue-echarts@4.0.2"></script>
    <link href="${contextPath}/resources/css/override.css" rel="stylesheet" />
</head>

<!-- end::Head -->
<!-- begin::Body -->
<body class="visualisation m-page--fluid m--skin- m-content--skin-light2 m-header--fixed m-header--fixed-mobile m-aside-left--enabled m-aside-left--skin-dark m-aside-left--fixed m-aside-left--offcanvas m-aside-left--minimize m-brand--minimize m-footer--push m-aside--offcanvas-default">
<div id="q-app" v-cloak>
    <app-loader v-if="showLoading"></app-loader>
    <!-- Top header bar background color -->
    <div>
        <!-- begin::Page loader -->
        <div class="m-page-loader m-page-loader--base">
            <div class="m-blockui">
                <span>Please wait...</span>
                <span>
                        <div class="m-loader m-loader--brand"></div>
                    </span>
            </div>
        </div>
        <!-- end::Page Loader -->
        <!-- begin:: Page -->
        <div class="m-grid m-grid--hor m-grid--root m-page">

            <!-- BEGIN: Main Header panel -->
            <header class="m-grid__item  m-header " m-minimize-offset="200" m-minimize-mobile-offset="200" style="height:80px; background-color:#0099cc; top:0px; padding-top:70px">
                <div class="m-container m-container--fluid m-container--full-height">
                    <div class="m-stack m-stack--ver m-stack--desktop">
                        <!-- BEGIN: Brand -->
                        <div class="m-stack__item m-brand  m-brand--skin-dark">
                            <div class="m-stack m-stack--ver m-stack--general">
                                <div class="m-stack__item m-stack__item--middle m-brand__logo"> <a href="../../../index.html" class="m-brand__logo-wrapper"> <img alt="" src="../../../assets/demo/default/media/img/logo/logo_default_dark.png" /> </a> </div>
                                <div class="m-stack__item m-stack__item--middle m-brand__tools">

                                    <!-- BEGIN: Left Aside Minimize Toggle -->
                                    <a href="javascript:;" id="m_aside_left_minimize_toggle" class="m-brand__icon m-brand__toggler m-brand__toggler--left m--visible-desktop-inline-block m-brand__toggler--active "> <span></span> </a>

                                    <!-- END -->
                                    <!-- BEGIN: Responsive Aside Left Menu Toggler -->
                                    <a href="javascript:;" id="m_aside_left_offcanvas_toggle" class="m-brand__icon m-brand__toggler m-brand__toggler--left m--visible-tablet-and-mobile-inline-block"> <span></span> </a>

                                    <!-- END -->
                                    <!-- BEGIN: Responsive Header Menu Toggler -->
                                    <a id="m_aside_header_menu_mobile_toggle" href="javascript:;" class="m-brand__icon m-brand__toggler m--visible-tablet-and-mobile-inline-block"> <span></span> </a>

                                    <!-- END -->
                                    <!-- BEGIN: Topbar Toggler -->
                                    <a id="m_aside_header_topbar_mobile_toggle" href="javascript:;" class="m-brand__icon m--visible-tablet-and-mobile-inline-block"> <i class="flaticon-more"></i> </a>

                                    <!-- BEGIN: Topbar Toggler -->
                                </div>
                            </div>
                        </div>
                        <!-- END: Brand -->

                        <div class="m-stack__item m-stack__item--fluid m-header-head" id="m_header_nav">
                            <!-- BEGIN: Horizontal Menu -->
                            <button class="m-aside-header-menu-mobile-close  m-aside-header-menu-mobile-close--skin-dark " id="m_aside_header_menu_mobile_close_btn"> <i class="la la-close"></i> </button>
                            <div id="m_header_menu" class="m-header-menu m-aside-header-menu-mobile m-aside-header-menu-mobile--offcanvas  m-header-menu--skin-light m-header-menu--submenu-skin-light m-aside-header-menu-mobile--skin-dark m-aside-header-menu-mobile--submenu-skin-dark ">
                                <ul class="m-menu__nav  m-menu__nav--submenu-arrow ">

                                    <!-- START Broadcrumbs resource code come here -->
                                    <div class="m-subheader ">
                                        <div class="d-flex align-items-center">
                                            <div class="mr-auto">
                                                <h3 class="m-subheader__title m-subheader__title--separator">Search Criterion</h3>
                                                <ul class="m-subheader__breadcrumbs m-nav m-nav--inline">
                                                    <li class="m-nav__item m-nav__item--home"> <a href="#" class="m-nav__link m-nav__link--icon"> <i class="m-nav__link-icon la la-home"></i> </a> </li>
                                                    <li class="m-nav__separator"></li>
                                                    <li class="m-nav__item"> <a href="${contextPath}/welcome" class="m-nav__link"> <span class="m-nav__link-text">Search</span> </a> </li>
                                                    <li class="m-nav__separator">-</li>
                                                    <li class="m-nav__item"> <a href="${contextPath}/visualization/${usersession}" class="m-nav__link"> <span class="m-nav__link-text">Visulization View</span> </a> </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- ENDS Broadcrumbs resource code come here -->
                                </ul>
                            </div>
                            <!-- END: Horizontal Menu -->
                            <!-- BEGIN: Topbar -->
                            <div id="m_header_topbar" class="m-topbar  m-stack m-stack--ver m-stack--general m-stack--fluid">
                                <div class="m-stack__item m-topbar__nav-wrapper">
                                    <ul class="m-topbar__nav m-nav m-nav--inline">
                                    </ul>
                                </div>
                            </div>

                            <!-- END: Topbar -->
                        </div>
                    </div>
                </div>
            </header>
            <!-- ENDS: Main Header panel -->
            <!-- BEGIN: Header -->
            <header id="m_header" class="m-grid__item m-header" m-minimize-offset="200" m-minimize-mobile-offset="200">
                <div class="m-container m-container--fluid m-container--full-height">
                    <div class="m-stack m-stack--ver m-stack--desktop">
                        <!-- BEGIN: Brand -->
                        <span><a href="https://www.gostardb.com/gostar/"><img src="${contextPath}/assets/images/logos/GOSTARNext_Logo_main_04-A.png"  height="80" alt="GOSTARNext"></a></span>
                        <!-- END: Brand -->
                        <div class="m-stack__item m-stack__item--fluid m-header-head" id="" style="background-color:#485461" >
                            <!-- BEGIN: Horizontal Menu -->
                            <button class="m-aside-header-menu-mobile-close  m-aside-header-menu-mobile-close--skin-dark " id=""> <i class="la la-close"></i> </button>
                            <div id="" class="m-header-menu m-aside-header-menu-mobile m-aside-header-menu-mobile--offcanvas m-header-menu--skin-light m-header-menu--submenu-skin-light m-aside-header-menu-mobile--skin-dark m-aside-header-menu-mobile--submenu-skin-dark "> </div>
                            <!-- END: Horizontal Menu -->

                            <!-- BEGIN: Topbar -->
                            <div id="" class="m-topbar  m-stack m-stack--ver m-stack--general m-stack--fluid">
                                <div class="m-stack__item m-topbar__nav-wrapper">
                                    <ul class="m-topbar__nav m-nav m-nav--inline">
                                        <li class="m-nav__item m-dropdown m-dropdown--large m-dropdown--arrow m-dropdown--align-center m-dropdown--mobile-full-width m-dropdown--skin-light	m-list-search m-list-search--skin-light" m-dropdown-toggle="click" id="m_quicksearch" m-quicksearch-mode="dropdown"
                                            m-dropdown-persistent="1"> <a href="#" class="m-nav__link m-dropdown__toggle"> <span class="m-nav__link-icon"> <i class="flaticon-search-1"></i> </span> </a>
                                            <div class="m-dropdown__wrapper"> <span class="m-dropdown__arrow m-dropdown__arrow--center"></span>
                                                <div class="m-dropdown__inner ">
                                                    <div class="m-dropdown__header">
                                                        <form class="m-list-search__form">
                                                            <div class="m-list-search__form-wrapper"> <span class="m-list-search__form-input-wrapper">
                                        <input id="m_quicksearch_input" autocomplete="off" type="text" name="q" class="m-list-search__form-input" value="" placeholder="Search...">
                                        </span> <span class="m-list-search__form-icon-close" id="m_quicksearch_close"> <i class="la la-remove"></i> </span> </div>
                                                        </form>
                                                    </div>
                                                    <div class="m-dropdown__body">
                                                        <div class="m-dropdown__scrollable m-scrollable" data-scrollable="true" data-height="300" data-mobile-height="200">
                                                            <div class="m-dropdown__content"> </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="m-nav__item m-topbar__user-profile m-topbar__user-profile--img  m-dropdown m-dropdown--medium m-dropdown--arrow m-dropdown--header-bg-fill m-dropdown--align-right m-dropdown--mobile-full-width m-dropdown--skin-light" m-dropdown-toggle="click"> <a href="#" class="m-nav__link m-dropdown__toggle"> <span class="m-topbar__userpic"><i class="flaticon-user"></i> </span> <span class="m-topbar__username m--hide">Nick</span> </a>
                                            <div class="m-dropdown__wrapper"> <span class="m-dropdown__arrow m-dropdown__arrow--right m-dropdown__arrow--adjust"></span>
                                                <div class="m-dropdown__inner">
                                                    <div class="m-dropdown__header m--align-center" style="background: url(../../../assets/app/media/img/misc/user_profile_bg.jpg); background-size: cover;">
                                                        <div class="m-card-user m-card-user--skin-dark">
                                                            <div class="m-card-user__pic"> <img src="../../../assets/app/media/img/users/user4.jpg" class="m--img-rounded m--marginless" alt="" /> </div>
                                                            <div class="m-card-user__details"> <span class="m-card-user__name m--font-weight-500">${username}</span> <a href="" class="m-card-user__email m--font-weight-300 m-link">user.name@gostar.com</a> </div>
                                                        </div>
                                                    </div>
                                                    <div class="m-dropdown__body">
                                                        <div class="m-dropdown__content">
                                                            <ul class="m-nav m-nav--skin-light">
                                                                <li class="m-nav__section m--hide"> <span class="m-nav__section-text">Section</span> </li>
                                                                <li class="m-nav__item"> <a href="../../../header/profile.html" class="m-nav__link"> <i class="m-nav__link-icon flaticon-profile-1"></i> <span class="m-nav__link-title"> <span class="m-nav__link-wrap"> <span class="m-nav__link-text">My Profile</span> <span class="m-nav__link-badge"> <span class="m-badge m-badge--success">2</span> </span> </span> </span> </a> </li>
                                                                <li class="m-nav__item"> <a href="../../../header/profile.html" class="m-nav__link"> <i class="m-nav__link-icon flaticon-share"></i> <span class="m-nav__link-text">Activity</span> </a> </li>
                                                                <li class="m-nav__item"> <a href="../../../header/profile.html" class="m-nav__link"> <i class="m-nav__link-icon flaticon-chat-1"></i> <span class="m-nav__link-text">Messages</span> </a> </li>
                                                                <li class="m-nav__separator m-nav__separator--fit"> </li>
                                                                <li class="m-nav__item"> <a href="../../../header/profile.html" class="m-nav__link"> <i class="m-nav__link-icon flaticon-info"></i> <span class="m-nav__link-text">FAQ</span> </a> </li>
                                                                <li class="m-nav__item"> <a href="../../../header/profile.html" class="m-nav__link"> <i class="m-nav__link-icon flaticon-lifebuoy"></i> <span class="m-nav__link-text">Support</span> </a> </li>
                                                                <li class="m-nav__separator m-nav__separator--fit"> </li>
                                                                <li class="m-nav__item"> <a href="javascript:void(0)" onclick="javascript:document.logoutform.submit()" class="btn m-btn--pill    btn-secondary m-btn m-btn--custom m-btn--label-brand m-btn--bolder">Logout</a> </li>
                                                                <form id="logoutform" name="logoutform" action="${contextPath}/appLogout" method="post">
                                                                    <input type="hidden" name="currentSession" value="${usersession}"/>
                                                                </form>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                        <li id="m_quick_sidebar_toggle" class="m-nav__item"> <a href="#" class="m-nav__link m-dropdown__toggle"> <span class="m-nav__link-icon"> <i class="flaticon-grid-menu"></i> </span> </a> </li>
                                    </ul>
                                </div>
                            </div>

                            <!-- END: Topbar -->
                        </div>
                    </div>
                </div>
            </header>
            <!-- END: Header -->

            <!-- begin::Body -->
            <div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-body">
                <!-- BEGIN: Left Aside -->
                <button class="m-aside-left-close  m-aside-left-close--skin-dark " id="m_aside_left_close_btn"> <i class="la la-close"></i> </button>
                <div id="m_aside_left" class="m-grid__item	m-aside-left  m-aside-left--skin-dark ">

                    <!-- BEGIN: Aside Menu -->
                    <div id="m_ver_menu" class="m-aside-menu  m-aside-menu--skin-dark m-aside-menu--submenu-skin-dark " m-menu-vertical="1" m-menu-scrollable="1" m-menu-dropdown-timeout="500" style="position: relative;">
                        <ul class="m-menu__nav  m-menu__nav--dropdown-submenu-arrow">
                            <li class="m-menu__section ">
                            <li class="m-menu__item " aria-haspopup="true"> <a href="#" class="m-menu__link "><i class="m-menu__link-icon flaticon-dashboard"></i> <span class="m-menu__link-title"> <span class="m-menu__link-wrap"> <span class="m-menu__link-text">Dashboard</span> <span class="m-menu__link-badge"> <span class="m-badge m-badge--danger">2</span> </span> </span> </span> </a> </li>
                            <li class="m-menu__item  m-menu__item--submenu"  aria-haspopup="true" m-menu-submenu-toggle="hover"> <a href="javascript:;" @click="searchViewClick" class="m-menu__link m-menu__toggle" > <i class="m-menu__link-icon flaticon-search-magnifier-interface-symbol"></i> <span class="m-menu__link-text">Search</span> </a>
                            </li>
                            <li class="m-menu__item  m-menu__item--submenu  m-menu_active-bg" aria-haspopup="true" m-menu-submenu-toggle="hover"> <a href="javascript:;" class="m-menu__link m-menu__toggle m-menu_active-bg-back"> <i class="m-menu__link-icon m-menu__link-icon-active-icon flaticon-pie-chart"  style="color:#000000"></i> <span class="m-menu__link-text"  style="color:#1d1b1b">Visualization</span> </a> </li>
                            <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" m-menu-submenu-toggle="hover"> <a href="javascript:;"  @click="tabularViewClick"  target="_blank" class="m-menu__link m-menu__toggle"> <i class="m-menu__link-icon flaticon-interface-11"></i> <span class="m-menu__link-text">Tab Tabular</span> </a> </li>
                            <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" m-menu-submenu-toggle="hover"> <a href="javascript:;" class="m-menu__link m-menu__toggle"> <i class="m-menu__link-icon flaticon-layers"></i> <span class="m-menu__link-text">Leaflet View</span> </a> </li>
                            <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" m-menu-submenu-toggle="hover"> <a href="javascript:;" class="m-menu__link m-menu__toggle"> <i class="m-menu__link-icon flaticon-analytics"></i> <span class="m-menu__link-text">Analytics</span> </a> </li>
                            <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" m-menu-submenu-toggle="hover"> <a href="javascript:;" class="m-menu__link m-menu__toggle"> <i class="m-menu__link-icon flaticon-time-1"></i> <span class="m-menu__link-text">Session Summary</span> </a> </li>
                            </li>
                        </ul>
                    </div>

                    <!-- END: Aside Menu -->
                </div>

                <!-- END: Left Aside -->
                <div class="m-grid__item m-grid__item--fluid m-wrapper">
                    <!-- BEGIN: Subheader -->
                    <div class="m-subheader "> </div>
                    <!-- END: Subheader -->
                    <div class="m-content">
                        <!--begin::Portlet-->
                        <div class="m-portlet">
                            <div class="m-portlet__body">
                                <!-- STARTS Cards container block -->
                                <div class="row">
                                    <div class="m-stack m-stack--ver m-stack--general m-stack--demo">
                                        <div class="m-stack__item ">
                                            <!-- STARTS Cards container block -->
                                            <div class="m-widget17__item">
                                                <div class="row  align-items-center">
                                                    <div class="col">
                                                        <div id="" class="m-widget14__chart crd-icn-algn zoomIn">
                                                            <div class="m-widget14__stat"><img src="${contextPath}/assets/images/icons/Icon_Referances.png" width="80" alt="Referances"><!--<i class="flaticon-profile m--font-info crd-icn-txt"></i>--></div>
                                                        </div>
                                                    </div>
                                                    <div class="col">
                                                        <div class="m-widget14__legends">
                                                            <div class="m-widget14__legend"> <span class="m-widget14__legend-bullet m--bg-accent"></span> <span class="m-widget14__legend-text crd-icn-hdr">References</span> </div>
                                                            <div class="m-widget14__legend"> <span class="m-widget14__legend-bullet m--bg-warning"></span><span class="m-widget14__legend-text crd-icn-count crd-icn-col-blu timer count-title count-number" :data-to="getCounts.refIdsCount" data-speed="2000">{{getCounts.refIdsCount}}</span></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- ENDS Cards container block -->
                                        </div>
                                        <div class="m-stack__item">
                                            <!-- STARTS Cards container block -->
                                            <div class="m-widget17__item">
                                                <div class="row  align-items-center">
                                                    <div class="col" >
                                                        <div class="m-widget14__chart crd-icn-algn zoomIn">
                                                            <div class="m-widget14__stat"><img src="${contextPath}/assets/images/icons/Icon_Unique_Structures.png" width="85" alt="Unique Structure"></div>
                                                        </div>
                                                    </div>
                                                    <div class="col">
                                                        <div class="m-widget14__legends">
                                                            <div class="m-widget14__legend"> <span class="m-widget14__legend-bullet m--bg-accent"></span> <span class="m-widget14__legend-text crd-icn-hdr">Structures</span> </div>
                                                            <div class="m-widget14__legend"> <span class="m-widget14__legend-bullet m--bg-warning"></span> <span class="m-widget14__legend-text crd-icn-count crd-icn-col-blu timer count-title count-number" :data-to="getCounts.strIdsCount" data-speed="2000">{{getCounts.strIdsCount}}</span> </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- ENDS Cards container block -->
                                        </div>
                                        <!-- STARTS Cards container block -->
                                        <div class="m-stack__item">
                                            <div class="m-widget17__item">
                                                <div class="row  align-items-center">
                                                    <div class="col" >
                                                        <div class="m-widget14__chart crd-icn-algn zoomIn">
                                                            <div class="m-widget14__stat"><img src="${contextPath}/assets/images/icons/Icon_Data_Records.png" width="80" alt="Records"></div>
                                                        </div>
                                                    </div>
                                                    <div class="col">
                                                        <div class="m-widget14__legends">
                                                            <div class="m-widget14__legend"> <span class="m-widget14__legend-bullet m--bg-accent"></span> <span class="m-widget14__legend-text crd-icn-hdr">Records</span> </div>
                                                            <div class="m-widget14__legend"> <span class="m-widget14__legend-bullet m--bg-warning"></span> <span class="m-widget14__legend-text crd-icn-count crd-icn-col-blu timer count-title count-number" :data-to="getCounts.gvkIdsCount" data-speed="2000">{{getCounts.gvkIdsCount}}</span> 
                                                                <!-- <span class="m-widget14__legend-text crd-icn-count crd-icn-col-blu">1564505</span> -->
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- ENDS Cards container block -->
                                        <!-- STARTS Cards container block -->
                                        <div class="m-stack__item">
                                            <div class="m-widget17__item">
                                                <div class="row  align-items-center">
                                                    <div class="col" >
                                                        <div class="m-widget14__chart crd-icn-algn zoomIn">
                                                            <div class="m-widget14__stat"><img src="${contextPath}/assets/images/icons/Icon_Activities_01.png" width="80" alt="Activites"></div>
                                                        </div>
                                                    </div>
                                                    <div class="col">
                                                        <div class="m-widget14__legends">
                                                            <div class="m-widget14__legend"> <span class="m-widget14__legend-bullet m--bg-accent"></span> <span class="m-widget14__legend-text crd-icn-hdr">Activities</span> </div>
                                                            <div class="m-widget14__legend"> <span class="m-widget14__legend-bullet m--bg-warning"></span> <span class="m-widget14__legend-text crd-icn-count crd-icn-col-blu timer count-title count-number" :data-to="getCounts.actIdsCount" data-speed="2000">{{getCounts.actIdsCount}}</span>
                                                                <!-- <span class="m-widget14__legend-text crd-icn-count crd-icn-col-blu">1480265</span> -->
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- ENDS Cards container block -->
                                        <!-- STARTS Cards container block -->
                                        <div class="m-stack__item" style="border-right:1px #ffffff">
                                            <div class="m-widget17__item">
                                                <div class="row  align-items-center">
                                                    <div class="col">
                                                        <div class="m-widget14__legends">
                                                            <!-- STARTS Two splitted blocks container -->
                                                            <div class="m-section m-section--last">
                                                                <div class="m-section__content">
                                                                    <!--begin::Preview-->
                                                                    <div class="" data-code-preview="true" data-code-html="true" data-code-js="false">
                                                                        <div class="m-demo__preview">
                                                                            <ul class="m-nav m-nav--inline">
                                                                                <li class="m-nav__item"> <span class="m-nav__link"> <i class="m-nav__link-icon flaticon-share"></i> <span class="m-nav__link-text">Clinical Compound</span><span class="num-vl-blu">1,304</span> </span> </li>
                                                                            </ul>
                                                                        </div>
                                                                    </div>
                                                                    <!--end::Preview-->
                                                                    <div class="hr-line"></div>
                                                                    <!--begin::Preview-->
                                                                    <div class="" data-code-preview="true" data-code-html="true" data-code-js="false">
                                                                        <div class="m-demo__preview">
                                                                            <ul class="m-nav m-nav--inline">
                                                                                <li class="m-nav__item"> <span class="m-nav__link"> <i class="m-nav__link-icon flaticon-share"></i> <span class="m-nav__link-text">Drug</span><span class="num-vl-blu">805</span> </span> </li>
                                                                            </ul>
                                                                        </div>
                                                                    </div>
                                                                    <!--end::Preview-->
                                                                </div>
                                                            </div>
                                                            <!-- ENDS Two splitted blocks container -->
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- ENDS Cards container block -->
                                    </div>
                                </div>
                                <!-- ENDS Cards container block -->
                            </div>
                        </div>
                        <!--end::Portlet-->
                        <!--begin::Portlet-->
                        <div class="m-portlet">
                            <div class="m-portlet__head">
                                <div class="m-portlet__head-caption">
                                    <div class="m-portlet__head-title"> <span class="m-portlet__head-icon"> <i class="la la-search"></i> </span>
                                        <h3 class="m-portlet__head-text"> Visulization View </h3>
                                    </div>
                                </div>
                            </div>
                            <div class="m-portlet__body ">
                                <!-- STARTS Inside Container block for Visulization view -->

                                <!-- Container block start from here -->
                                <div class="row">
                                    <div class="col-6">
                                        <div class="m-alert m-alert--icon m-alert--icon-solid m-alert--outline alert alert-brand alert-dismissible fade show heght-70px" role="alert">
                                            <div class="m-alert__icon"> <i class="flaticon-interface-3"></i> <span></span> </div>
                                            <div class="m-alert__text">
                                                <!-- <strong>Well done!</strong> You successfully read this message. -->
                                                <label class="m-checkbox m-checkbox--state-primary txt-hdr" >
                                                    <input type="checkbox">
                                                    Progressive Filter <span></span> </label>
                                            </div>
                                            <div class="m-alert__text flot-rt txt-hdr">
                                                <div><span><strong>You have selected: </strong> </span><span class="sp-bthsd-10">Item 1 X</span><span class="sp-bthsd-10">Item 2 X</span></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <div class="m-alert m-alert--icon m-alert--outline alert alert-primary heght-70px" role="alert">
                                            <div class="m-alert__icon"> <i class="fa flaticon-list-2"></i> </div>
                                            <div class="m-alert__text txt-hdr"> <strong>Total Structure:</strong> <span>4672</span> </div>
                                            <div class="m-alert__actions"> <a href="javascript:void(0)" :disable="disableAllButtons" @click="tabularViewClick" 
                                                class="btn btn-excelra btn-sm m-btn  m-btn m-btn--icon"> <span> <i class="flaticon-interface-6"></i> <span>Tabuler View</span> 
                                            </span> </a> <a href="javascript:void(0)" :disable="disableAllButtons" @click="analyzerViewClick" class="btn btn-excelra btn-sm m-btn  m-btn m-btn--icon"> <span> <i class="flaticon-analytics"></i> <span>Analyzer</span> </span> </a> </div>
                                        </div>
                                    </div>
                                </div>
                                <!--begin::Portlet-->
                                <div id="modal-backdrop"></div>
                                <div v-if="showNoRelevantDataMsg" class="charts-wrapper row text-center text-capitalize">
                                    <h5 class="col-12">{{showNoRelevantDataMsg}}</h5>
                                    <div class="col-12">
                                        <q-btn flat to="/search" label="Back To Search"/>
                                        <q-btn flat @click="tabularViewClick" label="Go to Tabular View"/>
                                    </div>
                                </div>
                                <div class="" v-else>
                                    <!-- <div class="heght-10px"></div> -->
                                    <!-- STARTS Dragable (Movable) window pallets -->
                                    <!-- STARTS Activities Data and Referance Distrubution panel blocks -->
                                    <div class="row">
                                        <div class="col-10">
                                            <div class="row">
                                                <div :class="[index === 0 && !chart.hideChart ? 'col-4' : 'col-4', {'hidden':chart.hideChart}]"
                                                     v-for="(chart,index) in $store.state.charts.finalVisualizationPayload"
                                                     :key="index">
                                                    <!-- START Activities Data Block -->
                                                    <!-- START Activities Data Block -->
                                                    <div :id="index" class="m-portlet m-portlet--accent m-portlet--head-solid-bg m-portlet--head-sm" m-portlet="true">
                                                        <div class="m-portlet__head" :class="{'chart-header-margin': pieData[chart.key]['isExpanded']}">
                                                            <div class="m-portlet__head-caption">
                                                                <div class="m-portlet__head-title"> <span class="m-portlet__head-icon"> <i class="flaticon-graphic"></i> </span>
                                                                    <h3 class="m-portlet__head-text"> {{pieData[chart.key]['name']}} </h3>
                                                                </div>
                                                            </div>
                                                            <div class="m-portlet__head-tools">
                                                                <ul class="m-portlet__nav">
                                                                    <li class="m-portlet__nav-item"> <a href="javascript:void(0)" m-portlet-tool="toggle" class="m-portlet__nav-link m-portlet__nav-link--icon"> <i class="la la-angle-down"></i> </a> </li>
                                                                    <li class="m-portlet__nav-item"> <a href="javascript:void(0)" :disable="disableAllButtons" @click="resetChartDataToInitial()" m-portlet-tool="reload" class="m-portlet__nav-link m-portlet__nav-link--icon"> <i class="la la-refresh"></i> </a> </li>
                                                                    <li class="m-portlet__nav-item"> <a href="javascript:void(0)" v-if="pieData[chart.key]['isExpanded']" :disabled="pieData[chart.key]['loading']" @click="toggleFullScreen(chart.key,index,false)" class="m-portlet__nav-link m-portlet__nav-link--icon"> <i class="la la-compress"></i> </a> </li>
                                                                    <li class="m-portlet__nav-item"> <a href="javascript:void(0)" v-if="!pieData[chart.key]['isExpanded']" :disabled="pieData[chart.key]['loading']" flat round icon="fa fa-expand" @click="toggleFullScreen(chart.key,index,true)" class="m-portlet__nav-link m-portlet__nav-link--icon"> <i class="la la-expand"></i> </a> </li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <!-- STARTS FlotChart resource code -->
                                                        <div class="m-portlet__body">
                                                            <!--  <div id="m_flotcharts_5" style="height:220px;"> </div> -->
                                                            <!-- STARTS D3 Visualization chart 01 -->
                                                            <div class="items-center no-wrap box row justify-center">
                                                                <div class="row items-center justify-center" v-if="pieData[chart.key]['loading'] == false && pieData[chart.key]['data']['length'] == 0">
                                                                    <div class="col text-center text-capitalize">data not found</div>
                                                                </div>
                                                                <q-inner-loading :showing="pieData[chart.key]['loading']">
                                                                    <q-spinner-audio size="50px" color="primary"></q-spinner-audio>
                                                                    <div>chart loading...</div>
                                                                </q-inner-loading>
                                                                <charts-visualization
                                                                        ref="chart"
                                                                        :class="{'gs-chart-expand': pieData[chart.key]['isExpanded']}"
                                                                        :chart-type="pieData[chart.key]['chartType']"
                                                                        v-if="pieData[chart.key]['data'].length && !pieData[chart.key]['loading']"
                                                                        :chart-data="pieData[chart.key]['data']"
                                                                        :final-visualization-payload="chart.data"
                                                                        @final-payload="chart.data = $event"
                                                                        :chart-key="chart.key"
                                                                        @chart-clicked="chartClickedEvent($event)"
                                                                        @hide-current-chart="hideChart($event)"
                                                                        @resize-chart-if-expanded="resizeChart($event, pieData[chart.key]['isExpanded'])"
                                                                ></charts-visualization>
                                                            </div>
                                                            <!-- ENDS FlotChart resources code -->
                                                            <div class="m-portlet__foot heght-70px ">
                                                                <div class="m-btn-group btn-group btn-group-sm m-btn-group bg-col-wht" role="group" aria-label="Small button group">
                                                                    <button type="button" class="btn btn-outline-info">
                                                                        <input type="checkbox" name="[0][1]" value="1" onselect="select">
                                                                        And
                                                                    </button>
                                                                    <button type="button" class="btn btn-outline-info">
                                                                        <input type="checkbox" name="[0][1]" value="1">
                                                                        Not
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- ENDS D3 Visualization chart 01 -->

                                                    <!-- ENDS D3 Visualization chart 01 -->
                                                </div>

                                            </div>
                                        </div>
                                        <div class="col-2">
                                            <!--<div class="m-widget1"> -->
                                            <div class="row">
                                                <!-- STARTS Right side pallet block resources -->
                                                <div class="m-widget29 rgt-sd-spc">
                                                    <div class="m-widget_content m-widget29-bg-col">
                                                        <h3 class="m-widget_content-title">Target</h3>
                                                        <div class="m-widget_content-items">
                                                            <div class="m-widget_content-item"> <span class="m--font-accent">4,345</span> </div>
                                                            <div>
                                                    <span>
                                                        <img class="graph-insd-pr" src="${contextPath}/assets/images/icons/rounded_circle_view_01.png" width="100" alt="Circle">
                                                        </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="m-widget_content m-widget29-bg-col">
                                                        <h3 class="m-widget_content-title">Clinical</h3>
                                                        <div class="m-widget_content-items">
                                                            <div class="m-widget_content-item"> <span class="m--font-accent">1,557</span> </div>
                                                            <div>
                                                    <span>
                                                        <img class="graph-insd-pr" src="${contextPath}/assets/images/icons/rounded_circle_view_02.png" width="100" alt="Circle">
                                                        </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="m-widget_content m-widget29-bg-col">
                                                        <h3 class="m-widget_content-title">SAR</h3>
                                                        <div class="m-widget_content-items" >
                                                            <div class="m-widget_content-item"> <span class="m--font-accent">1,962</span> </div>
                                                            <div>
                                                    <span>
                                                        <img class="graph-insd-pr" src="${contextPath}/assets/images/icons/rounded_circle_view_03.png" width="100" alt="Circle">
                                                        </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- ENDS Right side pallet block resources -->

                                            </div>
                                        </div>
                                    </div>
                                    <!-- ENDS Activities Data and Referance Distrubution panel blocks -->

                                    <!-- ENDS Dragable (Movable) window pallets -->

                                </div>
                                <!--end::Portlet-->

                                <!-- ENDS Inside Container block fpr Visualization -->
                            </div>
                            <!-- <div class="m-portlet__foot">
                                <div class="row align-items-center">
                                    <div class="col-lg-6 m--valign-middle"> Submit selected values: </div> -->
                                   <!--  <div class="col-lg-6 m--align-right">
                                        <button type="submit" class="btn btn-success">Submit</button>
                                    </div>
                                </div>
                            </div> -->
                        </div>
                        <!--end::Portlet-->
                    </div>
                </div>
            </div>
            <!-- end:: Body -->

            <!-- begin::Footer -->
            <footer class="m-grid__item m-footer ">
                <div class="m-container m-container--fluid m-container--full-height m-page__container">
                    <div class="m-stack m-stack--flex-tablet-and-mobile m-stack--ver m-stack--desktop">
                        <div class="m-stack__item m-stack__item--left m-stack__item--middle m-stack__item--last"> <span class="m-footer__copyright"> 2019 &copy; <a href="https://www.excelra.com/" class="m-link" style="color:#ffffff">EXCELRA Knowledge Solutions Pvt Ltd</a> </span> </div>
                        <div class="m-stack__item m-stack__item--right m-stack__item--middle m-stack__item--first">
                            <ul class="m-footer__nav m-nav m-nav--inline m--pull-right">
                                <li class="m-nav__item"> <a href="#" class="m-nav__link"> <span class="m-nav__link-text" style="color:#ffffff">About</span> </a> </li>
                                <li class="m-nav__item"> <a href="#" class="m-nav__link"> <span class="m-nav__link-text txt-wht" style="color:#ffffff">Privacy</span> </a> </li>
                                <li class="m-nav__item"> <a href="#" class="m-nav__link"> <span class="m-nav__link-text txt-wht" style="color:#ffffff">T&C</span> </a> </li>
                                <li class="m-nav__item"> <a href="#" class="m-nav__link"> <span class="m-nav__link-text txt-wht" style="color:#ffffff">Purchase</span> </a> </li>
                                <li class="m-nav__item m-nav__item " > <a href="#" class="m-nav__link" data-toggle="m-tooltip " title="Support Center" data-placement="left"> <i class="m-nav__link-icon flaticon-info m--icon-font-size-lg3" style="color:#ffffff"></i> </a> </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </footer>

            <!-- end::Footer -->
        </div>
        <!-- end:: Page -->
        <!-- begin::Quick Sidebar -->
        <div id="m_quick_sidebar" class="m-quick-sidebar m-quick-sidebar--tabbed m-quick-sidebar--skin-light">
            <div class="m-quick-sidebar__content m--hide"> <span id="m_quick_sidebar_close" class="m-quick-sidebar__close"> <i class="la la-close"></i> </span>
                <ul id="m_quick_sidebar_tabs" class="nav nav-tabs m-tabs m-tabs-line m-tabs-line--brand" role="tablist">
                    <li class="nav-item m-tabs__item"> <a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_quick_sidebar_tabs_settings" role="tab">Settings</a> </li>
                </ul>
                <div class="tab-content">
                    <!-- START Right side settings control resource -->

                    <!-- ENDS Right side settings control resource -->
                </div>
            </div>
        </div>
        <!-- end::Quick Sidebar -->
        <!-- begin::Quick Nav -->
        <ul class="m-nav-sticky" style="margin-top: 30px;">
            <!-- <li class="m-nav-sticky__item" data-toggle="m-tooltip" title="Visualization" data-placement="left"> <a href="javascript:void(0)" target="_blank"> <i class="flaticon-pie-chart"></i> </a> </li> -->
            <li class="m-nav-sticky__item" data-toggle="m-tooltip" title="Tab Tabular" data-placement="left"> <a href="javascript:void(0)" 
                                                                                                                 @click="tabularViewClick"  target="_blank"> <i class="flaticon-tabs"></i> </a> </li>
            <li class="m-nav-sticky__item" data-toggle="m-tooltip" title="Export Data" data-placement="left"> <a href="javascript:void(0)" @click="exportFile" target="_blank"> <i class="flaticon-folder-2"></i> </a> </li>
        </ul>
        <!-- begin::Quick Nav -->
    </div>
    <!-- Modal popup for user access -->
    <b-modal id="modal-xl" ref="userAccessModal" :hide-footer="true" size="xl">
        <template v-slot:modal-header>
            <h5>Alert</h5>
            <div>
                <i class="fa fa-times" style="cursor:pointer" @click="$refs.userAccessModal.hide()"></i>
            </div>
        </template>
        Access denied! Please contact support team.
    </b-modal>
</div>
</body>
<!-- end::Body -->
<!--begin::Base Scripts -->
<!-- Multiple selection form elements -->
<!-- <script src="${contextPath}/assets/scripts/select2.js" type="text/javascript"></script>  -->

<!-- START Cards Number count Scripts -->
<script src="${contextPath}/assets/scripts/cards_numbers_count_animate.js"></script>
<!-- ENDS Cards Number Count Scripts -->
<!--end::Base Scripts -->

<!-- No UI Slider script resource file -->
<!-- <script src="${contextPath}/assets/scripts/forms/widgets/nouislider.js" type="text/javascript"></script> -->

<!-- No UI Slider script resource file -->
<<!-- script src="${contextPath}/assets/scripts/forms/widgets/nouislider.js" type="text/javascript"></script>  -->

<!-- Model window effects scripts -->
<script src="${contextPath}/assets/scripts/model_classie.js" type="text/javascript"></script>
<script src="${contextPath}/assets/scripts/modal_effects.js" type="text/javascript"></script>

<!-- Script for Drop down values -->
<div id="m_scroll_top" class="m-scroll-top"> <i class="la la-arrow-up"></i></div>
<!-- end::Scroll Top -->

<!--begin::Page Vendors Scripts -->
<script src="${contextPath}/assets/vendors/custom/jquery-ui/jquery-ui.bundle.js" type="text/javascript"></script>

<!--begin::Page Resources -->
<!--<script src="${contextPath}/assets/scripts/portlets/draggable.js" type="text/javascript"></script> -->
<!--<script src="${contextPath}/assets/scripts/portlets/tools.js" type="text/javascript"></script> -->

<!--begin::Page Snippets -->
<script src="${contextPath}/assets/scripts/dashboard.js" type="text/javascript"></script>
<!-- <script src="${contextPath}/assets/scripts/fullcalendar.bundle.js" type="text/javascript"></script> -->
<!--end::Page Resources -->

<!--begin::Page Vendors Scripts -->
<script src="${contextPath}/assets/vendors/custom/flot/flot.bundle.js" type="text/javascript"></script>
<!--end::Page Vendors Scripts -->

<!--begin::Page Resources -->
<script src="${contextPath}/assets/scripts/charts/flotcharts.js" type="text/javascript"></script>
<!--end::Page Resources -->

<!-- D3 Visualization Scripts resources -->
<!--
<script src="${contextPath}/assets/scripts/d3_resources/jquery.min.1.7.1.js"></script>
<script src="${contextPath}/assets/scripts/d3_resources/vue.min.js"></script>
<script src="${contextPath}/assets/scripts/d3_resources/integrateIndex.js"></script>
<script src="${contextPath}/assets/scripts/d3_resources/d3.v3.min.js"></script>
-->

<!-- Exisiting JS files -->
<script src="${contextPath}/assets/scripts/vendors.bundle.js" type="text/javascript"></script>
<!--begin::bundle scripts -->
<script src="${contextPath}/assets/scripts/scripts.bundle.js" type="text/javascript"></script>
<!--end::Base Scripts -->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

<script>
    /* begin::Page Loader */
    $(window).on('load', function () {
        $('body').removeClass('m-page--loading');
    });

    /* configurations */
    window.config = {
        domain: '',
        userSessionId: "${usersession}",
        contextPath: "${contextPath}",
        apiContextPath: "",
        userRoles: "${userroles}"
    };
    window.config.apiUrl = window.config.domain + "${contextPath}/";

    axios.defaults.headers.common['userSessionId'] = window.config.userSessionId;
    axios.defaults.headers.common['X-Requested-With'] = "XMLHttpRequest";
    axios.defaults.headers.common['Content-Type'] = 'application/json';

    axios.interceptors.response.use(
        response => {
        return response;
    },
    function(error) {
        let errorStatusCodes = [401,403];
        if(errorStatusCodes.indexOf(error.response.status) > -1) {
            window.location.href = 'http://localhost:9099/gostarjdbcservice/login'
        }
        return Promise.reject(error.response);
    }
    );

</script>
<script src="${contextPath}/resources/js/libraries/polyfills/quasar.ie.polyfills.umd.min.js"></script>
<script src="${contextPath}/resources/js/libraries/quasar.umd.min.js"></script>
<script src="${contextPath}/resources/js/libraries/fontawesome-v5.umd.min.js"></script>
<script src="${contextPath}/resources/js/libraries/vuex.min.js"></script>

<script src="${contextPath}/resources/store/charts/actions.js"></script>
<script src="${contextPath}/resources/store/charts/getters.js"></script>
<script src="${contextPath}/resources/store/charts/mutations.js"></script>
<script src="${contextPath}/resources/store/charts/state.js"></script>
<script src="${contextPath}/resources/store/user/state.js"></script>
<script src="${contextPath}/resources/components/reusable/theme.js"></script>

<script src="${contextPath}/resources/components/reusable/charts-visualization.js"></script>
<script src="${contextPath}/resources/js/visualization.js"></script>
<script src="${contextPath}/resources/components/reusable/loader.js"></script>
<!-- End of existing JS files -->
</html>