# GitHub Actions badge
[![Java CI with Checkstyle and Jacoco](https://github.com/mnnnssr/devtools/actions/workflows/ci.yml/badge.svg)](https://github.com/mnnnssr/devtools/actions/workflows/ci.yml)

## Технологический стек проекта

### Языки и платформы
- **Java 25 LTS** — основной язык разработки
- **Gradle 9.2** — система сборки (через Gradle Wrapper)

### Инструменты качества кода
- **Checkstyle** — статический анализ стиля кода
  - Конфигурация: `config/checkstyle/checkstyle.xml`
  - Запуск: `./gradlew checkstyleMain`
- **JUnit 5** — фреймворк тестирования
  - Запуск: `./gradlew test`

### CI/CD
- **GitHub Actions** — автоматическая проверка PR
  - Checkstyle на каждый коммит
  - Тесты на каждый коммит
  - Конфигурация: `.github/workflows/`

### Правила кода
- Стиль: Google Java Style Guide (через Checkstyle)
- Коммиты: Conventional Commits (`feat:`, `fix:`, `docs:`)
- Ветки: `feature/DVT-X` для задач, `master` — основная
- Pull Request: обязателен для слияния в master

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
```
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

---

# Code Review Checklist

Используйте этот чеклист для само-ревью перед запросом ревью у ментора:

### Функциональность
- [ ] Код решает поставленную задачу полностью
- [ ] Обработаны граничные случаи (null, пустые данные, экстремальные значения)
- [ ] Обработка ошибок реализована корректно

### Тесты
- [ ] Добавлены тесты для нового функционала (или обновлены существующие)
- [ ] Все тесты проходят локально: `./gradlew test`
- [ ] Покрыты позитивные и негативные сценарии
- [ ] JaCoCo coverage ≥ 80% для нового кода

### Читаемость и стиль
- [ ] Имена переменных, методов и классов отражают назначение
- [ ] Нет дублирования кода (DRY principle)
- [ ] Checkstyle проходит без ошибок: `./gradlew checkstyleMain`
- [ ] Нет закомментированного кода или отладочного вывода (`System.out.println`)

### Документация
- [ ] README обновлён (если добавлена новая функциональность)
- [ ] Публичные методы имеют JavaDoc (если применимо)
- [ ] Примеры использования актуальны
- [ ] Runbook обновлён (если изменились команды запуска/проверки)

### Производительность и безопасность
- [ ] Нет очевидных проблем производительности
- [ ] Нет хардкода паролей, токенов или конфиденциальных данных

---
## Примеры Code Review комментариев

### Хорошие комментарии (конструктивные)

**Пример 1:**

**Проблема:** Метод `calculateDiscount` (строка 45) имеет 3 вложенных if-else и 40 строк.
**Почему это важно:** Сложная логика плохо тестируется и тяжело поддерживается.
**Предложение:** Вынести каждое условие в отдельный метод (например, `isEligibleForBonusDiscount()`)
и использовать паттерн Strategy для разных типов скидок.



**Пример 2:**

**Проблема:** Тест `testProcessOrder` (строка 78) проверяет только успешный сценарий.
**Почему это важно:** Не проверена обработка ошибок при недостаточном балансе.
**Предложение:** Добавить тест `testProcessOrder_InsufficientBalance_ThrowsException()`
с использованием `assertThatThrownBy()`.



### Плохие комментарии (неконструктивные)

**Пример 1:**

Этот код ужасен, полностью переписать.


**Почему плохо:** Нет конкретики (что именно плохо), нет предложения (как исправить),
токсичный тон (демотивирует автора).

**Пример 2:**

Здесь лучше использовать Stream API.


**Почему плохо:** Нет объяснения почему лучше, нет примера как переписать,
неясно какую проблему это решает.

---

## Результаты само-ревью DVT-9


### Найденные проблемы

#### 1. Недостаточное описание CI в README
**Файл:** README.md  
**Проблема:** В README есть CI badge, но отсутствует описание, какие именно проверки выполняет CI (Checkstyle, тесты, сборка).  
**Почему важно:** Другие разработчики не понимают, что именно проверяется автоматически при push и в PR.  
**Исправление:** Добавить описание CI (какие шаги выполняются: checkstyle, test, build).

---

#### 2. Возможный отладочный вывод в коде
**Файл:** Java-классы проекта  
**Проблема:** При разработке мог остаться `System.out.println` для отладки.  
**Почему важно:** Отладочный вывод засоряет консоль и не используется в production-коде.  
**Исправление:** Удалить отладочные выводы или заменить на логирование (в будущем).

---

#### 3. Возможный закомментированный код
**Файл:** Java-классы проекта  
**Проблема:** В коде могут оставаться закомментированные участки старой логики.  
**Почему важно:** Закомментированный код ухудшает читаемость и усложняет поддержку. История изменений уже хранится в Git.  
**Исправление:** Удалить закомментированные участки кода.

---
# Информационный поиск — результаты DVT-11

### Запросы и источники
| № | Запрос | Операторы | Официальный источник | Альтернатива | Статус | Дата проверки |
|---|--------|-----------|----------------------|--------------|--------|---------------|
| 1 | Lombok Gradle Short | site:search.maven.org "lombok" OR site:mvnrepository.com "lombok" | https://search.maven.org/artifact/org.projectlombok/lombok | https://mvnrepository.com/artifact/org.projectlombok/lombok | 200 OK | 28.03.2026 |
| 2 | Java Stream API | site:docs.oracle.com "stream api" intitle:api java | https://docs.oracle.com/en/java/javase/21/docs/api/java/util/stream/package-summary.html | https://www.baeldung.com/java-8-streams | 200 OK | 28.03.2026 |
| 3 | IntelliJ IDEA keymap | site:www.jetbrains.com/help "keymap" "intellij idea" | https://www.jetbrains.com/help/idea/keymap-reference.html | - | 200 OK | 28.03.2026 |



## AI-промпты и сравнение ассистентов

#### Промпт 1: План решения

**Задача:**  
добавить валидацию email

**Контекст:**  
Java 25, есть класс Student

**Ограничения:**  
без regex

**Ожидаемый результат:**  
план решения, без кода

**Критерии успеха:**  
проверка наличия `@` и точки

---

**AI-1 (ChatGPT):**

1. Проверить, что строка email не равна null и не пустая
2. Найти позицию символа `@`
3. Убедиться, что `@` не первый и не последний символ
4. Проверить наличие точки `.` после `@`
5. Проверить, что между `@` и `.` есть хотя бы один символ

---

**AI-2 (DeepSeek):**

1. Проверить, что email не null и не пустой
2. Проверить, что есть ровно один символ `@`
3. Разделить строку на локальную и доменную части
4. Проверить, что доменная часть содержит точку `.`
5. Проверить, что части не начинаются и не заканчиваются точкой

---

**Сравнение:**

Оба ассистента предложили схожий подход с проверкой символа `@` и точки.  
ChatGPT дал более простой и понятный алгоритм, а DeepSeek предложил более детализированный подход.  
Я выбрала подход ChatGPT, так как он проще для реализации.

---

#### Промпт 2: Проверка кода

**Код для проверки:**

```java
public void addStudent(Student student) {
    if (student != null) {
        studentList.add(student);
    }
}
```

---

**AI-1 (ChatGPT):**

1. Нет обработки случая, когда student равен null  
2. Возможен NullPointerException, если studentList не инициализирован  
3. Нет проверки на дубликаты студентов  
4. Нет валидации данных внутри объекта Student  

---

**AI-2 (DeepSeek):**

1. Проверка на null есть, но нет явной обработки  
2. Нет проверки на инициализацию списка studentList  
3. Метод игнорирует null без уведомления  
4. Не проверяется корректность объекта Student  

---

**Сравнение:**

Оба ассистента указали на недостаточную обработку null и отсутствие валидации.  
ChatGPT больше сфокусировался на потенциальных ошибках выполнения, а DeepSeek отметил поведение метода при передаче null.  
Оба ответа полезны и показывают, что метод можно улучшить.



# Личный глоссарий терминов Dev Tools

### Категория: Java-экосистема

#### JDK — Java Development Kit

**Определение:** The Java Virtual Machine is the cornerstone of the Java platform. It is the component of the technology responsible for its hardware- and operating system-independence, the small size of its compiled code, and its ability to protect users from malicious programs.

**Контекст использования:** JDK необходим для компиляции Java-кода в байт-код и создания исполняемых JAR-файлов. Без JDK невозможно собрать Java-проект.

**Пример:** После установки JDK выполняем `java -version` для проверки версии. В IntelliJ IDEA настраиваем Project SDK.

**Источник:** https://docs.oracle.com/javase/specs/jvms/se26/html/jvms-1.html#jvms-1.1

---

#### JRE — Java Runtime Environment

**Определение:** The JRE is the software environment in which programs compiled for a typical JVM implementation can run. The runtime system includes the code necessary to run Java programs, dynamically link native methods, manage memory, and handle exceptions, as well as an implementation of the JVM.

**Контекст использования:** JRE нужен только для *запуска* уже скомпилированных Java-приложений. Разработчику не нужен — достаточно JDK.

**Пример:** Пользователь запускает JAR-файл через `java -jar app.jar`. Для этого на его компьютере должна быть установлена JRE.

**Источник:** https://docs.oracle.com/cd/E19620-01/805-4031/6j3qv1oec/index.html

---

#### JVM — Java Virtual Machine

**Определение:** The Java Virtual Machine is the cornerstone of the Java platform. It is the component of the technology responsible for its hardware- and operating system-independence, the small size of its compiled code, and its ability to protect users from malicious programs.
The Java Virtual Machine is an abstract computing machine. Like a real computing machine, it has an instruction set and manipulates various memory areas at run time.

**Контекст использования:** JVM обеспечивает кроссплатформенность Java («Write Once, Run Anywhere»). Код компилируется в байт-код, который JVM интерпретирует под конкретную ОС.

**Пример:** Один и тот же JAR-файл работает на Windows, macOS и Linux, потому что для каждой ОС своя реализация JVM.

**Источник:** https://docs.oracle.com/javase/specs/jvms/se25/html/jvms-1.html

---

#### Gradle Wrapper — Gradle Wrapper

**Определение:** The Gradle Wrapper is a script that invokes a declared version of Gradle, downloading it beforehand if necessary. As a result, developers can get up and running with a Gradle project quickly without having to follow manual installation processes.

**Контекст использования:** Используется для обеспечения одинаковой версии Gradle у всех разработчиков и в CI. Команды `./gradlew build` вместо `gradle build`.

**Пример:** В проекте есть файлы `gradlew`, `gradlew.bat` и папка `gradle/wrapper/`. Разработчик клонирует репозиторий и запускает `./gradlew build` — Gradle Wrapper сам скачает нужную версию.

**Источник:** https://docs.gradle.org/current/userguide/gradle_wrapper.html

---

#### Dependency — Dependency

**Определение:** A dependency is a pointer to another piece of software required to build, test, or run a module. For more information, see the section on declaring dependencies.

**Контекст использования:** Gradle автоматически скачивает зависимости из репозиториев (например, Maven Central) и добавляет их в classpath.

**Пример:** `implementation 'org.projectlombok:lombok:1.18.30'` — объявление зависимости от библиотеки Lombok.

**Источник:** https://docs.gradle.org/current/userguide/glossary.html

---

#### Build Tool — Инструмент сборки

**Определение:** A build system or build automation tool automates the compilation and linking of source code into an executable program or library. It manages dependencies and ensures consistent builds across different environments

**Контекст использования:** Build tools manage the entire build lifecycle — dependency resolution, compilation, testing, and packaging. In our project, Gradle is used to run `./gradlew build`, which compiles code, runs Checkstyle, executes tests, and produces a JAR artifact.

**Пример:** `./gradlew build` — команда, которая запускает полный цикл сборки: компиляция (`compileJava`), тесты (`test`), Checkstyle (`checkstyleMain`), и создание JAR-файла в папке `build/libs/`.

**Источник:** https://en.wikipedia.org/wiki/Build_tool

---

#### Artifact — Артефакт

**Определение:** An artifact is a file or directory produced by a build, such as a JAR, WAR, or native executable. In Maven/Gradle, artifacts are identified by coordinates: `groupId:artifactId:version`.

**Контекст использования:** After compiling and packaging a Java project, the build tool produces an artifact (e.g., `devtools-1.0.0.jar`). This artifact can be deployed to a server, shared with other projects, or published to a repository.

**Пример:** После выполнения `./gradlew jar` артефакт создаётся в `build/libs/devtools.jar`. Этот JAR-файл можно запустить командой `java -jar devtools.jar`.

**Источник:** https://docs.gradle.org/current/userguide/dependency_management_terminology.html

---

### Категория: Инструменты разработки

#### IDE — Integrated Development Environment

**Определение:** An integrated development environment (IDE) is a software application that provides comprehensive facilities to computer programmers for software development. An IDE normally consists of at least a source code editor, build automation tools, and a debugger.
**Контекст использования:** IDE ускоряет разработку за счёт автодополнения, рефакторинга, интеграции с Git и отладчиком.

**Пример:** IntelliJ IDEA, Eclipse, VS Code. Мы используем IntelliJ IDEA.

**Источник:** https://en.wikipedia.org/wiki/Integrated_development_environment

---

#### SDK — Software Development Kit

**Определение:** A software development kit (SDK) is a collection of software development tools in one installable package. It facilitates the creation of applications by having a compiler, debugger, and sometimes a software framework. They are normally specific to a hardware platform and operating system combination.
**Контекст использования:** The Java SDK is the JDK (Java Development Kit) — it includes the compiler (`javac`), runtime (`java`), archiver (`jar`), and core libraries.

**Пример:** После установки JDK (Java SDK) мы можем скомпилировать код: `javac Main.java` и запустить его: `java Main`.

**Источник:**  https://en.wikipedia.org/wiki/Software_development_kit

---

#### Git — Git

**Определение:** Git is a distributed version control system that tracks changes in source code during software development. Each working directory is a full-fledged repository with complete history and version tracking capabilities.

**Контекст использования:** Git allows multiple developers to work on the same project simultaneously, creates branches for new features, merges changes, and maintains a complete history of all modifications.

**Пример:** `git add README.md` — подготовить изменения; `git commit -m "docs: update glossary"` — сохранить снимок; `git push origin feature/DVT-12` — отправить на удалённый репозиторий.

**Источник:** https://git-scm.com/docs/git

---

#### Repository — Репозиторий (Git context)

**Определение:** A repository (or "repo") is a collection of refs together with an object database containing all objects which are reachable from the refs, possibly accompanied by meta data from one or more porcelains. A repository can share an object database with other repositories via alternates mechanism.

**Контекст использования:** Repositories can be local (on a developer's machine) or remote (hosted on GitHub, GitLab, Bitbucket). Developers clone remote repositories to their local machines, make changes, commit them, and push back to the remote.

**Пример:** `git clone https://github.com/username/devtools.git` — скопировать удалённый репозиторий локально. Локальный репозиторий: `Z:\Projects\devtools\.git`

**Источник:** https://git-scm.com/docs/gitglossary

---

#### Commit — Commit

**Определение:** A Git operation that records changes to the repository. Each commit has a unique SHA-1 hash and contains a snapshot of the project at a specific time.

**Контекст использования:** Перед коммитом нужно сделать `git add` для отслеживания изменений. Коммиты создают историю проекта.

**Пример:** `git commit -m "feat: добавить глоссарий в README"`

**Источник:** https://git-scm.com/docs/git-commit

---

#### Branch — Branch

**Определение:**  A "branch" is a line of development. The most recent commit on a branch is referred to as the tip of that branch. The tip of the branch is referenced by a branch head, which moves forward as additional development is done on the branch. A single Git repository can track an arbitrary number of branches, but your working tree is associated with just one of them (the "current" or "checked out" branch), and HEAD points to that branch.

**Контекст использования:** Новые фичи разрабатываются в отдельных ветках (`feature/DVT-12`). После завершения — ветка сливается через Pull Request.

**Пример:** `git checkout -b feature/DVT-12` — создать и переключиться на новую ветку.

**Источник:** https://git-scm.com/docs/gitglossary

---

#### Pull Request — Pull Request (или Запрос на слияние)

**Определение:** Pull requests are proposals to merge code changes into a project. A pull request is GitHub's foundational collaboration feature, letting you discuss and review changes before merging them. This helps teams work together, catch issues early, and maintain code quality.

**Контекст использования:** PR обязателен перед слиянием в `master`. В PR запускается CI (проверки, тесты) и проходит Code Review.

**Пример:** Создали ветку `feature/DVT-12`, запушили, открыли PR на GitHub → коллеги комментируют, автор исправляет → после одобрения мержим.

**Источник:** https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/about-pull-requests

---

#### Checkstyle — Checkstyle

**Определение:** A static code analysis tool that checks Java source code for adherence to a set of coding style rules (e.g., naming conventions, indentation, line length).

**Контекст использования:** Checkstyle запускается автоматически в CI и локально через Gradle. Нарушения стиля делают сборку красной.

**Пример:** `./gradlew checkstyleMain` — проверяет код в `src/main/java` по правилам из `config/checkstyle/checkstyle.xml`.

**Источник:** https://checkstyle.org/

---

#### Debug — Debug

**Определение:** The process of identifying, analyzing, and removing errors (bugs) in software. In IDEs, debugging typically involves breakpoints, step execution, and variable inspection.

**Контекст использования:** Запуск приложения в режиме Debug вместо обычного Run позволяет приостанавливать выполнение и смотреть состояние переменных.

**Пример:** Ставим Breakpoint на строке с ошибкой, запускаем Debug (`Shift+F9`), программа останавливается, проверяем значения через Evaluate.

**Источник:** https://www.jetbrains.com/help/idea/debugging-code.html

---

#### Breakpoint — Точка остановки

**Определение:** A breakpoint is a marker set in source code that pauses program execution during debugging, allowing developers to inspect variables, evaluate expressions, and step through code line by line.

**Контекст использования:** Breakpoints help identify bugs by freezing the program at a specific line. When execution hits a breakpoint, the debugger opens, showing current variable values, call stack, and threads.

**Пример:** В IntelliJ IDEA кликните на левой полосе рядом с номером строки, чтобы поставить Breakpoint (красная точка). Затем запустите Debug (`Shift+F9`). Программа остановится на этой строке.

**Источник:** https://www.jetbrains.com/help/idea/debugging-code.html#breakpoints

---

### Категория: Процессы и практики

#### Code Review — Code Review

**Определение:** Code review is a systematic examination of computer source code intended to find and fix mistakes overlooked in the initial development phase, improving software quality and developer skills.

**Контекст использования:** Автор PR создаёт запрос, ревьюверы оставляют комментарии, автор исправляет. После одобрения — слияние.

**Пример:** В комментарии к PR: «Пожалуйста, добавь JavaDoc к публичному методу». Автор исправляет и пушит.

**Источник:**
- Agile Alliance Glossary: https://www.agilealliance.org/glossary/code-review (недоступно, 404, дата проверки: 09.04.2026)
- Использована альтернатива: https://en.wikipedia.org/wiki/Code_review

---

#### CI/CD — Continuous Integration / Continuous Deployment

**Определение:** CI is the practice of merging all developer working copies to a shared mainline several times a day, with automated building and testing. CD automatically deploys successfully tested changes to production or staging environments.

**Контекст использования:** В нашем проекте GitHub Actions запускает Checkstyle и тесты на каждый Push и Pull Request.

**Пример:** При создании PR `feature/DVT-12 → master` GitHub Actions автоматически выполняет `./gradlew checkstyleMain test`. Если всё зелено — можно мёржить.

**Источник:**
- Agile Alliance Glossary: https://www.agilealliance.org/glossary/ci-cd (недоступно, 404, дата проверки: 09.04.2026)
- Использована альтернатива: https://en.wikipedia.org/wiki/CI/CD

---

#### Runbook — Сценарий действий

**Определение:** Runbooks are a collection of documented procedures that explain how to carry out a particular process, be it starting, stopping, debugging, or troubleshooting a particular system.

**Контекст использования:** Runbooks are essential in DevOps and SRE (Site Reliability Engineering). They reduce downtime by giving clear instructions for restarting services, restoring data, scaling resources, or handling alerts.

**Пример:** Runbook для упавшего CI билда: 1) Проверить ошибку в логах GitHub Actions; 2) Запустить `./gradlew checkstyleMain` локально; 3) Исправить нарушения в `config/checkstyle/checkstyle.xml`; 4) Закоммитить и запушить фикс.

**Источник:** https://docs.devnet-academy.com/docs/gitlab_v14.10/ee/user/project/clusters/runbooks/index.html

---

## Вопросы по сложным терминам

### Вопрос 1: Различие JDK / JRE / JVM

**Задача:** Понять практическую разницу между JDK, JRE и JVM — когда и что нужно устанавливать разработчику, а что — конечному пользователю.

**Контекст:** Изучил определения в Oracle Docs, но неясно, почему у меня установлен JDK 25, а в `build.gradle` нет упоминания JRE. При этом приложение запускается через `java -jar`.

**Ограничения:** Пробовал искать на Stack Overflow, но ответы смешивают версии Java 8 и 21. Нужно понимание для Java 25.

**Ожидаемый результат:** Чёткое понимание: JDK нужен для компиляции (содержит `javac`), JRE — только для запуска (содержит `java`), JVM исполняет байт-код и находится внутри обоих. Разработчику достаточно JDK (он включает JRE).

**Критерии успеха:** Могу объяснить коллеге разницу за 30 секунд и показать пример: `javac` → JDK, `java` → JRE, байт-код → JVM.

---

### Вопрос 2: Как работает механизм Pull Request в связке Git + GitHub?

**Задача:** Понять, как Pull Request связывает Git-операции (Commit, Push, Merge) и GitHub-механизмы (Code Review, CI, комментарии).

**Контекст:** Понимаю, что PR создаётся на GitHub после `git push`. Но не ясно: где хранятся комментарии к PR — в Git или GitHub? Что происходит при `git commit --amend` после открытия PR?

**Ограничения:** Читал Git Docs и GitHub Docs, но в них не описана связь между двумя системами.

**Ожидаемый результат:** PR — это объект только на GitHub (не в Git). Комментарии, статусы CI и лейблы хранятся в GitHub, не в репозитории. После `git commit --amend` и `git push --force` PR автоматически обновляется (ветка та же, но история коммитов изменилась).

**Критерии успеха:** Могу описать последовательность: `git commit` → `git push` → создание PR на GitHub → Code Review → `git commit --amend` → `git push --force` → PR показывает обновлённые коммиты → слияние через GitHub → `git pull` локально.

---
