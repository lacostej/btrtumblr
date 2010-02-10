package tumblr

class SlideshowController {

  def apiReaderService

  def defaultAction = "show"

  def show = {
    def blog = params['blog']

    if (!blog.equals("bonjourmadame")) {
      flash['message'] = "Unsupported blog '${blog}'"
      render(view:"unsupported_blog")
    }

    def year = params['year']
    def month = params['month']
    def day = params['day']

    def tumblrBlog = apiReaderService.getTumblrBlog(blog)
    tumblrBlog.update()

    def cal = tumblrBlog.getCalendar()

    if (month == null) {
      month = cal.get(Calendar.MONTH) + 1
    } else {
      month = new Integer(month)
    }
    if (year == null) {
      year = cal.get(Calendar.YEAR)
    } else {
      year = new Integer(year)
    }

    def width = 500

    def photos = tumblrBlog.postsIn(year, month).collect {
      def post = it.slurp
      def pic = post.'photo-url'.findAll{ it2 -> it2.'@max-width'.text() == "${width}" }
      def picUrl = pic[0].text()
      def thumb = post.'photo-url'.findAll{ it2 -> it2.'@max-width'.text() == "75" }
      def thumbUrl = thumb[0].text()
      println "Found $picUrl"
      //
      def caption = post.'photo-caption'.text()
      caption = caption.replaceAll("&lt;", "<").replaceAll("&gt;", ">")
      if (caption.contains("</a")) {
        caption = "" // FIXME implement proper HTML stripping / escaping ?
      }
      def hover = String.format("%1\$tb %1\$td", it.datetime) + "th" // FIXME st, nd, rd, th
      [url: picUrl, caption: caption, hover: hover, thumbnail: thumbUrl]
    }
    println photos
    return [photos: photos, year: year, month: month, day: day, width: width]
  }

}
