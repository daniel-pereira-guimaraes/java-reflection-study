package querybuilder;

public class QueryBuilderTest {
	
	public static void main(String[] args) {
		
		final QueryBuilder queryBuilder = new QueryBuilder();
		final String sql = queryBuilder.buildSQL(SaleItem.class);
		System.out.println(sql);
		
	}

}
