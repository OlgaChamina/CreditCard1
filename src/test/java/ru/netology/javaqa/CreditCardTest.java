package ru.netology.javaqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CreditCardTest {

    @BeforeEach
    void SetUp() {
        Configuration.headless = true;
        open("http://localhost:9999");
    }

    @Test
    void shouldTestIfOk1() //поле ввода имени - кириллица через пробел;
    //поле ввода телефона 11 цифр, + на первом месте
    {
        $("[data-test-id=name] input").setValue("Ольга Геннадьевна");
        $("[data-test-id=phone] input").setValue("+79883334411");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=order-success").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! " +
                "Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldTestIfOk2() //поле ввода имени - кириллица через дефис
    //поле ввода телефона 11 цифр, + на первом месте
    {
        $("[data-test-id=name] input").setValue("Анна-Мария Лютикова");
        $("[data-test-id=phone] input").setValue("+79883334411");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=order-success").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! " +
                "Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldTestIfOk3() //поле ввода имени - кириллица с caps lock - все буквы заглавные
    //поле ввода телефона 11 цифр, + на первом месте
    {
        $("[data-test-id=name] input").setValue("ДМИТРИЙ АЛЕКСЕЕВИЧ");
        $("[data-test-id=phone] input").setValue("+79883334411");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=order-success").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! " +
                "Наш менеджер свяжется с вами в ближайшее время."));

    }

    //@Test
    //void shouldTestIfOk4() //поле ввода имени - написание Ё
    //поле ввода телефона 11 цифр, + на первом месте
    //{
    //$("[data-test-id=name] input").setValue("Эдуард Ёжиков");
    //$("[data-test-id=phone] input").setValue("+79883334411");
    //$("[data-test-id=agreement]").click();
    //$(".button").click();
    // $("[data-test-id=order-success").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! " +
    //        "Наш менеджер свяжется с вами в ближайшее время."));

    //}

    @Test
    void shouldTestIfOk5() //поле ввода имени - 10 пробелов между именем и фамилией
    //поле ввода телефона 11 цифр, + на первом месте
    {
        $("[data-test-id=name] input").setValue("Максим          Викторович");
        $("[data-test-id=phone] input").setValue("+79883334411");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=order-success").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! " +
                "Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldTestIfNotOk1() //поле ввода имени - латиница
    //поле ввода телефона 11 цифр, + на первом месте
    {
        $("[data-test-id=name] input").setValue("Andrey Fox");
        $("[data-test-id=phone] input").setValue("+79883334411");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия " +
                "указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestIfNotOk2() //поле ввода имени - кириллица и цифры
    //поле ввода телефона 11 цифр, + на первом месте
    {
        $("[data-test-id=name] input").setValue("Андрей 1986");
        $("[data-test-id=phone] input").setValue("+79883334411");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия " +
                "указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestIfNotOk3() //поле ввода имени - кириллица с символом +
    //поле ввода телефона 11 цифр, + на первом месте
    {
        $("[data-test-id=name] input").setValue("Валентина + Ивановна");
        $("[data-test-id=phone] input").setValue("+79883334411");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия " +
                "указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestIfNotOk4() //поле ввода имени - кириллица с апострофом
    //поле ввода телефона 11 цифр, + на первом месте
    {
        $("[data-test-id=name] input").setValue("Жанна Д`арк");
        $("[data-test-id=phone] input").setValue("+79883334411");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия " +
                "указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestIfNotOk5() //поле ввода имени - только пробелы
    //поле ввода телефона 11 цифр, + на первом месте
    {
        $("[data-test-id=name] input").setValue("          ");
        $("[data-test-id=phone] input").setValue("+79883334411");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_has-value .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestIfNotOk6() //поле ввода имени - пустое
    //поле ввода телефона 11 цифр, + на первом месте
    {
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79883334411");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestIfNotOk7() //поле ввода имени - заполнено
    //поле ввода телефона 11 цифр, + на первом месте, с символами - код в ()
    {
        $("[data-test-id=name] input").setValue("Андрей Васильевич");
        $("[data-test-id=phone] input").setValue("+7(988)3334411");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан " +
                "неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestIfNotOk8() //поле ввода имени - заполнено
    //поле ввода телефона 11 цифр, = символ на первом месте,
    {
        $("[data-test-id=name] input").setValue("Андрей Васильевич");
        $("[data-test-id=phone] input").setValue("=79883334411");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан " +
                "неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestIfNotOk9() //поле ввода имени - заполнено
    //поле ввода телефона 11 цифр, + символ на первом месте, с пробелами
    {
        $("[data-test-id=name] input").setValue("Андрей Васильевич");
        $("[data-test-id=phone] input").setValue("+7 988 333 44 11");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан " +
                "неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestIfNotOk10() //поле ввода имени - заполнено
    //поле ввода телефона 10 цифр, + символ на первом месте
    {
        $("[data-test-id=name] input").setValue("Андрей Васильевич");
        $("[data-test-id=phone] input").setValue("+7988333441");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан " +
                "неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestIfNotOk11() //поле ввода имени - заполнено
    //поле ввода телефона 12 цифр, + символ на первом месте
    {
        $("[data-test-id=name] input").setValue("Андрей Васильевич");
        $("[data-test-id=phone] input").setValue("+798833344111");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан " +
                "неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestIfNotOk12() //поле ввода имени - заполнено
    //поле ввода телефона 1 цифра, + символ на первом месте
    {
        $("[data-test-id=name] input").setValue("Андрей Васильевич");
        $("[data-test-id=phone] input").setValue("+7");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан " +
                "неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestIfNotOk13() //поле ввода имени - заполнено
    //поле ввода телефона - пустое
    {
        $("[data-test-id=name] input").setValue("Андрей Васильевич");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно " +
                "для заполнения"));
    }

    @Test
    void shouldTestIfNotOk14() //поле ввода имени - заполнено
    //поле ввода телефона - 11 пробелов
    {
        $("[data-test-id=name] input").setValue("Андрей Васильевич");
        $("[data-test-id=phone] input").setValue("           ");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно " +
                "для заполнения"));
    }

    @Test
    void shouldTestIfNotOk15() //поле ввода имени - заполнено
    //поле ввода телефона - 11 цифр + символ на первом месте
    // не нажат чек-бокс
    {
        $("[data-test-id=name] input").setValue("Андрей Васильевич");
        $("[data-test-id=phone] input").setValue("+79092132683");
        $(".button").click();
        $("[data-test-id=agreement].input_invalid").shouldHave(Condition.exactText("Я соглашаюсь с условиями " +
                "обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}
