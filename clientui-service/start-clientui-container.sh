# Function to implement the pause command
function pause(){
 read -s -n 1 -p "Press any key to continue . . ."
 echo ""
}
echo "> DOCKERISATION OF CLIENTUI-SERVICE MICROSERVICE <"
# Go back to the parent repository
cd ..

# Dangling images are not referenced by other images and are safe to delete
docker rmi -f $(docker images -f "dangling=true" -q)

docker compose up clientui-service -d --build --force-recreate 

echo ""
echo ""
# pause
sleep 30