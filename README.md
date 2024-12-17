## MineCraft Forge Events

MC 1.7.10

Данный проект - это библиотека содержащая классы дополнительных событий(events)
для их пере-использования  в других модах кубача версии 1.7.10.

То есть это не мод, а только библиотека необходимая для некоторых других модов.


## Пример подключения к своему моду

Для локального использования из локального мавен-репозитория

build.gradle
```gradle
repositories {
    mavenLocal()
    // ...
}

dependencies {
  // ...
  implementation "org.swarg.mcfevent:mcfevents:0.1.0"
}
```

> Как установить данную библиотеку в свой локальный мавен-репозиторий
```sh
git clone --depth 1 git@github.com:Svvarg/mcfevents.git
cd mcfevents
gradle publishToMavenLocal
```


## Моды и библиотеки использующие данную библиотеку:

- MCFBackendLib
- MerchantsTFC 1.2+
