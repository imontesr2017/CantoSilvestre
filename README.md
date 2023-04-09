# CantoSilvestre

Idea 1: Web de gestión de jaulas de cría de pajaros

Instancias:web, usuarios, jaulas, pájaros, mensajes, foro.
Un usuario tiene 0 o más jaulas
Las jaulas contienen 0 o mas pájaros.
Los usuarios tienen 0 o mas mensajes.
El foro contiene todos los mensajes ordenados por fecha de última actualización.
Los usuarios registrados pueden colgar mensajes en un foro y gestionar sus jaulas y pajaros.
Los usuarios no resgistrados solo podrán ver los mensajes colgados en el foro.

Existe una segunda aplicación encargada de comprobar si los codigos de
los pájaros en las jaulas son códigos reales dados de alta y están a nombre de ese usuario.
Si los datos no coinciden se envia un mensaje de alerta al admin.

Capturas de pantalla de las ventanas:

![imagen](https://user-images.githubusercontent.com/85401502/223197358-7fb2122a-2110-4db4-9adf-05f5e38fc23d.png)
Página con los hilos de mensajes del foro y botón para crear nuevos hilos.

![imagen](https://user-images.githubusercontent.com/85401502/223197811-5f235a3a-2d07-4d91-a6a1-1cd3bc3d80f5.png)
Página de un hilo.

![imagen](https://user-images.githubusercontent.com/85401502/223198075-a8f7404a-084a-4a85-b86d-de0bcf2d2546.png)
Página para añadir un mensaje.

![imagen](https://user-images.githubusercontent.com/85401502/223199147-30bedae0-9b61-43cb-80e5-56f84d40c4e9.png)
Página con los usuarios de la aplicación.

![imagen](https://user-images.githubusercontent.com/85401502/223198851-67455ae2-a16a-49f4-9483-110b3069f3c8.png)
Página de un usuario.

![imagen](https://user-images.githubusercontent.com/85401502/223199641-0980aa95-0ad8-4650-97a3-4d6d3491df27.png)
Página nuevo usuario.

![imagen](https://user-images.githubusercontent.com/85401502/223199910-53e891a4-c994-43d6-853e-2253fd20a149.png)
Página con las jaulas de la aplicación.

![imagen](https://user-images.githubusercontent.com/85401502/223200314-812a7242-f35e-471e-be5e-d9efacb9892d.png)
Página de una jaula.

![imagen](https://user-images.githubusercontent.com/85401502/223200657-75e28325-7745-43de-bfdb-ae1e3a703d2e.png)
Página para añadir un pájaro nuevo a la jaula.

![imagen](https://user-images.githubusercontent.com/85401502/223203699-c7a02188-f657-42b0-9c13-e0ea8ae4ff7c.png)
Página para añadir una jaula.

![imagen](https://user-images.githubusercontent.com/85401502/223204217-5467f847-85f9-4366-a85e-f851d538cf7b.png)
Página para añadir un hilo.


Diagrama de navegación
![Diagrama Navegación](https://user-images.githubusercontent.com/85401502/223211844-34426b93-537f-48e4-b8f7-ffe1e05a3e44.jpg)


Diagrama UML

![imagen](https://user-images.githubusercontent.com/85401502/221797626-9c3b09c0-e9cf-4a89-97b4-892dc9a15ea7.png)

Definición de la interfaz entre el servicio y la aplicación web:
El servicio constiste en una base de datos gubernamental en la que se encuentran almacenados los datos de los Españoles con licencia de silvestrismo.
A cada una de estas licencias se le asignan una lista de identificadores únicos (anillas), que deben colocarse en las patas de los pajaros capturados para identificarlos.
No se pueden capturar un número de pájaros superior al número de anillas que se le asigna a la persona. Tampoco pueden capturarse pájaros sin una licencia de Silvestrismo, es una práctica ilegal.
El servicio interno permite saber si un id de usuario es real y tiene una licencia de Silvestrismo. Esta utilidad sirve para que la aplicación web solo admita a usuarios con licencia de silvestrismo.

  @GetMapping("/usuarios/{id}")
	public Boolean UsuarioCorrecto(Model model, @PathVariable String id)
  
Además, el servicio también permite saber si una anilla pertenece a un usuario concreto. Esta utilidad se aplica cuando un usuario quiere guardar un pajaro en el sistema. El sistema comprobará si la anilla del pájaro pertenece a ese usuario.

  @GetMapping("/usuarios/{id}/pajaro/{etiqueta}")
	public Boolean PajaroCorrecto(Model model, @PathVariable String id, @PathVariable String etiqueta)


Como instalar la aplicación en una máquina limpia.

Paso 1: Instalar java.

En el caso de que Java no este instalado en la máquina busque en su navegador "descargar java" + nombre de su sistema operativo.
Abra la pagina oficial de java (ejemplo linux) https://www.java.com/es/download/help/linux_x64_install.html y siga las intrucciones de instalación.

Paso2: Instalar y configurar MySQL.

Instale MySQL en su máquina. En caso de ser una máquina con SO tipo linux, ejecute la siguiente linea en la consola de comandos "sudo apt install mysql-server"

Utilize el siguiente comando para instalar la interfaz gráfica.
$ sudo apt-get install mysql-workbench

Abre la interfaz gráfica.
Crea una conexión con el nombre "CantoSilvestre" en localhost con puerto 3306.

Paso 3: Descarga el proyecto.

Enlace al proyecto en gitHub: https://github.com/imontesr2017/CantoSilvestre
Descargalo y descomprimelo.

Paso 4: Genera los jar
Utiliza el comando "sudo apt install maven"

Después ejecuta los siguientes comandos en sus las carpetas de los proyectos (donde se encuentra el archivo pom).
mvn clean install
mvn package
cd target/
java -jar nombreDelJar

Ejecutar el servicio interno antes de la aplicación.






