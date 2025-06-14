🚀 URL Shortener Microservice

This is a Java-based microservice built with Spring Boot for shortening URLs and redirecting users to their original destinations. It also handles link validation and expiration. To run this service locally, you must have Java 17 or newer, Maven or Gradle, a Kubernetes cluster (e.g., Minikube), and a MongoDB instance.

🔧 Build the project using Maven or Gradle:

./mvnw clean install  
# or  
./gradlew build

# API Usage

🌐 After the pod is running, forward the port so you can access the application locally:

kubectl port-forward svc/url-shortener 8080:8080

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

If the link is valid and not expired, it will redirect to the original URL.

ℹ️ Additional notes: links are generated using UUIDs trimmed to 10 characters. Expired links are automatically invalidated and will not redirect. Logs are printed using SLF4J. The service uses the builder pattern and centralized constants for maintainability. MongoDB must be properly configured and accessible inside your cluster. The Kubernetes service must be named `url-shortener`, or the `port-forward` command should be updated accordingly.

# Notes
- Links are generated with random UUID strings (10 characters).
- Expired links will no longer redirect and will return an appropriate error.
- All logs are output through SLF4J.
- The project structure uses builders and constants for better maintainability.
