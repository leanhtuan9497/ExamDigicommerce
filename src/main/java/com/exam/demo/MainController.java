package com.exam.demo;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@Autowired
	private com.exam.demo.EmplRepository emplRepository;

	private static final String[] FIRSTNAMES = new String[] { "Nguyen", "Le", "Tran" };
	private static final String[] LASTNAMES = new String[] { "Tuan", "Hang", "Lam" };
	private static final Double[] MONTHLYSALARY = new Double[] { 123.56, 777.25, 555.36 };
	private static final String[] PROGRAMMINGLANGUAGE = new String[] { "Java", "C#", "Python" };
	private static final Boolean[] AUTOMATED = new Boolean[] { true, false };

	@ResponseBody
	@RequestMapping("/")
	public String home() {
		String html = "";
		html += "<ul>";
		html += " <li><a href='/CreateDeveloper'>Create 5 Developer</a></li>";
		html += " <li><a href='/CreateTester'>Create 5 Tester</a></li>";
		html += " <li><a href='/ShowAllEmployee'>Show All Employee</a></li>";
		html += " <li><a href='/UpdateMonthlySalary'>Update Monthly Salary 10%</a></li>";
		html += " <li><a href='/DeleteLowestSalary'>Delete 2 Lowest Salary</a></li>";
		html += "</ul>";
		return html;
	}

	@ResponseBody
	@RequestMapping("/CreateDeveloper")
	public String createDeveloper() {
		String html = "<a href='/'>back</a><br>";
		try {
			int i = 5;
			Random random = new Random();
			while (i-- > 0) {
				Developer dev = new Developer();
				dev.setFirstName(FIRSTNAMES[random.nextInt(3)]);
				dev.setLastName(LASTNAMES[random.nextInt(3)]);
				dev.setMonthlySalary(MONTHLYSALARY[random.nextInt(3)]);
				dev.setProgrammingLanguage(PROGRAMMINGLANGUAGE[random.nextInt(3)]);
				emplRepository.save(dev);
			}
			html += "Complete";
		} catch (Exception e) {
			// TODO: handle exception
			html += "failed: " + e.getMessage();
		}
		return html;
	}

	@ResponseBody
	@RequestMapping("/CreateTester")
	public String createTester() {
		String html = "<a href='/'>back</a><br>";
		try {
			int i = 5;
			Random random = new Random();
			while (i-- > 0) {
				Tester tester = new Tester();
				tester.setFirstName(FIRSTNAMES[random.nextInt(3)]);
				tester.setLastName(LASTNAMES[random.nextInt(3)]);
				tester.setMonthlySalary(MONTHLYSALARY[random.nextInt(3)]);
				tester.setAutomated(AUTOMATED[random.nextInt(2)]);
				emplRepository.save(tester);
			}
			html += "Complete";
		} catch (Exception e) {
			// TODO: handle exception
			html += "failed: " + e.getMessage();
		}
		return html;
	}

	@ResponseBody
	@RequestMapping("/ShowAllEmployee")
	public String showAllEmployee() {
		Iterable<Employee> employees = this.emplRepository.findAll();

		String html = "<a href='/'>back</a><br>";
		for (com.exam.demo.Employee emp : employees) {
			html += emp + "<br>";
		}
		return html;
	}

	@ResponseBody
	@RequestMapping("/UpdateMonthlySalary")
	public String updateMonthlySalary() {
		String html = "<a href='/'>back</a><br>";
		try {
			List<Employee> employees = this.emplRepository.findAll();
			for (com.exam.demo.Employee emp : employees) {
				html += emp + "<br>";
			}
//            this.emplRepository.updateMonthlySalary(10);
			employees.stream().parallel().forEach(i -> {
				i.setMonthlySalary(i.getMonthlySalary() * (0.9));
				this.emplRepository.saveAndFlush(i);
			});
			html += " <li><a href='/ShowAllEmployee'>Show All Employee After Update</a></li>";
		} catch (Exception e) {
			// TODO: handle exception
			html += "failed: " + e.getMessage();
		}

		return html;
	}

	@ResponseBody
	@RequestMapping("/DeleteLowestSalary")
	public String deleteLowestSalary() {
		String html = "<a href='/'>back</a><br>";
		try {
			this.emplRepository
					.deleteAll(this.emplRepository.findAll(Sort.by(Sort.Direction.ASC, "MonthlySalary")).subList(0, 2));
			html = "Complete<br>";
			html += " <li><a href='/ShowAllEmployee'>Show All Employee After Delete</a></li>";
		} catch (Exception e) {
			// TODO: handle exception
			html += "failed: " + e.getMessage();
		}
		return html;
	}
}
