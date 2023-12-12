package Core;

import net.bytebuddy.description.type.TypeDefinition;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import Core.BaseSingletonMap;

import java.util.Collection;

public class PageFactoryBase extends Driver {

    public PageFactoryBase(WebDriver driver)
    {
        this.driver = driver;
        initElements();
    }
        private void initElements ()
        {
            PageFactory.initElements(getDefaultDriver(), this);
        }



    }




