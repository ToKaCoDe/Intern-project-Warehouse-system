package Warehouse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class WarehouseInfoSystem {

	public static void main(String[] args) {

		ArrayList<Items> productList = new ArrayList<>();

		Scanner scan = new Scanner(System.in);

		boolean t = true;
		while (t) {
			System.out.println("\n-------------------------------- ");
			System.out.println("              MENIU:              ");
			System.out.println("-------------------------------- ");
			System.out.println("|1:         Visas sąršas         |" + "\n -------------------------------- "
					+ "\n|2:    Mažas prekių kiekis       |" + "\n -------------------------------- "
					+ "\n|3: Pasibaigusi galiojimo data   |" + "\n -------------------------------- "
					+ "\n|4:          Išjungti            |" + "\n -------------------------------- \n");
			System.out.println("Įveskite MENIU eilės numerį:");

			char c = scan.next().charAt(0);
			switch (c) {
			case '1':
				System.out.println("Jūsų prekių sąrašas:");
				System.out.println("\nItem Name, Code, Quantity, Expiration Date");
				fileReading(productList);
				listPrinting(productList);
				break;
			case '2':
				fileReading(productList);
				System.out.println("Įveskite prekių kiekį:");
				int quantinty = scan.nextInt();
				System.out.println("Jūsų prekių likutis mažesnis už: " + quantinty);
				System.out.println("\nItem Name, Code, Quantity, Expiration Date");
				listPrinting(quantityLow(quantinty, productList));
				break;
			case '3':
				fileReading(productList);
				System.out.println("Įveskite datą (MMMM-MM-DD):");
				scan.nextLine();
				String date = scan.nextLine();
				System.out.println("Pasibaigusios galiojimo datos prekių sąrašas:");
				System.out.println("\nItem Name, Code, Quantity, Expiration Date");
				listPrinting(dateLow(date, productList));
				break;
			case '4':
				System.out.println("\nIšjungta...\n");
				t = false;
				break;
			}
		}
		scan.close();
	}

	public static ArrayList<Items> dateLow(String lowDate, ArrayList<Items> lowExirationDate) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date testDate = df.parse(lowDate);
			for (int i = 0; i < lowExirationDate.size(); i++) {
				Date expDate = df.parse(lowExirationDate.get(i).expirationDate);

				if (expDate.after(testDate)) {
					lowExirationDate.remove(i);
					i--;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return lowExirationDate;
	}

	public static ArrayList<Items> quantityLow(int input, ArrayList<Items> lowQuantity) {

		for (int i = 0; i < lowQuantity.size(); i++) {
			if (lowQuantity.get(i).quantity >= input) {
				lowQuantity.remove(i);
				i--;
			}
		}
		return lowQuantity;
	}

	public static void listPrinting(ArrayList<Items> productList) {

		productList.sort(null);

		for (Items value : productList) {
			System.out.print(value.getItemName() + ", ");
			System.out.print(value.getCode() + ", ");
			System.out.print(value.getQuantity() + ", ");
			System.out.println(value.getExpirationDate());
		}
	}

	public static void fileReading(ArrayList<Items> productList) {
		String filePath = "sample.csv";
		String product = null;

		try (FileReader fr = new FileReader(filePath); BufferedReader br = new BufferedReader(fr)) {

			br.readLine();
			while ((product = br.readLine()) != null) {

				boolean isLine = true;
				String[] labels = product.split(",");
				String name = labels[0];
				long code = Long.parseLong(labels[1]);
				int pcs = Integer.parseInt(labels[2]);
				String date = labels[3];

				Items items = new Items(name, code, pcs, date);

				for (int i = 0; i < productList.size(); i++) {
					if ((name.equalsIgnoreCase(productList.get(i).itemName)) && (code == productList.get(i).code)
							&& (date.equalsIgnoreCase(productList.get(i).expirationDate))) {

						productList.get(i).quantity = productList.get(i).quantity + pcs;
						isLine = false;
					} else {
					}
				}
				if (isLine) {
					productList.add(items);
				}
			}
		} catch (Exception e) {
			System.out.println("File not found: " + e.getMessage());
		}
	}

}
