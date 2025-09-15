# Разработка защищенного REST API с интеграцией в CI/CD

Выполнила: Кузенина Валерия P3432

## Описание проекта
Проект представляет собой простой REST API для управления пользователями и постами с использованием Spring Boot, JPA и JWT-аутентификации.

### Технологии
- Spring Boot 3.5
- Spring Security
- JWT (io.jsonwebtoken)
- JPA / Hibernate
- H2 Database (in-memory)
- Lombok

## Эндпоинты API

### 1. Регистрация пользователя
**URL:** `POST /auth/register`  
**Тело запроса (JSON):**
```json
{
  "username": "test",
  "password": "test",
  "fullName": "Test Example",
  "email": "test@example.com"
}
```
**Описание:** Регистрирует нового пользователя и создаёт для него профиль.

---

### 2. Логин пользователя
**URL:** `POST /auth/login`  
**Тело запроса (JSON):**
```json
{
  "username": "test",
  "password": "test"
}
```
**Ответ:** JWT-токен
```json
"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzU3ODc3NTE5LCJleHAiOjE3NTc5NjM5MTl9.zl-8mupkjnmRoE_9ALAOWjlxc8jkYfRtI09ROZGC62I"
```
### 3. Получение данных профиля
**URL:** `GET /api/data?username=test`
**Ответ:** 
```json
[
"example post"
]
```
**Описание:** Регистрирует нового пользователя и создаёт для него профиль.

### 4. Создание поста
**URL:** `POST /api/create`  

**Заголовок запроса:**
```makefile
  Authorization: Bearer <JWT-токен>
```
**Тело запроса (JSON):**
```json
{
  "content": "Example post content"
}
```
**Описание:** Создаёт новый пост текущего пользователя. Только аутентифицированный пользователь может создавать посты для себя.

## Реализованные меры защиты

### 1. Защита от SQL-инъекций
- Используется Spring Data JPA с параметризованными запросами.
- Нет конкатенации строк для формирования SQL.

### 2. Защита от XSS
- Все пользовательские данные сериализуются в JSON.
- Spring Boot экранирует специальные символы для безопасного отображения.

### 3. Аутентификация и авторизация
- JWT-токены используются для аутентификации пользователей.
- Middleware проверяет токен на защищённых эндпоинтах.
- Пароли хранятся в хэшированном виде с помощью `BCryptPasswordEncoder`.

## Как запускать

```bash
git clone https://github.com/larentiina/isecurity1.git
.\mvnw spring-boot:run
```
## CI/CD
ci/cd настроен в [файле](https://github.com/larentiina/isecurity1/blob/master/.github/workflows/ci.yml)

[ссылка](https://github.com/larentiina/isecurity1/actions/runs/17716903024/job/50343059380) на успешно пройденный пайплайн


