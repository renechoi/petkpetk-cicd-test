#!/bin/bash

source
 ~/.bashrc


BUILD_JAR=$(ls /home/ec2-user/action/service/build/libs/*.jar)
JAR_NAME=$(basename $BUILD_JAR)
echo "> build 파일명: $JAR_NAME" >> /home/ec2-user/action/admin/deploy.log

echo "> build 파일 복사" >> /home/ec2-user/action/admin/deploy.log
DEPLOY_PATH=/home/ec2-user/action/admin/
cp $BUILD_JAR $DEPLOY_PATH

echo "> 현재 실행중인 애플리케이션 pid 확인" >> /home/ec2-user/action/admin/deploy.log
CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> /home/ec2-user/action/admin/deploy.log
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi


#DEPLOY_JAR=$DEPLOY_PATH$JAR_NAME
#echo "> DEPLOY_JAR 배포"    >> /home/ec2-user/action/admin/deploy.log
#nohup java -jar $DEPLOY_JAR >> /home/ec2-user/action/admin/deploy.log 2>/home/ec2-user/action/admin/deploy_err.log &
#



DEPLOY_JAR=$DEPLOY_PATH$JAR_NAME

# 새로운 어플리케이션을 실행할 때는 환경 변수를 참조해야 한다.
# $SERVER_PORT, $DB_URL, $DB_USERNAME, $DB_PASSWORD, $GOOGLE_OAUTH_CLIENT_ID
echo "> DEPLOY_JAR 배포" >> /home/ec2-user/action/admin/deploy.log
nohup java -jar -Dserver.port=$SERVER_PORT -Dspring.datasource.url=$DB_URL \
  -Dspring.datasource.username=$DB_USERNAME -Dspring.datasource.password=$DB_PASSWORD \
  -Dspring.security.oauth2.client.registration.google.client-id=$GOOGLE_OAUTH_CLIENT_ID \
  -Dspring.security.oauth2.client.registration.google.client-secret=$GOOGLE_OAUTH_CLIENT_SECRET \
  $DEPLOY_JAR >> /home/ec2-user/action/admin/deploy.log 2>/home/ec2-user/action/admin/deploy_err.log &




#nohup java -Dspring.profiles.active=prod -jar $DEPLOY_JAR



#nohup java -jar $DEPLOY_JAR -Dspring.profiles.active=prod --spring.config.location=/home/ec2-user/action/admin/src/main/resources/application-prod.yml >> /home/ec2-user/deploy.log 2>/home/ec2-user/action/admin/deploy_err.log &

#export
# SPRING_PROFILES_ACTIVE=prod
#nohup java -jar $DEPLOY_JAR --spring.config.location=/home/ec2-user/action/admin/src/main/resources/application-prod.yml

#nojup java -jar $DEPLOY_JAR -Dspring.profiles.active=prod /home/ec2-user/action/admin/src/main/resources/application-prod.yml