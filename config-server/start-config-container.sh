# Function to implement the pause command
function pause(){
 read -s -n 1 -p "Press any key to continue . . ."
 echo ""
}
echo "> DOCKERISATION OF CONFIG SERVER MICROSERVICE <"
docker compose up -d --build --force-recreate 

echo ""
echo ""
pause
sleep 2m