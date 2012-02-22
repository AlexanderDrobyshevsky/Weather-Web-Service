Вариант веб сервиса для исходного задания.

J2EE проект.
В качестве среды разработки использовался NetBeans. В качестве сервера приложений - Glassfish 3+.

Для запуска проекта потребуется Application Server.
Необходимо задеплоить разработанный EJB Bean на Application Server.

Иструкция как задеплоить на Glassfish (очень кратко).
1. Установить сервер приложений Glassfish 3+. 
2. Зайти в консоль администратора

3. Перед тем как деплоить непосредственно сам Bean , необходимо сконфигурировать некоторые ресурсы в Glassfish.
Конфигурирование ресурсов.

3.1
После входа в веб консоль администратора перейти на вкладку
JMS Resources / Connection Factories
Выбрать опцию "New.."

Для соответствующих полей ввести/выбрать данные:
Pool Name:  jms/drqFactoryPool
JNDI Name:  jms/drqFactoryPool
Resource Type:   javax.jms.QueueConnectionFactory

3.2
Перейти на вкладку
JMS Resources / Destination Resources

Выбрать опцию "New.."
Для соответствующих полей ввести/выбрать данные:
JNDI Name: jms/drq
PhusicalQueue: PhysicalQueue
Resource Type: javax.jms.Queue

3.3
Resources / Connectors / Connector Resources
Удалить коннектор jms/drqFactoryPool (если он там есть, скорее всего есть)

Выбрать опцию "New.."
Для соответствующих полей ввести/выбрать данные:
JNDI Name: jms/drqFactory
Pool Name: jms/drqFactoryPool

4. Перейти на вкладку Applications, выбрать опцию Deploy..
Из списка файлов надо выбрать 1 файл: WeatherEJBWebService\WeatherEJBWebService-ejb\dist\WeatherEJBWebService-ejb.jar 
Нажать ок - приложение должно задеплоиться на сервер. После чего оно появится в списке среди Deployed Applications на вкладке Applications с именем WeatherEJBWebService-ejb


Для проверки веб методов можно использовать средства самого Application Servera Glassfish.
Для этого нужно перейти по ссылке, кликнут на задеплоенное приложение. Откроется список компонентов приложения.
Рядом с компонентом WeatherWebService в колонке Action надо кликнуть по ссылке View Endpoint.
После перехода кликнуть по ссылке Tester. На следующей странице выбрать вариант тестирования для http.
На следующей странице появятся 2 метода веб сервиса. 

Что это за методы - в описании работы самого веб сервиса.


