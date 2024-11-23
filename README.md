# To-Do List Application

This is a To-Do List application built with Java, Spring Boot, and Maven. It also uses Thymeleaf, HTML, TailwindCSS for front-end development.

## Features

- User registration and authentication
- Add, edit, and delete to-do items
- Secure password storage with BCrypt
- Form-based login and logout

## Technologies Used

- Java
- Spring Boot
- Maven
- Thymeleaf
- npm
- TailwindCSS

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- Node.js and npm

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/AnuragKrVerma/to_do-list.git
    cd to-do-list
    ```

2. Install backend dependencies:
    ```sh
    mvn clean install
    ```

3. Install frontend dependencies:
    ```sh
    cd src/main/resources/static
    npm install
    cd ../../../..
    ```

### Running the Application

1. Start the Spring Boot application:
    ```sh
    mvn spring-boot:run
    ```

2. Start TailwindCSS in watch mode:
    ```sh
    npx tailwindcss -i ./src/main/resources/static/css/input.css -o ./src/main/resources/static/css/output.css --watch
    ```

3. Open your browser and navigate to `http://localhost:8080`.

## Usage

- Register a new user account
- Log in with your credentials
- Create, edit, and delete to-do items
