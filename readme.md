#Постановка задачи
Написать программу, на вход которой подаётся два аргумента:
1. Слово
2. Путь к файлу со словарём.
 
Цель: найти в слове все слова из словаря, которые можно составить из этого слова или любого подмножества его букв.

Итоговый результат должен собираться с помощью maven или gradle. jar-файл должен запускаться без указания classpath

##Пример
Слово: крокодил.

В нём есть слова
- код
- док
- род

но нет слов кадило (в исходном слове нет буквы а)
и рококо (в исходном слове нет третьей буквы о)