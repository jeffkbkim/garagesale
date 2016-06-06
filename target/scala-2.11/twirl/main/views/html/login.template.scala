
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
                            <label class="mdl-textfield__label" for="firstname">First Name</label>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label lastname registration">
                            <input class="mdl-textfield__input" type="text" id="lname" />
                            <label class="mdl-textfield__label" for="lastname">Last Name</label>
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
                <div class="mdl-card__actions mdl-card--border">
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
                </div>
            </div>
            <div class="register">
                <a href="/#">Create an account</a>
            </div>
        </main>
    </div>
    <script   src="https://code.jquery.com/jquery-2.2.4.min.js"   integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="   crossorigin="anonymous"></script>
    <script src=""""),_display_(/*73.19*/routes/*73.25*/.Assets.versioned("javascripts/typed.js")),format.raw/*73.66*/(""""></script>
    <script src=""""),_display_(/*74.19*/routes/*74.25*/.Assets.versioned("javascripts/login-registration.js")),format.raw/*74.79*/(""""></script>
    <script>
        $(function()"""),format.raw/*76.21*/("""{"""),format.raw/*76.22*/("""
            """),format.raw/*77.13*/("""$(".title-logo").typed("""),format.raw/*77.36*/("""{"""),format.raw/*77.37*/("""
                """),format.raw/*78.17*/("""strings: ["#ffff00 ^400", "Buy... ^400", "Sell... ^400", "Trade... ^400", "#ffff00"],
                typeSpeed: 70,
                backSpeed: 30,
                backDelay: 100
            """),format.raw/*82.13*/("""}"""),format.raw/*82.14*/(""");
        """),format.raw/*83.9*/("""}"""),format.raw/*83.10*/(""");
    </script>
    <script src="https://code.getmdl.io/1.1.3/material.min.js"></script>
</body>
<footer class="mdl-mini-footer">
    <div class="mdl-mini-footer__right-section">
        #ffff00 Version 1.0
    </div>
</footer>

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
                  DATE: Sun Jun 05 22:50:10 EDT 2016
                  SOURCE: C:/Users/Douglas/Documents/GitHub/ffff00/app/views/login.scala.html
                  HASH: 90e06a882fdf60e1ed39a2e00561fde6d8ceed8c
                  MATRIX: 827->0|1275->422|1289->428|1351->470|5535->4627|5550->4633|5612->4674|5670->4705|5685->4711|5760->4765|5835->4812|5864->4813|5906->4827|5957->4850|5986->4851|6032->4869|6255->5064|6284->5065|6323->5077|6352->5078
                  LINES: 32->1|38->7|38->7|38->7|104->73|104->73|104->73|105->74|105->74|105->74|107->76|107->76|108->77|108->77|108->77|109->78|113->82|113->82|114->83|114->83
                  -- GENERATED --
              */
          