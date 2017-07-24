create sequence if not exists hibernate_sequence;

create table if not exists pet(
 id serial primary key,
 name varchar(100) UNIQUE,
 description varchar(100),
 price decimal,
 quantity int
);

insert into pet
 (name, description, price, quantity)
 VALUES 
 ('Black Lab', 'Black Lab', 2000, 5),
 ('White Cat', 'White Cat', 700, 10),
 ('Tropical Fish', 'Tropical Fish', 3.25,80);

