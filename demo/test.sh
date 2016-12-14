docker-compose down
sh rmi-images.sh
# docker-compose -f docker-compose.yml -f docker-compose.dev.yml up
cd springboot && gradle bootRe && cd ..
docker-compose up -d

echo "sleep 90s wait all service start"
sleep 90s

curl 'http://localhost:8001/apis' -X PUT -H 'Origin: http://localhost:7400' -H 'Accept-Encoding: gzip, deflate, sdch, br' -H 'Accept-Language: en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4' -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.100 Safari/537.36' -H 'Content-Type: application/json;charset=UTF-8' -H 'Accept: application/json, text/plain, */*' -H 'Referer: http://localhost:7400/' -H 'Connection: keep-alive' --data-binary '{"name":"b-service","request_path":"/v1/plus1and2","upstream_url":"http://b-service:5200/plus1and2","strip_request_path":true}' --compressed

curl 'http://localhost:8001/apis' -X PUT -H 'Origin: http://localhost:7400' -H 'Accept-Encoding: gzip, deflate, sdch, br' -H 'Accept-Language: en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4' -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.100 Safari/537.36' -H 'Content-Type: application/json;charset=UTF-8' -H 'Accept: application/json, text/plain, */*' -H 'Referer: http://localhost:7400/' -H 'Connection: keep-alive' --data-binary '{"name":"A-service","request_path":"/v1/calc","upstream_url":"http://a-service:5100/add","strip_request_path":true}' --compressed

curl 'http://localhost:8001/apis' -X PUT -H 'Origin: http://localhost:7400' -H 'Accept-Encoding: gzip, deflate, sdch, br' -H 'Accept-Language: en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4' -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.100 Safari/537.36' -H 'Content-Type: application/json;charset=UTF-8' -H 'Accept: application/json, text/plain, */*' -H 'Referer: http://localhost:7400/' -H 'Connection: keep-alive' --data-binary '{"name":"All-B-Service","request_path":"/v1/b/","upstream_url":"http://b-service:5200/","strip_request_path":true}' --compressed

curl 'http://localhost:8001/apis' -X PUT -H 'Origin: http://localhost:7400' -H 'Accept-Encoding: gzip, deflate, sdch, br' -H 'Accept-Language: en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4' -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.100 Safari/537.36' -H 'Content-Type: application/json;charset=UTF-8' -H 'Accept: application/json, text/plain, */*' -H 'Referer: http://localhost:7400/' -H 'Connection: keep-alive' --data-binary '{"name":"NodeAPI","request_path":"/v1/node/user","upstream_url":"http://nodejs:1337/user","strip_request_path":true}' --compressed
