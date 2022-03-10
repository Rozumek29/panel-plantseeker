package com.github.rozumek29.plantseekerpanel.views.users;

import com.github.rozumek29.plantseekerpanel.enums.Role;
import com.github.rozumek29.plantseekerpanel.data.entity.User;
import com.github.rozumek29.plantseekerpanel.data.service.UserService;
import com.github.rozumek29.plantseekerpanel.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.security.RolesAllowed;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route(value = "addUser", layout = MainLayout.class)
@RolesAllowed("admin")
public class NewUser extends VerticalLayout {

    private final TextField usernameField = new TextField("Username");
    private final TextField nameField = new TextField("Name");
    private final PasswordField passwordField = new PasswordField("Password");
    private final ComboBox<Role> role = new ComboBox<Role>("Roles");

    public NewUser(UserService service, PasswordEncoder encoder) {
        role.setItems(Role.USER, Role.ADMIN);
        add(
                new Button(new Icon(VaadinIcon.ARROW_BACKWARD), event -> {
                    UI.getCurrent().navigate(UsersView.class);}),
                new FormLayout(usernameField, nameField, passwordField, role),
                new Button("Save", event -> {

                    if (!usernameField.getValue().isEmpty() && !nameField.isEmpty() && !passwordField.isEmpty()){
                        User user = new User();
                        user.setUsername(this.usernameField.getValue());
                        user.setName(this.nameField.getValue());
                        user.setHashedPassword(encoder.encode(this.passwordField.getValue()));
                        if (this.role.getValue() == Role.ADMIN) {
                            user.setRoles(Stream.of(Role.USER, Role.ADMIN).collect(Collectors.toSet()));
                        } else {
                            user.setRoles(Collections.singleton(Role.USER));
                        }
                        service.add(user);

                        Notification.show("User has been added");
                    }else {
                        Notification.show("Fields cannot be empty.");
                    }
                })
        );
    }
}
