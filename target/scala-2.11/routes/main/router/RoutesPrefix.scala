
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/sunho207/courses/cs2340/ffff00/conf/routes
// @DATE:Sun Jun 05 22:54:33 EDT 2016


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
