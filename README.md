<h1 align="center"> Проект по автоматизации тестирования для сайта 
<a href="https://petstore.swagger.io"> petstore.swagger.io</a> 
</h1>     


  
>petstore.swagger.io — это пример сервера для зоомагазина.
____
## **Содержание:**


* <a href="#tools">Технологии и инструменты</a>

* <a href="#cases">Примеры автоматизированных тест-кейсов</a>

* <a href="#jenkins">Сборка в Jenkins</a>

* <a href="#console">Запуск из терминала</a>

* <a href="#allure">Allure отчет</a>

* <a href="#allure-testops">Интеграция с Allure TestOps</a>

* <a href="#jira">Интеграция с Jira</a>

* <a href="#telegram">Уведомление в Telegram при помощи бота</a>
____

<a id="tools"></a>

## <a name="Технологии и инструменты">**Технологии и инструменты:**</a>
<p align="center">  
<a href="https://www.jetbrains.com/idea/"><img src="images/Intelij_IDEA.svg" width="50" height="50"  alt="IDEA"/></a>  
<a href="https://www.java.com/"><img src="images/Java.svg" width="50" height="50"  alt="Java"/></a>  
<a href="https://github.com/"><img src="images/github.svg" width="50" height="50"  alt="Github"/></a>  
<a href="https://junit.org/junit5/"><img src="images/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a>  
<a href="https://gradle.org/"><img src="images/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>  
<a href="https://selenide.org/"><img src="images/Selenium.svg" width="50" height="50"  alt="Selenium"/></a>  
<a href="ht[images](images)tps://github.com/allure-framework/allure2"><img src="images/Allure.svg" width="50" height="50"  alt="Allure"/></a> 
<a href="https://qameta.io/"><img src="images/Allure2.svg" width="50" height="50"  alt="Allure TestOps"/></a>   
<a href="https://www.jenkins.io/"><img src="images/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>  
<a href="https://www.atlassian.com/ru/software/jira/"><img src="images/Jira.svg" width="50" height="50"  alt="Jira"/></a>  
</p>

Тесты написаны на языке <code>Java</code> с использованием фреймворка для автоматизации тестирования <code>Selenide</code>, сборщик - <code>Gradle</code>.

<code>JUnit 5</code> задействован в качестве фреймворка модульного тестирования.
При прогоне тестов для удаленного запуска используется <code>Selenoid</code>.

Для удаленного запуска реализована джоба в <code>Jenkins</code> с формированием Allure-отчета и отправкой результатов в <code>Telegram</code> при помощи бота.
Также реализована интеграция с <code>Allure TestOps</code> и <code>Jira</code>.
API тесты реализованы при помощи <code>RestAssured</code>.

____

<a id="cases"></a>

## <a name="Примеры автоматизированных тест-кейсов">**Примеры автоматизированных тест-кейсов:**</a>

-  *Успешная регистрация пользователя*
-  *Получение информации о пользователе*
-  *Удаление пользователя*
-  *Создание заказа*
-  *Получение информации о заказе*
-  *Удаление заказа*


____
<a id="jenkins"></a>
## <a name="Сборка"></a>Сборка в [Jenkins](https://jenkins.autotests.cloud/job/petstoreApitests)</a>
### **Параметры сборки в Jenkins:**

- *task (выбор группы тестов для запуска, по умолчанию все - test)*


<a id="console"></a>
## Команды для запуска из терминала
___
***Локальный запуск:***
```bash  
gradle clean test
```


***Удалённый запуск через Jenkins:***
```bash  
clean test
```

___
<a id="allure"></a>
## <a name="Allure"></a>Allure [отчет](https://jenkins.autotests.cloud/job/petstoreApitests/)</a>
___

### *Основная страница отчёта*

<p align="center">  
<img title="Allure Overview Dashboard" src="images/AllureMain.jpg" width="850">  
</p>  

### *Тест-кейсы*

<p align="center">  
<img title="Allure Tests" src="images/AllureTests.jpg" width="850">  
</p>

### *Графики*

  <p align="center">  
<img title="Allure Graphics" src="images/AllureGraghs.jpg" width="850">

___
<a id="allure-testops"></a>
## Интеграция с <a target="_blank" href="https://allure.autotests.cloud/launch/46538">Allure TestOps</a>
____
### *Allure TestOps Dashboard*

<p align="center">  
<img title="Allure TestOps Dashboard" src="images/TestopsMain.jpg" width="850">  
</p>  

### *Авто тест-кейсы*

<p align="center">  
<img title="Allure TestOps Tests" src="images/testopsTests.jpg" width="850">  
</p>
