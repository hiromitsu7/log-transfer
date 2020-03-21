FROM centos:7

RUN yum update -y \
    && yum install -y java-1.6.0-openjdk-devel which vim iproute net-tools  \
    && yum reinstall -y glibc-common \
    && localedef -f UTF-8 -i ja_JP ja_JP.UTF-8

ENV LANG="ja_JP.UTF-8" \
    LANGUAGE="ja_JP:ja" \
    LC_ALL="ja_JP.UTF-8" \
    TZ="Asia/Tokyo"
