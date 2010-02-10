package tumblr

/**
 * Created by IntelliJ IDEA.
 * @author jerome@coffeebreaks.org
 * @since  Feb 10, 2010 7:33:17 PM
 */
abstract class BaseController {
  def apiReaderService

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

  def getTumblrBlog(blog) {
    def tumblrBlog = apiReaderService.getTumblrBlog(blog)
    tumblrBlog.update()
    return tumblrBlog
  }

  def yearMonthDayIntParams(params) {
    Integer year = toInt(params['year'])
    Integer month = toInt(params['month'])
    Integer day = toInt(params['day'])
    return [year, month, day]
  }

  protected def ensureBlogIsSupported(String blog, Map flash) {
    if(!blog.equals("bonjourmadame")){
      flash['message'] = "Unsupported blog '${blog}'"
      render(view: "unsupported_blog")
    }
  }
}
