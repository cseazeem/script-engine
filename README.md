###Scripting Engine
A Spring Boot application to execute JavaScript and Python scripts dynamically within the JVM, as per the APIwiz assignment.

##Objective
Develop a Java-based utility to run JavaScript and Python scripts, returning results as Java objects, with support for external libraries and optional file-based script execution.

##Approach
Framework: Spring Boot for modularity and REST API exposure.
JavaScript: Uses GraalVM's graal.js engine for modern JavaScript support.
Python: Uses Jython for Python execution within the JVM.
Thread-Safety: Each script execution creates a new engine/interpreter instance to ensure thread-safety.
Library Support: Loads lodash.js for JavaScript and math.py for Python as sample libraries.

##Tools and Libraries
Spring Boot: 3.3.4 (Web starter for REST API).
GraalVM JS: 24.0.2 (for JavaScript execution).
Jython: 2.7.4 (for Python execution).
Java: 17.

##Setup Instructions
Prerequisites:
Java 17
Maven 3.8+


Build:mvn clean install

Run:mvn spring-boot:run


##Test the API:
Endpoint: POST http://localhost:8080/api/script/run
Sample Request Body:{
    "language": "JavaScript",
    "script": "_uniq([1, 2, 2, 3])",
    "isFile": false
}

{
    "language": "python",
    "script": "import math\nresult = math.sqrt(16)",
    "isFile": false
}





##Assumptions
Python scripts must set a result variable to capture the output.
Library paths (lodash.js, math.py) are hardcoded for simplicity.
File-based script execution assumes valid file paths relative to the project root.

##Design Decisions
GraalVM JS over Nashorn: Nashorn is deprecated; GraalVM offers better performance and modern JavaScript support.
Jython for Python: Ensures execution within the JVM, avoiding external processes.
Thread-Safety: New engine/interpreter per request to avoid shared state issues.
REST API: Simplifies testing and interaction with the scripting engine.
Library Loading: Preloads sample libraries; can be extended for dynamic library paths.

##Limitations
Jython supports Python 2.7 and limited third-party libraries (e.g., no numpy).
Library loading is static; dynamic library imports require additional configuration.

##Testing
Use the provided REST endpoint with tools like Postman or curl.
Test cases include basic arithmetic, library usage (_uniq for JS, math.sqrt for Python), and file-based scripts.

