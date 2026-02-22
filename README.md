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