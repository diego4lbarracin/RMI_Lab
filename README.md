# RMI_Lab
Repository to host the code related with a lab about Remote Method Invocation for the Introduction to Distributed Systems class.
## How to Execute this Project
To execute this project follow the next steps:
- Change the ip address to the server's ip address in the Client.java and compile it.
- Change the ip address if needed in the server side if working on different networks.
`System.setProperty("java.rmi.server.hostname", "98.81.215.75");`
- Run the Server.class file and make sure the information inside the `DatosEstudiantes.txt` is loaded successfully.
  - If not, change the path to text file in `ServerImpl.java` to the correct one.
  `this.studentList = loadStudentsFromFile("DatosEstudiantes.txt");`
- Run the Client.class file and begin to interact with the server through the console-based UI.

That's all folks ;)