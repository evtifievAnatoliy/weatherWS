Программа - web service, протокол SOAP
язык разработки - java
сборка - maven
библиотеки - javax.jws v1.1, json-simple v1.1, slf4j v1.7.7

входные параметры (окружение среды)
Args[0] - сервер, Args[1] - порт, Args[2] - ключ авторизации Openweathermap, Args[3] - частота обновления в миллисеундах 
пример 
//args[
//    	localhost 
//    	8083      
//    	7ad6640f2d4b76480afbe652df3a7efa
//    	60000
//    ]
выходной параметр - строка, содержащая json
пример
{"MediumTemperature":"19.112505","localDate":"2021-07-30","MaxMorningTemperature":"22.85"}

Краткое описание
web service реализует WebMethod /wss/weather - Входной параметр строка: yyyy-MMM-dd
web service собственной базы не имеет,
обновление данных происходит со стороннего ресурса 
http://api.openweathermap.org/data/2.5/forecast?q=Saint%20Petersburg,%20RU&mode=json&appid=" + key
обновление данных происходит в асинхронном режиме
логирование реализовано с помощью slf4j