consul agent -join $CONSUL_HOST -bind=`hostname -i` -client=0.0.0.0 -data-dir=/tmp/consul -cofig-dir /etc/consul.d
mongod
