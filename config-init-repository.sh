# Function to implement the pause command
function pause(){
 read -s -n 1 -p "Press any key to continue . . ."
 echo ""
}

#@echo "Initialisation of local repository GIT..."
git init

#@echo "Adding files on repository..."
git add .

#@echo "adding url of distant repository"
git remote add myorigin https://rentalfilm72@github.com/rentalfilms72/stage-ecommerce.git

#@echo "Our first commit..."
git commit -m "First commit"

#@echo "pushing..."
git push -u myorigin master

echo ""
echo ""
# pause
sleep 30