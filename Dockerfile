FROM payara/micro

COPY target/biblioteca.war $PAYARA_PATH/deployments/

EXPOSE 8080

CMD ["java", "-jar", "payara-micro.jar", "--deploy", "$PAYARA_PATH/deployments/biblioteca.war", "--contextRoot", "/"]
