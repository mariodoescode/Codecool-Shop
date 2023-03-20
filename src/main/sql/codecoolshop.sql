CREATE TABLE products (
                        id serial NOT NULL PRIMARY KEY ,
                        name text NOT NULL,
                        description text NOT NULL,
                        price bigint NOT NULL,
                        currency text NOT NULL,
                        quantity integer NOT NULL,
                        category_id integer NOT NULL ,
                        supplier_id integer NOT NULL
);

CREATE TABLE categories (
                        id serial NOT NULL PRIMARY KEY ,
                        name text NOT NULL ,
                        description text NOT NULL ,
                        department text NOT NULL
);

CREATE TABLE suppliers (
                       id serial NOT NULL PRIMARY KEY ,
                       name text NOT NULL ,
                       description text NOT NULL
);
