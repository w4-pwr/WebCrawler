package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class WebParser {
    public List<String> parser(String html) {
        List<String> parse = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements links = doc.select("a");
        for (Element link : links) {
            String str = link.attr("abs:href");
            if(str != "")
                parse.add(str);
        }
        return parse;
    }
}
