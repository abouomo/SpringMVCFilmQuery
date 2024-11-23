package com.skilldistillery.filmquery.controllers;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.entities.Film;

@Controller
public class FilmController {

	private final DatabaseAccessor dao;

	public FilmController(DatabaseAccessor dao) {
		this.dao = dao;
	}

	/**
	 * To handle the home page
	 * 
	 * @return
	 */
	@RequestMapping(path = { "/", "index.do" }) // handling both path (/ and index.do)
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("WEB-INF/index.jsp");

		return mv;
	}

	/**
	 * Search film by ID
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(path = { "findById.do" })
	public ModelAndView findFilmById(@RequestParam("id") int id) {
		ModelAndView mv = new ModelAndView();

		try {
			Film film = dao.findFilmById(id);
			if (film != null) {
				mv.addObject("film", film); // Pass film details to the JSP.

			} else {
				mv.addObject("film", null);
				mv.addObject("message", "No film found for id: " + id);

			}
			mv.setViewName("WEB-INF/film.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
			mv.setViewName("WEB-INF/error.jsp");
		}

		return mv;
	}

	/**
	 * Show Add Film Form
	 * 
	 * @return
	 */
	@RequestMapping(path = { "addFilmForm.do" })
	public String showAddFilmForm() {
		return "WEB-INF/addFilm.jsp"; // Load the form for adding a new film.
	}

	/**
	 * To Handle Add Film
	 * 
	 * @param title
	 * @param description
	 * @return
	 */
	@RequestMapping(path = { "addFilm.do" }, method = RequestMethod.POST)
	public ModelAndView addFilm(@RequestParam("title") String title, @RequestParam("description") String description) {
		ModelAndView mv = new ModelAndView();
		try {
			// Create a Film object with user inputs
			Film film = new Film();
			film.setTitle(title);
			film.setDescription(description);

			// Default values for the additional fields
			film.setReleaseYear(1993);
			film.setRentalDuration(6);
			film.setRentalRate(0.99);
			film.setLength(86);
			film.setReplacementCost(20.99);
			film.setRating("PG");
			film.setSpecialFeatures("Deleted Scenes");

			Film addedFilm = dao.createFilm(film);

			if (addedFilm != null) {
				mv.addObject("message", "Film added successfully! ID: " + addedFilm.getId());
			} else {
				mv.addObject("message", "Failed to add the film. Please try again.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		mv.setViewName("WEB-INF/addFilm.jsp");
		return mv;
	}

	/**
	 * Show delete film form
	 * @param id
	 * @return
	 */
	@RequestMapping(path = {"deleteFilmForm.do"}, method = RequestMethod.GET)
	public ModelAndView showDeleteFilmForm(@RequestParam("id") int id) {
	    ModelAndView mv = new ModelAndView();

	    try {
	        Film film = dao.findFilmById(id);

	        if (film != null) {
	            mv.addObject("film", film); 
	            mv.setViewName("WEB-INF/deleteFilm.jsp");
	        } else {
	            mv.addObject("message", "Film with ID " + id + " not found.");
	            mv.setViewName("WEB-INF/error.jsp"); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        mv.setViewName("WEB-INF/error.jsp");
	    }

	    return mv;
	}

	/**
	 * To handle Delete Film
	 * @param id
	 * @return
	 */
	@RequestMapping(path = {"deleteFilm.do"}, method = RequestMethod.POST)
	public ModelAndView deleteFilm(@RequestParam("id") int id) {
	    ModelAndView mv = new ModelAndView();

	    try {
	        Film film = dao.findFilmById(id);

	        if (film != null) { // Film exists
	            boolean deleted = dao.deleteFilm(film);

	            if (deleted) {
	                mv.addObject("message", "Film with ID " + id + " deleted successfully.");
	            } else {
	                mv.addObject("message", "Failed to delete film with ID " + id + ". Please try again.");
	            }
	        } else {
	            mv.addObject("message", "Film with ID " + id + " not found.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    mv.setViewName("WEB-INF/film.jsp");
	    return mv;
	}
	
	/**
	 * Show update film form
	 * @param id
	 * @return
	 */
	@RequestMapping(path = {"updateFilmForm.do"}, method = RequestMethod.GET)
	public ModelAndView showUpdateFilmForm(@RequestParam("id") int id) {
	    ModelAndView mv = new ModelAndView();

	    try {
	        Film film = dao.findFilmById(id);

	        if (film != null) {
	            mv.addObject("film", film); 
	            mv.setViewName("WEB-INF/updateFilm.jsp"); 
	        } else {
	            mv.addObject("message", "Film with ID " + id + " not found.");
	            mv.setViewName("WEB-INF/error.jsp"); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        mv.addObject("message", "An error occurred while retrieving the film for update.");
	        mv.setViewName("WEB-INF/error.jsp");
	    }

	    return mv;
	}

	/**
	 * To handle Update Film
	 * @param id
	 * @return
	 */
	@RequestMapping(path = {"updateFilm.do"}, method = RequestMethod.POST)
	public ModelAndView updateFilm(
	    @RequestParam("id") int id,
	    @RequestParam("title") String title,
	    @RequestParam("description") String description
	) {
	    ModelAndView mv = new ModelAndView();

	    try {
	        Film film = dao.findFilmById(id);

	        if (film != null) {
	            film.setTitle(title);
	            film.setDescription(description); 

	            boolean updated = dao.updateFilm(film);

	            if (updated) {
	                mv.addObject("message", "Film with ID " + id + " updated successfully.");
	            } else {
	                mv.addObject("message", "Failed to update film with ID " + id + ". Please try again.");
	            }
	        } else {
	            mv.addObject("message", "Film with ID " + id + " not found.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    mv.setViewName("WEB-INF/updateFilm.jsp");
	    return mv;
	}
}
