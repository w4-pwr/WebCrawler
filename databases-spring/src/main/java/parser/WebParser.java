package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class WebParser {
    /*
    public List<String> parser(String url) throws IOException {
        List<String> parse = null;
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("div.results dl a");
        for (Element link : links) {
            parse.add(link.absUrl("href"));
        }
        return parse;
    }*/
    // Nie jestem pewien, czy to jest dobrze, ale teoretycznie powinno zadziałać.
    // W razie czego mam kilka pomysłów co zmienić.
    // I zastanawiam się jak naprawić błąd - Method invocation 'add' may produce 'java.lang.NullPointerException'
    public List<String> parser(String html) {
        List<String> parse = null;
        Document doc = Jsoup.parse(html);
        Elements links = doc.select("a[href]");
        for (Element link : links) {
            if(link != null)
                parse.add(link.text());
        }
        return parse;
    }
}
