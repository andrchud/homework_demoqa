package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.verification.RegistrationPageVerification;


import static utils.RandomUtils.*;

@Tag("demoqa")
public class RegistrationFormPageObjectTests extends TestBase {

    String firstName = getFirstName();
    String lastName = getLastName();
    String gender = getGender();
    String email = getUserEmail();
    String phoneNumber = getUserPhone();
    String dayOfBirth = getDayOfBirth();
    String monthOfBirth = getMonthOfBirth();
    String yearOfBirth = getYearOfBirth();
    String subjectFirst = getSubject();
    String subjectSecond = getSubject();
    String hobbies = getHobby();
    String pathToFile = getPicture();
    String currentAddress = getStreetAddress();
    String state = getState();
    String city = getCity();


    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationPageVerification registrationVerification = new RegistrationPageVerification();

    @Test
    @Description("Полностью заполненная форма для успешной регистрации")
    void fillFormTest(){

        String[][] expectedValues = {
                {"Student Name", String.format("%s %s",firstName,lastName)},
                {"Student Email", email},
                {"Gender", gender},
                {"Mobile", phoneNumber},
                {"Date of Birth", String.format("%s %s,%s",dayOfBirth,monthOfBirth,yearOfBirth)},
                {"Subjects", String.format("%s, %s",subjectFirst,subjectSecond)},
                {"Hobbies", hobbies},
                {"Picture", pathToFile},
                {"Address", currentAddress},
                {"State and City", String.format("%s %s",state,city)}
        };

        registrationPage.openPage().
                deleteBanners().
                setFirstName(firstName).
                setLastName(lastName).
                setEmail(email).
                setGender(gender).
                setPhone(phoneNumber).
                setDateOfBirth(dayOfBirth,monthOfBirth,yearOfBirth).
                setSubjectByInput(subjectFirst).
                setSubjectByInput(subjectSecond).
                setHobbyByCheckBox(hobbies).
                uploadFile(pathToFile).
                setCurrentAddress(currentAddress).
                deleteBanners().
                setState(state).
                setCity(city).
                submit();
        for (String[] pair : expectedValues) {
            registrationVerification.checkResultTable(pair[0],pair[1]);
        }
    }

    @Test
    @Description("Минимально заполненная форма для успешной регистрации")
    void minimumFillFormTest(){

        String[][] expectedValues = {
                {"Student Name", String.format("%s %s",firstName,lastName)},
                {"Student Email", " "},
                {"Gender", gender},
                {"Mobile", phoneNumber},
                {"Date of Birth", String.format("%s %s,%s",dayOfBirth,monthOfBirth,yearOfBirth)},
                {"Subjects", " "},
                {"Hobbies", " "},
                {"Picture", " "},
                {"Address", " "},
                {"State and City", " "}
        };

        registrationPage.openPage().
                deleteBanners().
                setFirstName(firstName).
                setLastName(lastName).
                setGender(gender).
                setPhone(phoneNumber).
                setDateOfBirth(dayOfBirth,monthOfBirth,yearOfBirth).
                deleteBanners().
                submit();
        for (String[] pair : expectedValues) {
            registrationVerification.checkResultTable(pair[0],pair[1]);
        }
    }

    @Test
    @Description("Неуспешно заполненная форма регистрации — неправельный телефонный номер")
    void negativeFillFormTest(){
        registrationPage.openPage().
                deleteBanners().
                setFirstName(firstName).
                setLastName(lastName).
                setGender(gender).
                setDateOfBirth(dayOfBirth,monthOfBirth,yearOfBirth).
                deleteBanners().
                submit();

        registrationVerification.checkResultTableInvisibility().
                checkPhoneRequired();
    }
}
