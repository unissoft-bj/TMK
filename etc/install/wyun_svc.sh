#!/bin/bash

echo "Service [$APP_NAME] - [$1]"

export APP_HOME=/opt/tmk
export APP_NAME=dianxiao
export APP_PORT=8080

echo "    JAVA_HOME=$JAVA_HOME"
echo "    APP_HOME=$APP_HOME"
echo "    APP_NAME=$APP_NAME"
echo "    APP_PORT=$APP_PORT"

function start {
    if pkill -0 -f $APP_NAME.jar > /dev/null 2>&1
    then
        echo "Service [$APP_NAME] is already running. Ignoring startup request."
        exit 1
    fi
    echo "Starting application..."
    nohup sudo -u tmk java -jar $APP_HOME/$APP_NAME.jar --spring.profiles.active=production  < /dev/null > /dev/null 2>&1 &
}

function stop {
    if ! pkill -0 -f $APP_NAME.jar > /dev/null 2>&1
    then
        echo "Service [$APP_NAME] is not running. Ignoring shutdown request."
        exit 1
    fi

    # First, we will try to trigger a controlled shutdown using
    # spring-boot-actuator
    curl -X POST http://localhost:$APP_PORT/shutdown < /dev/null > /dev/null 2>&1

    # Wait until the server process has shut down
    attempts=0
    while pkill -0 -f $APP_NAME.jar > /dev/null 2>&1
    do
        attempts=$[$attempts + 1]
        if [ $attempts -gt 5 ]
        then
            # We have waited too long. Kill it.
            pkill -f $APP_NAME.jar > /dev/null 2>&1
        fi
        sleep 1s
    done
}

case $1 in
start)
    start
;;
stop)
    stop
;;
restart)
    stop
    start
;;
esac
exit 0
