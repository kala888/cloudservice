docker rmi -f $(docker images | grep "^demo_" | awk '{print $3}')
