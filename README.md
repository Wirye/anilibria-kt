# anilibria-kt

Kotlin-библиотека для работы с API AniLibria.
(Обновляется по мере развития моего приложения Garden, если тут чего-то нету, то оно скоро будет)

Чтобы начать работу создайте AniLibriaClient(), в нём находятся все функции, например для входа в аккаунт - AniLibriaClient().account.auth.login(login, password) и так далее

ВАЖНО: При создании клиента библиотеки, желательно указывать осмысленный User-Agent
ОЧЕНЬ ВАЖНО: В httpClient указывайте json → ignoreUnknownKeys = true
