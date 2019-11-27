package com.scraping.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;

public class WebScraper {

    public static void main(String[] args) {

        String searchQuery = "iphone 8s";
        String baseUrl = "https://newyork.craigslist.org/";

        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setThrowExceptionOnFailingStatusCode(false);

        try {
            String searchUrl = baseUrl + "search/sss?sort=rel&query=" + URLEncoder.encode(searchQuery, "UTF-8");

            HtmlPage page = client.getPage(searchUrl);

            List<HtmlElement> items = page.getByXPath("//li[@class='result-row']");

            if (items.isEmpty()) {
                System.out.println("No items found !");
            } else {
                for(HtmlElement htmlItem : items) {
                    HtmlAnchor itemAnchor = htmlItem.getFirstByXPath(".//p[@class='result-info']/a");
                    System.out.println(itemAnchor);
                    HtmlElement spanPrice = htmlItem.getFirstByXPath(".//a/span[@class='result-price']");
                    System.out.println(spanPrice);

                    // It is possible that an item doesn't have any price, we set the price to 0.0 in this case
                    String itemPrice = spanPrice == null ? "0.0" : spanPrice.asText();

                    Item item = new Item();
                    item.setTitle(itemAnchor.asText());
                    item.setUrl( baseUrl + itemAnchor.getHrefAttribute());

                    item.setPrice(new BigDecimal(itemPrice.replace("$", "")));

                    ObjectMapper mapper = new ObjectMapper();
                    String jsonString = mapper.writeValueAsString(item) ;

                    System.out.println(jsonString);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
