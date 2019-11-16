FROM ubuntu:18.04

RUN apt-get update && apt-get upgrade -y \
 && apt-get install -y software-properties-common \
  build-essential git \
  openjdk-8-jre openjdk-8-jdk