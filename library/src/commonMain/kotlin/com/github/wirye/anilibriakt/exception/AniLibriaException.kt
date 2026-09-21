package com.github.wirye.anilibriakt.exception

sealed class AniLibriaException(message: String) : Exception(message) {
    class InvalidCredentialsException : AniLibriaException("Неправильный логин или пароль")
    class ValidationErrorException : AniLibriaException("Ошибка валидации (неверный формат данных)")
    class HtmlResponseException(code: Int) : AniLibriaException("Сервер вернул HTML-страницу (Код $code). Возможно, заблокировано антиботом.")
    class ServerErrorException(code: Int) : AniLibriaException("Ошибка сервера (код $code)")
}
