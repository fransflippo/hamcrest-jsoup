package nl.fd.hamcrest.jsoup;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Matches a given element, if it contains at least one child selectable
 * by given CSS selector.
 *
 * Created by mohamnag on 25/04/16.
 */
public class ElementWithChild extends TypeSafeDiagnosingMatcher<Element> {

    private String cssSelector;

    private ElementWithChild(String cssSelector) {
        this.cssSelector = cssSelector;
    }

    @Override
    protected boolean matchesSafely(Element parent, Description description) {
        Elements elements = parent.select(cssSelector);
        Element element = elements.first();
        if (element == null) {
            description
                    .appendText("expected element to have at least one child matching selector ")
                    .appendValue(cssSelector)
                    .appendText(" but nothing found");

            return false;
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(" has child selected by ").appendValue(cssSelector);
    }

    @Factory
    public static Matcher<Element> hasChild(String cssSelector) {
        return new ElementWithChild(cssSelector);
    }
}
