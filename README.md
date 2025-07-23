## MineCraft Forge Events

MC 1.7.10

Данный проект - это библиотека содержащая классы дополнительных событий(events)
и других полезных классов и интерфейсов, для создания интеграции между модами
и для их пере-использования в других модах кубача версии 1.7.10.

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
  implementation "org.swarg.mcfevent:mcfevents:0.2.0"
}
```

> Как установить данную библиотеку в свой локальный мавен-репозиторий
```sh
git clone --depth 1 git@github.com:Svvarg/mcfevents.git
cd mcfevents
gradle build
gradle publishToMavenLocal
# or
# make build to-mvn-local
```


## Моды и библиотеки использующие данную библиотеку:

- MCFBackendLib
- MerchantsTFC 1.2+
- MCFCoreFix 0.4+
- LevelUp 2.3+


## TODOList
- Событие на изменение квеста. Например для того чтобы обновлять общее кол-во
  опыта которые можно получить со всех квестов для RPG-системы
