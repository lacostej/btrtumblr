package tumblr

class ApiReaderService {
  private def tumblrCache = [:]

    boolean transactional = true

    TumblrBlog getTumblrBlog(blogName) {
      getTumblr(blogName)
    }

    private TumblrBlog getTumblr(blogName) {
      def result = null
      if (!tumblrCache.containsKey(blogName)) {
        result = new TumblrBlog(blogName)
        tumblrCache[blogName] = result
      } else {
        result = tumblrCache[blogName]
      }
      (TumblrBlog) result
    }
}
