package tumblr

class ArchiveController {

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

    return [tumblr: tumblrBlog, year: year, month: month, day: day]
  }

}
