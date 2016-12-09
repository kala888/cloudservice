FROM consul
WORKDIR /

CMD consul agent -bind=`hostname -i` -server -ui -client=0.0.0.0 -node=consul-server -bootstrap-expect=1 -data-dir=/tmp/consul
EXPOSE 8400 8500 8600
