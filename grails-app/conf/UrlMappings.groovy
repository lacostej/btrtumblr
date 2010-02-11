class UrlMappings {
  static mappings = {
    "/$blog/slideshow/$year?/$month?" {
      controller = "archive"
      action = "slideshow"
      constraints {
        year(matches:/\d{4}/)
        month(matches:/\d{1,2}/)
      }
    }
    
    "/$blog/archive/$year?/$month?/$day?" {
      controller = "archive"
      action = "show"
      constraints {
        year(matches:/\d{4}/)
        month(matches:/\d{1,2}/)
        day(matches:/\d{1,2}/)
      }
    }
    /* required for reloader to work in development mode */
    "/$controller/$action?/$id?"{
       constraints {
         // apply constraints here
       }
    }

    "/" {
      controller = "archive"
      action = "index"
    }
    "/grails_info"(view:"/grails_info")

	  "500"(view:'/error')
	}
}
