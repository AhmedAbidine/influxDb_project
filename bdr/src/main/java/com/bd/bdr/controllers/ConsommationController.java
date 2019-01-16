package com.bd.bdr.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bd.bdr.dao.ConsommationDao;
import com.bd.bdr.model.Consommation;

@Controller
public class ConsommationController {
	
	private ConsommationDao  consommationDao = new ConsommationDao();
	
	
	@RequestMapping(value="/consommation")
	public String getConsommation(Model model,
			@RequestParam(value = "nom", required = false) String nom,
			@RequestParam(value = "prenom", required = false) String prenom,
			@RequestParam(value = "pd", required = false) String pd,
			@RequestParam(value = "pf", required = false) String pf) {
		
		float somme=0;
		float moy=0;
		List<Consommation> consommations = consommationDao.getAllByName(nom,prenom,pd,pf);
		
		//List<Consommation> consommations1 = consommationDao.getAllByConsommation(consommation);
		if(consommations.size()!=0) {
			for (Consommation consommation : consommations) {
				somme+=consommation.getConsommation();
			}
			moy=somme/consommations.size();
		}
		
		model.addAttribute("consommations", consommations);
		model.addAttribute("moy",moy);
		//model.addAttribute("consommations1",consommations1);
		return "consommation";

    
	}}
