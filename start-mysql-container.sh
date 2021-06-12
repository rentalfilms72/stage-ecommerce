# Function to implement the pause command
function pause(){
 read -s -n 1 -p "Press any key to continue . . ."
 echo ""
}
echo "> START MYSQL CONTAINER <"

docker compose up mysql-cloud -d --force-recreate 

echo ""
echo ""
# pause
sleep 30