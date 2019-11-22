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
    <title>GOSTAR - Tabular View</title>
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

    <!-- Do you need all animations? -->
    <!--
       Finally, add Quasar's CSS:
       Replace version below (1.0.0-beta.0) with your desired version of Quasar.
       Add ".rtl" for the RTL support (example: quasar.rtl.min.css).
       -->

       <!-- <link href="${contextPath}/resources/css/app.css" rel="stylesheet" /> -->

       <link
     rel="stylesheet"
     href="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/styles/jqx.base.css"
     type="text/css"
   />
       <link
     rel="stylesheet"
     href="https://cdn.rawgit.com/claviska/jquery-alertable/master/jquery.alertable.css"
     type="text/css"
   />


    <!-- Add this to <head> -->
    <!-- Load required Bootstrap and BootstrapVue CSS -->

            <link type="text/css" rel="stylesheet" href="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/styles/jqx.light.css"></link>

<script src="${contextPath}/assets/scripts/vendors.bundle.js" type="text/javascript"></script>
<!--begin::bundle scripts -->
<script src="${contextPath}/assets/scripts/scripts.bundle.js" type="text/javascript"></script>

            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/scripts/jquery-1.12.4.min.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxcore.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxtabs.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxbuttons.js"></script>

            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxdata.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxscrollbar.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxlistbox.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxdropdownlist.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxmenu.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxgrid.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxgrid.filter.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxgrid.sort.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxgrid.selection.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxpanel.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxcalendar.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxdatetimeinput.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxcheckbox.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxinput.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxtooltip.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxpopover.js"></script>

            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/globalization/globalize.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxgrid.pager.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxgrid.selection.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxgrid.columnsreorder.js"></script>
            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/jqwidgets/jqxnotification.js"></script>

            <script type="text/javascript" src="${contextPath}/resources/jqwidgets-ver8.0.0/scripts/demos.js"></script>
             <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.2.2/jszip.min.js"></script>
           <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Base64/1.0.2/base64.js"></script>
          <script src="https://cdn.jsdelivr.net/npm/@cryptolize/FileSaver@1.0.0/FileSaver.min.js" type="text/javascript"></script>
           <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>-->
         <script src=" https://cdn.jsdelivr.net/npm/base64toblob@0.0.2/example/main.js" type="text/javascript"></script>

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
                            <!-- Logo resource code -->
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
                                                                                                                                        m-tabs__link active"
                                    href="${contextPath}/tabularview/${usersession}" role="tab"> Tabular View </a>
                                </li>
                                <li class="nav-item2 m-tabs__item"> <a class="nav-link
                                                                      m-tabs__link" 
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
                                                                           href="${contextPath}/downloads/${usersession}" role="tab"> Downloads </a> </li>
                                </ul>
                            </div>
                        </div>
                      <div class="col-2">
                        <div class="m-stack__item m-topbar__nav-wrapper" style="float:right">
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
                            <li class="m-nav__item m-dropdown m-dropdown--medium
                                              m-dropdown--arrow m-dropdown--align-right
                                              m-dropdown--mobile-full-width m-dropdown--skin-light" m-dropdown-toggle="click"> <a href="#"
                                class="m-nav__link m-dropdown__toggle"> <span class="m-topbar__userpic"> <img
                                    src="${contextPath}/assets/images/users/user_profile_pic.png" class="m--img-rounded m--marginless
                                                    m--img-centered" alt="" style="height:30px" />
                                </span>
                                <span class="m-nav__link-icon m-topbar__usericon
                                                  m--hide"> <span class="m-nav__link-icon-wrapper"> <i class="flaticon-user-ok"></i> </span>
                                </span>
                                <span class="m-topbar__username m--hide">User</span> </a>
                              <div class="m-dropdown__wrapper"> <span class="m-dropdown__arrow m-dropdown__arrow--right
                                                  m-dropdown__arrow--adjust"></span>
                                <div class="m-dropdown__inner">
                                  <div class="m-dropdown__header m--align-center">
                                    <div class="m-card-user m-card-user--skin-light">
                                      <div class="m-card-user__pic"> <img src="${contextPath}/assets/images/users/user_profile_pic.png"
                                          class="m--img-rounded m--marginless" alt="" /> </div>
                                      <div class="m-card-user__details"> <span class="m-card-user__name
                                                          m--font-weight-500">${username}</span> <a href="" class="m-card-user__email m--font-weight-300
                                                          m-link">user.name@gostar.com</a> </div>
                                    </div>
                                  </div>
                                  <div class="m-dropdown__body">
                                    <div class="m-dropdown__content">
                                      <ul class="m-nav m-nav--skin-light">
                                        <li class="m-nav__section m--hide"> <span class="m-nav__section-text">Section</span>
                                        </li>
                                        <li class="m-nav__item"> <a href="#" class="m-nav__link">
                                            <i class="m-nav__link-icon
                                                              flaticon-profile-1"></i> <span class="m-nav__link-title">
                                              <span class="m-nav__link-wrap"> <span class="m-nav__link-text">My Profile</span>
                                                <span class="m-nav__link-badge"> <span class="m-badge m-badge--success">2</span>
                                                </span> </span> </span> </a> </li>
                                        <li class="m-nav__item"> <a href="#" class="m-nav__link">
                                            <i class="m-nav__link-icon flaticon-share"></i>
                                            <span class="m-nav__link-text">Activity</span>
                                          </a> </li>
                                        <li class="m-nav__item"> <a href="#" class="m-nav__link">
                                            <i class="m-nav__link-icon
                                                              flaticon-chat-1"></i> <span class="m-nav__link-text">Messages</span>
                                          </a> </li>
                                        <li class="m-nav__separator
                                                          m-nav__separator--fit"> </li>
                                        <li class="m-nav__item"> <a href="#" class="m-nav__link">
                                            <i class="m-nav__link-icon flaticon-info"></i>
                                            <span class="m-nav__link-text">FAQ</span> </a>
                                        </li>
                                        <li class="m-nav__item"> <a href="#" class="m-nav__link">
                                            <i class="m-nav__link-icon
                                                              flaticon-lifebuoy"></i> <span class="m-nav__link-text">Support</span>
                                          </a> </li>
                                        <li class="m-nav__separator
                                                          m-nav__separator--fit"> </li>
                                        <li class="m-nav__item"> <a href="#" onclick="javascript:document.logoutform.submit()" class="btn m-btn--pill btn-secondary m-btn
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
                                              m-nav__item--info m-nav__item--qs"> <a href="#" class="m-nav__link m-dropdown__toggle"> <span
                                  class="m-nav__link-icon m-nav__link-icon-alt"> <span class="m-nav__link-icon-wrapper"> <i
                                      class="flaticon-grid-menu"></i> </span> </span>
                              </a>
                            </li>
                          </ul>
                        </div>
                      </div>

                    </div>


                    <!-- selection part - Ends -->

                    <!-- ENDS Top Menu bar block -->
                    <!-- BEGIN: Subheader -->
                    <div class="m-subheader">
                        <div class="d-flex align-items-center contr-hedr-brd br-hdr-sty"
                             style="background-color:#62c0e1; height: 60px;">
                            <div class="mr-auto">
                                <h3 class="m-subheader__title
                      m-subheader__title--separator"><i class="m-nav__link-icon
                        la la-home"
                                                        style="font-size:1.5rem"></i> Tabular View</h3>
                                <ul class="m-subheader__breadcrumbs m-nav m-nav--inline">
                                    <li class="m-nav__separator"></li>
                                    <li class="m-nav__item"> </li>
                                </ul>
                            </div>
                        </div>
<div class="default">
  <!-- <div id="output_data"></div> -->

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
                                                                
                                                                <span id="actIdsCount" >0</span> </span></span></h2>
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
                                                                
                                                                <span id="refIdsCount" >0</span> </span></span></h2>
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
                                                                
                                                                <span id="gvkIdsCount" >0</span> </span></span></h2>
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
                                                               
                                                                <span id="strIdsCount"  >0</span> </span></span></h2>
                                                    <!--  <p class="m-b-0">Structure Text Description<span class="f-right">351</span></p>  -->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--  ENDS Sample Cards -->
                            </div>
                        </div>


     <input onclick="exportTabularViewData()" style="margin-top: 10px;" value="Export CSV Data" type="button" />
    <input onclick="exportExcelTabularViewData()" style="margin-top: 10px;" value="Export Excel Data" type="button" />

    <input onclick="exportTabularViewDataSingleFile()" style="..." value ="Export TSV Data"  type="button"/>

    <input onclick="exportTabularViewStrcutureDataSingleFileInSDFFormat()" style="..." value ="Export Structure Tab SDF Data"  type="button"/>

    <input onclick="MyDownloads()"  style="..." value ="MyDownloads"  type="button""/>

    <%--<input onclick="pngCheck()"  style="..." value ="PngTest"  type="button""/>

     <input onclick="FlatFileTest()"  style="..." value ="PngTest"  type="button""/>

    <input onclick="generateImagesForStructureSmiles()"  style="..." value ="StrcutureSmilesPng"  type="button"/>--%>


    <div id='jqxlistbox'>
</div>

<!-- <div id="output_data"></div> -->

  <div id="jqxWidget">
    <div style="float: left;" id="jqxTabs">
      <ul style="margin-left: 30px;">
        <li>Activity</li>
        <li>Reference</li>
        <li>Structure</li>
        <li>Assay</li>
      </ul>
      <div>
        <div id="activity-grid"></div>
        <input onclick="resetTabularViewData(gbl_tabularview_store.activity.currentTab)" style="margin-top: 10px;" value="Remove Filter" id="activity-grid-clearfilteringbutton" type="button" />
      </div>

      <div>
        <div id="reference-grid"></div>
        <input onclick="resetTabularViewData(gbl_tabularview_store.reference.currentTab)" style="margin-top: 10px;" value="Remove Filter" id="reference-grid-clearfilteringbutton" type="button" />
      </div>
      <div>
      <div id="structure-grid"></div>
      <input onclick="resetTabularViewData(gbl_tabularview_store.structure.currentTab)" style="margin-top: 10px;" value="Remove Filter" id="structure-grid-clearfilteringbutton" type="button" />
      </div>
      <div>
        <div id="assay-grid"></div>
        <input onclick="resetTabularViewData(gbl_tabularview_store.assay.currentTab)" style="margin-top: 10px;" value="Remove Filter" id="assay-grid-clearfilteringbutton" type="button" />
      </div>
    </div>
  </div>

</div>

                    </div>
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
                              m-stack__item--middle m-stack__item--last"> <span class="m-footer__copyright"> 2018 &copy; <a
                        href="https://www.gostardb.com/gostar/" target="_blank" class="m-link">GOSTAR - Excelra</a> </span> </div>
                  <div class="m-stack__item m-stack__item--right
                              m-stack__item--middle m-stack__item--first">
                    <ul class="m-footer__nav m-nav m-nav--inline
                                m--pull-right">
                      <li class="m-nav__item"> <a href="#" class="m-nav__link">
                          <span class="m-nav__link-text">About</span>
                        </a> </li>
                      <li class="m-nav__item"> <a href="#" class="m-nav__link">
                          <span class="m-nav__link-text">Privacy</span> </a> </li>
                      <li class="m-nav__item"> <a href="#" class="m-nav__link">
                          <span class="m-nav__link-text">T&C</span>
                        </a> </li>
                      <li class="m-nav__item" style="color:#ffffff"> <a href="#" class="m-nav__link" data-toggle="m-tooltip"
                          title="Support Center" data-placement="left"> <i class="m-nav__link-icon flaticon-info
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
        <div id="alert">

        </div>



    </div>
</div>
</body>


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


</script>
<script type="text/javascript"
  src="https://rawgit.com/claviska/jquery-alertable/master/jquery.alertable.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/tabular-view-data/data.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/tabular-view-data/TabularView.js"></script>
<script type="text/javascript">
    //  On initail load remove localstorage items
    localStorage.removeItem('tabularview');
    var getGlobalCounts = function(params) {
        setTimeout(function () {
            $.ajax({
                type: params.type,
                url: params.url,
                data: params.data,
                headers: { userSessionId: window.config.userSessionId },
                contentType: "application/json"
            }).then(data => {

                $('#gvkIdsCount').html(data.gvkIdsCount)
                $('#refIdsCount').html(data.refIdsCount)
                $('#strIdsCount').html(data.strIdsCount)
                $('#actIdsCount').html(data.actIdsCount)
                $('#assayIdsCount').html(data.assayIdsCount)

            });
        }, params.delay || 0 )
    }


    $(document).ready(function () {
      // reorder tabs
      // $('#jqxTabs').jqxTabs({ width: '100%', height: 'auto', scrollPosition: 'both', reorder: true });
      $('#jqxTabs').jqxTabs({ width: '100%', height: 'auto', scrollPosition: 'both' });

      $('#jqxTabs').on('selected', function (event) {

        // DON'T HIT API IF THERE IS NO SELECTION
        var isRowSelected = false
         for (var i in gbl_tabularview_store) {
           if(gbl_tabularview_store[i]['selectedRowIdsArr']['length'] > 0 || gbl_tabularview_store[i]['unSelectedRowIdsArr']['length'] > 0) {
            isRowSelected = true
           }
         }

        //  if(!isRowSelected) {
        //    return ;
        //  }

        var currentTabIndex = event.args.item;
        getCurrentTab(currentTabIndex)

        window.getFilterResultsGlobalCount();

      });

      let params = {
        type: 'GET',
        url: window.config.apiUrl + "security/allmapping/search/visualresults/count",
        data: null,
        delay: 5000
      }
      getGlobalCounts(params)

      // var source = [
      //   {label: '(Select All)', value: '(Select All)'},
      //   {label: 'Assay', value: 'assay'},
      //   {label: 'Reference', value: 'reference'},
      //   {label: 'Structure', value: 'structure'},
      //   {label: 'Activity', value: 'activity'},
      // ];
      // Create a jqxListBox
      // $("#jqxlistbox").jqxListBox({ selectedIndex: 3, source: source, width: 200, height: 150, checkboxes: true });
      // let checkAllCounter = 1
      // for(var i=0; i < gbl_total_tabular_tabs.length; i++) {
      //   if(gbl_active_tabular_tabs.indexOf(gbl_total_tabular_tabs[i]['value']) > -1) {
      //     $("#jqxlistbox").jqxListBox('checkIndex', i)
      //     checkAllCounter += 1
      //   }
      // }

      // if(checkAllCounter == gbl_total_tabular_tabs.length) {
      //     $("#jqxlistbox").jqxListBox('checkAll')
      //   }else if(checkAllCounter == 0 ){
      //     $("#jqxlistbox").jqxListBox('unCheckAll')
      // }

      // // handle select all item.
      // var handleCheckChange = true;
      // $("#jqxlistbox").on('checkChange', function (event) {
      //   if (!handleCheckChange)
      //     return;

      //   if (event.args.label != '(Select All)') {
      //     handleCheckChange = false;
      //     $("#jqxlistbox").jqxListBox('checkIndex', 0);
      //     var checkedItems = $("#jqxlistbox").jqxListBox('getCheckedItems');
      //     var items = $("#jqxlistbox").jqxListBox('getItems');

      //     if (checkedItems.length == 1) {
      //       $("#jqxlistbox").jqxListBox('uncheckIndex', 0);
      //     }
      //     else if (items.length != checkedItems.length) {
      //       $("#jqxlistbox").jqxListBox('indeterminateIndex', 0);
      //     }
      //     handleCheckChange = true;
      //   }
      //   else {
      //     handleCheckChange = false;
      //     if (event.args.checked) {
      //       $("#jqxlistbox").jqxListBox('checkAll');
      //     }
      //     else {
      //       $("#jqxlistbox").jqxListBox('uncheckAll');
      //     }

      //     handleCheckChange = true;
      //   }
      // });
    });

    let tabularViewActivity = new TabularView(gbl_tabularview_store.activity)
    let tabularViewStructure = new TabularView(gbl_tabularview_store.structure)
    let tabularViewAssay = new TabularView(gbl_tabularview_store.assay)
    let tabularViewReference = new TabularView(gbl_tabularview_store.reference)

    // $(".jqx-menu-wrapper").css({ "z-index": 999999999 });

    var getCurrentTab = function(currentTabIndex) {
      for(let tabKey in gbl_tabularview_store) {
        if(gbl_tabularview_store[tabKey]['tabPosition'] == currentTabIndex) {
          if(tabKey) {
            $('#' + gbl_tabularview_store[tabKey]['uniqueIdentifier']).jqxGrid('updatebounddata');
          }
        }
      }
    }

    //just test
/*
    var letsTest = function () {

        // show alert if no records is selected in any tab
        if (window.config.userRoles != "ROLE_FULLUSER") {
            $.alertable.alert('Access Denied! Please contact system admin.').always(function () {
                console.log('Alert dismissed');
            });
            return;
        }

        $.ajax({
            url: '${contextPath}/security/tabularview/export/pp',
            headers: {
                'userSessionId': window.config.userSessionId,
                'Content-Type': 'application/json'
            },
            responseType: "blob", // important
            method: 'POST',
            data: JSON.stringify(exportParams),
            success: function (response) {
                const url = window.URL.createObjectURL(new Blob([response]));
                const link = document.createElement("a");
                link.href = url;
                link.setAttribute("download", "pp.jpg");
                document.body.appendChild(link);
                link.click();
            }
        });

    }*/

    //assumption all the tabs with single file
    var exportTabularViewDataSingleFile = function () {
        alert("Really Want to download tsv data ?");
        let exportParams = { selectedTabIds: {}, unSelectedTabIds: {}}
        let selectedTabIds = {}
        let showNoRecordsSelected = true
        for (let tabKey in gbl_tabularview_store) {
            var curTabData = gbl_tabularview_store[tabKey];

            if(curTabData['selectedRowIdsArr']['length'] > 0) {
                exportParams['selectedTabIds'][curTabData['currentTabPayloadId']] = curTabData['selectedRowIdsArr']
            }
            if(curTabData['unSelectedRowIdsArr']['length'] > 0) {
                exportParams['unSelectedTabIds'][curTabData['currentTabPayloadId']] = curTabData['unSelectedRowIdsArr']
            }

            var curTabSelectedRowIndexes = $("#" + curTabData.uniqueIdentifier).jqxGrid( "getselectedrowindexes" );
            if(curTabSelectedRowIndexes.length > 0) {
                showNoRecordsSelected = false;
            }
        }
        // show alert if no records is selected in any tab
        if (window.config.userRoles != "ROLE_FULLUSER") {
            $.alertable.alert('Access Denied! Please contact system admin.').always(function () {
                console.log('Alert dismissed');
            });
            return;
        }

        if (showNoRecordsSelected) {
            $.alertable.alert('Please select the checkboxes to export data.').always(function () {
                console.log('Alert dismissed');
            });
            return;
        }

        $.ajax({
            url: '${contextPath}/security/tabularview/export/download/fromtempdata',
            headers: {
                'userSessionId': window.config.userSessionId,
                'Content-Type': 'application/json'
            },
            responseType: "blob",
            // important
            method: 'POST',
            data: JSON.stringify(exportParams),
            success: function (response) {
               // console.log("hahahah" + response);
                const url = window.URL.createObjectURL(new Blob([response]));
                var zip = new JSZip();
                var timestamp = Number(new Date())
                var date = new Date(timestamp)
                var dateFullYear = date.getFullYear();
                //var dateMonth = date.getMonth()+1;
                var dateMonth =  "0" + (date.getMonth() + 1);//.slice(-2);
                var dateDate = date.getDate();
                var getHours = date.getHours();
                var getMinut = date.getMinutes();
                var getSeconds = date.getSeconds();

                var timeTimeStamp = dateFullYear + "-" + dateMonth +"-"+ dateDate + " " + getHours;
                console.log(timeTimeStamp);
               zip.file("export.tsv" ,new Blob([response]));

                zip.generateAsync({type: "blob"}).then(function(content) {
                    saveAs(content , "GoStarAllTSVData" + ".zip");
                    console.log("inside the generate function");
                }.bind(window));

            }
        });
        (function() {

            function base64ToBlob(base64, mime) {
                mime = mime || '';
                var sliceSize = 1024;
                var byteChars = window.atob(base64);
                var byteArrays = [];

                for (var offset = 0, len = byteChars.length; offset < len; offset += sliceSize) {
                    var slice = byteChars.slice(offset, offset + sliceSize);

                    var byteNumbers = new Array(slice.length);
                    for (var i = 0; i < slice.length; i++) {
                        byteNumbers[i] = slice.charCodeAt(i);
                    }

                    var byteArray = new Uint8Array(byteNumbers);

                    byteArrays.push(byteArray);
                }

                return new Blob(byteArrays, {type: mime});
            }

            if (typeof exports !== 'undefined') {
                if (typeof module !== 'undefined' && module.exports) {
                    exports = module.exports = base64ToBlob;
                }
                exports.base64ToBlob = base64ToBlob;
            } else if (typeof define === 'function' && define.amd) {
                define([], function() {
                    return base64ToBlob;
                });
            } else {
                this.base64ToBlob = base64ToBlob;
            }

        }).call(window);


        var saveAs = saveAs || (function(view) {
            "use strict";
            // IE <10 is explicitly unsupported
            if (typeof view === "undefined" || typeof navigator !== "undefined" && /MSIE [1-9]\./.test(navigator.userAgent)) {
                return;
            }
            var
                doc = view.document
                // only get URL when necessary in case Blob.js hasn't overridden it yet
                , get_URL = function() {
                    return view.URL || view.webkitURL || view;
                }
                , save_link = doc.createElementNS("http://www.w3.org/1999/xhtml", "a")
                , can_use_save_link = "download" in save_link
                , click = function(node) {
                    var event = new MouseEvent("click");
                    node.dispatchEvent(event);
                }
                , is_safari = /constructor/i.test(view.HTMLElement) || view.safari
                , is_chrome_ios =/CriOS\/[\d]+/.test(navigator.userAgent)
                , throw_outside = function(ex) {
                    (view.setImmediate || view.setTimeout)(function() {
                        throw ex;
                    }, 0);
                }
                , force_saveable_type = "application/octet-stream"
                // the Blob API is fundamentally broken as there is no "downloadfinished" event to subscribe to
                , arbitrary_revoke_timeout = 1000 * 40 // in ms
                , revoke = function(file) {
                    var revoker = function() {
                        if (typeof file === "string") { // file is an object URL
                            get_URL().revokeObjectURL(file);
                        } else { // file is a File
                            file.remove();
                        }
                    };
                    setTimeout(revoker, arbitrary_revoke_timeout);
                }
                , dispatch = function(filesaver, event_types, event) {
                    event_types = [].concat(event_types);
                    var i = event_types.length;
                    while (i--) {
                        var listener = filesaver["on" + event_types[i]];
                        if (typeof listener === "function") {
                            try {
                                listener.call(filesaver, event || filesaver);
                            } catch (ex) {
                                throw_outside(ex);
                            }
                        }
                    }
                }
                , auto_bom = function(blob) {
                    // prepend BOM for UTF-8 XML and text/* types (including HTML)
                    // note: your browser will automatically convert UTF-16 U+FEFF to EF BB BF
                    if (/^\s*(?:text\/\S*|application\/xml|\S*\/\S*\+xml)\s*;.*charset\s*=\s*utf-8/i.test(blob.type)) {
                        return new Blob([String.fromCharCode(0xFEFF), blob], {type: blob.type});
                    }
                    return blob;
                }
                , FileSaver = function(blob, name, no_auto_bom) {
                    if (!no_auto_bom) {
                        blob = auto_bom(blob);
                    }
                    // First try a.download, then web filesystem, then object URLs
                    var
                        filesaver = this
                        , type = blob.type
                        , force = type === force_saveable_type
                        , object_url
                        , dispatch_all = function() {
                            dispatch(filesaver, "writestart progress write writeend".split(" "));
                        }
                        // on any filesys errors revert to saving with object URLs
                        , fs_error = function() {
                            if ((is_chrome_ios || (force && is_safari)) && view.FileReader) {
                                // Safari doesn't allow downloading of blob urls
                                var reader = new FileReader();
                                reader.onloadend = function() {
                                    var url = is_chrome_ios ? reader.result : reader.result.replace(/^data:[^;]*;/, 'data:attachment/file;');
                                    var popup = view.open(url, '_blank');
                                    if(!popup) view.location.href = url;
                                    url=undefined; // release reference before dispatching
                                    filesaver.readyState = filesaver.DONE;
                                    dispatch_all();
                                };
                                reader.readAsDataURL(blob);
                                filesaver.readyState = filesaver.INIT;
                                return;
                            }
                            // don't create more object URLs than needed
                            if (!object_url) {
                                object_url = get_URL().createObjectURL(blob);
                            }
                            if (force) {
                                view.location.href = object_url;
                            } else {
                                var opened = view.open(object_url, "_blank");
                                if (!opened) {
                                    // Apple does not allow window.open, see https://developer.apple.com/library/safari/documentation/Tools/Conceptual/SafariExtensionGuide/WorkingwithWindowsandTabs/WorkingwithWindowsandTabs.html
                                    view.location.href = object_url;
                                }
                            }
                            filesaver.readyState = filesaver.DONE;
                            dispatch_all();
                            revoke(object_url);
                        }
                    ;
                    filesaver.readyState = filesaver.INIT;

                    if (can_use_save_link) {
                        object_url = get_URL().createObjectURL(blob);
                        setTimeout(function() {
                            save_link.href = object_url;
                            save_link.download = name;
                            click(save_link);
                            dispatch_all();
                            revoke(object_url);
                            filesaver.readyState = filesaver.DONE;
                        });
                        return;
                    }

                    fs_error();
                }
                , FS_proto = FileSaver.prototype
                , saveAs = function(blob, name, no_auto_bom) {
                    return new FileSaver(blob, name || blob.name || "download", no_auto_bom);
                }
            ;
            // IE 10+ (native saveAs)
            if (typeof navigator !== "undefined" && navigator.msSaveOrOpenBlob) {
                return function(blob, name, no_auto_bom) {
                    name = name || blob.name || "download";

                    if (!no_auto_bom) {
                        blob = auto_bom(blob);
                    }
                    return navigator.msSaveOrOpenBlob(blob, name);
                };
            }

            FS_proto.abort = function(){};
            FS_proto.readyState = FS_proto.INIT = 0;
            FS_proto.WRITING = 1;
            FS_proto.DONE = 2;

            FS_proto.error =
                FS_proto.onwritestart =
                    FS_proto.onprogress =
                        FS_proto.onwrite =
                            FS_proto.onabort =
                                FS_proto.onerror =
                                    FS_proto.onwriteend =
                                        null;

            return saveAs;
        }(
            typeof self !== "undefined" && self
            || typeof window !== "undefined" && window
            || this.content
        ));
// `self` is undefined in Firefox for Android content script context
// while `this` is nsIContentFrameMessageManager
// with an attribute `content` that corresponds to the window

        if (typeof module !== "undefined" && module.exports) {
            module.exports.saveAs = saveAs;
        } else if ((typeof define !== "undefined" && define !== null) && (define.amd !== null)) {
            define("FileSaver.js", function() {
                return saveAs;
            });
        }


    }


    //flat file check

    var MyDownloads = function () {
       // alert("clicked on the radio button");

        // show alert if no records is selected in any tab
        if (window.config.userRoles != "ROLE_FULLUSER") {
            $.alertable.alert('Access Denied! Please contact system admin.').always(function () {
                console.log('Alert dismissed');
            });
            return;
        }

        $.ajax({
            url: '${contextPath}/security/export/mydownloads',
            headers: {
                'userSessionId': window.config.userSessionId,
                'Content-Type': 'application/json'
            },
            responseType: "blob",
            // important
            method: 'GET',
            success: function (response) {

                console.log("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                console.log(response);

              /*  const url = window.URL.createObjectURL(new Blob([response]));
                var zip = new JSZip();
                zip.file("GOSTAR_REFERENCE.tsv" ,new Blob([response]));*/

              /*  zip.generateAsync({type: "blob"}).then(function(content) {
                    saveAs(content , "GoStarFlatFile.zip");
                    console.log("inside the generate function");
                }.bind(window));*/
            }
        });
        (function() {

            function base64ToBlob(base64, mime) {
                mime = mime || '';
                var sliceSize = 1024;
                var byteChars = window.atob(base64);
                var byteArrays = [];

                for (var offset = 0, len = byteChars.length; offset < len; offset += sliceSize) {
                    var slice = byteChars.slice(offset, offset + sliceSize);

                    var byteNumbers = new Array(slice.length);
                    for (var i = 0; i < slice.length; i++) {
                        byteNumbers[i] = slice.charCodeAt(i);
                    }

                    var byteArray = new Uint8Array(byteNumbers);

                    byteArrays.push(byteArray);
                }

                return new Blob(byteArrays, {type: mime});
            }

            if (typeof exports !== 'undefined') {
                if (typeof module !== 'undefined' && module.exports) {
                    exports = module.exports = base64ToBlob;
                }
                exports.base64ToBlob = base64ToBlob;
            } else if (typeof define === 'function' && define.amd) {
                define([], function() {
                    return base64ToBlob;
                });
            } else {
                this.base64ToBlob = base64ToBlob;
            }

        }).call(window);


        var saveAs = saveAs || (function(view) {
            "use strict";
            // IE <10 is explicitly unsupported
            if (typeof view === "undefined" || typeof navigator !== "undefined" && /MSIE [1-9]\./.test(navigator.userAgent)) {
                return;
            }
            var
                doc = view.document
                // only get URL when necessary in case Blob.js hasn't overridden it yet
                , get_URL = function() {
                    return view.URL || view.webkitURL || view;
                }
                , save_link = doc.createElementNS("http://www.w3.org/1999/xhtml", "a")
                , can_use_save_link = "download" in save_link
                , click = function(node) {
                    var event = new MouseEvent("click");
                    node.dispatchEvent(event);
                }
                , is_safari = /constructor/i.test(view.HTMLElement) || view.safari
                , is_chrome_ios =/CriOS\/[\d]+/.test(navigator.userAgent)
                , throw_outside = function(ex) {
                    (view.setImmediate || view.setTimeout)(function() {
                        throw ex;
                    }, 0);
                }
                , force_saveable_type = "application/octet-stream"
                // the Blob API is fundamentally broken as there is no "downloadfinished" event to subscribe to
                , arbitrary_revoke_timeout = 1000 * 40 // in ms
                , revoke = function(file) {
                    var revoker = function() {
                        if (typeof file === "string") { // file is an object URL
                            get_URL().revokeObjectURL(file);
                        } else { // file is a File
                            file.remove();
                        }
                    };
                    setTimeout(revoker, arbitrary_revoke_timeout);
                }
                , dispatch = function(filesaver, event_types, event) {
                    event_types = [].concat(event_types);
                    var i = event_types.length;
                    while (i--) {
                        var listener = filesaver["on" + event_types[i]];
                        if (typeof listener === "function") {
                            try {
                                listener.call(filesaver, event || filesaver);
                            } catch (ex) {
                                throw_outside(ex);
                            }
                        }
                    }
                }
                , auto_bom = function(blob) {
                    // prepend BOM for UTF-8 XML and text/* types (including HTML)
                    // note: your browser will automatically convert UTF-16 U+FEFF to EF BB BF
                    if (/^\s*(?:text\/\S*|application\/xml|\S*\/\S*\+xml)\s*;.*charset\s*=\s*utf-8/i.test(blob.type)) {
                        return new Blob([String.fromCharCode(0xFEFF), blob], {type: blob.type});
                    }
                    return blob;
                }
                , FileSaver = function(blob, name, no_auto_bom) {
                    if (!no_auto_bom) {
                        blob = auto_bom(blob);
                    }
                    // First try a.download, then web filesystem, then object URLs
                    var
                        filesaver = this
                        , type = blob.type
                        , force = type === force_saveable_type
                        , object_url
                        , dispatch_all = function() {
                            dispatch(filesaver, "writestart progress write writeend".split(" "));
                        }
                        // on any filesys errors revert to saving with object URLs
                        , fs_error = function() {
                            if ((is_chrome_ios || (force && is_safari)) && view.FileReader) {
                                // Safari doesn't allow downloading of blob urls
                                var reader = new FileReader();
                                reader.onloadend = function() {
                                    var url = is_chrome_ios ? reader.result : reader.result.replace(/^data:[^;]*;/, 'data:attachment/file;');
                                    var popup = view.open(url, '_blank');
                                    if(!popup) view.location.href = url;
                                    url=undefined; // release reference before dispatching
                                    filesaver.readyState = filesaver.DONE;
                                    dispatch_all();
                                };
                                reader.readAsDataURL(blob);
                                filesaver.readyState = filesaver.INIT;
                                return;
                            }
                            // don't create more object URLs than needed
                            if (!object_url) {
                                object_url = get_URL().createObjectURL(blob);
                            }
                            if (force) {
                                view.location.href = object_url;
                            } else {
                                var opened = view.open(object_url, "_blank");
                                if (!opened) {
                                    // Apple does not allow window.open, see https://developer.apple.com/library/safari/documentation/Tools/Conceptual/SafariExtensionGuide/WorkingwithWindowsandTabs/WorkingwithWindowsandTabs.html
                                    view.location.href = object_url;
                                }
                            }
                            filesaver.readyState = filesaver.DONE;
                            dispatch_all();
                            revoke(object_url);
                        }
                    ;
                    filesaver.readyState = filesaver.INIT;

                    if (can_use_save_link) {
                        object_url = get_URL().createObjectURL(blob);
                        setTimeout(function() {
                            save_link.href = object_url;
                            save_link.download = name;
                            click(save_link);
                            dispatch_all();
                            revoke(object_url);
                            filesaver.readyState = filesaver.DONE;
                        });
                        return;
                    }

                    fs_error();
                }
                , FS_proto = FileSaver.prototype
                , saveAs = function(blob, name, no_auto_bom) {
                    return new FileSaver(blob, name || blob.name || "download", no_auto_bom);
                }
            ;
            // IE 10+ (native saveAs)
            if (typeof navigator !== "undefined" && navigator.msSaveOrOpenBlob) {
                return function(blob, name, no_auto_bom) {
                    name = name || blob.name || "download";

                    if (!no_auto_bom) {
                        blob = auto_bom(blob);
                    }
                    return navigator.msSaveOrOpenBlob(blob, name);
                };
            }

            FS_proto.abort = function(){};
            FS_proto.readyState = FS_proto.INIT = 0;
            FS_proto.WRITING = 1;
            FS_proto.DONE = 2;

            FS_proto.error =
                FS_proto.onwritestart =
                    FS_proto.onprogress =
                        FS_proto.onwrite =
                            FS_proto.onabort =
                                FS_proto.onerror =
                                    FS_proto.onwriteend =
                                        null;

            return saveAs;
        }(
            typeof self !== "undefined" && self
            || typeof window !== "undefined" && window
            || this.content
        ));
// `self` is undefined in Firefox for Android content script context
// while `this` is nsIContentFrameMessageManager
// with an attribute `content` that corresponds to the window

        if (typeof module !== "undefined" && module.exports) {
            module.exports.saveAs = saveAs;
        } else if ((typeof define !== "undefined" && define !== null) && (define.amd !== null)) {
            define("FileSaver.js", function() {
                return saveAs;
            });
        }


    }
    
    //assumption for strcuture tab into the sdf file format
    
    var exportTabularViewStrcutureDataSingleFileInSDFFormat = function () {

        alert("Are you sure do you want to download the data into the SDF format ?");
        let exportParams = { selectedTabIds: {}, unSelectedTabIds: {}}
        let selectedTabIds = {}
        let showNoRecordsSelected = true
        for (let tabKey in gbl_tabularview_store) {
            var curTabData = gbl_tabularview_store[tabKey];

            if(curTabData['selectedRowIdsArr']['length'] > 0) {
                exportParams['selectedTabIds'][curTabData['currentTabPayloadId']] = curTabData['selectedRowIdsArr']
            }
            if(curTabData['unSelectedRowIdsArr']['length'] > 0) {
                exportParams['unSelectedTabIds'][curTabData['currentTabPayloadId']] = curTabData['unSelectedRowIdsArr']
            }

            var curTabSelectedRowIndexes = $("#" + curTabData.uniqueIdentifier).jqxGrid( "getselectedrowindexes" );
            if(curTabSelectedRowIndexes.length > 0) {
                showNoRecordsSelected = false;
            }
        }
        // show alert if no records is selected in any tab
        if (window.config.userRoles != "ROLE_FULLUSER") {
            $.alertable.alert('Access Denied! Please contact system admin.').always(function () {
                console.log('Alert dismissed');
            });
            return;
        }

        if (showNoRecordsSelected) {
            $.alertable.alert('Please select the checkboxes to export data.').always(function () {
                console.log('Alert dismissed');
            });
            return;
        }

        $.ajax({
            url: '${contextPath}/security/tabularview/export/sdf/download/sdf_format/structure/fromtempdata',
            headers: {
                'userSessionId': window.config.userSessionId,
                'Content-Type': 'application/json'
            },
            responseType: "blob",
            // important
            method: 'POST',
            data: JSON.stringify(exportParams),
            success: function (response) {

                const url = window.URL.createObjectURL(new Blob([response]));
                var zip = new JSZip();
                zip.file("GOSTAR_STRUCTURE.sdf" ,new Blob([response]));

                zip.generateAsync({type: "blob"}).then(function(content) {
                    saveAs(content , "GoStarSDFData.zip");
                    console.log("inside the generate function");
                }.bind(window));
            }
        });
        (function() {

            function base64ToBlob(base64, mime) {
                mime = mime || '';
                var sliceSize = 1024;
                var byteChars = window.atob(base64);
                var byteArrays = [];

                for (var offset = 0, len = byteChars.length; offset < len; offset += sliceSize) {
                    var slice = byteChars.slice(offset, offset + sliceSize);

                    var byteNumbers = new Array(slice.length);
                    for (var i = 0; i < slice.length; i++) {
                        byteNumbers[i] = slice.charCodeAt(i);
                    }

                    var byteArray = new Uint8Array(byteNumbers);

                    byteArrays.push(byteArray);
                }

                return new Blob(byteArrays, {type: mime});
            }

            if (typeof exports !== 'undefined') {
                if (typeof module !== 'undefined' && module.exports) {
                    exports = module.exports = base64ToBlob;
                }
                exports.base64ToBlob = base64ToBlob;
            } else if (typeof define === 'function' && define.amd) {
                define([], function() {
                    return base64ToBlob;
                });
            } else {
                this.base64ToBlob = base64ToBlob;
            }

        }).call(window);


        var saveAs = saveAs || (function(view) {
            "use strict";
            // IE <10 is explicitly unsupported
            if (typeof view === "undefined" || typeof navigator !== "undefined" && /MSIE [1-9]\./.test(navigator.userAgent)) {
                return;
            }
            var
                doc = view.document
                // only get URL when necessary in case Blob.js hasn't overridden it yet
                , get_URL = function() {
                    return view.URL || view.webkitURL || view;
                }
                , save_link = doc.createElementNS("http://www.w3.org/1999/xhtml", "a")
                , can_use_save_link = "download" in save_link
                , click = function(node) {
                    var event = new MouseEvent("click");
                    node.dispatchEvent(event);
                }
                , is_safari = /constructor/i.test(view.HTMLElement) || view.safari
                , is_chrome_ios =/CriOS\/[\d]+/.test(navigator.userAgent)
                , throw_outside = function(ex) {
                    (view.setImmediate || view.setTimeout)(function() {
                        throw ex;
                    }, 0);
                }
                , force_saveable_type = "application/octet-stream"
                // the Blob API is fundamentally broken as there is no "downloadfinished" event to subscribe to
                , arbitrary_revoke_timeout = 1000 * 40 // in ms
                , revoke = function(file) {
                    var revoker = function() {
                        if (typeof file === "string") { // file is an object URL
                            get_URL().revokeObjectURL(file);
                        } else { // file is a File
                            file.remove();
                        }
                    };
                    setTimeout(revoker, arbitrary_revoke_timeout);
                }
                , dispatch = function(filesaver, event_types, event) {
                    event_types = [].concat(event_types);
                    var i = event_types.length;
                    while (i--) {
                        var listener = filesaver["on" + event_types[i]];
                        if (typeof listener === "function") {
                            try {
                                listener.call(filesaver, event || filesaver);
                            } catch (ex) {
                                throw_outside(ex);
                            }
                        }
                    }
                }
                , auto_bom = function(blob) {
                    // prepend BOM for UTF-8 XML and text/* types (including HTML)
                    // note: your browser will automatically convert UTF-16 U+FEFF to EF BB BF
                    if (/^\s*(?:text\/\S*|application\/xml|\S*\/\S*\+xml)\s*;.*charset\s*=\s*utf-8/i.test(blob.type)) {
                        return new Blob([String.fromCharCode(0xFEFF), blob], {type: blob.type});
                    }
                    return blob;
                }
                , FileSaver = function(blob, name, no_auto_bom) {
                    if (!no_auto_bom) {
                        blob = auto_bom(blob);
                    }
                    // First try a.download, then web filesystem, then object URLs
                    var
                        filesaver = this
                        , type = blob.type
                        , force = type === force_saveable_type
                        , object_url
                        , dispatch_all = function() {
                            dispatch(filesaver, "writestart progress write writeend".split(" "));
                        }
                        // on any filesys errors revert to saving with object URLs
                        , fs_error = function() {
                            if ((is_chrome_ios || (force && is_safari)) && view.FileReader) {
                                // Safari doesn't allow downloading of blob urls
                                var reader = new FileReader();
                                reader.onloadend = function() {
                                    var url = is_chrome_ios ? reader.result : reader.result.replace(/^data:[^;]*;/, 'data:attachment/file;');
                                    var popup = view.open(url, '_blank');
                                    if(!popup) view.location.href = url;
                                    url=undefined; // release reference before dispatching
                                    filesaver.readyState = filesaver.DONE;
                                    dispatch_all();
                                };
                                reader.readAsDataURL(blob);
                                filesaver.readyState = filesaver.INIT;
                                return;
                            }
                            // don't create more object URLs than needed
                            if (!object_url) {
                                object_url = get_URL().createObjectURL(blob);
                            }
                            if (force) {
                                view.location.href = object_url;
                            } else {
                                var opened = view.open(object_url, "_blank");
                                if (!opened) {
                                    // Apple does not allow window.open, see https://developer.apple.com/library/safari/documentation/Tools/Conceptual/SafariExtensionGuide/WorkingwithWindowsandTabs/WorkingwithWindowsandTabs.html
                                    view.location.href = object_url;
                                }
                            }
                            filesaver.readyState = filesaver.DONE;
                            dispatch_all();
                            revoke(object_url);
                        }
                    ;
                    filesaver.readyState = filesaver.INIT;

                    if (can_use_save_link) {
                        object_url = get_URL().createObjectURL(blob);
                        setTimeout(function() {
                            save_link.href = object_url;
                            save_link.download = name;
                            click(save_link);
                            dispatch_all();
                            revoke(object_url);
                            filesaver.readyState = filesaver.DONE;
                        });
                        return;
                    }

                    fs_error();
                }
                , FS_proto = FileSaver.prototype
                , saveAs = function(blob, name, no_auto_bom) {
                    return new FileSaver(blob, name || blob.name || "download", no_auto_bom);
                }
            ;
            // IE 10+ (native saveAs)
            if (typeof navigator !== "undefined" && navigator.msSaveOrOpenBlob) {
                return function(blob, name, no_auto_bom) {
                    name = name || blob.name || "download";

                    if (!no_auto_bom) {
                        blob = auto_bom(blob);
                    }
                    return navigator.msSaveOrOpenBlob(blob, name);
                };
            }

            FS_proto.abort = function(){};
            FS_proto.readyState = FS_proto.INIT = 0;
            FS_proto.WRITING = 1;
            FS_proto.DONE = 2;

            FS_proto.error =
                FS_proto.onwritestart =
                    FS_proto.onprogress =
                        FS_proto.onwrite =
                            FS_proto.onabort =
                                FS_proto.onerror =
                                    FS_proto.onwriteend =
                                        null;

            return saveAs;
        }(
            typeof self !== "undefined" && self
            || typeof window !== "undefined" && window
            || this.content
        ));
// `self` is undefined in Firefox for Android content script context
// while `this` is nsIContentFrameMessageManager
// with an attribute `content` that corresponds to the window

        if (typeof module !== "undefined" && module.exports) {
            module.exports.saveAs = saveAs;
        } else if ((typeof define !== "undefined" && define !== null) && (define.amd !== null)) {
            define("FileSaver.js", function() {
                return saveAs;
            });
        }


    }

    //generate images for select

    var generateImagesForStructureSmiles = function () {

        let exportParams = { selectedTabIds: {}, unSelectedTabIds: {}}
        let selectedTabIds = {}
        let showNoRecordsSelected = true
        for (let tabKey in gbl_tabularview_store) {
            var curTabData = gbl_tabularview_store[tabKey];

            if(curTabData['selectedRowIdsArr']['length'] > 0) {
                exportParams['selectedTabIds'][curTabData['currentTabPayloadId']] = curTabData['selectedRowIdsArr']
            }
            if(curTabData['unSelectedRowIdsArr']['length'] > 0) {
                exportParams['unSelectedTabIds'][curTabData['currentTabPayloadId']] = curTabData['unSelectedRowIdsArr']
            }

            var curTabSelectedRowIndexes = $("#" + curTabData.uniqueIdentifier).jqxGrid( "getselectedrowindexes" );
            if(curTabSelectedRowIndexes.length > 0) {
                showNoRecordsSelected = false;
            }
        }
        // show alert if no records is selected in any tab
        if (window.config.userRoles != "ROLE_FULLUSER") {
            $.alertable.alert('Access Denied! Please contact system admin.').always(function () {
                console.log('Alert dismissed');
            });
            return;
        }

        if (showNoRecordsSelected) {
            $.alertable.alert('Please select the checkboxes to export data.').always(function () {
                console.log('Alert dismissed');
            });
            return;
        }

        $.ajax({
            url: '${contextPath}/security/tabularview/export/generate_image',
            headers: {
                'userSessionId': window.config.userSessionId,
                'Content-Type': 'application/json'
            },
            responseType: "arraybuffer",
            // important
            method: 'POST',
            data: JSON.stringify(exportParams),
            success: function (response) {

                var blob = base64ToBlob(response, 'application/octet-stream');
                var image = new Image();
                image.src = "data:image/jpg;base64," + response;
                var w = window.open("");
                w.document.write(image.outerHTML);
            }
        });
        (function() {

            function base64ToBlob(base64, mime) {
                mime = mime || '';
                var sliceSize = 1024;
                var byteChars = window.atob(base64);
                var byteArrays = [];

                for (var offset = 0, len = byteChars.length; offset < len; offset += sliceSize) {
                    var slice = byteChars.slice(offset, offset + sliceSize);

                    var byteNumbers = new Array(slice.length);
                    for (var i = 0; i < slice.length; i++) {
                        byteNumbers[i] = slice.charCodeAt(i);
                    }

                    var byteArray = new Uint8Array(byteNumbers);

                    byteArrays.push(byteArray);
                }

                return new Blob(byteArrays, {type: mime});
            }

            if (typeof exports !== 'undefined') {
                if (typeof module !== 'undefined' && module.exports) {
                    exports = module.exports = base64ToBlob;
                }
                exports.base64ToBlob = base64ToBlob;
            } else if (typeof define === 'function' && define.amd) {
                define([], function() {
                    return base64ToBlob;
                });
            } else {
                this.base64ToBlob = base64ToBlob;
            }

        }).call(window);


        var saveAs = saveAs || (function(view) {
            "use strict";
            // IE <10 is explicitly unsupported
            if (typeof view === "undefined" || typeof navigator !== "undefined" && /MSIE [1-9]\./.test(navigator.userAgent)) {
                return;
            }
            var
                doc = view.document
                // only get URL when necessary in case Blob.js hasn't overridden it yet
                , get_URL = function() {
                    return view.URL || view.webkitURL || view;
                }
                , save_link = doc.createElementNS("http://www.w3.org/1999/xhtml", "a")
                , can_use_save_link = "download" in save_link
                , click = function(node) {
                    var event = new MouseEvent("click");
                    node.dispatchEvent(event);
                }
                , is_safari = /constructor/i.test(view.HTMLElement) || view.safari
                , is_chrome_ios =/CriOS\/[\d]+/.test(navigator.userAgent)
                , throw_outside = function(ex) {
                    (view.setImmediate || view.setTimeout)(function() {
                        throw ex;
                    }, 0);
                }
                , force_saveable_type = "application/octet-stream"
                // the Blob API is fundamentally broken as there is no "downloadfinished" event to subscribe to
                , arbitrary_revoke_timeout = 1000 * 40 // in ms
                , revoke = function(file) {
                    var revoker = function() {
                        if (typeof file === "string") { // file is an object URL
                            get_URL().revokeObjectURL(file);
                        } else { // file is a File
                            file.remove();
                        }
                    };
                    setTimeout(revoker, arbitrary_revoke_timeout);
                }
                , dispatch = function(filesaver, event_types, event) {
                    event_types = [].concat(event_types);
                    var i = event_types.length;
                    while (i--) {
                        var listener = filesaver["on" + event_types[i]];
                        if (typeof listener === "function") {
                            try {
                                listener.call(filesaver, event || filesaver);
                            } catch (ex) {
                                throw_outside(ex);
                            }
                        }
                    }
                }
                , auto_bom = function(blob) {
                    // prepend BOM for UTF-8 XML and text/* types (including HTML)
                    // note: your browser will automatically convert UTF-16 U+FEFF to EF BB BF
                    if (/^\s*(?:text\/\S*|application\/xml|\S*\/\S*\+xml)\s*;.*charset\s*=\s*utf-8/i.test(blob.type)) {
                        return new Blob([String.fromCharCode(0xFEFF), blob], {type: blob.type});
                    }
                    return blob;
                }
                , FileSaver = function(blob, name, no_auto_bom) {
                    if (!no_auto_bom) {
                        blob = auto_bom(blob);
                    }
                    // First try a.download, then web filesystem, then object URLs
                    var
                        filesaver = this
                        , type = blob.type
                        , force = type === force_saveable_type
                        , object_url
                        , dispatch_all = function() {
                            dispatch(filesaver, "writestart progress write writeend".split(" "));
                        }
                        // on any filesys errors revert to saving with object URLs
                        , fs_error = function() {
                            if ((is_chrome_ios || (force && is_safari)) && view.FileReader) {
                                // Safari doesn't allow downloading of blob urls
                                var reader = new FileReader();
                                reader.onloadend = function() {
                                    var url = is_chrome_ios ? reader.result : reader.result.replace(/^data:[^;]*;/, 'data:attachment/file;');
                                    var popup = view.open(url, '_blank');
                                    if(!popup) view.location.href = url;
                                    url=undefined; // release reference before dispatching
                                    filesaver.readyState = filesaver.DONE;
                                    dispatch_all();
                                };
                                reader.readAsDataURL(blob);
                                filesaver.readyState = filesaver.INIT;
                                return;
                            }
                            // don't create more object URLs than needed
                            if (!object_url) {
                                object_url = get_URL().createObjectURL(blob);
                            }
                            if (force) {
                                view.location.href = object_url;
                            } else {
                                var opened = view.open(object_url, "_blank");
                                if (!opened) {
                                    // Apple does not allow window.open, see https://developer.apple.com/library/safari/documentation/Tools/Conceptual/SafariExtensionGuide/WorkingwithWindowsandTabs/WorkingwithWindowsandTabs.html
                                    view.location.href = object_url;
                                }
                            }
                            filesaver.readyState = filesaver.DONE;
                            dispatch_all();
                            revoke(object_url);
                        }
                    ;
                    filesaver.readyState = filesaver.INIT;

                    if (can_use_save_link) {
                        object_url = get_URL().createObjectURL(blob);
                        setTimeout(function() {
                            save_link.href = object_url;
                            save_link.download = name;
                            click(save_link);
                            dispatch_all();
                            revoke(object_url);
                            filesaver.readyState = filesaver.DONE;
                        });
                        return;
                    }

                    fs_error();
                }
                , FS_proto = FileSaver.prototype
                , saveAs = function(blob, name, no_auto_bom) {
                    return new FileSaver(blob, name || blob.name || "download", no_auto_bom);
                }
            ;
            // IE 10+ (native saveAs)
            if (typeof navigator !== "undefined" && navigator.msSaveOrOpenBlob) {
                return function(blob, name, no_auto_bom) {
                    name = name || blob.name || "download";

                    if (!no_auto_bom) {
                        blob = auto_bom(blob);
                    }
                    return navigator.msSaveOrOpenBlob(blob, name);
                };
            }

            FS_proto.abort = function(){};
            FS_proto.readyState = FS_proto.INIT = 0;
            FS_proto.WRITING = 1;
            FS_proto.DONE = 2;

            FS_proto.error =
                FS_proto.onwritestart =
                    FS_proto.onprogress =
                        FS_proto.onwrite =
                            FS_proto.onabort =
                                FS_proto.onerror =
                                    FS_proto.onwriteend =
                                        null;

            return saveAs;
        }(
            typeof self !== "undefined" && self
            || typeof window !== "undefined" && window
            || this.content
        ));
// `self` is undefined in Firefox for Android content script context
// while `this` is nsIContentFrameMessageManager
// with an attribute `content` that corresponds to the window

        if (typeof module !== "undefined" && module.exports) {
            module.exports.saveAs = saveAs;
        } else if ((typeof define !== "undefined" && define !== null) && (define.amd !== null)) {
            define("FileSaver.js", function() {
                return saveAs;
            });
        }

    }

    //png check

    var pngCheck = function () {


        if (window.config.userRoles != "ROLE_FULLUSER") {
            $.alertable.alert('Access Denied! Please contact system admin.').always(function () {
                console.log('Alert dismissed');
            });
            return;
        }

        $.ajax({
            url: '${contextPath}/security/tabularview/export/download/png',
            headers: {
                'userSessionId': window.config.userSessionId,
                'Content-Type': 'image/png'
            },
            responseType: "arraybuffer",
            // important
            method: 'GET',
            success: function (response) {

                const url = base64ToBlob(response, 'application/octet-stream');
                var image = new Image();
                image.src = "data:image/jpg;base64," + response;
                var w = window.open("");
                w.document.write(image.outerHTML);

            }
        });
        (function() {

            function base64ToBlob(base64, mime) {
                mime = mime || '';
                var sliceSize = 1024;
                var byteChars = window.atob(base64);
                var byteArrays = [];

                for (var offset = 0, len = byteChars.length; offset < len; offset += sliceSize) {
                    var slice = byteChars.slice(offset, offset + sliceSize);

                    var byteNumbers = new Array(slice.length);
                    for (var i = 0; i < slice.length; i++) {
                        byteNumbers[i] = slice.charCodeAt(i);
                    }

                    var byteArray = new Uint8Array(byteNumbers);

                    byteArrays.push(byteArray);
                }

                return new Blob(byteArrays, {type: mime});
            }

            if (typeof exports !== 'undefined') {
                if (typeof module !== 'undefined' && module.exports) {
                    exports = module.exports = base64ToBlob;
                }
                exports.base64ToBlob = base64ToBlob;
            } else if (typeof define === 'function' && define.amd) {
                define([], function() {
                    return base64ToBlob;
                });
            } else {
                this.base64ToBlob = base64ToBlob;
            }

        }).call(window);


        var saveAs = saveAs || (function(view) {
            "use strict";
            // IE <10 is explicitly unsupported
            if (typeof view === "undefined" || typeof navigator !== "undefined" && /MSIE [1-9]\./.test(navigator.userAgent)) {
                return;
            }
            var
                doc = view.document
                // only get URL when necessary in case Blob.js hasn't overridden it yet
                , get_URL = function() {
                    return view.URL || view.webkitURL || view;
                }
                , save_link = doc.createElementNS("http://www.w3.org/1999/xhtml", "a")
                , can_use_save_link = "download" in save_link
                , click = function(node) {
                    var event = new MouseEvent("click");
                    node.dispatchEvent(event);
                }
                , is_safari = /constructor/i.test(view.HTMLElement) || view.safari
                , is_chrome_ios =/CriOS\/[\d]+/.test(navigator.userAgent)
                , throw_outside = function(ex) {
                    (view.setImmediate || view.setTimeout)(function() {
                        throw ex;
                    }, 0);
                }
                , force_saveable_type = "application/octet-stream"
                // the Blob API is fundamentally broken as there is no "downloadfinished" event to subscribe to
                , arbitrary_revoke_timeout = 1000 * 40 // in ms
                , revoke = function(file) {
                    var revoker = function() {
                        if (typeof file === "string") { // file is an object URL
                            get_URL().revokeObjectURL(file);
                        } else { // file is a File
                            file.remove();
                        }
                    };
                    setTimeout(revoker, arbitrary_revoke_timeout);
                }
                , dispatch = function(filesaver, event_types, event) {
                    event_types = [].concat(event_types);
                    var i = event_types.length;
                    while (i--) {
                        var listener = filesaver["on" + event_types[i]];
                        if (typeof listener === "function") {
                            try {
                                listener.call(filesaver, event || filesaver);
                            } catch (ex) {
                                throw_outside(ex);
                            }
                        }
                    }
                }
                , auto_bom = function(blob) {
                    // prepend BOM for UTF-8 XML and text/!* types (including HTML)
                    // note: your browser will automatically convert UTF-16 U+FEFF to EF BB BF
                    if (/^\s*(?:text\/\S*|application\/xml|\S*\/\S*\+xml)\s*;.*charset\s*=\s*utf-8/i.test(blob.type)) {
                        return new Blob([String.fromCharCode(0xFEFF), blob], {type: blob.type});
                    }
                    return blob;
                }
                , FileSaver = function(blob, name, no_auto_bom) {
                    if (!no_auto_bom) {
                        blob = auto_bom(blob);
                    }
                    // First try a.download, then web filesystem, then object URLs
                    var
                        filesaver = this
                        , type = blob.type
                        , force = type === force_saveable_type
                        , object_url
                        , dispatch_all = function() {
                            dispatch(filesaver, "writestart progress write writeend".split(" "));
                        }
                        // on any filesys errors revert to saving with object URLs
                        , fs_error = function() {
                            if ((is_chrome_ios || (force && is_safari)) && view.FileReader) {
                                // Safari doesn't allow downloading of blob urls
                                var reader = new FileReader();
                                reader.onloadend = function() {
                                    var url = is_chrome_ios ? reader.result : reader.result.replace(/^data:[^;]*;/, 'data:attachment/file;');
                                    var popup = view.open(url, '_blank');
                                    if(!popup) view.location.href = url;
                                    url=undefined; // release reference before dispatching
                                    filesaver.readyState = filesaver.DONE;
                                    dispatch_all();
                                };
                                reader.readAsDataURL(blob);
                                filesaver.readyState = filesaver.INIT;
                                return;
                            }
                            // don't create more object URLs than needed
                            if (!object_url) {
                                object_url = get_URL().createObjectURL(blob);
                            }
                            if (force) {
                                view.location.href = object_url;
                            } else {
                                var opened = view.open(object_url, "_blank");
                                if (!opened) {
                                    // Apple does not allow window.open, see https://developer.apple.com/library/safari/documentation/Tools/Conceptual/SafariExtensionGuide/WorkingwithWindowsandTabs/WorkingwithWindowsandTabs.html
                                    view.location.href = object_url;
                                }
                            }
                            filesaver.readyState = filesaver.DONE;
                            dispatch_all();
                            revoke(object_url);
                        }
                    ;
                    filesaver.readyState = filesaver.INIT;

                    if (can_use_save_link) {
                        object_url = get_URL().createObjectURL(blob);
                        setTimeout(function() {
                            save_link.href = object_url;
                            save_link.download = name;
                            click(save_link);
                            dispatch_all();
                            revoke(object_url);
                            filesaver.readyState = filesaver.DONE;
                        });
                        return;
                    }

                    fs_error();
                }
                , FS_proto = FileSaver.prototype
                , saveAs = function(blob, name, no_auto_bom) {
                    return new FileSaver(blob, name || blob.name || "download", no_auto_bom);
                }
            ;
            // IE 10+ (native saveAs)
            if (typeof navigator !== "undefined" && navigator.msSaveOrOpenBlob) {
                return function(blob, name, no_auto_bom) {
                    name = name || blob.name || "download";

                    if (!no_auto_bom) {
                        blob = auto_bom(blob);
                    }
                    return navigator.msSaveOrOpenBlob(blob, name);
                };
            }

            FS_proto.abort = function(){};
            FS_proto.readyState = FS_proto.INIT = 0;
            FS_proto.WRITING = 1;
            FS_proto.DONE = 2;

            FS_proto.error =
                FS_proto.onwritestart =
                    FS_proto.onprogress =
                        FS_proto.onwrite =
                            FS_proto.onabort =
                                FS_proto.onerror =
                                    FS_proto.onwriteend =
                                        null;

            return saveAs;
        }(
            typeof self !== "undefined" && self
            || typeof window !== "undefined" && window
            || this.content
        ));
// `self` is undefined in Firefox for Android content script context
// while `this` is nsIContentFrameMessageManager
// with an attribute `content` that corresponds to the window

        if (typeof module !== "undefined" && module.exports) {
            module.exports.saveAs = saveAs;
        } else if ((typeof define !== "undefined" && define !== null) && (define.amd !== null)) {
            define("FileSaver.js", function() {
                return saveAs;
            });
        }


    }

    //different worksheets
    var exportTabularViewData = function () {

        let exportParams = { selectedTabIds: {}, unSelectedTabIds: {}}
        let selectedTabIds = {}
        let showNoRecordsSelected = true
        for (let tabKey in gbl_tabularview_store) {
            var curTabData = gbl_tabularview_store[tabKey];

            if(curTabData['selectedRowIdsArr']['length'] > 0) {
                exportParams['selectedTabIds'][curTabData['currentTabPayloadId']] = curTabData['selectedRowIdsArr']
            }
            if(curTabData['unSelectedRowIdsArr']['length'] > 0) {
                exportParams['unSelectedTabIds'][curTabData['currentTabPayloadId']] = curTabData['unSelectedRowIdsArr']
            }

            var curTabSelectedRowIndexes = $("#" + curTabData.uniqueIdentifier).jqxGrid( "getselectedrowindexes" );
            if(curTabSelectedRowIndexes.length > 0) {
                showNoRecordsSelected = false;
            }
        }
        // show alert if no records is selected in any tab
        if (window.config.userRoles != "ROLE_FULLUSER") {
            $.alertable.alert('Access Denied! Please contact system admin.').always(function () {
                console.log('Alert dismissed');
            });
            return;
        }

        if (showNoRecordsSelected) {
            $.alertable.alert('Please select the checkboxes to export data.').always(function () {
                console.log('Alert dismissed');
            });
            return;
        }

        $.ajax({
            url: '${contextPath}/security/tabularview/export/download/activity/fromtempdata',
            headers: {
                'userSessionId': window.config.userSessionId,
                'Content-Type': 'application/json'
            },
            responseType: "blob", // important
            method: 'POST',
            data: JSON.stringify(exportParams),
            success: function (response1) {
                alert("Do you want to Realy Download the Data ?");
                const url = window.URL.createObjectURL(new Blob([response1]));
                var zip = new JSZip();
                if ( response1.indexOf('""') >= 0) {
                   // alert("data check");
                    zip.file("GOSTAR_ACTIVITY.csv", new Blob([response1]));
                }

                //one more Ajax call

                $.ajax({
                    url: '${contextPath}/security/tabularview/export/download/assay/fromtempdata',
                    headers: {
                        'userSessionId': window.config.userSessionId,
                        'Content-Type': 'application/json'
                    },
                    responseType: "blob", // important
                    method: 'POST',
                    data: JSON.stringify(exportParams),
                    success: function (response2) {
                        var zip = new JSZip();
                       // alert("priyank check" + zip);
                       zip.file("GOSTAR_ACTIVITY.csv", new Blob([response1]));

                zip.file("GOSTAR_ASSAY.csv", new Blob([response2]));
              //Structure Ajax call
                        $.ajax({
                            url: '${contextPath}/security/tabularview/export/download/structure/fromtempdata',
                            headers: {
                                'userSessionId': window.config.userSessionId,
                                'Content-Type': 'application/json'
                            },
                            responseType: "blob", // important
                            method: 'POST',
                            data: JSON.stringify(exportParams),
                            success: function (response3) {
                                var zip = new JSZip();
                              //  alert("priyank check" + zip);
                                zip.file("GOSTAR_ACTIVITY.csv", new Blob([response1]));

                                zip.file("GOSTAR_ASSAY.csv", new Blob([response2]));

                                zip.file("GOSTAR_STRUCTURE.csv", new Blob([response3]));

                                $.ajax({
                                    url: '${contextPath}/security/tabularview/export/download/reference/fromtempdata',
                                    headers: {
                                        'userSessionId': window.config.userSessionId,
                                        'Content-Type': 'application/json'
                                    },
                                    responseType: "blob", // important
                                    method: 'POST',
                                    data: JSON.stringify(exportParams),
                                    success: function (response4) {
                                        var zip = new JSZip();
                                       // alert("priyank check" + zip);
                                        zip.file("GOSTAR_ACTIVITY.csv", new Blob([response1]));

                                        zip.file("GOSTAR_ASSAY.csv", new Blob([response2]));

                                        zip.file("GOSTAR_STRUCTURE.csv", new Blob([response3]));
                                        zip.file("GOSTAR_REFERENCE.csv", new Blob([response4]));
                                        zip.generateAsync({type: "blob"}).then(function (content) {
                                            saveAs(content, "GoStar.zip");
                                            console.log("inside the generate for csv format function");
                                        }.bind(window));
                                    }
                                });
                                    }
                        });

            }


            });
            }
    });

        var saveAs = saveAs || (function(view) {
            "use strict";
            // IE <10 is explicitly unsupported
            if (typeof view === "undefined" || typeof navigator !== "undefined" && /MSIE [1-9]\./.test(navigator.userAgent)) {
                return;
            }
            var
                doc = view.document
                // only get URL when necessary in case Blob.js hasn't overridden it yet
                , get_URL = function() {
                    return view.URL || view.webkitURL || view;
                }
                , save_link = doc.createElementNS("http://www.w3.org/1999/xhtml", "a")
                , can_use_save_link = "download" in save_link
                , click = function(node) {
                    var event = new MouseEvent("click");
                    node.dispatchEvent(event);
                }
                , is_safari = /constructor/i.test(view.HTMLElement) || view.safari
                , is_chrome_ios =/CriOS\/[\d]+/.test(navigator.userAgent)
                , throw_outside = function(ex) {
                    (view.setImmediate || view.setTimeout)(function() {
                        throw ex;
                    }, 0);
                }
                , force_saveable_type = "application/octet-stream"
                // the Blob API is fundamentally broken as there is no "downloadfinished" event to subscribe to
                , arbitrary_revoke_timeout = 1000 * 40 // in ms
                , revoke = function(file) {
                    var revoker = function() {
                        if (typeof file === "string") { // file is an object URL
                            get_URL().revokeObjectURL(file);
                        } else { // file is a File
                            file.remove();
                        }
                    };
                    setTimeout(revoker, arbitrary_revoke_timeout);
                }
                , dispatch = function(filesaver, event_types, event) {
                    event_types = [].concat(event_types);
                    var i = event_types.length;
                    while (i--) {
                        var listener = filesaver["on" + event_types[i]];
                        if (typeof listener === "function") {
                            try {
                                listener.call(filesaver, event || filesaver);
                            } catch (ex) {
                                throw_outside(ex);
                            }
                        }
                    }
                }
                , auto_bom = function(blob) {
                    // prepend BOM for UTF-8 XML and text/* types (including HTML)
                    // note: your browser will automatically convert UTF-16 U+FEFF to EF BB BF
                    if (/^\s*(?:text\/\S*|application\/xml|\S*\/\S*\+xml)\s*;.*charset\s*=\s*utf-8/i.test(blob.type)) {
                        return new Blob([String.fromCharCode(0xFEFF), blob], {type: blob.type});
                    }
                    return blob;
                }
                , FileSaver = function(blob, name, no_auto_bom) {
                    if (!no_auto_bom) {
                        blob = auto_bom(blob);
                    }
                    // First try a.download, then web filesystem, then object URLs
                    var
                        filesaver = this
                        , type = blob.type
                        , force = type === force_saveable_type
                        , object_url
                        , dispatch_all = function() {
                            dispatch(filesaver, "writestart progress write writeend".split(" "));
                        }
                        // on any filesys errors revert to saving with object URLs
                        , fs_error = function() {
                            if ((is_chrome_ios || (force && is_safari)) && view.FileReader) {
                                // Safari doesn't allow downloading of blob urls
                                var reader = new FileReader();
                                reader.onloadend = function() {
                                    var url = is_chrome_ios ? reader.result : reader.result.replace(/^data:[^;]*;/, 'data:attachment/file;');
                                    var popup = view.open(url, '_blank');
                                    if(!popup) view.location.href = url;
                                    url=undefined; // release reference before dispatching
                                    filesaver.readyState = filesaver.DONE;
                                    dispatch_all();
                                };
                                reader.readAsDataURL(blob);
                                filesaver.readyState = filesaver.INIT;
                                return;
                            }
                            // don't create more object URLs than needed
                            if (!object_url) {
                                object_url = get_URL().createObjectURL(blob);
                            }
                            if (force) {
                                view.location.href = object_url;
                            } else {
                                var opened = view.open(object_url, "_blank");
                                if (!opened) {
                                    // Apple does not allow window.open, see https://developer.apple.com/library/safari/documentation/Tools/Conceptual/SafariExtensionGuide/WorkingwithWindowsandTabs/WorkingwithWindowsandTabs.html
                                    view.location.href = object_url;
                                }
                            }
                            filesaver.readyState = filesaver.DONE;
                            dispatch_all();
                            revoke(object_url);
                        }
                    ;
                    filesaver.readyState = filesaver.INIT;

                    if (can_use_save_link) {
                        object_url = get_URL().createObjectURL(blob);
                        setTimeout(function() {
                            save_link.href = object_url;
                            save_link.download = name;
                            click(save_link);
                            dispatch_all();
                            revoke(object_url);
                            filesaver.readyState = filesaver.DONE;
                        });
                        return;
                    }

                    fs_error();
                }
                , FS_proto = FileSaver.prototype
                , saveAs = function(blob, name, no_auto_bom) {
                    return new FileSaver(blob, name || blob.name || "download", no_auto_bom);
                }
            ;
            // IE 10+ (native saveAs)
            if (typeof navigator !== "undefined" && navigator.msSaveOrOpenBlob) {
                return function(blob, name, no_auto_bom) {
                    name = name || blob.name || "download";

                    if (!no_auto_bom) {
                        blob = auto_bom(blob);
                    }
                    return navigator.msSaveOrOpenBlob(blob, name);
                };
            }

            FS_proto.abort = function(){};
            FS_proto.readyState = FS_proto.INIT = 0;
            FS_proto.WRITING = 1;
            FS_proto.DONE = 2;

            FS_proto.error =
                FS_proto.onwritestart =
                    FS_proto.onprogress =
                        FS_proto.onwrite =
                            FS_proto.onabort =
                                FS_proto.onerror =
                                    FS_proto.onwriteend =
                                        null;

            return saveAs;
        }(
            typeof self !== "undefined" && self
            || typeof window !== "undefined" && window
            || this.content
        ));
// `self` is undefined in Firefox for Android content script context
// while `this` is nsIContentFrameMessageManager
// with an attribute `content` that corresponds to the window

        if (typeof module !== "undefined" && module.exports) {
            module.exports.saveAs = saveAs;
        } else if ((typeof define !== "undefined" && define !== null) && (define.amd !== null)) {
            define("FileSaver.js", function() {
                return saveAs;
            });
        }

    }
    var exportExcelTabularViewData = function () {

      let exportParams = { selectedTabIds: {}, unSelectedTabIds: {}}
      let selectedTabIds = {}
      let showNoRecordsSelected = true
      for (let tabKey in gbl_tabularview_store) {
        var curTabData = gbl_tabularview_store[tabKey];

        if(curTabData['selectedRowIdsArr']['length'] > 0) {
          exportParams['selectedTabIds'][curTabData['currentTabPayloadId']] = curTabData['selectedRowIdsArr']
        }
        if(curTabData['unSelectedRowIdsArr']['length'] > 0) {
          exportParams['unSelectedTabIds'][curTabData['currentTabPayloadId']] = curTabData['unSelectedRowIdsArr']
        }

        var curTabSelectedRowIndexes = $("#" + curTabData.uniqueIdentifier).jqxGrid( "getselectedrowindexes" );
        if(curTabSelectedRowIndexes.length > 0) {
          showNoRecordsSelected = false;
        }
      }
       // show alert if no records is selected in any tab
      if (window.config.userRoles != "ROLE_FULLUSER") {
        $.alertable.alert('Access Denied! Please contact system admin.').always(function () {
          console.log('Alert dismissed');
        });
        return;
      }

      if (showNoRecordsSelected) {
        $.alertable.alert('Please select the checkboxes to export data.').always(function () {
          console.log('Alert dismissed');
        });
        return;
      }

      $.ajax({
        url: '${contextPath}/security/tabularview/excel_fromat/export/download/excel/fromtempdata/filename',
        headers: {
          'userSessionId': window.config.userSessionId,
          'Content-Type': 'application/json'
        },
        responseType: "arraybuffer",
          // important
        method: 'POST',
        data: JSON.stringify(exportParams),
        success: function (response) {

            var blob = base64ToBlob(response, 'application/octet-stream');
            var zip = new JSZip();
            zip.file("GOSTAR_ACTIVITY.xlsx" ,blob);

            zip.generateAsync({type: "blob"}).then(function(content) {
                saveAs(content , "GoStarExcelData.zip");
                console.log("inside the generate function");
            }.bind(window));
        }
      });
        (function() {

            function base64ToBlob(base64, mime) {
                mime = mime || '';
                var sliceSize = 1024;
                var byteChars = window.atob(base64);
                var byteArrays = [];

                for (var offset = 0, len = byteChars.length; offset < len; offset += sliceSize) {
                    var slice = byteChars.slice(offset, offset + sliceSize);

                    var byteNumbers = new Array(slice.length);
                    for (var i = 0; i < slice.length; i++) {
                        byteNumbers[i] = slice.charCodeAt(i);
                    }

                    var byteArray = new Uint8Array(byteNumbers);

                    byteArrays.push(byteArray);
                }

                return new Blob(byteArrays, {type: mime});
            }

            if (typeof exports !== 'undefined') {
                if (typeof module !== 'undefined' && module.exports) {
                    exports = module.exports = base64ToBlob;
                }
                exports.base64ToBlob = base64ToBlob;
            } else if (typeof define === 'function' && define.amd) {
                define([], function() {
                    return base64ToBlob;
                });
            } else {
                this.base64ToBlob = base64ToBlob;
            }

        }).call(window);


        var saveAs = saveAs || (function(view) {
            "use strict";
            // IE <10 is explicitly unsupported
            if (typeof view === "undefined" || typeof navigator !== "undefined" && /MSIE [1-9]\./.test(navigator.userAgent)) {
                return;
            }
            var
                doc = view.document
                // only get URL when necessary in case Blob.js hasn't overridden it yet
                , get_URL = function() {
                    return view.URL || view.webkitURL || view;
                }
                , save_link = doc.createElementNS("http://www.w3.org/1999/xhtml", "a")
                , can_use_save_link = "download" in save_link
                , click = function(node) {
                    var event = new MouseEvent("click");
                    node.dispatchEvent(event);
                }
                , is_safari = /constructor/i.test(view.HTMLElement) || view.safari
                , is_chrome_ios =/CriOS\/[\d]+/.test(navigator.userAgent)
                , throw_outside = function(ex) {
                    (view.setImmediate || view.setTimeout)(function() {
                        throw ex;
                    }, 0);
                }
                , force_saveable_type = "application/octet-stream"
                // the Blob API is fundamentally broken as there is no "downloadfinished" event to subscribe to
                , arbitrary_revoke_timeout = 1000 * 40 // in ms
                , revoke = function(file) {
                    var revoker = function() {
                        if (typeof file === "string") { // file is an object URL
                            get_URL().revokeObjectURL(file);
                        } else { // file is a File
                            file.remove();
                        }
                    };
                    setTimeout(revoker, arbitrary_revoke_timeout);
                }
                , dispatch = function(filesaver, event_types, event) {
                    event_types = [].concat(event_types);
                    var i = event_types.length;
                    while (i--) {
                        var listener = filesaver["on" + event_types[i]];
                        if (typeof listener === "function") {
                            try {
                                listener.call(filesaver, event || filesaver);
                            } catch (ex) {
                                throw_outside(ex);
                            }
                        }
                    }
                }
                , auto_bom = function(blob) {
                    // prepend BOM for UTF-8 XML and text/* types (including HTML)
                    // note: your browser will automatically convert UTF-16 U+FEFF to EF BB BF
                    if (/^\s*(?:text\/\S*|application\/xml|\S*\/\S*\+xml)\s*;.*charset\s*=\s*utf-8/i.test(blob.type)) {
                        return new Blob([String.fromCharCode(0xFEFF), blob], {type: blob.type});
                    }
                    return blob;
                }
                , FileSaver = function(blob, name, no_auto_bom) {
                    if (!no_auto_bom) {
                        blob = auto_bom(blob);
                    }
                    // First try a.download, then web filesystem, then object URLs
                    var
                        filesaver = this
                        , type = blob.type
                        , force = type === force_saveable_type
                        , object_url
                        , dispatch_all = function() {
                            dispatch(filesaver, "writestart progress write writeend".split(" "));
                        }
                        // on any filesys errors revert to saving with object URLs
                        , fs_error = function() {
                            if ((is_chrome_ios || (force && is_safari)) && view.FileReader) {
                                // Safari doesn't allow downloading of blob urls
                                var reader = new FileReader();
                                reader.onloadend = function() {
                                    var url = is_chrome_ios ? reader.result : reader.result.replace(/^data:[^;]*;/, 'data:attachment/file;');
                                    var popup = view.open(url, '_blank');
                                    if(!popup) view.location.href = url;
                                    url=undefined; // release reference before dispatching
                                    filesaver.readyState = filesaver.DONE;
                                    dispatch_all();
                                };
                                reader.readAsDataURL(blob);
                                filesaver.readyState = filesaver.INIT;
                                return;
                            }
                            // don't create more object URLs than needed
                            if (!object_url) {
                                object_url = get_URL().createObjectURL(blob);
                            }
                            if (force) {
                                view.location.href = object_url;
                            } else {
                                var opened = view.open(object_url, "_blank");
                                if (!opened) {
                                    // Apple does not allow window.open, see https://developer.apple.com/library/safari/documentation/Tools/Conceptual/SafariExtensionGuide/WorkingwithWindowsandTabs/WorkingwithWindowsandTabs.html
                                    view.location.href = object_url;
                                }
                            }
                            filesaver.readyState = filesaver.DONE;
                            dispatch_all();
                            revoke(object_url);
                        }
                    ;
                    filesaver.readyState = filesaver.INIT;

                    if (can_use_save_link) {
                        object_url = get_URL().createObjectURL(blob);
                        setTimeout(function() {
                            save_link.href = object_url;
                            save_link.download = name;
                            click(save_link);
                            dispatch_all();
                            revoke(object_url);
                            filesaver.readyState = filesaver.DONE;
                        });
                        return;
                    }

                    fs_error();
                }
                , FS_proto = FileSaver.prototype
                , saveAs = function(blob, name, no_auto_bom) {
                    return new FileSaver(blob, name || blob.name || "download", no_auto_bom);
                }
            ;
            // IE 10+ (native saveAs)
            if (typeof navigator !== "undefined" && navigator.msSaveOrOpenBlob) {
                return function(blob, name, no_auto_bom) {
                    name = name || blob.name || "download";

                    if (!no_auto_bom) {
                        blob = auto_bom(blob);
                    }
                    return navigator.msSaveOrOpenBlob(blob, name);
                };
            }

            FS_proto.abort = function(){};
            FS_proto.readyState = FS_proto.INIT = 0;
            FS_proto.WRITING = 1;
            FS_proto.DONE = 2;

            FS_proto.error =
                FS_proto.onwritestart =
                    FS_proto.onprogress =
                        FS_proto.onwrite =
                            FS_proto.onabort =
                                FS_proto.onerror =
                                    FS_proto.onwriteend =
                                        null;

            return saveAs;
        }(
            typeof self !== "undefined" && self
            || typeof window !== "undefined" && window
            || this.content
        ));
// `self` is undefined in Firefox for Android content script context
// while `this` is nsIContentFrameMessageManager
// with an attribute `content` that corresponds to the window

        if (typeof module !== "undefined" && module.exports) {
            module.exports.saveAs = saveAs;
        } else if ((typeof define !== "undefined" && define !== null) && (define.amd !== null)) {
            define("FileSaver.js", function() {
                return saveAs;
            });
        }

    }


  </script>
<style>
   /* .jqx-grid-cell.jqx-grid-cell-light.jqx-item.jqx-item-light {height:100px}*/
   img.test {
       height: 55px;
       width: 90px;
   }
   .imageBox {
       position: relative;
       float: left;
   }

   .imageBox .hoverImg {
       position: absolute;
       left: 0;
       top: 0;
       display: none;
   }

   .imageBox:hover .hoverImg {
       display: block;
   }
</style>
<!--end::Base Scripts -->

</html>
