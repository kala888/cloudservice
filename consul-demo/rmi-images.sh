docker rmi -f $(docker images | grep "^kala888/demo-" | awk '{print $3}')
