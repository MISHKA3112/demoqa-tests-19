package com.demoqa;

import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PractriceFormTest extends TestBase {
    @Test
    void practiceForm() {
        // открываем сайт
        open("/automation-practice-form");
        // убрать рекламу
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        // заполняем форму студента: Name (First Name, Last Name) и Email
        $("#firstName").setValue("Tsukerman");
        $("#lastName").setValue("Mikhail");
        $("#userEmail").setValue("Mikhail@tsukerman.ru");

        // выбираем пол
        $(byText("Male")).click();

        // вводим номер телефона
        $("#userNumber").setValue("8926365787");

        //выбираем дату рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(byText("December")).click();
        $(".react-datepicker__year-select").click();
        $(byText("1992")).click();
        $(".react-datepicker__day--031").click();

        // выбираем предмет изучения
        $("#subjectsInput").setValue("Arts");
        $("#subjectsInput").pressEnter();

        // выбираем категорию
        $(byText("Sports")).click();

        // загружаем файл
        $("#uploadPicture").uploadFile(new File("src/test/resources/images/1.jpg"));

        // указываем адрес
        $("#currentAddress").setValue("3-rd Gr");

        //выбираем штат
        $("#state").click();
        $(byText("NCR")).click();

        //выбираем город
        $("#city").click();
        $(byText("Delhi")).click();

        // отправляем форму
        $("#submit").click();

        // проверка
        $(".table").shouldHave(
                text("Tsukerman Mikhail"),
                text("Mikhail@tsukerman.ru"),
                text("Male"),
                text("8926365787"),
                text("31 December,1992"),
                text("Arts"),
                text("Sports"),
                text("1.jpg"),
                text("3-rd Gr"),
                text("NCR Delhi"));

    }
}
