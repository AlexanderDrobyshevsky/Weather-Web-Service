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
