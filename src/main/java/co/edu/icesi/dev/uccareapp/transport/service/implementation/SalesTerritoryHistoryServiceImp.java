package co.edu.icesi.dev.uccareapp.transport.service.implementation;

import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.customexeptions.InvalidValueException;
import co.edu.icesi.dev.uccareapp.transport.customexeptions.ObjectAlreadyExistException;
import co.edu.icesi.dev.uccareapp.transport.customexeptions.ObjectDoesNotExistException;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesperson;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritory;
import co.edu.icesi.dev.uccareapp.transport.model.sales.Salesterritoryhistory;
import co.edu.icesi.dev.uccareapp.transport.repository.SalesPersonRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.SalesTerritoryHistoryRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.SalesTerritoryRepository;
import co.edu.icesi.dev.uccareapp.transport.service.interfaces.SalesTerritoryHistoryService;
@Service
public class SalesTerritoryHistoryServiceImp implements SalesTerritoryHistoryService {
	
	private SalesTerritoryHistoryRepository salesTerritoryHistoryRepository;
	private SalesTerritoryRepository salesTerritoryRepository;
	private SalesPersonRepository salesPersonRepository;
	@Autowired
	public SalesTerritoryHistoryServiceImp(SalesTerritoryHistoryRepository sthr,SalesTerritoryRepository str,SalesPersonRepository spr) {
		salesTerritoryHistoryRepository = sthr;
		salesTerritoryRepository = str;
		salesPersonRepository = spr;
	}

	@Override
	public void add(Salesterritoryhistory salesTerritoryHistory,Integer businessId, Integer territoryId) throws InvalidValueException, ObjectDoesNotExistException, ObjectAlreadyExistException {
		if(
			salesTerritoryHistory.getId()==null||
			salesTerritoryHistory.getEnddate()==null||
			salesTerritoryHistory.getModifieddate()==null||
			businessId == null||
			territoryId==null){
			
				throw new NullPointerException("Values empties or null");
		}
		
		Optional<Salesterritoryhistory> opTerriHistory =  this.salesTerritoryHistoryRepository.findById(salesTerritoryHistory.getId());
		Optional<Salesterritory> opTerritory = this.salesTerritoryRepository.findById(territoryId);
		Optional<Salesperson> opSalesPerson = this.salesPersonRepository.findById(businessId);
		
		
		if(opTerriHistory.isEmpty()) {
			if(salesTerritoryHistory.getEnddate().compareTo(Timestamp.valueOf(LocalDateTime.now()))>0) {
				throw new InvalidValueException("The end date have to be lower than the current date");
			}
			if(salesTerritoryHistory.getModifieddate().compareTo(salesTerritoryHistory.getEnddate())>=0) {
				throw new InvalidValueException("The moddified data no should be equals or higgier to the end data");
			}
			if(opSalesPerson.isEmpty()) {
				throw new ObjectDoesNotExistException("this sales person does not exist");
			}
			if(opTerritory.isEmpty()) {
				throw new ObjectDoesNotExistException("The territory does not exist");
			}
			salesTerritoryHistory.setSalesperson(opSalesPerson.get());
			salesTerritoryHistory.setSalesterritory(opTerritory.get());
			this.salesTerritoryHistoryRepository.save(salesTerritoryHistory);
		}else {
			throw new ObjectAlreadyExistException("This ID already Exist");
		}
		
	}

	@Override
	public void edit(Salesterritoryhistory salesTerritoryHistory) throws InvalidValueException, ObjectDoesNotExistException {
		if(		salesTerritoryHistory.getSalesperson()==null||
				salesTerritoryHistory.getSalesterritory()==null||
				salesTerritoryHistory.getId()==null||
				salesTerritoryHistory.getEnddate()==null||
				salesTerritoryHistory.getModifieddate()==null){
				
					throw new NullPointerException("Values empties or null");
		}
		Optional<Salesterritoryhistory> opTerritoryHistory =  this.salesTerritoryHistoryRepository.findById(salesTerritoryHistory.getId());
		
		if(!opTerritoryHistory.isEmpty()) {
			if(salesTerritoryHistory.getEnddate().compareTo(Timestamp.valueOf(LocalDateTime.now()))>0) {
				throw new InvalidValueException("The end date have to be lower than the current date");
			}
			
			if(salesTerritoryHistory.getModifieddate().compareTo(salesTerritoryHistory.getEnddate())>=0) {
				throw new InvalidValueException("The moddified data no should be equals or higgier to the end data");
			}
			Salesterritoryhistory oldTerritoryHistory = opTerritoryHistory.get();
			oldTerritoryHistory.setEnddate(salesTerritoryHistory.getEnddate());
			oldTerritoryHistory.setModifieddate(salesTerritoryHistory.getModifieddate());
			this.salesTerritoryHistoryRepository.save(oldTerritoryHistory);
		}else {
			throw new ObjectDoesNotExistException("This id does not exist");
		}
	}

	@Override
	public Optional<Salesterritoryhistory> findById(Integer id) {
		
		return this.salesTerritoryHistoryRepository.findById(id);
	}

	@Override
	public Iterable<Salesterritoryhistory> findAll() {
		
		return this.salesTerritoryHistoryRepository.findAll();
	}

	@Override
	public void clear() {
		
		this.salesTerritoryHistoryRepository.deleteAll();
	}


}
