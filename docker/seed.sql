-- Users
INSERT INTO users (id, email, username, password, cpf, role) VALUES
(1, 'admin@example.com', 'admin', 'admin123', '11122233344', 'ADMIN'),
(2, 'user1@example.com', 'userone', 'user123', '55566677788', 'USER'),
(3, 'user2@example.com', 'usertwo', 'user456', '99988877766', 'USER');

-- Categories
INSERT INTO categories (id, name) VALUES
(1, 'Cimento e Argamassa'),
(2, 'Tijolos e Blocos'),
(3, 'Ferramentas'),
(4, 'Tintas e Acessórios'),
(5, 'Elétrica'),
(6, 'Hidráulica');

-- Products
INSERT INTO products (id, name, description, price, category_id, created_at, updated_at) VALUES
(1, 'Cimento Votoran Todas as Obras 50kg', 'Cimento de alta qualidade para todos os tipos de obra.', 28.90, 1, NOW(), NOW()),
(2, 'Argamassa Colante ACIII Quartzolit 20kg', 'Ideal para assentamento de porcelanatos em áreas internas e externas.', 35.50, 1, NOW(), NOW()),
(3, 'Tijolo Baiano 9 Furos (Milheiro)', 'Tijolo cerâmico de alta resistência para alvenaria.', 850.00, 2, NOW(), NOW()),
(4, 'Furadeira de Impacto Bosch GSB 13 RE', 'Furadeira de 650W com velocidade variável e reversível.', 399.90, 3, NOW(), NOW()),
(5, 'Tinta Acrílica Fosca Branca Suvinil 18L', 'Tinta de alta cobertura e rendimento para paredes.', 320.00, 4, NOW(), NOW()),
(6, 'Disjuntor Monopolar 20A Siemens', 'Disjuntor para proteção de circuitos elétricos.', 12.50, 5, NOW(), NOW()),
(7, 'Cano PVC Esgoto 100mm (Barra 6m)', 'Tubo de PVC para sistemas de esgoto predial.', 89.90, 6, NOW(), NOW());

-- Addresses
INSERT INTO addresses (id, street, city, state, zip_code, user_id) VALUES
(1, 'Rua das Flores, 123', 'São Paulo', 'SP', '01000-000', 2),
(2, 'Avenida Principal, 456', 'Rio de Janeiro', 'RJ', '20000-000', 3);

-- Carts
-- One cart for each user
INSERT INTO carts (id, user_id, status) VALUES
(1, 2, 'ABERTO'),
(2, 3, 'ABERTO');

-- Cart Items
-- Adding some products to the carts
INSERT INTO cart_items (id, cart_id, product_id, quantity) VALUES
(1, 1, 1, 10), -- User 1, Cart 1: 10 sacos de cimento
(2, 1, 4, 1),  -- User 1, Cart 1: 1 furadeira
(3, 2, 5, 2);  -- User 2, Cart 2: 2 latas de tinta

-- Orders
-- Creating one sample order from a user's cart
INSERT INTO orders (id, user_id, total_price, status, order_date) VALUES
(1, 2, 327.90, 'PAGAMENTO_PENDENTE', NOW());

-- Order Items
-- Items for the sample order
INSERT INTO order_items (id, order_id, product_id, quantity, price) VALUES
(1, 1, 1, 10, 28.90), -- 10 x Cimento
(2, 1, 6, 2, 12.50);  -- 2 x Disjuntor (example)

-- Product Images (Optional, if you have the table)
-- INSERT INTO product_images (id, product_id, image_url) VALUES
-- (1, 1, 'https://example.com/images/cimento.jpg'),
-- (2, 4, 'https://example.com/images/furadeira.jpg');

