-- Insert sample data into USERS table
INSERT INTO USERS (ID, USERNAME, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD)
VALUES (1, 'user1', 'John', 'Doe', 'john.doe@example.com', 'password123');

-- Insert sample data into ADDRESSES table
INSERT INTO ADDRESSES (ID, COUNTRY, STREET, CITY, POSTAL_CODE, USER_ID)
VALUES (1, 'Country A', 'Street A', 'City A', '12345', 1);

-- Insert sample data into ORDERS table
INSERT INTO ORDERS (ID, QUANTITY, USER_ID)
VALUES (1, 5.0, 1);

-- Insert sample data into PRODUCTS table
INSERT INTO PRODUCTS (ID, NAME, PRICE)
VALUES (1, 'Product A', 9.99);

-- Insert sample data into ORDER_PRODUCT table (join table for many-to-many relationship)
INSERT INTO ORDER_PRODUCT (ORDER_ID, PRODUCT_ID)
VALUES (1, 1);


