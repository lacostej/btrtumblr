package tumblr

import grails.test.*

class SimpleURIBuilderTest extends GrailsUnitTestCase {
  def uri

  protected void setUp() {
      super.setUp()
      uri = new SimpleURIBuilder("http://www.example.com")
  }

  protected void tearDown() {
      super.tearDown()
  }

  def testURIBuilderSimple() {
    assertEquals "http://www.example.com", uri.toString()
  }

  def testURIBuilderOneOrMoreParams() {
    uri.addParam("a", "b")
    assertEquals "http://www.example.com?a=b", uri.toString()
  }

  def testURIBuilderNullValuesArentAdded() {
    uri.addParam("a", null)
    assertEquals "http://www.example.com", uri.toString()
  }

  def testURIBuilderParamMaps() {
    uri.addParams("a": "b", "c":"d", "e": "f")

    // we can't assure the parameter order when using the map
    assertTrue uri.toString().startsWith("http://www.example.com?")
    assert uri.toString().contains("a=b")
    assert uri.toString().contains("c=d")
    assert uri.toString().contains("e=f")
  }
}
