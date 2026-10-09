# anilibria-kt

Kotlin-библиотека для работы с [API AniLibria](https://anilibria.top/api/docs/v1#/).
Не связана с AniLibria

Обновляется по мере развития моего приложения Garden, если тут чего-то нету, то оно скоро будет

# Установка

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
    repositories { maven("https://jitpack.io") }
}

// build.gradle.kts
dependencies {
    implementation("com.github.Wirye.anilibria-kt:anilibria-kt:1.1.2")
}
```

# Быстрый старт

```kotlin
val client = AniLibriaClient(userAgent = "MyApp/1.0.0 ( me@example.com )")

client.account.auth.login("login", "password")
    .onSuccess { token -> println("Token: $token") }
    .onFailure { exception -> println("Exception: $exception") }
```

Клиент создаётся один раз. В нём:

- `client.account` - работа с аккаунтом (вход, выход, профиль, избранное, история)
- `client.search` - поиск
- `client.releases` - всё что связанно с релизами (случайные релизы, рекомендации, последние релизы, конкретный релиз, релизы по жанрам, франшизам, работа с эпизодами (получение например), расписание и т.д)
- `client.ads` - получение реклам
- `client.torrents` - получение торрентов

## Ошибки

Все функции возвращают `Result<T>`, а исключения приходят в `Result.failure`

- `InvalidCredentialsException` - неправильный логин или пароль
- `ValidationErrorException` - неверный запрос
- `ServerErrorException(code)` - ошибка сервера, код `503` обычно значит превышение лимита запросов
- `HtmlResponseException(code)` - Сервер вернул HTML-страницу

## Данные и лицензии

Обычно всё открыто, но рекомендую проверять правила
