package com.example.application.views.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.application.data.entity.Contact;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.login.testbench.LoginFormElement;
import com.vaadin.flow.component.orderedlayout.testbench.VerticalLayoutElement;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.testbench.annotations.Attribute;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@SpringBootTest
public class ListViewTest {

    static {
        // Prevent Vaadin Development mode to launch browser window
        System.setProperty("vaadin.launch-browser", "false");
    }

    @Autowired
    private ListView listView;

    @Test
    public void formShownWhenContactSelected() {
        Grid<Contact> grid = listView.grid;
        Contact firstContact = getFirstItem(grid);

        ContactForm form = listView.form;

        assertFalse(form.isVisible());
        grid.asSingleSelect().setValue(firstContact);
        assertTrue(form.isVisible());
        assertEquals(firstContact.getFirstName(), form.firstName.getValue());
    }

    private Contact getFirstItem(Grid<Contact> grid) {
        return( (ListDataProvider<Contact>) grid.getDataProvider()).getItems().iterator().next();
    }

    @Attribute(name = "class", contains = "login-view")
    public static class LoginViewElement extends VerticalLayoutElement {

        public boolean login(String username, String password) {
            LoginFormElement form = $(LoginFormElement.class).first();
            form.getUsernameField().setValue(username);
            form.getPasswordField().setValue(password);
            form.getSubmitButton().click();

            // Return true if we end up on another page
            try {
                getDriver().manage().timeouts().implicitlyWait(Duration.of(1, ChronoUnit.SECONDS));
                getDriver().findElement(By.tagName("vaadin-app-layout"));
                return true;
            } catch (Exception e) {
                return false;
            }
        }

    }
}