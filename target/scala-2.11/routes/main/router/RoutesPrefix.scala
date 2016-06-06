
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Douglas/Documents/GitHub/ffff00/conf/routes
// @DATE:Sun Jun 05 21:42:30 EDT 2016


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
