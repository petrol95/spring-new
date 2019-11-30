package com.scraping.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.List;

public class WebScraper {

    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {

        final WebClient webClient = new WebClient();
        final HtmlPage startPage = webClient.getPage("http://en.wikipedia.org/wiki/Screen_Award_for_Best_Film");

        String source = "/html/body/div[3]/div[3]/div[4]/div/table[2]/tbody/tr[:?:]/td[2]/i/a/@href";

        String[] sourceArr = source.split(":");

        String title = "/html/body/div[3]/div[3]/div[4]/div/table[2]/tbody/tr[:?:]/td[2]/i/a/@title";
        String[] titleArr = title.split(":");

        String titleData = titleArr[0] + 2 + titleArr[2];
        String sourceData = sourceArr[0] + 2 + sourceArr[2];

        List<DomNode> titleNodes = startPage.getByXPath(titleData);
        List<DomNode> sourceNodes = startPage.getByXPath(sourceData);
        System.out.println(titleNodes);
        System.out.println(sourceNodes);

        System.out.println("Hum Aapke Hain Kaun: " + titleNodes.get(0).getNodeValue());
        System.out.println("/wiki/Hum_Aapke_Hain_Kaun: " + sourceNodes.get(0).getNodeValue());

        titleData = titleArr[0] + 3 + titleArr[2];
        sourceData = sourceArr[0] + 3 + sourceArr[2];
        titleNodes = startPage.getByXPath(titleData);
        System.out.println(titleNodes);

        sourceNodes = startPage.getByXPath(sourceData);
        System.out.println(sourceNodes);

        System.out.println("Dilwale Dulhaniya Le Jayenge: " + titleNodes.get(0).getNodeValue());
        System.out.println("/wiki/Dilwale_Dulhaniya_Le_Jayenge: " + sourceNodes.get(0).getNodeValue());


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
}
