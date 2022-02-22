package com.github.rozumek29.plantseekerpanel.views.users;

import com.github.rozumek29.plantseekerpanel.data.entity.User;
import com.github.rozumek29.plantseekerpanel.data.service.UserService;
import com.github.rozumek29.plantseekerpanel.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.vaadin.crudui.crud.impl.GridCrud;

import javax.annotation.security.RolesAllowed;

@PageTitle("Users")
@Route(value = "users", layout = MainLayout.class)
@RolesAllowed("admin")
public class UsersView extends VerticalLayout {

    public UsersView(UserService service) {
        setSpacing(false);

        var grid = new GridCrud<>(User.class, service);
        grid.getGrid().setColumns("id", "username", "roles");
        grid.setAddOperationVisible(false);
        grid.setUpdateOperationVisible(false);
        Button addUser = new Button(new Icon(VaadinIcon.PLUS));
        addUser.setIconAfterText(true);
        addUser.addClickListener( e -> UI.getCurrent().navigate(NewUser.class));
        grid.getCrudLayout().addToolbarComponent(addUser);

        add(
                grid
        );

    }

}
