drop table if exists lego CASCADE; 
create table lego 
(
id integer PRIMARY KEY AUTO_INCREMENT,
kit_name varchar(255), 
kit_number integer not null, 
release_year integer not null, 
series_name varchar(255) 
);