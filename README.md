# Scripting Engine

A Spring Boot application to execute **JavaScript** and **Python** scripts dynamically within the JVM, as per the APIwiz assignment.

---

## 📌 Objective

Develop a Java-based utility to run JavaScript and Python scripts, returning results as Java objects. The engine should support:

- Raw script input
- Optional file-based script execution
- Use of standard and third-party libraries (within JVM compatibility)

---

## 🧩 Approach

- **Framework**: Spring Boot for modular service design and REST API exposure.
- **JavaScript**: Uses **GraalVM's graal.js** engine for modern and performant JavaScript support.
- **Python**: Uses **Jython** to execute Python 2.7 scripts within the JVM.
- **Thread-Safety**: A fresh interpreter/engine instance is created for each script execution.
- **Library Support**: Demonstrates loading of `lodash.js` (JavaScript) and `math.py` (Python) as sample libraries.

---

## 🛠 Tools and Libraries

| Tool         | Version  | Purpose                         |
|--------------|----------|----------------------------------|
| Spring Boot  | 3.3.4    | Web starter for REST API         |
| GraalVM JS   | 24.0.2   | JavaScript execution             |
| Jython       | 2.7.4    | Python execution in JVM          |
| Java         | 17       | Language version used            |

---

## ⚙️ Setup Instructions

### ✅ Prerequisites

- Java 17
- Maven 3.8+

### 🔨 Build the Project

```bash
mvn clean install

# 🧠 Script Execution API

This application provides a REST API to execute JavaScript and Python scripts using JVM-based engines.

---

## ▶️ Run the Application

```bash
mvn spring-boot:run

