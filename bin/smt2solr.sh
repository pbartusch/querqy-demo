DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
SOLR_CONF="$DIR/../solr-home/qdemo/conf"
RULES=$SOLR_CONF/rules.txt
LANG=$1
AUTH=$2

DOWNLOAD_HTTP_STATUS=$(curl -w "%{http_code}" -s -o $RULES -H "X-AUTH-TOKEN: $AUTH" "http://localhost/.seem-api/querqy/commonrules/download?language=$LANG")  

if [ $? -ne 0 ]; then
    exit 1
fi

if [ $DOWNLOAD_HTTP_STATUS -ne "200" ]; then
   >&2 echo "Error: Download returned status code $DOWNLOAD_HTTP_STATUS"
   exit 2
fi

SOLR_STATUS=$(curl -s -i -XGET "http://localhost:8983/solr/admin/cores?wt=xml&action=RELOAD&core=qdemo")

if [ $? -ne 0 ]; then
    exit 16
fi

if ! [[ $SOLR_STATUS ==  *"200 OK"* ]]
then 
    >&2 echo "Error reloading Solr core: $SOLR_STATUS"
    exit 17
fi

if ! [[ $SOLR_STATUS ==  *"<int name=\"status\">0</int>"* ]]
then
    >&2 echo "Error reloading Solr core: $SOLR_STATUS"
    exit 18
fi

