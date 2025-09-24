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
3. Create a MySQL database named `rmt` and import the tables 
   (user, trip, population) from `baza.sql` (RMTServer/database).
4. Make sure MySQL is running on:
   URL:  jdbc:mysql://localhost:3306/rmt?useSSL=false&serverTimezone=Europe/Belgrade
   User: root
   Pass: root
5. Start the server:
   Run `RMTServer/main/Main.java`.
6. Start the client:
   Run `RMTClient/main/Main.java`.

