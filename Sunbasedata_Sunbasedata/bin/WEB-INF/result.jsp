<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8080"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8080">
<title>Insert title here</title>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Response</title>
</head>
<body>
    <h2>Response from API:</h2>
    <pre><%= request.getAttribute("response") %></pre>
</body>
</html>
# Server port
server.port=8081

# Context path (base path for your application)
server.servlet.context-path=/myapp

# Datasource
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=dbuser
spring.datasource.password=dbpassword

# Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.show-sql=true

# Logging
logging.level.org.springframework.web=DEBUG
logging.level.com.mycompany.myapp=INFO
logging.file.name=myapp.log

spring.profiles.active=dev




app.custom.property=value

