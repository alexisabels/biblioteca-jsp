FROM payara/micro

COPY target/biblioteca.war /opt/payara/deployments/

EXPOSE 8080

CMD ["java", "-jar", "/opt/payara/payara-micro.jar", "--deploy", "/opt/payara/deployments/biblioteca.war", "--contextRoot", "/"]
