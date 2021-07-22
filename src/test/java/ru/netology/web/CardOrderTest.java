package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class CardOrderTest {
//    java -jar artifacts\app-order.jar

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Василий");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $(".Success_successBlock__2L3Cw").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldPass() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Васин-Красин Василь");
        form.$("[data-test-id=phone] input").setValue("+71234567890");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=button]").click();
        $(".Success_successBlock__2L3Cw").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

//    Задание 2.
@Test
void NameValidation() {
    open("http://localhost:9999");
    SelenideElement form = $("[id=root]");
    form.$("[data-test-id=name] input").setValue("5");
    form.$("[role=button]").click();
    $(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
}

    @Test
    void PhoneValidation() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Василий");
        form.$("[data-test-id=phone] input").setValue("5");
        form.$("[role=button]").click();
        $$(".input__sub").get(1).shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void CheckboxValidation() {
        open("http://localhost:9999");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=name] input").setValue("Василий");
        form.$("[data-test-id=phone] input").setValue("+79012345678");
        form.$("[role=button]").click();
        $(".input_invalid ").exists();
    }

}
