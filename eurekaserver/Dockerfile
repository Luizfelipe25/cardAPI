#AUTOMATIZACAO DO BUILD, PARA SEMPRE QUE SUBIR ATUALIZAR O BUILD
#IMAGEM DO MAVEN + ALIAS CHAMANDO DE BUILD
FROM maven:3.8.5-openjdk-11 as build
# Diretorio onde vai ser criado
WORKDIR /app
#COPIA TODO O PROJETO, DOCKER IGNORE EH USADO COMO O GIT IGNORE
COPY . .
#VAI RODAR O COMANDO PARA DAR O CLEAN E GERAR O JAR
RUN mvn clean package -DskipTests

#Imagem para rodar o container (dockerHub)
FROM openjdk:11
#Diretorio onde vai ser criada a imagem
WORKDIR /app
#COPY copia a aplicacao onde vc esta deixando o dockerFile para a pasta acima
#Voce vai sempre pegar o jar, que vai estar dentro da target.
#Build sao os comandos de cima Para buildar a aplicacao
#./app/target/*.jar ./app.jar PEGA O EXECUTAVEL JAVA PRA SUBIR NA IMAGEM
COPY --from=build ./app/target/*.jar ./app.jar
#expor a porta
EXPOSE 8761
#Ponto de entrada, ele vai fazer isso qnd subir a imagem
ENTRYPOINT java -jar app.jar