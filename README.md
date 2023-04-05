<h3>Тестовое задание - приложение Springboot “Обработка заявок”</h3>

<b>База данных Postgress</b>  - vitasoft

<b>Скрипт database</b> – initDB.spl

<b>Скрипт популирования DB</b> – populateDB.sql</p>



<h3>Credentials:</h3>

<h4>for User</h4>
<li>username: user@gmail.com</li>
<li>password: password</li>
<h4>for User2</h4>
<li>username: user2@gmail.com</li>
<li>password: password</li>
<h4>Operator:</h4>   
<li>username: operator@gmail.com</li>
<li>password: operator</li>

<h4>Admin:</h4>           
<li>username: admin@gmail.com</li>
<li>password:  admin</li> 
<br>


Тестирование приложения осуществлялось вручную с помощью Swagger:
http://localhost:8080/swagger-ui.html


http://localhost:8080/rest/profile - работа с профайлом юзера
<hr>

http://localhost:8080/rest/profile/applications - работа юзера с его заявками

   GET: http://localhost:8080/rest/profile/applications/{id} – получение заявки по id</li>
   
PUT: http://localhost:8080/rest/profile/applications/{id} – редактирование заявки по id (возможно редактировать только неотправленные заявки – статус DRAFT)

POST: http://localhost:8080/rest/profile/applications - создать заявку 

POST: http://localhost:8080/rest/profile/applications/{id} – отправить заявку оператору (возможно только для заявок в статусе DRAFT)

DELETE: http://localhost:8080/rest/profile/applications/{id} – удаление заявки 
   
GET: http://localhost:8080/rest/profile/applications?page=0&size=5 – получить все заявки, отсортированные в натуральном порядке по дате и с пагинацией 5 элементов ( page, size – не обязательны)
   
GET: http://localhost:8080/rest/profile/applications/sortDesc?page=0&size=5 - получить все заявки, отсортированные в обратном порядке по дате и с пагинацией 5 элементов ( page, size – не обязательны)
<hr>

http://localhost:8080/rest/admin/users - работа админа с юзерами

GET: http://localhost:8080/rest/admin/users - просмотр всех юзеров с их заявками
GET: http://localhost:8080/rest/admin/users/by-name?name=%25se%25 – поиск юзеров по имени или части имени. 
Возможен поиск по:
<li>целому имени,</li>
<li>по началу имени (например: Use% )</li>
<li>по окончанию имени ( например: %er)</li>
<li>по части имени (как в примере: %se%)</li>

PATCH: http://localhost:8080/rest/admin/users/{id} – отключение/подключение юзера (enabled/disabled)

PATCH: http://localhost:8080/rest/admin/users/{id}/set-role - установление юзеру роли оператора.

PATCH: http://localhost:8080/rest/admin/users/{id}/remove-role - удаление роли оператора
<hr>

http://localhost:8080/rest/operator/applications - работа оператора с заявками

GET: http://localhost:8080/rest/operator/applications?page=0&size=5 – просмотр всех присланных юзерами заявок, отсортированных в натуральном порядке по дате и с пагинацией 5 элементов (page, size – не обязательны). Текст заявки выводится со знаком <-> после каждого символа.

GET: http://localhost:8080/rest/operator/applications/sortDesc?page=0&size=5 - просмотр всех присланных юзерами заявок, отсортированных в обратном порядке по дате и с пагинацией 5 элементов (page, size – не обязательны). Текст заявки выводится со знаком <-> после каждого символа.

GET http://localhost:8080/rest/operator/applications/by-name?name=Us%25&page=0&size=5 – просмотр заявок по имени или части имени конкретного(ых) юзера (ов). 
Возможен поиск по:
<li>целому имени,</li>
<li>по началу имени (как в примере: Us% ) </li>
<li>по окончанию имени ( например: %er)</li>
<li>по части имени (например: %se%)</li>

PATCH: http://localhost:8080/rest/operator/applications/{id}?status={status} –
Принять (ACCEPTED) или отклонить (REJECTED) заявку


<h3>Описание задания:</h3>
Спроектировать и разработать систему регистрации и обработки пользовательских заявок. 
Пользователь посредством системы может подавать заявки оператору на рассмотрение. 
Оператор может просматривать пользовательские заявки и принимать или отклонять их. 
Администратор управляет правами доступа.
• Спроектировать и разработать back-приложение
• Спроектировать и разработать Базу данных

Функции приложения
<li> Создать заявку (Заявка помимо прочих системных полей состоит из статуса и текстового обращения пользователя)</li>
<li>Отправить заявку оператору на рассмотрение</li>
<li>Просмотреть список заявок с возможностью сортировки по дате создания в оба направления (как от самой старой к самой новой, так и наоборот) и пагинацией по 5 элементов</li>
<li>Посмотреть заявку</li>
<li>Принять заявку</li>
<li>Отклонить заявку</li>
<li>Просмотреть список пользователей</li>
<li>Назначить права оператора</li>

В системе предусмотрены 3 роли:
<li>Пользователь</li>
<li>Оператор</li>
<li>Администратор</li>

У пользователя системы может быть одновременно несколько ролей, например, «Оператор» и «Администратор».

У заявки пользователя предусмотрено 4 состояния:
<li>черновик</li>
<li>отправлено</li> 
<li>принято</li>
<li>отклонено</li> 

Пользователь может
<li>создавать заявки</li>
<li>просматривать созданные им заявки с возможностью сортировки по дате создания в оба направления (как от самой старой к самой новой, так и наоборот) и пагинацией по 5 элементов</li>
<li>редактировать созданные им заявки в статусе «черновик»</li>
<li>отправлять заявки на рассмотрение оператору.</li>

Пользователь НЕ может:
<li>редактировать отправленные на рассмотрение заявки</li>
<li>видеть заявки других пользователей</li>
<li>принимать заявки</li>
<li>отклонять заявки</li>
<li>назначать права</li>
<li>смотреть список пользователей</li>

Оператор может
<li>Просматривать все отправленные на рассмотрение  заявки с возможностью сортировки по дате создания в оба направления (как от самой старой к самой новой, так и наоборот) и пагинацией по 5 элементов </li>
<li>Просматривать отправленные заявки только конкретного пользователя по его имени/части имени (у пользователя, соотетственно, должно быть поле name) с возможностью сортировки по дате создания в оба направления (как от самой старой к самой новой, так и наоборот) и пагинацией по 5 элементов</li>
<li>Принимать заявки</li>
<li>Отклонять заявки</li>

Оператор НЕ может
<li>создавать заявки</li>
<li>просматривать заявки в статусе отличном от «отправлено»</li> 
<li>редактировать заявки</li>
<li>назначать права</li>

Администратор может
<li>смотреть список пользователей</li>
<li>искать конкретного пользователя по его имени/части имени</li>
<li>назначать пользователям права оператора</li>

Администратор НЕ может
<li>создавать заявки</li>
<li>просматривать заявки</li>
<li>редактировать заявки</li>
<li>принимать заявки</li> 
<li>отклонять заявки</li>


Технические требования к приложению
<li>Java 1.8/Java 11</li>
<li>Использовать архитектуру REST</li>
<li>Использовать Spring Boot</li>
<li>Использовать Spring Security</li>
<li>Использовать Hibernate</li> 
<li>Использовать реляционную БД (MS SQL, MS SQL Lite, PostgreSQL, MariaBD)</li> 
<li>Создание пользователей и ролей не предусмотрено в этой системе. Подразумевается, что данные об учетных записях пользователей и роли уже есть в БД.</li>
<li>В случае просмотра заявки оператором текст заявки выводить со знаком <-> после каждого символа. Пример: Пользователь отправил на рассмотрение заявку с текстом «Мне нужна помощь», а оператор на экране видит текст в формате «М-н-е- -н-у-ж-н-а- -п-о-м-о-щ-ь».</li>
