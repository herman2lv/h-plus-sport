package com.epam.hplus.controller.commands.enums;

import com.epam.hplus.controller.commands.AddProductCommand;
import com.epam.hplus.controller.commands.ApproveOrderCommand;
import com.epam.hplus.controller.commands.CartCommand;
import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.controller.commands.DeleteUserCommand;
import com.epam.hplus.controller.commands.HomeCommand;
import com.epam.hplus.controller.commands.LanguageCommand;
import com.epam.hplus.controller.commands.LoginCommand;
import com.epam.hplus.controller.commands.LoginPageCommand;
import com.epam.hplus.controller.commands.LogoutCommand;
import com.epam.hplus.controller.commands.MakeCustomerCommand;
import com.epam.hplus.controller.commands.MakeManagerCommand;
import com.epam.hplus.controller.commands.MakeOrderCommand;
import com.epam.hplus.controller.commands.OrderManagementCommand;
import com.epam.hplus.controller.commands.OrdersCommand;
import com.epam.hplus.controller.commands.ProductManagementCommand;
import com.epam.hplus.controller.commands.ProfileCommand;
import com.epam.hplus.controller.commands.RedirectCommand;
import com.epam.hplus.controller.commands.RegisterUserCommand;
import com.epam.hplus.controller.commands.RegisterUserPageCommand;
import com.epam.hplus.controller.commands.RejectOrderCommand;
import com.epam.hplus.controller.commands.RemoveOrderByManagerCommand;
import com.epam.hplus.controller.commands.RemoveOrderCommand;
import com.epam.hplus.controller.commands.RemoveProductCommand;
import com.epam.hplus.controller.commands.SearchCommand;
import com.epam.hplus.controller.commands.UserManagementCommand;

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
    ADD_PRODUCT(new AddProductCommand()),
    CART(new CartCommand()),
    PRODUCT_MANAGEMENT(new ProductManagementCommand()),
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
