# Site of the sports nutrition company "H+ Sport"

### Author: Herman Rabinkin

## Description

The project represents the website of the sports nutrition company "H+ Sport".
The website provides an opportunity for guests to get information about the
company and its products. Guests also can register or login as
customer/manager/administrator. Customers can make an order, which can be
approved or rejected by a manager of the company. The administrator maintains
the website's catalog and user's accounts.

## Actors

### Guest

Anu unauthorized visitor of the website considered as a guest and can visit
pages with general information about the company, search at products catalog,
add products to the personal cart, and register (log in if already has an
account).

* Allowed actions:
    * Visit the main page
    * Login
    * Register
    * Browse catalog
    * Search in catalog
    * Add a product to the cart
    * View cart
    * Change website language

### Customer

An authorized user with minimal privileges - a customer. Customer can do
everything, that is allowed to the guest, has access to all products in the cart
that were added before login, make an order from his cart, view personal order's
history, remove order (if still not approved), and view personal profile
information.

* Allowed actions:
    * Make an order
    * Remove a previously made order
    * View personal orders history
    * View profile information
    * Visit the main page
    * Login
    * Register
    * Browse catalog
    * Search in catalog
    * Add a product to the cart
    * View cart
    * Change website language

### Manager

The manager is an authorized user - an H+ Sport employee. He is responsible for
order management and can view a list of all orders of users, can approve, reject
and delete orders. Also has all privileges of a customer and a guest.

* Allowed actions:
    * View list of orders of all users
    * Reject or approve pending orders
    * Remove rejected orders
    * Make an order
    * Remove a previously made order
    * View personal orders history
    * View profile information
    * Visit the main page
    * Login
    * Register
    * Browse catalog
    * Search in catalog
    * Add a product to the cart
    * View cart
    * Change website language

### Administrator

Administrator - is an authorized user responsible for website administration.
Can view list of all users, set user's privileges level (customer/manager),
delete or create user's accounts. Can't reach out user's password (stored
encrypted). Can view a list of all products in the catalog and edit them, as
well as add new products, upload images, delete products from the catalog. Has
all privileges of a guest and a customer, but don't have privileges of a
manager (but can register a new user with a manager's role).

* Allowed actions:
    * View list of all user's accounts
    * Change privileges for existing user's accounts
    * Delete users
    * Create new users
    * View list of all products in the catalog
    * Edit existing products
    * Upload images
    * Delete products from the catalog
    * Add new products
    * Make an order
    * Remove a previously made order
    * View personal orders history
    * View profile information
    * Visit the main page
    * Login
    * Register
    * Browse catalog
    * Search in catalog
    * Add a product to the cart
    * View cart
    * Change website language
  