<h1 align="center"> Ten Pin Bowling Game </h1> <br>

<p align="center">
  A command-line application to score a game of ten-pin bowling
</p>

<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Project Build](#project-build)
* [Usage](#usage)




<!-- ABOUT THE PROJECT -->
## About The Project
This project is designed to test your knowledge of back-end web technologies, specifically in
Java and assess your ability to create back-​ end products with attention to details, standards,
and reusability.

### Built With

* [Gradle](https://gradle.org)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [jUnit](https://junit.org/junit5/)



## Getting Started


### Prerequisites

To compile this project it is necessary to have Java 8+ and Gradle 6.5+ installed and configured in your computer.

### Project Build

1. Clone the repo
```sh
git clone https://github.com/casmota/tenpinbowling.git
```
2. Change to correct directory
```sh
cd tenpinbowling
```
3. Run Gradle
```JS
./gradlew clean bootJar
```



<!-- USAGE EXAMPLES -->
## Usage

After the application has been successfully built, go to a command line terminal, change to the build folder `tenpinbowling/build` and run it informing the data input file path as argument using the command: `java -jar tenpinbowling-1.0.0.jar /src/test/resources/integration-tests/IT001_sample_input.txt`.


