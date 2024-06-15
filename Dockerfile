# Usa una imagen base de Payara Micro
FROM payara/micro

# Copia el archivo WAR en el directorio de despliegue de Payara
COPY target/biblioteca.war /opt/payara/deployments/

# Exponer el puerto 8080
EXPOSE 8080

# Iniciar Payara Micro
ENTRYPOINT ["java", "-jar", "/opt/payara/payara-micro.jar", "--deploy", "/opt/payara/deployments/biblioteca.war", "--noCluster"]
