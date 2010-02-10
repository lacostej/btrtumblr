package tumblr

class ArchiveController extends BaseController {

  def defaultAction = "show"

  def show = {

    def blog = params['blog']

    ensureBlogIsSupported(blog, flash)

    def (year, month, day) = yearMonthDayIntParams(params)

    def tumblrBlog = apiReaderService.getTumblrBlog(blog)
    tumblrBlog.update()

    year = tumblrBlog.getBlogCurrentYear(year)
    month = tumblrBlog.getBlogCurrentMonth(month)

    return [tumblr: tumblrBlog, year: year, month: month, day: day]
  }

}
