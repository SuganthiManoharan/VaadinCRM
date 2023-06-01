INSERT INTO STATUS (ID,VERSION,name)
SELECT 7,1,'Lead2'
WHERE NOT EXISTS (SELECT * FROM STATUS WHERE name='Lead2');


INSERT INTO `users` (userid,username,password,role,first_name,last_name,email)
SELECT 10,'admin',
'$2a$10$MwGZcyxNg0LVvWlPjRD10OXWS3axwvaYdB4Bf9v38aWuURQy1yh0O',
'ADMIN','firstname','lname','admin@gmail.com' WHERE NOT EXISTS (SELECT * FROM users WHERE userid=10);


INSERT INTO `users` (userid,username,password,role,first_name,last_name,email)
SELECT 11,'administrator',
'$2a$10$MwGZcyxNg0LVvWlPjRD10OXWS3axwvaYdB4Bf9v38aWuURQy1yh0O',
'ADMIN','firstname','lname','admin@gmail.com' WHERE NOT EXISTS (SELECT * FROM users WHERE userid=11);