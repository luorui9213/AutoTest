package browser;

import common.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class Browser {
    Actions actions = null;
    WebDriver driver = null;
    Log logger = new Log();

    static {
        System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver.exe");
    }

    public Browser(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }
    public Browser(){
        try {
            this.driver = new ChromeDriver();
            this.actions = new Actions(this.driver);
            logger.printLog("创建浏览器成功");
        }catch (Exception exception){
            logger.printLog("创建浏览器失败");
        }
    }
    //打开浏览器
    public void openUrl(String url){
        try {
            this.driver.get(url);
            this.driver.manage().window().maximize();
            logger.printLog("打开网址成功");
        }catch (Exception exception){
            exception.printStackTrace();
            logger.printLog("打开网址失败");
        }
    }
    //关闭浏览器
    public void quit(){
        try {
            this.driver.quit();
            logger.printLog("浏览器已关闭");
        }catch (Exception exception){
            exception.printStackTrace();
            logger.printLog("关闭浏览器失败");
        }
    }

    public void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //元素定位
    public WebElement waitElementById(String desc, String id, int timeout){
        //结束时间，在该时间内查找不到元素，超时
        long endTime = System.currentTimeMillis() + (long) timeout;
        try {
            WebElement element = null;
            while (element == null && System.currentTimeMillis() < endTime){
                element = this.driver.findElement(By.id(id));
            }
            if (element == null){
                logger.printLog("未找到元素：" + desc + "id: " + id);
            }else {
                logger.printLog("找到元素：" + desc + "id: " + id);
            }
            return element;
        }catch (Exception exception){
            logger.printLog("等待元素报错：" + exception.getMessage());
            logger.writeLog("等待元素报错：" + exception.getMessage());
        }
        return null;
    }

    public WebElement waitElementById(String desc, String id){
        return waitElementById(desc,id,60000);
    }

    public WebElement waitElementByName(String desc, String name, int timeout){
        //结束时间，在该时间内查找不到元素，超时
        long endTime = System.currentTimeMillis() + (long) timeout;
        try {
            WebElement element = null;
            while (element == null && System.currentTimeMillis() < endTime){
                element = this.driver.findElement(By.name(name));
            }
            if (element == null){
                logger.printLog("未找到元素：" + desc + "id: " + name);
            }else {
                logger.printLog("找到元素：" + desc + "id: " + name);
            }
            return element;
        }catch (Throwable throwable){
            logger.printLog("等待元素报错：" + throwable.getMessage());
            logger.writeLog("等待元素报错：" + throwable.getMessage());
        }
        return null;
    }

    public WebElement waitElementByName(String desc, String name){
        return waitElementByName(desc,name,60000);
    }

    public WebElement waitElementByClass(String desc, String className, int timeout){
        //结束时间，在该时间内查找不到元素，超时
        long endTime = System.currentTimeMillis() + (long) timeout;
        try {
            WebElement element = null;
            while (element == null && System.currentTimeMillis() < endTime){
                element = this.driver.findElement(By.className(className));
            }
            if (element == null){
                logger.printLog("未找到元素：" + desc + "class: " + className);
            }else {
                logger.printLog("找到元素：" + desc + "class: " + className);
            }
            return element;
        }catch (Throwable throwable){
            logger.printLog("等待元素报错：" + throwable.getMessage());
            logger.writeLog("等待元素报错：" + throwable.getMessage());
        }
        return null;
    }

    public WebElement waitElementByClass(String desc, String className){
        return waitElementByClass(desc,className,60000);
    }

    public WebElement waitElementByXPath(String desc, String xpath, int timeout){
        //结束时间，在该时间内查找不到元素，超时
        long endTime = System.currentTimeMillis() + (long) timeout;
        try {
            WebElement element = null;
            while (element == null && System.currentTimeMillis() < endTime){
                element = this.driver.findElement(By.xpath(xpath));
            }
            if (element == null){
                logger.printLog("未找到元素：" + desc + "xpath: " + xpath);
            }else {
                logger.printLog("找到元素：" + desc + "xpath: " + xpath);
            }
            return element;
        }catch (Throwable throwable){
            logger.printLog("等待元素报错：" + throwable.getMessage());
            logger.writeLog("等待元素报错：" + throwable.getMessage());
        }
        return null;
    }

    public WebElement waitElementByXPath(String desc, String xpath){
        return waitElementByXPath(desc,xpath,60000);
    }

    //输入文本
    public void enterText(String desc,WebElement element,String content){
        if (element == null){
            logger.printLog("元素：" + desc + "不存在，输入文本失败");
            return;
        }
        int retryTimes = 0;
        while (retryTimes < 10){
            try {
                element.click();
                element.sendKeys(Keys.CONTROL + "A");
                element.sendKeys(Keys.DELETE);
                element.sendKeys(content);
                logger.printLog("元素：" + desc + "输入完成：" + content);
                return;
            }catch (Exception exception){
                logger.printLog("元素：" + desc + "输入失败");
                logger.printLog(exception.getMessage());
                logger.writeLog("元素：" + desc + "输入失败");
                logger.writeLog(exception.getMessage());
                retryTimes++;
            }
        }
    }
    //单击
    public void clickElement(String desc, WebElement element) {
        if (element == null){
            logger.printLog("元素" + desc + "不存在，单击失败");
            return;
        }
        int retryTimes = 0;
        while (retryTimes < 5){
            try {
                element.click();
                Thread.sleep(500);
                logger.printLog("元素：" + desc + "单击成功");
                return;
            }catch (Exception exception){
                logger.printLog("元素：" + desc + "单击失败");
                logger.writeLog("元素：" + desc + "单击失败");
                retryTimes++;
            }
        }
    }

}
