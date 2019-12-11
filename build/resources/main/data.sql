insert into AIRPORTS (select * from CSVREAD('src\main\resources\airports.dat'));

insert into ROUTES (select * from CSVREAD('src\main\resources\routes.dat'));

insert into AIRLINES(select * from CSVREAD('src\main\resources\airlines.dat'));