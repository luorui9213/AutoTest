package pages;

import browser.Browser;
import org.openqa.selenium.WebElement;

public class BaiduPage {
    private Browser browser;

    public BaiduPage(Browser browser) {
        this.browser = browser;
    }

    public void baidu(String content){
        browser.openUrl("https://www.baidu.com");
        browser.sleep(1000);
        WebElement element = browser.waitElementByXPath("百度一下","//span[@class='soutu-btn']/following-sibling::input[1]");
        browser.enterText("百度一下输入框",element,content);
        WebElement button = browser.waitElementByXPath("百度一下","//input[@clas='bg s_btn']");
        browser.clickElement("百度一下按钮",button);
        browser.sleep(3000);
        browser.quit();
    }
}
