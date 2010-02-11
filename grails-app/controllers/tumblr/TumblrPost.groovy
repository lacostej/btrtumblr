package tumblr

/**
 * Created by IntelliJ IDEA.
 * @author jerome@coffeebreaks.org
 * @since  Feb 10, 2010 11:57:37 AM
 */
class TumblrPost {
  def cal = new GregorianCalendar()
  def id
  def type
  def datetime
  def slurp
  def xml

  TumblrPost(gpathResult) {
    this.id = gpathResult.@id.text()
    this.type = gpathResult.@type.text()
    this.datetime = toDate(gpathResult.'@unix-timestamp'.text())
    this.slurp = gpathResult

    // slurper.parseText(it.xml) // FIXME find a way to dump the XML
    def writer = new StringWriter()
    gpathResult.writeTo(writer)
    this.xml = writer.toString()
  }

   def toDate(seconds) {
      cal.setTimeInMillis(new Long(seconds + "000"))
      return cal.getTime()
   }

   String toString() {
     return "post: [$id, $datetime]"
   }
}
