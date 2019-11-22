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

<html>
<head>
    <meta charset="utf-8" />
    <title>GOSTAR - Visualization</title>
    <meta name="description" content="GoStar Search">
    <meta name="viewport" content="width=device-width, initial-scale=1,
         maximum-scale=1, shrink-to-fit=no">
    <!--begin::Web font -->
    <script
            src="https://ajax.googleapis.com/ajax/libs/webfont/1.6.16/webfont.js"></script>
    <script>
        WebFont.load({
            google: {
                "families": ["Poppins:300,400,500,600,700", "Roboto:300,400,500,600,700", "Asap+Condensed:500"]
            },
            active: function () {
                sessionStorage.fonts = true;
            }
        });
    </script>
    <!--end::Web font -->
    <!--begin::Page Vendors Styles -->
    <link href="${contextPath}/assets/styles/fullcalendar.bundle.css" rel="stylesheet"
          type="text/css" />
    <!--end::Page Vendors Styles -->
    <!--begin::Base Styles -->
    <link href="${contextPath}/assets/styles/vendors.bundle.css" rel="stylesheet"
          type="text/css" />
    <!--begin style bundle -->
    <link href="${contextPath}/assets/styles/style.bundle.css" rel="stylesheet"
          type="text/css" />
    <!-- 'rSlder' Slider range component script -->
    <!-- <link href="${contextPath}/assets/styles/rSlider.min.css" rel="stylesheet" type="text/css" /> -->
    <!--end::Base Styles -->
    <link rel="shortcut icon" href="${contextPath}/assets/images/favicon.ico" />
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


            <script src="https://d3js.org/d3.v3.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/echarts@4.1.0/dist/echarts.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue-echarts@4.0.2"></script>
    <style>
		.axis path,
		.axis line {
		fill: none;
		stroke: black;
		shape-rendering: crispEdges;
		}

		.axis text {
		font-family: sans-serif;
		font-size: 11px;
		}

		.tooltip {
			position: absolute;
		}
	</style>

</head>
<body
        class="m-page--fluid m-page--loading-enabled m-page--loading m-header--fixed
    m-header--fixed-mobile m-footer--push m-aside--offcanvas-default">
<div id="q-app">
    

    <!-- Top header bar background color -->
    <div>

        <!-- begin::Page loader -->
        <div class="m-page-loader m-page-loader--base">
            <div class="m-blockui"> <span>Please wait...</span> <span>
              <div class="m-loader m-loader--brand"></div>
            </span> </div>
        </div>

        <!-- end::Page Loader -->

        <!-- begin:: Page -->
        <div class="m-grid m-grid--hor m-grid--root m-page">
            <!-- begin::Header -->
            <header id="m_header" class="m-grid__item m-header"
                    m-minimize="minimize" m-minimize-mobile="minimize"
                    m-minimize-offset="10" m-minimize-mobile-offset="10">
                <!--<div class="m-header__top">
              <div class="m-container m-container--fluid m-container--full-height m-page__container">

              </div>
            </div>
            <div class="m-header__bottom">
              <div class="m-container m-container--fluid m-container--full-height m-page__container">
                <div class="m-stack m-stack--ver m-stack--desktop"> </div>
              </div>
            </div> -->
            </header>
            <!-- end::Header -->
            <!-- begin::Body -->
            <div
                    class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop
            m-grid--desktop m-page__container m-body">
                <div class="m-grid__item m-grid__item--fluid m-wrapper">
                    <!-- START Top Menu bar block -->
                    <!-- Tabination selection part - Starts -->
                    <div class="row" style="height: 60px;">
                        <div class="col-2">

                            <div class="m-stack__item m-stack__item--middle
                    m-brand__logo"> <a href="index.html"
                                       class="m-brand__logo-wrapper"> <img alt=""
                                                                           src="${contextPath}/assets/images/Gostar_Logo_mediam.png"
                                                                           class="m-brand__logo-desktop" style="width:180px" />
                            </a> </div>

                        </div>
                        <div class="col-8">
                            <div class="m-portlet__head-tools">
                                <ul class="nav nav-tabs2 m-tabs-line m-tabs-line--primary
                      m-tabs-line--2x" role="tablist">
                                    <li class="nav-item2 m-tabs__item"> <a class="nav-link
                          m-tabs__link" 
                                                                           href="#m_tabs_6_1" role="tab"> Dashboard </a> </li>
                                    <li class="nav-item2 m-tabs__item"> <a class="nav-link
                          m-tabs__link " 
                                                                           href="${contextPath}/welcome" role="tab"> Search </a> </li>
                                    <li class="nav-item2 m-tabs__item"> <a class="nav-link
                          m-tabs__link" 
                                                                           href="${contextPath}/visualization/${usersession}" role="tab"> Visualization </a> </li>
                                <li class="nav-item2 m-tabs__item"> <a class="nav-link
                                                                                                                                        m-tabs__link" 
                                    href="${contextPath}/tabularview/${usersession}" role="tab"> Tabular View </a>
                                </li>
                                <li class="nav-item2 m-tabs__item"> <a class="nav-link
                                  m-tabs__link active" 
                                href="${contextPath}/heatmap/${usersession}" role="tab"> Heat Map Analyzer </a>
                                </li>
                                <li class="nav-item2 m-tabs__item"> <a class="nav-link
                                  m-tabs__link" 
                                href="${contextPath}/moleularanalyzerdata/${usersession}" role="tab"> Molecular Pair Query Analyzer </a>
                                </li>
                                    <li class="nav-item2 m-tabs__item"> <a class="nav-link
                          m-tabs__link" 
                                                                           href="#m_tabs_6_4" role="tab"> Search History </a>
                                    </li>
                                    <li class="nav-item2 m-tabs__item"> <a class="nav-link
                          m-tabs__link" 
                                                                           href="#m_tabs_6_5" role="tab"> Downloads </a> </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-2">
                            <div class="m-stack__item m-topbar__nav-wrapper"
                                 style="float:right">
                                <ul class="m-topbar__nav m-nav m-nav--inline">
                                    <!-- <li class="m-nav__item m-nav__item--focus m-dropdown m-dropdown--large m-dropdown--arrow m-dropdown--align-center m-dropdown--mobile-full-width m-dropdown--skin-light	m-list-search m-list-search--skin-light" m-dropdown-toggle="click" m-dropdown-persistent="1"
                                                             id="m_quicksearch" m-quicksearch-mode="dropdown"> <a href="#" class="m-nav__link m-dropdown__toggle"> <span class="m-nav__link-icon"> <span class="m-nav__link-icon-wrapper"> <i class="flaticon-search-1"></i> </span> </span> </a>
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
                                    </li>-->
                                    <!-- <li class="m-nav__item m-nav__item--accent m-dropdown m-dropdown--large m-dropdown--arrow m-dropdown--align-center 	m-dropdown--mobile-full-width" m-dropdown-toggle="click" m-dropdown-persistent="1"> <a href="#" class="m-nav__link m-dropdown__toggle" id="m_topbar_notification_icon"> <span class="m-nav__link-badge m-badge m-badge--dot m-badge--dot-small m-badge--danger"></span> <span class="m-nav__link-icon"> <span class="m-nav__link-icon-wrapper"> <i class="flaticon-alarm"></i> </span> </span> </a>
                                    </li> -->
                                    <!-- <li class="m-nav__item m-nav__item--danger m-dropdown m-dropdown--skin-light m-dropdown--large m-dropdown--arrow m-dropdown--align-right m-dropdown--align-push m-dropdown--mobile-full-width m-dropdown--skin-light" m-dropdown-toggle="click"> <a href="#" class="m-nav__link m-dropdown__toggle"> <span class="m-nav__link-badge m-badge m-badge--dot m-badge--info m--hide"></span> <span class="m-nav__link-icon"> <span class="m-nav__link-icon-wrapper"> <i class="flaticon-share"></i> </span> </span> </a>
                                    </li> -->
                                    <li
                                            class="m-nav__item m-dropdown m-dropdown--medium
                        m-dropdown--arrow m-dropdown--align-right
                        m-dropdown--mobile-full-width m-dropdown--skin-light"
                                            m-dropdown-toggle="click"> <a href="#"
                                                                          class="m-nav__link m-dropdown__toggle"> <span
                                            class="m-topbar__userpic"> <img
                                            src="${contextPath}/assets/images/users/user_profile_pic.png"
                                            class="m--img-rounded m--marginless
                              m--img-centered" alt="" style="height:30px" />
                          </span>
                                        <span class="m-nav__link-icon m-topbar__usericon
                            m--hide"> <span
                                                class="m-nav__link-icon-wrapper"> <i
                                                class="flaticon-user-ok"></i> </span> </span>
                                        <span
                                                class="m-topbar__username m--hide">User</span> </a>
                                        <div class="m-dropdown__wrapper"> <span
                                                class="m-dropdown__arrow m-dropdown__arrow--right
                            m-dropdown__arrow--adjust"></span>
                                            <div class="m-dropdown__inner">
                                                <div class="m-dropdown__header m--align-center">
                                                    <div class="m-card-user m-card-user--skin-light">
                                                        <div class="m-card-user__pic"> <img
                                                                src="${contextPath}/assets/images/users/user_profile_pic.png"
                                                                class="m--img-rounded m--marginless" alt=""
                                                        /> </div>
                                                        <div class="m-card-user__details"> <span
                                                                class="m-card-user__name
                                    m--font-weight-500">${username}</span> <a href=""
                                                   class="m-card-user__email m--font-weight-300
                                    m-link">user.name@gostar.com</a> </div>
                                                    </div>
                                                </div>
                                                <div class="m-dropdown__body">
                                                    <div class="m-dropdown__content">
                                                        <ul class="m-nav m-nav--skin-light">
                                                            <li class="m-nav__section m--hide"> <span
                                                                    class="m-nav__section-text">Section</span>
                                                            </li>
                                                            <li class="m-nav__item"> <a
                                                                    href="#" class="m-nav__link">
                                                                <i
                                                                        class="m-nav__link-icon
                                        flaticon-profile-1"></i> <span
                                                                    class="m-nav__link-title">
                                        <span class="m-nav__link-wrap"> <span
                                                class="m-nav__link-text">My Profile</span>
                                          <span class="m-nav__link-badge"> <span
                                                  class="m-badge m-badge--success">2</span>
                                          </span> </span> </span> </a> </li>
                                                            <li class="m-nav__item"> <a
                                                                    href="#" class="m-nav__link">
                                                                <i
                                                                        class="m-nav__link-icon flaticon-share"></i>
                                                                <span
                                                                        class="m-nav__link-text">Activity</span>
                                                            </a> </li>
                                                            <li class="m-nav__item"> <a
                                                                    href="#" class="m-nav__link">
                                                                <i
                                                                        class="m-nav__link-icon
                                        flaticon-chat-1"></i> <span
                                                                    class="m-nav__link-text">Messages</span>
                                                            </a> </li>
                                                            <li class="m-nav__separator
                                    m-nav__separator--fit"> </li>
                                                            <li class="m-nav__item"> <a
                                                                    href="#" class="m-nav__link">
                                                                <i
                                                                        class="m-nav__link-icon flaticon-info"></i>
                                                                <span
                                                                        class="m-nav__link-text">FAQ</span> </a>
                                                            </li>
                                                            <li class="m-nav__item"> <a
                                                                    href="#" class="m-nav__link">
                                                                <i
                                                                        class="m-nav__link-icon
                                        flaticon-lifebuoy"></i> <span
                                                                    class="m-nav__link-text">Support</span>
                                                            </a> </li>
                                                            <li class="m-nav__separator
                                    m-nav__separator--fit"> </li>
                                                            <li class="m-nav__item"> <a
                                                                    href="#" onclick="javascript:document.logoutform.submit()"
                                                                    class="btn m-btn--pill btn-secondary m-btn
                                      m-btn--custom m-btn--label-brand
                                      m-btn--bolder">Logout</a>
                                                            </li>
                                                            <form id="logoutform" name="logoutform" action="${contextPath}/appLogout" method="post">
                                                                <input type="hidden" name="currentSession" value="${usersession}"/>
                                                            </form>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li id="m_quick_sidebar_toggle" class="m-nav__item
                        m-nav__item--info m-nav__item--qs"> <a href="#"
                                                               class="m-nav__link m-dropdown__toggle"> <span
                                            class="m-nav__link-icon m-nav__link-icon-alt"> <span
                                            class="m-nav__link-icon-wrapper"> <i
                                            class="flaticon-grid-menu"></i> </span> </span>
                                    </a>
                                    </li>
                                </ul>
                            </div>
                        </div>


                    </div>

                    <!-- Tabination selection part - Ends -->

                    <!-- ENDS Top Menu bar block -->
                    <!-- BEGIN: Subheader -->
                    <div class="m-subheader">
                        <div class="d-flex align-items-center contr-hedr-brd br-hdr-sty"
                             style="background-color:#62c0e1; height: 60px;">
                            <div class="mr-auto">
                                <h3 class="m-subheader__title
                      m-subheader__title--separator"><i class="m-nav__link-icon
                        la la-home"
                                                        style="font-size:1.5rem"></i> HeatMap Analyzer</h3>
                                <ul class="m-subheader__breadcrumbs m-nav m-nav--inline">
                                    <li class="m-nav__separator"></li>
                                    <li class="m-nav__item"> </li>
                                </ul>
                            </div>
                        </div>

                        <!-- END: Subheader -->
                        <div class="">
                            <div class="m-portlet__body  m-portlet__body--no-padding">
                                <!--  START Sample Cards  -->
                                <div class="">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <div class="dash-card bg-c-blue order-card">
                                                <div class="card-block">
                                                    <h5 class="m-b-20">Activity</h5>
                                                    <h2 class="text-right"><i class="fa fa-diagnoses f-left"></i><span> <span
                                                                class="m-widget24__desc"></span> <span class="m-widget24__stats txt-sz-crd">
                                                                
                                                                <span >879654</span> </span></span></h2>
                                                    <!--  <p class="m-b-0">Activity Text Description<span class="f-right">351</span></p>  -->
                        
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="dash-card bg-c-green order-card">
                                                <div class="card-block">
                                                    <h5 class="m-b-20">Reference</h5>
                                                    <h2 class="text-right"><i class="fa fa-project-diagram f-left"></i><span><span
                                                                class="m-widget24__desc"></span> <span class="m-widget24__stats txt-sz-crd">
                                                                
                                                                <span >54789</span> </span></span></h2>
                                                    <!--   <p class="m-b-0">Reference Text Description<span class="f-right">351</span></p>  -->
                                                </div>
                                            </div>
                                        </div>
                        
                                        <div class="col-md-3">
                                            <div class="dash-card bg-c-yellow order-card">
                                                <div class="card-block">
                                                    <h5 class="m-b-20">GVK ID</h5>
                                                    <h2 class="text-right"><i class="fa fa-address-card f-left"></i><span><span
                                                                class="m-widget24__desc"></span> <span class="m-widget24__stats txt-sz-crd">
                                                                
                                                                <span >25478</span> </span></span></h2>
                                                    <!--  <p class="m-b-0">GVK ID Text Description<span class="f-right">351</span></p>  -->
                                                </div>
                                            </div>
                                        </div>
                        
                                        <div class="col-md-3">
                                            <div class="dash-card bg-c-pink order-card">
                                                <div class="card-block">
                                                    <!-- <pre>
                                            {{$store.state.simpleSearch.simpleSearchRowData}}
                                          </pre> -->
                                                    <h5 class="m-b-20">Structure</h5>
                        
                                                    <h2 class="text-right"><i class="fa fa-bezier-curve f-left"></i><span><span
                                                                class="m-widget24__desc"></span> <span class="m-widget24__stats txt-sz-crd">
                                                               
                                                                <span >25478</span> </span></span></h2>
                                                    <!--  <p class="m-b-0">Structure Text Description<span class="f-right">351</span></p>  -->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--  ENDS Sample Cards -->
                            </div>
                        </div>
                        <!-- STARTS Container block with chart related information -->
                        <!-- Container block start from here -->
                        <div class=""></div>
                        <!--begin::Portlet-->
                        <div class="">
                            <div class="m-portlet__head">
                                <div class="m-portlet__head-caption">
                                    <div class="m-portlet__head-title">
                                        <div class="row col-md-12">
                                            <div class="col-md-3" id="showHeatMapResults">
                                                <div>
                                                    <label>Target List</label>
                                                    <select class="custom-select" id="heatMapTargetList">
                                                        <option value="0">Select Option</option>
                                                        <option value="526">AURORA KINASE A</option>
                                                        <option value="527">AURORA KINASE B</option>
                                                        <option value="528">AURORA KINASE C</option>
                                                    </select>
                                                </div>
                                                <div>
                                                    <label>Activity Type</label>
                                                    <select class="custom-select" id="heatMapActivityType">
                                                        <option value="">Select Option</option>
                                                        <option value="IC50">IC50/PIC50</option>
                                                        <option value="KI">KI/PKI</option>
                                                    </select>
                                                </div>
                                                <div>
                                                    <label>Source</label>
                                                    <select class="custom-select" id="heatMapSource">
                                                        <option value="0">Select Option</option>
                                                        <option value="117">Human</option>
                                                        <option value="115">Mouse</option>
                                                        <option value="116">RAT</option>
                                                    </select>
                                                </div>
                                                <div>
                                                    <label>Activity Prefix</label>
                                                    <select class="custom-select" id="heatMapActivityPrefix" >
                                                        <option value="">Select Option</option>
                                                        <option value="0" selected>Any</option>
                                                        <option value="1">&lt;</option>
                                                        <option value="2">&gt;</option>
                                                        <option value="3">=</option>
                                                    </select>
                                                </div>
                                                <div>
                                                    <label>Target Type</label>
                                                    <select class="custom-select" id="heatMapTargetType" >
                                                        <option value="">Select Option</option>
                                                        <option value="Primary">Primary</option>
                                                        <option value="Secondary&Pofile">Secondary&Pofile</option>
                                                        <option value="Any">Any</option>
                                                    </select>
                                                </div>   
                                                <input onclick="generateHeapMapResults()" style="margin-top:10px" value="Generate HeatMap" type="button"/>
                                            </div>
                                            <div class="col-md-5">
                                                <div class="heatmap" ></div>
                                                <button onclick="prevPage()" id="btn_prev">Prev</button>
                                                <button onclick="nextPage()" id="btn_next">Next</button>
                                                <a class="nav-link"
                                                    href="${contextPath}/tabularview/${usersession}" > View Records in Tab Tabular View </a>
                                            </div>
                                            <div class="col-md-4" id="heatMapDetails">
                                                <img id="molecularImgPath" src=""/> <br/>
                                                <!-- <div>
                                                    STR ID :  <span id="strIdVal">0</span> <br/>
                                                    Target Name : <span id="targetNameVal"></span> <br/>
                                                    Micro Value : <span id="microVal">0</span> <br/>
                                                </div> -->
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                        <!--end::Portlet-->
                    </div>
                    <!-- ENDS Container block with chart related information -->
                </div>
            </div>

            <!-- end::Body -->

            <!-- begin::Footer -->
            <footer class="m-grid__item m-footer">
                <div class="m-container m-container--fluid
              m-container--full-height
              m-page__container">
                    <div class="m-footer__wrapper">
                        <div class="m-stack m-stack--flex-tablet-and-mobile
                  m-stack--ver
                  m-stack--desktop">
                            <div class="m-stack__item m-stack__item--left
                    m-stack__item--middle m-stack__item--last"> <span
                                    class="m-footer__copyright"> 2018 &copy; <a
                                    href="https://www.gostardb.com/gostar/"
                                    target="_blank"
                                    class="m-link">GOSTAR - Excelra</a> </span> </div>
                            <div class="m-stack__item m-stack__item--right
                    m-stack__item--middle m-stack__item--first">
                                <ul class="m-footer__nav m-nav m-nav--inline
                      m--pull-right">
                                    <li class="m-nav__item"> <a href="#"
                                                                class="m-nav__link">
                                        <span class="m-nav__link-text">About</span>
                                    </a> </li>
                                    <li class="m-nav__item"> <a href="#"
                                                                class="m-nav__link">
                          <span
                                  class="m-nav__link-text">Privacy</span> </a> </li>
                                    <li class="m-nav__item"> <a href="#"
                                                                class="m-nav__link">
                                        <span class="m-nav__link-text">T&C</span>
                                    </a> </li>
                                    <li class="m-nav__item" style="color:#ffffff"> <a
                                            href="#"
                                            class="m-nav__link" data-toggle="m-tooltip"
                                            title="Support Center" data-placement="left"> <i
                                            class="m-nav__link-icon flaticon-info
                            m--icon-font-size-lg3"></i> </a> </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </footer>
            <!-- end::Footer -->
        </div>
        <!-- end:: Page -->
        <!-- target search modal popup start -->

        <!-- target search modal popup end -->


        <div id="m_scroll_top" class="m-scroll-top"> <i class="la
            la-arrow-up"></i></div>
        <!-- end::Scroll Top -->

        <!-- end::Page Loader -->
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

<script src="//d3js.org/d3.v5.min.js"></script>
<script src="${contextPath}/resources/js/libraries/polyfills/quasar.ie.polyfills.umd.min.js"></script>
<script src="${contextPath}/resources/js/libraries/quasar.umd.min.js"></script>
<script src="${contextPath}/resources/js/libraries/fontawesome-v5.umd.min.js"></script>
<script src="${contextPath}/resources/js/libraries/vuex.min.js"></script>
<script src="${contextPath}/resources/components/reusable/theme.js"></script>
<script src="${contextPath}/resources/components/reusable/loader.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/data-analyzer/heatMapData.js"></script>
<script>
    $(document).ready(function () {
      getHeatMapData();      
    }); 

    function getHeatMapData() {
      $.ajax({
        type: "GET",
        contentType: "application/json",
        url: '${contextPath}/security/analyzers/heapmapanalyzerdata',
        headers: { 'userSessionId' : window.config.userSessionId },              
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {                 
          console.log(data);
          $('#heatMapDetails').show();
          /* $('#strIdVal').html(data.heatMapResultsData[0].strId);
          $('#targetNameVal').html(data.heatMapResultsData[0].commonName);
          $('#microVal').html(data.heatMapResultsData[0].activityValue); */
          $('#molecularImgPath').attr('src','../assets/images/strsmileimages/'+data.heatMapResultsData[0].strId+'.png');
          generateHeatMap(data.heatMapResultsData);              
        },
        error: function (e) {             
          console.log(e);
        }
      });
    }

    function generateHeapMapResults() {
      var data = {
        proteinName:$('#heatMapTargetList').val(),
        activityType:$('#heatMapActivityType').val(),
        activitySource:$('#heatMapSource').val(),   
        activityPrefix:$('#heatMapActivityPrefix').val(), 
        targetType:$('#heatMapTargetType').val(), 
      }
      $.ajax({
        type: "POST",
        contentType: "application/json",
        url: '${contextPath}/security/analyzers/heapmapanalyzerdata',
        headers: { 'userSessionId' : window.config.userSessionId },
        data: JSON.stringify(data),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {                 
          console.log(data);
          if(data.heatMapResultsData != undefined && data.heatMapResultsData.length > 0) {
            $('#heatMapDetails').show();
            /* $('#strIdVal').html(data.heatMapResultsData[0].strId);
            $('#targetNameVal').html(data.heatMapResultsData[0].commonName);
            $('#microVal').html(data.heatMapResultsData[0].activityValue); */
            $('#molecularImgPath').attr('src','../assets/images/strsmileimages/'+data.heatMapResultsData[0].strId+'.png');
            d3.select("svg").remove();
            generateHeatMap(data.heatMapResultsData);  
          } else {
            $('#heatMapDetails').hide();
            d3.select("svg").remove();
          }
                      
        },
        error: function (e) {             
          console.log(e);
        }
      });      
    }   

</script>
</html>
