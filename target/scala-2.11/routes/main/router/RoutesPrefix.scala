
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/yudawinata/Documents/School/Subjects/CS2340/ffff00/conf/routes
// @DATE:Mon Jun 06 19:32:29 EDT 2016


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
