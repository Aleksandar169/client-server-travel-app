# client-server-travel-app

# EU Travel Registration App

A Java client-server application that allows citizens of Serbia to register their trips to EU countries.  
The project was developed as part of the *Network Programming* course and consists of three modules: **Client**, **Server**, and **Common**.

## Features
- User registration and login
- Trip registration (country, travel dates, transport type, payment info)
- Trip update (up to 48h before EU entry)
- Trip history overview
- Data persistence in MySQL
- Error handling and multi-client support

## Technologies
- Java SE (JDK 17)
- Swing (GUI)
- TCP/IP Sockets
- MySQL
- NetBeans IDE

## How to Run
1. Clone the repository
2. Open the projects (RMTCommon, RMTClient, RMTServer) in NetBeans.
3. Build RMTCommon:
   - Clean and Build the project `RMTCommon`.
   - This will generate a `.jar` file (e.g., `RMTCommon.jar`) in the `dist` folder.
4. Add the RMTCommon JAR to Client and Server:
   - In NetBeans, right-click on `RMTClient` → Properties → Libraries → Add JAR/Folder → select `RMTCommon/dist/RMTCommon.jar`.
   - Repeat the same for `RMTServer`.
5. Create a MySQL database named `rmt` and import the tables 
   (user, trip, population) from `baza.sql` (RMTServer/database).
6. Make sure MySQL is running on:
   URL:  jdbc:mysql://localhost:3306/rmt?useSSL=false&serverTimezone=Europe/Belgrade
   User: root
   Pass: root
7. Add MySQL Connector/J:
   - Download from https://dev.mysql.com/downloads/connector/j/
   - Add the `.jar` file to the project libraries in NetBeans for all three modules (RMTCommon, RMTClient, RMTServer).
8. Check ports:
   - **Server port** → default is `9000` (defined in RMTServer).  
     Make sure this port is free and not blocked by firewall or another application.  
     If needed, change the port in the server configuration class.
9. Start the server:
   Run `RMTServer/main/Main.java`.
10. Start the client:
   Run `RMTClient/main/Main.java`.

