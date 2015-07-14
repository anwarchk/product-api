insert into CATEGORY(id,name) values(1, 'baby');

insert into CATEGORY(id,name) values(2, 'toys');


insert into  PRODUCT(id,sku,name,category_id,LAST_UPDATED) values(1,'AEX143','Stroller',1,CURRENT_TIMESTAMP);
insert into  PRODUCT(id,sku,name,category_id,LAST_UPDATED) values(2,'IOL123','Optimus Prime',2,CURRENT_TIMESTAMP);
insert into  PRODUCT(id,sku,name,category_id,LAST_UPDATED) values(3,'XYZ904','Sega Genesis',2,CURRENT_TIMESTAMP);

insert into PRODUCT_PRICE(ID,PRODUCT_ID,PRICE,PRICE_TYPE,CURRENCY_CODE) VALUES (1,1,199.99,'REGULAR','USD');
insert into PRODUCT_PRICE(ID,PRODUCT_ID,PRICE,PRICE_TYPE,CURRENCY_CODE) VALUES (2,2,13.3766,'REGULAR','USD');
insert into PRODUCT_PRICE(ID,PRODUCT_ID,PRICE,PRICE_TYPE,CURRENCY_CODE) VALUES (3,3,149.99,'REGULAR','USD');
insert into PRODUCT_PRICE(ID,PRODUCT_ID,PRICE,PRICE_TYPE,CURRENCY_CODE) VALUES (4,1,189.99,'SALE','USD');

