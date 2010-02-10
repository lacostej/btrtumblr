package tumblr

class SlideshowController extends BaseController {

  def defaultAction = "show"

  def show = {
    String blog = params['blog']

    ensureBlogIsSupported(blog, flash)

    def (year, month, day) = yearMonthDayIntParams(params)

    def tumblrBlog = apiReaderService.getTumblrBlog(blog)
    tumblrBlog.update()

    def photos = tumblrBlog.postsIn(year, month).collect {
      def post = it.slurp
      def pic = post.'photo-url'.findAll{ it2 -> it2.'@max-width'.text() == "1280" }
      def picUrl = pic[0].text()
      def thumb = post.'photo-url'.findAll{ it2 -> it2.'@max-width'.text() == "75" }
      def thumbUrl = thumb[0].text()
      def caption = post.'photo-caption'.text()
      caption = caption.replaceAll("&lt;", "<").replaceAll("&gt;", ">")

      if (caption.contains("</")) {
        caption = "" // FIXME implement proper HTML stripping / escaping ?
      }
      def hover = String.format("%1\$tb %1\$td", it.datetime) + "th" // FIXME st, nd, rd, th
      [url: picUrl, caption: caption, hover: hover, thumbnail: thumbUrl]
    }
    [photos: photos, year: year, month: month, day: day, maxWidth: 1024, maxHeight: 768]
  }
  
}
