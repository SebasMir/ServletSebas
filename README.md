# ServletSebas

AUTOR: SEBASTIAN MIR PERAL

OBJETIVO: Aplicación web con un formulario con la lista de alumnos. 
Al seleccionar uno, nos tiene que mostrar por pantalla sus asignaturas y tutorias.

RESOLUCIÓN: El Servlet con una petición POST recibe el codigo de Alumno i devuelve un JSON (responsejson) al javascript 
para pintar el HTML. La petición del javascript es asíncrona de tal manera no tiene que recargar cada vez la página.

Alumnos.java
------------

Clase que almacena codi(int), nom(string), Assignatures(Arraylist<string>) y Tutories(Arraylist<string>).
Y metodos set y get para trabajar con ellos.

SQLConnect.java
---------------

Clase donde esta definida la conexión con la base de datos y metodos para trabajar con ella.

Metodos:
conectar() hace la conexión con el servidor.
getListaAlumn(connection conn) Mediante la conexión, crea una lista de Alumnos con sus codigos y nombres.
Despues devuelve esta lista.
getAssigTutorias(Connection conn, int alumncode) Recibe una conexión y un codigo de alumno.
Y devuelve un objeto alumno con nombre, codigo y las dos listas de assignaturas y tutorias.

Servlet.java
------------

Lo utilizamos para responder solicitudes GET y POST de la aplicación web.

doGet(HttpServletRequest request, HttpServletResponse response)
Se crea conexión con la base de datos.
Y cargamos una lista de alumnos con codigos y nombres.
Convertimos esta lista a un objeto tipo json.
Y este elemento lo convertimos en un array.

Especificando que es una application/json, lo enviamos al jsp para generar la lista desplegable.

doPost(HttpServletRequest request, HttpServletResponse response) 
Recibimos el codigo seleccionado en la lista de alumnos.
Creamos la conexión con la base de datos.
Con la conexión y el código alumno recibido creamos un objeto completo(nombre, codigo, assignaturas y tutorias) 
Convertimos el objeto alumno a json y lo devolvemos al jsp.

index.jsp
---------

ajax type = GET

Recibe la respuesta json.
Crea la lista de alumnos colocando cada alumno como una opción.
Y al seleccionar uno hace la petición POST

ajax type = POST

Recibe la respuesta json con el objeto alumno.
muestra el campo span asignaturas y tutorias





