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

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES public.category(id);
ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES public.supplier(id);
