Simple Scala/Play application that provides a demo frontend on top of a local Solr+querqy (https://github.com/renekrie/querqy) instance. You can also use SMUI (https://github.com/pbartusch/smui) to maintain your querqy rules.


@todo reference working Solr config

To install:

- You need Solr 5.1

- get querqy-solr-2.7.0-RC1-jar-with-dependencies.jar from https://bintray.com/renekrie/maven/querqy/2.7.0-RC1/view#files/querqy/querqy-solr/2.7.0-RC1 and put it into Solr's lib folder.

@todo falls Du solr installierst - was in der anleitung noch fehlt: ein jar-file “solr-dataimporthandler....jar” muss noch vom solr-dist verzeichnis nach server/solr/lib kopiert werden

- create a core (qdemo) in Solr and use this as /conf: http://176.9.72.110/qq/solr/conf.tar.gz
@todo: hier noch definieren, in welches verzeichnis genau die heruntergeladene conf muss

@todo create a demo product index (maybe out of icecat / https://icecat.biz/de-ch?)

- Start Solr, go to the DataImportHandler and run a full-index (you should see 38413 docs).

The /select SearchHandler is already configured for Querqy: DisMax style, just throw some queries at it using q=... (no field names - they have been preconfigured). You can edit rules in conf/rules.txt - Core reload required.
