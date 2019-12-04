package com.scraping.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpHeader;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class WebScraper {

    public static void main(String[] args) throws Exception {

        // scraping Indian films from wikipedia
//        getIndianFilms();

        // scraping info from web-site
        getItemInfo();

        }

    private static void getItemInfo() throws IOException {

        WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        HtmlPage page = webClient.getPage("https://www.eldorado.ru/search/catalog.php?q=iphone%20xs");
        System.out.println(page.asXml());

        HtmlInput inputZona = (HtmlInput) page.getElementById("search_price_min");
        System.out.println(inputZona.asXml());

//        String searchQuery = "iphone xs";
//        String baseUrl = "https://www.eldorado.ru/";
//
//        WebClient client = new WebClient();
//        client.getOptions().setCssEnabled(false);
//        client.getOptions().setJavaScriptEnabled(false);
//        client.addRequestHeader(HttpHeader.ACCEPT_ENCODING, "windows-1251");
//        client.addRequestHeader(HttpHeader.ACCEPT_LANGUAGE, "ru");
//        client.getOptions().setThrowExceptionOnFailingStatusCode(false);
//        client.getOptions().setThrowExceptionOnScriptError(false);
//        client.getOptions().setRedirectEnabled(true);
//
//        // https://www.eldorado.ru/search/catalog.php?q=iphone%20xs
//        String searchUrl = baseUrl + "search/catalog.php?q=" + URLEncoder.encode(searchQuery, "UTF-8");
//        System.out.println(searchUrl);
//        HtmlPage page = client.getPage(searchUrl);
//
//        List<HtmlElement> items = page.getByXPath("//div[@class='item']");
//
//        System.out.println("==========================");
//        System.out.println(items);
//
//        if (items.isEmpty()) {
//            System.out.println("No items found !");
//        } else {
//            for (HtmlElement htmlItem : items) {
//                HtmlAnchor itemAnchor = htmlItem.getFirstByXPath(".//div[@class='itemTitle']/a");
//                HtmlElement spanPrice = htmlItem.getFirstByXPath(".//div/span[@class='discountPrice.itemPrice']");
//
//                // It is possible that an item doesn't have any price, we set the price to 0.0 in this case
//                String itemPrice = spanPrice == null ? "0.0" : spanPrice.asText();
//
//                Item item = new Item();
//                item.setTitle(itemAnchor.asText());
//                item.setUrl(baseUrl + itemAnchor.getHrefAttribute());
//
//                item.setPrice(new BigDecimal(itemPrice.replace("$", "")));
//
//                ObjectMapper mapper = new ObjectMapper();
//                String jsonString = mapper.writeValueAsString(item);
//
//                System.out.println(jsonString);
//            }
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
        List<DomNode> sourceNodes = new ArrayList<>();

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
