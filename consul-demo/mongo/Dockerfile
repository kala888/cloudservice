FROM mongo:3

WORKDIR /
ADD consul.tar.gz /usr/local/bin/
RUN mkdir -p {/srv/consul,/etc/consul.d}
ADD mongo.json /etc/consul.d/
RUN chmod 755 /etc/consul.d -R

ADD entrypoint.sh entrypoint.sh
RUN chmod +x entrypoint.sh

RUN export BUILD_ON=$(date -d today +"%Y%m%d-%H%M-%S")
EXPOSE 27017
ENTRYPOINT ["./entrypoint.sh"]
