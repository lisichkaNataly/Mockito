# Mockito
проект с применением Mockito
Небольшое приложение которое управляет списком пользователей и протестируем его с помощью Junit и Mockito.
Для начала создан обычный Maven проект без Spring.
Часть 1
1. Создан класс User который содержит следующие поля
- Неизменяемое строковое поле 
login
- Изменяемое строковое поле 
password
Подсказка: Неизменяемые поля мы делаем 
final
2. Созданы необходимые геттеры и сеттеры для полей, определены equals, hashCode и toString
Часть 2
1. Создан класс UserRepository, который будет хранить в себе список пользователей и содержит следующие методы
- Получение всех пользователей: метод должен возвращать коллекцию пользователей
- Поиск пользователя по логину: метод должен принимать строку логина по которой мы ищем и возвращать  Optional<User>
- Поиск пользователя по логину и паролю: метод должен принимать строку логина и строку пароля и возвращать Optional<User>. В этом методе мы возвращаем пользователя только если получили полное соответствие (то есть у этого пользователя совпали и логин и пароль).
- Метод добавления пользователя в список: метод должен принимать User, а возвращать void
2. Будем считать что в методы мы получаем только валидные данные, строки которые нам передаются никогда не будут null, а проверка уникальности логина выполняется выше по коду, поэтому дополнительно проверять уникальность в методе добавления не надо.
Часть 3
1. Создан класс UserService, который будет принимать запросы извне и работать со списком пользователей.
2. Данный класс должен использовать UserRepository в качестве поля и содержать следующие методы:
- Метод получения всех логинов пользователей - должен возвращать список строк со всеми логинами
- Метод создания нового пользователя - он должен принимать в себя логин и пароль нового пользователя и выполнять группу проверок:
а) Проверка на то что логин и пароль не пустые и не null. Если условие не выполняется, то метод выбрасывает 
IllegalArgumentException
б) Проверка на то, что пользователь с таким логином не существует. Если такой пользователь существует, то выбрасываем пользовательское исключение которое нужно будет создать 
UserNonUniqueException(). Данное исключение желательно сделать непроверяемым.
в) Если проверки выполнены, то вызывается соотвествующий метод репозитория для добавления.
г) Метод ничего не возвращает.
д) Метод логина пользователя принимает строки логина и пароля. Метод должен возвращать true в случае если пользователь с таким логином и паролем найден и false в обратном случае, никаких исключений выбрасываться не должно.
Для первоначального тестирования класса можно добавить несколько пользователей в конструкторе.
Часть 4
Созданы два класса для тестирования.
1. Первый класс UserRepositoryTestбудет без применения Mockito. В этом классе мы тестируем все методы UserRepository, так как условий и проверок там практически нет то нужно сделать тесты на следующие сценарии:
- Получение пустого списка пользователей → должен возвращаться пустой список.
- Получение списка пользователей при изначально заполненном сервисе → должны возвращаться те же самые пользователи которых добавляли.
- Поиск пользователя по логину → в случае если такой пользователь есть.
- Поиск пользователя по логину → в случае когда такого пользователя нет.
- Поиск пользователя по логину и паролю → в случае если такой пользователь есть.
- Поиск пользователя по логину и паролю → в случае когда пароль совпадает с одним из существующих, а логин - нет.
- Поиск пользователя по логину и паролю - в случае когда логин совпадает с одним из существующих, а пароль - нет.
2. Вторым классом для тестирования будет класс UserServiceTest, в котором нам необходимо: протестировать все методы  UserService. 
Покрыты все методы и возможные ситуации так же, как мы делали в тестировании предыдущего класса. 
UserRepository не создается в этом классе, мы включаем его как @Mock и конфигурируем в каждом тесте согласно желаемому результату.
Нужно помнить, что тесты надо запустить и по одному и все вместе, чтобы проверить, что они не конфликтуют.
