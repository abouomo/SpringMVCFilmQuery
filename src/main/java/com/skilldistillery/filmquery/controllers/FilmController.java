package com.skilldistillery.filmquery.controllers;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping(path= {"/", "index.do"}) // handling both path (/ and index.do)
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("WEB-INF/index.jsp");
		
		return mv;
	}
	
	@RequestMapping(path= {"findById.do"})
	public ModelAndView findFilmById( @RequestParam("id") int id ) {
		ModelAndView mv = new ModelAndView();
		
		try {
			Film film = dao.findFilmById(id);
			mv.addObject(film);
			mv.setViewName("WEB-INF/film.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mv;
		
	}
}
