package tumblr

/**
 * doesn't support encoding, doesn't support default parameters in request, etc..
 * doesn't support anything but simple parameters....
 *
 * consider using groovyx.net.http.URIBuilder instead
 * @author jerome@coffeebreaks.org
 * @since  Feb 10, 2010 10:17:29 PM
 */
class SimpleURIBuilder {
  StringBuilder base
  int nbQueryParams = 0
  SimpleURIBuilder(String uri) {
    this.base = new StringBuilder(uri)
  }
  SimpleURIBuilder addParams(Map params) {
    params.each { p, v ->
      this.addParam(p, v)
    }
    this
  }
  SimpleURIBuilder addParam(name, value) {
    if (value != null) {
      base.append(nbQueryParams++ == 0 ? "?" : "&" ).append("${name}=${value}")
    }
    this
  }
  String toString() {
    return base.toString()
  }
}

