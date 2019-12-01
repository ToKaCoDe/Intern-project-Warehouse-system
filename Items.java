package Warehouse;

public class Items implements Comparable<Items> {

	String itemName;
	long code;
	int quantity;
	String expirationDate;

	public Items(String itemName, long code, int quantity, String expirationDate) {

		this.itemName = itemName;
		this.code = code;
		this.quantity = quantity;
		this.expirationDate = expirationDate;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public int compareTo(Items o) {

		return this.itemName.compareToIgnoreCase(o.itemName);

	}

}
