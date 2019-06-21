In case you already want to start playing with Querqy: I’ve uploaded the Icecat data and the Solr config to here: http://176.9.72.110/qq/

To install:

- You need Solr 5.1

- get querqy-solr-2.7.0-RC1-jar-with-dependencies.jar from  https://bintray.com/renekrie/maven/querqy/2.7.0-RC1/view#files/querqy/querqy-solr/2.7.0-RC1 and put it into Solr's lib folder.

@todo falls Du solr installierst - was in der anleitung noch fehlt: ein jar-file “solr-dataimporthandler....jar” muss noch vom solr-dist verzeichnis nach server/solr/lib kopiert werden

- create a core (qdemo) in Solr and use this as /conf: http://176.9.72.110/qq/solr/conf.tar.gz
@todo: hier noch definieren, in welches verzeichnis genau die heruntergeladene conf muss

- Get the 3 data files from here: http://176.9.72.110/qq/data/ and unpack the tar.gz files. Copy all *.xml files into one single folder (no sub-directories) (override, if already exists). Use the following order:
-- open-icecat[.tar.gz]
-- us.samsung[.tar.gz]
-- us.toshiba[.tar.gz]

- Go to <solr>/<core>/conf/ and edit data-config.xml -> change baseDir="/Users/rene/Developer/data/icecat/open-icecat” to the folder that contains the *.xml files. 

- Start Solr, go to the DataImportHandler and run a full-index (you should see 38413 docs).

The /select SearchHandler is already configured for Querqy: DisMax style, just throw some queries at it using q=... (no field names - they have been preconfigured). You can edit rules in conf/rules.txt - Core reload required.

