use hh_db;

INSERT INTO users (first_name,last_name,is_admin,is_org,password,phn_num,username,zipcode) values('tj','english',TRUE,FALSE,'codeup1','2101111111','tjenglish','78233');
INSERT INTO users (first_name,last_name,is_admin,is_org,password,phn_num,username,zipcode) values('vince','lewis',TRUE,FALSE,'codeup1','2101111111','vlewis','78205');
INSERT INTO users (first_name,last_name,is_admin,is_org,password,phn_num,username,zipcode) values('mykel','kovar',FALSE,TRUE,'codeup1','2101111111','mkovar','78111');
INSERT INTO users (first_name,last_name,is_admin,is_org,password,phn_num,username,zipcode) values('lisa','rodriguez',FALSE,FALSE,'codeup1','2101111111','lrod','78240');
INSERT INTO users (first_name,last_name,is_admin,is_org,password,phn_num,username,zipcode) values('sponge','bob',FALSE,FALSE,'codeup1','222222222','sbob','58576');
INSERT INTO users (first_name,last_name,is_admin,is_org,password,phn_num,username,zipcode) values('patrick','starfish',FALSE,FALSE,'codeup1','222222222','pstarfish','58576');
INSERT INTO users (first_name,last_name,is_admin,is_org,password,phn_num,username,zipcode) values('sandy','squirrel',FALSE,FALSE,'codeup1','222222222','ssquirrel','58576');
INSERT INTO users (first_name,last_name,is_admin,is_org,password,phn_num,username,zipcode) values('mr','crabs',FALSE,FALSE,'codeup1','222222222','mcrabs','58576');

INSERT INTO organizations(city,is_validated,org_name,state,str_addr,tax_id,user_id) values('San Antonio',TRUE,'Codeup','TX','600 Navarro','12-3456757','1');
INSERT INTO organizations(city,is_validated,org_name,state,str_addr,tax_id,user_id) values('Dallas',TRUE,'ClearStream','TX','123 Hello','12-3456758','2');
INSERT INTO organizations(city,is_validated,org_name,state,str_addr,tax_id,user_id) values('Houston',TRUE,'Petra Oil','TX','34 Pump','12-3456759','3');
INSERT INTO organizations(city,is_validated,org_name,state,str_addr,tax_id,user_id) values('New Braunfels',TRUE,'Family T','TX','23 San Antonio St','12-3456760','4');
INSERT INTO organizations(city,is_validated,org_name,state,str_addr,tax_id,user_id) values('San Antonio',TRUE,'Creek Solutions','TX','987 Houston St','12-3456761','5');
INSERT INTO organizations(city,is_validated,org_name,state,str_addr,tax_id,user_id) values('Oklahoma City',TRUE,'Brock and Lee','OK','890 Veggies Way','12-3456762','6');
INSERT INTO organizations(city,is_validated,org_name,state,str_addr,tax_id,user_id) values('Phoenix',TRUE,'Panel Time','AZ','56 Sun Alley','12-3456763','7');
INSERT INTO organizations(city,is_validated,org_name,state,str_addr,tax_id,user_id) values('New Braunfels',TRUE,'Sole Pacer','NB','22 One Lace','12-3456764','8');


INSERT INTO events(id,description,str_addr,zip_code,organization) values('2','Dev Day','1 UTSA blvd','78261','1');
INSERT INTO events(id,description,str_addr,zip_code,organization) values('6','Family Day','7181 san jose','56789','1');
INSERT INTO events(id,description,str_addr,zip_code,organization) values('7','Park View','1 Peace Out','32145','1');
INSERT INTO events(id,description,str_addr,zip_code,organization) values('8','Thanskgiving','534 Feast','19191','4');
INSERT INTO events(id,description,str_addr,zip_code,organization) values('4','Oct Run','210 Kicks','78209','2');
INSERT INTO events(id,description,str_addr,zip_code,organization) values('5','Front End X','34 UI Way','78245','2');
INSERT INTO events(id,description,str_addr,zip_code,organization) values('9','Daily Kitchen','78 Laddle','98765','3');
INSERT INTO events(id,description,str_addr,zip_code,organization) values('3','Solar Day','456 Reflection','90178','5');
INSERT INTO events(id,description,str_addr,zip_code,organization) values('10','Landa Run','23 San Antonio Street','78130','1');