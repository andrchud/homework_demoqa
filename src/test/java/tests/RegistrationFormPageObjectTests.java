package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.verification.RegistrationPageVerification;

import java.util.Locale;

public class RegistrationFormPageObjectTests extends TestBase {

    Faker faker = new Faker(new Locale("en"));

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String gender = "Other";
    String email = faker.internet().emailAddress();
    String phoneNumber = faker.phoneNumber().phoneNumber();
    String dayOfBirth = "17";
    String monthOfBirth = "January";
    String yearOfBirth = "1998";
    String subjectFirst = "Physics";
    String subjectSecond = "English";
    String hobbies = "Reading";
    String pathToFile = "orshnik.png";
    String currentAddress = faker.address().streetAddress();
    String state = "Haryana";
    String city = "Karnal";


    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationPageVerification registrationVerification = new RegistrationPageVerification();

    @Test
    void fillFormTest(){

        String[][] expectedValues = {
                {"Student Name", firstName + " " + lastName},
                {"Student Email", email},
                {"Gender", gender},
                {"Mobile", phoneNumber},
                {"Date of Birth", dayOfBirth + " " + monthOfBirth +","+yearOfBirth},
                {"Subjects", subjectFirst + ", " + subjectSecond},
                {"Hobbies", hobbies},
                {"Picture", pathToFile},
                {"Address", currentAddress},
                {"State and City", state + " " + city}
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
                setState(state).
                setCity(city).
                submit();
        for (String[] pair : expectedValues) {
            registrationVerification.checkResultTable(pair[0],pair[1]);
        }
    }

    @Test
    void minimumFillFormTest(){

        String[][] expectedValues = {
                {"Student Name", firstName + " " + lastName},
                {"Student Email", " "},
                {"Gender", gender},
                {"Mobile", phoneNumber},
                {"Date of Birth", dayOfBirth + " " + monthOfBirth +","+yearOfBirth},
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
                submit();
        for (String[] pair : expectedValues) {
            registrationVerification.checkResultTable(pair[0],pair[1]);
        }
    }

    @Test
    void negativeFillFormTest(){
        registrationPage.openPage().
                deleteBanners().
                setFirstName(firstName).
                setLastName(lastName).
                setGender(gender).
                setDateOfBirth(dayOfBirth,monthOfBirth,yearOfBirth).
                submit();

        registrationVerification.checkResultTableInvisibility().
                checkPhoneRequired();
    }
}
