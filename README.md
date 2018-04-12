# Typing-Race-Login-And-Data-Server

Demo Video: https://youtu.be/MvyhU7ZtN7w

The purpose of this server is to handle users signing up, logging in, their leaderboard and win/loss data, and requesting matches.

It is built using the Java EE framework and Apache Tomcat, and will use MYSQL for the database.
This server is currently unfinished and only has the functionality to test the game server, but I'm working on porting existing code from another project to finish it.

The reason I split the Game Server and Login Server was to avoid giving the Game Server the load of accessing persistent data in a database. That would lag the games.  
