# Enunciado
Se nos contrata para realizar el desarrollo de una tienda web, en donde se ofrecen artículos de
tecnología.
Al iniciar la aplicación, se puede ver y buscar distintos tipos de artículos dentro de la tienda, pero para
poder constituir un carrito y luego su posterior compra se debe de estar registrado y con sesión iniciada,
en caso contrario no se deben de realizar estas acciones, únicamente se podrá consultar los artículos de
la tienda.
Nos informan que existirán dos tipos de clientes donde sus principales datos son: nombre, apellido y
dni. También tendrán uno o varios domicilios para la entrega de los artículos de los cuales contamos con
los siguientes datos: calle, número, código postal, localidad y domicilio principal. Adicionalmente
tendrán registradas uno o más tarjetas de las cuales almacenaremos los siguientes datos: número,
marca, tipo de tarjeta (débito, crédito o prepaga) y tarjeta principal.
Los tipos de clientes que usarán la tienda son empresas, que únicamente pueden vender artículos y
personas físicas que pueden comprar los artículos.
De los artículos conocemos su nombre, descripción, categoría, cantidad y precio. Los vendedores
pueden ofrecer uno o varios artículos.
Los carritos deben de ser identificados con un número que será único para cada carrito y tendrán dos
estados posibles: abiertos y cerrados.
Puede darse la situación de que el comprador decida no continuar en la tienda y salir de la misma con
un carrito ya generado, luego volver a iniciar sesión y finalizar la compra. Para estos casos se deberá de
generar una especie cookie en un archivo de texto plano que contendrá el número de carrito y dni del
comprador.
Al momento de proceder al cierre del carrito y posterior compra, el sistema deberá de tomar el
domicilio y tarjeta principal con la opción de que el comprador pueda cambiarlos en caso de ser
necesario.

#Puntos a Desarrollar
1 - Desarrollar el diagrama de clases, solamente del dominio de la aplicación.
2 - Generar un modelo de persistencia de los datos, usando la persistencia en archivos orientada a String
y Byte (queda a libre criterio la selección de cada persistencia para cada entidad del dominio). Generar
un manager para la persistencia de archivos orientado a Byte. Tener en cuenta que para los archivos
orientado a Byte los datos deben de ser persistidos en archivos separados para cada entidad del
dominio.
3 - El modelo generado deberá contener los patrones vistos en clases (DAO - MVC - Factory) asignando
las responsabilidades correspondientes con las buenas prácticas vistas en clase. Además, agregar el uso
de la clase Properties.
4 - La aplicación deberá contener tanto el manejo de excepciones propias de Java, como excepciones
custom.
5 - Al iniciar la aplicación se deberán de traer de manera concurrente los datos de los compradores para
que queden alojados en memoria y luego poder validar la identidad al inicio de sesión con su dni y clave.
Una vez finalizada la carga de datos iniciales, se visualiza el menú de la aplicación.
6 - La aplicación deberá de ser de interfaz gráfica usando Swing.
7 - Generar un módulo de reportes con las siguiente características (Utilizar stream):
a- Obtener el nombre y dni de los clientes que realizaron compras por más de $10.000
en el último mes.
b- Obtener el artículo más comercializado a partir del nombre de una empresa.
c- A partir del dni de un cliente, obtener las últimas 5 compras realizadas mostrando:
Nombre de empresa vendedora, Fecha de Compra y listado de artículos (descripción, precio,
cantidad) y el total de la compra.
