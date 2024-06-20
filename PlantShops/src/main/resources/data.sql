--  INSER USER
INSERT INTO "user" (user_name, password, email, phone, name, role)
VALUES ('admin', '$2a$10$Uqxn13qrL0j6TetMuKcVle.hnEShBeFkFgyWM0ZSuhD1uwKsLuBtS', 'admin@example.com', '1234567890', 'admin', 'ROLE_ADMIN');
INSERT INTO "user" (user_name, password, email, phone, name, role) VALUES ('seller', '$2a$10$Uqxn13qrL0j6TetMuKcVle.hnEShBeFkFgyWM0ZSuhD1uwKsLuBtS', 'seller@example.com', '1234567890', 'John Doe', 'ROLE_SELLER');
INSERT INTO "user" (user_name, password, email, phone, name, role) VALUES ('buyer', '$2a$10$Uqxn13qrL0j6TetMuKcVle.hnEShBeFkFgyWM0ZSuhD1uwKsLuBtS', 'buyer@example.com', '1245677890', 'Anna Jade', 'ROLE_BUYER');

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

-- inser discount
INSERT INTO discount_and_offer
(discount_name, description,user_id, percent, start_date, end_date, created_date, update_date, code,discount_type)
VALUES
    ('Summer Sale', 'Up to 50% off on summer items',2, 15.00, '2024-06-01', '2024-06-30', '2024-06-01', '2024-06-01', 'SUMMER50',0),
    ('Back to School Promo', 'Get discounts on school supplies', 2,20.00, '2024-08-15', '2024-09-15', '2024-08-01', '2024-08-01', 'SCHOOL30',1),
    ('Holiday Special', 'Year-end discounts',2, 30.00, '2024-11-20', '2024-12-31', '2024-11-01', '2024-11-01', 'HOLIDAY40',0);
