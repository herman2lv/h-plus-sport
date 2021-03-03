# Site of the sport nutrition company "H+ Sport"

### Author: Herman Rabinkin

## Description

Project represents website of the sport nutrition company "H+ Sport". Website
provide opportunity for guests to get information about company and its
products. Guest also can register or login as customer/manager/administrator.
Customer can make an order, which can be approved or rejected by a manager of
the company. Administrator maintains website's catalog and user's accounts.

## Actors

### Guest

Anu unauthorized visitor of the website considered as a guest and can visit
pages with general information about company, search at products catalog, add
products to the personal cart, and register (login if already has account).

* Allowed actions:
    * Visit main page
    * Login
    * Register
    * Browse catalog
    * Search in catalog
    * Add a product to cart
    * View cart
    * Change website language

### Customer

An authorized user with minimal privileges - a customer. Customer can do
everything, that is allowed to guest, has access to all product in the cart that
were added before login, make an order from his cart, view personal order's
history, remove order (if still not approved) and view personal profile
information.

* Allowed actions:
    * Make an order
    * Remove a previously made order
    * View personal orders history
    * View profile information
    * Visit main page
    * Login
    * Register
    * Browse catalog
    * Search in catalog
    * Add a product to cart
    * View cart
    * Change website language

### Manager

Manager is an authorized user - H+ Sport employee. He is responsible for orders
management and can view list of all orders of users, can approve, reject and
delete order. Also has all privileges of customer and guest.

* Allowed actions:
    * View list of orders of all users
    * Reject or approve pending orders
    * Remove rejected orders
    * Make an order
    * Remove a previously made order
    * View personal orders history
    * View profile information
    * Visit main page
    * Login
    * Register
    * Browse catalog
    * Search in catalog
    * Add a product to cart
    * View cart
    * Change website language

### Administrator

Administrator - is an authorized user responsible for website administration.
Can view list of all users, set user's privileges level (customer/manager),
delete or create user's accounts. Can't reach out user's password (stored
encrypted). Can view list of all products in catalog and edit them, as well as
add new products, upload images, delete products from the catalog. Has all
privileges of guest and customer, but don't have privileges of manager (but can
register new user with a manager's role).

* Allowed actions:
    * View list of all user's accounts
    * Change privileges for existing user's accounts
    * Delete users
    * Create new users
    * View list of all products in catalog
    * Edit existing products
    * Upload images
    * Delete products from the catalog
    * Add new products
    * Make an order
    * Remove a previously made order
    * View personal orders history
    * View profile information
    * Visit main page
    * Login
    * Register
    * Browse catalog
    * Search in catalog
    * Add a product to cart
    * View cart
    * Change website language
  