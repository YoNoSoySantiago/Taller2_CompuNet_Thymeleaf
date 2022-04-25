package co.edu.icesi.dev.uccareapp.transport.controller.implementation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.InvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.customexeptions.ObjectDoesNotExistException;
import co.edu.icesi.dev.uccareapp.transport.model.person.Countryregion;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.repository.CountryRegionRepository;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.SalesTerritoryService;

@Controller
public class SalesTerritoryControllerImp {
	
	private SalesTerritoryService salesTerritoryService;
	private CountryRegionRepository countryRegionRepository;
	
	public SalesTerritoryControllerImp(SalesTerritoryService sts,CountryRegionRepository crr) {
		this.salesTerritoryService = sts;
		this.countryRegionRepository = crr;
	}
	
	@GetMapping("/sales_territories")
	public String salesTerritories(Model model) {
		model.addAttribute("territories", salesTerritoryService.findAll());
		System.out.println(salesTerritoryService.findAll().toString());
		return "sales/territory/index";
	}
	
	@GetMapping("/sales_territories/add")
	public String addSalesTerritory(Model model) {
		model.addAttribute("territory", new Salesterritory());
		model.addAttribute("countries_region", countryRegionRepository.findAll());
		return "sales/territory/add-sales-territory";
	}
	
	@PostMapping("/sales_territories/add")
	public String saveSalesTerritory(Model model, Salesterritory salesTerritory) {
		try {
			salesTerritoryService.add(salesTerritory);
		} catch (InvalidValueException | ObjectDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/sales_territories";
	}
}
