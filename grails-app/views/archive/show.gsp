<%
  def slurper = new XmlSlurper()
  def dfs = new java.text.DateFormatSymbols()
  def header = tumblr.getHeader()
  def domain = "http://${header.@cname}"
  def myDomain = "/${header.@name}"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	    <title>${header.@title}</title>
    	<link rel="stylesheet" type="text/css" href="${domain}/stylesheets/archive.css?4"/>
    	<script> </script>
    	<style type="text/css">
    	    #controls { background-color: #ddd; }
    	    h1 a { color: #999; }
    	    h2 { color: #666; }
    	    .month { color: #999; }
    	    .month a { color: #555; }
    	    .month a.active { color: #444; }
    	    .post_count { background-color: #999; color: #ddd; }
    	</style>
    </head>
    <body>
                    <div id="controls">
                <h1><a href="${myDomain}/archive/">${header.@title}</a></h1>

<%
  // FIXME recurse based on data between now and first post of the year
  [2010, 2009].each { yearTitle ->
%>
                    <div class="year_container">
                          <h2>${yearTitle}</h2>
                      <%
                        def monthsHtml = (0..11).collect { monthIdx ->
                          def nbPosts = tumblr.postsIn(yearTitle, monthIdx + 1).size()
                          def active = (yearTitle == year && month == (monthIdx + 1)) ? "class=\"active\"" : ""
                          if (nbPosts > 0) {
                            return """\
                                                                                      <div class="month">
                                                                                                      <a href="${myDomain}/archive/${year}/${monthIdx + 1}"
                                                              ${active}>${dfs.months[monthIdx]}</a>
                                                                                                              <span class="post_count">
                                                                      ${nbPosts}                                                </span>
                                                                                                                                          </div>
                      """
                          } else
                            return """\
                                                                                      <div class="month">
                                                                                                      ${dfs.months[monthIdx]}
                                                                            </div>
                      """
                          }
                          // 2 columns
                          [11..6, 5..0].each {
                      %>
                                                                          <div class="month_list">
                      <%
                             it.each{ it2 ->
                      %>
                      <%=monthsHtml[it2]%>
                      <%}%>
                                                                                  </div>
                      <%
                           }
                      %>
                      <div class="clear"></div>
                  </div>
<%}%>

                <div class="clear"></div>
            </div>

            <div id="post_bricks">
<%
   tumblr.postsIn(year, month).each {
     def post = it.slurp
     def thumb = post.'photo-url'.findAll{ it2 -> it2.'@max-width'.text() == "250" }
     def thumbUrl = thumb[0].text()
     //
     def caption = post.'photo-caption'.text()
     caption = caption.replaceAll("&lt;", "<").replaceAll("&gt;", ">")
     if (caption.contains("</a")) {
       caption = "" // FIXME implement proper HTML stripping / escaping ?
     }
     def hover = String.format("%1\$tb %1\$td", it.datetime) + "th" // FIXME st, nd, rd, th
%>
                                                    <a class="brick" href="${post.@url}"
                            style="background:url('${thumbUrl}') center no-repeat;">
                                <div class="inside_brick">
                                    <span class="caption">${caption}</span>
                                                    </div> <!-- inside_brick -->

                            <div class="user_hover">
                                ${hover}                            </div>
                        </a>
<%
   }
%>


                    <div class="clear"></div>
                            </div>
            </body>
</html>