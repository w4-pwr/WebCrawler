package parser;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;


@Component
public class FetchSite {
    public String fetch(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc.outerHtml();
    }
}
