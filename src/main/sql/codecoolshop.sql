DROP TABLE IF EXISTS public.product CASCADE;
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


DROP TABLE IF EXISTS public.category CASCADE;
CREATE TABLE public.category (
                        id serial NOT NULL PRIMARY KEY ,
                        name text NOT NULL ,
                        description text NOT NULL ,
                        department text NOT NULL
);


DROP TABLE IF EXISTS public.supplier CASCADE;
CREATE TABLE public.supplier (
                       id serial NOT NULL PRIMARY KEY ,
                       name text NOT NULL ,
                       description text NOT NULL
);



DROP TABLE IF EXISTS public.users CASCADE ;
CREATE TABLE public.users (
                    id serial NOT NULL PRIMARY KEY ,
                    email text NOT NULL ,
                    password text NOT NULL ,
                    name text ,
                    phone_number text ,
                    billing_country text ,
                    billing_city text ,
                    billing_zip text ,
                    billing_address text ,
                    shipping_country text ,
                    shipping_city text ,
                    shipping_zip text ,
                    shipping_address text,
                    shoppingcart_id int
);


DROP TABLE IF EXISTS public.order CASCADE;
CREATE TABLE public.order (
                          id serial NOT NULL PRIMARY KEY,
                          status text NOT NULL,
                          date timestamp NOT NULL,
                          user_id int NOT NULL
);


DROP TABLE IF EXISTS public.shopping_cart CASCADE;
CREATE TABLE public.shopping_cart (
                        id serial NOT NULL PRIMARY KEY ,
                        user_id int NOT NULL ,
                        product_id int
);

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES public.category(id);
ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES public.supplier(id);
ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_shoppingCart_id FOREIGN KEY (shoppingCart_id) REFERENCES public.shopping_cart(id);
ALTER TABLE ONLY public.order
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES public.users(id);