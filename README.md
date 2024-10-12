# AdvSoftwareDevG3

AdvSoftwareDev Group 3  
Online Bookstore System

**GitHub Repository**: https://github.com/nixonboros/AdvSoftwareDevG3

## Team Member Responsibilities

- **Jerry** 
  - Cleaned up index.jsp (added Best Sellers section)
  - Login/Register
  - User Dashboard (Edit and view account details, delete account) 
  - Page Footer
  - General data validation
  - Helped with catalogue features (add book, delete book, display book(s) in catalogue and best sellers in index.jsp, individual product pages and Manage Catalogue menu (staff))
  - Helped setup database
  - Set up testing and linked to Azure
  - UserTest test class (Testing: creating, updating and deleting of users in database)
  - BookTest test class (Testing: creating and deleting of books in database)

- **Nixon**
  - Customer Support Feature (user/staff support chat, viewing and opening/closing tickets)
  - Notifications Feature
  - Tomcat server setup 
  - Database setup (tables and contents)
  - General data validation
  - Helped with User/Staff login and registration
  - User/Staff page navigation bar
  - SupportTicketTest test class (Testing: creating, retrieving and adding messages to support tickets in database)

- **Sarneet** 
  - Staff Login/Registration + management feature
  - Created staff login and registration pages
  - Created staffUser database
  - Created staff dashboard and accompanying JSP pages
  - Book Catalogue feature
  - Worked on staff management feature - Update, search, delete and add staff users
  - Worked on book catalogue feature (add book, delete book, display book(s) in catalogue and Manage Catalogue menu (staff))
  - General data validation
  - Created and worked on staff navigation pages
  - Created filter for book catalogue product search page
  - StaffUser test class (Testing creating, retrieving, deleting and updating staff user data)


- **Nicolas**
  - Cleaned up styles.css
  - Added Shipment.java
  - Added ShipmentTest.java (CRUD testing)
  - Created shipment_table.jsp
  - Created shipment_tracking.jsp
  - Handled Orders Management feature
  - Handled Orders Tracking feature

- **Yuri** 
  - Checkout
  - Product Pages
  - Order Cart and Payment Features
  - Developed order cart functionality to manage selected books
  - Implemented 'Add to Cart' and 'Edit Cart' features for customer order management
  - Created payment functionality for storing customer payment information
  - Set up the payments database table for storing payment details securely
  - Integrated payment and order functionalities with the front-end JSP pages
  - Implemented payment processing and checkout workflows
  - Developed data validation logic for payment and order details

## Setup Instructions

### 1. Install NetBeans
- Install **NetBeans 23** (latest version).

### 2. Open Project
- Open the **online-bookstore-app** project directory

### 3. Set Up Tomcat Server
- Download the Tomcat server zip file: [Tomcat Server](https://dlcdn.apache.org/tomcat/tomcat-11/v11.0.0-M26/bin/apache-tomcat-11.0.0-M26-windows-x64.zip)
- Inside NetBeans, go to the **Services** tab > **Add Server** > Choose **Tomcat** and select the directory to the Tomcat server folder. Use:
  - **Username**: admin
  - **Password**: admin
- After adding the Tomcat server, go to the **Projects** tab > Right-click **online-bookstore-app** > **Properties** > **Run** > Change the server to **Tomcat**.

### 4. Set Up Database
- Download MySQL: [MySQL Download](https://dev.mysql.com/downloads/file/?id=532677) (server only).
- Download MySQL Connector: [MySQL Connector](https://downloads.mysql.com/archives/get/p/3/file/mysql-connector-j-8.0.32.zip)
- Go to the **Services** tab > Right-click **Databases** > **New Connection** > Choose **MySQL** > Add > Select the `mysql-connector-j-8.0.32.jar` file > Enter **Password**: root.
- Start the MySQL server > Right-click > Create database > Name it **`bookstoredb`** exactly.

## Repository Structure

The repository is organized as follows:
```
AdvSoftwareDevG3
├── online-bookstore-app
│   ├── src
│   │   ├── main
│   │   │   ├── webapp                   # Contains all web-related files
│   │   │   │   ├── css                  # Stylesheets for the application
│   │   │   │   ├── images               # Images used in the application
│   │   │   └── java                     # Contains Java source code
│   │   │       └── com
│   │   │           └── g3app
│   │   │               ├── controller   # Servlet and controller classes
│   │   │               ├── dao          # Data Access Objects
│   │   │               └── model        # Model classes representing data
│   │   └── test                         # Contains test classes
│   ├── target                           # Compiled output and artifacts
│   └── pom.xml                          # Maven Project Object Model file
├── azure-pipelines.yml                  # Configuration for Azure DevOps pipeline
└── README.md                            # Documentation file for the project
```

### Explanation of the Structure

- **AdvSoftwareDevG3**: The root folder of the project containing all necessary files and folders.
- **online-bookstore-app**: The main application directory that contains the source code and resources for the online bookstore system.
  - **src**: Contains the source code for the application.
    - **main**: Main source set where the application code resides.
      - **webapp**: This folder holds all web-related files such as JSP files, stylesheets (CSS), and images.
      - **java**: Contains Java source code organized under the package `com.g3app`, which includes:
        - **controller**: Contains servlet and controller classes that handle requests and responses.
        - **dao**: Contains Data Access Object classes that interact with the database.
        - **model**: Contains model classes that represent the application's data structures.
    - **test**: Contains test classes for the application to ensure the functionality of various components.
  - **target**: Directory where compiled output and artifacts from the build process are stored.
  - **pom.xml**: The Maven Project Object Model (POM) file, which defines project dependencies, build configurations, and other project settings.
- **azure-pipelines.yml**: A configuration file for setting up continuous integration and deployment using Azure DevOps.
- **README.md**: A documentation file that provides an overview of the project, setup instructions, and other relevant information.
