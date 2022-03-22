package com.krishnajava.coronavirustracker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.krishnajava.coronavirustracker.models.LocationStats;
import com.krishnajava.coronavirustracker.services.CoronaVirusDataService;

@Controller
public class HomeController {
	
	@Autowired
	CoronaVirusDataService coronaVirusDataService;

	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = coronaVirusDataService.getAllStats();
		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotal()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		model.addAttribute("LocationStats",allStats);
		model.addAttribute("totalReportedCases",totalReportedCases);
		model.addAttribute("totalNewCases",totalNewCases);
		return "home";
	}
}
