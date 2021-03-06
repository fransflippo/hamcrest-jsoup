package nl.fd.hamcrest.jsoup;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.jsoup.nodes.Element;

/**
 * Matcher for a Jsoup element with a certain attribute.
 * @author Frans Flippo
*/
public class ElementWithAttribute extends TypeSafeDiagnosingMatcher<Element> {
    private final String attributeName;
    private final Matcher<? super String> attributeValueMatcher;

    private ElementWithAttribute(String attributeName, Matcher<? super String> attributeValueMatcher) {
        this.attributeName = attributeName;
        this.attributeValueMatcher = attributeValueMatcher;
    }

    @Override
    protected boolean matchesSafely(Element item, Description mismatchDescription) {
        if (item.hasAttr(attributeName)) {
            String value = item.attr(attributeName);
            if (!attributeValueMatcher.matches(value)) {
                mismatchDescription.appendText("expected attribute with name ").appendValue(attributeName)
                        .appendText(" and with value matching ").appendDescriptionOf(attributeValueMatcher)
                        .appendText(" but value was ").appendValue(value);
                return false;
            }
        } else {
            mismatchDescription.appendText("expected attribute with name ").appendValue(attributeName)
                    .appendText(" but there were none ");
            return false;
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(attributeName).appendText(" ").appendDescriptionOf(attributeValueMatcher);
    }

    /**
     * Creates a {@link org.hamcrest.Matcher} for a JSoup {@link org.jsoup.nodes.Element} with the given {@code expectedValue} for the given {@code
     * attributeName}. See {@link #hasAttribute(String, org.hamcrest.Matcher)} for use with Matchers.
     *
     * @param attributeName The attribute name whose value to check
     * @param expectedValue The attribute value that is expected
     * @return a {@link org.hamcrest.Matcher} for a JSoup {@link org.jsoup.nodes.Element} with the given {@code expectedValue} for the given {@code
     * attributeName}
     */
    public static Matcher<Element> hasAttribute(final String attributeName,
                                                final String expectedValue) {
        return new ElementWithAttribute(attributeName, Matchers.is(expectedValue));
    }

    /**
     * Creates a {@link org.hamcrest.Matcher} for a JSoup {@link org.jsoup.nodes.Element} with the given {@code expectedValue} for the given {@code
     * attributeName}.
     *
     * @param attributeName         The attribute name whose value to check
     * @param attributeValueMatcher A matcher for the attribute value
     * @return a {@link org.hamcrest.Matcher} for a JSoup {@link org.jsoup.nodes.Element} with the given {@code expectedValue} for the given {@code
     * attributeName}
     */
    public static Matcher<Element> hasAttribute(final String attributeName, final Matcher<? super String> attributeValueMatcher) {
        return new ElementWithAttribute(attributeName, attributeValueMatcher);
    }

    /**
     * Creates a {@link org.hamcrest.Matcher} for a JSoup {@link org.jsoup.nodes.Element} with the given {@code expectedValue} for the "href"
     * attribute. See {@link #hasAttribute(String, org.hamcrest.Matcher)} for use with Matchers.
     *
     * @param expectedValue The attribute value that is expected
     * @return a {@link org.hamcrest.Matcher} for a JSoup {@link org.jsoup.nodes.Element} with the given {@code expectedValue} for the "href"
     * @deprecated Use {@link #hasAttribute instead}
     */
    public static Matcher<Element> hasHref(final String expectedValue) {
        return hasAttribute("href", Matchers.is(expectedValue));
    }

    /**
     * Creates a {@link org.hamcrest.Matcher} for a JSoup {@link org.jsoup.nodes.Element} with the given {@code expectedValue} for the "href"
     * attribute. See {@link #hasAttribute(String, org.hamcrest.Matcher)} for use with Matchers.
     *
     * @param attributeValueMatcher A matcher for the attribute value
     * @return a {@link org.hamcrest.Matcher} for a JSoup {@link org.jsoup.nodes.Element} with the given {@code expectedValue} for the "href"
     * @deprecated Use {@link #hasAttribute instead}
     */
    public static Matcher<Element> hasHref(final Matcher<? super String> attributeValueMatcher) {
        return hasAttribute("href", attributeValueMatcher);
    }

}
