package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import play.api.Play.current
import play.api.i18n.Messages.Implicits._

import anorm._

import views._
import models._

/**
 * Manage a database of computers
 */
class Application extends Controller { 
  
  /**
   * This result directly redirect to the application home.
   */
  val Home = Redirect(routes.Application.search("", 0))
  

  // -- Actions

  /**
   * Handle default path requests, redirect to computers list
   */  
  def index = Action { Home }
  
  /**
   * Display the paginated list of computers.
   *
   * @param page Current page number (starts from 0)

   * @param query The search query text
   */
  def search(query: String, page: Int = 0) = Action { implicit request =>
    Ok(html.list(
      Search.find(query, page), query
    ))
  }
  


}
            
