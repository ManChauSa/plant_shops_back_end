--  INSER USER
INSERT INTO "user" (user_name, password, email, phone, first_name, last_name, role)
VALUES ('admin', 'password123', 'admin@example.com', '1234567890', 'admin', 'admin', 'ROLE_ADMIN');
INSERT INTO "user" (user_name, password, email, phone, first_name, last_name, role) VALUES ('seller', 'password123', 'seller@example.com', '1234567890', 'John', 'Doe', 'ROLE_SELLER');
INSERT INTO "user" (user_name, password, email, phone, first_name, last_name, role) VALUES ('buyer', 'password123', 'buyer@example.com', '1245677890', 'Anna', 'Jade', 'ROLE_BUYER');

-- -- insert category
INSERT INTO Category (category_name, category_desc, parent_id) VALUES ('Outdoor Plant', 'Plant live in outdoor', NULL);
INSERT INTO Category (category_name, category_desc, parent_id) VALUES ('Indoor Plant', 'Plant live in indoor', NULL);
INSERT INTO Category (category_name, category_desc, parent_id) VALUES ('Office Plant', 'Office decoration plants', NULL);
INSERT INTO Category (category_name, category_desc, parent_id)  VALUES ('Potted', 'Pots', NULL);
--
-- -- insert seller
INSERT INTO Seller (seller_name, seller_description, user_id, is_verified)
VALUES ('Garden Paradise', 'Selling plants', 2, true);
--
-- -- -- insert product

INSERT INTO Product (product_name, description, price, seller_id, inventory_count, status, category_id, product_type, created_date)
VALUES ('Cactus Flower', 'Cactus Flower desc', 10.99, 1, 50, 'AVAILABLE', 1, 1, '2024-06-01');
