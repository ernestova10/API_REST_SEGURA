# API REST Pedidos Hamburguesas
![portada](https://github.com/user-attachments/assets/33226166-33e0-4d45-9900-ca3ab8687b3e)

# Idea API REST
La API consistirá en un restaurante de hamburguesas al cual se pedirá online. Las tablas serán “Usuarios”, “Hamburguesas”, “Pedidos”.

Los usuarios podrán ver todo tipo de hamburguesas disponibles, además de poder ver todos los pedidos realizados. Cada usuario podrá ver su historial sin poder entrar en el historial de pedidos de otro usuario.

Los usuarios con rol de “administrador” podrán tanto añadir hamburguesas como borrar, actualizar y consultar estas mismas. También podrán ver el historial de pedidos de los demás usuarios.
# Justificación del proyecto 
El proyecto de la API REST para un restaurante de hamburguesas tiene como objetivo principal crear una plataforma digital que permita a los usuarios realizar pedidos en línea de forma eficiente y segura. 
Este sistema está diseñado para mejorar la experiencia del cliente y optimizar la gestión interna de un restaurante especializado en hamburguesas.
También no hay mucha competencia en el mundo de las hamburguesas ya que no hay muchas páginas web que ofrezcan estos servicios lo cual hace que la idea pueda destacar y funcionar.
# Tablas de la API REST
- En la tabla usuarios se guardarán los usuarios registrados.
- En la tabla hamburguesas se guardarán todas las hamburguesas del restaurante.
- En la tabla de pedidos  se guardarán los pedidos realizados por los usuarios.

# Campos, Tipos de Datos y Restricciones
## Tabla Usuario
Esta tabla tendrá los siguientes campos:
- Identificador de usuario | Long se incrementará automáticamente.
- Nombre | String | No puede ser nulo.
- Contraseña | String | No puede ser nulo, debe tener más de 4 caracteres.
- Rol | String | No puede ser nulo, debe de ser “Admin” o “User”.
- Edad | int | No puede ser nulo, debe de ser numérico.
- Sexo | String | No puede ser nulo, debe ser un valor predefinido, como "Masculino", "Femenino" u "Otro".
- Correo electrónico | String | No puede ser nulo, debe cumplir con el formato estándar de un correo electrónico.
## Tabla Hamburguesa
Esta tabla tendrá los siguientes campos:
- Identificador de hamburguesa | Long se incrementará automáticamente.
- Nombre | String | No puede ser nulo.
- Precio | int | No puede ser nulo.
- Tipo de carne| String | No puede ser nulo. (Ternera, cerdo, pollo, vegetariana, etc)
- Ingredientes| List| No puede ser nulo.
## Tabla Pedidos
Esta tabla tendrá los siguientes campos:
- Identificador de pedidos | Clave compuesta por Usuario y Hamburguesa
- Usuario | ID Usuario.
- Hamburguesa | ID Hamburguesa.
- Fecha | Date | No puede ser nulo.
- Precio | int | No puede ser nulo.
- Cantidad | int | No puede ser nulo.
# Diagrama entidad relación
![img](https://github.com/user-attachments/assets/1535a3de-c907-47ba-b5dd-572b45a9f847)
# Endpoints
## Tabla Usuario
Los endpoints será los siguientes:
1. **Registrar usuarios**
    - **Método**: Post
    - **Endpoint**: /usuario/registrar
    - **Descripción**: Permite registrar un usuario en el sistema.
    - **Excepciones**:

        A. 201 Created: Creado el usuario correctamente.

        B. 400 Bad Request: Datos de entrada inválidos.
    - **Cuerpo de la solicitud**:

          json
      
              {
      
                  "nombre": "string",
      
                  "contraseña": "string",
      
                  "rol": "string (Admin o User)",
      
                  "edad": "int",
      
                  "sexo": "string (Masculino, Femenino, Otro)",
      
                  "correo": "string"
      
              }
2. **Iniciar sesión**
    - **Método**: Post
    - **Endpoint**: /usuario/login
    - **Descripción**: Permite a un usuario iniciar sesión y obtener el token de acceso.
    - **Excepciones**:

        A. 200 OK: Solicitud procesada con éxito.

        B. 400 Bad Request: Datos de entrada inválidos.

        C. 401 Unauthorized: Credenciales incorrectas.

        D. 404 Not Found: Usuario no encontrado.
    - **Cuerpo de la solicitud**:

          json

              {
                  "correo": "string",
      
                  "contraseña": "string"

              }

3. **Obtener perfil de usuario**
    - **Método**: Get
    - **Endpoint**: /usuario/perfil (perfil es nombre de usuario)
    - **Descripción**: Devuelve la información del usuario que está autenticado.
    - **Cabeceras**: Token
    - **Permisos**: Sólo usuario autenticado o usuario con rol Admin.
    - **Excepciones**:

        A. 200 OK: Solicitud procesada con éxito.

        B. 403 Forbidden: Usuario sin permisos para realizar la acción.

        C. 404 Not Found: Usuario no encontrado.
4. **Actualizar usuario**
    - **Método**: Put
    - **Endpoint**: /usuario/perfil (perfil es nombre de usuario)
    - **Descripción**: Permite actualizar la información personal del usuario autenticado.
    - **Cabeceras**: Token
    - **Permisos**: Sólo usuario autenticado.
    - **Excepciones**:

        A. 200 OK: Solicitud procesada con éxito.

        B. 403 Forbidden: Usuario sin permisos para realizar la acción.

        C. 404 Not Found: Usuario no encontrado.
    - **Cuerpo de la solicitud**:

          json

              {
                  "nombre": "string",
      
                  "edad": "int",
      
                  "sexo": "string",
      
                  "correo": "string"
      
              }
5. **Eliminar usuario**
    - **Método**: Delete
    - **Endpoint**: /usuario/{id}
    - **Descripción**: Permite eliminar el usuario.
    - **Cabeceras**: Token
    - **Permisos**: Usuario con rol Admin.
    - **Excepciones**:

        A. 200 OK: Solicitud procesada con éxito.

        B. 400 Bad Request: El usuario intentó eliminarse a sí mismo.

        C. 403 Forbidden: Usuario sin permisos para realizar la acción.

        D. 404 Not Found: Usuario no encontrado.
## Tabla Hamburguesa
Los endpoints será los siguientes:
1. **Listar todas las hamburguesas**
    - **Método**: Get
    - **Endpoint**: /hamburguesas
    - **Descripción**: Devuelve un listado de todas las hamburguesas.
    - **Cabeceras**: Token
    - **Permisos**: Sólo usuario autenticado o usuario con rol Admin.
    - **Excepciones**:

        A. 200 OK: Solicitud procesada con éxito.

        B. 403 Forbidden: Usuario sin permisos para realizar la acción.
2. **Añadir hamburguesa**
    - **Método**: Post
    - **Endpoint**: /hamburguesas
    - **Descripción**: Permite añadir una nueva hamburguesa al sistema.
    - **Cabeceras**: Token
    - **Permisos**: Usuario con rol Admin.
    - **Excepciones**:

        A. 201 Created: Creada la hamburguesa correctamente.

        B. 400 Bad Request: Datos de entrada inválidos.

    - **Cuerpo de la solicitud**

          json

              {
                  "nombre": "string",
      
                  "precio": "int",
      
                  "tipo_carne": "string",
      
                  "ingredientes": ["string"]
      
              }
3. **Actualizar hamburguesa**
    - **Método**: Put
    - **Endpoint**: /hamburguesas/{id}
    - **Descripción**: Permite actualizar la hamburguesa.
    - **Cabeceras**: Token
    - **Permisos**: Usuario con rol Admin.
    - **Excepciones**:

        A. 200 OK: Solicitud procesada con éxito.

        B. 403 Forbidden: Usuario sin permisos para realizar la acción.

        C. 404 Not Found: Hamburguesa no encontrada.

    - **Cuerpo de la solicitud**
          json

              {

                  "nombre": "string",
      
                  "precio": "int",
      
                  "tipo_carne": "string",
      
                  "ingredientes": ["string"]
      
              }
4. **Eliminar hamburguesa**
    - **Método**: Delete
    - **Endpoint**: /hamburguesa/{id}
    - **Descripción**: Permite eliminar la hamburguesa.
    - **Cabeceras**: Token
    - **Permisos**: Usuario con rol Admin.
    - **Excepciones**:

        A. 200 OK: Solicitud procesada con éxito.

        B. 403 Forbidden: Usuario sin permisos para realizar la acción.

        C. 404 Not Found: Hamburguesa no encontrada.
## Tabla Pedidos
Los endpoints será los siguientes:
1. **Realizar pedidos**
    - **Método**: Post
    - **Endpoint**: /pedidos
    - **Descripción**: Permite realizar un nuevo pedido.
    - **Cabeceras**: Token
    - **Permisos**: Sólo usuario autenticado o usuario con rol Admin.
    - **Excepciones**:

        A. 200 OK: Solicitud procesada con éxito.

        B. 403 Forbidden: Usuario sin permisos para realizar la acción.

        C. 404 Not Found: Hamburguesa o usuario no encontrado.
    - **Cuerpo de la solicitud**

          json

              {
                  "nombre_usuario": "String",
      
                  "nombre_hamburguesa": "String",
      
                  "cantidad": "int"

              }
2. **Ver historial de pedidos del usuario autenticado**
    - **Método**: Get
    - **Endpoint**: /pedidos/historial/perfil (perfil es nombre de usuario)
    - **Descripción**: Devuelve el historial de pedidos del usuario autenticado.
    - **Cabeceras**: Token
    - **Permisos**: Sólo usuario autenticado.
    - **Excepciones**:

        A. 200 OK: Solicitud procesada con éxito.

        B. 403 Forbidden: Usuario sin permisos para realizar la acción.

        C. 404 Not Found: Pedidos no encontrados.
3. **Ver historial de todos los pedidos**
    - **Método**: Get
    - **Endpoint**: /pedidos/admin/historial
    - **Descripción**: Permite a los administradores ver el historial de pedidos de todos los usuarios.
    - **Cabeceras**: Token
    - **Permisos**: Sólo usuario con rol Admin.
    - **Excepciones**:

        A. 200 OK: Solicitud procesada con éxito.

        B. 403 Forbidden: Usuario sin permisos para realizar la acción.

        C. 404 Not Found: Pedidos no encontrados.
4. **Actualizar pedidos**
    - **Método**: Put
    - **Endpoint**: /pedidos/{id}
    - **Descripción**: Permite actualizar un pedido existente. Solo el usuario que realizó el pedido o un administrador pueden realizar esta acción.
    - **Cabeceras**: Token
    - **Permisos**: Usuario con rol Admin o usuario autenticado.
    - **Excepciones**:

        A. 200 OK: Solicitud procesada con éxito.
  
        B. 400 Bad Request: La cantidad deber ser mayor a 0.

        C. 403 Forbidden: Usuario sin permisos para realizar la acción.

        D. 404 Not Found: Pedido no encontrado.

    - **Cuerpo de la solicitud**

          json

              {

                  "cantidad": "int"

              }
5. **Eliminar pedido**
    - **Método**: Delete
    - **Endpoint**: /pedidos/{id}
    - **Descripción**: Permite eliminar el pedido.
    - **Cabeceras**: Token
    - **Permisos**: Usuario con rol Admin.
    - **Excepciones**:

        A. 200 OK: Solicitud procesada con éxito.

        B. 403 Forbidden: Usuario sin permisos para realizar la acción.

        C. 404 Not Found: Pedido no encontrado.
      
# Lógica de Negocio
**Gestión de Roles**:

Los usuarios con rol "Admin" tendrán acceso a funcionalidades exclusivas como agregar, actualizar y eliminar hamburguesas, así como consultar pedidos de todos los usuarios.
Los usuarios con rol "User" podrán realizar pedidos y consultar su propio historial.

**Cálculo del Precio Final del Pedido**:

El precio total de un pedido se calcula como:
- Precio Total = Precio Hamburguesa × Cantidad
    
**Validaciones de Pedido**:
- Verificar que la hamburguesa solicitada existe.
- Validar que la cantidad sea mayor a 0.

# Restricciones de Seguridad
**Autenticación y Autorización**:
- Autenticación basada en tokens (JWT).
- Control de acceso según el rol del usuario.

**Validación de Datos**:
- Validación estricta de las entradas en cada solicitud.

**Cifrado de Contraseñas**:
- Contraseñas almacenadas usando algoritmos de hash seguros como bcrypt.
