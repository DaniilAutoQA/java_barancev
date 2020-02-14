package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

public class ClientCreationTests extends TestBase {


    @Test
    public void testUntitledTestCase() {
        app.getClientHelper().createClient(new ClientData("dast", "mast", "deep", "english", "learn", "englishPod", "London", "6214987651", "test1"), true);

    }

}
