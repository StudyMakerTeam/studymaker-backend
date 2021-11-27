#!/bin/bash
PROJECT_NAME=studymaker
BUILD_JAR=$(ls /home/ec2-user/app/$PROJECT_NAME/build/libs/*.jar)
JAR_NAME=$(basename $BUILD_JAR)

DEPLOY_LOG_PATH=/home/ec2-user/log/$PROJECT_NAME
echo "> build 파일명: $JAR_NAME" >>$DEPLOY_LOG_PATH/deploy.log

DEPLOY_JAR_PATH=/home/ec2-user/app/deployment/$JAR_NAME
cp BUILD_JAR DEPLOY_JAR_PATH
echo "> 배포용 jar 생성 : $DEPLOY_JAR_PATH" >>$DEPLOY_LOG_PATH/deploy.log

echo "> 현재 실행중인 애플리케이션 pid 확인" >>$DEPLOY_LOG_PATH/deploy.log
CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]; then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >>$DEPLOY_LOG_PATH/deploy.log
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> DEPLOY_JAR 배포" >>$DEPLOY_LOG_PATH/deploy.log
nohup java -jar $DEPLOY_JAR_PATH >>$DEPLOY_LOG_PATH/deploy.log 2>$DEPLOY_LOG_PATH/deploy_err.log &
