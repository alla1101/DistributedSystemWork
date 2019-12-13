FROM ubuntu:18.04

RUN apt-get update && apt-get upgrade -y \
 && apt-get install -y software-properties-common \
  build-essential git \
  openjdk-8-jre openjdk-8-jdk

RUN apt-get update && groupadd -g 1000 alla && useradd -d /home/alla -s /bin/bash -m alla -u 1000 -g 1000
USER alla
ENV HOME /home/alla