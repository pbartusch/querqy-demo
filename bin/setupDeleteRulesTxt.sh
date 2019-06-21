#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
SOLR_CONF="$DIR/../solr-home/qdemo/conf"
RULES=$SOLR_CONF/rules.txt

truncate -s 0 ${RULES}
#./smt2solr.sh $1

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

