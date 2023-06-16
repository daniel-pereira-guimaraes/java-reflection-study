package querybuilder;

import querybuilder.Annotations.Column;
import querybuilder.Annotations.Table;

@Table(value = "SALE_ITEM", alias = "SAIT")
public class SaleItem {
	
	@Column("SAIT_ID")
	private Long id;
	
	@Column(value = "PROD_NAME", 
			formula = "(SELECT P.PROD_NAME FROM PRODUCT PROD WHERE PROD.PROD_ID = SAIT.PROD_ID)")
	private String name;
	
	@Column("SAIT_QUANTITY")
	private Integer quantity;
	
	@Column("SAIT_PRICE")
	private Double price;
	
	@Column("SAIT_DISCOUNT")
	private Double discount;
	
	@Column(value = "SAIT_TOTAL", formula = "SAIT.SAIT_QUANTITY * SAIT.SAIT_PRICE")
	private Double total;

}
