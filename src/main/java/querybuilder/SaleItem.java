package querybuilder;

import querybuilder.Annotations.Column;
import querybuilder.Annotations.Table;

@Table(name = "SALE_ITEM", alias = "SAIT")
public class SaleItem {
	
	@Column(name = "SAIT_ID")
	private Long id;
	
	@Column(name = "PROD_NAME", 
			formula="(SELECT P.PROD_NAME FROM PRODUCT PROD WHERE PROD.PROD_ID = SAIT.PROD_ID)")
	private String name;
	
	@Column(name = "SAIT_QUANTITY")
	private Integer quantity;
	
	@Column(name = "SAIT_PRICE")
	private Double price;
	
	@Column
	private Double discount;
	
	@Column(name = "SAIT_TOTAL", formula = "SAIT.SAIT_QUANTITY * SAIT.SAIT_PRICE")
	private Double total;
	

}
