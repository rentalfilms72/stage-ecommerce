# ###############################################################################################
# Util link                                                                                     #
# https://unix.stackexchange.com/questions/86722/how-do-i-loop-through-only-directories-in-bash #
# https://devconnected.com/how-to-check-if-file-or-directory-exists-in-bash/                    #
#################################################################################################
#if [ -d "$f" ]; then
        # $f is a directory
 #   fi


# Function to implement the pause command
function pause(){
 read -s -n 1 -p "Press any key to continue . . ."
 echo ""
}

for directory in */ ; do
	# enter in a directory
	cd "$directory"
	
	if [[ -f "pom.xml" ]]; then
		echo "<pom.xml> exists on your filesystem."
		# In each directories create the jar file
		echo ""
		echo ""
		echo "############################################################"
		echo "## >> $directory"
		echo "############################################################"
		echo "Creation of jar file..."
		mvn -DskipTests clean install 
	fi
	
	# Back to the parent directory
	cd ..
done

# Launch the comand docker-compose up
docker-compose up


echo ""
echo ""
pause
sleep 30m