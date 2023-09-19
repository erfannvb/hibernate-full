package nvb.dev.constant;

public class DbConstant {

    private DbConstant() {
    }

    public static final String DB_DRIVER = "org.postgresql.Driver";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/hibernate-full-db";
    public static final String DB_USER = "postgres";
    public static final String DB_PASS = "123456789Erfan";
    public static final String DB_POOL_SIZE = "20";
    public static final String DB_HBM2DDL_AUTO = "update";
    public static final String DB_SHOW_SQL = "true";
    public static final String DB_DIALECT = "org.hibernate.dialect.PostgreSQLDialect";

}
