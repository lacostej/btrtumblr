<html>
    <head>
        <title>Welcome to Better Tumblr</title>
		<meta name="layout" content="main" />
		<style type="text/css" media="screen">

			#nav {
				margin-top:20px;
				margin-left:30px;
				width:228px;
				float:left;

			}
			.homePagePanel * {
				margin:0px;
			}
			.homePagePanel .panelBody ul {
				list-style-type:none;
				margin-bottom:10px;
			}
			.homePagePanel .panelBody h1 {
				text-transform:uppercase;
				font-size:1.1em;
				margin-bottom:10px;
			}
			.homePagePanel .panelBody {
			    background: url(images/leftnav_midstretch.png) repeat-y top;
				margin:0px;
				padding:15px;
			}
			.homePagePanel .panelBtm {
			    background: url(images/leftnav_btm.png) no-repeat top;
				height:20px;
				margin:0px;
			}

			.homePagePanel .panelTop {
			    background: url(images/leftnav_top.png) no-repeat top;
				height:11px;
				margin:0px;
			}
			h2 {
				margin-top:15px;
				margin-bottom:15px;
				font-size:1.2em;
			}
			#pageBody {
				margin-left:280px;
				margin-right:20px;
			}
		</style>
    </head>
    <body>
		<div id="nav">
			<div class="homePagePanel">
				<div class="panelTop">

				</div>
				<div class="panelBody">
					<h1>Links</h1>
					<ul>
						<li><a href="http://github.com/lacostej/btrtumblr">Git Hub project</a></li>
            <li><a href="/grails_info">Grails Info</a></li>
					</ul>
				</div>
				<div class="panelBtm">
				</div>
			</div>


		</div>
		<div id="pageBody">
	        <h1>Welcome to Better Tumblr</h1>
	        <p>A small project to explore Tumblr APIs and add features to the limited but nice tumblr service...</p>

      <h2>News features</h2>
      <ul>
        <li>Slideshows implemented using this <a href="http://www.electricprism.com/aeron/slideshow/">slideshow javascript library</a></li>
        <!-- fixed URLs for -->
      </ul>

      <h2>Supported Blogs</h2>
      While we're testing the infrastructure, we currently limit the support to a small number of blogs:
      <ul>
      <g:each var="blog" in="${supportedBlogs}">
        <li><a href="/${blog.name}/archive/">${blog.name}</a> (${blog.comment})</li>
      </g:each>
      </ul>
      If you wish one particular blog to be added, <a href="mailto:contact.btrtumblr@gmail.com">contact us</a> or send us a patch ;).

		</div>
    </body>
</html>