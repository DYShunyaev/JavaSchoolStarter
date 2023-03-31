___
# Java School Starter Project
___
## Data Base
*Данный проект был написан в соответсвии с [условиями](https://docs.google.com/document/d/1QNtu5L3ppvNF-o06ho7eU4jm1R2MQK-For_DWnNZRRE/edit),
высланными на почту
командой ***Digital Design***.*

### 1) Описание проекта:
Программа представляет собой Базу данных, с SQL подобным языком управления
данными в коллекции. Поддерживаемые команды: *вставка, изменение, удаление
и поиск **элементов в коллекции***.

### 2)Стэк проекта
- *[Java 17](https://openjdk.java.net/projects/jdk/17/)* as the programming language.
- *[Maven](https://maven.apache.org/)* as the Java build tool.
- *[IntelliJ IDEA](https://www.jetbrains.com/idea/)* as the IDE.

### 3) Запуск программы
Для запуска программы необходимо:

1. [Скачать](https://github.com/DYShunyaev/JavaSchoolStarter.git) репозиторий.
2. Клонировать, т.е., загрузить копию *репозитория* на локальный диск компьютера.
```
https://github.com/DYShunyaev/JavaSchoolStarter.git
```
3. Импортировать проект в [IntelliJ IDEA](https://www.jetbrains.com/idea/download/).
4. Сборка проекта `` mvn compile``
5. Перейти в класс [Main](src/main/java/com/digdes/school/Main.java)
6. В параметре метода [*JavaSchoolStarter.execute()*](src\main\java\com\digdes\school\JavaSchoolStarter.java) ввести SQL запрос (параметр принимает переменную типа *String*).
7. Выполнить запуск.

### 4) Порядок написания запроса
В начале запроса указывается ключевое слово, которое указывает на тип 
выполняемого действия:
* INSERT
* UPDATE
* SELECT
* DELETE

Далее, в одинарных ковычках (') указывается название колонки,
если запрос подразумевает изменение данных, то они указываются через
знак равно.

``'lastName' = 'Федоров', 'id' = 3``

Названия колонок определены заранее: **id, lastName, age, cost, 
active**.

**Разрешенные типы данных:**
1. id = Long;
2. lastName = String;
3. age = Long;
4. cost = Double;
5. active = Boolean;

При фильтрации с использованием команды **WHERE** значения должны 
быть одного типа, за исключенем Long и Double, иначе программа выдаст ошибку.

### 5) Выполненные требования
1. Коллекция List<Map<String, Object>>;
2. Команнды INSERT, UPDATE, SELECT, DELETE;
3. Логические операторы и операторы сравнения;
4. Валидация запроса;
5. Exception, при неккоректном вводе запроса: 
   * неправильное наименование колонки;
   * ошибки в синтаксисе запроса;
   * сравнение различных типов данных (Кроме Long & Double);
6. Колонки и команды регистро независимые;
7. Данные в коллекции, на время исполнения программы, сохраняются;