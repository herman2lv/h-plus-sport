package com.epam.hplus.controller.commands.enums;

import com.epam.hplus.controller.commands.AddProductCommand;
import com.epam.hplus.controller.commands.ApproveOrderCommand;
import com.epam.hplus.controller.commands.CartCommand;
import com.epam.hplus.controller.commands.Command;
import com.epam.hplus.controller.commands.HomeCommand;
import com.epam.hplus.controller.commands.LanguageCommand;
import com.epam.hplus.controller.commands.LoginCommand;
import com.epam.hplus.controller.commands.LogoutCommand;
import com.epam.hplus.controller.commands.MakeOrderCommand;
import com.epam.hplus.controller.commands.OrderManagementCommand;
import com.epam.hplus.controller.commands.OrdersCommand;
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
    REGISTERUSER(new RegisterUserCommand()),
    REGISTERUSERPAGE(new RegisterUserPageCommand()),
    REMOVEPRODUCT(new RemoveProductCommand()),
    LANGUAGE(new LanguageCommand()),
    HOME(new HomeCommand()),
    SEARCH(new SearchCommand()),
    PROFILE(new ProfileCommand()),
    ADDPRODUCT(new AddProductCommand()),
    CART(new CartCommand()),
    REMOVEORDER(new RemoveOrderCommand()),
    ORDERMANAGEMENT(new OrderManagementCommand()),
    REMOVEORDERBYMANAGER(new RemoveOrderByManagerCommand()),
    APPROVEORDER(new ApproveOrderCommand()),
    REJECTORDER(new RejectOrderCommand()),
    USERMANAGEMENT(new UserManagementCommand()),
    MAKEORDER(new MakeOrderCommand()),
    REDIRECT(new RedirectCommand());

    private final Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
