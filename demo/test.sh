docker-compose down
sh rmi-images.sh
# docker-compose -f docker-compose.yml -f docker-compose.dev.yml up
cd springboot && gradle bootRe && cd ..
docker-compose up
