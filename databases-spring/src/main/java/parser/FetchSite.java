package parser;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;


public class FetchSite {
    public String fetch(String input) throws IOException {
        //Document doc = Jsoup.connect(url).get();
        File in = new File(input);
        Document doc = Jsoup.parse(in, null);
        return doc.outerHtml();
    }
}
