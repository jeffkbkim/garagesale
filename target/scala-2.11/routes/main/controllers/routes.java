
// @GENERATOR:play-routes-compiler
<<<<<<< HEAD
// @SOURCE:/Users/sunho207/courses/cs2340/ffff00/conf/routes
// @DATE:Fri Jun 03 14:57:34 EDT 2016
=======
// @SOURCE:C:/Users/Douglas/Documents/GitHub/ffff00/conf/routes
// @DATE:Sun Jun 05 21:42:30 EDT 2016
>>>>>>> f9ffb3ff6c0aae7c2ba943720b927f92ad2696f2

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseApplication Application = new controllers.ReverseApplication(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseApplication Application = new controllers.javascript.ReverseApplication(RoutesPrefix.byNamePrefix());
  }

}
