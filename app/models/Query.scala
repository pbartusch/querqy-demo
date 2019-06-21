package models

import org.apache.solr.client.solrj.SolrQuery
import org.apache.solr.client.solrj.impl.HttpSolrClient
import scala.collection.JavaConversions._

/**
 * Created by rene on 03/11/2015.
 */

case class Hit(title: String, longSummary: String, shortDescription: String, longDescription: String, imageUrl: String)

object Search {

  val solr = new HttpSolrClient("http://localhost:8983/solr/qdemo")

  def find(q: String, page: Int = 0, pageSize: Int = 20): Page[Hit] = {

    val start = page * pageSize

    if (q.isEmpty) {

      Page(List.empty[Hit], page, start, 0, "")

    } else {

      val query = new SolrQuery(q)
      query.setStart(start)
      query.setRows(pageSize)

      val response = solr.query(query)
      val results = response.getResults

      // get querqy_decorations as simple String, if present
      var idx = 0;
      var debugDecorateInfo = "";
      while( idx < response.getResponse.size ) {
        if( response.getResponse.getName(idx).equals("querqy_decorations") ) {
          debugDecorateInfo = response.getResponse.getVal(idx).toString
        }
        idx = idx + 1
      }

      Page(results.map { solrDoc =>
        Hit(
          solrDoc.getOrElse("title", "").toString,
          solrDoc.getOrElse("longSummary", "").toString,
          solrDoc.getOrElse("shortDescription", "").toString,
          solrDoc.getOrElse("longDescription", "").toString,
          solrDoc.getOrElse("thumbUrl", "").toString)
      }, page, start, results.getNumFound, debugDecorateInfo)

    }

  }

}

