# Interplanetary Market - Backend

Acest repository conține backend-ul aplicației **Interplanetary Market** și fișierele bazei de date H2.

## Cerințe
- Java 17+  
- Maven 3+  
- Spring Boot  

## Structura repository-ului
- `backend/` → codul sursă Spring Boot  
- `data/` → fișierele bazei H2 (`interplanetary.mv.db`, `interplanetary.trace.db`)  

## Pași pentru rulare

1. Clonează repository-ul:
git clone https://github.com/Ionut111508/interplanetary-market.git
cd interplanetary-market

2. Verifică că fișierele bazei de date se află în folderul data/.
3. Configurează conexiunea la baza de date în backend/src/main/resources/application.properties:
   spring.datasource.url=jdbc:h2:file:./data/interplanetary
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true

4.cd backend
mvn spring-boot:run
