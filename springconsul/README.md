# Cloud Service

## Services

## service1
* provid add service
* curl http://localhost:8080/add?a=13&b=2

## service2
* provide 1+2 service
* curl http://localhost:8180/plus1and2

## client1
* consul client which will call remote service of service1
* curl http://localhost:8280/discover
* curl http://localhost:8280/services
* curl http://localhost:8280/remote/add?a=4&b=2

## consul
* consul server
