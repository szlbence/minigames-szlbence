FROM openjdk:17-oracle
COPY ./target/gift_rocket-0.0.1-SNAPSHOT.jar .
RUN chmod +x /gift_rocket-0.0.1-SNAPSHOT.jar
WORKDIR /app/backend
EXPOSE 8080
ENTRYPOINT ["/gift_rocket-0.0.1-SNAPSHOT.jar", "-D", "FOREGROUND"]
ENTRYPOINT ["java", "-jar", "/gift_rocket-0.0.1-SNAPSHOT.jar"]

FROM centos:7
COPY startUpScript.sh /
RUN yum install -y epel-release maven wget \
&& yum clean all \
&& yum install -y  https://download.postgresql.org/pub/repos/yum/reporpms/EL-7-x86_64/pgdg-redhat-repo-latest.noarch.rpm \
&& yum install -y postgresql11-server postgresql11-contrib \
&& chown root /startUpScript.sh \
&& chgrp root /startUpScript.sh \
&& chmod 777 /startUpScript.sh
CMD ["/bin/bash","-c","/startUpScript.sh && tail -f /dev/null"]
