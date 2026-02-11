# Project Overview

This project is a comprehensive patient management system that enables healthcare providers to efficiently manage patient data, appointments, and treatment records.

# Microservices Architecture

The application follows a microservices architecture, where each service is independently deployable and scalable. It includes services for authentication, patient management, appointment scheduling, and reporting.

# Services Description
- **Authentication Service**: Handles user registration, login, and token management.
- **Patient Management Service**: Manages patient records, including personal information and medical history.
- **Appointment Service**: Facilitates appointment scheduling and reminders.
- **Reporting Service**: Generates reports based on patient data and activities.

# Tech Stack
- Frontend: React.js
- Backend: Node.js with Express
- Database: MongoDB
- Testing: Jest, Mocha

# Prerequisites
- Node.js (version X.X.X)
- MongoDB (version Y.Y.Y)

# Getting Started Guide
1. Clone the repository:
   ```bash
   git clone https://github.com/praneeth172004/patient_management.git
   cd patient_management
   ```  
2. Install dependencies:
   ```bash
   npm install
   ```  
3. Setup your environment variables as outlined in the `.env.example` file.
4. Start the application:
   ```bash
   npm start
   ```

# Project Structure
```
patient_management/
├── client/            # Frontend application
├── server/            # Backend API services
├── README.md          # Project documentation
└── .env.example       # Example environment configuration
```

# Features
- User authentication and role management.
- Patient record creation and management.
- Appointment scheduling and reminders.
- Reporting tools for analyzing patient data.

# API Documentation
The API follows RESTful conventions. For detailed API specifications, check the `API_DOCS.md` file within the `server/` directory.

# Database Information
The database stores user and patient information in a structured way. For schema details, refer to the `DB_SCHEMA.md` in the `server/` directory.

# Configuration
Environment variables are used for configuration. Please refer to the `.env.example` file to set up your local environment correctly.

# Development Guidelines
- Follow the coding standards outlined in `CODING_GUIDELINES.md`.
- Ensure all tests are passing before making any pull requests.

# Deployment Instructions
1. Build the application:
   ```bash
   npm run build
   ```
2. Deploy the application using your preferred cloud provider.
   
   **Example**:
   - AWS: Use Elastic Beanstalk to deploy the Node.js app.
   - Heroku: Use the Heroku CLI to push your code directly.
   
By following these instructions, you will be able to set up, develop, and deploy the patient management system effectively.