package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TakeScreenshot {

    /**
     *takes screenshots of current screen
     *names as per current time to ensure no duplication
     *of images are done
     * @param driver
     * @throws IOException
     */
    public void takeScreenshots(RemoteWebDriver driver) throws IOException {
        File screenShootFile = driver.getScreenshotAs(OutputType.FILE);
        driver.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        FileUtils.copyFile(screenShootFile, new File(
                "C:\\Test\\GithHub\\Screenshots\\Screen_" + date.getHours() +
                        date.getMinutes() + date.getSeconds() + ".png"));
    }
}
