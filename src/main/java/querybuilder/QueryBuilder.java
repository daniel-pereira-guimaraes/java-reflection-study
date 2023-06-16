package querybuilder;

import java.lang.reflect.Field;

import querybuilder.Annotations.Column;
import querybuilder.Annotations.Table;

public class QueryBuilder {

	public String buildSQL(Class<?> entityClass) {
		
		final Table table = entityClass.getAnnotation(Table.class);
		if (table == null) {
			return null;
		}

		final StringBuilder sb = new StringBuilder("SELECT");
		
		String tableName = trim(table.name());
		if (isEmpty(tableName)) {
			tableName = entityClass.getSimpleName();
		}
		final String tableAlias = trim(table.alias());

		boolean hasField = false;
		final Field[] fields = entityClass.getDeclaredFields();
		for (Field field : fields) {
			
			final String fieldName = field.getName();
			final Column column = field.getAnnotation(Column.class);
			if (column != null) {
				
				boolean hasFormula = true;
				String origin = trim(column.formula());
				if (isEmpty(origin)) {
					hasFormula = false;
					origin = trim(column.name());
					if (isEmpty(origin)) {
						origin = field.getName();
					}
				}
				
				if (hasField) {
					sb.append(',');
				}
				sb.append('\n').append('\t');
				if (!hasFormula && !isEmpty(tableAlias)) {
					sb.append(tableAlias).append('.');
				}
				sb.append(origin);
				if (!origin.equals(fieldName)) {
					sb.append(' ').append(fieldName);
				}
				hasField = true;
			}
		}

		if (hasField) {
			sb.append('\n');
		}
		else {
			sb.append(" * ");
		}
		
		sb.append("FROM ").append(tableName);
		if (!isEmpty(tableAlias) && !tableAlias.equals(tableName)) {
			sb.append(' ').append(tableAlias);
		}

		return sb.toString();
	}
	
	private String trim(String s) {
		return s == null ? null : s.trim();
	}
	
	private boolean isEmpty(String s) {
		return s == null || s.isEmpty();
	}
	
}
