# student-management
Swagger UI
http://localhost:8080/swagger-ui/index.html#/

Login Controller

method : post
permit : all
http://localhost:8080/api/users/signup

method : post
permit : all
http://localhost:8080/api/users/signin


Course Controller

method : put
permit : both 'admin' and 'user'
http://localhost:8080/api/course/update

method : post
permit : both 'admin' and 'user'
http://localhost:8080/api/course/register

method : get
permit : only 'user'
http://localhost:8080/api/course/getAllCourse

method : get
permit : only 'user'
http://localhost:8080/api/course/findById

method : delete
permit : both 'admin' and 'user'
http://localhost:8080/api/course/delete


Student Controller

method : put
permit : both 'admin' and 'user'
http://localhost:8080/api/student/update

method : post
permit : both 'admin' and 'user'
http://localhost:8080/api/student/register

method : get
permit : only 'user'
http://localhost:8080/api/student/getAllStudents

method : get
permit : only 'user'
http://localhost:8080/api/student/findById

method : delete
permit : both 'admin' and 'user'
http://localhost:8080/api/student/delete
