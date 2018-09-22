use hh_db;

#users


INSERT INTO users (first_name, last_name, is_admin,is_org, password, phn_num, username, email, zipcode) Values('tj', 'english', TRUE, FALSE, 'codeup1', '2101111111', 'tjenglish', 'tjenglish@codeup.com', '78233');
INSERT INTO users (first_name, last_name, is_admin,is_org, password, phn_num, username, email, zipcode) Values('vince', 'lewis', TRUE, FALSE, 'codeup1', '2101111111', 'vlewis', 'vlewis@codeup.com', '78205');
INSERT INTO users (first_name, last_name, is_admin,is_org, password, phn_num, username, email, zipcode) Values('mykel', 'kovar', FALSE, TRUE, 'codeup1', '2101111111', 'mkovar', 'mkovar@codeup.com', '78111');
INSERT INTO users (first_name, last_name, is_admin,is_org, password, phn_num, username, email, zipcode) Values('lisa', 'rodriguez', FALSE, FALSE, 'codeup1', '2101111111', 'lrod', 'lrod@codeup.com', '78240');
INSERT INTO users (first_name, last_name, is_admin,is_org, password, phn_num, username, email, zipcode) Values('sponge', 'bob', FALSE, FALSE, 'codeup1', '222222222', 'sbob', 'sbob@bbottom.com', '58576');
INSERT INTO users (first_name, last_name, is_admin,is_org, password, phn_num, username, email, zipcode) Values('squid', 'ward', FALSE, FALSE, 'codeup1', '222222222', 'sward', 'sward@bbottom.com', '58576');
INSERT INTO users (first_name, last_name, is_admin,is_org, password, phn_num, username, email, zipcode) Values('patrick', 'starfish', FALSE, FALSE, 'codeup1', '222222222', 'pstarfish', 'pstarfish@bbottom.com', '58576');
INSERT INTO users (first_name, last_name, is_admin,is_org, password, phn_num, username, email, zipcode) Values('sandy', 'squirrel', FALSE, FALSE, 'codeup1', '222222222', 'ssquirrel', 'ssquirrel@bbottom.com', '58576');
INSERT INTO users (first_name, last_name, is_admin,is_org, password, phn_num, username, email, zipcode) Values('mr', 'crabs', FALSE, FALSE, 'codeup1', '222222222', 'mcrabs', 'mrcrabs@bbbottom.com', '58576');


#organizations


INSERT INTO organizations(city,is_validated,org_name,state,str_addr,tax_id,user_id) values('San Antonio',TRUE,'Codeup','TX','600 Navarro','12-3456757','1');
INSERT INTO organizations(city,is_validated,org_name,state,str_addr,tax_id,user_id) values('Dallas',TRUE,'ClearStream','TX','123 Hello','12-3456758','2');
INSERT INTO organizations(city,is_validated,org_name,state,str_addr,tax_id,user_id) values('Houston',TRUE,'Petra Oil','TX','34 Pump','12-3456759','3');
INSERT INTO organizations(city,is_validated,org_name,state,str_addr,tax_id,user_id) values('New Braunfels',TRUE,'Family T','TX','23 San Antonio St','12-3456760','4');
INSERT INTO organizations(city,is_validated,org_name,state,str_addr,tax_id,user_id) values('San Antonio',TRUE,'Creek Solutions','TX','987 Houston St','12-3456761','5');
INSERT INTO organizations(city,is_validated,org_name,state,str_addr,tax_id,user_id) values('Oklahoma City',TRUE,'Brock and Lee','OK','890 Veggies Way','12-3456762','6');
INSERT INTO organizations(city,is_validated,org_name,state,str_addr,tax_id,user_id) values('Phoenix',TRUE,'Panel Time','AZ','56 Sun Alley','12-3456763','7');
INSERT INTO organizations(city,is_validated,org_name,state,str_addr,tax_id,user_id) values('New Braunfels',TRUE,'Sole Pacer','NB','22 One Lace','12-3456764','8');

#events


INSERT INTO events(id,description,str_addr,zip_code,organization) values('2','Dev Day','1 UTSA blvd','78261','1');
INSERT INTO events(id,description,str_addr,zip_code,organization) values('6','Family Day','7181 san jose','56789','1');
INSERT INTO events(id,description,str_addr,zip_code,organization) values('7','Park View','1 Peace Out','32145','1');
INSERT INTO events(id,description,str_addr,zip_code,organization) values('8','Thanskgiving','534 Feast','19191','4');
INSERT INTO events(id,description,str_addr,zip_code,organization) values('4','Oct Run','210 Kicks','78209','2');
INSERT INTO events(id,description,str_addr,zip_code,organization) values('5','Front End X','34 UI Way','78245','2');
INSERT INTO events(id,description,str_addr,zip_code,organization) values('9','Daily Kitchen','78 Laddle','98765','3');
INSERT INTO events(id,description,str_addr,zip_code,organization) values('3','Solar Day','456 Reflection','90178','5');
INSERT INTO events(id,description,str_addr,zip_code,organization) values('10','Landa Run','23 San Antonio Street','78130','1');

#categories

INSERT INTO categories (id,category,user_id) values('10','run event', '1');
INSERT INTO categories (id,category,user_id) values('11','construction', '2');
INSERT INTO categories (id,category,user_id) values('12','clean up', '3');
INSERT INTO categories (id,category,user_id) values('13','server', '4');
INSERT INTO categories (id,category,user_id) values('14','set up', '1');
INSERT INTO categories (id,category,user_id) values('15','stocking', '2');
INSERT INTO categories (id,category,user_id) values('16','first aid', '5');
INSERT INTO categories (id,category,user_id) values('17','education', '6');

#feedback_from_orgs

# INSERT INTO feedback_from_orgs (id,date_created,did_attend,feedback,flags,rating,event_id,org_id) values('1','43363',TRUE,'Hard worker','reliable','5','2','2');
# INSERT INTO feedback_from_orgs (id,date_created,did_attend,feedback,flags,rating,event_id,org_id) values('2','43364',TRUE,'Very helpful','reliable','5','6','6');
# INSERT INTO feedback_from_orgs (id,date_created,did_attend,feedback,flags,rating,event_id,org_id) values('3','43365',TRUE,'Hard worker','reliable','5','7','7');
# INSERT INTO feedback_from_orgs (id,date_created,did_attend,feedback,flags,rating,event_id,org_id) values('4','43366',TRUE,'Very helpful','reliable','5','8','4');
# INSERT INTO feedback_from_orgs (id,date_created,did_attend,feedback,flags,rating,event_id,org_id) values('5','43367',FALSE,'No show','Not reliable','0','4','5');
# INSERT INTO feedback_from_orgs (id,date_created,did_attend,feedback,flags,rating,event_id,org_id) values('5','43368',FALSE,'No show','Not reliable','0','5','9');
# INSERT INTO feedback_from_orgs (id,date_created,did_attend,feedback,flags,rating,event_id,org_id) values('6','43369',TRUE,'Enthusiastic','reliable','5','9','3');
# INSERT INTO feedback_from_orgs (id,date_created,did_attend,feedback,flags,rating,event_id,org_id) values('7','43370',TRUE,'Hard worker','reliable','5','3','10');





#messages

INSERT INTO messages (id, message, receiver_id, sender_id) Values('20', 'This was a great event.', '2', '1');
INSERT INTO messages (id, message, receiver_id, sender_id) Values('21', 'I want to get more involved.', '6', '2');
INSERT INTO messages (id, message, receiver_id, sender_id) Values('22', 'I love to give back to the community.', '7', '3');
INSERT INTO messages (id, message, receiver_id, sender_id) Values('23', 'I cannot wait till the next event.', '4', '4');
INSERT INTO messages (id, message, receiver_id, sender_id) Values('24', 'This was a great event.', '5', '5');


#reports

INSERT INTO reports (id, message, reasons, reporter_id) Values('30', 'Volunteer needed help.', 'Needed proper instruction', '5');
INSERT INTO reports (id, message, reasons, reporter_id) Values('31', 'Volunteer xyz was late.', 'Too far.', '6');
INSERT INTO reports (id, message, reasons, reporter_id) Values('32', 'Volunteer needed help.', 'Needed proper instruction', '7');
INSERT INTO reports (id, message, reasons, reporter_id) Values('33', 'Volunteer xyz was late.', 'Not sure where it was located.', '8');
INSERT INTO reports (id, message, reasons, reporter_id) Values('34', 'Volunteer xyz was late.', 'Too far.', '9');


#volunteers

INSERT INTO volunteers (bio, is_suspended, user_id) Values('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut tempus et ante eu porttitor. Fusce a congue nunc. Pellentesque faucibus rhoncus lectus, eget maximus quam pharetra sed.', FALSE, '1');
INSERT INTO volunteers (bio, is_suspended, user_id) Values('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut tempus et ante eu porttitor. Fusce a congue nunc. Pellentesque faucibus rhoncus lectus, eget maximus quam pharetra sed.', FALSE, '2');
INSERT INTO volunteers (bio, is_suspended, user_id) Values('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut tempus et ante eu porttitor. Fusce a congue nunc. Pellentesque faucibus rhoncus lectus, eget maximus quam pharetra sed.', FALSE, '3');
INSERT INTO volunteers (bio, is_suspended, user_id) Values('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut tempus et ante eu porttitor. Fusce a congue nunc. Pellentesque faucibus rhoncus lectus, eget maximus quam pharetra sed.', FALSE, '4');
INSERT INTO volunteers (bio, is_suspended, user_id) Values('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut tempus et ante eu porttitor. Fusce a congue nunc. Pellentesque faucibus rhoncus lectus, eget maximus quam pharetra sed.', FALSE, '5');


#feedback_from_vols

INSERT INTO feedback_from_vols (feedback, rating, org_id, vol_id) Values('great event', '5', '2', '1');
INSERT INTO feedback_from_vols (feedback, rating, org_id, vol_id) Values('great event', '5', '6', '2');
INSERT INTO feedback_from_vols (feedback, rating, org_id, vol_id) Values('needed staff', '3', '7', '3');
INSERT INTO feedback_from_vols (feedback, rating, org_id, vol_id) Values('great event', '4', '4', '4');
INSERT INTO feedback_from_vols (feedback, rating, org_id, vol_id) Values('needed staff', '3', '5', '5');


