CREATE TABLE IF NOT EXISTS pet_table (
    id serial NOT NULL PRIMARY KEY,
    food_type text NOT NULL,
    name text NOT NULL,
    brand text NOT NULL,
    expiration date NOT NULL
);

CREATE TABLE IF NOT EXISTS stock (
    id serial NOT NULL PRIMARY KEY,
    food_id integer NOT NULL,
    quantity integer NOT NULL,
    price integer NOT NULL,
    CONSTRAINT fk_food_id
        FOREIGN KEY(food_id)
        REFERENCES public.pet_table(id)
        ON DELETE CASCADE
);
