
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object login_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._

class login extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.1*/("""<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.1.3/material.cyan-light_blue.min.css">
    <link rel="stylesheet" media="screen" href=""""),_display_(/*7.50*/routes/*7.56*/.Assets.versioned("stylesheets/login.css")),format.raw/*7.98*/("""">
    <title>FFFF00</title>
</head>

<body>
    <div id="translucent-background"></div>
    <div class="mdl-layout mdl-js-layout">
        <div class="mdl-typography--display-4">
        <div class="mdl-logo title-logo">#ffff00</div>
        </div>
        <main class="mdl-layout__content">
            <div class="mdl-card mdl-shadow--6dp login-window">
<!--                 <div class="mdl-card__title mdl-color--primary mdl-color-text--white">
                    <h3 class="mdl-card__title-text">Login</h3>
                </div> -->
                <div class="mdl-card__supporting-text">
                    <form action="#" class="login-form">
                        <i class="material-icons">person</i>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <input class="mdl-textfield__input" type="text" id="username" />
                            <label class="mdl-textfield__label" for="username">Username</label>
                        </div>
                        <i class="material-icons">lock</i>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                            <input class="mdl-textfield__input" type="password" id="password" />
                            <label class="mdl-textfield__label" for="password">Password</label>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label firstname registration">
                            <input class="mdl-textfield__input" type="text" id="fname" />
                            <label class="mdl-textfield__label" for="fname">First Name</label>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label lastname registration">
                            <input class="mdl-textfield__input" type="text" id="lname" />
                            <label class="mdl-textfield__label" for="lname">Last Name</label>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label emailaddress registration">
                            <input class="mdl-textfield__input" type="text" id="email" />
                            <label class="mdl-textfield__label" for="email">Email Address</label>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label phonenumber registration">
                            <input class="mdl-textfield__input" type="text" pattern="[0-9]*" id="phone" />
                            <label class="mdl-textfield__label" for="phone">Phone</label>
                        </div>

                    </form>
                </div>
                <div class="mdl-card__actions mdl-card--border login-elements">
                    <div class="mdl-grid">
                        <div class="mdl-cell mdl-cell--6-col mdl-cell--4-col-tablet mdl-cell--2-col-phone">
                            <label class="mdl-checkbox mdl-js-checkbox" for="checkbox">
                                <input type="checkbox" id="checkbox" class="mdl-checkbox__input" checked>
                                <span class="mdl-checkbox__label">Remember me</span>
                            </label>
                        </div>
                        <div class="mdl-cell mdl-cell--6-col mdl-cell--4-col-tablet mdl-cell--2-col-phone login">
                            <a href=""><button class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect login-button">Log in</button></a>
                        </div>
                    </div>
                    <div class="mdl-grid registration">
                        <div class="mdl-cell mdl-cell--6-col mdl-cell--4-col-tablet mdl-cell--2-col-phone">
                            <a href="/#"><button class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect cancel-button">Cancel</button></a>
                        </div>
                        <div class="mdl-cell mdl-cell--6-col mdl-cell--4-col-tablet mdl-cell--2-col-phone">
                            <a href=""><button class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect register-button">Register</button></a>
                        </div>
                    </div>
                </div>
                <div class="mdl-card__actions mdl-card--border registration">
                    <div class="mdl-grid">
                        <div class="mdl-cell mdl-cell--6-col mdl-cell--4-col-tablet mdl-cell--2-col-phone">
                            <a href="/#"><button class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect cancel-button">Cancel</button></a>
                        </div>
                        <div class="mdl-cell mdl-cell--6-col mdl-cell--4-col-tablet mdl-cell--2-col-phone">
                            <a href=""><button class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect register-button">Register</button></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="register">
                <a href="/#">Create an account</a>
            </div>

        </main>

    <script   src="https://code.jquery.com/jquery-2.2.4.min.js"   integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="   crossorigin="anonymous"></script>
    <script src=""""),_display_(/*92.19*/routes/*92.25*/.Assets.versioned("javascripts/typed.js")),format.raw/*92.66*/(""""></script>
    <script src=""""),_display_(/*93.19*/routes/*93.25*/.Assets.versioned("javascripts/login-registration.js")),format.raw/*93.79*/(""""></script>
    <script>
        $(function()"""),format.raw/*95.21*/("""{"""),format.raw/*95.22*/("""
            """),format.raw/*96.13*/("""$(".title-logo").typed("""),format.raw/*96.36*/("""{"""),format.raw/*96.37*/("""
                """),format.raw/*97.17*/("""strings: ["#ffff00 ^400", "Buy... ^400", "Sell... ^400", "Trade... ^400", "#ffff00"],
                typeSpeed: 70,
                backSpeed: 30,
                backDelay: 100
            """),format.raw/*101.13*/("""}"""),format.raw/*101.14*/(""");
        """),format.raw/*102.9*/("""}"""),format.raw/*102.10*/(""");
    </script>
    <script src="https://code.getmdl.io/1.1.3/material.min.js"></script>
    
</body>


</html>"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


}

/**/
object login extends login_Scope0.login
              /*
                  -- GENERATED --
                  DATE: Sun Jun 05 23:35:14 EDT 2016
                  SOURCE: /Users/sunho207/courses/cs2340/ffff00/app/views/login.scala.html
                  HASH: 31ab1ca7e7e950a84aa1bb5f777e445edd1d5e3b
                  MATRIX: 827->0|1269->416|1283->422|1345->464|6904->5996|6919->6002|6981->6043|7038->6073|7053->6079|7128->6133|7201->6178|7230->6179|7271->6192|7322->6215|7351->6216|7396->6233|7616->6424|7646->6425|7685->6436|7715->6437
                  LINES: 32->1|38->7|38->7|38->7|123->92|123->92|123->92|124->93|124->93|124->93|126->95|126->95|127->96|127->96|127->96|128->97|132->101|132->101|133->102|133->102
                  -- GENERATED --
              */
          