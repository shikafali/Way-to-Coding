package com.project.manager.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.manager.entity.HomeManager;
import com.project.manager.exception.InvalidNameException;
import com.project.manager.exception.ManagerNotFoundException;
import com.project.manager.repository.AssignedRepository;
import com.project.manager.repository.ManagerRepository;

@Service
public class ManagerService {

	@Autowired
	private ManagerRepository managerRepository;

	public List<HomeManager> getAllManagers() {
		return managerRepository.findAll();
	}

	public HomeManager getManagerById(int id) throws Exception {
		return managerRepository.findById(id)
				.orElseThrow(() -> new ManagerNotFoundException("Manager with ID: " + id + " not found"));
	}

	public HomeManager deleteManagerById(int id) throws ManagerNotFoundException {
		HomeManager manager = managerRepository.findById(id)
				.orElseThrow(() -> new ManagerNotFoundException("Manager with ID: " + id + " not found"));
		managerRepository.deleteById(id);
		return manager;
	}

	public HomeManager addManager(HomeManager homeManager) throws Exception {
		validateHomeManager(homeManager);
		homeManager.setRegDate(Instant.now());
		return managerRepository.save(homeManager);
	}

	public HomeManager updateManager(int id, HomeManager homeManager) throws Exception {
		HomeManager manager = managerRepository.findById(id)
				.orElseThrow(() -> new ManagerNotFoundException("Manager with ID: " + id + " not found"));
		manager.setFirstName(homeManager.getFirstName());
		manager.setLastName(homeManager.getLastName());
		validateHomeManager(manager);
		return managerRepository.save(manager);
	}

	private void validateHomeManager(HomeManager homeManager) throws Exception {
		if (homeManager.getFirstName().isBlank())
			throw new InvalidNameException("Invalid Name!");

	}

}