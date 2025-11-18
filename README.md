## Database Configuration

### 1. Create the Database Configuration File
Inside the root of the `src/` folder, create a file named:
<pre> db.properties  </pre>

### 2. Add the Following Contents to `db.properties`

```properties
db.url=jdbc:mysql://localhost:3306/ticketing_system
db.user=<your mysql user>
db.password=<your mysql password>
```
### 3. Make Sure the Database Exists
Before running the project, ensure that the MySQL database is created:
```sql 
CREATE DATABASE ticketing_system;
```
