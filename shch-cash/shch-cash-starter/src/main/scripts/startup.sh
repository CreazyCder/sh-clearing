#!/bin/bash
SERVICE_NAME=shch-funds
## Adjust log dir if necessary
LOG_DIR=../logs
## Adjust server port if necessary
SERVER_PORT=8080
## set app name
export APP_NAME=$SERVICE_NAME

## You must Adjust for apollo !!!!!!!!!!!!!!!!!``
#是否启用apollo
APOLLO_ENABLED=true
APOLLO_APPID=yusp-gateway
APOLLO_ENV=DEV
APOLLO_DEVMETA=http://localhost:9001
NAMESPACES=application.yml.apollo

## Adjust memory settings if necessary
#export JAVA_OPTS="-Xms6144m -Xmx6144m -Xss256k -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=384m -XX:NewSize=4096m -XX:MaxNewSize=4096m -XX:SurvivorRatio=8"
#export JAVA_OPTS="-Xms6144m -Xmx6144m -Xss256k -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=384m -XX:NewSize=4096m -XX:MaxNewSize=4096m -XX:SurvivorRatio=8"

## Only uncomment the following when you are using server jvm
#export JAVA_OPTS="$JAVA_OPTS -server -XX:-ReduceInitialCardMarks"

export JAVA_OPTS="$JAVA_OPTS -XX:+UseParNewGC -XX:ParallelGCThreads=4 -XX:MaxTenuringThreshold=9 -XX:+UseConcMarkSweepGC -XX:+DisableExplicitGC -XX:+UseCMSInitiatingOccupancyOnly -XX:+ScavengeBeforeFullGC -XX:+UseCMSCompactAtFullCollection -XX:+CMSParallelRemarkEnabled -XX:CMSFullGCsBeforeCompaction=9 -XX:CMSInitiatingOccupancyFraction=60 -XX:+CMSClassUnloadingEnabled -XX:SoftRefLRUPolicyMSPerMB=0 -XX:+CMSPermGenSweepingEnabled -XX:CMSInitiatingPermOccupancyFraction=70 -XX:+ExplicitGCInvokesConcurrent -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationConcurrentTime -XX:+PrintHeapAtGC -XX:+HeapDumpOnOutOfMemoryError -XX:-OmitStackTraceInFastThrow -Duser.timezone=Asia/Shanghai -Dclient.encoding.override=UTF-8 -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom"
export JAVA_OPTS="$JAVA_OPTS -Dserver.port=$SERVER_PORT -Dlogging.file=$LOG_DIR/$SERVICE_NAME.log -Xloggc:$LOG_DIR/heap_trace.txt -XX:HeapDumpPath=$LOG_DIR/HeapDumpOnOutOfMemoryError/"
if [ $APOLLO_ENABLED ]; then
    export JAVA_OPTS="$JAVA_OPTS -Dapp.id=$APOLLO_APPID -Denv=$APOLLO_ENV -Dapollo.meta=$APOLLO_DEVMETA -Dapollo.bootstrap.enabled=true -Dapollo.bootstrap.namespaces=$NAMESPACES"
else
    export JAVA_OPTS="$JAVA_OPTS -Dapollo.bootstrap.enabled=false"
fi

PATH_TO_JAR=$SERVICE_NAME".jar"
SERVER_URL="http://localhost:$SERVER_PORT"

function checkPidAlive {
    return 0;
}

cd `dirname $0`/..

for i in `ls *$SERVICE_NAME-*.jar 2>/dev/null`
do
    if [[ ! $i == *"-sources.jar" ]]
    then
        PATH_TO_JAR=$i
        break
    fi
done

if [[ ! -f PATH_TO_JAR && -d current ]]; then
    cd current
    for i in `ls *$SERVICE_NAME-*.jar 2>/dev/null`
    do
        if [[ ! $i == *"-sources.jar" ]]
        then
            PATH_TO_JAR=$i
            break
        fi
    done
fi

if [[ -f $SERVICE_NAME".jar" ]]; then
  rm -rf $SERVICE_NAME".jar"
fi

printf "$(date) ==== Starting ==== \n"

ln $PATH_TO_JAR $SERVICE_NAME".jar"
chmod a+x $SERVICE_NAME".jar"
./$SERVICE_NAME".jar" start

rc=$?;

if [[ $rc != 0 ]];
then
    echo "$(date) Failed to start $SERVICE_NAME.jar, return code: $rc"
    exit $rc;
fi

declare -i counter=0
declare -i max_counter=48 # 48*5=240s
declare -i total_time=0

printf "Waiting for server startup"
until [[ (( counter -ge max_counter )) || "$(curl -X GET --silent --connect-timeout 1 --max-time 2 --head $SERVER_URL | grep "HTTP/1.1 200")" != "" ]];
do
    printf "."
    counter+=1
    sleep 5

    checkPidAlive
done

total_time=counter*5

if [[ (( counter -ge max_counter )) ]];
then
    printf "\n$(date) Server failed to start in $total_time seconds!\n"
    exit 1;
fi

printf "\n$(date) Server started in $total_time seconds!\n"

exit 0;
