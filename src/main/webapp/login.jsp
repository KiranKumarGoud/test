<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <!-- Do you need Material Icons? -->
    <link
            href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons"
            rel="stylesheet"
            type="text/css"
    />
    <!-- Do you need Fontawesome? -->
    <link
            href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
            rel="stylesheet"
    />
    <!-- Do you need all animations? -->
    <link
            href="https://cdn.jsdelivr.net/npm/animate.css@^3.5.2/animate.min.css"
            rel="stylesheet"
    />
    <!--
       Finally, add Quasar's CSS:
       Replace version below (1.0.0-beta.0) with your desired version of Quasar.
       Add ".rtl" for the RTL support (example: quasar.rtl.min.css).
       -->
    <link
            href="https://cdn.jsdelivr.net/npm/quasar@^1.0.0-beta.0/dist/quasar.min.css"
            rel="stylesheet"
            type="text/css"
    />
</head>
<body>
<div id="q-app">
    <div>
        <div class="m-grid m-grid--hor m-grid--root m-page">
            <div
                    class="m-grid__item m-grid__item--fluid m-grid m-grid--hor m-login m-login--signin m-login--2 m-login-2--skin-2"
                    id="m_login" style="height:100%;background-image: url(resources/quasar/statics/bg/login_background_11.jpg); background-size:cover;">
                <div class="m-grid__item m-grid__item--fluid m-login__wrapper">
                    <div class="flex flex-center">
                        <div class="m-login__container">
                            <div class="m-login__logo" style="padding-top:160px;">
                                <a href="#">
                                    <img src="resources/quasar/assets/images/logos/Gostar_Logo_mediam.png" class="loginLogo" alt>
                                </a>
                            </div>
                            <form action="${contextPath}/login" method="post" class="q-gutter-md" >
                                <div>
                                    <div class="row q-gutter-md items-center">
                                        <div class="col-12 loginInput">
                                            <q-input bg-color="white" outlined color="black" v-model.trim="username" :dense="true"
                                                     name="username" label="Username"  />
                                        </div>
                                        <div class="col-12 loginInput">
                                            <q-input outlined bg-color="white" color="black" type="password" v-model="password" :dense="true"
                                                     name="password" label="Password" />
                                        </div>
                                        <div class="col-12 alignCenter">
                                            <q-btn color="primary" type="submit" label="Login" />
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Do you want IE support? Replace "1.0.0-beta.0" with your desired Quasar version -->
<script src="https://cdn.jsdelivr.net/npm/quasar@^1.0.0-beta.0/dist/quasar.ie.polyfills.umd.min.js"></script>
<!-- You need Vue too -->
<script src="https://cdn.jsdelivr.net/npm/vue@latest/dist/vue.min.js"></script>
<!--
   Add Quasar's JS:
   Replace version below (1.0.0-beta.0) with your desired version of Quasar.
   -->
<script src="https://cdn.jsdelivr.net/npm/quasar@^1.0.0-beta.0/dist/quasar.umd.min.js"></script>
<!--
   If you want to make Quasar components (not your own) use a specific set of icons (unless you're using Material Icons already).
   Replace version below (1.0.0-beta.0) with your desired version of Quasar.
   Icon sets list: https://github.com/quasarframework/quasar/tree/dev/quasar/icon-set
   -->
<script src="https://cdn.jsdelivr.net/npm/quasar@^1.0.0-beta.0/dist/icon-set/fontawesome-v5.umd.min.js"></script>
<script>
    // console.log(Quasar)
    // if using a Quasar language pack other than the default "en-us";
    // requires the language pack style tag from above
    // Quasar.lang.set(Quasar.lang.ptBr); // notice camel-case "ptBr"

    // if you want Quasar components to use a specific icon library
    // other than the default Material Icons;
    // requires the icon set style tag from above
    Quasar.iconSet.set(Quasar.iconSet.fontawesomeV5); // fontawesomeV5 is just an example

    /*
    Example kicking off the UI.
    Obviously, adapt this to your specific needs.
    */

    // custom component example, assumes you have a <div id="my-page"></div> in your <body>
    Vue.component("my-page", {
        template: "#my-page"
    });

    // start the UI; assumes you have a <div id="q-app"></div> in your <body>
    new Vue({
        el: "#q-app",
        data() {
            return {
                username: "",
                password: "",
                submitCLicked: false
            };
        }
        // ...etc
    });
</script>
</body>
</html>