# API Rest con un micro servicio para el Banco de Crédito e Inversiones
API Rest con un micro servicio para detectar crear usuarios y consultar en base a un token.
Desarrollador: Daniel Gauna.
# 
# Instrucciones de uso:

La API tiene 3 servicios que se detallan a continuación.

Síntesis de funcionalidad:
Al levantar la API se exponen los 3 métodos que contiene el servicio. 
Al realizar una petición al endpoint sign-up se crea un usuario en base a los datos enviados en el request generando un token con una fecha de expiración
devolviendo un json con los datos requeridos en la documentación.

NOTA: antes de crear un usuario verifica que éste no exista en base al email ingresado. Si el email existe pero tiene el token expirado 
le genera uno nuevo(con el cual pueda buscarse ese usuario pero con el nuevo token) y sino lanza una exception.

Al realizar una petición al endpoint login busca en base a valor enviado en el request(token) que exista un usuario. Si existe verifica que el token 
no haya expirado. En caso de éxito devuelve un json con los campos requeridos, caso contrario lanza una excepción controlada.

A tener en cuenta:
Al crear un usuario con el servicio sign-up se genera un token que expira a los 6 minutos (360000 milisegundos), para realizar pruebas de expiración de token
y no esperar el tiempo configurado habría que modificar este valor por el deseado. Dicha propiedad se encuentra en el application.properties(jwt.expiryTime).
# 
# Servicio: /sign-up
Recibe como parámetro una objeto de tipo UserRequest que reprenta el body a crear y devuelve un json con valores de respuestas.

Response:

Devuelve un objecto de tipo UserResponse con las siguientes características:

El objeto contiene dos atributos

* - Object data: mapea todos los atributos requeridos en la documentación en formato json.
* - Error error: objecto con 3 atributos(fecha, codigo y detail) que se completan en caso de error.

En los casos de éxito el atributo data contiene la informacón requerida y error contiene valor null.
En los casos de error el atributo error contiene la información requerida y data contiene valor null.


URL: http://localhost:8080/user/sign-up

Método: POST

Header: Content-Type value application/json

Ejemplos de request:

Ejemplo de creación exitosa:

{
   "name":"Julio Gonzales",
   "email":"juliogonzalez@testssw.cl",
   "password":"a2asfGfdfdf4",
   "phones":[
      {
         "number":"123456",
         "cityCode":1,
         "countryCode":"2"
      }
   ]
}

Ejemplo de error de formato de email:

{
   "name":"Julio Gonzales",
   "email":"juliogonzaleztestssw.cl",
   "password":"a2asfGfdfdf4",
   "phones":[
      {
         "number":"123456",
         "cityCode":1,
         "countryCode":"2"
      }
   ]
}

Ejemplo de error de contraseña inválida:

{
   "name":"Julio Gonzales",
   "email":"juliogonzalez@testssw.cl",
   "password":"aaasfgfdfdf4",
   "phones":[
      {
         "number":"123456",
         "cityCode":1,
         "countryCode":"2"
      }
   ]
}

Ejemplos response:

-Ejemplo de caso exitoso:

{
    "data": {
        "id": 1,
        "uuid": "5d91f804b1b540fda1f905b37382e5b4",
        "created": "2022-04-15T23:36:06.186+00:00",
        "lastLogin": "2022-04-15T23:36:06.186+00:00",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW55QGdtYWlsLmNvbSIsInV1aWQiOiI1ZDkxZjgwNGIxYjU0MGZkYTFmOTA1YjM3MzgyZTViNCIsImlhdCI6MTY1MDA2NTc2NiwiZXhwIjoxNjUwMDY2MTI2fQ.fLJM70DCiaqlr2QKaJa3Da6w1oC3nz9tugrwwzyin6g",
        "isActivo": true
    },
    "error": null
}

-Ejemplo de caso con error:

{
    "data": null,
    "error": {
        "fecha": "2022-04-15T20:36:39.7220534",
        "codigo": 3,
        "detail": "El usuario ya existe."
    }
}

# 
# Servicio: /login
El método recibe por parámetro el token para realizar la consulta.

URL: http://localhost:8080/user/login

Method: POST

Ejemplo de request:

Para obtener un usuario es necesario consultarlo mediante el token que se obtiene con su creación

{
   	"token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW55QGdtYWlsLmNvbSIsInV1aWQiOiI0OGIxNDc4MjJhODY0ZTUxYjU4NTRkYWQzYWY3MDI4MSIsImlhdCI6MTY1MDE0Nzg0MCwiZXhwIjoxNjUwMTQ4MjAwfQ.oM47G										sIP3HAvhQrQTUDCHATsgJZcZ5E_RVmH30UO3zI"
}
Ejemplo de response exitoso:

{
    "data": {
        "id": 1,
        "uuid": "48b147822a864e51b5854dad3af70281",
        "created": "2022-04-16T22:24:00.542+00:00",
        "lastLogin": "2022-04-16T22:24:00.542+00:00",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW55QGdtYWlsLmNvbSIsInV1aWQiOiI0OGIxNDc4MjJhODY0ZTUxYjU4NTRkYWQzYWY3MDI4MSIsImlhdCI6MTY1MDE0Nzg0MCwiZXhwIjoxNjUwMTQ4MjAwfQ.oM47GsIP3HAvhQrQTUDCHATsgJZcZ5E_RVmH30UO3zI",
        "isActivo": true,
        "name": "Julio Gonzales",
        "email": "dany@gmail.com",
        "password": "$2a$10$aiT2bp4g2HS1PAPfJ1iAZuzmy6TSICNvUCMKFEovyWtfX1oopA.Lu",
        "phones": [
            {
                "number": 123456,
                "cityCode": 1,
                "countryCode": "2"
            }
        ]
    },
    "error": null
}

Ejemplos response con token inválido:

{
    "data": null,
    "error": {
        "fecha": "2022-04-16T20:21:38.3608268",
        "codigo": 4,
        "detail": "Token inválido."
    }
}

Ejemplos response con token inválido:

{
    "data": null,
    "error": {
        "fecha": "2022-04-16T20:26:31.5468158",
        "codigo": 5,
        "detail": "Token expirado."
    }
}
# 
# Servicio: /version

Método que solo devuelve la versión

Method: GET

http://localhost:8080/version

Respuesta: Versión: 1.0.0

# 
# Base de Datos:

La base de datos se puede utilizar de dos formas.
1 - Levantando la API con la configuración actual se crea una base de datos DB2 en memoria.
 * Ventajas: no es necesario la creación de una base de datos externa ni correr scripts.
 * Desventajas: cada vez que paramos el servidor se elimina la base y por lo tanto los datos.
2 - Levantando la API con una configuración de conexión a una base de datos MySQL. Para esto hay que correr los scripts ubicados en la carpeta resources/scripts y descomentar las propiedades que se encuentran en el application.properties con el tag Database connection.
 * Ventajas: los datos persisten indefinidamente.
 * Desventajas: hay que tomarse la molestia de correr todos los scripts.

NOTA: en caso de optar por la configuracion 2 no es necesario correr el scripts de la creación de las tablas ya que el el sistema la creará si no existe.
Solo es necesario que el schema exista.

Para la opción 2:
En la carpeta /resource/scripts se encuentran dos archivos para la creación de la base de datos.
Los archivos son:
01_CREATE_SCHEMA.sql: para la creacion de la base de datos
02_CREATE_TABLES.sql: para la creacion de las tablas users y phones.

