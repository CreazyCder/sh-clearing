#!/bin/bash
SERVICE_NAME=shch-funds
SERVICE_VERSION=2.2.2-SNAPSHOT
unzip -o $HOME/app/SERVICE_NAME-SERVICE_VERSION.zip -d $HOME/app/$SERVICE_NAME
cd $HOME/app/$SERVICE_NAME/scripts
./shutdown.sh
./startup.sh

