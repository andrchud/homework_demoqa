package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderInput = $("#genterWrapper"),
            phoneInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbyCheckbox = $("#hobbiesWrapper"),
            uploadPicture = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateSelector = $("#state"),
            citySelector = $("#city"),
            selectorInput = $("#stateCity-wrapper"),
            submitButton = $("#submit");


    CalendarComponent calendarComponent = new CalendarComponent();

    @Step("Открыть страницу формы регистрации")
    public RegistrationPage openPage(){
        open("/automation-practice-form");

        return this;
    }

    @Step("Убрать рекламные баннеры")
    public RegistrationPage deleteBanners(){
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    @Step("Ввести имя")
    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    @Step("Ввести фамилию")
    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    @Step("Ввести email")
    public RegistrationPage setEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    @Step("Выбрать пол")
    public RegistrationPage setGender(String value) {
        genderInput.$(byText(value)).click();

        return this;
    }

    @Step("Ввести номер телефона")
    public RegistrationPage setPhone(String value) {
        phoneInput.setValue(value);

        return this;
    }

    @Step("Выбрать дату рождения")
    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    @Step("Выбрать дисциплину")
    public RegistrationPage setSubjectByInput(String subject) {
        subjectsInput.setValue(subject).pressEnter();

        return this;
    }

    @Step("Выбрать хобби")
    public RegistrationPage setHobbyByCheckBox(String hobby) {
        hobbyCheckbox.$(byText(hobby)).click();

        return this;
    }

    @Step("Загрузить фото")
    public RegistrationPage uploadFile(String path) {
        uploadPicture.uploadFromClasspath(path);

        return this;
    }

    @Step("Вести адрес")
    public RegistrationPage setCurrentAddress(String address) {
        addressInput.setValue(address);

        return this;
    }

    @Step("Выбрать штат")
    public RegistrationPage setState(String state){
        stateSelector.click();
        selectorInput.$(byText(state)).click();

        return this;
    }

    @Step("Выбрать город")
    public RegistrationPage setCity(String city){
        citySelector.click();
        selectorInput.$(byText(city)).click();

        return this;
    }

    @Step("Подтвердить данные заполненной формы")
    public RegistrationPage submit() {
        submitButton.click();

        return this;
    }


}
