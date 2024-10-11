# Java Spring Boot Application Tasks

## 1. Project Setup and Configuration
- [x] **1.1 Setup the Spring Boot Application:**
    - Create a new Spring Boot project (with dependencies: Web, Security, Data JPA, H2 (or other DB), Cache, OpenAPI).
    - Add required dependencies for caching, OpenAPI 3, and file handling.

- [x] **1.2 Configure H2 or another relational database:**
    - Set up database connection settings in `application.properties`.
    - Ensure the database is shared between two running instances.

- [x] **1.3 Configure Caching Mechanism:**
    - Use a distributed cache like Redis or Hazelcast.
    - Configure cache settings in `application.properties`.

## 2. File Upload Functionality
- [x] **2.1 Define a JPA Entity for File Metadata:**
    - Create a `File` entity with the following fields:
        - `fileName`, `fileType`, `uploadedBy`, `uploadedAt`, `metadata`.
    - Define a repository interface for database interactions.

- [x] **2.2 Create File Upload API:**
    - Create an API endpoint (`/upload`) to handle file uploads.
    - Validate files to restrict `.jpg`, `.gif`, `.png` file types.
    - Save file data and metadata to the database.

## 3. File Metadata Handling
- [x] **3.1 Integration with External API:**
    - Choose a public API (e.g., OpenWeatherMap).
    - Fetch metadata from the external API after a file is uploaded.

- [x] **3.2 Metadata Storage:**
    - Update the `File` entity to include fields for the metadata.
    - Save the additional metadata in the database along with the file.

## 4. Cache Management
- [x] **4.1 Caching the List of Uploaded Files:**
    - Cache the result of file list queries per user.
    - Ensure that subsequent requests are served from the cache and not from the database.

- [x] **4.2 Cache Expiration and Consistency:**
    - Keep the cache valid across multiple instances.
    - Implement cache invalidation or update the cache when a new file is uploaded.

## 5. Security
- [x] **5.1 Configure Authentication:**
    - Set up Spring Security for authentication (JWT, OAuth2, or Basic Auth).
    - Secure all endpoints except `/ping` or `/actuator`.

- [x] **5.2 Secure APIs for File Handling:**
    - Protect file upload and file retrieval APIs.
    - Ensure users can only access files they uploaded.

## 6. API Documentation and Visualization
- [ ] **6.1 Add OpenAPI 3.0 / Swagger:**
    - Add `springdoc-openapi-ui` dependency.
    - Configure Swagger to expose API documentation at `/swagger-ui.html`.

## 7. File List Retrieval
- [x] **7.1 Create API to Retrieve User's Uploaded Files:**
    - Create an API endpoint (`/files`) to fetch the list of uploaded files and their metadata.
    - Ensure the first query is fetched from the database, then cached.
    - All subsequent queries must fetch from the cache only.

## 8. Bonus Points Implementation
- [x] **8.1 Return File List After Upload:**
    - Modify the file upload API to return the updated list of uploaded files after each upload.

- [x] **8.2 Ensure the File List Retrieval Only Hits Cache:**
    - Ensure the `/files` API uses only the cache and not the database for subsequent requests.

## 9. Deployment on Multiple Instances
- [x] **9.1 Run Multiple Local Instances:**
    - Configure the application to run on two instances locally on different ports.
    - Ensure that the cache and database are shared between both instances.

