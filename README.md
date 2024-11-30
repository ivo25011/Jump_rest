To make run an application is no big deal. The implementation provide that tables are fully made. 
There is just few simple steps to run it. 

1. Make some database in Postgre database (I used pgAdmin 4) and change application properties by your choise specially .url, .username, .password
2. Use next insert query to fill Animal table
   INSERT INTO animal (id, name, age, gender, breed_id) 
   VALUES 
    (1, 'Max', 3, 'MALE', 1),
    (2, 'Bella', 2, 'FEMALE', 2),
    (3, 'Charlie', 5, 'MALE', 3),
    (4, 'Luna', 4, 'FEMALE', 4),
    (5, 'Rocky', 1, 'MALE', 5);
3. Use next insert query to fill Breed table
   INSERT INTO breed (id, name)
   VALUES
    (1, 'Afganský chrt'),
    (2, 'Americká akita'),
    (3, 'Anglický buldog'),
    (4, 'Belgický ovčiak'),
    (5, 'Bradáč');
You can choose to use only step 3 and the Animal table can be filled manually through application. 
