import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardSendingTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Должен все заполнять, потом все данные оставлять и менять только дату, затем перепланировать")

    void requestToDeliveryCard() {
        open("http://localhost:9999");
        $("[placeholder ='Город']").setValue(DataHelper.getCity());
        DataHelper.cleanUp();
        $("[placeholder='Дата встречи']").setValue(DataHelper.getFormattedFutureDate());
        $("[data-test-id=name] input").setValue(DataHelper.getName());
        $("[data-test-id=phone] input").setValue(DataHelper.getPhone());
        $("[data-test-id=agreement]").click();
        $(".button__text").click();
        $(withText("Успешно!")).waitUntil(Condition.visible, 5000);
        DataHelper.cleanUp();
        $("[placeholder='Дата встречи']").setValue(DataHelper.getNewDate());
        $(".button__text").click();
        $("[data-test-id='replan-notification']").waitUntil(Condition.visible,
                5000);
        $(".button__text").click();
        $(withText("Успешно!")).waitUntil(Condition.visible, 15000);

    }

    }
