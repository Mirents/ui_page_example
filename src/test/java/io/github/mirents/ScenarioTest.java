package io.github.mirents;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScenarioTest {
    private static WebDriver driver = null;
    private WebDriverWait wait;
    
    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-notifications");
        options.addArguments("disable-infobars");
        // Без этих опций вообще не начинало работу
        options.addArguments("disable-popup-blocking");
        options.addArguments("incognito");        
        
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver(options);
        
        //driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 10, 1000);

        // 1. Перейти по ссылке http://www.rgs.ru
        String baseUrl = "http://www.rgs.ru/";
        driver.get(baseUrl);
        
        new WebDriverWait(driver, 1).until( 
            webDriver -> ((JavascriptExecutor) webDriver)
        .executeScript("return document.readyState").equals("complete"));
    }
    
    @Test
    public void changeTabTest() throws InterruptedException {

        /* Выбор и нажатие кнопки согласия обработки "Cookie"
        * Без нажатия на эту кнопку не выполнялось никакое действие, а как
        * включить в настройках при запуске мне не удалось найти.
        * Добавлен обработчик исключения на тот случай, если у вас такой 
        * ситуации не происходит, чтобы он пропускал дальше выполнение теста.*/
        try {
            String btnCookieXPath = "//div[contains(@class, 'btn btn-default text-uppercase')]";
            WebElement btnCookie = driver.findElement(By.xpath(btnCookieXPath));
            btnCookie.click();
        } catch(NoSuchElementException e) {}
        
        // 2. Выбрать Меню
        String btnMenuXPath = "//a[contains(@class, 'hidden-xs') and @data-toggle='dropdown']";
        WebElement btnMenu = driver.findElement(By.xpath(btnMenuXPath));
        btnMenu.click();
        
        // 3.1 Далее выбрать: Компаниям
        String pnktMenuXPath = "//form[contains(@id,"
                + " 'rgs-main-menu-insurance-dropdown')]//a[contains(text(), 'Компаниям')]";
        WebElement pnktMenu = driver.findElement(By.xpath(pnktMenuXPath));
        pnktMenu.click();
        
        // 3.2 Далее выбрать: Здоровье
        String leftMenu1XPath = "//a[contains(text(), 'Здоровье')"
                + " and contains(@class, 'list-group')]";
        WebElement leftMenu1 = driver.findElement(By.xpath(leftMenu1XPath));
        leftMenu1.click();
        
        // 3.3 Далее выбрать: Добровольное медицинское страхование
        String leftMenu2XPath = "//a[contains(text(), 'Добровольное"
                + " медицинское') and contains(@class, 'list-group')]";
        WebElement leftMenu2 = driver.findElement(By.xpath(leftMenu2XPath));
        leftMenu2.click();
        
        // 4. Проверить наличие заголовка - Добровольное медицинское страхование
        String textHeaderXPath = "//h1[contains(text(), 'Добровольное"
                + " медицинское') and contains(@class, 'content-document-header')]";
        WebElement textHeader = driver.findElement(By.xpath(textHeaderXPath));
        Assert.assertEquals("Добровольное медицинское страхование",
                textHeader.getText());
        
        // 5. Нажать на кнопку - Отправить заявку
        String buttonBottomSendXPath = "//a[contains(@class, 'btn btn-default"
                + " text-uppercase hidden-xs') and contains(text(), 'Отправить заявку')]";
        WebElement buttonBottomSend = driver.findElement(By.xpath(buttonBottomSendXPath));
        buttonBottomSend.click();
        
        // 6. Проверить, что открылась страница , на которой присутствует
        // текст - Заявка на добровольное медицинское страхование
        String textDialogXPath = "//div[contains(@class,"
                + " 'modal-dialog')]//b[@data-bind='text: options.title']";
        WebElement textDialog = driver.findElement(By.xpath(textDialogXPath));
        wait.until(ExpectedConditions.visibilityOf(textDialog));
        Assert.assertEquals("Заявка на добровольное медицинское страхование",
                textDialog.getText());
        
        // XPath диалогового окна. После каждого ввода происходит щелчок на
        // пустом месте диалогового окна - без этого некорректно срабатывал ввод
        String dialogWindowXPath = "//div[contains(@class, 'modal-dialog')]"
                + "//div[@class='modal-body']";
        WebElement dialogWindow = driver.findElement(By.xpath(dialogWindowXPath));
        
        // Маска для создания XPath полей ввода
        String inputXPath = "//div[contains(@class, 'modal-dialog')]"
                + "//*[contains(@data-bind, '%s')]";
        // 7.1 Заполнить поля - Имя
        inputTextToDialog(driver.findElement(By.xpath(String.format(inputXPath,
                "LastName"))),"Александр", dialogWindow);
        // 7.2 Заполнить поля - Фамилия
        inputTextToDialog(driver.findElement(By.xpath(String.format(inputXPath,
                "FirstName"))), "Петров", dialogWindow);
        // 7.3 Заполнить поля - Отчество
        inputTextToDialog(driver.findElement(By.xpath(String.format(inputXPath,
                "MiddleName"))), "Евгеньевич", dialogWindow);
        // 7.4 Заполнить поля - Email
        inputTextToDialog(driver.findElement(By.xpath(String.format(inputXPath,
                "Email"))), "qwertyqwerty", dialogWindow);
        // 7.5 Заполнить поля - Предпочитаемая дата контакта
        inputTextToDialog(driver.findElement(By.xpath(String.format(inputXPath,
                "ContactDate"))), "22.02.2021", dialogWindow);
        
        // Строка для проверки комментария
        String comment = "Так названы справочные материалы о языках, их словах и"
                + " структурах слов. В эту категорию (пространство имен "
                + "«Приложение») попадают материалы о языках и лингвистике, "
                + "не являющиеся словарными статьями, например, тематические "
                + "таблицы, списки ложных друзей переводчика, списки "
                + "частотности отдельных языков и т. п. Они созданы для "
                + "лучшего понимания общей картины, связанной как с конкретными "
                + "языками, так и с взаимосвязями между ними.";
        // 7.6 Заполнить поля - Комментарий
        inputTextToDialog(driver.findElement(By.xpath(String.format(inputXPath,
                "Comment"))), comment, dialogWindow);

        // 7.7 Заполнить поля - Телефон
        // Для проверки корретности работы окна ввода телефона пришлось воспользоваться
        // таким способом, т.к. исходная строка не совпадает с той, которая
        // получается после ввода
        WebElement inputPhone = driver.findElement(By.xpath(String.format(inputXPath, "Phone")));
        inputPhone.click();
        inputPhone.sendKeys("9998745421");
        wait.until(ExpectedConditions.elementToBeClickable(dialogWindow));
        dialogWindow.click();
        Assert.assertEquals("+7 (999) 874-54-21",
                driver.findElement(By.xpath(String.format(inputXPath, "Phone")))
                        .getAttribute("value"));
        
        // 7.7 Выбор региона в ниспадающем меню
        // Нажатие на ниспадающее меню для открытия
        String selectRegionXPath = "//div[contains(@class, 'modal-dialog')]"
                + "//select[@name='Region']";
        WebElement selectRegion = driver.findElement(By.xpath(selectRegionXPath));
        selectRegion.click();
        wait.until(ExpectedConditions.visibilityOf(selectRegion));
        // Получение списка всех регионов
        List<WebElement> el = driver.findElements(By.xpath(
                "//div[contains(@class, 'modal-dialog')]//select[@name='Region']//option"));
        // Генерация числа для выбора случайного региона
        int elem = (int) (Math.random() * el.size());
        // Формирование XPath выбранного региона и нажатие на него
        String valueSelectElement = el.get(elem).getAttribute("value");
        String maskSelectElement = "//div[contains(@class, 'modal-dialog')]"
                + "//select[@name='Region']//option[contains(@value, '%s')]";
        String selectElementXPath = String.format(maskSelectElement, valueSelectElement);
        WebElement selectElement = driver.findElement(By.xpath(selectElementXPath));
        selectElement.click();
        // Нажатие на пустую часть диалогового окна для закрытия
        // ниспадающего меню
        wait.until(ExpectedConditions.elementToBeClickable(dialogWindow));
        dialogWindow.click();
        
        // 7.7 Нажатие на чекбокс согласия на обработку персональных данных
        String checkBoxXPath = "//div[contains(@class, 'modal-dialog')]"
                + "//input[contains(@type, 'checkbox')]";
        WebElement checkBox = driver.findElement(By.xpath(checkBoxXPath));
        checkBox.click();
        
        // 9. Нажатие кнопки "Отправить"
        String btnSendXPath = "//div[contains(@class, 'modal-dialog')]//button[@id='button-m']";
        WebElement btnSend = driver.findElement(By.xpath(btnSendXPath));
        btnSend.click();
        
        // 10. Проверить, что у Поля - Эл. почта присутствует сообщение 
        // об ошибке - Введите корректный email
        String msgErrorEmail = "//div[contains(@class, 'modal-dialog')]//"
                + "label[text()='Эл. почта']//..//span[contains(@class, 'validation-error-text')]";
        WebElement element = driver.findElement(By.xpath(msgErrorEmail));
        Assert.assertEquals("Введите адрес электронной почты", element.getText());
    }

    // Ввод текстовых данных в диалог ввода с последующим нажатием на пустое поле
    // диалогового окна
    // Перепробовал много разных вариантов ожиданий, но только этот сработал
    private void inputTextToDialog(WebElement element, String value,
            WebElement dialogWindow) {
        element.click();
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(value);
        wait.until(ExpectedConditions.elementToBeClickable(dialogWindow));
        dialogWindow.click();
        Assert.assertEquals(value, element.getAttribute("value"));
    }
    
    @After
    public void tearDown() {
        driver.quit();
    }
}
