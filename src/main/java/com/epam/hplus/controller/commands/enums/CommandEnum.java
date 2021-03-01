package com.epam.hplus.controller.commands.enums;

import com.epam.hplus.controller.commands.impl.authorized.admin.AddNewProductCommand;
import com.epam.hplus.controller.commands.impl.authorized.admin.AddNewProductPageCommand;
import com.epam.hplus.controller.commands.impl.AddProductCommand;
import com.epam.hplus.controller.commands.impl.authorized.admin.EditProductCommand;
import com.epam.hplus.controller.commands.impl.authorized.manager.ApproveOrderCommand;
import com.epam.hplus.controller.commands.impl.CartCommand;
import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.controller.commands.impl.authorized.admin.DeleteProductCommand;
import com.epam.hplus.controller.commands.impl.authorized.admin.DeleteUserCommand;
import com.epam.hplus.controller.commands.impl.authorized.admin.EditProductPageCommand;
import com.epam.hplus.controller.commands.impl.HomeCommand;
import com.epam.hplus.controller.commands.impl.LanguageCommand;
import com.epam.hplus.controller.commands.impl.LoginCommand;
import com.epam.hplus.controller.commands.impl.LoginPageCommand;
import com.epam.hplus.controller.commands.impl.LogoutCommand;
import com.epam.hplus.controller.commands.impl.authorized.admin.MakeCustomerCommand;
import com.epam.hplus.controller.commands.impl.authorized.admin.MakeManagerCommand;
import com.epam.hplus.controller.commands.impl.MakeOrderCommand;
import com.epam.hplus.controller.commands.impl.authorized.manager.OrderManagementCommand;
import com.epam.hplus.controller.commands.impl.authorized.OrdersCommand;
import com.epam.hplus.controller.commands.impl.authorized.admin.ProductManagementCommand;
import com.epam.hplus.controller.commands.impl.authorized.ProfileCommand;
import com.epam.hplus.controller.commands.impl.RedirectCommand;
import com.epam.hplus.controller.commands.impl.RegisterUserCommand;
import com.epam.hplus.controller.commands.impl.RegisterUserPageCommand;
import com.epam.hplus.controller.commands.impl.authorized.manager.RejectOrderCommand;
import com.epam.hplus.controller.commands.impl.authorized.manager.RemoveOrderByManagerCommand;
import com.epam.hplus.controller.commands.impl.authorized.RemoveOrderCommand;
import com.epam.hplus.controller.commands.impl.RemoveProductCommand;
import com.epam.hplus.controller.commands.impl.SearchCommand;
import com.epam.hplus.controller.commands.impl.authorized.admin.UserManagementCommand;

public enum CommandEnum {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    ORDERS(new OrdersCommand()),
    REGISTER_USER(new RegisterUserCommand()),
    REGISTER_USER_PAGE(new RegisterUserPageCommand()),
    REMOVE_PRODUCT(new RemoveProductCommand()),
    LANGUAGE(new LanguageCommand()),
    HOME(new HomeCommand()),
    SEARCH(new SearchCommand()),
    PROFILE(new ProfileCommand()),
    ADD_NEW_PRODUCT(new AddNewProductCommand()),
    EDIT_PRODUCT(new EditProductCommand()),
    ADD_NEW_PRODUCT_PAGE(new AddNewProductPageCommand()),
    EDIT_PRODUCT_PAGE(new EditProductPageCommand()),
    ADD_PRODUCT(new AddProductCommand()),
    CART(new CartCommand()),
    PRODUCT_MANAGEMENT(new ProductManagementCommand()),
    DELETE_PRODUCT(new DeleteProductCommand()),
    REMOVE_ORDER(new RemoveOrderCommand()),
    ORDER_MANAGEMENT(new OrderManagementCommand()),
    REMOVE_ORDER_BY_MANAGER(new RemoveOrderByManagerCommand()),
    APPROVE_ORDER(new ApproveOrderCommand()),
    REJECT_ORDER(new RejectOrderCommand()),
    USER_MANAGEMENT(new UserManagementCommand()),
    MAKE_CUSTOMER(new MakeCustomerCommand()),
    MAKE_MANAGER(new MakeManagerCommand()),
    MAKE_ORDER(new MakeOrderCommand()),
    DELETE_USER(new DeleteUserCommand()),
    LOGIN_PAGE(new LoginPageCommand()),
    REDIRECT(new RedirectCommand());

    private final Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
