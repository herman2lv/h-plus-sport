package com.epam.hplus.model.service;

import com.epam.hplus.model.beans.Order;
import com.epam.hplus.model.dao.impl.OrderDaoJdbc;
import com.epam.hplus.model.dao.OrderDao;

import java.util.List;

/**
 * A service class of model layer, intended to process operations
 * with {@code Order}.
 */
public class OrderService {
    /**
     * DAO object to get stored information about committed {@code Order}
     * and to save new information to the storage.
     */
    private static final OrderDao ORDER_DAO = OrderDaoJdbc.getInstance();

    /**
     * Private constructor to prevent instantiating of class object.
     */
    private OrderService() {
    }

    /**
     * Get {@code Order} of given {@code User}. Get only items for selected
     * page determined by start index and number of items on page
     * @param username {@code String} define certain {@code User}
     * @param currentIndex {@code int} value of start index to get info about
     *                                user's orders. Intended for paging
     *                                option. Determined by the number of
     *                                current page. Can be calculated the next
     *                                way:<p>currentIndex = (currentPageNumber -
     *                                1) * itemsOnPage)</p> Accepted by LIMIT
     *                                statement in SQL standard.
     * @param itemsOnPage {@code int} number of items to display on one page.
     *                               Intended to be used for paging option.
     *                               Accepted by LIMIT statement in SQL standard.
     * @return {@code List} of {@code Order} of given {@code User} from position
     * data storage equals {@code int} currentIndex, max length of list -
     * no more than value of {@code int} itemsOnPage.
     */
    public static List<Order> getOrdersOfUser(String username, int currentIndex, int itemsOnPage) {
        return ORDER_DAO.getOrdersByUser(username, currentIndex, itemsOnPage);
    }

    /**
     * Count {@code Order} number for selected {@code User}.
     * @param username {@code String} define certain {@code User}
     * @return {@code int} number of {@code Order} of selected {@code User}
     */
    public static int countOrdersOfUser(String username) {
        return ORDER_DAO.countOrdersOfUser(username);
    }

    /**
     * Pass information about {@code Order} to DAO object to create a new
     * entry and storey it.
     * @param order instance of {@code Order}, containing all necessary
     *              information about user's order, that need to be stored
     * @return {@code true} if DAO object reports that entry or order was
     * successfully created
     */
    public static boolean createOrder(Order order) {
        return ORDER_DAO.createOrder(order) > 0;
    }

    /**
     * Remove information about user's order from storage via DAO object.
     * Order is defined by unique identification number.
     * @param orderId {@code int} value of unique {@code Order} id
     * @return {@code true} if DAO object reports that entry containing order
     * id was successfully removed from the storage
     */
    public static boolean removeOrder(int orderId) {
        return ORDER_DAO.removeOrder(orderId);
    }

    /**
     * Get {@code List} of {@code Order} that represented in information
     * storage. Get only items for selected page determined by start
     * index and number of items on page
     * @param currentIndex {@code int} value of start index to get info about
     *                                orders. Intended for paging option.
     *                                Determined by the number of current page.
     *                                Can be calculated the next way:
     *                                <p>currentIndex = (currentPageNumber -
     *                                1) * itemsOnPage)</p> Accepted by LIMIT
     *                                statement in SQL standard.
     * @param itemsOnPage {@code int} number of items to display on one page.
     *                               Intended to be used for paging option.
     *                               Accepted by LIMIT statement in SQL standard.
     * @return {@code List} of {@code Order} from position in data storage that
     * equals {@code int} currentIndex, max length of list - no more than value
     * of {@code int} itemsOnPage.
     */
    public static List<Order> getOrders(int currentIndex, int itemsOnPage) {
        return ORDER_DAO.getOrders(currentIndex, itemsOnPage);
    }

    /**
     * Count all {@code Order} number in information storage.
     * @return {@code int} number of {@code Order}
     */
    public static int countOrders() {
        return ORDER_DAO.countOrders();
    }

    /**
     * Changes {@code Order} confirmation status for {@code true} - "approved".
     * @param orderId {@code int} value of unique {@code Order} id
     * @return {@code true} if order confirmation status was changed
     * successfully.
     */
    public static boolean approveOrder(int orderId) {
        return setOrderStatus(orderId, true);
    }

    /**
     * Changes {@code Order} confirmation status for {@code true} - "approved",
     * or {@code false} - "pending/rejected".
     * @param orderId {@code int} value of unique {@code Order} id
     * @param status {@code boolean} - new order confirmation status.
     *                              {@code true} for "approved", and
     *                              {@code false} for "pending/rejected"
     * @return {@code true} if order confirmation status was changed
     * successfully.
     */
    private static boolean setOrderStatus(int orderId, boolean status) {
        Order order = ORDER_DAO.getOrderById(orderId);
        order.setStatus(status);
        return ORDER_DAO.updateOrder(order);
    }

    /**
     * Changes {@code Order} confirmation status for {@code false} -
     * "pending/rejected".
     * @param orderId {@code int} value of unique {@code Order} id
     * @return {@code true} if order confirmation status was changed
     * successfully.
     */
    public static boolean rejectOrder(int orderId) {
        return setOrderStatus(orderId, false);
    }
}
