package tumblr

/**
 * Created by IntelliJ IDEA.
 * @author jerome@coffeebreaks.org
 * @since  Feb 10, 2010 11:58:03 AM
 */
class TumblrBlog {
  def blog
  def MAX_FETCH = 50
  def slurper = new XmlSlurper()

  private def posts = []

  private def header
  private def calendar
  
  TumblrBlog(blog) {
    this.blog = blog
  }

  /**
   * we don't do any parameter verification.
   * make sure one reads http://www.tumblr.com/api
   */
  def readUrl(Map props = [:]) {
    def url = new SimpleURIBuilder("http://${this.blog}.tumblr.com/api/read")
    url.addParams(props)
    return url.toString()
  }

  def slurp(start = 0, num = 20, type = null) {
    def url = readUrl(start: start, num: num, type: type)
    println "URL: $url"
    def maxTries = 3
    def waitBetweenTriesInMillis = 1000
    while (maxTries-- > 0) {
      try {
        def slurped = slurper.parse(url)
        // quick quality check. It seems that sometimes we get garbage and the slurper doesn't notify us. Check the API
        new Integer(slurped.posts.@total.text())
        return slurped
      } catch (Exception e) {
        println "FAILED, trying again... " + e
//        e.printStackTrace()
        Thread.sleep(waitBetweenTriesInMillis)
      }
    }
    throw new IllegalStateException("Unable to fetch data from ${url}")
  }

  def dump_latest() {
    new File("posts_${this.blog}.xml").withOutputStream {
      os -> new URL(readUrl(start: 0, num: 0)).withInputStream { is -> os << is }
    }
  }


  // FIXME couldn't get @Lazy to work ??

  def getHeader() {
    if (header == null) {
      header = slurp(0, 0).tumblelog
    }
    header
  }

  def getCalendar() {
    if (calendar == null) {
      calendar = createBlogCalendar()
    }
    return calendar
  }

  private Calendar createBlogCalendar() {
    return Calendar.getInstance(TimeZone.getTimeZone(getHeader().@timezone.text()))
  }

  /* if no specified year is passed, return the current year in the blog's timezone */
  def getBlogCurrentYear(year) {
    if (year == null) {
      year = getCalendar().get(Calendar.YEAR)
    }
    return year
  }

  /* if no specified year is passed, return the current month in the blog's timezone */
  def getBlogCurrentMonth(month) {
    if (month == null) {
      month = getCalendar().get(Calendar.MONTH) + 1
    }
    return month
  }

  def postsIn(year, month) {
    year = getBlogCurrentYear(year)
    month = getBlogCurrentMonth(month)

    def cal = createBlogCalendar()
    cal.set(year, month-1, 1, 0, 0, 0)
    def monthStart = cal.getTime()
    cal.set(year, month, 1, 0, 0, 0)
    def monthEnd = cal.getTime()
    def collected = []
    posts.each { if (it.datetime >= monthStart  && it.datetime <= monthEnd) { collected << it } }
    return collected
  }

  def count() {
    def tumblr = slurp(0, 0)
    return new Integer(tumblr.posts.@total.text())
  }

  def getNew(alreadyFetchedCount, type = null) {
    def slurped = slurp(0, MAX_FETCH, type)
    int total = new Integer(slurped.posts.@total.text())
    def missing = total - alreadyFetchedCount
    def newPosts = []
    while (missing > 0) {
      def fetched = slurped.posts.post.size()
      if (fetched > 0) {
        newPosts += slurped.posts.post.collect{ new TumblrPost(it) }[0..(fetched-1)]
        missing -= fetched
      }
      def toRetain = Math.min(missing, MAX_FETCH)
      slurped = slurp(newPosts.size(), toRetain, type)
    }
    return newPosts
  }

  def update() {
    def count = count()
    def missing = count - posts.size()
    if (missing > 0) {
      println "Fetching missing ${missing} blog entries..."
      posts += getNew(posts.size())
    }
  }
}
