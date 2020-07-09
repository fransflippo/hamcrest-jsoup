# hamcrest-jsoup

[![All Contributors](https://img.shields.io/badge/all_contributors-4-orange.svg?style=flat-square)](#contributors)


The Hamcrest JSoup library provides a set of matchers for JSoup elements to assert the contents of those objects. With this library you can easily unit test HTML contents in your backend.   

## How to use hamcrest-jsoup in your project:

### Maven

Add it to your `pom.xml` dependencies:
```
<dependency>
    <groupId>com.assisisolutions</groupId>
    <artifactId>hamcrest-jsoup</artifactId>
    <version>1.0</version>
</dependency>
```

### Gradle

Add it to your `build.gradle` dependencies:
```
    implementation 'com.assisisolutions:hamcrest-jsoup:1.0'
```

### Example Usage

A code sample to give you a rough idea of how easy it is to use this library:

```
    @Test
    public void testMyLoginForm() {
        String html = "<html><body> <div class=\"content container\"><h1>Login form</h1> "
                + "<form role=\"form\" method=\"POST\" action=\"/users/login\"> "
                + "<div class=\"form-group\">"
                + "<label for=\"username\">Username</label>"
                + "<input type=\"text\" id=\"username\" name=\"username\" />"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label for=\"password\">Password</label>"
                + "<input type=\"password\" id=\"password\" name=\"password\" />"
                + "</div>"
                + "</form>"
                + "</div>"
                + "</body>"
                + "</html>";
                
        Document document = Jsoup.parse(html);
        
        Elements formElements = document.select("div.content > form");
        assertThat(formElements.get(0), hasAttribute("action", "/users/login"));
        assertThat(document, selecting("div.form-group", contains(
            allOf(
                selectingFirst("label", hasText("Username")),
        	selectingFirst("input", hasAttribute("name", "username"))
            ),
            allOf(
                selectingFirst("label", hasText("Password")),
        	selectingFirst("input", allOf(
                    hasAttribute("name", "password")),
	            hasAttribute("type", "password"))
                )
            )
        );
    }
```

When combined with Spring's [MVC Test Framework](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/testing.html#spring-mvc-test-framework), Hamcrest-JSoup is a powerful way to test the correctness of your web application's HTML output, regardless of the underlying view technology:
```
    @Test
    public void testMyLoginForm() {
        MvcResult result = mockMvc.perform(get("/users/login")).andReturn();
        assertThat(result.getResponse().getStatus(), is(200));
	
        String content = result.getResponse().getContentAsString();
        Document document = Jsoup.parse(content);
	
        Elements formElements = document.select("div.content > form");
        assertThat(formElements.get(0), hasAttribute("action", "/users/login"));
        assertThat(document, selecting("div.form-group", contains(
            allOf(
                selectingFirst("label", hasText("Username")),
        	selectingFirst("input", hasAttribute("name", "username"))
            ),
            allOf(
                selectingFirst("label", hasText("Password")),
        	selectingFirst("input", allOf(
                    hasAttribute("name", "password")),
	            hasAttribute("type", "password"))
                )
            )
        );
    }
```

### Matchers

The following matchers are provided: 
- **ElementWithAttribute** Matcher with a certain attribute having a specific value
  - hasAttribute
  - hasHref
- **ElementWithClass** Matcher with a certain css class 
  - hasClass 
- **ElementWithData** Matcher having the desired matcher 
  - hasData
- **ElementWithInnerHtml** Matcher containing (inner) html 
  - hasHtml
  - hasInnerHtml
- **ElementWithName** Matcher having name 
  - hasName 
- **ElementWithOwnText** Matcher having text  
  - hasOwnText 
- **ElementWithText** Matcher having text matcher 
  - hasText
- **ElementWithChild** Matcher having at least one child with given CSS selector.
  - hasChild
- **ElementWithUniqueChild** Matcher having exactly one child with given CSS selector.
  - hasUniqueChild
- **Selecting** Matcher that has a list of child nodes matching the specified cssExpression  
- **SelectingFirst** Matcher selecting the first element child nodes matching the specified cssExpression


## Contributors ✨

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore -->
<table>
  <tr>
    <td align="center"><a href="https://github.com/JHannema"><img src="https://avatars2.githubusercontent.com/u/5299964?v=4" width="100px;" alt="Joram Hannema"/><br /><sub><b>Joram Hannema</b></sub></a><br /><a href="https://github.com/FDMediagroep/hamcrest-jsoup/commits?author=JHannema" title="Code">💻</a> <a href="https://github.com/FDMediagroep/hamcrest-jsoup/commits?author=JHannema" title="Documentation">📖</a> <a href="#maintenance-JHannema" title="Maintenance">🚧</a></td>
    <td align="center"><a href="https://github.com/jmsnoeij"><img src="https://avatars3.githubusercontent.com/u/3830004?v=4" width="100px;" alt="Jeroen M Snoeij"/><br /><sub><b>Jeroen M Snoeij</b></sub></a><br /><a href="https://github.com/FDMediagroep/hamcrest-jsoup/commits?author=jmsnoeij" title="Code">💻</a> <a href="https://github.com/FDMediagroep/hamcrest-jsoup/commits?author=jmsnoeij" title="Documentation">📖</a> <a href="#maintenance-jmsnoeij" title="Maintenance">🚧</a></td>
    <td align="center"><a href="https://github.com/Krijger"><img src="https://avatars1.githubusercontent.com/u/3886732?v=4" width="100px;" alt="Quinten Krijger"/><br /><sub><b>Quinten Krijger</b></sub></a><br /><a href="https://github.com/FDMediagroep/hamcrest-jsoup/commits?author=Krijger" title="Code">💻</a> <a href="https://github.com/FDMediagroep/hamcrest-jsoup/commits?author=Krijger" title="Documentation">📖</a></td>
    <td align="center"><a href="http://naghavi.me"><img src="https://avatars1.githubusercontent.com/u/4481421?v=4" width="100px;" alt="Mohammad Naghavi"/><br /><sub><b>Mohammad Naghavi</b></sub></a><br /><a href="https://github.com/FDMediagroep/hamcrest-jsoup/commits?author=mohamnag" title="Code">💻</a></td>
  </tr>
</table>

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!
