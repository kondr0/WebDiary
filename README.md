# WebDiary

REST API «Онлайн-дневник студентов» на Spring Boot: управление студентами и событиями (занятиями) с учётом посещаемости.

## Возможности

- CRUD для студентов: создание, чтение, обновление, удаление.
- Постраничная выдача списка студентов (`PagingAndSorting`).
- Поиск студента по имени.
- Домен студентов и событий (занятий), связанных отношением «многие ко многим».
- Чёткое разделение слоёв: контроллер → сервис → репозиторий → модель.
- DTO в виде Java `record`, конвертация моделей в DTO через отдельные мапперы.
- Централизованная обработка ошибок: `404 Not Found` для отсутствующих сущностей.
- UUID в качестве первичных ключей.
- Тесты на JUnit 5 + Mockito, интеграционные тесты через Testcontainers.

## Стек

| Технология | Версия |
|------------|--------|
| Java | 17 |
| Spring Boot | 4.1.1 |
| Spring Web MVC | — |
| Spring Data JPA | — |
| Bean Validation | — |
| PostgreSQL | — |
| Gradle (wrapper) | 9.7.1 |
| JUnit 5 / Mockito / Testcontainers | — |

## Архитектура

```
src/main/java/com/example/WebDiary
├── WebDiaryApplication.java   — точка входа
├── controller/                — REST-контроллеры (HTTP-слой)
├── service/                   — бизнес-логика
├── repository/                — Spring Data JPA репозитории
├── model/                     — JPA-сущности (Student, Event)
├── dto/                       — request/response в виде record
├── mapper/                    — конвертация entity <-> dto
└── exception/                 — доменные исключения и глобальный обработчик
```

Слои не смешиваются: контроллер не работает с сущностями напрямую, а сервис не знает про HTTP.

## Модель данных

- **Student** — `id (UUID)`, `name`, `birthday`, `presence`, `studyDirection`, `course`, `endOfStudying`
- **Event** — `id (UUID)`, `name`, `date`
- **Student ↔ Event** — отношение `@ManyToMany`, промежуточная таблица `student_event`

## REST API

| Метод | Путь | Описание |
|-------|------|----------|
| `GET` | `/` | Список студентов, постранично (`page`, `size`, `sort`) |
| `GET` | `/{id}` | Студент по `UUID` |
| `POST` | `/` | Создать студента |
| `PUT` | `/{id}` | Обновить студента |
| `DELETE` | `/{id}` | Удалить студента |
| `GET` | `/search?name=...` | Найти студента по имени |

Пример:

```bash
curl -X POST http://localhost:8080/ \
  -H "Content-Type: application/json" \
  -d '{"name":"Nikolay","birthday":"2004-03-09","presence":9,"studyDirection":"ИСиТ","course":4,"endOfStudying":"2026"}'
```

Пример ответа:

```json
{
  "id": "8f14e45f-ceea-467a-9be3-9c2f5e2d3a11",
  "name": "Nikolay",
  "birthday": "2004-03-09",
  "presence": 9,
  "studyDirection": "ИСиТ",
  "course": 4,
  "endOfStudying": "2026",
  "events": []
}
```

## Настройка БД

Создайте базу в PostgreSQL и укажите параметры подключения в `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tasks
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
```

## Запуск

```bash
# приложение
./gradlew bootRun

# тесты
./gradlew test

# сборка jar
./gradlew bootJar
```
