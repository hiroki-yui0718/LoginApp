package com.tuyano.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.repositories.UserRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RestController; JSONやXMLを返す

@Controller
public class UserController {
	@Autowired
	UserRepository repository; //コントローラーからモデルをいじるときに必要
	
	@PersistenceContext
	EntityManager entityManager;
	
	UserDaoImpl dao;
	
	@PostConstruct
	public void init() {
		dao = new UserDaoImpl(entityManager);
	}
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public ModelAndView user(@ModelAttribute("formModel") User user,ModelAndView mav) {
		mav.setViewName("user");
		return mav;
	}
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public ModelAndView send(@ModelAttribute("formModel") User user,ModelAndView mav) {
		repository.saveAndFlush(user);
		return new ModelAndView("redirect:/user");
	}
	@RequestMapping(value="/show",method=RequestMethod.GET) //SELECT文だけだったら特にいらん
	public ModelAndView show(ModelAndView mav) {
		mav.setViewName("show");
		Iterable<User> list = dao.getAll();
		mav.addObject("datalist",list);
		return mav;
	}
}

