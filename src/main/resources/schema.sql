CREATE TABLE if NOT EXISTS product
(
    id
    SERIAL
    auto_increment
    PRIMARY
    KEY,
    product_name
    VARCHAR
(
    255
),
    product_type VARCHAR
(
    255
),
    price DECIMAL
(
    10,
    2
) NOT NULL,
    quantity INT NOT NULL
    );

INSERT INTO product(product_name, product_type, price, quantity)
VALUES ('Spite', 'Beverage', 10.00, 10),
       ('Spite1', 'Beverage', 10.00, 100),
       ('Spite2', 'Beverage', 10.00, 200),
       ('Spite3', 'Beverage', 10.00, 300),
       ('Spite4', 'Beverage', 10.00, 400),
       ('Spite5', 'Beverage', 10.00, 500);
