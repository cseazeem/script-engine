# Scripting Engine

A Spring Boot application to execute **JavaScript** and **Python** scripts dynamically within the JVM, as per the APIwiz assignment.

---

## 📌 Objective

Create a Java utility to run JavaScript and Python scripts, with results returned as Java objects, supporting external libraries and optional file-based script running.
- Raw script input
- Optional file-based script execution
- Use of standard and third-party libraries (within JVM compatibility)

---

## 🧩 Approach

- **Framework**: Spring Boot for modularity and REST API exposure.
- **JavaScript**: Leverages GraalVM's graal.js engine for contemporary JavaScript support.
- **Python**: Leverages **Jython** for Python execution inside the JVM.
- **Thread-Safety**: Every script run has a new engine/interpreter instance to provide thread-safety..
- **Library Support**: Loads `lodash.js` for JavaScript and `math.py` for Python as sample libraries.

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

## 🖼️ Example Screenshot

![Script Engine Screenshot](images/Screenshot%20(26).png)
![Script Engine Screenshot](images/Screenshot%20(27).png)
![Script Engine Screenshot](images/Screenshot%20(28).png)

🧠 Script Execution API

This application provides a REST API to execute JavaScript and Python scripts using JVM-based engines.

📮 API Usage
Endpoint

POST http://localhost:8080/api/script/run

Sample Request Body

JavaScript Example

{
  "language": "JavaScript",
  "script": "_uniq([1, 2, 2, 3])",
  "isFile": false
}

Python Example
{
  "language": "python",
  "script": "import math\nresult = math.sqrt(16)",
  "isFile": false
}

# 📎 Assumptions
Python scripts must assign the output to a variable named result.

Library paths like lodash.js (for JavaScript) and math.py (for Python) are hardcoded for simplicity.

File-based script execution assumes paths are relative to the project root directory.

# Design Decisions
✅ Nashorn is deprecated; GraalVM has improved performance and support for modern JavaScript.

✅ Jython for Python: Guarantees execution in the JVM, eluding external processes.

✅ Thread-Safety: Engine/interpreter instance per request to prevent shared state problems.

✅ REST API: Ease of testing and communication with scripting engine.

✅ Library Loading: Prereload sample libraries; may be extended to dynamic library path.


Testing

Utilize the available REST endpoint via tools such as Postman or curl.
Test cases cover simple arithmetic, library calls (_uniq for JS, math.sqrt for Python), and file-based scripts.

Test Scripts Include:
Basic arithmetic operations
Utility functions like:

_uniq from Lodash (JS)
math.sqrt (Python)

File-based script execution

### 🔨 Build the Project

```bash
mvn clean install
