package ru.bethowen;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;

public class LoginTestClass {
    @Test
    public void authEmail() {
        timeout = 500;
        pageLoadStrategy = "none";

        open("https://bethowen.ru/");

        $$("[data-name='auth']").get(0).waitWhile(hidden, 7000).click();
        $("#new-reg-auth").find(".js_show_new-auth").waitWhile(hidden, 3000).click();
        $("#new-auth").find(".new-auth-login").setValue("nkh-4-dev@yandex.ru");
        // умышлено неверный пароль
        $("#new-auth").find(".new-auth-pswd").setValue("405834089");

        $("#new-auth").find(".new-auth-button-action").click();
        // проверяем что сообщение не появилось
        $(".new-auth-new-reg-error").waitWhile(hidden, 2000).find("div").shouldHave(text("Неверные телефон/email или пароль"));
        // удаляем лишний симпол
        $("#new-auth").find(".new-auth-pswd").sendKeys("\b");
        //
        $("#new-auth").find(".new-auth-button-action").click();
        // проеверяем, что попарли на персональную старницу
        $(".page-top-wrapper").waitWhile(hidden, 15000).find("#pagetitle").shouldHave(text("Личный кабинет"));

    }
}
