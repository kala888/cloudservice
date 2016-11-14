FROM consul

ADD startup.sh /startup.sh
RUN chmod 755 /startup.sh

WORKDIR /

CMD ./startup.sh "-server -ui -client=0.0.0.0 -node=consul-server -bootstrap-expect=1 -data-dir=/tmp/consul"
