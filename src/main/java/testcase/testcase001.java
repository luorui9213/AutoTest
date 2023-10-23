package testcase;

import browser.Browser;
import org.openqa.selenium.WebElement;
import pages.BaiduPage;

public class testcase001 {
    private static final BaiduPage baiduPage = new BaiduPage(new Browser());
    public static void main(String[] args) {
        baiduPage.baidu("王者荣耀");
    }
}
