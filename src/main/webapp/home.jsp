<%--
  Created by IntelliJ IDEA.
  User: venkateswarlu.s
  Date: 02-05-2019
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Do you need Material Icons? -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons" rel="stylesheet" type="text/css">

    <!-- Do you need Fontawesome? -->
    <link href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" rel="stylesheet">

    <!-- Do you need Ionicons? -->
    <link href="https://cdn.jsdelivr.net/npm/ionicons@^4.0.0/dist/css/ionicons.min.css" rel="stylesheet">

    <!-- Do you need MDI? -->
    <link href="https://cdn.jsdelivr.net/npm/@mdi/font@^3.0.0/css/materialdesignicons.min.css" rel="stylesheet">

    <!-- Do you need all animations? -->
    <link href="https://cdn.jsdelivr.net/npm/animate.css@^3.5.2/animate.min.css" rel="stylesheet">


    <!--
      Finally, add Quasar's CSS:
      Replace version below (1.0.0-beta.0) with your desired version of Quasar.
      Add ".rtl" for the RTL support (example: quasar.rtl.min.css).
    -->
    <link href="https://cdn.jsdelivr.net/npm/quasar@^1.0.0-beta.0/dist/quasar.min.css" rel="stylesheet" type="text/css">
</head>

<body>




<div id="q-app">
    <div data-v-92b70de4="">
        <div data-v-92b70de4="" class="m-grid m-grid--hor m-grid--root m-page">
            <div data-v-92b70de4="" id="m_login" class="m-grid__item m-grid__item--fluid m-grid m-grid--hor m-login m-login--signin m-login--2 m-login-2--skin-2" style="background-image: url(&quot;resources/img/bg/login_background_11.jpg&quot;); background-size: cover;">
                <div data-v-92b70de4="" class="m-grid__item m-grid__item--fluid m-login__wrapper">
                    <div data-v-92b70de4="" class="flex flex-center">
                        <div data-v-92b70de4="" class="m-login__container">
                            <div data-v-92b70de4="" class="m-login__logo" style="padding-top: 160px;"><a data-v-92b70de4="" href="#"><img data-v-92b70de4="" src="resources/img/Gostar_Logo_mediam.png" alt="" class="loginLogo"></a></div>
                            <form data-v-92b70de4="" class="q-gutter-md">
                                <div data-v-92b70de4="">
                                    <div data-v-92b70de4="" class="row q-gutter-md items-center">
                                        <div data-v-92b70de4="" class="col-12 loginInput">
                                            <div data-v-92b70de4="" class="q-field row no-wrap items-start q-input q-field--outlined q-field--labeled q-field--dense">
                                                <div class="q-field__inner relative-position col self-stretch column justify-center">
                                                    <div tabindex="-1" class="q-field__control relative-position row no-wrap text-black bg-white">
                                                        <div class="q-field__control-container col relative-position row no-wrap q-anchor--skip">
                                                            <input tabindex="0" aria-label="Email" type="text" class="q-field__native">
                                                            <div class="q-field__label no-pointer-events absolute ellipsis">Email</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!---->
                                        </div>
                                        <div data-v-92b70de4="" class="col-12 loginInput">
                                            <div data-v-92b70de4="" class="q-field row no-wrap items-start q-input q-field--outlined q-field--labeled q-field--dense">
                                                <div class="q-field__inner relative-position col self-stretch column justify-center">
                                                    <div tabindex="-1" class="q-field__control relative-position row no-wrap text-black bg-white">
                                                        <div class="q-field__control-container col relative-position row no-wrap q-anchor--skip">
                                                            <input tabindex="0" aria-label="Password" type="password" class="q-field__native">
                                                            <div class="q-field__label no-pointer-events absolute ellipsis">Password</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!---->
                                        </div>
                                        <div data-v-92b70de4="" class="col-12 alignCenter">
                                            <button data-v-92b70de4="" tabindex="0" type="submit" class="q-btn inline q-btn-item non-selectable q-btn--standard q-btn--rectangle bg-primary text-white q-focusable q-hoverable">
                                                <div tabindex="-1" class="q-focus-helper"></div>
                                                <div class="q-btn__content text-center col items-center q-anchor--skip justify-center row">
                                                    <div>Login</div>
                                                </div>
                                            </button>
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
  If you want to add a Quasar Language pack (other than "en-us").
  Notice "pt-br" in "i18n.pt-br.umd.min.js" for Brazilian Portuguese language pack.
  Replace version below (1.0.0-beta.0) with your desired version of Quasar.
  Also check final <script> tag below to enable the language
  Language pack list: https://github.com/quasarframework/quasar/tree/dev/quasar/lang
-->
<script src="https://cdn.jsdelivr.net/npm/quasar@^1.0.0-beta.0/dist/lang/pt-br.umd.min.js"></script>

<!--
  If you want to make Quasar components (not your own) use a specific set of icons (unless you're using Material Icons already).
  Replace version below (1.0.0-beta.0) with your desired version of Quasar.
  Icon sets list: https://github.com/quasarframework/quasar/tree/dev/quasar/icon-set
-->
<script src="https://cdn.jsdelivr.net/npm/quasar@^1.0.0-beta.0/dist/icon-set/fontawesome-v5.umd.min.js"></script>

<script>
    // if using a Quasar language pack other than the default "en-us";
    // requires the language pack style tag from above
    // Quasar.lang.set(Quasar.lang.ptBr) // notice camel-case "ptBr"

    // if you want Quasar components to use a specific icon library
    // other than the default Material Icons;
    // requires the icon set style tag from above
    // Quasar.icons.set(Quasar.iconSet.fontawesomeV5) // fontawesomeV5 is just an example

    /*
      Example kicking off the UI.
      Obviously, adapt this to your specific needs.
     */

    // custom component example, assumes you have a <div id="my-page"></div> in your <body>
    Vue.component('my-page', {
        template: '#my-page'
    })

    // start the UI; assumes you have a <div id="q-app"></div> in your <body>
    new Vue({
        el: '#q-app',
        data: function () {
            return {}
        },
        methods: {},
        // ...etc
    })
</script>
</body>
