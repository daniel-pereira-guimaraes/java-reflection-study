package querybuilder;

public class QueryBuilderTest {
	
	public static void main(String[] args) {
		
		final QueryBuilder queryBuilder = new QueryBuilder();
		System.out.println(queryBuilder.buildSQL(Sale.class));
		System.out.println();
		System.out.println(queryBuilder.buildSQL(SaleItem.class));
		
	}

}
