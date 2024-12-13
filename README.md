Команда для запуска приложения:
./mvnw clean spring-boot:run -D"spring-boot.run.arguments=--spring.datasource.url=jdbc:postgresql://localhost:5432/taskmanagementsystem --spring.datasource.username=postgres --spring.datasource.password=1234 --spring.jpa.hibernate.ddl-auto=update"
