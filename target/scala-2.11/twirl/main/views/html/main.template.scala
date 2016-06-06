
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object main_Scope0 {
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

class main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(title: String)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.32*/("""

"""),format.raw/*3.1*/("""<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="A front-end template that helps you build fast, modern mobile web apps.">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>FFFF00</title>

    <link rel="shortcut icon" href=""""),_display_(/*11.38*/routes/*11.44*/.Assets.versioned("images/favicon.png")),format.raw/*11.83*/("""">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.1.3/material.cyan-light_blue.min.css">
    <link rel="stylesheet" media="screen" href=""""),_display_(/*16.50*/routes/*16.56*/.Assets.versioned("stylesheets/main.css")),format.raw/*16.97*/("""">
  </head>
  <body>
    <div class="layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">


      <div class="drawer mdl-layout__drawer mdl-color-text--blue-grey-50">
        <header class="drawer-header">
          <img src=""""),_display_(/*24.22*/routes/*24.28*/.Assets.versioned("images/user.png")),format.raw/*24.64*/("""" class="user">
          <div class="user-dropdown">
            <span class="user-text">Welcome Hayun</span>
            <div class="mdl-layout-spacer"></div>
            <button id="accbtn" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon user-text">
              <i class="material-icons" role="presentation">arrow_drop_down</i>
              <span class="visuallyhidden">Accounts</span>
            </button>
            <ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect" for="accbtn">
              <li class="mdl-menu__item"><i class="material-icons">settings</i>Profile</li>
              <li class="mdl-menu__item"><i class="material-icons">person</i>Logout</li>
            </ul>
          </div>
        </header>

        <nav class="navigation mdl-navigation mdl-color--white">
          <a class="mdl-navigation__link" href=""><i class="material-icons" role="presentation">home</i>Home</a>
          <a class="mdl-navigation__link" href=""><i class="material-icons" role="presentation">inbox</i>Inventory</a>
          <a class="mdl-navigation__link" href=""><i class="material-icons" role="presentation">shopping_cart</i>Create Sale</a>
          <a class="mdl-navigation__link" href=""><i class="material-icons" role="presentation">local_offer</i>Print Tags</a>
          <a class="mdl-navigation__link" href=""><i class="material-icons" role="presentation">account_balance</i>Statement</a>
          <a class="mdl-navigation__link" href=""><i class="material-icons" role="presentation">monetization_on</i>Reports</a>
          <a class="mdl-navigation__link" href=""><i class="material-icons" role="presentation">people</i>Administration</a>
        </nav>
      </div>

      <main class="mdl-layout__content mdl-color--grey-50 content">
        """),_display_(/*51.10*/content),format.raw/*51.17*/("""
      """),format.raw/*52.7*/("""</main>
    </div>
    <script src="https://code.getmdl.io/1.1.3/material.min.js"></script>
  </body>
</html>
"""))
      }
    }
  }

  def render(title:String,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(title)(content)

  def f:((String) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)

  def ref: this.type = this

}


}

/**/
object main extends main_Scope0.main
              /*
                  -- GENERATED --
                  DATE: Sun Jun 05 21:42:30 EDT 2016
                  SOURCE: C:/Users/Douglas/Documents/GitHub/ffff00/app/views/main.scala.html
                  HASH: 4feaf0bf2cf1efe726147f0fda9826a429098367
                  MATRIX: 748->1|873->31|903->35|1319->424|1334->430|1394->469|1816->864|1831->870|1893->911|2187->1178|2202->1184|2259->1220|4126->3060|4154->3067|4189->3075
                  LINES: 27->1|32->1|34->3|42->11|42->11|42->11|47->16|47->16|47->16|55->24|55->24|55->24|82->51|82->51|83->52
                  -- GENERATED --
              */
          