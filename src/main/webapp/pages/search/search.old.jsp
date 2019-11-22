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

<head>
  <meta charset="utf-8" />
  <title>GOSTAR - Search</title>
  <meta name="description" content="GoStar Search">
  <meta name="viewport" content="width=device-width, initial-scale=1,
         maximum-scale=1, shrink-to-fit=no">
  <!--begin::Web font -->
  <script src="https://ajax.googleapis.com/ajax/libs/webfont/1.6.16/webfont.js"></script>
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
  <link href="${contextPath}/assets/styles/fullcalendar.bundle.css" rel="stylesheet" type="text/css" />
  <!--end::Page Vendors Styles -->
  <!--begin::Base Styles -->
  <link href="${contextPath}/assets/styles/vendors.bundle.css" rel="stylesheet" type="text/css" />
  <!--begin style bundle -->
  <link href="${contextPath}/assets/styles/style.bundle.css" rel="stylesheet" type="text/css" />
  <!-- 'rSlder' Slider range component script -->
  <!-- <link href="${contextPath}/assets/styles/rSlider.min.css" rel="stylesheet" type="text/css" /> -->
  <!--end::Base Styles -->
  <link rel="shortcut icon" href="${contextPath}/assets/images/favicon.ico" />
  <!-- Do you need Fontawesome? -->
  <link href="${contextPath}/resources/css/fontawesome/css/all.min.css" rel="stylesheet" />
  <!-- Do you need all animations? -->
  <link href="${contextPath}/resources/css/animate.min.css" rel="stylesheet" />
  <!-- Page loader css -->
  <link href="${contextPath}/resources/css/page-loader/_css/Icomoon/style.css" rel="stylesheet" type="text/css" />
  <link href="${contextPath}/resources/css/page-loader/_css/main.css" rel="stylesheet" type="text/css" />
  <!--
     Finally, add Quasar's CSS:
    Replace version below (1.0.0-beta.0) with your desired version of Quasar.
     Add ".rtl" for the RTL support (example: quasar.rtl.min.css).
     -->
  <link href="${contextPath}/resources/css/quasar.min.css" rel="stylesheet" type="text/css" />
  <link href="${contextPath}/resources/css/app.css" rel="stylesheet" />
  <link href="${contextPath}/resources/css/override.css" rel="stylesheet" />
  <!-- Add this to <head> -->
  <!-- Load required Bootstrap and BootstrapVue CSS -->
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
</head>
<!-- these are custom components -->

<body class="m-page--fluid m-page--loading-enabled m-page--loading m-header--fixed
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
        <header id="m_header" class="m-grid__item m-header" m-minimize="minimize" m-minimize-mobile="minimize"
          m-minimize-offset="10" m-minimize-mobile-offset="10">
          <!--<div class="m-header__top">
                 <div class="m-container m-container--fluid m-container--full-height m-page__container">

                 </div>
                 </div>
                 <div class="m-header__bottom">
                 <div class="m-container m-container--fluid m-container--full-height m-page__container">
                   <div class="m-stack m-stack--ver m-stack--desktop"> </div>
                 </div>
                 </div> 
      </header>
        <!-- end::Header -->
          <!-- begin::Body -->
          <div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop
                m-grid--desktop m-page__container m-body">
            <div class="m-grid__item m-grid__item--fluid m-wrapper">
              <!-- START Top Menu bar block -->
              <!-- Tabination selection part - Starts -->
              <div class="row">
                <div class="col-2">
                  <!-- Logo resource code -->
                  <div class="m-stack__item m-stack__item--middle
                            m-brand__logo"> <a href="" class="m-brand__logo-wrapper">
                      <img alt="" src="${contextPath}/assets/images/Gostar_Logo_mediam.png"
                        class="m-brand__logo-desktop" style="width:180px" /> </a> </div>
                </div>
                <div class="col-8">
                  <div class="m-portlet__head-tools">
                    <ul class="nav nav-tabs2 m-tabs-line m-tabs-line--primary
                               m-tabs-line--2x" role="tablist">
                      <li class="nav-item2 m-tabs__item"> <a class="nav-link
                                  m-tabs__link" role="tab"> Dashboard </a> </li>

                      <li class="nav-item2 m-tabs__item"> <a class="nav-link
                                  m-tabs__link active"  @click="handleNavigation('search')" role="tab"> Search </a>
                      </li>
                     
                      <li class="nav-item2 m-tabs__item"  > <a class="nav-link 
                                  m-tabs__link"  @click="handleNavigation('visualization')" :disabled="!$store.state.simpleSearch.enableDashboardOnSubmit"  role="tab"> Visualization </a> </li>
                      <li class="nav-item2 m-tabs__item" > <a class="nav-link
                                                        m-tabs__link " :disabled="!$store.state.simpleSearch.enableDashboardOnSubmit" @click="handleNavigation('tabularView')"  role="tab"> Tabular View </a>
                      </li>
                      <li class="nav-item2 m-tabs__item"> <a class="nav-link
                                  m-tabs__link"    role="tab"> Search History </a>
                      </li>
                      <li class="nav-item2 m-tabs__item"> <a class="nav-link
                                  m-tabs__link"   role="tab"> Downloads </a> </li>
                    </ul>
                  </div>
                </div>
                <div class="col-2">
                  <div class="m-stack__item m-topbar__nav-wrapper" style="float:right">
                    <ul class="m-topbar__nav m-nav m-nav--inline">
                      <li class="m-nav__item m-dropdown m-dropdown--medium
                                  m-dropdown--arrow m-dropdown--align-right
                                  m-dropdown--mobile-full-width m-dropdown--skin-light" m-dropdown-toggle="click"> <a
                          href="#" class="m-nav__link m-dropdown__toggle"> <span class="m-topbar__userpic"> <img
                              src="${contextPath}/assets/images/users/user_profile_pic.png" class="m--img-rounded m--marginless
                                     m--img-centered" alt="" style="height:30px" /> </span> <span class="m-nav__link-icon m-topbar__usericon
                                     m--hide"> <span class="m-nav__link-icon-wrapper"> <i class="flaticon-user-ok"></i>
                            </span> </span> <span class="m-topbar__username m--hide">User</span> </a>
                        <div class="m-dropdown__wrapper"> <span class="m-dropdown__arrow m-dropdown__arrow--right
                                        m-dropdown__arrow--adjust"></span>
                          <div class="m-dropdown__inner">
                            <div class="m-dropdown__header m--align-center">
                              <div class="m-card-user m-card-user--skin-light">
                                <div class="m-card-user__pic"> <img
                                    src="${contextPath}/assets/images/users/user_profile_pic.png"
                                    class="m--img-rounded m--marginless" alt="" /> </div>
                                <div class="m-card-user__details"> <span class="m-card-user__name
                                                 m--font-weight-500">
                                                ${username}</span> <a href="" class="m-card-user__email m--font-weight-300
                                                    m-link">user.name@gostar.com</a> </div>
                              </div>
                            </div>
                            <div class="m-dropdown__body">
                              <div class="m-dropdown__content">
                                <ul class="m-nav m-nav--skin-light">
                                  <li class="m-nav__section m--hide"> <span class="m-nav__section-text">Section</span>
                                  </li>
                                  <li class="m-nav__item"> <a href="#" class="m-nav__link"> <i class="m-nav__link-icon
                                                       flaticon-profile-1"></i> <span class="m-nav__link-title"> <span
                                          class="m-nav__link-wrap"> <span class="m-nav__link-text">My Profile</span>
                                          <span class="m-nav__link-badge"> <span
                                              class="m-badge m-badge--success">2</span> </span> </span> </span> </a>
                                  </li>
                                  <li class="m-nav__item"> <a href="#" class="m-nav__link"> <i
                                        class="m-nav__link-icon flaticon-share"></i> 
                                       
                                        <span
                                        class="m-nav__link-text">Activity</span> </a> </li>
                                  <li class="m-nav__item"> <a href="#" class="m-nav__link"> <i class="m-nav__link-icon
                                                       flaticon-chat-1"></i> <span
                                        class="m-nav__link-text">Messages</span> </a> </li>
                                  <li class="m-nav__separator
                                                    m-nav__separator--fit"> </li>
                                  <li class="m-nav__item"> <a href="#" class="m-nav__link"> <i
                                        class="m-nav__link-icon flaticon-info"></i> <span
                                        class="m-nav__link-text">FAQ</span> </a> </li>
                                  <li class="m-nav__item"> <a href="#" class="m-nav__link"> <i class="m-nav__link-icon
                                                       flaticon-lifebuoy"></i> <span
                                        class="m-nav__link-text">Support</span> </a> </li>
                                  <li class="m-nav__separator
                                                    m-nav__separator--fit"> </li>
                                  <li class="m-nav__item"> <a href="#" onclick="javascript:document.logoutform.submit()" class="btn m-btn--pill btn-secondary m-btn
                                                    m-btn--custom m-btn--label-brand
                                                    m-btn--bolder">Logout</a> </li>

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
                          class="m-nav__link m-dropdown__toggle"> <span class="m-nav__link-icon m-nav__link-icon-alt">
                            <span class="m-nav__link-icon-wrapper"> <i class="flaticon-grid-menu"></i> </span> </span>
                        </a> </li>
                    </ul>
                  </div>
                </div>
              </div>
              <!-- Tabination selection part - Ends -->
              <!-- ENDS Top Menu bar block -->
              <!-- BEGIN: Subheader -->
              <div class="m-subheader">
                <div class="d-flex align-items-center contr-hedr-brd pnl-hdr-sty" style="background-color:#62c0e1">
                  <div class="mr-auto">
                    <h3 class="m-subheader__title
                               m-subheader__title--separator"><i class="m-nav__link-icon
                               la la-home" style="font-size:1.5rem"></i> Search</h3>
                    <ul class="m-subheader__breadcrumbs m-nav m-nav--inline">
                      <li class="m-nav__separator"></li>
                      <li class="m-nav__item"> </li>
                    </ul>
                  </div>
                </div>
              </div>
              <!--  Top level Analytical Reporting Widgets/blocks -->
              <!--begin:: Widgets/Stats-->

              <div class="">
                <div class="m-portlet__body  m-portlet__body--no-padding">
                  <!--  START Sample Cards  -->
                  <div class="">
                    <div class="row">
                      <div class="col-md-3">
                        <div class="dash-card bg-c-blue order-card">
                          <div class="card-block">
                            <h5 class="m-b-20">Activity</h5>
                            {{$store.state.simpleSearch.stateShowAssayBindingDeleteAlert}}
                            <h2 class="text-right"><i class="fa fa-diagnoses f-left"></i><span> <span
                                  class="m-widget24__desc"></span> <span class="m-widget24__stats txt-sz-crd"> <span
                                    v-if="$store.state.simpleSearch.searchCount.actIdsCount || $store.state.simpleSearch.searchCount.actIdsCount == 0">{{$store.state.simpleSearch.searchCount.actIdsCount}}</span>
                                  <span v-else><q-spinner color="primary" size="1em" ></q-spinner> </span> </span></span></h2>
                            <!--  <p class="m-b-0">Activity Text Description<span class="f-right">351</span></p>  -->

                          </div>
                        </div>
                      </div>
                      <div class="col-md-3">
                        <div class="dash-card bg-c-green order-card">
                          <div class="card-block">
                            <h5 class="m-b-20">Reference</h5>
                            <h2 class="text-right"><i class="fa fa-project-diagram f-left"></i><span><span
                                  class="m-widget24__desc"></span> <span class="m-widget24__stats txt-sz-crd"> <span
                                    v-if="$store.state.simpleSearch.searchCount.refIdsCount || $store.state.simpleSearch.searchCount.refIdsCount == 0">{{$store.state.simpleSearch.searchCount.refIdsCount}}</span>
                                  <span v-else><q-spinner color="primary" size="1em"></q-spinner></span> </span></span></h2>
                            <!--   <p class="m-b-0">Reference Text Description<span class="f-right">351</span></p>  -->
                          </div>
                        </div>
                      </div>

                      <div class="col-md-3">
                        <div class="dash-card bg-c-yellow order-card">
                          <div class="card-block">
                            <h5 class="m-b-20">GVK ID</h5>
                            <h2 class="text-right"><i class="fa fa-address-card f-left"></i><span><span
                                  class="m-widget24__desc"></span> <span class="m-widget24__stats txt-sz-crd"> <span
                                    v-if="$store.state.simpleSearch.searchCount.gvkIdsCount  || $store.state.simpleSearch.searchCount.gvkIdsCount == 0">{{$store.state.simpleSearch.searchCount.gvkIdsCount}}</span>
                                  <span v-else><q-spinner color="primary" size="1em"></q-spinner></span> </span></span></h2>
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
                                  class="m-widget24__desc"></span> <span class="m-widget24__stats txt-sz-crd"> <span
                                    v-if="$store.state.simpleSearch.searchCount.strIdsCount  || $store.state.simpleSearch.searchCount.strIdsCount == 0">{{$store.state.simpleSearch.searchCount.strIdsCount}}</span>
                                  <span v-else><q-spinner color="primary" size="1em"></q-spinner></span> </span></span></h2>
                            <!--  <p class="m-b-0">Structure Text Description<span class="f-right">351</span></p>  -->
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <!--  ENDS Sample Cards -->
                </div>
              </div>

              <!--end:: Widgets/Stats-->

              <!-- END: Subheader -->
              <!-- STARTS Container block with chart related information -->
              <!-- Container block start from here -->
              <div class=""></div>
              <!--begin::Portlet-->
              <div class="">
                <div class="m-portlet__head">
                  <div class="m-portlet__head-caption">
                    <div class="m-portlet__head-title"> </div>
                  </div>
                </div>
                <!--begin::Form-->
                <div class="m-form m-form--fit m-form--label-align-right">
                  <!--  STARTS Container block from here -->
                  <div class="m-content simple-search">
                    <div class="row">
                      <!-- STARTS  Leftside Panel Block-->
                      <div class="col-lg-12">
                        <!--begin::Portlet-->
                        <div class="m-portlet" id="m_portlet">
                          <div class="m-portlet__head">
                            <div class="m-portlet__head-caption">
                              <div class="m-portlet__head-title"> <span class="m-portlet__head-icon"> <i
                                    class="flaticon-add"></i> </span>
                                <h3 class="hd-txt-col-wht"> Search Criterion </h3>
                              </div>
                            </div>
                          </div>
                          <div class="custm-scrll" id="scrl-stl-1">
                            <!-- Body part container section -->
                            <!--  STARTS Search Block Container  -->
                            <div class="row">
                              <div class="col-xl-12 col-lg-12">
                                <!--  <div class="m-portlet m-portlet--full-height
                          m-portlet--tabs m-portlet--rounded"> -->
                                <div class="m-portlet--tabs m-portlet--rounded">
                                  <div class="tab-content">
                                    <div class="tab-pane active">
                                      <form class="m-form m-form--fit
                                m-form--label-align-right">
                                        <div class="m-portlet__body2">
                                          <div id="m_repeater_3" style="margin:0px; " v-for="(item,itemIndex) in
                                      $store.state.simpleSearch.simpleSearchRowData"
                                            :key="item.simpleSearchSelectedValue">
                                            <div class="form-group m-form__group row" id="m_repeater_1">
                                              <div class="col-lg-12">
                                                <div class="m-stack m-stack--ver m-stack--tablet m-stack--demo"
                                                  style="padding-right:5px">
                                                  <div
                                                    class="m-stack__item m-stack__item--center m-stack__item--top blk-blu-bg">
                                                    <div class="row">
                                                      <div class="col-md-4 col-lg-4 col-sm-4">
                                                        <!--  START Search input control -->
                                                        <b-form-select v-model="item.simpleSearchSelectedValue"
                                                          :disabled="item.disableSimpleSearchSelect"
                                                          @input="simpleSearchOptionChange($event,item,itemIndex)">
                                                          <option :value="null" disabled>Please select an option
                                                          </option>
                                                          <option :value="item.simpleSearchSelectedValue"
                                                            v-if="item.simpleSearchSelectedValue" disabled>
                                                            {{item.simpleSearchSelectedValue}}</option>
                                                          <optgroup :label="group.label" v-for="group in
													                                            $store.state.simpleSearch.simpleSearchParents">
                                                            <option :value="opt.value" v-for="opt in
													                                            $store.state.simpleSearch.simpleSearchOptions" v-if="group.value ==
													                                            opt.parent &&
													                                            !opt.isDisabled">{{opt.label}}</option>
                                                          </optgroup>
                                                        </b-form-select>
                                                        <!--  ENDS Search Input control -->
                                                      </div>
                                                      <div class="col-md-8 col-lg-8 col-sm-8">
                                                        <div class="row">
                                                          <!-- autocomplete functionality -->
                                                          <!-- :disable-auto-complete="item.disableFields"  -->
                                                          <div class="col-md-11" v-if="item.simpleSearchSelectedValue == 
                                                          'bibliography'">
                                                          <div  v-if="item.fileName == '' &&
                                                          item.advanceSearch.autoComplete.length
                                                          == 0 && !item.isStructureAdvanceSearch && item.advanceSearchSelectedValue != 'ref_id'">
                                                              <auto-complete search-type="SIMPLE_SEARCH"
                                                                ref="simpleAutocomplete"
                                                                @show-alert-popup="showAlertPopup($event)"
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
                                                            <div class="col-md-10" v-else-if="item.fileName != ''">
                                                              <div class="freeze-field">{{item.fileName}}</div>
                                                            </div>
                                                            <div class="col-md-11"
                                                            v-else-if="item.bibliographySearch && item.bibliographySearch.customSearch">
                                                            <div class="freeze-field">
                                                              {{ item.advanceSearchSelectedValueName + '...' + ':count(' + item.advanceSearch.autoComplete.length + ')'}}
                                                              <div style="display:inline;position:relative"> ...
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
                                                              v-else-if="item.advanceSearch.autoComplete.length > 0">
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
                                                              :show-options-on-focus="['Assay', 'ActivityMechanism'].indexOf(item.simpleSearchSelectedValue) > -1  ? true : false"
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
                                                          <div class="col-md-10" v-else-if="item.fileName !=''">
                                                            <div class="freeze-field">{{item.fileName}}</div>
                                                          </div>
                                                          <!-- Freezed field for file upload end -->
                                                          <!-- Freezed field for target search start -->
                                                          <div class="col-md-11"
                                                            v-else-if="item.advanceSearch.autoComplete.length> 0">
                                                            <div class="freeze-field">
                                                              {{ $store.state.advanceSearch.selectedValueName  + ':count(' + item.advanceSearch.autoComplete.length + ')'}}
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
                                                          <div class="col-md-10"
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
                                                          <div class="col-md-10"
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
                                                          <div class="col-md-10"
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
                                                          <div class="col-md-10" v-else-if="item.isStructureAdvanceSearch && item.advanceSearchSelectedValue == 'structural'">
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
                                                          <div class="col-md-10" v-else-if="item.isStructureAdvanceSearch && item.advanceSearchSelectedValue == 'physico'">
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
                                                          <div class="col-md-1"
                                                            :class="{'disable-button':(item.disableAdvanceSearchButton  || item.disableFields)}">
                                                            <span class="btn-pick btn-md "
                                                              :class="{'btn-accent':!(item.disableAdvanceSearchButton  || item.disableFields), 'cursor-pointer': !(item.disableAdvanceSearchButton  || item.disableFields)}"
                                                              variant="primary"
                                                              @click="(item.disableAdvanceSearchButton  || item.disableFields) ? '' : openAdvanceSearchPopup(item, itemIndex)"
                                                              :disabled="item.disableAdvanceSearchButton"> <i
                                                                class="fa flaticon-more-v5"></i> </span>
                                                          </div>

                                                          <!--  ENDS Select Window popup pallet resource -->
                                                        </div>
                                                      </div>
                                                    </div>
                                                  </div>

                                                  <div
                                                    class="m-stack__item m-stack__item--center m-stack__item--bottom blk-gry-bg bdr-rds-btm">
                                                    <!--  STARTS  AND - OR - NOT / Refresh / Delete function -->
                                                    <div class="row justify-end">
                                                      <div class="col-md-2 col-sm-2 col-lg-2">
                                                        <div class="m-btn-group
                                                m-btn-group--pill btn-group
                                                m-btn-group m-btn-group--pill
                                                btn-group" role="group" aria-label="Small
                                                button group"
                                                          v-if="(itemIndex+1)!=$store.state.simpleSearch.simpleSearchRowData.length">

                                                          <!-- <b-form-radio value="OR" v-model="item.operator" @input="operatorChanged"> OR </b-form-radio> -->
                                                        </div>
                                                        <div
                                                          class="m-btn-group m-btn-group--pill btn-group m-btn-group m-btn-group--pill btn-group"
                                                          role="group" aria-label="Small button group" v-if="(itemIndex+1)!=$store.state.simpleSearch.simpleSearchRowData.length">
                                                          <button type="button" class="btn btn-outline-info">
                                                            <b-form-radio v-model="item.operator" value="AND"
                                                              @input="operatorChanged" class="radio-btn-wht"> AND
                                                            </b-form-radio>
                                                          </button>
                                                          <!-- <button type="button" class="btn btn-outline-info"><input type="radio" name="[0][1]" value="1"> OR</button>  -->
                                                          <button type="button" class="btn btn-outline-info">
                                                            <b-form-radio value="NOT" v-model="item.operator"
                                                              @input="operatorChanged" class="radio-btn-wht"> NOT
                                                            </b-form-radio>
                                                          </button>
                                                        </div>
                                                      </div>
                                                      <div class="col-md-2 col-sm-2 col-lg-2">
                                                        <div style="float:left">
                                                          <div calss="row">
                                                            <div class="col-lg-12 col-md-12 col-sm-12">
                                                              <div class="row items-center">
                                                                <div class="col-lg-8 col-md-8 col-sm-8"> <span
                                                                    class="btn-lx spn_dsply_in"
                                                                    @click="reset(item,itemIndex)"> <i
                                                                      class="flaticon-refresh"
                                                                      style="font-size:28px; font-weight:bold; color:#ffffff"></i>
                                                                  </span> </div>
                                                                <div class="col-lg-4 col-md-4 col-sm-4"> <span
                                                                    class="btn-lx"
                                                                    v-if="$store.state.simpleSearch.simpleSearchRowData.length>1"
                                                                    @click="deleteRow(item,itemIndex)"> <i
                                                                      class="flaticon-delete-2"
                                                                      style="font-size: 24px; color:#ffffff"></i>
                                                                  </span> </div>
                                                              </div>
                                                            </div>
                                                          </div>
                                                        </div>
                                                      </div>

                                                      <div class="col-md-1 col-sm-1 col-lg-1 "> <span
                                                          @click="addNewRow(item,itemIndex)" 
                                                          :disabled="(item.disableSimpleSearchSelect || disableAdd(item,itemIndex))"
                                                          :class="{'pointerNone':( item.disableSimpleSearchSelect || disableAdd(item,itemIndex))}">
                                                          <i class="fa fa-plus-circle
                                                  m-demo-icon__preview plus-icn-wht"></i> </span> </div>
                                                    </div>
                                                  </div>
                                                </div>
                                              </div>
                                            </div>
                                          </div>
                                        </div>
                                    </div>
                                    <div class="m-portlet__foot
                                  m-portlet__foot--fit">
                                      <div class="m-form__actions">
                                        <div class="row">
                                          <div class="col-12">
                                            <button type="button" class="btn
                                          btn-excelra" style="float:right" @click="onSubmit">Submit</button>
                                           <!--  <button type="button" class="btn
                                        btn-secondary btn-rght-ft">Cancel</button> -->
                                          </div>
                                        </div>
                                      </div>
                                      </form>
                                    </div>
                                    <div class="tab-pane"> </div>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <!--  ENDS Search Block Container -->

                          </div>
                        </div>
                        <!--end::Portlet-->
                      </div>
                      <!-- ENDS Leftside Panel Block-->
                      <!-- STARTS Rightside Container Panel Block -->
                      <!-- ENDS Rightside Container Panel Block -->
                    </div>
                  </div>
                  <!--  ENDS Container block from here -->

                  <div class="m-content">
                    <!--  Removed search elements block -->
                  </div>
                </div>
                <!--end::Form-->
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
                            m-stack__item--middle m-stack__item--last"> <span class="m-footer__copyright"> 2018 &copy;
                  <a href="https://www.gostardb.com/gostar/" target="_blank" class="m-link">GOSTAR - Excelra</a> </span>
              </div>
              <div class="m-stack__item m-stack__item--right
                            m-stack__item--middle m-stack__item--first">
                <ul class="m-footer__nav m-nav m-nav--inline
                               m--pull-right">
                  <li class="m-nav__item"> <a href="#" class="m-nav__link"> <span class="m-nav__link-text">About</span>
                    </a> </li>
                  <li class="m-nav__item"> <a href="#" class="m-nav__link"> <span
                        class="m-nav__link-text">Privacy</span> </a> </li>
                  <li class="m-nav__item"> <a href="#" class="m-nav__link"> <span class="m-nav__link-text">T&C</span>
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
    <!-- Loader  -->
    <app-loader v-if="showLoading"></app-loader>
    <!-- Modal popup for user access -->
    <b-modal id="modal-xl" ref="userAccessModal" :hide-footer="true" size="xl" >
      <template v-slot:modal-header>
       
          <h5 >Alert</h5>
          <div >
            <i class="fa fa-times" style="cursor:pointer" @click="$refs.userAccessModal.hide()"></i>
          </div>
        
      </template>
      Access denied! Please contact support team.
    </b-modal>
    <!-- Modal for assay binding validation when deleting rows -->
    <div v-if="showAssayBindingDeleteAlert || showAssayBindingRefreshAlert" class="backdrop-bg"></div>
    <div style="z-index: 112" class="modal-custom-content" v-if="showAssayBindingDeleteAlert">
      Your data will be lost do you want to continue?
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" @click="showAssayBindingDeleteAlert = false">Cancel</button>
        <button type="button" class="btn btn-excelra" @click="deleteOnConfirm">Yes</button>
      </div>
    </div>
    
    <div style="z-index: 112" class="modal-custom-content" v-if="showAssayBindingRefreshAlert">
      Your data will be lost do you want to continue?
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" @click="showAssayBindingRefreshAlert = false">Cancel</button>
        <button type="button" class="btn btn-excelra" @click="resetOnConfirm">Yes</button>
      </div>
    </div>
      <div v-if="openAdvSearch" class="backdrop-bg"></div>
    <div style="z-index: 112" class="modal-custom-content " v-if="openAdvSearch">
        <target-search @popup-closed="popupClosed"
        v-if="openAdvSearch && $store.state.advanceSearch.currentItem.simpleSearchSelectedValue =='Target'">
      </target-search>
      <structure-search @popup-closed="popupClosed"
      v-if="openAdvSearch && $store.state.advanceSearch.currentItem.simpleSearchSelectedValue =='Structure'">
    </structure-search>
      <bibliography-search @popup-closed="popupClosed"
      v-if="openAdvSearch && $store.state.advanceSearch.currentItem.simpleSearchSelectedValue =='bibliography'">
    </bibliography-search>
  </div>
     
    <div id="m_scroll_top" class="m-scroll-top"> <i class="la la-arrow-up"></i></div>
  </div>
  </div>

</body>
<script src="${contextPath}/assets/scripts/vendors.bundle.js" type="text/javascript"></script>
<!--begin::bundle scripts -->
<script src="${contextPath}/assets/scripts/scripts.bundle.js" type="text/javascript"></script>
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
<script type="text/javascript" src="${contextPath}/resources/css/page-loader/_scripts/main.js"></script>
<!--Marvin js scripts-->
<script src="${contextPath}/marvinjs/js/lib/rainbow/rainbow-custom.min.js"></script>
<!--  <script src="${contextPath}/marvinjs/js/lib/jquery-1.9.1.min.js"></script> -->
<script src="${contextPath}/marvinjs/gui/lib/promise-1.0.0.min.js"></script>
<script src="${contextPath}/marvinjs/js/marvinjslauncher.js"></script>
<script src="${contextPath}/marvinjs/gui/lib/promise-1.0.0.min.js"></script>
<script src="${contextPath}/marvinjs/js/marvinjslauncher.js"></script>
<script>
  type = "text/javascript"
  src = "${contextPath}/resources/js/utils/draw-structure.js "

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

<!-- shared data scripts -->

<script src="${contextPath}/resources/store/shared/actions.js"></script>
<script src="${contextPath}/resources/store/shared/getters.js"></script>
<script src="${contextPath}/resources/store/shared/mutations.js"></script>
<script src="${contextPath}/resources/store/shared/state.js"></script>

<!-- advance search search scripts -->

<script src="${contextPath}/resources/store/advance-search/actions.js"></script>
<script src="${contextPath}/resources/store/advance-search/getters.js"></script>
<script src="${contextPath}/resources/store/advance-search/mutations.js"></script>
<script src="${contextPath}/resources/store/advance-search/state.js"></script>

<!-- structure search scripts -->
<script src="${contextPath}/resources/store/structure-search/actions.js"></script>
<script src="${contextPath}/resources/store/structure-search/getters.js"></script>
<script src="${contextPath}/resources/store/structure-search/mutations.js"></script>
<script src="${contextPath}/resources/store/structure-search/state.js"></script>

<!-- bibliogrphy search scripts -->
<script src="${contextPath}/resources/store/bibliography-search/actions.js"></script>
<script src="${contextPath}/resources/store/bibliography-search/getters.js"></script>
<script src="${contextPath}/resources/store/bibliography-search/mutations.js"></script>
<script src="${contextPath}/resources/store/bibliography-search/state.js"></script>



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

<!-- bibliography advance search components -->
<script src="${contextPath}/resources/components/bibliography-advance-search/list-search.js"></script>
<script src="${contextPath}/resources/components/bibliography-advance-search/criterion-search.js"></script>
<script src="${contextPath}/resources/components/bibliography-advance-search/custom-search.js"></script>
<script src="${contextPath}/resources/components/bibliography-advance-search/bibliography-search.js"></script>

<script src="${contextPath}/resources/js/simple-search.js"></script>
<script src="${contextPath}/assets/scripts/login.js" type="text/javascript"></script>
<script src="${contextPath}/resources/components/reusable/read-more.js"></script>
</html>