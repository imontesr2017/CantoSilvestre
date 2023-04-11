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

Vista de un usuario no registrado.

![imagen](https://user-images.githubusercontent.com/85401502/230796909-ffb970c1-a681-4f7d-adda-19b8bcddaa41.png)
Página con los hilos de mensajes del foro. También puedes hacer login en la parte superior.

![imagen](https://user-images.githubusercontent.com/85401502/230796949-480cdf37-aee5-4101-b7c0-50ec2415f3cd.png)
Página de un hilo. También puedes hacer login en la parte superior.

![imagen](https://user-images.githubusercontent.com/85401502/230796976-7517a847-d2a7-468c-b223-97ddfe407b17.png)
Página de login incorrecto.

![imagen](https://user-images.githubusercontent.com/85401502/230797006-d647fae5-3847-4bf8-a5a7-73abd35269c7.png)
Página de login.

Vista de usuario registrado (no administrador).

![imagen](https://user-images.githubusercontent.com/85401502/230797044-0ba7883e-ad8a-461f-a97f-cfd95c7c30df.png)
Página con los hilos de mensajes del foro. También puedes desloguearte en la parte superior.

![imagen](https://user-images.githubusercontent.com/85401502/230797168-7ccd64d1-7499-4ce8-86d0-ed1464d5b761.png)
Página de un hilo.

![imagen](https://user-images.githubusercontent.com/85401502/230797194-773a1170-e471-4eb9-9402-381657ead261.png)
Página para añadir un mensaje.

![imagen](https://user-images.githubusercontent.com/85401502/230797244-b3a311ec-9e60-464c-a7a2-e818678b5fce.png)
Página de un usuario.

![imagen](https://user-images.githubusercontent.com/85401502/230797289-d89f6863-071c-4e09-a978-a4a93258078c.png)
Página para añadir un pájaro nuevo.

![imagen](https://user-images.githubusercontent.com/85401502/230805268-1547cbc6-d8dd-4f11-9ec5-87abdddf20c8.png)
Error id de pajaro ya registrado.

![imagen](https://user-images.githubusercontent.com/85401502/230805307-0f26cd57-c7c2-46e6-b1c5-4fde64b3a007.png)
Error id de pajaro no pertenece al usuario.

![imagen](https://user-images.githubusercontent.com/85401502/230797325-72a963c9-df43-464b-8d5a-196fe6001cac.png)
Página para añadir una jaula.

![imagen](https://user-images.githubusercontent.com/85401502/230797343-49537e49-a8cb-4d1d-bb77-d207e0ad50c1.png)
Página de una jaula.

![imagen](https://user-images.githubusercontent.com/85401502/230797374-d2b77eb5-7ce1-4137-8ea9-e3c8ee5378b8.png)
Página para añadir un hilo.

Vista de administrador.

![imagen](https://user-images.githubusercontent.com/85401502/230797408-4133f618-1862-4df1-9b81-b65c91e6c35b.png)
Página del foro.

![imagen](https://user-images.githubusercontent.com/85401502/230797433-88936d85-a4c6-47c6-b7fb-8a5c5b50fe64.png)
Página con los usuarios de la aplicación.

![imagen](https://user-images.githubusercontent.com/85401502/230797442-23d1f8ce-ff84-4917-9cd5-f65027785920.png)
Página nuevo usuario.

![imagen](https://user-images.githubusercontent.com/85401502/230805170-ce55cad6-7f15-49c2-9e0a-62904c909952.png)
Error id de usuario ya existente.

![imagen](https://user-images.githubusercontent.com/85401502/230805196-1f74fa4f-db5f-4ad2-84ab-944d58fdd96d.png)
Error el id de usuario no se corresponde con ningun usuario con licencia.


Diagrama de navegación

![diagrama fase 3](https://user-images.githubusercontent.com/85401502/230806120-b69ef2f2-2448-4737-ab66-a0c1d0b94980.jpg)


Diagrama UML

![imagen](https://user-images.githubusercontent.com/85401502/230801315-ec3f923c-b2f6-494f-8924-30b0f27fc2ae.png)

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

Instale maven con "sudo apt install maven"

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







