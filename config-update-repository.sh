#@echo "Adding files on repository..."
git add .

#@echo "adding url of distant repository"
git remote add myorigin https://rentalfilm72@github.com/rentalfilms72/stage-ecommerce.git

#@echo "Our first commit..."
git commit -m "UPDATE"

#@echo "pushing..."
git push -u myorigin master