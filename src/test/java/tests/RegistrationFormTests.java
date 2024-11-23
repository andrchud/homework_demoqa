package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTests {

    String firstName = "Andrey";
    String lastName = "Chudov";
    String email = "andr.chud@yandex.ru";
    String phoneNumber = "9854441122";
    String currentAddress = "Indian, Ragesh Kutropale str. 13";

    String[][] expectedValues = {
            {"Student Name", firstName + " " + lastName},
            {"Student Email", email},
            {"Gender", "Other"},
            {"Mobile", phoneNumber},
            {"Date of Birth", "17 January,1998"},
            {"Subjects", "Physics, English"},
            {"Hobbies", "Reading"},
            {"Picture", "orshnik.png"},
            {"Address", currentAddress},
            {"State and City", "Haryana Karnal"}
    };

    @BeforeAll
    static void beforeAll(){
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillFormTest(){
        open("/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText("Other")).click();
        $("#userNumber").setValue(phoneNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__year-select").selectOption("1998");
        $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)").findBy(text("17")).click();
        $("#subjectsInput").setValue("Physics").pressEnter();
        $("#subjectsInput").setValue("English").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("orshnik.png");
        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#react-select-4-input").setValue("Karnal").pressEnter();
        $("#submit").click();
        $(".modal-content").shouldBe(visible);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        for (String[] pair : expectedValues) {
            $(".table").$(byText(pair[0])).sibling(0).shouldHave(text(pair[1]));
        }
    }
}
