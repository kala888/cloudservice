ip=`hostname --ip-address`
java -jar /project/build/libs/$1 --spring.cloud.consul.discovery.hostname=$ip
