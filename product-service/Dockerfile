FROM openjdk:11-jre-slim
COPY /build/libs/product-service-1.0.0.jar /usr/apps/

WORKDIR /usr/apps
ENTRYPOINT ["java","-jar","product-service-1.0.0.jar"]
EXPOSE 8091


# docker login
# docker build  -t edumar111/product-service:1.0.0 -f Dockerfile .
# docker  push edumar111/product-service:1.0.0
# docker run  -itd  --name product-service  -p 8091:8091   edumar111/product-service:1.0.0
