package com.example.wangzhaoyu.myguokr.core.net;

/**
 * @author wangzhaoyu
 */
public class NetUtils {

    public static String getArticleHtml(String content) {
        String style = "  <link rel=\"stylesheet\" href=\"file:///android_asset/static.guokr.com/apps/minisite/styles/f79e35f9.main.css\" /> \n" +
                "  <link rel=\"stylesheet\" href=\"file:///android_asset/static.guokr.com/apps/minisite/styles/e8ff5a9c.gbbcode.css\" /> \n" +
                "  <link rel=\"stylesheet\" href=\"file:///android_asset/static.guokr.com/apps/minisite/styles/e263077d.article.css\" /> \n";

        String prefix = "<html class=\"no-js screen-scroll\">\n" +
                " <head> \n" +
                "  <meta charset=\"UTF-8\" /> \n" +
                "  <meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0,maximum-scale=1,user-scalable=no\" /> \n" +
                "  <meta name=\"format-detection\" content=\"telephone=no\" /> \n" +
                style +
                " </head> \n" +
                " <body> \n" +
                "  <div class=\"container article-page\"> \n" +
                "   <div class=\"main\"> \n" +
                "    <div class=\"content\"> ";
        String suffix = "</div> \n" +
                "   </div> \n" +
                "  </div> \n" +
                " </body>\n" +
                "</html>";
        return prefix + content + suffix;
    }
}
