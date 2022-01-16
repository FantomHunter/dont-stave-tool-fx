package org.codehunter.dontstave.cheatfx.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public final class JsoupUtil {

    public static String parsePageHeaderInfo(String urlStr) throws Exception {

        StringBuilder sb = new StringBuilder();
        Connection con = Jsoup.connect(urlStr);

        /* this browseragant thing is important to trick servers into sending us the LARGEST versions of the images */
//        con.userAgent(Constants.BROWSER_USER_AGENT);
        Document doc = con.get();

        String text;
        Elements metaOgTitle = doc.select("meta[property=og:title]");
        text = metaOgTitle.attr("content");

        String imageUrl;
        Elements metaOgImage = doc.select("meta[property=og:image]");
        imageUrl = metaOgImage.attr("content");

        sb.append("<img src='");
        sb.append(imageUrl);
        sb.append("' align='left' hspace='12' vspace='12' width='150px'>");

        sb.append(text);

        return sb.toString();
    }

    public static String getOgImageUrl(String url) {
        StringBuilder sb = new StringBuilder();
        Connection con = Jsoup.connect(url);
        /* this browseragant thing is important to trick servers into sending us the LARGEST versions of the images */
//        con.userAgent(Constants.BROWSER_USER_AGENT);
        try {
            Document doc = con.get();
            String imageUrl;
            Elements metaOgImage = doc.select("figure.pi-item.pi-image > a > img");
            imageUrl = metaOgImage.attr("src");
            System.out.println("imageUrl: " + imageUrl);
            return imageUrl;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
