ip=`hostname --ip-address`
echo "container started, the war file is $1"
if test "$wait_seconds";
then
 echo "sleep $wait_seconds seconds wait for other service"
 sleep $wait_seconds
fi

java -jar /project/build/libs/$1
