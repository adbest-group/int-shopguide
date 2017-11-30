PRGDIR=`dirname $0`
#echo $PRGDIR
SERVER_HOME=$(echo `readlink -f $PRGDIR` | sed 's/\/bin//')
cd $SERVER_HOME
#echo $SERVER_HOME
mvn clean package
exit 0