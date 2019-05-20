package com.hdy.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
public class Myprocessor implements PageProcessor {

    public void process(Page page) {
       System.out.print("-----"+page.getHtml().xpath("//*[@id='wp']/div[2]/div[3]/text()").toString());
       String line;
       line = new String(page.getHtml().xpath("//*[@id='wp']/div[2]/div[3]/text()").toString());
       System.out.print(""+line);

    }

    public Site getSite() {
        return Site.me().setSleepTime(1000).setRetrySleepTime(5);
    }

    public static void main(String[] args) {
        Spider.create(new Myprocessor()).addUrl("https://www.txtwu.org/files/article/html/3/3445/379679.html").run();
    }
}
