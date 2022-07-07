# back-end

# How to set database
1. Open XAMPP (Apache and MySQL start)
2. Create table commsult_database
3. Import the database from the database file that has been prepare in back-end/database folder

# How to start back-end folder
1. Open back-end folder with intelliJ
2. Start the database using XAMPP
3. Setting the database environment according to your database
4. Setting the @CrossOrigins origins value in UserController and ItemController according to your front-end url and port (ex. http://localhost:3000)
5. Run the API with `mvn spring-boot:run`(Tomcat Module in XAMPP should run)
