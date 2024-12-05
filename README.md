# API REST Pedidos Hamburguesas
![portada](https://github.com/user-attachments/assets/33226166-33e0-4d45-9900-ca3ab8687b3e)

# Idea API REST
La API consistirá en un restaurante de hamburguesas al cual se pedirá online. Las tablas serán “Usuarios”, “Hamburguesas”, “Pedidos”.

Los usuarios podrán ver todo tipo de hamburguesas disponibles, además de poder ver todos los pedidos realizados. Cada usuario podrá ver su historial sin poder entrar en el historial de pedidos de otro usuario.

Los usuarios con rol de “administrador” podrán tanto añadir hamburguesas como borrar, actualizar y consultar estas mismas. También podrán ver el historial de pedidos de los demás usuarios.
# Justificación del proyecto 
El proyecto de la API REST para un restaurante de hamburguesas tiene como objetivo principal crear una plataforma digital que permita a los usuarios realizar pedidos en línea de forma eficiente y segura. 
Este sistema está diseñado para mejorar la experiencia del cliente y optimizar la gestión interna de un restaurante especializado en hamburguesas, facilitando tanto la operación para los administradores como la interacción para los usuarios.
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
