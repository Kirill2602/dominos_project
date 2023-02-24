package ru.dominospizza.drivers;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

import static ru.dominospizza.drivers.BrowserstackDriver.browserstackDriver;

public class BrowserstackHelper {
    public static void scroll(int startx, int starty, int endx, int endy) {

        TouchAction touchAction = new TouchAction((PerformsTouchActions) browserstackDriver);

        touchAction.longPress(PointOption.point(startx, starty))
                .moveTo(PointOption.point(endx, endy))
                .release()
                .perform();
    }

    public static void browserstackScrollDown() {
        //The viewing size of the device
        Dimension size = browserstackDriver.manage().window().getSize();

        //Starting y location set to 80% of the height (near bottom)
        int starty = (int) (size.height * 0.80);
        //Ending y location set to 20% of the height (near top)
        int endy = (int) (size.height * 0.20);
        //x position set to mid-screen horizontally
        int startx = (int) size.width / 2;

        scroll(startx, starty, startx, endy);
    }
}
