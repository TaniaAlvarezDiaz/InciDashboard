[![Build Status](https://travis-ci.com/TaniaAlvarezDiaz/InciDashboard.svg?token=ENc151Ahc3Y3oqzaSf7S&branch=master)](https://travis-ci.com/TaniaAlvarezDiaz/InciDashboard)

# InciDashboard
Módulo que representa un cuadro de mandos, el cual permite al personal de gestión de incidencias poder visualizar y gestionar las incidencias que ocurren en el sistema.

# Author

Tania Álvarez Díaz ([@TaniaAlvarezDiaz](https://github.com/TaniaAlvarezDiaz))

# Execute

Para ejecutar el proyecto seguir los siguientes pasos:

1. Descargar [Apache Kafka](https://kafka.apache.org/quickstart) y seguir los siguientes pasos, todos ellos desde la consola CMD.
   * Ir a la capeta donde está el zip de Kafka descomprimido.
   * Ejecutar Apache Zookeeper. Para ello ejecutar:
     * En Windows: bin\windows\zookeeper-server-start.bat config\zookeeper.properties
     * En Mac: bin/zookeeper-server-start.sh config/zookeeper.properties
   * Se va a quedar bloqueada esa consola CMD, por tanto, sin cerrarla, abrir otra consola. Ejecutar Apache Kafka:
     * En Windows: bin\windows\kafka-server-start.bat config\server.properties
     * En Mac: bin/kafka-server-start.sh config/server.properties

2. Ejecutar la base de datos, en este caso HSQLDB.

4. Desde la carpeta InciDashboard ejecutar:
   * mvn spring-boot:run

5. Desde un navegador acceder a:
   * http://localhost:8092

