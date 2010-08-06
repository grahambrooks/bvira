package bvira.web

import org.junit.runner.RunWith
import bvira.test.{WebEnvironment, AcceptanceTestRunner}
import bvira.test.abstraction.{HomePage, To}
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

@RunWith(classOf[AcceptanceTestRunner])
class HomePageSpec extends FlatSpec with ShouldMatchers {
  "Home page" should "have an appropriate title" in {
    val page = WebEnvironment.getInstance().navigate(To.homePage()).responsePage(classOf[HomePage])

    page.getTitle should be === "Home - bvira"
  }

  //  it("should navigate to the login page when the homepage login link is clicked")(pending)
}
