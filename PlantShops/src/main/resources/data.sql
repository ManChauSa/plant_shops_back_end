--  INSER USER
INSERT INTO "user" (user_name, password, email, phone, name, role)
VALUES ('admin', '$2a$10$Uqxn13qrL0j6TetMuKcVle.hnEShBeFkFgyWM0ZSuhD1uwKsLuBtS', 'admin@example.com', '1234567890', 'admin', 'ROLE_ADMIN');
INSERT INTO "user" (user_name, password, email, phone, name, role) VALUES ('seller', '$2a$10$Uqxn13qrL0j6TetMuKcVle.hnEShBeFkFgyWM0ZSuhD1uwKsLuBtS', 'seller@example.com', '1234567890', 'John Doe', 'ROLE_SELLER');
INSERT INTO "user" (user_name, password, email, phone, name, role) VALUES ('buyer', '$2a$10$Uqxn13qrL0j6TetMuKcVle.hnEShBeFkFgyWM0ZSuhD1uwKsLuBtS', 'buyer@example.com', '1245677890', 'Anna Jade', 'ROLE_BUYER');
INSERT INTO "user" (user_name, password, email, phone, name, role) VALUES ('seller2', '$2a$10$Uqxn13qrL0j6TetMuKcVle.hnEShBeFkFgyWM0ZSuhD1uwKsLuBtS', 'seller2@example.com', '1234567890', 'Tina Nguyen', 'ROLE_SELLER');
--
-- -- insert seller
INSERT INTO Seller (seller_name, seller_description, user_id, is_verified)
VALUES ('Garden Paradise', 'Selling plants', 2, true),
       ('Be Ut Garden', 'Selling flowers', 4, true);
-- -- insert category
INSERT INTO Category (category_name, category_desc, parent_id) VALUES ('Outdoor Plant', 'Plant live in outdoor', NULL);
INSERT INTO Category (category_name, category_desc, parent_id) VALUES ('Indoor Plant', 'Plant live in indoor', NULL);
INSERT INTO Category (category_name, category_desc, parent_id) VALUES ('Office Plant', 'Office decoration plants', NULL);
INSERT INTO Category (category_name, category_desc, parent_id)  VALUES ('Potted', 'Pots', NULL);
--
-- -- -- insert product

INSERT INTO Product (product_name, description, price, seller_id, inventory_count, status, category_id, product_type, created_date)
VALUES
    ('Rose Bush', 'Beautiful blooming rose bush', 25.99, 4, 30, 'AVAILABLE', 1, 1, '2024-06-01'),
    ('Ficus Tree', 'Large indoor ficus tree', 45.99, 2, 20, 'AVAILABLE', 2, 1, '2024-06-02'),
    ('Snake Plant', 'Low maintenance office plant', 19.99, 2, 40, 'AVAILABLE', 3, 1, '2024-06-03'),
    ('Succulent Mix', 'Assorted succulent plants', 15.99, 2, 35, 'AVAILABLE', 4, 1, '2024-06-04'),
    ('Lavender', 'Fragrant lavender plant for outdoors', 12.99, 4, 50, 'AVAILABLE', 1, 1, '2024-06-05'),
    ('Bamboo Palm', 'Tropical indoor bamboo palm', 29.99, 2, 25, 'AVAILABLE', 2, 1, '2024-06-06'),
    ('Aloe Vera', 'Aloe vera plant for office use', 17.99, 2, 45, 'AVAILABLE', 3, 1, '2024-06-07'),
    ('Hanging Fern', 'Fern in a hanging pot', 22.99, 4, 30, 'AVAILABLE', 4, 1, '2024-06-08'),
    ('Bougainvillea', 'Colorful outdoor bougainvillea', 27.99, 4, 20, 'AVAILABLE', 1, 1, '2024-06-09'),
    ('Peace Lily', 'Peace lily for indoor spaces', 21.99, 4, 40, 'AVAILABLE', 2, 1, '2024-06-10'),
    ('ZZ Plant', 'Easy-care office plant', 24.99, 2, 35, 'AVAILABLE', 3, 1, '2024-06-11'),
    ('Terrarium Set', 'Set of small terrarium plants', 32.99, 2, 25, 'AVAILABLE', 4, 1, '2024-06-12'),
    ('Gardenia', 'Outdoor gardenia with fragrant flowers', 20.99, 2, 30, 'AVAILABLE', 1, 1, '2024-06-13'),
    ('Spider Plant', 'Indoor spider plant with variegated leaves', 18.99, 2, 50, 'AVAILABLE', 2, 1, '2024-06-14'),
    ('Philodendron', 'Philodendron for office settings', 23.99, 2, 40, 'AVAILABLE', 3, 1, '2024-06-15');

-- inser discount
INSERT INTO discount_and_offer
(discount_name, description,user_id, percent, start_date, end_date, created_date, update_date, code,discount_type)
VALUES
    ('Summer Sale', 'Up to 50% off on summer items',2, 15.00, '2024-06-01', '2024-06-30', '2024-06-01', '2024-06-01', 'SUMMER50',0),
    ('Back to School Promo', 'Get discounts on school supplies', 2,20.00, '2024-08-15', '2024-09-15', '2024-08-01', '2024-08-01', 'SCHOOL30',1),
    ('Holiday Special', 'Year-end discounts',2, 30.00, '2024-11-20', '2024-12-31', '2024-11-01', '2024-11-01', 'HOLIDAY40',0);
