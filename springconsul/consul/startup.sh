ip=`ifconfig eth0 | egrep -o "inet addr:[^ ]*" | grep -o "[0-9.]*"`
consul agent -bind=$ip $1
