
// @GENERATOR:play-routes-compiler
<<<<<<< HEAD
// @SOURCE:/Users/sunho207/courses/cs2340/ffff00/conf/routes
// @DATE:Fri Jun 03 14:57:34 EDT 2016
=======
// @SOURCE:C:/Users/Douglas/Documents/GitHub/ffff00/conf/routes
// @DATE:Sun Jun 05 21:42:30 EDT 2016
>>>>>>> f9ffb3ff6c0aae7c2ba943720b927f92ad2696f2


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
