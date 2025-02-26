# 🐳 Spring Boot with MySQL & Adminer using Docker Compose

This project demonstrates how to containerize a **Spring Boot application** with **MySQL** and **Adminer** using **Docker Compose**.

## 📌 Project Structure

```
project-root/
├── src/main/java       # Java source code
├── src/main/resources  # Configuration files
├── Dockerfile          # Defines how to build the Spring Boot app image
├── docker-compose.yml  # Defines multi-container setup
├── README.md           # Project documentation
```

## 🔧 Services Overview

- **MySQL (****`mysql-service`****)**
    - Runs MySQL database on port **3306** (internal Docker network)
    - Stores data in a persistent volume
- **Spring Boot Application (****`springApplication-service`****)**
    - Connects to MySQL at `mysql-service:3306`
    - Exposes API on port **8080**
- **Adminer (****`adminer-service`****)**
    - Provides a web-based database UI
    - Runs on port **8081** (accessible from browser)

## 📡 Docker Network Diagram

***
![applicationOnDockerAchitecture.png](src/main/resources/static/images/applicationOnDockerAchitecture.png)
***
## 🚀 Getting Started

### 1️⃣ Clone the Repository

```sh
git clone https://github.com/beneite/SpringBootBasics.git
cd SpringWithDocker
```

### 2️⃣ Build and Run Containers

```sh
docker-compose up -d --build
```

- `-d` runs containers in detached mode
- `--build` forces a rebuild of the Spring Boot image

### 3️⃣ Check Running Containers

```sh
docker ps
```

### 4️⃣ Access Services

- **Spring Boot API** → `http://localhost:8080`
- **Adminer UI** → `http://localhost:8081`
    - **System:** `MySQL`
    - **Server:** `mysql-service`
    - **Username:** `ashish`
    - **Password:** `ashish@123`
    - **database:** `userManagementDb`

### 5️⃣ Stop and Remove Containers

```sh
docker-compose down -v
```

- `-v` removes named volumes to avoid leftover data
