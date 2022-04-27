mvn test -Dbrowser=opera
mv -Fr target/surefire-reports Reports
read -n1 -s -r -p $'Press space to continue...\n' key