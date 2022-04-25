package co.edu.icesi.dev.uccareapp.transport.controller.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.InvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.customexeptions.ObjectAlreadyExistException;
import co.edu.icesi.dev.uccareapp.transport.customexeptions.ObjectDoesNotExistException;
import co.edu.icesi.dev.uccareapp.transport.model.person.Businessentity;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesperson;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.repository.BusinessentityRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.SalesTerritoryRepository;
import co.edu.icesi.dev.uccareapp.transport.service.implementation.SalesPersonServiceImp;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.SalesPersonService;

@Controller
public class SalesPersonControllerImp {
	
	private SalesPersonService salesPersonService;
	private BusinessentityRepository businessentityRepository;
	private SalesTerritoryRepository salesTerritoryRepository;
	
	@Autowired
	public SalesPersonControllerImp(SalesPersonServiceImp sps,BusinessentityRepository br,SalesTerritoryRepository str) {
		this.salesPersonService = sps;
		this.businessentityRepository = br;
		this.salesTerritoryRepository = str;
	}
	
	@GetMapping("/sales_persons")
	public String salesPersons(Model model) {
		model.addAttribute("sales_persons", salesPersonService.findAll());
		return "sales/person/index";
	}
	
	@GetMapping("/sales_persons/add")
	public String addSalesPerson(Model model) {
		
		Iterable<Businessentity> entities =	businessentityRepository.findAll();
		List<Integer> bussinessentities_ids =  new ArrayList<Integer>();
		for(Businessentity entity : entities) {
			bussinessentities_ids.add(entity.getBusinessentityid());
		}
		Iterable<Salesterritory> territories = salesTerritoryRepository.findAll();
		
		model.addAttribute("businessentities_ids", bussinessentities_ids.iterator());
		model.addAttribute("territories", territories.iterator());
		model.addAttribute("sales_person", new Salesperson());
		return "sales/person/add-sales-person";
	}
	
	@PostMapping("/sales_persons/add")
	public String saveSalesPerson(Model model,  Salesperson sales_person) {
		try {
			if(sales_person.getBusinessentityid()==-1) {
				Businessentity be = new Businessentity();
				businessentityRepository.save(be);
				
				Businessentity last = null;
				for(Businessentity aux : businessentityRepository.findAll()) {
					if(last==null) {
						last = aux;
					}else if(aux.getBusinessentityid()>last.getBusinessentityid()) {
						last = aux;
					}
				}
				sales_person.setBusinessentityid(last.getBusinessentityid());
			}
			salesPersonService.add(sales_person,  sales_person.getBusinessentityid(),sales_person.getSalesterritory().getTerritoryid());
		} catch (InvalidValueException | ObjectAlreadyExistException | ObjectDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/sales_persons";
	}
}
