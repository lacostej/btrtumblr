class UrlMappings {
    static mappings = {
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
