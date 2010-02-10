package tumblr

class SlideshowController {

  def apiReaderService

  def defaultAction = "show"

  def show = {
    String blog = params['blog']

    if (!blog.equals("bonjourmadame")) {
      flash['message'] = "Unsupported blog '${blog}'"
      render(view:"unsupported_blog")
    }

    Integer year = toInt(params['year'])
    Integer month = toInt(params['month'])
    Integer day = toInt(params['day'])

    def tumblrBlog = apiReaderService.getTumblrBlog(blog)
    tumblrBlog.update()

    def cal = tumblrBlog.getCalendar()

    if (month == null) {
      month = cal.get(Calendar.MONTH) + 1
    }
    if (year == null) {
      year = cal.get(Calendar.YEAR)
    }

    def photos = tumblrBlog.postsIn(year, month).collect {
      def post = it.slurp
      def pic = post.'photo-url'.findAll{ it2 -> it2.'@max-width'.text() == "1280" }
      def picUrl = pic[0].text()
      def thumb = post.'photo-url'.findAll{ it2 -> it2.'@max-width'.text() == "75" }
      def thumbUrl = thumb[0].text()
      def caption = post.'photo-caption'.text()
      caption = caption.replaceAll("&lt;", "<").replaceAll("&gt;", ">")
      if (caption.contains("</a")) {
        caption = "" // FIXME implement proper HTML stripping / escaping ?
      }
      def hover = String.format("%1\$tb %1\$td", it.datetime) + "th" // FIXME st, nd, rd, th
      [url: picUrl, caption: caption, hover: hover, thumbnail: thumbUrl]
    }
    [photos: photos, year: year, month: month, day: day, maxWidth: 1024, maxHeight: 768]
  }

  Integer toInt(String param) {
    if (param == null)
      return null
    try {
      new Integer(param)
    } catch (NumberFormatException e) {
      println "ERROR : " + e.getMessage()
      return null
    }
  }

}
