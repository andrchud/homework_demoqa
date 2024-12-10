package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.TableComponent;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private SelenideElement firstNameInput = $("#firstName"),
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
            selecorInput = $("#stateCity-wrapper"),
            submitButton = $("#submit"),
            resultsTable = $(".table-responsive");

    CalendarComponent calendarComponent = new CalendarComponent();
    TableComponent tableComponent = new TableComponent();

    public RegistrationPage openPage(){
        open("/automation-practice-form");

        return this;
    }

    public RegistrationPage deleteBanners(){
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genderInput.$(byText(value)).click();

        return this;
    }
    public RegistrationPage setPhone(String value) {
        phoneInput.setValue(value);

        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPage selectSubjectsByInput(String subject) {
        subjectsInput.setValue(subject).pressEnter();

        return this;
    }

    public RegistrationPage selectHobbyByCheckBox(String hobby) {
        hobbyCheckbox.$(byText(hobby)).click();

        return this;
    }
    public RegistrationPage uploadFile(String path) {
        uploadPicture.uploadFromClasspath(path);

        return this;
    }
    public RegistrationPage currentAddress(String address) {
        addressInput.setValue(address);

        return this;
    }
    public RegistrationPage selectState(String state){
        stateSelector.click();
        selecorInput.$(byText(state)).click();

        return this;
    }

    public RegistrationPage selectCity(String city){
        citySelector.click();
        selecorInput.$(byText(city)).click();

        return this;
    }

    public RegistrationPage submit() {
        submitButton.click();

        return this;
    }
    public RegistrationPage checkResultTable(String key, String value){
        tableComponent.checkTable(key,value);
        return this;
    }

    public RegistrationPage unsuccessfulSubmit() {
        resultsTable.shouldNotBe(visible);

        return this;
    }
    public RegistrationPage invalidPhone(){
        phoneInput.shouldHave(cssValue("border-bottom-color", "rgba(220, 53, 69, 1)"));
        return this;
    }

}
