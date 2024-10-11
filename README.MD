# How to start the project

### 1. Clone the repository

### 2. I used xampp for the database, so you need to create a database named `file_upload` inside you create 2 tables.
- `file` table
```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    roles VARCHAR(255), -- You can store roles as a comma-separated string or create a separate roles table
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
- `file` table
```sql
CREATE TABLE files (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    file_name VARCHAR(255) NOT NULL,
    file_type VARCHAR(50), -- E.g., 'image/png', 'application/pdf', etc.
    file_size BIGINT, -- File size in bytes
    file_path VARCHAR(255) NOT NULL, -- The path where the file is stored (if stored locally)
    upload_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT, -- Foreign key to associate the file with a user (if needed)
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
```
### 3. See the `application.properties` file and change the database username and password according to your xampp settings.

### 4. Run the project using the following command:
```shell
mvn spring-boot:run
```
### 4.1 Run second instance using the following command:
```shell
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```
### 5. You can use or postman or curls to access API `http://localhost:8080` and `http://localhost:8081`

### 5.1 Login API
fistly you need to login to get the token
```shell
curl --location --request POST 'http://localhost:8080/api/auth/login?username=user1&password=password1'
```
### 5.2 After getting the token you can use the token to access the other API. In postman set Bearer token in the Authorization header. 
```shell
curl --location 'http://localhost:8080/api/files/upload' \
--header 'Content-Type: multipart/form-data' \
--header 'Authorization: Bearer <your_token>' \
--form 'file=@"<Path_To_File_you_want_to_upload>"'
```

### 5.3 You can use the following API to get the list of files
```shell
curl --location 'http://localhost:8081/api/files/all' \
--header 'Authorization: Bearer <your_token>'

```
## You can also see my TODO to see the things that are done in this project.