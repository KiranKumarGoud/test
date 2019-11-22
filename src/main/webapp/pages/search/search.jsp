<%--
  Created by IntelliJ IDEA.
  User: venkateswarlu.s
  Date: 10-05-2019
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<!-- begin::Head -->
<head>
<meta charset="utf-8" />
<title>GOSTARNext | Search</title>
<meta name="description" content="Latest updates and statistic charts">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">

<!--begin::Web font --> 
<!-- <script src="https://ajax.googleapis.com/ajax/libs/webfont/1.6.16/webfont.js"></script>  
-->
<script src="assets/scripts/fonts/webfont.js"></script>
<script>
			WebFont.load({
				google: {
					"families": ["Poppins:300,400,500,600,700", "Roboto:300,400,500,600,700", "Asap+Condensed:500"]
				},
				active: function() {
					sessionStorage.fonts = true;
				}
			});
</script> 

<!-- STARTS Switch toggle style --> 

<!-- ENDS Switch toggle style --> 
<!--end::Web font --> 

<!--begin::Base Styles -->
<link href="assets/vendors/base/vendors.bundle.css" rel="stylesheet" type="text/css" />
<link href="assets/scripts/base/style.bundle.css" rel="stylesheet" type="text/css" />
    
<!-- Modal window animated effects Scripts -->
<!-- <link href="assets/styles/effects_onent.css" rel="stylesheet" type="text/css" /> -->
    
<!--end::Base Styles -->
<link rel="shortcut icon" href="assets/images/logos/favicon.ico" />
<!-- Do you need Fontawesome? -->
<link href="${contextPath}/resources/css/fontawesome/css/all.min.css" rel="stylesheet" />
<!-- Do you need all animations? -->
<link href="${contextPath}/resources/css/animate.min.css" rel="stylesheet" />
<!-- Page loader css -->
 <!-- <link href="${contextPath}/resources/css/page-loader/_css/Icomoon/style.css" rel="stylesheet" type="text/css" />
  <link href="${contextPath}/resources/css/page-loader/_css/main.css" rel="stylesheet" type="text/css" /> -->
  <!--
     Finally, add Quasar's CSS:
     Replace version below (1.0.0-beta.0) with your desired version of Quasar.
     Add ".rtl" for the RTL support (example: quasar.rtl.min.css).
     -->
     <!-- 
        <link href="${contextPath}/resources/css/app.css" rel="stylesheet" />
      -->
      <!-- Add this to <head> -->
         <!-- Load required Bootstrap and BootstrapVue CSS -->
   <link href="${contextPath}/resources/css/quasar.min.css" rel="stylesheet" type="text/css" />
  <link type="text/css" rel="stylesheet" href="http://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.min.css" />
  <!-- Marvin js css files-->
  <link type="text/css" rel="stylesheet" href="${contextPath}/marvinjs/css/doc.css" />
  <link type="text/css" rel="stylesheet" href="${contextPath}/marvinjs/js/lib/rainbow/github.css" />
  <!-- Load polyfills to support older browsers -->
  <script src="http://polyfill.io/v3/polyfill.min.js?features=es2015%2CMutationObserver"
    crossorigin="anonymous"></script>
  <!-- Load Vue followed by BootstrapVue -->
  <script src="http://unpkg.com/vue@latest/dist/vue.js"></script>
  <script src="http://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.js"></script>
  <link href="${contextPath}/resources/css/override.css" rel="stylesheet" />
</head>

<!-- end::Head -->
<!-- begin::Body -->
<body class="m-page--fluid m--skin- m-content--skin-light2 m-header--fixed m-header--fixed-mobile m-aside-left--enabled m-aside-left--skin-dark m-aside-left--fixed m-aside-left--offcanvas m-aside-left--minimize m-brand--minimize m-footer--push m-aside--offcanvas-default">
    <div id="q-app" v-cloak>
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
       <header class="m-grid__item  m-header " m-minimize-offset="200" m-minimize-mobile-offset="200" style="height:68px; background-color:#0099cc; top:0px; padding-top:60px">
          <div class="m-container m-container--fluid m-container--full-height">
             <div class="m-stack m-stack--ver m-stack--desktop">
                <!-- BEGIN: Brand -->
                <div class="m-stack__item m-brand  m-brand--skin-dark">
                   <div class="m-stack m-stack--ver m-stack--general">
                      <div class="m-stack__item m-stack__item--middle m-brand__logo"> <a href="../../../index.html" class="m-brand__logo-wrapper"> <!-- <img alt="" src="../../../assets/demo/default/media/img/logo/logo_default_dark.png" /> --> </a> </div>
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
                                     <li class="m-nav__item"> <a href="" class="m-nav__link"> <span class="m-nav__link-text">Search</span> </a> </li>
                                     <!-- <li class="m-nav__separator">-</li>
                                     <li class="m-nav__item"> <a href="" class="m-nav__link"> <span class="m-nav__link-text">Target Search</span> </a> </li> -->
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
       <header id="m_header" class="m-grid__item    m-header " m-minimize-offset="200" m-minimize-mobile-offset="200">
          <!-- <div style="height:80px; background-color: #485462"></div> -->
          <div class="m-container m-container--fluid m-container--full-height">
             <div class="m-stack m-stack--ver m-stack--desktop">
                <!-- BEGIN: Brand --> 
                <span><a href="https://www.gostardb.com/gostar/"><img src="assets/images/logos/GOSTARNext_Logo_main_04-A.png" width="200" alt="GOSTARNext"></a></span> 
                <!-- END: Brand -->
                <div class="m-stack__item m-stack__item--fluid m-header-head" id="" style="background-color:#485461" >
                   <!-- BEGIN: Horizontal Menu -->
                   <button class="m-aside-header-menu-mobile-close  m-aside-header-menu-mobile-close--skin-dark " id=""> <i class="la la-close"></i> </button>
                   <div id="" class="m-header-menu m-aside-header-menu-mobile m-aside-header-menu-mobile--offcanvas  m-header-menu--skin-light m-header-menu--submenu-skin-light m-aside-header-menu-mobile--skin-dark m-aside-header-menu-mobile--submenu-skin-dark "> </div>
                   <!-- END: Horizontal Menu --> 
                   <!-- BEGIN: Topbar -->
                   <div id="" class="m-topbar  m-stack m-stack--ver m-stack--general m-stack--fluid">
                      <div class="m-stack__item m-topbar__nav-wrapper">
                         <ul class="m-topbar__nav m-nav m-nav--inline">
                            <li class="m-nav__item m-dropdown m-dropdown--large m-dropdown--arrow m-dropdown--align-center m-dropdown--mobile-full-width m-dropdown--skin-light	m-list-search m-list-search--skin-light" m-dropdown-toggle="click" id="m_quicksearch" m-quicksearch-mode="dropdown"
                               m-dropdown-persistent="1">
                               <a href="#" class="m-nav__link m-dropdown__toggle"> <span class="m-nav__link-icon"> <i class="flaticon-search-1"></i> </span> </a>
                               <div class="m-dropdown__wrapper">
                                  <span class="m-dropdown__arrow m-dropdown__arrow--center"></span>
                                  <div class="m-dropdown__inner ">
                                     <div class="m-dropdown__header">
                                        <form class="m-list-search__form">
                                           <div class="m-list-search__form-wrapper"> <span class="m-list-search__form-input-wrapper">
                                              <input id="m_quicksearch_input" autocomplete="off" type="text" name="q" class="m-list-search__form-input" value="" placeholder="Search...">
                                              </span> <span class="m-list-search__form-icon-close" id="m_quicksearch_close"> <i class="la la-remove"></i> </span> 
                                           </div>
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
                            <li id="user-profile" m-dropdown-toggle="click" :class="['m-nav__item m-topbar__user-profile m-topbar__user-profile--img  m-dropdown m-dropdown--medium m-dropdown--arrow m-dropdown--header-bg-fill m-dropdown--align-right m-dropdown--mobile-full-width m-dropdown--skin-light', ($store.state.simpleSearch.showUserProfileMenu) ? 'm-dropdown--open' : '']">
                               <a href="#" class="m-nav__link m-dropdown__toggle"> <span class="m-topbar__userpic"><i class="flaticon-user"></i> </span> <span class="m-topbar__username m--hide">Nick</span> </a>
                               <div class="m-dropdown__wrapper">
                                  <span class="m-dropdown__arrow m-dropdown__arrow--right m-dropdown__arrow--adjust"></span>
                                  <div class="m-dropdown__inner">
                                     <div class="m-dropdown__header m--align-center" style="background: url(${contextPath}/app/media/img/misc/user_profile_bg.jpg); background-size: cover;">
                                        <div class="m-card-user m-card-user--skin-dark">
                                           <div class="m-card-user__pic">
                                              <img src="../../../assets/app/media/img/users/user4.jpg" class="m--img-rounded m--marginless" alt="" /> 
                                              <!--
                                                 <span class="m-type m-type--lg m--bg-danger"><span class="m--font-light">S<span><span>
                                                       --> 
                                           </div>
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
                                              <li class="m-nav__item"> <a href="javascript:void(0)" onclick="javascript:document.logoutform.submit()" class="btn m-btn--pill  btn-secondary m-btn m-btn--custom m-btn--label-brand m-btn--bolder" >Logout</a> </li>
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
                   <li class="m-menu__item  m-menu__item--submenu m-menu_active-bg"  aria-haspopup="true" m-menu-submenu-toggle="hover">
                      <a href="javascript:;" class="m-menu__link m-menu__toggle m-menu_active-bg-back" > <i class="m-menu__link-icon m-menu__link-icon-active-icon flaticon-search-magnifier-interface-symbol"  style="color:#000000"></i> <span class="m-menu__link-text" style="color:#1d1b1b">Search</span> </a>
                      <div class="m-menu__submenu "> <span class="m-menu__arrow"></span> </div>
                   </li>
                   <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" m-menu-submenu-toggle="hover"> <a href="javascript:;"  @click="handleNavigation('visualization')" :disabled="!$store.state.simpleSearch.enableDashboardOnSubmit"  class="m-menu__link m-menu__toggle"> <i class="m-menu__link-icon flaticon-pie-chart"></i> <span class="m-menu__link-text"  >Visualization</span> </a> </li>
                   <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" m-menu-submenu-toggle="hover"> <a href="javascript:;"  @click="handleNavigation('tabularView')"   :disabled="!$store.state.simpleSearch.enableDashboardOnSubmit" class="m-menu__link m-menu__toggle"> <i class="m-menu__link-icon flaticon-interface-11"></i> <span class="m-menu__link-text">Tab Tabular</span> </a> </li>
                    <!-- <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" m-menu-submenu-toggle="hover"> <a href="javascript:;" :disabled="!$store.state.simpleSearch.enableDashboardOnSubmit"  class="m-menu__link m-menu__toggle"> <i class="m-menu__link-icon flaticon-pie-chart"></i> <span class="m-menu__link-text"  >Visualization</span> </a> </li>
                   <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" m-menu-submenu-toggle="hover"> <a href="javascript:;"  :disabled="!$store.state.simpleSearch.enableDashboardOnSubmit" class="m-menu__link m-menu__toggle"> <i class="m-menu__link-icon flaticon-interface-11"></i> <span class="m-menu__link-text">Tab Tabular</span> </a> </li> -->
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
                                     <div class="col" >
                                        <div id="m_chart_profit_share" class="m-widget14__chart crd-icn-algn zoomIn">
                                           <div class="m-widget14__stat">
                                           <!-- <i class="flaticon-profile m--font-info crd-icn-txt"></i>  -->
                                           <img src="assets/images/icons/Icon_Referances.png" width="80" alt="Referances">
                                           </div>
                                        </div>
                                     </div>
                                     <div class="col">
                                        <div class="m-widget14__legends">                                            
                                           <div class="m-widget14__legend"> <span class="m-widget14__legend-bullet m--bg-accent"></span> <span class="m-widget14__legend-text crd-icn-hdr">References</span> </div>
                                         <!--  <div class="m-widget14__legend"> <span class="m-widget14__legend-bullet m--bg-warning"></span> <span v-if="$store.state.simpleSearch.searchCount.refIdsCount || $store.state.simpleSearch.searchCount.refIdsCount == 0" class="m-widget14__legend-text crd-icn-count crd-icn-col-blu timer count-title count-number" :data-to="$store.state.simpleSearch.searchCount.refIdsCount" data-speed="2000">{{$store.state.simpleSearch.searchCount.refIdsCount}}</span></div> -->
                                           <div class="m-widget14__legend"> <span class="m-widget14__legend-bullet m--bg-warning"></span> <span v-if="$store.state.simpleSearch.searchCount.refIdsCount || $store.state.simpleSearch.searchCount.refIdsCount == 0" class="m-widget14__legend-text crd-icn-count crd-icn-col-blu count-title count-number" :data-to="$store.state.simpleSearch.searchCount.refIdsCount" data-speed="2000">{{$store.state.simpleSearch.searchCount.refIdsCount}}</span></div>
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
                                           <div class="m-widget14__stat">
                                          <!--  <i class="flaticon-map m--font-info crd-icn-txt"></i>  -->
                                           <img src="assets/images/icons/Icon_Unique_Structures.png" width="85" alt="Unique Structure">
                                           </div>
                                        </div>
                                     </div>
                                     <div class="col">
                                        <div class="m-widget14__legends">
                                           <div class="m-widget14__legend"> <span class="m-widget14__legend-bullet m--bg-accent"></span> <span class="m-widget14__legend-text crd-icn-hdr">Structures</span> </div>
                                           <!-- <div class="m-widget14__legend"> <span class="m-widget14__legend-bullet m--bg-warning"></span> <span v-if="$store.state.simpleSearch.searchCount.strIdsCount  || $store.state.simpleSearch.searchCount.strIdsCount == 0" class="m-widget14__legend-text crd-icn-count crd-icn-col-blu timer count-title count-number" :data-to="$store.state.simpleSearch.searchCount.strIdsCount" data-speed="2000">{{$store.state.simpleSearch.searchCount.strIdsCount}}</span> </div> -->
                                           <div class="m-widget14__legend"> <span class="m-widget14__legend-bullet m--bg-warning"></span> <span v-if="$store.state.simpleSearch.searchCount.strIdsCount  || $store.state.simpleSearch.searchCount.strIdsCount == 0" class="m-widget14__legend-text crd-icn-count crd-icn-col-blu count-title count-number" :data-to="$store.state.simpleSearch.searchCount.strIdsCount" data-speed="2000">{{$store.state.simpleSearch.searchCount.strIdsCount}}</span> </div>
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
                                           <div class="m-widget14__stat">
                                           <!-- <i class="flaticon-interface-11 m--font-info crd-icn-txt"></i>  -->
                                           <img src="assets/images/icons/Icon_Data_Records.png" width="80" alt="Records">
                                           </div>
                                        </div>
                                     </div>
                                     <div class="col">
                                        <div class="m-widget14__legends">
                                           <div class="m-widget14__legend"> <span class="m-widget14__legend-bullet m--bg-accent"></span> <span class="m-widget14__legend-text crd-icn-hdr">Records</span> </div>
                                           <div class="m-widget14__legend">
                                              <!-- <span class="m-widget14__legend-bullet m--bg-warning"></span> <span v-if="$store.state.simpleSearch.searchCount.gvkIdsCount  || $store.state.simpleSearch.searchCount.gvkIdsCount == 0" class="m-widget14__legend-text crd-icn-count crd-icn-col-blu timer count-title count-number" :data-to="$store.state.simpleSearch.searchCount.gvkIdsCount" data-speed="2000">{{$store.state.simpleSearch.searchCount.gvkIdsCount}}</span> -->
                                              <span class="m-widget14__legend-bullet m--bg-warning"></span> <span v-if="$store.state.simpleSearch.searchCount.gvkIdsCount  || $store.state.simpleSearch.searchCount.gvkIdsCount == 0" class="m-widget14__legend-text crd-icn-count crd-icn-col-blu count-title count-number" :data-to="$store.state.simpleSearch.searchCount.gvkIdsCount" data-speed="2000">{{$store.state.simpleSearch.searchCount.gvkIdsCount}}</span>
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
                                           <div class="m-widget14__stat">
                                           <!-- <i class="flaticon-list m--font-info crd-icn-txt"></i> -->
                                           <img src="assets/images/icons/Icon_Activities_01.png" width="80" alt="Activites">
                                           </div>
                                        </div>
                                     </div>
                                     <div class="col">
                                        <div class="m-widget14__legends">
                                           <div class="m-widget14__legend"> <span class="m-widget14__legend-bullet m--bg-accent"></span> <span class="m-widget14__legend-text crd-icn-hdr">Activities</span> </div>
                                           <div class="m-widget14__legend">
                                              <!-- <span class="m-widget14__legend-bullet m--bg-warning"></span><span v-if="$store.state.simpleSearch.searchCount.actIdsCount || $store.state.simpleSearch.searchCount.actIdsCount == 0" class="m-widget14__legend-text crd-icn-count crd-icn-col-blu timer count-title count-number" :data-to="$store.state.simpleSearch.searchCount.actIdsCount" data-speed="2000">{{$store.state.simpleSearch.searchCount.actIdsCount}}</span> -->
                                              <span class="m-widget14__legend-bullet m--bg-warning"></span><span v-if="$store.state.simpleSearch.searchCount.actIdsCount || $store.state.simpleSearch.searchCount.actIdsCount == 0" class="m-widget14__legend-text crd-icn-count crd-icn-col-blu count-title count-number" :data-to="$store.state.simpleSearch.searchCount.actIdsCount" data-speed="2000">{{$store.state.simpleSearch.searchCount.actIdsCount}}</span>
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
                                           <!--  <div class="m-widget14__legend"> <span class="m-widget14__legend-bullet m--bg-accent"></span> <span class="m-widget14__legend-text">Header</span> </div>
                                              <div class="m-widget14__legend"> <span class="m-widget14__legend-bullet m--bg-warning"></span> <span class="m-widget14__legend-text" style="font-size:3rem">1450</span> </div>  -->
                                           <!-- STARTS Two splitted blocks container -->    
                                           <div class="m-section m-section--last">
                                              <div class="m-section__content">
                                                 <!--begin::Preview-->
                                                 <div class="" data-code-preview="true" data-code-html="true" data-code-js="false">
                                                    <div class="m-demo__preview">
                                                       <ul class="m-nav m-nav--inline">
                                                          <li class="m-nav__item">
                                                             <span class="m-nav__link">
                                                             <i class="m-nav__link-icon flaticon-share"></i>
                                                             <span class="m-nav__link-text">Clinical Compound</span><span class="num-vl-blu">1,304</span>
                                                             </span>
                                                          </li>
                                                       </ul>
                                                    </div>
                                                 </div>
                                                 <!--end::Preview-->
                                                 <div class="hr-line"></div>
                                                 <!--begin::Preview-->
                                                 <div class="" data-code-preview="true" data-code-html="true" data-code-js="false">
                                                    <div class="m-demo__preview">
                                                       <ul class="m-nav m-nav--inline">
                                                          <li class="m-nav__item">
                                                             <span class="m-nav__link">
                                                             <i class="m-nav__link-icon flaticon-share"></i>
                                                             <span class="m-nav__link-text">Drug</span><span class="num-vl-blu">805</span>
                                                             </span>                                                              
                                                          </li>
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
                         <div class="m-portlet__head-title">
                            <span class="m-portlet__head-icon"> <i class="la la-search"></i> </span>
                            <h3 class="m-portlet__head-text"> Search Criterion </h3>
                         </div>
                      </div>
                   </div>
                   <div class="m-portlet__body ">
                      <div class="m-pricing-table-1">
                         <!-- STARTS Search field form elements block -->
                         <div id="">
                            <div class="form-group  m-form__group row">
                               <div class="col-lg-12">
                                  <div class="form-group m-form__group row align-items-center" v-for="(item,itemIndex) in $store.state.simpleSearch.simpleSearchRowData" :key="item.simpleSearchSelectedValue">
                                     <div class="col-md-3">
                                        <div class="m-form__group m-form__group--inline inpt-tp-10-fix" >
                                           <div class="m-form__control">
                                              <!-- <input type="email" class="form-control m-input shadow-efct-stl" placeholder="Enter full name"> -->                         
                                              <!--  START Search input control -->
                                              <b-form-select
                                              class="form-control shadow-efct-stl inpt-hgt-fld"
                                                v-model="item.simpleSearchSelectedValue"
                                                :disabled="item.disableSimpleSearchSelect"
                                                @input="simpleSearchOptionChange($event,item,itemIndex)">
                                                <option  :value="null" disabled>Please select an option
                                                </option>
                                                <option  v-if="item.simpleSearchSelectedValue != null" :value="item.simpleSearchSelectedValue" disabled>
                                                   {{item.simpleSearchSelectedLabel}}
                                                </option>
                                                <optgroup class="gs-bmodal-select-dd":label="group.label" v-for="group in
                                                $store.state.simpleSearch.simpleSearchParents">
                                                <option
                                                   class="gs-bmodal-select-option"
                                                   :value="opt.value"
                                                   v-for="opt in $store.state.simpleSearch.simpleSearchOptions"
                                                   v-if="group.value == opt.parent && !opt.isDisabled"
                                                   :disabled="item.simpleSearchSelectedValue == opt.value && !opt.isDisabled"
                                                   >
                                                   {{opt.label}}
                                                </option>
                                                </optgroup>
                                             </b-form-select>
                                              <!--  ENDS Search Input control -->
                                           </div>
                                        </div>
                                        <div class="d-md-none m--margin-bottom-10"></div>
                                     </div>
                                     <div class="col-md-6">
                                          <div class="row">
                                                <!-- autocomplete functionality -->
                                                <!-- :disable-auto-complete="item.disableFields"  -->                                                
                                                <div class="col-md-11" v-if="item.treeViewSearch && item.simpleSearchSelectedValue && item.simpleSearchSelectedValue.indexOf('TreeView') > -1">
                                                   <div class="col-md-11" >
                                                   <div class="freeze-field">
                                                     <div style="text-decoration: underline" @click="getTreeViewData(item, itemIndex,'update')">
                                                       {{ item.treeViewSearch.currentTreeViewSelected  + ':count(' + item.treeViewSearch.selectedNodeIds.length + ')'}}
                                                     </div>
                                                   </div>
                                                   </div>
                                                 </div>
                                                 <!-- source search --> 
                                               
                                                 <div class="col-md-11" v-else-if="item.simpleSearchSelectedValue == 'ActivityTypes'">
                                                   <div  v-if="item.advanceSearch.autoComplete.length == 0 && !item.isStructureAdvanceSearch && item.advanceSearchSelectedValue != 'ref_id'">
                                                      <auto-complete search-type="SIMPLE_SEARCH"                                                     
                                                        ref="simpleAutocomplete"
                                                        @show-alert-popup="showAlertPopup($event)"
                                                        @show-alert-popup-activity="showAlertPopupActivity($event)"
                                                        :show-options-on-focus="item.simpleSearchSelectedValue == 'Assay' ? true : false"
                                                        :is-target-search="item.simpleSearchSelectedValue == 'Target' ? true : false"
                                                        :selected-value="item.simpleSearchSelectedValue"
                                                        :disable-auto-complete="item.simpleSearchSelectedValue ? false : true"
                                                        :default-selected-options="JSON.parse(JSON.stringify(item.simpleSearchAutoComplete))"
                                                        :disable-auto-complete="item.disableFields"
                                                        @auto-complete-selection-change="autoCompleteChange($event,item,itemIndex)"
                                                        :current-index="itemIndex" id="autoSearch">
                                                      </auto-complete>
                                                   </div>                                                                                             
                                                   <div class="col-md-11" v-else-if="item.advanceSearch.autoComplete.length > 0">
                                                      <div class="freeze-field">
                                                        {{ item.advanceSearchSelectedValue  + ':count(' + item.advanceSearch.autoComplete.length + ')'}}
                                                         <div style="display:inline; position:relative"> ...
                                                            <q-tooltip anchor="top middle" self="top middle"
                                                              :offset="[50,90]">
                                                              <div
                                                               v-for="i in item.advanceSearch.autoComplete.slice(0,5)"
                                                               :key="i.value">                                                                                                                            
                                                               Activity type : {{i.activityType}}
                                                              </div>
                                                              ...
                                                            </q-tooltip>
                                                         </div>
                                                      </div>
                                                   </div>                                                 
                                                </div>
                                                 <div class="col-md-11" v-else-if="item.simpleSearchSelectedValue == 'Source'">
                                                   <div  v-if="item.fileName == '' && item.advanceSearch.autoComplete.length == 0 && !item.isStructureAdvanceSearch && item.advanceSearchSelectedValue != 'ref_id'">
                                                      <auto-complete search-type="SIMPLE_SEARCH"                                                     
                                                        ref="simpleAutocomplete"
                                                        @show-alert-popup="showAlertPopup($event)"
                                                        @show-alert-popup-activity="showAlertPopupActivity($event)"
                                                        :show-options-on-focus="item.simpleSearchSelectedValue == 'Assay' ? true : false"
                                                        :is-target-search="item.simpleSearchSelectedValue == 'Target' ? true : false"
                                                        :selected-value="item.simpleSearchSelectedValue"
                                                        :disable-auto-complete="item.simpleSearchSelectedValue ? false : true"
                                                        :default-selected-options="JSON.parse(JSON.stringify(item.simpleSearchAutoComplete))"
                                                        :disable-auto-complete="item.disableFields"
                                                        @auto-complete-selection-change="autoCompleteChange($event,item,itemIndex)"
                                                        :current-index="itemIndex" id="autoSearch">
                                                      </auto-complete>
                                                   </div>
                                                   <!-- Freezed field for file upload start -->
                                                   <div class="col-md-9" v-else-if="item.fileName != ''">
                                                      <div class="freeze-field">{{item.fileName}}</div>
                                                   </div>                                              
                                                   <div class="col-md-11" v-else-if="item.advanceSearch.autoComplete.length > 0">
                                                      <div class="freeze-field">
                                                        {{ item.advanceSearchSelectedValueName  + ':count(' + item.advanceSearch.autoComplete.length + ')'}}
                                                         <div style="display:inline; position:relative"> ...
                                                            <q-tooltip anchor="top middle" self="top middle"
                                                              :offset="[50,90]">
                                                              <div
                                                               v-for="i in item.advanceSearch.autoComplete.slice(0,5)"
                                                               :key="i.value">
                                                               {{item.advanceSearchSelectedValueName}}:{{i.label}}
                                                              </div>
                                                              ...
                                                            </q-tooltip>
                                                         </div>
                                                      </div>
                                                   </div>                                                 
                                                </div>
                                                 <!-- source search ends -->                                                
                                                <div class="col-md-11" v-else-if="item.simpleSearchSelectedValue == 
                                                'bibliography'">
                                                <div  v-if="item.fileName == '' &&
                                                item.advanceSearch.autoComplete.length
                                                == 0 && !item.isStructureAdvanceSearch && item.advanceSearchSelectedValue != 'ref_id'">                                               
                                                    <auto-complete search-type="SIMPLE_SEARCH"                                                     
                                                      ref="simpleAutocomplete"
                                                      @show-alert-popup="showAlertPopup($event)"
                                                      @show-alert-popup-activity="showAlertPopupActivity($event)"
                                                      :show-options-on-focus="item.simpleSearchSelectedValue == 'Assay' ? true : false"
                                                      :is-target-search="item.simpleSearchSelectedValue == 'Target' ? true : false"
                                                      :selected-value="item.simpleSearchSelectedValue"
                                                      :disable-auto-complete="item.simpleSearchSelectedValue ? false : true"
                                                      :default-selected-options="JSON.parse(JSON.stringify(item.simpleSearchAutoComplete))"
                                                      :disable-auto-complete="item.disableFields"
                                                      @auto-complete-selection-change="autoCompleteChange($event,item,itemIndex)"
                                                      :current-index="itemIndex" id="autoSearch">
                                                    </auto-complete>
                                                  </div>
                                                  <!-- Freezed field for file upload start -->
                                                  <div class="col-md-9" v-else-if="item.fileName != ''">
                                                    <div class="freeze-field">{{item.fileName}}</div>
                                                  </div>
                                                  <div class="col-md-11"
                                                  v-else-if="item.bibliographySearch && item.bibliographySearch.currentSelectedTab == 'custom'">
                                                  <div class="freeze-field"> 
                                                    {{ item.advanceSearchSelectedValueName + '...' + ':count(' + item.advanceSearch.autoComplete.length + ')'}}
                                                    <div style="display:inline;position:relative" v-if="item.advanceSearch.bibliographyRow"> ...
                                                        <q-tooltip anchor="top middle" self="top middle"
                                                          :offset="[50,90]">
                                                          <div
                                                            v-for="(i, index) in item.advanceSearch.bibliographyRow.slice(0,5)"
                                                            :key="index">
                                                            {{i.selectedDataSource == 'j' ? 'Journal' : 'Patent'}}: {{i.journalName ? i.journalName : i.countryCode[0]}}
                                                          </div>
                                                          ...
                                                        </q-tooltip>
                                                      </div>
                                                    </div>
                                                  </div>
                                                  <div class="col-md-11"
                                                    v-else-if="item.bibliographySearch.criterionSearch && item.advanceSearch.autoComplete.length > 0 && item.bibliographySearch.currentSelectedTab != 'custom'">
                                                    <div class="freeze-field">
                                                     {{ item.advanceSearchSelectedValueName  + ':count(' + item.advanceSearch.autoComplete.length + ')'}}
                                                      <div style="display:inline;position:relative"> ...
                                                        <q-tooltip anchor="top middle" self="top middle"
                                                          :offset="[50,90]">
                                                          <div
                                                            v-for="i in item.advanceSearch.autoComplete.slice(0,5)"
                                                            :key="i.value">
                                                            {{item.advanceSearchSelectedValueName}}:{{i.label}}
                                                          </div>
                                                          ...
                                                        </q-tooltip>
                                                      </div>
                                                    </div>
                                                  </div>
                                                  <div class="col-md-11"
                                                    v-else-if="item.advanceSearchSelectedValue == 'ref_id'">
                                                    <div class="freeze-field">
                                                     {{ item.advanceSearchSelectedValueName  + ':count(' + item.bibliographySearch.listSearch.advanceSearch.refIds.length + ')'}}
                                                      <div style="display:inline;position:relative"> ...
                                                        <q-tooltip anchor="top middle" self="top middle"
                                                          :offset="[50,90]">
                                                          <div
                                                            v-for="i in item.bibliographySearch.listSearch.advanceSearch.refIds.slice(0,5)"
                                                            :key="i">
                                                            {{item.advanceSearchSelectedValueName}}:{{i}}
                                                          </div>
                                                          ...
                                                        </q-tooltip>
                                                      </div>
                                                    </div>
                                                  </div>
                                                </div>
                                                <div class="col-md-11" v-else-if="item.fileName == '' &&
                                      item.advanceSearch.autoComplete.length
                                      == 0 && !item.isStructureAdvanceSearch">
                                                  <auto-complete search-type="SIMPLE_SEARCH"
                                                    ref="simpleAutocomplete"
                                                    @show-alert-popup="showAlertPopup($event)"
                                                    @show-alert-popup-activity="showAlertPopupActivity($event)"
                                                    :show-options-on-focus="['Assay', 'ActivityMechanism','ClinicalPhases','CompoundCategories'].indexOf(item.simpleSearchSelectedValue) > -1  ? true : false"
                                                    :is-target-search="item.simpleSearchSelectedValue == 'Target' ? true : false"
                                                    :selected-value="item.simpleSearchSelectedValue"
                                                    :disable-auto-complete="item.simpleSearchSelectedValue ? false : true"
                                                    :default-selected-options="JSON.parse(JSON.stringify(item.simpleSearchAutoComplete))"
                                                    :disable-auto-complete="item.disableFields"
                                                    @auto-complete-selection-change="autoCompleteChange($event,item,itemIndex)"
                                                    :current-index="itemIndex" id="autoSearch">
                                                  </auto-complete>
                                                </div>
                                                <!-- Freezed field for file upload start -->
                                                <div class="col-md-11" v-else-if="item.fileName !=''">
                                                  <div class="freeze-field">{{item.fileName}}</div>
                                                </div>
                                                <!-- Freezed field for file upload end -->
                                                <!-- Freezed field for target search start -->
                                                <div class="col-md-11"
                                                  v-else-if="item.advanceSearch.autoComplete.length> 0">
                                                  <div class="freeze-field" v-if="item.simpleSearchSelectedValue == 'Indication'">
                                                   {{ $store.state.advanceSearch.selectedValueNameIndication  + ':count(' + item.advanceSearch.autoComplete.length + ')'}}
                                                    <div style="display:inline;position:relative"> ...
                                                      <q-tooltip anchor="top middle" self="top middle"
                                                        :offset="[50,90]">
                                                        <div
                                                          v-for="i in item.advanceSearch.autoComplete.slice(0,5)"
                                                          :key="i.value">
                                                          {{$store.state.advanceSearch.selectedValueName}}:{{i.label}}
                                                        </div>
                                                        ...
                                                      </q-tooltip>
                                                    </div>
                                                  </div>
                                                  <div class="freeze-field" v-if="item.simpleSearchSelectedValue != 'Indication'">
                                                   {{ $store.state.advanceSearch.selectedValueName + ':count(' + item.advanceSearch.autoComplete.length + ')'}}
                                                    <div style="display:inline;position:relative"> ...
                                                      <q-tooltip anchor="top middle" self="top middle"
                                                        :offset="[50,90]">
                                                        <div
                                                          v-for="i in item.advanceSearch.autoComplete.slice(0,5)"
                                                          :key="i.value">
                                                          {{$store.state.advanceSearch.selectedValueName}}:{{i.label}}
                                                        </div>
                                                        ...
                                                      </q-tooltip>
                                                    </div>
                                                  </div>
                                                </div>

                                                <!-- Freezed field for target search ends -->
                                                <!-- Freezed field for structure search start -->
                                                <div class="col-md-11"
                                                  v-else-if="item.isStructureAdvanceSearch && item.structureAdvanceSearchTab == 'term'&& !item.advanceSearch.structureInputField">
                                                  <div class="freeze-field">

                                                    {{ $store.state.structureSearch.selectedValueName + ':count(' + item.advanceSearch.structureInputField.length + ')'}}
                                                    <div style="display:inline;position:relative"> ...
                                                      <q-tooltip anchor="top middle" self="top middle"
                                                        :offset="[50,90]">
                                                        <div
                                                          v-for="i in item.advanceSearch.structureInputField.slice(0,5)"
                                                          :key="i.label">
                                                          {{$store.state.structureSearch.selectedValueName}}:{{i}}
                                                        </div>
                                                        ...
                                                      </q-tooltip>
                                                    </div>
                                                  </div>
                                                </div>
                                                <div class="col-md-11"
                                                  v-else-if="item.isStructureAdvanceSearch && item.structureAdvanceSearchTab == 'term' && item.advanceSearch.structureInputField">
                                                  <div class="freeze-field">

                                                    {{ $store.state.structureSearch.selectedValueName + ':count(' + item.advanceSearch.structureInputField.length + ')'}}
                                                    <div style="display:inline;position:relative"> ...
                                                      <q-tooltip anchor="top middle" self="top middle"
                                                        :offset="[50,90]">
                                                        <div
                                                          v-for="i in item.advanceSearch.structureInputField.slice(0,5)"
                                                          :key="i.label">
                                                          {{$store.state.structureSearch.selectedValueName}}:{{i.label}}
                                                        </div>
                                                        ...
                                                      </q-tooltip>
                                                    </div>
                                                  </div>
                                                </div>
                                                <div class="col-md-11"
                                                  v-else-if="item.isStructureAdvanceSearch && item.advanceSearchSelectedValue == 'draw'">
                                                  <div class="freeze-field">

                                                    {{ $store.state.structureSearch.selectedValueName + ':' + item.advanceSearch.chemistrySearch.strDraw.slice(0,60) }}
                                                    <div style="display:inline;position:relative" v-if="item.advanceSearch.chemistrySearch.strDraw.length > 60"> ...
                                                      <q-tooltip anchor="top middle" self="top middle" :offset="[10,30]">
                                                        
                                                          {{item.advanceSearch.chemistrySearch.strDraw}}
                                                        
                                                      
                                                      </q-tooltip>
                                                    </div>

                                                  </div>
                                                </div>
                                                <div class="col-md-11"
                                                  v-else-if="item.isStructureAdvanceSearch && item.advanceSearchSelectedValue == 'readOnly'">
                                                  <div class="freeze-field">

                                                    {{ $store.state.structureSearch.selectedValueName + ':count(' + item.advanceSearch.chemistrySearch.strComboData.length + ')'}}
                                                    <div style="display:inline;position:relative"> ...
                                                      <q-tooltip anchor="top middle" self="top middle"
                                                        :offset="[50,90]">
                                                        <div
                                                          v-for="i in item.advanceSearch.chemistrySearch.strComboData.slice(0,5)"
                                                          :key="i.label">
                                                          {{$store.state.structureSearch.selectedValueName}}:{{i}}
                                                        </div>
                                                        ...
                                                      </q-tooltip>
                                                    </div>
                                                  </div>
                                                </div>
                                                <div class="col-md-11" v-else-if="item.isStructureAdvanceSearch && item.advanceSearchSelectedValue == 'structural'">
                                                  <div class="freeze-field">
                                                
                                                    Structural Property
                                                   
                                                    <div style="display:inline;position:relative"> ...
                                                      <!-- <q-tooltip anchor="top middle" self="top middle" :offset="[50,90]">
                                                        <div v-for="i in item.advanceSearch.propertySearch.structuralProperties.slice(0,5)" :key="i">
                                                         {{i}}
                                                        </div>
                                                        ...
                                                      </q-tooltip> -->
                                                    </div>
                                                  </div>
                                                </div>
                                                <div class="col-md-11" v-else-if="item.isStructureAdvanceSearch && item.advanceSearchSelectedValue == 'physico'">
                                                  <div class="freeze-field">
                                                
                                                    Physico Chemical Property
                                                
                                                    <div style="display:inline;position:relative"> ...
                                                      <!-- <q-tooltip anchor="top middle" self="top middle" :offset="[50,90]">
                                                                                                                  <div v-for="i in item.advanceSearch.propertySearch.structuralProperties.slice(0,5)" :key="i">
                                                                                                                   {{i}}
                                                                                                                  </div>
                                                                                                                  ...
                                                                                                                </q-tooltip> -->
                                                    </div>
                                                  </div>
                                                </div>
                                                
                                                <!-- Freeze field for structure search ends -->
                                                <!--  START Select Window popup pallet resource -->
                                                <div  v-if="!item.disableAdvanceSearchButton" class="col-md-1" 
                                                   :class="{'disable-button':(item.disableAdvanceSearchButton  || item.disableFields)}">
                                                   <span class="btn btn-accent shadow_stly m-btn m-btn--icon btn-sm m-btn--icon-only "
                                                        :class="{'btn-accent':!(item.disableAdvanceSearchButton  || item.disableFields), 'cursor-pointer': !(item.disableAdvanceSearchButton  || item.disableFields)}"
                                                        variant="primary"
                                                       
                                                        @click="(item.disableAdvanceSearchButton  || item.disableFields) ? '' : openAdvanceSearchPopup(item, itemIndex)"
                                                        :disabled="item.disableAdvanceSearchButton"><i class="la la-archive"></i></span>
                                                   <!-- <a href="#" class="btn shadow_stly btn-accent m-btn m-btn--icon btn-sm m-btn--icon-only" data-toggle="modal" data-target="#m_modal_4"> 
                                                       <i class="la la-archive"></i> </a>  -->
                                                <!-- <div class="col-md-1"
                                                  :class="{'disable-button':(item.disableAdvanceSearchButton  || item.disableFields)}">
                                                  <span class="btn-pick btn-md "
                                                    :class="{'btn-accent':!(item.disableAdvanceSearchButton  || item.disableFields), 'cursor-pointer': !(item.disableAdvanceSearchButton  || item.disableFields)}"
                                                    variant="primary"
                                                    @click="(item.disableAdvanceSearchButton  || item.disableFields) ? '' : openAdvanceSearchPopup(item, itemIndex)"
                                                    :disabled="item.disableAdvanceSearchButton"> <i
                                                      class="fa flaticon-more-v5"></i> </span>
                                                </div> -->

                                                <!--  ENDS Select Window popup pallet resource -->
                                              </div>
                                        <div class="d-md-none m--margin-bottom-10"></div>
                                     </div>
                                     <!-- <div class="col-md-1"> <a href="#" class="btn shadow_stly btn-accent m-btn m-btn--icon btn-sm m-btn--icon-only" data-toggle="modal" data-target="#m_modal_4"> <i class="la la-archive"></i> </a>  -->
                                     </div>
                                     <div class="col-md-1" style="width:95px;">
                                        <!-- STARTS Switch toggle -->
                                        <div class="outerDivFull">
                                           <div class="switchToggle">
                                              <input type="checkbox" checked :id="'switch' + itemIndex" v-on:change="operatorChanged($event, item)">
                                              <label :for="'switch' + itemIndex">NOT</label>
                                           </div>
                                        </div>
                                        <!-- ENDS Switch toggle --> 
                                     </div>
                                     <div class="col-md-2">
                                        <!--<div data-repeater-delete="" class="btn-sm btn btn-primary m-btn m-btn--icon m-btn--pill shadow-efct-stl"> <span> <i class="la la-rotate-right"></i> <span>Refresh</span> </span> </div> -->                        
                                        <div style="float:left"><span class="btn-lx btn spn_dsply_in"> 
                                           <i v-if="!item.treeViewSearch" @click="reset(item,itemIndex)" class="la la-rotate-right" style="font-size:28px; font-weight:bold"></i>
                                          <i v-if="item.treeViewSearch" @click="getTreeViewData(item, itemIndex,'reset')"  class="la la-rotate-right" style="font-size:28px; font-weight:bold"></i>
                                       </span><span class="btn-lx btn">
                                           <i v-if="$store.state.simpleSearch.simpleSearchRowData.length > 1" @click="deleteRow(item,itemIndex)" class="flaticon-delete-2" style="font-size: 24px"></i></span> </div>
                                        <!-- <div data-repeater-delete="" class="btn-sm btn btn-danger m-btn m-btn--icon m-btn--pill shadow-efct-stl"> <span> <i class="la la-trash-o"></i> <span>Delete</span> </span> </div> -->
                                        <div v-if="!(item.disableSimpleSearchSelect || disableAdd(item,itemIndex))" class="btn btn btn-sm btn-primary m-btn m-btn--icon m-btn--pill m-btn--wide btn-mrgn-5" :class="{'add-btn-mar': $store.state.simpleSearch.simpleSearchRowData.length > 1}" style="margin-left: -10px;"><span @click="addNewRow(item,itemIndex)" 
                                          :disabled="(item.disableSimpleSearchSelect || disableAdd(item,itemIndex))"
                                          :class="{'pointerNone':( item.disableSimpleSearchSelect || disableAdd(item,itemIndex))}"><i class="la la-plus"></i> <span>Add</span> </span> </div>
                                       </div>                                    
                                  </div>
                               </div>
                            </div>                           
                         </div>
                         <!-- ENDS Search field form elements block --> 
                      </div>
                   </div>
                   <div class="m-portlet__foot">
                      <div class="row align-items-center">
                         <!--  <div class="col-lg-6 m--valign-middle"> Submit selected values: </div>  -->
                         <div class="col-lg-12 m--align-right footer-rht">
                            <button type="submit" class="btn btn-success"  @click="onSubmit">Submit</button>
                         </div>
                      </div>
                   </div>
                </div>
                <!--end::Portlet--> 
             </div>
          </div>
       </div>
       <!-- end:: Body --> 
       <!-- begin::Footer -->
       <footer class="m-grid__item m-footer" style="position: absolute; bottom: 0px;">
          <div class="m-container m-container--fluid m-container--full-height m-page__container">
             <div class="m-stack m-stack--flex-tablet-and-mobile m-stack--ver m-stack--desktop">
                <div class="m-stack__item m-stack__item--left m-stack__item--middle m-stack__item--last"> <span class="m-footer__copyright" style="color:#fff"> 2019 &copy; <a href="https://www.excelra.com/" class="m-link" style="color:#ffffff">EXCELRA Knowledge Solutions Pvt Ltd</a> </span> </div>
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
    <!-- Loader  -->
    <app-loader v-if="showLoading"></app-loader>
    <app-loader v-if="$store.state.advanceSearch.showCountsLoader"></app-loader>
    <!-- Modal popup for user access -->
    <b-modal id="modal-xl" ref="userAccessModal" :hide-footer="true" size="xl" >
      <template v-slot:modal-header>       
          <h5 >Alert</h5>
          <div>
            <i class="fa fa-times" style="cursor:pointer" @click="$refs.userAccessModal.hide()"></i>
          </div>
      </template>
      Access denied! Please contact support team.
    </b-modal>
    <!-- Modal for assay binding validation when deleting rows -->
    

   <b-modal id="modal-center" v-model="showAssayBindingDeleteAlert" title="Notification" hide-footer hide-header-close :no-close-on-backdrop="true" :no-close-on-esc="true" centered>
         <div v-if="showAssayBindingDeleteAlert || showAssayBindingRefreshAlert" class="backdrop-bg"></div>
         <div style="z-index: 112" class="modal-custom-content" v-if="showAssayBindingDeleteAlert">
               Your data will be lost do you want to continue?
               <div class="modal-footer" style="background-color:#fff;">
                 <button type="button" class="btn btn-danger" @click="showAssayBindingDeleteAlert = false">Cancel</button>
                 <button type="button" class="btn btn-excelra" @click="deleteOnConfirm">Ok</button>
               </div>
             </div>
   </b-modal>

    <b-modal id="modal-center" v-model="showAssayBindingRefreshAlert" title="Notification" hide-footer hide-header-close :no-close-on-backdrop="true" :no-close-on-esc="true" centered>
    
    <div style="z-index: 112" class="modal-custom-content" v-if="showAssayBindingRefreshAlert">
      Your data will be lost do you want to continue?
      <div class="modal-footer" style="background-color:#fff;">
        <button type="button" class="btn btn-danger" @click="showAssayBindingRefreshAlert = false">Cancel</button>
        <button type="button" class="btn btn-excelra" @click="resetOnConfirm">Ok</button>
      </div>
    </div>
   </b-modal>

   <b-modal id="modal-center" v-model="showActivityTypesAlert" title="Alert" hide-footer hide-header-close :no-close-on-backdrop="true" :no-close-on-esc="true" centered>
    
      <div style="z-index: 112" class="modal-custom-content" v-if="showActivityTypesAlert">
        Please Remove ActivityTypes
        <div class="modal-footer" style="background-color:#fff;">
          <button type="button" class="btn btn-excelra"  @click="showActivityTypesAlert = false">OK</button>
        </div>
      </div>
     </b-modal>
   <div v-if="openAdvSearch || $store.state.treeViewSearch.currentTreeViewSelected" class="backdrop-bg"></div>
   <div class="modal animated"
   :class="{'show': $store.state.treeViewSearch.currentTreeViewSelected}"
   style="z-index: 1000" class="modal-custom-content " v-if="$store.state.treeViewSearch.currentTreeViewSelected">
      <treeview-container
      v-if="$store.state.treeViewSearch.currentTreeViewSelected"
         @popup-closed="popupClosed"
         >
      </treeview-container>
   </div>
   <div  class="modal animated" style="z-index: 1000"
      :class="{
         show: openAdvSearch,
         'fadeInDown': openAdvSearch
      }"  tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true"> 
      <target-search
          @popup-closed="popupClosed"
          v-if="openAdvSearch && $store.state.advanceSearch.currentItem.simpleSearchSelectedValue =='Target'">
        </target-search>
        <indication-advance-search
          @popup-closed="popupClosed"
          v-if="openAdvSearch && $store.state.advanceSearch.currentItem.simpleSearchSelectedValue =='Indication'">
        </indication-advance-search>
        <structure-search
          @popup-closed="popupClosed"
          v-if="openAdvSearch && $store.state.advanceSearch.currentItem.simpleSearchSelectedValue =='Structure'">
        </structure-search>
        <bibliography-search
          @popup-closed="popupClosed"
          v-if="openAdvSearch && $store.state.advanceSearch.currentItem.simpleSearchSelectedValue =='bibliography'">
        </bibliography-search>
        <activity-type-advance-search
          @popup-closed="popupClosed"
          v-if="openAdvSearch && $store.state.advanceSearch.currentItem.simpleSearchSelectedValue =='ActivityTypes'">
        </activity-type-advance-search>
        <source-search
          @popup-closed="popupClosed"
          v-if="openAdvSearch && $store.state.advanceSearch.currentItem.simpleSearchSelectedValue =='Source'">
        </source-search>

   </div>
   <div class="modal-backdrop fade show"  style="z-index: 999" v-if="openAdvSearch || $store.state.treeViewSearch.currentTreeViewSelected"></div>
     
    <div id="m_scroll_top" class="m-scroll-top"> <i class="la la-arrow-up"></i></div>
    <!-- end:: Page --> 
    <!-- begin::Quick Sidebar -->
   <!-- 
    <div id="m_quick_sidebar" class="m-quick-sidebar m-quick-sidebar--tabbed m-quick-sidebar--skin-light">
       <div class="m-quick-sidebar__content m--hide">
          <span id="m_quick_sidebar_close" class="m-quick-sidebar__close"> <i class="la la-close"></i> </span>
          <ul id="m_quick_sidebar_tabs" class="nav nav-tabs m-tabs m-tabs-line m-tabs-line--brand" role="tablist">
             <li class="nav-item m-tabs__item"> <a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_quick_sidebar_tabs_settings" role="tab">Settings</a> </li>
          </ul>
          <div class="tab-content">

          </div>
       </div>
    </div>
     -->
    <!-- end::Quick Sidebar --> 
    <!-- begin::Scroll Top -->
    <div id="m_scroll_top" class="m-scroll-top"> <i class="la la-arrow-up"></i> </div>
    <!-- end::Scroll Top --> 
    <!-- begin::Quick Nav -->
    <!-- <ul class="m-nav-sticky" style="margin-top: 30px;">
       <li class="m-nav-sticky__item" data-toggle="m-tooltip" title="Visualization" data-placement="left"> <a href="#" target="_blank"> <i class="flaticon-pie-chart"></i> </a> </li>
       <li class="m-nav-sticky__item" data-toggle="m-tooltip" title="Tab Tabular" data-placement="left"> <a href="#" target="_blank"> <i class="flaticon-tabs"></i> </a> </li>
       <li class="m-nav-sticky__item" data-toggle="m-tooltip" title="Export Data" data-placement="left"> <a href="#" target="_blank"> <i class="flaticon-folder-2"></i> </a> </li>
    </ul> -->
    
   </div>
   </div>
 </body>
    <!-- begin::Quick Nav --> 
    <!--begin::Base Scripts --> 
    <script src="assets/scripts/vendors.bundle.js" type="text/javascript"></script> 
    <!--begin::bundle scripts --> 
    <script src="assets/scripts/scripts.bundle.js" type="text/javascript"></script> 
    <!--begin::Base Scripts --> 
    <!-- Multiple selection form elements --> 
    <script src="assets/scripts/select2.js" type="text/javascript"></script> 
    <!-- START Cards Number count Scripts --> 
    <script src="assets/scripts/cards_numbers_count_animate.js"></script> 
    <!-- ENDS Cards Number Count Scripts --> 
    <!--end::Base Scripts --> 
    <!-- No UI Slider script resource file --> 
    <script src="assets/scripts/forms/widgets/nouislider.js" type="text/javascript"></script> 
    <!--begin::Page Resources --> 
    <!--<script src="assets/scripts/forms/widgets/form-repeater.js" type="text/javascript"></script> --> 
    <!--end::Page Resources --> 
    <!-- No UI Slider script resource file --> 
    <script src="assets/scripts/forms/widgets/nouislider.js" type="text/javascript"></script> 
    <!-- Model window effects scripts --> 
    <script src="assets/scripts/model_classie.js" type="text/javascript"></script> 
    <script src="assets/scripts/modal_effects.js" type="text/javascript"></script> 
    <!-- Form repeater wizard elements --> 
    <script src="assets/scripts/form-repeater.js" type="text/javascript"></script>
    <!-- Modal window animated effects Scripts -->
    <!--
       <script src="assets/scripts/modalClassie.js" type="text/javascript"></script>
       <script src="assets/scripts/modalEffects.js " type="text/javascript"></script>
       <script>
         // this is important for IEs
         var polyfilter_scriptpath = '/js/';
       </script>
       -->    
<!-- end::Body -->
<script src="${contextPath}/assets/scripts/vendors.bundle.js" type="text/javascript"></script>
<!--begin::bundle scripts -->
<!--end::Base Scripts -->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
  // disable CTRL + mousewheel event for zooming in chrome browser
  window.addEventListener("wheel", function (event) {
    if (event.ctrlKey == true) {
      event.preventDefault();
    }
  }, { passive: false });


  $(window).on('load', function () {
    $('body').removeClass('m-page--loading');
  });

   window.config = {
      domain: '',
      userSessionId: "${usersession}",
      contextPath: "${contextPath}",
      apiContextPath: "",
      userRoles:"${userroles}",
      userName: "${username}"
      
    };
    window.config.apiUrl = window.config.domain + "${contextPath}/";

  axios.defaults.headers.common['userSessionId'] = window.config.userSessionId;
  axios.defaults.headers.common['X-Requested-With'] = "XMLHttpRequest";

  // set user token to cookie
   
  localStorage.setItem('userSessionId', window.config.userSessionId)
  
  axios.interceptors.response.use(
    response => {
      return response;
    },
    function (error) {
      let errorStatusCodes = [401, 403];
      if (errorStatusCodes.indexOf(error.response.status) > -1) {
        window.location.href = '${contextPath}/login'
      }
      return Promise.reject(error.response);
    }
  );

</script>
<!-- page loader scripts -->
<!-- <script type="text/javascript" src="${contextPath}/resources/css/page-loader/_scripts/main.js"></script> -->
<!--Marvin js scripts-->
<script src="${contextPath}/marvinjs/js/lib/rainbow/rainbow-custom.min.js"></script>
<!--  <script src="${contextPath}/marvinjs/js/lib/jquery-1.9.1.min.js"></script> -->
<script src="${contextPath}/marvinjs/gui/lib/promise-1.0.0.min.js"></script>
<script src="${contextPath}/marvinjs/js/marvinjslauncher.js"></script>
<script src="${contextPath}/marvinjs/gui/lib/promise-1.0.0.min.js"></script>
<script src="${contextPath}/marvinjs/js/marvinjslauncher.js"></script>
<!-- <script src="${contextPath}/resources/js/utils/draw-structure.js"></script> -->
<script>
  // ajax bar
  window.quasarConfig = {
    loadingBar: { //this takes QAjaxBar Props
      skipHijack: false // set this to true if ajax bar is required
    }
  }
</script>
<!--End of marvin scripts-->

<script src="${contextPath}/resources/js/libraries/polyfills/quasar.ie.polyfills.umd.min.js"></script>
<script src="${contextPath}/resources/js/libraries/quasar.umd.min.js"></script>
<script src="${contextPath}/resources/js/libraries/fontawesome-v5.umd.min.js"></script>
<script src="${contextPath}/resources/js/libraries/vuex.min.js"></script>



<!-- simple search scripts -->

<script src="${contextPath}/resources/store/simple-search/actions.js"></script>
<script src="${contextPath}/resources/store/simple-search/getters.js"></script>
<script src="${contextPath}/resources/store/simple-search/mutations.js"></script>
<script src="${contextPath}/resources/store/simple-search/state.js"></script>

<!-- activity type search scripts -->

<script src="${contextPath}/resources/store/activity-type-search/actions.js"></script>
<script src="${contextPath}/resources/store/activity-type-search/getters.js"></script>
<script src="${contextPath}/resources/store/activity-type-search/mutations.js"></script>
<script src="${contextPath}/resources/store/activity-type-search/state.js"></script>

<script src="${contextPath}/resources/components/activity-type-advance-search/activity-type-advance-search.js"></script>

<!-- tree view search scripts -->

<script src="${contextPath}/resources/store/treeview-search/actions.js"></script>
<script src="${contextPath}/resources/store/treeview-search/getters.js"></script>
<script src="${contextPath}/resources/store/treeview-search/mutations.js"></script>
<script src="${contextPath}/resources/store/treeview-search/state.js"></script>
<!-- tree view advance search components -->
<script src="${contextPath}/resources/components/treeview-search/taxonomy-treeview-search.js"></script>
<script src="${contextPath}/resources/components/treeview-search/target-treeview-search.js"></script>
<script src="${contextPath}/resources/components/treeview-search/pathways-treeview-search.js"></script>
<script src="${contextPath}/resources/components/treeview-search/clinical-phase-treeview-search.js"></script>
<script src="${contextPath}/resources/components/treeview-search/pharmacokinetics-treeview-search.js"></script>
<script src="${contextPath}/resources/components/treeview-search/toxicity-treeview-search.js"></script>
<script src="${contextPath}/resources/components/treeview-search/assay-method-treeview-search.js"></script>
<script src="${contextPath}/resources/components/treeview-search/bibliography-treeview-search.js"></script>
<script src="${contextPath}/resources/components/treeview-search/activity-type-treeview-search.js"></script>
<script src="${contextPath}/resources/components/treeview-search/indication-treeview-search.js"></script>
<script src="${contextPath}/resources/components/treeview-search/treeview-container.js"></script>


<!-- shared data scripts -->

<script src="${contextPath}/resources/store/shared/actions.js"></script>
<script src="${contextPath}/resources/store/shared/getters.js"></script>
<script src="${contextPath}/resources/store/shared/mutations.js"></script>
<script src="${contextPath}/resources/store/shared/state.js"></script>


<!-- bibliogrphy search scripts -->
<script src="${contextPath}/resources/store/bibliography-search/actions.js"></script>
<script src="${contextPath}/resources/store/bibliography-search/getters.js"></script>
<script src="${contextPath}/resources/store/bibliography-search/mutations.js"></script>
<script src="${contextPath}/resources/store/bibliography-search/state.js"></script>

<!-- bibliography advance search components -->
<script src="${contextPath}/resources/components/bibliography-advance-search/list-search.js"></script>
<script src="${contextPath}/resources/components/bibliography-advance-search/criterion-search.js"></script>
<script src="${contextPath}/resources/components/bibliography-advance-search/custom-search.js"></script>
<script src="${contextPath}/resources/components/bibliography-advance-search/bibliography-search.js"></script>


<!-- Indication advance search components -->
<script src="${contextPath}/resources/components/indication-advance-search/indication-advance-search.js"></script>

<!-- structure search scripts -->
<script src="${contextPath}/resources/store/structure-search/actions.js"></script>
<script src="${contextPath}/resources/store/structure-search/getters.js"></script>
<script src="${contextPath}/resources/store/structure-search/mutations.js"></script>
<script src="${contextPath}/resources/store/structure-search/state.js"></script>



<script src="${contextPath}/resources/store/user/state.js"></script>
<script src="${contextPath}/resources/components/reusable/loader.js" ></script>
<script src="${contextPath}/resources/components/reusable/auto-complete.js"></script>
<script src="${contextPath}/resources/components/reusable/multi-select.js"></script>
<script src="${contextPath}/resources/components/reusable/target-search.js"></script>
<script src="${contextPath}/resources/components/draw-structure/marvin-draw-structure.js"></script>

<!-- structure advance search components -->
<script src="${contextPath}/resources/components/structure-advance-search/chemistry-search.js"></script>
<script src="${contextPath}/resources/components/structure-advance-search/term-search.js"></script>
<script src="${contextPath}/resources/components/structure-advance-search/property-search/property-search.js"></script>
<script src="${contextPath}/resources/components/structure-advance-search/property-search/structural-property.js"></script>
<script src="${contextPath}/resources/components/structure-advance-search/property-search/physico-chemical.js"></script>
<script src="${contextPath}/resources/components/structure-advance-search/structure-search.js"></script>

<!-- source search scripts -->
<script src="${contextPath}/resources/store/source-search/actions.js"></script>
<script src="${contextPath}/resources/store/source-search/getters.js"></script>
<script src="${contextPath}/resources/store/source-search/mutations.js"></script>
<script src="${contextPath}/resources/store/source-search/state.js"></script>


<!-- source advance search components -->
<script src="${contextPath}/resources/components/source-advance-search/source-list-search.js"></script>
<script src="${contextPath}/resources/components/source-advance-search/strain-search.js"></script>
<script src="${contextPath}/resources/components/source-advance-search/source-search.js"></script>


<!-- advance search search scripts -->

<script src="${contextPath}/resources/store/advance-search/actions.js"></script>
<script src="${contextPath}/resources/store/advance-search/getters.js"></script>
<script src="${contextPath}/resources/store/advance-search/mutations.js"></script>
<script src="${contextPath}/resources/store/advance-search/state.js"></script>

<script src="${contextPath}/resources/js/simple-search.js"></script>
<script src="${contextPath}/assets/scripts/login.js" type="text/javascript"></script>
<script src="${contextPath}/resources/components/reusable/read-more.js"></script>
</html>