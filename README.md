# DVT-2

Учебный Gradle-проект для освоения:

Gradle Wrapper  
Java Toolchain (Java 25)  
JUnit 5 и AssertJ  
запуска задач run и test

---

## Quick Start

### Запуск приложения

Windows (PowerShell):  
.\gradlew.bat run

### Запуск тестов

Windows (PowerShell):  
.\gradlew.bat test

---

### Также проект можно запускать из IntelliJ IDEA без терминала:

- через Gradle Tool Window:

  Tasks → application → run  
  Tasks → build → build

  Tasks → verification → test


- через Run Anything (Ctrl + Ctrl):
  
  gradle run  
  gradle test

---
### Как создать Junit-тест
Выделить имя record (MenteeProgress), где описано все и нажать Alt + Enter (Generate Test…) → выбери JUnit 5
---
## Packages

Основной пакет проекта:  
ru.mentee.power

Содержит следующие классы:

ProgressDemo — точка входа приложения (main), создаёт объект прогресса и выводит информацию в консоль.

MenteeProgress — record, хранящий данные о прогрессе студента. Содержит методы summary() и readyForSprint().

MenteeProgressTest — JUnit-тесты для проверки логики MenteeProgress с использованием AssertJ.

---

## MenteeProgress fields

menteeName — String — имя студента  
sprintNumber — int — номер спринта  
plannedHoursPerWeek — int — запланированные часы на спринт

---

## Rule

Если plannedHoursPerWeek >= 3 → sprint ready  
Иначе → backlog first

---

## Lesson

DVT-2 — Gradle-проект и базовый main

---
# DVT-3

## Правило веток: feature/DVT-X

В проекте используется правило именования веток:

- `master` — основная ветка, содержит стабильное состояние проекта
- `feature/DVT-X` — рабочая ветка под конкретный урок или задачу

Пример:
- `feature/DVT-3` — ветка для выполнения задания DVT-3

# DVT-4
Создана новая ветка feature/DVT-4

---
# DVT-5 — Чистый Git-репозиторий

### 🎯 Цель
Очистить репозиторий от мусорных файлов (IDE, сборка, OS-файлы), обновить `.gitignore` и закрепить правило **`git status clean` перед push**.

---

## 🧹 Очистка репозитория

### 1. Диагностика состояния репозитория

Перед очисткой проверяется состояние Git.

```bash
git status
```

Если в списке появляются файлы:

```
.idea/
build/
out/
.DS_Store
Thumbs.db
```

Значит в репозитории присутствует мусор.

---

### 2. Обновление `.gitignore`

В `.gitignore` добавлены правила для IDE, Gradle и OS.

```gitignore
# Gradle
.gradle/
build/
!gradle/wrapper/gradle-wrapper.jar
!gradle/wrapper/gradle-wrapper.properties

# IntelliJ IDEA
.idea/
*.iml
out/

# OS files
.DS_Store
Thumbs.db
```

`.gitignore` предотвращает попадание мусорных файлов в индекс.

---

### 3. Проверка работы `.gitignore`

После обновления `.gitignore` проверяется, какие файлы будут игнорироваться.

```bash
git check-ignore -v .DS_Store
git check-ignore -v .idea/workspace.xml
```

Git показывает правило `.gitignore`, которое совпало с файлом.

---

### 4. Удаление мусора из индекса

Если мусор уже был добавлен в Git, его нужно удалить из индекса.

Используется `--cached`, чтобы файлы **не удалились с диска**.

```bash
git rm -r --cached .idea build out
```

Проверка:

```bash
git status
```

Мусорные файлы исчезают из списка staged.

---

### 5. Санитарный коммит

После очистки создаётся отдельный коммит.

```bash
git commit -m "Очистить репозиторий; обновить .gitignore"
```

Проверка истории:

```bash
git log --oneline
```

Проверка изменений коммита:

```bash
git diff --name-status HEAD~1..HEAD
```

---

## 📋 Runbook

### Перед каждым push

Перед отправкой изменений необходимо убедиться, что репозиторий чистый.

```bash
git status
```

Ожидаемый результат:

```
nothing to commit, working tree clean
```

Это правило закреплено в Runbook проекта.

---

## 🔎 Проверка результата

Репозиторий считается очищенным, если:

- `.gitignore` содержит правила для IDE / Gradle / OS
- мусорные файлы удалены из индекса
- создан отдельный санитарный коммит
- `git status` показывает **clean**
- правило **status clean перед push** добавлено в Runbook
---
# DVT-6 — Циклы и контроль прогресса

### 🎯 Цель
Реализовать вычисление суммарного прогресса группы mentee с использованием цикла, создать тесты и запускать приложение через Gradle.

---

## 📊 Реализация логики прогресса

### 1. Модель данных Mentee

Для хранения информации о студенте используется record `Mentee`.

```java
public record Mentee(
  String name,
  String city,
  String track,
  int completedLessons,
  int totalLessons
) {}
---
# Кодстайл-гайд проекта DVT-7

Проект следует правилам **Google Java Style Guide** с адаптацией.  
Автоматическая проверка выполняется через **Checkstyle**.

Проверка code-style:


./gradlew checkstyleMain


---

## 1. Именование методов: camelCase

**До**


public void add_student(Student s) { }


**После**


public void addStudent(Student student) { }


**Почему**

Java Convention требует использовать **camelCase** для методов и переменных.

**Источник**

https://google.github.io/styleguide/javaguide.html#s5.3-camel-case

---

## 2. Именование полей и методов

**До**


private List<Student> student_List;

public void setStudent_List(Student student) { }


**После**


private List<Student> studentList;

public void setStudentList(Student student) { }


**Почему**

Символ `_` не используется в именах переменных и методов в Java.  
Применяется стиль **camelCase**.

**Источник**

https://google.github.io/styleguide/javaguide.html#s5.3-camel-case

---

## 3. Пробелы после if

**До**


if(student!= null ) student_List.add(student);


**После**


if (student != null) {
studentList.add(student);
}


**Почему**

Пробелы вокруг операторов делают код более читаемым.

**Источник**

Oracle Code Conventions — Whitespace

---

## 4. Длина имени метода

**До**


public List<Student> get_StudentList_from__This_very_LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOng_function()


**После**


public List<Student> getStudentList()


**Почему**

Слишком длинные имена ухудшают читаемость и нарушают ограничение длины строки.

**Источник**

https://google.github.io/styleguide/javaguide.html#s4.4-column-limit

---

## 5. Порядок импортов

**До**


import java.util.*;


**После**


import java.util.List;
import java.util.ArrayList;


**Почему**

Wildcard-импорты (`*`) скрывают реальные зависимости и ухудшают читаемость кода.

**Источник**

Google Java Style Guide — Imports

---

## 6. Фигурные скобки для if

**До**


if (condition) doSomething();


**После**


if (condition) {
doSomething();
}


**Почему**

Фигурные скобки обязательны даже для одной строки, чтобы избежать логических ошибок.

**Источник**

https://google.github.io/styleguide/javaguide.html#s4.1.1-braces-always-used