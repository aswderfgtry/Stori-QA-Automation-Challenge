mvn test -Dbrowser=firefox
mv -Fr target/surefire-reports Reports
read -n1 -s -r -p $'Press space to continue...\n' key