class UrlMappings {
  static mappings = {
    "/$blog/slideshow/$year?/$month?" {
      controller = "slideshow"
      action = "show"
    }
    
    "/$blog/archive/$year?/$month?/$day?" {
      controller = "archive"
      action = "show"
      constraints {
        // FIXME constraints don't work ??!
//        year(matches:/d{4}/)
//         month(matches:/d{2}/)
//         day(matches:/d{2}/)
      }
    }
    /* required for reloader to work in development mode */
    "/$controller/$action?/$id?"{
       constraints {
         // apply constraints here
       }
    }

    "/"(view:"/index")
    "/grails_info"(view:"/grails_info")

	  "500"(view:'/error')
	}
}
