
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
    <div class="mdl-layout mdl-js-layout">
        <main class="mdl-layout__content">
            <div class="mdl-card mdl-shadow--6dp">
                <div class="mdl-card__title mdl-color--primary mdl-color-text--white">
                    <h3 class="mdl-card__title-text">Login</h3>
                </div>
                <div class="mdl-card__supporting-text">
                    <form action="#">
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
                            <a href=""><button class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Log in</button></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="register">
                <a href="">Create an account</a>
            </div>
        </main>
    </div>
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
                  DATE: Sun Jun 05 20:02:22 EDT 2016
                  SOURCE: /Users/sunho207/courses/cs2340/ffff00/app/views/login.scala.html
                  HASH: 1be6ec556caeff982dcf236f1639ceddc535f09e
                  MATRIX: 827->0|1269->416|1283->422|1345->464
                  LINES: 32->1|38->7|38->7|38->7
                  -- GENERATED --
              */
          