package com.epam.hplus.controller.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestrictedActions {
    private static final List<String> ADMIN_ONLY_ACTIONS = new ArrayList<>(Arrays.asList(
            "add_new_product",
            "add_new_product_page",
            "delete_product",
            "delete_user",
            "edit_product",
            "edit_product_page",
            "make_customer",
            "make_manager",
            "product_management",
            "user_management"));
    private static final List<String> MANAGER_ONLY_ACTIONS = new ArrayList<>(Arrays.asList(
            "approve_order",
            "order_management",
            "reject_order",
            "remove_order_by_manager"));
    private static final List<String> AUTHENTICATED_USER_ONLY_ACTIONS = new ArrayList<>();
    static {
      AUTHENTICATED_USER_ONLY_ACTIONS.addAll(Arrays.asList(
                    "orders",
                    "profile",
                    "remove_order"));
      AUTHENTICATED_USER_ONLY_ACTIONS.addAll(ADMIN_ONLY_ACTIONS);
      AUTHENTICATED_USER_ONLY_ACTIONS.addAll(MANAGER_ONLY_ACTIONS);
    }

    private RestrictedActions() {
    }

    public static List<String> getAdminOnlyActions() {
        return ADMIN_ONLY_ACTIONS;
    }

    public static List<String> getManagerOnlyActions() {
        return MANAGER_ONLY_ACTIONS;
    }

    public static List<String> getAuthenticatedUserOnlyActions() {
        return AUTHENTICATED_USER_ONLY_ACTIONS;
    }
}
