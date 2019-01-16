package com.bd.bdr.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bd.bdr.dao.MeteoDao;
import com.bd.bdr.model.Consommation;
import com.bd.bdr.model.Meteo;

@Controller
public class MeteoController {

	private MeteoDao meteoDao = new MeteoDao();

	@GetMapping("/meteo")
	public String getMeteo(Model model,
			@RequestParam(value = "v", required = false) String ville,
			@RequestParam(value = "pd", required = false) String pd,
			@RequestParam(value = "pf", required = false) String pf) {
		
		float somme=0;
		float moye=0;
		 List<Meteo> meteos=new ArrayList<Meteo>();
		List<Meteo> meteos1 = meteoDao.getAllByVille(ville,pd,pf);
		if(meteos1.size()!=0) {
			for (Meteo meteo : meteos1) {
				somme+=meteo.getTemperature();
			}
			moye=somme/meteos1.size();
		}
		model.addAttribute("meteos", meteos);
		model.addAttribute("meteos1",meteos1);
		model.addAttribute("moye",moye);
		return "meteo";
	}
	
	/*
	@GetMapping("/consommation")
	public String getConsommation(Model model,
			@RequestParam(value = "nom", required = false) String nom,
			@RequestParam(value = "prenom", required = false) String prenom,
			@RequestParam(value = "consommation", required = false) String consommation) {
		
		List<Consommation> consommations = meteoDao.getAllByName(nom, prenom);
		List<Consommation> consommations1 = meteoDao.getAllByConsommation(consommation);
		model.addAttribute("consommations", consommations);
		model.addAttribute("consommations1",consommations1);
		return "consommation";
	}*/
	
	
	@GetMapping("/")
	public String afficher() 
	{
		return "meteo";
	}
}
