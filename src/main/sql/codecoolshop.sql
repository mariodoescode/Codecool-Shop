DROP TABLE IF EXISTS public.product;
CREATE TABLE public.product (
                                 id serial NOT NULL PRIMARY KEY ,
                                 name text NOT NULL,
                                 description text NOT NULL,
                                 price bigint NOT NULL,
                                 currency text NOT NULL,
                                 quantity integer NOT NULL,
                                 category_id integer NOT NULL ,
                                 supplier_id integer NOT NULL
);


DROP TABLE IF EXISTS public.category;
CREATE TABLE public.category (
                        id serial NOT NULL PRIMARY KEY ,
                        name text NOT NULL ,
                        description text NOT NULL ,
                        department text NOT NULL
);


DROP TABLE IF EXISTS public.supplier;
CREATE TABLE public.supplier (
                       id serial NOT NULL PRIMARY KEY ,
                       name text NOT NULL ,
                       description text NOT NULL
);



DROP TABLE IF EXISTS public.user;
CREATE TABLE public.user (
                    id serial NOT NULL PRIMARY KEY ,
                    email text NOT NULL ,
                    password text NOT NULL ,
                    name text ,
                    phone_number text ,
                    billingCountry text ,
                    billingCity text ,
                    billingZip text ,
                    billingAddress text ,
                    shippingCountry text ,
                    shippingCity text ,
                    shippingZip text ,
                    shippingAddress text,
                    shoppingCart_id int NOT NULL
);


DROP TABLE IF EXISTS public.order;
CREATE TABLE public.order (
                          id serial NOT NULL PRIMARY KEY,
                          status text NOT NULL,
                          date timestamp NOT NULL,
                          user_id int NOT NULL
);


DROP TABLE IF EXISTS public.shopping_cart;
CREATE TABLE public.shopping_cart (
                        id serial NOT NULL PRIMARY KEY ,
                        user_id int NOT NULL ,
                        product_id int NOT NULL
);

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES public.category(id);
ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES public.supplier(id);
ALTER TABLE ONLY public.user
    ADD CONSTRAINT fk_shoppingCart_id FOREIGN KEY (shoppingCart_id) REFERENCES public.shopping_cart(id);
ALTER TABLE ONLY public.order
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.user(id);