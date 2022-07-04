package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.PaypalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf = new  SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Enter contract date");
		System.out.println("Number: ");
		int number = sc.nextInt();
		System.out.println("Date (dd/MM/yyyy):");
		Date date = sdf.parse(sc.next());
		System.out.println("Contract value: ");
		double totalValue = sc.nextDouble();
		
		//instanciar contrato
		
		Contract contract = new Contract(number, date, totalValue);
		
		System.out.println("Enter number of installments: ");
		int quantityMonths = sc.nextInt();
		
		//processar contrato(instanciar services), injetar dependencia
		
		ContractService cs = new ContractService(new PaypalService());
		
		cs.processContract(contract, quantityMonths);
		
		System.out.println("Instalments: ");
		for(Installment it : contract.getInstallments()) {
			System.out.println(it);
		}
		sc.close();

	}

}
