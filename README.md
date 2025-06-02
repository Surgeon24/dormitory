# System Rezerwacji Domu Studenckiego

Aplikacja webowa do zarządzania rezerwacjami pomieszczeń wspólnych w domu studenckim, stworzona przy użyciu Spring Boot, Spring Security, Spring Data JPA oraz Thymeleaf.

## 📌 Funkcjonalności

- Rejestracja i logowanie użytkowników (z uwzględnieniem bezpieczeństwa i migracji haseł).
- Przegląd dostępnych pomieszczeń (np. pralni, pokoju TV, stołu pingpongowego).
- Rezerwacja pomieszczeń w określonym dniu i godzinach.
- Przegląd rezerwacji zalogowanego użytkownika.
- System autoryzacji (dostęp do funkcji zależny od zalogowania).
- Prosty i przejrzysty interfejs użytkownika zbudowany z wykorzystaniem Thymeleaf i CSS.

## ⚙️ Technologie

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA**
- **Thymeleaf**
- **MySQL**
- **Gradle**

## 🗂 Struktura katalogów
src/main/java/krakow/university/dormitory/<br />
│<br />
├── controllers/<br />
│ └── UserController, ReservationController, RoomController ...<br />
├── entities/<br />
│ └── User, Reservations, Room ...<br />
├── repositories/<br />
│ └── UserRepository, ReservationRepository ...<br />
├── services/<br />
│ └── UserService, ReservationService ...<br />
├── security/<br />
│ └── SecurityConfig, CustomUserDetailsService ...<br />
└── DormitoryApplication.java<br />

## ▶️ Uruchamianie projektu

1. **Konfiguracja baz danych**:
    - Upewnij się, że masz lokalną bazę danych MySQL.
    - W pliku `application.properties` skonfiguruj dostęp do bazy:

      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/dormitory
      spring.datasource.username=twoja_nazwa_uzytkownika
      spring.datasource.password=twoje_haslo
      spring.jpa.hibernate.ddl-auto=validate
      ```

2. **Budowanie i uruchomienie aplikacji**
    ```bash
    ./gradlew clean build  
    ./gradlew bootRun
    ```

3. **Wejście do aplikacji**:
    - Domyślnie dostępna pod adresem: [http://localhost:8080](http://localhost:8080)
    - Strona logowania: `/login`

## ✅ Przykładowy użytkownik testowy

- **E-mail**: `sylwiamalz@example.org`  
- **Hasło**: `SandyBrownEat43*`

## 🧑‍💻 Autorzy

Projekt stworzony przez studentów Politechniki Krakowskiej w ramach kursu ZTP<br />
Piotr Jakubas<br />
Jan Karpiuk<br />
Mikhail Ermolaev<br />
