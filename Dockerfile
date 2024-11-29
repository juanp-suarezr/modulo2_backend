# IMAGEN MODELO
FROM amazoncorretto:17

#DEFINI DIRECTORIO RAIZ DE NUESTRO CONTENEDOR
WORKDIR /root

# COPIAR Y PEGAR ARCHIVOS DENTRO DEL CONTENEDOR
COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

# Otorgar permisos de ejecución a mvnw
RUN chmod +x ./mvnw

# Instalar tar y cualquier otra dependencia necesaria
RUN yum update -y && yum install -y tar gzip

#DESCARGAR LAS DEPENDENCIAS
RUN ./mvnw dependency:go-offline

# COPIAR EL CODIGO FUENTE DENTRO DEL CONTENEDOR
COPY ./src /root/src

# CONSTUIR NUESTRA APLICACION
RUN ./mvnw clean install -DskipTests

# LEVANTAR NUESTRA APLICAION CUANDO EL CONTENEDOR INICIE
ENTRYPOINT ["java","-jar","/root/target/mf-0.0.1-SNAPSHOT.jar"]