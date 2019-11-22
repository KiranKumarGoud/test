<%--
  Created by IntelliJ IDEA.
  User: venkateswarlu.s
  Date: 10-05-2019
  Time: 11:08
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
<title>GOSTARNext | User Authontication</title>
<meta name="description" content="Latest updates and statistic charts">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">

<!--begin::Web font --> 
<script src="https://ajax.googleapis.com/ajax/libs/webfont/1.6.16/webfont.js"></script> 
<script>
			WebFont.load({
				google: {
					"families": ["Roboto:300,400,500,600,700", "Poppins:300,400,500,600,700"]
				},
				active: function() {
					sessionStorage.fonts = true;
				}
			});
</script> 
    
<!-- STARTS Script & Styles  for background image changes on refresh -->
	<script  type="text/javascript">
		function changeImg(imgNumber)	{
			var myImages = ["assets/images/bg/login_background_10.jpg", "assets/images/bg/login_background_12.jpg", "assets/images/bg/login_background_13.jpg", "assets/images/bg/login_background_16.jpg",  "assets/images/bg/login_background_19.jpg", "assets/images/bg/login_background_20.jpg", "assets/images/bg/login_background_21.jpg", "assets/images/bg/login_background_23.jpg", "assets/images/bg/login_background_24.jpg", "assets/images/bg/login_background_25.jpg", "assets/images/bg/login_background_26.jpg", "assets/images/bg/login_background_27.jpg"]; 
			var imgShown = document.body.style.backgroundImage;
			var newImgNumber =Math.floor(Math.random()*myImages.length);
			document.body.style.backgroundImage = 'url('+myImages[newImgNumber]+')';
		}
		window.onload=changeImg;
	</script>   
    
<style type="text/css">
	.bg-back {background-attachment:fixed; background-repeat: no-repeat; background-position: center center; background-size: cover;  -webkit-background-size: cover; -moz-background-size: cover; -o-background-size: cover;}
</style>
<!-- ENDS Script & Styles for background image changes on refresh -->

<!-- STARTS background animated waves effects styles -->    
<style>
    .validation-error {
            display: none;
        }
        body {
            min-height: 100vh;
        }
@keyframes move_wave {
    0% {
        transform: translateX(0) translateZ(0) scaleY(1)
    }
    50% {
        transform: translateX(-25%) translateZ(0) scaleY(0.55)
    }
    100% {
        transform: translateX(-50%) translateZ(0) scaleY(1)
    }
}
.waveWrapper {
    overflow: hidden;
    position: absolute;
    left: 0;
    right: 0;
    bottom: 0;
    top: 0;
    margin: auto;
}
.waveWrapperInner {
    position: absolute;
    width: 100%;
    overflow: hidden;
    height: 100%;
    bottom: 0px;
    background-image: #0099cc; 
   /* background-image: linear-gradient(to top, #86377b 20%, #27273c 80%); */
    
}
.bgTop {
    z-index: 15;
    opacity: 0.5;
}
.bgMiddle {
    z-index: 10;
    opacity: 0.75;
}
.bgBottom {
    z-index: 5;
}
.wave {
    position: absolute;
    left: 0;
    width: 200%;
    height: 100%;
    background-repeat: repeat no-repeat;
    background-position: 0 bottom;
    transform-origin: center bottom;
}
.waveTop {
    background-size: 50% 100px;
}
.waveAnimation .waveTop {
  animation: move-wave 3s;
   -webkit-animation: move-wave 3s;
   -webkit-animation-delay: 1s;
   animation-delay: 1s;
}
.waveMiddle {
    background-size: 50% 120px;
}
.waveAnimation .waveMiddle {
    animation: move_wave 10s linear infinite;
}
.waveBottom {
    background-size: 50% 100px;
}
.waveAnimation .waveBottom {
    animation: move_wave 15s linear infinite;
}
</style>    
<!-- ENDS background animated waves effects styles -->     
    
    
<!--end::Web font --> 
<!--begin::Base Styles -->
<link href="${contextPath}/assets/styles/vendors.bundle.css" rel="stylesheet" type="text/css" />
<!--begin::style-bundle -->
<link href="${contextPath}/assets/styles/style.bundle.css" type="text/css" rel="stylesheet" />
<!--end::Base Styles -->
<link rel="shortcut icon" href="${contextPath}/assets/images/logos/favicon.ico" />
 <!-- Load Vue followed by BootstrapVue -->
 <script src="http://unpkg.com/vue@latest/dist/vue.min.js"></script>
</head>
<!-- end::Head -->

<!-- begin::Body -->
<body class="m--skin- m-header--fixed m-header--fixed-mobile m-aside-left--enabled m-aside-left--skin-dark m-aside-left--fixed m-aside-left--offcanvas m-footer--push m-aside--offcanvas-default bg-back">
<!-- begin:: Page -->
<div class="m-grid m-grid--hor m-grid--root m-page">
  <div class="m-grid__item m-grid__item--fluid m-grid m-grid--hor m-login m-login--signin m-login--2 m-login-2--skin-2" id="m_login" >
 

<!-- STARTS Login page Waves animation at bottom of the page -->
<div class="waveWrapper waveAnimation">
  <div class="waveWrapperInner bgTop">
    <div class="wave waveTop" style="background-image: url('${contextPath}/assets/images/bg/wave-top.png')"></div>
  </div>
  <div class="waveWrapperInner bgMiddle">
    <div class="wave waveMiddle" style="background-image: url('${contextPath}/assets/images/bg/wave-mid.png')"></div>
  </div>
  <div class="waveWrapperInner bgBottom">
    <div class="wave waveBottom" style="background-image: url('${contextPath}/assets/images/bg/wave-bot.png')"></div>
  </div>
</div>
<!-- ENDS Login page Waves animation at bottom of the page -->
      
      
      
<!--<div class="m-grid__item m-grid__item--fluid m-grid m-grid--hor m-login m-login--signin m-login--2 m-login-2--skin-2" id="m_login" style="background-image: url(assets/images/bg/login_background_23-A.jpg);">  -->
    <div class="m-grid__item m-grid__item--fluid m-login__wrapper" style="z-index: 1000; cursor:auto ">
      <div class="m-login__container">
        <div class="m-login__logo"> <a href="#"> <img src="${contextPath}/assets/images/logos/GOSTARNext_Logo_main_02.png" alt="" width="100%" > </a> </div>
        <div class="m-login__signin">
          <div class="m-login__head">
            <h3 class="m-login__title">Sign In To Admin</h3>
          </div>
          <form class="m-login__form m-form" name="loginForm" action="${contextPath}/login" method="post" @submit="onLogin">

            <div class="form-group m-form__group">
                <input class="form-control m-input heght-40px" type="text" placeholder="Email" name="username" v-model="username" autocomplete="off">
                <div class="validation-error" v-if="servererror && !$v.username.required &&
        submitCLicked">This field is required </div>

                <%--<div class="validation-error" v-if="servererror && !$v.username.email &&
        submitCLicked">Please enter a valid email
                </div>--%>

            </div>
            <div class="heght-20px"></div>
            <div class="form-group m-form__group">
                <input class="form-control m-input m-login__form-input--last
        heght-40px" type="password" v-model="password" placeholder="Password" name="password">
                <div class="validation-error" v-if="servererror && !$v.password.required &&
        submitCLicked">This field is required </div>
            </div>

            <p v-if="servererror && errors.length">
                <b class="validation-error">Please correct the following error(s):</b>
            <ul>
                <li class="validation-error" v-for="error in errors">{{ error }}</li>
            </ul>
            </p>
            <div class="validation-error" v-if="servererror">Invalid user credentials</div>

            <div class="row m-login__form-sub">
                <div class="col m--align-left m-login__form-left">
                    <label class="m-checkbox m-checkbox--focus">
                        <input type="checkbox" name="remember"> Remember me
                        <span></span>
                    </label>
                </div>
                <div class="col m--align-right m-login__form-right">
                    <a href="javascript:;" id="m_login_forget_password" class="m-link">Forgot Password ?</a>
                </div>
            </div>

            <div class="m-login__form-action">
                <!--<button id="m_login_signin_submit" class="btn m-btn btn-success m-btn--pill m-btn--custom m-btn--air m-login__btn m-login__btn--primary">Sign In</button> -->
                <button type="submit" class="btn
        btn-info btn-lg">Sign In</button>
            </div>
        </form>
        </div>
        <div class="m-login__signup">
          <div class="m-login__head">
            <h3 class="m-login__title">Sign Up</h3>
            <div class="m-login__desc">Enter your details to create your account:</div>
          </div>
          <form class="m-login__form m-form" action="">
            <div class="form-group m-form__group">
              <input class="form-control m-input heght-40px" type="text" placeholder="Fullname" name="fullname">
            </div>
            <div class="heght-20px"></div>
            <div class="form-group m-form__group">
              <input class="form-control m-input heght-40px" type="text" placeholder="Email" name="email" autocomplete="off">
            </div>
            <div class="heght-20px"></div>
            <div class="form-group m-form__group">
              <input class="form-control m-input heght-40px" type="password" placeholder="Password" name="password">
            </div>
            <div class="heght-20px"></div>
            <div class="form-group m-form__group">
              <input class="form-control m-input m-login__form-input--last heght-40px" type="password" placeholder="Confirm Password" name="rpassword">
            </div>
            <div class="row form-group m-form__group m-login__form-sub">
              <div class="col m--align-left">
                <label class="m-checkbox m-checkbox--focus">
                  <input type="checkbox" name="agree">
                  I Agree the <a href="#" class="m-link m-link--focus">terms and conditions</a>. <span></span> </label>
                <span class="m-form__help"></span> </div>
            </div>
            <div class="m-login__form-action">
              <button id="m_login_signup_submit" class="btn btn-info btn-lg">Sign Up</button>
              &nbsp;&nbsp;
              <button id="m_login_signup_cancel" class="btn btn-outline-info btn-lg">Cancel</button>
            </div>
          </form>
        </div>
        <div class="m-login__forget-password">
          <div class="m-login__head">
            <h3 class="m-login__title">Forgotten Password ?</h3>
            <div class="m-login__desc">Enter your email to reset your password:</div>
          </div>
          <form class="m-login__form m-form" action="">
            <div class="form-group m-form__group">
              <input class="form-control m-input heght-40px" type="text" placeholder="Email" name="email" id="m_email" autocomplete="off">
            </div>
            <div class="m-login__form-action">
              <button id="m_login_forget_password_submit" class="btn btn-info btn-lg">Request</button>
              &nbsp;&nbsp;
              <button id="m_login_forget_password_cancel" class="btn btn-outline-info btn-lg">Cancel</button>
            </div>
          </form>
        </div>
        <div class="m-login__account signup-back"> <span class="m-login__account-msg signup-txt"> Don't have an account yet ? </span>&nbsp;&nbsp; <a href="javascript:;" id="m_login_signup" class="m-link m-link--light m-login__account-link" style="color:#0099cc; font-weight: bold">Sign Up</a> </div>
      </div>
    </div>
  </div>
</div>

    
    
<!-- end:: Page --> 

<!--begin::Base Scripts --> 
<script src="${contextPath}/assets/scripts/vendors.bundle.js" type="text/javascript"></script> 
<script src="https://unpkg.com/vuelidate@0.7.4/dist/validators.min.js" type="text/javascript"></script>
<script src="https://unpkg.com/vuelidate@0.7.4/dist/vuelidate.min.js" type="text/javascript"></script>
<script src="${contextPath}/assets/scripts/scripts.bundle.js" type="text/javascript"></script> 

<!--end::Base Scripts --> 
<script>
        $(window).on('load', function () {
            $('body').removeClass('m-page--loading')
        });
    
        $.ajax('${contextPath}/appLogout', {
            dataType: 'json',
            method: 'post',
            data: {
                currentSession: localStorage.getItem('userSessionId')
            }
        });
    
        Vue.use(window.vuelidate.default)
        const {
            required, email, minLength
        } = window.validators
        new Vue({
            el: "#app",
            data: {
                text: '',
                username: "",
                password: "",
                submitCLicked: false,
                errors: [],
                servererror: ""
            },
            validations: {
                username: {
                    required,
                    email
                },
                password: {
                    required
                }
            },
            created() {
                urlParams = new URLSearchParams(window.location.search);
                this.servererror = urlParams.get('error');
                 localStorage.clear();
            },
            methods: {
                onLogin(e) {
    
                    if (this.username && this.password) {
                        return true;
                    }
    
                    this.errors = [];
                    if(!this.username) {
                        this.errors.push('Username is required.');
                    }
    
                    if(!this.password) {
                        this.errors.push('Password is required.');
                    }
    
                    e.preventDefault();
    
    
                },
                status(validation) {
                    return {
                        error: validation.$error,
                        dirty: validation.$dirty
                    }
                }
            }
        })
    </script>

<!--begin::Page Snippets --> 
<!-- <script src="${contextPath}/assets/demo/default/assets/snippets/custom/pages/user/login.js" type="text/javascript"></script> --> 
<script src="${contextPath}/assets/scripts/login.js" type="text/javascript"></script> 


    
<!--end::Page Snippets -->
</body>
    
    
<!-- STARTS Script for background image changes on refresh -->    
<script type="text/javascript"> 
ChangeIt();
</script> 
<!-- ENDS Script for background image changes on refresh --> 

<!-- end::Body -->
</html>