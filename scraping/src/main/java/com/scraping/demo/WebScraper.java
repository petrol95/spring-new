package com.scraping.demo;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebScraper {

    public static void main(String[] args) throws FailingHttpStatusCodeException, IOException {

        getIndianFilms();

//        String searchQuery = "iphone 8s";
//        String baseUrl = "https://newyork.craigslist.org/";
//
//        WebClient client = new WebClient();
//        client.getOptions().setCssEnabled(false);
//        client.getOptions().setJavaScriptEnabled(false);
//        client.getOptions().setThrowExceptionOnFailingStatusCode(false);
//
//        try {
//            String searchUrl = baseUrl + "search/sss?sort=rel&query=" + URLEncoder.encode(searchQuery, "UTF-8");
//            HtmlPage page = client.getPage(searchUrl);
//
//            List<HtmlElement> items = page.getByXPath("//li[@class='result-row']");
//
//            if (items.isEmpty()) {
//                System.out.println("No items found !");
//            } else {
//                for(HtmlElement htmlItem : items) {
//                    HtmlAnchor itemAnchor = htmlItem.getFirstByXPath(".//p[@class='result-info']/a");
//                    HtmlElement spanPrice = htmlItem.getFirstByXPath(".//a/span[@class='result-price']");
//
//                    // It is possible that an item doesn't have any price, we set the price to 0.0 in this case
//                    String itemPrice = spanPrice == null ? "0.0" : spanPrice.asText();
//
//                    Item item = new Item();
//                    item.setTitle(itemAnchor.asText());
//                    item.setUrl( baseUrl + itemAnchor.getHrefAttribute());
//
//                    item.setPrice(new BigDecimal(itemPrice.replace("$", "")));
//
//                    ObjectMapper mapper = new ObjectMapper();
//                    String jsonString = mapper.writeValueAsString(item) ;
//
//                    System.out.println(jsonString);
//                }
//            }
//        } catch(Exception e) {
//            e.printStackTrace();
//        }

    }

    private static void getIndianFilms() throws IOException {
        final WebClient webClient = new WebClient();
        final HtmlPage startPage = webClient.getPage("http://en.wikipedia.org/wiki/Screen_Award_for_Best_Film");
        final int number = 10;

        // XPath:/html/body/div[3]/div[3]/div[4]/div/table[2]/tbody/tr[2]/td[2]/i/a
        String source = "/html/body/div[3]/div[3]/div[4]/div/table[2]/tbody/tr[:?:]/td[2]/i/a/@href";
        String[] sourceArr = source.split(":");
        String title = "/html/body/div[3]/div[3]/div[4]/div/table[2]/tbody/tr[:?:]/td[2]/i/a/@title";
        String[] titleArr = title.split(":");

        List<DomNode> titleNodes = new ArrayList<>();
        List<DomNode> sourceNodes= new ArrayList<>();

        for (int i = 2; i <= number + 1; i++) {
            String titleData = titleArr[0] + i + titleArr[2];
            String sourceData = sourceArr[0] + i + sourceArr[2];
            titleNodes.addAll(startPage.getByXPath(titleData));
            sourceNodes.addAll(startPage.getByXPath(sourceData));
        }

        for (int i = 0; i < number; i++) {
            System.out.println(titleNodes.get(i).getNodeValue() + ": " + sourceNodes.get(i).getNodeValue());
        }
    }
}
