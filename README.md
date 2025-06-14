🚀 URL Shortener Microservice

This is a Java-based microservice built with Spring Boot for shortening URLs and redirecting users to their original destinations. It also handles link validation and expiration. To run this service locally, you must have Java 17 or newer, Maven or Gradle, a Kubernetes cluster (e.g., Minikube), and a MongoDB instance.

🔧 Build the project using Maven or Gradle:

./mvnw clean install  
# or  
./gradlew build

# API Usage

🌐 After the pod is running, forward the port so you can access the application locally:

kubectl port-forward svc/urlshortener-dev 3000:3000 -n urlshortener-dev

The service will now be available at http://localhost:8080

📩 To create a shortened link, send a POST request:

curl -X POST http://localhost:8080 -H "Content-Type: application/json" -d '{"originalLink":"https://www.example.com/long-url"}'

Expected response:

{
  "originalLink": "https://www.example.com/long-url",
  "shortLink": "http://localhost:8080/abc123xyz"
}

🔁 To access the original URL through the shortened one, use:

curl -L http://localhost:8080/abc123xyz

# Notes
- Links are generated with random UUID strings (10 characters).
- Expired links will no longer redirect and will return an appropriate error.
- All logs are output through SLF4J.
- The project structure uses builders and constants for better maintainability.
