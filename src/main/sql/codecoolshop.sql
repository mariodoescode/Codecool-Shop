DROP TABLE IF EXISTS public.products;
CREATE TABLE public.products (
                                 id serial NOT NULL PRIMARY KEY ,
                                 name text NOT NULL,
                                 description text NOT NULL,
                                 price bigint NOT NULL,
                                 currency text NOT NULL,
                                 quantity integer NOT NULL,
                                 category_id integer NOT NULL ,
                                 supplier_id integer NOT NULL
);


DROP TABLE IF EXISTS public.categories;
CREATE TABLE public.categories (
                        id serial NOT NULL PRIMARY KEY ,
                        name text NOT NULL ,
                        description text NOT NULL ,
                        department text NOT NULL
);


DROP TABLE IF EXISTS public.suppliers;
CREATE TABLE public.suppliers (
                       id serial NOT NULL PRIMARY KEY ,
                       name text NOT NULL ,
                       description text NOT NULL
);

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES public.categories(id);
ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES public.suppliers(id);
