package entity;

public class Offers {
	/* OFFERS:
	 	3x2 on apples
		1 Orange free for every 2 Pears
		1€ off 4€ on Pears
	 */
	private Purchases trigger;
	private String name;
	private Purchases prize;
	
	public Purchases getTrigger() {
		return trigger;
	}
	public void setTrigger(Purchases trigger) {
		this.trigger = trigger;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Purchases getPrize() {
		return prize;
	}
	public void setPrize(Purchases prize) {
		this.prize = prize;
	}
	
}
