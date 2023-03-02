<h1>Дипломный проект по автоматизации тестирования WEB, API, MOBILE платформ
<a href="https://podolsk.dominospizza.ru/">DOMINO'S PIZZA</a></h1>

<div style="display: flex; flex-direction:column;">
<a target="_blank" href="https://podolsk.dominospizza.ru/"><img src="readmemedia/dominos-logo.png"></a>
</div>

## :pushpin: Содержание:

<div id="header"></div>

- [Технологии и инструменты](#tech)
- [Запуск из терминала](#term)
- [Возможные комбинации запуска](#combinations)
- [Сборка в Jenkins](#job)
- [Allure отчет](#allure)
- [Интеграция с Allure TestOps](#testOps)
- [Интеграция с Jira](#jira)
- [Отчет в Telegram](#tm)
- [Видео примеры прохождения тестов](#video)

## :rocket: Технологии и инструменты

| Intellij Idea                                                                                                                    | Java         | Github  | JUnit 5  | Gradle | Selenide | Selenoid | Allure | Jenkins | Browserstack                                                                                                                                                                    | Android Studio                                                                                                                         |                                                                                                     Appium |
|:---------------------------------------------------------------------------------------------------------------------------------|-------------| ----- | ----- | ----- | ----- | ----- | ----- | ----- |---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------:|
| <a id ="tech" href="https://www.jetbrains.com/idea/"><img src="readmemedia/Intelij_IDEA.svg" width="50" height="50"  alt="IDEA"/></a> | <a href="https://www.java.com/"><img src="readmemedia/Java.svg" width="50" height="50"  alt="Java"/></a>| <a href="https://github.com/"><img src="readmemedia/Github.svg" width="50" height="50"  alt="Github"/></a> | <a href="https://junit.org/junit5/"><img src="readmemedia/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a> | <a href="https://gradle.org/"><img src="readmemedia/Gradle.svg" width="50" height="50"  alt="Gradle"/></a> | <a href="https://selenide.org/"><img src="readmemedia/Selenide.svg" width="50" height="50"  alt="Selenide"/></a> | <a href="https://aerokube.com/selenoid/"><img src="readmemedia/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a> | <a href="https://github.com/allure-framework/allure2"><img src="readmemedia/Allure_Report.svg" width="50" height="50"  alt="Allure"/></a> | <a href="https://www.jenkins.io/"><img src="readmemedia/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>| <a href="https://app-automate.browserstack.com/"><img src="readmemedia/browserstack.svg" style="display:block; margin: 0 auto" width="50" height="50"  alt="Browserstack"/></a> | <a href="https://developer.android.com/studio"><img src="readmemedia/androidS.svg" style="display:block; margin: 0 auto" width="50" height="50"  alt="Android Studio"/></a> | <a href="https://appium.io/"><img src="readmemedia/appium.svg" width="50" height="50"  alt="Appium"/></a> |

Автотесты в этом проекте написаны на `Java` с использованием `Selenide`.\
`Gradle` - инструмент автоматизации сборки.  \
`JUnit5` - для выполнения тестов.\
`REST Assured` - для тестирования REST-API сервисов.\
`Jenkins` - CI/CD для запуска тестов удаленно.\
`Selenoid` - для удаленного запуска браузера в `Docker` контейнерах.\
`Browserstack` - для запуска тестов мобильных устройств удаленно.\
`Android Studio tools`, `Appium` - для запуска мобильных тестов локально на эмуляторе мобильных устройств.\
`Allure Report` - для визуализации результатов тестирования.\
`Telegram Bot` - для уведомлений о результатах тестирования.\
`Allure TestOps` - система управления тестированием.

<a href="#header" style="display:block; text-align:center; cursor:pointer; color: #fff !important; text-decoration: none">
&#8679; К содержанию</a>

## :computer: Запуск тестов из терминала

Для запуска тестов локально и в Jenkins используется команда:
<a id="term"></a>

```bash
gradle -Denv=${ENV} clean ${TAG}
```

`tag` - теги для запуска выполнения тестов:
> - *api*
>- *ui*
>- *mobile*

`env` - определяет среду для запуска этих тестов:
> - *api*
>- *ui.remote*
>- *ui.local*
>- *browserstack*
>- *local*

Дополнительные свойства извлекаются из соответствующего файла конфигурации (в зависимости от значения `env`):

```bash
./resources/config/api/${env}.properties
./resources/config/ui/${env}.properties
./resources/config/mobile/${env}.properties
```

<a href="#header" style="display:block; text-align:center; cursor:pointer; color: #fff !important; text-decoration: none">
&#8679; К содержанию</a>

<p id="combinations">Возможные комбинации:</p>

```mermaid
graph LR
A[tag] --> B[api]
A --> C[ui]
A --> D[mobile]
B --> K[api]
C --> E[ui.remote]
C --> F[ui.local]
D --> G[browserstack]
D --> H[local]
```

<a href="#header" style="display:block; text-align:center; cursor:pointer; color: #fff !important; text-decoration: none">
&#8679; К содержанию</a>

## <a id="job" name="Запуск в Jenkins">Запуск в [Jenkins](https://jenkins.autotests.cloud/job/dominos_project/)</a>

Главная страница сборки:
<p  align="center">
<img src="readmemedia/jenkinsMain.png" width="950">
</p>

Параметризованное задание Jenkins может быть запущено с необходимыми ***tag*** и ***env***:
<p  align="center">
<img src="readmemedia/jenkinsParam.png" alt="JenkinsBuildParameters" width="950">
</p>

Конфиденциальная информация (имена для входа и пароли) хранится в закодированном виде в
./resources/config/credentials/credentials.properties.

После завершения сборки результаты тестирования доступны в:
> - <code><strong>*Allure Report*</strong></code>
>- <code><strong>*Allure TestOps*</strong></code> - результаты загружаются туда и тест-кейсы могут автоматически
   обновляться в соответствии с последними изменениями в коде.

<a href="#header" style="display:block; text-align:center; cursor:pointer; color: #fff !important; text-decoration: none">
&#8679; К содержанию</a>

## <img id="allure" src="readmemedia/Allure_Report.svg" width="25" height="25"  alt="Allure"/> Отчет в <a target="_blank" href="https://jenkins.autotests.cloud/job/dominos_project/32/allure/">Allure report</a>

### Основное окно

<div align="center">
<img title="Allure Overview Dashboard" src="readmemedia/allure_dashboard.png">
</div>

### Тесты

<div align="center">
<img title="Allure Tests" src="readmemedia/allure_s.png">
</div>

### Графики

<div align="center">
<img title="Allure Graphics" src="readmemedia/graph.png">
</div>
<a href="#header" style="display:block; text-align:center; cursor:pointer; color: #fff !important; text-decoration: none">&#8679; К содержанию</a>

## <img src="readmemedia/allure_ee.svg" width="25" height="25"  alt="Allure"/></a>Интеграция с <a target="_blank" href="https://allure.autotests.cloud/launch/19608">Allure TestOps</a>

### Дашборд

<div align="center">
<img id="testOps" title="Allure TestOps Dashboard" src="readmemedia/testopsDash.png">
</div>

### Тест-кейсы

<div align="center">
<img title="Allure TestOps Tests" src="readmemedia/totc.png">
</div>

<a href="#header" style="display:block; text-align:center; cursor:pointer; color: #fff !important; text-decoration: none">
&#8679; К содержанию</a>

## <img src="readmemedia/Jira.svg" width="25" height="25"  alt="Allure"/></a>Интеграция с трекером задач <a target="_blank" href="https://jira.autotests.cloud/browse/HOMEWORK-498">Jira</a>

<div align="center">
<img id="jira" title="Jira" src="readmemedia/jira.png">
</div>

<a href="#header" style="display:block; text-align:center; cursor:pointer; color: #fff !important; text-decoration: none">
&#8679; К содержанию</a>

## <img src="readmemedia/Telegram.svg" width="25" height="25"  alt="Allure"/></a> Уведомление в Telegram при помощи бота

<div align="center">
<img id="tm" title="Allure Overview Dashboard" src="readmemedia/tg.png">
</div>

<a href="#header" style="display:block; text-align:center; cursor:pointer; color: #fff !important; text-decoration: none">
&#8679; К содержанию</a>

### <img src="readmemedia/Selenoid.svg" width="25" height="25"  alt="Allure"/></a> Примеры видео о прохождении тестов Selenoid

<div id="video" align="center">
<video src="https://user-images.githubusercontent.com/59203915/221523275-0d24cd32-d37b-43bf-91c3-81ed86881149.mp4"></video>
</div>

### <img src="readmemedia/browserstack.svg" width="25" height="25"  alt="Allure"/></a> Примеры видео о прохождении тестов Browserstack

<div align="center">
<video src="https://user-images.githubusercontent.com/59203915/221526156-cace06ec-a478-49b9-9011-ce2155ba95f1.mp4"></video>
</div>

<a href="#header" style="display:block; text-align:center; cursor:pointer; color: #fff !important; text-decoration: none">
&#8679; К содержанию</a>