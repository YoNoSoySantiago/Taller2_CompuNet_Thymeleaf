package co.edu.icesi.dev.uccareapp.transport.controller.implementation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.icesi.dev.uccareapp.transport.repository.SalesTerritoryHistoryRepository;

@Controller
public class SalesTerritoryHistoryControllerImp {
	
	private SalesTerritoryHistoryRepository salesTerritoryHistoryRepository;
	
	public SalesTerritoryHistoryControllerImp(SalesTerritoryHistoryRepository sthr) {
		this.salesTerritoryHistoryRepository = sthr;
	}
	
	@GetMapping("/sales_territories_history")
	public String salesTerritories(Model model) {
		model.addAttribute("territories-history", salesTerritoryHistoryRepository.findAll());
		System.out.println("ENtreee");
		return "history/territory/index";
	}
}
