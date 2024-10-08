# JavaToAvalla
[![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://isaacmaffeis.github.io/javaToAvalla/javadoc/)
[![Build and Push CI Pipeline](https://github.com/isaacmaffeis/javaToAvalla/actions/workflows/maven-docker-pipeline.yml/badge.svg)](https://github.com/isaacmaffeis/javaToAvalla/actions/workflows/maven-docker-pipeline.yml)

<!--
[![pages-build-deployment](https://github.com/isaacmaffeis/javaToAvalla/actions/workflows/pages/pages-build-deployment/badge.svg)](https://github.com/isaacmaffeis/javaToAvalla/actions/workflows/pages/pages-build-deployment)
-->

**JavaToAvalla**  is a tool that allows you to automatically convert a Junit scenario into an avalla language one.
This tool was developed with the aim of simplifying the testing procedure of an Abstract State Machine (ASM),
and is part of the [evoAvalla](https://github.com/isaacmaffeis/evoAvalla) project.

## How to start

To use **JavaToAvalla**, you have two options: you can run it using **Maven** or **Docker Compose**:

### - Running with Maven:

If you prefer to run the application directly using Maven, make sure you have installed:

  - Java 8
  - Maven 3+

and follow these steps:

1. **Clone the Repository**:

   Start by cloning the repository, open a terminal and digit:

   ```shell
   git clone https://github.com/isaacmaffeis/javaToAvalla.git
   ```

   and navigate to the root of the cloned repository folder:
   ```shell
   cd path/to/cloned/repository
   ```


2. **Build the Project**:

   Use Maven to build the project:
   ```shell
   mvn clean package
   ```

3. **Run the Application**:

   You can run the application using the following command:
   ```shell
   java -jar .\target\javatoavalla-1.0-SNAPSHOT-jar-with-dependencies.jar -help
   ```

### - Running with Docker Compose:

If you want to use Docker Compose, you need to have [Docker Desktop](https://www.docker.com/products/docker-desktop)
installed and running on your pc, then you can pull the image from the dockerhub and use Docker Compose to run it.

1. **Create a docker-compose.yml file:**

    In the project root directory, create a `docker-compose.yml` file and add:
    
    ```yml
    version: '3'
    services:
      javatoavalla:
        build:
          context: .
          dockerfile: Dockerfile
        image: isaacmaffeis/javatoavalla:latest
        volumes:
          - ./input:/app/input
          - ./output:/app/output
    ```
    
2. **Run the Application:**

    To start the application with Docker Compose, run:
    ```shell
    docker compose pull
    ```
   to pull the latest Docker images for the services defined in the `docker-compose.yml` file from the Docker Hub.
   Then run:
    ```shell
    docker compose up
    ```
   to Builds and starts the containers defined in the docker-compose.yml file.
   And finally:
    ```shell
    docker compose run --rm javatoavalla -help
    ```
    to run the one-time specified command into the container (--rm : removes automatically the container once the command has completed.):

## Usage

After building the project, you can use it with the following command-line options:

### Command-Line Options

| Option             | Argument Type     | Description                                                                 |
|--------------------|-------------------|-----------------------------------------------------------------------------|
| `-help`            | None              | Prints the help message with a description of available options.            |
| `-input`           | String (required) | Path to the Java input file.                                                |
| `-stepFunctionArgs`| String (optional) | Path to the `stepFunctionArgs.txt` file. Defaults to `./input/stepFunctionArgs.txt`.|
| `-output`          | String (optional) | Specifies the output folder. Defaults to `./output/`.                       |
| `-clean`           | Boolean (optional)| Cleans the input and stepFunctionArgs files after the process.              |

#### example

Below there is an example of a typically used command:

with Maven:
```shell
java -jar .\target\javatoavalla-1.0-SNAPSHOT-jar-with-dependencies.jar -input "./input/exampleScenario.java" -clean true
```
with Docker Compose:
```shell
docker compose run --rm javatoavalla -input "./input/exampleScenario.java" -clean true
```

## Repository Structure
The project repository is organized into the following folders:

- `input/`: The application expects .java input files to be placed here before execution.

- `output/`: The output .avalla files generated by the services will be located here.

- `src/`: The main source code of the project.

- `.github/workflows/`: Contains the YAML files that define workflows for continuous integration (CI) and continuous deployment (CD) using GitHub Actions.
